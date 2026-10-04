package com.upb.objetosperdidos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.upb.objetosperdidos.ui.screens.CustodianDashboardScreen
import com.upb.objetosperdidos.ui.screens.PointsBenefitsScreen
import com.upb.objetosperdidos.ui.screens.RecoveryRequestScreen
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
        mutableStateOf("SOLICITUD")
    }

    Scaffold(
        bottomBar = {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = Color.White,
                shadowElevation = 8.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 6.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    NavTabButton(
                        label = "Solicitud",
                        selected = pantallaActual == "SOLICITUD",
                        onClick = { pantallaActual = "SOLICITUD" }
                    )

                    NavTabButton(
                        label = "Devolución",
                        selected = pantallaActual == "DEVOLUCION",
                        onClick = { pantallaActual = "DEVOLUCION" }
                    )

                    NavTabButton(
                        label = "Puntos",
                        selected = pantallaActual == "PUNTOS",
                        onClick = { pantallaActual = "PUNTOS" }
                    )

                    NavTabButton(
                        label = "Encargado",
                        selected = pantallaActual == "ENCARGADO",
                        onClick = { pantallaActual = "ENCARGADO" }
                    )
                }
            }
        }
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (pantallaActual) {
                "SOLICITUD" -> RecoveryRequestScreen()
                "DEVOLUCION" -> ReturnProcessScreen()
                "PUNTOS" -> PointsBenefitsScreen()
                "ENCARGADO" -> CustodianDashboardScreen()
            }
        }
    }
}

@Composable
private fun NavTabButton(
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {

    Surface(
        modifier = Modifier.clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        color = if (selected) Color(0xFF1689FF) else Color.Transparent
    ) {
        Box(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = label,
                color = if (selected) Color.White else Color(0xFF777783),
                fontSize = 11.sp,
                fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
            )
        }
    }
}
