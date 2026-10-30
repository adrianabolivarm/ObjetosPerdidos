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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.upb.objetosperdidos.ui.theme.ObjetosPerdidosTheme
import com.upb.objetosperdidos.ui.viewmodel.ReportesViewModel

private val ReportBlue = Color(0xFF1689FF)
private val ReportBlueSoft = Color(0xFFEAF4FF)
private val ReportBackground = Color(0xFFF7F7FB)
private val ReportTextPrimary = Color(0xFF181820)
private val ReportTextSecondary = Color(0xFF777783)
private val ReportBorder = Color(0xFFE3E3EA)

@Composable
fun ReportObjectScreen(
    viewModel: ReportesViewModel = viewModel(),
    onBackClick: () -> Unit = {},
    onReportCreated: () -> Unit = {}
) {
    var tipoReporte by remember { mutableStateOf("ENCONTRADO") }
    var titulo by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf("Tecnología") }
    var descripcion by remember { mutableStateOf("") }
    var lugar by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf("30 / 09 / 2026") }
    var opcionCustodia by remember { mutableStateOf("PERSONAL") }

    Scaffold(
        containerColor = ReportBackground
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {

            ReportTopBar(onBackClick = onBackClick)

            Column(
                modifier = Modifier.padding(
                    horizontal = 18.dp,
                    vertical = 18.dp
                )
            ) {

                Text(
                    text = "¿Qué ocurrió con el objeto?",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = ReportTextPrimary
                )

                Spacer(modifier = Modifier.height(12.dp))

                TypeSelector(
                    selectedType = tipoReporte,
                    onTypeSelected = { tipoReporte = it }
                )

                Spacer(modifier = Modifier.height(24.dp))

                SectionTitle(
                    title = "Fotos del objeto",
                    subtitle = "Agrega imágenes que ayuden a identificarlo"
                )

                Spacer(modifier = Modifier.height(12.dp))

                PhotoSection()

                Spacer(modifier = Modifier.height(24.dp))

                SectionTitle(
                    title = "Información del objeto",
                    subtitle = "Describe las características principales"
                )

                Spacer(modifier = Modifier.height(14.dp))

                OutlinedTextField(
                    value = titulo,
                    onValueChange = { titulo = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Título del objeto") },
                    placeholder = { Text("Ej. AirPods Pro") },
                    singleLine = true,
                    shape = RoundedCornerShape(16.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = ReportBlue,
                        unfocusedBorderColor = ReportBorder,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    )
                )

                Spacer(modifier = Modifier.height(13.dp))

                OutlinedTextField(
                    value = categoria,
                    onValueChange = { categoria = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Categoría") },
                    placeholder = { Text("Ej. Tecnología, Accesorios...") },
                    singleLine = true,
                    shape = RoundedCornerShape(16.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = ReportBlue,
                        unfocusedBorderColor = ReportBorder,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    )
                )

                Spacer(modifier = Modifier.height(13.dp))

                OutlinedTextField(
                    value = descripcion,
                    onValueChange = { descripcion = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),
                    label = { Text("Descripción") },
                    placeholder = { Text("Describe color, marca, características...") },
                    shape = RoundedCornerShape(16.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = ReportBlue,
                        unfocusedBorderColor = ReportBorder,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    )
                )

                Spacer(modifier = Modifier.height(24.dp))

                SectionTitle(
                    title = "Información del reporte",
                    subtitle = "Indica dónde y cuándo ocurrió"
                )

                Spacer(modifier = Modifier.height(14.dp))

                OutlinedTextField(
                    value = lugar,
                    onValueChange = { lugar = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Lugar") },
                    placeholder = { Text("Ej. Biblioteca Central") },
                    singleLine = true,
                    shape = RoundedCornerShape(16.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = ReportBlue,
                        unfocusedBorderColor = ReportBorder,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    )
                )

                Spacer(modifier = Modifier.height(13.dp))

                OutlinedTextField(
                    value = fecha,
                    onValueChange = { fecha = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Fecha del hallazgo / pérdida") },
                    placeholder = { Text("30 / 09 / 2026") },
                    singleLine = true,
                    shape = RoundedCornerShape(16.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = ReportBlue,
                        unfocusedBorderColor = ReportBorder,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    )
                )

                Spacer(modifier = Modifier.height(24.dp))

                SectionTitle(
                    title = "Custodia del objeto",
                    subtitle = "Indica dónde se encuentra actualmente"
                )

                Spacer(modifier = Modifier.height(13.dp))

                CustodyOptions(
                    selectedOption = opcionCustodia,
                    onOptionSelected = { opcionCustodia = it }
                )

                Spacer(modifier = Modifier.height(18.dp))

                InfoMessage()

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        if (titulo.isNotBlank()) {
                            viewModel.agregarObjeto(
                                nombre = titulo,
                                descripcion = descripcion,
                                categoria = categoria,
                                tipoReporte = tipoReporte,
                                lugar = lugar.ifBlank { "Campus UPB" },
                                fecha = fecha
                            )
                            onReportCreated()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(18.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ReportBlue
                    )
                ) {

                    Text(
                        text = "Publicar reporte",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(30.dp))
            }
        }
    }
}

@Composable
private fun ReportTopBar(onBackClick: () -> Unit) {

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
                .background(ReportBlueSoft)
                .clickable { onBackClick() },
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = "‹",
                color = ReportBlue,
                fontSize = 30.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.width(14.dp))

        Column {

            Text(
                text = "Reportar objeto",
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold,
                color = ReportTextPrimary
            )

            Text(
                text = "Completa los datos del reporte",
                fontSize = 12.sp,
                color = ReportTextSecondary
            )
        }
    }
}

@Composable
private fun TypeSelector(
    selectedType: String,
    onTypeSelected: (String) -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(Color(0xFFEEEEF4))
            .padding(4.dp)
    ) {

        Surface(
            modifier = Modifier
                .weight(1f)
                .clickable { onTypeSelected("ENCONTRADO") },
            shape = RoundedCornerShape(15.dp),
            color = if (selectedType == "ENCONTRADO") ReportBlue else Color.Transparent,
            shadowElevation = if (selectedType == "ENCONTRADO") 2.dp else 0.dp
        ) {

            Box(
                modifier = Modifier.padding(vertical = 12.dp),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = "Encontré",
                    color = if (selectedType == "ENCONTRADO") Color.White else ReportTextSecondary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }
        }

        Spacer(modifier = Modifier.width(5.dp))

        Surface(
            modifier = Modifier
                .weight(1f)
                .clickable { onTypeSelected("PERDIDO") },
            shape = RoundedCornerShape(15.dp),
            color = if (selectedType == "PERDIDO") ReportBlue else Color.Transparent,
            shadowElevation = if (selectedType == "PERDIDO") 2.dp else 0.dp
        ) {

            Box(
                modifier = Modifier.padding(vertical = 12.dp),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = "Perdí",
                    color = if (selectedType == "PERDIDO") Color.White else ReportTextSecondary,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
private fun SectionTitle(
    title: String,
    subtitle: String
) {

    Column {

        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = ReportTextPrimary
        )

        Spacer(modifier = Modifier.height(3.dp))

        Text(
            text = subtitle,
            fontSize = 12.sp,
            color = ReportTextSecondary
        )
    }
}

@Composable
private fun PhotoSection() {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        PhotoOption(
            modifier = Modifier.weight(1f),
            symbol = "□",
            title = "Cámara",
            subtitle = "Tomar foto"
        )

        PhotoOption(
            modifier = Modifier.weight(1f),
            symbol = "+",
            title = "Galería",
            subtitle = "Subir imagen"
        )
    }
}

@Composable
private fun PhotoOption(
    modifier: Modifier,
    symbol: String,
    title: String,
    subtitle: String
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
                .padding(vertical = 22.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(ReportBlueSoft),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = symbol,
                    color = ReportBlue,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = title,
                color = ReportTextPrimary,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )

            Text(
                text = subtitle,
                color = ReportTextSecondary,
                fontSize = 11.sp
            )
        }
    }
}

@Composable
private fun CustodyOptions(
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {

    Column(
        verticalArrangement = Arrangement.spacedBy(11.dp)
    ) {

        CustodyCard(
            selected = selectedOption == "PERSONAL",
            title = "Lo tengo conmigo",
            description = "Coordinaré la entrega directamente.",
            onClick = { onOptionSelected("PERSONAL") }
        )

        CustodyCard(
            selected = selectedOption == "OFICIAL",
            title = "Entregado en punto oficial",
            description = "Biblioteca, Recepción o Seguridad.",
            onClick = { onOptionSelected("OFICIAL") }
        )
    }
}

@Composable
private fun CustodyCard(
    selected: Boolean,
    title: String,
    description: String,
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
        elevation = CardDefaults.cardElevation(
            defaultElevation = if (selected) 2.dp else 1.dp
        )
    ) {

        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(
                        if (selected) {
                            ReportBlue
                        } else {
                            Color(0xFFE6E6EC)
                        }
                    ),
                contentAlignment = Alignment.Center
            ) {

                if (selected) {

                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .clip(CircleShape)
                            .background(Color.White)
                    )
                }
            }

            Spacer(modifier = Modifier.width(13.dp))

            Column {

                Text(
                    text = title,
                    color = ReportTextPrimary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = description,
                    color = ReportTextSecondary,
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Composable
private fun InfoMessage() {

    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        color = ReportBlueSoft
    ) {

        Row(
            modifier = Modifier.padding(15.dp),
            verticalAlignment = Alignment.Top
        ) {

            Box(
                modifier = Modifier
                    .size(27.dp)
                    .clip(CircleShape)
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = "i",
                    color = ReportBlue,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }

            Spacer(modifier = Modifier.width(11.dp))

            Text(
                text = "Al publicar, el sistema podrá comparar este reporte con otros para detectar posibles coincidencias.",
                modifier = Modifier.weight(1f),
                color = ReportBlue,
                fontSize = 12.sp,
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
fun ReportObjectScreenPreview() {

    ObjetosPerdidosTheme {
        ReportObjectScreen()
    }
}