package bsleskov.android.apk.model



data class ArchiveModel(
    val Time: String,
    val Number: String,
    val PlateCode: String,
    val Longitude: Double = 0.0,
    val Latitude: Double = 0.0
    )

