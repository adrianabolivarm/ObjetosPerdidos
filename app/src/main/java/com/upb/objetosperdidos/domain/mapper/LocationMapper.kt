package com.upb.objetosperdidos.domain.mapper

import com.upb.objetosperdidos.data.remote.dto.LocationDto
import com.upb.objetosperdidos.domain.model.Ubicacion

fun LocationDto.toDomain(): Ubicacion {
    return Ubicacion(
        id = this.placeId,
        nombreDireccion = this.displayName,
        latitud = this.lat.toDoubleOrNull() ?: 0.0,
        longitud = this.lon.toDoubleOrNull() ?: 0.0
    )
}
