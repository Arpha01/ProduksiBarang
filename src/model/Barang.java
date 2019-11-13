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
public class Barang {
    private Integer jumlah, harga;
    private String kode, satuan, jenis, nama, pesan;
    private Object[][] list;
    private final Koneksi koneksi = new Koneksi();
    private final PesanDialog pesanDialog = new PesanDialog();
    
    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }
    

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
      
    public String getPesan(){
        return pesan;
    }
    
    public Object[][] getList(){
        return list;
    }
    
    public void setList(Object[][] list){
        this.list = list;
    }
    
    public boolean simpan(){
        boolean adaKesalahan = false;
        Connection connection;
        
        if((connection = koneksi.getConnection()) != null){
            int jumlahSimpan=0;
            String SQLStatemen="";
            boolean simpan = false;
            ResultSet rset;
            PreparedStatement preparedStatement;
            
            try{
                SQLStatemen = "select * from tbbarang where kode=?";
                
                preparedStatement = connection.prepareStatement(SQLStatemen);
                preparedStatement.setString(1, kode);
                rset = preparedStatement.executeQuery();
                
                rset.next();
                if(rset.getRow()>0){
                    if(pesanDialog.tampilkanPilihan("Barang sudah ada\nApakah data ingin diperbaharui?", "Konfirmasi", new Object[]{"Ya", "Tidak"}) == 0){
                        simpan = true;
                        SQLStatemen = "update tbbarang set nama=?, jenis=?, satuan=?, jumlah=?, harga=? where kode=?";
                        
                        preparedStatement = connection.prepareStatement(SQLStatemen);
                        preparedStatement.setString(1, nama);
                        preparedStatement.setString(2, jenis);
                        preparedStatement.setString(3, satuan);
                        preparedStatement.setInt(4, jumlah);
                        preparedStatement.setInt(5, harga);
                        preparedStatement.setString(6, kode);
                        
                        jumlahSimpan = preparedStatement.executeUpdate();
                    }
                }else{
                    simpan = true;
                    SQLStatemen = "insert into tbbarang(kode, nama, jenis, satuan, jumlah, harga) values (?,?,?,?,?,?)"; 
                    
                    preparedStatement = connection.prepareStatement(SQLStatemen);
                    preparedStatement.setString(1, kode);
                    preparedStatement.setString(2, nama);
                    preparedStatement.setString(3, jenis);
                    preparedStatement.setString(4, satuan);
                    preparedStatement.setInt(5, jumlah);
                    preparedStatement.setInt(6, harga);
                    
                    jumlahSimpan = preparedStatement.executeUpdate();
                }
                
                if(simpan){
                    if(jumlahSimpan < 1){
                        adaKesalahan = true;
                        pesan = "Gagal menyimpan data";
                    }
                }
                
                preparedStatement.close();
                rset.close();
                connection.close();
                
            }catch(SQLException ex){
                adaKesalahan = true;
                pesan = "Tidak dapat memuat table tbbarang " + ex;
            }
        }else{
            adaKesalahan = true;
            pesan = "Tidak dapat melakukan koneksi ke server " + koneksi.getPesanKesalahan();
        }
         return !adaKesalahan;   
    }
    
    public void reset(){
        this.kode = "";
        this.nama = "";
        this.jenis = "";
        this.jumlah = 0;
        this.harga = 0;
    }
     public boolean baca(String kode){
        boolean adaKesalahan = false;
        Connection connection; 
        reset();
         if ((connection = koneksi.getConnection()) != null){
            PreparedStatement preparedStatement;
            ResultSet rset;
                    
            try {
                String SQLStatemen = "select * from tbbarang where kode=?";
                preparedStatement = connection.prepareStatement(SQLStatemen);
                preparedStatement.setString(1, kode);
                rset = preparedStatement.executeQuery();
                
                rset.next();
                if (rset.getRow()>0){
                    this.kode = rset.getString("kode");
                    this.nama = rset.getString("nama");
                    this.jenis = rset.getString("jenis");
                    this.satuan = rset.getString("satuan");
                    this.jumlah = rset.getInt("jumlah");
                    this.harga = rset.getInt("harga");
                } else {
                    adaKesalahan = true;
                    pesan = "Kode barang \""+kode+"\" tidak ditemukan";
                }
                
                preparedStatement.close();
                rset.close();
                connection.close();
            } catch (SQLException ex){
                adaKesalahan = true;
                pesan = "Tidak dapat membuka tabel tbbarang\n"+ex;
            }
        } else {
            adaKesalahan = true;
            pesan = "Tidak dapat melakukan koneksi ke server\n"+koneksi.getPesanKesalahan();
        }
        
        return !adaKesalahan;
    }
    
    public boolean hapus(String kode){
        boolean adaKesalahan = false;	
        Connection connection; 
        
        if((connection = koneksi.getConnection()) != null){
            int jumlahHapus;
            String SQLStatemen;
            PreparedStatement preparedStatement;
            
            try{
                SQLStatemen = "delete from tbbarang where kode=?";
                preparedStatement = connection.prepareStatement(SQLStatemen);
                preparedStatement.setString(1, kode);
                jumlahHapus = preparedStatement.executeUpdate();
                
                 if (jumlahHapus < 1){
                    pesan = "Data barang dengan kode "+kode+" tidak ditemukan";
                    adaKesalahan = true;
                }
                
                preparedStatement.close();
                connection.close();
            }catch(SQLException ex){
                adaKesalahan = true;
                pesan = "Tidak dapat membuka tabel tbbarang\n"+ex;
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
                SQLStatemen = "select kode,nama from tbbarang";
                preparedStatement = connection.prepareStatement(SQLStatemen);
                rset = preparedStatement.executeQuery();
                
                rset.next();
                rset.last();
                list = new Object[rset.getRow()][2];
                
                if (rset.getRow()>0){
                    rset.first();
                    int i=0;
                    do {
                        list[i] = new Object[]{rset.getString("kode"), rset.getString("nama")};
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
}
