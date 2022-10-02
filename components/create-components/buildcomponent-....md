# 📏 buildComponent { ... }

{% hint style="warning" %}
**NOTE!** The #buildComponent function is not part of the Stacked DSL! This function is a Component-Builder function, like the others, provided by the Kotlin Standard Library
{% endhint %}

This function just creates a Builder, out of the provided base parameter, applies the builder process, and returns the built Component, out of the temporary building.&#x20;

The recommended way to create TextComponents via Stacked is with the [`text { ... }`](text-....md) function. This is the Stacked DSL, which should be used in Stacked.
