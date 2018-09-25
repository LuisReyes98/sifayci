package Ve.com.biller.helpers.reyes;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import java.sql.Time;
/**
 *
 * @author Luis Reyes
 */
public class DateManager {
    
    /**
     * Clase que recibe 
     * Formato de la fecha en forma 
     * de un String
     * @param formato
     * @return fecha 
    */
    public static String getTodayDateString(String formato){//recieves an string with the date format
        String fecha=null;
        try{                        
            Calendar calendario;
            calendario = Calendar.getInstance();
            SimpleDateFormat sdfFecha= new SimpleDateFormat(formato);    
            fecha= sdfFecha.format(calendario.getTime());            
       }catch(Throwable t){}
        
        return fecha;//retunrs the current date in a string 
    }
    
    public static Date getTodayDate(){//gets the date in the MM/dd/yyyy format
      
        Date todayDate= new Date(DateManager.getTodayDateString("MM/dd/yyyy"));
        
        return todayDate;
    }
    
    /**
     * Transforma el valor Long de una fecha a su forma String con formato "dd/MM/yyyy"
     * @param longDate
     * @return 
     */
    public static String fromLongDateToString(long longDate){
        String fecha;
        Date date= new Date(longDate);
        
        
        SimpleDateFormat dateFormat= new SimpleDateFormat("dd/MM/yyyy");//formato visual de la fecha"HH:mm:ss MM/dd/yyyy"
        
        fecha= dateFormat.format(date);
        
        return fecha;
    }
    
    /**
     * Transforma el valor Long de una fecha a su forma String con el formato dado
     * @param longDate
     * @param format
     * @return 
     */
    public static String fromLongDateToString(long longDate,String format){
        String fecha;
        Date date= new Date(longDate);
        
        
        SimpleDateFormat dateFormat= new SimpleDateFormat(format);//formato visual de la fecha"HH:mm:ss MM/dd/yyyy"
        
        fecha= dateFormat.format(date);
        
        return fecha;
    }
    
    /**
     * Transforma el valor Long de una hora a su forma String con formato "hh:mm:ss aa"
     * @param longHour
     * @return 
     */
    public static String fromLongHourToString(long longHour){
        String hora;
        Time time = new Time(longHour);
        
        SimpleDateFormat dateFormatHora= new SimpleDateFormat("hh:mm:ss aa");
        
        hora=dateFormatHora.format(time);                
        return hora;
    }
    
    /**
     * Se obtiene un vector con las fechas de inicio y fin del día de hoy 
     * en formato Date sql
     * @return 
     */
    public static java.sql.Date[] getBeginEndToday(){
        java.sql.Date[] todayDates=new java.sql.Date[2];
        long longAux;
        Date auxDate;
        String auxStringDate=getTodayDateString("MM/dd/yyyy");
        String[] stringDates={"00:00:00 ","23:59:59 "};        
        
        for (int i = 0; i < 2; i++) {
            stringDates[i]+=auxStringDate;
            auxDate=new Date(stringDates[i]);
            longAux= auxDate.getTime();
            
            todayDates[i]=new java.sql.Date(longAux);
        }
            
        return todayDates;
    }
    /**
     * recibe dos fechas desde y hasta y las transforma ha formato SQLite
     * para que contemplen todos las fechas en el rango
     * 
     * @param dateFrom
     * @param dateTo
     * @return 
     */ 
    public static java.sql.Date[] getBeginEndDateRange(Date dateFrom, Date dateTo){
        java.sql.Date[] dateRange=new java.sql.Date[2];//vector a devolver
        Date[] dateVector={dateFrom,dateTo};//fechas recividas
        String[] stringDates={"00:00:00 ","23:59:59 "};//rango de horas para asegurar la petición de todas las ventas
        Date dateAux;
        String stringAux;
        long longAux;
        
        for (int i = 0; i < 2; i++) {
            longAux=dateVector[i].getTime();
            stringAux= DateManager.fromLongDateToString(longAux, "MM/dd/yyyy");
            stringDates[i]+=stringAux;
            dateAux= new Date(stringDates[i]);
            longAux=dateAux.getTime();
            dateRange[i]= new java.sql.Date(longAux);
        }
        
        return dateRange; 
    }
    
}
