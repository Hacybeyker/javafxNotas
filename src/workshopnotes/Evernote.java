/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workshopnotes;

import com.evernote.auth.EvernoteAuth;
import com.evernote.auth.EvernoteService;
import com.evernote.clients.ClientFactory;
import com.evernote.clients.NoteStoreClient;
import com.evernote.clients.UserStoreClient;
import com.evernote.thrift.protocol.TBinaryProtocol;

/**
 *
 * @author Carlos Alfredo Osorio Perez <hacybeyker@hotmail.com>
 */
public class Evernote {
    
    private NoteStoreClient noteStore;
    
    public Evernote(String token) throws Exception{
        EvernoteAuth evernoteAuth = new EvernoteAuth(EvernoteService.SANDBOX,token);
        ClientFactory clientFactory = new ClientFactory(evernoteAuth);
        UserStoreClient userStoreClient = clientFactory.createUserStoreClient();
        
        boolean version_ok = userStoreClient.checkVersion("Evernote EDAMDemo (Java)", 
                com.evernote.edam.userstore.Constants.EDAM_VERSION_MAJOR, 
                com.evernote.edam.userstore.Constants.EDAM_VERSION_MINOR);
        if(!version_ok){
            System.err.println("No es posible concetarse con Evernote");
            System.exit(1);
        }
        
        System.out.println("Version correcta");
        this.noteStore = clientFactory.createNoteStoreClient();
        
    }
    
    public void createNote(Note current_note) throws Exception{
        com.evernote.edam.type.Note note = new com.evernote.edam.type.Note();
        note.setTitle("Nuevo Titulo");
        note.setContent("Contenido");
        this.noteStore.createNote(note);
    }
}
