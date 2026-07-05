package dev.fruxz.stacked.extension.sound

import net.kyori.adventure.key.Keyed
import net.kyori.adventure.sound.Sound

/**
 * Builds a sound effect with some default values
 * @see Sound.sound
 */
fun soundOf(
    sound: Keyed,
    source: Sound.Source = Sound.Source.MASTER,
    volume: Number = 1F,
    pitch: Number = 1F,
) = Sound.sound(sound.key(), source, volume.toFloat(), pitch.toFloat())