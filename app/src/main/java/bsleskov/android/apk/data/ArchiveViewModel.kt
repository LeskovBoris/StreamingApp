package bsleskov.android.apk.data

import androidx.lifecycle.*
import bsleskov.android.apk.model.ArchiveModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import java.lang.Exception

class ArchiveViewModel() : ViewModel() {

    var repository = Repository()
    val archiveList: MutableLiveData<ArrayList<ArchiveModel>> = MutableLiveData()

    fun getArchiveList() {

        viewModelScope.launch {

            try {
                archiveList.value = repository.getArchiveList()
            }
            catch (e: Exception) {

            }

        }
    }

}
