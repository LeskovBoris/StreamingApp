package bsleskov.android.apk.api

import bsleskov.android.apk.model.ArchiveModel
import bsleskov.android.apk.model.StreamUserModel
import bsleskov.android.apk.model.User
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StreamingAPPApi {

    @GET("./archive.get")

    suspend fun getArchiveList(): ArrayList<ArchiveModel>

    @GET("./action.login?")
    suspend fun getLogin(@Query("user") userName:String, @Query("pass") password:String): User

    @GET("/devices.status_get")
    suspend fun getStreamList(): ArrayList<StreamUserModel>
}
