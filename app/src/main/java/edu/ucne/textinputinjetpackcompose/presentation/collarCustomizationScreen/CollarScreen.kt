package edu.ucne.textinputinjetpackcompose.presentation.collarCustomizationScreen

import android.R.attr.stepSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.maxLength
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.ucne.textinputinjetpackcompose.R
import kotlin.math.max
import kotlin.math.min

@Composable
fun CollarScreen(
    modifier: Modifier,
) {
    val collarNameState = rememberTextFieldState()
    val collarPhoneState = rememberTextFieldState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.secondary)
            .verticalScroll(rememberScrollState()),
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
                        )
                        .padding(16.dp), // Margen interno para el texto
                    contentAlignment = Alignment.Center
                ) {
                    var currentFontSize by remember { mutableStateOf(72.sp) }
                    val minFontSize = 32.sp
                    val maxFontSize = 112.sp
                    val stepSize = 0.005.sp
                    Text(
                        text = collarNameState.text.toString() + "\n${collarPhoneState.text}",
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .padding(8.dp),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.headlineLarge.copy(
                            lineHeight = currentFontSize * 1.01f
                        ),
                        overflow = TextOverflow.Ellipsis,
                        autoSize = TextAutoSize.StepBased(
                            minFontSize = 32.sp,
                            maxFontSize = 72.sp,
                        ),
                        maxLines = 3,
                        softWrap = true,
                        onTextLayout = { textLayoutResult ->
                            val currentSizeValue = currentFontSize.value // Obtenemos el valor Float
                            val stepValue = stepSize.value

                            if (textLayoutResult.didOverflowHeight || textLayoutResult.didOverflowWidth) {
                                // Reducir (operando con Floats)
                                currentFontSize =
                                    (currentSizeValue - stepValue).coerceAtLeast(minFontSize.value).sp
                            } else if (currentSizeValue < maxFontSize.value) {
                                // Aumentar (operando con Floats)
                                currentFontSize =
                                    (currentSizeValue + stepValue).coerceAtMost(maxFontSize.value).sp
                            }
                        }
                    )
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
                        inputTransformation = InputTransformation.maxLength(16),
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
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                        inputTransformation = InputTransformation.maxLength(10),
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

@Preview
@Composable
fun Preview() {
    CollarScreen(Modifier)
}
