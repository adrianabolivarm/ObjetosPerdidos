package com.upb.objetosperdidos.ui.screens

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
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
import com.upb.objetosperdidos.ui.theme.ObjetosPerdidosTheme

private val AdminBlue = Color(0xFF1689FF)
private val AdminBlueSoft = Color(0xFFEAF4FF)
private val AdminBackground = Color(0xFFF7F7FB)
private val AdminTextPrimary = Color(0xFF181820)
private val AdminTextSecondary = Color(0xFF777783)
private val AdminBorder = Color(0xFFE5E5EC)

private val AdminGreen = Color(0xFF198754)
private val AdminGreenSoft = Color(0xFFE8F7F0)

private val AdminOrange = Color(0xFFF4A340)
private val AdminOrangeSoft = Color(0xFFFFF5E8)

private val AdminRed = Color(0xFFE44747)
private val AdminRedSoft = Color(0xFFFFECEC)

data class SolicitudPendienteUi(
    val id: String,
    val objetoNombre: String,
    val estudianteNombre: String,
    val estudianteCodigo: String,
    val descripcionPrueba: String,
    val fecha: String,
    val imagenRes: Int
)

data class ObjetoCustodiaUi(
    val id: String,
    val objetoNombre: String,
    val ubicacionCaja: String,
    val halladorNombre: String,
    val fechaIngreso: String,
    val estado: String,
    val imagenRes: Int
)

