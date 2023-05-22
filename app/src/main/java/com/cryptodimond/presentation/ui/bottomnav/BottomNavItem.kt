package com.cryptodimond.presentation.ui.bottomnav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Place
import androidx.compose.ui.graphics.vector.ImageVector
import com.cryptodimond.R as Res

sealed class BottomNavItem(
    val title: String,
    val icon: Int,
    val route: String
) {

    object LatestTab : BottomNavItem(
        "Latest",
        Res.mipmap.latest,
        "latest"
    )

    object ExchangeTab : BottomNavItem(
       "Exchanges",
        Res.mipmap.exchanges,
        "exchange"
    )

    object CategoriesTab : BottomNavItem(
        "Categories",
        Res.mipmap.categories,
        "categories"
    )

    object AboutTab : BottomNavItem(
        "About",
        Res.mipmap.about,
        "about"
    )
}
