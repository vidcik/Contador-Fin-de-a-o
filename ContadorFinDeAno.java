import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;


public class ContadorFinDeAno extends JFrame {
    private JLabel label;
    private Timer timer;

    public ContadorFinDeAno() {
        label = new JLabel();
        label.setHorizontalAlignment(JLabel.CENTER);
        add(label);

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarContador();
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setTitle("Contador Fin de Año");
        setLocationRelativeTo(null);
        iniciarContador();
    }

    private void iniciarContador() {
        timer.start();
    }

    private void actualizarContador() {
        Date ahora = new Date();
        Date finDeAno = obtenerFechaFinDeAno();

        long diferencia = finDeAno.getTime() - ahora.getTime();

        if (diferencia > 0) {
            long segundos = diferencia / 1000 % 60;
            long minutos = diferencia / (60 * 1000) % 60;
            long horas = diferencia / (60 * 60 * 1000) % 24;
            long dias = diferencia / (24 * 60 * 60 * 1000);

            String tiempoRestante = String.format("%d días, %02d:%02d:%02d", dias, horas, minutos, segundos);
            label.setText(tiempoRestante);
        } else {
            label.setText("¡Feliz Año Nuevo!");
            timer.stop();
            mostrarImagenFelizAnoNuevo();
        }
    }

    private Date obtenerFechaFinDeAno() {
        Calendar calendario = Calendar.getInstance();
        calendario.set(Calendar.YEAR, calendario.get(Calendar.YEAR) + 1);
        calendario.set(Calendar.MONTH, Calendar.JANUARY);
        calendario.set(Calendar.DAY_OF_MONTH, 1);
        calendario.set(Calendar.HOUR_OF_DAY, 0);
        calendario.set(Calendar.MINUTE, 0);
        calendario.set(Calendar.SECOND, 0);
        calendario.set(Calendar.MILLISECOND, 0);

        return calendario.getTime();
    }

    private void mostrarImagenFelizAnoNuevo() {
        ImageIcon imagen = new ImageIcon("C:\\Users\\vic54\\Downloads\\360_F_183916823_8Y20amxjHCpzjha3scSb84mIauWIiywh.jpg");
        JLabel labelImagen = new JLabel(imagen);
        JOptionPane.showMessageDialog(this, labelImagen, "¡Feliz Año Nuevo Mi Gente !", JOptionPane.PLAIN_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ContadorFinDeAno().setVisible(true);
            }
        });
    }
}
