package Ve.com.biller.helpers.reyes;


/**
 *
 * @author Luis Reyes
 */
public class IntegerOnlyDocFilter extends CustomDocFilter{
    private int maxValue;
    private boolean hasMaxValue;
    private int minValue;
    private boolean hasMinValue;

    public IntegerOnlyDocFilter(int maxValue, boolean hasMaxValue, int minValue, boolean hasMinValue) {
        this.maxValue = maxValue;
        this.hasMaxValue = hasMaxValue;
        this.minValue = minValue;
        this.hasMinValue = hasMinValue;
    }

    public IntegerOnlyDocFilter(int minValue, boolean hasMinValue) {
        this.minValue = minValue;
        this.hasMinValue = hasMinValue;
    }
     public IntegerOnlyDocFilter( boolean hasMaxValue,int maxValue) {
        this.maxValue = maxValue;
        this.hasMaxValue = hasMaxValue;
    }
    
     
    
    @Override
    public boolean test(String word){
        
        try{
            if (word.toCharArray()[0]=='+') {//para que no se use el simbolo +
                return false;
            }
            int numero = Integer.parseInt(word);
            if (hasMaxValue&&hasMinValue) {
                return numero>=minValue&&numero<=maxValue;
            }else if (hasMaxValue) {
                return numero<=maxValue;
            }else if (hasMinValue) {
                return numero >= minValue;
            }else{
                return true;
            }                        
        } catch (NumberFormatException  e) {//evita que se aplique algun valor no numerico
            return false;
        }catch(IndexOutOfBoundsException ex ){//para permitir borrar todo y dejar el campo vacio
            return true;
        }
    }
}
