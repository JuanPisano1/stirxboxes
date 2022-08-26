package locators;

import org.openqa.selenium.By;

public interface TaskPageLocators {
    By HOME_TITLE_LABEL=By.className("ui-panel-title");
    By TASK_SEARCH_FIELD=By.xpath("//input[@id='tareas:tareasPendientesDataTable:j_idt74:filter']");
    By TASK_DROPDOWN_FILTER_LIST=By.xpath("//div[contains(@class,'ui-selectcheckboxmenu-trigger')]");
    By COMBO_FILTRO_TAREA=By.xpath("//th[@id='tareas:tareasPendientesDataTable:j_idt68']//span[contains(@class, 'ui-icon ui-icon-triangle-1-s')]");
    By OPCION_COMBO_FILTRO_TAREA=By.xpath("//li//label[text()='%s']");
    By CERRAR_COMBO_FILTRO_TAREA=By.xpath("//div[@id='tareas:tareasPendientesDataTable:j_idt69_panel']//span[contains(@class,'ui-icon-circle-close')]");
    By TASK_OPTION_TYPE_RECEPTION=By.xpath("//div[@class='ui-chkbox ui-widget']/following-sibling::label [contains(text(),'Recep')]");
    By TASK_OPTION_TYPE_INSTALL=By.xpath("//div[@class='ui-chkbox ui-widget']/following-sibling::label [contains(text(),'Instal')]");
    By TASK_OPTION_TYPE_FILTER=By.xpath("//div[@class='ui-chkbox ui-widget']/following-sibling::label [contains(text(),'%s')]");
    By TASK_FIRST_ELEMENT=By.xpath("//tr[@data-ri='0'] //td/a[contains(text(),'Rece')]");
    By TASKS_DOMAIN_SELECTED=By.xpath("//tr[@data-ri='0'] //td/a[contains(text(),'%s')]"); //select service type from domain selected
    By TASK_FIRST_ELEMENT_DOMAIN=By.xpath("//tr[@data-ri='0']//td[contains(text(),'AUT-')]");
    By TASK_TABLE=By.className("ui-datatable-tablewrapper");
    By CLOSE_X_BTN_LIST=By.xpath("//span[contains(@class,'ui-icon-circle-close')]");
    By TASK_RECIBE_BTN=By.xpath("//form[@id='acciones']//button[@id='acciones:j_idt50']");
    By BUTTON_TOMAR_TAREA=By.xpath("//button/span[text()='Recibir' or text()='Tomar tarea']");
    By IFRAME=By.xpath("//div[contains(@class,'ui-dialog-content')]//iframe");
    By TASK_TAKE_BTN_OK=By.id("accionesTecnico:j_idt54");
    By COMBO_CONTRATO = By.id("gestionOperativa:contrato_label");
    By OPCION_CONTRATO = By.id("gestionOperativa:contrato_1");
    By COMBO_PRODUCTO = By.id("gestionOperativa:producto_label");
    By OPCION_PRODUCTO = By.id("gestionOperativa:producto_1");
    By BOTON_ACEPTAR_INSTALACION = By.id("botones:j_idt557");
    By EDITAR_DATOS_GENERALES = By.id("tabs:j_idt129:formDatosCliente:j_idt150");
    By TIPO_DE_CLIENTE = By.id("formDetalleCliente:clienteTipoCliente_label");
    By CLIENTE_POR_CIA_DE_SEGURO = By.id("formDetalleCliente:clienteTipoCliente_1");
    By CONDICION_IMPOSITIVA = By.id("formDetalleCliente:clienteCondicionImpositiva_label");
    By RESPONSABLE_INSCRIPTO = By.id("formDetalleCliente:clienteCondicionImpositiva_1");
    By GENERO = By.id("formDetalleCliente:genero_label");
    By OPCION_M = By.id("formDetalleCliente:genero_1");
    By GUARDAR_DATOS_GENERALES = By.id("acciones:j_idt62");
    By AGREGAR_TELEFONO = By.id("tabs:j_idt129:j_idt175:formTelefonos:telefonos:j_idt195");
    By TELEFONO_SIN_NORMALIZAR = By.id("tabs:j_idt129:j_idt175:formTelefonos:telefonosSinNormalizar");
    By TIPO_DE_CONTACTO = By.id("telefono:tipoContacto_label");
    By BOTON_TELEFONO = By.id("telefono:j_idt46");
    By NUMERO_ = By.xpath("//input[@id='j_idt29:numero']");
    By BOTON_NORMALIZAR = By.id("j_idt29:normalizar");
    By TILDE_VALIDAR = By.id("j_idt29:telefonosNormalizados:0:j_idt52");
    By GUARDAR_TELEFONOS = By.xpath("//button/span[text()='Guardar']");
    By IDENTIFICABLE = By.xpath("//li/a[text()='Identificable']");
    By EDITAR = By.id("tabs:j_idt200:formDatosSecundarios:j_idt233");
    By USO = By.id("formDatosSecundarios:uso_label");
    By DATO_SECUNDARIO_COMERCIAL = By.id("formDatosSecundarios:uso_6");
    By COLOR_SELECT = By.id("formDatosSecundarios:color_label");
    By GUARDAR_DATO_SECUNDARIO = By.id("acciones:j_idt71");
    By FACTURACION = By.xpath("//li/a[text()='Facturacion']");

