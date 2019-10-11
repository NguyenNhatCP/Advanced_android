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
import java.util.Date;

/**
 *
 * @author lhd
 */
public class Controller {

    Database db = new Database();

    //Truy van tat ca du lieu trong Table LoaiSP         
    public ResultSet ShowLoaiSP() throws SQLException {
        db.connectSQL();
        String sql = "SELECT * FROM Loai";
        return db.LoadData(sql);
    }
    
   
    public ResultSet ShowListSP() throws SQLException {
        db.connectSQL();
        String sql = "select MaTU,TenThucUong,DonGia,TenLoai from Loai,ThucUong where Loai.MaLoai=ThucUong.MaLoai";
        return db.LoadData(sql);
    }

    public ResultSet ShowListCoffee() throws SQLException {
        db.connectSQL();
        String sql = "select TenThucUong, DonGia from ThucUong where MaLoai = 1";
        return db.LoadData(sql);
    }

    public ResultSet ShowListTea() throws SQLException {
        db.connectSQL();
        String sql = "select TenThucUong, DonGia from ThucUong where MaLoai = 2";
        return db.LoadData(sql);
    }

    public ResultSet ShowListBlender() throws SQLException {
        db.connectSQL();
        String sql = "select TenThucUong, DonGia from ThucUong where MaLoai = 3";
        return db.LoadData(sql);
    }

    public ResultSet ShowListJuice() throws SQLException {
        db.connectSQL();
        String sql = "select TenThucUong, DonGia from ThucUong where MaLoai = 4";
        return db.LoadData(sql);
    }

    public ResultSet ShowMaLoaiTheoTenLoai(String ten) throws SQLException {
        db.connectSQL();
        String sql = "SELECT MaLoai FROM Loai where TenLoai = N'" + ten + "'";
        return db.LoadData(sql);
    }

    public void InsertSP(String ten, int dg, int ma) throws SQLException {
        db.connectSQL();
        String sql = "INSERT INTO ThucUong values (N'" + ten + "'," + dg + "," + ma + ")";
        db.UpdateData(sql);
    }

    public void UpdateSP(String ten, int dg, int maLoai, int maTU) throws SQLException {
        db.connectSQL();
        String sql = "Update ThucUong set TenThucUong=N'" + ten + "',DonGia=" + dg + ",MaLoai=" + maLoai + "where MaTU=" + maTU + "";
        db.UpdateData(sql);
    }

    public void DeleteSP(int maTU) throws SQLException {
        db.connectSQL();
        String sql = "Delete from ThucUong where MaTU = " + maTU + "";
        db.UpdateData(sql);
    }

    public ResultSet showDiscountCode(String code) throws SQLException {
        db.connectSQL();
        String sql = "Select MaKM, PhanTram from KhuyenMai where TenKM = '" + code + "'";
        return db.LoadData(sql);
    }

    /*After insert => return lasted MaDH*/
    public ResultSet insertHD(int TongTien, int maKM) throws SQLException {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        db.connectSQL();
        String sql = "INSERT INTO HoaDon(Ngay, TongTien, MaKM) OUTPUT INSERTED.MaHD values ('" + dateFormat.format(date) + "', " + TongTien + ", " + maKM + ")";
        return db.LoadData(sql);
    }

    public ResultSet getMaTU(String tenTU) throws SQLException {
        db.connectSQL();
        String sql = "Select MaTU from ThucUong where TenThucUong = N'" + tenTU + "'";
        return db.LoadData(sql);
    }

    public void insetCTHDtheoMaHD(int maHD, int maTU, int soLuong) throws SQLException {
        db.connectSQL();
        String sql = "Insert into ChiTietHoaDon(MaHD,MaTU, Soluong) values (" + maHD + "," + maTU + "," + soLuong + ")";
        db.UpdateData(sql);
    }

    public ResultSet showDoanhThuTheoNgay(String tuNgay, String denNgay) throws SQLException {
        db.connectSQL();
        String sql = "Select MaHD, Ngay, TongTien from HoaDon where Ngay Between '" + tuNgay + "' And '" + denNgay + "'";
        return db.LoadData(sql);
    }

    public ResultSet showSoLuongTheoNgay(String tuNgay, String denNgay) throws SQLException {
        db.connectSQL();
        String sql = "exec sp_SoLuongMon '" + tuNgay + "', '" + denNgay + "'";
        return db.LoadData(sql);
    }

    public ResultSet getUser(String user, String pass) throws SQLException {
        db.connectSQL();
        String sql = "Select username,pass from NguoiDung where username ='" + user + "' and pass ='" + pass + "'";
        return db.LoadData(sql);
    }
    
    public void UpdateLoaiSP(int ma, String ten) throws SQLException {
        db.connectSQL();
        String sql = "Update Loai set TenLoai=N'" + ten + "' where MaLoai = "+ma+"";
        db.UpdateData(sql);
    }
    
    public ResultSet showHoaDon() throws SQLException {
        db.connectSQL();
        String sql = "exec sp_showHoaDon";
        return db.LoadData(sql);
    }
    public ResultSet showHDtheoMaHD(int maHD) throws SQLException {
        db.connectSQL();
        String sql = "exec sp_showHoaDontheoMaHD " + maHD;
        return db.LoadData(sql);
    }
  
    public void InsertKM(String ten, int pt, String nd) throws SQLException{
        db.connectSQL();
        String sql = "INSERT INTO KhuyenMai values ('" + ten + "'," + pt + ", N'" + nd + "')";
        db.UpdateData(sql);
    }

    public void UpdateKM(int ma,String ten, int pt, String nd) throws SQLException{
        db.connectSQL();
        String sql = "Update KhuyenMai set TenKM='" + ten + "',PhanTram=" + pt + ",NoiDung=N'" + nd + "'where MaKM=" + ma + "";
        db.UpdateData(sql);
    }
    
    public void DeleteKM(int ma) throws SQLException{
        db.connectSQL();
        String sql = "Delete from KhuyenMai where MaKM = " + ma + "";
        db.UpdateData(sql);
    } 
    
    public ResultSet showMaKM() throws SQLException {
        db.connectSQL();
        String sql = "select * from KhuyenMai";
        return db.LoadData(sql);
    }
}
