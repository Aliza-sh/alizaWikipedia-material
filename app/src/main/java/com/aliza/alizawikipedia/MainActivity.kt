package com.aliza.alizawikipedia

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.aliza.alizawikipedia.base.BaseActivity
import com.aliza.alizawikipedia.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun inflateBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolBarMain)

        setIconNavigationDrawer()
        setNavigationDrawerMenuOnClickListener()

    }

    private fun setIconNavigationDrawer() {
        val actionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayoutMain,
            binding.toolBarMain,
            R.string.openDrawer,
            R.string.closeDrawer
        )
        binding.drawerLayoutMain.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
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
}