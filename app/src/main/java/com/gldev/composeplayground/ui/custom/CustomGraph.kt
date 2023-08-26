package com.gldev.composeplayground.ui.custom

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.LayoutScopeMarker
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.MeasurePolicy
import androidx.compose.ui.layout.ParentDataModifier
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.temporal.ChronoUnit
import kotlin.math.roundToInt


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TimeGraph(
    hoursHeader: @Composable () -> Unit,
    rowCount: Int,
    dayLabel: @Composable (index: Int) -> Unit,
    sleepBar: @Composable (index: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val dayLabels = @Composable {
        repeat(rowCount) { dayLabel(it) }
    }

    val sleepBars = @Composable {
        repeat(rowCount) { sleepBar(it) }
    }

    //Measuring TimeGraph components.
    Layout(
        contents = listOf(hoursHeader, dayLabels, sleepBars),
        modifier = modifier.padding(bottom = 32.dp)
    ) {

        // 1. Measurement step
        // Determine sizes of components
            (hoursHeaderMeasurables, dayLabelMeasurables, barMeasureables),
            constraints,
        ->
        require(hoursHeaderMeasurables.size == 1) {
            "hoursHeader should only emit one composable"
        }

        // 2. Placement step
        // Determine position of components

        val hoursHeaderPlaceable = hoursHeaderMeasurables.first().measure(constraints)

        val dayLabelPlaceables = dayLabelMeasurables.map { measurable ->
            val placeable = measurable.measure(constraints)
            placeable
        }

        var totalHeight = hoursHeaderPlaceable.height

        val barPlaceables = barMeasureables.map { measurable ->
            val barParentData = measurable.parentData as TimeGraphParentData
            val barWidth = (barParentData.duration * hoursHeaderPlaceable.width).roundToInt()

            val barPlaceable = measurable.measure(
                constraints.copy(
                    minWidth = barWidth,
                    maxWidth = barWidth
                )
            )
            totalHeight += barPlaceable.height
            barPlaceable
        }

        val totalWidth = dayLabelPlaceables.first().width + hoursHeaderPlaceable.width

        layout(totalWidth, totalHeight) {
            val xPosition = dayLabelPlaceables.first().width
            var yPosition = hoursHeaderPlaceable.height

            hoursHeaderPlaceable.place(xPosition, 0)

            barPlaceables.forEachIndexed { index, barPlaceable ->
                val barParentData = barPlaceable.parentData as TimeGraphParentData
                val barOffset = (barParentData.offset * hoursHeaderPlaceable.width).roundToInt()

                barPlaceable.place(xPosition + barOffset, yPosition)
                // the label depend on the size of the bar content - so should use the same y
                val dayLabelPlaceable = dayLabelPlaceables[index]
                dayLabelPlaceable.place(x = 0, y = yPosition)

                yPosition += barPlaceable.height
            }
        }
    }
}

class TimeGraphParentData(
    val duration: Float,
    val offset: Float,
) : ParentDataModifier {
    override fun Density.modifyParentData(parentData: Any?) = this@TimeGraphParentData
}

@LayoutScopeMarker
@Immutable
object TimeGraphScope {
    @RequiresApi(Build.VERSION_CODES.O)
    @Stable
    fun Modifier.timeGraphBar(
        start: LocalDateTime,
        end: LocalDateTime,
        hours: List<Int>,
    ): Modifier {
        val earliestTime = LocalTime.of(hours.first(), 0)
        val durationInHours = ChronoUnit.MINUTES.between(start, end) / 60f
        val durationFromEarliestToStartInHours =
            ChronoUnit.MINUTES.between(earliestTime, start.toLocalTime()) / 60f
        // we add extra half of an hour as hour label text is visually centered in its slot
        val offsetInHours = durationFromEarliestToStartInHours + 0.5f
        return then(
            TimeGraphParentData(
                duration = durationInHours / hours.size,
                offset = offsetInHours / hours.size
            )
        )
    }
}

@Composable
fun implCustomGraph() {

    val hours = emptyList<String>()
    val days = emptyList<String>()

    TimeGraph(
        hoursHeader = {
            Row {
                hours.forEach { Text(it, Modifier.width(50.dp)) }
            }
        },
        rowCount = 1,
        dayLabel = {
            Text(text = "Sunday", modifier = Modifier.height(24.dp))
        },
        sleepBar = {
            Box(Modifier)
        }
    )
}