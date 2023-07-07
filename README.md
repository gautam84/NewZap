![GitHub Cards Preview](https://github.com/gautam84/NewZap/blob/master/art/NewZap.jpg?raw=true)

# 📰 NewZap
NewZap is a simple MVVM app to fetch news and provide the users with latest information. It retrieves information from the server using news.org API and also stores news for the future.

## Screenshots 📱 
Home | Search | ForYou | Sections | Settings
--- | --- | --- | --- | --- 
![](https://github.com/gautam84/NewZap/blob/master/screenshots/Home.jpeg) | ![](https://github.com/gautam84/NewZap/blob/master/screenshots/Search.jpeg) | ![](https://github.com/gautam84/NewZap/blob/master/screenshots/ForYou.jpeg) | ![](https://github.com/gautam84/NewZap/blob/master/screenshots/Sections.jpeg) | ![](https://github.com/gautam84/NewZap/blob/master/screenshots/Settings.jpeg)


## Build With 🛠

- [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android
  development.
- [Jetpack Compose](https://developer.android.com/jetpack/compose) - Jetpack Compose is Android’s
  modern toolkit for building native UI.
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - A coroutine is a
  concurrency design pattern that you can use on Android to simplify code that executes
  asynchronously.
- [Flow](https://kotlinlang.org/docs/reference/coroutines/flow.html) - A flow is an asynchronous
  version of a Sequence, a type of collection whose values are lazily produced.
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) -
  Collection of libraries that help you design robust, testable, and maintainable apps.
  - [Stateflow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow) - StateFlow is a
    state-holder observable flow that emits the current and new state updates to its collectors.
  - [Flow](https://kotlinlang.org/docs/reference/coroutines/flow.html) - A flow is an asynchronous
    version of a Sequence, a type of collection whose values are lazily produced.
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores
    UI-related data that isn"t destroyed on UI changes.
  - [Jetpack Compose Navigation](https://developer.android.com/jetpack/compose/navigation) - The
    Navigation component provides support for Jetpack Compose applications.
- [Retrofit](https://square.github.io/retrofit/) -  Retrofit is type-safe REST client for Android and Java which aims to make it easier to consume RESTful web services. Retrofit is type-safe REST client for Android and Java which aims to make it easier to consume RESTful web services.
- [Dagger-Hilt](https://developer.android.com/training/dependency-injection/hilt-android) - Hilt is a dependency injection library for Android that reduces the     boilerplate of doing manual dependency injection in your project. Doing manual dependency injection requires you to construct every class and its                 dependencies by hand, and to use containers to reuse and manage dependencies.
- [Room](https://developer.android.com/training/data-storage/room) - The Room persistence library provides an abstraction layer over SQLite to allow fluent database access while harnessing the full power of SQLite.
- [Material Components for Android](https://github.com/material-components/material-components-android)
  - Modular and customizable Material Design UI components for Android.
- [Figma](https://figma.com/) - Figma is a vector graphics editor and prototyping tool which is
  primarily web-based.
  
  
## Back-end 🛰️
The app uses retrofit to retrieve data from [News API](https://newsapi.org/). For more information, read the [documentation](https://newsapi.org/docs). You would need to log in to get an API key. Paste the API key in ``` app/src/main/java/com/example/newzap/util/Constants.kt ```

## Architecture 🗼

This app uses [***MVVM (Model View
View-Model)***](https://developer.android.com/jetpack/docs/guide#recommended-app-arch) architecture.

## Build-tool 🧰
You need to have [Android Studio Beta 3 or above](https://developer.android.com/studio/preview) to build this project.

## Contact 📩

Wanna reach out to me? DM me at 👇

Drop a mail to:- gautamhazarika01@gmail.com

## Donation 💰

If this project help you reduce time to develop, you can give me a cup of coffee :)

<a href="https://www.buymeacoffee.com/gautam.hz" target="_blank"><img src="https://www.buymeacoffee.com/assets/img/custom_images/yellow_img.png" alt="Buy Me A Coffee" style="height: 41px !important;width: 174px !important;box-shadow: 0px 3px 2px 0px rgba(190, 190, 190, 0.5) !important;-webkit-box-shadow: 0px 3px 2px 0px rgba(190, 190, 190, 0.5) !important;" ></a>


## License 🔖

```

MIT License

Copyright (c) 2023 Gautam Hazarika

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.



```