@Composable
fun CustodianDashboardScreen() {

    var busqueda by remember {
        mutableStateOf("")
    }

    var filtroSeleccionado by remember {
        mutableStateOf("TODOS") // "TODOS", "SOLICITUDES", "CUSTODIA"
    }

    val solicitudesPendientes = remember {
        mutableStateListOf(
            SolicitudPendienteUi(
                id = "SOL-001",
                objetoNombre = "AirPods Pro",
                estudianteNombre = "David Silva",
                estudianteCodigo = "EST-2026-089",
                descripcionPrueba = "Poseen un estuche color blanco con un rasguño pequeño en la parte trasera derecha, se conectan como 'David's AirPods Pro'.",
                fecha = "Hace 10 min",
                imagenRes = R.drawable.airpods_pro
            ),
            SolicitudPendienteUi(
                id = "SOL-002",
                objetoNombre = "Mochila negra",
                estudianteNombre = "Ana María López",
                estudianteCodigo = "EST-2026-041",
                descripcionPrueba = "Marca Adidas con un llavero de metal de UPB colgado en el cierre principal.",
                fecha = "Hace 1 hora",
                imagenRes = R.drawable.mochila_negra
            )
        )
    }

    val objetosCustodia = remember {
        mutableStateListOf(
            ObjetoCustodiaUi(
                id = "CUST-101",
                objetoNombre = "AirPods Pro",
                ubicacionCaja = "Caja Custodia A-12",
                halladorNombre = "Sebastián Vargas",
                fechaIngreso = "29 Sep 2026",
                estado = "Solicitud en revisión",
                imagenRes = R.drawable.airpods_pro
            ),
            ObjetoCustodiaUi(
                id = "CUST-102",
                objetoNombre = "Carnet universitario",
                ubicacionCaja = "Estante B-04",
                halladorNombre = "Adriana Bolívar",
                fechaIngreso = "27 Sep 2026",
                estado = "Esperando reclamante",
                imagenRes = R.drawable.carnet_estudiante
            ),
            ObjetoCustodiaUi(
                id = "CUST-103",
                objetoNombre = "Calculadora científica",
                ubicacionCaja = "Caja Custodia A-08",
                halladorNombre = "Carlos Mendoza",
                fechaIngreso = "25 Sep 2026",
                estado = "Esperando reclamante",
                imagenRes = R.drawable.calculadora_cientifica
            )
        )
    }

    Scaffold(
        containerColor = AdminBackground
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {

            CustodianTopBar()

            Column(
                modifier = Modifier.padding(
                    horizontal = 18.dp,
                    vertical = 18.dp
                )
            ) {

                CustodianProfileCard()

                Spacer(modifier = Modifier.height(18.dp))

                // Métricas
                CustodianMetricsRow(
                    enCustodia = objetosCustodia.size,
                    solicitudesPendientes = solicitudesPendientes.size,
                    entregasHoy = 5
                )

                Spacer(modifier = Modifier.height(22.dp))

                // Buscador de objetos / solicitudes
                OutlinedTextField(
                    value = busqueda,
                    onValueChange = { busqueda = it },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Text(text = "⌕", color = AdminTextSecondary, fontSize = 24.sp)
                    },
                    placeholder = {
                        Text(text = "Buscar por objeto, código o estudiante...", color = AdminTextSecondary, fontSize = 13.sp)
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(18.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = AdminBlue,
                        unfocusedBorderColor = AdminBorder,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    )
                )

                Spacer(modifier = Modifier.height(14.dp))

                // Filtros de sección
                CustodianFilterChips(
                    seleccionado = filtroSeleccionado,
                    onSeleccionar = { filtroSeleccionado = it }
                )

                Spacer(modifier = Modifier.height(22.dp))

                // Sección Solicitudes Pendientes
                if (filtroSeleccionado == "TODOS" || filtroSeleccionado == "SOLICITUDES") {

                    Text(
                        text = "Solicitudes por aprobar (${solicitudesPendientes.size})",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = AdminTextPrimary
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Evalúa las pruebas presentadas por los estudiantes solicitantes",
                        fontSize = 12.sp,
                        color = AdminTextSecondary
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    if (solicitudesPendientes.isEmpty()) {

                        Surface(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(18.dp),
                            color = Color.White
                        ) {

                            Text(
                                text = "No hay solicitudes pendientes de revisión.",
                                modifier = Modifier.padding(20.dp),
                                fontSize = 13.sp,
                                color = AdminTextSecondary
                            )
                        }

                    } else {

                        solicitudesPendientes.forEach { solicitud ->

                            PendingRequestCard(
                                solicitud = solicitud,
                                onAprobar = {
                                    solicitudesPendientes.remove(solicitud)
                                },
                                onRechazar = {
                                    solicitudesPendientes.remove(solicitud)
                                }
                            )

                            Spacer(modifier = Modifier.height(14.dp))
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                }

                // Sección Objetos en Custodia
                if (filtroSeleccionado == "TODOS" || filtroSeleccionado == "CUSTODIA") {

                    Text(
                        text = "Objetos depositados en custodia (${objetosCustodia.size})",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = AdminTextPrimary
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Inventario físico de objetos en Biblioteca Central",
                        fontSize = 12.sp,
                        color = AdminTextSecondary
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    objetosCustodia.forEach { objeto ->

                        CustodyItemAdminCard(objeto = objeto)

                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }

                Spacer(modifier = Modifier.height(30.dp))
            }
        }
    }
}

@Composable
private fun CustodianTopBar() {

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
                .background(AdminBlueSoft),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = "‹",
                color = AdminBlue,
                fontSize = 30.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.width(14.dp))

        Column {

            Text(
                text = "Panel del Encargado",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = AdminTextPrimary
            )

            Text(
                text = "Gestión institucional de Punto de Entrega",
                fontSize = 12.sp,
                color = AdminTextSecondary
            )
        }
    }
}

@Composable
private fun CustodianProfileCard() {

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
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(54.dp)
                    .clip(CircleShape)
                    .background(AdminBlue),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = "MG",
                    color = Color.White,
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = "Lic. Mario Gutiérrez",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = AdminTextPrimary
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = "Encargado Oficial - Biblioteca Central",
                    fontSize = 12.sp,
                    color = AdminBlue,
                    fontWeight = FontWeight.SemiBold
                )

                Text(
                    text = "Sede La Paz - Universidad Privada Boliviana",
                    fontSize = 11.sp,
                    color = AdminTextSecondary
                )
            }
        }
    }
}

@Composable
private fun CustodianMetricsRow(
    enCustodia: Int,
    solicitudesPendientes: Int,
    entregasHoy: Int
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        MetricCard(
            modifier = Modifier.weight(1f),
            numero = "$enCustodia",
            etiqueta = "En Custodia",
            color = AdminBlue,
            fondo = AdminBlueSoft
        )

        MetricCard(
            modifier = Modifier.weight(1f),
            numero = "$solicitudesPendientes",
            etiqueta = "Pendientes",
            color = AdminOrange,
            fondo = AdminOrangeSoft
        )

        MetricCard(
            modifier = Modifier.weight(1f),
            numero = "$entregasHoy",
            etiqueta = "Entregados",
            color = AdminGreen,
            fondo = AdminGreenSoft
        )
    }
}

@Composable
private fun MetricCard(
    modifier: Modifier,
    numero: String,
    etiqueta: String,
    color: Color,
    fondo: Color
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
                .padding(vertical = 14.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(fondo),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = numero,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = color
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = etiqueta,
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium,
                color = AdminTextSecondary
            )
        }
    }
}

