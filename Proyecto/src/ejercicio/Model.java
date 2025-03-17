package ejercicio;

import java.util.HashMap;

public class Model {

	public static String sumStrings(String num1, String num2) {
	    StringBuilder result = new StringBuilder();
	    int carry = 0, i = num1.length() - 1, j = num2.length() - 1;

	    while (i >= 0 || j >= 0 || carry > 0) {
	        int sum = carry;
	        if (i >= 0) sum += num1.charAt(i--) - '0';
	        if (j >= 0) sum += num2.charAt(j--) - '0';

	        result.insert(0, sum % 10);
	        carry = sum / 10;
	    }
	    return result.toString();
	}

	public static String multiplyStrings(String num1, String num2) {
	    int len1 = num1.length();
	    int len2 = num2.length();
	    int[] result = new int[len1 + len2];

	    for (int i = len1 - 1; i >= 0; i--) {
	        for (int j = len2 - 1; j >= 0; j--) {
	            int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
	            int sum = mul + result[i + j + 1];

	            result[i + j + 1] = sum % 10;
	            result[i + j] += sum / 10;
	        }
	    }

	    StringBuilder product = new StringBuilder();
	    for (int num : result) {
	        if (!(product.length() == 0 && num == 0)) { // Evitar ceros iniciales
	            product.append(num);
	        }
	    }

	    return product.length() == 0 ? "0" : product.toString();
	}
	
	public static String divideStrings(String dividend, String divisor) throws Exception {
	    if (divisor.equals("0")) throw new Exception("División por cero no permitida");
	    if (dividend.equals("0")) return "0";

	    StringBuilder result = new StringBuilder();
	    String current = "";
	    
	    for (int i = 0; i < dividend.length(); i++) {
	        current += dividend.charAt(i);
	        current = removeLeadingZeros(current);
	        
	        int quotient = 0;
	        while (compareStrings(current, divisor) >= 0) {
	            current = subtractStrings(current, divisor);
	            quotient++;
	        }
	        result.append(quotient);
	    }

	    return removeLeadingZeros(result.toString());
	}

	private static String removeLeadingZeros(String str) {
	    int i = 0;
	    while (i < str.length() - 1 && str.charAt(i) == '0') {
	        i++;
	    }
	    return str.substring(i);
	}

	private static int compareStrings(String num1, String num2) {
	    if (num1.length() > num2.length()) return 1;
	    if (num1.length() < num2.length()) return -1;
	    return num1.compareTo(num2);
	}

	private static int compareStrings2(String num1, String num2) {
        // Primero comparar por longitud
        if (num1.length() > num2.length()) {
            return 1;
        } else if (num1.length() < num2.length()) {
            return -1;
        }
        // Si las longitudes son iguales, comparar dígito por dígito
        for (int i = 0; i < num1.length(); i++) {
            int digit1 = num1.charAt(i) - '0';
            int digit2 = num2.charAt(i) - '0';
            if (digit1 > digit2) {
                return 1;
            } else if (digit1 < digit2) {
                return -1;
            }
        }
        return 0; // Son iguales
    }

    
    public static String subtractStrings(String num1, String num2) {
        
        int comparison = compareStrings2(num1, num2);
        if (comparison < 0) {
            return "-" + subtractStrings(num2, num1);
        } else if (comparison == 0) {
            return "0"; 
        }

        StringBuilder result = new StringBuilder();
        int carry = 0;
        int i = num1.length() - 1;
        int j = num2.length() - 1;

        while (i >= 0 || j >= 0) {
            int digit1 = (i >= 0) ? num1.charAt(i) - '0' : 0;
            int digit2 = (j >= 0) ? num2.charAt(j) - '0' : 0;

            // Aplicar el préstamo (borrow)
            digit1 -= carry;
            if (digit1 < digit2) {
                digit1 += 10; // Tomar prestado de la siguiente posición
                carry = 1;
            } else {
                carry = 0;
            }

            int diff = digit1 - digit2;
            result.append(diff);

            i--;
            j--;
        }

        // Eliminar ceros a la izquierda del resultado
        result.reverse();
        while (result.length() > 1 && result.charAt(0) == '0') {
            result.deleteCharAt(0);
        }

        return result.toString();
    }

	public static String decimalToBinary(String decimal) throws Exception{
	    if (decimal.equals("0")) return "0";
	    
	    if(decimal.isEmpty()) throw new Exception("Tienes que agregar un valor");
	    if(decimal.charAt(0) == '-') throw new Exception("No se manejan negativos, solamente restas que sean mayores a num1, y se devuelven como negativos decimales (Formato = Decimal)");	    
	    StringBuilder binary = new StringBuilder();
	    
	    while (!decimal.equals("0")) {
	        StringBuilder divisionResult = new StringBuilder();
	        int residuo = 0;
	        
	        //Forma manual de hacer una division
	        for (int i = 0; i < decimal.length(); i++) {
	            int digito = Character.getNumericValue(decimal.charAt(i));
	            if(digito > 9) throw new Exception("No es un valor decimal");
	            int numero = residuo * 10 + digito;
	            residuo = numero % 2;
	            divisionResult.append(numero / 2);
	        }
	        
	        // Eliminar ceros iniciales pero asegurarse de que no quede vacío
	        while (divisionResult.length() > 0 && divisionResult.charAt(0) == '0') {
	            divisionResult.deleteCharAt(0);
	        }
	        
	        binary.insert(0, residuo);
	        decimal = divisionResult.length() == 0 ? "0" : divisionResult.toString();
	    }
	    return binary.toString();
	}
	
