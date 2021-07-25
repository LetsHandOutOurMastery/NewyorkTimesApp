# New York Times App

|<img width="250" alt="Screenshot_1625865314" src="https://raw.githubusercontent.com/LetsHandOutOurMastery/NewyorkTimesApp/master/screenshots/darkmode.jpeg">
<img width="250" alt="Screenshot_1625816182" src="https://raw.githubusercontent.com/LetsHandOutOurMastery/NewyorkTimesApp/master/screenshots/lightmode.jpeg">

## About this app

# This app follows Clean Architecture.

<img width="600" alt="clean_arch" src="https://koenig-media.raywenderlich.com/uploads/2019/06/Clean-Architecture-graph.png">

# This app follows Single Source of Truth.

<img width="800" alt="ssot" src="https://google-developer-training.github.io/android-developer-fundamentals-course-concepts-v2/images/10-1-c-room-livedata-viewmodel/dg_architecture_comonents.png">

This app uses Most Popular API from [nytimes developer](https://developer.nytimes.com/apis).

For example: https://api.nytimes.com/svc/mostpopular/v2/mostviewed/all-sections/7.json?api-key=yourkey

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
