package ch.robinglauser.winnetou.Services;

/**
 * Created by robin on 19.10.14.
 */
public class MySQLService implements Service {
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
        return "MySQL";
    }
}
