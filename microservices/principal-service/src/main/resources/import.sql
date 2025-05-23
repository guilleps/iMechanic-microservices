-- ROLES
INSERT INTO role (role_name) VALUES ('WORKSHOP');
INSERT INTO role (role_name) VALUES ('CUSTOMER');
INSERT INTO role (role_name) VALUES ('MECHANIC');

-- SERVICIOS DE MANTENIMIENTO
INSERT INTO operation (name, type_service, service_status) VALUES ('Cambio de Aceite y Filtro', 'MANTENIMIENTO', 'EN_ESPERA');
INSERT INTO step (name, order_step, operation_id) VALUES ('Inicio', 1, 1);
INSERT INTO step (name, order_step, operation_id) VALUES ('Drenaje del aceite usado.', 2, 1);
INSERT INTO step (name, order_step, operation_id) VALUES ('Reemplazo del filtro de aceite.', 3, 1);
INSERT INTO step (name, order_step, operation_id) VALUES ('Llenado con aceite nuevo.', 4, 1);
INSERT INTO step (name, order_step, operation_id) VALUES ('Verificación de fugas.', 5, 1);
INSERT INTO step (name, order_step, operation_id) VALUES ('Fin', 6, 1);

INSERT INTO operation (name, type_service, service_status) VALUES ('Cambio de Filtros de Aire', 'MANTENIMIENTO', 'EN_ESPERA');
INSERT INTO step (name, order_step, operation_id) VALUES ('Inicio', 1, 2);
INSERT INTO step (name, order_step, operation_id) VALUES ('Localización y extracción del filtro de aire.', 2, 2);
INSERT INTO step (name, order_step, operation_id) VALUES ('Inspección del filtro usado.', 3, 2);
INSERT INTO step (name, order_step, operation_id) VALUES ('Instalación del nuevo filtro.', 4, 2);
INSERT INTO step (name, order_step, operation_id) VALUES ('Prueba de funcionamiento.', 5, 2);
INSERT INTO step (name, order_step, operation_id) VALUES ('Fin', 6, 2);

INSERT INTO operation (name, type_service, service_status) VALUES ('Cambio de Filtros de Combustible', 'MANTENIMIENTO', 'EN_ESPERA');
INSERT INTO step (name, order_step, operation_id) VALUES ('Inicio', 1, 3);
INSERT INTO step (name, order_step, operation_id) VALUES ('Localización y extracción del filtro de combustible.', 2, 3);
INSERT INTO step (name, order_step, operation_id) VALUES ('Inspección del filtro usado.', 3, 3);
INSERT INTO step (name, order_step, operation_id) VALUES ('Instalación del nuevo filtro.', 4, 3);
INSERT INTO step (name, order_step, operation_id) VALUES ('Prueba de funcionamiento.', 5, 3);
INSERT INTO step (name, order_step, operation_id) VALUES ('Fin', 6, 3);

INSERT INTO operation (name, type_service, service_status) VALUES ('Cambio de Bujías', 'MANTENIMIENTO', 'EN_ESPERA');
INSERT INTO step (name, order_step, operation_id) VALUES ('Inicio', 1, 4);
INSERT INTO step (name, order_step, operation_id) VALUES ('Extracción de las bujías usadas.', 2, 4);
INSERT INTO step (name, order_step, operation_id) VALUES ('Inspección de las bujías.', 3, 4);
INSERT INTO step (name, order_step, operation_id) VALUES ('Instalación de las nuevas bujías.', 4, 4);
INSERT INTO step (name, order_step, operation_id) VALUES ('Prueba de funcionamiento.', 5, 4);
INSERT INTO step (name, order_step, operation_id) VALUES ('Fin', 6, 4);

