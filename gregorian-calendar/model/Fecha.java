
public class Fecha {
    private int anno;
    private int mes;
    private int dia;

    /* Todas las fechas serán creadas como instancias de una clase con 
    tres atributos que sean números enteros positivos (ternas), en este
    orden: (año, mes, día). El resultado debe ser un valor booleano,
    True o False.*/
    public boolean fecha_es_tupla(int anno, int mes, int dia) {
        //Revisar que sean numeros positivos
        //Revisar que cumplan con condiciones de fecha
        return (this.anno%4==0 && this.anno%100==0)||(this.anno%400==0);
    }
    
    /* Dado un año perteneciente al rango permitido, determinar si este 
    es bisiesto. El resultado debe ser un valor booleano, True o False*/
    public boolean bisiesto(int anno) {
        //Revisar q sea un numero positivo
        return (this.anno%4==0 && this.anno%100==0)||(this.anno%400==0);
    }

    /* Dada una fecha, determinar si ésta es válida. El resultado debe 
    ser un valor booleano, True o False.*/
    public boolean fecha_es_valida() {
        return true;
    }

    /* Dada una fecha válida, determinar la fecha del día siguiente.
    El resultado debe ser una fecha válida (objeto de clase fecha,
    con tres números enteros positivos, que corresponde a una fecha
    en el Calendario gregoriano).*/
    public Fecha dia_siguiente() {
        return null;
    }

    /* Dada una fecha válida, determinar el número de días transcurridos 
    desde el primero de enero de su año (el número de días transcurridos 
    entre el primero de enero y el primero de enero, dentro de un mismo año,
     es 0). El resultado debe ser un número entero */
    public int dias_desde_primero_enero() {
        int resultado = 0;
        return resultado;
    }

    /* Dado un año perteneciente al rango permitido, determinar el día de 
    la semana que le corresponde al primero de enero de ese año,
    con la siguiente codificación:
    0 = domingo, 1 = lunes, 2 = martes, 3 = miércoles, 4 = jueves, 5 = viernes, 6 = sábado.
    El resultado debe ser un número entero, conforme a la codificación indicada. */
    public int dia_primero_enero() {
        int semana = 0;
        return semana;
    }

    /* Dado un año perteneciente al rango permitido, desplegar en consola 
    el calendario de ese año en formato de 4 secuencias (‘filas’) de 3 meses 
    cada una. 
    El resultado debe lucir semejante al que se muestra al final de este enunciado. */
    public void imprimir_4x3() {

    }
}