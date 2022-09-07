package bsleskov.android.apk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import bsleskov.android.apk.R
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {
  //  private var fragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)





//        val navigation = findViewById<NavigationBarView>(R.id.navigation)
//        navigation.setOnItemSelectedListener(this)
//
//        fragment = LoginFragment.newInstance()
//        val transaction = supportFragmentManager.beginTransaction()
//        transaction.replace(R.id.content, fragment as Fragment).commit()
    }

//    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            R.id.stream_navigation -> {
//                fragment = StreamFragment.newInstance()
//            }
//            R.id.archive_navigation -> {
//                fragment = ArchiveFragment.newInstance()
//            }
//
//            else -> {
//            }
//        }
//
//        val transaction = supportFragmentManager.beginTransaction()
//        transaction.replace(R.id.content, fragment as Fragment).commit()
//        return true
//    }
}
