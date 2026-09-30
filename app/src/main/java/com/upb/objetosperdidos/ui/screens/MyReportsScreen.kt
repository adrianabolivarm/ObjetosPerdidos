package com.upb.objetosperdidos.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.upb.objetosperdidos.R
import com.upb.objetosperdidos.ui.theme.ObjetosPerdidosTheme

private val ReportsBlue = Color(0xFF1689FF)
private val ReportsBlueSoft = Color(0xFFEAF4FF)
private val ReportsBackground = Color(0xFFF7F7FB)
private val ReportsTextPrimary = Color(0xFF181820)
private val ReportsTextSecondary = Color(0xFF777783)
private val ReportsBorder = Color(0xFFE5E5EC)

private val LostRed = Color(0xFFFF5A67)
private val LostRedSoft = Color(0xFFFFEEF0)

private val FoundGreen = Color(0xFF2DBE7F)
private val FoundGreenSoft = Color(0xFFEAF9F2)

private val WarningOrange = Color(0xFFF4A340)
private val WarningOrangeSoft = Color(0xFFFFF5E8)

@Composable
fun MyReportsScreen() {

    Scaffold(
        containerColor = ReportsBackground
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {

            MyReportsTopBar()

            Column(
                modifier = Modifier.padding(
                    horizontal = 18.dp,
                    vertical = 18.dp
                )
            ) {

                Text(
                    text = "Resumen",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = ReportsTextPrimary
                )

                Spacer(modifier = Modifier.height(12.dp))

                ReportsSummary()

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Mis publicaciones",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = ReportsTextPrimary
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Revisa el estado de los objetos que reportaste",
                    fontSize = 12.sp,
                    color = ReportsTextSecondary
                )

                Spacer(modifier = Modifier.height(14.dp))

                ReportFilters()

                Spacer(modifier = Modifier.height(18.dp))

                MyReportCard(
                    type = "PERDIDO",
                    title = "Mochila negra",
                    category = "Accesorios",
                    location = "Bloque B - Aula 204",
                    date = "28 sep. 2026",
                    status = "Buscando coincidencias",
                    statusColor = WarningOrange,
                    statusBackground = WarningOrangeSoft,
                    typeColor = LostRed,
                    typeBackground = LostRedSoft,
                    custody = null,
                    imageRes = R.drawable.mochila_negra
                )

                Spacer(modifier = Modifier.height(14.dp))

                MyReportCard(
                    type = "ENCONTRADO",
                    title = "AirPods Pro",
                    category = "Electrónica",
                    location = "Biblioteca Central",
                    date = "27 sep. 2026",
                    status = "Posible coincidencia",
                    statusColor = ReportsBlue,
                    statusBackground = ReportsBlueSoft,
                    typeColor = FoundGreen,
                    typeBackground = FoundGreenSoft,
                    custody = "Lo tengo conmigo",
                    imageRes = R.drawable.airpods_pro
                )

                Spacer(modifier = Modifier.height(14.dp))

                MyReportCard(
                    type = "ENCONTRADO",
                    title = "Carnet universitario",
                    category = "Documentos",
                    location = "Cafetería",
                    date = "24 sep. 2026",
                    status = "En custodia",
                    statusColor = FoundGreen,
                    statusBackground = FoundGreenSoft,
                    typeColor = FoundGreen,
                    typeBackground = FoundGreenSoft,
                    custody = "Recepción principal",
                    imageRes = R.drawable.carnet_estudiante
                )

                Spacer(modifier = Modifier.height(14.dp))

                MyReportCard(
                    type = "PERDIDO",
                    title = "Calculadora científica",
                    category = "Estudio",
                    location = "Laboratorio 3",
                    date = "19 sep. 2026",
                    status = "Recuperado",
                    statusColor = FoundGreen,
                    statusBackground = FoundGreenSoft,
                    typeColor = LostRed,
                    typeBackground = LostRedSoft,
                    custody = null,
                    imageRes = R.drawable.calculadora_cientifica
                )

                Spacer(modifier = Modifier.height(30.dp))
            }
        }
    }
}

@Composable
private fun MyReportsTopBar() {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(
                horizontal = 18.dp,
                vertical = 16.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(ReportsBlueSoft),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = "‹",
                color = ReportsBlue,
                fontSize = 30.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.width(14.dp))

        Column {

            Text(
                text = "Mis reportes",
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold,
                color = ReportsTextPrimary
            )

            Text(
                text = "Publicaciones y seguimiento",
                fontSize = 12.sp,
                color = ReportsTextSecondary
            )
        }
    }
}

