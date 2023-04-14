package dev.fruxz.stacked.extension

import net.kyori.adventure.text.ComponentLike

val ComponentLike.content
	get() = asPlainString

val ComponentLike.isBlank
	get() = content.isBlank()

val ComponentLike.isNotBlank
	get() = content.isNotBlank()

val ComponentLike.isEmpty
	get() = content.isEmpty()

val ComponentLike.isNotEmpty
	get() = content.isNotEmpty()