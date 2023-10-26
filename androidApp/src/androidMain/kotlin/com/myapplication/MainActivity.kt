package com.myapplication

import MainView
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import di.initKoin
import org.koin.android.ext.koin.androidContext

class MainActivity : AppCompatActivity() {
   // private val database: Database by inject()
    //private val birdRepository: BirdRepository by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       //val birdRepository = BirdRepository(DriverFactory(this))
        //GlobalScope.launch(Dispatchers.IO) {
        //   birdRepository.syncLocalDatabaseWithServer()
        //}

        initKoin() {
            androidContext(applicationContext)
        }
        setContent {
            MainView()
        }
    }
}