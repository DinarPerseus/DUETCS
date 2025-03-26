package com.example.duetcs

data class BottomNavItem(val title: String, val icon: Int, val route: String)
val bottomNavItems = listOf(
    BottomNavItem("About", R.drawable.duetcs , "about"),
    BottomNavItem("Panels", R.drawable.members, "panels"),
    BottomNavItem("Contributor", R.drawable.contributor, "contributor")
)
