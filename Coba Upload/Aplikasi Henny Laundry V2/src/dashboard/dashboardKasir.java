package dashboard;

import codingDashboardKasir.imageKomponenDBUtama;
import codingDashboardKasir.componenUangHariIni;
import codingDashboardKasir.componentJumlahClient;
import codingDashboardKasir.componentClientBaru;
import codingDashboardKasir.componenTransaksiDashboard;
import codingDashboardKasir.componentDashboardBesar;
import codingDashboardKasir.componentImageProfileAdmin;
import codingDashboardKasir.imageProfileAdmin;
import com.mysql.cj.x.protobuf.MysqlxCursor.Open;
import com.mysql.jdbc.Statement;
import connection.connect;
import static detailDataJtextField.PelangganTR.alamat_pelanggan_form;
import static detailDataJtextField.PelangganTR.id_detail_pelanggan_form;
import static detailDataJtextField.PelangganTR.nama_pelanggan_form;
import static detailDataJtextField.PelangganTR.nomor_telfon_pelanggan_form;
import static detailDataJtextField.UserTR.alamat_kasir_form;
import static detailDataJtextField.UserTR.hakakses_detail_form;
import static detailDataJtextField.UserTR.id_detail_kasir_form;
import static detailDataJtextField.UserTR.nama_kasir_form;
import static detailDataJtextField.UserTR.nomor_telfon_kasir_form;
import static detailDataJtextField.UserTR.password_detail_form;
import static detailDataJtextField.UserTR.username_kasir_detail_form;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import responsive.login_rs;
import swing.EventCallBack;
import swing.EventTextField;

public class dashboardKasir extends javax.swing.JFrame {

    static String selected_pelanggan_id;
    static String selectedkdtrs;
    DefaultTableModel modelpj;
    DefaultTableModel modelDataPelanggan;
    public int thotal;
    public int tot_brg;
    ArrayList<String> idlist = new ArrayList<String>();

    public dashboardKasir() {

        initComponents();
        setTitle("DASHBOARD KASIR");

        // form data pelanggan
        menampilkan_data_pelanggan();
        id_otomatis_pelanggan();
        search_data_pelanggan();

        // form transaksi
        id_kasir_tr();
        kodeTransaksiOtomatis();
        tampil_data_barang();
        reset_nambah_krj();
        tampil_data_penjualan();

        // Data Transaksi
        search_data_transaksi();
        menampilkan_data_pelanggan_data_transaksi();

        field_harga_paket.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
                // tidak perlu dilakukan apa-apa
            }

            public void keyPressed(KeyEvent e) {
                // tidak perlu dilakukan apa-apa
            }

