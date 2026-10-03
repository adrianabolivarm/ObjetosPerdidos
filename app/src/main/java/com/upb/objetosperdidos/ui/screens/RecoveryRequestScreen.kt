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
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.upb.objetosperdidos.R
import com.upb.objetosperdidos.model.EstadoSolicitud
import com.upb.objetosperdidos.ui.theme.ObjetosPerdidosTheme

private val RequestBlue = Color(0xFF1689FF)
private val RequestBlueSoft = Color(0xFFEAF4FF)
private val RequestBackground = Color(0xFFF7F7FB)
private val RequestTextPrimary = Color(0xFF181820)
private val RequestTextSecondary = Color(0xFF777783)
private val RequestBorder = Color(0xFFE5E5EC)

private val RequestGreen = Color(0xFF198754)
private val RequestGreenSoft = Color(0xFFE8F7F0)

private val RequestOrange = Color(0xFFF4A340)
private val RequestOrangeSoft = Color(0xFFFFF5E8)

@Composable
fun RecoveryRequestScreen() {

    var descripcionPrueba by remember {
        mutableStateOf("")
    }

    var metodoEntregaSeleccionado by remember {
        mutableStateOf("PUNTO_OFICIAL") // "PUNTO_OFICIAL" o "ENTREGA_DIRECTA"
    }

    var adjuntoAgregado by remember {
        mutableStateOf(false)
    }

    var estadoSolicitud by remember {
        mutableStateOf<EstadoSolicitud?>(null)
    }

    Scaffold(
        containerColor = RequestBackground
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {

            RequestTopBar()

            Column(
                modifier = Modifier.padding(
                    horizontal = 18.dp,
                    vertical = 18.dp
                )
            ) {

                // Banner de estado en caso de haberse enviado la solicitud
                AnimatedVisibility(visible = estadoSolicitud != null) {

                    Column {

                        RequestSubmittedBanner(
                            estado = estadoSolicitud ?: EstadoSolicitud.PENDIENTE
                        )

                        Spacer(modifier = Modifier.height(18.dp))
                    }
                }

                Text(
                    text = "Objeto a solicitar",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = RequestTextPrimary
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Verifica la información del reporte publicado",
                    fontSize = 12.sp,
                    color = RequestTextSecondary
                )

                Spacer(modifier = Modifier.height(12.dp))

                TargetObjectCard()

                Spacer(modifier = Modifier.height(22.dp))

                Text(
                    text = "Datos del solicitante",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = RequestTextPrimary
                )

                Spacer(modifier = Modifier.height(12.dp))

                ApplicantStudentCard()

                Spacer(modifier = Modifier.height(22.dp))

                Text(
                    text = "Pruebas de propiedad",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = RequestTextPrimary
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Describe características únicas del objeto (marcas, contenido, números de serie, funda, etc.)",
                    fontSize = 12.sp,
                    color = RequestTextSecondary,
                    lineHeight = 17.sp
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = descripcionPrueba,
                    onValueChange = { descripcionPrueba = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp),
                    placeholder = {

                        Text(
                            text = "Ej: El estuche tiene un sticker pequeño de UPB en la parte trasera. Adicionalmente, el audífono derecho tiene una pequeña marca cerca del micrófono...",
                            color = RequestTextSecondary,
                            fontSize = 13.sp
                        )
                    },
                    shape = RoundedCornerShape(18.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = RequestBlue,
                        unfocusedBorderColor = RequestBorder,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    )
                )

                Spacer(modifier = Modifier.height(14.dp))

                // Botón para simular adjuntar comprobante / foto
                AttachmentSection(
                    adjuntoAgregado = adjuntoAgregado,
                    onToggleAdjunto = { adjuntoAgregado = !adjuntoAgregado }
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Preferencia de entrega",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = RequestTextPrimary
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Selecciona cómo prefieres recuperar tu objeto",
                    fontSize = 12.sp,
                    color = RequestTextSecondary
                )

                Spacer(modifier = Modifier.height(12.dp))

                DeliveryMethodOptions(
                    seleccionado = metodoEntregaSeleccionado,
                    onSeleccionar = { metodoEntregaSeleccionado = it }
                )

                Spacer(modifier = Modifier.height(28.dp))

                Button(
                    onClick = {
                        estadoSolicitud = EstadoSolicitud.PENDIENTE
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    enabled = descripcionPrueba.isNotBlank() && estadoSolicitud == null,
                    shape = RoundedCornerShape(17.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = RequestBlue,
                        disabledContainerColor = Color(0xFFC4DFFF)
                    )
                ) {

                    Text(
                        text = if (estadoSolicitud != null) "Solicitud enviada" else "Enviar Solicitud de Recuperación",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Tu solicitud será evaluada por el encargado del punto de entrega o el estudiante hallador.",
                    color = RequestTextSecondary,
                    fontSize = 11.sp,
                    lineHeight = 16.sp
                )

                Spacer(modifier = Modifier.height(30.dp))
            }
        }
    }
}

@Composable
private fun RequestTopBar() {

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
                .background(RequestBlueSoft),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = "‹",
                color = RequestBlue,
                fontSize = 30.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.width(14.dp))

        Column {

            Text(
                text = "Solicitud de recuperación",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = RequestTextPrimary
            )

            Text(
                text = "Comprueba la propiedad de tu objeto",
                fontSize = 12.sp,
                color = RequestTextSecondary
            )
        }
    }
}

