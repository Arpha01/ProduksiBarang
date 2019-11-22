/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import model.Barang;
import model.BarangKeluar;
import model.Supplier;
import view.FormLihatBarang;
import view.FormLihatBarangKeluar;
import view.FormLihatSupplier;
import view.FormUtama;

/**
 *
 * @author mucha
 */
public class PengeluaranController {
   private final Barang barang = new Barang();
   private final Supplier supplier = new Supplier();
   private final BarangKeluar barangKeluar = new BarangKeluar();
   private FormLihatBarangKeluar formLihatBarangKeluar;
   private FormLihatBarang formLihatBarang;
   private FormLihatSupplier formLihatSupplier;
   
   public void cariBarang(javax.swing.JTextField kd_barang){
       if(!kd_barang.getText().equals("")){
           if(barang.baca(kd_barang.getText())){
               FormUtama.formBarangKeluar.setNamaBarang(barang.getNama());
               FormUtama.formBarangKeluar.setJumlah(barang.getJumlah());
               FormUtama.formBarangKeluar.setHarga(barang.getHarga());
               FormUtama.formBarangKeluar.bersihkanTableBarangKeluar();
               
               
           }
       }
   }
   public String getTanggalSekarang(){
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime tgl = LocalDateTime.now();
        
        return df.format(tgl);
   }
   
   public void cetakLaporan(javax.swing.JTextField tglKeluarTextField, javax.swing.JTextField kdBarangTextField){
       if(!barangKeluar.cetakLaporan(tglKeluarTextField.getText(), kdBarangTextField.getText())){
           JOptionPane.showMessageDialog(null,barangKeluar.getPesan(),"Kesalahan",JOptionPane.ERROR_MESSAGE);
       }
   }
   
   public void tampilkanFormLihatBarang(){
       formLihatBarang = new FormLihatBarang(null, true);
       if(barang.bacaData()){
           formLihatBarang.tampilkanData(barang.getList());
           formLihatBarang.setVisible(true);
           
           if(!formLihatBarang.getKodeBarangDipilih().equals("")){
               if(barang.baca(formLihatBarang.getKodeBarangDipilih())){
                    FormUtama.formBarangKeluar.setKodeBarang(barang.getKode());
                    FormUtama.formBarangKeluar.setNamaBarang(barang.getNama());
                    FormUtama.formBarangKeluar.setJumlah(barang.getJumlah());
                    FormUtama.formBarangKeluar.setHarga(barang.getHarga());
               }
           }
       }
   }
    
   public void tampilkanFormLihatBarangKeluar(){
       formLihatBarangKeluar = new FormLihatBarangKeluar(null, true);
       if(barangKeluar.bacaData()){
           formLihatBarangKeluar.tampilkanData(barangKeluar.getList());
           formLihatBarangKeluar.setVisible(true);
           int jumlahBarangKeluar=0;
           if(!formLihatBarangKeluar.getBarangKeluarDipilih().equals("")){
               if(barangKeluar.baca(formLihatBarangKeluar.getBarangKeluarDipilih())){
                   FormUtama.formBarangKeluar.bersihkanTableBarangKeluar();
                   
                   Object[][] listBarangKeluar = barangKeluar.getListPengeluaran();
                   if(listBarangKeluar.length > 0){
                       FormUtama.formBarangKeluar.setTglKeluar(listBarangKeluar[0][4].toString());
                       for(int i=0; i<listBarangKeluar.length; i++){
                           if(!((String)listBarangKeluar[i][3]).equals("")){
                               String namaSupplier="";
                               if(supplier.baca((String)listBarangKeluar[i][3])){
                                   namaSupplier = supplier.getNama();
                               }
                               FormUtama.formBarangKeluar.setTambahBarangKeluar(new Object[]{listBarangKeluar[i][0], listBarangKeluar[i][2], listBarangKeluar[i][3], namaSupplier, listBarangKeluar[i][5]});
                               jumlahBarangKeluar++;
                           }
                       }
                       if(!listBarangKeluar[0][4].toString().equals(getTanggalSekarang())){
                           FormUtama.formBarangKeluar.setEnableButtonSimpan(false);
                       }else{
                           FormUtama.formBarangKeluar.setEnableButtonSimpan(true);
                       }
                       
                       if(barang.baca(listBarangKeluar[0][1].toString())){
                          
                             FormUtama.formBarangKeluar.setKodeBarang(barang.getKode());
                             FormUtama.formBarangKeluar.setNamaBarang(barang.getNama());
                             FormUtama.formBarangKeluar.setJumlah(barang.getJumlah());
                             FormUtama.formBarangKeluar.setHarga(barang.getHarga());
                       }
                    
                   }
               }
               if(jumlahBarangKeluar==0){
                   FormUtama.formBarangKeluar.setTambahBarangKeluar(new Object[]{});
               }
           }else{
               JOptionPane.showMessageDialog(null, barangKeluar.getPesan());
           }
       }
   }
   
   public void cariSupplier(String kodeSupplier){
       if(supplier.baca(kodeSupplier)){
           FormUtama.formBarangKeluar.setNamaSupplier(supplier.getNama());
       }else{
           FormUtama.formBarangKeluar.setNamaSupplier("");
           
           JOptionPane.showMessageDialog(null, supplier.getPesan(), "Error", JOptionPane.ERROR_MESSAGE);
       }
   }
   
   public void simpan(javax.swing.JTextField kodeBarangTextField, javax.swing.JTextField tglKeluarTextField, javax.swing.JTable pengeluaranTable){
       if(!kodeBarangTextField.getText().equals("")){
           barangKeluar.setKd_barang(kodeBarangTextField.getText());
           barangKeluar.setTgl_keluar(tglKeluarTextField.getText());
           
           Object[][] listPengeluaran = new Object[pengeluaranTable.getRowCount()][5];
           
           for(int i=0; i<pengeluaranTable.getRowCount(); i++){
               listPengeluaran[i][0] = pengeluaranTable.getValueAt(i, 0);
               listPengeluaran[i][1] = pengeluaranTable.getValueAt(i, 1);
               listPengeluaran[i][2] = pengeluaranTable.getValueAt(i, 2);
               listPengeluaran[i][3] = pengeluaranTable.getValueAt(i, 3);
               listPengeluaran[i][4] = pengeluaranTable.getValueAt(i, 4);
           }
           
           barangKeluar.setListPengeluaran(listPengeluaran);
           if(barangKeluar.simpan()){
                FormUtama.formBarangKeluar.setKodeBarang("");
                FormUtama.formBarangKeluar.setNamaBarang("");
                FormUtama.formBarangKeluar.setJumlah(0);
                FormUtama.formBarangKeluar.setHarga(0);
                FormUtama.formBarangKeluar.setTambahBarangKeluar(new Object[]{});
           }else{
               JOptionPane.showMessageDialog(null, barangKeluar.getPesan());
           }      
       }else{
           JOptionPane.showMessageDialog(null, "Kode pengeluaran tidak boleh koseong\n", "Kesalahan", JOptionPane.ERROR_MESSAGE);
       }
   }
   
}
