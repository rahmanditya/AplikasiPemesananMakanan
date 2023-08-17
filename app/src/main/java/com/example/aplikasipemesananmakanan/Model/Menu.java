package com.example.aplikasipemesananmakanan.Model;

import java.io.Serializable;

public class Menu implements Serializable {
    private String nama,deskripsi;
    private float harga;
    private int id_gambar;
    private int numberInCart;

    public Menu(String nama, String deskripsi, float harga, int id_gambar, int numberInCart) {
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.harga = harga;
        this.id_gambar = id_gambar;
        this.numberInCart = numberInCart;
    }
    public String getNama() { return nama; }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public Float getHarga() {
        return harga;
    }

    public void setHarga(Float harga) {
        this.harga = harga;
    }

    public int getId_gambar() {
        return id_gambar;
    }

    public void setId_gambar(int id_gambar) {
        this.id_gambar = id_gambar;
    }

    public int getNumberInCart() {
        return numberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }
}
