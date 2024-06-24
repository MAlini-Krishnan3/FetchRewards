GET API-  https://fetch-hiring.s3.amazonaws.com/hiring.json.

This is a simple native app in Kotlin designed using MVVM architecture. The app displays a list of items from the API to the user based on the following requirements:

- Display all the items grouped by "listId"
- Sort the results first by "listId" then by "name" when displaying.
- Filter out any items where "name" is blank or null.

Technologies/ Frameworks used-
- Dagger- Dependency Injection
- MVVM
- Kotlin
- Activities and Fragments
- Recycler Views and Caching using DiffUtil
- RxJava