INSERT INTO operation (name, type_service, service_status) VALUES ('Alineación y Balanceo de Ruedas', 'MANTENIMIENTO', 'EN_ESPERA');
INSERT INTO step (name, order_step, operation_id) VALUES ('Inicio', 1, 5);
INSERT INTO step (name, order_step, operation_id) VALUES ('Inspección visual de las ruedas.', 2, 5);
INSERT INTO step (name, order_step, operation_id) VALUES ('Alineación de las ruedas.', 3, 5);
INSERT INTO step (name, order_step, operation_id) VALUES ('Balanceo de las ruedas.', 4, 5);
INSERT INTO step (name, order_step, operation_id) VALUES ('Prueba de manejo para verificar la alineación y el balanceo.', 5, 5);
INSERT INTO step (name, order_step, operation_id) VALUES ('Fin', 6, 5);

INSERT INTO operation (name, type_service, service_status) VALUES ('Revisión y Recarga del Sistema de Aire Acondicionado', 'MANTENIMIENTO', 'EN_ESPERA');
INSERT INTO step (name, order_step, operation_id) VALUES ('Inicio', 1, 6);
INSERT INTO step (name, order_step, operation_id) VALUES ('Inspección visual del sistema.', 2, 6);
INSERT INTO step (name, order_step, operation_id) VALUES ('Recarga del refrigerante.', 3, 6);
INSERT INTO step (name, order_step, operation_id) VALUES ('Comprobación de fugas.', 4, 6);
INSERT INTO step (name, order_step, operation_id) VALUES ('Prueba de funcionamiento.', 5, 6);
INSERT INTO step (name, order_step, operation_id) VALUES ('Fin', 6, 6);

INSERT INTO operation (name, type_service, service_status) VALUES ('Revisión del Sistema de Frenos', 'MANTENIMIENTO', 'EN_ESPERA');
INSERT INTO step (name, order_step, operation_id) VALUES ('Inicio', 1, 7);
INSERT INTO step (name, order_step, operation_id) VALUES ('Inspección visual de las pastillas y discos de freno.', 2, 7);
INSERT INTO step (name, order_step, operation_id) VALUES ('Comprobación del nivel de líquido de frenos.', 3, 7);
INSERT INTO step (name, order_step, operation_id) VALUES ('Ajuste o reemplazo de pastillas o discos si es necesario.', 4, 7);
INSERT INTO step (name, order_step, operation_id) VALUES ('Prueba de frenado.', 5, 7);
INSERT INTO step (name, order_step, operation_id) VALUES ('Fin', 6, 7);

INSERT INTO operation (name, type_service, service_status) VALUES ('Revisión y Mantenimiento de la Suspensión', 'MANTENIMIENTO', 'EN_ESPERA');
INSERT INTO step (name, order_step, operation_id) VALUES ('Inicio', 1, 8);
INSERT INTO step (name, order_step, operation_id) VALUES ('Inspección visual de los componentes de la suspensión.', 2, 8);
INSERT INTO step (name, order_step, operation_id) VALUES ('Ajuste o reemplazo de amortiguadores o resortes si es necesario.', 3, 8);
INSERT INTO step (name, order_step, operation_id) VALUES ('Lubricación de componentes.', 4, 8);
INSERT INTO step (name, order_step, operation_id) VALUES ('Prueba de manejo para verificar la suspensión.', 5, 8);
INSERT INTO step (name, order_step, operation_id) VALUES ('Fin', 6, 8);

INSERT INTO operation (name, type_service, service_status) VALUES ('Revisión y Mantenimiento del Sistema de Dirección', 'MANTENIMIENTO', 'EN_ESPERA');
INSERT INTO step (name, order_step, operation_id) VALUES ('Inicio', 1, 9);
INSERT INTO step (name, order_step, operation_id) VALUES ('Inspección visual de la dirección.', 2, 9);
INSERT INTO step (name, order_step, operation_id) VALUES ('Ajuste o reemplazo de piezas gastadas.', 3, 9);
INSERT INTO step (name, order_step, operation_id) VALUES ('Alineación de la dirección si es necesario.', 4, 9);
INSERT INTO step (name, order_step, operation_id) VALUES ('Prueba de manejo para verificar la dirección.', 5, 9);
INSERT INTO step (name, order_step, operation_id) VALUES ('Fin', 6, 9);

