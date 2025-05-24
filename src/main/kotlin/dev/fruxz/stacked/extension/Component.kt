package dev.fruxz.stacked.extension

import net.kyori.adventure.text.Component

/**
 * This extension function allows you to return the current [Component] if it is not null,
 * otherwise it returns the provided [default] [Component].
 * @param default The default [Component] to return if the current one is null.
 * @return The current [Component] or the provided default one.
 * @see Component
 * @author Fruxz
 * @since 2025.5
 */
fun Component?.orElse(default: Component) = this ?: default