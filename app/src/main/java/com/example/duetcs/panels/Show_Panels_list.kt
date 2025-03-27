package com.example.duetcs.panels

import Show_Panel
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

@Composable
fun Show_Panels_list() {
    var selectedPanel by remember { mutableStateOf<Panel?>(null) }

    BackHandler(enabled = selectedPanel != null) {
        selectedPanel = null
    }

    if (selectedPanel == null) {
        Scaffold(topBar = {
            TopAppBar(title = {
                Text("Select Panel",
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.h6,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.onPrimary,
                    fontWeight = FontWeight.Bold,
                )
            },
                modifier = Modifier.height(42.dp))
        }) { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                items(panels_list) { panel ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable { selectedPanel = panel },
                        elevation = 8.dp, // Soft shadow
                        shape = RoundedCornerShape(16.dp),
                        backgroundColor = MaterialTheme.colors.background
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colors.primary.copy(alpha = 0.2f)) // Light color
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = panel.year,
                                style = MaterialTheme.typography.h5.copy(color = MaterialTheme.colors.primary),
                                modifier = Modifier.padding(8.dp),
                                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    } else {
        Show_Panel(selectedPanel!!)
    }
}
