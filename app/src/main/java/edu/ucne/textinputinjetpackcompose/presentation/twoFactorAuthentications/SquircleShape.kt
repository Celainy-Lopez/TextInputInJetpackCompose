package edu.ucne.textinputinjetpackcompose.presentation.twoFactorAuthentications

import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.Path
import androidx.compose.foundation.shape.GenericShape
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sign

val SquircleShape: Shape = GenericShape { size, _ ->
    val width = size.width
    val height = size.height
    val centerX = width / 2
    val centerY = height / 2
    val radiusX = width / 2
    val radiusY = height / 2

    val n = 4f
    val steps = 360
    val path = Path()
    for (i in 0..steps) {
        val theta = Math.toRadians(i.toDouble())
        val cosTheta = kotlin.math.cos(theta)
        val sinTheta = kotlin.math.sin(theta)

        val x = centerX + radiusX * sign(cosTheta) * abs(cosTheta).toFloat().pow(2f / n)
        val y = centerY + radiusY * sign(sinTheta) * abs(sinTheta).toFloat().pow(2f / n)

        if (i == 0) {
            path.moveTo(x.toFloat(), y.toFloat())
        } else {
            path.lineTo(x.toFloat(), y.toFloat())
        }
    }
    path.close()
    addPath(path)
}

