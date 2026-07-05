package dev.fruxz.stacked.extension

import net.kyori.adventure.text.format.TextColor

/**
 * Interpolates the color between [this] and [other] as much as [ratio]
 * @param ratio between 0.0 (=0%) and 1.0 (=100%)
 */
fun TextColor.interpolate(other: TextColor, ratio: Double): TextColor {
    val r1 = this.red()
    val g1 = this.green()
    val b1 = this.blue()

    val r2 = other.red()
    val g2 = other.green()
    val b2 = other.blue()

    val clampedRatio = ratio.coerceIn(0.0, 1.0)
    val r = (r1 + ((r2 - r1) * clampedRatio)).toInt().coerceIn(0, 255)
    val g = (g1 + ((g2 - g1) * clampedRatio)).toInt().coerceIn(0, 255)
    val b = (b1 + ((b2 - b1) * clampedRatio)).toInt().coerceIn(0, 255)

    return TextColor.color(r, g, b)
}