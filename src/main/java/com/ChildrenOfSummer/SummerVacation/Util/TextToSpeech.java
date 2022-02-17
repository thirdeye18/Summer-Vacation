package com.ChildrenOfSummer.SummerVacation.Util;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;


public class TextToSpeech  {

       public static String talkNpc(String text){
           //System.setProperty("mbrola.base", "de.dfki.lt.freetts.mbrola");
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        //Voice voice = VoiceManager.getInstance().getVoice("mbrola_us3");
        Voice voice = VoiceManager.getInstance().getVoice("kevin16");

    if (voice != null) {
            voice.allocate();

        }
    try{
        voice.setRate(150);
        voice.setPitch(150);
        voice.setVolume(6);
        voice.speak(text);
    } catch (Exception e){
        e.printStackTrace();
    }
           return text;
       }
}