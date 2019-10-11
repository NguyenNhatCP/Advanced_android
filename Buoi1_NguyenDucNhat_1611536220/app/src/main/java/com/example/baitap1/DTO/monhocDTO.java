package com.example.baitap1.DTO;

public class monhocDTO {
    private int maMH;
    private String tenMH;

    public monhocDTO() {
    }

    public monhocDTO(int maMH, String tenMH) {
        this.maMH = maMH;
        this.tenMH = tenMH;
    }

    public int getMaMH() {
        return maMH;
    }

    public void setMaMH(int maMH) {
        this.maMH = maMH;
    }

    public String getTenMH() {
        return tenMH;
    }

    public void setTenMH(String tenMH) {
        this.tenMH = tenMH;
    }
}
