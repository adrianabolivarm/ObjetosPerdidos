package com.upb.objetosperdidos.ui.screens

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.upb.objetosperdidos.ui.theme.ObjetosPerdidosTheme

private val MatchesBlue = Color(0xFF1689FF)
private val MatchesBlueSoft = Color(0xFFEAF4FF)
private val MatchesBackground = Color(0xFFF7F7FB)
private val MatchesTextPrimary = Color(0xFF181820)
private val MatchesTextSecondary = Color(0xFF777783)
private val MatchesBorder = Color(0xFFE5E5EC)

private val MatchGreen = Color(0xFF2DBE7F)
private val MatchGreenSoft = Color(0xFFEAF9F2)

private val MatchRed = Color(0xFFFF5A67)
private val MatchRedSoft = Color(0xFFFFEEF0)

private val MatchOrange = Color(0xFFF4A340)
private val MatchOrangeSoft = Color(0xFFFFF5E8)

@Composable
fun MatchesScreen() {

    Scaffold(
        containerColor = MatchesBackground
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {

            MatchesTopBar()

            Column(
                modifier = Modifier.padding(
                    horizontal = 18.dp,
                    vertical = 18.dp
                )
            ) {

                MatchInfoCard()

                Spacer(modifier = Modifier.height(22.dp))

                Text(
                    text = "Posibles coincidencias",
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold,
                    color = MatchesTextPrimary
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Compara los reportes y revisa si se trata del mismo objeto.",
                    fontSize = 12.sp,
                    color = MatchesTextSecondary,
                    lineHeight = 17.sp
                )

                Spacer(modifier = Modifier.height(15.dp))

                MatchFilters()

                Spacer(modifier = Modifier.height(18.dp))

                MatchCard(
                    lostTitle = "AirPods Pro",
                    lostLocation = "Bloque A - Aula 103",
                    lostDate = "26 sep. 2026",
                    foundTitle = "AirPods Pro blancos",
                    foundLocation = "Biblioteca Central",
                    foundDate = "27 sep. 2026",
                    category = "Electrónica",
                    status = "Nueva coincidencia",
                    statusColor = MatchGreen,
                    statusBackground = MatchGreenSoft
                )

                Spacer(modifier = Modifier.height(15.dp))

                MatchCard(
                    lostTitle = "Mochila negra",
                    lostLocation = "Bloque B - Aula 204",
                    lostDate = "28 sep. 2026",
                    foundTitle = "Mochila negra Adidas",
                    foundLocation = "Cafetería",
                    foundDate = "29 sep. 2026",
                    category = "Accesorios",
                    status = "Por revisar",
                    statusColor = MatchOrange,
                    statusBackground = MatchOrangeSoft
                )

                Spacer(modifier = Modifier.height(15.dp))

                MatchCard(
                    lostTitle = "Carnet universitario",
                    lostLocation = "Patio principal",
                    lostDate = "22 sep. 2026",
                    foundTitle = "Carnet de estudiante",
                    foundLocation = "Recepción",
                    foundDate = "22 sep. 2026",
                    category = "Documentos",
                    status = "Revisada",
                    statusColor = MatchesBlue,
                    statusBackground = MatchesBlueSoft
                )

                Spacer(modifier = Modifier.height(30.dp))
            }
        }
    }
}

@Composable
private fun MatchesTopBar() {

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
                .background(MatchesBlueSoft),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = "‹",
                color = MatchesBlue,
                fontSize = 30.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.width(14.dp))

        Column {

            Text(
                text = "Coincidencias",
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold,
                color = MatchesTextPrimary
            )

            Text(
                text = "Objetos que podrían coincidir",
                fontSize = 12.sp,
                color = MatchesTextSecondary
            )
        }
    }
}

@Composable
private fun MatchInfoCard() {

    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        color = MatchesBlueSoft
    ) {

        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = "↔",
                    color = MatchesBlue,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = "Detectamos objetos similares",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = MatchesBlue
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "El sistema compara publicaciones de objetos perdidos y encontrados para ayudar a identificar posibles coincidencias.",
                    fontSize = 12.sp,
                    color = MatchesBlue,
                    lineHeight = 17.sp
                )
            }
        }
    }
}

