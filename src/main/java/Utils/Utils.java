package Utils;

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

            String files[] = item.split("^");
            htmlFiles.add(files[0]);
            ++ctr;
            //TODO : Factor in image weight
        }


        data.put("htmlFiles", htmlFiles);

        return data;
    }
}
