package dev.fruxz.stacked.extension

import dev.fruxz.stacked.Stacked
import dev.fruxz.stacked.extension.api.StyledString
import net.kyori.adventure.text.ComponentLike
import kotlin.time.Duration
import kotlin.time.toJavaDuration
import net.kyori.adventure.title.Title as AdventureTitle
import net.kyori.adventure.title.Title.Times as AdventureTimes

fun Title(
    title: ComponentLike,
    subtitle: ComponentLike,
    times: AdventureTimes? = null,
) = AdventureTitle.title(title.asComponent(), subtitle.asComponent(), times)

@Stacked
fun Title(
    @StyledString styledTitle: String,
    @StyledString styledSubtitle: String,
    times: AdventureTimes? = null,
) = AdventureTitle.title(styledTitle.asStyledComponent, styledSubtitle.asStyledComponent, times)

fun Times(
    fadeIn: Duration,
    stay: Duration,
    fadeOut: Duration,
) = AdventureTimes.times(fadeIn.toJavaDuration(), stay.toJavaDuration(), fadeOut.toJavaDuration())