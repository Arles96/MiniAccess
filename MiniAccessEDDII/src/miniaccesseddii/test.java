package miniaccesseddii;

import java.io.File;
import java.util.Arrays;

public class test {
    
    public static void main(String[] args){
        Util u = new Util(new File("test.txt"));
        /*u.deleteFile();
        
        u.addRegister(new Register());
        u.getRegister().get(0).addField(new Field(false, "Name", "AAAAA", "String"));
        u.getRegister().get(0).addField(new Field(false, "Last", "BBBBB", "String"));
        u.getRegister().get(0).addField(new Field(false, "Code", "12345", "int"));
        
        u.addRegister(new Register());
        u.getRegister().get(1).addField(new Field(false, "Name", "DDDDD", "double"));
        u.getRegister().get(1).addField(new Field(false, "Last", "TTTTT", "float"));
        u.getRegister().get(1).addField(new Field(false, "Code", "09876", "boolean"));
        
        u.saveFileTxt();*/
        
        u.loadFileTxt();
    }
}
