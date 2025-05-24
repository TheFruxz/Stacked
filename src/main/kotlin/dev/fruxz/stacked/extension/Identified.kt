package dev.fruxz.stacked.extension

import net.kyori.adventure.identity.Identified

/**
 * This function checks if the current [Identified] instance identifies the [other] [Identified] instance as well.
 * This means both instances have the same [java.util.UUID] behind their [Identified.identity].
 * @param other The [Identified] instance to compare with.
 * @return `true` if both instances have the same UUID, otherwise `false`.
 * @see Identified.identity
 * @see net.kyori.adventure.identity.Identity.uuid
 * @author Fruxz
 * @since 2025.5
 */
infix fun Identified.identifies(other: Identified): Boolean =
    this.identity().uuid() == other.identity().uuid()