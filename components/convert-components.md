# ♻ Convert Components

To be able to conveniently and easily convert Strings to components and components to Strings, Stacked provides some nice properties and functions, to provide this kind of easy-to-use solution.

## Serializer

To make this officially happen, we use some serializers.

<table><thead><tr><th align="right">Serializer</th><th data-type="checkbox">MiniMessage</th><th data-type="checkbox">Formatting</th><th data-type="select" data-multiple>Creation-Formats</th></tr></thead><tbody><tr><td align="right"><code>miniMessageSerializer</code></td><td>true</td><td>true</td><td></td></tr><tr><td align="right"><code>strictMiniMessageSerializer</code></td><td>true</td><td>true</td><td></td></tr><tr><td align="right"><code>adventureSerializer</code></td><td>false</td><td>true</td><td></td></tr><tr><td align="right"><code>plainAdventureSerializer</code></td><td>false</td><td>false</td><td></td></tr></tbody></table>

<details>

<summary><code>adventureSerializer</code></summary>

The adventure serializer is the serializer, that you would consider as a 'normal' serializer. This serializer is compatible with hexColors and URLs.

</details>

<details>

<summary><code>plainAdventureSerializer</code></summary>

The plain adventure serializer is just there, to get the raw string of the components, without any styling or nice-features. Just the plain text.

</details>

<details>

<summary><code>miniMessageSerializer</code></summary>

The miniMessage serializer uses the [MiniMessage](https://docs.adventure.kyori.net/minimessage/format.html)-Format to define the style, hover, and other actions, via HTML-like tags.

</details>

<details>

<summary><code>strictMiniMessageSerializer</code></summary>

The strict miniMessage serializer is like the [miniMessageSerializer](convert-components.md#minimessageserializer), but only with the strict option enabled. This forces us to define not only the open-tag but also the close tags. This serializer is primarily used for outputs, to be more explicit!&#x20;

</details>

These are the serializers and configurations, that Stacked uses for its APIs. These are primarily used in the following stuff:

## Conversion

### String to Component

#### `String#asComponent`

This computational property converts the provided String in the `this` context to a TextComponent, using the adventureSerializer's `deserializeOr` function.

{% hint style="success" %}
This is a direct wrap of the String into the Component, without any special result returned, to work with.
{% endhint %}

#### `String#asComponent(builder)`

This function does the following:

1. This function is utilizing the String#asComponent value, to convert the String into a perfect Component wrap of this string.
2. Then the built component is transformed into a Builder using the `toBuilder` function.
3. After the builder parameter is applied, which allows, for modify the builder and its state.
4. After that, the Builder gets built via the `build` function and the result component of that gets returned.

#### `String#asComponents`

This function is like the asComponent computational value, but instead of converting the whole String, it converts each line, specified by the String#lines function of the Kotlin Standard Library.

The result is, each line of the String converted into a Component, collected inside a new List of TextComponents

#### `Iterable<String>#asComponents`

This function converts every String, contained in this Iterable, into an individual Component, returned as a new List.

### Styled-String to Component <a href="#styled-strings" id="styled-strings"></a>

{% hint style="danger" %}
The miniMessage Format, used inside the Styled-String functions/properties, do not allow legacy colors! If you migrate your existing code, that uses the legacy Colors (like '§' and ChatColor.%) these functions/properties will throw Exceptions!&#x20;
{% endhint %}

The StyledString methods are generally the same as the 'normal' Strings conversion. The key difference is, that they does not get transformed via the [adventureSerializer](convert-components.md#adventureserializer), but via the [miniMessageSerializers](convert-components.md#minimessageserializer), so that they have all miniMessage support, which makes the Strings 'styled'!

### (Text)Components to Strings

The opposite transformation is generally the same as the String -> Components conversion but reversed. Nothing really special!
