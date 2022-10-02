# 📋 Titles

Titles should support Styled-Strings and instead of Java-Durations, Kotlin-Durations, because:

> We all know for sure, the Kotlin-Duration API is the best duration API!



## Title-Constructor

Stacked introduces 2 constructor-like functions, all called Title, but each has its own parameters!

The first is a function with the following parameters:

* title: ComponentLike
* subtitle: ComponentLike
* times: AdventureTimes? = null

The second one is a function with the following parameters:

* styledTitle: String <- **A Styled-String!**
* styledSubtitle: String <- **A Styled-String!**
* times: AdventureTimes? = null

With these 2 functions, you can quickly create Title, but the other part of the improvement comes here:

## AdventureTimes-Constructor

Stacked also introduces a constructor-like function called Times. This function returns an AdventureTimes object, which, for example, you can use in the functions above!

What makes this function unique, is, that you can define the fadeIn, stay, and fadeOut durations as Kotlin-Durations instead of Java-Durations!
