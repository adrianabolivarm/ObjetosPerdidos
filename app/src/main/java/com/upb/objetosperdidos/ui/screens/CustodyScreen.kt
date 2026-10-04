package com.upb.objetosperdidos.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.upb.objetosperdidos.ui.theme.ObjetosPerdidosTheme

// Design Tokens
private val PrimaryBlue = Color(0xFF1689FF)
private val SoftBlue = Color(0xFFEAF4FF)
private val AppBackground = Color(0xFFF7F7FB)
private val TextPrimary = Color(0xFF181820)
private val TextSecondary = Color(0xFF777783)
private val WarningOrange = Color(0xFFD97706)
private val WarningOrangeSoft = Color(0xFFFFF7ED)
private val BorderColor = Color(0xFFE5E5EC)
private val SurfaceWhite = Color(0xFFFFFFFF)
private val DangerRed = Color(0xFFDC3545)
private val DangerRedSoft = Color(0xFFFDE8E8)

@Composable
fun CustodyScreen(
    onBackClick: () -> Unit = {},
    onViewDeliveryPointsClick: () -> Unit = {},
    onCancelReportClick: () -> Unit = {}
) {
    Scaffold(
        containerColor = AppBackground
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // 1. Encabezado
            item {
                HeaderSection(onBackClick = onBackClick)
            }

            // 2. Tarjeta principal del objeto en custodia con chip de estado
            item {
                ObjectCustodyCard()
            }

            // 3. Ubicación actual del objeto
            item {
                CustodyLocationCard()
            }

            // 4. Código QR / Código de verificación
            item {
                VerificationCodeCard()
            }

            // 5. Botones de acción principales
            item {
                ActionButtonsSection(
                    onViewDeliveryPointsClick = onViewDeliveryPointsClick,
                    onCancelReportClick = onCancelReportClick
                )
            }
        }
    }
}

@Composable
private fun HeaderSection(onBackClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .size(42.dp)
                .clip(CircleShape)
                .background(SurfaceWhite)
                .border(1.dp, BorderColor, CircleShape)
                .clickable { onBackClick() },
            contentAlignment = Alignment.Center
        ) {
            BackArrowIcon(tint = TextPrimary)
        }

        Text(
            text = "Estado de Custodia",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = TextPrimary
        )

        Spacer(modifier = Modifier.size(42.dp))
    }
}

@Composable
private fun ObjectCustodyCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(18.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Termo Yeti Azul 26oz",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Reporte #REP-2023-8841",
                        fontSize = 12.sp,
                        color = TextSecondary
                    )
                }

                // Chip de estado ("En custodia temporal")
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = WarningOrangeSoft,
                    border = BorderStroke(1.dp, WarningOrange.copy(alpha = 0.3f))
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        ClockCanvasIcon(tint = WarningOrange, modifier = Modifier.size(14.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "En custodia temporal",
                            color = WarningOrange,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(BorderColor)
            )

            Spacer(modifier = Modifier.height(14.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Categoría",
                        fontSize = 12.sp,
                        color = TextSecondary
                    )
                    Text(
                        text = "Accesorios Personales",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = TextPrimary
                    )
                }

                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "Fecha de Ingreso",
                        fontSize = 12.sp,
                        color = TextSecondary
                    )
                    Text(
                        text = "Hoy, 11:15 AM",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = TextPrimary
                    )
                }
            }
        }
    }
}

