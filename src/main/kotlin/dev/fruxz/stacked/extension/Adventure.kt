package dev.fruxz.stacked.extension

import dev.fruxz.stacked.StackedBuilder
import dev.fruxz.stacked.extension.api.StyledString
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.ComponentLike
import net.kyori.adventure.text.TextComponent
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver
import net.kyori.adventure.text.serializer.ComponentSerializer
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer

/**
 * This value represents the [LegacyComponentSerializer] instance, which
 * is used to convert between strings/objects and [Component]s.
 * @see ComponentLike.asString
 * @see String.asComponent
 * @see String.asStyledComponent
 * @author Fruxz
 * @since 1.0
 */
var adventureSerializer = LegacyComponentSerializer
	.builder().extractUrls().hexColors().build()

var plainAdventureSerializer: ComponentSerializer<Component, TextComponent, String> =
	PlainTextComponentSerializer.plainText()

/**
 * This value represents the [MiniMessage] instance, which
 * is used to convert between strings/objects and [Component]s.
 * This is especially adding the [String]-features like `<rainbow>`!
 * @see ComponentLike.asStyledString
 * @see String.asStyledComponent
 * @author Fruxz
 * @since 1.0
 */
var miniMessageSerializer: MiniMessage =
	MiniMessage.miniMessage()

var strictMiniMessageSerializer: MiniMessage =
	MiniMessage.builder().strict(true).build()

/**
 * This computational value converts this [ComponentLike]
 * into a [String] by using the [LegacyComponentSerializer],
 * provided by the [adventureSerializer] value.
 * @see adventureSerializer
 * @author Fruxz
 * @since 1.0
 */
val ComponentLike.asString: String
	get() = adventureSerializer.serialize(asComponent())

val ComponentLike.asPlainString: String
	get() = plainAdventureSerializer.serialize(asComponent())

/**
 * This computational value converts this [String] into a [TextComponent]
 * by using the [LegacyComponentSerializer], provided by the
 * [adventureSerializer] value.
 * @see adventureSerializer
 * @author Fruxz
 * @since 1.0
 */
val String.asComponent: TextComponent
	get() = adventureSerializer.deserializeOr(this, Component.text("FAILED", NamedTextColor.RED))!!

/**
 * This function converts this [String] into a [TextComponent]
 * by using the [LegacyComponentSerializer], provided by the
 * [adventureSerializer] value.
 * @see adventureSerializer
 * @author Fruxz
 * @since 2025.8
 * @param builder the process, to modify the component
 * @return the modified component as an [TextComponent]
 * @see StackedBuilder
 * @see String.asComponent
 */
inline fun String.asComponent(builder: StackedBuilder.() -> Unit) =
	Component.text().append(asComponent).toStackedBuilder().apply(builder).build()

/**
 * This computational value converts this [String] into a [TextComponent]
 * list (every list entry is a line) by using the [LegacyComponentSerializer],
 * provided by the [adventureSerializer] value.
 * @see adventureSerializer
 * @author Fruxz
 * @since 1.0
 */
val String.asComponents: List<TextComponent>
	get() = this.lines().asComponents

/**
 * This computational value converts this [Iterable] into a [TextComponent]
 * list (every list entry is a line) by using the [LegacyComponentSerializer],
 * provided by the [adventureSerializer] value.
 * @see adventureSerializer
 * @author Fruxz
 * @since 1.0
 */
val Iterable<String>.asComponents: List<TextComponent>
	get() = map { it.asComponent }

/**
 * This computational value converts this [ComponentLike]
 * into a [String] by using the [MiniMessage], provided by the
 * [miniMessageSerializer] value.
 * This is especially adding the [String]-features like `<rainbow>`!
 * @see miniMessageSerializer
 * @author Fruxz
 * @since 1.0
 */
@get:StyledString
val ComponentLike.asStyledString: String
	get() = strictMiniMessageSerializer.serialize(asComponent())

