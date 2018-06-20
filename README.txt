Pasos a seguir para jugar al Timbiriche:
1 - Abrir una terminal
2 - Posicionarse en la carpeta que contiene el ejecutable "Timbiriche-1.0.jar"
3 - Ejecutar por línea de comando la siguiente instrucción:

		$ java -jar Timbiriche-1.0.jar -size [n] -ai [m] -mode [time|depth] -param [k] -prune [on|off]

	donde el texto entre comillas es la configuración a utilizar:
		-size [n]: determina el tamaño del tablero; “n” debe ser un número entero mayor a 2.
 		-ai [m]: determina el rol de la AI; “m” es un número que significa:
			0: no hay AI. Juegan dos jugadores humanos
 			1: AI mueve primero
			2: AI mueve segundo
			3: AI vs AI
		-mode [time|depth]: determina si el algoritmo minimax se corre por tiempo o por profundidad.
		-param [k]: acompaña al parámetro anterior. En el caso de “time”, k deben ser los segundos. En el caso de “depth”, debe ser la profundidad del árbol.
		-prune [on|off]: activa o desactiva la poda.

IMPORTANTE: Cada argumento se debe pasar en el orden que figura arriba.