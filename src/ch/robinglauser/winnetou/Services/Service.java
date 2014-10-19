package ch.robinglauser.winnetou.Services;


public interface Service {

    abstract boolean isRunning();

    abstract boolean start();

    abstract boolean stop();

    abstract String getName();
}
