package edu.ucne.textinputinjetpackcompose.presentation.registrationScreen.SecureTextField
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedSecureTextField
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.ContentType
import androidx.compose.ui.platform.LocalAutofillManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentType
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import edu.ucne.textinputinjetpackcompose.R


@Composable
fun PasswordManagerScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_pet_app),
            contentDescription = "Logo",
            modifier = Modifier
                .size(300.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Welcome Back",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
        )

        Spacer(modifier = Modifier.height(24.dp))

        val autoFillManager = LocalAutofillManager.current
        OutlinedTextField(
            state = rememberTextFieldState(),
            lineLimits = TextFieldLineLimits.SingleLine,
            placeholder = {Text("Username")},
            modifier = Modifier
                .semantics {
                    contentType = ContentType.NewUsername
                }
                .fillMaxWidth(),
            shape = RoundedCornerShape(32.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        val passwordState = rememberTextFieldState()
        OutlinedSecureTextField(
            state = passwordState,
            placeholder = {Text("Password")},
            supportingText = {
                Text("Password must be at least 8 characters")
            },
            isError = passwordState.text.length in 1..7,
            modifier = Modifier
                .semantics {
                    contentType = ContentType.NewPassword
                }
                .fillMaxWidth(),
            shape = RoundedCornerShape(32.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Forgot Password?",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.align(Alignment.End)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = ({ autoFillManager?.commit() }),
            enabled = passwordState.text.length >= 8,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ),
            shape = RoundedCornerShape(25.dp)
        ) {
            Text(text = "Sign Up", style = MaterialTheme.typography.bodyLarge)
        }
    }
}
