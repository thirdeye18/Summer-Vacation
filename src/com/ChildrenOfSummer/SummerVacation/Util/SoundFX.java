package com.ChildrenOfSummer.SummerVacation.Util;

/*
 * Enum class to handle playing sound for the game. Class will preload all the sound files using init().
 */

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public enum SoundFX {   // list all the sound types here
    MUSIC1("summer.wav");   // main music

    // specifies available volumes, defaults to MUTE
    public enum Volume {
        MUTE, LOW
    }

    public static Volume volume = Volume.MUTE;  //set volume to mute by default
    public Clip clip;  // clip is the object holding the sound file

    //constructor handles instantiating the sound clips
    SoundFX(String filename) {
        String filePath = "./Assets/Sound/" + filename;
        try {
            if (Files.exists(Path.of(filePath))) {
                File file = new File(filePath); // read the file from disk
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);  // file to audio stream
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("There was a problem loading the audio file");
            e.printStackTrace();
        }
    }

    public void play() {
        if (volume != Volume.MUTE) {    // if mute is set don't play
            if (clip.isRunning())
                clip.stop();   // Stop clip if still running
            clip.setFramePosition(0); // rewind to the beginning
            clip.start();     // Start playing
        }
    }

    public void loopPlay(int loops) {
        if (volume != Volume.MUTE) {    // if mute is set don't play
            if (clip.isRunning())
                clip.stop();   // Stop clip if still running
            clip.setFramePosition(0); // rewind to the beginning
            clip.loop(loops);     // Start playing
        }
    }

    public void stopPlay() {
        if (clip.isRunning())
            clip.stop();   // Stop clip if still running
    }

    public static void setVolume(Volume volume) {
        SoundFX.volume = volume;
    }

    // optional to prevent long load times preload sound files
    static void init() {
        values(); // calls the constructor for all the elements, preloading them into memory
    }

}
