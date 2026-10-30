package com.upb.objetosperdidos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
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

// Colores institucionales de la aplicación UPB
private val AzulNavegacion = Color(0xFF1689FF)
private val TextoInactivo = Color(0xFF777783)
private val FondoBarra = Color.White

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

// Destinos principales de la barra inferior
private enum class TabPrincipal {
    HOME,
    POINTS,
    REPORT,
    MATCHES,
    PROFILE
}

@Composable
fun MainAppNavigator() {

    // Estado del tab activo en la barra inferior
    var tabActivo by remember {
        mutableStateOf(TabPrincipal.HOME)
    }

    // Estado de sub-pantallas de detalle
    var subPantallaActiva by remember {
        mutableStateOf<String?>(null)
    }

    // Manejador del botón 'Atrás' físico o gesto del teléfono
    if (subPantallaActiva != null) {
        BackHandler {
            subPantallaActiva = null
        }
    } else if (tabActivo != TabPrincipal.HOME) {
        BackHandler {
            tabActivo = TabPrincipal.HOME
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            // Se muestra la barra de navegación en las pestañas principales
            if (subPantallaActiva == null) {
                AppBottomNavigationBar(
                    tabActivo = tabActivo,
                    onTabSeleccionado = { tab ->
                        subPantallaActiva = null
                        tabActivo = tab
                    }
                )
            }
        }
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            // Transición suave entre pantallas
            Crossfade(
                targetState = subPantallaActiva ?: tabActivo.name,
                animationSpec = tween(300),
                label = "PantallaCrossfade"
            ) { destino ->

                when (destino) {

                    // --- PESTAÑA 1: INICIO ---
                    TabPrincipal.HOME.name -> {
                        HomeScreen(
                            onPublicacionClick = {
                                subPantallaActiva = "DETALLE_PUBLICACION"
                            },
                            onProfileClick = {
                                tabActivo = TabPrincipal.PROFILE
                            }
                        )
                    }

                    // --- PESTAÑA 2: PUNTOS DE ENTREGA UPB ---
                    TabPrincipal.POINTS.name -> {
                        DeliveryPointsScreen(
                            onBackClick = {
                                tabActivo = TabPrincipal.HOME
                            },
                            onPointClick = {
                                subPantallaActiva = "DETALLE_PUNTO"
                            }
                        )
                    }

                    // --- PESTAÑA 3: REPORTAR OBJETO (Destacado) ---
                    TabPrincipal.REPORT.name -> {
                        ReportObjectScreen()
                    }

                    // --- PESTAÑA 4: COINCIDENCIAS ---
                    TabPrincipal.MATCHES.name -> {
                        MatchesScreen(
                            onMatchClick = {
                                subPantallaActiva = "DETALLE_COINCIDENCIA"
                            },
                            onBackClick = {
                                tabActivo = TabPrincipal.HOME
                            }
                        )
                    }

                    // --- PESTAÑA 5: PERFIL / MI CUENTA ---
                    TabPrincipal.PROFILE.name -> {
                        ProfileScreen(
                            onMyReportsClick = {
                                subPantallaActiva = "MIS_REPORTES"
                            },
                            onMatchesClick = {
                                tabActivo = TabPrincipal.MATCHES
                            },
                            onPointsBenefitsClick = {
                                subPantallaActiva = "PUNTOS_BENEFICIOS"
                            },
                            onCustodianDashboardClick = {
                                subPantallaActiva = "PANEL_ENCARGADO"
                            }
                        )
                    }

                    // --- SUB-PANTALLAS DE DETALLE (Flujo relacionado) ---
                    "DETALLE_PUBLICACION" -> {
                        PublicationDetailScreen(
                            onBackClick = {
                                subPantallaActiva = null
                            },
                            onRequestRecoveryClick = {
                                subPantallaActiva = "SOLICITUD_RECUPERACION"
                            }
                        )
                    }

                    "SOLICITUD_RECUPERACION" -> {
                        RecoveryRequestScreen()
                    }

                    "DETALLE_PUNTO" -> {
                        DeliveryPointDetailScreen(
                            onBackClick = {
                                subPantallaActiva = null
                            },
                            onRequestDeliveryClick = {
                                subPantallaActiva = "PROCESO_DEVOLUCION"
                            }
                        )
                    }

                    "CUSTODIA" -> {
                        CustodyScreen(
                            onBackClick = {
                                subPantallaActiva = null
                            },
                            onViewDeliveryPointsClick = {
                                subPantallaActiva = null
                                tabActivo = TabPrincipal.POINTS
                            },
                            onCancelReportClick = {
                                subPantallaActiva = null
                            }
                        )
                    }

                    "DETALLE_COINCIDENCIA" -> {
                        MatchDetailScreen(
                            onBackClick = {
                                subPantallaActiva = null
                            },
                            onConfirmClick = {
                                subPantallaActiva = "PROCESO_DEVOLUCION"
                            },
                            onRejectClick = {
                                subPantallaActiva = null
                            }
                        )
                    }

                    "PROCESO_DEVOLUCION" -> {
                        ReturnProcessScreen()
                    }

                    "PUNTOS_BENEFICIOS" -> {
                        PointsBenefitsScreen()
                    }

                    "MIS_REPORTES" -> {
                        MyReportsScreen()
                    }

                    "PANEL_ENCARGADO" -> {
                        CustodianDashboardScreen()
                    }
                }
            }
        }
    }
}

