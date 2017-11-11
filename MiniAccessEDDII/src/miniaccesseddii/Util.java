/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniaccesseddii;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Arles Cerrato
 */
public class Util {

    //Atributes
    
    private ArrayList<Register> registers;
    private File file;
    private int bufferSize;
    private int selectedLine;

    //Constructors
    
    public Util() {
        registers = new ArrayList();
    }

    public ArrayList<Register> getRegister() {
        return registers;
    }

    public void setRegister(ArrayList<Register> register) {
        this.registers = register;
    }

    public void addRegister(Register nRegister) {
        registers.add(nRegister);
    }

    public void modifyRegister(Register modRegister, int i) {
        registers.set(i, modRegister);
    }

    public void removeRegister(int i) {
        registers.remove(i);
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void setFile(String path) {
        this.file = new File(path);
    }

    //Administration methods
    
    public Register getRegister(int i){
        return registers.get(i);
    }
    
    public void exportExcel() {

    }

    public void exportXml() {

    }

    public void saveFile() {
        if (file != null) {
            FileOutputStream fos = null;
            ObjectOutputStream bos = null;
            try {
                fos = new FileOutputStream(file);
                bos = new ObjectOutputStream(fos);
                for (Register register : registers) {
                    bos.writeObject(register);
                }
            } catch (Exception e) {
            } finally {
                try {
                    bos.close();
                    fos.close();
                } catch (Exception e) {
                }
            }
        }
    }

    public void loadFile() {
        try {
            this.registers = new ArrayList();
            Register temp;
            if (this.file.exists()) {
                FileInputStream entrada = new FileInputStream(file);
                ObjectInputStream objeto = new ObjectInputStream(entrada);
                try {//End of file Exception
                    while ((temp = (Register) (objeto.readObject())) != null) { //EOFException siempre va a ocurrir
                        registers.add(temp);
                    }
                } catch (EOFException e) {
                } finally {
                    objeto.close();
                    entrada.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void saveFileTxt() {
        if (file != null || !file.exists()) {
            try {
                RandomAccessFile raf = new RandomAccessFile(file, "w");
                raf.
                for (int i = 0; i < registers.size(); i++) {
                    
                }
            } catch (IOException e) {
                System.out.println("Hubo un error en la escritura.");
            }
        }
    }

    public void loadFileTxt() {
        FileReader fr;
        RandomAccessFile raf;
        registers = new ArrayList();
        try {
            fr = new FileReader(file);
            raf = new RandomAccessFile(file, "r");
            
            
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al abrir el archivo.");
        }
    }
}
