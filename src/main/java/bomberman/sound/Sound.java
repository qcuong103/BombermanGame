package bomberman.sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {
    public Sound(String name) {

    }

    public static void play(String soundName) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = getClass().getResource("/sound/" + soundName + ".wav");
                    AudioInputStream ais = AudioSystem.getAudioInputStream(url);
                    Clip clip = AudioSystem.getClip();
                    clip.open(ais);
                    clip.start();
                } catch (Exception e) {
                    System.out.println("sound fall");
                }
            }
        }).start();
    }

    public static void stop(String soundName) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = getClass().getResource("/sound/" + soundName + ".wav");
                    AudioInputStream ais = AudioSystem.getAudioInputStream(url);
                    Clip clip = AudioSystem.getClip();
                    clip.open(ais);
                    clip.stop();
                } catch (Exception e) {
                    System.out.println("sound fall");
                }
            }
        }).start();
    }
}