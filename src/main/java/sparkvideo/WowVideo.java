package sparkvideo;

import model.Screen;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by krishna.tiwari on 2/17/17.
 */
public class WowVideo {


    public static boolean create(String imageDirectory, String output) {
        try {

            String cmd = "ffmpeg -r 1/5 -f concat -safe 0 -i " + imageDirectory + " -c:v libx264 -vf fps=25,format=yuv420p " + output;
            System.out.println(cmd);
            Process p = Runtime.getRuntime().exec(cmd);
            p.waitFor();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean create(String imageDirectory, String mp3file, String output) {
        try {
            String cmd = "ffmpeg -r 1/5 -f concat -safe 0 -i " + imageDirectory + " -i " + mp3file + " -c:v libx264 -vf fps=25,format=yuv420p " + output;
            System.out.println(cmd);
            Process p = Runtime.getRuntime().exec(cmd);
            p.waitFor();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String createWithOutWeight(List<Screen> screens, String mp3file, String campaignname) {
        BufferedWriter bw = null;
        FileWriter fw = null;
        File imageFile = null;
        try {
            imageFile = new File("src/main/resources/" + campaignname + ".txt");
            fw = new FileWriter(imageFile);
            bw = new BufferedWriter(fw);
            for (Screen screen : screens) {
                String content = "file '" + screen.getImage() + "'";
                System.out.println(content);
                bw.write(content);
                bw.write("\n");
            }
            System.out.println("Done");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null)
                    bw.close();
                if (fw != null)
                    fw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        try {
            String output = "/opt/spark/dump/"+campaignname+"/videos/" + campaignname + ".mp4";
            String cmd = "ffmpeg -r 1/5 -f concat -safe 0 -i " + imageFile.getAbsolutePath() + " -i " + mp3file + " -c:v libx264 -vf fps=25,format=yuv420p " + output;
            System.out.println(cmd);
            Process p = Runtime.getRuntime().exec(cmd);
            p.waitFor();
            System.out.println("Done");
            return output;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean create(List<Screen> screens, String mp3file, String campaignname) {
        BufferedWriter bw = null;
        FileWriter fw = null;
        File imageFile = null;
        String zoonpan = "zoompan=d=25";
        try {
            imageFile = new File("src/main/resources/" + campaignname + ".txt");
            fw = new FileWriter(imageFile);
            bw = new BufferedWriter(fw);
            int index = 0;
            for (Screen screen : screens) {
                String content = "file '" + screen.getImage() + "'";
                zoonpan = zoonpan + " + '" + screen.getWeight() + "*eq(in," + index + ")'";
                bw.write(content);
                bw.write("\n");
            }
            System.out.println("Done " + zoonpan);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null)
                    bw.close();
                if (fw != null)
                    fw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        try {
            String output = "/opt/spark/dump/fk/video/" + campaignname + ".mp4";
            String cmd = "ffmpeg -r 1/5 -f concat -safe 0 -i " + imageFile.getAbsolutePath() + " -i " + mp3file + " -c:v libx264 -vf " + zoonpan + output;
            System.out.println(cmd);
            Process p = Runtime.getRuntime().exec(cmd);
            p.waitFor();
            System.out.println("Done");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        List<Screen> map = new ArrayList<Screen>();
        map.add(new Screen("/opt/spark/dump/test2/images/24526.jpg", 0));
        map.add(new Screen("/opt/spark/dump/test2/images/24526.jpg", 0));
        map.add(new Screen("/opt/spark/dump/test2/images/24526.jpg", 0));
        map.add(new Screen("/opt/spark/dump/test2/images/24526.jpg", 0));
        createWithOutWeight(map, "/opt/spark/dump/test2/audios/fk.mp3", "test2");
//        create("/Users/krishna.tiwari/Documents/workspace/krazzy4/krazzy4/src/main/resources/imagesfiles.txt", "/opt/spark/dump/fk/audio/fk.mp3", "/opt/spark/dump/fk/video/fkvideo2.mp4");
    }
}
