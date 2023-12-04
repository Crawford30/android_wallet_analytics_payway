# wallet-analytics-payway

# Wallet Analytics With MVVM Architecture

### 1. **Model-View-ViewModel (MVVM)**
-> This architectural pattern separates an application into three main components: <br />
      a. **Model** (data and business logic), <br />
      b. **View** (UI and presentation logic), and <br />
      c. **ViewModel** (communication link between Model and View). <br />
      
Hence used MVVM to promote a clean and modular code structure.<br />


### 2. **Single Activity - Multiple Fragments**
_**Single Activity Approach**_<br />
-> Having a single activity as the core of the application provides several benefits, such as improved navigation and a more consistent user experience. <br />
-> Fragments are then used for different UI components, providing modular and reusable building blocks.

### 3. **Navigation Components**
-> Navigation Architecture Component <br />
-> The use of Navigation Components simplifies the implementation of navigation in the app.<br />
-> It provides a visual and structured way to navigate between different destinations (fragments) and helps manage the navigation stack efficiently.<br />

### 4. **Dependency Injection (Hilt and Dagger)**<br />
-> Hilt: Hilt is a dependency injection library for Android that builds on top of Dagger. It simplifies the integration of Dagger into Android apps. <br />

-> Using Hilt allows for a more straightforward and concise setup of dependency injection, making the codebase more maintainable and testable.<br />

### 5. **Paging Library 3**
-> Paging Library: The Paging library is used for efficient loading of large data sets in a paginated manner.<br />
-> It seamlessly integrates with RecyclerView, allowing for smooth scrolling and optimized memory usage.<br />
-> This provides a better user experience when dealing with large data sets, especially in scenarios like endless scrolling. (as seen in the All Transactions Screen)<br />

### 6. **Retrofit**
-> Retrofit serving as the communication bridge between  Android application and backend server, facilitating seamless API interactions. 
