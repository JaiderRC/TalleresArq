package ejemplo;
import java.util.*;
public class Model {
	    //Se hace uso de la formula en las láminas por medio de un mapa.
	 private static final double KILO = 1000.0; 
	    private static final double KIBI = 1024.0; 
	    

	    private static final Map<String, Double> SI_UNITS = Map.of(
	        "B", 1.0, "KB", KILO, "MB", Math.pow(KILO, 2),
	        "GB", Math.pow(KILO, 3), "TB", Math.pow(KILO, 4)
	    );

	    private static final Map<String, Double> IEC_UNITS = Map.of(
	        "B", 1.0, "KiB", KIBI, "MiB", Math.pow(KIBI, 2),
	        "GiB", Math.pow(KIBI, 3), "TiB", Math.pow(KIBI, 4)
	    );

	    public static double convert(double value, String from, String to) {
	        Double fromBytes = SI_UNITS.getOrDefault(from, IEC_UNITS.get(from));
	        Double toBytes = SI_UNITS.getOrDefault(to, IEC_UNITS.get(to));
	        if (fromBytes == null || toBytes == null) {
	            throw new IllegalArgumentException("Unidad no válida: " + from + " o " + to);
	        } 
	        return value * (fromBytes / toBytes);
	    }
}
