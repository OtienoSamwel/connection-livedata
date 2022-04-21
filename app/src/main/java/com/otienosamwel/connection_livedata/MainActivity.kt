package com.otienosamwel.connection_livedata

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.otienosamwel.connection_livedata.ui.theme.ConnectionlivedataTheme

class MainActivity : ComponentActivity() {
    private lateinit var mainViewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConnectionlivedataTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }

        val factory = MainViewModelFactory(this)
        mainViewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]

        mainViewModel.isConnected.observe(this) {
            Toast.makeText(
                this,
                "ConnectionChanged ${getConnectionType(it)} $it",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun getConnectionType(isConnected: Boolean): String =
        if (isConnected) "Connected" else "Disconnected"
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ConnectionlivedataTheme {
        Greeting("Android")
    }
}