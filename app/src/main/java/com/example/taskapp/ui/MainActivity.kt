package com.example.taskapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.example.taskapp.R
import com.example.taskapp.databinding.ActivityMainBinding
import java.io.Serializable

class MainActivity : AppCompatActivity() {

    lateinit var mainActivityBinding :ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainActivityBinding.root
        setContentView(view)
//        val fragmentManager = supportFragmentManager
//        val fragmentTransaction = fragmentManager.beginTransaction()
//        val myFragment = PostFragment()
//        fragmentTransaction.replace(R.id.fragment_container_view, myFragment)
//        fragmentTransaction.addToBackStack(null)
//        fragmentTransaction.commit()

        val fragment1: Fragment = PostFragment()
        val fragmentManager1 = supportFragmentManager
        val fragmentTransaction1 = fragmentManager1.beginTransaction()
        fragmentTransaction1.replace(R.id.fragment_container_view, fragment1)
        fragmentTransaction1.commit()

    }

}