@Composable
private fun CustodianFilterChips(
    seleccionado: String,
    onSeleccionar: (String) -> Unit
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        FilterChip(
            modifier = Modifier.weight(1f),
            texto = "Todos",
            activo = seleccionado == "TODOS",
            onClick = { onSeleccionar("TODOS") }
        )

        FilterChip(
            modifier = Modifier.weight(1f),
            texto = "Solicitudes",
            activo = seleccionado == "SOLICITUDES",
            onClick = { onSeleccionar("SOLICITUDES") }
        )

        FilterChip(
            modifier = Modifier.weight(1f),
            texto = "En Custodia",
            activo = seleccionado == "CUSTODIA",
            onClick = { onSeleccionar("CUSTODIA") }
        )
    }
}

@Composable
private fun FilterChip(
    modifier: Modifier,
    texto: String,
    activo: Boolean,
    onClick: () -> Unit
) {

    Surface(
        modifier = modifier.clickable { onClick() },
        shape = RoundedCornerShape(14.dp),
        color = if (activo) AdminBlue else Color.White,
        shadowElevation = if (activo) 2.dp else 0.dp
    ) {

        Box(
            modifier = Modifier.padding(vertical = 10.dp),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = texto,
                fontSize = 12.sp,
                fontWeight = if (activo) FontWeight.Bold else FontWeight.Medium,
                color = if (activo) Color.White else AdminTextSecondary
            )
        }
    }
}

@Composable
private fun PendingRequestCard(
    solicitud: SolicitudPendienteUi,
    onAprobar: () -> Unit,
    onRechazar: () -> Unit
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
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
                    color = AdminOrangeSoft
                ) {

                    Text(
                        text = "SOLICITUD PENDIENTE",
                        modifier = Modifier.padding(
                            horizontal = 8.dp,
                            vertical = 4.dp
                        ),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = AdminOrange
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = solicitud.fecha,
                    fontSize = 11.sp,
                    color = AdminTextSecondary
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                verticalAlignment = Alignment.Top
            ) {

                Image(
                    painter = painterResource(id = solicitud.imagenRes),
                    contentDescription = solicitud.objetoNombre,
                    modifier = Modifier
                        .size(64.dp)
                        .clip(RoundedCornerShape(14.dp)),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {

                    Text(
                        text = solicitud.objetoNombre,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = AdminTextPrimary
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    Text(
                        text = "Solicitante: ${solicitud.estudianteNombre}",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = AdminBlue
                    )

                    Text(
                        text = "Código: ${solicitud.estudianteCodigo}",
                        fontSize = 11.sp,
                        color = AdminTextSecondary
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Surface(
                shape = RoundedCornerShape(12.dp),
                color = AdminBackground
            ) {

                Column(
                    modifier = Modifier.padding(12.dp)
                ) {

                    Text(
                        text = "Prueba presentada:",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = AdminTextSecondary
                    )

                    Spacer(modifier = Modifier.height(3.dp))

                    Text(
                        text = solicitud.descripcionPrueba,
                        fontSize = 12.sp,
                        color = AdminTextPrimary,
                        lineHeight = 17.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                Button(
                    onClick = onAprobar,
                    modifier = Modifier
                        .weight(1f)
                        .height(44.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AdminGreen
                    )
                ) {

                    Text(
                        text = "✓ Aprobar",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

                OutlinedButton(
                    onClick = onRechazar,
                    modifier = Modifier
                        .weight(1f)
                        .height(44.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = AdminRed
                    )
                ) {

                    Text(
                        text = "✕ Rechazar",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
private fun CustodyItemAdminCard(
    objeto: ObjetoCustodiaUi
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

        Row(
            modifier = Modifier.padding(15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(id = objeto.imagenRes),
                contentDescription = objeto.objetoNombre,
                modifier = Modifier
                    .size(56.dp)
                    .clip(RoundedCornerShape(14.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = AdminBlueSoft
                ) {

                    Text(
                        text = objeto.ubicacionCaja,
                        modifier = Modifier.padding(
                            horizontal = 8.dp,
                            vertical = 3.dp
                        ),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = AdminBlue
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = objeto.objetoNombre,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = AdminTextPrimary
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = "Depositado por: ${objeto.halladorNombre}",
                    fontSize = 11.sp,
                    color = AdminTextSecondary
                )
            }

            Text(
                text = "›",
                fontSize = 24.sp,
                color = AdminTextSecondary
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
fun CustodianDashboardScreenPreview() {

    ObjetosPerdidosTheme {
        CustodianDashboardScreen()
    }
}