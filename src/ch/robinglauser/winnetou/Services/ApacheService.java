package ch.robinglauser.winnetou.Services;


public class ApacheService implements Service {
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
        return "Apache";
    }
}
