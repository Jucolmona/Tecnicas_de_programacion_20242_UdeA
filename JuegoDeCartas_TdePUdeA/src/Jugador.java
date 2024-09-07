import javax.swing.*;
import java.util.Random;

public class Jugador {
    private Random randon;
    private Carta[] cartas;

    private Pinta[] figuras;
    private NombreCarta[] cartasFigura;

    public Jugador(){
        randon = new Random();
    }

    public void repartir(){
        /*
        * Este método permite crear instancias de las 10 cartas simulando la repartición
        * de cartas. Es preciso tener en cuenta que cada objeto Carta generado, el valor
        * del indice que identifica es aleatorio
        *
        * LOGICA
        * 1 Instanciar 10 cartas con un ciclo for
         */

        cartas = new Carta[10];
        for(int i=0;i<cartas.length;i++){
            cartas[i] = new Carta(randon);
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

    public String obtenerFiguras(){
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
        int totalFiguras = 0;
        for(int i=0;i<13;i++){
            if(contadores[i]>=3){
                totalFiguras++;
            }
        }

        if(totalFiguras>0){
            figuras = new Pinta[totalFiguras];
            cartasFigura = new NombreCarta[totalFiguras];
            totalFiguras = 0;

            for(int i = 0;i < 13;i++){
                if(contadores[i]>=3){
                    figuras[totalFiguras] = Pinta.values()[contadores[i]];
                    cartasFigura[totalFiguras] = NombreCarta.values()[i+1];
                    totalFiguras++;
                }
            }
            String mensaje = "";
            if(figuras==null){
                mensaje = "No hay figuras";
            }
            else{
                mensaje = "El jugador tiene las siguientes figuras:\n";
                for(int i = 0;i<figuras.length;i++){
                    mensaje += figuras[i].name() + "de" + cartasFigura[i].toString()+"\n";
                }
            }
            return mensaje;
        }
        return "";
    }
}
