package com.upb.objetosperdidos.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.upb.objetosperdidos.R
import com.upb.objetosperdidos.model.EstadoDevolucion
import com.upb.objetosperdidos.ui.theme.ObjetosPerdidosTheme

// Constantes de diseño y paleta cromática para el proceso de devolución
private val ReturnBlue = Color(0xFF1689FF)
private val ReturnBlueSoft = Color(0xFFEAF4FF)
private val ReturnBackground = Color(0xFFF7F7FB)
private val ReturnTextPrimary = Color(0xFF181820)
private val ReturnTextSecondary = Color(0xFF777783)
private val ReturnBorder = Color(0xFFE5E5EC)

private val ReturnGreen = Color(0xFF198754)
private val ReturnGreenSoft = Color(0xFFE8F7F0)

private val ReturnOrange = Color(0xFFF4A340)
private val ReturnOrangeSoft = Color(0xFFFFF5E8)

/**
 * Pantalla del Proceso de Devolución e intercambio seguro.
 * Muestra el seguimiento mediante código QR, linea de tiempo y acreditación de puntos.
 */
@Composable
fun ReturnProcessScreen() {

    var estadoDevolucion by remember {
        mutableStateOf(EstadoDevolucion.PENDIENTE)
    }

    var mostrarQrModal by remember {
        mutableStateOf(false)
    }

    Scaffold(
        containerColor = ReturnBackground
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {

            ReturnTopBar()

            Column(
                modifier = Modifier.padding(
                    horizontal = 18.dp,
                    vertical = 18.dp
                )
            ) {

                // Status Banner
                ReturnStatusHeader(estado = estadoDevolucion)

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Línea de tiempo de la devolución",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = ReturnTextPrimary
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Pasos para completar el proceso de entrega",
                    fontSize = 12.sp,
                    color = ReturnTextSecondary
                )

                Spacer(modifier = Modifier.height(14.dp))

                ReturnTimelineStepper(estado = estadoDevolucion)

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Verificación de identidad",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = ReturnTextPrimary
                )

                Spacer(modifier = Modifier.height(12.dp))

                QrVerificationCard(
                    codigoVerificacion = "DEV-8921-UPB",
                    onMostrarQrClick = { mostrarQrModal = !mostrarQrModal }
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Detalles del acuerdo",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = ReturnTextPrimary
                )

                Spacer(modifier = Modifier.height(12.dp))

                ReturnTransactionDetailsCard()

                Spacer(modifier = Modifier.height(24.dp))

                // Recompensa en Puntos
                RewardPointsNoticeCard()

                Spacer(modifier = Modifier.height(28.dp))

                if (estadoDevolucion == EstadoDevolucion.PENDIENTE) {

                    Button(
                        onClick = {
                            estadoDevolucion = EstadoDevolucion.COMPLETADA
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),
                        shape = RoundedCornerShape(17.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = ReturnGreen
                        )
                    ) {

                        Text(
                            text = "Confirmar Recepción del Objeto",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedButton(
                        onClick = {},
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(17.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = ReturnTextSecondary
                        )
                    ) {

                        Text(
                            text = "Reportar Inconveniente",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                } else {

                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(18.dp),
                        color = ReturnGreenSoft
                    ) {

                        Column(
                            modifier = Modifier.padding(18.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Text(
                                text = "🎉 Devolución Finalizada Exitosamente",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = ReturnGreen
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            Text(
                                text = "Se han acreditado +30 Puntos UPB a la cuenta del hallador.",
                                fontSize = 12.sp,
                                color = ReturnTextPrimary,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(30.dp))
            }
        }
    }
}

@Composable
private fun ReturnTopBar() {

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
                .background(ReturnBlueSoft),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = "‹",
                color = ReturnBlue,
                fontSize = 30.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.width(14.dp))

        Column {

            Text(
                text = "Proceso de Devolución",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = ReturnTextPrimary
            )

            Text(
                text = "Seguimiento y entrega final",
                fontSize = 12.sp,
                color = ReturnTextSecondary
            )
        }
    }
}

@Composable
private fun ReturnStatusHeader(
    estado: EstadoDevolucion
) {

    val esCompletada = estado == EstadoDevolucion.COMPLETADA

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (esCompletada) ReturnGreenSoft else ReturnBlueSoft
        )
    ) {

        Row(
            modifier = Modifier.padding(18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(if (esCompletada) ReturnGreen else ReturnBlue),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = if (esCompletada) "✓" else "🔄",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = if (esCompletada) "Devolución Completada" else "Devolución en Proceso",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (esCompletada) ReturnGreen else ReturnBlue
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = if (esCompletada) "El objeto ya fue devuelto a su dueño" else "Punto de entrega acordado: Biblioteca Central",
                    fontSize = 12.sp,
                    color = ReturnTextPrimary
                )
            }
        }
    }
}

@Composable
private fun ReturnTimelineStepper(
    estado: EstadoDevolucion
) {

    val paso4Activo = estado == EstadoDevolucion.COMPLETADA

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
            modifier = Modifier.padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            TimelineStepItem(
                numero = "1",
                titulo = "Solicitud de recuperación aprobada",
                subtitulo = "Aprobada por el Encargado de custodia",
                completado = true
            )

            TimelineStepItem(
                numero = "2",
                titulo = "Punto de entrega oficial establecido",
                subtitulo = "Biblioteca Central - Campus UPB",
                completado = true
            )

            TimelineStepItem(
                numero = "3",
                titulo = "Presentación de Código QR o PIN",
                subtitulo = "Verificación de carnet universitario",
                completado = true,
                esActual = !paso4Activo
            )

            TimelineStepItem(
                numero = "4",
                titulo = "Entrega de objeto & Otorgamiento de Puntos",
                subtitulo = "Se transfieren +30 Puntos UPB al hallador",
                completado = paso4Activo,
                esActual = paso4Activo,
                esUltimo = true
            )
        }
    }
}

