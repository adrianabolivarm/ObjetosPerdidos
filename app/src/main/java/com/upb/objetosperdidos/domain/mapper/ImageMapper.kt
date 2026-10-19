package com.upb.objetosperdidos.domain.mapper

import com.upb.objetosperdidos.data.remote.dto.ImgBBResponseDto
import com.upb.objetosperdidos.domain.model.ImagenSubida

fun ImgBBResponseDto.toDomain(): ImagenSubida? {
    val data = this.data ?: return null
    return ImagenSubida(
        id = data.id,
        urlPublica = data.displayUrl
    )
}
