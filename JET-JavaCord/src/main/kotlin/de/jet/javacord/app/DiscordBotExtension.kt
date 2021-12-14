package de.jet.javacord.app

import de.jet.javacord.extension.runBot
import de.jet.javacord.structure.Bot
import de.jet.javacord.structure.BotAppearance
import de.jet.javacord.structure.BotCredentials
import de.jet.jvm.application.extension.AppExtension
import kotlin.reflect.KFunction1

/**
 * This is a [AppExtension], that runs a DiscordBot using the
 * [runBot] function. It can be attached to a App-Runtime using
 * the *attach* or *attachWith* function.
 * @property identity is "discordbot"
 * @property parallelRunAllowed is true - parallel runs allowed
 * @property runtimeAccessor is [runBot] - also a builder for the bot instance
 * @author Fruxz
 * @since 1.0
 */
object DiscordBotExtension : AppExtension<Bot, Unit, Unit> {

	override val identity = "discordbot"

	override val parallelRunAllowed = true

	override val runtimeAccessor: KFunction1<Bot.() -> Unit, Unit> = ::runBot

}