    By GIS = By.xpath("//li/a[text()='GIS']");

    By EDITAR_USUARIO_GIS = By.xpath("//h2[text()='Nuevo Usuario']/following-sibling::button");

    By INPUT_LOGIN_USUARIO_GIS = By.xpath("//label[text()='Usuario Login']/following-sibling::input");

    By GUARDAR_DATOS_USUARIO_GIS = By.xpath("//button/span[contains(text(),'Guardar')]");
    By EDITAR_FORMA_DE_PAGO = By.id("tabs:facturacion:formaDePago:j_idt311");
    By CREAR_NUEVO_MEDIO_DE_PAGO = By.xpath("//button[@title='Crear nuevo']");
    By TIPO_FORMA_DE_PAGO = By.id("formaDePago:tipoFormaDePago_label");
    By SIN_MEDIO_DE_PAGO = By.id("formaDePago:tipoFormaDePago_3");
    By GUARDAR_FORMA_DE_PAGO = By.xpath("//button//span[text()='Guardar']");
    By OPERACIONAL = By.xpath("//li/a[text()='Operacional']");
    By AGREGAR_PERSONA_AUTORIZADA = By.id("tabs:j_idt436:formPersonaAutorizada:j_idt437");
    By NOMBRE = By.id("formDatosGenerales:nombre");
    By APELLIDO = By.id("formDatosGenerales:apellido");
    By TIPO_DE_IDENTIFICABLE = By.id("formDatosGenerales:tipoIdentificacion_label");
    By DNI = By.id("formDatosGenerales:tipoIdentificacion_2");
    By NUMERO_DE_IDENTIFICABLE = By.xpath("//input[@id='formDatosGenerales:numeroIdentificacion']");
    By RELACION_PERSONAL = By.id("formOperaciones:relacionPersonal_label");
    By CONDUCTOR = By.id("formOperaciones:relacionPersonal_1");
    By GUARDAR_PERSONA_AUTORIZADA = By.id("acciones:j_idt64");
    By TELEFONO_PERSONA_AUTORIZADA = By.id("tabs:j_idt436:formPersonaAutorizada:personasAutorizadasOperaciones:0:j_idt457");
    By AGREGAR_TELEFONO_PERSONA_AUTORIZADA = By.id("j_idt29:formTelefonos:telefonos:j_idt50");
    By CELULAR_TIPO_DE_CONTACTO = By.id("telefono:tipoContacto_2");
    By EDITAR_TELEFONO_CONTACTO = By.id("accionesEditarTelefonoContacto:j_idt59");
    By VOLVER = By.id("accionVolver:j_idt53");
    By IMPRIMIR_SDS = By.xpath("//button//span[text()='Imprimir SDS']");
    By FINALIZAR = By.xpath("//button[@id='botones:botonFinalizar']");
    By TOMAR_TAREA_CONTROL_SDS = By.id("j_idt35:acciones:j_idt39");
    By TOMAR_TAREA_CIERRE = By.id("j_idt35:acciones:j_idt39");
    By FINALIZAR_CIERRE = By.xpath("//button//span[text()='Finalizar']");
    By BOTON_SI_ACEPTAR_FIRMA = By.xpath("//button/span[text()='Si']");
    By FINALIZAR_C_CALIDAD = By.id("botones:j_idt166");
    By COMBO_RANDOM = By.id("j_idt131:j_idt132:gestionDeCalidadRandom");
    By OPCION_COMBO = By.xpath("//li[@data-label='%s']");
    By COMBO_CLAS1 = By.xpath( "//div[@id= 'j_idt131:j_idt132:gestionDeCalidadClasificacion1']");
    By OPCION_CLAS1 = By.xpath("//li[@id='j_idt131:j_idt132:gestionDeCalidadClasificacion1_1' and @data-label='Bueno']");
    By COMBO_CLAS2 = By.xpath( "//div[@id= 'j_idt131:j_idt132:gestionDeCalidadClasificacion2']");
    By OPCION_CLAS2 = By.xpath("//li[@id='j_idt131:j_idt132:gestionDeCalidadClasificacion2_1' and @data-label='%s']");

}
