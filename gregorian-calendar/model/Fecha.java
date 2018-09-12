package sample;

public class Fecha {
    private int anno;
    private int mes;
    private int dia;

    public Fecha(int anno, int mes, int dia) {
        this.anno = anno;
        this.mes = mes;
        this.dia = dia;
    }

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
        return (this.anno%4==0 && this.anno%100!=0)||(this.anno%400==0);
    }

    /* Dada una fecha, determinar si ésta es válida. El resultado debe 
    ser un valor booleano, True o False.*/
    public boolean fecha_es_valida(int anno, int mes, int dia) {
        if (dia> 0 && 0<mes && mes<=12 && anno>1600) {
            switch (mes) {
                case 2:
                    if ((dia <= 29 && this.bisiesto(anno)) || (dia <=28) ) {
                        return true;
                    }
                    return false;
                case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                    if( dia <= 31) {
                        return true;
                    } 
                    return false;
                default:
                if (dia <= 30 ){
                    return true;
                }
                return false;
            }
        }
        return true;
    }
    // Comprobar año, comprobar mes, comprobar diar (excepcion febrero si es bisiesto) 
    /* Dada una fecha válida, determinar la fecha del día siguiente.
    El resultado debe ser una fecha válida (objeto de clase fecha,
    con tres números enteros positivos, que corresponde a una fecha
    en el Calendario gregoriano).*/
    public Fecha dia_siguiente() {//Recibe una fecha valida
        int newDay;
        switch (dia){
            case 31:
                newDay =1;
                break;
            case 30:
                if(mes == 4 || mes == 6 || mes == 9 || mes == 11){
                   newDay =1;
                }else{
                    newDay=31;
                }
                break;
            case 29:
                if(mes==2){
                    newDay=1;
                }else{
                    newDay=30;
                }
                break;
            case 28:
                if(mes==2){
                    if(bisiesto(anno)){
                        newDay =29;
                    }else{
                        newDay=1;
                    }
                }else{
                    newDay=30;
                }
                break;
            default:
                newDay = dia+1;
                break;
        }
        if(newDay==1){
            if(mes==12){
                return new Fecha(anno+1,1,1);
            }else{
                return new Fecha(anno,mes+1,1);
            }
        }else{
            return new Fecha(anno,mes,newDay);
        }
    }

    /* Dada una fecha válida, determinar el número de días transcurridos 
    desde el primero de enero de su año (el número de días transcurridos 
    entre el primero de enero y el primero de enero, dentro de un mismo año,
     es 0). El resultado debe ser un número entero */
    public int dias_desde_primero_enero() {
        int resultado = 0;
        while(mes>1){
            mes--;
            switch (mes){
                case 2:
                    if(bisiesto(anno)){
                        resultado+=29;
                    }else{
                        resultado+=28;
                    }
                    break;
                case 4: case 6: case 9: case 11:
                    resultado+=30;
                    break;
                default:
                    resultado+=31;
                    break;
            }

        }
        return resultado+dia-1;
    }

    /* Dado un año perteneciente al rango permitido, determinar el día de 
    la semana que le corresponde al primero de enero de ese año,
    con la siguiente codificación:
    0 = domingo, 1 = lunes, 2 = martes, 3 = miércoles, 4 = jueves, 5 = viernes, 6 = sábado.
    El resultado debe ser un número entero, conforme a la codificación indicada. */
    public int dia_primero_enero() {//algoritmo de zeller

        int anho = anno - 13/12;
        return  (1 + anho + (anho/4) - (anho/100) + (anho/400) + (31*(12*(13/12)-1)) / 12) % 7;
    }

    /* Dado un año perteneciente al rango permitido, desplegar en consola 
    el calendario de ese año en formato de 4 secuencias (‘filas’) de 3 meses 
    cada una. 
    El resultado debe lucir semejante al que se muestra al final de este enunciado. */
    public void imprimir_4x3() {

    }

    @Override
    public String toString() {
        return "Fecha{" +
                "anno=" + anno +
                ", mes=" + mes +
                ", dia=" + dia +
                '}';
    }
}