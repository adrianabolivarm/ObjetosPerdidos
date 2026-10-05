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

private data class StoredObject(
    val id: String,
    val title: String,
    val category: String,
    val entryTime: String
)

@Composable
fun DeliveryPointDetailScreen(
    onBackClick: () -> Unit = {},
    onRequestDeliveryClick: () -> Unit = {}
) {
    val sampleStoredObjects = listOf(
        StoredObject("1", "Audífonos Sony WH-1000XM4", "Electrónicos", "Hace 2h"),
        StoredObject("2", "Termo Yeti Azul 26oz", "Accesorios", "Hoy, 11:15 AM"),
        StoredObject("3", "Mochila JanSport Negra", "Equipaje", "Ayer, 04:30 PM")
    )

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

            // 2. Mapa Conceptual y Nombre del Punto de Entrega
            item {
                PointHeaderCard(
                    pointName = "Biblioteca Central",
                    isOpen = true,
                    schedule = "08:00 AM - 06:00 PM"
                )
            }

            // 3. Ubicación Exacta
            item {
                ExactLocationCard()
            }

            // 4. Encargado en Turno
            item {
                StaffOnDutyCard()
            }

            // 5. Objetos Resguardados Actualmente
            item {
                StoredObjectsCard(storedObjects = sampleStoredObjects)
            }

            // 6. Botón de Acción Principal
            item {
                ActionButtonSection(onRequestDeliveryClick = onRequestDeliveryClick)
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
            text = "Detalle del Punto",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = TextPrimary
        )

        Spacer(modifier = Modifier.size(42.dp))
    }
}

@Composable
private fun PointHeaderCard(
    pointName: String,
    isOpen: Boolean,
    schedule: String,
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column {
            // Mapa Conceptual Gráfico con Pin
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .background(SoftBlue),
                contentAlignment = Alignment.Center
            ) {
                MapConceptualGraphic(modifier = Modifier.fillMaxSize())
            }

            Column(
                modifier = Modifier.padding(18.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = pointName,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Punto Autorizado de Custodia",
                            fontSize = 12.sp,
                            color = TextSecondary
                        )
                    }

                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = SuccessGreenSoft,
                        border = BorderStroke(1.dp, SuccessGreen.copy(alpha = 0.3f))
                    ) {
                        Text(
                            text = if (isOpen) "Abierto" else "Cerrado",
                            color = SuccessGreen,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    ClockCanvasIcon(tint = TextSecondary, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Horario de atención: $schedule",
                        fontSize = 13.sp,
                        color = TextSecondary,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

@Composable
private fun ExactLocationCard() {
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
                text = "Ubicación Exacta",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )

            Spacer(modifier = Modifier.height(14.dp))

            Row(verticalAlignment = Alignment.Top) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(SoftBlue),
                    contentAlignment = Alignment.Center
                ) {
                    LocationPinCanvasIcon(tint = PrimaryBlue, modifier = Modifier.size(20.dp))
                }

                Spacer(modifier = Modifier.width(14.dp))

                Column {
                    Text(
                        text = "Bloque Central (Biblioteca)",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "Piso 1 • Módulo de Recepción 3",
                        fontSize = 13.sp,
                        color = TextPrimary,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Referencia: Frente al acceso principal de la Hemeroteca.",
                        fontSize = 12.sp,
                        color = TextSecondary,
                        lineHeight = 16.sp
                    )
                }
            }
        }
    }
}

@Composable
private fun StaffOnDutyCard() {
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
                text = "Encargado en Turno",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )

            Spacer(modifier = Modifier.height(14.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(SoftBlue),
                    contentAlignment = Alignment.Center
                ) {
                    UserCanvasIcon(tint = PrimaryBlue, modifier = Modifier.size(22.dp))
                }

                Spacer(modifier = Modifier.width(14.dp))

                Column {
                    Text(
                        text = "Lic. María Fernández",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "Auxiliar de Custodia y Recepción",
                        fontSize = 12.sp,
                        color = TextSecondary
                    )
                }
            }
        }
    }
}

@Composable
private fun StoredObjectsCard(storedObjects: List<StoredObject>) {
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
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Objetos Resguardados",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )

                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = SoftBlue
                ) {
                    Text(
                        text = "${storedObjects.size} activos",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryBlue,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            storedObjects.forEachIndexed { index, obj ->
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
                                .size(34.dp)
                                .clip(CircleShape)
                                .background(AppBackground),
                            contentAlignment = Alignment.Center
                        ) {
                            BoxCanvasIcon(tint = TextSecondary, modifier = Modifier.size(18.dp))
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Column {
                            Text(
                                text = obj.title,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = TextPrimary
                            )
                            Text(
                                text = "${obj.category} • Ingreso: ${obj.entryTime}",
                                fontSize = 11.sp,
                                color = TextSecondary
                            )
                        }
                    }
                }

                if (index < storedObjects.size - 1) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(BorderColor)
                    )
                }
            }
        }
    }
}

