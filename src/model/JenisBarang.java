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
public class JenisBarang {
    private String kode, nama;
    private String pesan;
    private Object[][] list;
    private String[] listNama;
    
    private final Koneksi koneksi = new Koneksi();
    private final PesanDialog pesanDialog = new PesanDialog();

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }
    public String getPesan(){
        return pesan;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
    
    public Object[][] getList(){
        return list;
    }
    
    public String[] getListNama(){
        return listNama;
    }
    
    public void setList(Object[][] list){
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
                SQLStatemen = "delete from tbjenisbarang where kodejenis=?";
                preparedStatement = connection.prepareStatement(SQLStatemen);
                preparedStatement.setString(1, kode);
                jumlahHapus = preparedStatement.executeUpdate();
                
                 if (jumlahHapus < 1){
                    pesan = "Data jenis barang dengan kode "+kode+" tidak ditemukan";
                    adaKesalahan = true;
                }
                
                preparedStatement.close();
                connection.close();
            }catch(SQLException ex){
                adaKesalahan = true;
                pesan = "Tidak dapat membuka tabel tbjenisbarang\n"+ex;
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
                SQLStatemen = "select kodejenis, namajenis from tbjenisbarang";
                preparedStatement = connection.prepareStatement(SQLStatemen);
                rset = preparedStatement.executeQuery();
                
                rset.next();
                rset.last();
                list = new Object[rset.getRow()][2];
                
                if (rset.getRow()>0){
                    rset.first();
                    int i=0;
                    do {
                        list[i] = new Object[]{rset.getString("kodejenis"), rset.getString("namajenis")};
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
                String SQLStatemen = "select * from tbjenisbarang where kodejenis=?";
                preparedStatement = connection.prepareStatement(SQLStatemen);
                preparedStatement.setString(1, kode);
                rset = preparedStatement.executeQuery();
                
                rset.next();
                if (rset.getRow()>0){
                    this.kode = rset.getString("kodejenis");
                    this.nama = rset.getString("namajenis");
                } else {
                    adaKesalahan = true;
                    pesan = "Kode jenis barang \""+kode+"\" tidak ditemukan";
                }
                
                preparedStatement.close();
                rset.close();
                connection.close();
            } catch (SQLException ex){
                adaKesalahan = true;
                pesan = "Tidak dapat membuka tabel tbjenisbarang\n"+ex;
            }
        } else {
            adaKesalahan = true;
            pesan = "Tidak dapat melakukan koneksi ke server\n"+koneksi.getPesanKesalahan();
        }
        
        return !adaKesalahan;
    }
    
    public void reset(){
        this.kode = "";
        this.nama = "";
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
                SQLStatemen = "select * from tbjenisbarang where kodejenis=?";
                
                preparedStatement = connection.prepareStatement(SQLStatemen);
                preparedStatement.setString(1, kode);
                rset = preparedStatement.executeQuery();
                
                rset.next();
                if (rset.getRow()>0){
                    if (pesanDialog.tampilkanPilihan("Kode jenis barang sudah ada\nApakah data diperbaharui?","Konfirmasi", new Object[]{"Ya","Tidak"}) == 0){
                        simpan = true;
                        SQLStatemen = "update tbjenisbarang set namajenis=? where kodejenis=?";
                        
                        preparedStatement = connection.prepareStatement(SQLStatemen);   
                        preparedStatement.setString(1, nama);
                        preparedStatement.setString(2, kode);
                        
                        jumlahSimpan = preparedStatement.executeUpdate(); 
                    }
                } else {
                    simpan = true;
                    SQLStatemen = "insert into tbjenisbarang(kodejenis, namajenis) values (?,?)"; 
                    
                    preparedStatement = connection.prepareStatement(SQLStatemen);   
                    preparedStatement.setString(1, kode);
                    preparedStatement.setString(2, nama);
                    
                    jumlahSimpan = preparedStatement.executeUpdate();
                }
                
                if (simpan) {
                    if (jumlahSimpan < 1){
                        adaKesalahan = true; 
                        pesan = "Gagal menyimpan data jenis barang";
                    }
                }
                
                preparedStatement.close();
                rset.close();
                connection.close();                
            } catch (SQLException ex){
                adaKesalahan = true;
                pesan = "Tidak dapat membuka tabel tbjenisbarang\n"+ex+"\n"+SQLStatemen;
            }            
        } else {
            adaKesalahan = true;
            pesan = "Tidak dapat melakukan koneksi ke server\n"+koneksi.getPesanKesalahan();
        }
        
        return !adaKesalahan;
    }
}
