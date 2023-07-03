@WholeBoxesFeature
Feature: Estandar + VLU

  Background:
    Given Genera una solicitud para el servicio "ESTANDAR" y espera 60 segundos
    #Given un turno de Instalacion para la solucion "ESTANDAR"
    When Ingresa a la pagina de Boxes
    And Selecciona opcion login
    And Ingresa credenciales para 'ale' y selecciona ingresar
    Then Valida que se ecuentra en correctamente logueado

  @Estandar
  Scenario: Instalacion de la solucion 'Estandar' con servicio adicional Recupero VLU
    Given un turno de Instalacion para la solucion "ESTANDAR"
    When se filtran tareas por vehiculo
    #When Selecciona un vehiculo con dominio 'ZZ580XG'

    And se accede a la tarea de: "Recepción"
    And se accede a la tarea de: "Instalación"

    And se agrega el servicio "Recupero VLU"
    And Selecciona la opcion 'GPS'
    And selecciona empresa 'Oleiros'
    And obtener nro de serie del GPS para "Estandar"
    And ingresa numero de serie de 'GPS' valido
    And completar Ubicacion 'Piso' y Posicion 'Piso conductor'
    And Selecciona la opcion 'VLU'
    And obtener nro de serie del VLU para "ESTANDAR"
    And ingresa numero de serie de 'VLU' valido
    And agregar servicio "Recupero VLU"
    And completar Ubicacion 'Piso' y Posicion 'Piso conductor'

    And valida que 'Monitoreo Vehicular' tenga tilde verde
    And valida que 'Recupero VLU' tenga tilde verde
    And edita el kilometraje
    And finaliza la instalacion sin confirmar
    And se filtran tareas por vehiculo
    And se accede a la tarea de: "Verificación"
    And se registran los datos de la gestion operativa con contrato: "Oleiros" y producto: "STD - MASIVO"
    And se filtran tareas por vehiculo
    And se accede a la tarea de: "Control de SDS"
    And se registran los datos de Cliente, tipo de cliente: "Particular", condición impositiva: "Consumidor Final"
    And se registran los datos del Identificable con uso: "41 - TRANSPORTE", color: "8 - AZUL"
    And se registran los datos de Facturacion
    And se registran los datos de GIS
    And se registran los datos de Operaciones
    And imprimir SDS
    And se finaliza la tarea de Control SDS
    And el cliente tiene SDS firmada electronicamente
    And se filtran tareas por vehiculo
    And se accede a la tarea de: "Cierre"
    And Realizar Cierre
    #When se prueban asserts con Dominio: "ZZ667AK" Solicitud ID: "482550685" DNI: "04051308" GPS: "900100000000231" VLU: "0DC8AB5"
    Then se imprimen los datos de la prueba y se espera 20 segundos
    And se verifica en Calipso que el equipo "GPS" haya quedado asociado al vehiculo
    And se verifica en Calipso que el equipo "VLU" haya quedado asociado al vehiculo
    And se verifica en Calipso el plan "PL_ESTANDAR" y el servicio "SE_MONITOREO". Company ID "COM_OLEIROS"
    And se verifica en Calipso el plan "PL_ESTANDAR_RECUPEROVLU" y el servicio "SE_RECUPERO". Company ID "COM_OLEIROS"
    And se verifican en Calipso los datos del vehiculo. Company ID "COM_OLEIROS"
    And se verifica en GIS la entidad del vehículo y la entidad del equipo
    And se verifica en Vehiculos el alta del cliente: Company ID "COM_OLEIROS", Nombre y apellido del cliente "RATON", "PEREZ"
    And se verifica en Vehiculos la relación del vehiculo y el equipo "GPS"
    And se verifica en Vehiculos la relación del vehiculo y el equipo "VLU"
    And se verifica en Plataforma el consumo de la partida instalada. Dispositivo "GPS"
#    And se verifica en ATyC la generacion de la SDS y el envio por mail
    And se verifica en Turnos el cierre de la solicitud
    And se verifica en Boxes el estado "Completed" de la tarea "Venta - Recepción"
    And se verifica en Boxes el estado "Completed" de la tarea "Venta - Control de SDS"
    And se verifica en Boxes el estado "Completed" de la tarea "Venta - Instalación"
    And se verifica en Boxes el estado "Completed" de la tarea "Venta - Verificación"
    And se verifica en Boxes el estado "Completed" de la tarea "Venta - Cierre"