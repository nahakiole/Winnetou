package ch.robinglauser.sac;

import java.io.File;

public class VirtualHost {
    private String hostName;
    private File location;

    public VirtualHost(String hostName, String location) {
        this.hostName = hostName;
        this.location = new File(location);
    }

    public String getLocation() {
        return location.getAbsolutePath();
    }

    public void setLocation(File location) {
        this.location = location;
    }

    public String getHostName() {
        return hostName;
    }
}
