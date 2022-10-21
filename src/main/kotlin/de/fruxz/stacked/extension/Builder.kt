package de.fruxz.stacked.extension

import de.fruxz.stacked.Stacked
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.TextComponent

@Stacked
fun TextComponent.Builder.newlines(amount: Int) = apply { repeat(amount) { append(Component.newline()) } }

@Stacked
fun TextComponent.Builder.newline() = apply { append(Component.newline()) }

@Stacked
fun TextComponent.Builder.space() = append(Component.space())

@Stacked
fun TextComponent.Builder.spaces(amount: Int) = apply { repeat(amount) { space() } }
