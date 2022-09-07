package com.haishinkit.codec.util

/**
 * This source code from grafika.
 * https://github.com/google/grafika/blob/c747398a8f0d5c8ec7be2c66522a80b43dfc9a1e/app/src/main/java/com/android/grafika/ScheduledSwapActivity.java#L76
 */
class ScheduledFpsController : FpsController {
    private var framesAheadIndex = DEFAULT_FRAMES_AHEAD_INDEX
    private var refreshPeriodNs = DEFAULT_REFRESH_PERIOD_NS
    private var holdFrames = DEFAULT_HOLD_FRAMES
    private var updatePatternOffset = DEFAULT_UPDATE_PATTERN_OFFSET
    private var choreographerSkips = DEFAULT_CHOREGRAPHER_SKIPS
    private var droppedFrames = DEFAULT_DROPPED_FRAMES
    private var previousRefreshNs = DEFAULT_PREVIOUS_REFRESH_NS
    private var updatePatternIdx = DEFAULT_UPDATE_PATTERN_INDEX
    private var position = DEFAULT_POSITION
    private var speed = DEFAULT_SPEED

    override fun advanced(timestamp: Long): Boolean {
        var draw = false

        if (1 < holdFrames) {
            holdFrames--
        } else {
            updatePatternOffset =
                (updatePatternOffset + 1) % UPDATE_PATTERNS[updatePatternIdx].length
            holdFrames = getHoldTime()
            draw = true
            position += speed
        }

        if (previousRefreshNs != 0L && timestamp - previousRefreshNs > refreshPeriodNs + ONE_MILLISECOND_NS) {
            choreographerSkips++
        }
        previousRefreshNs = timestamp

        val diff: Long = System.nanoTime() - timestamp
        if (diff > refreshPeriodNs - ONE_MILLISECOND_NS) {
            droppedFrames++
        }

        return draw
    }

    override fun timestamp(timestamp: Long): Long {
        val framesAhead = FRAME_AHEAD[framesAheadIndex]
        return timestamp + refreshPeriodNs * framesAhead
    }

    override fun clear() {
        framesAheadIndex = DEFAULT_FRAMES_AHEAD_INDEX
        refreshPeriodNs = DEFAULT_REFRESH_PERIOD_NS
        holdFrames = DEFAULT_HOLD_FRAMES
        updatePatternOffset = DEFAULT_UPDATE_PATTERN_OFFSET
        choreographerSkips = DEFAULT_CHOREGRAPHER_SKIPS
        droppedFrames = DEFAULT_DROPPED_FRAMES
        previousRefreshNs = DEFAULT_PREVIOUS_REFRESH_NS
        updatePatternIdx = DEFAULT_UPDATE_PATTERN_INDEX
        position = DEFAULT_POSITION
        speed = DEFAULT_SPEED
    }

    private fun getHoldTime(): Int {
        val ch: Char = UPDATE_PATTERNS[updatePatternIdx][updatePatternOffset]
        return ch - '0'
    }

    companion object {
        private const val DEFAULT_FRAMES_AHEAD_INDEX = 2
        private const val DEFAULT_REFRESH_PERIOD_NS = -1L
        private const val DEFAULT_HOLD_FRAMES = 0
        private const val DEFAULT_UPDATE_PATTERN_OFFSET = 0
        private const val DEFAULT_CHOREGRAPHER_SKIPS = 0
        private const val DEFAULT_DROPPED_FRAMES = 0
        private const val DEFAULT_PREVIOUS_REFRESH_NS = 0L
        private const val DEFAULT_UPDATE_PATTERN_INDEX = 3
        private const val DEFAULT_POSITION = 0
        private const val DEFAULT_SPEED = 0

        private val UPDATE_PATTERNS = arrayOf(
            "4", // 15 fps
            "32", // 24 fps
            "32322", // 25 fps
            "2", // 30 fps
            "2111", // 48 fps
            "1", // 60 fps
            "15" // erratic, useful for examination with systrace
        )
        private const val ONE_MILLISECOND_NS: Long = 1000000
        private val FRAME_AHEAD = intArrayOf( // sync with scheduledSwapAheadNames
            0, 1, 2, 3
        )
        private val TAG = ScheduledFpsController::class.java.simpleName
    }
}
