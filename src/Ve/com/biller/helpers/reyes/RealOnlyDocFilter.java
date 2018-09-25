package Ve.com.biller.helpers.reyes;

/**
 *
 * @author Luis Reyes
 */
public class RealOnlyDocFilter extends CustomDocFilter {
    private boolean hasMaxValue;
    private boolean hasMinValue;
    private double maxValue;
    private double minValue;
    
    /**
     * this class will only allowed
     * positive Doubles to pass the filter
     * it is set for spanish entrys
     * aka the character ',' defines a decimal
     * instead of the usual '.' 
     * @param hasMaxValue
     * @param hasMinValue
     * @param maxValue
     * @param minValue
     */      
    public RealOnlyDocFilter(boolean hasMaxValue, boolean hasMinValue, double maxValue, double minValue) {
        this.hasMaxValue = hasMaxValue;
        this.hasMinValue = hasMinValue;
        this.maxValue = maxValue;
        this.minValue = minValue;        
    }   
    /**
     * valor maximo 
     * @param hasMaxValue
     * @param maxValue 
     */
    public RealOnlyDocFilter(boolean hasMaxValue, double maxValue) {
        this.hasMaxValue = hasMaxValue;
        this.maxValue = maxValue;
    }
    /**
     * valor minimo 
     * @param minValue
     * @param hasMinValue 
     */
    public RealOnlyDocFilter( double minValue, boolean hasMinValue) {
        this.minValue = minValue;          
        this.hasMinValue = hasMinValue;                      
    }   
    public RealOnlyDocFilter(){
        hasMinValue=false;
        hasMaxValue=false;
    }
          
    
    private String characterSwap(String text,char char1, char char2){
        String aux="";
        char[] words;
        words = text.toCharArray();
        int i=0;
        while(i<text.length()){
            if (words[i]==char1){
                words[i]=char2;                
            }else if(words[i]==char2){
                words[i]=char1;
            }
            aux+=words[i++];       
        }        
        return aux;
    }
    
    @Override
    public boolean test(String text){
        text=characterSwap(text,',','.');
        int i=0;
        try {
            double numero = Double.parseDouble(text);
           
            int size=text.length()-1;  
            char[] aux=text.toCharArray();
            if (aux[size]==('f')||
                    aux[size]==('F')||
                        aux[size]==('d')||
                            aux[size]==('D')||
                                aux[0]==('+')) {
                //para que el caracter final no sea ni f F d D ni el inicial sea - +           
                return false;                        
            }else{ 
               
                while(i<text.length()){
                    if (aux[i]=='e'||aux[i]=='E') {//para evitar que la e | E se ponnga en alguna posicion ya que representa el exponencial de la función 
                        return false;
                    }
                    i++;
                }
                //si no puede ejecutar este metodo el valor ingresado no es un numero y se atrapa el error para regresar el valor falso
                if (hasMaxValue&&hasMinValue) {
                    return numero >= minValue&&numero<=maxValue;
                }else if (hasMaxValue) {
                    return numero <= maxValue;
                }else if (hasMinValue) {
                    return numero >= minValue;
                }else{                    
                    return true;//no tiene restricciones 
                }
                
            }            
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return false;
        }
        
    }
    
    
    
}
