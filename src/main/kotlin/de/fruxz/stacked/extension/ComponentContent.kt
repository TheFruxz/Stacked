package de.fruxz.stacked.extension

import net.kyori.adventure.text.ComponentLike

fun ComponentLike.content() = asPlainString

fun ComponentLike.isBlank() = content().isBlank()

fun ComponentLike.isNotBlank() = content().isNotBlank()

fun ComponentLike.isEmpty() = content().isEmpty()

fun ComponentLike.isNotEmpty() = content().isNotEmpty()