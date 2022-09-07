package bsleskov.android.apk.screens

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.os.HandlerCompat.postDelayed
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import bsleskov.android.apk.R
import bsleskov.android.apk.data.LoginViewModel
import bsleskov.android.apk.model.User
import kotlinx.coroutines.delay


class LoginFragment: Fragment(R.layout.fragment_login) {

    private  var userName: String? = ""
    private  var password:String? = ""
    private lateinit var loginEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var preferences: SharedPreferences
    private val loginViewModel: LoginViewModel by lazy {
        ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        loginEditText = view.findViewById(R.id.login_editText)
       // passwordEditText = view.findViewById(R.id.password_editText)
        progressBar = view.findViewById(R.id.login_progressBar)
        loginButton = view.findViewById(R.id.login_button)




        return view
    }

    override fun onStart() {
        super.onStart()


        val loginWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s!=null)
                {
                    userName = s.toString()
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        }

        val passwordWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s!=null)
                {
                    password = s.toString()
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        }

        loginEditText.addTextChangedListener(loginWatcher)
  //      passwordEditText.addTextChangedListener(passwordWatcher)


        loginButton.setOnClickListener {

            if(userName != "" ) {

                loginViewModel.getLogin(userName!!, "password")
                loginViewModel.login.observe(viewLifecycleOwner) {login  ->
                    val user = login.user
                    val status = login.status
                    if (status == 0)
                    {
                        preferences = requireActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
                        preferences.edit()
                            .putString(KEY, user)
                            .apply()
                    } else
                    {
                        Toast.makeText(this.activity, "Ошибка авторизации, повторите попытку", Toast.LENGTH_LONG).show()
                    }

                }


                progressBar.visibility = View.VISIBLE


               view?.postDelayed({findNavController().navigate(R.id.action_loginFragment_to_mainFragment)},
                   1000)

            }
            else {
                Toast.makeText(this.activity, "Введите имя и пароль", Toast.LENGTH_LONG).show()
            }
        }
    }

    companion object {
        fun newInstance(): LoginFragment {
            return LoginFragment()
        }

    }
}
