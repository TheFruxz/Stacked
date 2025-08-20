package dev.fruxz.stacked.extension

import net.kyori.adventure.text.format.TextColor
import java.awt.Color as AwtColor

val TextColor.asAwtColor: AwtColor
    get() = AwtColor(this.value())

val AwtColor.asTextColor: TextColor
    get() = TextColor.color(this.rgb)