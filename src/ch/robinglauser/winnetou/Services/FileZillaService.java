package ch.robinglauser.winnetou.Services;

/**
 * Created by Robin on 22.03.2015.
 */
public class FileZillaService implements Service {
    private boolean isRunning;
    @Override
    public boolean isRunning() {
        return isRunning;
    }

    @Override
    public boolean start() {
        isRunning = !isRunning;
        return true;
    }

    @Override
    public boolean stop() {
        isRunning = !isRunning;
        return false;
    }

    @Override
    public String getName() {
        return "FileZilla";
    }
}
