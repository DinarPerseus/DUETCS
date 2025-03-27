package com.example.duetcs.panels
import Show_Panel
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.foundation.shape.RoundedCornerShape



import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
//import androidx.compose.material.LocalIndication
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.ripple
//import androidx.compose.material.icons.filled.SearchOff
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Show_Panels_list() {
   // var selectedPanel by rememberSaveable { mutableStateOf<Panel?>(null) }
    var selectedPanel by remember { mutableStateOf<Panel?>(null) }

    var searchQuery by remember { mutableStateOf("") }
    val filteredPanels = panels_list.filter { it.year.contains(searchQuery, true) }
    var isSearchActive by remember { mutableStateOf(false) }

    BackHandler(enabled = selectedPanel != null) {
        selectedPanel = null
    }





    if (selectedPanel == null) {
        Scaffold(
            topBar = {
                if (isSearchActive) {
                    SearchAppBar(
                        searchQuery = searchQuery,
                        onSearchQueryChange = { searchQuery = it },
                        onCloseSearch = {
                            isSearchActive = false
                            searchQuery = ""
                        }
                    )
                } else {
                    TopAppBar(
                        title = {
                            Text(
                                text = "SELECT PANEL YEAR",
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.h6.copy(
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 1.sp
                                ),
                                color = AppColors.TextPrimary
                            )
                        },
                        backgroundColor = AppColors.PrimaryColor,
                        modifier = Modifier.height(60.dp),
                        elevation = 8.dp,
                        actions = {
                            IconButton(onClick = { isSearchActive = true }) {
                                Icon(
                                    Icons.Default.Search,
                                    "Search",
                                    tint = AppColors.TextPrimary
                                )
                            }
                        }
                    )
                }
            },
            backgroundColor = AppColors.DarkBackground
        ) { paddingValues ->
            when {
                filteredPanels.isEmpty() && searchQuery.isNotEmpty() -> {
                    EmptySearchResult(searchQuery = searchQuery)
                }
                filteredPanels.isEmpty() -> {
                    LoadingState()
                }
                else -> {
                    PanelListContent(
                        panels = filteredPanels,
                        onPanelSelected = { selectedPanel = it },
                        modifier = Modifier.padding(paddingValues)
                    )
                }
            }
        }
    } else {
        Show_Panel(selectedPanel!!)
    }
}

@Composable
private fun SearchAppBar(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    onCloseSearch: () -> Unit
) {
    TopAppBar(
        backgroundColor = AppColors.PrimaryColor,
        modifier = Modifier.height(60.dp),
        elevation = 8.dp
    ) {
        TextField(
            value = searchQuery,
            onValueChange = onSearchQueryChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 2.dp),
            colors = TextFieldDefaults.textFieldColors(
                textColor = AppColors.TextPrimary,
                backgroundColor = Color.Transparent,
                cursorColor = AppColors.TextPrimary,
                focusedIndicatorColor = AppColors.TextHighlight,
                unfocusedIndicatorColor = AppColors.TextSecondary
            ),
            placeholder = {
                Text("Search panels...", color = AppColors.TextSecondary)
            },
            singleLine = true,
            leadingIcon = {
                Icon(Icons.Default.Search, null, tint = AppColors.TextPrimary)
            },
            trailingIcon = {
                IconButton(onClick = onCloseSearch) {
                    Icon(Icons.Default.Close, null, tint = AppColors.TextPrimary)
                }
            }
        )
    }
}

@Composable
private fun PanelListContent(
    panels: List<Panel>,
    onPanelSelected: (Panel) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(panels) { panel ->
            PanelListItem(
                panel = panel,
                onClick = { onPanelSelected(panel) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
        }
    }
}

@Composable
private fun PanelListItem(
    panel: Panel,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }

    Card(
        modifier = modifier
            .height(80.dp)
            .clickable(
                interactionSource = interactionSource,
                indication = ripple(), // Use default ripple
                onClick = onClick
            ),
        elevation = 4.dp,
        shape = RoundedCornerShape(12.dp),
        backgroundColor = AppColors.CardSurface
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(
                            AppColors.CardGradientStart.copy(alpha = 0.7f),
                            AppColors.CardGradientEnd.copy(alpha = 0.7f)
                        )
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = panel.year,
                style = MaterialTheme.typography.h5.copy(
                    color = AppColors.TextPrimary,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 2.sp
                )
            )
        }
    }
}

@Composable
private fun EmptySearchResult(searchQuery: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            Icons.Default.Search,
            contentDescription = "No results",
            tint = AppColors.TextSecondary,
            modifier = Modifier.size(48.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "No panels found for '$searchQuery'",
            style = MaterialTheme.typography.body1.copy(
                color = AppColors.TextSecondary
            ),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun LoadingState() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(color = AppColors.PrimaryColor)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            "Loading panels...",
            color = AppColors.TextSecondary
        )
    }
}