INSERT INTO operation (name, type_service, service_status) VALUES ('Revisión y Mantenimiento del Sistema de Escape', 'MANTENIMIENTO', 'EN_ESPERA');
INSERT INTO step (name, order_step, operation_id) VALUES ('Inicio', 1, 10);
INSERT INTO step (name, order_step, operation_id) VALUES ('Inspección visual del sistema de escape.', 2, 10);
INSERT INTO step (name, order_step, operation_id) VALUES ('Reparación de fugas o daños.', 3, 10);
INSERT INTO step (name, order_step, operation_id) VALUES ('Reemplazo de piezas gastadas.', 4, 10);
INSERT INTO step (name, order_step, operation_id) VALUES ('Prueba de funcionamiento y de emisiones.', 5, 10);
INSERT INTO step (name, order_step, operation_id) VALUES ('Fin', 6, 10);

-- SERVICIOS DE REPARACION
INSERT INTO operation (name, type_service, service_status) VALUES ('Reparación de Sistema de Frenos', 'REPARACION', 'EN_ESPERA');
INSERT INTO step (name, order_step, operation_id) VALUES ('Inicio', 1, 11);
INSERT INTO step (name, order_step, operation_id) VALUES ('Identificación y reemplazo de componentes dañados.', 2, 11);
INSERT INTO step (name, order_step, operation_id) VALUES ('Sangrado del sistema de frenos.', 3, 11);
INSERT INTO step (name, order_step, operation_id) VALUES ('Ajuste y prueba de frenado.', 4, 11);
INSERT INTO step (name, order_step, operation_id) VALUES ('Prueba de manejo.', 5, 11);
INSERT INTO step (name, order_step, operation_id) VALUES ('Fin', 6, 11);

INSERT INTO operation (name, type_service, service_status) VALUES ('Reparación de Sistema de Suspensión', 'REPARACION', 'EN_ESPERA');
INSERT INTO step (name, order_step, operation_id) VALUES ('Inicio', 1, 12);
INSERT INTO step (name, order_step, operation_id) VALUES ('Identificación y reemplazo de componentes dañados.', 2, 12);
INSERT INTO step (name, order_step, operation_id) VALUES ('Alineación y balanceo.', 3, 12);
INSERT INTO step (name, order_step, operation_id) VALUES ('Lubricación de componentes.', 4, 12);
INSERT INTO step (name, order_step, operation_id) VALUES ('Prueba de manejo.', 5, 12);
INSERT INTO step (name, order_step, operation_id) VALUES ('Fin', 6, 12);

INSERT INTO operation (name, type_service, service_status) VALUES ('Reparación de Sistema de Dirección', 'REPARACION', 'EN_ESPERA');
INSERT INTO step (name, order_step, operation_id) VALUES ('Inicio', 1, 13);
INSERT INTO step (name, order_step, operation_id) VALUES ('Identificación y reemplazo de componentes dañados.', 2, 13);
INSERT INTO step (name, order_step, operation_id) VALUES ('Alineación de la dirección.', 3, 13);
INSERT INTO step (name, order_step, operation_id) VALUES ('Prueba de manejo para verificar la dirección.', 4, 13);
INSERT INTO step (name, order_step, operation_id) VALUES ('Ajuste fino si es necesario.', 5, 13);
INSERT INTO step (name, order_step, operation_id) VALUES ('Fin', 6, 13);

