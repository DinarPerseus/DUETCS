package com.example.duetcs.panels

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Panels(viewModel: PanelViewModel) {
    val panelMembers by viewModel.allPanelMembers.collectAsState(initial = emptyList())

    Column(Modifier.padding(16.dp)) {
        var name by remember { mutableStateOf("") }
        var phone by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var role by remember { mutableStateOf("") }
        var currentDesignation by remember { mutableStateOf("") }
        var series by remember { mutableStateOf("") }

        TextField(value = name, onValueChange = { name = it }, label = { Text("Name") })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = phone, onValueChange = { phone = it }, label = { Text("Phone") })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = role, onValueChange = { role = it }, label = { Text("Role") })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = currentDesignation, onValueChange = { currentDesignation = it }, label = { Text("Current Designation") })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = series, onValueChange = { series = it }, label = { Text("Series") })
        Spacer(modifier = Modifier.height(8.dp))


        Button(onClick = {
            if (name.isNotEmpty() && phone.isNotEmpty() && email.isNotEmpty() && role.isNotEmpty() && currentDesignation.isNotEmpty() && series.isNotEmpty()) {
                viewModel.addPanelMember(name, phone, email, role, currentDesignation, series)
                name = ""
                phone = ""
                email = ""
                role = ""
                currentDesignation = ""
            }

        }) {
            Text("Add Panel Item")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(panelMembers) { item ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    elevation = 4.dp
                ) {
                    Row(Modifier.padding(16.dp)) {
                        Column(Modifier.weight(1f)) {
                            Text(text = "Name: ${item.name}")
                            Text(text = "Phone: ${item.phone}")
                            Text(text = "Email: ${item.email}")
                            Text(text = "Role: ${item.role}")
                            Text(text = "Current Designation: ${item.currentDesignation}")
                            Text(text = "Series: ${item.series}")

                        }
                        IconButton(onClick = { viewModel.deletePanelMember(item) }) {
                            Icon(Icons.Default.Delete, contentDescription = "Delete")
                        }
                    }
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PanelsPreview() {
//    Panels(viewModel = PanelViewModel(PanelRepository(PanelDatabase.getDatabase(LocalContext.current).panelMemberDao())))
//}