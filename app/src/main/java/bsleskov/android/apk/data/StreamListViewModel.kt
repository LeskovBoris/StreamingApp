package bsleskov.android.apk.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bsleskov.android.apk.model.ArchiveModel
import bsleskov.android.apk.model.StreamUserModel
import kotlinx.coroutines.launch
import java.lang.Exception

class StreamListViewModel: ViewModel() {
    var repository = Repository()
    val streamList: MutableLiveData<ArrayList<StreamUserModel>> = MutableLiveData()

    fun getStreamList() {

        viewModelScope.launch {

            try {
                streamList.value = repository.getStreamList()
            }
            catch (e: Exception) {

            }

        }
    }

}