INSERT INTO operation (name, type_service, service_status) VALUES ('Reparación de Sistema Eléctrico', 'REPARACION', 'EN_ESPERA');
INSERT INTO step (name, order_step, operation_id) VALUES ('Inicio', 1, 14);
INSERT INTO step (name, order_step, operation_id) VALUES ('Diagnóstico de fallas eléctricas.', 2, 14);
INSERT INTO step (name, order_step, operation_id) VALUES ('Reemplazo de cables o conexiones dañadas.', 3, 14);
INSERT INTO step (name, order_step, operation_id) VALUES ('Reparación de componentes electrónicos.', 4, 14);
INSERT INTO step (name, order_step, operation_id) VALUES ('Prueba de funcionamiento.', 5, 14);
INSERT INTO step (name, order_step, operation_id) VALUES ('Fin', 6, 14);


INSERT INTO operation (name, type_service, service_status) VALUES ('Reparación de Sistema de Transmisión', 'REPARACION', 'EN_ESPERA');
INSERT INTO step (name, order_step, operation_id) VALUES ('Inicio', 1, 15);
INSERT INTO step (name, order_step, operation_id) VALUES ('Diagnóstico de fallas en la transmisión.', 2, 15);
INSERT INTO step (name, order_step, operation_id) VALUES ('Reemplazo de piezas dañadas.', 3, 15);
INSERT INTO step (name, order_step, operation_id) VALUES ('Ajuste de la transmisión.', 4, 15);
INSERT INTO step (name, order_step, operation_id) VALUES ('Prueba de manejo para verificar la transmisión.', 5, 15);
INSERT INTO step (name, order_step, operation_id) VALUES ('Fin', 6, 15);

INSERT INTO operation (name, type_service, service_status) VALUES ('Reparación de Sistema de Escape', 'REPARACION', 'EN_ESPERA');
INSERT INTO step (name, order_step, operation_id) VALUES ('Inicio', 1, 16);
INSERT INTO step (name, order_step, operation_id) VALUES ('Identificación y reemplazo de componentes dañados.', 2, 16);
INSERT INTO step (name, order_step, operation_id) VALUES ('Soldadura de tubos de escape.', 3, 16);
INSERT INTO step (name, order_step, operation_id) VALUES ('Prueba de emisiones y funcionamiento.', 4, 16);
INSERT INTO step (name, order_step, operation_id) VALUES ('Inspección visual final.', 5, 16);
INSERT INTO step (name, order_step, operation_id) VALUES ('Fin', 6, 16);

INSERT INTO operation (name, type_service, service_status) VALUES ('Reparación de Motor', 'REPARACION', 'EN_ESPERA');
INSERT INTO step (name, order_step, operation_id) VALUES ('Inicio', 1, 17);
INSERT INTO step (name, order_step, operation_id) VALUES ('Diagnóstico de problemas en el motor.', 2, 17);
INSERT INTO step (name, order_step, operation_id) VALUES ('Reemplazo de piezas dañadas.', 3, 17);
INSERT INTO step (name, order_step, operation_id) VALUES ('Ajuste de la sincronización y carburación.', 4, 17);
INSERT INTO step (name, order_step, operation_id) VALUES ('Prueba de funcionamiento y rendimiento.', 5, 17);
INSERT INTO step (name, order_step, operation_id) VALUES ('Fin', 6, 17);

INSERT INTO operation (name, type_service, service_status) VALUES ('Reparación de Sistema de Refrigeración', 'REPARACION', 'EN_ESPERA');
INSERT INTO step (name, order_step, operation_id) VALUES ('Inicio', 1, 18);
INSERT INTO step (name, order_step, operation_id) VALUES ('Diagnóstico de problemas en el sistema de refrigeración.', 2, 18);
INSERT INTO step (name, order_step, operation_id) VALUES ('Reemplazo de radiador o mangueras dañadas.', 3, 18);
INSERT INTO step (name, order_step, operation_id) VALUES ('Llenado y purgado del sistema.', 4, 18);
INSERT INTO step (name, order_step, operation_id) VALUES ('Prueba de temperatura y funcionamiento.', 5, 18);
INSERT INTO step (name, order_step, operation_id) VALUES ('Fin', 6, 18);

