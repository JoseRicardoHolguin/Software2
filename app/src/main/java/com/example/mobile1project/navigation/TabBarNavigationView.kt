package com.example.mobile1project.navigation

import IMCScreen
import TempView
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import androidx.navigation.compose.rememberNavController
import com.example.mobile1project.Ids.IdsView
import com.example.mobile1project.FirstPartial.FirstPartialView
import com.example.mobile1project.SecondPartial.SecondPartialView
import com.example.mobile1project.Ids.login.views.LoginView
import com.example.mobile1project.ListaEstudiantes.View.StudentView
import com.example.mobile1project.ThirdPartial.API.View.LocationListView
import com.example.mobile1project.ThirdPartial.ThirdPartialView

@Composable
fun TabBarNavigationView(navController: NavHostController = rememberNavController()) {
    val items = listOf(
        ScreenNavigation.Ids,
        ScreenNavigation.FirstPartial,
        ScreenNavigation.SecondPartial,
        ScreenNavigation.ThirdPartial
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                items.forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(screen.icon, contentDescription = screen.label) },
                        label = { Text(screen.label) },
                        selected = currentRoute == screen.route,
                        onClick = {
                            if (currentRoute != screen.route) {
                                navController.navigate(screen.route)
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = ScreenNavigation.Ids.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(ScreenNavigation.Ids.route) { IdsView(navController) }
            composable(ScreenNavigation.FirstPartial.route) { FirstPartialView() }
            composable(ScreenNavigation.SecondPartial.route) { SecondPartialView() }
            composable(ScreenNavigation.ThirdPartial.route) { ThirdPartialView(navController) }
            composable(ScreenNavigation.IMC.route) { IMCScreen() }
            composable(ScreenNavigation.Login.route) { LoginView() }
            composable(ScreenNavigation.Temperature.route) { TempView() }
            composable("studentList") { StudentView() }
            composable("GymAPI") { LocationListView() }
            }

        }
        }

