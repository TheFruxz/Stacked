package de.jet.jvm.interchange

import de.jet.jvm.tool.smart.positioning.Address

/**
 * This interface
 */
interface InterchangeStructured<T> {

	val name: String

	fun getNearestBranchWithParameters(original: Address<T>): Pair<T, String>

}