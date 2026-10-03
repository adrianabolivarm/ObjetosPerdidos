package com.upb.objetosperdidos.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.upb.objetosperdidos.ui.theme.ObjetosPerdidosTheme

@Composable
fun DeliveryPointsScreen() {
    Text(text = "Puntos de Entrega")
}

@Preview(showBackground = true)
@Composable
fun DeliveryPointsScreenPreview() {
    ObjetosPerdidosTheme {
        DeliveryPointsScreen()
    }
}