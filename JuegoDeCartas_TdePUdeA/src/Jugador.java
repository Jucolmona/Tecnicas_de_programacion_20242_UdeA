import javax.swing.*;
import java.util.Random;

public class Jugador {

    private Random random;
    private String mensaje;
    private Carta[] cartas;

    private String[] figuras;
    private NombreCarta[] cartasFigura;

    public Jugador(){
        random = new Random();
    }

    public void repartir(){
        /*
        * Este método permite crear instancias de las 10 cartas simulando la repartición
        * de cartas. Es preciso tener en cuenta que cada objeto Carta generado, el valor
        * del indice que identifica es aleatorio
        *
        * LOGICA
        * 1. Instanaciar el arreglo cartas con un tamaño definido
        * 2. Instanciar las 10 cartas con un ciclo for, esta operación simulará la repartición
        * de cartas
         */

        cartas = new Carta[10];
        for(int i=0;i<cartas.length;i++){
            cartas[i] = new Carta(random);
        }
    }

    public void mostrarCarta(JPanel panel, boolean tapada){
        /*
        *permite ver las imagenes de las cartas que tiene el jugador
        *LOGICA
        * 1. Limpiar panel
        * 2. Mostrar cada carta
        * 2.1. Redibujar panel
         */
        panel.removeAll();
        for(int i=0;i<cartas.length;i++){
            cartas[i].mostrarCarta(5+i*40, 5, panel, tapada);
            panel.repaint();
        }
    }

    private void verificarFiguras(int numeroFiguras, int cont[]) {
        if (numeroFiguras > 0) {
            figuras = new String[numeroFiguras];
            cartasFigura = new NombreCarta[numeroFiguras];

            int numFig = 0;
            for (int i = 0; i < 13; i++) {
                if (cont[i] >= 3) {
                    if (cont[i] == 3) {
                        figuras[numFig] = "TERNA";
                    } else if (cont[i] == 4) {
                        figuras[numFig] = "CUARTA";
                    }
                    cartasFigura[numFig] = NombreCarta.values()[i + 1];
                    numFig++;
                }
            }
        }
    }

    private void obtenerFiguras(){
        /*
        * Devuelve un texto que indica las figuras encontradas, es decir, si hubo pares, ternas, cuartas etc.
        * y de que nombres de las cartas
        * LOGICA
        * 1. Iniciar estructura para almacenar las figuras, iniciar en null
        * 2. Declarar los contadores de cada nombre de carta
        * 3. Recorrer cada carta e incrementar el contador del respectivo nombre de la carta
        * 4. Obtener cuantas figuras se encontraron
        * 5. Instanciar las figuras
         */
        figuras = null;
        int[] contadores = new int[13];
        for(int i = 0;i < 10;i++){
            contadores[cartas[i].obtenerNombre().ordinal()-1]++;
        }

        //En este bloque de código se recorren los contadores para ver si se
        //repiten los nombres de las cartas, sin importar las pintas
        int totalFiguras = 0;
        for(int i=0;i<13;i++){
            if(contadores[i]>=3){
                totalFiguras++;
            }
        }

        verificarFiguras(totalFiguras, contadores);


        if(figuras==null){
            mensaje = "NO SE ENCONTRARON FIGURAS";
        }
        else{
            mensaje = "EL JUGADOR TIENE LAS SIGUIENTES FIGURAS:\n";
            for(int i = 0;i<figuras.length;i++){
                mensaje += figuras[i] + " de " + cartasFigura[i].toString()+"\n";
            }
        }
    }

    private void obtenerEscaleras() {
        int[][] contadores = new int[4][13];

        for (int i = 0; i < cartas.length; i++) {
            int pintaIndex = cartas[i].obtenerPinta().ordinal() - 1;
            int valorIndex = cartas[i].obtenerNombre().ordinal() - 1;
            contadores[pintaIndex][valorIndex]++;
        }

        StringBuilder resultado = new StringBuilder();

        for (int pinta = 0; pinta < 4; pinta++) {
            int consecutivas = 0; // Contador de cartas consecutivas
            int inicioEscalera = -1; // Para marcar el inicio de la escalera

            for (int valor = 0; valor < 13; valor++) {
                if (contadores[pinta][valor] > 0) {
                    consecutivas++;
                    if (consecutivas == 1) {
                        inicioEscalera = valor;
                    }
                    if (consecutivas >= 3) {
                        resultado.append("ESCALERA DE ").append(Pinta.values()[pinta]).append(": ");
                        for (int i = inicioEscalera; i <= valor; i++) {
                            resultado.append(NombreCarta.values()[i]).append(" ");
                        }
                        resultado.append("\n");
                    }
                } else {
                    consecutivas = 0;
                }
            }
        }
        mensaje += resultado.length() > 0 ? resultado.toString() : "\n NO SE ENCONTRARON ESCALERAS.";
    }

    private void calcularPuntajeCartasNoAgrupadas() {
        int[] contadores = new int[13];
        int puntaje = 0;
        for (Carta carta : cartas) {
            contadores[carta.obtenerNombre().ordinal() - 1]++;
        }

        for (int i = 0; i < contadores.length; i++) {
            if (contadores[i] < 3) {
                if (i == 0 || i >= 10) {
                    puntaje += contadores[i] * 10;
                } else {
                    puntaje += contadores[i] * (i + 1);
                }
            }
        }
        mensaje += "\n PUNTAJE: " + Integer.toString(puntaje);
    }

    public String obtenerMensaje(){
        obtenerFiguras();
        obtenerEscaleras();
        calcularPuntajeCartasNoAgrupadas();
        return mensaje;
    }

}


