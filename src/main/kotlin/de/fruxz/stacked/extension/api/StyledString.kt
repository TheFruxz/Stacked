package de.fruxz.stacked.extension.api

import de.fruxz.stacked.Stacked
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage

/**
 * This annotation is used to mark a parameter as a styled string.
 * This means the String is transformed into a [Component], using
 * the [MiniMessage] format. So this consumes for example <b>bold</b>,
 * but has the side effect that old colors like §2 are not supported.
 * @see MiniMessage
 * @see MiniMessage.miniMessage
 * @see Component
 * @author Fruxz
 * @since 1.0
 */
@Stacked
@Target(AnnotationTarget.VALUE_PARAMETER)
annotation class StyledString