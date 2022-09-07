package bsleskov.android.apk.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bsleskov.android.apk.model.ArchiveModel
import bsleskov.android.apk.model.User
import kotlinx.coroutines.launch
import java.lang.Exception

class LoginViewModel : ViewModel() {

    var repository = Repository()

    val login: MutableLiveData<User> = MutableLiveData()

    fun getLogin(userName: String, password: String) {
        viewModelScope.launch {
            try
            {
                login.value = repository.getLogin(userName, password)

            }
            catch (e: Exception)
            {

            }
        }
    }
}
