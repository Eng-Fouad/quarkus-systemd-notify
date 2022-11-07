package io.fouad.systemd;

import io.quarkus.logging.Log;

import java.util.Locale;

// See https://www.freedesktop.org/software/systemd/man/sd_notify.html#
public class SystemDNotifier {
    
    private static final boolean IS_LINUX = System.getProperty("os.name").toLowerCase(Locale.ENGLISH).contains("linux");
    private static boolean systemdLoaded = false;
    static {
        try {
            if (IS_LINUX) {
                System.loadLibrary("systemd-notify");
                systemdLoaded = true;
            }
        } catch (Throwable t) {
            Log.info("Failed to load native library \"systemd-notify\"", t);
        }
    }
    
    public static void notify(String state) {
        if (systemdLoaded) {
            sdNotify(state);
        }
    }
    
    private static native int sdNotify(String state);
}