package ehu.isad.utils;

import com.google.gson.Gson;
import ehu.isad.model.CMSModel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Properties;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;


public class Utils {

    public static Properties getProperties()  {
        Properties properties = null;

        try (InputStream in = Utils.class.getResourceAsStream("/setup.properties")) {
            properties = new Properties();
            properties.load(in);

        } catch (
                IOException e) {
            e.printStackTrace();
        }

        return properties;
    }

    private CMSModel readFromUrl(String value) throws IOException {
        // get JSON
        URL api = new URL("website.com?id="+value+"");
        URLConnection yc = api.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
        String inputLine = in.readLine(); // raw data
        in.close();
        // process if needed
        Gson gson = new Gson();
        return gson.fromJson(inputLine, CMSModel.class);
    }

    public static void getREADME(String url){
        //Emandako izena duen irudia zerbitzarira eskatzen da, eta sisteman gordetzen da
        try (BufferedInputStream inputStream = new BufferedInputStream(new URL(url +"/README").openStream());
             FileOutputStream fileOS = new FileOutputStream(Utils.getProperties().getProperty("pathtoimages")+"file.txt")) {
            byte data[] = new byte[1024];
            int byteContent;
            while ((byteContent = inputStream.read(data, 0, 1024)) != -1) {
                fileOS.write(data, 0, byteContent);
            }
        } catch (IOException e) {
            // handles IO exceptions
        }

    }

    public static String getmd5(String url) throws NoSuchAlgorithmException, FileNotFoundException, IOException {

        URL urla = new URL(url +"/README");
        InputStream is = urla.openStream();
        MessageDigest md = MessageDigest.getInstance("MD5");
        String digest = getDigest(is, md, 2048);

        System.out.println("MD5 Digest:" + digest);
        return digest;
    }

    public static String getDigest(InputStream is, MessageDigest md, int byteArraySize)
            throws NoSuchAlgorithmException, IOException {

        md.reset();
        byte[] bytes = new byte[byteArraySize];
        int numBytes;
        while ((numBytes = is.read(bytes)) != -1) {
            md.update(bytes, 0, numBytes);
        }
        byte[] digest = md.digest();
        String result = new String(Hex.encodeHex(digest));
        return result;
    }

}