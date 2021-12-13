package de.jet.paper.tool.display.item

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.bukkit.NamespacedKey
import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.enchantments.Enchantment

@Serializable
@SerialName("ItemModification")
data class Modification(
	@SerialName("modificationType") val type: String,
	val level: Int,
) : ConfigurationSerializable {

	constructor(
		enchantment: Enchantment,
		level: Int,
	) : this(enchantment.key.toString(), level)

	constructor(
		map: Map<String, Any>
	) : this(Enchantment.getByKey(NamespacedKey.fromString("${map["type"]}"))!!, (map["level"] as Number).toInt())

	val enchantment: Enchantment
		get() = Enchantment.getByKey(NamespacedKey.fromString(type))!!

	override fun serialize() = mapOf(
		"type" to type,
		"level" to level,
	)

}