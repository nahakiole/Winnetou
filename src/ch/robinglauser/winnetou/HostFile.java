package ch.robinglauser.winnetou;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Robin on 21.03.2015.
 */
public class HostFile {

    private ArrayList<HostEntry> hostEntries = new ArrayList<>();

    public HostFile(File file){
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();
            while (line != null) {
                Pattern pattern = Pattern.compile("((([0-9]+\\.){1,3}){3}[0-9]+) ([a-z\\.]+)");
                Matcher matcher = pattern.matcher(line);
                while(matcher.find()) {
                    hostEntries.add(new HostEntry(matcher.group(4),matcher.group(1)));
                }
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public ArrayList getData() {
        return hostEntries;
    }

    public void addHostEntry(String hostName, String IPadress) {
        hostEntries.add(new HostEntry(hostName, IPadress));
        System.out.println(hostEntries);
    }
}
