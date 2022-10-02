---
description: You use the Stacked DSL? Now this can be interesting for you!
---

# ⚒ Builder extensions

Stacked provides some additional extension functions, to easily, quickly, and consistently build components with builders, which are provided in such functions as `text { ... }` and `buildComponent { ... }`.



{% tabs %}
{% tab title="Simple additions" %}
We have the following simple additions:



### Builder#newlines(<mark style="background-color:blue;">amount</mark>: int)

With this function, you can easily append <mark style="background-color:blue;">x</mark>-amount of `Component.newline()`s to your current builder



### Builder#space

With this function, you can quickly append a `Component.space()` to your current builder



### Builder#spaces(<mark style="background-color:green;">amount</mark>: Int)

With this function, you can easily append <mark style="background-color:green;">x</mark>-amount of [`space()`](builder-extensions.md#builder-space)s to your current builder
{% endtab %}
{% endtabs %}

{% hint style="danger" %}
The following API specification will be slightly changed when the future Kotlin context-receiver API gets into a usable state!
{% endhint %}

You have also the possibility, to use operator functions to add content to your current builder state:

{% tabs %}
{% tab title="Builder + #" %}
The operator functions provided to add content are all `infix operator fun`ctions, with a `Builder` as the receiver.

We have the following options available to choose from:

* Builder.plus(styledString: String) <- _A_ [_styled String via MiniMessage_](../convert-components.md) _is used!_
* Builder.plus(component: ComponentLike)
* Builder.plus(components: Iterable\<ComponentLike>)



But there is more, not only you can add content via the plus operators, but you can also set ClickEvents, Colors, and Styles of the current Builder:

* Builder.plus(clickEvent: ClickEvent?)
* Builder.plus(color: TextColor?)
* Builder.plus(style: Style)
{% endtab %}
{% endtabs %}



