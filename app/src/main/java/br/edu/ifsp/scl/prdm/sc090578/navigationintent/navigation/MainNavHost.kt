package br.edu.ifsp.scl.prdm.sc090578.navigationintent.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.edu.ifsp.scl.prdm.sc090578.navigationintent.MainViewModel
import br.edu.ifsp.scl.prdm.sc090578.navigationintent.ui.composable.screen.IntentScreen
import br.edu.ifsp.scl.prdm.sc090578.navigationintent.ui.composable.screen.ParameterScreen

@Composable
fun MainNavHost(
    navHostController: NavHostController,
    modifier: Modifier,
    mainViewModel: MainViewModel
) {
    val parameter by mainViewModel.parameterState.collectAsStateWithLifecycle()

    NavHost(
        navController = navHostController,
        startDestination = Screen.IntentScreen.route
    ) {
        composable(route = Screen.IntentScreen.route) {
            IntentScreen(
                receivedParameter = parameter,
                modifier = modifier
            )
        }
        composable(route = Screen.ParameterScreen.route) {
            ParameterScreen(
                receivedParameter = parameter,
                modifier = modifier,
                onSave = mainViewModel::updateParameter
            ) {
                navHostController.popBackStack()
            }
        }
    }
}