/**
 * Barra de Navegación Inferior Unificada para toda la aplicación.
 * Proporciona acceso rápido a los 5 módulos principales con diseño institucional UPB.
 */
@Composable
private fun AppBottomNavigationBar(
    tabActivo: TabPrincipal,
    onTabSeleccionado: (TabPrincipal) -> Unit
) {

    Surface(
        color = FondoBarra,
        shadowElevation = 12.dp
    ) {

        NavigationBar(
            containerColor = FondoBarra,
            tonalElevation = 0.dp
        ) {

            BottomNavItem(
                simbolo = "⌂",
                etiqueta = "Inicio",
                seleccionado = tabActivo == TabPrincipal.HOME,
                onClick = { onTabSeleccionado(TabPrincipal.HOME) }
            )

            BottomNavItem(
                simbolo = "⌖",
                etiqueta = "Puntos UPB",
                seleccionado = tabActivo == TabPrincipal.POINTS,
                onClick = { onTabSeleccionado(TabPrincipal.POINTS) }
            )

            BottomNavItem(
                simbolo = "+",
                etiqueta = "Reportar",
                seleccionado = tabActivo == TabPrincipal.REPORT,
                destacado = true,
                onClick = { onTabSeleccionado(TabPrincipal.REPORT) }
            )

            BottomNavItem(
                simbolo = "◎",
                etiqueta = "Coincid.",
                seleccionado = tabActivo == TabPrincipal.MATCHES,
                onClick = { onTabSeleccionado(TabPrincipal.MATCHES) }
            )

            BottomNavItem(
                simbolo = "●",
                etiqueta = "Perfil",
                seleccionado = tabActivo == TabPrincipal.PROFILE,
                onClick = { onTabSeleccionado(TabPrincipal.PROFILE) }
            )
        }
    }
}

@Composable
private fun RowScope.BottomNavItem(
    simbolo: String,
    etiqueta: String,
    seleccionado: Boolean,
    destacado: Boolean = false,
    onClick: () -> Unit
) {

    NavigationBarItem(
        selected = seleccionado,
        onClick = onClick,
        colors = NavigationBarItemDefaults.colors(
            indicatorColor = if (destacado) Color.Transparent else Color(0xFFEAF4FF)
        ),
        icon = {

            if (destacado) {

                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(AzulNavegacion),
                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        text = simbolo,
                        color = Color.White,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Normal
                    )
                }

            } else {

                Text(
                    text = simbolo,
                    color = if (seleccionado) AzulNavegacion else TextoInactivo,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        },
        label = {

            Text(
                text = etiqueta,
                fontSize = 10.sp,
                fontWeight = if (seleccionado) FontWeight.Bold else FontWeight.Medium,
                color = if (seleccionado) AzulNavegacion else TextoInactivo
            )
        }
    )
}