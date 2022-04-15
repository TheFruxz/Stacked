package de.moltenKt.paper.extension.external

import de.moltenKt.paper.tool.display.skull.SkullLibrary

/**
 * The skull with the id [identity] from your own skull database (CSV-Database), set in the
 * configuration file
 */
fun texturedSkull(identity: Int) = SkullLibrary.getSkull(identity)