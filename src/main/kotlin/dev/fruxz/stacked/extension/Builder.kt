package dev.fruxz.stacked.extension

import net.kyori.adventure.text.TextComponent

// TODO @IgnorableReturnValue
fun TextComponent.Builder.newlines(amount: Int) = apply { repeat(amount) { appendNewline() } }

// TODO @IgnorableReturnValue
fun TextComponent.Builder.newline() = appendNewline()

// TODO @IgnorableReturnValue
fun TextComponent.Builder.space() = appendSpace()

// TODO @IgnorableReturnValue
fun TextComponent.Builder.spaces(amount: Int) = apply { repeat(amount) { space() } }
