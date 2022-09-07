package bsleskov.android.apk.screens

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import bsleskov.android.apk.R
import bsleskov.android.apk.model.User


const val APP_PREFERENCES = "APP_PREFERENCES"
const val KEY = "key"
class SettingsFragment : Fragment() {
    private lateinit var preferences: SharedPreferences

    private lateinit var loginEditText: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)
        preferences = requireActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        loginEditText = view.findViewById(R.id.login_editText)
        loginEditText.setText(preferences.getString(KEY, ""))

        (activity as AppCompatActivity).supportActionBar?.title = "Настройки"

        return view

    }
}
