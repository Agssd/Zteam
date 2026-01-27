package com.example.zteam.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.zteam.detail.DetailScreen
import com.example.zteam.detail.DetailViewModel
import com.example.zteam.list.GameListScreen
import com.example.zteam.list.ListViewModel
import com.example.zteam.search.SearchScreen
import com.example.zteam.search.SearchViewModel
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun AppNavHost(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = "game_list"
    ) {
        composable("game_list") {
            val viewModel: ListViewModel = koinViewModel()
            GameListScreen(
                viewModel,
                onGameClick = { gameId ->
                    navController.navigate("game_details/$gameId")
                },
                onSearchClick = { navController.navigate("game_search") }
            )
        }
        composable(
            route = "game_details/{gameId}",
            arguments = listOf(navArgument("gameId") { type = NavType.IntType })
        ) {
            val detailViewModel: DetailViewModel = viewModel()
            val gameId = it.arguments?.getInt("gameId") ?: 0
            DetailScreen(
                gameId = gameId,
                viewModel = detailViewModel,
                onBack = { navController.navigateUp() }
            )
        }
        composable(route = "game_search") {
            val searchViewModel: SearchViewModel = viewModel()
            SearchScreen(
                viewModel = searchViewModel,
                onGameClick = { gameId -> navController.navigate("game_details/$gameId") },
                onBack = { navController.navigateUp() }
            )
        }
    }
}