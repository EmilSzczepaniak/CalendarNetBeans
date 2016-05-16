/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

/**
 *
 * @author Emil
 */
public class AlarmSound {
    private static Clip clip;
    private static AudioInputStream stream;

    private static void loadMusic(){
        if(clip != null) return;
        try {
            AudioFormat format;
            DataLine.Info info;
            stream = AudioSystem.getAudioInputStream(AlarmSound.class.getResource("alarm.WAV"));
            format = stream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(stream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void playMusic(boolean loop){
        loadMusic();
        if(clip == null) return;
        if(loop) clip.loop(Clip.LOOP_CONTINUOUSLY);
        else clip.start();
    }

    public static void stopMusic(){
        clip.stop();
        clip.setMicrosecondPosition(0);
    }
}
