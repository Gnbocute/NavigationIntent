package br.edu.ifsp.scl.prdm.sc090578.navigationintent.ui.composable.screen

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.edu.ifsp.scl.prdm.sc090578.navigationintent.ui.theme.NavigationIntentTheme
import androidx.compose.material3.Surface

@Composable
fun ParameterScreen(
    receivedParameter: String,
    modifier: Modifier,
    onSave: (String) -> Unit,
    onQuit: () -> Unit
) {
    var parameter by remember { mutableStateOf(receivedParameter) }
    Column(modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = parameter,
            label = { Text("Parameter") },
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { parameter = it }
        )
        Button(
            onClick = {
                onSave(parameter)
                onQuit()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save and quit")
        }
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
fun ParameterScreenPreview() {
    NavigationIntentTheme {
        // Usando o Surface somente para dar cor de fundo no preview. Sem ele, o fundo fica
        // transparente e não dá para ver o preview em tema escuro.
        Surface {
            ParameterScreen(
                receivedParameter = "",
                modifier = Modifier,
                onSave = { }
            ) { }
        }
    }
}