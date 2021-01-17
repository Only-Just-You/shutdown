package org.example.shut;

import java.util.Timer;

public class CountTimeTool {
    public static void delaytime(long dt){
        long delay = 1000;
        Timer timer = new Timer();
        CutDownTool wl = new CutDownTool();
        timer.schedule(wl,delay * dt);
    }
}
