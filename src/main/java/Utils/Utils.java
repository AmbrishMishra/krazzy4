package Utils;

import model.Screen;
import org.apache.commons.lang.StringUtils;
import sparkvideo.WowVideo;

import javax.ws.rs.core.Response;
import java.io.*;
import java.util.*;

/**
 * Created by yogeesh.rajendra on 2/17/17.
 */
public class Utils {

    public static List<String> getImage(List<String> htmlFiles, String campaignName) throws Exception {
        List<String> images = new ArrayList<>();

        String idS = campaignName;

        String[] dirs = {"/opt/spark/dump/" + idS, "/opt/spark/dump/" + idS + "/videos", "/opt/spark/dump/" + idS + "/audios",
                "/opt/spark/dump/" + idS + "/images"};

        for (String fileItem : dirs) {
            File dir = new File(fileItem);
            if (!dir.exists()) {
                dir.mkdir();
            }
        }

        int counter = 0;
        for (String file : htmlFiles) {

            String imagefile = "/opt/spark/dump/" + idS + "/images/" + "pic" + (counter++) + ".jpg";

            String command = "phantomjs /Users/yogeesh" +
                    ".rajendra/Documents/HUB/Personal/work/github/krazzy4/getscreenshot.js " + file + " " +
                    imagefile;


            Process p = Runtime.getRuntime().exec(command);
            p.waitFor();

            images.add(imagefile);
        }

        return images;

    }

    /**
     *
     * @param incomingData
     * @return
     * @throws IOException
     */
    public static Map<String, Object> getHtmlFiles(InputStream incomingData) throws IOException {

        Map<String,Object> data = new HashMap<>();

        StringBuilder input = new StringBuilder();
        String line = null;


        BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));
        while ((line = in.readLine()) != null) {
            input.append(line);
        }


        //If JSON is empty then throw bad request
        if (input.length() <= 0) {
            return null;
        }

        String []data1 = input.toString().split("\\$");

        List<String> htmlFiles = new ArrayList<>();

        int ctr = 0;
        String cmpName = "";
        String audioUrl = "";

        for ( String item : data1 ) {
            //Campaign
            if (ctr==0) {
                cmpName = item;
                data.put("campaign_name", cmpName);
                ++ctr;
                continue;
            } else if (ctr==1) {
                audioUrl  = item;
                data.put("audio_url", audioUrl);
                ++ctr;
                continue;
            }

<<<<<<< HEAD
            String files[] = item.split("^");
=======
            String files[] = item.split("\\^");
>>>>>>> 384d86c3ac5e1e21353765137d639590bc0a180b
            htmlFiles.add(files[0]);
            ++ctr;
            //TODO : Factor in image weight
        }


        data.put("htmlFiles", htmlFiles);

        return data;
    }


    /**
     *
     * @param incomingData
     * @return
     * @throws IOException
     */
    public static Map<String, Object> getHtmlFilesByString(String incomingData) throws IOException {
        Map<String,Object> data = new HashMap<>();

        String []data1 = incomingData.split("\\$");

        List<String> htmlFiles = new ArrayList<>();

        int ctr = 0;
        String cmpName = "";
        String audioUrl = "";

        for ( String item : data1 ) {
            //Campaign
            if (ctr==0) {
                cmpName = item;
                data.put("campaign_name", cmpName);
                ++ctr;
                continue;
            } else if (ctr==1) {
                audioUrl  = item;
                data.put("audio_url", audioUrl);
                ++ctr;
                continue;
            }

            String files[] = item.split("\\^");
            htmlFiles.add(files[0]);
            ++ctr;
            //TODO : Factor in image weight
        }


        data.put("htmlFiles", htmlFiles);

        return data;
    }

    /**
     *
     * @param incomingData
     * @return
     * @throws Exception
     */
    public static String getVideoPath(InputStream incomingData) throws  Exception{
        Map<String, Object> data = Utils.getHtmlFiles(incomingData);

        String campaignName = (String) data.get("campaign_name");

        System.out.println("Campaign Name : "+campaignName);

        String audioUrl = (String) data.get("audio_url");

        System.out.println("Audio Url : "+audioUrl);

        List<String> pngFiles = Utils.getImage((List<String>) data.get("htmlFiles"), campaignName);

        System.out.println("PNG Files : "+pngFiles);

        //screen create
        List<Screen> screens = new ArrayList<Screen>();

        for(String image: pngFiles) {
            screens.add(new Screen(image,0));
        }

        if (StringUtils.isBlank(audioUrl)) {
            audioUrl = "http://freedownloadmobileringtones.com/wp-content/uploads/2014/09/starsports.com-Football-season-2014-TV-ad-song.mp3";
        }

        String video = WowVideo.createWithOutWeight(screens, audioUrl, campaignName);

        return video;
    }

    /**
     *
     * @param incomingData
     * @return
     * @throws Exception
     */
    public static String getVideoPathByString(String incomingData) throws  Exception{

        Map<String, Object> data = Utils.getHtmlFilesByString(incomingData);

        String campaignName = (String) data.get("campaign_name");

        System.out.println("Campaign Name : "+campaignName);

        String audioUrl = (String) data.get("audio_url");

        System.out.println("Audio Url : "+audioUrl);

        List<String> pngFiles = Utils.getImage((List<String>) data.get("htmlFiles"), campaignName);

        System.out.println("PNG Files : "+pngFiles);

        //screen create
        List<Screen> screens = new ArrayList<Screen>();

        for(String image: pngFiles) {
            screens.add(new Screen(image,0));
        }

        if (StringUtils.isBlank(audioUrl)) {
            audioUrl = "http://freedownloadmobileringtones.com/wp-content/uploads/2014/09/starsports.com-Football-season-2014-TV-ad-song.mp3";
        }

        String video = WowVideo.createWithOutWeight(screens, audioUrl, campaignName);

        return video;
    }


    /**
     *
     * @param incomingData
     * @return
     * @throws Exception
     */
    public static String getString(InputStream incomingData) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));

        StringBuilder input = new StringBuilder();
        String line = null;

        while ((line = in.readLine()) != null) {
            input.append(line);
        }

        //If JSON is empty then throw bad request
        if (input.length() <= 0) {
            return null;
        }

        return input.toString();
    }

}
