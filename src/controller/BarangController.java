/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.swing.JOptionPane;
import model.Barang;
import view.FormLihatBarang;
import view.FormUtama;

/**
 *
 * @author mucha
 */
public class BarangController {
    private final Barang barang = new Barang();
    private FormLihatBarang formLihatBarang;
    
    public void simpan(javax.swing.JTextField kodeTextField, javax.swing.JTextField namaTextField, javax.swing.JTextField jenisTextField,
                       javax.swing.JComboBox satuanComboBox, javax.swing.JTextField jumlahTextField, javax.swing.JTextField hargaTextField){
        if(!kodeTextField.getText().equals("")){
            barang.setKode(kodeTextField.getText());
            barang.setNama(namaTextField.getText());
            barang.setJenis(jenisTextField.getText());
            barang.setSatuan(satuanComboBox.getSelectedItem().toString());
            barang.setJumlah(Integer.parseInt(jumlahTextField.getText()));
            barang.setHarga(Integer.parseInt(hargaTextField.getText()));
            
            if(barang.simpan()){
                FormUtama.formBarang.setKodeBarang("");
                FormUtama.formBarang.setNama("");
                FormUtama.formBarang.setJenis("");
                FormUtama.formBarang.setJumlah(0);
                FormUtama.formBarang.setHarga(0);
            }else{
                if(barang.getPesan().length() > 0){
                    JOptionPane.showMessageDialog(null, barang.getPesan(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "Kode barang tidak boleh kosong", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void cari(javax.swing.JTextField kodeBarangTextField){
        if(!kodeBarangTextField.getText().equals("")){
            if(barang.baca(kodeBarangTextField.getText())){
                FormUtama.formBarang.setNama(barang.getNama());
                FormUtama.formBarang.setJenis(barang.getJenis());
                FormUtama.formBarang.setJumlah(barang.getJumlah());
                FormUtama.formBarang.setHarga(barang.getHarga());
            }else{
                FormUtama.formBarang.setNama("");
                FormUtama.formBarang.setJenis("");
                FormUtama.formBarang.setJumlah(0);
                FormUtama.formBarang.setHarga(0);
            
                JOptionPane.showMessageDialog(null, barang.getPesan(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Kode jenis barang tidak boleh kosong", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void hapus(javax.swing.JTextField kodeTextField){
        if(!kodeTextField.getText().equals("")){
            if(barang.hapus(kodeTextField.getText())){
                FormUtama.formBarang.setKodeBarang("");
                FormUtama.formBarang.setNama("");
                FormUtama.formBarang.setJenis("");
                FormUtama.formBarang.setJumlah(0);
                FormUtama.formBarang.setHarga(0);
            }else{
               JOptionPane.showMessageDialog(null, barang.getPesan(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }else{
           JOptionPane.showMessageDialog(null, "Kode barang tidak boleh kosong", "Error", JOptionPane.ERROR_MESSAGE);

        }
    }
    
    public void tampilkanFormLihatBarang(){
        formLihatBarang = new FormLihatBarang(null, true);
        
        if(barang.bacaData()){
            formLihatBarang.tampilkanData(barang.getList());
            
            formLihatBarang.setVisible(true);
            if(!formLihatBarang.getKodeBarangDipilih().equals("")){
                FormUtama.formBarang.setKodeBarang(formLihatBarang.getKodeBarangDipilih());
                if(barang.baca(formLihatBarang.getKodeBarangDipilih())){
                    FormUtama.formBarang.setNama(barang.getNama());
                    FormUtama.formBarang.setJenis(barang.getJenis());
                    FormUtama.formBarang.setJumlah(barang.getJumlah());
                    FormUtama.formBarang.setHarga(barang.getHarga());
                }else{
                    FormUtama.formBarang.setNama("");
                    FormUtama.formBarang.setJenis("");
                    FormUtama.formBarang.setJumlah(0);
                    FormUtama.formBarang.setHarga(0);
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, barang.getPesan());
        }
    }
    
}
