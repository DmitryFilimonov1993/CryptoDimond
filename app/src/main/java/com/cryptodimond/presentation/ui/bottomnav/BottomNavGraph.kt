 package com.cryptodimond.presentation.ui.bottomnav

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.cryptodimond.presentation.ui.aboutscreen.AboutApiUsageViewModel
import com.cryptodimond.presentation.ui.aboutscreen.AboutScreen
import com.cryptodimond.presentation.ui.bottomnav.BottomNavItem.AboutTab
import com.cryptodimond.presentation.ui.bottomnav.BottomNavItem.CategoriesTab
import com.cryptodimond.presentation.ui.bottomnav.BottomNavItem.ExchangeTab
import com.cryptodimond.presentation.ui.bottomnav.BottomNavItem.LatestTab
import com.cryptodimond.presentation.ui.bottomnav.Graph.COIN_DETAILS
import com.cryptodimond.presentation.ui.categoriesscreen.CategoriesScreen
import com.cryptodimond.presentation.ui.coindetails.CoinDetailsScreen
import com.cryptodimond.presentation.ui.exchangelist.ExchangeListScreen
import com.cryptodimond.presentation.ui.exchangesscreen.ExchangesScreen
import com.cryptodimond.presentation.ui.latestscreen.LatestScreen

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = LatestTab.route
    ) {
        composable(route = LatestTab.route) {
            LatestScreen(
                onClick = {
//                    navController.currentBackStackEntry?.savedStateHandle?.apply {
//                        set("coinId", it)
//                    }
                    Log.i("TEEEEEST", "coin id = $it")
                    navController.navigate(buildTwoRoute(it))
                }
            )
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

        detailsNavGraph(navController)
    }

}
 fun buildTwoRoute(argument: String) = "$COIN_DETAILS/$argument"

 const val DestinationOneArg = "arg"
 private const val DestinationTwoRoute = "$COIN_DETAILS/{$DestinationOneArg}"

 fun NavGraphBuilder.detailsNavGraph(navController: NavHostController) {
     navigation(
         route = Graph.COIN_DETAILS,
         startDestination = DetailsScreen.Information.route
     ) {
         composable(route = DestinationTwoRoute) {

             CoinDetailsScreen(
                 onClick = {
                     navController.navigate(DetailsScreen.Overview.route)
                 }
             )
         }
         composable(route = DetailsScreen.Overview.route) {

         }
     }
 }

 sealed class DetailsScreen(val route: String) {
     object Information : DetailsScreen(route = "INFORMATION")
     object Overview : DetailsScreen(route = "OVERVIEW")
 }

 object Graph {
     const val COIN_DETAILS = "coin_details"
     const val CATEGORY_DETAILS = "category_details"
     const val EXCHANGE_DETAILS = "exchange_details"
     const val EXCHANGE_LIST = "exchange_list"
 }