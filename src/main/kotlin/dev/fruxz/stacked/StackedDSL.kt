package dev.fruxz.stacked

import dev.fruxz.stacked.extension.asStyledComponent
import net.kyori.adventure.text.ComponentLike
import net.kyori.adventure.text.TextComponent
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.TextComponent.Builder as ComponentBuilder

/**
 * This operator function appends the given [String] to the
 * current Builder instance by converting it to a [TextComponent]
 * using the [asStyledComponent] value first.
 * @author Fruxz
 * @since 2025.1
 */
context(builder: ComponentBuilder)
operator fun String.unaryPlus() =
    builder.append(this.asStyledComponent)

/**
 * This operator function appends the given [ComponentLike] to the
 * current Builder instance.
 * @author Fruxz
 * @since 2025.1
 */
context(builder: ComponentBuilder)
operator fun ComponentLike.unaryPlus() =
    builder.append(this)

/**
 * This operator function appends the given [Iterable] of [ComponentLike] to the
 * current Builder instance.
 * @author Fruxz
 * @since 2025.1
 */
context(builder: ComponentBuilder)
operator fun Iterable<ComponentLike>.unaryPlus() =
    builder.append(this)

/**
 * This operator function appends the given [ClickEvent] to the
 * current Builder instance.
 * @author Fruxz
 * @since 2025.1
 */
context(builder: ComponentBuilder)
operator fun ClickEvent.unaryPlus() =
    builder.clickEvent(this)

/**
 * This operator function adds the ability, to create a builder-function
 * from a string. The string itself, will be converted to a styled MiniMessage
 * String using [String.asStyledComponent].
 * @author Fruxz
 * @since 2025.1
 */
context(_: ComponentBuilder)
operator fun String.invoke(builder: ComponentBuilder.() -> Unit) =
    this.asStyledComponent(builder = builder)