package dev.fruxz.stacked.extension

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.ComponentLike
import net.kyori.adventure.text.TextReplacementConfig
import org.intellij.lang.annotations.RegExp
import java.util.regex.Pattern

private fun Component.performReplace(builder: TextReplacementConfig.Builder.() -> Unit) =
	this.replaceText(TextReplacementConfig.builder().apply(builder).build())

/**
 * Replaces all occurrences of the given [key] with the given [value]
 * and then returns the result component.
 * @param key The key to replace.
 * @param value The value to replace the key with.
 * @return The result component.
 * @author Fruxz
 * @since 1.0
 */
fun <T : Component> T.replace(@RegExp key: String, value: String): Component =
	performReplace {
		match(key)
		replacement(value)
	}

/**
 * Replaces all occurrences of the given [key] with the given [value]
 * and then returns the result component.
 * @param key The key to replace.
 * @param value The value to replace the key with.
 * @return The result component.
 * @author Fruxz
 * @since 1.0
 */
fun <T : Component> T.replace(@RegExp key: String, value: ComponentLike): Component =
	performReplace {
		match(key)
		replacement(value)
	}

/**
 * Replaces all occurrences of the given [regex] with the given [value]
 * and then returns the result component.
 * @param regex The regex to replace.
 * @param value The value to replace the regex with.
 * @return The result component.
 * @author Fruxz
 * @since 1.0
 */
fun <T : Component> T.replace(regex: Regex, value: String): Component =
	performReplace {
		match(Pattern.compile(regex.pattern))
		replacement(value)
	}

/**
 * Replaces all occurrences of the given [regex] with the given [value]
 * and then returns the result component.
 * @param regex The regex to replace.
 * @param value The value to replace the regex with.
 * @return The result component.
 * @author Fruxz
 */
fun <T : Component> T.replace(regex: Regex, value: ComponentLike): Component =
	performReplace {
		match(Pattern.compile(regex.pattern))
		replacement(value)
	}

/**
 * Replaces all occurrences of the given [pattern] with the given [value]
 * and then returns the result component.
 * @param pattern The pattern to replace.
 * @param value The value to replace the pattern with.
 * @return The result component.
 * @author Fruxz
 * @since 1.0
 */
fun <T : Component> T.replace(pattern: Pattern, value: String): Component =
	performReplace {
		match(pattern)
		replacement(value)
	}

/**
 * Replaces all occurrences of the given [pattern] with the given [value]
 * and then returns the result component.
 * @param pattern The pattern to replace.
 * @param value The value to replace the pattern with.
 * @return The result component.
 * @author Fruxz
 * @since 1.0
 */
fun <T : Component> T.replace(pattern: Pattern, value: ComponentLike): Component =
	performReplace {
		match(pattern)
		replacement(value)
	}
