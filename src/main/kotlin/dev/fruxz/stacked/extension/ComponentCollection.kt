package dev.fruxz.stacked.extension

import dev.fruxz.ascend.extension.container.splitBy
import dev.fruxz.stacked.buildComponent
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.ComponentLike
import net.kyori.adventure.text.TextComponent

/**
 * This function creates a [TextComponent] containing
 * all components of [this] [Iterable] of [ComponentLike]s.
 * No spliterator is used!
 * @author Fruxz
 * @since 1.0
 */
fun Iterable<ComponentLike>.joinToComponent(): TextComponent =
    Component.text().append(this).build()

/**
 * This function creates a [TextComponent] containing
 * all components of [this] [Iterable] of [ComponentLike]s
 * split by the specified [spliterator].
 * @author Fruxz
 * @since 1.0
 */
fun Iterable<ComponentLike>.joinToComponent(spliterator: ComponentLike): TextComponent =
    joinToComponent(spliterator) { it }

/**
 * This function creates a [TextComponent] containing
 * all components of [this] [Iterable] of [ComponentLike]s,
 * transformed via the [builder] process and split by the
 * specified [spliterator].
 * @author Fruxz
 * @since 1.0
 */
inline fun <T : ComponentLike> Iterable<T>.joinToComponent(spliterator: ComponentLike, builder: (T) -> ComponentLike): TextComponent = buildComponent {
    withIndex().forEach { (index, split) ->
        if (index > 0) append(spliterator)
        append(builder.invoke(split))
    }
}

/**
 * This computational value splits every line of the component to its
 * own component.
 * This is useful if you need the specific lines of the component,
 * like adding a prefix to each line.
 * @return A list of components, each representing a line of the original component
 * @author Fruxz
 * @since 1.0
 */
val ComponentLike.lines: List<Component>
    get() = this.asComponent().let { original ->
        (original.children().takeIf { it.isNotEmpty() } ?: listOf(original))
            .splitBy { it == Component.newline() }
            .map {
                it.joinToComponent().colorIfAbsent(original.color()).mergeStyle(original)
            }
    }
