package bsleskov.android.apk.screens

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import bsleskov.android.apk.R
import bsleskov.android.apk.data.LoginViewModel

class SplashFragment : Fragment() {

    private lateinit var preferences: SharedPreferences


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_splash, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navOptions = navOptions {
            anim {
                enter = androidx.navigation.ui.R.anim.nav_default_enter_anim
                popEnter = androidx.navigation.ui.R.anim.nav_default_pop_enter_anim
                popExit = androidx.navigation.ui.R.anim.nav_default_pop_exit_anim
                exit = androidx.navigation.ui.R.anim.nav_default_exit_anim
            }
            launchSingleTop = true
            popUpTo(R.id.nav_graph_application) {
                inclusive = true
            }
        }
        preferences = requireActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)

        if (!preferences.contains(KEY))
        {
            view.postDelayed({
                findNavController().navigate(
                    R.id.action_splashFragment_to_loginFragment,
                    null,
                    navOptions
                )
            }, 2000)

        } else
        {
            val loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
            preferences.getString(KEY, "")?.let { loginViewModel.getLogin(it, "password") }
            loginViewModel.login.observe(viewLifecycleOwner) {login  ->
                val status = login.status
                if (status == 0)
                {
                    view.postDelayed({
                        findNavController().navigate(
                            R.id.action_splashFragment_to_mainFragment,
                            null,
                            navOptions
                        )
                    }, 2000)
                } else
                {
                    Toast.makeText(this.activity, "Ошибка авторизации, повторите попытку", Toast.LENGTH_LONG).show()
                }

            }


        }



    }
}
