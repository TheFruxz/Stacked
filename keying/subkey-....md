---
description: A way to create keys, linked to other keys
---

# 🛤 subKey(...)

## The `subKey` extension function

{% hint style="info" %}
A **subKey** extension function is also available for the Sparkle framework, but it is mainly for NamespacedKeys instead of Adventures Key!
{% endhint %}

Sometimes you have a sub-object of another object, for example, a module of a system.

In this example, the system has a Key-based identity and the modules themselves have all their own Keys. To quickly create a new key of the key, provided by the main system, you can choose to use the `subKey` extension function on the key of the main system.

```kotlin
val mainSystemKey = Key.key("namespace", "value")
val moduleKey = mainSystemKey.subKey("module1" /*strategy = PATHING*/)

// mmainSystemKey = Key("namespace", "value")
// moduleKey = Key("namespace", "value.module1")
```

{% hint style="danger" %}
**NOTE!** The input of the `subKey`extension function must also meet the requirements set by the [regex.md](regex.md "mention")
{% endhint %}

With this behavior, this function can help to create a structure of Main-components and sub-components, fully identifiable, via their identity key!

## The different strategies

But sometimes you want, that the result of the subKey extension function is quite different, or you have a focus on other information, so then you need to set the strategy parameter.

As the default value of the strategy, used inside this function, is **PATHING**.

Here is a small table, shortly describing the features and issues with the strategies:

<table><thead><tr><th align="right">Strategy</th><th data-type="rating" data-max="3">Compact</th><th data-type="rating" data-max="3">Traceable</th><th data-type="rating" data-max="3">Origin Visibility</th></tr></thead><tbody><tr><td align="right"><a href="subkey-....md#the-pathing-technique">PATHING</a></td><td>1</td><td>3</td><td>3</td></tr><tr><td align="right"><a href="subkey-....md#undefined">SQUASH</a></td><td>1</td><td>3</td><td>3</td></tr><tr><td align="right"><a href="subkey-....md#undefined">ORIGIN</a></td><td>3</td><td>1</td><td>3</td></tr><tr><td align="right"><a href="subkey-....md#undefined">CONTINUE</a></td><td>3</td><td>2</td><td>1</td></tr></tbody></table>



### The `PATHING` technique

The pathing technique always contains the full path and is so fully traceable. The origin is always inside the namespace and is so always visible.

An example, how the path works:

```kotlin
val key0 = Key.key("origin", "value")
val key1 = key0.subKey("1", PATHING)
val key2 = key1.subKey("2", PATHING)
val key3 = key2.subKey("3", PATHING)

// key0 = Key("origin", "value")
// key1 = Key("origin", "value.1")
// key2 = Key("origin", "value.1.2")
// key3 = Key("origin", "value.1.2.3")
```

### The `SQUASH` technique

The squashing technique always contains the full path and is so fully traceable. The origin is always inside the namespace's start and is so always visible.

An example, of how the path works:

```kotlin
val key0 = Key.key("origin", "value")
val key1 = key0.subKey("1", SQUASH)
val key2 = key1.subKey("2", SQUASH)
val key3 = key2.subKey("3", SQUASH)

// key0 = Key("origin", "value")
// key1 = Key("origin_value", "1")
// key2 = Key("origin_value_1", "2")
// key3 = Key("origin_value_1_2", "3")
```

### The `ORIGIN` technique

The origin technique always keeps the origin but does not save a path at all. This makes it incredibly hard to trace the way back to the origin itself, but it is still easy to find out the origin.

An example, of how the path works:

```kotlin
val key0 = Key.key("origin", "value")
val key1 = key0.subKey("1", ORIGIN)
val key2 = key1.subKey("2", ORIGIN)
val key3 = key2.subKey("3", ORIGIN)

// key0 = Key("origin", "value")
// key1 = Key("origin", "1")
// key2 = Key("origin", "2")
// key3 = Key("origin", "3")
```

### The `CONTINUE` technique

The continue technique replaces the namespace with the direct parent of this object. The value represents always the current one. So, the origin is not visible, but with all keys you can fully trace back to the origin.

```kotlin
val key0 = Key.key("origin", "value")
val key1 = key0.subKey("1", CONTINUE)
val key2 = key1.subKey("2", CONTINUE)
val key3 = key2.subKey("3", CONTINUE)

// key0 = Key("origin", "value")
// key1 = Key("value", "1")
// key2 = Key("1", "2")
// key3 = Key("2", "3")
```
