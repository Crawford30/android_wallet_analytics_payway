# wallet-analytics-payway

# MVVM Architecture

### 1. **Model-View-ViewModel (MVVM)**
-> This architectural pattern separates an application into three main components: 
      a. **Model** (data and business logic), 
      b. **View** (UI and presentation logic), and 
      c. **ViewModel** (communication link between Model and View). 
      
    Hence used MVVM to promote a clean and modular code structure.


### 2. **Single Activity - Multiple Fragments**
_**Single Activity Approach**_
-> Having a single activity as the core of the application provides several benefits, such as improved navigation and a more consistent user experience. Fragments are then used for different UI components, providing modular and reusable building blocks.

### 3. **Navigation Components**
-> Navigation Architecture Component -> The use of Navigation Components simplifies the implementation of navigation in the app. It provides a visual and structured way to navigate between different destinations (fragments) and helps manage the navigation stack efficiently.

### 4. **Dependency Injection (Hilt and Dagger)**
-> Hilt: Hilt is a dependency injection library for Android that builds on top of Dagger. It simplifies the integration of Dagger into Android apps. 
-> Using Hilt allows for a more straightforward and concise setup of dependency injection, making the codebase more maintainable and testable.

### 5. **Paging Library 3**
-> Paging Library: The Paging library is used for efficient loading of large data sets in a paginated manner.
-> It seamlessly integrates with RecyclerView, allowing for smooth scrolling and optimized memory usage. This provides a better user experience when dealing with large data sets, especially in scenarios like endless scrolling. (as seen in the All Transactions Screen)

### 6. **Retrofit**
-> Retrofit serving as the communication bridge between  Android application and backend server, facilitating seamless API interactions. 
