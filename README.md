# NYT Article App


A simple app to display NY Times Most Popular Articles API. Core functionality added in the app,
* Show a list of articles
* Shows details when any of the list item is tapped. 
* Sliding side drawer for navigation. 
* Following API is used for loading data in the app,
http://api.nytimes.com/svc/mostpopular/v2/viewed/{section}/{period}.json?api-key='sample-key' To test this API, 
* 1,7, and 30 days period is used for testing the URL

### Architecture:
This project used MVVM Design pattern and Jetpack componenets and is fully developed in Kotlin language.


### Libraries Used

- [Jetpack](https://developer.android.com/jetpack) - Jetpack is a suite of libraries to help developers follow best practices, reduce boilerplate code, and write code that works consistently across Android versions and devices.
- [Retrofit](https://square.github.io/retrofit/) - Networking library which makes API integration easier.
- [Glide](https://bumptech.github.io/glide/) - A powerful image loading and caching library which is memory efficient and fast.
- [Koin](https://insert-koin.io/) - Koin is a pragmatic lightweight dependency injection framework for Kotlin developers to whom we will give the responsibility to instantiate the different objects of our application.
- [Coroutines](https://developer.android.com/kotlin/coroutines) - A tool for symplifying asychronous task which is light-weight and works without flaws with Jetpack.
- [Material Design support libraries](https://material.io/develop/android/docs/getting-started) - Modern UI designing library for modern apps.


## Installation

* Cloning this repository and import the project in Android Studio by moving to File->new->import project
* After the gradle sync complete, connect a physical device or use Android emulator. 
* Run the app by clicking on the play button on top bar, or by pressing ctrl+R buttons.


### Running The Tests 

Follow the steps to get test case reports:
* Move to test packages in Android Studio (Java test packages)
* Select the package by right clicking, select more run option then select 'run Tests in package with coverage'
* Test results will be shown after the executions are finished.

# MIT License

Copyright 2021

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.