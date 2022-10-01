package de.fruxz.stacked.extension

import de.fruxz.stacked.Unfold
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.TextComponent

@Unfold
fun TextComponent.Builder.newlines(amount: Int) = apply { repeat(amount) { append(Component.newline()) } }

@Unfold
fun TextComponent.Builder.space() = append(Component.space())

@Unfold
fun TextComponent.Builder.spaces(amount: Int) = apply { repeat(amount) { space() } }
