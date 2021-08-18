package de.jet.library.runtime.app

import de.jet.app.JetApp
import de.jet.library.extension.app
import de.jet.library.extension.collection.replace
import de.jet.library.tool.display.ide.API
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonPrimitive
import org.bukkit.ChatColor
import kotlin.text.RegexOption.IGNORE_CASE

class LanguageSpeaker(
	val languageId: String
) {

	@Serializable
	data class LanguageContainer(
		val languageId: String,
		val languageVersion: String,
		val languageVendor: String,
		val languageVendorWebsite: String,
		val jetVersion: String,
		val languageData: Map<String, JsonElement> = mapOf("generator" to JsonPrimitive("v${JetApp.instance.description.version}")),
	)

	val languageContainer: LanguageContainer by lazy {
		JetApp.instance.getResourceFile("lang/$languageId.json").let { data ->

			if (data != null) {
				Json.decodeFromString(data)
			} else
				LanguageContainer(
					languageId = "backup",
					languageVersion = "0",
					languageVendor = "system",
					languageVendorWebsite = "/",
					jetVersion = "/",
					languageData = mapOf("error" to JsonPrimitive("yes"))
				)

		}
	}

	val cachedLanguageData: Map<String, String> by lazy {
		languageContainer.languageData.map { it.key to it.value.jsonPrimitive.content }.toMap()
	}

	fun dataToJson() = JsonObject(cachedLanguageData.map { it.key to JsonPrimitive(it.value) }.toMap())

	@API
	val error: Boolean
		get() = cachedLanguageData["error"] == "yes"

	private val smartColors = ChatColor.values().associateBy {
		"[${it.name}]"
	} + mapOf(
		"[LIME]" to ChatColor.GREEN,
		"[ORANGE]" to ChatColor.GOLD,
		"[NONE]" to ChatColor.RESET,
	)

	fun message(id: String, smartColor: Boolean = true): String {
		return if (!error) {

			(cachedLanguageData[id] ?: "LANGUAGE-DATA '$id' UNKNOWN ($languageId)").let { output ->
				if (smartColor) {
					output.replace(smartColors)
				} else
					output
			}

		} else "LANGUAGE FILE $languageId NOT CORRECT"
	}

}