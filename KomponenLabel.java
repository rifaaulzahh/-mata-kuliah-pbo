import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class KomponenLabel {
public static void main(String[ ] args) {
JFrame frame = new JFrame("Membuat Label");
JLabel label = new JLabel("Pemrograman Berorientasi Obyek");
frame.getContentPane().add(label);
frame.pack();
frame.setVisible(true);
frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
}
} 