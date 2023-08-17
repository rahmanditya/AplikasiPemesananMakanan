package com.example.aplikasipemesananmakanan.Riwayat;

public class RiwayatModel {

    String radioPembayaran,totalHarga,totalItem,saveTgl;

    public RiwayatModel(){}

    public RiwayatModel(String radioPembayaran, String totalHarga, String totalItem, String saveTgl) {
        this.radioPembayaran = radioPembayaran;
        this.totalHarga = totalHarga;
        this.totalItem = totalItem;
        this.saveTgl = saveTgl;
    }

    public String getRadioPembayaran() {
        return radioPembayaran;
    }

    public void setRadioPembayaran(String radioPembayaran) {
        this.radioPembayaran = radioPembayaran;
    }

    public String getTotalHarga() {
        return totalHarga;
    }

    public void setTotalHarga(String totalHarga) {
        this.totalHarga = totalHarga;
    }

    public String getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(String totalItem) {
        this.totalItem = totalItem;
    }

    public String getSaveTgl() {
        return saveTgl;
    }

    public void setSaveTgl(String saveTgl) {
        this.saveTgl = saveTgl;
    }
}
