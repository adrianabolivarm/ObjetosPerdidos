package com.upb.objetosperdidos.ui.screens

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.upb.objetosperdidos.model.Beneficio
import com.upb.objetosperdidos.model.MovimientoPuntos
import com.upb.objetosperdidos.model.TipoMovimientoPuntos
import com.upb.objetosperdidos.ui.theme.ObjetosPerdidosTheme

private val PointsBlue = Color(0xFF1689FF)
private val PointsBlueSoft = Color(0xFFEAF4FF)
private val PointsBackground = Color(0xFFF7F7FB)
private val PointsTextPrimary = Color(0xFF181820)
private val PointsTextSecondary = Color(0xFF777783)
private val PointsBorder = Color(0xFFE5E5EC)

private val PointsGreen = Color(0xFF198754)
private val PointsGreenSoft = Color(0xFFE8F7F0)

private val PointsOrange = Color(0xFFF4A340)
private val PointsOrangeSoft = Color(0xFFFFF5E8)

private val PointsPurple = Color(0xFF7C6CF2)
private val PointsPurpleSoft = Color(0xFFF0EEFF)

@Composable
fun PointsBenefitsScreen() {

    var saldoPuntos by remember {
        mutableIntStateOf(180)
    }

    var pestanaSeleccionada by remember {
        mutableStateOf("BENEFICIOS") // "BENEFICIOS", "HISTORIAL", "CANJES"
    }

    var canjeExitosoMensaje by remember {
        mutableStateOf<String?>(null)
    }

    val beneficiosLista = listOf(
        Beneficio(
            id = 1,
            nombre = "Descuento 15% Cafetería UPB",
            descripcion = "Válido para un consumo en la cafetería central del campus.",
            costoPuntos = 100,
            disponible = true
        ),
        Beneficio(
            id = 2,
            nombre = "Impresiones Gratis Biblioteca (50 pág)",
            descripcion = "Validez para impresiones blanco y negro en la Biblioteca.",
            costoPuntos = 120,
            disponible = true
        ),
        Beneficio(
            id = 3,
            nombre = "Pase Preferencial Parqueadero UPB",
            descripcion = "Reserva de estacionamiento preferencial por un día.",
            costoPuntos = 200,
            disponible = true
        ),
        Beneficio(
            id = 4,
            nombre = "Termo Oficial UPB Objetos Perdidos",
            descripcion = "Merchandising de la universidad por colaboración solidaria.",
            costoPuntos = 250,
            disponible = true
        )
    )

    val movimientosLista = listOf(
        MovimientoPuntos(
            id = 101,
            estudianteId = 1,
            cantidad = 30,
            tipo = TipoMovimientoPuntos.GANANCIA,
            motivo = "Devolución exitosa: AirPods Pro",
            fecha = "29 Sep 2026"
        ),
        MovimientoPuntos(
            id = 102,
            estudianteId = 1,
            cantidad = 30,
            tipo = TipoMovimientoPuntos.GANANCIA,
            motivo = "Devolución exitosa: Carnet Universitario",
            fecha = "22 Sep 2026"
        ),
        MovimientoPuntos(
            id = 103,
            estudianteId = 1,
            cantidad = 100,
            tipo = TipoMovimientoPuntos.CANJE,
            motivo = "Canje realizado: Descuento Cafetería 10%",
            fecha = "15 Sep 2026"
        ),
        MovimientoPuntos(
            id = 104,
            estudianteId = 1,
            cantidad = 30,
            tipo = TipoMovimientoPuntos.GANANCIA,
            motivo = "Devolución exitosa: Mochila Negra",
            fecha = "10 Sep 2026"
        )
    )

    Scaffold(
        containerColor = PointsBackground
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {

            PointsTopBar()

            Column(
                modifier = Modifier.padding(
                    horizontal = 18.dp,
                    vertical = 18.dp
                )
            ) {

                PointsBalanceHeroCard(
                    saldoPuntos = saldoPuntos,
                    totalDevoluciones = 3
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Mensaje de éxito de canje
                AnimatedVisibility(visible = canjeExitosoMensaje != null) {

                    Column {

                        RedeemSuccessBanner(
                            mensaje = canjeExitosoMensaje ?: "",
                            onCerrar = { canjeExitosoMensaje = null }
                        )

                        Spacer(modifier = Modifier.height(18.dp))
                    }
                }

                // Selector de pestañas
                PointsTabsSelector(
                    seleccionada = pestanaSeleccionada,
                    onSeleccionar = { pestanaSeleccionada = it }
                )

                Spacer(modifier = Modifier.height(18.dp))

                when (pestanaSeleccionada) {

                    "BENEFICIOS" -> {

                        Text(
                            text = "Catálogo de recompensas",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = PointsTextPrimary
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "Canjea tus puntos acumulados por beneficios exclusivos en la UPB",
                            fontSize = 12.sp,
                            color = PointsTextSecondary
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        beneficiosLista.forEach { beneficio ->

                            BenefitCatalogCard(
                                beneficio = beneficio,
                                saldoActual = saldoPuntos,
                                onCanjear = {
                                    if (saldoPuntos >= beneficio.costoPuntos) {
                                        saldoPuntos -= beneficio.costoPuntos
                                        canjeExitosoMensaje = "¡Has canjeado '${beneficio.nombre}' por ${beneficio.costoPuntos} pts! Código de cupón generado: UPB-CUPON-${(1000..9999).random()}."
                                    }
                                }
                            )

                            Spacer(modifier = Modifier.height(12.dp))
                        }
                    }

                    "HISTORIAL" -> {

                        Text(
                            text = "Historial de movimientos",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = PointsTextPrimary
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "Registro de puntos ganados por devoluciones y puntos utilizandos",
                            fontSize = 12.sp,
                            color = PointsTextSecondary
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        PointsHistoryCard(movimientos = movimientosLista)
                    }

                    "CANJES" -> {

                        Text(
                            text = "Mis canjes activos",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = PointsTextPrimary
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "Cupones y beneficios listos para utilizar en la universidad",
                            fontSize = 12.sp,
                            color = PointsTextSecondary
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        ActiveCouponsCard()
                    }
                }

                Spacer(modifier = Modifier.height(30.dp))
            }
        }
    }
}

@Composable
private fun PointsTopBar() {

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
                .background(PointsBlueSoft),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = "‹",
                color = PointsBlue,
                fontSize = 30.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.width(14.dp))

        Column {

            Text(
                text = "Puntos y Beneficios UPB",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = PointsTextPrimary
            )

            Text(
                text = "Recompensas por tu solidaridad",
                fontSize = 12.sp,
                color = PointsTextSecondary
            )
        }
    }
}

@Composable
private fun PointsBalanceHeroCard(
    saldoPuntos: Int,
    totalDevoluciones: Int
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = PointsBlue
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
    ) {

        Column(
            modifier = Modifier.padding(20.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column(
                    modifier = Modifier.weight(1f)
                ) {

                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = Color.White.copy(alpha = 0.20f)
                    ) {

                        Text(
                            text = "Estudiante Solidario ⭐",
                            modifier = Modifier.padding(
                                horizontal = 10.dp,
                                vertical = 5.dp
                            ),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Saldo de Puntos",
                        fontSize = 12.sp,
                        color = Color.White.copy(alpha = 0.85f)
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    Text(
                        text = "$saldoPuntos pts",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.20f)),
                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        text = "★",
                        color = Color.White,
                        fontSize = 32.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            HorizontalDivider(color = Color.White.copy(alpha = 0.20f))

            Spacer(modifier = Modifier.height(14.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Objetos devueltos: $totalDevoluciones",
                    fontSize = 12.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Medium
                )

                Text(
                    text = "Puntos no vencen",
                    fontSize = 11.sp,
                    color = Color.White.copy(alpha = 0.80f)
                )
            }
        }
    }
}

@Composable
private fun PointsTabsSelector(
    seleccionada: String,
    onSeleccionar: (String) -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(Color(0xFFEEEEF4))
            .padding(4.dp)
    ) {

        TabOption(
            modifier = Modifier.weight(1f),
            text = "Beneficios",
            selected = seleccionada == "BENEFICIOS",
            onClick = { onSeleccionar("BENEFICIOS") }
        )

        TabOption(
            modifier = Modifier.weight(1f),
            text = "Historial",
            selected = seleccionada == "HISTORIAL",
            onClick = { onSeleccionar("HISTORIAL") }
        )

        TabOption(
            modifier = Modifier.weight(1f),
            text = "Mis Canjes",
            selected = seleccionada == "CANJES",
            onClick = { onSeleccionar("CANJES") }
        )
    }
}

@Composable
private fun TabOption(
    modifier: Modifier,
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {

    Surface(
        modifier = modifier.clickable { onClick() },
        shape = RoundedCornerShape(14.dp),
        color = if (selected) Color.White else Color.Transparent,
        shadowElevation = if (selected) 1.dp else 0.dp
    ) {

        Box(
            modifier = Modifier.padding(vertical = 10.dp),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = text,
                fontSize = 12.sp,
                fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium,
                color = if (selected) PointsBlue else PointsTextSecondary
            )
        }
    }
}

@Composable
private fun BenefitCatalogCard(
    beneficio: Beneficio,
    saldoActual: Int,
    onCanjear: () -> Unit
) {

    val alcanzaPuntos = saldoActual >= beneficio.costoPuntos

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

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Row(
                verticalAlignment = Alignment.Top
            ) {

                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(if (alcanzaPuntos) PointsOrangeSoft else PointsPurpleSoft),
                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        text = "🎁",
                        fontSize = 22.sp
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {

                    Text(
                        text = beneficio.nombre,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = PointsTextPrimary
                    )

                    Spacer(modifier = Modifier.height(3.dp))

                    Text(
                        text = beneficio.descripcion,
                        fontSize = 11.sp,
                        color = PointsTextSecondary,
                        lineHeight = 16.sp
                    )
                }

                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = PointsBlueSoft
                ) {

                    Text(
                        text = "${beneficio.costoPuntos} pts",
                        modifier = Modifier.padding(
                            horizontal = 10.dp,
                            vertical = 6.dp
                        ),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = PointsBlue
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            Button(
                onClick = onCanjear,
                enabled = alcanzaPuntos,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PointsBlue,
                    disabledContainerColor = Color(0xFFE2E2EC)
                )
            ) {

                Text(
                    text = if (alcanzaPuntos) "Canjear Beneficio" else "Te faltan ${beneficio.costoPuntos - saldoActual} pts",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (alcanzaPuntos) Color.White else PointsTextSecondary
                )
            }
        }
    }
}

@Composable
private fun PointsHistoryCard(
    movimientos: List<MovimientoPuntos>
) {

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

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            movimientos.forEachIndexed { index, mov ->

                val esGanancia = mov.tipo == TipoMovimientoPuntos.GANANCIA

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(if (esGanancia) PointsGreenSoft else PointsOrangeSoft),
                        contentAlignment = Alignment.Center
                    ) {

                        Text(
                            text = if (esGanancia) "+" else "-",
                            color = if (esGanancia) PointsGreen else PointsOrange,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column(
                        modifier = Modifier.weight(1f)
                    ) {

                        Text(
                            text = mov.motivo,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = PointsTextPrimary
                        )

                        Spacer(modifier = Modifier.height(2.dp))

                        Text(
                            text = mov.fecha,
                            fontSize = 11.sp,
                            color = PointsTextSecondary
                        )
                    }

                    Text(
                        text = "${if (esGanancia) "+" else "-"}${mov.cantidad} pts",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (esGanancia) PointsGreen else PointsOrange
                    )
                }

                if (index < movimientos.size - 1) {

                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 12.dp),
                        color = PointsBorder
                    )
                }
            }
        }
    }
}

