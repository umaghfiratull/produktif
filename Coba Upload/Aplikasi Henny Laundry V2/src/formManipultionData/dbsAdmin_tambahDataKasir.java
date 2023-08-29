package formManipultionData;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static dashboard.dashboardAdmin.tabelkasir;

public class dbsAdmin_tambahDataKasir extends javax.swing.JFrame {

    public dbsAdmin_tambahDataKasir() {
        initComponents();
        setTitle("Form tambah data pengguna");
        menampilkanDataPengguna();
        idPenggunaOtomatis();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        kButton1 = new com.k33ptoo.components.KButton();
        jLabel1 = new javax.swing.JLabel();

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(350, 580));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel3.setText("Tambah Data Pengguna");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 110, -1, -1));

        notel_user.setLabelText("Nomor");
        notel_user.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                notel_userActionPerformed(evt);
            }
        });
        jPanel1.add(notel_user, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, 310, -1));

        id_user.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        id_user.setEnabled(false);
        id_user.setLabelText("ID Pengguna");
        jPanel1.add(id_user, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 310, -1));

        username_user.setLabelText("Username");
        jPanel1.add(username_user, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 310, -1));

        nama_user.setLabelText("Nama Pengguna");
        jPanel1.add(nama_user, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 310, -1));

        password_user.setLabelText("Password");
        password_user.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                password_userActionPerformed(evt);
            }
        });
        jPanel1.add(password_user, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 310, -1));

        alamat_user.setLabelText("Alamat");
        alamat_user.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alamat_userActionPerformed(evt);
            }
        });
        jPanel1.add(alamat_user, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, 310, -1));

        kButton1.setText("Simpan Data");
        kButton1.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        kButton1.setkBackGroundColor(new java.awt.Color(64, 112, 244));
        kButton1.setkEndColor(new java.awt.Color(64, 112, 244));
        kButton1.setkHoverEndColor(new java.awt.Color(159, 189, 255));
        kButton1.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        kButton1.setkHoverStartColor(new java.awt.Color(159, 189, 255));
        kButton1.setkPressedColor(new java.awt.Color(64, 112, 244));
        kButton1.setkSelectedColor(new java.awt.Color(64, 112, 244));
        kButton1.setkStartColor(new java.awt.Color(64, 112, 244));
        kButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(kButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 520, 310, 40));

        hakAkses_user.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "admin", "kasir" }));
        hakAkses_user.setSelectedIndex(-1);
        hakAkses_user.setLabeText("Pilih Hak Akses");
        hakAkses_user.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hakAkses_userActionPerformed(evt);
            }
        });
        jPanel1.add(hakAkses_user, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 450, 310, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Desktop - 13_1.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 350, 520));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    void reset() {
        id_user.setText("");
        nama_user.setText("");
        alamat_user.setText("");
        notel_user.setText("");
        username_user.setText("");
        password_user.setText("");
    }

    public void idPenggunaOtomatis() {
        try {
            String sql = "SELECT * from user ORDER BY id_user DESC";
            Connection con = (Connection) connection.connect.configDB();
            Statement st = (Statement) con.createStatement();
            ResultSet res = st.executeQuery(sql);
            if (res.next()) {
                String Noplg = res.getString("id_user").substring(2);
                String AN = "" + (Integer.parseInt(Noplg) + 1);
                String Nol = "";
                if (AN.length() == 1) {
                    Nol = "000";
                } else if (AN.length() == 2) {
                    Nol = "00";
                } else if (AN.length() == 3) {
                    Nol = "0";
                } else if (AN.length() == 4) {
                    Nol = "";
                }
                id_user.setText("US" + Nol + AN);//sesuaikan dengan variable namenya
            } else {
                id_user.setText("US0001");//sesuaikan dengan variable namenya
            }
            res.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Koneksi gagal" + e.getMessage());
        }
    }

    public void menampilkanDataPengguna() {
        DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("ID Kasir");
        tbl.addColumn("Nama Kasir");
        tbl.addColumn("Alamat");
        tbl.addColumn("Nomor");
        tabelkasir.setModel(tbl);
        try {
            String kasir = "kasir";
            Statement statement = (Statement) connection.connect.configDB().createStatement();
            ResultSet res = statement.executeQuery("select * from user");
            while (res.next()) {
                tbl.addRow(new Object[]{
                    res.getString("id_user"),
                    res.getString("nama"),
                    res.getString("alamat"),
                    res.getString("no_hp")
                });
                tabelkasir.setModel(tbl);
            }
            //int b = tabelkasir.getRowCount();
            //jumlah_data_kasir.setText("" + b);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Koneksi gagal" + e.getMessage());
        }
    }
    private void notel_userActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_notel_userActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_notel_userActionPerformed

    private void password_userActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_password_userActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_password_userActionPerformed

    private void alamat_userActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alamat_userActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_alamat_userActionPerformed

    private void hakAkses_userActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hakAkses_userActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hakAkses_userActionPerformed

    private void kButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButton1ActionPerformed
        // TODO add your handling code here:
        String ID = id_user.getText();
        String Nama = nama_user.getText();
        String username1 = username_user.getText();
        String Password = password_user.getText();
        String Alamat = alamat_user.getText();
        String noTel = notel_user.getText();
        String aks = (String) hakAkses_user.getSelectedItem();

        try {
            Long.parseLong(notel_user.getText());
            if (notel_user.getText().length() > 13) {
                JOptionPane.showMessageDialog(null, "Nomor Telfon Tidak Boleh Berjumlah Lebih Dari 13 Angka");
            } else if (notel_user.getText().length() < 11) {
                JOptionPane.showMessageDialog(null, "Nomor Telfon Tidak Boleh Berjumlah Kurang Dari 11 Angka");
            } else if (nama_user.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, " Nama Pengguna masih belum di isi");
            } else if (username_user.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Username masih belum di isi");
            } else if (password_user.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Password masih belum di isi");
            } else if (alamat_user.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Alamat masih belum di isi");
            } else if (notel_user.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nomor masih belum di isi");
            } else if (hakAkses_user.getSelectedItem().equals("")) {
                JOptionPane.showMessageDialog(null, "Hak akses masih belum di pilih");
            } else if (nama_user.getText().length() > 50) {
                JOptionPane.showMessageDialog(null, "Hanya dapat mengisi 50 karakter tidak boleh lebih");
            } else if (nama_user.getText().length() < 7) {
                JOptionPane.showMessageDialog(null, "Harap masukkan nama lengkap, nama terlalu pendek");
            } else if (username_user.getText().length() > 15) {
                JOptionPane.showMessageDialog(null, "Username tidak boleh lebih dari 15 karakter");
            } else if (username_user.getText().length() < 5) {
                JOptionPane.showMessageDialog(null, "Username tidak boleh lebih pendek dari 5 karakter");
            } else if (password_user.getText().length() > 15) {
                JOptionPane.showMessageDialog(null, "Password tidak boleh lebih dari 15 karakter");
            } else if (password_user.getText().length() < 5) {
                JOptionPane.showMessageDialog(null, "Password tidak boleh lebih pendek dari 5 karakter");
            } else if (alamat_user.getText().length() < 5) {
                JOptionPane.showMessageDialog(null, "Harap masukkan alamat dengan lengkap");
            } else {
                try {
                    String sql = "INSERT INTO user VALUES('" + ID + "', '" + Nama + "','" + username1 + "','" + Password + "', '" + Alamat + "','" + noTel + "','" + aks + "')";
                    Connection con = (Connection) connection.connect.configDB();
                    PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
                    pst.execute();
                    JOptionPane.showMessageDialog(null, "Data Berhasil Di simpan");
                    reset();
                    menampilkanDataPengguna();
                    dispose();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Data gagal di tambah" + e.getMessage());
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Nomor Telfon Tidak Boleh Mengandung Huruf");
        }
    }//GEN-LAST:event_kButton1ActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(dbsAdmin_tambahDataKasir.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dbsAdmin_tambahDataKasir.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dbsAdmin_tambahDataKasir.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dbsAdmin_tambahDataKasir.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new dbsAdmin_tambahDataKasir().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static final textfield.TextField alamat_user = new textfield.TextField();
    public static final combobox.Combobox hakAkses_user = new combobox.Combobox();
    public static final textfield.TextField id_user = new textfield.TextField();
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private com.k33ptoo.components.KButton kButton1;
    public static final textfield.TextField nama_user = new textfield.TextField();
    public static final textfield.TextField notel_user = new textfield.TextField();
    public static final textfield.TextField password_user = new textfield.TextField();
    public static final textfield.TextField username_user = new textfield.TextField();
    // End of variables declaration//GEN-END:variables
}
