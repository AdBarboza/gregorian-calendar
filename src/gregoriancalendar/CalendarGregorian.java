package gregoriancalendar;

import model.Date;

/**
 *
 * @author Adrián, Dafne, JuanDeDios
 */
public class CalendarGregorian {

    /* Dado un año perteneciente al rango permitido, determinar si este 
    es bisiesto. El resultado debe ser un valor booleano, True o False*/
    public boolean bisiesto(Date fecha) {
        int anno = fecha.getYear();

        if (anno >= 1700 && anno <= 2199) {
            return (anno % 4 == 0 && anno % 100 != 0) || (anno % 400 == 0);
        }
        return false;
    }

    /* Dada una fecha, determinar si ésta es válida. El resultado debe 
    ser un valor booleano, True o False.*/
    public boolean fecha_es_valida(Date fecha) {
        int dia = fecha.getDay();
        int mes = fecha.getMonth();
        int anno = fecha.getYear();
        
        if (dia > 0 && 1 < mes && mes <= 12 && anno >= 1700 && anno <= 2199) {
            switch (mes) {
                case 2:
                    if ((dia <= 29 && this.bisiesto(fecha)) || (dia <= 28)) {
                        return true;
                    }
                    return false;
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    if (dia <= 31) {
                        return true;
                    }
                    return false;
                default:
                    if (dia <= 30) {
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
    public Date dia_siguiente(Date fecha) {//Recibe una fecha valida
        if (!this.fecha_es_valida(fecha)) {
            return null;
        }
        
        int newDay;
        int dia = fecha.getDay();
        int mes = fecha.getMonth();
        int anno = fecha.getYear();
        
        switch (dia) {
            case 31:
                newDay = 1;
                break;
            case 30:
                if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
                    newDay = 1;
                } else {
                    newDay = 31;
                }
                break;
            case 29:
                if (mes == 2) {
                    newDay = 1;
                } else {
                    newDay = 30;
                }
                break;
            case 28:
                if (mes == 2) {
                    if (this.bisiesto(new Date(0,0,anno))) {
                        newDay = 29;
                    } else {
                        newDay = 1;
                    }
                } else {
                    newDay = 30;
                }
                break;
            default:
                newDay = dia + 1;
                break;
        }
        if (newDay == 1) {
            if (mes == 12) {
                return new Date(anno + 1, 1, 1);
            } else {
                return new Date(anno, mes + 1, 1);
            }
        } else {
            return new Date(anno, mes, newDay);
        }
    }

    /* Dada una fecha válida, determinar el número de días transcurridos 
    desde el primero de enero de su año (el número de días transcurridos 
    entre el primero de enero y el primero de enero, dentro de un mismo año,
     es 0). El resultado debe ser un número entero */
    public int dias_desde_primero_enero(Date fecha) {
        if(!this.fecha_es_valida(fecha)){
            return -1;
        }
        int resultado = 0;
        int dia = fecha.getDay();
        int mes = fecha.getMonth();
        
        while (mes > 1) {
            mes--;
            switch (mes) {
                case 2:
                    if (this.bisiesto(fecha)) {
                        resultado += 29;
                    } else {
                        resultado += 28;
                    }
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    resultado += 30;
                    break;
                default:
                    resultado += 31;
                    break;
            }
        }
        return resultado + dia - 1;
    }

    /* Dado un año perteneciente al rango permitido, determinar el día de 
    la semana que le corresponde al primero de enero de ese año,
    con la siguiente codificación:
    0 = domingo, 1 = lunes, 2 = martes, 3 = miércoles, 4 = jueves, 5 = viernes, 6 = sábado.
    El resultado debe ser un número entero, conforme a la codificación indicada. */
    public int dia_primero_enero(Date fecha) {//algoritmo de zeller
        
        int anho = fecha.getYear() - 13 / 12;
        return (1 + anho + (anho / 4) - (anho / 100) + (anho / 400) + (31 * (12 * (13 / 12) - 1)) / 12) % 7;
    }

    /* Dado un año perteneciente al rango permitido, desplegar en consola 
    el calendario de ese año en formato de 4 secuencias (‘filas’) de 3 meses 
    cada una. 
    El resultado debe lucir semejante al que se muestra al final de este enunciado. */
    public void imprimir_4x3(int anno) {
        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        System.out.println("Calendario del año :" + anno + " D.C.");
        String[][][] anho = new String[12][6][7];
        int cantDias = 0;
        int diaActual = this.dia_primero_enero(new Date(anno,1,1));
        for (int i = 0; i < 12; i++) {
            switch (i + 1) {
                case 2:
                    if (bisiesto(new Date(anno,1,1)))
                        cantDias = 29;
                    else
                        cantDias = 28;
                    break;
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    cantDias = 31;
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    cantDias = 30;
                    break;
            }
            int semAct = 0;
            for (int o = 0; o < cantDias; o++) {
                anho[i][semAct][diaActual] = Integer.toString(o + 1);
                if (diaActual == 6)
                    semAct += 1;
                diaActual = (diaActual + 1) % 7;
            }
        }
        for(int i=0;i<12;i++){
            for(int e=0;e<6;e++){
                for(int u=0;u<7;u++){
                    String a = anho[i][e][u];
                    if(a == null)
                        anho[i][e][u]="";

                }
            }
            System.out.println();
        }
        for (int i = 0; i < 12; i = i + 3) {
            System.out.format("%20s%20s%20s%20s%20s%20s\n", meses[i], "|", meses[i + 1], "|", meses[i + 2], "|");
            for (int e = 0; e < 3; e++) {
                System.out.format("%5s%5s%5s%5s%5s%5s%5s%5s", "D", "L", "K", "M", "J", "V", "S", "|");
                
            }
            for(int p=0;p<6;p++){
                System.out.println();
                System.out.format("%5s%5s%5s%5s%5s%5s%5s%5s", anho[i][p][0] , anho[i][p][1],anho[i][p][2] ,anho[i][p][3], anho[i][p][4],anho[i][p][5] , anho[i][p][6], "|");
                System.out.format("%5s%5s%5s%5s%5s%5s%5s%5s", anho[i+1][p][0] , anho[i+1][p][1],anho[i+1][p][2] ,anho[i+1][p][3], anho[i+1][p][4],anho[i+1][p][5] , anho[i+1][p][6], "|");
                System.out.format("%5s%5s%5s%5s%5s%5s%5s%5s", anho[i+2][p][0] , anho[i+2][p][1],anho[i+2][p][2] ,anho[i+2][p][3], anho[i+2][p][4],anho[i+2][p][5] , anho[i+2][p][6], "|");

            }
            System.out.println();
        }

    }
    
        /* Dada una fecha válida f y un número entero no-negativo n, determinar la fecha
    que está n días naturales en el futuro. El resultado debe ser una fecha válida. */
    public Date fecha_futura(Date f, int n) {
        if (!this.fecha_es_valida(f) && n<=0) {
            return null;
        }
        
        Date temp = null;
        int newDay;
        int dia = f.getDay();
        int mes = f.getMonth();
        int anno = f.getYear();

        for(int i=0; n>i; ++i){
            switch (dia) {
                case 31:
                    newDay = 1;
                    break;
                case 30:
                    if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
                    newDay = 1;
                    } else {
                     newDay = 31;
                    }
                    break;
                case 29:
                    if (mes == 2) {
                        newDay = 1;
                    } else {
                        newDay = 30;
                    }
                    break;
                case 28:
                    if (mes == 2) {
                        if (this.bisiesto(new Date(0,0,anno))) {
                            newDay = 29;
                        } else {
                            newDay = 1;
                        }
                    } else {
                        newDay = 29;
                    }
                    break;
                default:
                    newDay = dia + 1;
                    break;
            }
            if (newDay == 1) {
                if (mes == 12) {
                    temp = new Date(anno + 1, 1, 1);
                } else {
                    temp = new Date(anno, mes + 1, 1);
                }
            } else {
                temp = new Date(anno, mes, newDay);
            }
            dia = temp.getDay();
            mes = temp.getMonth();
            anno = temp.getYear();
        }
        return temp;
    }

    /* Dadas dos fechas válidas, f1 y f2, sin importar si f1≤f2 o f2≤f1, determinar
    el número de días naturales entre las dos fechas. Si f1=f2, entonces días_entre(f1,f2)=0.
    El resultado debe ser un número entero no negativo.*/
    public int dias_entre(Date f1, Date f2){
        if (!this.fecha_es_valida(f1) && !this.fecha_es_valida(f2)) {
            System.out.println("ERROR: La o las fechas no son válidan");
        }
        
        int resultado = 0;
        int temp1;
        int temp2;
        int dia1 = f1.getDay();
        int mes1 = f1.getMonth();
        int anno1 = f1.getYear();
        int dia2 = f2.getDay();
        int mes2 = f2.getMonth();
        int anno2 = f2.getYear();
        
        if (anno1 == anno2 && mes1 == mes2 && dia1 == dia2){ //son la misma fecha
            return resultado;        
        }
        else{ //fechas diferentes
            temp1 = (int)((anno1*365.25)+((mes1-1)*30.627)+dia1); //resultados redondeados
            temp2 = (int)((anno2*365.25)+((mes2-1)*30.627)+dia2);
            if(temp1 < temp2){
                resultado = temp2 - temp1; 
            }
            else{
                resultado = temp1 - temp2;
            }
        return resultado;
        }
    }

    /* Dada una fecha válida, determinar el día de la semana que le corresponde,
    con la siguiente codificación: 0=domingo, 1=lunes, 2=martes, 3=miércoles,
    4=jueves, 5=viernes, 6=sábado. El resultado debe ser un número entero, conforme
    a la codificación indicada. */
    public int dia_semana(Date fecha){ //Algoritmo de Zeller
        if (!this.fecha_es_valida(fecha)) {
            System.out.println("ERROR: La fecha no es válida");
        }
        
        int a = (14-fecha.getMonth())/12;
        int y = fecha.getYear() - a;
        int m = fecha.getMonth()+12*a-2;
        
        return (fecha.getDay() + y + (y/4) - (y/100) + (y/400) + (31*m)/12)% 7;
    }

    /* Dada una fecha válida f y un número entero no-negativo n, determinar la fecha
    que está n días hábiles en el futuro. El resultado debe ser una fecha válida que
    corresponda a un día hábil. Note que f puede corresponder a la fecha de un día no hábil. */
    public Date fecha_futura_habil(Date f, int n){

        return null;
    }

    /* Dadas dos fechas válidas, f1 y f2, sin importar si f1≤f2 o f2≤f1, determinar
    el número de días hábiles entre las dos fechas. Si f1=f2, entonces días_habiles_entre(f1,f2)=0.
    El resultado debe ser un número entero no negativo. */
    public int dias_habiles_entre(Date f1, Date f2){

        return 0;
    }

    public static void main(String[] args) {
        CalendarGregorian calendar1 = new CalendarGregorian();
        Date date1 = new Date(2018,9,30);
        Date date2 = new Date(2018,10,20);
        Date date3 = new Date(2003,10,20);
        Date date4 = new Date(1953,8,2);
        
        
        //Pruebas fecha_futura
        date1.toString();
        System.out.println("fecha futura: " + calendar1.fecha_futura(date1, 10));
        //System.out.println("fecha futura: " + calendar1.fecha_futura(date1, 0));
        //System.out.println("fecha futura: " + calendar1.fecha_futura(date1, 365));
        //System.out.println("fecha futura: " + calendar1.fecha_futura(date1, 50));
        
        //Pruebas para dias_entre
        date2.toString();
        date3.toString();
        System.out.println("dias antre: " + calendar1.dias_entre(date1, date2));
        System.out.println("dias antre: " + calendar1.dias_entre(date2, date3));
        
        //Pruebas dia_semana
        System.out.println("dia semana: " + calendar1.dia_semana(date4));
    }

}
