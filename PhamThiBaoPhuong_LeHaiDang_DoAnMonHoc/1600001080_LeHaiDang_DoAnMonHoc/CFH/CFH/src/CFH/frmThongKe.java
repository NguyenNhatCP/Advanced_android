/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CFH;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lhd
 */
public class frmThongKe extends javax.swing.JFrame {

    Controller c = new Controller();

    /**
     * Creates new form ThongKe
     */
    public frmThongKe() {
        initComponents();
        showListHoaDon();
    }

    void showListSoLuong(String tuNgay, String denNgay) {
        DefaultTableModel model = (DefaultTableModel) tbSoLuong.getModel();
        try {
            ResultSet rs = c.showSoLuongTheoNgay(tuNgay, denNgay);
            while (rs.next()) {
                model.addRow(new Object[]{model.getRowCount() + 1, rs.getInt("MaTU"), rs.getString("TenThucUong"), rs.getInt("Số Lượng")});
            }
        } catch (SQLException ex) {
            Logger.getLogger(frmThongKe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void showListDoanhThu(String tuNgay, String denNgay) {
        DefaultTableModel model = (DefaultTableModel) tbDoanhThu.getModel();
        try {
            ResultSet rs = c.showDoanhThuTheoNgay(tuNgay, denNgay);
            while (rs.next()) {
                model.addRow(new Object[]{model.getRowCount() + 1, rs.getInt("MaHD"), rs.getString("Ngay"), rs.getInt("TongTien")});
            }
        } catch (SQLException ex) {
            Logger.getLogger(frmThongKe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void calDoanhThu() {
        DefaultTableModel model = (DefaultTableModel) tbDoanhThu.getModel();
        double totalP = 0;
        for (int i = 0; i < tbDoanhThu.getRowCount(); i++) {
            if (tbDoanhThu.getRowCount() < 0) {
                txtDoanhThu.setText("0");
            } else {
                totalP = totalP + Double.parseDouble(model.getValueAt(i, 3).toString());
                txtDoanhThu.setText(Double.toString(totalP));
            }
        }
    }
    
     void showListHoaDon() {
        DefaultTableModel model = (DefaultTableModel) tbHoaDon.getModel();
        try {
            ResultSet rs = c.showHoaDon();
            while (rs.next()) {
                model.addRow(new Object[]{rs.getInt("MaHD"), rs.getString("TenThucUong"),rs.getInt("Soluong"), rs.getInt("DonGia"), rs.getInt("TongTien"), rs.getString("Ngay")});
            }
        } catch (SQLException ex) {
            Logger.getLogger(frmThongKe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelHeader = new javax.swing.JPanel();
        lbLogo = new javax.swing.JLabel();
        lbFrmTitle = new javax.swing.JLabel();
        tabThongKe = new javax.swing.JTabbedPane();
        tabDoanhThu = new javax.swing.JPanel();
        lbTuNgay = new javax.swing.JLabel();
        lbDenNgay = new javax.swing.JLabel();
        dtpDenNgay = new com.toedter.calendar.JDateChooser();
        dtpTuNgay = new com.toedter.calendar.JDateChooser();
        btnHienThi = new javax.swing.JButton();
        lbDenNgay1 = new javax.swing.JLabel();
        txtDoanhThu = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbDoanhThu = new javax.swing.JTable();
        tabSoLuong = new javax.swing.JPanel();
        lbTuNgay1 = new javax.swing.JLabel();
        dtpDenNgay1 = new com.toedter.calendar.JDateChooser();
        dtpTuNgay1 = new com.toedter.calendar.JDateChooser();
        btnHienThi1 = new javax.swing.JButton();
        lbDenNgay3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbSoLuong = new javax.swing.JTable();
        tabDonHang = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbHoaDon = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Thống kê");
        setName("frameThongKe"); // NOI18N
        setType(java.awt.Window.Type.POPUP);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelHeader.setBackground(new java.awt.Color(234, 128, 37));

        lbLogo.setBackground(new java.awt.Color(255, 255, 255));
        lbLogo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbLogo.setForeground(new java.awt.Color(255, 255, 255));
        lbLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/CFH/images/logo.png"))); // NOI18N
        lbLogo.setText("The CàPhê House");

        lbFrmTitle.setBackground(new java.awt.Color(255, 255, 255));
        lbFrmTitle.setFont(new java.awt.Font("Lucida Grande", 1, 24)); // NOI18N
        lbFrmTitle.setForeground(new java.awt.Color(255, 255, 255));
        lbFrmTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbFrmTitle.setText("Thống kê");

        javax.swing.GroupLayout jPanelHeaderLayout = new javax.swing.GroupLayout(jPanelHeader);
        jPanelHeader.setLayout(jPanelHeaderLayout);
        jPanelHeaderLayout.setHorizontalGroup(
            jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelHeaderLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lbLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(lbFrmTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanelHeaderLayout.setVerticalGroup(
            jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelHeaderLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbFrmTitle))
                .addGap(30, 30, 30))
        );

        getContentPane().add(jPanelHeader, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 810, 110));

        tabThongKe.setBackground(new java.awt.Color(255, 255, 255));
        tabThongKe.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        tabThongKe.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tabThongKe.setOpaque(true);

        tabDoanhThu.setBackground(new java.awt.Color(255, 255, 255));
        tabDoanhThu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tabDoanhThu.setLayout(null);

        lbTuNgay.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbTuNgay.setText("Đến ngày:");
        tabDoanhThu.add(lbTuNgay);
        lbTuNgay.setBounds(350, 10, 70, 20);

        lbDenNgay.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbDenNgay.setText("Tổng doanh thu: ");
        tabDoanhThu.add(lbDenNgay);
        lbDenNgay.setBounds(30, 60, 130, 20);

        dtpDenNgay.setDateFormatString("dd/MM/yyyy");
        dtpDenNgay.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tabDoanhThu.add(dtpDenNgay);
        dtpDenNgay.setBounds(480, 10, 140, 30);

        dtpTuNgay.setDateFormatString("dd/MM/yyyy");
        dtpTuNgay.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tabDoanhThu.add(dtpTuNgay);
        dtpTuNgay.setBounds(160, 10, 140, 30);

        btnHienThi.setBackground(new java.awt.Color(234, 128, 37));
        btnHienThi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnHienThi.setForeground(new java.awt.Color(255, 255, 255));
        btnHienThi.setText("Hiển thị");
        btnHienThi.setBorder(null);
        btnHienThi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHienThiActionPerformed(evt);
            }
        });
        tabDoanhThu.add(btnHienThi);
        btnHienThi.setBounds(480, 60, 100, 40);

        lbDenNgay1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbDenNgay1.setText("Từ ngày:");
        tabDoanhThu.add(lbDenNgay1);
        lbDenNgay1.setBounds(30, 10, 60, 20);

        txtDoanhThu.setEditable(false);
        txtDoanhThu.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tabDoanhThu.add(txtDoanhThu);
        txtDoanhThu.setBounds(160, 60, 140, 30);

        tbDoanhThu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        tbDoanhThu.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tbDoanhThu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã Hóa Đơn", "Ngày", "Tổng tiền"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbDoanhThu.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tbDoanhThu.setColumnSelectionAllowed(true);
        tbDoanhThu.setRowHeight(30);
        tbDoanhThu.setRowMargin(8);
        tbDoanhThu.setSelectionBackground(new java.awt.Color(204, 204, 204));
        tbDoanhThu.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbDoanhThu.setSurrendersFocusOnKeystroke(true);
        jScrollPane1.setViewportView(tbDoanhThu);
        tbDoanhThu.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        tabDoanhThu.add(jScrollPane1);
        jScrollPane1.setBounds(0, 130, 810, 410);

        tabThongKe.addTab("Doanh thu", tabDoanhThu);

        tabSoLuong.setBackground(new java.awt.Color(255, 255, 255));
        tabSoLuong.setLayout(null);

        lbTuNgay1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbTuNgay1.setText("Đến ngày:");
        tabSoLuong.add(lbTuNgay1);
        lbTuNgay1.setBounds(350, 10, 70, 20);

        dtpDenNgay1.setDateFormatString("dd/MM/yyyy");
        dtpDenNgay1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tabSoLuong.add(dtpDenNgay1);
        dtpDenNgay1.setBounds(480, 10, 140, 30);

        dtpTuNgay1.setDateFormatString("dd/MM/yyyy");
        dtpTuNgay1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tabSoLuong.add(dtpTuNgay1);
        dtpTuNgay1.setBounds(160, 10, 140, 30);

        btnHienThi1.setBackground(new java.awt.Color(234, 128, 37));
        btnHienThi1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnHienThi1.setForeground(new java.awt.Color(255, 255, 255));
        btnHienThi1.setText("Hiển thị");
        btnHienThi1.setBorder(null);
        btnHienThi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHienThi1ActionPerformed(evt);
            }
        });
        tabSoLuong.add(btnHienThi1);
        btnHienThi1.setBounds(480, 60, 100, 40);

        lbDenNgay3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbDenNgay3.setText("Từ ngày:");
        tabSoLuong.add(lbDenNgay3);
        lbDenNgay3.setBounds(30, 10, 60, 20);

        tbSoLuong.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        tbSoLuong.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tbSoLuong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã thức uống", "Tên thức uống", "Số Lượng"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbSoLuong.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tbSoLuong.setRowHeight(30);
        tbSoLuong.setRowMargin(8);
        tbSoLuong.setSelectionBackground(new java.awt.Color(204, 204, 204));
        tbSoLuong.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbSoLuong.setSurrendersFocusOnKeystroke(true);
        jScrollPane2.setViewportView(tbSoLuong);
        tbSoLuong.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        tabSoLuong.add(jScrollPane2);
        jScrollPane2.setBounds(0, 130, 810, 410);

        tabThongKe.addTab("Số Lượng", tabSoLuong);

        tabDonHang.setBackground(new java.awt.Color(255, 255, 255));
        tabDonHang.setLayout(null);

        tbHoaDon.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        tbHoaDon.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tbHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Hóa Đơn", "Tên Thức Uống", "Số Lượng", "Đơn giá", "Tổng tiền", "Ngày"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbHoaDon.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tbHoaDon.setColumnSelectionAllowed(true);
        tbHoaDon.setRowHeight(30);
        tbHoaDon.setRowMargin(8);
        tbHoaDon.setSelectionBackground(new java.awt.Color(204, 204, 204));
        tbHoaDon.setSurrendersFocusOnKeystroke(true);
        jScrollPane3.setViewportView(tbHoaDon);
        tbHoaDon.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        tabDonHang.add(jScrollPane3);
        jScrollPane3.setBounds(0, 0, 810, 540);

        tabThongKe.addTab("Hóa đơn", tabDonHang);

        getContentPane().add(tabThongKe, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 810, 570));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnHienThiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHienThiActionPerformed
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        showListDoanhThu(dateFormat.format(dtpTuNgay.getDate()), dateFormat.format(dtpDenNgay.getDate()));
        calDoanhThu();
    }//GEN-LAST:event_btnHienThiActionPerformed

    private void btnHienThi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHienThi1ActionPerformed
        // TODO add your handling code here:
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        showListSoLuong(dateFormat.format(dtpTuNgay1.getDate()), dateFormat.format(dtpDenNgay1.getDate()));
    }//GEN-LAST:event_btnHienThi1ActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(frmThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmThongKe().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHienThi;
    private javax.swing.JButton btnHienThi1;
    private com.toedter.calendar.JDateChooser dtpDenNgay;
    private com.toedter.calendar.JDateChooser dtpDenNgay1;
    private com.toedter.calendar.JDateChooser dtpTuNgay;
    private com.toedter.calendar.JDateChooser dtpTuNgay1;
    private javax.swing.JPanel jPanelHeader;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbDenNgay;
    private javax.swing.JLabel lbDenNgay1;
    private javax.swing.JLabel lbDenNgay3;
    private javax.swing.JLabel lbFrmTitle;
    private javax.swing.JLabel lbLogo;
    private javax.swing.JLabel lbTuNgay;
    private javax.swing.JLabel lbTuNgay1;
    private javax.swing.JPanel tabDoanhThu;
    private javax.swing.JPanel tabDonHang;
    private javax.swing.JPanel tabSoLuong;
    public javax.swing.JTabbedPane tabThongKe;
    private javax.swing.JTable tbDoanhThu;
    private javax.swing.JTable tbHoaDon;
    private javax.swing.JTable tbSoLuong;
    private javax.swing.JTextField txtDoanhThu;
    // End of variables declaration//GEN-END:variables
}