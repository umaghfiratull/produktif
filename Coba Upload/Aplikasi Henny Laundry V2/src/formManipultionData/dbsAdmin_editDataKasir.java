package formManipultionData;

import com.mysql.jdbc.Statement;
import static formManipultionData.dbsAdmin_editDataKasir_det.alamat_user_edit;
import static formManipultionData.dbsAdmin_editDataKasir_det.hakAkses_user_edit;
import static formManipultionData.dbsAdmin_editDataKasir_det.id_user_edit;
import static formManipultionData.dbsAdmin_editDataKasir_det.nama_user_edit;
import static formManipultionData.dbsAdmin_editDataKasir_det.notel_user_edit;
import static formManipultionData.dbsAdmin_editDataKasir_det.password_user_edit;
import static formManipultionData.dbsAdmin_editDataKasir_det.username_user_edit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static dashboard.dashboardAdmin.tabelkasir;

public class dbsAdmin_editDataKasir extends javax.swing.JFrame {

    public dbsAdmin_editDataKasir() {
        initComponents();
        menampilkanDataPengguna();
        setTitle("Form edit data pengguna");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        kButton1 = new com.k33ptoo.components.KButton();
        kButton2 = new com.k33ptoo.components.KButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.CardLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel3.setText("Edit Data Pengguna");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 110, -1, -1));

        id_user_edit1.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        id_user_edit1.setEnabled(false);
        id_user_edit1.setLabelText("ID Pengguna");
        jPanel1.add(id_user_edit1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 310, -1));

        nama_user_edit1.setLabelText("Nama Pengguna");
        jPanel1.add(nama_user_edit1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 310, -1));

        alamat_user_edit1.setLabelText("Alamat");
        alamat_user_edit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alamat_user_edit1ActionPerformed(evt);
            }
        });
        jPanel1.add(alamat_user_edit1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 310, -1));

        notel_user_edit1.setLabelText("Nomor");
        notel_user_edit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                notel_user_edit1ActionPerformed(evt);
            }
        });
        jPanel1.add(notel_user_edit1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 310, -1));

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
        jPanel1.add(kButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 380, 230, 40));

        kButton2.setText("Detail");
        kButton2.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        kButton2.setkBackGroundColor(new java.awt.Color(64, 112, 244));
        kButton2.setkEndColor(new java.awt.Color(64, 112, 244));
        kButton2.setkHoverEndColor(new java.awt.Color(159, 189, 255));
        kButton2.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        kButton2.setkHoverStartColor(new java.awt.Color(159, 189, 255));
        kButton2.setkPressedColor(new java.awt.Color(64, 112, 244));
        kButton2.setkSelectedColor(new java.awt.Color(64, 112, 244));
        kButton2.setkStartColor(new java.awt.Color(64, 112, 244));
        kButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(kButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 70, 40));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Desktop - 13_1.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 520));

        getContentPane().add(jPanel1, "card2");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

      void reset(){
        id_user_edit1.setText("");
        nama_user_edit1.setText("");
        alamat_user_edit1.setText("");
        notel_user_edit1.setText("");
    }
    private void alamat_user_edit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alamat_user_edit1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_alamat_user_edit1ActionPerformed

    private void notel_user_edit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_notel_user_edit1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_notel_user_edit1ActionPerformed

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

    private void kButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButton1ActionPerformed
        // TODO add your handling code here:
        String ID = id_user_edit1.getText();
        String Nama = nama_user_edit1.getText();
//        String username1 = username_user_edit.getText();
//        String Password = password_user_edit.getText();
        String Alamat = alamat_user_edit1.getText();
        String noTel = notel_user_edit1.getText();
//        String aks = (String) hakAkses_user_edit.getSelectedItem();
        try {
            Long.parseLong(notel_user_edit1.getText());
            if (notel_user_edit1.getText().length() > 13) {
                JOptionPane.showMessageDialog(null, "Nomor Telfon Tidak Boleh Berjumlah Lebih Dari 13 Angka");
            } else if (notel_user_edit1.getText().length() < 11) {
                JOptionPane.showMessageDialog(null, "Nomor Telfon Tidak Boleh Berjumlah Kurang Dari 11 Angka");
            } else if (nama_user_edit1.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, " Nama Pengguna masih belum di isi");
            } //            else if (username_user_edit.getText().isEmpty()) {
            //                JOptionPane.showMessageDialog(null, "Username masih belum di isi");
            //            } else if (password_user_edit.getText().isEmpty()) {
            //                JOptionPane.showMessageDialog(null, "Password masih belum di isi");
            //            } 
            else if (alamat_user_edit1.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Alamat masih belum di isi");
            } else if (notel_user_edit1.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nomor masih belum di isi");
            } //            else if (hakAkses_user_edit.getSelectedItem().equals("")) {
            //                JOptionPane.showMessageDialog(null, "Hak akses masih belum di pilih");
            //            } 
            else if (nama_user_edit1.getText().length() > 50) {
                JOptionPane.showMessageDialog(null, "Hanya dapat mengisi 50 karakter tidak boleh lebih");
            } else if (nama_user_edit1.getText().length() < 7) {
                JOptionPane.showMessageDialog(null, "Harap masukkan nama lengkap, nama terlalu pendek");
            } //            else if (username_user_edit.getText().length() > 15) {
            //                JOptionPane.showMessageDialog(null, "Username tidak boleh lebih dari 15 karakter");
            //            } else if (username_user_edit.getText().length() < 5) {
            //                JOptionPane.showMessageDialog(null, "Username tidak boleh lebih pendek dari 5 karakter");
            //            } else if (password_user_edit.getText().length() > 15) {
            //                JOptionPane.showMessageDialog(null, "Password tidak boleh lebih dari 15 karakter");
            //            } else if (password_user_edit.getText().length() < 5) {
            //                JOptionPane.showMessageDialog(null, "Password tidak boleh lebih pendek dari 5 karakter");
            //            } 
            else if (alamat_user_edit1.getText().length() < 5) {
                JOptionPane.showMessageDialog(null, "Harap masukkan alamat dengan lengkap");
            } else {
                try {

                    String sql = "UPDATE user SET id_user='" + ID + "',nama='" + Nama + "',alamat='" + Alamat + "',no_hp='" + noTel + "'WHERE id_user='" + ID + "'";
                    Connection con = (Connection) connection.connect.configDB();
                    PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
                    JOptionPane.showMessageDialog(null, "Data Berhasil Di Update");
                    pst.execute();
                    menampilkanDataPengguna();
                    dispose();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Data GAGAL di EDIT" + e.getMessage());
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Nomor Telfon Tidak Boleh Mengandung Huruf");
        }
    }//GEN-LAST:event_kButton1ActionPerformed

    private void kButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButton2ActionPerformed
        // TODO add your handling code here:
        new dbsAdmin_editDataKasir_det().setVisible(true);
        dispose();
    }//GEN-LAST:event_kButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(dbsAdmin_editDataKasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dbsAdmin_editDataKasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dbsAdmin_editDataKasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dbsAdmin_editDataKasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new dbsAdmin_editDataKasir().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static final textfield.TextField alamat_user_edit1 = new textfield.TextField();
    public static final textfield.TextField id_user_edit1 = new textfield.TextField();
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private com.k33ptoo.components.KButton kButton1;
    private com.k33ptoo.components.KButton kButton2;
    public static final textfield.TextField nama_user_edit1 = new textfield.TextField();
    public static final textfield.TextField notel_user_edit1 = new textfield.TextField();
    // End of variables declaration//GEN-END:variables
}
