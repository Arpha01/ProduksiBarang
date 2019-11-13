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
public class Petugas {
    private String nip, nama, alamat, password;
    public Object[][] list;
    public String pesan;
    private final Koneksi koneksi = new Koneksi();
    private final PesanDialog pesanDialog = new PesanDialog();

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Object[][] getList() {
        return list;
    }

    public void setList(Object[][] list) {
        this.list = list;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }
    
    public boolean hapus(String nip){
        boolean adaKesalahan = false;	
        Connection connection; 
        
        if ((connection = koneksi.getConnection()) != null){
            int jumlahHapus;
            String SQLStatemen;
            PreparedStatement preparedStatement;
            
            try {
                SQLStatemen = "delete from tbpetugas where nip=?";
                preparedStatement = connection.prepareStatement(SQLStatemen);
                preparedStatement.setString(1, nip);
                jumlahHapus = preparedStatement.executeUpdate();
                
                if (jumlahHapus < 1){
                    pesan = "Data petugas dengan NIP "+nip+" tidak ditemukan";
                    adaKesalahan = true;
                }
                
                preparedStatement.close();
                connection.close();
            } catch (SQLException ex){
                adaKesalahan = true;
                pesan = "Tidak dapat membuka tabel tbpetugas\n"+ex;
            }
        } else {
            adaKesalahan = true;
            pesan = "Tidak dapat melakukan koneksi ke server\n"+koneksi.getPesanKesalahan();
        }
        
        return !adaKesalahan;
    }
    public boolean bacaData(){
        boolean adaKesalahan = false;
        list = new Object[0][0];
        Connection connection;
        
        if ((connection = koneksi.getConnection()) != null){
            String SQLStatemen;
            PreparedStatement preparedStatement;
            ResultSet rset;
            
            try {
                SQLStatemen = "select nip, nama from tbpetugas";
                preparedStatement = connection.prepareStatement(SQLStatemen);
                rset = preparedStatement.executeQuery();
                
                rset.next();
                rset.last();
                list = new Object[rset.getRow()][2];
                
                if (rset.getRow()>0){
                    rset.first();
                    int i=0;
                    do {
                        list[i] = new Object[]{rset.getString("nip"), rset.getString("nama")};
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
        } else {
            adaKesalahan = true;
            pesan = "Tidak dapat melakukan koneksi ke server\n"+koneksi.getPesanKesalahan();
        }
        
        return !adaKesalahan;
    }
    
    public void reset(){
        this.nip = "";
        this.nama = "";
        this.alamat = "";
        this.password = "";
    }
    
    public boolean baca(String nip){
        boolean adaKesalahan = false;	
        Connection connection;
        reset();
        
        if ((connection = koneksi.getConnection()) != null){
            PreparedStatement preparedStatement;
            ResultSet rset;
                    
            try {
                String SQLStatemen = "select * from tbpetugas where nip=?";
                preparedStatement = connection.prepareStatement(SQLStatemen);
                preparedStatement.setString(1, nip);
                rset = preparedStatement.executeQuery();
                
                rset.next();
                if (rset.getRow()>0){
                    this.nip = rset.getString("nip");
                    this.nama = rset.getString("nama");
                    this.alamat = rset.getString("alamat");
                    this.password = rset.getString("password");  
                } else {
                    adaKesalahan = true;
                    pesan = "NIP \""+nip+"\" tidak ditemukan";
                }
                
                preparedStatement.close();
                rset.close();
                connection.close();
            } catch (SQLException ex){
                adaKesalahan = true;
                pesan = "Tidak dapat membuka tabel tbpetugas\n"+ex;
            }
        } else {
            adaKesalahan = true;
            pesan = "Tidak dapat melakukan koneksi ke server\n"+koneksi.getPesanKesalahan();
        }
        
        return !adaKesalahan;
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
                SQLStatemen = "select * from tbpetugas where nip=?";
                
                preparedStatement = connection.prepareStatement(SQLStatemen);
                preparedStatement.setString(1, nip);
                rset = preparedStatement.executeQuery();
                
                rset.next();
                if (rset.getRow()>0){
                    if (pesanDialog.tampilkanPilihan("NIP sudah ada\nApakah data diperbaharui?","Konfirmasi", new Object[]{"Ya","Tidak"}) == 0){
                        simpan = true;
                        SQLStatemen = "update tbpetugas set nama=?, alamat=?, password=? where nip=?";
                        
                        preparedStatement = connection.prepareStatement(SQLStatemen);   
                        preparedStatement.setString(1, nama);
                        preparedStatement.setString(2, alamat);
                        preparedStatement.setString(3, password);
                        preparedStatement.setString(4, nip);
                        
                        jumlahSimpan = preparedStatement.executeUpdate(); 
                    }
                } else {
                    simpan = true;
                    SQLStatemen = "insert into tbpetugas(nip, nama, alamat, password) values (?,?,?,?)"; 
                    
                    preparedStatement = connection.prepareStatement(SQLStatemen);  
                    preparedStatement.setString(1, nip);
                    preparedStatement.setString(2, nama);
                    preparedStatement.setString(3, alamat);
                    preparedStatement.setString(4, password);
                    
                    jumlahSimpan = preparedStatement.executeUpdate();
                }
                
                if (simpan) {
                    if (jumlahSimpan < 1){
                        adaKesalahan = true; 
                        pesan = "Gagal menyimpan data petugas";
                    }
                }
                
                preparedStatement.close();
                rset.close();
                connection.close();                
            } catch (SQLException ex){
                adaKesalahan = true;
                pesan = "Tidak dapat membuka tabel tbpetugas\n"+ex+"\n"+SQLStatemen;
            }            
        } else {
            adaKesalahan = true;
            pesan = "Tidak dapat melakukan koneksi ke server\n"+koneksi.getPesanKesalahan();
        }
        
        return !adaKesalahan;
    }
}
