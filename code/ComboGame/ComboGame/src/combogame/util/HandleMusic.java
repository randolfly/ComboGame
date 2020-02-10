/* File Info 
 * Author:      Randolf 
 * CreateTime:  2019/12/14 下午2:24:35 
 * LastEditor:  Randolf
 * ModifyTime:  2019/12/14 下午2:28:56
 * Description: 
*/
package combogame.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import combogame.MainApp;
import javazoom.jl.player.*;
import javazoom.jl.decoder.JavaLayerException;

public class HandleMusic {
    static AudioPlayer backgroundMusic;

    public static void loadMusics() throws FileNotFoundException {
        System.out.println("music started to load!");
        loadBackgroundMusic();
        System.out.println("music load finished!");
    }

    public static void loadBackgroundMusic() throws FileNotFoundException {
        String jarWholePath = MainApp.class.getProtectionDomain().getCodeSource().getLocation().getFile();
        try {
            jarWholePath = java.net.URLDecoder.decode(jarWholePath, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            System.out.println(e.toString());
        }
        String jarPath = new File(jarWholePath).getParentFile().getAbsolutePath();
        System.out.println(jarPath);
        backgroundMusic = new AudioPlayer(jarPath + "/music/background.mp3");
        // backgroundMusic = new AudioPlayer("../music/background.mp3");
        // System.out.println(MainApp.class.getResource("music/background.mp3").getPath());
    }

    public static void playBackgroundMusic() {
        try {
            if ((backgroundMusic == null) || !backgroundMusic.isAlive()) {
                String jarWholePath = MainApp.class.getProtectionDomain().getCodeSource().getLocation().getFile();
                try {
                    jarWholePath = java.net.URLDecoder.decode(jarWholePath, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    System.out.println(e.toString());
                }
                String jarPath = new File(jarWholePath).getParentFile().getAbsolutePath();
                System.out.println(jarPath);
                backgroundMusic = new AudioPlayer(jarPath + "/music/background.mp3");
                // backgroundMusic = new
                // AudioPlayer(MainApp.class.getResource("music").getPath() +
                // "/background.mp3");
                // backgroundMusic = new AudioPlayer("../music/background.mp3");

            }
            backgroundMusic.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void stopBackgroundMusic() {
        if (backgroundMusic.isAlive()) {
            backgroundMusic.stop();
        }
    }
}

class AudioPlayer extends Thread {
    Player mPlayer;
    File mFile;

    public AudioPlayer(File file) {
        this.mFile = file;
    }

    public AudioPlayer(String filePath) {
        try {
            this.mFile = new File(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        super.run();
        try {
            play();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public void play() throws FileNotFoundException, JavaLayerException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(mFile));
        mPlayer = new Player(bis);
        mPlayer.play();
        System.out.println("music played finished");
    }

}