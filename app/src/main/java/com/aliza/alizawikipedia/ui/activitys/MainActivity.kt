package com.aliza.alizawikipedia.ui.activitys

import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.aliza.alizawikipedia.R
import com.aliza.alizawikipedia.base.BaseActivity
import com.aliza.alizawikipedia.base.Constant
import com.aliza.alizawikipedia.base.Keys
import com.aliza.alizawikipedia.base.createSharedPreferences
import com.aliza.alizawikipedia.base.readPref
import com.aliza.alizawikipedia.base.writePref
import com.aliza.alizawikipedia.databinding.ActivityMainBinding
import com.aliza.alizawikipedia.ui.fragments.FragmentExplore
import com.aliza.alizawikipedia.ui.fragments.FragmentProfile
import com.aliza.alizawikipedia.ui.fragments.FragmentTrend

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun inflateBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
    private lateinit var changeThemeButton: ImageView
    private lateinit var sharedPreferences: SharedPreferences

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolBarMain)
        sharedPreferences = createSharedPreferences()
        initialize()

        changeThemeButton.setOnClickListener {
            toggleTheme()
        }

        setNavigationDrawerMenuOnClickListener()
        setNavigationBottomOnClickListener()
        binding.bottomNavigationMain.setOnItemReselectedListener {}

    }

    private fun initialize() {
        /*enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                topMargin = insets.top
                bottomMargin = insets.bottom
            }
            WindowInsetsCompat.CONSUMED
        }*/
        changeThemeButton = binding.navigationViewMain.getHeaderView(0).findViewById(R.id.btn_change_theme)

        val currentTheme = sharedPreferences.readPref(Keys.THEME)
        if (currentTheme == Constant.LIGHT) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            changeThemeButton.setImageResource(R.drawable.light_mode)
        } else {
            changeThemeButton.setImageResource(R.drawable.dark_mode)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }

        val actionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayoutMain,
            binding.toolBarMain,
            R.string.openDrawer,
            R.string.closeDrawer
        )
        binding.drawerLayoutMain.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        replaceFragment(FragmentExplore())
        binding.bottomNavigationMain.selectedItemId = R.id.menu_explore
    }
    
    private fun toggleTheme() {

        val currentTheme = sharedPreferences.readPref(Keys.THEME)

        if (currentTheme == Constant.LIGHT) {
            sharedPreferences.writePref(Keys.THEME, Constant.DARK)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            changeThemeButton.setImageResource(R.drawable.dark_mode);
        } else {
            sharedPreferences.writePref(Keys.THEME, Constant.LIGHT)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            changeThemeButton.setImageResource(R.drawable.light_mode);
        }
    }

    private fun setNavigationDrawerMenuOnClickListener() {
        binding.navigationViewMain.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.menu_writer -> {
                    Toast.makeText(applicationContext, "writer", Toast.LENGTH_SHORT).show()
                    binding.drawerLayoutMain.closeDrawer(GravityCompat.START)
                }
                R.id.menu_photograph -> {
                    Toast.makeText(applicationContext, "photograph", Toast.LENGTH_SHORT).show()
                    binding.drawerLayoutMain.closeDrawer(GravityCompat.START)
                }
                R.id.menu_vieo_maker -> {
                    Toast.makeText(applicationContext, "vieo maker", Toast.LENGTH_SHORT).show()
                    binding.drawerLayoutMain.closeDrawer(GravityCompat.START)
                }
                R.id.menu_translator -> {
                    Toast.makeText(applicationContext, "translator", Toast.LENGTH_SHORT).show()
                    binding.drawerLayoutMain.closeDrawer(GravityCompat.START)
                }
                R.id.menu_open_wikipedia -> {
                    Toast.makeText(applicationContext, "wikipedia", Toast.LENGTH_SHORT).show()
                    binding.drawerLayoutMain.closeDrawer(GravityCompat.START)
                }
                R.id.menu_open_wikimedia -> {
                    Toast.makeText(applicationContext, "wikimedia", Toast.LENGTH_SHORT).show()
                    binding.drawerLayoutMain.closeDrawer(GravityCompat.START)
                }

            }
            true
        }
    }

    private fun setNavigationBottomOnClickListener() {
        binding.bottomNavigationMain.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_explore -> {
                    replaceFragment(FragmentExplore())
                }
                R.id.menu_trend -> {
                    replaceFragment(FragmentTrend())
                }
                R.id.menu_profile -> {
                    replaceFragment(FragmentProfile())
                }
            }
            true
        }
    }
    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameMainContainer, fragment)
            .commit()
    }

}