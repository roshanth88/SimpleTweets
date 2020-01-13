/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.io.*;
import java.util.*;

/**
 *
 * @author Admin
 */
public class TweetFilesUtil {

    List<String> textFiles;

    //System.out.println("current dir = " + dir);
    public List<String> listAllTags() {
        textFiles = new ArrayList<String>();
        File dir = new File(Constants.dir);
        for (File file : dir.listFiles()) {
            if (file.getName().endsWith((".txt"))) {
                textFiles.add(file.getName());
            }
        }
        return textFiles;
    }

    public boolean isValidTweetTag(String index) {
        try {
            int tweetIndex = Integer.parseInt(index);
            return textFiles.size() - 1 >= tweetIndex ? true : false;
        } catch (Exception ex) {
            //System.out.println(ex.getMessage());
        }
        return false;
    }

    public boolean writeToTweetFile(int tweetTagIndex, String tweet) {
        File dir = new File(Constants.dir);
        BufferedWriter writer = null;
        boolean isFilewriteSuccess = false;
        String tweetfileName = textFiles.get(tweetTagIndex);
        for (File file : dir.listFiles()) {
            if (file.getName().equalsIgnoreCase(tweetfileName)) {
                try {
                    writer = new BufferedWriter(new FileWriter(file,true));
                    writer.write(Constants.newLine+tweet);
                    isFilewriteSuccess = true;
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                } finally {
                    try {
                        writer.close();
                    } catch (Exception ex) {

                    }
                }
            }
        }
        return isFilewriteSuccess;
    }

    public boolean createFileInDefault(String fileName) {
        Writer writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(Constants.dir + "/" + fileName + ".txt"), "utf-8"));
            //writer.write("Something");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                writer.close();
            } catch (Exception ex) {

            }
        }
        return true;
    }
}
