package com.example.notesappassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Gallery
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.notesappassignment.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
  private var  toggle: ActionBarDrawerToggle?=null
    private lateinit var fragment: Fragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        toggle = ActionBarDrawerToggle(this, binding.drawerLayout, R.string.open, R.string.close)
        binding.drawerLayout.addDrawerListener(toggle!!)
        toggle!!.syncState()
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        //
        binding.navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {

                R.id.home -> {

                    fragment=HomeFragment()
                 supportFragmentManager.beginTransaction().addToBackStack("Home").replace(R.id.fragmentContainer,fragment).commit()


                }
                R.id.gallery -> {

                    fragment=GalleryFragment()

                  supportFragmentManager.beginTransaction().addToBackStack("Gallery").replace(R.id.fragmentContainer,fragment).commit()

                }
                R.id.Slideshow -> {

                    fragment=SlideShowFragment()
                    supportFragmentManager.beginTransaction().addToBackStack("Slide").replace(R.id.fragmentContainer,fragment).commit()
                }
            }
            true

        }


        intializingFragment(savedInstanceState)

    }



    private fun intializingFragment(savedInstanceState: Bundle?) {
        if(savedInstanceState==null)
        {

            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer,HomeFragment()).commit()
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if(toggle!!.onOptionsItemSelected(item)) true
        else return false
    }

    override fun onBackPressed() {

        if(binding.drawerLayout.isDrawerOpen(GravityCompat.START))
            binding.drawerLayout.close()

     else {
            supportFragmentManager.popBackStack("Home",FragmentManager.POP_BACK_STACK_INCLUSIVE)
            super.onBackPressed()

        }
       }

    }
