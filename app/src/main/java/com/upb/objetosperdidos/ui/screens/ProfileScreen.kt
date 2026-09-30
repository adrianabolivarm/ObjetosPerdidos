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

private val ProfileBlue = Color(0xFF1689FF)
private val ProfileBlueSoft = Color(0xFFEAF4FF)
private val ProfileBackground = Color(0xFFF7F7FB)
private val ProfileTextPrimary = Color(0xFF181820)
private val ProfileTextSecondary = Color(0xFF777783)
private val ProfileBorder = Color(0xFFE5E5EC)

private val ProfileGreen = Color(0xFF2DBE7F)
private val ProfileGreenSoft = Color(0xFFEAF9F2)

private val ProfileOrange = Color(0xFFF4A340)
private val ProfileOrangeSoft = Color(0xFFFFF5E8)

private val ProfilePurple = Color(0xFF7C6CF2)
private val ProfilePurpleSoft = Color(0xFFF0EEFF)

@Composable
fun ProfileScreen() {

    Scaffold(
        containerColor = ProfileBackground
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {

            ProfileTopBar()

            Column(
                modifier = Modifier.padding(
                    horizontal = 18.dp,
                    vertical = 18.dp
                )
            ) {

                StudentProfileCard()

                Spacer(modifier = Modifier.height(20.dp))

                ProfileStats()

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Mis puntos",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = ProfileTextPrimary
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Obtén puntos por ayudar a devolver objetos.",
                    fontSize = 12.sp,
                    color = ProfileTextSecondary
                )

                Spacer(modifier = Modifier.height(13.dp))

                PointsCard()

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Beneficios disponibles",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = ProfileTextPrimary
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Canjea tus puntos por recompensas.",
                    fontSize = 12.sp,
                    color = ProfileTextSecondary
                )

                Spacer(modifier = Modifier.height(14.dp))

                BenefitCard(
                    symbol = "C",
                    title = "Descuento en cafetería",
                    description = "10% de descuento en una compra.",
                    points = "150 pts",
                    background = ProfileOrangeSoft,
                    color = ProfileOrange
                )

                Spacer(modifier = Modifier.height(11.dp))

                BenefitCard(
                    symbol = "P",
                    title = "Premio universitario",
                    description = "Canje por un beneficio disponible.",
                    points = "250 pts",
                    background = ProfilePurpleSoft,
                    color = ProfilePurple
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Mi cuenta",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = ProfileTextPrimary
                )

                Spacer(modifier = Modifier.height(13.dp))

                AccountOptions()

                Spacer(modifier = Modifier.height(28.dp))

                Button(
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape = RoundedCornerShape(17.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = ProfileTextSecondary
                    )
                ) {

                    Text(
                        text = "Cerrar sesión",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(30.dp))
            }
        }
    }
}

@Composable
private fun ProfileTopBar() {

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

        Column {

            Text(
                text = "Mi perfil",
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold,
                color = ProfileTextPrimary
            )

            Text(
                text = "Cuenta, actividad y recompensas",
                fontSize = 12.sp,
                color = ProfileTextSecondary
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(ProfileBlueSoft),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = "⚙",
                fontSize = 18.sp,
                color = ProfileBlue
            )
        }
    }
}

@Composable
private fun StudentProfileCard() {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {

        Column(
            modifier = Modifier.padding(18.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .size(72.dp)
                        .clip(CircleShape)
                        .background(ProfileBlue),
                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        text = "SV",
                        color = Color.White,
                        fontSize = 23.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.width(15.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {

                    Text(
                        text = "Sebastián Vargas",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = ProfileTextPrimary
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Ingeniería en Sistemas",
                        fontSize = 12.sp,
                        color = ProfileTextSecondary
                    )

                    Spacer(modifier = Modifier.height(3.dp))

                    Text(
                        text = "Universidad Privada Boliviana",
                        fontSize = 11.sp,
                        color = ProfileTextSecondary
                    )
                }
            }

            Spacer(modifier = Modifier.height(17.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(ProfileBorder)
            )

            Spacer(modifier = Modifier.height(14.dp))

            ProfileInfoRow(
                label = "Código",
                value = "EST-2026-001"
            )

            Spacer(modifier = Modifier.height(9.dp))

            ProfileInfoRow(
                label = "Correo",
                value = "sebastian@upb.edu"
            )

            Spacer(modifier = Modifier.height(9.dp))

            ProfileInfoRow(
                label = "Sede",
                value = "La Paz"
            )
        }
    }
}

@Composable
private fun ProfileInfoRow(
    label: String,
    value: String
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = label,
            fontSize = 11.sp,
            color = ProfileTextSecondary
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = value,
            fontSize = 11.sp,
            fontWeight = FontWeight.SemiBold,
            color = ProfileTextPrimary
        )
    }
}

