package Ve.com.biller.helpers.reyes;

/**
 *
 * @author Luis Reyes
 */
public class WordsOnlyDocFilter extends CustomDocFilter {
    

    public WordsOnlyDocFilter(){

    }

    @Override
    public boolean test(String word){
    	char[] auxChar=word.toCharArray(); 
        //boolean retornar=true;
        
    	for (int i=0; i<auxChar.length;i++ ) {
            if (!((auxChar[i]>=65&&auxChar[i]<=90)||(auxChar[i]>=97&&auxChar[i]<=122)||auxChar[i]==32)) {//verdadero si el caracter NO es una letra mayuscula o minuscula o es un spacio SPACE 
                return false;
            }           
    	}
        return true;
    }
}
