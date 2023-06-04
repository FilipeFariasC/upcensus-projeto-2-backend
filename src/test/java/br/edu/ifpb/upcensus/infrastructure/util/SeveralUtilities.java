package br.edu.ifpb.upcensus.infrastructure.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SeveralUtilities {
	
	protected Random random = new Random();
	
	public static String getTestTemplate() {
		Map<Integer, String> mappings = new HashMap();
		mappings.put(1,"aluno.nome");
		mappings.put(2,"aluno.matricula");
		mappings.put(3,"aluno.idade");
		mappings.put(4,"aluno.situacao");
		mappings.put(5,"aluno.cota");
		Random random = new Random();
		return mappings.get(random.nextInt(5));
	}

	public static String getAlphaNumericString(int n)
    {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int index
                = (int)(AlphaNumericString.length()
                        * Math.random());
            sb.append(AlphaNumericString
                          .charAt(index));
        }
        return sb.toString();
    }
	
	public static String getNumericString(int n)
    {
        String NumericString ="0123456789";
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int index
                = (int)(NumericString.length()
                        * Math.random());
            sb.append(NumericString
                          .charAt(index));
        }
        return sb.toString();
    }
	
	public static String getSpecialString(int n)
    {
        String AlphaNumericString = "!@#$#%¨~´[]/?.;:.><&*()_+";
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int index
                = (int)(AlphaNumericString.length()
                        * Math.random());
            sb.append(AlphaNumericString
                          .charAt(index));
        }
        return sb.toString();
    }
}
