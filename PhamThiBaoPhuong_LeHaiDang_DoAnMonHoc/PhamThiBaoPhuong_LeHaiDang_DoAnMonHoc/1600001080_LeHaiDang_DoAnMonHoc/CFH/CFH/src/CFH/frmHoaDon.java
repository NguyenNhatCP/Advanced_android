/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CFH;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lehai
 */
public class frmHoaDon extends javax.swing.JFrame {

    /**
     * Creates new form frmHoaDon
     */
    public frmHoaDon() {
        initComponents();
    }
    Controller c = new Controller();

    private int maHD;

    public int getMaHD() {
        return maHD;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    void showHD(int maHD) {
        double totalP = 0;
        DefaultTableModel model = (DefaultTableModel) tbInvoice.getModel();
        try {
            ResultSet rs = c.showHDtheoMaHD(maHD);
            while (rs.next()) {
                lbMaHD.setText("#" + rs.getInt("MaHD"));
                model.addRow(new Object[]{model.getRowCount() + 1, rs.getString("TenThucUong"), rs.getInt("Soluong"), rs.getInt("DonGia"), rs.getInt("TongTien")});
                lbNgayDat.setText(rs.getString("Ngay"));
            }
            for (int i = 0; i < tbInvoice.getRowCount(); i++) {

                totalP = totalP + Double.parseDouble(model.getValueAt(i, 4).toString());
                lbTongTien.setText(Double.toString(totalP));
            
        }
    }
    catch (SQLException ex

    
        ) {
            Logger.getLogger(frmHoaDon.class.getName()).log(Level.SEVERE, null, ex);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tbInvoice = new javax.swing.JTable();
        lbTong = new javax.swing.JLabel();
        lbTitle = new javax.swing.JLabel();
        lbMaHD = new javax.swing.JLabel();
        lbTongTien = new javax.swing.JLabel();
        lbNgay = new javax.swing.JLabel();
        lbNgayDat = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hóa đơn");
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbInvoice.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        tbInvoice.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tbInvoice.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tên", "Số lượng", "Giá", "Thành tiền"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbInvoice.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tbInvoice.setColumnSelectionAllowed(true);
        tbInvoice.setRowHeight(30);
        tbInvoice.setRowMargin(8);
        tbInvoice.setSelectionBackground(new java.awt.Color(204, 204, 204));
        tbInvoice.setSurrendersFocusOnKeystroke(true);
        jScrollPane1.setViewportView(tbInvoice);
        tbInvoice.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 480, 402));

        lbTong.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbTong.setText("Tổng:");
        getContentPane().add(lbTong, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 500, 40, -1));

        lbTitle.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbTitle.setText("Hóa Đơn ");
        getContentPane().add(lbTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 40, 90, 40));

        lbMaHD.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        getContentPane().add(lbMaHD, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 40, 150, 40));

        lbTongTien.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbTongTien.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        getContentPane().add(lbTongTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 490, 150, 40));

        lbNgay.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbNgay.setText("Ngày:");
        getContentPane().add(lbNgay, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 500, -1, -1));

        lbNgayDat.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        getContentPane().add(lbNgayDat, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 490, 150, 40));

        jButton1.setText("Thoát");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 0, -1, -1));

        setSize(new java.awt.Dimension(480, 550));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(frmHoaDon.class
.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        

} catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmHoaDon.class
.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        

} catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmHoaDon.class
.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        

} catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmHoaDon.class
.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmHoaDon().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbMaHD;
    private javax.swing.JLabel lbNgay;
    private javax.swing.JLabel lbNgayDat;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JLabel lbTong;
    private javax.swing.JLabel lbTongTien;
    private javax.swing.JTable tbInvoice;
    // End of variables declaration//GEN-END:variables
}
