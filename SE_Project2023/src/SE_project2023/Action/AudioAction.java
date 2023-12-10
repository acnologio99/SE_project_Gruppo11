package SE_project2023.Action;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * La classe AudioAction implementa l'interfaccia Action e fornisce la funzionalità
 * per riprodurre un file audio. 
 * 
 * Questa classe è serializzabile per consentirne il salvataggio per un'altra sessione.
 * 
 * @author emanu
 */
public class AudioAction implements Action, Serializable {

    private String path;//e' il percorso del file audio da riprodurre
    private boolean isFired = false;//tiene traccia se l'azione è stata attivata
    transient private Clip clip;//oggetto Clip per la riproduzione dell'audio

    public AudioAction(String path) {
        this.path = path;
    }

    public AudioAction() {
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Clip getClip() {
        return clip;
    }
    

    @Override
    public boolean isFired() {
        return this.isFired;
    }

    //inizia la riproduzione del file audio, usando 'stopClip' per interrompere la riproduzione
    @Override
    public void fire() {
        AudioInputStream audioStream = null;
        // Crea un oggetto File con il percorso memorizzato nel campo path
        File audioFile = new File(this.path);
        System.out.println("music!");

        try {
            audioStream = AudioSystem.getAudioInputStream(audioFile); //si ottiene AudioInputStream dal file audio
            clip = AudioSystem.getClip();//clip viene inizializzato ed aperto con l'AudioInputStream
            clip.open(audioStream);
            
            clip.addLineListener(event -> {//si aggunge LineListener al Clip per gestire eventi come la fine della riproduzione
                if (event.getType() == LineEvent.Type.STOP) {
                    stopClip();
                }
            });
            clip.start();//avvia la riproduzione

            this.isFired = true;
        } catch (LineUnavailableException exc) {
            throw new RuntimeException("Sorry. Cannot play audio files.");
        } catch (UnsupportedAudioFileException exc) {
            throw new RuntimeException("Unsupported file format for: " + audioFile);
        } catch (FileNotFoundException exc) {
            throw new RuntimeException("File not found: " + audioFile);
        } catch (IOException exc) {
            throw new RuntimeException("IOException: " + exc);
        } finally {
            try {
                audioStream.close();
            } catch (IOException ex) {
                throw new RuntimeException("IOException: " + ex);
            } catch (NullPointerException ex) {
                throw new RuntimeException("NullPointerException: " + ex);
            }
        }
    }
    
    // Il metodo chiamato per interrompere la riproduzione e chiudere Clip.
    public void stopClip(){
        clip.stop();
        clip.close();
    }

    //I metodi 'add', 'remove' e 'getChild' non sono supportati e lanciano UnsupportedOperationException.
    @Override
    public void add() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Action getChild() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String toString() {
        return "Audio : " + this.path;
    }

}
