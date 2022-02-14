package com.nxet.emstelltask


import android.content.Context
import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.navigation.NavigationBarView
import com.nxet.emstelltask.DataClasses.MainData
import com.nxet.emstelltask.Fragments.ClinicsFragment
import com.nxet.emstelltask.Fragments.DoctorsFragment
import com.nxet.emstelltask.databinding.ActivityMainBinding
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException


const val TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoiMTI0IiwiaWF0IjoxNjM4NDM5NjE0fQ.7qm4dyI1G3kMf-VAwWrNS_F_D2LjZDX9Wew-HBYUjd4"
const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var container : FrameLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavigationItemView = binding.bottomNavigation
        container = binding.fragmentContainer
        bottomNavigationItemView.setOnItemSelectedListener(navListener)
        bottomNavigationItemView.itemIconTintList = null
        binding.toolbarTitle.text = "Doctors"
        val colors = intArrayOf(
            resources.getColor(R.color.selected_color),
            resources.getColor(R.color.bottom_nav_bg)
        )
        val csl = ColorStateList(arrayOf(IntArray(0)), intArrayOf(colors[0]))
        bottomNavigationItemView.itemActiveIndicatorColor = csl

        supportFragmentManager.beginTransaction().replace(
            R.id.fragment_container,
            DoctorsFragment()
        ).commit()









    }


    private val navListener = NavigationBarView.OnItemSelectedListener { item ->
        when (item.itemId) {
            R.id.doctors  -> {
                binding.toolbarTitle.text = "Doctors"
                supportFragmentManager.beginTransaction().replace(
                    R.id.fragment_container,
                    DoctorsFragment()
                ).commit()
            }
            R.id.clinics -> {
                binding.toolbarTitle.text = "Clinics"
                supportFragmentManager.beginTransaction().replace(
                    R.id.fragment_container,
                    ClinicsFragment()
                ).commit()
            }
        }
        true
    }

}