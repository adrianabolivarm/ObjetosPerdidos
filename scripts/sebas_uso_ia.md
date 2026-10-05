# Uso de IA - Sebastián Vargas

Herramienta utilizada: ChatGPT.

Se utilizó IA como apoyo para:

- estructurar y desarrollar interfaces en Jetpack Compose;
- organizar clases y enums del modelo de dominio;
- revisar errores de Kotlin y compilación;
- apoyar el manejo de Git y la rama de integración;
- generar prompts para algunas imágenes de ejemplo usadas en las pantallas.

## Pantallas trabajadas

- HomeScreen
- PublicationDetailScreen
- ReportObjectScreen
- MyReportsScreen
- MatchesScreen
- ProfileScreen

## Clases trabajadas

- ObjetoReportado
- Publicacion
- Custodia
- Estudiante
- Coincidencia
- SolicitudRecuperacion
- MovimientoPuntos
- Beneficio
- Canje
- Devolucion
- PuntoEntrega
- Sede
- Encargado

El código generado con apoyo de IA fue revisado y probado antes de incorporarlo al proyecto.

La compilación final fue verificada con:

`./gradlew assembleDebug`

Resultado: `BUILD SUCCESSFUL`

En esta etapa no se implementaron funcionalidades completas como base de datos, autenticación o backend; el trabajo se centró en interfaces y clases del dominio.
