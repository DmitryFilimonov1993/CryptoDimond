 package com.cryptodimond.presentation.ui.bottomnav

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.cryptodimond.presentation.ui.aboutscreen.AboutScreen
import com.cryptodimond.presentation.ui.bottomnav.BottomNavItem.AboutTab
import com.cryptodimond.presentation.ui.bottomnav.BottomNavItem.CategoriesTab
import com.cryptodimond.presentation.ui.bottomnav.BottomNavItem.ExchangeTab
import com.cryptodimond.presentation.ui.bottomnav.BottomNavItem.LatestTab
import com.cryptodimond.presentation.ui.bottomnav.Graph.COIN_DETAILS
import com.cryptodimond.presentation.ui.bottomnav.Graph.EXCHANGE_DETAILS
import com.cryptodimond.presentation.ui.bottomnav.Graph.EXCHANGE_LIST
import com.cryptodimond.presentation.ui.categoriesscreen.CategoriesScreen
import com.cryptodimond.presentation.ui.coindetails.CoinDetailsScreen
import com.cryptodimond.presentation.ui.exchangedetails.ExchangesDetailsScreen
import com.cryptodimond.presentation.ui.exchangesscreen.ExchangesScreen
import com.cryptodimond.presentation.ui.exchangesscreen.ExchangesTopListScreen
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
                    Log.i("TEEEEEST", "coin id = $it")
                    navController.navigate(buildCoinDetailsRoute(it))
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
            ExchangesScreen(
                onClick = {
                    Log.i("TEEEEEST", "coin id = $it")
                    navController.navigate(buildExchangesDetailsRoute(it))
                }
            )
        }

        detailsNavGraph(navController)
        exchangesDetailsNavGraph(navController)
    }

}
 fun buildCoinDetailsRoute(argument: String) = "$COIN_DETAILS/$argument"
 fun buildExchangesDetailsRoute(argument: String) = "$EXCHANGE_DETAILS/$argument"
 fun buildExchangesTopListRoute(argument: String) = "$EXCHANGE_LIST/$argument"

 const val DestinationCoinDetailsArg = "coin_details_arg"
 const val DestinationExchangesDetailsArg = "exchanges_details_arg"
 const val DestinationExchangesTopListArg = "exchanges_top_list_arg"

 private const val DestinationCoinDetailsRoute = "$COIN_DETAILS/{$DestinationCoinDetailsArg}"
 private const val DestinationExchangesDetailsRoute = "$EXCHANGE_DETAILS/{$DestinationExchangesDetailsArg}"
 private const val DestinationExchangesTopListRoute = "$EXCHANGE_LIST/{$DestinationExchangesTopListArg}"

 fun NavGraphBuilder.exchangesDetailsNavGraph(navController: NavHostController) {
     navigation(
         route = Graph.EXCHANGE_DETAILS,
         startDestination = DetailsScreen.ExchangesDetails.route
     ) {
         composable(route = DestinationExchangesDetailsRoute) {
             ExchangesDetailsScreen()
         }
     }
 }

 fun NavGraphBuilder.detailsNavGraph(navController: NavHostController) {
     navigation(
         route = Graph.COIN_DETAILS,
         startDestination = DetailsScreen.CoinDetails.route
     ) {
         composable(route = DestinationCoinDetailsRoute) {


             CoinDetailsScreen(
                 onClick = {
                     navController.navigate(buildExchangesTopListRoute(it))
                 }
             )
         }

         composable(route = DestinationExchangesTopListRoute) {
             ExchangesTopListScreen(
                 onClick = {
                     navController.navigate(buildExchangesDetailsRoute(it))
                 }
             )
         }
     }
 }

 sealed class DetailsScreen(val route: String) {
     object ExchangesDetails : DetailsScreen(route = "EXCHANGES_DETAILS")
     object CoinDetails : DetailsScreen(route = "COIN_DETAILS")
     object ExchangesTopList : DetailsScreen(route = "EXCHANGES_TOP_LIST")
 }

 object Graph {
     const val COIN_DETAILS = "coin_details"
     const val CATEGORY_DETAILS = "category_details"
     const val EXCHANGE_DETAILS = "exchange_details"
     const val EXCHANGE_LIST = "exchange_list"
 }