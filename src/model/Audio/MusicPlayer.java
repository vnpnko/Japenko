package model.Audio;

import model.Game;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class MusicPlayer {
    private Clip titleMusicClip;
    private Clip battleMusicClip;

    public MusicPlayer() {
        if(Game.sound_on){
            try {
                File titleMusicFile = new File("assets/sound/title.wav");
                AudioInputStream titleMusicStream = AudioSystem.getAudioInputStream(titleMusicFile);
                titleMusicClip = AudioSystem.getClip();
                titleMusicClip.open(titleMusicStream);
                FloatControl titleMusicVolume = (FloatControl) titleMusicClip.getControl(FloatControl.Type.MASTER_GAIN);
                titleMusicVolume.setValue(-15.0f);

                File battleMusicFile = new File("assets/sound/battle.wav");
                AudioInputStream battleMusicStream = AudioSystem.getAudioInputStream(battleMusicFile);
                battleMusicClip = AudioSystem.getClip();
                battleMusicClip.open(battleMusicStream);
                FloatControl battleMusicVolume = (FloatControl) battleMusicClip.getControl(FloatControl.Type.MASTER_GAIN);
                battleMusicVolume.setValue(-15.0f);
            } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void titleMusicStart() {
        stopAll();
        titleMusicClip.setFramePosition(0);
        titleMusicClip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    protected void titleMusicStop() {
        if (titleMusicClip != null && titleMusicClip.isRunning()) {
            titleMusicClip.stop();
        }
    }

    public void battleMusicStart() {
        stopAll();
        battleMusicClip.setFramePosition(0);
        battleMusicClip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    protected void battleMusicStop() {
        if (battleMusicClip != null && battleMusicClip.isRunning()) {
            battleMusicClip.stop();
        }
    }

    protected void stopAll(){
        titleMusicStop();
        battleMusicStop();
    }

    public void muteMusic() {
        if (titleMusicClip != null) {
            FloatControl titleMusicVolume = (FloatControl) titleMusicClip.getControl(FloatControl.Type.MASTER_GAIN);
            titleMusicVolume.setValue(titleMusicVolume.getMinimum());
        }
        if (battleMusicClip != null) {
            FloatControl battleMusicVolume = (FloatControl) battleMusicClip.getControl(FloatControl.Type.MASTER_GAIN);
            battleMusicVolume.setValue(battleMusicVolume.getMinimum());
        }
    }
    public void unmuteMusic() {
        if (titleMusicClip != null) {
            FloatControl titleMusicVolume = (FloatControl) titleMusicClip.getControl(FloatControl.Type.MASTER_GAIN);
            titleMusicVolume.setValue(-15.0f); // This is the normal volume level for title music
        }
        if (battleMusicClip != null) {
            FloatControl battleMusicVolume = (FloatControl) battleMusicClip.getControl(FloatControl.Type.MASTER_GAIN);
            battleMusicVolume.setValue(-15.0f); // This is the normal volume level for battle music
        }
    }
}
