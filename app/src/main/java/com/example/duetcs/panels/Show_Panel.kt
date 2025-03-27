import androidx.compose.animation.AnimatedContent

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.core.animateDpAsState


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.layout.ContentScale
import com.example.duetcs.R
import com.example.duetcs.panels.Panel
import com.example.duetcs.panels.PanelMember


// Improved Color Palette with Better Contrast
object AppColors {
    // Background colors
    val DarkBackground = Color(0xFF121212)
    val CardSurface = Color(0xFF1E1E1E)

    // Text colors
    val TextPrimary = Color(0xFFFFFFFF)
    val TextSecondary = Color(0xFFCCCCCC)
    val TextHighlight = Color(0xFF03DAC5)

    // Accent colors
    val PrimaryColor = Color(0xFF6200EE)  // Deep Purple
    val SecondaryColor = Color(0xFF03DAC5) // Teal
    val AccentColor = Color(0xFFFF6D00)    // Vibrant Orange

    // Button colors
    val CallButton = Color(0xFF4CAF50)     // Green
    val EmailButton = Color(0xFF2196F3)    // Blue

    // Card gradients
    val CardGradientStart = Color(0xFF1A237E) // Dark Blue
    val CardGradientEnd = Color(0xFF0D47A1)   // Lighter Blue
}

