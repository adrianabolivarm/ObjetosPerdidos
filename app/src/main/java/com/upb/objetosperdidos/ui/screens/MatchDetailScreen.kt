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
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
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
private val SuccessGreen = Color(0xFF198754)
private val SuccessGreenSoft = Color(0xFFE8F7F0)
private val BorderColor = Color(0xFFE5E5EC)
private val SurfaceWhite = Color(0xFFFFFFFF)
private val DangerRed = Color(0xFFDC3545)
private val DangerRedSoft = Color(0xFFFDE8E8)

@Composable
fun MatchDetailScreen(
    onBackClick: () -> Unit = {},
    onConfirmClick: () -> Unit = {},
    onRejectClick: () -> Unit = {}
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
            // 1. Encabezado con título
            item {
                HeaderSection(onBackClick = onBackClick)
            }

            // Badge con el porcentaje (95% Coincidencia)
            item {
                MatchBadgeBanner(percentage = "95%", matchLevel = "Alta Coincidencia")
            }

            // 2. Tarjeta con la comparativa ("Tu reporte" vs "Encontrado por comunidad")
            item {
                ComparisonCard()
            }

            // 3. Lista de puntos de verificación (Categoría, Ubicación, Fecha, Estado de Custodia)
            item {
                VerificationPointsSection()
            }

            // 4. Botones de acción principales ("Confirmar y solicitar devolución" y "No es mi objeto")
            item {
                ActionButtonsSection(
                    onConfirmClick = onConfirmClick,
                    onRejectClick = onRejectClick
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
            text = "Detalle de Coincidencia",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = TextPrimary
        )

        Spacer(modifier = Modifier.size(42.dp))
    }
}

@Composable
private fun MatchBadgeBanner(
    percentage: String,
    matchLevel: String
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        color = SuccessGreenSoft
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(SuccessGreen),
                    contentAlignment = Alignment.Center
                ) {
                    CheckmarkIcon(tint = SurfaceWhite, modifier = Modifier.size(20.dp))
                }

                Spacer(modifier = Modifier.width(14.dp))

                Column {
                    Text(
                        text = "Coincidencia Detectada",
                        fontSize = 13.sp,
                        color = SuccessGreen,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = matchLevel,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                }
            }

            Surface(
                shape = RoundedCornerShape(12.dp),
                color = SuccessGreen
            ) {
                Text(
                    text = "$percentage Coincidencia",
                    color = SurfaceWhite,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                )
            }
        }
    }
}

@Composable
private fun ComparisonCard() {
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
                text = "Comparativa de Reportes",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )
            Spacer(modifier = Modifier.height(14.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Tu reporte
                ReportColumn(
                    modifier = Modifier.weight(1f),
                    badgeTitle = "Tu reporte",
                    badgeColor = SoftBlue,
                    badgeTextColor = PrimaryBlue,
                    title = "Audífonos Sony WH-1000XM4",
                    description = "Audífonos de diadema color negro, guardados en estuche rígido."
                )

                // Encontrado por la comunidad
                ReportColumn(
                    modifier = Modifier.weight(1f),
                    badgeTitle = "Encontrado por comunidad",
                    badgeColor = SuccessGreenSoft,
                    badgeTextColor = SuccessGreen,
                    title = "Audífonos Negros Sony",
                    description = "Encontrados en mesa de lectura. Con estuche y cable de carga."
                )
            }
        }
    }
}

@Composable
private fun ReportColumn(
    modifier: Modifier = Modifier,
    badgeTitle: String,
    badgeColor: Color,
    badgeTextColor: Color,
    title: String,
    description: String
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(14.dp),
        color = AppBackground,
        border = BorderStroke(1.dp, BorderColor)
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = badgeColor
            ) {
                Text(
                    text = badgeTitle,
                    color = badgeTextColor,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary,
                lineHeight = 18.sp
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = description,
                fontSize = 12.sp,
                color = TextSecondary,
                lineHeight = 16.sp
            )
        }
    }
}

@Composable
private fun VerificationPointsSection() {
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
                text = "Puntos de Verificación",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )

            Spacer(modifier = Modifier.height(14.dp))

            VerificationItem(
                icon = { CategoryIcon(tint = PrimaryBlue) },
                label = "Categoría",
                value = "Electrónicos / Audífonos",
                isMatch = true
            )

            VerificationItem(
                icon = { LocationIcon(tint = PrimaryBlue) },
                label = "Ubicación",
                value = "Biblioteca Principal - Piso 2",
                isMatch = true
            )

            VerificationItem(
                icon = { DateIcon(tint = PrimaryBlue) },
                label = "Fecha y Hora",
                value = "24 de Octubre, 2023 • 10:30 AM",
                isMatch = true
            )

            VerificationItem(
                icon = { ShieldIcon(tint = PrimaryBlue) },
                label = "Estado de Custodia",
                value = "En Punto Autorizado (Biblioteca)",
                isMatch = true,
                isLast = true
            )
        }
    }
}

