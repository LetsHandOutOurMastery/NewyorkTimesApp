# NyTimesArticleApp

|<img width="250" alt="Screenshot_1625865314" src="https://user-images.githubusercontent.com/5355440/125136652-4ea98b00-e11c-11eb-950a-b1a01c6a14e9.png">
<img width="250" alt="Screenshot_1625816182" src="https://user-images.githubusercontent.com/5355440/125041754-2a14cb00-e0aa-11eb-949e-9dc4721d74aa.png">

## About
It loads NyTimes most popular articles and then displays in a list.
- Supports offline first architecture. 
- Display the thumbnail in a circular layout
- Loads details of article in a [WebView](https://developer.android.com/guide/webapps/webview)
- Added search capability

***To Install and test latest v1.0 app follow below link***

[![NyTimes Article App](https://img.shields.io/badge/V1.0-Article%20App-green)](https://github.com/roymithun/NyTimesArticleApp/releases/download/v1.0/app-release.apk)

## Pre-requisites
Most Popular API from [nytimes developer](https://developer.nytimes.com/apis) have been used.

For example: https://api.nytimes.com/svc/mostpopular/v2/mostviewed/all-sections/7.json?api-key=yourkey

Where your_key has to be generated following [get-started](https://developer.nytimes.com/get-started) page from nytimes.

Once you have yourkey, please add a new entry in **local.properties** inside your project directory. Since local.properties is added in [.gitignore](https://github.com/roymithun/NyTimesArticleApp/blob/master/.gitignore). it is never added to VCS. This way your appKey can be kept somehow secured.
- **appKey = your_key**

## Built With 🛠
- [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - For asynchronous programming.
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Data objects that notify views when the underlying database changes.
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes. 
  - [ViewBinding](https://developer.android.com/topic/libraries/view-binding) - Generates a binding class for each XML layout file present in that module and allows you to more easily write code that interacts with views.
  - [Room](https://developer.android.com/topic/libraries/architecture/room) - SQLite object mapping library.
- [Dependency Injection](https://developer.android.com/training/dependency-injection) -
  - [Hilt-Dagger](https://dagger.dev/hilt/) - Standard library to incorporate Dagger dependency injection into an Android application.
  - [Hilt-ViewModel](https://developer.android.com/training/dependency-injection/hilt-jetpack) - DI for injecting `ViewModel`.
- [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java.
- [Moshi](https://github.com/square/moshi) - A modern JSON library for Kotlin and Java.
- [Moshi Converter](https://github.com/square/retrofit/tree/master/retrofit-converters/moshi) - A Converter which uses Moshi for serialization to and from JSON.
- [Coil-kt](https://coil-kt.github.io/coil/) - An image loading library for Android backed by Kotlin Coroutines.

# Package Structure
    
    com.inhouse.nytimesarticleapp    # Root Package
    .
    ├── data                      # For data handling.
    │   ├── local                 # Local Persistence Database. Room (SQLite) database
    |   │   ├── dao               # Data Access Object for Room   
    |   |   ├── database          # Room database
    |   |   └── typeconverter     # Type converter
    │   ├── remote                # Remote Data Handlers     
    |   │   ├── api               # Retrofit API for remote end point.
    │   └── repository            # Single source of data.
    |
    ├── model                     # Model classes acting as entities for Room
    |
    ├── di                        # Dependency Injection             
    │   ├── datamodule            # dao provider
    │   └── networkmodule         # network interface and moshi provider       
    |
    ├── ui                        # Activity/View layer
    │   ├── mainactivity          # Base View
    │   ├── main                  # Article List Screen & ViewModel
    |   │   ├── adapter           # Adapter for RecyclerView
    |   |   ├── di                # Dependency Injection in ui
    |   |   |   └── articlemodule # ViewModelComponenet module
    |   |   ├── fragment          # Article list Fragment
    |   │   └── viewmodel         # ViewHolder for RecyclerView   
    │   └── details               # Detail Screen Fragment
    |
    └── utils                     # Utility Classes / Kotlin extensions

## Architecture
This app uses [***MVVM (Model View View-Model)***](https://developer.android.com/jetpack/docs/guide#recommended-app-arch) architecture.

<img width="600" alt="final-mvvm-architecture" src="https://developer.android.com/topic/libraries/architecture/images/final-architecture.png">
