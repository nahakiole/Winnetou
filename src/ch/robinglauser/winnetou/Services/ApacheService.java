package ch.robinglauser.winnetou.Services;


public class ApacheService implements Service {
    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public boolean start() {
        return false;
    }

    @Override
    public boolean stop() {
        return false;
    }

    @Override
    public String getName() {
        return "Apache";
    }
}
