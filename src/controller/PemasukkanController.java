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
import model.BarangMasuk;
import view.FormLihatBarang;
import view.FormLihatBarangMasuk;
import view.FormUtama;

/**
 *
 * @author mucha
 */
public class PemasukkanController {
    private FormLihatBarangMasuk formLihatBarangMasuk;
    private FormLihatBarang formLihatBarang;
    private final BarangMasuk barangMasuk = new BarangMasuk();
    private final Barang barang = new Barang();
    
    public void tampilkanFormLihatBarangMasuk(){
        formLihatBarangMasuk = new FormLihatBarangMasuk(null, true);
        
        if(barangMasuk.bacaData()){
            formLihatBarangMasuk.tampilkanData(barangMasuk.getList());
            formLihatBarangMasuk.setVisible(true);
            if(!formLihatBarangMasuk.getBarangMasukDipilih().equals("")){
                FormUtama.formBarangMasuk.setNo_masuk(formLihatBarangMasuk.getBarangMasukDipilih());
                if(barangMasuk.baca(formLihatBarangMasuk.getBarangMasukDipilih())){
                    FormUtama.formBarangMasuk.setNo_barang(barangMasuk.getKd_barang());
                    FormUtama.formBarangMasuk.setTgl_produksi(barangMasuk.getTgl_produksi());
                }else{
                    FormUtama.formBarangMasuk.setNo_barang("");
                    FormUtama.formBarangMasuk.setTgl_produksi("");
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, barangMasuk.getPesan());
        }
    }
    
    public void tampilkanFormLihatBarang(){
        formLihatBarang = new FormLihatBarang(null, true);
        
        if(barang.bacaData()){
            formLihatBarang.tampilkanData(barang.getList());
            formLihatBarang.setVisible(true);
            if(!formLihatBarang.getKodeBarangDipilih().equals("")){
                FormUtama.formBarangMasuk.setNo_barang(formLihatBarang.getKodeBarangDipilih());
                if(barang.baca(formLihatBarang.getKodeBarangDipilih())){
                    FormUtama.formBarangMasuk.setNama(barang.getNama());
                }else{
                    FormUtama.formBarangMasuk.setNama("");
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, barang.getPesan());
        }
    }
    
    public void simpan(javax.swing.JTextField no_masukTextField, javax.swing.JTextField no_barangTextField, 
                       javax.swing.JTextField tglProduksiTextField){
        if(!no_masukTextField.getText().equals("")){
            barangMasuk.setNo_masuk(no_masukTextField.getText());
            barangMasuk.setKd_barang(no_barangTextField.getText());
            barangMasuk.setTgl_produksi(tglProduksiTextField.getText());
            
            if(barangMasuk.simpan()){
                FormUtama.formBarangMasuk.setNo_masuk("");
                FormUtama.formBarangMasuk.setNo_barang("");
                FormUtama.formBarangMasuk.setNama("");
                FormUtama.formBarangMasuk.setTgl_produksi("");
            }else{
                if(barangMasuk.getPesan().length() > 0){
                    JOptionPane.showMessageDialog(null, barangMasuk.getPesan(), "Kesalahan", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "Kode jenis barang tidak boleh kosong", "Kesalahan", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void cetakLaporan(javax.swing.JTextField tglProduksiTextField){
        if(!barangMasuk.cetakLaporan(tglProduksiTextField.getText())){
            JOptionPane.showMessageDialog(null,barangMasuk.getPesan(),"Kesalahan",JOptionPane.ERROR_MESSAGE);
        }
    }
    public void hapus(javax.swing.JTextField no_masukTextField){
          if(!no_masukTextField.getText().equals("")){
            if(barangMasuk.hapus(no_masukTextField.getText())){
                FormUtama.formBarangMasuk.setNo_masuk("");
                FormUtama.formBarangMasuk.setNo_barang("");
                FormUtama.formBarangMasuk.setNama("");
                FormUtama.formBarangMasuk.setTgl_produksi("");
            }else{
                JOptionPane.showMessageDialog(null, barangMasuk.getPesan(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null, "No masuk tidak boleh kosong", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void cari(javax.swing.JTextField no_masukTextField){
         if(!no_masukTextField.getText().equals("")){
            if(barangMasuk.baca(no_masukTextField.getText())){
                FormUtama.formBarangMasuk.setNo_barang(barangMasuk.getKd_barang());
                FormUtama.formBarangMasuk.setTgl_produksi(barangMasuk.getTgl_produksi());
            }else{
                FormUtama.formBarangMasuk.setNo_barang("");
                FormUtama.formBarangMasuk.setTgl_produksi("");
            
                JOptionPane.showMessageDialog(null, barangMasuk.getPesan(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Kode jenis barang tidak boleh kosong", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void cariBarang(javax.swing.JTextField no_barangTextField){
        if(!no_barangTextField.getText().equals("")){
            if(barang.baca( no_barangTextField.getText())){
                FormUtama.formBarangMasuk.setNama(barang.getNama());
            }else{
                FormUtama.formBarangMasuk.setNama(barang.getNama());
                JOptionPane.showMessageDialog(null, barang.getPesan(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Kode jenis barang tidak boleh kosong", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public String getTanggalSekarang(){
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime tgl = LocalDateTime.now();
        
        return df.format(tgl);
    }
}
