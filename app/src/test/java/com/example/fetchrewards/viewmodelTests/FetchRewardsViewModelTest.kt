package com.example.fetchrewards.viewmodelTests

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.fetchrewards.datarepo.FetchRewardsDataRepo
import com.example.fetchrewards.model.FetchRewardsModel
import com.example.fetchrewards.testUtil.TestUtils
import com.example.fetchrewards.viewmodel.FetchRewardsViewModel
import io.mockk.MockKAnnotations
import io.mockk.clearMocks
import io.mockk.every

import org.junit.Rule
import org.junit.rules.TestRule
import io.mockk.impl.annotations.MockK
import io.mockk.just
import io.mockk.runs
import io.mockk.spyk
import io.mockk.verify
import io.reactivex.Single
import io.reactivex.schedulers.TestScheduler
import io.reactivex.subjects.SingleSubject
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.util.concurrent.TimeUnit


class FetchRewardsViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @MockK
    private lateinit var dataRepository: FetchRewardsDataRepo

    private lateinit var viewModel: FetchRewardsViewModel

    @MockK
    private lateinit var itemsObserver: Observer<Map<Int, List<FetchRewardsModel>>>

    @MockK
    private lateinit var errorObserver: Observer<Boolean>

    private lateinit var testScheduler: TestScheduler

    companion object {
        private const val mockedResponseFileName = "fetch_rewards_mocked.json"
    }

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        viewModel = spyk(FetchRewardsViewModel(dataRepository))
        viewModel.itemsLiveData.observeForever(itemsObserver)
        viewModel.errorLiveData.observeForever(errorObserver)
        TestUtils.setUpRxSchedulers()

        every { itemsObserver.onChanged(any()) } just runs
        every { errorObserver.onChanged(any()) } just runs

        testScheduler = TestScheduler()
    }

    @After
    fun tearDown() {
        viewModel.itemsLiveData.removeObserver(itemsObserver)
        viewModel.errorLiveData.removeObserver(errorObserver)
        clearMocks(dataRepository, itemsObserver, errorObserver, viewModel)
    }

    @Test
    fun `fetchItems success`() {
        // Given
        val mockItems = TestUtils.getMockItemsFromJson(mockedResponseFileName)
        val singleSubject = SingleSubject.create<List<FetchRewardsModel>>()
        every { dataRepository.fetchRewardItems() } returns singleSubject

        // When
        viewModel.fetchItems()
        singleSubject.onSuccess(mockItems)
        testScheduler.advanceTimeBy(1, TimeUnit.SECONDS)

        // Then
        verify { itemsObserver.onChanged(match { it.isNotEmpty() }) }
        verify { errorObserver.onChanged(false) }
        verify { viewModel.fetchItems() }
    }

    @Test
    fun `fetchItems error`() {
        // Given
        val singleSubject = SingleSubject.create<List<FetchRewardsModel>>()
        every { dataRepository.fetchRewardItems() } returns singleSubject

        // When
        viewModel.fetchItems()
        singleSubject.onError(Exception("Network error"))
        testScheduler.advanceTimeBy(1, TimeUnit.SECONDS)

        // Then
        verify { itemsObserver.onChanged(emptyMap()) }
        verify { errorObserver.onChanged(true) }
    }

    // Test for correct sorting
    @Test
    fun `fetchItems correct sorting`() {
        // Given
        val mockItems = listOf(
            FetchRewardsModel(1, 1, "Item 100"),
            FetchRewardsModel(2, 1, "Item 10"),
            FetchRewardsModel(3, 1, "Item 20")
        )
        val singleSubject = SingleSubject.create<List<FetchRewardsModel>>()
        every { dataRepository.fetchRewardItems() } returns singleSubject

        // When
        viewModel.fetchItems()
        singleSubject.onSuccess(mockItems)
        testScheduler.advanceTimeBy(1, TimeUnit.SECONDS)

        // Then
        verify {
            itemsObserver.onChanged(match {
                it[1]?.get(0)?.name == "Item 10" &&
                        it[1]?.get(1)?.name == "Item 20" &&
                        it[1]?.get(2)?.name == "Item 100"
            })
        }
        verify { errorObserver.onChanged(false) }
    }
}