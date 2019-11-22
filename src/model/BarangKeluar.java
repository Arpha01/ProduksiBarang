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
import java.sql.Statement;
import java.util.HashMap;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author mucha
 */
public class BarangKeluar {
    private String no_keluar, kd_barang, tgl_produksi, kd_supplier, tgl_keluar, pesan;
    private final Koneksi koneksi = new Koneksi();
    private Integer jumlah;
    private Object[][] list;
    private Object[][] listPengeluaran;

    public Object[][] getList() {
        return list;
    }

    public void setList(Object[][] list) {
        this.list = list;
    }

    
    public Object[][] getListPengeluaran() {
        return listPengeluaran;
    }

    public void setListPengeluaran(Object[][] listPengeluaran) {
        this.listPengeluaran = listPengeluaran;
    }

    
    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public String getNo_keluar() {
        return no_keluar;
    }

    public void setNo_keluar(String no_keluar) {
        this.no_keluar = no_keluar;
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

    public String getKd_supplier() {
        return kd_supplier;
    }

    public void setKd_supplier(String kd_supplier) {
        this.kd_supplier = kd_supplier;
    }

    public String getTgl_keluar() {
        return tgl_keluar;
    }

    public void setTgl_keluar(String tgl_keluar) {
        this.tgl_keluar = tgl_keluar;
    }

    public Integer getJumlah() {
        return jumlah;
    }

    public void setJumlah(Integer jumlah) {
        this.jumlah = jumlah;
    }
    
    public boolean cetakLaporan(String kdBarang, String tglKeluar){
        boolean adaKesalahan = false;
        Connection connection;
        
        if ((connection = koneksi.getConnection()) != null){
            String SQLStatement;
            Statement statement;
            ResultSet resultSet = null;
            
            try{
                SQLStatement = "SELECT tbkeluar.`no_keluar` AS tbkeluar_no_keluar, " 
                + " tbkeluar.`kd_barang` AS tbkeluar_kd_barang, " 
                + " tbkeluar.`tgl_produksi` AS tbkeluar_tgl_produksi, " 
                + " tbkeluar.`kd_supplier` AS tbkeluar_kd_supplier, " 
                + " tbkeluar.`tgl_keluar` AS tbkeluar_tgl_keluar, " 
                + " tbkeluar.`jumlah` AS tbkeluar_jumlah, " 
                + " tbsupplier.`nama_supplier` AS tbsupplier_nama_supplier, " 
                + " tbsupplier.`alamat` AS tbsupplier_alamat, " 
                + " tbsupplier.`no_telp` AS tbsupplier_no_telp, " 
                + " tbbarang.`nama` AS tbbarang_nama, " 
                + " tbbarang.`jenis` AS tbbarang_jenis, " 
                + " tbbarang.`satuan` AS tbbarang_satuan, " 
                + " tbbarang.`jumlah` AS tbbarang_jumlah, " 
                + " tbbarang.`harga` AS tbbarang_harga, " 
                + " tbkeluar.`jumlah`*tbbarang.`harga` AS tbharga_total " 
                + " FROM " 
                + " `tbsupplier` tbsupplier INNER JOIN `tbkeluar` tbkeluar ON tbsupplier.`kode` = tbkeluar.`kd_supplier`\n" 
                + " INNER JOIN `tbbarang` tbbarang ON tbkeluar.`kd_barang` = tbbarang.`kode`";
                
                if((!kdBarang.equals("")) && (!tglKeluar.equals(""))){
                    SQLStatement = SQLStatement + " where tbkeluar.`kd_barang`="+kdBarang;
                    SQLStatement = SQLStatement + " and tbkeluar.`tgl_keluar`='"+tglKeluar+"' ";
                }else if(!kdBarang.equals("")){
                    SQLStatement = SQLStatement + " where tbkeluar.`kd_barang`="+kdBarang;
                }else if(!tglKeluar.equals("")){
                    SQLStatement = SQLStatement + " where tbkeluar.`tgl_keluar` LIKE '%" + tglKeluar + "%'";
                }
                statement = connection.createStatement();
                resultSet = statement.executeQuery(SQLStatement);
            }catch(SQLException ex){
                adaKesalahan = true;
                pesan = "Tidak dapat membaca data\n"+ex;
            }
            if(resultSet != null){
               try{
                    JasperDesign disain = JRXmlLoader.load("src/reports/BarangReport.jrxml");
                    JasperReport nilaiLaporan = JasperCompileManager.compileReport(disain);
                    JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(resultSet);
                    JasperPrint cetak = JasperFillManager.fillReport(nilaiLaporan,new HashMap(),resultSetDataSource);
                    JasperViewer.viewReport(cetak,false);
               }catch(JRException ex){
                    adaKesalahan = true;
                    pesan = "Tidak dapat mencetak laporan\n"+ex;
               }
            }
        }else{
                adaKesalahan = true;
                pesan = "Tidak dapat melakukan koneksi ke server\n"+koneksi.getPesanKesalahan();
        }
        return !adaKesalahan;
    }
    
    public boolean simpan(){
        boolean adaKesalahan = false;	
	Connection connection; 
	
	if ((connection = koneksi.getConnection()) != null){
            int jumlahSimpan = 0;
            String SQLStatemen;
            PreparedStatement preparedStatement;
            
            try{
                SQLStatemen = "delete from tbkeluar where no_keluar=?"; 
                preparedStatement = connection.prepareStatement(SQLStatemen);
                preparedStatement.setString(1, no_keluar);
                preparedStatement.executeUpdate();
            } catch (SQLException ex){}
            
            for (Object[] recPengeluaran: listPengeluaran){
                try {
                    SQLStatemen = "insert into tbkeluar(no_keluar, kd_barang, tgl_produksi, kd_supplier, tgl_keluar, jumlah) values (?,?,?,?,?,?)";
                    preparedStatement = connection.prepareStatement(SQLStatemen);
                    preparedStatement.setString(2, kd_barang);
                    preparedStatement.setString(5, tgl_keluar);
                    //dari table
                    preparedStatement.setString(1, recPengeluaran[0].toString());
                    preparedStatement.setString(3, recPengeluaran[1].toString());
                    preparedStatement.setString(4, recPengeluaran[2].toString());
                    preparedStatement.setInt(6, Integer.parseInt(recPengeluaran[4].toString()));
                    
                    
                    jumlahSimpan += preparedStatement.executeUpdate();
                } catch (SQLException ex){}
            }
            
            if (jumlahSimpan>0) {
                adaKesalahan = false;
            }
        } else {
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
                SQLStatemen = "select no_keluar, tgl_keluar from tbkeluar";
                preparedStatement = connection.prepareStatement(SQLStatemen);
                rset = preparedStatement.executeQuery();
                
                rset.next();
                rset.last();
                list = new Object[rset.getRow()][2];
                
                if (rset.getRow()>0){
                    rset.first();
                    int i=0;
                    do {
                        list[i] = new Object[]{rset.getString("no_keluar"), rset.getString("tgl_keluar")};
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
        
	this.no_keluar = kode;
	listPengeluaran = null;
	
	if ((connection = koneksi.getConnection()) != null){
            String SQLStatemen;
            PreparedStatement preparedStatement;
            ResultSet rset;
            
            try {
                SQLStatemen = "select * from tbkeluar where no_keluar=?"; 
                preparedStatement = connection.prepareStatement(SQLStatemen);
                preparedStatement.setString(1, kode);
                rset = preparedStatement.executeQuery();
                
                rset.next();
                rset.last();
                listPengeluaran = new Object[rset.getRow()][5];
                
                rset.first();
                int i=0;
                do {
                    if (!rset.getString("kd_barang").equals("")){
                        listPengeluaran[i] = new Object[]{ rset.getString("no_keluar"), rset.getString("kd_barang"), 
                            rset.getObject("tgl_produksi"), rset.getObject("kd_supplier"), rset.getObject("tgl_keluar"), rset.getObject("jumlah")}; 		    
                    }
                    i++;
                } while (rset.next());
                
                if (listPengeluaran.length > 0) {
                    adaKesalahan = false;
                }
                
                preparedStatement.close();
                rset.close();
                connection.close();
            } catch (SQLException ex){
                adaKesalahan = true;
                pesan = "Tidak dapat membaca data pengeluaran barang \n"+ex.getMessage();
            }
        } else {
            adaKesalahan = true;
            pesan = "Tidak dapat melakukan koneksi ke server\n"+koneksi.getPesanKesalahan();
        }
        
        return !adaKesalahan;
    }
    
}