/**
 * This function converts this [ComponentLike] into a [String]
 * by using the [MiniMessage], provided by the
 * [miniMessageSerializer] value.
 * This is especially adding the [String]-features like `<rainbow>`!
 * @see miniMessageSerializer
 * @author Fruxz
 * @since 2025.8
 * @param serializer the [OpenMiniMessageSerializer] to use for serialization
 * @return the serialized string representation of the component
 * @see OpenMiniMessageSerializer
 * @see ComponentLike.asStyledString
 * @see strictMiniMessageSerializer
 */
@StyledString
fun ComponentLike.asStyledString(
    serializer: MiniMessage = strictMiniMessageSerializer,
) = serializer.serialize(asComponent())

/**
 * This computational value converts this [String] into a [TextComponent]
 * by using the [MiniMessage], provided by the
 * [miniMessageSerializer] value.
 * This is especially adding the [String]-features like `<rainbow>`!
 * @see miniMessageSerializer
 * @author Fruxz
 * @since 1.0
 */
val String.asStyledComponent: TextComponent
	get() = Component.text().append(miniMessageSerializer.deserializeOr(this, Component.empty())!!).build()

/**
 * This function converts this [String] into a [TextComponent]
 * by using the [MiniMessage], provided by the [miniMessageSerializer] value.
 * This is especially adding the [String]-features like `<rainbow>`!
 * @see miniMessageSerializer
 * @author Fruxz
 * @since 2025.8
 * @param serializer the [OpenMiniMessageSerializer] to use for deserialization
 * @param builder the process, to modify the component
 * @return the modified component as an [TextComponent]
 * @see StackedBuilder
 * @see String.asStyledComponent
 * @see miniMessageSerializer
 * @see OpenMiniMessageSerializer
 */
inline fun String.asStyledComponent(
    serializer: MiniMessage = miniMessageSerializer,
    tagResolver: TagResolver = TagResolver.standard(),
    builder: StackedBuilder.() -> Unit = { },
) = StackedBuilder(Component.text().append(serializer.deserialize(this, tagResolver)))
    .apply(builder)
    .build()

/**
 * This computational value converts this [String] into a [TextComponent]
 * list (every entry represents a line) by using the [MiniMessage], provided by the
 * [miniMessageSerializer] value.
 * This is especially adding the [String]-features like `<rainbow>`!
 * @see miniMessageSerializer
 * @author Fruxz
 * @since 1.0
 */
val String.asStyledComponents: List<TextComponent>
	get() = this.lines().asStyledComponents

/**
 * This function converts this [String] into a [TextComponent] list (every entry represents a line)
 * by using the [MiniMessage], provided by the [miniMessageSerializer] value.
 * This is especially adding the [String]-features like `<rainbow>`!
 * @see miniMessageSerializer
 * @author Fruxz
 * @since 2025.8
 */
fun String.asStyledComponents(
    serializer: MiniMessage = miniMessageSerializer,
    tagResolver: TagResolver = TagResolver.standard(),
): List<TextComponent> = this.lines().asStyledComponents(serializer = serializer, tagResolver = tagResolver)

/**
 * This computational value converts this [Iterable] into a [TextComponent]
 * list (every entry represents a line) by using the [MiniMessage], provided by the
 * [miniMessageSerializer] value.
 * This is especially adding the [String]-features like `<rainbow>`!
 * @see miniMessageSerializer
 * @author Fruxz
 * @since 1.0
 */
val Iterable<String>.asStyledComponents: List<TextComponent>
	get() = map { it.asStyledComponent }


/**
 * This function converts this [Iterable] of [String]s into a [List] of [TextComponent]s
 * by using the [MiniMessage], provided by the [miniMessageSerializer] value.
 * This is especially adding the [String]-features like `<rainbow>`!
 * @see miniMessageSerializer
 * @author Fruxz
 * @since 2025.8
 */
fun Iterable<String>.asStyledComponents(
    serializer: MiniMessage = miniMessageSerializer,
    tagResolver: TagResolver = TagResolver.standard(),
): List<TextComponent> = map { it.asStyledComponent(serializer = serializer, tagResolver = tagResolver) }