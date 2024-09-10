<h1 align="center">Forecast App Task</h1>





<p align="center">  
A forecast app that provides weather updates, leveraging modern Android development tools and libraries such as Retrofit, and Coroutines for seamless data fetching and UI rendering
</p>
</br>




<p align="center">
<img src="/wiki/screenshot.jpg"/>
</p>

## Download
Go to the [Releases](https://github.com/alihrhera/AppTask-forecast/blob/master/wiki/app-1.0.0.apk) to download the latest APK.



## Tech stack & Open-source libraries
- Minimum SDK level 21
- [Kotlin](https://kotlinlang.org/) based, [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous.
- [Hilt](https://dagger.dev/hilt/) for dependency injection.
- JetPack
    - Lifecycle - dispose of observing data when lifecycle state changes.
    - ViewModel - UI related data holder, lifecycle aware.
    - Room Persistence - construct a database using the abstract layer.
- Architecture
    - MVVM Architecture (View - DataBinding - ViewModel - Model)
    - [Bindables](https://github.com/skydoves/bindables) - Android DataBinding kit for notifying data changes to UI layers.
    - Repository pattern
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit) - construct the REST APIs and paging network data.
- [Material-Components](https://github.com/material-components/material-components-android) - Material design components like ripple animation, cardView.

## Data flow
Forecast app is data flow.

![data_flow](/wiki/data_flow.jpg)

## Architecture
Forecast app is based on MVVM architecture and a repository pattern.

![architecture](/wiki/architecture.jpg)