@Composable
private fun ReportsSummary() {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        SummaryCard(
            modifier = Modifier.weight(1f),
            number = "4",
            label = "Total",
            color = ReportsBlue,
            background = ReportsBlueSoft
        )

        SummaryCard(
            modifier = Modifier.weight(1f),
            number = "2",
            label = "Activos",
            color = WarningOrange,
            background = WarningOrangeSoft
        )

        SummaryCard(
            modifier = Modifier.weight(1f),
            number = "1",
            label = "Recuperado",
            color = FoundGreen,
            background = FoundGreenSoft
        )
    }
}

@Composable
private fun SummaryCard(
    modifier: Modifier,
    number: String,
    label: String,
    color: Color,
    background: Color
) {

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = 16.dp,
                    horizontal = 12.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier
                    .size(43.dp)
                    .clip(CircleShape)
                    .background(background),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = number,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = color
                )
            }

            Spacer(modifier = Modifier.height(7.dp))

            Text(
                text = label,
                fontSize = 11.sp,
                color = ReportsTextSecondary
            )
        }
    }
}

@Composable
private fun ReportFilters() {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(17.dp))
            .background(Color(0xFFEEEEF4))
            .padding(4.dp)
    ) {

        FilterOption(
            modifier = Modifier.weight(1f),
            title = "Todos",
            selected = true
        )

        FilterOption(
            modifier = Modifier.weight(1f),
            title = "Perdidos",
            selected = false
        )

        FilterOption(
            modifier = Modifier.weight(1f),
            title = "Encontrados",
            selected = false
        )
    }
}

@Composable
private fun FilterOption(
    modifier: Modifier,
    title: String,
    selected: Boolean
) {

    Surface(
        modifier = modifier.clickable { },
        shape = RoundedCornerShape(14.dp),
        color = if (selected) {
            Color.White
        } else {
            Color.Transparent
        },
        shadowElevation = if (selected) 1.dp else 0.dp
    ) {

        Box(
            modifier = Modifier.padding(
                vertical = 10.dp,
                horizontal = 8.dp
            ),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = title,
                fontSize = 12.sp,
                fontWeight = if (selected) {
                    FontWeight.Bold
                } else {
                    FontWeight.Medium
                },
                color = if (selected) {
                    ReportsBlue
                } else {
                    ReportsTextSecondary
                }
            )
        }
    }
}

@Composable
private fun MyReportCard(
    type: String,
    title: String,
    category: String,
    location: String,
    date: String,
    status: String,
    statusColor: Color,
    statusBackground: Color,
    typeColor: Color,
    typeBackground: Color,
    custody: String?,
    imageRes: Int
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { },
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {

        Column(
            modifier = Modifier.padding(17.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = typeBackground
                ) {

                    Text(
                        text = type,
                        modifier = Modifier.padding(
                            horizontal = 10.dp,
                            vertical = 6.dp
                        ),
                        color = typeColor,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = date,
                    fontSize = 11.sp,
                    color = ReportsTextSecondary
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {

                Image(
                    painter = painterResource(
                        id = imageRes
                    ),
                    contentDescription = title,
                    modifier = Modifier
                        .size(68.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.width(13.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {

                    Text(
                        text = title,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = ReportsTextPrimary
                    )

                    Spacer(modifier = Modifier.height(3.dp))

                    Text(
                        text = category,
                        fontSize = 12.sp,
                        color = ReportsTextSecondary
                    )

                    Spacer(modifier = Modifier.height(7.dp))

                    Text(
                        text = "Lugar: $location",
                        fontSize = 12.sp,
                        color = ReportsTextSecondary
                    )
                }

                Text(
                    text = "›",
                    fontSize = 26.sp,
                    color = Color(0xFFB5B5BF)
                )
            }

            Spacer(modifier = Modifier.height(15.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(ReportsBorder)
            )

            Spacer(modifier = Modifier.height(13.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(statusColor)
                )

                Spacer(modifier = Modifier.width(7.dp))

                Text(
                    text = "Estado",
                    color = ReportsTextSecondary,
                    fontSize = 11.sp
                )

                Spacer(modifier = Modifier.weight(1f))

                Surface(
                    shape = RoundedCornerShape(9.dp),
                    color = statusBackground
                ) {

                    Text(
                        text = status,
                        modifier = Modifier.padding(
                            horizontal = 9.dp,
                            vertical = 5.dp
                        ),
                        color = statusColor,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            if (custody != null) {

                Spacer(modifier = Modifier.height(11.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "Custodia",
                        color = ReportsTextSecondary,
                        fontSize = 11.sp
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = custody,
                        color = ReportsTextPrimary,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    widthDp = 390,
    heightDp = 844
)
@Composable
fun MyReportsScreenPreview() {

    ObjetosPerdidosTheme {
        MyReportsScreen()
    }
}