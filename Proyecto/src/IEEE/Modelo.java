package IEEE;

public class Modelo {
//Validar Entrada
    public static void validarIEEE(String ieee) {
        int length = ieee.length();
        if (length != 32 && length != 64) {
            throw new IllegalArgumentException();
        }
    }
//función calcular el ieee de un decimal 32/64 bits
    public static String convertirDecimalAIEEE(double numero, int bits) {
        if (bits == 32) {
            int ieeeBits = Float.floatToIntBits((float) numero);
            return String.format("%32s", Integer.toBinaryString(ieeeBits)).replace(' ', '0');
        } else if (bits == 64) {
            long ieeeBits = Double.doubleToLongBits(numero);
            return String.format("%64s", Long.toBinaryString(ieeeBits)).replace(' ', '0');
        } else {
            throw new IllegalArgumentException();
        }
    }

  //función auxiliar para llenar los campos signo mantisa exponente
    public static String[] dividirIEEE(String ieee) {
        validarIEEE(ieee);
        int expBits = ieee.length() == 32 ? 8 : 11;
        int mantBits = ieee.length() - expBits - 1;

        String signo = ieee.substring(0, 1);
        String exponente = ieee.substring(1, 1 + expBits);
        String mantisa = ieee.substring(1 + expBits);

        return new String[]{signo, exponente, mantisa};
    }
  //función para calcular el decimal apartir ieee 32/64 bits
    public static float ieeeADecimal(String ieee) {
        validarIEEE(ieee);

        int expBits = ieee.length() == 32 ? 8 : 11;
        int bias = ieee.length() == 32 ? 127 : 1023;

        String signoStr = ieee.substring(0, 1);
        String exponenteStr = ieee.substring(1, 1 + expBits);
        String mantisaStr = ieee.substring(1 + expBits);

        int signo = signoStr.equals("1") ? -1 : 1;
        int exponente = Integer.parseInt(exponenteStr, 2) - bias;
        double mantisa = 1.0;

        for (int i = 0; i < mantisaStr.length(); i++) {
            if (mantisaStr.charAt(i) == '1') {
                mantisa += Math.pow(2, -(i + 1));
            }
        }

        return (float) (signo * mantisa * Math.pow(2, exponente));
    }

    public static double ieeeADecimal64(String ieee) {
        validarIEEE(ieee);

        int expBits = 11; // Exponente en IEEE 64
        int bias = 1023;

        String signoStr = ieee.substring(0, 1);
        String exponenteStr = ieee.substring(1, 1 + expBits);
        String mantisaStr = ieee.substring(1 + expBits);

        int signo = signoStr.equals("1") ? -1 : 1;
        int exponente = Integer.parseInt(exponenteStr, 2) - bias;
        double mantisa = 1.0;

        for (int i = 0; i < mantisaStr.length(); i++) {
            if (mantisaStr.charAt(i) == '1') {
                mantisa += Math.pow(2, -(i + 1));
            }
        }

        return signo * mantisa * Math.pow(2, exponente);
    }


}
