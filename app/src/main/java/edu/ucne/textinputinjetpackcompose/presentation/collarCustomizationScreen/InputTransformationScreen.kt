package edu.ucne.textinputinjetpackcompose.presentation.collarCustomizationScreen

import android.text.TextUtils
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.maxLength
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.text.input.then
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.ucne.textinputinjetpackcompose.R

@Composable
fun  InputTransformationScreen () {

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
                    )  {
                        Text(
                            text = collarNameState.text.toString(),
                            fontWeight = FontWeight.ExtraBold,
                            style = MaterialTheme.typography.headlineLarge
                        )
                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = collarPhoneState.text.toString(),
                            fontWeight = FontWeight.ExtraBold,
                            style = MaterialTheme.typography.headlineLarge
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

                    TextField(
                        state = collarPhoneState,
                        placeholder = { Text("CollarPhone") },
                        inputTransformation = InputTransformation.maxLength(10).then {
                            if (!TextUtils.isDigitsOnly(asCharSequence())){
                                revertAllChanges()
                            }
                        },
                        lineLimits = TextFieldLineLimits.SingleLine,
                        modifier = Modifier.fillMaxWidth(),
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
