package com.cryptodimond.presentation.ui.bottomnav

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cryptodimond.presentation.ui.aboutscreen.AboutScreen
import com.cryptodimond.presentation.ui.bottomnav.BottomNavItem.AboutTab
import com.cryptodimond.presentation.ui.bottomnav.BottomNavItem.CategoriesTab
import com.cryptodimond.presentation.ui.bottomnav.BottomNavItem.ExchangeTab
import com.cryptodimond.presentation.ui.bottomnav.BottomNavItem.LatestTab
import com.cryptodimond.presentation.ui.categoriesscreen.CategoriesScreen
import com.cryptodimond.presentation.ui.exchangesscreen.ExchangesScreen
import com.cryptodimond.presentation.ui.latestscreen.LatestScreen

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = LatestTab.route
    ) {
        composable(route = LatestTab.route) {
            LatestScreen()
        }
        composable(route = CategoriesTab.route) {
            CategoriesScreen()
        }
        composable(route = AboutTab.route) {
            AboutScreen()
        }
        composable(route = ExchangeTab.route) {
            ExchangesScreen()
        }
    }

}