	public static String binaryToDecimal(String binary) throws Exception {
	    if (binary.equals("0")) return "0";

	    if(binary.isEmpty()) throw new Exception("Tienes que agregar un valor");
	    if(binary.charAt(0) == '-') throw new Exception("No se manejan negativos, solamente restas que sean mayores a num1, y se devuelven como negativos decimales (Formato = Decimal)");

	    
	    String decimal = "0"; // Se inicia en 0 como String
	    String powerOfTwo = "1"; // 2^0 = 1 como String

	    for (int i = binary.length() - 1; i >= 0; i--) {
	        int digit = Character.getNumericValue(binary.charAt(i));
	        if (digit > 1) throw new Exception("No es un número binario");

	        if (digit == 1) {
	            decimal = sumStrings(decimal, powerOfTwo);
	        }

	        powerOfTwo = multiplyStrings(powerOfTwo, "2");
	    }

	    return decimal;
	}
	
	public static String hexToDecimal(String hex) throws Exception {
		if (hex.equals("0")) return "0";
		if(hex.isEmpty()) throw new Exception("Tienes que agregar un valor");
		if(hex.charAt(0) == '-') throw new Exception("No se manejan negativos, solamente restas que sean mayores a num1, y se devuelven como negativos decimales (Formato = Decimal)");
		String decimal = "0";
		String powerOfSixteen = "1";
		
		HashMap<Character, Integer> hexMap = new HashMap<>();
	    String hexChars = "0123456789ABCDEF";
	    for (int i = 0; i < hexChars.length(); i++) {
	        hexMap.put(hexChars.charAt(i), i);
	    }
	    
	    for(int i = hex.length()-1;i>=0;i--) {
	    	char digit = hex.charAt(i);
	        if (!hexMap.containsKey(digit)) throw new Exception("No es un número hexadecimal");

	        if(digit != '0') {
	        	String tmp = multiplyStrings(hexMap.get(digit)+"", powerOfSixteen);
	        	decimal = sumStrings(decimal, tmp);
	        }
	        powerOfSixteen = multiplyStrings(powerOfSixteen, "16");
	    }
	    return decimal;
	}

	public static String decimalToHex(String decimal) throws Exception{
		if (decimal.equals("0")) return "0";
		if(decimal.isEmpty()) throw new Exception("Tienes que agregar un valor");
		if(decimal.charAt(0) == '-') throw new Exception("No se manejan negativos, solamente restas que sean mayores a num1, y se devuelven como negativos decimales (Formato = Decimal)");
		
	    StringBuilder hex = new StringBuilder();
	    String hexChars = "0123456789ABCDEF";
	    
	    while (!decimal.equals("0")) {
	        StringBuilder divisionResult = new StringBuilder();
	        int residuo = 0;
	        
	        //Forma manual de hacer una division
	        for (int i = 0; i < decimal.length(); i++) {
	            int digito = Character.getNumericValue(decimal.charAt(i));
	            if(digito > 9) throw new Exception("No es un valor decimal");
	            int numero = residuo * 10 + digito;
	            residuo = numero % 16;
	            divisionResult.append(numero / 16);
	        }
	        	        
	        // Eliminar ceros iniciales pero asegurarse de que no quede vacío
	        while (divisionResult.length() > 0 && divisionResult.charAt(0) == '0') {
	            divisionResult.deleteCharAt(0);
	        }
	        	        
	        hex.insert(0, hexChars.charAt(residuo));
	        decimal = divisionResult.length() == 0 ? "0" : divisionResult.toString();
	    }
	    return hex.toString();
		
	}

	public static String octalToDecimal(String octal) throws Exception {
		if (octal.equals("0")) return "0";
		if(octal.isEmpty()) throw new Exception("Tienes que agregar un valor");
		if(octal.charAt(0) == '-') throw new Exception("No se manejan negativos, solamente restas que sean mayores a num1, y se devuelven como negativos decimales (Formato = Decimal)");
	    String decimal = "0";
	    String powerOfEight = "1";
	    for (int i = octal.length() - 1; i >= 0; i--) {
	        int digit = Character.getNumericValue(octal.charAt(i));
	        if (digit > 7) throw new Exception("No es un número octal");

	        if (digit > 0) {
	            String tmp = multiplyStrings(digit+"", powerOfEight);
	            decimal = sumStrings(decimal,tmp);
	        }
	        powerOfEight = multiplyStrings(powerOfEight, "8");
	    }
	    return decimal;
	}
	
	public static String decimalToOctal(String decimal) throws Exception{
		if (decimal.equals("0")) return "0";
		if(decimal.isEmpty()) throw new Exception("Tienes que agregar un valor");
		if(decimal.charAt(0) == '-') throw new Exception("No se manejan negativos, solamente restas que sean mayores a num1, y se devuelven como negativos decimales (Formato = Decimal)");

	    StringBuilder octal = new StringBuilder();
	    
	    while (!decimal.equals("0")) {
	        StringBuilder divisionResult = new StringBuilder();
	        int residuo = 0;
	        
	        //Forma manual de hacer una division
	        for (int i = 0; i < decimal.length(); i++) {
	            int digito = Character.getNumericValue(decimal.charAt(i));
	            if(digito > 9) throw new Exception("No es un valor decimal");
 	            int numero = residuo * 10 + digito;
	            residuo = numero % 8;
	            divisionResult.append(numero / 8);
	        }
	        
	        // Eliminar ceros iniciales pero asegurarse de que no quede vacío
	        while (divisionResult.length() > 0 && divisionResult.charAt(0) == '0') {
	            divisionResult.deleteCharAt(0);
	        }
	        
	        octal.insert(0, residuo);
	        decimal = divisionResult.length() == 0 ? "0" : divisionResult.toString();
	    }
	    return octal.toString();
	}
	
}
