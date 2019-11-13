/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.swing.JOptionPane;
import model.JenisBarang;
import view.FormLihatJenisBarang;
import view.FormUtama;

/**
 *
 * @author mucha
 */
public class JenisBarangController {
    private FormLihatJenisBarang formLihatJenisBarang;
    private final JenisBarang jenisBarang = new JenisBarang();
    
    public void tampilkanFormLihatJenisBarang(){
        formLihatJenisBarang = new FormLihatJenisBarang(null, true);
        
        if(jenisBarang.bacaData()){
            formLihatJenisBarang.tampilkanData(jenisBarang.getList());
            
            formLihatJenisBarang.setVisible(true);
             if (!formLihatJenisBarang.getKodeJenisBarangDipilih().equals("")){
                FormUtama.formJenisBarang.setKode(formLihatJenisBarang.getKodeJenisBarangDipilih());
                if (jenisBarang.baca(formLihatJenisBarang.getKodeJenisBarangDipilih())){
                    FormUtama.formJenisBarang.setNama(jenisBarang.getNama());
                } else {
                    FormUtama.formJenisBarang.setNama("");
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, jenisBarang.getPesan());
        }
    }
    
    public void tampilkanFormLihatJenisBarangUntukFormBarang(){
         formLihatJenisBarang = new FormLihatJenisBarang(null, true);
        
        if(jenisBarang.bacaData()){
            formLihatJenisBarang.tampilkanData(jenisBarang.getList());
            
            formLihatJenisBarang.setVisible(true);
             if (!formLihatJenisBarang.getKodeJenisBarangDipilih().equals("")){
                FormUtama.formBarang.setJenis(formLihatJenisBarang.getKodeJenisBarangDipilih());
            }
        }else{
            JOptionPane.showMessageDialog(null, jenisBarang.getPesan());
        }
        
    }
    public void simpan(javax.swing.JTextField kodeJenisBarangTextField, javax.swing.JTextField namaTextField){
        if(!kodeJenisBarangTextField.getText().equals("")){
            jenisBarang.setKode(kodeJenisBarangTextField.getText());
            jenisBarang.setNama(namaTextField.getText());
            
            if(jenisBarang.simpan()){
                FormUtama.formJenisBarang.setKode("");
                FormUtama.formJenisBarang.setNama("");
            }else{
                if(jenisBarang.getPesan().length() > 0){
                   JOptionPane.showMessageDialog(null, jenisBarang.getPesan(), "Kesalahan", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "Kode jenis barang tidak boleh kosong", "Kesalahan", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void hapus(javax.swing.JTextField kodeJenisBarangTextField){
        if(!kodeJenisBarangTextField.getText().equals("")){
            if(jenisBarang.hapus(kodeJenisBarangTextField.getText())){
                FormUtama.formJenisBarang.setKode("");
                FormUtama.formJenisBarang.setNama("");
            }else{
                JOptionPane.showMessageDialog(null, jenisBarang.getPesan(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Kode Jenis barang tidak boleh kosong", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void cari(javax.swing.JTextField kodeJenisBarangTextField){
        if(!kodeJenisBarangTextField.getText().equals("")){
            if(jenisBarang.baca(kodeJenisBarangTextField.getText())){
                FormUtama.formJenisBarang.setNama(jenisBarang.getNama());
            }else{
                FormUtama.formJenisBarang.setNama("");
            
                JOptionPane.showMessageDialog(null, jenisBarang.getPesan(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Kode jenis barang tidak boleh kosong", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
