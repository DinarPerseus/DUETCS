package com.example.duetcs

import MainScreen
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
import com.example.duetcs.panels.PanelDatabase
import com.example.duetcs.panels.PanelRepository
import com.example.duetcs.panels.PanelViewModel
import com.example.duetcs.panels.PanelMember

import com.example.duetcs.ui.theme.DUETCSTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = PanelDatabase.getDatabase(applicationContext)
        val repository = PanelRepository(database.panelMemberDao())
        val viewModel = PanelViewModel(repository)
        setContent {
            MainScreen(viewModel)
        }
    }
}
