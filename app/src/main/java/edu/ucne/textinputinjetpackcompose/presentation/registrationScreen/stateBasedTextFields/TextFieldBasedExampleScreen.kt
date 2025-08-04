import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.OutputTransformation
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable

@Composable
fun StateBasedTextField() {
    val usernameState = rememberTextFieldState()

    TextField(
        state = usernameState,
        inputTransformation = InputTransformation {  },
        outputTransformation = OutputTransformation { },
        lineLimits = TextFieldLineLimits.SingleLine,
        placeholder = { Text("Ej: Juan Pérez") }
    )
}
