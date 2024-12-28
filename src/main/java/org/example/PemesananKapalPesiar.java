package org.example;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PemesananKapalPesiar {
    private static final List<String> daftarPesanan = new ArrayList<>();
    private static final String FILE_NAME = "PemesananKapalPesiar.docx";

    public static void main(String[] args) {
        bacaPesananDariWord();
        SwingUtilities.invokeLater(PemesananKapalPesiar::tampilkanFrameRute);
    }

    private static void tampilkanFrameRute() {
        JFrame frameRute = new JFrame("Rute Perjalanan");
        frameRute.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameRute.setSize(500, 400);

        // Membuat panel dengan gambar sebagai background
        JPanel panelRute = new JPanel() {
            // Override paintComponent untuk menggambar gambar sebagai background
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon("D:\\TUGAS LAB\\Semester 3\\Pemrograman Lanjut\\Modul 6\\UAP Tugas\\pesiar.jpg"); // Pastikan path gambar benar
                Image img = icon.getImage();  // Mengambil gambar asli
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);  // Gambar dengan ukuran panel
            }
        };

        panelRute.setLayout(new GridLayout(4, 1, 10, 10));
        panelRute.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel labelRute = new JLabel("Pilih Rute:", JLabel.CENTER);
        labelRute.setFont(new Font("Arial", Font.BOLD, 20));
        String[] rute = {"Lokal: Semarang - Pontianak", "Lokal: Semarang - Pasuruan", "Lokal: Semarang - Surabaya"};
        JComboBox<String> comboBoxRute = new JComboBox<>(rute);
        JButton tombolLanjut = new JButton("Lanjut");
        JButton tombolDaftarPesanan = new JButton("Lihat Pesanan");

        tombolLanjut.setBackground(Color.GREEN);
        tombolLanjut.setForeground(Color.WHITE);
        tombolLanjut.setFont(new Font("Arial", Font.BOLD, 14));

        tombolDaftarPesanan.setBackground(Color.BLUE);
        tombolDaftarPesanan.setForeground(Color.WHITE);
        tombolDaftarPesanan.setFont(new Font("Arial", Font.BOLD, 14));

        tombolLanjut.addActionListener(e -> {
            String ruteTerpilih = (String) comboBoxRute.getSelectedItem();
            frameRute.dispose();
            tampilkanFrameKapalDanKabin(ruteTerpilih);
        });

        tombolDaftarPesanan.addActionListener(e -> {
            frameRute.dispose();
            tampilkanFrameDaftarPesanan();
        });

        panelRute.add(labelRute);
        panelRute.add(comboBoxRute);
        panelRute.add(tombolLanjut);
        panelRute.add(tombolDaftarPesanan);

        frameRute.add(panelRute);
        frameRute.setLocationRelativeTo(null);
        frameRute.setVisible(true);
    }


    private static void tampilkanFrameKapalDanKabin(String ruteTerpilih) {
        JFrame frame = new JFrame("Kapal, Jadwal & Kabin");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        // Membuat panel dengan gambar sebagai background
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon("D:\\TUGAS LAB\\Semester 3\\Pemrograman Lanjut\\Modul 6\\UAP Tugas\\budi.jpg"); // Pastikan path gambar benar
                Image img = icon.getImage();  // Mengambil gambar asli
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);  // Gambar dengan ukuran panel
            }
        };

        panel.setLayout(new GridLayout(6, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel labelKapal = new JLabel("Pilih Kapal & Jadwal:", JLabel.CENTER);
        labelKapal.setFont(new Font("Arial", Font.BOLD, 20));

        String[] kapal = null;
        if (ruteTerpilih.equals("Lokal: Semarang - Pontianak")) {
            kapal = new String[]{"Ocean Star: Rabu, 08.30", "Ocean Star: Kamis, 19.45"};
        } else if (ruteTerpilih.equals("Lokal: Semarang - Pasuruan")) {
            kapal = new String[]{"Sea Explorer: Sabtu, 12.30", "Sea Explorer: Senin, 21.00"};
        } else if (ruteTerpilih.equals("Lokal: Semarang - Surabaya")) {
            kapal = new String[]{"Ocean Star: Selasa, 13.00", "Sea Explorer: Jumat, 07.00"};
        }

        JComboBox<String> comboBoxKapal = new JComboBox<>(kapal);

        JLabel labelKabin = new JLabel("Pilih Kelas Kabin:", JLabel.CENTER);
        labelKabin.setFont(new Font("Arial", Font.BOLD, 20));
        String[] kabin = {
                "Ekonomi : Rp 1.000.000 / Orang",
                "Bisnis : Rp 1.500.000 / Orang",
                "Premium : Rp 2.000.000 / Orang",
                "Suite : Rp 2.500.000 / Orang"
        };
        JComboBox<String> comboBoxKabin = new JComboBox<>(kabin);

        JLabel labelJumlahPenumpang = new JLabel("Jumlah Penumpang:");
        JTextField jumlahPenumpangField = new JTextField(5);

        JButton tombolLanjut = new JButton("Lanjut");
        JButton tombolKembali = new JButton("Kembali");

        tombolLanjut.setBackground(Color.GREEN);
        tombolLanjut.setForeground(Color.WHITE);
        tombolLanjut.setFont(new Font("Arial", Font.BOLD, 14));

        tombolKembali.setBackground(Color.RED);
        tombolKembali.setForeground(Color.WHITE);
        tombolKembali.setFont(new Font("Arial", Font.BOLD, 14));

        tombolLanjut.addActionListener(e -> {
            try {
                int jumlahPenumpang = Integer.parseInt(jumlahPenumpangField.getText());
                if (jumlahPenumpang <= 0) {
                    throw new NumberFormatException("Jumlah penumpang harus lebih dari 0");
                }

                String selectedKapal = (String) comboBoxKapal.getSelectedItem();
                String selectedKabin = (String) comboBoxKabin.getSelectedItem();
                int hargaPerOrang = getHargaKabin(selectedKabin);

                int totalHarga = hargaPerOrang * jumlahPenumpang;
                String pesanan = "Rute: " + ruteTerpilih + ", Kapal: " + selectedKapal + ", Kabin: " + selectedKabin + ", Jumlah: " + jumlahPenumpang + ", Total: Rp " + totalHarga;
                daftarPesanan.add(pesanan);
                tulisPesananKeWord();

                frame.dispose();
                tampilkanFrameHarga(totalHarga, ruteTerpilih, selectedKapal, selectedKabin, jumlahPenumpang);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Input tidak valid. Penumpang tidak boleh 0 dan kosong.", "Kesalahan Input", JOptionPane.ERROR_MESSAGE);
            }
        });

        tombolKembali.addActionListener(e -> {
            frame.dispose();
            tampilkanFrameRute();
        });

        panel.add(labelKapal);
        panel.add(comboBoxKapal);
        panel.add(labelKabin);
        panel.add(comboBoxKabin);
        panel.add(labelJumlahPenumpang);
        panel.add(jumlahPenumpangField);
        panel.add(tombolLanjut);
        panel.add(tombolKembali);

        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static int getHargaKabin(String selectedKabin) {
        switch (selectedKabin) {
            case "Ekonomi : Rp 1.000.000 / Orang":
                return 1000000;
            case "Bisnis : Rp 1.500.000 / Orang":
                return 1500000;
            case "Premium : Rp 2.000.000 / Orang":
                return 2000000;
            case "Suite : Rp 2.500.000 / Orang":
                return 2500000;
            default:
                return 0;
        }
    }

    private static void tampilkanFrameDaftarPesanan() {
        JFrame frameDaftarPesanan = new JFrame("Daftar Pesanan");
        frameDaftarPesanan.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameDaftarPesanan.setSize(500, 400);

        // Gunakan DefaultListModel untuk menampilkan daftar pesanan
        DefaultListModel<String> model = new DefaultListModel<>();
        daftarPesanan.forEach(pesanan -> {
            // Format setiap pesanan agar tampil dengan baris baru
            String formattedPesanan = formatPesanan(pesanan);
            model.addElement(formattedPesanan);
        });

        JList<String> listPesanan = new JList<>(model);
        listPesanan.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Tombol untuk Hapus, Update, dan Kembali
        JButton tombolHapus = new JButton("Hapus");
        JButton tombolUpdate = new JButton("Update");
        JButton tombolKembali = new JButton("Kembali");

        tombolHapus.setBackground(Color.RED);
        tombolHapus.setForeground(Color.WHITE);
        tombolHapus.setFont(new Font("Arial", Font.BOLD, 14));

        tombolUpdate.setBackground(Color.ORANGE);
        tombolUpdate.setForeground(Color.WHITE);
        tombolUpdate.setFont(new Font("Arial", Font.BOLD, 14));

        tombolKembali.setBackground(Color.GREEN);
        tombolKembali.setForeground(Color.WHITE);
        tombolKembali.setFont(new Font("Arial", Font.BOLD, 14));

        tombolHapus.addActionListener(e -> {
            int selectedIndex = listPesanan.getSelectedIndex();
            if (selectedIndex != -1) {
                daftarPesanan.remove(selectedIndex);
                model.remove(selectedIndex);  // Hapus item dari list model
                tulisPesananKeWord();
            } else {
                JOptionPane.showMessageDialog(frameDaftarPesanan, "Pilih pesanan untuk dihapus.", "Kesalahan", JOptionPane.ERROR_MESSAGE);
            }
        });

        tombolUpdate.addActionListener(e -> {
            int selectedIndex = listPesanan.getSelectedIndex();
            if (selectedIndex != -1) {
                String newPesanan = JOptionPane.showInputDialog(frameDaftarPesanan, "Update Pesanan:", daftarPesanan.get(selectedIndex));
                if (newPesanan != null && !newPesanan.trim().isEmpty()) {
                    daftarPesanan.set(selectedIndex, newPesanan);
                    model.set(selectedIndex, newPesanan);  // Update item di list model
                    tulisPesananKeWord();
                }
            } else {
                JOptionPane.showMessageDialog(frameDaftarPesanan, "Pilih pesanan untuk diupdate.", "Kesalahan", JOptionPane.ERROR_MESSAGE);
            }
        });

        tombolKembali.addActionListener(e -> {
            frameDaftarPesanan.dispose();
            tampilkanFrameRute();
        });

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(listPesanan), BorderLayout.CENTER);  // Membungkus JList dalam JScrollPane

        JPanel panelTombol = new JPanel();
        panelTombol.add(tombolHapus);
        panelTombol.add(tombolUpdate);
        panelTombol.add(tombolKembali);

        frameDaftarPesanan.add(panel, BorderLayout.CENTER);
        frameDaftarPesanan.add(panelTombol, BorderLayout.SOUTH);

        frameDaftarPesanan.setLocationRelativeTo(null);
        frameDaftarPesanan.setVisible(true);
    }



    // Format tampilan pesanan untuk ditampilkan di daftar
    private static String formatPesanan(String pesanan) {
        // Pisahkan pesanan dengan koma dan tampilkan dalam format yang diinginkan
        String[] bagianPesanan = pesanan.split(", ");

        // Pastikan ada cukup elemen dalam array
        if (bagianPesanan.length < 5) {
            return "Format pesanan tidak valid: " + pesanan;  // Jika tidak valid, tetap tambahkan koma di sini
        }

        // Format pesanan sesuai dengan yang diinginkan
        StringBuilder formattedPesanan = new StringBuilder();
        formattedPesanan.append(bagianPesanan[0]).append(", ");  // Rute
        formattedPesanan.append(bagianPesanan[1]).append(", ");  // Kapal
        formattedPesanan.append(bagianPesanan[2]).append(", ");  // Kabin
        formattedPesanan.append(bagianPesanan[3]).append(", ");  // Jumlah
        formattedPesanan.append(bagianPesanan[4]).append(", ");  // Total

        return formattedPesanan.toString();
    }



    private static void tampilkanFrameHarga(int totalHarga, String rute, String kapal, String kabin, int jumlahPenumpang) {
        JFrame frameHarga = new JFrame("Total Pembayaran");
        frameHarga.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameHarga.setSize(500, 400);

        JLabel labelHarga = new JLabel("Total Pembayaran:", JLabel.CENTER);
        labelHarga.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel labelTotal = new JLabel("Rp " + totalHarga, JLabel.CENTER);
        labelTotal.setFont(new Font("Arial", Font.PLAIN, 24));
        JButton tombolKonfirmasi = new JButton("Konfirmasi");
        JButton tombolKembali = new JButton("Kembali");

        tombolKonfirmasi.setBackground(Color.BLUE);
        tombolKonfirmasi.setForeground(Color.WHITE);
        tombolKonfirmasi.setFont(new Font("Arial", Font.BOLD, 14));

        tombolKembali.setBackground(Color.RED);
        tombolKembali.setForeground(Color.WHITE);
        tombolKembali.setFont(new Font("Arial", Font.BOLD, 14));

        tombolKonfirmasi.addActionListener(e -> {
            String orderDetails = "Rute: " + rute + "\n" +
                    "Kapal: " + kapal + "\n" +
                    "Kabin: " + kabin + "\n" +
                    "Jumlah Penumpang: " + jumlahPenumpang + "\n" +
                    "Total Harga: Rp " + totalHarga;
            JOptionPane.showMessageDialog(frameHarga, "Pesanan Berhasil!\n" + orderDetails, "Konfirmasi", JOptionPane.INFORMATION_MESSAGE);
            frameHarga.dispose();
            tampilkanFrameRute();
        });

        tombolKembali.addActionListener(e -> {
            frameHarga.dispose();
            tampilkanFrameKapalDanKabin(rute);
        });

        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(labelHarga);
        panel.add(labelTotal);
        panel.add(tombolKonfirmasi);
        panel.add(tombolKembali);

        frameHarga.add(panel);
        frameHarga.setLocationRelativeTo(null);
        frameHarga.setVisible(true);
    }

    private static void tulisPesananKeWord() {
        try (XWPFDocument document = new XWPFDocument()) {
            XWPFParagraph paragraph = document.createParagraph();
            paragraph.setAlignment(ParagraphAlignment.LEFT);
            XWPFRun run = paragraph.createRun();
            run.setFontSize(14);

            // Tambahkan setiap pesanan tanpa duplikasi
            for (String pesanan : daftarPesanan) {
                // Pisahkan pesanan berdasarkan koma
                String[] bagianPesanan = pesanan.split(", ");

                // Tambahkan bagian-bagian pesanan satu per satu
                for (String bagian : bagianPesanan) {
                    run.setText(bagian);  // Setiap bagian pesanan
                    run.addBreak();  // Baris baru setelah setiap bagian pesanan
                }
                run.addBreak();  // Tambahkan baris baru setelah setiap pesanan
            }

            try (FileOutputStream out = new FileOutputStream(FILE_NAME)) {
                document.write(out);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void bacaPesananDariWord() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return;
        }

        try (XWPFDocument document = new XWPFDocument(new FileInputStream(file))) {
            document.getParagraphs().forEach(paragraph -> {
                String text = paragraph.getText();
                if (!text.isEmpty()) {
                    daftarPesanan.add(text);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
