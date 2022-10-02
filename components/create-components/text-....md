---
description: The Stacked DSL
---

# 📐 text { ... }

{% hint style="warning" %}
Currently, the Stacked DSL is mostly accessible via the `this + TextComponent` style. We are waiting for the Kotlin context(...) receiver to get to a non-prototype stage, to remove the requirement of the `this` keyword.
{% endhint %}

{% hint style="info" %}
Styled-Strings are heavily used inside the Stacked DSL, look into the [Convert Components](../convert-components.md) Section to learn more about Styled-Strings
{% endhint %}

To start a new component, you can choose to create it via one of the multiple text functions

> In the directly following examples we use (Builder) as the short form for
>
> `Builder.() -> Unit = { }`!&#x20;

{% tabs %}
{% tab title="text(String, (Builder))" %}
This function allows you to insert a pass a Styled-String as the first required parameter and additionally you can choose to edit this String inside the optional second builder process parameter.
{% endtab %}
{% endtabs %}

{% tabs %}
{% tab title="text(ComponentLike, (Builder))" %}
This function is quite like the [text(String, (Builder))](text-....md#text-string-builder) function, but instead of using a Styled-String as the base, on which the builder will be created, a ComponentLike object is used.
{% endtab %}
{% endtabs %}

{% tabs %}
{% tab title="text(Builder, (Builder))" %}
This function is like the [text(String, (Builder))](text-....md#text-string-builder) function, but instead of using a Styled-String as the base, which will be transformed into a builder, a Builder is directly passed as the parameter of the function, which will also be used to apply the builder process parameter onto it.
{% endtab %}
{% endtabs %}

{% tabs %}
{% tab title="text(builder: Builder.() -> Unit)" %}
This function is like the [text(ComponentLike, (Builder))](text-....md#text-componentlike-builder) function, but instead of using an input-Parameter as the base of the Component, a `Component.empty()` is used instead. So no base parameter is provided in this function.
{% endtab %}
{% endtabs %}
