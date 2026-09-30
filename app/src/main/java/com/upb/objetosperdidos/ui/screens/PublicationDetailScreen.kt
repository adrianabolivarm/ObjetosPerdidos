package com.upb.objetosperdidos.ui.screens

import androidx.compose.foundation.background
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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.upb.objetosperdidos.ui.theme.ObjetosPerdidosTheme

private val DetailBlue = Color(0xFF1689FF)
private val DetailBlueSoft = Color(0xFFEAF4FF)
private val DetailBackground = Color(0xFFF7F7FB)

private val DetailTextPrimary = Color(0xFF181820)
private val DetailTextSecondary = Color(0xFF777783)

private val DetailGreen = Color(0xFF198754)
private val DetailGreenBackground = Color(0xFFE8F7F0)

@Composable
fun PublicationDetailScreen() {

    Scaffold(
        containerColor = DetailBackground
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {

            DetailTopBar()

            DetailImage()

            Column(
                modifier = Modifier.padding(
                    horizontal = 20.dp,
                    vertical = 18.dp
                )
            ) {

                StatusLabel()

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = "AirPods Pro",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = DetailTextPrimary
                )

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = "Tecnología",
                    fontSize = 14.sp,
                    color = DetailBlue,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(22.dp))

                DetailInformationCard()

                Spacer(modifier = Modifier.height(18.dp))

                CustodyCard()

                Spacer(modifier = Modifier.height(18.dp))

                DescriptionCard()

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp),
                    shape = RoundedCornerShape(17.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = DetailBlue
                    )
                ) {

                    Text(
                        text = "Solicitar recuperación",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Si crees que este objeto te pertenece, podrás enviar información para verificar la propiedad.",
                    modifier = Modifier.fillMaxWidth(),
                    color = DetailTextSecondary,
                    fontSize = 12.sp,
                    lineHeight = 17.sp
                )

                Spacer(modifier = Modifier.height(28.dp))
            }
        }
    }
}

@Composable
private fun DetailTopBar() {

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
                .background(DetailBlueSoft),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = "‹",
                color = DetailBlue,
                fontSize = 30.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.width(14.dp))

        Text(
            text = "Detalle de publicación",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = DetailTextPrimary
        )
    }
}

@Composable
private fun DetailImage() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(245.dp)
            .background(
                Brush.linearGradient(
                    listOf(
                        Color(0xFFDCEEFF),
                        Color(0xFFF5FAFF)
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {

        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(RoundedCornerShape(32.dp))
                .background(Color.White.copy(alpha = 0.85f)),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = "AP",
                color = DetailBlue,
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Surface(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            color = Color.Black.copy(alpha = 0.60f),
            shape = RoundedCornerShape(15.dp)
        ) {

            Text(
                text = "1 / 2",
                modifier = Modifier.padding(
                    horizontal = 10.dp,
                    vertical = 6.dp
                ),
                color = Color.White,
                fontSize = 12.sp
            )
        }
    }
}

@Composable
private fun StatusLabel() {

    Surface(
        color = DetailGreenBackground,
        shape = RoundedCornerShape(20.dp)
    ) {

        Text(
            text = "Encontrado",
            modifier = Modifier.padding(
                horizontal = 12.dp,
                vertical = 7.dp
            ),
            color = DetailGreen,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun DetailInformationCard() {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {

        Column(
            modifier = Modifier.padding(18.dp)
        ) {

            Text(
                text = "Información del reporte",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = DetailTextPrimary
            )

            Spacer(modifier = Modifier.height(17.dp))

            DetailRow(
                symbol = "⌖",
                title = "Lugar",
                value = "Biblioteca Central"
            )

            DetailDivider()

            DetailRow(
                symbol = "▣",
                title = "Fecha del hallazgo",
                value = "29 de septiembre de 2026"
            )

            DetailDivider()

            DetailRow(
                symbol = "#",
                title = "Estado de publicación",
                value = "Activa"
            )
        }
    }
}

@Composable
private fun CustodyCard() {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {

        Row(
            modifier = Modifier.padding(18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .background(DetailBlueSoft),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = "C",
                    color = DetailBlue,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column {

                Text(
                    text = "Custodia actual",
                    fontSize = 13.sp,
                    color = DetailTextSecondary
                )

                Spacer(modifier = Modifier.height(3.dp))

                Text(
                    text = "Biblioteca Central",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = DetailTextPrimary
                )

                Text(
                    text = "Punto oficial",
                    fontSize = 12.sp,
                    color = DetailBlue
                )
            }
        }
    }
}

@Composable
private fun DescriptionCard() {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {

        Column(
            modifier = Modifier.padding(18.dp)
        ) {

            Text(
                text = "Descripción",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = DetailTextPrimary
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "AirPods Pro blancos encontrados en una mesa de la Biblioteca Central. El estuche presenta una pequeña marca en la parte posterior.",
                color = DetailTextSecondary,
                fontSize = 14.sp,
                lineHeight = 21.sp
            )
        }
    }
}

@Composable
private fun DetailRow(
    symbol: String,
    title: String,
    value: String
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(38.dp)
                .clip(CircleShape)
                .background(DetailBlueSoft),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = symbol,
                color = DetailBlue,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.width(13.dp))

        Column {

            Text(
                text = title,
                color = DetailTextSecondary,
                fontSize = 12.sp
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = value,
                color = DetailTextPrimary,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
private fun DetailDivider() {

    HorizontalDivider(
        modifier = Modifier.padding(
            vertical = 14.dp
        ),
        color = Color(0xFFF0F0F4)
    )
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    widthDp = 390,
    heightDp = 844
)
@Composable
fun PublicationDetailScreenPreview() {

    ObjetosPerdidosTheme {
        PublicationDetailScreen()
    }
}