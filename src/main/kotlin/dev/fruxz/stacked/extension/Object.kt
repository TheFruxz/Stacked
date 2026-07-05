package dev.fruxz.stacked.extension

import net.kyori.adventure.key.Key
import net.kyori.adventure.key.Keyed
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.`object`.ObjectContents
import net.kyori.adventure.text.`object`.PlayerHeadObjectContents
import java.util.UUID
import kotlin.uuid.Uuid
import kotlin.uuid.toJavaUuid

/**
 * Alias for [Component.`object`]
 */
fun content(content: ObjectContents) = Component.`object`(content)

// default player heads

/**
 * Alias for [content] with [source]
 */
fun playerHead(source: PlayerHeadObjectContents) = content(source)

/**
 * Utilizes [ObjectContents.playerHead]
 * @see content
 */
fun playerHead(builder: PlayerHeadObjectContents.Builder.() -> Unit) = content(ObjectContents.playerHead(builder))

/**
 * Utilizes [ObjectContents.playerHead]
 * @see content
 */
fun playerHead(username: String) = content(ObjectContents.playerHead(username))

/**
 * Utilizes [ObjectContents.playerHead]
 * @see content
 */
fun playerHead(javaUUID: UUID) = content(ObjectContents.playerHead(javaUUID))

/**
 * Utilizes [ObjectContents.playerHead]
 * @see content
 */
fun playerHead(uuid: Uuid) = content(ObjectContents.playerHead(uuid.toJavaUuid()))

// heads by textures

/**
 * Creates a [PlayerHeadObjectContents] only by its [texture] string.
 * Great for using external head database websites and only having to
 * copy the texture string, to ensure stable skin textures.
 * @param texture the encoded texture string
 * @param signature defaults to null, replace if needed, but usually not required
 */
fun skinObject(texture: String, signature: String? = null) = ObjectContents.playerHead {
    it.profileProperty(
        object : PlayerHeadObjectContents.ProfileProperty {
            override fun name() = "textures"
            override fun value() = texture
            override fun signature() = signature
        }
    )
}

/**
 * Defines a player-head only by its texture string
 */
fun textureHead(texture: String) = content(content = skinObject(texture))

// sprites

/**
 * Utilizes [ObjectContents.sprite]
 * @see content
 */
fun sprite(atlas: Keyed, sprite: Keyed) = content(
    ObjectContents.sprite(atlas.key(), sprite.key())
)

/**
 * Utilizes [ObjectContents.sprite].
 * All strings are parsed using [Key.key] therefore automatically validating the input.
 * If no [Key.namespace] is defined in the string it will default to [Key.MINECRAFT_NAMESPACE].
 * @see content
 * @see Key.key(String)
 */
fun sprite(atlas: String, sprite: String) = content(
    ObjectContents.sprite(
        Key.key(atlas),
        Key.key(sprite)
    )
)