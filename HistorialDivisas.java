import java.util.ArrayList;
public class HistorialDivisas  {
    public ArrayList<String> historial = new ArrayList<String >();
    public void agregarConsulta(String divisaBase, String divisaCambio, double respuesta) {
        String formatoHistorial = "La Divisa base: "+divisaBase+"\n"+
                "El cambio de divisa: "+divisaCambio+"\n"+
                "El resultado de la convercion: "+respuesta+"\n"+
                "***************************************";
        historial.add(formatoHistorial);
        obtenerHistorial();
    }
    public void obtenerHistorial() {
        System.out.println(historial);
    }
}
