package ch.robinglauser.winnetou;



/**
 * Created by Robin on 21.03.2015.
 */
public class HostEntry {
    private String hostname;
    private String ipAddress;

    public HostEntry(String hostname, String ipAddress){
        this.hostname = hostname;
        this.ipAddress = ipAddress;
    }


    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
