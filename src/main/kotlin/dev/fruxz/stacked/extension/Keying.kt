package dev.fruxz.stacked.extension

import net.kyori.adventure.key.Key
import dev.fruxz.stacked.extension.KeyingStrategy.*
import java.util.*
import kotlin.jvm.Throws

/**
 * This function creates a new [Key] object, which originates from [this] key,
 * using the [value] as the value of the new key. The [strategy] is used to
 * determine how the new key is generated.
 * @throws IllegalArgumentException if the [value] does not match the [KEY_REGEX]
 * @author Fruxz
 * @since 2024.1
 */
@Throws(IllegalArgumentException::class)
fun Key.subKey(value: String, strategy: KeyingStrategy = CONTINUE): Key {
	val part = value.lowercase(Locale.ENGLISH).replace(" ", "_")

	if (!part.all { "$it" matches KEY_REGEX }) {
		throw IllegalArgumentException("The value '$value' does not match the key regex $KEY_REGEX")
	}

	return Key.key(
		when (strategy) {
			SQUASH -> asString().replace(":", "_")
			ORIGIN -> namespace()
			CONTINUE -> value()
			PATHING -> namespace()
		},
		when (strategy) {
			PATHING -> "${value()}.$part"
			else -> part
		}
	)
}

/**
 * This function creates a new [Key] object, which originates from [this] key,
 * using the [Key.subKey] function with the [value] as the value of the new key.
 * @throws IllegalArgumentException if the [value] does not match the [KEY_REGEX]
 * @see Key.subKey
 * @author Fruxz
 * @since 2024.1
 */
@Throws(IllegalArgumentException::class)
infix operator fun Key.div(value: String): Key = subKey(value)

val KEY_REGEX = "[a-z0-9_.-]".toRegex()

/**
 * Every strategy to generate sub keys of a key has its pros and cons.
 * Decide wisely by your use case, which you want to use, for a little help,
 * look at the documentation of each strategy enum.
 */
enum class KeyingStrategy {

	/**
	 * The identity of the parent gets squashed and used inside the child namespace.
	 * Example: 'origin:parent' -> 'origin_parent:child' -> 'origin_parent_child:sub-child'
	 * Info: Stores the complete path & origin, but can quickly become quite large
	 */
	SQUASH,

	/**
	 * The namespace of the origins key is used as the namespace of every child.
	 * Example: 'origin:parent' -> 'origin:child' -> 'origin:sub-child'
	 * Info: Always keeps the origin of the keys in mind, but loses history of keys between start and end
	 */
	ORIGIN,

	/**
	 * The value of the parent is used as the namespace of the child
	 * Example: 'origin:parent' -> 'parent:child' -> 'child:sub-child'
	 * Info: Small and allows to track the history, but the back-tracking requires that you have all the keys!
	 */
	CONTINUE,

	/**
	 * The namespace is the same as the value of the parent, but the value is the
	 * parents value + a dot + the child value.
	 * 'origin:parent' -> 'origin:parent.child' -> 'origin:parent.child.sub-child'
	 * Info: Always keeps the complete source present and also contains the full
	 * history, but can be quite large!
	 */
	PATHING;

}