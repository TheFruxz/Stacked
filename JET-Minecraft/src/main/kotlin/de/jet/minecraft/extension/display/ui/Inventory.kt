package de.jet.minecraft.extension.display.ui

import de.jet.library.extension.paper.createInventory
import de.jet.minecraft.tool.display.item.Item
import de.jet.minecraft.tool.display.ui.inventory.Container
import de.jet.minecraft.tool.display.ui.panel.Panel
import net.kyori.adventure.text.Component
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.PlayerInventory

fun buildContainer(lines: Int = 3, action: Container.() -> Unit) = Container(size = lines*9).apply(action)

fun buildPanel(lines: Int = 3, action: Panel.() -> Unit) = Panel(lines = lines).apply(action)

fun Inventory.copyRaw(title: Component) = createInventory(this.holder, this.size, title).apply {
	contents = this.contents
	storageContents = this.storageContents
}

operator fun <T : Inventory> T.get(slot: Int) = getItem(slot)

operator fun <T : Inventory> T.set(slot: Int, itemStack: ItemStack) = setItem(slot, itemStack)

operator fun <T : Inventory> T.set(slot: Int, item: Item) = setItem(slot, item.produce())