package com.aliza.alizawikipedia.ui.activitys

import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.aliza.alizawikipedia.R
import com.aliza.alizawikipedia.base.BaseActivity
import com.aliza.alizawikipedia.base.Constant
import com.aliza.alizawikipedia.base.Keys
import com.aliza.alizawikipedia.base.TITLE
import com.aliza.alizawikipedia.base.URL_DATA
import com.aliza.alizawikipedia.base.WEBSITE
import com.aliza.alizawikipedia.base.createSharedPreferences
import com.aliza.alizawikipedia.base.readPref
import com.aliza.alizawikipedia.base.showDialog
import com.aliza.alizawikipedia.base.writePref
import com.aliza.alizawikipedia.databinding.ActivityMainBinding
import com.aliza.alizawikipedia.ui.fragments.FragmentExplore
import com.aliza.alizawikipedia.ui.fragments.FragmentProfile
import com.aliza.alizawikipedia.ui.fragments.FragmentTrend
import com.aliza.alizawikipedia.ui.fragments.FragmentWeb

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun inflateBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
    private lateinit var changeThemeButton: ImageView
    private lateinit var sharedPreferences: SharedPreferences
    private  var selectedItem: Int = 0

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = createSharedPreferences()
        initialize()

        toolBarOnMenuItemClick()

        changeThemeButton.setOnClickListener {
            toggleTheme()
        }

        setNavigationDrawerMenuOnClickListener()
        setNavigationBottomOnClickListener()
        binding.bottomNavigationMain.setOnItemReselectedListener {}

    }

    private fun initialize() {
        enableEdgeToEdge()

        changeThemeButton =
            binding.navigationViewMain.getHeaderView(0).findViewById(R.id.btn_change_theme)

        val currentTheme = sharedPreferences.readPref(Keys.THEME)
        if (currentTheme == Constant.LIGHT) {
            changeThemeButton.setImageResource(R.drawable.dark_mode)
        } else {
            changeThemeButton.setImageResource(R.drawable.light_mode)
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
            changeThemeButton.setImageResource(R.drawable.light_mode);
        } else {
            sharedPreferences.writePref(Keys.THEME, Constant.LIGHT)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            changeThemeButton.setImageResource(R.drawable.dark_mode);
        }
    }

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    private fun toolBarOnMenuItemClick() {
        binding.toolBarMain.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu_info -> {
                    showDialog(
                        "About us",
                        "Wikipedia is a free, web-based, collaborative, multilingual encyclopedia project launched in 2001 supported by the non-profit Wikimedia Foundation. \n\nIt is one of the largest and most popular general reference works on the internet. Wikipedia's articles are written collaboratively by volunteers around the world, and almost all of its articles can be edited by anyone with internet access."
                    )
                }
                R.id.menu_writer -> {
                    replaceActivityFromNavigationDrawer(
                        "https://en.wikipedia.org/wiki/Wikipedia:How_to_create_a_page",
                        "Be a Writer"
                    )
                }
            }
            true
        }
    }

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    private fun setNavigationDrawerMenuOnClickListener() {
        binding.navigationViewMain.setNavigationItemSelectedListener {
            selectedItem = it.itemId
            when(it.itemId){
                R.id.menu_writer -> {
                    replaceFragmentFromNavigationDrawer("https://en.wikipedia.org/wiki/Wikipedia:How_to_create_a_page")
                    binding.drawerLayoutMain.closeDrawer(GravityCompat.START)
                }
                R.id.menu_photograph -> {
                    replaceFragmentFromNavigationDrawer("https://en.wikipedia.org/wiki/Wikipedia:How_to_create_a_page")
                    binding.drawerLayoutMain.closeDrawer(GravityCompat.START)
                }
                R.id.menu_vieo_maker -> {
                    replaceFragmentFromNavigationDrawer("https://en.wikipedia.org/wiki/Wikipedia:How_to_create_a_page")
                    binding.drawerLayoutMain.closeDrawer(GravityCompat.START)
                }
                R.id.menu_translator -> {
                    replaceFragmentFromNavigationDrawer("https://en.wikipedia.org/wiki/Wikipedia:How_to_create_a_page")
                    binding.drawerLayoutMain.closeDrawer(GravityCompat.START)
                }
                R.id.menu_open_wikipedia -> {
                    replaceActivityFromNavigationDrawer("https://www.wikipedia.org/","wikipedia")
                    binding.drawerLayoutMain.closeDrawer(GravityCompat.START)
                }
                R.id.menu_open_wikimedia -> {
                    replaceActivityFromNavigationDrawer("https://www.wikimedia.org/","wikimedia")
                    binding.drawerLayoutMain.closeDrawer(GravityCompat.START)
                }

            }
            true
        }
    }

    private fun replaceFragmentFromNavigationDrawer(url: String) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.frameMainContainer, FragmentWeb(url))
            .addToBackStack(null)
            .commit()
    }
    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    private fun replaceActivityFromNavigationDrawer(url: String, title: String) {
        val intent = Intent(this,WebActivity::class.java)

        val bundle = Bundle()
        bundle.putString(WEBSITE, url)
        bundle.putString(TITLE, title)
        intent.putExtra(URL_DATA, bundle)
        startActivity(intent)
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

    override fun onBack() {
        super.onBack()
        when(selectedItem){
            R.id.menu_writer -> {
                binding.navigationViewMain.menu.getItem(0).isChecked = false
            }
            R.id.menu_photograph -> {
                binding.navigationViewMain.menu.getItem(1).isChecked = false
            }
            R.id.menu_vieo_maker -> {
                binding.navigationViewMain.menu.getItem(2).isChecked = false
            }
            R.id.menu_translator -> {
                binding.navigationViewMain.menu.getItem(3).isChecked = false
            }
        }
    }

}