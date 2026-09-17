package br.edu.ifsp.scl.prdm.sc090578.navigationintent.ui.composable.component

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import br.edu.ifsp.scl.prdm.sc090578.navigationintent.R
import br.edu.ifsp.scl.prdm.sc090578.navigationintent.ui.theme.NavigationIntentTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopAppBar(showActions: Boolean, onCallPhone: () -> Unit = {}, onOpenDialer: () -> Unit = {}, onOpenWebNavigator: () -> Unit = {}, onOpenActivityOrApp: () -> Unit = {}, onNavigate: (String) -> Unit) {
    TopAppBar(
        title = { Text(stringResource(R.string.app_name)) },
        modifier = Modifier.fillMaxWidth(),
        colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        ),
        actions = {
            if (showActions)
                MainDropdownMenu(
                    onCallPhone = onCallPhone,
                    onOpenDialer = onOpenDialer,
                    onOpenWebNavigator = onOpenWebNavigator,
                    onOpenActivityOrApp = onOpenActivityOrApp,
                    onNavigate = onNavigate
                )
        }
    )
}

@Preview(
    name = "Light Mode",
    showBackground = true,
    uiMode = UI_MODE_NIGHT_NO
)
@Preview(
    name = "Dark Mode",
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
fun MainTopAppBarPreview() {
    NavigationIntentTheme {
        Surface{
            MainTopAppBar(true) { }
        }
    }
}