@Composable
private fun MatchFilters() {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(17.dp))
            .background(Color(0xFFEEEEF4))
            .padding(4.dp)
    ) {

        MatchFilterOption(
            modifier = Modifier.weight(1f),
            text = "Todas",
            selected = true
        )

        MatchFilterOption(
            modifier = Modifier.weight(1f),
            text = "Nuevas",
            selected = false
        )

        MatchFilterOption(
            modifier = Modifier.weight(1f),
            text = "Revisadas",
            selected = false
        )
    }
}

@Composable
private fun MatchFilterOption(
    modifier: Modifier,
    text: String,
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
            modifier = Modifier.padding(vertical = 10.dp),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = text,
                fontSize = 12.sp,
                fontWeight = if (selected) {
                    FontWeight.Bold
                } else {
                    FontWeight.Medium
                },
                color = if (selected) {
                    MatchesBlue
                } else {
                    MatchesTextSecondary
                }
            )
        }
    }
}

@Composable
private fun MatchCard(
    lostTitle: String,
    lostLocation: String,
    lostDate: String,
    foundTitle: String,
    foundLocation: String,
    foundDate: String,
    category: String,
    status: String,
    statusColor: Color,
    statusBackground: Color
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
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
                    color = statusBackground
                ) {

                    Text(
                        text = status,
                        modifier = Modifier.padding(
                            horizontal = 10.dp,
                            vertical = 6.dp
                        ),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = statusColor
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = category,
                    fontSize = 11.sp,
                    color = MatchesTextSecondary
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            ObjectMatchSection(
                type = "PERDIDO",
                title = lostTitle,
                location = lostLocation,
                date = lostDate,
                typeColor = MatchRed,
                typeBackground = MatchRedSoft
            )

            Spacer(modifier = Modifier.height(12.dp))

            MatchConnector()

            Spacer(modifier = Modifier.height(12.dp))

            ObjectMatchSection(
                type = "ENCONTRADO",
                title = foundTitle,
                location = foundLocation,
                date = foundDate,
                typeColor = MatchGreen,
                typeBackground = MatchGreenSoft
            )

            Spacer(modifier = Modifier.height(17.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(MatchesBorder)
            )

            Spacer(modifier = Modifier.height(14.dp))

            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MatchesBlue
                )
            ) {

                Text(
                    text = "Ver coincidencia",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
private fun ObjectMatchSection(
    type: String,
    title: String,
    location: String,
    date: String,
    typeColor: Color,
    typeBackground: Color
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(57.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(typeBackground),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = if (type == "PERDIDO") "?" else "✓",
                color = typeColor,
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {

            Surface(
                shape = RoundedCornerShape(8.dp),
                color = typeBackground
            ) {

                Text(
                    text = type,
                    modifier = Modifier.padding(
                        horizontal = 8.dp,
                        vertical = 4.dp
                    ),
                    color = typeColor,
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = title,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = MatchesTextPrimary
            )

            Spacer(modifier = Modifier.height(3.dp))

            Text(
                text = location,
                fontSize = 11.sp,
                color = MatchesTextSecondary
            )

            Text(
                text = date,
                fontSize = 10.sp,
                color = Color(0xFFA2A0AA)
            )
        }
    }
}

@Composable
private fun MatchConnector() {

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .weight(1f)
                .height(1.dp)
                .background(MatchesBorder)
        )

        Spacer(modifier = Modifier.width(9.dp))

        Box(
            modifier = Modifier
                .size(33.dp)
                .clip(CircleShape)
                .background(MatchesBlueSoft),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = "↕",
                color = MatchesBlue,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.width(9.dp))

        Box(
            modifier = Modifier
                .weight(1f)
                .height(1.dp)
                .background(MatchesBorder)
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    widthDp = 390,
    heightDp = 844
)
@Composable
fun MatchesScreenPreview() {

    ObjetosPerdidosTheme {
        MatchesScreen()
    }
}