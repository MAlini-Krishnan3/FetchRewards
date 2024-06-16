package com.example.fetchrewards.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fetchrewards.FetchRewardsApplication
import com.example.fetchrewards.viewmodel.FetchRewardsViewModel
import com.example.fetchrewards.views.adapter.GroupedItemAdapter
import com.example.myapplication.databinding.FetchRewardsFragmentBinding
import javax.inject.Inject

class FetchRewardsFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val fetchRewardsViewModel: FetchRewardsViewModel by viewModels { viewModelFactory }
    private var _binding: FetchRewardsFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var groupAdapter: GroupedItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        (requireActivity().application as FetchRewardsApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FetchRewardsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        groupAdapter = GroupedItemAdapter()

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = groupAdapter
        }


    }

    override fun onResume() {
        super.onResume()

        fetchRewardsViewModel.fetchItems()

        fetchRewardsViewModel.itemsLiveData.observe(viewLifecycleOwner) { groupedItems ->
            groupAdapter.submitList(groupedItems.map { Pair(it.key, it.value) })
        }

        fetchRewardsViewModel.errorLiveData.observe(viewLifecycleOwner) { isError ->
            if (isError) {
                binding.errorTv.visibility = View.VISIBLE
            } else {
                binding.errorTv.visibility = View.GONE
            }
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}