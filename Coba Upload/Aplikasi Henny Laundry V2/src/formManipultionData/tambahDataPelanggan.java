package formManipultionData;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import static dashboard.dashboardAdmin.tabel_data_pelanggan;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class tambahDataPelanggan extends javax.swing.JFrame {

    public tambahDataPelanggan() {
        initComponents();
        setTitle("Form tambah data pelanggan");
        menampilkanDataPelanggan();
        id_otomatis_pelanggan();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        kButton1 = new com.k33ptoo.components.KButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new java.awt.CardLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel3.setText("Tambah Data Pelanggan");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, -1, -1));

        field_nomor_telfon.setLabelText("Nomor");
        field_nomor_telfon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                field_nomor_telfonActionPerformed(evt);
            }
        });
        jPanel1.add(field_nomor_telfon, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 310, -1));

        field_id_pelanggan.setEnabled(false);
        field_id_pelanggan.setLabelText("ID Pelanggan");
        jPanel1.add(field_id_pelanggan, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 310, -1));

        field_nama_pelanggan.setLabelText("Nama Pelanggan");
        jPanel1.add(field_nama_pelanggan, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 310, -1));

        field_alamat_pelanggan.setLabelText("Alamat Pelanggan");
        jPanel1.add(field_alamat_pelanggan, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 310, -1));

        kButton1.setText("Simpan");
        kButton1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
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
        jPanel1.add(kButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, 310, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Desktop - 14.png"))); // NOI18N
        jLabel1.setPreferredSize(new java.awt.Dimension(37, 550));
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 350, 580));

        getContentPane().add(jPanel1, "card2");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    void reset_pengisian_data_pelanggan() {
        field_id_pelanggan.setText("");
        field_nama_pelanggan.setText("");
        field_alamat_pelanggan.setText("");
        field_nomor_telfon.setText("");
    }

    public void menampilkanDataPelanggan() {
        DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("ID Pelanggan");
        tbl.addColumn("Nama Pelanggan");
        tbl.addColumn("Alamat Pelanggan");
        tbl.addColumn("Nomor Telfon");
        tabel_data_pelanggan.setModel(tbl);
        try {
            Statement statement = (Statement) connection.connect.configDB().createStatement();
            ResultSet res = statement.executeQuery("select * from pelanggan");
            while (res.next()) {
                tbl.addRow(new Object[]{
                    res.getString("id_pelanggan"),
                    res.getString("nama_pelanggan"),
                    res.getString("alamat"),
                    res.getString("no_telp"),});
                tabel_data_pelanggan.setModel(tbl);
            }
            int b = tabel_data_pelanggan.getRowCount();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Koneksi gagal" + e.getMessage());
        }
    }
    private void field_nomor_telfonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_field_nomor_telfonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_field_nomor_telfonActionPerformed

    void id_otomatis_pelanggan() {
        try {
            String sql = "SELECT * from pelanggan ORDER BY id_pelanggan DESC";
            Connection con = (Connection) connection.connect.configDB();
            Statement st = (Statement) con.createStatement();
            ResultSet res = st.executeQuery(sql);
            if (res.next()) {
                String Noplg = res.getString("id_pelanggan").substring(2);
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
                field_id_pelanggan.setText("PL" + Nol + AN);//sesuaikan dengan variable namenya
            } else {
                field_id_pelanggan.setText("PL0001");//sesuaikan dengan variable namenya
            }
            res.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Koneksi gagal" + e.getMessage());
        }
    }
    private void kButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButton1ActionPerformed
        // TODO add your handling code here:
        String ID = field_id_pelanggan.getText();
        String Nama = field_nama_pelanggan.getText();
        String Alamat = field_alamat_pelanggan.getText();
        String noTel = field_nomor_telfon.getText();

        try {
            Long.parseLong(field_nomor_telfon.getText());
            if ((field_nomor_telfon.getText().length() > 13) || (field_nomor_telfon.getText().length() < 11)) {
                JOptionPane.showMessageDialog(null, "harap masukkan jumlah telfon dengan benar");
            } else if (field_nama_pelanggan.getText().length() > 50) {
                JOptionPane.showMessageDialog(null, "Nama tidak boleh lebih dari 50 karakter");
            } else if (field_nama_pelanggan.getText().length() < 5) {
                JOptionPane.showMessageDialog(null, "Harap masukkan nama lengkap");
            } else if (field_nama_pelanggan.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nama pelanggan belum di isi");
            } else if (field_alamat_pelanggan.getText().length() > 250) {
                JOptionPane.showMessageDialog(null, "Alamat tidak boleh melebihi 250 karakter");
            } else if (field_alamat_pelanggan.getText().length() < 7) {
                JOptionPane.showMessageDialog(null, "Harap masukkan alamat lengkap");
            } else if (field_alamat_pelanggan.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Alamat pelanggan belum di isis");
            } else if (field_nomor_telfon.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nomor telfon belum di isi");
            } else {
                try {
                    String sql = "INSERT INTO pelanggan VALUES('" + ID + "', '" + Nama + "', '" + Alamat + "','" + noTel + "')";
                    Connection con = (Connection) connection.connect.configDB();
                    PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
                    pst.execute();
                    JOptionPane.showMessageDialog(null, "Data berhasil di tambah");
                    reset_pengisian_data_pelanggan();
                    menampilkanDataPelanggan();
                    id_otomatis_pelanggan();
                    dispose();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Data gagal di tambah");
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "harap masukkan no telfon dengan benar");
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
            java.util.logging.Logger.getLogger(tambahDataPelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(tambahDataPelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(tambahDataPelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(tambahDataPelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new tambahDataPelanggan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static final textfield.TextField field_alamat_pelanggan = new textfield.TextField();
    public static final textfield.TextField field_id_pelanggan = new textfield.TextField();
    public static final textfield.TextField field_nama_pelanggan = new textfield.TextField();
    public static final textfield.TextField field_nomor_telfon = new textfield.TextField();
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private com.k33ptoo.components.KButton kButton1;
    // End of variables declaration//GEN-END:variables
}
