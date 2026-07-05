package dev.fruxz.stacked

import dev.fruxz.stacked.extension.api.StyledString
import dev.fruxz.stacked.extension.asStyledComponent
import dev.fruxz.stacked.extension.miniMessageSerializer
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.ComponentLike
import net.kyori.adventure.text.TextComponent
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.event.HoverEventSource
import net.kyori.adventure.text.format.Style
import net.kyori.adventure.text.format.StyleSetter
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver
import net.kyori.adventure.text.TextComponent.Builder as ComponentBuilder

/**
 * This function creates a [TextComponent] with the given base and modifier.
 * A new base is getting formed via the base parameter, which is by default a [Component.empty] TextComponent.
 * Then it gets converted to a [ComponentBuilder] using the [TextComponent.toBuilder] function.
 * After that the [builder] parameter is called onto the builder of the [base] and the result
 * gets build and returned via the [ComponentBuilder.build] function.
 * @param base The base component, which is by default a [Component.empty] TextComponent.
 * @param builder The builder function, which gets called onto the builder of the [base].
 * @return The built [TextComponent].
 * @see TextComponent
 * @see ComponentBuilder
 * @see Component.empty
 * @see TextComponent.toBuilder
 * @see ComponentBuilder.build
 * @author Fruxz
 * @since 1.0
 */
inline fun buildComponent(base: TextComponent = Component.empty(), builder: ComponentBuilder.() -> Unit): TextComponent =
	base.toBuilder().apply(builder).build()

// context receivers END

/**
 * This operator function, which will be replaced with an *unaryPlus* function in the future,
 * allows you to append a [String] specified by [content] to the current [ComponentBuilder] using
 * the conversion via the [String.asStyledComponent] value.
 * @param content The string, which will be converted and applied to this [ComponentBuilder]
 * @return The current [ComponentBuilder] instance
 * @see String.asStyledComponent
 * @see ComponentBuilder.append
 * @author Fruxz
 * @since 1.0
 */
infix operator fun ComponentBuilder.plus(@StyledString content: String): ComponentBuilder =
	append(content.asStyledComponent)

/**
 * This operator function, which will be replaced with an *unaryPlus* function in the future,
 * allows you to append a [ComponentLike] specified by [component] to the current [ComponentBuilder].
 * @param component The [ComponentLike] instance, which will be applied to this [ComponentBuilder]
 * @return The current [ComponentBuilder] instance
 * @see ComponentBuilder.append
 * @author Fruxz
 * @since 1.0
 */
infix operator fun ComponentBuilder.plus(component: ComponentLike): ComponentBuilder =
	append(component)

/**
 * This operator function, which will be replaced with an *unaryPlus* function in the future,
 * allows you to append a [Iterable] of [ComponentLike] specified by [components] to the current [ComponentBuilder].
 * @param components The [Iterable] of [ComponentLike] instances, which will be applied to this [ComponentBuilder]
 * @return The current [ComponentBuilder] instance
 * @see ComponentBuilder.append
 * @author Fruxz
 * @since 1.0
 */
infix operator fun ComponentBuilder.plus(components: Iterable<ComponentLike>): ComponentBuilder =
	append(components)

/**
 * This operator function, which will be replaced with an *unaryPlus* function in the future,
 * allows you to append a [Component] provided by the [component] parameter to this [I].
 * @param component The [Component] instance, which will be applied to this [I]
 * @param I Any object, based on an [Component]
 * @return The modified [I] as an [Component]
 * @see Component.append
 * @author Fruxz
 * @since 1.0
 */
infix operator fun <I : Component> I.plus(component: Component): Component =
	append(component)

/**
 * This operator function, which will be replaced with an *unaryPlus* function in the future,
 * allows you to set the click event of this [ComponentBuilder] with the provided [clickEvent].
 * @param clickEvent The [ClickEvent] instance, which will be applied to this [ComponentBuilder]
 * @return The current [ComponentBuilder] instance
 * @see ComponentBuilder.clickEvent
 * @author Fruxz
 * @since 1.0
 */
