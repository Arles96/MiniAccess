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
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;
import sun.rmi.log.ReliableLog;

/**
 *
 * @author Arles Cerrato
 */
public class Util {

    //Atributes
    
    private ArrayList<Register> registers;
    private File file;
    private Register model;
    private int bufferSize;
    private int selectedLine;

    //Constructors
    
    public Util() {
        registers = new ArrayList();
    }

    public Util(File file) {
        registers = new ArrayList();
        this.file = file;
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

    public ArrayList<Register> getRegisters() {
        return registers;
    }

    public void setRegisters(ArrayList<Register> registers) {
        this.registers = registers;
    }

    public Register getModel() {
        return model;
    }

    public void setModel(Register model) {
        this.model = model;
    }

    public int getBufferSize() {
        return bufferSize;
    }

    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    public int getSelectedLine() {
        return selectedLine;
    }

    public void setSelectedLine(int selectedLine) {
        this.selectedLine = selectedLine;
    }
   
    //Administration methods
    
    public Register getRegister(int i){
        return registers.get(i);
    }
    
    public void exportExcel() {

    }

    public void exportXml() {

    }
    
    public void deleteFile(){
        file.delete();
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
        if (file != null || !file.exists() || !registers.isEmpty()) {
            try {
                RandomAccessFile raf = new RandomAccessFile(file, "rw");
                //Writing metadata
                
                String metadata = "";
                for (int i = 0; i < registers.get(0).getFields().size(); i++) {
                    Field  f = registers.get(0).getFields().get(i);
                    metadata += f.getName();
                    switch(f.getType().toLowerCase()){
                        case "int":{
                            metadata += "1";
                            break;
                        }
                        
                        case "string":{
                            metadata += "2";
                            break;
                        }
                        
                        case "double":{
                            metadata += "3";
                            break;
                        }
                        
                        case "float":{
                            metadata += "4";
                            break;
                        }
                        
                        case "byte":{
                            metadata += "5";
                            break;
                        }
                        
                        case "boolean":{
                            metadata += "6";
                            break;
                        }
                    }
                    metadata += "|";
                }
                raf.writeBytes(metadata + "\r\n");
                //System.out.println("Metadata:" + metadata);
                
                //Writing registers
                for (int i = 0; i < registers.size(); i++) {
                    raf.write('#');
                    String register = "";
                    for (int j = 0; j < registers.get(0).getFields().size(); j++) {
                        register += registers.get(i).getFields().get(j).getContent() + "|";
                    }
                    //System.out.println("register: " + register);
                    raf.writeBytes(register);
                }
                
                raf.close();
            } catch (IOException e) {
                System.out.println("Hubo un error en la escritura.");
            }
        }
    }

    
    public String[] split(String s, char delim){
        int c = 0;
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == delim){
                c++;
            }
        }
        
        String[] parts = new String[c];
        int cParts = 0;
        String tempS = "";
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) != delim)
                tempS += s.charAt(i);
            else{
                parts[cParts] = tempS;
                tempS = "";
                cParts++;
            }
        }
        
        return parts;
    }
    
    public void loadFileTxt() {
        RandomAccessFile raf;
        registers = new ArrayList();
        try {
            raf = new RandomAccessFile(file, "r");
            
            //Loading metadata
            ArrayList<Field> fields = new ArrayList();
            String metadata[] = split(raf.readLine(), '|');
            System.out.println(Arrays.toString(metadata));
            for (int i = 0; i < metadata.length; i++) {
                String type = metadata[i].substring(metadata[i].length() - 1);
                switch(type){
                    case "1":{
                        type = "int";
                        break;
                    }
                    
                    case "2":{
                        type = "string";
                        break;
                    }
                    case "3":{
                        type = "double";
                        break;
                    }
                    
                    case "4":{
                        type = "float";
                        break;
                    }
                    
                    case "5":{
                        type = "byte";
                        break;
                    }
                    
                    case "6":{
                        type = "boolean";
                        break;
                    }
                }
                
                fields.add(new Field(metadata[i].substring(0, metadata[i].length() - 1), type));
            }
            
            //System.out.println(fields);
            
            //Loading registers
            String regs[] = raf.readLine().split("#");
            for (int i = 0; i < regs.length; i++) {
                String fieldsC[] = split(regs[i], '|');
                registers.add(new Register());
                Register lastReg = registers.get(registers.size() - 1);
                for (int j = 0; j < fieldsC.length; j++) {
                    Field fN = fields.get(i);
                    lastReg.addField(new Field(fN.isKey(), fN.getName(), fieldsC[j], fN.getType()));
                }
                //System.out.println(lastReg);
            }
            raf.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al abrir el archivo.");
        }
    }
}
