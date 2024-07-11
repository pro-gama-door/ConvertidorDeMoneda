import java.io.IOException;
import java.util.Scanner;

public class Principal {
    public HistorialDivisas datos;
    public Principal(HistorialDivisas historialDivisas) {
    this.datos = historialDivisas;
    }

    public static void main(String[] args)  throws IOException, InterruptedException {

        Scanner lectura = new Scanner(System.in);
        ConsultaDivisa consultaDivisa = new ConsultaDivisa();

        String opcion;
        int accion;
        int retorno;
        double salir;
        HistorialDivisas historialDivisas = new HistorialDivisas();

        retorno=0;
        while (retorno==0){
            System.out.println("Bienvenido al sistema de conversión de moneda Gale");
            System.out.println("Ingrese la accion que desea realizar");
            System.out.println("(1) Conversión de moneda"+"\n"+
                    "(2) Historial de converciones"+"\n"+
                    "(0) Para salir"+"\n"
                    +"******************");
            accion = lectura.nextInt();
            switch (accion) {
                case 0:
                    System.out.println("Gracias por usar el sistema :)");
                    retorno =1;
                    break;
                case 1:
                    salir = 1;
                    while (salir == 1) {
                        System.out.println("Divisas disponibles");
                        System.out.println("ARS: Pesos Argentinos" + "\n"
                                + "BOB: Boliviando boliviano" + '\n'
                                + "BRL: Real brasileño" + '\n'
                                + "CLP: Peso chileno" + '\n'
                                + "COP: Peso colombiano" + '\n'
                                + "USD: Dólar Estadunidense" + '\n');
                        System.out.println("Seleccione la divisa base");

                        opcion = lectura.next().toUpperCase();
                        Proceso proceso = new Proceso(opcion,lectura,consultaDivisa,historialDivisas);
                        try {
                            switch (opcion) {
                                case "ARS":
                                    proceso.prosesoCambio();
                                    break;
                                case "BOB":
                                    proceso.prosesoCambio();
                                    break;
                                case "BRL":
                                    proceso.prosesoCambio();
                                    break;
                                case "CLP":
                                    proceso.prosesoCambio();
                                    break;
                                case "COP":
                                    proceso.prosesoCambio();
                                    break;
                                case "USD":
                                    proceso.prosesoCambio();
                                    break;
                                default:
                                    System.out.println("Divisa no diponible");
                            }
                            System.out.println("*****************************************"+"\n"
                                    +"Ingrese 0 para ir al menu" + "\n" + "ingrese 1 para continuar");
                            salir = lectura.nextInt();
                        } catch (Exception e) {
                            System.out.println("*****************************************"+"\n"
                                    +"Ingrese 0 para ir al menu" + "\n" + "ingrese 1 para continuar");
                            salir = lectura.nextInt();
                        }
                    }
                    break;
                case 2:
                    salir = 1;
                    while (salir == 1) {
                        System.out.println("Bienvenido al historial de consultas " + "\n" + "lista de consultas realizadas"
                                + "\n" + "*********************************");
                        historialDivisas.obtenerHistorial();
                        System.out.println("Ingrese 0 para ir al menu" + "\n" + "ingrese 1 para continuar");
                        salir = lectura.nextInt();
                    }
                    break;
                default:
                    System.out.println("ingrese una obvion disponible");
                    retorno = 0;
            }

        }


    }

}





