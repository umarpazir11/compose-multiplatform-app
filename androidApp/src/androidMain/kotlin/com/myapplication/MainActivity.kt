package com.myapplication

import MainView
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import data.local.DriverFactory
import data.repository.BirdRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.core.component.inject

class MainActivity : AppCompatActivity() {
    private val database: Database by inject()
    private val birdRepository: BirdRepository by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       //val birdRepository = BirdRepository(DriverFactory(this))
        //GlobalScope.launch(Dispatchers.IO) {
        //   birdRepository.syncLocalDatabaseWithServer()
        //}
        setContent {
            MainView()
        }
    }
}