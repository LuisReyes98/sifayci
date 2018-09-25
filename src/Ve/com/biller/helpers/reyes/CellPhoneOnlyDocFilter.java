package Ve.com.biller.helpers.reyes;

/**
 *
 * @author Luis Reyes
 */
public class CellPhoneOnlyDocFilter extends CustomDocFilter {
    /**
     * permite la insercion de números y los simbolos
     * +
     * -
     * .
     */
    public CellPhoneOnlyDocFilter() {
    }
    
    @Override
    public boolean test(String word) {
        char[] charVector= word.toCharArray();
        boolean retornar=true;
        for (int i = 0; i < charVector.length; i++) {
            try{
                int aux= Integer.parseInt(String.valueOf(charVector[i]));//si el caracter es un numero                
            }catch(NumberFormatException e){
                if (!(charVector[i]=='-'||charVector[i]=='+'||charVector[i]=='.')) {
                    retornar=false;//es falso si no es "-" o "+" o "."
                }
            }     
            
        }
        
        return retornar;
    }
    
}
