@WholeBoxesFeature
Feature: Strix Auto

  Background:
    Given Genera una solicitud para el servicio "STRIX_AUTO" y espera 60 segundos
#    Given un turno de Instalacion para la solucion "STRIX_AUTO"
    When Ingresa a la pagina de Boxes
    And Selecciona opcion login
    And Ingresa credenciales para 'ale' y selecciona ingresar
    Then Valida que se ecuentra en correctamente logueado

  @StrixAuto
  Scenario: Instalacion de la solucion Strix Auto
    Given un turno de Instalacion para la solucion "STRIX_AUTO"
    When se filtran tareas por vehiculo
#    When Selecciona un vehiculo con dominio 'SS165SZ'
    And se accede a la tarea de: "Recepción"
    And se accede a la tarea de: "Instalación"
    And Selecciona la opcion 'GPS'
    And selecciona empresa 'Oleiros'
    And obtener nro de serie del GPS para "STRIX_AUTO"
    And ingresa numero de serie de 'GPS' valido
    And completar Ubicacion 'Piso' y Posicion 'Piso conductor'
    And valida que 'Recupero Vehicular' tenga tilde verde
    And edita el kilometraje
    And valida el numero de serie del GPS en el resumen
    And finaliza la instalacion
    And se filtran tareas por vehiculo
    And se accede a la tarea de: "Verificación"
    And se registran los datos de la gestion operativa con contrato: "Oleiros" y producto: "STRIX"
    And se filtran tareas por vehiculo
    And se accede a la tarea de: "Control de SDS"
    And se registran los datos de Cliente por cia, tipo de cliente: "Cliente por Cia de Seguro", condición impositiva: "Consumidor Final"
    And se registran los datos del Identificable con uso: "41 - TRANSPORTE", color: "8 - AZUL"
    And se registran los datos de Facturacion
    And se registran los datos de Operaciones
    And imprimir SDS
    And se finaliza la tarea de Control SDS
    And el cliente tiene SDS firmada electronicamente
    And se filtran tareas por vehiculo
    And se accede a la tarea de: "Cierre"
    And Realizar Cierre
#    When se prueban asserts con Dominio: "ZZ131JP" Solicitud ID: "482459289" DNI: "18639754" GPS: "900000000000155" VLU: ""
    Then se imprimen los datos de la prueba y se espera 20 segundos
    And se verifica en Calipso que el equipo "GPS" haya quedado asociado al vehiculo
    And se verifica en Calipso el plan "PL_STRIXAUTO" y el servicio "SE_RECUPERO". Company ID "COM_OLEIROS"
    And se verifica en Calipso el plan "PL_STRIXAUTO" y el servicio "SE_VISUALIZACION". Company ID "COM_OLEIROS"
    And se verifican en Calipso los datos del vehiculo. Company ID "COM_OLEIROS"
    And se verifica en Vehiculos el alta del cliente: Company ID "COM_OLEIROS", Nombre y apellido del cliente "RATON", "PEREZ"
    And se verifica en Vehiculos la relación del vehiculo y el equipo "GPS"
    And se verifica en Plataforma el consumo de la partida instalada. Dispositivo "GPS"
    And se verifica en ATyC la generacion de la SDS y el envio por mail
    And se verifica en Turnos el cierre de la solicitud
    And se verifica en Boxes el estado "Completed" de la tarea "Venta - Recepción"
    And se verifica en Boxes el estado "Completed" de la tarea "Venta - Control de SDS"
    And se verifica en Boxes el estado "Completed" de la tarea "Venta - Instalación"
    And se verifica en Boxes el estado "Completed" de la tarea "Venta - Verificación"
    And se verifica en Boxes el estado "Completed" de la tarea "Venta - Cierre"
    And se verifica en Magenta el servicio. Nombre "PEREZ, RATON" y servicio activo
