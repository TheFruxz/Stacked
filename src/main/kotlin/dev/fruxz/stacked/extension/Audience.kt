package dev.fruxz.stacked.extension

import net.kyori.adventure.audience.Audience

/**
 * This extension takes every [Audience] and converts it into a single [Audience] instance.
 * @see Audience.audience
 * @author Fruxz
 * @since 2025.5
 */
val Iterable<Audience>.asAudience get() = Audience.audience(this)

/**
 * This extension takes every [Audience] and converts it into a single [Audience] instance.
 * @see Audience.audience
 * @author Fruxz
 * @since 2025.5
 */
val Array<out Audience>.asAudience get() = Audience.audience(*this)