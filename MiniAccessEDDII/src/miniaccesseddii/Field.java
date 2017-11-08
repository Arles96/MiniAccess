/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniaccesseddii;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Arles Cerrato
 */
public class Field implements Serializable {

    //Atributes
    private boolean key;
    private String name;
    private String content;
    private String type;

    //Constructors
    public Field(boolean key, String name, String content, String type) {
        this.key = key;
        this.name = name;
        this.content = content;
        this.type = type;
    }

    public Field(String name, String content, String type) {
        this.name = name;
        this.content = content;
        this.type = type;
    }

    public Field(String name, String content) {
        this.name = name;
        this.content = content;
    }

    //Getter and setter
    public boolean isKey() {
        return key;
    }

    public void setKey(boolean key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    //Administration Methods
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.key ? 1 : 0);
        hash = 29 * hash + Objects.hashCode(this.name);
        hash = 29 * hash + Objects.hashCode(this.content);
        hash = 29 * hash + Objects.hashCode(this.type);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Field other = (Field) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.content, other.content)) {
            return false;
        }
        return Objects.equals(this.type, other.type);
    }

    @Override
    public String toString() {
        return "Field{" + "key=" + key + ", name=" + name + ", content=" + content + ", type=" + type + '}';
    }

}
