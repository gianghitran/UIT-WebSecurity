package com.example.d2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.d2.ui.theme.D2Theme

import android.content.Intent
import android.content.ComponentName

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val intent = Intent().apply {
            component = ComponentName(
                "com.tcpip.netsight",
                "com.tcpip.netsight.WebviewActivity"
            )
            putExtra("url", "file:///data/data/com.tcpip.netsight/files/bmw.txt")
        }
        startActivity(intent)
    }
}