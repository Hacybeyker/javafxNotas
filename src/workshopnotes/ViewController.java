/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workshopnotes;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author Carlos Alfredo Osorio Perez <hacybeyker@hotmail.com>
 */
public class ViewController implements Initializable {
    
    @FXML private Button botonEliminar, botonAgregar, botonSync;
    @FXML private TextArea contenidoPricipal;
    @FXML private ListView listaNotas;
    
    private List<Note> notas;
    private List<String> resumenes_de_notas;
    private ListProperty<String> propiedades_resumen_de_notas;
    
    private Note nota_actual;
    
    private final String token = "S=s1:U=929d0:E=15ca9970494:C=15551e5d628:P=1cd:A=en-devtoken:V=2:H=6d42a1a08efc4110a7af36805788c3bd";

    private Evernote evernote;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        nota_actual = new Note();
        resumenes_de_notas = new ArrayList<>();
        notas = new ArrayList<>();
        propiedades_resumen_de_notas = new SimpleListProperty<>(FXCollections.observableArrayList(resumenes_de_notas));
        listaNotas.itemsProperty().bind(propiedades_resumen_de_notas);
        nuevaNota();
        
        contenidoPricipal.textProperty().addListener((ObservableValue<? extends String> ov, String oldValue, String newValue) ->{
            nota_actual.change(newValue);
        });
        
        this.botonSync.setOnAction(event -> createNote());
        initEvernote();
    }
    
    private void createNote(){
        try{
            this.evernote.createNote(this.nota_actual);
        }catch(Exception e){
            System.err.println("Erro:"+e.getMessage());
        }
    }
    
    private void create_content(String content){
        
    }
    
    public void initEvernote(){
        try{
            this.evernote = new Evernote(token);
        }catch(Exception e){
            
        }
    }
    
    public void nuevaNota(){
        nota_actual= new Note();
        notas.add(nota_actual);
        propiedades_resumen_de_notas.add(nota_actual.resumen());
    }
    
}
