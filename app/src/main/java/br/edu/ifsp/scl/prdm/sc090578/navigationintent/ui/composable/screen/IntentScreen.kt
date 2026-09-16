package br.edu.ifsp.scl.prdm.sc090578.navigationintent.ui.composable.screen

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.edu.ifsp.scl.prdm.sc090578.navigationintent.ui.theme.NavigationIntentTheme

@Composable
fun IntentScreen(receivedParameter: String, modifier: Modifier) {
    val parameter by remember { mutableStateOf(receivedParameter) }
    Column(modifier = modifier.fillMaxWidth()) {
        Text(text = parameter)
    }
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
fun IntentScreenPreview() {
    NavigationIntentTheme {
        // Usando o Surface somente para dar cor de fundo no preview. Sem ele, o fundo fica
        // transparente e não dá para ver o preview em tema escuro.
        Surface {
            IntentScreen(
                receivedParameter = "",
                modifier = Modifier
            )
        }
    }
}