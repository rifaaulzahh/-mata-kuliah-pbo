import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BacaFileApp {
    public static void main(String[] args) {
        // Membuat frame
        final JFrame frame = new JFrame("Aplikasi Baca File");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Membuat komponen
        JButton button = new JButton("Baca File");
        final JTextArea textArea = new JTextArea(10, 30);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Menambahkan ActionListener ke tombol
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    try (BufferedReader reader = new BufferedReader(new FileReader(fileChooser.getSelectedFile()))) {
                        textArea.setText(""); // Membersihkan area teks
                        String line;
                        while ((line = reader.readLine()) != null) {
                            textArea.append(line + "\n");
                        }
                    } catch (IOException ex) {
                        textArea.setText("Error: File tidak ditemukan atau tidak bisa dibaca.");
                    }
                }
            }
        });

        // Menambahkan komponen ke frame
        JPanel panel = new JPanel();
        panel.add(button);
        panel.add(scrollPane);
        frame.add(panel);

        // Menampilkan frame
        frame.setVisible(true);
    }
}