package org.example.shut;

import java.io.IOException;
import java.util.TimerTask;

public class CutDownTool extends TimerTask {

    @Override
    public void run() {
        try {
            Runtime.getRuntime().exec("shutdown -s");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
