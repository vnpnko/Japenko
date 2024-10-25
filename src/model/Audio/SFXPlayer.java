package model.Audio;

import model.Game;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SFXPlayer {
    public void play(String filePath) {
        if(Game.sound_on){
            Clip clip;
            try {
                File file = new File(filePath);
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);

                clip.start();
            }  catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