@Composable
private fun CustodyLocationCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(18.dp)
        ) {
            Text(
                text = "Ubicación de Custodia",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )

            Spacer(modifier = Modifier.height(14.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(SoftBlue),
                    contentAlignment = Alignment.Center
                ) {
                    LocationPinCanvasIcon(tint = PrimaryBlue, modifier = Modifier.size(22.dp))
                }

                Spacer(modifier = Modifier.width(14.dp))

                Column {
                    Text(
                        text = "Punto Autorizado - Biblioteca Principal",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "Recepción de Objetos • Bloque Central, Piso 1",
                        fontSize = 12.sp,
                        color = TextSecondary
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                color = AppBackground
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Horario de recepción:",
                        fontSize = 12.sp,
                        color = TextSecondary,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "08:00 AM - 06:00 PM",
                        fontSize = 12.sp,
                        color = TextPrimary,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
private fun VerificationCodeCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Código de Verificación",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Muestra este código QR o código de entrega en el punto autorizado para retirar o verificar tu objeto.",
                fontSize = 12.sp,
                color = TextSecondary,
                textAlign = TextAlign.Center,
                lineHeight = 16.sp
            )

            Spacer(modifier = Modifier.height(18.dp))

            // Gráfico QR Code Mock
            Box(
                modifier = Modifier
                    .size(160.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(AppBackground)
                    .border(1.dp, BorderColor, RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) {
                QrCodeCanvasGraphic(modifier = Modifier.size(130.dp))
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Texto con código alfanumérico
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = SoftBlue,
                border = BorderStroke(1.dp, PrimaryBlue.copy(alpha = 0.2f))
            ) {
                Text(
                    text = "CUST-9824-2023",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily.Monospace,
                    color = PrimaryBlue,
                    modifier = Modifier.padding(horizontal = 18.dp, vertical = 8.dp)
                )
            }
        }
    }
}

@Composable
private fun ActionButtonsSection(
    onViewDeliveryPointsClick: () -> Unit,
    onCancelReportClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Button(
            onClick = onViewDeliveryPointsClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = PrimaryBlue,
                contentColor = SurfaceWhite
            )
        ) {
            Text(
                text = "Ver puntos de entrega cercanos",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
        }

        OutlinedButton(
            onClick = onCancelReportClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = DangerRed
            ),
            border = BorderStroke(1.dp, DangerRedSoft)
        ) {
            Text(
                text = "Cancelar reporte",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

// Custom Graphics (Canvas Icons)
@Composable
private fun BackArrowIcon(
    tint: Color,
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier.size(18.dp)) {
        val w = size.width
        val h = size.height
        val strokeWidth = 2.2.dp.toPx()

        drawLine(
            color = tint,
            start = Offset(w * 0.85f, h * 0.5f),
            end = Offset(w * 0.15f, h * 0.5f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round
        )
        drawLine(
            color = tint,
            start = Offset(w * 0.15f, h * 0.5f),
            end = Offset(w * 0.45f, h * 0.2f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round
        )
        drawLine(
            color = tint,
            start = Offset(w * 0.15f, h * 0.5f),
            end = Offset(w * 0.45f, h * 0.8f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round
        )
    }
}

@Composable
private fun ClockCanvasIcon(
    tint: Color,
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier.size(16.dp)) {
        val w = size.width
        val h = size.height
        val stroke = 1.8.dp.toPx()

        drawCircle(
            color = tint,
            radius = w * 0.42f,
            center = Offset(w * 0.5f, h * 0.5f),
            style = Stroke(width = stroke)
        )
        drawLine(
            color = tint,
            start = Offset(w * 0.5f, h * 0.5f),
            end = Offset(w * 0.5f, h * 0.25f),
            strokeWidth = stroke,
            cap = StrokeCap.Round
        )
        drawLine(
            color = tint,
            start = Offset(w * 0.5f, h * 0.5f),
            end = Offset(w * 0.7f, h * 0.5f),
            strokeWidth = stroke,
            cap = StrokeCap.Round
        )
    }
}

@Composable
private fun LocationPinCanvasIcon(
    tint: Color,
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier.size(20.dp)) {
        val w = size.width
        val h = size.height

        val path = Path().apply {
            moveTo(w * 0.5f, h * 0.95f)
            cubicTo(w * 0.2f, h * 0.65f, w * 0.12f, h * 0.48f, w * 0.12f, h * 0.36f)
            cubicTo(w * 0.12f, h * 0.16f, w * 0.29f, 0f, w * 0.5f, 0f)
            cubicTo(w * 0.71f, 0f, w * 0.88f, h * 0.16f, w * 0.88f, h * 0.36f)
            cubicTo(w * 0.88f, h * 0.48f, w * 0.8f, h * 0.65f, w * 0.5f, h * 0.95f)
            close()
        }
        drawPath(path = path, color = tint)

        drawCircle(
            color = Color.White,
            radius = w * 0.15f,
            center = Offset(w * 0.5f, h * 0.36f)
        )
    }
}

@Composable
private fun QrCodeCanvasGraphic(
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height
        val mainColor = Color(0xFF181820)

        // Corner squares (QR positioning marks)
        fun drawCornerMark(x: Float, y: Float, boxSize: Float) {
            drawRoundRect(
                color = mainColor,
                topLeft = Offset(x, y),
                size = Size(boxSize, boxSize),
                cornerRadius = CornerRadius(4.dp.toPx()),
                style = Stroke(width = 3.dp.toPx())
            )
            drawRoundRect(
                color = mainColor,
                topLeft = Offset(x + boxSize * 0.25f, y + boxSize * 0.25f),
                size = Size(boxSize * 0.5f, boxSize * 0.5f),
                cornerRadius = CornerRadius(2.dp.toPx())
            )
        }

        val markSize = w * 0.28f
        drawCornerMark(0f, 0f, markSize)
        drawCornerMark(w - markSize, 0f, markSize)
        drawCornerMark(0f, h - markSize, markSize)

        // Random pattern modules in middle and bottom right
        val pSize = w * 0.08f
        val points = listOf(
            Offset(w * 0.4f, h * 0.1f),
            Offset(w * 0.55f, h * 0.1f),
            Offset(w * 0.4f, h * 0.25f),
            Offset(w * 0.1f, h * 0.45f),
            Offset(w * 0.25f, h * 0.45f),
            Offset(w * 0.45f, h * 0.45f),
            Offset(w * 0.6f, h * 0.45f),
            Offset(w * 0.8f, h * 0.45f),
            Offset(w * 0.45f, h * 0.6f),
            Offset(w * 0.65f, h * 0.6f),
            Offset(w * 0.45f, h * 0.8f),
            Offset(w * 0.65f, h * 0.8f),
            Offset(w * 0.8f, h * 0.8f),
            Offset(w * 0.8f, h * 0.65f)
        )

        for (pt in points) {
            drawRoundRect(
                color = mainColor,
                topLeft = pt,
                size = Size(pSize, pSize),
                cornerRadius = CornerRadius(1.dp.toPx())
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CustodyScreenPreview() {
    ObjetosPerdidosTheme {
        CustodyScreen()
    }
}
