package com.example.meet09.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.meet09.ui.mahasiswa.DestinasiInsert
import com.example.meet09.ui.mahasiswa.InsertMhsView

@Composable
fun PengolahHalaman(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiInsert.route) {
        composable(
            route = DestinasiInsert.route
        ){
            InsertMhsView(
                onBack = {},
                onNavigate = {}
            )
        }
    }
}