package com.cryptodimond.presentation.ui.bottomnav

import androidx.annotation.StringRes
import com.cryptodimond.R as Res

sealed class BottomNavItem(
    @StringRes val title: Int,
    val icon: Int,
    val route: String
) {

    object LatestTab : BottomNavItem(
        Res.string.latest_tab_name,
        Res.mipmap.latest,
        "latest"
    )

    object ExchangeTab : BottomNavItem(
        Res.string.exchanges_tab_name,
        Res.mipmap.exchanges,
        "exchange"
    )

    object CategoriesTab : BottomNavItem(
        Res.string.categories_tab_name,
        Res.mipmap.categories,
        "categories"
    )

    object AboutTab : BottomNavItem(
        Res.string.about_tab_name,
        Res.mipmap.about,
        "about"
    )
}
