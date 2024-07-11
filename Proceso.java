import java.io.IOException;
import java.util.Scanner;

public class Proceso  {

    private String divisaBase;//opcion
    String divisaCambio;
    double respuesta;
    private Scanner lectura;
    private ConsultaDivisa consultaDivisa;
private HistorialDivisas historialDivisas;

    public Proceso(String opcion, Scanner lectura, ConsultaDivisa consultaDivisa, HistorialDivisas historialDivisas) {
        this.divisaBase = opcion;
        this.lectura = lectura;
        this.consultaDivisa = consultaDivisa;
        this.historialDivisas = historialDivisas;
    }
    public void   prosesoCambio(){

        try {

            consultaDivisa.ValidacionExistencia(divisaBase);
            System.out.println("lista de divisas disponibles para cambio");
            listaDivisasDisponibles(consultaDivisa,divisaBase);
            System.out.println("ingrese la divisa a cambiar");
            divisaCambio = lectura.next().toUpperCase();
            consultaDivisa.ValidacionExistencia(divisaCambio);

            Moneda moneda = consultaDivisa.busquedaDivisa(divisaBase, divisaCambio);
            consultaDivisa.busquedaDivisa(divisaBase,divisaCambio);
            System.out.println("Ingrese la cantidad a convertir");
            double valorBase = lectura.nextDouble();

            respuesta = consultaDivisa.calculo(valorBase, moneda, divisaCambio, divisaBase);

            GeneradorDeArchivo generador = new GeneradorDeArchivo();
            generador.guardarJson(moneda);
            historialDivisas.agregarConsulta(divisaBase,divisaCambio,respuesta);
            //Principal principal = new Principal(historialDivisas);
        } catch (IOException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        } catch (Exception e) {
            System.out.println("error en la consulta del historial");
            System.out.println(e);
            //throw new RuntimeException(e);
        }
    }
    public void listaDivisasDisponibles(ConsultaDivisa consultaDivisa,String divisaBase) throws IOException {
        consultaDivisa.listaDivisaCambio(divisaBase);
    }
}