@Composable
fun PanelTopAppBar(
    year: String,
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    onSearchStateChange: (Boolean) -> Unit,
    isSearchVisible: Boolean,
    modifier: Modifier = Modifier
) {
    val gradientBrush = Brush.horizontalGradient(
        colors = listOf(Color(0xFF6A11CB), Color(0xFF2575FC))
    )

    TopAppBar(
        modifier = modifier
            .height(80.dp)
            .shadow(elevation = 12.dp)
            .background(gradientBrush),
        elevation = 0.dp,
        backgroundColor = Color.Transparent,
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Back button when search is visible
            if (isSearchVisible) {
                IconButton(
                    onClick = { onSearchStateChange(false) },
                    modifier = Modifier.size(40.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
            } else {

                Image(
                    painter = androidx.compose.ui.res.painterResource(id = R.drawable.group),
                    contentDescription = "App Logo",
                    modifier = Modifier
                        .size(40.dp)
                        .padding(end = 8.dp),
                    contentScale = ContentScale.Fit
                )
            }

            // Animated content area
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {
                if (isSearchVisible) {
                    TextField(
                        value = searchText,
                        onValueChange = onSearchTextChange,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = Color.White,
                            cursorColor = Color.White,
                            backgroundColor = Color.Transparent,
                            focusedIndicatorColor = Color.White.copy(alpha = 0.7f),
                            unfocusedIndicatorColor = Color.White.copy(alpha = 0.3f)
                        ),
                        placeholder = {
                            Text("Search name, email, etc...", color = Color.White.copy(alpha = 0.5f))
                        },
                        singleLine = true,
                        leadingIcon = {
                            Icon(
                                Icons.Default.Search,
                                contentDescription = "Search",
                                tint = Color.White
                            )
                        },
                        trailingIcon = {
                            if (searchText.isNotEmpty()) {
                                IconButton(onClick = { onSearchTextChange("") }) {
                                    Icon(
                                        Icons.Default.Close,
                                        contentDescription = "Clear",
                                        tint = Color.White
                                    )
                                }
                            }
                        },
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                        keyboardActions = KeyboardActions(
                            onSearch = { /* Optional: handle keyboard search action */ }
                        )
                    )
                } else {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "PANEL MEMBERS",
                            style = MaterialTheme.typography.subtitle1.copy(
                                color = Color.White.copy(alpha = 0.8f),
                                letterSpacing = 4.sp,
                                fontWeight = FontWeight.Medium
                            )
                        )
                        Text(
                            text = year,
                            style = MaterialTheme.typography.h5.copy(
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.sp
                            )
                        )
                    }
                }
            }

            // Search toggle button
            if (!isSearchVisible) {
                IconButton(
                    onClick = { onSearchStateChange(true) },
                    modifier = Modifier.size(40.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun Show_Panel(panel: Panel) {
    var searchText by remember { mutableStateOf("") }
    var isSearchVisible by remember { mutableStateOf(false) }

    val filteredMembers = remember(panel.members, searchText) {
        if (searchText.isBlank()) {
            panel.members
        } else {
            panel.members.filter { member ->
                member.name.contains(searchText, ignoreCase = true) ||
                        member.designation.contains(searchText, ignoreCase = true) ||
                        member.studentID.contains(searchText, ignoreCase = true) ||
                        member.series.contains(searchText, ignoreCase = true) ||
                        member.phone.contains(searchText, ignoreCase = true) ||
                        member.email.contains(searchText, ignoreCase = true) ||
                        member.currentDesignation.contains(searchText, ignoreCase = true)
            }
        }
    }

    Column(
        modifier = Modifier
            .background(AppColors.DarkBackground)
            .fillMaxSize()
    ) {
        PanelTopAppBar(
            year = panel.year,
            searchText = searchText,
            onSearchTextChange = { searchText = it },
            onSearchStateChange = { isSearchVisible = it },
            isSearchVisible = isSearchVisible
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp, vertical = 16.dp)
        ) {
            items(filteredMembers) { item ->
                PanelMemberCard(item)
                Spacer(modifier = Modifier.height(8.dp))
            }

            if (filteredMembers.isEmpty() && searchText.isNotEmpty()) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No members found for '$searchText'",
                            style = MaterialTheme.typography.body1.copy(
                                color = AppColors.TextSecondary
                            )
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun PanelMemberCard(item: PanelMember) {
    val context = LocalContext.current
    var isExpanded by remember { mutableStateOf(false) }

    val gradientBrush = Brush.verticalGradient(
        colors = listOf(AppColors.CardGradientStart, AppColors.CardGradientEnd),
        startY = 0f,
        endY = 500f
    )

    // Animation values
    val elevation by animateDpAsState(targetValue = if (isExpanded) 12.dp else 4.dp)
    val bottomPadding by animateDpAsState(targetValue = if (isExpanded) 16.dp else 8.dp)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .shadow(elevation = elevation, shape = RoundedCornerShape(12.dp))
            .clickable { isExpanded = !isExpanded },
        shape = RoundedCornerShape(12.dp),
        elevation = 0.dp // Using shadow modifier instead
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(gradientBrush)
                .clip(RoundedCornerShape(12.dp))
                .padding(
                    top = 16.dp,
                    start = 16.dp,
                    end = 16.dp,
                    bottom = bottomPadding
                )
        ) {
            Column {
                // Always visible content
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = item.name,
                        style = MaterialTheme.typography.h6.copy(
                            color = AppColors.TextPrimary,
                            fontWeight = FontWeight.Bold
                        ),
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = item.designation,
                        style = MaterialTheme.typography.subtitle1.copy(
                            color = AppColors.TextHighlight,
                            fontWeight = FontWeight.SemiBold
                        ),
                        textAlign = TextAlign.Center
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Member Info
                InfoRow("Student ID:", item.studentID)
                InfoRow("Series:", item.series)
                InfoRow("Phone:", item.phone)
                InfoRow("Email:", item.email)

                if (item.currentDesignation.isNotEmpty()) {
                    InfoRow("Current Designation:", item.currentDesignation)
                }

                // Animated buttons section
                AnimatedVisibility(
                    visible = isExpanded,
                    enter = fadeIn() + expandVertically(
                        expandFrom = Alignment.Top,
                        animationSpec = tween(durationMillis = 300)
                    ),
                    exit = fadeOut() + shrinkVertically(
                        shrinkTowards = Alignment.Top,
                        animationSpec = tween(durationMillis = 250)
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Call and Email Buttons
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Button(
                                onClick = { makeCall(item.phone, context) },
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = AppColors.CallButton,
                                    contentColor = AppColors.TextPrimary
                                ),
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(horizontal = 8.dp)
                                    .height(48.dp),
                                elevation = ButtonDefaults.elevation(
                                    defaultElevation = 4.dp,
                                    pressedElevation = 8.dp
                                )
                            ) {
                                Icon(Icons.Default.Call, contentDescription = "Call")
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("Call")
                            }

                            Button(
                                onClick = { sendEmail(item.email, context) },
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = AppColors.EmailButton,
                                    contentColor = AppColors.TextPrimary
                                ),
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(horizontal = 8.dp)
                                    .height(48.dp),
                                elevation = ButtonDefaults.elevation(
                                    defaultElevation = 4.dp,
                                    pressedElevation = 8.dp
                                )
                            ) {
                                Icon(Icons.Default.Email, contentDescription = "Email")
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("Email")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.body2.copy(
                color = AppColors.TextSecondary,
                fontWeight = FontWeight.SemiBold
            ),
            modifier = Modifier.width(120.dp)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.body2.copy(
                color = AppColors.TextPrimary,
                fontWeight = FontWeight.Normal
            ),
            modifier = Modifier.weight(1f)
        )
    }
}

// Function to make a phone call
fun makeCall(phoneNumber: String, context: Context) {
    val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
    context.startActivity(intent)
}

// Function to send an email
fun sendEmail(email: String, context: Context) {
    val intent = Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("mailto:$email")
    }
    context.startActivity(Intent.createChooser(intent, "Choose an email client"))
}