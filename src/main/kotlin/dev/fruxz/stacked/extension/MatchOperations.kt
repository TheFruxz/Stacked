package dev.fruxz.stacked.extension

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.TextReplacementConfig
import kotlin.text.contains

/**
 * Replaces all [replacements] in order to the component.
 * @return the new component with all [replacements] replaced
 * @see Component.replaceText
 */
fun Component.replace(
    replacements: List<TextReplacementConfig>
) = replacements.fold(this) { acc, replacement ->
    acc.replaceText(replacement)
}

/**
 * Checks if the raw [asPlainString] contents contains a matching [regex] pattern.
 * @see CharSequence.contains
 */
fun Component.contains(regex: Regex) =
    this.asPlainString.contains(regex)

/**
 * Checks if the raw [asPlainString] contents matches the [regex] pattern.
 * @see Regex.matches
 */
fun Component.matches(regex: Regex) =
    regex.matches(this.asPlainString)