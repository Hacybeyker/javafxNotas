/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workshopnotes;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

/**
 *
 * @author Carlos Alfredo Osorio Perez <hacybeyker@hotmail.com>
 */
public class Note {
    public SimpleStringProperty texto,resumen;
    public Note() {
        texto=new SimpleStringProperty("");
        resumen=new SimpleStringProperty("Nueva Nota");
        texto.addListener((ObservableValue<? extends String> ov, String oldValue, String newValue) ->{
            this.resumen.set(texto.get().substring(0,Math.min(texto.get().length(), 20)));
            System.out.println(this.resumen());
        });
    }
    
    public String get(){
        return texto.get();
    }
    
    public String resumen(){
        return resumen.get();
    }
    
    public void change(String texto){
        this.texto.set(texto);
    }
}
