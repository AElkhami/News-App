package com.example.news

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.news.core.ui.theme.AppTheme
import com.example.news.discover.presentation.DiscoverScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                DiscoverScreen()
            }
        }
    }
}

@Preview
@Composable
fun ActivityPreview(){
    AppTheme {
        DiscoverScreen()
    }
}