@Composable
private fun ActionButtonSection(onRequestDeliveryClick: () -> Unit) {
    Button(
        onClick = onRequestDeliveryClick,
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
            text = "Solicitar entrega en este punto",
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

// Custom Graphics Icons (Canvas)
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
private fun ClockCanvasIcon(
    tint: Color,
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier.size(16.dp)) {
        val w = size.width
        val h = size.height
        val stroke = 1.6.dp.toPx()

        drawCircle(
            color = tint,
            radius = w * 0.42f,
            center = Offset(w * 0.5f, h * 0.5f),
            style = Stroke(width = stroke)
        )
        drawLine(
            color = tint,
            start = Offset(w * 0.5f, h * 0.5f),
            end = Offset(w * 0.5f, h * 0.26f),
            strokeWidth = stroke,
            cap = StrokeCap.Round
        )
        drawLine(
            color = tint,
            start = Offset(w * 0.5f, h * 0.5f),
            end = Offset(w * 0.72f, h * 0.5f),
            strokeWidth = stroke,
            cap = StrokeCap.Round
        )
    }
}

@Composable
private fun UserCanvasIcon(
    tint: Color,
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier.size(22.dp)) {
        val w = size.width
        val h = size.height

        // Head
        drawCircle(
            color = tint,
            radius = w * 0.25f,
            center = Offset(w * 0.5f, h * 0.3f)
        )
        // Shoulders
        val path = Path().apply {
            moveTo(w * 0.15f, h * 0.85f)
            cubicTo(w * 0.15f, h * 0.62f, w * 0.3f, h * 0.55f, w * 0.5f, h * 0.55f)
            cubicTo(w * 0.7f, h * 0.55f, w * 0.85f, h * 0.62f, w * 0.85f, h * 0.85f)
            close()
        }
        drawPath(path = path, color = tint)
    }
}

@Composable
private fun BoxCanvasIcon(
    tint: Color,
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier.size(18.dp)) {
        val w = size.width
        val h = size.height
        val stroke = 1.8.dp.toPx()

        drawRoundRect(
            color = tint,
            topLeft = Offset(w * 0.12f, h * 0.25f),
            size = Size(w * 0.76f, h * 0.62f),
            cornerRadius = CornerRadius(3.dp.toPx()),
            style = Stroke(width = stroke)
        )
        drawLine(
            color = tint,
            start = Offset(w * 0.12f, h * 0.45f),
            end = Offset(w * 0.88f, h * 0.45f),
            strokeWidth = stroke
        )
    }
}

@Composable
private fun MapConceptualGraphic(
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // Background grid / campus paths
        val roadColor = Color(0xFFD6E8FF)
        val buildingColor = Color(0xFFC2DCFF)

        // Roads
        drawRoundRect(
            color = roadColor,
            topLeft = Offset(0f, h * 0.4f),
            size = Size(w, h * 0.2f)
        )
        drawRoundRect(
            color = roadColor,
            topLeft = Offset(w * 0.45f, 0f),
            size = Size(w * 0.18f, h)
        )

        // Building blocks
        drawRoundRect(
            color = buildingColor,
            topLeft = Offset(w * 0.1f, h * 0.12f),
            size = Size(w * 0.3f, h * 0.22f),
            cornerRadius = CornerRadius(6.dp.toPx())
        )
        drawRoundRect(
            color = buildingColor,
            topLeft = Offset(w * 0.68f, h * 0.12f),
            size = Size(w * 0.22f, h * 0.22f),
            cornerRadius = CornerRadius(6.dp.toPx())
        )
        drawRoundRect(
            color = buildingColor,
            topLeft = Offset(w * 0.1f, h * 0.66f),
            size = Size(w * 0.28f, h * 0.22f),
            cornerRadius = CornerRadius(6.dp.toPx())
        )

        // Highlighted Central Building (Target Point)
        drawRoundRect(
            color = PrimaryBlue.copy(alpha = 0.25f),
            topLeft = Offset(w * 0.66f, h * 0.64f),
            size = Size(w * 0.26f, h * 0.26f),
            cornerRadius = CornerRadius(8.dp.toPx())
        )

        // Map Pin in central building
        val pinX = w * 0.79f
        val pinY = h * 0.72f

        drawCircle(
            color = PrimaryBlue,
            radius = 16.dp.toPx(),
            center = Offset(pinX, pinY)
        )
        drawCircle(
            color = Color.White,
            radius = 6.dp.toPx(),
            center = Offset(pinX, pinY)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DeliveryPointDetailScreenPreview() {
    ObjetosPerdidosTheme {
        DeliveryPointDetailScreen()
    }
}