@Composable
private fun ActiveCouponsCard() {

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

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = PointsGreenSoft
                ) {

                    Text(
                        text = "DISPONIBLE",
                        modifier = Modifier.padding(
                            horizontal = 8.dp,
                            vertical = 4.dp
                        ),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = PointsGreen
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "Vence: 30 Oct 2026",
                    fontSize = 11.sp,
                    color = PointsTextSecondary
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Descuento 10% Cafetería UPB",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = PointsTextPrimary
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Presenta el siguiente cupón en la caja de la cafetería central.",
                fontSize = 12.sp,
                color = PointsTextSecondary
            )

            Spacer(modifier = Modifier.height(12.dp))

            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                color = PointsBlueSoft
            ) {

                Text(
                    text = "CUPON-CAF-9831",
                    modifier = Modifier.padding(12.dp),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = PointsBlue,
                    textAlign = TextAlign.Center,
                    letterSpacing = 2.sp
                )
            }
        }
    }
}

@Composable
private fun RedeemSuccessBanner(
    mensaje: String,
    onCerrar: () -> Unit
) {

    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        color = PointsGreenSoft,
        border = androidx.compose.foundation.BorderStroke(1.dp, PointsGreen)
    ) {

        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(38.dp)
                    .clip(CircleShape)
                    .background(PointsGreen),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = "✓",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = "Canje Realizado Exitosamente",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = PointsGreen
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = mensaje,
                    fontSize = 11.sp,
                    color = PointsTextPrimary,
                    lineHeight = 15.sp
                )
            }

            Text(
                text = "✕",
                modifier = Modifier
                    .clickable { onCerrar() }
                    .padding(4.dp),
                fontSize = 16.sp,
                color = PointsTextSecondary
            )
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
fun PointsBenefitsScreenPreview() {

    ObjetosPerdidosTheme {
        PointsBenefitsScreen()
    }
}