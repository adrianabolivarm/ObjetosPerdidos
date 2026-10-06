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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
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
private val ClosedGray = Color(0xFF6C757D)
private val ClosedGraySoft = Color(0xFFF1F3F5)
private val BorderColor = Color(0xFFE5E5EC)
private val SurfaceWhite = Color(0xFFFFFFFF)

private data class DeliveryPoint(
    val id: String,
    val name: String,
    val location: String,
    val schedule: String,
    val isOpen: Boolean,
    val storedObjectsCount: Int
)

@Composable
fun DeliveryPointsScreen(
    onBackClick: () -> Unit = {},
    onPointClick: (String) -> Unit = {}
) {
    var searchQuery by remember { mutableStateOf("") }

    val pointsList = remember {
        listOf(
            DeliveryPoint(
                id = "1",
                name = "Biblioteca Central",
                location = "Bloque Central - Piso 1",
                schedule = "08:00 - 18:00",
                isOpen = true,
                storedObjectsCount = 3
            ),
            DeliveryPoint(
                id = "2",
                name = "Caja General",
                location = "Bloque A - Piso 1",
                schedule = "07:30 - 16:30",
                isOpen = true,
                storedObjectsCount = 1
            ),
            DeliveryPoint(
                id = "3",
                name = "Portería Principal",
                location = "Acceso Peatonal Norte",
                schedule = "24 Horas",
                isOpen = true,
                storedObjectsCount = 5
            ),
            DeliveryPoint(
                id = "4",
                name = "Recepción de Bloque D",
                location = "Bloque D - Piso 1",
                schedule = "08:00 - 17:00",
                isOpen = false,
                storedObjectsCount = 0
            ),
            DeliveryPoint(
                id = "5",
                name = "Gimnasio Universitario",
                location = "Zona Deportiva",
                schedule = "06:00 - 20:00",
                isOpen = true,
                storedObjectsCount = 2
            )
        )
    }

    val filteredPoints = pointsList.filter { point ->
        point.name.contains(searchQuery, ignoreCase = true) ||
                point.location.contains(searchQuery, ignoreCase = true)
    }

    Scaffold(
        containerColor = AppBackground
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Encabezado y Barra de Búsqueda
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 16.dp)
            ) {
                HeaderSection(onBackClick = onBackClick)

                Spacer(modifier = Modifier.height(18.dp))

                SearchBarSection(
                    searchQuery = searchQuery,
                    onQueryChange = { searchQuery = it }
                )
            }

            // Lista Deslizable de Puntos (LazyColumn)
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                item {
                    Text(
                        text = "Puntos Autorizados (${filteredPoints.size})",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextSecondary,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                }

                items(filteredPoints, key = { it.id }) { point ->
                    DeliveryPointCard(
                        point = point,
                        onClick = { onPointClick(point.id) }
                    )
                }
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
            text = "Puntos de Entrega",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = TextPrimary
        )

        Spacer(modifier = Modifier.size(42.dp))
    }
}

@Composable
private fun SearchBarSection(
    searchQuery: String,
    onQueryChange: (String) -> Unit
) {
    OutlinedTextField(
        value = searchQuery,
        onValueChange = onQueryChange,
        placeholder = {
            Text(
                text = "Buscar punto de entrega...",
                fontSize = 14.sp,
                color = TextSecondary
            )
        },
        leadingIcon = {
            SearchCanvasIcon(tint = TextSecondary, modifier = Modifier.size(20.dp))
        },
        singleLine = true,
        shape = RoundedCornerShape(16.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = SurfaceWhite,
            unfocusedContainerColor = SurfaceWhite,
            disabledContainerColor = SurfaceWhite,
            focusedBorderColor = PrimaryBlue,
            unfocusedBorderColor = BorderColor,
            focusedTextColor = TextPrimary,
            unfocusedTextColor = TextPrimary
        ),
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun DeliveryPointCard(
    point: DeliveryPoint,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
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
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(SoftBlue),
                        contentAlignment = Alignment.Center
                    ) {
                        LocationPinCanvasIcon(tint = PrimaryBlue, modifier = Modifier.size(20.dp))
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column {
                        Text(
                            text = point.name,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = point.location,
                            fontSize = 12.sp,
                            color = TextSecondary
                        )
                    }
                }

                // Chip de estado ("Abierto" / "Cerrado")
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = if (point.isOpen) SuccessGreenSoft else ClosedGraySoft,
                    border = BorderStroke(
                        1.dp,
                        if (point.isOpen) SuccessGreen.copy(alpha = 0.3f) else ClosedGray.copy(alpha = 0.3f)
                    )
                ) {
                    Text(
                        text = if (point.isOpen) "Abierto" else "Cerrado",
                        color = if (point.isOpen) SuccessGreen else ClosedGray,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(BorderColor)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    ClockCanvasIcon(tint = TextSecondary, modifier = Modifier.size(15.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Horario: ${point.schedule}",
                        fontSize = 12.sp,
                        color = TextSecondary,
                        fontWeight = FontWeight.Medium
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "${point.storedObjectsCount} objetos",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryBlue
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    ChevronRightCanvasIcon(tint = PrimaryBlue, modifier = Modifier.size(14.dp))
                }
            }
        }
    }
}

// Custom Canvas Icons
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
private fun SearchCanvasIcon(
    tint: Color,
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier.size(20.dp)) {
        val w = size.width
        val h = size.height
        val stroke = 2.dp.toPx()

        drawCircle(
            color = tint,
            radius = w * 0.32f,
            center = Offset(w * 0.4f, h * 0.4f),
            style = Stroke(width = stroke)
        )
        drawLine(
            color = tint,
            start = Offset(w * 0.62f, h * 0.62f),
            end = Offset(w * 0.88f, h * 0.88f),
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
private fun ClockCanvasIcon(
    tint: Color,
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier.size(15.dp)) {
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
private fun ChevronRightCanvasIcon(
    tint: Color,
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier.size(14.dp)) {
        val w = size.width
        val h = size.height
        val stroke = 2.dp.toPx()

        drawLine(
            color = tint,
            start = Offset(w * 0.3f, h * 0.15f),
            end = Offset(w * 0.75f, h * 0.5f),
            strokeWidth = stroke,
            cap = StrokeCap.Round
        )
        drawLine(
            color = tint,
            start = Offset(w * 0.75f, h * 0.5f),
            end = Offset(w * 0.3f, h * 0.85f),
            strokeWidth = stroke,
            cap = StrokeCap.Round
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DeliveryPointsScreenPreview() {
    ObjetosPerdidosTheme {
        DeliveryPointsScreen()
    }
}
