package br.edu.ifsp.scl.prdm.sc090578.navigationintent

import android.Manifest.permission.CALL_PHONE
import android.content.Intent
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.net.toUri
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import br.edu.ifsp.scl.prdm.sc090578.navigationintent.navigation.MainNavHost
import br.edu.ifsp.scl.prdm.sc090578.navigationintent.navigation.Screen
import br.edu.ifsp.scl.prdm.sc090578.navigationintent.ui.composable.component.MainTopAppBar
import br.edu.ifsp.scl.prdm.sc090578.navigationintent.ui.theme.NavigationIntentTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navHostController = rememberNavController()

            val navBackStackEntry by navHostController.currentBackStackEntryAsState()
            val showActions = navBackStackEntry?.destination?.route == Screen.IntentScreen.route

            val mainViewModel: MainViewModel = viewModel()

            val parameter by mainViewModel.parameterState.collectAsStateWithLifecycle()

            val callPermissionLauncher = rememberLauncherForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) {isGranted ->
                if(isGranted){
                    dialOrCallPhone(isDial = true, phoneNumber = parameter)
                }
                else{
                    Toast.makeText(this@MainActivity,
                        "Call permission required to continue",
                        Toast.LENGTH_SHORT)
                        .show()
                }
            }

            NavigationIntentTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        MainTopAppBar(
                            showActions = showActions,
                            onCallPhone = {
                                if(checkSelfPermission(CALL_PHONE) == PERMISSION_GRANTED) {
                                    dialOrCallPhone(isDial = false, parameter)
                                }
                                else{
                                    //Solicitar permissão
                                    callPermissionLauncher.launch(CALL_PHONE)
                                }
                            },
                            onOpenDialer = {
                                dialOrCallPhone(isDial = true, parameter)
                            },
                            onOpenWebNavigator = {
                                startActivity(Intent(Intent.ACTION_VIEW, parameter.toUri()))
                            },
                            onOpenActivityOrApp = {
                                startActivity(Intent("MY_PERSONALIZED_ACTION"))
                            }
                        ) { destination ->
                            navHostController.navigate(destination)
                        }
                    }
                ) { innerPadding ->
                    MainNavHost(
                        navHostController = navHostController,
                        modifier = Modifier.padding(innerPadding),
                        mainViewModel = mainViewModel
                    )
                }
            }
        }
    }

    private fun dialOrCallPhone(isDial:Boolean, phoneNumber: String){
        Intent(if (isDial) Intent.ACTION_DIAL else Intent.ACTION_CALL).apply {
            data = "tel: $phoneNumber".toUri()
            startActivity(this)
        }

    }
}