INSERT INTO operation (name, type_service, service_status) VALUES ('Reparación de Sistema de Climatización', 'REPARACION', 'EN_ESPERA');
INSERT INTO step (name, order_step, operation_id) VALUES ('Inicio', 1, 19);
INSERT INTO step (name, order_step, operation_id) VALUES ('Diagnóstico de problemas en el sistema de climatización.', 2, 19);
INSERT INTO step (name, order_step, operation_id) VALUES ('Reemplazo de compresor, evaporador u otros componentes dañados.', 3, 19);
INSERT INTO step (name, order_step, operation_id) VALUES ('Recarga de refrigerante.', 4, 19);
INSERT INTO step (name, order_step, operation_id) VALUES ('Prueba de funcionamiento y temperatura.', 5, 19);
INSERT INTO step (name, order_step, operation_id) VALUES ('Fin', 6, 19);

INSERT INTO operation (name, type_service, service_status) VALUES ('Reparación de Carrocería y Pintura', 'REPARACION', 'EN_ESPERA');
INSERT INTO step (name, order_step, operation_id) VALUES ('Inicio', 1, 20);
INSERT INTO step (name, order_step, operation_id) VALUES ('Evaluación de daños en la carrocería.', 2, 20);
INSERT INTO step (name, order_step, operation_id) VALUES ('Enderezado de chasis si es necesario.', 3, 20);
INSERT INTO step (name, order_step, operation_id) VALUES ('Reparación de abolladuras y rasguños.', 4, 20);
INSERT INTO step (name, order_step, operation_id) VALUES ('Pintura y pulido final.', 5, 20);
INSERT INTO step (name, order_step, operation_id) VALUES ('Fin', 6, 20);


-- VEHICULOS
-- Marcas
INSERT INTO brand (name) VALUES ('Toyota');
INSERT INTO brand (name) VALUES ('Hyundai');
INSERT INTO brand (name) VALUES ('Kia');
INSERT INTO brand (name) VALUES ('Chevrolet');
INSERT INTO brand (name) VALUES ('Nissan');
INSERT INTO brand (name) VALUES ('Volkswagen');
INSERT INTO brand (name) VALUES ('Ford');
INSERT INTO brand (name) VALUES ('Suzuki');
INSERT INTO brand (name) VALUES ('Honda');
INSERT INTO brand (name) VALUES ('Mitsubishi');
INSERT INTO brand (name) VALUES ('Mercedes-Benz');
INSERT INTO brand (name) VALUES ('BMW');
INSERT INTO brand (name) VALUES ('Mazda');
INSERT INTO brand (name) VALUES ('Audi');
INSERT INTO brand (name) VALUES ('Renault');
INSERT INTO brand (name) VALUES ('Peugeot');
INSERT INTO brand (name) VALUES ('Subaru');
INSERT INTO brand (name) VALUES ('Fiat');
INSERT INTO brand (name) VALUES ('Chery');
INSERT INTO brand (name) VALUES ('Geely');


-- Modelos
INSERT INTO model (name, brand_id) VALUES ('Corolla', 1);
INSERT INTO model (name, brand_id) VALUES ('Yaris', 1);
INSERT INTO model (name, brand_id) VALUES ('Hilux', 1);

INSERT INTO model (name, brand_id) VALUES ('Tucson', 2);
INSERT INTO model (name, brand_id) VALUES ('Accent', 2);
INSERT INTO model (name, brand_id) VALUES ('Creta', 2);

INSERT INTO model (name, brand_id) VALUES ('Rio', 3);
INSERT INTO model (name, brand_id) VALUES ('Sportage', 3);
INSERT INTO model (name, brand_id) VALUES ('Cerato', 3);

