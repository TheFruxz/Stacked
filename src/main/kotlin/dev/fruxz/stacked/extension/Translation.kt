package dev.fruxz.stacked.extension

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.TranslatableComponent
import net.kyori.adventure.translation.GlobalTranslator
import net.kyori.adventure.translation.Translatable
import java.util.Locale

/**
 * Creates a new [TranslatableComponent] from this [Translatable] object.
 * @see Component.translatable
 */
val Translatable.translateComponent: TranslatableComponent
    get() = Component.translatable(this)

/**
 * Translates this [Translatable] using the [GlobalTranslator] and [locale].
 * @return the translated string
 */
fun Translatable.translate(locale: Locale = Locale.getDefault()): String =
    GlobalTranslator.render(this.translateComponent, locale).asPlainString