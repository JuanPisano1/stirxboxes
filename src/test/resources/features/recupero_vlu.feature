@WholeBoxesFeature
Feature: Recupero VLU

  Background:
    Given Genera una solicitud para el servicio "Recupero" y espera 60 segundos
    #Given un turno de Instalacion para la solucion "RECUPERO"
    When Ingresa a la pagina de Boxes
    And Selecciona opcion login
    And Ingresa credenciales para 'ale' y selecciona ingresar
    Then Valida que se ecuentra en correctamente logueado

  @RecuperoVlu
  Scenario: Instalacion de la solucion 'Recupero VLU' por compania de seguros
    Given un turno de Instalacion para la solucion "Recupero"
    When se filtran tareas por vehiculo
   #When Selecciona un vehiculo con dominio 'ZZ800SU'
    And se accede a la tarea de: "Recepción"
    And se accede a la tarea de: "Instalación"
    And Selecciona la opcion 'VLU'
    And obtener nro de serie del VLU para "Recupero"
    And ingresa numero de serie de 'VLU' valido
    And agregar servicio "Recupero Vehicular"
    And completar Ubicacion 'Asiento' y Posicion 'Debajo asiento conductor'
    And valida que 'Recupero Vehicular' tenga tilde verde
    And edita el kilometraje
    And finaliza la instalacion
    And se filtran tareas por vehiculo
    And se accede a la tarea de: "Verificación"
    And se registran los datos de la gestion operativa con contrato: "Empresa" y producto: "RECUPERO"
    And se filtran tareas por vehiculo
    And se accede a la tarea de: "Control de SDS"
    And se registran los datos de Cliente, tipo de cliente: "Cliente por Cia de Seguro", condición impositiva: "Consumidor Final"
    And se registran los datos del Identificable con uso: "41 - TRANSPORTE", color: "8 - AZUL"
    And se registran los datos de Facturacion
    And se registran los datos de Operaciones
    And imprimir SDS
    And se finaliza la tarea de Control SDS
    And el cliente tiene SDS firmada electronicamente
    And se filtran tareas por vehiculo
    And se accede a la tarea de: "Cierre"
    And Realizar Cierre
 # When se prueban asserts con Dominio: "ZZ800SU" Solicitud ID: "482561265" DNI: "14061712" GPS: "" VLU: "99AA170"
    Then se imprimen los datos de la prueba y se espera 20 segundos
    And se verifica en Calipso que el equipo "VLU" haya quedado asociado al vehiculo
    And se verifica en Calipso el plan "PL_RECUPERO" y el servicio "SE_RECUPERO". Company ID "COM_CARSECURITY"
    And se verifican en Calipso los datos del vehiculo. Company ID "COM_CARSECURITY"
    And se verifica en Vehiculos el alta del cliente: Company ID "COM_CARSECURITY", Nombre y apellido del cliente "RATON", "PEREZ"
    And se verifica en Vehiculos la relación del vehiculo y el equipo "VLU"
    And se verifica en Plataforma el consumo de la partida instalada. Dispositivo "VLU"
    And se verifica en ATyC la generacion de la SDS y el envio por mail
    And se verifica en Turnos el cierre de la solicitud
    And se verifica en Boxes el estado "Completed" de la tarea "Venta - Recepción"
    And se verifica en Boxes el estado "Completed" de la tarea "Venta - Control de SDS"
    And se verifica en Boxes el estado "Completed" de la tarea "Venta - Instalación"
    And se verifica en Boxes el estado "Completed" de la tarea "Venta - Verificación"
    And se verifica en Boxes el estado "Completed" de la tarea "Venta - Cierre"