# Sistema de Registro Personal para el conjunto Residencial Turquesa

## Objetivo

Desarrollar un sistema en el cual el adminitrador del conjunto tenga un control del personal externo que brinda servicio
al conjunto.

## Historias de Usuario

* H-01: Registrar Empleado

Como administrador, quiero registrar un nuevo empleado ingresando nombre completo,
tipo y número de identificación, rol, teléfono, turno y estado, para llevar un
control del personal que presta servicios en el conjunto.

* H-02: Evitar números duplicados

Como administrador, quiero que el sistema valide que el número de identificación
no esté registrado previamente, para evitar datos duplicados en el sistema.

* H-03: Validar información obligatoria

Como administrador, quiero que el sistema valide que todos los campos obligatorios
estén diligenciados antes de registrar un empleado, para evitar información
incompleta.

* H-04: Consultar todo el personal

Como administrador, quiero consultar todo el personal registrado, para tener un
historial de empleados que prestan o han prestado servicio en el conjunto.

* H-05: Filtrar por turno

Como administrador, quiero filtrar el personal según su turno, ya sea diurno o
nocturno, para identificar qué empleados se encuentran disponibles en cada turno.

* H-06: Filtrar por rol

Como administrador, quiero filtrar el personal según su rol, para realizar
consultas más rápidas y encontrar empleados según una función específica.

* H-07: Consultar empleado por identificación

Como administrador, quiero consultar el personal con su número de identificación
para obtener rápidamente la información del empleado.

* H-08: Actualizar información del empleado
Como administrador, quiero actualizar la información de un empleado, para mantener
sus datos actualizados cuando se presente algún cambio.

* H-09: Cambiar estado del empleado
Como administrador, quiero cambiar el estado de un empleado entre activo e
inactivo, para mantener actualizado el personal que actualmente presta servicio
en el conjunto.

* H-10: Eliminar Empleado
Como administrador, quiero eliminar un registro de empleado, para retirar del
sistema información que ya no deba permanecer registrada.

## Estado HTTP

- 200 OK : Estado utilizado para consultar datos existentes.
- 201 CREATED : Estado utilizado para agregar datos a el servidor.
- 409 CONFLICT : Cuando en una peticion post se envia un recurso ya existente en el servidor.