@Composable
private fun VerificationItem(
    icon: @Composable () -> Unit,
    label: String,
    value: String,
    isMatch: Boolean,
    isLast: Boolean = false
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(SoftBlue),
                    contentAlignment = Alignment.Center
                ) {
                    icon()
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = label,
                        fontSize = 12.sp,
                        color = TextSecondary
                    )
                    Text(
                        text = value,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = TextPrimary
                    )
                }
            }

            if (isMatch) {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .clip(CircleShape)
                        .background(SuccessGreenSoft),
                    contentAlignment = Alignment.Center
                ) {
                    CheckmarkIcon(tint = SuccessGreen, modifier = Modifier.size(14.dp))
                }
            }
        }

        if (!isLast) {
            Spacer(modifier = Modifier.height(4.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(BorderColor)
            )
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

@Composable
private fun ActionButtonsSection(
    onConfirmClick: () -> Unit,
    onRejectClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Button(
            onClick = onConfirmClick,
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
                text = "Confirmar y solicitar devolución",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
        }

        OutlinedButton(
            onClick = onRejectClick,
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
                text = "No es mi objeto",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

// Custom Graphics Icons (Canvas) sin dependencias de extended icons
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
private fun CheckmarkIcon(
    tint: Color,
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier.size(16.dp)) {
        val w = size.width
        val h = size.height
        val strokeWidth = 2.2.dp.toPx()

        val path = Path().apply {
            moveTo(w * 0.18f, h * 0.5f)
            lineTo(w * 0.42f, h * 0.76f)
            lineTo(w * 0.82f, h * 0.24f)
        }
        drawPath(
            path = path,
            color = tint,
            style = Stroke(
                width = strokeWidth,
                cap = StrokeCap.Round,
                join = StrokeJoin.Round
            )
        )
    }
}

@Composable
private fun CategoryIcon(
    tint: Color,
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier.size(18.dp)) {
        val w = size.width
        val h = size.height
        val s = w * 0.38f

        drawRoundRect(
            color = tint,
            topLeft = Offset(w * 0.08f, h * 0.08f),
            size = Size(s, s),
            cornerRadius = CornerRadius(3.dp.toPx())
        )
        drawRoundRect(
            color = tint,
            topLeft = Offset(w * 0.54f, h * 0.08f),
            size = Size(s, s),
            cornerRadius = CornerRadius(3.dp.toPx())
        )
        drawRoundRect(
            color = tint,
            topLeft = Offset(w * 0.08f, h * 0.54f),
            size = Size(s, s),
            cornerRadius = CornerRadius(3.dp.toPx())
        )
        drawRoundRect(
            color = tint,
            topLeft = Offset(w * 0.54f, h * 0.54f),
            size = Size(s, s),
            cornerRadius = CornerRadius(3.dp.toPx())
        )
    }
}

@Composable
private fun LocationIcon(
    tint: Color,
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier.size(18.dp)) {
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
private fun DateIcon(
    tint: Color,
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier.size(18.dp)) {
        val w = size.width
        val h = size.height
        val stroke = 1.8.dp.toPx()

        drawRoundRect(
            color = tint,
            topLeft = Offset(w * 0.1f, h * 0.2f),
            size = Size(w * 0.8f, h * 0.72f),
            cornerRadius = CornerRadius(3.dp.toPx()),
            style = Stroke(width = stroke)
        )
        drawLine(
            color = tint,
            start = Offset(w * 0.1f, h * 0.45f),
            end = Offset(w * 0.9f, h * 0.45f),
            strokeWidth = stroke
        )
        drawLine(
            color = tint,
            start = Offset(w * 0.3f, h * 0.08f),
            end = Offset(w * 0.3f, h * 0.25f),
            strokeWidth = stroke,
            cap = StrokeCap.Round
        )
        drawLine(
            color = tint,
            start = Offset(w * 0.7f, h * 0.08f),
            end = Offset(w * 0.7f, h * 0.25f),
            strokeWidth = stroke,
            cap = StrokeCap.Round
        )
    }
}

@Composable
private fun ShieldIcon(
    tint: Color,
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier.size(18.dp)) {
        val w = size.width
        val h = size.height

        val path = Path().apply {
            moveTo(w * 0.5f, h * 0.05f)
            lineTo(w * 0.88f, h * 0.2f)
            cubicTo(w * 0.88f, h * 0.6f, w * 0.72f, h * 0.85f, w * 0.5f, h * 0.95f)
            cubicTo(w * 0.28f, h * 0.85f, w * 0.12f, h * 0.6f, w * 0.12f, h * 0.2f)
            close()
        }
        drawPath(path = path, color = tint)
    }
}

@Preview(showBackground = true)
@Composable
fun MatchDetailScreenPreview() {
    ObjetosPerdidosTheme {
        MatchDetailScreen()
    }
}