infix operator fun ComponentBuilder.plus(clickEvent: ClickEvent<*>?): ComponentBuilder =
	clickEvent(clickEvent)

/**
 * This operator function, which will be replaced with an *unaryPlus* function in the future,
 * allows you to set the color of this [ComponentBuilder] with the provided [color].
 * @param color The [TextColor] instance, which will be applied to this [ComponentBuilder]
 * @return The current [ComponentBuilder] instance
 * @see ComponentBuilder.color
 * @author Fruxz
 * @since 1.0
 */
infix operator fun ComponentBuilder.plus(color: TextColor?): ComponentBuilder =
	color(color)

/**
 * This operator function, which will be replaced with an *unaryPlus* function in the future,
 * allows you to set the style of this [ComponentBuilder] with the provided [style].
 * @param style The [Style] instance, which will be applied to this [ComponentBuilder]
 * @return The current [ComponentBuilder] instance
 * @see ComponentBuilder.style
 * @author Fruxz
 * @since 1.0
 */
infix operator fun ComponentBuilder.plus(style: Style): ComponentBuilder =
	style(style)

/**
 * This function applies the result of the [process] to [this],
 * using the [StyleSetter.hoverEvent] function.
 * @param process The process, which generates the [HoverEventSource]<*>
 * @return The modified [this]
 * @author Fruxz
 * @since 1.0
 */
inline fun <T : StyleSetter<T>> T.hover(process: () -> HoverEventSource<*>?) = this.hoverEvent(process())

fun Component.click(process: () -> ClickEvent<*>?) = this.clickEvent(process())

fun ComponentBuilder.click(process: () -> ClickEvent<*>?) = this.clickEvent(process())

/**
 * This function converts the [content] to an [TextComponent] using the [String.asStyledComponent].
 * MiniMessage is used to give the ability to apply colors, styles and more using only text.
 * Then, the [builder] process is applied, to modify the components state, which is performed
 * with the [String.asStyledComponent] function.
 * @param content the content, which will be converted to a component via MiniMessage
 * @param builder the process, to modify the component
 * @return the modified component as an [TextComponent]
 * @author Fruxz
 * @since 1.0
 */
inline fun text(
    @StyledString content: String,
    serializer: MiniMessage = miniMessageSerializer,
    tagResolver: TagResolver = TagResolver.standard(),
    builder: ComponentBuilder.() -> Unit = { },
) = content.asStyledComponent(serializer = serializer, tagResolver = tagResolver, builder = builder)

/**
 * This function uses the [component] to apply it to an new [TextComponent.Builder1]
 * and then applies the [builder] process to modify the components state.
 * @param component the base, which will be applied to the empty text component
 * @param builder the process, to modify the component
 * @return the modified component as an [TextComponent]
 * @author Fruxz
 * @since 1.0
 */
inline fun text(
    component: ComponentLike,
    builder: ComponentBuilder.() -> Unit = { },
) = Component.text().append(component).apply(builder).build()

/**
 * This function uses the [componentBuilder] and applies the [builder] process
 * to modify the components state.
 * @param componentBuilder the base, which will be used to be modified
 * @param builder the process, to modify the component
 * @return the modified component as an [TextComponent]
 * @author Fruxz
 * @since 1.0
 */
inline fun text(
    componentBuilder: ComponentBuilder,
    builder: ComponentBuilder.() -> Unit = { },
) = componentBuilder.apply(builder).build()

/**
 * This function uses a new [Component.empty] component and applies the [builder] process
 * to modify the components state.
 * This function utilizes the ... function
 * @param builder the process, to modify the component
 * @return the modified component as an [TextComponent]
 * @author Fruxz
 * @since 1.0
 */
inline fun text(builder: ComponentBuilder.() -> Unit) = text(Component.empty(), builder)