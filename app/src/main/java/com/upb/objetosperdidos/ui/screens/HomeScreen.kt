package com.upb.objetosperdidos.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.upb.objetosperdidos.R
import com.upb.objetosperdidos.ui.theme.ObjetosPerdidosTheme

private val AzulPrincipal = Color(0xFF1689FF)
private val AzulSuave = Color(0xFFEAF4FF)
private val FondoApp = Color(0xFFF7F7FB)

private val TextoPrincipal = Color(0xFF181820)
private val TextoSecundario = Color(0xFF777783)

private val VerdeEstado = Color(0xFF198754)
private val VerdeFondo = Color(0xFFE8F7F0)

private val RojoEstado = Color(0xFFE44747)
private val RojoFondo = Color(0xFFFFECEC)

data class PublicacionHomeUi(
    val nombre: String,
    val categoria: String,
    val lugar: String,
    val fecha: String,
    val tipo: String,
    val custodia: String?,
    val imagenRes: Int
)

@Composable
fun HomeScreen() {

    var busqueda by remember {
        mutableStateOf("")
    }

    var filtroSeleccionado by remember {
        mutableStateOf("Todos")
    }

    val publicaciones = listOf(
        PublicacionHomeUi(
            nombre = "AirPods Pro",
            categoria = "Tecnología",
            lugar = "Biblioteca Central",
            fecha = "29 Sep 2026",
            tipo = "ENCONTRADO",
            custodia = "En custodia - Biblioteca",
            imagenRes = R.drawable.airpods_pro
        ),
        PublicacionHomeUi(
            nombre = "Mochila negra",
            categoria = "Accesorios",
            lugar = "Bloque B",
            fecha = "28 Sep 2026",
            tipo = "PERDIDO",
            custodia = null,
            imagenRes = R.drawable.mochila_negra
        ),
        PublicacionHomeUi(
            nombre = "Carnet universitario",
            categoria = "Documentos",
            lugar = "Cafetería",
            fecha = "27 Sep 2026",
            tipo = "ENCONTRADO",
            custodia = "Con hallador",
            imagenRes = R.drawable.carnet_estudiante
        )
    )

    val publicacionesFiltradas = publicaciones.filter { publicacion ->

        val coincideFiltro =
            filtroSeleccionado == "Todos" ||
                    publicacion.tipo == filtroSeleccionado.uppercase()

        val coincideBusqueda =
            busqueda.isBlank() ||
                    publicacion.nombre.contains(busqueda, ignoreCase = true) ||
                    publicacion.lugar.contains(busqueda, ignoreCase = true)

        coincideFiltro && coincideBusqueda
    }

    Scaffold(
        containerColor = FondoApp,
        bottomBar = {
            BarraNavegacionInferior()
        }
    ) { innerPadding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(
                start = 18.dp,
                end = 18.dp,
                top = 20.dp,
                bottom = 24.dp
            ),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            item {
                EncabezadoHome()
            }

            item {
                BuscadorHome(
                    texto = busqueda,
                    onTextoChange = {
                        busqueda = it
                    }
                )
            }

            item {
                FiltrosHome(
                    seleccionado = filtroSeleccionado,
                    onSeleccionar = {
                        filtroSeleccionado = it
                    }
                )
            }

            item {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Column {

                        Text(
                            text = "Publicaciones recientes",
                            fontSize = 21.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextoPrincipal
                        )

                        Text(
                            text = "Objetos reportados por la comunidad",
                            fontSize = 13.sp,
                            color = TextoSecundario
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = "Ver todas",
                        color = AzulPrincipal,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            items(publicacionesFiltradas) { publicacion ->

                TarjetaPublicacion(
                    publicacion = publicacion
                )
            }

            if (publicacionesFiltradas.isEmpty()) {

                item {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 45.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Box(
                            modifier = Modifier
                                .size(55.dp)
                                .clip(CircleShape)
                                .background(AzulSuave),
                            contentAlignment = Alignment.Center
                        ) {

                            Text(
                                text = "?",
                                color = AzulPrincipal,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = "No encontramos publicaciones",
                            color = TextoPrincipal,
                            fontWeight = FontWeight.SemiBold
                        )

                        Text(
                            text = "Intenta con otra búsqueda",
                            color = TextoSecundario,
                            fontSize = 13.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun EncabezadoHome() {

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(
            modifier = Modifier.weight(1f)
        ) {

            Text(
                text = "Objetos Perdidos",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = TextoPrincipal
            )

            Spacer(modifier = Modifier.height(3.dp))

            Text(
                text = "Encuentra lo que estás buscando",
                fontSize = 14.sp,
                color = TextoSecundario
            )
        }

        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(AzulSuave),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = "S",
                color = AzulPrincipal,
                fontWeight = FontWeight.Bold,
                fontSize = 19.sp
            )
        }
    }
}

@Composable
private fun BuscadorHome(
    texto: String,
    onTextoChange: (String) -> Unit
) {

    OutlinedTextField(
        value = texto,
        onValueChange = onTextoChange,
        modifier = Modifier.fillMaxWidth(),
        leadingIcon = {

            Text(
                text = "⌕",
                color = TextoSecundario,
                fontSize = 25.sp
            )
        },
        placeholder = {

            Text(
                text = "Buscar objeto o lugar...",
                color = TextoSecundario
            )
        },
        singleLine = true,
        shape = RoundedCornerShape(18.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = AzulPrincipal,
            unfocusedBorderColor = Color(0xFFE2E2EA),
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White
        )
    )
}

@Composable
private fun FiltrosHome(
    seleccionado: String,
    onSeleccionar: (String) -> Unit
) {

    val filtros = listOf(
        "Todos",
        "Perdido",
        "Encontrado"
    )

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(9.dp)
    ) {

        filtros.forEach { filtro ->

            val activo = seleccionado == filtro

            Surface(
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        onSeleccionar(filtro)
                    },
                color = if (activo) AzulPrincipal else Color.White,
                shape = RoundedCornerShape(18.dp),
                shadowElevation = if (activo) 2.dp else 0.dp
            ) {

                Box(
                    modifier = Modifier.padding(
                        vertical = 10.dp,
                        horizontal = 8.dp
                    ),
                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        text = filtro,
                        color = if (activo) Color.White else TextoSecundario,
                        fontSize = 13.sp,
                        fontWeight = if (activo) {
                            FontWeight.SemiBold
                        } else {
                            FontWeight.Medium
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun TarjetaPublicacion(
    publicacion: PublicacionHomeUi
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
    ) {

        Column {

            ImagenObjeto(
                publicacion = publicacion
            )

            Column(
                modifier = Modifier.padding(17.dp)
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Top
                ) {

                    Column(
                        modifier = Modifier.weight(1f)
                    ) {

                        Text(
                            text = publicacion.nombre,
                            fontSize = 19.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextoPrincipal,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )

                        Spacer(modifier = Modifier.height(3.dp))

                        Text(
                            text = publicacion.categoria,
                            fontSize = 13.sp,
                            color = AzulPrincipal,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    EtiquetaEstado(
                        tipo = publicacion.tipo
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                InformacionFila(
                    simbolo = "⌖",
                    texto = publicacion.lugar
                )

                Spacer(modifier = Modifier.height(8.dp))

                InformacionFila(
                    simbolo = "▣",
                    texto = publicacion.fecha
                )

                if (publicacion.custodia != null) {

                    Spacer(modifier = Modifier.height(12.dp))

                    Surface(
                        color = AzulSuave,
                        shape = RoundedCornerShape(12.dp)
                    ) {

                        Text(
                            text = publicacion.custodia,
                            modifier = Modifier.padding(
                                horizontal = 11.dp,
                                vertical = 7.dp
                            ),
                            color = AzulPrincipal,
                            fontWeight = FontWeight.Medium,
                            fontSize = 12.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = if (publicacion.tipo == "ENCONTRADO") {
                            "Objeto encontrado"
                        } else {
                            "Objeto perdido"
                        },
                        color = TextoSecundario,
                        fontSize = 12.sp
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = "Ver detalles",
                        color = AzulPrincipal,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.width(5.dp))

                    Text(
                        text = "›",
                        color = AzulPrincipal,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
private fun ImagenObjeto(
    publicacion: PublicacionHomeUi
) {

    Image(
        painter = painterResource(
            id = publicacion.imagenRes
        ),
        contentDescription = publicacion.nombre,
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp),
        contentScale = ContentScale.Crop
    )
}

@Composable
private fun EtiquetaEstado(
    tipo: String
) {

    val encontrado = tipo == "ENCONTRADO"

    Surface(
        color = if (encontrado) VerdeFondo else RojoFondo,
        shape = RoundedCornerShape(20.dp)
    ) {

        Text(
            text = if (encontrado) {
                "Encontrado"
            } else {
                "Perdido"
            },
            modifier = Modifier.padding(
                horizontal = 10.dp,
                vertical = 6.dp
            ),
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = if (encontrado) {
                VerdeEstado
            } else {
                RojoEstado
            }
        )
    }
}

@Composable
private fun InformacionFila(
    simbolo: String,
    texto: String
) {

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(26.dp)
                .clip(CircleShape)
                .background(AzulSuave),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = simbolo,
                color = AzulPrincipal,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = texto,
            color = TextoSecundario,
            fontSize = 13.sp
        )
    }
}

@Composable
private fun BarraNavegacionInferior() {

    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 8.dp
    ) {

        ItemNavegacion(
            simbolo = "⌂",
            texto = "Inicio",
            seleccionado = true
        )

        ItemNavegacion(
            simbolo = "⌕",
            texto = "Buscar",
            seleccionado = false
        )

        ItemNavegacion(
            simbolo = "+",
            texto = "Reportar",
            seleccionado = false,
            destacado = true
        )

        ItemNavegacion(
            simbolo = "◎",
            texto = "Coincid.",
            seleccionado = false
        )

        ItemNavegacion(
            simbolo = "●",
            texto = "Perfil",
            seleccionado = false
        )
    }
}

@Composable
private fun androidx.compose.foundation.layout.RowScope.ItemNavegacion(
    simbolo: String,
    texto: String,
    seleccionado: Boolean,
    destacado: Boolean = false
) {

    NavigationBarItem(
        selected = seleccionado,
        onClick = {},
        icon = {

            if (destacado) {

                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .clip(CircleShape)
                        .background(AzulPrincipal),
                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        text = simbolo,
                        color = Color.White,
                        fontSize = 27.sp,
                        fontWeight = FontWeight.Normal
                    )
                }

            } else {

                Text(
                    text = simbolo,
                    color = if (seleccionado) {
                        AzulPrincipal
                    } else {
                        TextoSecundario
                    },
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        },
        label = {

            Text(
                text = texto,
                fontSize = 10.sp,
                color = if (seleccionado) {
                    AzulPrincipal
                } else {
                    TextoSecundario
                }
            )
        }
    )
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    widthDp = 390,
    heightDp = 844
)
@Composable
fun HomeScreenPreview() {

    ObjetosPerdidosTheme {
        HomeScreen()
    }
}