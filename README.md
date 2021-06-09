# Plexus Super Heroes Test

El ejercicio de prueba tiene como base el modelo MVC y lleva por la parte de Modelos de un Usuario, Perfil y Super Héroe donde he implementado la capa de persistencia de la aplicación con JPA. Utilizando anotaciones como:
- **@Entity**: donde mapeamos la entidad en la aplicación.
- **@Table**: donde definimos las características en la construcción de la base de datos.
- **@Id**: definición de la primary key en determinada tabla.
- **@Collumn**: donde determinamos las definiciones de los atributos en la base de datos.
También he utilizado algunas otras anotaciones para mejorar el modelo y así garantizar menos generación de código en el futuro.
- **@GeneratedValue**: donde se define la estrategia de generación de los identificadores de las tablas. 
- **@UniqueConstraint**: con esta anotación definimos otro atributo como único así también garantizamos que en nuestra base de datos no encontraremos entradas duplicadas 
- **@Data**: Hace parte del proyecto Lombok que implementa vía anotaciones la parte de código que se repite en cada modelo como los getters, setters, toString, constructores, etc. 


En la entidad *User* he utilizado la implementación de *UserDetails* que hace todo el manejo de datos sensibles como contraseña, email, teléfono, etc. Lo que hace en realidad es encapsular toda la información y generar un objeto de tipo Authentication y así gestionar la parte de seguridad con el Spring Security. 

En la entidad *Profile* he utilizado la implementación de *GrantedAuthority*, que maneja perfiles de acceso a los usuarios de la aplicación, he creado por defecto *MEDERATOR* y *USER* que en las configuraciones de permisos el *MODERATOR* tiene permisos para todos los endpoints y el USER no.

He hecho uso de *DTOs* para el manejo de las transacciones de la aplicación sin pasar datos desnecesarios en los servicios. 
 
La parte de los Controladores he utilizado el patrón REST y optado por algunas anotaciones y para la implementación de esta capa. 
- **@RestController**: definir determinada clase como un controlador Rest de la aplicación.
- **@RequestMapping**: es utilizado para definir y mapear las requisiciones web con el camino que determinamos con esta anotación. 
- **@Cacheable**: he decidido utilizar cache para cada vez que una requisición GET sea efectuada el cache verifique antes si hubo alguna modificación con llamadas del tipo *POST*, *DELETE* o *PUT* para así hacer una nueva requisición. 
- **@CacheEvict**: La anotación en los métodos que quiero hacer la verificación o del recurso anterior *@Cacheable*. 
- **@TrackExecutionTime**: Una anotación creada por mi para calcular el tiempo de procesamiento de cada método y mostrar en el log los valores de tiempo de ejecución.  
En la parte de Excepciones he creado la clase SuperHeroException que maneja los errores que pueden pasar con las transacciones de la aplicación.

He separado las reglas de negocio en la capa de *Service* y *ServiceImpl* para utilizar en los Controllers directamente con la inyección utilizando la anotación *@Autowired*.
También para la facilidad de las pruebas y documentación he decidido utilizar el Swagger en la aplicación que ya tiene todos los endpoints mapeados y muestra al desarrollador todo que el necesita saber acerca de los datos necesarios en cada requisición.

En la capa de seguridad hay tres perfiles de ejecución que son *DEV*, *PROD* y *TEST* cada uno tiene sus proprias reglas de autenticación y permisos. Con *PROD* tenemos la aplicación que hace la autenticación vía token (Stateless) y también verifica que role tiene cada usuario que garante los permisos de distintos endpoints de la aplicación. 

Para ejecutar la aplicación solamente tenemos que definir el Profile añadiendo en los argumentos: 
*-Dspring.profiles.active={dev, prod, test}* (para los perfiles de *prod* y *test* hay que definir las variables antes de ejecutar la aplicación)

Las contraseñas he encriptado con el bccrypt en la base de datos para añadir mas seguridad en los datos más sensibles.

 