            public void keyReleased(KeyEvent e) {
                try {
                    int input1 = Integer.parseInt(field_harga_paket.getText());
                    int input2 = Integer.parseInt(field_jumlah_barang.getText());
                    int hasil = input1 * input2;
                    field_subtotal.setText("" + hasil);
                } catch (NumberFormatException ex) {
                    //field_subtotal.setText("");
                }
            }
        });
        field_jumlah_barang.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
                // tidak perlu dilakukan apa-apa
            }

            public void keyPressed(KeyEvent e) {
                // tidak perlu dilakukan apa-apa
            }

            public void keyReleased(KeyEvent e) {
                try {
                    int input1 = Integer.parseInt(field_harga_paket.getText());
                    int input2 = Integer.parseInt(field_jumlah_barang.getText());
                    int hasil = input1 * input2;
                    field_subtotal.setText("" + hasil);
                } catch (NumberFormatException ex) {
                    //hasilLabel.setText("");
                }
            }
        });

        field_total_harga.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
                // tidak perlu dilakukan apa-apa
            }

            public void keyPressed(KeyEvent e) {
                // tidak perlu dilakukan apa-apa
            }

            public void keyReleased(KeyEvent e) {
                try {
                    int input2 = Integer.parseInt(field_total_harga.getText());
                    int input1 = Integer.parseInt(field_bayar.getText());
                    int hasil = input1 - input2;
                    int lain = input1 - input2;
                    if (hasil == 0) {
                        field_status_bayar.setText("lunas");
                        field_sisa_bayar.setText("" + hasil);
                        field_kembalian.setText("" + hasil);
                    } else if (input1 > input2) {
                        field_status_bayar.setText("lunas");
                        field_sisa_bayar.setText("" + 0);
                        field_kembalian.setText("" + lain);
                    } else if (hasil == input2) {
                        field_status_bayar.setText("belum bayar");
                        field_sisa_bayar.setText("" + hasil);
                        field_kembalian.setText("0");
                    } else if (hasil < input2) {
                        field_status_bayar.setText("kurang");
                        field_sisa_bayar.setText("" + hasil);
                        field_kembalian.setText("" + 0);
                    }
                } catch (NumberFormatException ex) {
                    //field_subtotal.setText("");
                }
            }
        });
        field_bayar.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
                // tidak perlu dilakukan apa-apa
            }

            public void keyPressed(KeyEvent e) {
                // tidak perlu dilakukan apa-apa
            }

            public void keyReleased(KeyEvent e) {
                try {
                    int input2 = Integer.parseInt(field_total_harga.getText());
                    int input1 = Integer.parseInt(field_bayar.getText());
                    int hasil = input1 - input2;
                    int lain = input1 - input2;
                    if (hasil == 0) {
                        field_status_bayar.setText("lunas");
                        field_sisa_bayar.setText("" + hasil);
                        field_kembalian.setText("" + hasil);
                    } else if (input1 > input2) {
                        field_status_bayar.setText("lunas");
                        field_sisa_bayar.setText("" + 0);
                        field_kembalian.setText("" + lain);
                    } else if (hasil == input2) {
                        field_status_bayar.setText("belum bayar");
                        field_sisa_bayar.setText("" + hasil);
                        field_kembalian.setText("0");
                    } else if (hasil < input2) {
                        field_status_bayar.setText("kurang");
                        field_sisa_bayar.setText("" + Math.abs(hasil));
                        field_kembalian.setText("" + 0);
                    }
                } catch (NumberFormatException ex) {
                    //hasilLabel.setText("");
                }
            }
        });

        // Data Transaksi
        pembayaran_dt.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
                // tidak perlu dilakukan apa-apa
            }

            public void keyPressed(KeyEvent e) {
                // tidak perlu dilakukan apa-apa
            }

            public void keyReleased(KeyEvent e) {
                try {
                    int input2 = Integer.parseInt(pembayaran_dt.getText());
                    int input1 = Integer.parseInt(sisa_bayar_dt.getText());
                    int hasil = input1 - input2;
                    int lain = input2 - input1;
                    if (hasil == 0) {
                        status_bayar_dt.setText("Lunas!");
                        sisa_bayar_dt.setText("" + hasil);
                        kembalian_pembayaran_dt.setText("" + hasil);
                    } else if (input2 > input1) {
                        status_bayar_dt.setText("Lunas");
                        sisa_bayar_dt.setText("" + 0);
                        kembalian_pembayaran_dt.setText("" + lain);

                    } else if (hasil == input2) {
                        status_bayar_dt.setText("Belum Bayar");
                        sisa_bayar_dt.setText("" + hasil);
                        kembalian_pembayaran_dt.setText("0");
                    } else if (hasil < input2) {
                        status_bayar_dt.setText("kurang");
                        sisa_bayar_dt.setText("" + Math.abs(hasil));
                        kembalian_pembayaran_dt.setText("" + 0);
                    }
                } catch (NumberFormatException ex) {
                    //hasilLabel.setText("");
                }
            }
        });
        sisa_bayar_dt.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
                // tidak perlu dilakukan apa-apa
            }

            public void keyPressed(KeyEvent e) {
                // tidak perlu dilakukan apa-apa
            }

            public void keyReleased(KeyEvent e) {
                try {
                    int input2 = Integer.parseInt(pembayaran_dt.getText());
                    int input1 = Integer.parseInt(sisa_bayar_dt.getText());
                    int hasil = input1 - input2;
                    int lain = input2 - input1;
                    if (hasil == 0) {
                        status_bayar_dt.setText("Lunas!");
                        sisa_bayar_dt.setText("" + hasil);
                        kembalian_pembayaran_dt.setText("" + hasil);
                    } else if (input2 > input1) {
                        status_bayar_dt.setText("Lunas");
                        sisa_bayar_dt.setText("" + 0);
                        kembalian_pembayaran_dt.setText("" + lain);

                    } else if (hasil == input2) {
                        status_bayar_dt.setText("Belum Bayar");
                        sisa_bayar_dt.setText("" + hasil);
                        kembalian_pembayaran_dt.setText("0");
                    } else if (hasil < input2) {
                        status_bayar_dt.setText("kurang");
                        sisa_bayar_dt.setText("" + Math.abs(hasil));
                        kembalian_pembayaran_dt.setText("" + 0);
                    }
                } catch (NumberFormatException ex) {
                    //hasilLabel.setText("");
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        container = new javax.swing.JPanel();
        wrapperMenu = new javax.swing.JPanel();
        logo = new javax.swing.JLabel();
        menu = new javax.swing.JPanel();
        menuDashboard = new javax.swing.JPanel();
        dashboardLabel = new javax.swing.JLabel();
        menuTransaksi = new javax.swing.JPanel();
        menuTransaksiLabel = new javax.swing.JLabel();
        menuDatatransaksi = new javax.swing.JPanel();
        datatransaksiLabel = new javax.swing.JLabel();
        menuDataPelanggan = new javax.swing.JPanel();
        dataPelangganLabel = new javax.swing.JLabel();
        menuHistoryTransaksi = new javax.swing.JPanel();
        historyTransaksiLabel = new javax.swing.JLabel();
        menuDataPaket = new javax.swing.JPanel();
        dataPaketLabel = new javax.swing.JLabel();
        wrapperKontent = new javax.swing.JPanel();
        tempatJuduldanUser = new javax.swing.JPanel();
        judulDb = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        containerContent = new javax.swing.JPanel();
        tampilanDashboardUtama = new javax.swing.JPanel();
        jPanel1 = new imageKomponenDBUtama();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel4 = new componenUangHariIni();
        jLabel14 = new javax.swing.JLabel();
        jPanel2 = new imageKomponenDBUtama();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel5 = new componentJumlahClient();
        jLabel15 = new javax.swing.JLabel();
        jPanel3 = new imageKomponenDBUtama();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel6 = new componentClientBaru();
        jLabel16 = new javax.swing.JLabel();
        jPanel7 = new componentDashboardBesar();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        kButton1 = new com.k33ptoo.components.KButton();
        jPanel8 = new componentImageProfileAdmin();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        kButton2 = new com.k33ptoo.components.KButton();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        tampilanTransaksi = new javax.swing.JPanel();
        containerTransaksi = new javax.swing.JPanel();
        tampilanAwalTransaksi = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_data_pelanggan_2 = new tabledark.TableDark();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        kButton3 = new com.k33ptoo.components.KButton();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        kButton4 = new com.k33ptoo.components.KButton();
        field_cari_data_pelanggan = new swing.TextFieldAnimation();
        jLabel33 = new javax.swing.JLabel();
        inputPelanggan = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        field_id_pelanggan_tr = new textfield.TextField();
        kButton5 = new com.k33ptoo.components.KButton();
        textField1 = new textfield.TextField();
        textField2 = new textfield.TextField();
        textField4 = new textfield.TextField();
        jLabel35 = new javax.swing.JLabel();
        lanjutTransaksi1 = new javax.swing.JPanel();
        field_kode_nota = new textfield.TextField();
        field_id_user = new textfield.TextField();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        kButton8 = new com.k33ptoo.components.KButton();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        lanjutTransaksi2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabel_barang = new tabledark.TableDark();
        field_harga_paket = new textfield.TextField();
        jLabel36 = new javax.swing.JLabel();
        field_keterangan = new textfield.TextField();
        field_subtotal = new textfield.TextField();
        kButton6 = new com.k33ptoo.components.KButton();
        kButton7 = new com.k33ptoo.components.KButton();
        jLabel37 = new javax.swing.JLabel();
        field_jumlah_barang = new textfield.TextField();
        lanjutTransaksi3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabel_penjualan = new tabledark.TableDark();
        field_total_harga = new textfield.TextField();
        jLabel50 = new javax.swing.JLabel();
        field_bayar = new textfield.TextField();
        field_status_bayar = new textfield.TextField();
        field_sisa_bayar = new textfield.TextField();
        jLabel51 = new javax.swing.JLabel();
        field_kembalian = new textfield.TextField();
        kButton9 = new com.k33ptoo.components.KButton();
        kButton10 = new com.k33ptoo.components.KButton();
        tampilanDataTransaksi = new javax.swing.JPanel();
        containerDataTransaksi = new javax.swing.JPanel();
        tahapPertama = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabel_data_transaksi = new tabledark.TableDark();
        kButton11 = new com.k33ptoo.components.KButton();
        field_cari_data_transaksi = new swing.TextFieldAnimation();
        field_lanjut_pengembalian = new javax.swing.JTextField();
        tahapKedua = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        kd_struk_data_transaksi = new textfield.TextField();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        id_pelanggan_dt = new textfield.TextField();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        id_kasir_dt = new textfield.TextField();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        tanggal_pesan_dt = new textfield.TextField();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        tanggal_selesai_dt = new textfield.TextField();
        status_ambil_dt = new combobox.Combobox();
        kButton12 = new com.k33ptoo.components.KButton();
        jLabel66 = new javax.swing.JLabel();
        tahapKetiga = new javax.swing.JPanel();
        jLabel67 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        total_harga_dt = new javax.swing.JTextField();
        jLabel69 = new javax.swing.JLabel();
        sisa_bayar_dt = new javax.swing.JTextField();
        jLabel72 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        status_bayar_dt = new javax.swing.JTextField();
        jLabel75 = new javax.swing.JLabel();
        pembayaran_dt = new textfield.TextField();
        kembalian_pembayaran_dt = new textfield.TextField();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jumlah_bayar_dt = new textfield.TextField();
        kembalian_dt = new textfield.TextField();
        jLabel79 = new javax.swing.JLabel();
        kButton13 = new com.k33ptoo.components.KButton();
        tampilanDataPelanggan = new javax.swing.JPanel();
        tampilanLaporanTransaksi = new javax.swing.JPanel();
        tampilanDataPaket = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.CardLayout());

        wrapperMenu.setBackground(new java.awt.Color(255, 255, 255));
        wrapperMenu.setPreferredSize(new java.awt.Dimension(192, 610));

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logo/logo (1).png"))); // NOI18N

        menu.setBackground(new java.awt.Color(255, 255, 255));

        menuDashboard.setBackground(new java.awt.Color(255, 255, 255));
        menuDashboard.setPreferredSize(new java.awt.Dimension(100, 36));

        dashboardLabel.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        dashboardLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dashboard_icon_menu.png"))); // NOI18N
        dashboardLabel.setText("Dashboard");
        dashboardLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        dashboardLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dashboardLabelMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                dashboardLabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout menuDashboardLayout = new javax.swing.GroupLayout(menuDashboard);
        menuDashboard.setLayout(menuDashboardLayout);
        menuDashboardLayout.setHorizontalGroup(
            menuDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(menuDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuDashboardLayout.createSequentialGroup()
                    .addGap(0, 14, Short.MAX_VALUE)
                    .addComponent(dashboardLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        menuDashboardLayout.setVerticalGroup(
            menuDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 36, Short.MAX_VALUE)
            .addGroup(menuDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(dashboardLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
        );

        menuTransaksi.setBackground(new java.awt.Color(255, 255, 255));
        menuTransaksi.setPreferredSize(new java.awt.Dimension(100, 36));

        menuTransaksiLabel.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        menuTransaksiLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/money-exchange_1.png"))); // NOI18N
        menuTransaksiLabel.setText("Transaksi");
        menuTransaksiLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuTransaksiLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuTransaksiLabelMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menuTransaksiLabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout menuTransaksiLayout = new javax.swing.GroupLayout(menuTransaksi);
        menuTransaksi.setLayout(menuTransaksiLayout);
        menuTransaksiLayout.setHorizontalGroup(
            menuTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuTransaksiLayout.createSequentialGroup()
                .addGap(0, 14, Short.MAX_VALUE)
                .addComponent(menuTransaksiLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        menuTransaksiLayout.setVerticalGroup(
            menuTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuTransaksiLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );

        menuDatatransaksi.setBackground(new java.awt.Color(255, 255, 255));
        menuDatatransaksi.setPreferredSize(new java.awt.Dimension(0, 36));

        datatransaksiLabel.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        datatransaksiLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/monitor (1).png"))); // NOI18N
        datatransaksiLabel.setText("Pengambilan Barang");
        datatransaksiLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        datatransaksiLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                datatransaksiLabelMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                datatransaksiLabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout menuDatatransaksiLayout = new javax.swing.GroupLayout(menuDatatransaksi);
        menuDatatransaksi.setLayout(menuDatatransaksiLayout);
        menuDatatransaksiLayout.setHorizontalGroup(
            menuDatatransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuDatatransaksiLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(datatransaksiLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        menuDatatransaksiLayout.setVerticalGroup(
            menuDatatransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(datatransaksiLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );

        menuDataPelanggan.setBackground(new java.awt.Color(255, 255, 255));

        dataPelangganLabel.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        dataPelangganLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/customer.png"))); // NOI18N
        dataPelangganLabel.setText("Data Pelanggan");
        dataPelangganLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        dataPelangganLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dataPelangganLabelMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                dataPelangganLabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout menuDataPelangganLayout = new javax.swing.GroupLayout(menuDataPelanggan);
        menuDataPelanggan.setLayout(menuDataPelangganLayout);
        menuDataPelangganLayout.setHorizontalGroup(
            menuDataPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuDataPelangganLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(dataPelangganLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        menuDataPelangganLayout.setVerticalGroup(
            menuDataPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dataPelangganLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );

        menuHistoryTransaksi.setBackground(new java.awt.Color(255, 255, 255));
        menuHistoryTransaksi.setPreferredSize(new java.awt.Dimension(0, 36));

        historyTransaksiLabel.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        historyTransaksiLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/data_pelanggan.png"))); // NOI18N
        historyTransaksiLabel.setText("Laporan Transaksi");
        historyTransaksiLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        historyTransaksiLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                historyTransaksiLabelMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                historyTransaksiLabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout menuHistoryTransaksiLayout = new javax.swing.GroupLayout(menuHistoryTransaksi);
        menuHistoryTransaksi.setLayout(menuHistoryTransaksiLayout);
        menuHistoryTransaksiLayout.setHorizontalGroup(
            menuHistoryTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuHistoryTransaksiLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(historyTransaksiLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        menuHistoryTransaksiLayout.setVerticalGroup(
            menuHistoryTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(historyTransaksiLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );

        menuDataPaket.setBackground(new java.awt.Color(255, 255, 255));
        menuDataPaket.setPreferredSize(new java.awt.Dimension(0, 36));

        dataPaketLabel.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        dataPaketLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/logistics-delivery.png"))); // NOI18N
        dataPaketLabel.setText("Data Paket");
        dataPaketLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        dataPaketLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dataPaketLabelMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                dataPaketLabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout menuDataPaketLayout = new javax.swing.GroupLayout(menuDataPaket);
        menuDataPaket.setLayout(menuDataPaketLayout);
        menuDataPaketLayout.setHorizontalGroup(
            menuDataPaketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuDataPaketLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(dataPaketLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        menuDataPaketLayout.setVerticalGroup(
            menuDataPaketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dataPaketLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout menuLayout = new javax.swing.GroupLayout(menu);
        menu.setLayout(menuLayout);
        menuLayout.setHorizontalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuDashboard, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
            .addComponent(menuTransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
            .addComponent(menuDatatransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
            .addComponent(menuDataPelanggan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(menuHistoryTransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
            .addComponent(menuDataPaket, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
        );
        menuLayout.setVerticalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(menuDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menuTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menuDatatransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menuDataPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menuHistoryTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menuDataPaket, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(118, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout wrapperMenuLayout = new javax.swing.GroupLayout(wrapperMenu);
        wrapperMenu.setLayout(wrapperMenuLayout);
        wrapperMenuLayout.setHorizontalGroup(
            wrapperMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(logo, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
            .addGroup(wrapperMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        wrapperMenuLayout.setVerticalGroup(
            wrapperMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapperMenuLayout.createSequentialGroup()
                .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(wrapperMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(wrapperMenuLayout.createSequentialGroup()
                    .addGap(110, 110, 110)
                    .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(110, Short.MAX_VALUE)))
        );

        wrapperKontent.setBackground(new java.awt.Color(255, 255, 255));

        tempatJuduldanUser.setBackground(new java.awt.Color(255, 255, 255));

        judulDb.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        judulDb.setText("Dashboard");

        jLabel4.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/settings (1).png"))); // NOI18N
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        namaLoginKasir.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        namaLoginKasir.setText("Agung Kurniawan");
        namaLoginKasir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/man_1.png"))); // NOI18N
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel3.setBackground(new java.awt.Color(131, 193, 255));
        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(131, 193, 255));
        jLabel3.setText("Kasir Laundry");
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout tempatJuduldanUserLayout = new javax.swing.GroupLayout(tempatJuduldanUser);
        tempatJuduldanUser.setLayout(tempatJuduldanUserLayout);
        tempatJuduldanUserLayout.setHorizontalGroup(
            tempatJuduldanUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tempatJuduldanUserLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(judulDb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tempatJuduldanUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(namaLoginKasir)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(25, 25, 25))
        );
        tempatJuduldanUserLayout.setVerticalGroup(
            tempatJuduldanUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tempatJuduldanUserLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(tempatJuduldanUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tempatJuduldanUserLayout.createSequentialGroup()
                        .addComponent(judulDb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(43, 43, 43))
                    .addGroup(tempatJuduldanUserLayout.createSequentialGroup()
                        .addGroup(tempatJuduldanUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addGroup(tempatJuduldanUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addGroup(tempatJuduldanUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4)
                                    .addComponent(namaLoginKasir))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        containerContent.setBackground(new java.awt.Color(102, 204, 0));
        containerContent.setLayout(new java.awt.CardLayout());

        tampilanDashboardUtama.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(51, 51, 255));

        jLabel5.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel5.setText("Hasil Uang Hari Ini");

        jLabel7.setFont(new java.awt.Font("Open Sans", 1, 18)); // NOI18N
        jLabel7.setText("150000");

        jLabel6.setFont(new java.awt.Font("Open Sans", 1, 18)); // NOI18N
        jLabel6.setText("Rp.");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 92, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 90, Short.MAX_VALUE)
        );

        jLabel14.setBackground(new java.awt.Color(51, 51, 255));
        jLabel14.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 255));
        jLabel14.setText("Halaman data >>>");
        jLabel14.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE))
                        .addGap(30, 30, 30))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel14)))
                .addGap(47, 47, 47))
        );

        jPanel2.setBackground(new java.awt.Color(51, 0, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(316, 184));

        jLabel8.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel8.setText("Jumlah Pelanggan");

        jLabel9.setFont(new java.awt.Font("Open Sans", 1, 18)); // NOI18N
        jLabel9.setText("+");

        jLabel10.setFont(new java.awt.Font("Open Sans", 1, 18)); // NOI18N
        jLabel10.setText("360");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 90, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel15.setBackground(new java.awt.Color(51, 51, 255));
        jLabel15.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 255));
        jLabel15.setText("Halaman Pelanggan >>>");
        jLabel15.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE))
                        .addGap(28, 28, 28))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                        .addComponent(jLabel15)))
                .addGap(47, 47, 47))
        );

        jPanel3.setBackground(new java.awt.Color(0, 51, 255));

        jLabel11.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel11.setText("Pelanggan Baru");

        jLabel13.setFont(new java.awt.Font("Open Sans", 1, 18)); // NOI18N
        jLabel13.setText("+");

        jLabel12.setFont(new java.awt.Font("Open Sans", 1, 18)); // NOI18N
        jLabel12.setText("300");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 90, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel16.setBackground(new java.awt.Color(51, 51, 255));
        jLabel16.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 255));
        jLabel16.setText("Halaman data>>>");
        jLabel16.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(1, 1, 1))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                        .addComponent(jLabel16)))
                .addGap(48, 48, 48))
        );

        jPanel7.setBackground(new java.awt.Color(255, 0, 204));

        jLabel17.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(102, 102, 102));
        jLabel17.setText("Transaksi mudah pada pelanggan");

        jLabel18.setFont(new java.awt.Font("Open Sans", 1, 24)); // NOI18N
        jLabel18.setText("Transaksi Pelanggan");

        jLabel19.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        jLabel19.setText("Pelaksanaan transaksi menjadi lebih mudah,");

        jLabel20.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        jLabel20.setText("beserta penjelasan kinerja dari fitur transaksi, ");

        jLabel21.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        jLabel21.setText("klik tombol dibawah untuk melakukan transaksi");

        jLabel22.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        jLabel22.setText("dengan pelanggan baru atau sudah terdaftar.");

        kButton1.setText("Transaksi Sekarang");
        kButton1.setBorderPainted(false);
        kButton1.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
        kButton1.setkBackGroundColor(new java.awt.Color(102, 153, 255));
        kButton1.setkBorderRadius(20);
        kButton1.setkEndColor(new java.awt.Color(102, 153, 255));
        kButton1.setkHoverEndColor(new java.awt.Color(0, 0, 255));
        kButton1.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        kButton1.setkHoverStartColor(new java.awt.Color(0, 0, 255));
        kButton1.setkPressedColor(new java.awt.Color(102, 153, 255));
        kButton1.setkSelectedColor(new java.awt.Color(102, 153, 255));
        kButton1.setkStartColor(new java.awt.Color(102, 153, 255));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(kButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(jLabel21)
                    .addComponent(jLabel20)
                    .addComponent(jLabel19)
                    .addComponent(jLabel18)
                    .addComponent(jLabel17))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jLabel17)
                .addGap(18, 18, 18)
                .addComponent(jLabel18)
                .addGap(18, 18, 18)
                .addComponent(jLabel19)
                .addGap(0, 0, 0)
                .addComponent(jLabel20)
                .addGap(0, 0, 0)
                .addComponent(jLabel21)
                .addGap(0, 0, 0)
                .addComponent(jLabel22)
                .addGap(27, 27, 27)
                .addComponent(kButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 0));

        jLabel23.setFont(new java.awt.Font("Open Sans", 1, 18)); // NOI18N
        jLabel23.setText("Profile Admin");

        jLabel24.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        jLabel24.setText("Anda bisa melihat profile dari admin");

        jLabel25.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        jLabel25.setText("laundry app ini dan anda bisa hubungi");

        jLabel26.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        jLabel26.setText("admin jika terdapat kebutuhan tertentu.");

        kButton2.setText("Lihat");
        kButton2.setBorderPainted(false);
        kButton2.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
        kButton2.setkBackGroundColor(new java.awt.Color(102, 153, 255));
        kButton2.setkEndColor(new java.awt.Color(102, 153, 255));
        kButton2.setkHoverEndColor(new java.awt.Color(51, 51, 255));
        kButton2.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        kButton2.setkHoverStartColor(new java.awt.Color(51, 51, 255));
        kButton2.setkPressedColor(new java.awt.Color(102, 153, 255));
        kButton2.setkSelectedColor(new java.awt.Color(102, 153, 255));
        kButton2.setkStartColor(new java.awt.Color(102, 153, 255));

        jLabel27.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        jLabel27.setText("Silahkan hubungi admin jika terdapat");

        jLabel28.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        jLabel28.setText("keperluan penting, terimakasih.");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(59, 59, 59))
                            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(kButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(10, 10, 10))
                            .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(46, 46, 46))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(37, 37, 37)
                .addComponent(kButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61))
        );

        javax.swing.GroupLayout tampilanDashboardUtamaLayout = new javax.swing.GroupLayout(tampilanDashboardUtama);
        tampilanDashboardUtama.setLayout(tampilanDashboardUtamaLayout);
        tampilanDashboardUtamaLayout.setHorizontalGroup(
            tampilanDashboardUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tampilanDashboardUtamaLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(tampilanDashboardUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(tampilanDashboardUtamaLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tampilanDashboardUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(27, 27, 27))
        );
        tampilanDashboardUtamaLayout.setVerticalGroup(
            tampilanDashboardUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tampilanDashboardUtamaLayout.createSequentialGroup()
                .addGroup(tampilanDashboardUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tampilanDashboardUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        containerContent.add(tampilanDashboardUtama, "card2");

        tampilanTransaksi.setBackground(new java.awt.Color(255, 255, 255));

        containerTransaksi.setBackground(new java.awt.Color(255, 255, 255));
        containerTransaksi.setPreferredSize(new java.awt.Dimension(503, 100));
        containerTransaksi.setLayout(new java.awt.CardLayout());

        tampilanAwalTransaksi.setBackground(new java.awt.Color(255, 255, 255));

        tabel_data_pelanggan_2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Name", "Age", "Tel", "Address"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabel_data_pelanggan_2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_data_pelanggan_2MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel_data_pelanggan_2);

        jLabel29.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        jLabel29.setText("Cari data pelanggan untuk transaksi, jika ada tekan");

        jLabel30.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        jLabel30.setText("data table lalu tekan tombol lanjut transaksi");

        kButton3.setText("Lanjut ke Transaksi");
        kButton3.setBorderPainted(false);
        kButton3.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
        kButton3.setkBackGroundColor(new java.awt.Color(102, 153, 255));
        kButton3.setkEndColor(new java.awt.Color(102, 153, 255));
        kButton3.setkHoverEndColor(new java.awt.Color(0, 0, 255));
        kButton3.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        kButton3.setkHoverStartColor(new java.awt.Color(0, 0, 255));
        kButton3.setkPressedColor(new java.awt.Color(102, 153, 255));
        kButton3.setkSelectedColor(new java.awt.Color(102, 153, 255));
        kButton3.setkStartColor(new java.awt.Color(102, 153, 255));
        kButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButton3ActionPerformed(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
        jLabel31.setText("Input data pelanggan baru dan berlanjut ke transaksi");

        jLabel32.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
        jLabel32.setText("jika data belum terdaftar.");

        kButton4.setText("Klik Disini");
        kButton4.setBorderPainted(false);
        kButton4.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
        kButton4.setkBackGroundColor(new java.awt.Color(102, 153, 255));
        kButton4.setkEndColor(new java.awt.Color(102, 153, 255));
        kButton4.setkHoverEndColor(new java.awt.Color(0, 0, 255));
        kButton4.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        kButton4.setkHoverStartColor(new java.awt.Color(0, 0, 255));
        kButton4.setkPressedColor(new java.awt.Color(102, 153, 255));
        kButton4.setkSelectedColor(new java.awt.Color(102, 153, 255));
        kButton4.setkStartColor(new java.awt.Color(102, 153, 255));
        kButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButton4ActionPerformed(evt);
            }
        });

        field_cari_data_pelanggan.setBackground(new java.awt.Color(245, 245, 245));
        field_cari_data_pelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                field_cari_data_pelangganActionPerformed(evt);
            }
        });

        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/illustration/client.png"))); // NOI18N

        javax.swing.GroupLayout tampilanAwalTransaksiLayout = new javax.swing.GroupLayout(tampilanAwalTransaksi);
        tampilanAwalTransaksi.setLayout(tampilanAwalTransaksiLayout);
        tampilanAwalTransaksiLayout.setHorizontalGroup(
            tampilanAwalTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tampilanAwalTransaksiLayout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addGroup(tampilanAwalTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tampilanAwalTransaksiLayout.createSequentialGroup()
                        .addGroup(tampilanAwalTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel29)
                            .addComponent(kButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(tampilanAwalTransaksiLayout.createSequentialGroup()
                        .addGroup(tampilanAwalTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
                            .addComponent(field_cari_data_pelanggan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(tampilanAwalTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tampilanAwalTransaksiLayout.createSequentialGroup()
                                .addGap(275, 275, 275)
                                .addComponent(jLabel33)
                                .addGap(238, 238, 238))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tampilanAwalTransaksiLayout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addGroup(tampilanAwalTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tampilanAwalTransaksiLayout.createSequentialGroup()
                                        .addGroup(tampilanAwalTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(kButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(80, 80, 80)))
                                .addGap(107, 107, 107))))))
        );
        tampilanAwalTransaksiLayout.setVerticalGroup(
            tampilanAwalTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tampilanAwalTransaksiLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(tampilanAwalTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tampilanAwalTransaksiLayout.createSequentialGroup()
                        .addComponent(jLabel33)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel31)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel32)
                        .addGap(18, 18, 18)
                        .addComponent(kButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tampilanAwalTransaksiLayout.createSequentialGroup()
                        .addComponent(field_cari_data_pelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel30)
                .addGap(18, 18, 18)
                .addComponent(kButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(133, 133, 133))
        );

        containerTransaksi.add(tampilanAwalTransaksi, "card2");

        inputPelanggan.setBackground(new java.awt.Color(255, 255, 255));

        jLabel34.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel34.setText("Masukkan data pelanggan di bawah :");

        field_id_pelanggan_tr.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        field_id_pelanggan_tr.setLabelText("ID Pelanggan");
        field_id_pelanggan_tr.setPreferredSize(new java.awt.Dimension(375, 46));

        kButton5.setText("Simpan dan Lanjut");
        kButton5.setBorderPainted(false);
        kButton5.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
        kButton5.setkBackGroundColor(new java.awt.Color(0, 0, 255));
        kButton5.setkBorderRadius(20);
        kButton5.setkEndColor(new java.awt.Color(0, 0, 255));
        kButton5.setkHoverEndColor(new java.awt.Color(102, 153, 255));
        kButton5.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        kButton5.setkHoverStartColor(new java.awt.Color(102, 153, 255));
        kButton5.setkPressedColor(new java.awt.Color(0, 0, 255));
        kButton5.setkSelectedColor(new java.awt.Color(0, 0, 255));
        kButton5.setkStartColor(new java.awt.Color(0, 0, 255));

        textField1.setLabelText("Alamat Pelanggan");

        textField2.setLabelText("Nomor Pelanggan");

        textField4.setLabelText("Nama Pelanggan");

        jLabel35.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/illustration/before.png"))); // NOI18N
        jLabel35.setText("Kembali");
        jLabel35.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel35.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel35MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout inputPelangganLayout = new javax.swing.GroupLayout(inputPelanggan);
        inputPelanggan.setLayout(inputPelangganLayout);
        inputPelangganLayout.setHorizontalGroup(
            inputPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inputPelangganLayout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addGroup(inputPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(inputPelangganLayout.createSequentialGroup()
                        .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(650, 650, 650))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, inputPelangganLayout.createSequentialGroup()
                        .addGroup(inputPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(kButton5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(inputPelangganLayout.createSequentialGroup()
                                .addGroup(inputPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textField4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(field_id_pelanggan_tr, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                                .addGap(128, 128, 128)
                                .addGroup(inputPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textField1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(textField2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(125, 125, 125))))
            .addGroup(inputPelangganLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel35)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        inputPelangganLayout.setVerticalGroup(
            inputPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inputPelangganLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel35)
                .addGap(46, 46, 46)
                .addComponent(jLabel34)
                .addGap(18, 18, 18)
                .addGroup(inputPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(field_id_pelanggan_tr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(inputPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addComponent(kButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(178, Short.MAX_VALUE))
        );

        containerTransaksi.add(inputPelanggan, "card3");

        lanjutTransaksi1.setBackground(new java.awt.Color(255, 255, 255));

        field_id_pelanggan.setLabelText("ID Pelanggan");

        field_kode_nota.setLabelText("Kode Transaksi");

        field_id_user.setLabelText("ID Anda ");

        jLabel38.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel38.setText("ID Pelanggan");

        jLabel39.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel39.setText("ID Pelanggan pada kolom dibawah adalah pelanggan");

        jLabel40.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel40.setText("yang akan melakukan transaksi");

        jLabel41.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel41.setText("Kode Transaksi");

        jLabel42.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel42.setText("Pada Field dibawah adalah kode transaksi yang akan");

        jLabel43.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel43.setText("tampil pada nota dan kunci data transaksi.");

        jLabel44.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel44.setText("ID Anda Sebagai Kasir");

        jLabel45.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel45.setText("ID anda akan tampil pada data dan nota transaksi");

        jLabel46.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel46.setText("sehingga admin tahu bahwa anda telah melakukan transaksi");

        kButton8.setText("Selanjutnya");
        kButton8.setBorderPainted(false);
        kButton8.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
        kButton8.setkBackGroundColor(new java.awt.Color(35, 134, 54));
        kButton8.setkEndColor(new java.awt.Color(35, 134, 54));
        kButton8.setkHoverEndColor(new java.awt.Color(51, 204, 0));
        kButton8.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        kButton8.setkHoverStartColor(new java.awt.Color(51, 204, 0));
        kButton8.setkPressedColor(new java.awt.Color(35, 134, 54));
        kButton8.setkSelectedColor(new java.awt.Color(35, 134, 54));
        kButton8.setkStartColor(new java.awt.Color(35, 134, 54));
        kButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButton8ActionPerformed(evt);
            }
        });

        jLabel47.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconJoptionPane/menu.png"))); // NOI18N
        jLabel47.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel47.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel47MouseClicked(evt);
            }
        });

        jLabel48.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconJoptionPane/menu.png"))); // NOI18N
        jLabel48.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel48.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel48MouseClicked(evt);
            }
        });

        jLabel49.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel49.setIcon(new javax.swing.ImageIcon(getClass().getResource("/illustration/before.png"))); // NOI18N
        jLabel49.setText("Kembali");
        jLabel49.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel49.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel49MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout lanjutTransaksi1Layout = new javax.swing.GroupLayout(lanjutTransaksi1);
        lanjutTransaksi1.setLayout(lanjutTransaksi1Layout);
        lanjutTransaksi1Layout.setHorizontalGroup(
            lanjutTransaksi1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lanjutTransaksi1Layout.createSequentialGroup()
                .addGap(165, 165, 165)
                .addGroup(lanjutTransaksi1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel47)
                    .addComponent(jLabel48))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(lanjutTransaksi1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lanjutTransaksi1Layout.createSequentialGroup()
                        .addComponent(kButton8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(lanjutTransaksi1Layout.createSequentialGroup()
                        .addGroup(lanjutTransaksi1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(lanjutTransaksi1Layout.createSequentialGroup()
                                .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(173, 173, 173))
                            .addGroup(lanjutTransaksi1Layout.createSequentialGroup()
                                .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(117, 117, 117))
                            .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(lanjutTransaksi1Layout.createSequentialGroup()
                                .addComponent(field_id_pelanggan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(31, 31, 31)))
                        .addGap(90, 90, 90)
                        .addGroup(lanjutTransaksi1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(lanjutTransaksi1Layout.createSequentialGroup()
                                .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(149, 149, 149))
                            .addGroup(lanjutTransaksi1Layout.createSequentialGroup()
                                .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(52, 52, 52))
                            .addGroup(lanjutTransaksi1Layout.createSequentialGroup()
                                .addComponent(field_kode_nota, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(25, 25, 25)))
                        .addGap(166, 166, 166))
                    .addGroup(lanjutTransaksi1Layout.createSequentialGroup()
                        .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(633, 633, 633))
                    .addGroup(lanjutTransaksi1Layout.createSequentialGroup()
                        .addGroup(lanjutTransaksi1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(lanjutTransaksi1Layout.createSequentialGroup()
                                .addComponent(jLabel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(303, 303, 303))
                            .addComponent(field_id_user, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lanjutTransaksi1Layout.createSequentialGroup()
                                .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(362, 362, 362)))
                        .addGap(191, 191, 191))))
            .addGroup(lanjutTransaksi1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel49)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        lanjutTransaksi1Layout.setVerticalGroup(
            lanjutTransaksi1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lanjutTransaksi1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(lanjutTransaksi1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lanjutTransaksi1Layout.createSequentialGroup()
                        .addComponent(jLabel38)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel39)
                        .addGroup(lanjutTransaksi1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(lanjutTransaksi1Layout.createSequentialGroup()
                                .addComponent(jLabel40)
                                .addGap(18, 18, 18)
                                .addComponent(field_id_pelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel47)))
                    .addGroup(lanjutTransaksi1Layout.createSequentialGroup()
                        .addComponent(jLabel41)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel42)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel43)
                        .addGap(18, 18, 18)
                        .addComponent(field_kode_nota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(86, 86, 86)
                .addComponent(jLabel44)
                .addGap(18, 18, 18)
                .addComponent(jLabel45)
                .addGap(0, 0, 0)
                .addComponent(jLabel46)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(lanjutTransaksi1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(field_id_user, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel48))
                .addGap(48, 48, 48)
                .addComponent(kButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(19, 19, 19))
        );

        containerTransaksi.add(lanjutTransaksi1, "card5");

        lanjutTransaksi2.setBackground(new java.awt.Color(255, 255, 255));

        tabel_barang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Name", "Age", "Tel", "Address"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabel_barang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_barangMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabel_barang);

        field_harga_paket.setLabelText("Harga Paket");

        jLabel36.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel36.setText("Pilih / tekan data paket yang dipilh pelanggan lalu isi form dibawah.");

        field_keterangan.setLabelText("Keterangan Barang");

        field_subtotal.setLabelText("Subtotal Harga");

        kButton6.setText("Simpan ke keranjang");
        kButton6.setBorderPainted(false);
        kButton6.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
        kButton6.setkBackGroundColor(new java.awt.Color(51, 51, 255));
        kButton6.setkEndColor(new java.awt.Color(51, 51, 255));
        kButton6.setkHoverEndColor(new java.awt.Color(102, 153, 255));
        kButton6.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        kButton6.setkHoverStartColor(new java.awt.Color(102, 153, 255));
        kButton6.setkPressedColor(new java.awt.Color(51, 51, 255));
        kButton6.setkSelectedColor(new java.awt.Color(51, 51, 255));
        kButton6.setkStartColor(new java.awt.Color(51, 51, 255));
        kButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButton6ActionPerformed(evt);
            }
        });

        kButton7.setText("Lanjutkan");
        kButton7.setBorderPainted(false);
        kButton7.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
        kButton7.setkBackGroundColor(new java.awt.Color(35, 134, 54));
        kButton7.setkEndColor(new java.awt.Color(35, 134, 54));
        kButton7.setkHoverEndColor(new java.awt.Color(51, 204, 0));
        kButton7.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        kButton7.setkHoverStartColor(new java.awt.Color(51, 204, 0));
        kButton7.setkPressedColor(new java.awt.Color(35, 134, 54));
        kButton7.setkSelectedColor(new java.awt.Color(35, 134, 54));
        kButton7.setkStartColor(new java.awt.Color(35, 134, 54));
        kButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButton7ActionPerformed(evt);
            }
        });

        jLabel37.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/illustration/before.png"))); // NOI18N
        jLabel37.setText("Kembali");
        jLabel37.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel37.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel37MouseClicked(evt);
            }
        });

        field_jumlah_barang.setLabelText("Masukkan Jumlah Paket");

        javax.swing.GroupLayout lanjutTransaksi2Layout = new javax.swing.GroupLayout(lanjutTransaksi2);
        lanjutTransaksi2.setLayout(lanjutTransaksi2Layout);
        lanjutTransaksi2Layout.setHorizontalGroup(
            lanjutTransaksi2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lanjutTransaksi2Layout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)
                .addGap(44, 44, 44)
                .addGroup(lanjutTransaksi2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel36)
                    .addComponent(field_harga_paket, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(field_keterangan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(field_subtotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(lanjutTransaksi2Layout.createSequentialGroup()
                        .addComponent(kButton6, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(kButton7, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE))
                    .addComponent(field_jumlah_barang, javax.swing.GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE))
                .addGap(49, 49, 49))
            .addGroup(lanjutTransaksi2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel37)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        lanjutTransaksi2Layout.setVerticalGroup(
            lanjutTransaksi2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lanjutTransaksi2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel37)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(lanjutTransaksi2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lanjutTransaksi2Layout.createSequentialGroup()
                        .addComponent(jLabel36)
                        .addGap(18, 18, 18)
                        .addComponent(field_harga_paket, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(field_keterangan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(field_jumlah_barang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(field_subtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(lanjutTransaksi2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(kButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(kButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(82, 82, 82))
        );

        containerTransaksi.add(lanjutTransaksi2, "card4");

        lanjutTransaksi3.setBackground(new java.awt.Color(255, 255, 255));

        tabel_penjualan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Name", "Age", "Tel", "Address"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabel_penjualan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_penjualanMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tabel_penjualan);

        field_total_harga.setLabelText("Total Harga");

        jLabel50.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel50.setText("Tabel disamping adalah paket cuci yang dipilih oleh pelanggan.");

        field_bayar.setLabelText("Masukkan Pembayaran Pelanggan");

        field_status_bayar.setLabelText("Status Bayar");

        field_sisa_bayar.setLabelText("Sisa Harus Dibayar Oleh Client");

        jLabel51.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel51.setIcon(new javax.swing.ImageIcon(getClass().getResource("/illustration/before.png"))); // NOI18N
        jLabel51.setText("Kembali");
        jLabel51.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel51.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel51MouseClicked(evt);
            }
        });

        field_kembalian.setLabelText("Kembalian");
        field_kembalian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                field_kembalianActionPerformed(evt);
            }
        });

        kButton9.setText("Hapus Paket");
        kButton9.setBorderPainted(false);
        kButton9.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        kButton9.setkBackGroundColor(new java.awt.Color(255, 51, 51));
        kButton9.setkEndColor(new java.awt.Color(255, 51, 51));
        kButton9.setkHoverEndColor(new java.awt.Color(204, 0, 0));
        kButton9.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        kButton9.setkHoverStartColor(new java.awt.Color(204, 0, 0));
        kButton9.setkPressedColor(new java.awt.Color(255, 51, 51));
        kButton9.setkSelectedColor(new java.awt.Color(255, 51, 51));
        kButton9.setkStartColor(new java.awt.Color(255, 51, 51));
        kButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButton9ActionPerformed(evt);
            }
        });

        kButton10.setText("Simpan Transaksi");
        kButton10.setBorderPainted(false);
        kButton10.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        kButton10.setkBackGroundColor(new java.awt.Color(51, 51, 255));
        kButton10.setkEndColor(new java.awt.Color(51, 51, 255));
        kButton10.setkHoverEndColor(new java.awt.Color(102, 153, 255));
        kButton10.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        kButton10.setkHoverStartColor(new java.awt.Color(102, 153, 255));
        kButton10.setkPressedColor(new java.awt.Color(51, 51, 255));
        kButton10.setkSelectedColor(new java.awt.Color(51, 51, 255));
        kButton10.setkStartColor(new java.awt.Color(51, 51, 255));
        kButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout lanjutTransaksi3Layout = new javax.swing.GroupLayout(lanjutTransaksi3);
        lanjutTransaksi3.setLayout(lanjutTransaksi3Layout);
        lanjutTransaksi3Layout.setHorizontalGroup(
            lanjutTransaksi3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lanjutTransaksi3Layout.createSequentialGroup()
                .addGap(286, 286, 286)
                .addComponent(kButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(kButton10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addGroup(lanjutTransaksi3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(field_kembalian, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(lanjutTransaksi3Layout.createSequentialGroup()
                        .addComponent(jLabel50)
                        .addGap(0, 1, Short.MAX_VALUE))
                    .addComponent(field_total_harga, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(field_bayar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(field_status_bayar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(field_sisa_bayar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(68, 68, 68))
            .addGroup(lanjutTransaksi3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(lanjutTransaksi3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(lanjutTransaksi3Layout.createSequentialGroup()
                    .addGap(70, 70, 70)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(462, Short.MAX_VALUE)))
        );
        lanjutTransaksi3Layout.setVerticalGroup(
            lanjutTransaksi3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lanjutTransaksi3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel51)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(lanjutTransaksi3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lanjutTransaksi3Layout.createSequentialGroup()
                        .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(field_total_harga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(lanjutTransaksi3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(kButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(kButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(field_bayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(field_status_bayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(field_sisa_bayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(field_kembalian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(128, Short.MAX_VALUE))
            .addGroup(lanjutTransaksi3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(lanjutTransaksi3Layout.createSequentialGroup()
                    .addGap(90, 90, 90)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(127, Short.MAX_VALUE)))
        );

        containerTransaksi.add(lanjutTransaksi3, "card6");

        javax.swing.GroupLayout tampilanTransaksiLayout = new javax.swing.GroupLayout(tampilanTransaksi);
        tampilanTransaksi.setLayout(tampilanTransaksiLayout);
        tampilanTransaksiLayout.setHorizontalGroup(
            tampilanTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(containerTransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, 1035, Short.MAX_VALUE)
        );
        tampilanTransaksiLayout.setVerticalGroup(
            tampilanTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(containerTransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, 507, Short.MAX_VALUE)
        );

        containerContent.add(tampilanTransaksi, "card3");

        tampilanDataTransaksi.setBackground(new java.awt.Color(255, 255, 255));
        tampilanDataTransaksi.setLayout(new java.awt.CardLayout());

        containerDataTransaksi.setLayout(new java.awt.CardLayout());

        tahapPertama.setBackground(new java.awt.Color(255, 255, 255));

        tabel_data_transaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Name", "Age", "Tel", "Address"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabel_data_transaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_data_transaksiMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tabel_data_transaksi);

        kButton11.setText("Lakukan Pengembalian");
        kButton11.setBorderPainted(false);
        kButton11.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        kButton11.setkBackGroundColor(new java.awt.Color(35, 134, 54));
        kButton11.setkEndColor(new java.awt.Color(35, 134, 54));
        kButton11.setkHoverEndColor(new java.awt.Color(102, 204, 0));
        kButton11.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        kButton11.setkHoverStartColor(new java.awt.Color(102, 204, 0));
        kButton11.setkIndicatorColor(new java.awt.Color(0, 255, 0));
        kButton11.setkPressedColor(new java.awt.Color(35, 134, 54));
        kButton11.setkSelectedColor(new java.awt.Color(35, 134, 54));
        kButton11.setkStartColor(new java.awt.Color(35, 134, 54));
        kButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButton11ActionPerformed(evt);
            }
        });

        field_cari_data_transaksi.setBackground(new java.awt.Color(245, 245, 245));
        field_cari_data_transaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                field_cari_data_transaksiActionPerformed(evt);
            }
        });

        field_lanjut_pengembalian.setForeground(new java.awt.Color(255, 255, 255));
        field_lanjut_pengembalian.setText("jTextField1");
        field_lanjut_pengembalian.setBorder(null);

        javax.swing.GroupLayout tahapPertamaLayout = new javax.swing.GroupLayout(tahapPertama);
        tahapPertama.setLayout(tahapPertamaLayout);
        tahapPertamaLayout.setHorizontalGroup(
            tahapPertamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tahapPertamaLayout.createSequentialGroup()
                .addGap(113, 113, 113)
                .addGroup(tahapPertamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tahapPertamaLayout.createSequentialGroup()
                        .addComponent(kButton11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(field_lanjut_pengembalian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tahapPertamaLayout.createSequentialGroup()
                        .addGroup(tahapPertamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(field_cari_data_transaksi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 809, Short.MAX_VALUE))
                        .addGap(113, 113, 113))))
        );
        tahapPertamaLayout.setVerticalGroup(
            tahapPertamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tahapPertamaLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(field_cari_data_transaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tahapPertamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(field_lanjut_pengembalian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(86, Short.MAX_VALUE))
        );

        containerDataTransaksi.add(tahapPertama, "card2");

        tahapKedua.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel2.setText("Kode Struk");

        jLabel52.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel52.setText("NO. transaksi yang dilakukan");

        kd_struk_data_transaksi.setLabelText("Kode Transaksi");
        kd_struk_data_transaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kd_struk_data_transaksiMouseClicked(evt);
            }
        });

        jLabel53.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel53.setText("ID Pelanggan");

        jLabel54.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel54.setText("Nomor ID pelanggan yang melakukan");

        jLabel55.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel55.setText("transaksi dan pengambilan");

        id_pelanggan_dt.setLabelText("ID Pelanggan");

        jLabel56.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel56.setText("ID Kasir");

        jLabel57.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel57.setText("Nomor ID anda sebagai kasir yang");

        jLabel58.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel58.setText("melakukan transaksi pengembalian");

        id_kasir_dt.setLabelText("ID Kasir");

        jLabel59.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel59.setText("Tanggal Pesan");

        jLabel60.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel60.setText("Tanggal laundry pelanggan");

        tanggal_pesan_dt.setLabelText("Tanggal Pesan");

        jLabel61.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel61.setText("Tanggal selesai");

        jLabel62.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel62.setText("Tanggal selesai laundry");

        jLabel63.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel63.setText("Status Ambil");

        jLabel64.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel64.setText("Nomor ID anda sebagai kasir yang");

        jLabel65.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel65.setText("melakukan transaksi pengembalian");

        tanggal_selesai_dt.setLabelText("Tanggal Selesai");
        tanggal_selesai_dt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tanggal_selesai_dtActionPerformed(evt);
            }
        });

        status_ambil_dt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "belum di ambil", "sudah di ambil" }));
        status_ambil_dt.setSelectedIndex(-1);
        status_ambil_dt.setLabeText("Status Ambil");

        kButton12.setText("Lanjutkan");
        kButton12.setBorderPainted(false);
        kButton12.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
        kButton12.setkBackGroundColor(new java.awt.Color(102, 153, 255));
        kButton12.setkEndColor(new java.awt.Color(102, 153, 255));
        kButton12.setkHoverEndColor(new java.awt.Color(0, 0, 255));
        kButton12.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        kButton12.setkHoverStartColor(new java.awt.Color(0, 0, 255));
        kButton12.setkPressedColor(new java.awt.Color(102, 153, 255));
        kButton12.setkSelectedColor(new java.awt.Color(102, 153, 255));
        kButton12.setkStartColor(new java.awt.Color(102, 153, 255));
        kButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButton12ActionPerformed(evt);
            }
        });

        jLabel66.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel66.setIcon(new javax.swing.ImageIcon(getClass().getResource("/illustration/before.png"))); // NOI18N
        jLabel66.setText("Kembali");
        jLabel66.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel66.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel66MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout tahapKeduaLayout = new javax.swing.GroupLayout(tahapKedua);
        tahapKedua.setLayout(tahapKeduaLayout);
        tahapKeduaLayout.setHorizontalGroup(
            tahapKeduaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tahapKeduaLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(tahapKeduaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel55)
                    .addComponent(jLabel54)
                    .addComponent(jLabel53)
                    .addComponent(jLabel52)
                    .addComponent(jLabel2)
                    .addComponent(kd_struk_data_transaksi, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                    .addComponent(id_pelanggan_dt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(56, 56, 56)
                .addGroup(tahapKeduaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(kButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel63)
                    .addComponent(status_ambil_dt, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(id_kasir_dt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel58)
                    .addComponent(jLabel57)
                    .addComponent(jLabel56)
                    .addComponent(jLabel65)
                    .addComponent(jLabel64))
                .addGap(60, 60, 60)
                .addGroup(tahapKeduaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel60)
                    .addComponent(jLabel59)
                    .addComponent(jLabel62)
                    .addComponent(jLabel61)
                    .addComponent(tanggal_selesai_dt, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tanggal_pesan_dt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(49, 49, 49))
            .addGroup(tahapKeduaLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel66)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tahapKeduaLayout.setVerticalGroup(
            tahapKeduaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tahapKeduaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel66)
                .addGap(18, 18, 18)
                .addGroup(tahapKeduaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(tahapKeduaLayout.createSequentialGroup()
                        .addComponent(jLabel56)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel57)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel58)
                        .addGap(18, 18, 18)
                        .addComponent(id_kasir_dt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tahapKeduaLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel52)
                        .addGap(21, 21, 21)
                        .addComponent(kd_struk_data_transaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tahapKeduaLayout.createSequentialGroup()
                        .addComponent(jLabel59)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel60)
                        .addGap(21, 21, 21)
                        .addComponent(tanggal_pesan_dt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(70, 70, 70)
                .addGroup(tahapKeduaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(tahapKeduaLayout.createSequentialGroup()
                        .addComponent(jLabel63)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel64)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel65)
                        .addGap(18, 18, 18)
                        .addComponent(status_ambil_dt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tahapKeduaLayout.createSequentialGroup()
                        .addComponent(jLabel53)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel54)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel55)
                        .addGap(18, 18, 18)
                        .addComponent(id_pelanggan_dt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tahapKeduaLayout.createSequentialGroup()
                        .addComponent(jLabel61)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel62)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tanggal_selesai_dt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addComponent(kButton12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(58, Short.MAX_VALUE))
        );

        containerDataTransaksi.add(tahapKedua, "card3");

        tahapKetiga.setBackground(new java.awt.Color(255, 255, 255));

        jLabel67.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel67.setIcon(new javax.swing.ImageIcon(getClass().getResource("/illustration/before.png"))); // NOI18N
        jLabel67.setText("Kembali");
        jLabel67.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel67.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel67MouseClicked(evt);
            }
        });

        jLabel70.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel70.setText("Total Harga :");

        total_harga_dt.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        total_harga_dt.setText("50000");
        total_harga_dt.setBorder(null);

        jLabel69.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel69.setText("Sisa Harus dibayar :");

        sisa_bayar_dt.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        sisa_bayar_dt.setText("50000");
        sisa_bayar_dt.setBorder(null);

        jLabel72.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel72.setText("Pengisian Pembayaran");

        jLabel68.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel68.setText("Isi pembayaran jika pelanggan sebelumnya memiliki");

        jLabel71.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel71.setText("status belum lunas, lanjutkan jika form terisi atau");

        jLabel73.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel73.setText("pelanggan memiliki status sudah lunas.");

        jLabel74.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel74.setText("Status Pelanggan :");

        status_bayar_dt.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        status_bayar_dt.setForeground(new java.awt.Color(255, 0, 51));
        status_bayar_dt.setText("Belum Lunas!");
        status_bayar_dt.setBorder(null);

        jLabel75.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel75.setText("Form Pembayaran :");

        pembayaran_dt.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        pembayaran_dt.setLabelText("Masukkan Jumlah Bayar");

        kembalian_pembayaran_dt.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        kembalian_pembayaran_dt.setLabelText("Kembalian");

        jLabel76.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel76.setText("Pembayaran Sebelumnya");

        jLabel77.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel77.setText("Jumlah pembayaran dari pelanggan saat pemesanan");

        jLabel78.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel78.setText("paket laundry seperti pada form yang tertera :");

        jumlah_bayar_dt.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jumlah_bayar_dt.setLabelText("Jumlah Pembayaran");

        kembalian_dt.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        kembalian_dt.setLabelText("Kembalian");

        jLabel79.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel79.setText("Pembayaran Pelanggan Sebelumnya :");

        kButton13.setText("Simpan");
        kButton13.setBorderPainted(false);
        kButton13.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        kButton13.setkBackGroundColor(new java.awt.Color(102, 153, 255));
        kButton13.setkEndColor(new java.awt.Color(102, 153, 255));
        kButton13.setkHoverEndColor(new java.awt.Color(0, 0, 255));
        kButton13.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        kButton13.setkHoverStartColor(new java.awt.Color(0, 0, 255));
        kButton13.setkPressedColor(new java.awt.Color(102, 153, 255));
        kButton13.setkSelectedColor(new java.awt.Color(102, 153, 255));
        kButton13.setkStartColor(new java.awt.Color(102, 153, 255));
        kButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButton13ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tahapKetigaLayout = new javax.swing.GroupLayout(tahapKetiga);
        tahapKetiga.setLayout(tahapKetigaLayout);
        tahapKetigaLayout.setHorizontalGroup(
            tahapKetigaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tahapKetigaLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel67)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tahapKetigaLayout.createSequentialGroup()
                .addGap(131, 131, 131)
                .addGroup(tahapKetigaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(kButton13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(tahapKetigaLayout.createSequentialGroup()
                        .addGroup(tahapKetigaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tahapKetigaLayout.createSequentialGroup()
                                .addComponent(jLabel74)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(status_bayar_dt, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(tahapKetigaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(kembalian_pembayaran_dt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel75, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel68, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel72, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel71, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel73, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tahapKetigaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(tahapKetigaLayout.createSequentialGroup()
                                        .addComponent(jLabel69)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(sisa_bayar_dt))
                                    .addGroup(tahapKetigaLayout.createSequentialGroup()
                                        .addComponent(jLabel70)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(total_harga_dt, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(pembayaran_dt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 178, Short.MAX_VALUE)
                        .addGroup(tahapKetigaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel77, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel76)
                            .addComponent(jLabel78)
                            .addComponent(jLabel79)
                            .addComponent(kembalian_dt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jumlah_bayar_dt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(148, 148, 148))
        );
        tahapKetigaLayout.setVerticalGroup(
            tahapKetigaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tahapKetigaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel67)
                .addGap(53, 53, 53)
                .addGroup(tahapKetigaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(total_harga_dt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel70))
                .addGap(0, 0, 0)
                .addGroup(tahapKetigaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel69)
                    .addComponent(sisa_bayar_dt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(tahapKetigaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel74)
                    .addComponent(status_bayar_dt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(tahapKetigaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(tahapKetigaLayout.createSequentialGroup()
                        .addComponent(jLabel72)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel68)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel71)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel73))
                    .addGroup(tahapKetigaLayout.createSequentialGroup()
                        .addComponent(jLabel76)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel77)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel78)
                        .addGap(15, 15, 15)))
                .addGap(18, 18, 18)
                .addGroup(tahapKetigaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel75)
                    .addComponent(jLabel79))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tahapKetigaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pembayaran_dt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jumlah_bayar_dt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tahapKetigaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kembalian_pembayaran_dt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kembalian_dt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(kButton13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        containerDataTransaksi.add(tahapKetiga, "card4");

        tampilanDataTransaksi.add(containerDataTransaksi, "card2");

        containerContent.add(tampilanDataTransaksi, "card4");

        javax.swing.GroupLayout tampilanDataPelangganLayout = new javax.swing.GroupLayout(tampilanDataPelanggan);
        tampilanDataPelanggan.setLayout(tampilanDataPelangganLayout);
        tampilanDataPelangganLayout.setHorizontalGroup(
            tampilanDataPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1035, Short.MAX_VALUE)
        );
        tampilanDataPelangganLayout.setVerticalGroup(
            tampilanDataPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 507, Short.MAX_VALUE)
        );

        containerContent.add(tampilanDataPelanggan, "card5");

        javax.swing.GroupLayout tampilanLaporanTransaksiLayout = new javax.swing.GroupLayout(tampilanLaporanTransaksi);
        tampilanLaporanTransaksi.setLayout(tampilanLaporanTransaksiLayout);
        tampilanLaporanTransaksiLayout.setHorizontalGroup(
            tampilanLaporanTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1035, Short.MAX_VALUE)
        );
        tampilanLaporanTransaksiLayout.setVerticalGroup(
            tampilanLaporanTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 507, Short.MAX_VALUE)
        );

        containerContent.add(tampilanLaporanTransaksi, "card6");

        javax.swing.GroupLayout tampilanDataPaketLayout = new javax.swing.GroupLayout(tampilanDataPaket);
        tampilanDataPaket.setLayout(tampilanDataPaketLayout);
        tampilanDataPaketLayout.setHorizontalGroup(
            tampilanDataPaketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1035, Short.MAX_VALUE)
        );
        tampilanDataPaketLayout.setVerticalGroup(
            tampilanDataPaketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 507, Short.MAX_VALUE)
        );

        containerContent.add(tampilanDataPaket, "card7");

        javax.swing.GroupLayout wrapperKontentLayout = new javax.swing.GroupLayout(wrapperKontent);
        wrapperKontent.setLayout(wrapperKontentLayout);
        wrapperKontentLayout.setHorizontalGroup(
            wrapperKontentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tempatJuduldanUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(containerContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        wrapperKontentLayout.setVerticalGroup(
            wrapperKontentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapperKontentLayout.createSequentialGroup()
                .addComponent(tempatJuduldanUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(containerContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout containerLayout = new javax.swing.GroupLayout(container);
        container.setLayout(containerLayout);
        containerLayout.setHorizontalGroup(
            containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerLayout.createSequentialGroup()
                .addComponent(wrapperMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(wrapperKontent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        containerLayout.setVerticalGroup(
            containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(wrapperMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(wrapperKontent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(container, "card2");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public void search_data_transaksi() {
        field_cari_data_transaksi.addEvent(new EventTextField() {
            @Override
            public void onPressed(EventCallBack call) {
                try {
                    DefaultTableModel tbl = new DefaultTableModel();
                    tbl.addColumn("Nama Pelanggan");
                    tbl.addColumn("Status Pengambilan");
                    try {
                        String sudah = "belum di ambil";
                        String sql = "select * from tbl_order join pelanggan on pelanggan.id_pelanggan=tbl_order.id_pelanggan where pelanggan.nama_pelanggan like '%" + field_cari_data_transaksi.getText() + "%'";
                        Connection con = (Connection) connect.configDB();
                        Statement st = (Statement) con.createStatement();
                        ResultSet rs = st.executeQuery(sql);
                        if (rs.next()) {
                            tbl.addRow(new Object[]{
                                rs.getString("nama_pelanggan"),
                                rs.getString("status_ambil"),});
                            tabel_data_transaksi.setModel(tbl);
                        } else if (!rs.next()) {
                            ImageIcon iconic = new ImageIcon(dashboardKasir.class.getResource("/illustration/emergency.png"));
                            JOptionPane.showMessageDialog(null, "Data pelanggan tidak ditemukan, silahkan lakukan redaftar untuk pelanggan baru", "PERINGATAN DATA PELANGGAN TIDAK DITEMUKAN!!", JOptionPane.INFORMATION_MESSAGE, iconic);
                        }
                    } catch (Exception e) {
                    }
                    for (int i = 1; i <= 70; i++) {
                        field_cari_data_transaksi.setText("");
                        Thread.sleep(10);
                    }
                    call.done();
                } catch (Exception e) {
                }
            }

            @Override
            public void onCancel() {

            }
        });
    }

    public void menampilkan_data_pelanggan_data_transaksi() {
        String status = "belum di ambil";
        DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("Nama Pelanggan");
        tbl.addColumn("Status ambil");
        tabel_data_transaksi.setModel(tbl);
        try {
            String sudah = "belum di ambil";
            Statement statement = (Statement) connect.configDB().createStatement();
            ResultSet res = statement.executeQuery("SELECT pelanggan.nama_pelanggan, `tbl_order`.status_ambil\n"
                    + "FROM pelanggan\n"
                    + "JOIN `tbl_order` ON pelanggan.id_pelanggan = `tbl_order`.id_pelanggan where tbl_order.status_ambil = '" + sudah + "'");
            while (res.next()) {
                tbl.addRow(new Object[]{
                    res.getString("nama_pelanggan"),
                    res.getString("status_ambil"),});
                tabel_data_transaksi.setModel(tbl);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Koneksi gagal" + e.getMessage());
        }
    }

    public void tampil_data_penjualan() {

        String[] judul = {"ID", "Nama paket", "Harga", "jumlah", " subtotal", "keterangan"};
        modelpj = new DefaultTableModel(judul, 0);
        tabel_penjualan.setModel(modelpj);
    }

    private void _settotal() {
        int total_pjl = 0;
        int row = tabel_penjualan.getRowCount();
        int tuotal = 0;
        for (int i = 0; i < row; i++) {
            int sb = Integer.parseInt(tabel_penjualan.getValueAt(i, 4).toString()); //22000
            int tot = Integer.parseInt(tabel_penjualan.getValueAt(i, 3).toString());
            tuotal = tuotal + (sb);
            total_pjl = total_pjl + tot;
            //subtotal = 15000 + (23*11)
            //subtotal = 15000 + 253
            //subtotal = 15253
            //subtotal = 15253 + (22000*11)
            //subtotal = 15253 + 242000
            //subtotal = 257253
        }
        this.tot_brg = total_pjl;
        this.thotal = tuotal;
        field_total_harga.setText(String.valueOf(this.thotal));

    }

    public void reset_nambah_krj() {
        field_subtotal.setText("");
        field_keterangan.setText("");
        field_jumlah_barang.setText("");
        field_harga_paket.setText("");

    }

    public void tampil_data_barang() {
        DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("Kode Paket");
        tbl.addColumn("Nama Paket");
        tbl.addColumn("Jenis Paket");
        tbl.addColumn("Harga Paket");
        tabel_barang.setModel(tbl);

        try {
            Statement statement = (Statement) connect.configDB().createStatement();

//           Statement statement=(Statement)coneksi.GetConnection().createStatement();
            ResultSet res = statement.executeQuery("select * from paket");
            while (res.next()) {
                tbl.addRow(new Object[]{
                    res.getString("kd_paket"),
                    res.getString("nama_paket"),
                    res.getString("jenis_paket"),
                    res.getString("harga")
                });
                tabel_barang.setModel(tbl);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Koneksi gagal" + e.getMessage());
        }
    }

    public void kodeTransaksiOtomatis() {
        try {

            String sql = "SELECT * from tbl_order ORDER BY kode_order DESC";
            Connection con = (Connection) connect.configDB();
            Statement st = (Statement) con.createStatement();
            ResultSet res = st.executeQuery(sql);
            if (res.next()) {
                String NoJual = res.getString("kode_order").substring(2);
                String AN = "" + (Integer.parseInt(NoJual) + 1);
//                String AN = "" + (Integer.parseInt(txt_idsupplier.getText()) + 1);
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
                field_kode_nota.setText("TR" + Nol + AN);//sesuaikan dengan variable namenya
            } else {
                field_kode_nota.setText("TR0001");//sesuaikan dengan variable namenya
            }
            res.close();
        } catch (Exception e) {
            //penanganan masalah
            JOptionPane.showMessageDialog(null, "Koneksi gagal" + e.getMessage());
        }
    }

    void id_kasir_tr() {
        if (responsive.login_rs.id_kasirr != null) {
            String id = responsive.login_rs.id_kasirr;
            field_id_user.setText(id);
            id_kasir_dt.setText(id);
        } else if (frame.login.id_kasir != null) {
            String id = frame.login.id_kasir;
            field_id_user.setText(id);
            id_kasir_dt.setText(id);
        }
    }

    public void search_data_pelanggan() {
        field_cari_data_pelanggan.addEvent(new EventTextField() {
            @Override
            public void onPressed(EventCallBack call) {
                try {
                    DefaultTableModel tbl = new DefaultTableModel();
                    tbl.addColumn("ID");
                    tbl.addColumn("Nama");
                    tbl.addColumn("Alamat");
                    tbl.addColumn("nomor");
                    try {
                        String sql = "SELECT * FROM pelanggan WHERE nama_pelanggan like'%" + field_cari_data_pelanggan.getText() + "%' || id_pelanggan like'%" + field_cari_data_pelanggan.getText() + "%' || alamat like'%" + field_cari_data_pelanggan.getText() + "%'";
                        Connection con = (Connection) connect.configDB();
                        Statement st = (Statement) con.createStatement();
                        ResultSet rs = st.executeQuery(sql);
                        if (rs.next()) {
                            tbl.addRow(new Object[]{
                                rs.getString("id_pelanggan"),
                                rs.getString("nama_pelanggan"),
                                rs.getString("alamat"),
                                rs.getString("no_telp"),});
                            tabel_data_pelanggan_2.setModel(tbl);
                        } else if (!rs.next()) {
                            ImageIcon iconic = new ImageIcon(dashboardKasir.class.getResource("/illustration/emergency.png"));
                            JOptionPane.showMessageDialog(null, "Data pelanggan tidak ditemukan, silahkan lakukan redaftar untuk pelanggan baru", "PERINGATAN DATA PELANGGAN TIDAK DITEMUKAN!!", JOptionPane.INFORMATION_MESSAGE, iconic);
                        }
                    } catch (Exception e) {
                    }
                    for (int i = 1; i <= 70; i++) {
                        field_cari_data_pelanggan.setText("");
                        Thread.sleep(10);
                    }
                    call.done();
                } catch (Exception e) {
                }
            }

            @Override
            public void onCancel() {

            }
        });
    }

    void id_otomatis_pelanggan() {
        try {
            String sql = "SELECT * from pelanggan ORDER BY id_pelanggan DESC";
            Connection con = (Connection) connect.configDB();
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
                field_id_pelanggan_tr.setText("PL" + Nol + AN);//sesuaikan dengan variable namenya
            } else {
                field_id_pelanggan_tr.setText("PL0001");//sesuaikan dengan variable namenya
            }
            res.close();
//            con.close();
        } catch (Exception e) {
            //penanganan masalah
            JOptionPane.showMessageDialog(null, "Koneksi gagal" + e.getMessage());
        }
    }

    public void menampilkan_data_pelanggan() {
        DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("ID");
        tbl.addColumn("Nama");
        tbl.addColumn("Alamat");
        tbl.addColumn("Nomor");
        //tabel_data_pelanggan.setModel(tbl);
        tabel_data_pelanggan_2.setModel(tbl);
        try {
            Statement statement = (Statement) connection.connect.configDB().createStatement();
//           Statement statement=(Statement)coneksi.GetConnection().createStatement();
            ResultSet res = statement.executeQuery("select * from pelanggan");
            while (res.next()) {
                tbl.addRow(new Object[]{
                    res.getString("id_pelanggan"),
                    res.getString("nama_pelanggan"),
                    res.getString("alamat"),
                    res.getString("no_telp"),});
                //tabel_data_pelanggan.setModel(tbl);
                tabel_data_pelanggan_2.setModel(tbl);
            }
            //int b = tabel_data_pelanggan.getRowCount();
            //data_jumlah_pelanggan_dashboard.setText("" + b);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Koneksi gagal" + e.getMessage());
        }
    }

    public void setColor(JPanel p) { // HOVER MENU
        p.setBackground(new Color(113, 144, 245));
    }

    public void resetColor(JPanel pl) { //HOVER MENU
        pl.setBackground(new Color(255, 255, 255));
    }
    private void dashboardLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashboardLabelMouseClicked
        // TODO add your handling code here:
        judulDb.setText("Dashboard");
        resetColor(menuTransaksi);
        resetColor(menuDataPaket);
        resetColor(menuDataPelanggan);
        resetColor(menuDatatransaksi);
        resetColor(menuHistoryTransaksi);
        containerContent.removeAll();
        containerContent.repaint();
        containerContent.revalidate();
        containerContent.add(tampilanDashboardUtama);
        containerContent.repaint();
        containerContent.revalidate();
    }//GEN-LAST:event_dashboardLabelMouseClicked

    private void dashboardLabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashboardLabelMousePressed
        // TODO add your handling code here:
        setColor(menuDashboard);
    }//GEN-LAST:event_dashboardLabelMousePressed

    private void menuTransaksiLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuTransaksiLabelMouseClicked
        // TODO add your handling code here:
        judulDb.setText("Transaksi");
        resetColor(menuDashboard);
        resetColor(menuDataPaket);
        resetColor(menuDataPelanggan);
        resetColor(menuDatatransaksi);
        resetColor(menuHistoryTransaksi);
        containerContent.removeAll();
        containerContent.repaint();
        containerContent.revalidate();
        containerContent.add(tampilanTransaksi);
        containerContent.repaint();
        containerContent.revalidate();
    }//GEN-LAST:event_menuTransaksiLabelMouseClicked

    private void menuTransaksiLabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuTransaksiLabelMousePressed
        // TODO add your handling code here:
        setColor(menuTransaksi);
    }//GEN-LAST:event_menuTransaksiLabelMousePressed

    private void datatransaksiLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_datatransaksiLabelMouseClicked
        // TODO add your handling code here:
        judulDb.setText("Data Transaksi");
        resetColor(menuDashboard);
        resetColor(menuDataPaket);
        resetColor(menuDataPelanggan);
        resetColor(menuTransaksi);
        resetColor(menuHistoryTransaksi);
        containerContent.removeAll();
        containerContent.repaint();
        containerContent.revalidate();
        containerContent.add(tampilanDataTransaksi);
        containerContent.repaint();
        containerContent.revalidate();
    }//GEN-LAST:event_datatransaksiLabelMouseClicked

    private void datatransaksiLabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_datatransaksiLabelMousePressed
        // TODO add your handling code here:
        setColor(menuDatatransaksi);
    }//GEN-LAST:event_datatransaksiLabelMousePressed

    private void dataPelangganLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dataPelangganLabelMouseClicked
        // TODO add your handling code here:
        judulDb.setText("Data Pelanggan");
        resetColor(menuDashboard);
        resetColor(menuDataPaket);
        resetColor(menuDatatransaksi);
        resetColor(menuTransaksi);
        resetColor(menuHistoryTransaksi);
        containerContent.removeAll();
        containerContent.repaint();
        containerContent.revalidate();
        containerContent.add(tampilanDataPelanggan);
        containerContent.repaint();
        containerContent.revalidate();
    }//GEN-LAST:event_dataPelangganLabelMouseClicked

    private void dataPelangganLabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dataPelangganLabelMousePressed
        // TODO add your handling code here:
        setColor(menuDataPelanggan);
    }//GEN-LAST:event_dataPelangganLabelMousePressed

    private void historyTransaksiLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_historyTransaksiLabelMouseClicked
        // TODO add your handling code here:
        judulDb.setText("Laporan History Transaksi");
        resetColor(menuDashboard);
        resetColor(menuDataPaket);
        resetColor(menuDatatransaksi);
        resetColor(menuTransaksi);
        resetColor(menuDataPelanggan);
        containerContent.removeAll();
        containerContent.repaint();
        containerContent.revalidate();
        containerContent.add(tampilanLaporanTransaksi);
        containerContent.repaint();
        containerContent.revalidate();
    }//GEN-LAST:event_historyTransaksiLabelMouseClicked

    private void historyTransaksiLabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_historyTransaksiLabelMousePressed
        // TODO add your handling code here:
        setColor(menuHistoryTransaksi);
    }//GEN-LAST:event_historyTransaksiLabelMousePressed

    private void dataPaketLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dataPaketLabelMouseClicked
        // TODO add your handling code here:
        judulDb.setText("Data Paket");
        resetColor(menuDashboard);
        resetColor(menuDataPaket);
        resetColor(menuDatatransaksi);
        resetColor(menuTransaksi);
        resetColor(menuDataPelanggan);
        containerContent.removeAll();
        containerContent.repaint();
        containerContent.revalidate();
        containerContent.add(tampilanDataPaket);
        containerContent.repaint();
        containerContent.revalidate();
    }//GEN-LAST:event_dataPaketLabelMouseClicked

    private void dataPaketLabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dataPaketLabelMousePressed
        // TODO add your handling code here:
        setColor(menuDataPaket);
    }//GEN-LAST:event_dataPaketLabelMousePressed

    private void tabel_data_pelanggan_2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_data_pelanggan_2MouseClicked
        // TODO add your handling code here:
        int baris = tabel_data_pelanggan_2.rowAtPoint(evt.getPoint());
        String tid = tabel_data_pelanggan_2.getValueAt(baris, 0).toString();
        this.selected_pelanggan_id = tid;
        field_id_pelanggan.setText(tid);


    }//GEN-LAST:event_tabel_data_pelanggan_2MouseClicked

    private void kButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButton3ActionPerformed
        // TODO add your handling code here:
        if (selected_pelanggan_id != null) {
            containerTransaksi.removeAll();
            containerTransaksi.repaint();
            containerTransaksi.revalidate();
            containerTransaksi.add(lanjutTransaksi1);
            containerTransaksi.repaint();
            containerTransaksi.revalidate();
        } else {
            ImageIcon iconic = new ImageIcon(dashboardKasir.class.getResource("/illustration/emergency.png"));
            JOptionPane.showMessageDialog(null, "Tekan data dahulu lalu lanjutkan", "PERHATIAN", JOptionPane.INFORMATION_MESSAGE, iconic);
        }
    }//GEN-LAST:event_kButton3ActionPerformed

    private void field_cari_data_pelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_field_cari_data_pelangganActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_field_cari_data_pelangganActionPerformed

    private void kButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButton4ActionPerformed
        // TODO add your handling code here:
        containerTransaksi.removeAll();
        containerTransaksi.repaint();
        containerTransaksi.revalidate();
        containerTransaksi.add(inputPelanggan);
        containerTransaksi.repaint();
        containerTransaksi.revalidate();
    }//GEN-LAST:event_kButton4ActionPerformed

    private void jLabel35MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel35MouseClicked
        // TODO add your handling code here:
        containerTransaksi.removeAll();
        containerTransaksi.repaint();
        containerTransaksi.revalidate();
        containerTransaksi.add(tampilanAwalTransaksi);
        containerTransaksi.repaint();
        containerTransaksi.revalidate();
    }//GEN-LAST:event_jLabel35MouseClicked

    private void jLabel37MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel37MouseClicked
        // TODO add your handling code here:
        containerTransaksi.removeAll();
        containerTransaksi.repaint();
        containerTransaksi.revalidate();
        containerTransaksi.add(lanjutTransaksi1);
        containerTransaksi.repaint();
        containerTransaksi.revalidate();
    }//GEN-LAST:event_jLabel37MouseClicked

    private void jLabel47MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel47MouseClicked
        // TODO add your handling code here:
        try {
            String sql = "SELECT * FROM pelanggan WHERE id_pelanggan = '" + field_id_pelanggan.getText() + "'";
            java.sql.Connection conn = (com.mysql.jdbc.Connection) connection.connect.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery(sql);
            if (rs.next()) {
                new detailDataJtextField.PelangganTR().setVisible(true);
                nama_pelanggan_form.setText(rs.getString(2));
                id_detail_pelanggan_form.setText(rs.getString(1));
                alamat_pelanggan_form.setText(rs.getString(3));
                nomor_telfon_pelanggan_form.setText(rs.getString(4));
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jLabel47MouseClicked

    private void jLabel48MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel48MouseClicked
        // TODO add your handling code here:
        try {
            String sql = "SELECT * FROM user WHERE id_user = '" + field_id_user.getText() + "'";
            java.sql.Connection conn = (com.mysql.jdbc.Connection) connection.connect.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery(sql);
            if (rs.next()) {
                new detailDataJtextField.UserTR().setVisible(true);
                nama_kasir_form.setText(rs.getString(2));
                id_detail_kasir_form.setText(rs.getString(1));
                alamat_kasir_form.setText(rs.getString(5));
                nomor_telfon_kasir_form.setText(rs.getString(6));
                username_kasir_detail_form.setText(rs.getString(3));
                password_detail_form.setText(rs.getString(4));
                hakakses_detail_form.setText(rs.getString(7));
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jLabel48MouseClicked

    private void jLabel49MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel49MouseClicked
        // TODO add your handling code here:
        containerTransaksi.removeAll();
        containerTransaksi.repaint();
        containerTransaksi.revalidate();
        containerTransaksi.add(tampilanAwalTransaksi);
        containerTransaksi.repaint();
        containerTransaksi.revalidate();
    }//GEN-LAST:event_jLabel49MouseClicked

    private void kButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButton8ActionPerformed
        // TODO add your handling code here:
        containerTransaksi.removeAll();
        containerTransaksi.repaint();
        containerTransaksi.revalidate();
        containerTransaksi.add(lanjutTransaksi2);
        containerTransaksi.repaint();
        containerTransaksi.revalidate();
    }//GEN-LAST:event_kButton8ActionPerformed

    private void tabel_barangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_barangMouseClicked
        // TODO add your handling code here:
        int baris = tabel_barang.rowAtPoint(evt.getPoint());
        tabel_barang.rowAtPoint(evt.getPoint());
        tabel_barang.getValueAt(baris, 0).toString();
        String nm = tabel_barang.getValueAt(baris, 1).toString();
        tabel_barang.getValueAt(baris, 2).toString();
        String pr = tabel_barang.getValueAt(baris, 3).toString();
        field_harga_paket.setText(pr);
    }//GEN-LAST:event_tabel_barangMouseClicked

    private void kButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButton6ActionPerformed
        // TODO add your handling code here:
        int jumlah = Integer.parseInt(field_jumlah_barang.getText());
        try {
            int row = tabel_barang.getSelectedRow();
            if ((jumlah == 0) && (field_jumlah_barang.getText().equals(""))) {
                JOptionPane.showMessageDialog(null, "harap masukkan jumlah dengan benar");
            } else {
                boolean result = idlist.contains(tabel_barang.getValueAt(row, 0).toString());
                if (result == false) {
                    idlist.add(tabel_barang.getValueAt(row, 0).toString());
                    String data[] = {tabel_barang.getValueAt(row, 0).toString(), tabel_barang.getValueAt(row, 1).toString(),
                        tabel_barang.getValueAt(row, 2).toString(), field_jumlah_barang.getText(), field_subtotal.getText(), field_keterangan.getText()};
                    modelpj.addRow(data);
                    reset_nambah_krj();
                    _settotal();
                    ImageIcon iconic = new ImageIcon(dashboardKasir.class.getResource("/illustration/checked.png"));
                    JOptionPane.showMessageDialog(null, "Berhasil disimpan di table keranjang", "SUKSES", JOptionPane.INFORMATION_MESSAGE, iconic);
                } else {
                    JOptionPane.showMessageDialog(null, "Data barang sudah terinput");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "harap masukkan jumlah dengan benar");

        }
    }//GEN-LAST:event_kButton6ActionPerformed

    private void tabel_penjualanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_penjualanMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tabel_penjualanMouseClicked

    private void jLabel51MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel51MouseClicked
        // TODO add your handling code here:
        containerTransaksi.removeAll();
        containerTransaksi.repaint();
        containerTransaksi.revalidate();
        containerTransaksi.add(lanjutTransaksi2);
        containerTransaksi.repaint();
        containerTransaksi.revalidate();
    }//GEN-LAST:event_jLabel51MouseClicked

    private void kButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButton7ActionPerformed
        // TODO add your handling code here:
        containerTransaksi.removeAll();
        containerTransaksi.repaint();
        containerTransaksi.revalidate();
        containerTransaksi.add(lanjutTransaksi3);
        containerTransaksi.repaint();
        containerTransaksi.revalidate();
    }//GEN-LAST:event_kButton7ActionPerformed

    private void field_kembalianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_field_kembalianActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_field_kembalianActionPerformed

    private void kButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButton9ActionPerformed
        // TODO add your handling code here:
        int row = tabel_penjualan.getSelectedRow();//0
        idlist.remove(row);
        modelpj.removeRow(row);
        _settotal();
    }//GEN-LAST:event_kButton9ActionPerformed

    private void kButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButton10ActionPerformed
        // TODO add your handling code here:
        SimpleDateFormat sfDate = new SimpleDateFormat("yyyy-MM-dd");
        Date tanggal = new Date();
        int rows = tabel_penjualan.getRowCount();
        String KD = field_kode_nota.getText();
        int grand_total = Integer.parseInt(field_total_harga.getText());
        int kembalian = Integer.parseInt(field_kembalian.getText());
        int majer = Integer.parseInt(field_bayar.getText());
        int sisa_byr = Integer.parseInt(field_sisa_bayar.getText());
        String idplga = field_id_pelanggan.getText();
        String skr = "" + sfDate.format(tanggal);
        java.sql.Connection conn = null;

        try {
            Connection con = (Connection) connect.configDB();
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, +3);
            Date sls = cal.getTime();
            String stsAmbil = "belum di ambil";
            String tanggal_selesai = "" + sfDate.format(sls);
            String sql1 = "INSERT INTO tbl_order VALUES('" + KD + "','" + idplga + "','" + field_id_user.getText() + "',now(),'" + tanggal_selesai + "','" + grand_total + "','" + majer + "','" + sisa_byr + "','" + field_status_bayar.getText() + "','" + kembalian + "','" + stsAmbil + "')";
            PreparedStatement pst1 = (PreparedStatement) con.prepareStatement(sql1);
            pst1.execute();
            for (int i = 0; i < rows; i++) {
                String sql2 = "INSERT INTO tbl_detail_order VALUES('" + KD + "', '" + tabel_penjualan.getValueAt(i, 0).toString() + "',"
                        + "'" + tabel_penjualan.getValueAt(i, 3).toString() + "','" + tabel_penjualan.getValueAt(i, 4).toString() + "', "
                        + "'" + tabel_penjualan.getValueAt(i, 5).toString() + "')";
                PreparedStatement pst2 = (PreparedStatement) con.prepareStatement(sql2);
                pst2.execute();
                containerTransaksi.removeAll();
                containerTransaksi.repaint();
                containerTransaksi.revalidate();
                containerTransaksi.add(tampilanAwalTransaksi);
                containerTransaksi.repaint();
                containerTransaksi.revalidate();
                menampilkan_data_pelanggan_data_transaksi();

            }
            ImageIcon iconic = new ImageIcon(dashboardKasir.class.getResource("/illustration/checked.png"));
            JOptionPane.showMessageDialog(null, "Transaksi berhasil disimpan", "SUKSES", JOptionPane.INFORMATION_MESSAGE, iconic);
            try {
                String jdbcDriver = "com.mysql.jdbc.Driver";
                Class.forName(jdbcDriver);
                String url = "jdbc:mysql://localhost:3306/app_laundry";
                String user = "agungkurniawan";
                String pass = "programmerwebdeveloper";
                conn = DriverManager.getConnection(url, user, pass);
                java.sql.Statement stm = conn.createStatement();
                try {
                    String report = ("D:\\Agung Project\\Aplikasi Henny Laundry Berbasis Desktop V2\\Aplikasi Henny Laundry V2\\src\\transaksiNota\\cetak_struk.jrxml");
                    HashMap hash = new HashMap();
                    //Mengambil parameter dari ireport
                    hash.put("nota", field_kode_nota.getText());
                    JasperReport JRpt = JasperCompileManager.compileReport(report);
                    JasperPrint JPrint = JasperFillManager.fillReport(JRpt, hash, conn);
                    JasperViewer.viewReport(JPrint, false);
                } catch (Exception rptexcpt) {
                    System.out.println("Report Can't view because : " + rptexcpt);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "" + e.getMessage());
        }
    }//GEN-LAST:event_kButton10ActionPerformed

    private void field_cari_data_transaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_field_cari_data_transaksiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_field_cari_data_transaksiActionPerformed

    private void tanggal_selesai_dtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tanggal_selesai_dtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tanggal_selesai_dtActionPerformed

    private void jLabel66MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel66MouseClicked
        // TODO add your handling code here:
        containerDataTransaksi.removeAll();
        containerDataTransaksi.repaint();
        containerDataTransaksi.revalidate();
        containerDataTransaksi.add(tahapPertama);
        containerDataTransaksi.repaint();
        containerDataTransaksi.revalidate();
    }//GEN-LAST:event_jLabel66MouseClicked

    private void kButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButton11ActionPerformed
        // TODO add your handling code here:
        try {
            String sql = "select * from tbl_order join pelanggan on pelanggan.id_pelanggan=tbl_order.id_pelanggan join user on user.id_user=tbl_order.id_user where pelanggan.nama_pelanggan ='" + field_lanjut_pengembalian.getText() + "'";
            java.sql.Connection conn = (com.mysql.jdbc.Connection) connection.connect.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery(sql);
            if (rs.next()) {
                containerDataTransaksi.removeAll();
                containerDataTransaksi.repaint();
                containerDataTransaksi.revalidate();
                containerDataTransaksi.add(tahapKedua);
                containerDataTransaksi.repaint();
                containerDataTransaksi.revalidate();
                kd_struk_data_transaksi.setText(rs.getString(1));
                id_pelanggan_dt.setText(rs.getString(2));
                status_ambil_dt.setSelectedItem(rs.getString(11));
                tanggal_pesan_dt.setText(rs.getString(4));
                tanggal_selesai_dt.setText(rs.getString(5));
                total_harga_dt.setText(rs.getString(6));
                sisa_bayar_dt.setText(rs.getString(8));
                status_bayar_dt.setText(rs.getString(9));
                jumlah_bayar_dt.setText(rs.getString(7));
                kembalian_dt.setText(rs.getString(10));
                if (status_bayar_dt.getText().equals("kurang")) {
                    status_bayar_dt.setForeground(Color.red);
                } else if (status_bayar_dt.getText().equals("lunas")) {
                    status_bayar_dt.setForeground(Color.green);
                } else if (status_bayar_dt.getText().equals("belum bayar")) {
                    status_bayar_dt.setForeground(Color.blue);
                }
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_kButton11ActionPerformed

    private void tabel_data_transaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_data_transaksiMouseClicked
        // TODO add your handling code here:
        int baris = tabel_data_transaksi.rowAtPoint(evt.getPoint());
        tabel_data_transaksi.rowAtPoint(evt.getPoint());
        String nm = tabel_data_transaksi.getValueAt(baris, 0).toString();
        field_lanjut_pengembalian.setText(nm);
    }//GEN-LAST:event_tabel_data_transaksiMouseClicked

    private void kButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButton12ActionPerformed
        // TODO add your handling code here:
        containerDataTransaksi.removeAll();
        containerDataTransaksi.repaint();
        containerDataTransaksi.revalidate();
        containerDataTransaksi.add(tahapKetiga);
        containerDataTransaksi.repaint();
        containerDataTransaksi.revalidate();
    }//GEN-LAST:event_kButton12ActionPerformed

    private void kd_struk_data_transaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kd_struk_data_transaksiMouseClicked
        // TODO add your handling code here:
        if (kd_struk_data_transaksi != null) {
            JOptionPane.showMessageDialog(null, "form sudah terisi otomatis");
            kd_struk_data_transaksi.setEnabled(false);
        }
    }//GEN-LAST:event_kd_struk_data_transaksiMouseClicked

    private void jLabel67MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel67MouseClicked
        // TODO add your handling code here:
        containerDataTransaksi.removeAll();
        containerDataTransaksi.repaint();
        containerDataTransaksi.revalidate();
        containerDataTransaksi.add(tahapKedua);
        containerDataTransaksi.repaint();
        containerDataTransaksi.revalidate();
    }//GEN-LAST:event_jLabel67MouseClicked

    private void kButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButton13ActionPerformed
        // TODO add your handling code here:
        if (pembayaran_dt.getText().isEmpty() && kembalian_pembayaran_dt.getText().isEmpty()) {
            java.sql.Connection conn = null;
            String KD = kd_struk_data_transaksi.getText();
            int kembaliann = Integer.parseInt(kembalian_dt.getText());
            int majer = Integer.parseInt(jumlah_bayar_dt.getText());
            int sisa_byr = Integer.parseInt(sisa_bayar_dt.getText());
            String status_byr = status_bayar_dt.getText();
            String status_ambil = (String) status_ambil_dt.getSelectedItem();
            String id_kasir = id_kasir_dt.getText();
            try {
                String sql = "UPDATE tbl_order SET id_user='" + id_kasir + "',bayar='" + majer + "',kurang_bayar='" + sisa_byr + "',status_bayar='" + status_byr + "',kembalian='" + kembaliann + "',status_ambil='" + status_ambil + "' WHERE kode_order='" + KD + "'";
                Connection con = (Connection) connection.connect.configDB();
                com.mysql.jdbc.PreparedStatement pst = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(sql);
                JOptionPane.showMessageDialog(null, "Data berhasil di EDIT");
                pst.execute();
                menampilkan_data_pelanggan_data_transaksi();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
            try {
                String jdbcDriver = "com.mysql.jdbc.Driver";
                Class.forName(jdbcDriver);
                String url = "jdbc:mysql://localhost:3306/aplikasi_laundry_main";
                String user = "root";
                String pass = "";
                conn = DriverManager.getConnection(url, user, pass);
                java.sql.Statement stm = conn.createStatement();
                try {

                    String report = ("D:\\Agung Project\\Aplikasi Henny Laundry Berbasis Desktop V2\\Aplikasi Henny Laundry V2\\src\\transaksiNota\\cetak_struk.jrxml");
                    HashMap hash = new HashMap();
                    //Mengambil parameter dari ireport
                    hash.put("cetak_struk", kd_struk_data_transaksi.getText());
                    JasperReport JRpt = JasperCompileManager.compileReport(report);
                    JasperPrint JPrint = JasperFillManager.fillReport(JRpt, hash, conn);
                    JasperViewer.viewReport(JPrint, false);
                } catch (Exception rptexcpt) {
                    System.out.println("Report Can't view because : " + rptexcpt);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } else if (pembayaran_dt.getText() != null && kembalian_pembayaran_dt.getText() != null) {
            java.sql.Connection conn = null;
            String KD = kd_struk_data_transaksi.getText();
            int kembaliann = Integer.parseInt(kembalian_pembayaran_dt.getText());
            int majer = Integer.parseInt(pembayaran_dt.getText());
            int sisa_byr = Integer.parseInt(sisa_bayar_dt.getText());
            String status_byr = status_bayar_dt.getText();
            String status_ambil = (String) status_ambil_dt.getSelectedItem();
            String id_kasir = id_kasir_dt.getText();
            try {
                String sql = "UPDATE tbl_order SET id_user='" + id_kasir + "',bayar='" + majer + "',kurang_bayar='" + sisa_byr + "',status_bayar='" + status_byr + "',kembalian='" + kembaliann + "',status_ambil='" + status_ambil + "' WHERE kode_order='" + KD + "'";
                Connection con = (Connection) connection.connect.configDB();
                com.mysql.jdbc.PreparedStatement pst = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(sql);
                JOptionPane.showMessageDialog(null, "Data berhasil di EDIT");
                pst.execute();
                menampilkan_data_pelanggan_data_transaksi();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
            try {
                String jdbcDriver = "com.mysql.jdbc.Driver";
                Class.forName(jdbcDriver);
                String url = "jdbc:mysql://localhost:3306/aplikasi_laundry_main";
                String user = "root";
                String pass = "";
                conn = DriverManager.getConnection(url, user, pass);
                java.sql.Statement stm = conn.createStatement();
                try {

                    String report = ("D:\\Agung Project\\Aplikasi Henny Laundry Berbasis Desktop V2\\Aplikasi Henny Laundry V2\\src\\transaksiNota\\cetak_struk.jrxml");
                    HashMap hash = new HashMap();
                    //Mengambil parameter dari ireport
                    hash.put("cetak_struk", kd_struk_data_transaksi.getText());
                    JasperReport JRpt = JasperCompileManager.compileReport(report);
                    JasperPrint JPrint = JasperFillManager.fillReport(JRpt, hash, conn);
                    JasperViewer.viewReport(JPrint, false);
                } catch (Exception rptexcpt) {
                    System.out.println("Report Can't view because : " + rptexcpt);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }//GEN-LAST:event_kButton13ActionPerformed

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
            java.util.logging.Logger.getLogger(dashboardKasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dashboardKasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dashboardKasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dashboardKasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new dashboardKasir().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel container;
    private javax.swing.JPanel containerContent;
    private javax.swing.JPanel containerDataTransaksi;
    private javax.swing.JPanel containerTransaksi;
    private javax.swing.JLabel dashboardLabel;
    private javax.swing.JLabel dataPaketLabel;
    private javax.swing.JLabel dataPelangganLabel;
    private javax.swing.JLabel datatransaksiLabel;
    private textfield.TextField field_bayar;
    private swing.TextFieldAnimation field_cari_data_pelanggan;
    private swing.TextFieldAnimation field_cari_data_transaksi;
    private textfield.TextField field_harga_paket;
    public static final textfield.TextField field_id_pelanggan = new textfield.TextField();
    private textfield.TextField field_id_pelanggan_tr;
    private textfield.TextField field_id_user;
    private textfield.TextField field_jumlah_barang;
    private textfield.TextField field_kembalian;
    private textfield.TextField field_keterangan;
    private textfield.TextField field_kode_nota;
    private javax.swing.JTextField field_lanjut_pengembalian;
    private textfield.TextField field_sisa_bayar;
    private textfield.TextField field_status_bayar;
    private textfield.TextField field_subtotal;
    private textfield.TextField field_total_harga;
    private javax.swing.JLabel historyTransaksiLabel;
    private textfield.TextField id_kasir_dt;
    private textfield.TextField id_pelanggan_dt;
    private javax.swing.JPanel inputPelanggan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel judulDb;
    private textfield.TextField jumlah_bayar_dt;
    private com.k33ptoo.components.KButton kButton1;
    private com.k33ptoo.components.KButton kButton10;
    private com.k33ptoo.components.KButton kButton11;
    private com.k33ptoo.components.KButton kButton12;
    private com.k33ptoo.components.KButton kButton13;
    private com.k33ptoo.components.KButton kButton2;
    private com.k33ptoo.components.KButton kButton3;
    private com.k33ptoo.components.KButton kButton4;
    private com.k33ptoo.components.KButton kButton5;
    private com.k33ptoo.components.KButton kButton6;
    private com.k33ptoo.components.KButton kButton7;
    private com.k33ptoo.components.KButton kButton8;
    private com.k33ptoo.components.KButton kButton9;
    private textfield.TextField kd_struk_data_transaksi;
    private textfield.TextField kembalian_dt;
    private textfield.TextField kembalian_pembayaran_dt;
    private javax.swing.JPanel lanjutTransaksi1;
    private javax.swing.JPanel lanjutTransaksi2;
    private javax.swing.JPanel lanjutTransaksi3;
    private javax.swing.JLabel logo;
    private javax.swing.JPanel menu;
    private javax.swing.JPanel menuDashboard;
    private javax.swing.JPanel menuDataPaket;
    private javax.swing.JPanel menuDataPelanggan;
    private javax.swing.JPanel menuDatatransaksi;
    private javax.swing.JPanel menuHistoryTransaksi;
    private javax.swing.JPanel menuTransaksi;
    private javax.swing.JLabel menuTransaksiLabel;
    public static final javax.swing.JLabel namaLoginKasir = new javax.swing.JLabel();
    private textfield.TextField pembayaran_dt;
    private javax.swing.JTextField sisa_bayar_dt;
    private combobox.Combobox status_ambil_dt;
    private javax.swing.JTextField status_bayar_dt;
    private tabledark.TableDark tabel_barang;
    private tabledark.TableDark tabel_data_pelanggan_2;
    private tabledark.TableDark tabel_data_transaksi;
    private tabledark.TableDark tabel_penjualan;
    private javax.swing.JPanel tahapKedua;
    private javax.swing.JPanel tahapKetiga;
    private javax.swing.JPanel tahapPertama;
    private javax.swing.JPanel tampilanAwalTransaksi;
    private javax.swing.JPanel tampilanDashboardUtama;
    private javax.swing.JPanel tampilanDataPaket;
    private javax.swing.JPanel tampilanDataPelanggan;
    private javax.swing.JPanel tampilanDataTransaksi;
    private javax.swing.JPanel tampilanLaporanTransaksi;
    private javax.swing.JPanel tampilanTransaksi;
    private textfield.TextField tanggal_pesan_dt;
    private textfield.TextField tanggal_selesai_dt;
    private javax.swing.JPanel tempatJuduldanUser;
    private textfield.TextField textField1;
    private textfield.TextField textField2;
    private textfield.TextField textField4;
    private javax.swing.JTextField total_harga_dt;
    private javax.swing.JPanel wrapperKontent;
    private javax.swing.JPanel wrapperMenu;
    // End of variables declaration//GEN-END:variables
}
