package dev.fruxz.stacked.extension

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.TextComponent

val nonBreakingSpace: TextComponent get() = Component.text("\u00A0")

val brokenPipe get() = Component.text("\u00A1")

val dottedPipe get() = Component.text("\u00A6")