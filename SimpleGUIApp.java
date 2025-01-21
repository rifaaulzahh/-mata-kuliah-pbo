import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleGUIApp {
    public static void main(String[] args) {
        // Membuat frame
        JFrame frame = new JFrame("Aplikasi GUI Sederhana");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Membuat label
        final JLabel label = new JLabel("Klik tombol di bawah!");
        label.setBounds(50, 50, 200, 20);
        frame.add(label);

        // Membuat tombol
        JButton button = new JButton("Klik Saya");
        button.setBounds(50, 100, 100, 30);
        frame.add(button);

        // Menambahkan ActionListener ke tombol
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setText("Tombol telah diklik!");
            }
        });

        // Menampilkan frame
        frame.setVisible(true);
    }
}
