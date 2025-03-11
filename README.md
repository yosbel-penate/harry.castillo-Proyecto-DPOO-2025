# Documento de visión inicial y de requisitos (Readme).


### Equipo 1:
 1- Harry Pérez Castillo.
 
 2- Lázaro Luis Ayala Oquendo
 
 3- Arturo Manuel Álvarez González.
 
 4- Xavier Alejandro Alfonso Borges.
 
 5- Eduardo Cortés Odio.
 

### Tema 2: 
 Crónicas de Valthar: El torneo de las Eras.

---

### En las siguientes líneas del documento de visión inicial del Trabajo de Curso para DPOO, se hablará de:
 1- Describir lo que se hará en cuanto a desarrollo.
 
 2- Tareas propuestas a cada miembro.
 
 3- Tecnologías usadas en el desarrollo.
 
 4- Librerías y su explicación.
 
 5- Función del juego.
 
 6- Descripción del sistema de carpetas del software.



# R1/ 
En cuanto a desarrollo, tenemos planeado un modelo de proceso de software incremental. Entiéndase que va por fases, cambia dependiendo de los avances que tengamos del programa y se puede volver a iterar en caso de que cambien los requisitos del proyecto. 

A continuación hay una figura de su representación general y luego otra imagen sobre cómo se adaptó a nuestro caso junto a una explicación:

![.](https://github.com/user-attachments/assets/53b1a035-4903-43d2-8e75-123009925540)

![.](https://github.com/user-attachments/assets/68cc24f3-a0fe-4c06-adf8-a83b2eebc41b)


Estas etapas NO influyen en las entregas semanales (por el contrario, las entregas semanales influyen en las etapas). Se dividen en tres por el amplio código que se necesita para que no solamente el juego funcione bien, sino que se sienta o fluya bien para el jugador.

Al finalizar la Etapa 1, estarán todas las mecánicas del juego ya hechas. Eso sí, sin haberse hecho control de errores aún.

Al finalizar la Etapa 2, se habrán perfeccionado las mecánicas y se habrán añadido algunas otras, además de haber arreglado gran parte de los errores.

Al finalizar la Etapa 3, el juego pasaría de tener una interfaz visual pobre y solamente para pruebas, a ser una interfaz de calidad y c
on efectos de sonido y música.




# R2/ 

Para cada miembro en este equipo, se han dividido las tareas según ciertas funcionalidades del juego y la afinidad de estos integrantes a esas tareas. Muestro entonces la repartición inicial que hemos hecho con tal de crear el esqueleto de buena parte de las funciones del juego:

Harry Pérez Castillo: controles e inteligencia artificial de los enemigos.

Lázaro Luis Ayala Oquendo: sistema de combate y de consumibles.

Arturo Manuel Álvarez González: mapa y menús.

Xavier Alejandro Alfonso Borges: roaster e implementación del equipo de personajes en el juego.

Eduardo Cortés Odio: modo campaña y sus escenas (cutscenes).


# R3/ 
Las tecnologías que de momento se piensan usar son las siguientes:

## IDEs: 

1- Intellij IDEA.

2- Net Beans.

## UML:

1- UMLet.

2- Página web que convierte de UXF (tipo de archivo que exporta el programa UMLet) a código de Java: https://charlycimino.github.io/uxf-to-java

3- PlantUML (donde se hará el UML principal).

Lenguaje de programación Java, a través del Kit de Desarrollo 23, el actualizado hasta la fecha.

Tiled, un editor de mapas 2D tanto de hexágonos como de cuadrados.

Scene Builder, para JavaFX.


# R4/ 

De momento, solamente usaremos la librería JavaFX (ampliamente usada y actualizada para crear interfaces gráficas) y las librerías estándar del vigésimo tercer Kit de Desarrollo de Java. 


# R5/ 

El juego tiene muchas funcionalidades, que aquí dividiremos en 4:

1- Funciones del mapa: tratan el movimiento del personaje (que serán 5, pero en el mapa se verá solamente uno) por el mapa y por puntos de acción, los efectos que tendrán en ellos el relieve, las trampas e incluso algunos enemigos y las colisiones.

2- Funciones del combate: basado en los típicos sistemas por turnos de RPG, este tendrá las funcionalidades de: Ataque, Habilidades, Consumibles y Huir (esta última opción desactivada en ciertas ocasiones); la funcionalidad de este empezará cuando se haga colisión con un enemigo, donde se pasará a una pantalla especial para esta funcionalidad.

3- Funciones de los modos de juego: en el modo campaña, se tendrán diálogos con una interfaz visual mostrando al personaje, su nombre y su diálogo (de sobrar tiempo, se diferenciarán los diálogos del personaje principal en el equipo elegido), habrán ciertos objetivos a cumplir y se añadirá alguna que otra cinemática (cutscene) para mejorar la experiencia. En el modo PvP, se elegirá un escenario de dos y luego a los cinco personajes de cada uno, para posteriormente ganar o acabando con todo el equipo del otro jugador o alcanzando un Núcleo de Era. En el modo Torneo, con unas combinaciones de hasta 26334 (C22 5) equipos posibles, se agarrarán dieciséis equipos que lucharán entre sí, obteniendo niveles y consumibles por el recorrido hasta ser finalistas (única forma de ganar en este modo).

4- Funciones del menú: botón de iniciar el juego (posteriormente llevaría a otro menú con la selección del modo de juego y, posteriormente, de los personajes), botón para explicar los controles y botón de cerrar el juego.


# R6/ 

De momento, el sistema de carpetas del software sería:

1- Src (incluyendo las carpetas de Clases y las carpetas de los Objetos predefinidos).

2- Nbproject (carpetas generadas automáticamente).

3- Test (carpetas generadas automáticamente).

4- Assets (música, efectos de sonido, sprites).


# Documento de Requisitos:

### -Título del proyecto:
“Crónicas de Valthar: El torneo de las eras. ”

### -Descripción del juego:
1- Género del juego: roguelike RPG de estrategia por turnos, fantasía.

2- Historia: En el mundo mágico de Valthar, un torneo legendario convoca a los guerreros más formidables de todas las eras para competir por el Cáliz de las Eras, un artefacto capaz de alterar el destino.

3- Cómo se juega: se utiliza teclado o mouse para elegir las casillas en las que se moverá el personaje, también se usan para las opciones del combate por turnos. Sus reglas son no usar más de cierta cantidad de consumibles, dependiendo del modo, las reglas se ajustan a los objetivos (si un objetivo es no matar a alguien, pues sería una regla), etc.


### -Requisitos (mínimos) del sistema:

1- Java JDK 23.

2- Sistema operativo: Windows, Linux o macOS.

3- Memoria RAM: 1 o 2GB mínimo.

4- CPU’s Intel, Radeon modernos.

5- Tarjeta gráfica (opcional).


### -Instalación:

(Requerido tener Git):

1- Clonar repositorio usando el comando git clone.

2- Navegar al directorio del proyecto con el comando cd.

3- Compilar el proyecto con bash javac *. java.

4- Ejecutar el proyecto con bash java Main.


### -Diseño del juego (POO):

Clases principales:


1- Entities: representa a cualquier entidad del juego, contiene atributos como vida, daño, velocidad, etc.

2- GamePanel: marco del juego.

3- AssetsManager: gestor de recursos del juego.

4- Consumables: representa todos los consumibles del juego.

5-  Characters: representa a todos los personajes del juego.

Todas estas clases son públicas, pero sus variables están encapsuladas.

Para evitar duplicar el código de los Characters y los Consumables, se hizo que estas heredaran de Entities. En el caso de Consumables, ellos heredan estos valores porque estos valores son los que aumentarán a los personajes en caso de consumirlos (la relación de Entities con Characters y Consumables es fuerte). Assets Manager 



### -Diagrama de clases: 

(De momento vacío, se entregará la próxima semana según el calendario dado)


### -Funcionalidades implementadas:

-Sistema de consumibles.

-Combates por turnos.

-Interacción con el mapa y los enemigos.

-Modos de juego: Torneo, Campaña y PvP.


### -Pruebas y validación:

-Para verificar el juego, a nivel funcional lo ejecutamos y probamos las debilidades del código a través de ciertos forcejeos con la movilidad y los combates. 

-Para comprobar la funcionalidad de las clases, estuvimos probando todas (o gran parte) las posibilidades que nos ofrecían  y en contextos distintos.


### -Conclusiones: 

(Se rellenará cuando terminemos el proyecto y se haya expuesto)

### -Referencias: 

(Se pondrán más adelante, ya que de momento solo hemos usado de referencias algunos vídeos de Youtube y poco más)


