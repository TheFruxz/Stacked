package de.fruxz.stacked.extension

import de.fruxz.stacked.color.KotlinColor
import net.kyori.adventure.text.format.TextColor
import java.awt.Color
import de.fruxz.ascend.tool.color.Color as CoreColor

fun colorOf(red: Int, green: Int, blue: Int) = KotlinColor(red, green, blue)

fun rgbOf(red: Int, green: Int, blue: Int) = colorOf(red, green, blue)

fun colorOf(rgb: Int) = KotlinColor(rgb)

fun colorOf(hexColor: String) = KotlinColor(hexColor)

fun colorOf(awtColor: Color) = KotlinColor(awtColor)

fun colorOf(textColor: TextColor) = KotlinColor(textColor)

fun CoreColor.asKotlinColor(): KotlinColor = KotlinColor(this.awtColor)