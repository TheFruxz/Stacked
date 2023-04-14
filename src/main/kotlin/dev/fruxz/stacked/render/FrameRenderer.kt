package dev.fruxz.stacked.render

import de.fruxz.ascend.extension.TransitionType
import de.fruxz.ascend.extension.hexString
import de.fruxz.ascend.extension.mix
import dev.fruxz.stacked.extension.asStyledComponent
import net.kyori.adventure.text.TextComponent
import java.awt.Color

object FrameRenderer {

	/**
	 * Renders the different transition frames for the color to fade
	 * from the center into the edges of the provided [text].
	 * The start color of the text is [from] and slowly fades into
	 * the provided [to] color, using the hex-rgb color spectrum.
	 * The result is returned as a [List] of [TextComponent]s, that
	 * are styled like the current state of fade.
	 * @param text the text to be colored
	 * @param from the base color of the text
	 * @param to the destination color of the text
	 * @return the transition from index 0 (only [from] color) to index x (only [to] color)
	 * @author Fruxz
	 * @since 1.0
	 */
	fun renderTransitionFrames(text: String, from: Color, to: Color, transitionType: TransitionType = TransitionType.EASY_IN_OUT): List<TextComponent> {

		val splits = text.length / 2
		val colorFrames = from.mix(to, splits, transitionType).toMutableList()
		val startColors = from.mix(to, 3, transitionType)

		return buildList {

			add("<${from.hexString}>$text")

			repeat(3) { step ->
				add("<gradient:${from.hexString}:${startColors[step].hexString}:${from.hexString}>$text")
			}

			for (x in 0 until colorFrames.size) {

				add("<gradient:${colorFrames.firstOrNull()?.hexString}:${colorFrames.lastOrNull()?.hexString}:${colorFrames.firstOrNull()?.hexString}>$text")

				colorFrames.removeFirstOrNull()

			}

			add("<${to.hexString}>$text")

		}.map { it.asStyledComponent }

	}

}