@Composable
private fun ProfileStats() {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        ProfileStatCard(
            modifier = Modifier.weight(1f),
            number = "6",
            label = "Reportes",
            color = ProfileBlue,
            background = ProfileBlueSoft
        )

        ProfileStatCard(
            modifier = Modifier.weight(1f),
            number = "3",
            label = "Devueltos",
            color = ProfileGreen,
            background = ProfileGreenSoft
        )

        ProfileStatCard(
            modifier = Modifier.weight(1f),
            number = "180",
            label = "Puntos",
            color = ProfileOrange,
            background = ProfileOrangeSoft
        )
    }
}

@Composable
private fun ProfileStatCard(
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
                .padding(vertical = 15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier
                    .size(42.dp)
                    .clip(CircleShape)
                    .background(background),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = number,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = color
                )
            }

            Spacer(modifier = Modifier.height(7.dp))

            Text(
                text = label,
                fontSize = 10.sp,
                color = ProfileTextSecondary
            )
        }
    }
}

@Composable
private fun PointsCard() {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(
            containerColor = ProfileBlue
        )
    ) {

        Column(
            modifier = Modifier.padding(18.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column(
                    modifier = Modifier.weight(1f)
                ) {

                    Text(
                        text = "Saldo disponible",
                        color = Color.White.copy(alpha = 0.80f),
                        fontSize = 12.sp
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "180 puntos",
                        color = Color.White,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Box(
                    modifier = Modifier
                        .size(53.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.18f)),
                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        text = "★",
                        color = Color.White,
                        fontSize = 25.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Surface(
                shape = RoundedCornerShape(13.dp),
                color = Color.White.copy(alpha = 0.15f)
            ) {

                Text(
                    text = "Último movimiento: +30 pts por devolución",
                    modifier = Modifier.padding(
                        horizontal = 12.dp,
                        vertical = 9.dp
                    ),
                    fontSize = 11.sp,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
private fun BenefitCard(
    symbol: String,
    title: String,
    description: String,
    points: String,
    background: Color,
    color: Color
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {

        Row(
            modifier = Modifier.padding(15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .background(background),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = symbol,
                    color = color,
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = ProfileTextPrimary
                )

                Spacer(modifier = Modifier.height(3.dp))

                Text(
                    text = description,
                    fontSize = 11.sp,
                    color = ProfileTextSecondary
                )
            }

            Surface(
                shape = RoundedCornerShape(10.dp),
                color = background
            ) {

                Text(
                    text = points,
                    modifier = Modifier.padding(
                        horizontal = 9.dp,
                        vertical = 6.dp
                    ),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = color
                )
            }
        }
    }
}

@Composable
private fun AccountOptions() {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {

        Column {

            AccountOption(
                symbol = "R",
                title = "Mis reportes",
                subtitle = "Consulta tus publicaciones"
            )

            AccountDivider()

            AccountOption(
                symbol = "C",
                title = "Coincidencias",
                subtitle = "Revisa posibles objetos encontrados"
            )

            AccountDivider()

            AccountOption(
                symbol = "H",
                title = "Historial de puntos",
                subtitle = "Movimientos y canjes realizados"
            )

            AccountDivider()

            AccountOption(
                symbol = "?",
                title = "Ayuda",
                subtitle = "Información sobre la aplicación"
            )
        }
    }
}

@Composable
private fun AccountOption(
    symbol: String,
    title: String,
    subtitle: String
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }
            .padding(
                horizontal = 15.dp,
                vertical = 14.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(41.dp)
                .clip(CircleShape)
                .background(ProfileBlueSoft),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = symbol,
                color = ProfileBlue,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {

            Text(
                text = title,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = ProfileTextPrimary
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = subtitle,
                fontSize = 10.sp,
                color = ProfileTextSecondary
            )
        }

        Text(
            text = "›",
            fontSize = 24.sp,
            color = Color(0xFFB5B5BF)
        )
    }
}

@Composable
private fun AccountDivider() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
            .height(1.dp)
            .background(ProfileBorder)
    )
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    widthDp = 390,
    heightDp = 844
)
@Composable
fun ProfileScreenPreview() {

    ObjetosPerdidosTheme {
        ProfileScreen()
    }
}