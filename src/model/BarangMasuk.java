/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import view.PesanDialog;

/**
 *
 * @author mucha
 */
public class BarangMasuk {
    private String no_masuk, kd_barang, tgl_produksi, pesan;
    private Object[][] list;
    private final Koneksi koneksi = new Koneksi();
    private final PesanDialog pesanDialog = new PesanDialog();

    public String getNo_masuk() {
        return no_masuk;
    }

    public void setNo_masuk(String no_masuk) {
        this.no_masuk = no_masuk;
    }

    public String getKd_barang() {
        return kd_barang;
    }

    public void setKd_barang(String kd_barang) {
        this.kd_barang = kd_barang;
    }

    public String getTgl_produksi() {
        return tgl_produksi;
    }

    public void setTgl_produksi(String tgl_produksi) {
        this.tgl_produksi = tgl_produksi;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public Object[][] getList() {
        return list;
    }

    public void setList(Object[][] list) {
        this.list = list;
    }
    
    public boolean hapus(String kode){
        boolean adaKesalahan = false;	
        Connection connection; 
        
        if((connection = koneksi.getConnection()) != null){
            int jumlahHapus;
            String SQLStatemen;
            PreparedStatement preparedStatement;
            
            try{
                SQLStatemen = "delete from tbmasuk where no_masuk=?";
                preparedStatement = connection.prepareStatement(SQLStatemen);
                preparedStatement.setString(1, kode);
                jumlahHapus = preparedStatement.executeUpdate();
                
                 if (jumlahHapus < 1){
                    pesan = "Data Barang masuk dengan kode "+kode+" tidak ditemukan";
                    adaKesalahan = true;
                }
                
                preparedStatement.close();
                connection.close();
            }catch(SQLException ex){
                adaKesalahan = true;
                pesan = "Tidak dapat membuka tabel tbmasuk\n"+ex;
            }
        }else{
            adaKesalahan = true;
            pesan = "Tidak dapat melakukan koneksi ke server\n"+koneksi.getPesanKesalahan();
        }
        
        return !adaKesalahan;
    }
    
    public boolean bacaData(){
        boolean adaKesalahan = false;
        Connection connection;
        list = new Object[0][0];
         if ((connection = koneksi.getConnection()) != null){
            String SQLStatemen;
            PreparedStatement preparedStatement;
            ResultSet rset;
            
             try {
                SQLStatemen = "select * from tbmasuk";
                preparedStatement = connection.prepareStatement(SQLStatemen);
                rset = preparedStatement.executeQuery();
                
                rset.next();
                rset.last();
                list = new Object[rset.getRow()][2];
                
                if (rset.getRow()>0){
                    rset.first();
                    int i=0;
                    do {
                        list[i] = new Object[]{rset.getString("no_masuk"), rset.getString("kd_barang"), rset.getString("tgl_produksi")};
                        i++;
                    } while (rset.next());
                }
                
                preparedStatement.close();
                rset.close();
                connection.close();
            } catch (SQLException ex){
                adaKesalahan = true;
                pesan = "Tidak dapat membaca data\n"+ex.getMessage();
            }
         }else{
             adaKesalahan = true;
             pesan = "Tidak dapat melakukan koneksi ke server " + koneksi.getPesanKesalahan();
         }
         
         return !adaKesalahan;
    }
    
     
    public boolean baca(String kode){
        boolean adaKesalahan = false;
        Connection connection; 
        
        reset();
         if ((connection = koneksi.getConnection()) != null){
            PreparedStatement preparedStatement;
            ResultSet rset;
                    
            try {
                String SQLStatemen = "select * from tbmasuk where no_masuk=?";
                preparedStatement = connection.prepareStatement(SQLStatemen);
                preparedStatement.setString(1, kode);
                rset = preparedStatement.executeQuery();
                
                rset.next();
                if (rset.getRow()>0){
                    this.no_masuk = rset.getString("no_masuk");
                    this.kd_barang = rset.getString("kd_barang");
                    this.tgl_produksi = rset.getString("tgl_produksi");
                } else {
                    adaKesalahan = true;
                    pesan = "No. Barang masuk \""+kode+"\" tidak ditemukan";
                }
                
                preparedStatement.close();
                rset.close();
                connection.close();
            } catch (SQLException ex){
                adaKesalahan = true;
                pesan = "Tidak dapat membuka tabel tbmasuk\n"+ex;
            }
        } else {
            adaKesalahan = true;
            pesan = "Tidak dapat melakukan koneksi ke server\n"+koneksi.getPesanKesalahan();
        }
        
        return !adaKesalahan;
    }
      
    public void reset(){
        this.no_masuk = "";
        this.kd_barang = "";
        this.tgl_produksi = "";
    }
    
    public boolean simpan(){
        boolean adaKesalahan = false;
        Connection connection; 
        
        if ((connection = koneksi.getConnection()) != null){
            int jumlahSimpan=0;
            boolean simpan = false; 
            String SQLStatemen="";
            PreparedStatement preparedStatement;
            ResultSet rset;
            
              try {
                SQLStatemen = "select * from tbmasuk where no_masuk=?";
                
                preparedStatement = connection.prepareStatement(SQLStatemen);
                preparedStatement.setString(1, no_masuk);
                rset = preparedStatement.executeQuery();
                
                rset.next();
                if (rset.getRow()>0){
                    if (pesanDialog.tampilkanPilihan("Kode jenis barang sudah ada\nApakah data diperbaharui?","Konfirmasi", new Object[]{"Ya","Tidak"}) == 0){
                        simpan = true;
                        SQLStatemen = "update tbmasuk set kd_barang=?, tgl_produksi=? where no_masuk=?";
                        
                        preparedStatement = connection.prepareStatement(SQLStatemen);   
                        preparedStatement.setString(1, kd_barang);
                        preparedStatement.setString(2, tgl_produksi);
                        preparedStatement.setString(3, no_masuk);
                        
                        jumlahSimpan = preparedStatement.executeUpdate(); 
                    }
                } else {
                    simpan = true;
                    SQLStatemen = "insert into tbmasuk(no_masuk, kd_barang, tgl_produksi) values (?,?,?)"; 
                    
                    preparedStatement = connection.prepareStatement(SQLStatemen);
                    preparedStatement.setString(1, no_masuk);
                    preparedStatement.setString(2, kd_barang);
                    preparedStatement.setString(3, tgl_produksi);
                    
                    jumlahSimpan = preparedStatement.executeUpdate();
                }
                
                if (simpan) {
                    if (jumlahSimpan < 1){
                        adaKesalahan = true; 
                        pesan = "Gagal menyimpan data pemasukkan barang";
                    }
                }
                
                preparedStatement.close();
                rset.close();
                connection.close();                
            } catch (SQLException ex){
                adaKesalahan = true;
                pesan = "Tidak dapat membuka tabel tbmasuk\n"+ex+"\n"+SQLStatemen;
            }            
        } else {
            adaKesalahan = true;
            pesan = "Tidak dapat melakukan koneksi ke server\n"+koneksi.getPesanKesalahan();
        }
        
        return !adaKesalahan;
    }
    
}
