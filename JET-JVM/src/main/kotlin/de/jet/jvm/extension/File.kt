@file:Suppress("NOTHING_TO_INLINE")

package de.jet.jvm.extension

import java.io.File
import java.nio.file.Path

/**
 * Returns the file inside the resource folder of the class, where the function is called from.
 * The file is returned by its content read by the [readText] function.
 * @param resource the file(+folder) path
 * @return the file content
 * @author Fruxz
 * @since 1.0
 */
inline fun getResourceText(resource: String) =
    object {}.javaClass.classLoader.getResource(resource)?.readText() ?: throw NoSuchElementException("Resource $resource not found")

/**
 * Returns the file inside the resource folder of the class, where the function is called from.
 * The file is returned by its content read by the [readBytes] function.
 * @param resource the file(+folder) path
 * @return the file content
 * @author Fruxz
 * @since 1.0
 */
inline fun getResourceByteArray(resource: String) =
    object {}.javaClass.classLoader.getResource(resource)?.readBytes() ?: throw NoSuchElementException("Resource $resource not found")

fun String.pathAsFile() =
    Path.of(this).toFile()

fun String.pathAsFileFromRuntime() =
    File(System.getProperty("user.dir") + "/$this")

operator fun Path.div(other: String): Path = resolve(other)