@Composable
private fun TargetObjectCard() {

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

        Row(
            modifier = Modifier.padding(15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(id = R.drawable.airpods_pro),
                contentDescription = "AirPods Pro",
                modifier = Modifier
                    .size(72.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(14.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = RequestGreenSoft
                ) {

                    Text(
                        text = "En custodia - Biblioteca Central",
                        modifier = Modifier.padding(
                            horizontal = 9.dp,
                            vertical = 4.dp
                        ),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = RequestGreen
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "AirPods Pro",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = RequestTextPrimary
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = "Lugar del hallazgo: Biblioteca Central",
                    fontSize = 11.sp,
                    color = RequestTextSecondary
                )

                Text(
                    text = "Fecha: 29 Sep 2026",
                    fontSize = 11.sp,
                    color = RequestTextSecondary
                )
            }
        }
    }
}

@Composable
private fun ApplicantStudentCard() {

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

                Box(
                    modifier = Modifier
                        .size(46.dp)
                        .clip(CircleShape)
                        .background(RequestBlue),
                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        text = "DS",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column {

                    Text(
                        text = "David Silva",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = RequestTextPrimary
                    )

                    Text(
                        text = "Estudiante UPB - Código EST-2026-089",
                        fontSize = 11.sp,
                        color = RequestTextSecondary
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            HorizontalDivider(color = RequestBorder)

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = "Correo:",
                    fontSize = 11.sp,
                    color = RequestTextSecondary
                )

                Text(
                    text = "david.silva@upb.edu",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = RequestTextPrimary
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = "Teléfono:",
                    fontSize = 11.sp,
                    color = RequestTextSecondary
                )

                Text(
                    text = "+591 76543210",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = RequestTextPrimary
                )
            }
        }
    }
}

@Composable
private fun AttachmentSection(
    adjuntoAgregado: Boolean,
    onToggleAdjunto: () -> Unit
) {

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onToggleAdjunto() },
        shape = RoundedCornerShape(18.dp),
        color = if (adjuntoAgregado) RequestGreenSoft else Color.White,
        border = androidx.compose.foundation.BorderStroke(
            width = 1.dp,
            color = if (adjuntoAgregado) RequestGreen else RequestBorder
        )
    ) {

        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(42.dp)
                    .clip(CircleShape)
                    .background(if (adjuntoAgregado) RequestGreen.copy(alpha = 0.20f) else RequestBlueSoft),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = if (adjuntoAgregado) "✓" else "+",
                    color = if (adjuntoAgregado) RequestGreen else RequestBlue,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = if (adjuntoAgregado) "Comprobante adjuntado" else "Adjuntar comprobante o foto de respaldo (Opcional)",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (adjuntoAgregado) RequestGreen else RequestTextPrimary
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = if (adjuntoAgregado) "Foto de la caja/factura añadida exitosamente" else "Sube una imagen de la caja, factura o foto anterior del objeto",
                    fontSize = 11.sp,
                    color = RequestTextSecondary
                )
            }
        }
    }
}

@Composable
private fun DeliveryMethodOptions(
    seleccionado: String,
    onSeleccionar: (String) -> Unit
) {

    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        DeliveryMethodCard(
            title = "Punto de Entrega Oficial UPB (Recomendado)",
            subtitle = "Retira tu objeto en Biblioteca Central con el Encargado oficial",
            selected = seleccionado == "PUNTO_OFICIAL",
            onClick = { onSeleccionar("PUNTO_OFICIAL") }
        )

        DeliveryMethodCard(
            title = "Entrega Directa con Hallador",
            subtitle = "Acuerda una reunión directa dentro del campus con el estudiante que lo encontró",
            selected = seleccionado == "ENTREGA_DIRECTA",
            onClick = { onSeleccionar("ENTREGA_DIRECTA") }
        )
    }
}

@Composable
private fun DeliveryMethodCard(
    title: String,
    subtitle: String,
    selected: Boolean,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        border = androidx.compose.foundation.BorderStroke(
            width = if (selected) 2.dp else 1.dp,
            color = if (selected) RequestBlue else RequestBorder
        )
    ) {

        Row(
            modifier = Modifier.padding(15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            RadioButton(
                selected = selected,
                onClick = onClick,
                colors = RadioButtonDefaults.colors(
                    selectedColor = RequestBlue
                )
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column {

                Text(
                    text = title,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = RequestTextPrimary
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = subtitle,
                    fontSize = 11.sp,
                    color = RequestTextSecondary,
                    lineHeight = 15.sp
                )
            }
        }
    }
}

@Composable
private fun RequestSubmittedBanner(
    estado: EstadoSolicitud
) {

    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        color = RequestOrangeSoft,
        border = androidx.compose.foundation.BorderStroke(1.dp, RequestOrange)
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .size(38.dp)
                        .clip(CircleShape)
                        .background(RequestOrange),
                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        text = "⏳",
                        fontSize = 18.sp
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column {

                    Text(
                        text = "Solicitud #SOL-2026-042 Enviada",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = RequestOrange
                    )

                    Text(
                        text = "Estado: ${estado.name}",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = RequestTextPrimary
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Tu solicitud ha sido registrada correctamente. El encargado del Punto de Entrega revisará la información de prueba en breve.",
                fontSize = 12.sp,
                color = RequestTextPrimary,
                lineHeight = 17.sp
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
fun RecoveryRequestScreenPreview() {

    ObjetosPerdidosTheme {
        RecoveryRequestScreen()
    }
}