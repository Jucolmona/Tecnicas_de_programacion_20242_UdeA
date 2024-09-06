import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Carta {
  /*
    Esta clase posee la funcionalidad asociada a cada carta

    En la clase el número de la carta es generado de forma aleatoria
    Atributos:
    ---------- indice: esta propiedad identifica a cada carta de acuerdo a la siguiente convención
    ------------- trebol: valores entre 1 y 13
    ------------- pica: valores entre 14 y 26
    ------------- corazon: valores entre 27 y 39
    ------------- diamante: valores entre 40 y 52

     */
    private int indice;

    public Carta(Random random){
        this.indice = random.nextInt(52) +1;
    }

    public Pinta obtenerPinta(){
        /*
        * Este método devuelve el valor enumerado Pinta con base en el indice
         */
        if(this.indice <= 13){
            return Pinta.TREBOL;
        } else if (this.indice > 13 && this.indice <= 26){
            return Pinta.PICA;
        } else if (this.indice > 26 && this.indice <= 39){
            return Pinta.CORAZON;
        } else if (this.indice > 39 && this.indice <= 52){
            return Pinta.DIAMANTE;
        }
        return null;
    }

    public NombreCarta obtenerNombre(){
        return null;
    }
  
    public int obtenerValor(){
        return 0;
    }
}
