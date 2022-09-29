package com.example.democorrutinas2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CoroutineScope(Main).launch {
            Log.i("Stocks", "Calculation started...")
            val firstStock = async(IO){
                getFirstStock()
            }
            val secondStock = async(IO) {
                getSecondStock()
            }
            val total = firstStock.await() + secondStock.await()
            tvCount.text = "Total is $total"
        }
    }
}

private suspend fun getFirstStock(): Int {
    delay(10000)
    Log.i("My Stock", "First store stock returned")
    return 55000
}

private suspend fun getSecondStock(): Int {
    delay(8000)
    Log.i("My Stock", "Second store stock returned")
    return 55000
}