/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.swing.JOptionPane;
import model.Enkripsi;
import model.Petugas;
import view.FormLihatPetugas;
import view.FormUtama;

/**
 *
 * @author mucha
 */
public class PetugasController {
    private final Petugas petugas = new Petugas();
    private FormLihatPetugas formLihatPetugas;
    private final Enkripsi enkripsi = new Enkripsi();
    private boolean hashed = false;

    public void setHashed(boolean hashed) {
        this.hashed = hashed;
    }
    
    public void simpan(javax.swing.JTextField nipTextField, javax.swing.JTextField namaTextField,
                       javax.swing.JTextArea alamatTextField, javax.swing.JPasswordField passwordField){
        if(!nipTextField.getText().equals("")){
            petugas.setNip(nipTextField.getText());
            petugas.setNama(namaTextField.getText());
            petugas.setAlamat(alamatTextField.getText());
            
            if(hashed){
                petugas.setPassword(new String(passwordField.getPassword()));
            }else{
                try{
                    petugas.setPassword(enkripsi.hashMD5(new String(passwordField.getPassword())));
                }catch (Exception ex){
                    petugas.setPassword("");
                }
            }
            if (petugas.simpan()){
                FormUtama.formPetugas.setNip("");
                FormUtama.formPetugas.setNama("");
                FormUtama.formPetugas.setAlamat("");
                FormUtama.formPetugas.setPassword("");
            }  else {
                if (petugas.getPesan().length() > 0){
                    JOptionPane.showMessageDialog(null, petugas.getPesan(), "Kesalahan", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "NIP tidak boleh kosong", "Kesalahan", JOptionPane.ERROR_MESSAGE);
        }

    }
    
    public void hapus(javax.swing.JTextField nipTextField){
        if(!nipTextField.getText().equals("")){
            if(petugas.hapus(nipTextField.getText())){
                FormUtama.formPetugas.setNip("");
                FormUtama.formPetugas.setNama("");
                FormUtama.formPetugas.setAlamat("");
                FormUtama.formPetugas.setPassword("");
            }else{
                JOptionPane.showMessageDialog(null, petugas.getPesan(), "Kesalahan", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null, "NIP tidak boleh kosong", "Kesalahan", JOptionPane.ERROR_MESSAGE);

        }
    }
    
    public void cari(javax.swing.JTextField nipTextField){
        if(!nipTextField.getText().equals("")){
            if(petugas.baca(nipTextField.getText())){
                FormUtama.formPetugas.setNama(petugas.getNama());
                FormUtama.formPetugas.setAlamat(petugas.getAlamat());
                FormUtama.formPetugas.setPassword(petugas.getPassword());
                hashed = true;
            }else{
                FormUtama.formPetugas.setNip("");
                FormUtama.formPetugas.setNama("");
                FormUtama.formPetugas.setAlamat("");
                FormUtama.formPetugas.setPassword("");
                JOptionPane.showMessageDialog(null, petugas.getPesan(), "Kesalahan", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null, "NIP tidak boleh kosong", "Kesalahan", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void tampilkanFormPetugas(){
        formLihatPetugas = new FormLihatPetugas(null, true);
        
        if(petugas.bacaData()){
            formLihatPetugas.tampilkanData(petugas.getList());
            
            formLihatPetugas.setVisible(true);
            if(!formLihatPetugas.getNipDipilih().equals("")){
                FormUtama.formPetugas.setNip(formLihatPetugas.getNipDipilih());
                if(petugas.baca(formLihatPetugas.getNipDipilih())){
                    FormUtama.formPetugas.setNama(petugas.getNama());
                    FormUtama.formPetugas.setAlamat(petugas.getAlamat());
                    FormUtama.formPetugas.setPassword(petugas.getPassword());
                    hashed = true;
                }else{
                    FormUtama.formPetugas.setNip("");
                    FormUtama.formPetugas.setNama("");
                    FormUtama.formPetugas.setAlamat("");
                    FormUtama.formPetugas.setPassword("");
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, petugas.getPesan());

        }
    }
    
}
