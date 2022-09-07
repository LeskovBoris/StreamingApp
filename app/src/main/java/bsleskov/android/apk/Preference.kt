package bsleskov.android.apk

data class Preference(var rtmpURL: String, var streamName: String) {
    companion object{
        var shared = Preference (
            "rtmp://.......",
            "testLeskov"
            )
        var useSurfaceView: Boolean = true
    }
}
