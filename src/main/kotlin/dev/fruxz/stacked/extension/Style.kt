package dev.fruxz.stacked.extension

import dev.fruxz.stacked.Stacked
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.TextComponent
import net.kyori.adventure.text.format.Style
import net.kyori.adventure.text.format.StyleSetter
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration

/**
 * This function combines [this] [TextColor] with the [decoration]
 * to produce a new [Style] object, which can be easily applied to
 * a text component as a design-property.
 * @author Fruxz
 * @since 1.0
 */
@Stacked
operator fun TextColor.plus(decoration: TextDecoration) =
	Style.style(this, decoration)

/**
 * This function combines [this] [TextDecoration] with the [color]
 * to produce a new [Style] object, which can be easily applied to
 * a text component as a design-property.
 * @author Fruxz
 * @since 1.0
 */
@Stacked
operator fun TextDecoration.plus(color: TextColor) =
	color + this

/**
 * This function applies the [color] to [this] provided [TextComponent]
 * and returns the result. To change the color of the [TextComponent]
 * the [TextComponent.color] function and the [color] parameter is used.
 * @author Fruxz
 * @since 1.0
 */
@Stacked
operator fun <T : StyleSetter<T>> T.plus(color: TextColor): T =
	this.color(color)

/**
 * This function applies the [decoration] to [this] provided [TextComponent]
 * and returns the result. To change the decoration of the [TextComponent]
 * the [TextComponent.decorate] function and the [decoration] parameter is used.
 * @author Fruxz
 * @since 1.0
 */
@Stacked
operator fun <T : StyleSetter<T>> T.plus(decoration: TextDecoration) =
	this.decorate(decoration)

/**
 * This function applies the [style] to [this] provided [TextComponent]
 * and returns the result. To change the style of the [TextComponent]
 * the [TextComponent.style] function and the [style] parameter is used.
 * @author Fruxz
 * @since 1.0
 */
@Stacked
operator fun TextComponent.plus(style: Style) =
	this.style(style)

/**
 * This function applies the [style] to [this] provided [TextComponent]
 * and returns the result. To change the style of the [TextComponent]
 * the [TextComponent.style] function and the [style] parameter is used.
 * @author Fruxz
 * @since 1.0
 */
@Stacked
infix fun TextComponent.style(style: Style) =
	this + style

/**
 * This function applies the [color] to [this] provided [TextComponent]
 * and returns the result. To change the color of the [TextComponent]
 * the [TextComponent.color] function and the [color] parameter is used.
 * @author Fruxz
 * @since 1.0
 */
@Stacked
infix fun TextComponent.style(color: TextColor): Component =
	(this + color)

@Stacked
fun Component.style(color: TextColor, vararg decorations: TextDecoration) =
	style(Style.style(color, *decorations))

@Stacked
fun Component.style(vararg decorations: TextDecoration) =
	style(Style.style(*decorations))

@Stacked
fun TextComponent.Builder.style(color: TextColor, vararg decorations: TextDecoration) =
	style(Style.style(color, *decorations))

@Stacked
fun TextComponent.Builder.style(vararg decorations: TextDecoration) =
	style(Style.style(*decorations))

@Stacked
fun <T : StyleSetter<T>> T.style(decoration: TextDecoration, flag: Boolean) = apply {
	decoration(decoration, flag)
}

@Stacked
fun <T : StyleSetter<T>> T.style(vararg decorations: Pair<TextDecoration, Boolean>) = apply {
	decorations.forEach { (decoration, flag) -> decoration(decoration, flag) }
}

// defaults

/**
 * Applies the [TextDecoration.BOLD] to [this] [TextComponent] using [style].
 * @author Fruxz
 * @since 2025.1
 */
@Stacked
fun <T : StyleSetter<T>> T.bold(flag: Boolean = true) =
	style(TextDecoration.BOLD, flag)

/**
 * Applies the [TextDecoration.ITALIC] to [this] [TextComponent] using [style].
 * @author Fruxz
 * @since 2025.1
 */
@Stacked
fun <T : StyleSetter<T>> T.italic(flag: Boolean = true) =
	style(TextDecoration.ITALIC, flag)

/**
 * Applies the [TextDecoration.UNDERLINED] to [this] [TextComponent] using [style].
 * @author Fruxz
 * @since 2025.1
 */
@Stacked
fun <T : StyleSetter<T>> T.underlined(flag: Boolean = true) =
	style(TextDecoration.UNDERLINED, flag)

/**
 * Applies the [TextDecoration.STRIKETHROUGH] to [this] [TextComponent] using [style].
 * @author Fruxz
 * @since 2025.1
 */
@Stacked
fun <T : StyleSetter<T>> T.strikethrough(flag: Boolean = true) =
	style(TextDecoration.STRIKETHROUGH, flag)

/**
 * Applies the [TextDecoration.OBFUSCATED] to [this] [TextComponent] using [style].
 * @author Fruxz
 * @since 2025.1
 */
@Stacked
fun <T : StyleSetter<T>> T.obfuscated(flag: Boolean = true) =
	style(TextDecoration.OBFUSCATED, flag)