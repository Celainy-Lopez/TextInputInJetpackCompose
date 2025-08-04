package edu.ucne.textinputinjetpackcompose.presentation.collarCustomizationScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.ucne.textinputinjetpackcompose.R

@Composable
fun CustomizeCollarScreen() {

    val collarNameState = rememberTextFieldState()
    val collarPhoneState = rememberTextFieldState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.secondary),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_pet_app_2),
                contentDescription = "App Logo",
                modifier = Modifier.size(200.dp)
            )
            Text(
                text = "Customize Collar",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontSize = 28.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.tertiary
                ),
            )

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(360.dp)
                    .background(
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(180.dp)
                    )
                    .padding(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            color = MaterialTheme.colorScheme.tertiary,
                            shape = RoundedCornerShape(180.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        BasicText(
                            text = collarNameState.text.toString(),
                            autoSize = TextAutoSize.StepBased(),
                        )
                        Spacer(modifier = Modifier.height(4.dp))

                        BasicText(
                            text = collarPhoneState.text.toString(),
                            autoSize = TextAutoSize.StepBased(),
                        )
                    }
                }
            }


            Spacer(modifier = Modifier.height(24.dp))


            Card(
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onSecondary),
                elevation = CardDefaults.cardElevation(8.dp),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    OutlinedTextField(
                        state = collarNameState,
                        lineLimits = TextFieldLineLimits.SingleLine,
                        placeholder = { Text("CollarName") },
                        modifier = Modifier
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(32.dp),
                        leadingIcon = {
                            Icon(
                                Icons.Default.Pets,
                                contentDescription = "Collar Name",
                            )
                        },
                    )

                    OutlinedTextField(
                        state = collarPhoneState,
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text("CollarPhone") },
                        lineLimits = TextFieldLineLimits.SingleLine,
                        shape = RoundedCornerShape(32.dp),
                        leadingIcon = {
                            Icon(
                                Icons.Default.Phone,
                                contentDescription = "Collar Name",
                            )
                        },
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))


            Button(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(56.dp),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.tertiary
                )
            ) {
                Text(
                    text = "Submit",
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}
