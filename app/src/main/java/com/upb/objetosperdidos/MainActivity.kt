package com.upb.objetosperdidos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.upb.objetosperdidos.ui.screens.CustodianDashboardScreen
import com.upb.objetosperdidos.ui.screens.CustodyScreen
import com.upb.objetosperdidos.ui.screens.DeliveryPointDetailScreen
import com.upb.objetosperdidos.ui.screens.DeliveryPointsScreen
import com.upb.objetosperdidos.ui.screens.HomeScreen
import com.upb.objetosperdidos.ui.screens.MatchDetailScreen
import com.upb.objetosperdidos.ui.screens.MatchesScreen
import com.upb.objetosperdidos.ui.screens.MyReportsScreen
import com.upb.objetosperdidos.ui.screens.PointsBenefitsScreen
import com.upb.objetosperdidos.ui.screens.ProfileScreen
import com.upb.objetosperdidos.ui.screens.PublicationDetailScreen
import com.upb.objetosperdidos.ui.screens.RecoveryRequestScreen
import com.upb.objetosperdidos.ui.screens.ReportObjectScreen
import com.upb.objetosperdidos.ui.screens.ReturnProcessScreen
import com.upb.objetosperdidos.ui.theme.ObjetosPerdidosTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            ObjetosPerdidosTheme {
                MainAppNavigator()
            }
        }
    }
}

@Composable
fun MainAppNavigator() {

    var pantallaActual by remember {
        mutableStateOf("MENU")
    }

    if (pantallaActual != "MENU") {
        BackHandler {
            pantallaActual = "MENU"
        }
    }

    when (pantallaActual) {

        "MENU" -> {
            PantallasMenu(
                onPantallaSeleccionada = {
                    pantallaActual = it
                }
            )
        }

        "HOME" -> HomeScreen()

        "DETALLE_PUBLICACION" -> PublicationDetailScreen()

        "REPORTAR" -> ReportObjectScreen()

        "MIS_REPORTES" -> MyReportsScreen()

        "COINCIDENCIAS" -> MatchesScreen()

        "DETALLE_COINCIDENCIA" -> MatchDetailScreen(
            onBackClick = {
                pantallaActual = "MENU"
            },
            onConfirmClick = {},
            onRejectClick = {}
        )

        "SOLICITUD" -> RecoveryRequestScreen()

        "CUSTODIA" -> CustodyScreen(
            onBackClick = {
                pantallaActual = "MENU"
            },
            onViewDeliveryPointsClick = {
                pantallaActual = "PUNTOS_ENTREGA"
            },
            onCancelReportClick = {}
        )

        "PUNTOS_ENTREGA" -> DeliveryPointsScreen(
            onBackClick = {
                pantallaActual = "MENU"
            },
            onPointClick = {
                pantallaActual = "DETALLE_PUNTO"
            }
        )

        "DETALLE_PUNTO" -> DeliveryPointDetailScreen(
            onBackClick = {
                pantallaActual = "MENU"
            },
            onRequestDeliveryClick = {
                pantallaActual = "DEVOLUCION"
            }
        )

        "DEVOLUCION" -> ReturnProcessScreen()

        "PUNTOS_BENEFICIOS" -> PointsBenefitsScreen()

        "PERFIL" -> ProfileScreen()

        "ENCARGADO" -> CustodianDashboardScreen()
    }
}

@Composable
fun PantallasMenu(
    onPantallaSeleccionada: (String) -> Unit
) {

    val pantallas = listOf(
        "HOME" to "1. Inicio / Publicaciones",
        "DETALLE_PUBLICACION" to "2. Detalle de publicación",
        "REPORTAR" to "3. Reportar objeto",
        "MIS_REPORTES" to "4. Mis reportes",
        "COINCIDENCIAS" to "5. Coincidencias",
        "DETALLE_COINCIDENCIA" to "6. Detalle de coincidencia",
        "SOLICITUD" to "7. Solicitud de recuperación",
        "CUSTODIA" to "8. Custodia",
        "PUNTOS_ENTREGA" to "9. Puntos de entrega",
        "DETALLE_PUNTO" to "10. Detalle de punto de entrega",
        "DEVOLUCION" to "11. Proceso de devolución",
        "PUNTOS_BENEFICIOS" to "12. Puntos y beneficios",
        "PERFIL" to "13. Perfil",
        "ENCARGADO" to "14. Panel del encargado"
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 40.dp)
        ) {

            Text(
                text = "Objetos Perdidos",
                modifier = Modifier.padding(horizontal = 20.dp),
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Pantallas del proyecto",
                modifier = Modifier.padding(
                    start = 20.dp,
                    end = 20.dp,
                    top = 4.dp,
                    bottom = 16.dp
                ),
                fontSize = 15.sp
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(
                    start = 20.dp,
                    end = 20.dp,
                    bottom = 24.dp
                ),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                items(pantallas) { pantalla ->

                    Button(
                        onClick = {
                            onPantallaSeleccionada(pantalla.first)
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        Text(
                            text = pantalla.second,
                            modifier = Modifier.padding(vertical = 5.dp)
                        )
                    }
                }
            }
        }
    }
}