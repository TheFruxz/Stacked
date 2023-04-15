package dev.fruxz.stacked.extension

import dev.fruxz.ascend.extension.dump
import dev.fruxz.stacked.Stacked
import net.kyori.adventure.text.TextComponent

@Stacked
fun TextComponent.Builder.newlines(amount: Int) = apply { repeat(amount) { appendNewline() } }.dump()

@Stacked
fun TextComponent.Builder.newline() = appendNewline().dump()

@Stacked
fun TextComponent.Builder.space() = appendSpace().dump()

@Stacked
fun TextComponent.Builder.spaces(amount: Int) = apply { repeat(amount) { space() } }.dump()
