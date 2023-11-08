package com.myapplication

import ui.MainView
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import di.initKoin
import org.koin.android.ext.koin.androidContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initKoin() {
            androidContext(applicationContext)
        }
        setContent {
            MainView()
        }
    }
}