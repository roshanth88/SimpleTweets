/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpletweets;

import Utils.TweetFilesUtil;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class SimpleTweets {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        TweetFilesUtil fileUtil = new TweetFilesUtil();
        boolean quit = false;
        String tweetIndex = null;
        String tweetMsg = null;

        do {
            boolean choiceIsOK = false;
            do {
                System.out.println("Select the below options :");
                System.out.println("For new Tweet tag enter TAG");
                System.out.println("For new Tweet enter TWEET");
                System.out.println("To exist enter EXIT");
                String userinput = in.nextLine();
                String choice = userinput.toLowerCase();
                switch (choice) {
                    case "tag":
                        System.out.println("Please enter new tweet tag name");
                        userinput = in.nextLine();
                        try {
                            String fileName = userinput.toLowerCase();
                            boolean result = fileUtil.createFileInDefault(fileName);
                            if (result) {
                                System.out.println("Tweet tag " + fileName + " successfully created");
                                System.out.println();
                                System.out.println();
                            }
                        } catch (Exception ex) {
                            System.out.println("Exception when receving file name :" + ex.getMessage());
                        }
                        choiceIsOK = false;
                        break;
                    case "tweet":
                        choiceIsOK = false;
                        System.out.println("Select available tweet tags :");
                        List<String> tweetTags = fileUtil.listAllTags();
                        for (int i = 0; i < tweetTags.size(); i++) {
                            System.out.println(i + " - " + "#" + tweetTags.get(i).substring(0, tweetTags.get(i).lastIndexOf('.')));
                        }
                        System.out.println("Please select a valid tweet tag index ");
                        tweetIndex = in.nextLine();
                        if (fileUtil.isValidTweetTag(tweetIndex)) {
                            System.out.println("Please enter new tweet messge ");
                            tweetMsg = in.nextLine();
                            fileUtil.writeToTweetFile(Integer.parseInt(tweetIndex), tweetMsg);
                            System.out.println();
                            System.out.println();
                        } else {
                            System.out.println("Invalid selection");
                        }
                        break;
                    case "exit":
                    case "e":
                        choiceIsOK = true;
                        System.out.println("Quiting the program...");
                        quit = true;
                        break;
                    default:                        
                        choiceIsOK = false;
                        System.out.println("Invalid input, Type E or Exit to quit the program");
                        System.out.println();
                        System.out.println();
                        break;
                }
            } while (!choiceIsOK);
        } while (!quit);
    }

}
