package controller;

import Utils.Utils;
import model.Screen;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import sparkvideo.WowVideo;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by yogeesh.rajendra on 2/13/17.
 */
@Path("/")
public class APIEntry {

    static {
        File theDir = new File("/opt/spark");
        File theDir1 = new File("/opt/spark/dump");
        File videoOutDir = new File("/opt/spark/video");

        // if the directory does not exist, create it
        if (!theDir.exists()) {

            boolean result = false;

            try {
                theDir.mkdir();
                result = true;
            } catch (SecurityException se) {
                //handle it
            }

        }

        if (!theDir1.exists()) {

            boolean result = false;

            try {
                theDir1.mkdir();
                result = true;
            } catch (SecurityException se) {
                //handle it
            }
            if (result) {
                System.out.println("DIR created");
            }
        }

        // if the directory does not exist, create it
        if (!videoOutDir.exists()) {
            System.out.println("creating directory: /opt/spark/video");
            boolean result = false;

            try {
                videoOutDir.mkdir();
                result = true;
            } catch (SecurityException se) {
                //handle it
            }
            if (result) {
                System.out.println("DIR created " + videoOutDir);
            }
        }

    }

    public static Logger logger = Logger.getLogger(APIEntry.class.getName());


    @GET
    @Path("/getAPI")
    @Produces("text/plain")
    public Response getAPI() {
        try {
            System.out.println("API Get");
            return Response.status(200).entity("[ API Get SUCCESS ]").build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(503).entity("Internal server error").build();
        }
    }

//    @POST
//    @Path("/getImagesFiles")
//    @Produces("text/plain")
//    @Consumes("application/json")
//    public Response postAPI(InputStream incomingData) {
//        try {
//            List<String> htmlFileNames = Utils.getHtmlFiles(incomingData);
//            List<String> pngFiles = Utils.getImage(htmlFileNames);
//            return Response.status(200).entity(pngFiles.toString()).build();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Response.status(503).entity("Internal server error").build();
//        }
//    }

    @POST
    @Path("/getVideoFile")
    @Produces("text/plain")
    @Consumes("application/json")
    public Response getVideo(InputStream incomingData) {
        try {
            String video = Utils.getVideoPath(incomingData);
            return Response.status(200).entity(video).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(503).entity("Internal server error").build();
        }
    }

    @POST
    @Path("/getVideoFiles")
    @Produces("text/plain")
    @Consumes("application/json")
    public Response getVideos(InputStream incomingData) {
        try {
            String video = Utils.getString(incomingData);

            String videos[] = video.split("\\#");
            String video1 = videos[0];
            String video2 = videos[1];

            String video1Str = Utils.getVideoPathByString(video1);
            String video2Str = Utils.getVideoPathByString(video2);

            return Response.status(200).entity(video1Str+"$"+video2Str).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(503).entity("Internal server error").build();
        }
    }


}
