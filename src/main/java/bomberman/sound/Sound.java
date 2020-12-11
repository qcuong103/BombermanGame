package bomberman.sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;

public class Sound {

    public static boolean stopSound = false;
    public static boolean isStopSound = false;

    public static void play(String soundName) {
        if (!stopSound) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        URL url = getClass().getResource("/sound/" + soundName + ".wav");
                        AudioInputStream ais = AudioSystem.getAudioInputStream(url);
                        Clip clip = AudioSystem.getClip();
                        clip.open(ais);
                        clip.start();
                        if (isStopSound) clip.stop();
                    } catch (Exception e) {
                        System.out.println("sound fall");
                    }
                }
            }).start();
        }
    }

    public static void play(String soundName, double volume) {
        if (!stopSound) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        URL url = getClass().getResource("/sound/" + soundName + ".wav");
                        AudioInputStream ais = AudioSystem.getAudioInputStream(url);
                        Clip clip = AudioSystem.getClip();
                        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                        float dB = (float) (Math.log(volume));
                        gainControl.setValue(dB);
                        clip.open(ais);
                        clip.start();
                        if (isStopSound) clip.stop();
                    } catch (Exception e) {
                        System.out.println("sound fall");
                    }
                }
            }).start();
        }
    }

}