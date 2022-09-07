package bsleskov.android.apk.data

import android.util.Log
import bsleskov.android.apk.api.RetrofitClient
import bsleskov.android.apk.model.ArchiveModel
import bsleskov.android.apk.model.StreamUserModel
import bsleskov.android.apk.model.User
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Query

class Repository {

    suspend fun getArchiveList(): ArrayList<ArchiveModel> {

        return RetrofitClient.apkApi.getArchiveList()


    }

    suspend fun getLogin(userName: String, password: String): User {
        return RetrofitClient.apkApi.getLogin(userName, password)
    }

    suspend fun getStreamList(): ArrayList<StreamUserModel> {
        return RetrofitClient.apkApi.getStreamList()
    }
}
