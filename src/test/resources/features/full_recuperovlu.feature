@WholeBoxesFeature
Feature: Full con Recupero Vlu
  Background:
   Given Genera una solicitud para el servicio "FULL" y espera 60 segundos
#   Given un turno de Instalacion para la solucion "FULL"
    When Ingresa a la pagina de Boxes
    And Selecciona opcion login
   And Ingresa credenciales para 'ale' y selecciona ingresar
   Then Valida que se ecuentra en correctamente logueado

  @FullRecuperoVlu
 Scenario: Instalacion de solucion Full con Recupero Vlu
  Given un turno de Instalacion para la solucion "Full"
    When se filtran tareas por vehiculo
   #When Selecciona un vehiculo con dominio 'ZZ294XX'
    And se accede a la tarea de: "Recepción"
    And se filtran tareas por vehiculo
    And se accede a la tarea de: "Instalación"
    And se agrega el servicio Gps "Recupero GPS"
    And se agrega el servicio "Recupero Vlu"
    And Selecciona la opcion 'GPS'
    And selecciona empresa 'Oleiros'
    And obtener nro de serie del GPS para "Full"
    And ingresa numero de serie de 'GPS' valido
    And completar Ubicacion 'Asiento' y Posicion 'Debajo asiento conductor'

   And Selecciona la opcion 'VLU'
   And obtener nro de serie del VLU para "Recupero"
   And ingresa numero de serie de 'VLU' valido
   And agregar servicio "Recupero Vlu"
   And completar Ubicacion 'Asiento' y Posicion 'Debajo asiento conductor'


    #And valida que 'Monitoreo Vehicular' tenga tilde verde
    And valida que 'Recupero Vlu' tenga tilde verde
    And edita el kilometraje
  #  And valida el numero de serie del GPS en el resumen
    And finaliza la instalacion
    And se filtran tareas por vehiculo
    And se accede a la tarea de: "Verificación"
    And se registran los datos de la gestion operativa con contrato: "Oleiros" y producto: "FULL"
    And se filtran tareas por vehiculo
    And se accede a la tarea de: "Control de SDS"
    And se registran los datos de Cliente, tipo de cliente: "Particular", condición impositiva: "Consumidor Final"
    And se registran los datos del Identificable con uso: "41 - TRANSPORTE", color: "8 - AZUL"
    And se registran los datos de Facturacion
    And se registran los datos de Operaciones
    And se registran los datos de GIS
    And imprimir SDS
    And se finaliza la tarea de Control SDS
    And el cliente tiene SDS firmada electronicamente
    And se filtran tareas por vehiculo
    And se accede a la tarea de: "Cierre"
    And Realizar Cierre
   # When se prueban asserts con Dominio: "ZZ493KM" Solicitud ID: "482561492" DNI: "30061310" GPS: "900100000000299" VLU: ""
    Then se imprimen los datos de la prueba y se espera 20 segundos
    And se verifica en Calipso que el equipo "GPS" haya quedado asociado al vehiculo
    And se verifica en Calipso que el equipo "VLU" haya quedado asociado al vehiculo
    And se verifica en Calipso el plan "PL_FULL" y el servicio "SE_MONITOREO". Company ID "COM_OLEIROS"
    And se verifican en Calipso los datos del vehiculo. Company ID "COM_OLEIROS"
    And se verifica en Vehiculos el alta del cliente: Company ID "COM_OLEIROS", Nombre y apellido del cliente "RATON", "PEREZ"
    And se verifica en Vehiculos la relación del vehiculo y el equipo "GPS"
    And se verifica en Plataforma el consumo de la partida instalada. Dispositivo "GPS"
    And se verifica en Plataforma el consumo de la partida instalada. Dispositivo "VLU"
    And se verifica en Turnos el cierre de la solicitud
    And se verifica en Boxes el estado "Completed" de la tarea "Venta - Recepción"
    And se verifica en Boxes el estado "Completed" de la tarea "Venta - Control de SDS"
    And se verifica en Boxes el estado "Completed" de la tarea "Venta - Instalación"
    And se verifica en Boxes el estado "Completed" de la tarea "Venta - Verificación"
    And se verifica en Boxes el estado "Completed" de la tarea "Venta - Cierre"