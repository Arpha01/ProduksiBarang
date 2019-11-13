/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.swing.JOptionPane;
import model.Supplier;
import view.FormLihatSupplier;
import view.FormUtama;
/**
 *
 * @author mucha
 */
public class SupplierController {
    private FormLihatSupplier formLihatSupplier;
    private final Supplier supplier = new Supplier();
    
    public void tampilkanFormLihatSupplier(){
        formLihatSupplier = new FormLihatSupplier(null, true);
        
        if(supplier.bacaData()){
            formLihatSupplier.tampilkanData(supplier.getList());
            
            formLihatSupplier.setVisible(true);
             if (!formLihatSupplier.getKodeSupplierDipilih().equals("")){
                FormUtama.formSupplier.setKode(formLihatSupplier.getKodeSupplierDipilih());
                if (supplier.baca(formLihatSupplier.getKodeSupplierDipilih())){
                    FormUtama.formSupplier.setNama(supplier.getNama());
                    FormUtama.formSupplier.setAlamat(supplier.getAlamat());
                    FormUtama.formSupplier.setNoTelp(supplier.getNo_telp());
                } else {
                    FormUtama.formSupplier.setNama("");
                    FormUtama.formSupplier.setAlamat("");
                    FormUtama.formSupplier.setNoTelp("");
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, supplier.getPesan());
        }
    }
    
    public void simpan(javax.swing.JTextField kodeSupplierTextField, javax.swing.JTextField namaTextField, javax.swing.JTextArea
                       alamatRichText, javax.swing.JTextField noTelpTextField){
        if(!kodeSupplierTextField.getText().equals("")){
            supplier.setKode(kodeSupplierTextField.getText());
            supplier.setNama(namaTextField.getText());
            supplier.setAlamat(alamatRichText.getText());
            supplier.setNo_telp(noTelpTextField.getText());
            
            if(supplier.simpan()){
                FormUtama.formSupplier.setKode("");
                FormUtama.formSupplier.setNama("");
                FormUtama.formSupplier.setAlamat("");
                FormUtama.formSupplier.setNoTelp("");
            }else{
                if(supplier.getPesan().length() > 0){
                   JOptionPane.showMessageDialog(null, supplier.getPesan(), "Kesalahan", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "Kode supplier tidak boleh kosong", "Kesalahan", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void hapus(javax.swing.JTextField kodeSupplierTextField){
        if(!kodeSupplierTextField.getText().equals("")){
            if(supplier.hapus(kodeSupplierTextField.getText())){
                FormUtama.formSupplier.setKode("");
                FormUtama.formSupplier.setNama("");
                FormUtama.formSupplier.setAlamat("");
                FormUtama.formSupplier.setNoTelp("");
            }else{
                JOptionPane.showMessageDialog(null, supplier.getPesan(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Kode supplier kosong", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void cari(javax.swing.JTextField kodeSupplierTextField){
        if(!kodeSupplierTextField.getText().equals("")){
            if(supplier.baca(kodeSupplierTextField.getText())){
                FormUtama.formSupplier.setNama(supplier.getNama());
                FormUtama.formSupplier.setAlamat(supplier.getAlamat());
                FormUtama.formSupplier.setNoTelp(supplier.getNo_telp());
            }else{
                FormUtama.formSupplier.setKode("");
                FormUtama.formSupplier.setNama("");
                FormUtama.formSupplier.setAlamat("");
                FormUtama.formSupplier.setNoTelp("");
            
                JOptionPane.showMessageDialog(null, supplier.getPesan(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Kode supplier tidak boleh kosong", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
