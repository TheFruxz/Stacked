package dev.fruxz.stacked

import dev.fruxz.ascend.extension.dump
import dev.fruxz.stacked.extension.asStyledComponent
import dev.fruxz.stacked.extension.toStackedBuilder
import net.kyori.adventure.text.ComponentLike
import net.kyori.adventure.text.TextComponent
import net.kyori.adventure.text.event.ClickEvent

/**
 * This class delegates [TextComponent.Builder] completely,
 * and adds additional operator functions to make building
 * [TextComponent]s more convenient.
 */
open class StackedBuilder(
    private val builder: TextComponent.Builder,
) : TextComponent.Builder by builder {

    /**
     * This operator function appends the given [String] to the
     * current Builder instance by converting it to a [TextComponent]
     * using the [String.asStyledComponent] value first.
     * @author Fruxz
     * @since 2025.1
     */
    @Stacked
    operator fun String.unaryPlus() =
        append(this.asStyledComponent).dump()

    /**
     * This operator function appends the given [ComponentLike] to the
     * current Builder instance.
     * @author Fruxz
     * @since 2025.1
     */
    @Stacked
    operator fun ComponentLike.unaryPlus() =
        append(this).dump()

    /**
     * This operator function appends the given [Iterable] of [ComponentLike] to the
     * current Builder instance.
     * @author Fruxz
     * @since 2025.1
     */
    @Stacked
    operator fun Iterable<ComponentLike>.unaryPlus() =
        append(this).dump()

    /**
     * This operator function appends the given [ClickEvent] to the
     * current Builder instance.
     * @author Fruxz
     * @since 2025.1
     */
    @Stacked
    operator fun ClickEvent.unaryPlus() =
        clickEvent(this).dump()

    /**
     * This operator function adds the ability, to create a builder-function
     * from a string. The string itself, will be converted to a styled MiniMessage
     * String using [String.asStyledComponent] and then converted to a [StackedBuilder].
     * @author Fruxz
     * @since 2025.1
     */
    @Stacked
    operator fun String.invoke(builder: StackedBuilder.() -> Unit) =
        this.asStyledComponent.toStackedBuilder().apply(builder).build()

}