@Composable
private fun TimelineStepItem(
    numero: String,
    titulo: String,
    subtitulo: String,
    completado: Boolean,
    esActual: Boolean = false,
    esUltimo: Boolean = false
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(
                        when {
                            completado && !esActual -> ReturnGreen
                            esActual -> ReturnBlue
                            else -> ReturnBorder
                        }
                    ),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = if (completado && !esActual) "✓" else numero,
                    color = Color.White,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            if (!esUltimo) {

                Box(
                    modifier = Modifier
                        .width(2.dp)
                        .height(30.dp)
                        .background(if (completado) ReturnGreen else ReturnBorder)
                )
            }
        }

        Spacer(modifier = Modifier.width(14.dp))

        Column {

            Text(
                text = titulo,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = ReturnTextPrimary
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = subtitulo,
                fontSize = 11.sp,
                color = ReturnTextSecondary
            )
        }
    }
}

@Composable
private fun QrVerificationCard(
    codigoVerificacion: String,
    onMostrarQrClick: () -> Unit
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
            modifier = Modifier.padding(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Código de Confirmación de Entrega",
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                color = ReturnTextSecondary
            )

            Spacer(modifier = Modifier.height(8.dp))

            Surface(
                shape = RoundedCornerShape(12.dp),
                color = ReturnBlueSoft
            ) {

                Text(
                    text = codigoVerificacion,
                    modifier = Modifier.padding(
                        horizontal = 20.dp,
                        vertical = 10.dp
                    ),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = ReturnBlue,
                    letterSpacing = 2.sp
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Simulación visual de código QR de la app
            Box(
                modifier = Modifier
                    .size(130.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFFF0F0F6))
                    .border(2.dp, ReturnBlueSoft, RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = "[ QR DEVOLUCIÓN ]\n$codigoVerificacion",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = ReturnBlue,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Muestra este código QR al Encargado del Punto de Entrega para confirmar la recepción.",
                fontSize = 11.sp,
                color = ReturnTextSecondary,
                textAlign = TextAlign.Center,
                lineHeight = 16.sp
            )
        }
    }
}

@Composable
private fun ReturnTransactionDetailsCard() {

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

            ReturnDetailRow(
                label = "Objeto:",
                value = "AirPods Pro"
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 10.dp), color = ReturnBorder)

            ReturnDetailRow(
                label = "Propietario (Solicitante):",
                value = "David Silva (EST-2026-089)"
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 10.dp), color = ReturnBorder)

            ReturnDetailRow(
                label = "Hallador:",
                value = "Sebastián Vargas (EST-2026-001)"
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 10.dp), color = ReturnBorder)

            ReturnDetailRow(
                label = "Punto de Entrega:",
                value = "Biblioteca Central UPB - Sede La Paz"
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 10.dp), color = ReturnBorder)

            ReturnDetailRow(
                label = "Encargado:",
                value = "Lic. Mario Gutiérrez"
            )
        }
    }
}

@Composable
private fun ReturnDetailRow(
    label: String,
    value: String
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = label,
            fontSize = 12.sp,
            color = ReturnTextSecondary
        )

        Text(
            text = value,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = ReturnTextPrimary
        )
    }
}

@Composable
private fun RewardPointsNoticeCard() {

    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        color = ReturnOrangeSoft
    ) {

        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(ReturnOrange),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = "★",
                    color = Color.White,
                    fontSize = 22.sp
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column {

                Text(
                    text = "Recompensa para el Hallador",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = ReturnOrange
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = "Al finalizar la entrega, Sebastián Vargas recibirá +30 Puntos UPB por su honestidad y colaboración.",
                    fontSize = 11.sp,
                    color = ReturnTextPrimary,
                    lineHeight = 16.sp
                )
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
fun ReturnProcessScreenPreview() {

    ObjetosPerdidosTheme {
        ReturnProcessScreen()
    }
}