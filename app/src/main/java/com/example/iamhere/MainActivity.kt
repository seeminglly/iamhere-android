package com.example.iamhere
import androidx.navigation.fragment.NavHostFragment

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("MainActivity", "MainActivity 실행됨")
        val navView = findViewById<BottomNavigationView>(R.id.nav_view)

        // ✅ NavController를 직접 NavHostFragment에서 가져오기
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val navController = navHostFragment.navController

        // ✅ 네비게이션 연결
        NavigationUI.setupWithNavController(navView, navController)
    }



}
