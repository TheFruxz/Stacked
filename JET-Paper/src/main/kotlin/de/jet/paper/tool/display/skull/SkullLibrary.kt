package de.jet.paper.tool.display.skull

import com.destroystokyo.paper.profile.ProfileProperty
import de.jet.jvm.extension.data.randomBoolean
import de.jet.jvm.extension.data.url
import de.jet.jvm.tool.data.Base64
import de.jet.jvm.tool.data.CSV
import de.jet.paper.app.JetData
import de.jet.paper.extension.debugLog
import de.jet.paper.extension.display.ui.item
import de.jet.paper.extension.paper.getOfflinePlayer
import de.jet.paper.tool.display.item.quirk.Quirk.Companion.skullQuirk
import de.jet.unfold.extension.asComponent
import de.jet.unfold.text
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Material

object SkullLibrary {

	data class SkullData(
		val category: String,
		val id: Int,
		val displayName: String,
		val textures: String,
		val unknown: Int,
		val description: String,
	)

	private val skulls by lazy {
		debugLog("loading skulls CSV data...")
		debugLog("downloading CSV file...")
		val data = url(JetData.skullDataURL.content).readText()
		debugLog("mapping CSV data...")
		CSV.convertCSV(data)
			.mapNotNull {
				if (it.isNotEmpty() && it.size > 4) {
					SkullData(
						it[0].removeSurrounding("\""),
						it[1].removeSurrounding("\"").toInt(),
						it[2].removeSurrounding("\""),
						Base64.encodeToString(
							"{\"textures\":{\"SKIN\":{\"url\":\"https://textures.minecraft.net/texture/${it[3]}\"}}}" //todo check, if https is working
						),
						it[4].removeSurrounding("\"").toInt(),
						it[5].removeSurrounding("\"")
					)
				} else null
			}.also { debugLog("successfully loaded skulls CSV data!") }
	}

	fun getSkull(id: Int) = Material.PLAYER_HEAD.item.apply {
		val skull = skulls.firstOrNull { it.id == id }

		skullQuirk {
			owningPlayer = getOfflinePlayer("MHF_Question")

			if (skull != null) {
				playerProfile = playerProfile!!.apply {
					setProperty(ProfileProperty("textures", skull.textures))
				}
				playerProfile!!.complete(true, randomBoolean())
			}
		}

		label = (skull?.displayName ?: "Unknown Head").asComponent
		lore = buildList {
			add(Component.empty())
			add(text("Categories:"))
			skull?.description?.split("|")?.forEach { category ->
				add(text {
					text("- ") {
						color(NamedTextColor.GRAY)
					}
					text(category) {
						color(NamedTextColor.YELLOW)
					}
				})
			} ?: add(text("Unknown"))
			add(Component.empty())
		}
	}

}