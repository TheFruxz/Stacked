# ![Stacked Banner](https://user-images.githubusercontent.com/28064149/193399365-3801846c-2552-49c4-84b6-e060e7ce03dc.jpg)


<br>

## 👋 Welcome to Stacked
[![Build Library](https://github.com/TheFruxz/Stacked/actions/workflows/build-Stacked.yml/badge.svg)](https://github.com/TheFruxz/Stacked/actions/workflows/build-Stacked.yml)
[![Publish Library](https://github.com/TheFruxz/Stacked/actions/workflows/publish-Stacked.yml/badge.svg)](https://github.com/TheFruxz/Stacked/actions/workflows/publish-Stacked.yml)

Stacked is an assistive library, which provides helpful and easy to use tools, to work with the Adventure API.
It is designed, to perfectly fit into the Adventure API used at a Paper Environment.

## ⚙️ Setup

### Repository

How can I use Stacked in my own projects? For this, you need to know what your project is based on, or should be based on.
We ourselves recommend that you use `Gradle Kotlin` in all your projects, but you can also use other systems like `Gradle` and `Maven`!

### Use in your Gradle Project

#### Repository
```kotlin
maven("https://nexus.fruxz.dev/repository/public/")
```

##### Dependency
```kotlin
implementation("dev.fruxz:stacked:$stackedVersion")
```
## 👥 Contribution

Of course, you can also participate in Stacked and contribute to the development. However, please follow all community and general guidelines of GitHub and the repositories. You also have to respect the licenses set in this repository as well as in other repositories.

If you have any questions, suggestions or other items you would like to contribute to Stacked or just discuss, check out the Discussions' section of this repository, where you will find the respective areas where you can create your own questions or join in discussions on other things.

## Example

By using the text function, you can easily create new text components. With the unaryPlus (+) operator, you can attach some components, or styled strings using the MiniMessage format to this component with ease!

```kotlin
    text {
        + "<rainbow>WOW! This is amazing!</rainbow>"
        + " "
        + text {
            + "CLICK" { bold().dyeYellow() }
            + " ME" { italic().dyeYellow() }
            hover { text("This is hovering!") }
        }
    }
```

###### We build & use Stacked on Java 17 - [Eclipse Temurin](https://adoptium.net/).
###### Also build & run Stacked with [Eclipse Temurin](https://adoptium.net/) to get the best possible experience!
