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
    MUSIC("summervacation.wav"),    // Background music
    MUSIC1("summer.wav");           // Alt background music

    // specifies available volumes
    public enum Volume {
        MUTE, LOW
    }

    public static Volume volume = Volume.LOW;  //set volume to low by default
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

    /*
     * This just plays the specified sound file once.
     */

    public void play() {
        if (volume != Volume.MUTE) {    // if mute is set don't play
            if (clip.isRunning())
                clip.stop();   // Stop clip if still running
            clip.setFramePosition(0); // rewind to the beginning
            clip.start();     // Start playing
        }
    }

    /*
     * This loops the sound file based on the int value passed.
     */

    public void loopPlay(int loops) {
        if (volume != Volume.MUTE) {    // if mute is set don't play
            if (clip.isRunning())
                clip.stop();   // Stop clip if still running
            clip.setFramePosition(0); // rewind to the beginning
            clip.loop(loops);     // Start playing
        }
    }

    public void stopPlay() {
        if (clip.isRunning()) {
            clip.stop();   // Stop clip if still running
            clip.setFramePosition(0);
        }
    }

    public static void setVolume(Volume volume) {
        SoundFX.volume = volume;
    }

    /*
     * Loads all the sound files into memory if desired to minimize initial load times.
     */

    public static void init() {
        values(); // calls the constructor for all the elements, preloading them into memory
    }

}