INSERT INTO model (name, brand_id) VALUES ('Spark', 4);
INSERT INTO model (name, brand_id) VALUES ('Sail', 4);
INSERT INTO model (name, brand_id) VALUES ('Tracker', 4);

INSERT INTO model (name, brand_id) VALUES ('March', 5);
INSERT INTO model (name, brand_id) VALUES ('Versa', 5);
INSERT INTO model (name, brand_id) VALUES ('Kicks', 5);

INSERT INTO model (name, brand_id) VALUES ('Gol', 6);
INSERT INTO model (name, brand_id) VALUES ('Polo', 6);
INSERT INTO model (name, brand_id) VALUES ('Amarok', 6);

INSERT INTO model (name, brand_id) VALUES ('Ecosport', 7);
INSERT INTO model (name, brand_id) VALUES ('Ranger', 7);
INSERT INTO model (name, brand_id) VALUES ('Fiesta', 7);

INSERT INTO model (name, brand_id) VALUES ('Swift', 8);
INSERT INTO model (name, brand_id) VALUES ('Jimny', 8);
INSERT INTO model (name, brand_id) VALUES ('Vitara', 8);

INSERT INTO model (name, brand_id) VALUES ('Civic', 9);
INSERT INTO model (name, brand_id) VALUES ('HR-V', 9);
INSERT INTO model (name, brand_id) VALUES ('CR-V', 9);

INSERT INTO model (name, brand_id) VALUES ('L200', 10);
INSERT INTO model (name, brand_id) VALUES ('Outlander', 10);
INSERT INTO model (name, brand_id) VALUES ('ASX', 10);

INSERT INTO model (name, brand_id) VALUES ('Clase A', 11);
INSERT INTO model (name, brand_id) VALUES ('Clase C', 11);
INSERT INTO model (name, brand_id) VALUES ('Clase GLA', 11);

INSERT INTO model (name, brand_id) VALUES ('Serie 1', 12);
INSERT INTO model (name, brand_id) VALUES ('Serie 3', 12);
INSERT INTO model (name, brand_id) VALUES ('X1', 12);

INSERT INTO model (name, brand_id) VALUES ('Mazda3', 13);
INSERT INTO model (name, brand_id) VALUES ('Mazda2', 13);
INSERT INTO model (name, brand_id) VALUES ('CX-5', 13);

INSERT INTO model (name, brand_id) VALUES ('A3', 14);
INSERT INTO model (name, brand_id) VALUES ('Q3', 14);
INSERT INTO model (name, brand_id) VALUES ('A4', 14);

INSERT INTO model (name, brand_id) VALUES ('Kwid', 15);
INSERT INTO model (name, brand_id) VALUES ('Logan', 15);
INSERT INTO model (name, brand_id) VALUES ('Duster', 15);

INSERT INTO model (name, brand_id) VALUES ('208', 16);
INSERT INTO model (name, brand_id) VALUES ('2008', 16);
INSERT INTO model (name, brand_id) VALUES ('3008', 16);

INSERT INTO model (name, brand_id) VALUES ('Forester', 17);
INSERT INTO model (name, brand_id) VALUES ('XV', 17);
INSERT INTO model (name, brand_id) VALUES ('Impreza', 17);

INSERT INTO model (name, brand_id) VALUES ('Argo', 18);
INSERT INTO model (name, brand_id) VALUES ('Cronos', 18);
INSERT INTO model (name, brand_id) VALUES ('Toro', 18);

INSERT INTO model (name, brand_id) VALUES ('Tiggo 2', 19);
INSERT INTO model (name, brand_id) VALUES ('Tiggo 3', 19);
INSERT INTO model (name, brand_id) VALUES ('Tiggo 5', 19);

INSERT INTO model (name, brand_id) VALUES ('CK', 20);
INSERT INTO model (name, brand_id) VALUES ('Emgrand 7', 20);
INSERT INTO model (name, brand_id) VALUES ('X7 Sport', 20);
