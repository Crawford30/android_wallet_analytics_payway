
# PayWay Wallet Analytics With MVVM Architecture


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

### 7. **Screenshots**

###  **Dashboard**

![WhatsApp Image 2023-12-05 at 2 36 56 PM](https://github.com/Crawford30/android_wallet_analytics_payway/assets/30619160/0284d6b5-6a92-46ef-955d-e15186a92bde)

![WhatsApp Image 2023-12-05 at 2 36 55 PM](https://github.com/Crawford30/android_wallet_analytics_payway/assets/30619160/df7cafe0-7a9a-4bdb-b9ea-2fff72a01339)

![WhatsApp Image 2023-12-05 at 2 36 56 PM (1)](https://github.com/Crawford30/android_wallet_analytics_payway/assets/30619160/c8dff44b-0152-479e-8a52-c90a19d3fbdf)

###  **All Transactions Screen**
![WhatsApp Image 2023-12-05 at 2 36 57 PM](https://github.com/Crawford30/android_wallet_analytics_payway/assets/30619160/ed430734-e833-45e7-8577-a940ed30b078)

###  **Chart Screen**
![WhatsApp Image 2023-12-05 at 2 36 58 PM (1)](https://github.com/Crawford30/android_wallet_analytics_payway/assets/30619160/7c613d22-d79d-4635-a82f-064c73d0bef7)


![WhatsApp Image 2023-12-05 at 2 36 58 PM (2)](https://github.com/Crawford30/android_wallet_analytics_payway/assets/30619160/783a9e9e-b05d-4d8b-b5e4-9b96005747cf)


![WhatsApp Image 2023-12-05 at 2 36 58 PM](https://github.com/Crawford30/android_wallet_analytics_payway/assets/30619160/d11b14f7-5e91-465a-8b02-a54dfd858f4e)


###  **Excel Downloaded File**

![WhatsApp Image 2023-12-05 at 4 47 19 PM](https://github.com/Crawford30/android_wallet_analytics_payway/assets/30619160/6dbf7f68-00af-4a23-b2cc-dff1a349d97b)

