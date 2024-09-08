import javax.swing.*;
import java.awt.*;

public class Juego extends JFrame {

    private JButton botonRepatir;
    private JButton botonVerificar;
    private JPanel panelJugador1;
    private JPanel panelJugador2;
    private JPanel panelBotones;
    private JTabbedPane tableroJugadores;

    public Juego(){

        this.botonRepatir = new JButton();
        this.botonVerificar = new JButton();
        this.panelJugador1 = new JPanel();
        this.panelJugador2 = new JPanel();
        this.panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        this.tableroJugadores = new JTabbedPane();

        //----- DEFINIR CARACTERISTICAS VISUALES DEL JFRAME -----
        this.setSize(600, 300);
        this.setTitle("Apuntado");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        this.botonRepatir.setText("Repartir");
        this.panelBotones.add(this.botonRepatir);
        this.botonVerificar.setText("Verificar");
        this.panelBotones.add(this.botonVerificar);

        this.panelJugador1.setBackground(Color.cyan);
        this.panelJugador2.setBackground(Color.cyan);
        this.tableroJugadores.addTab("Martín Estrada Contreras", panelJugador1);
        this.tableroJugadores.addTab("Raúl Vidal", panelJugador2);

        this.add(panelBotones, BorderLayout.NORTH);
        this.add(tableroJugadores, BorderLayout.CENTER);

        //Instanciar los Jugadores
        for(int i = 0;i<jugadores.length;i++){
            jugadores[i] = new Jugador();
        }

        //Creacion de funcionalidad del boton repartir
        this.botonRepatir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i = 0;i < jugadores.length;i++){
                    jugadores[i].repartir();
                }
                jugadores[0].mostrarCarta(panelJugador1, false);
                jugadores[1].mostrarCarta(panelJugador2, false);
            }
        });

        //Creación funcionalidad del boton verificar
        this.botonVerificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int pestaña = tableroJugadores.getSelectedIndex();
                JOptionPane.showMessageDialog(new JFrame(), jugadores[pestaña].obtenerFiguras());
            }
        });
        
        this.setVisible(true);
    }
}
