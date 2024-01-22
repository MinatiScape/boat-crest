package com.mappls.android.lms;

import android.os.Process;
import com.mappls.android.lms.MapplsLMSAPI;
import java.lang.Thread;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes11.dex */
public class ExceptionHandler implements Thread.UncaughtExceptionHandler {
    private static final int SLEEP_TIMEOUT_MS = 400;
    private static ExceptionHandler sInstance;
    private final Thread.UncaughtExceptionHandler mDefaultExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();

    public static void init() {
        if (sInstance == null) {
            synchronized (ExceptionHandler.class) {
                if (sInstance == null) {
                    sInstance = new ExceptionHandler();
                }
            }
        }
    }

    private void killProcessAndExit() {
        try {
            Thread.sleep(400L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Process.killProcess(Process.myPid());
        System.exit(10);
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, final Throwable th) {
        MapplsLMSAPI.allInstances(new MapplsLMSAPI.InstanceProcessor() { // from class: com.mappls.android.lms.ExceptionHandler.1
            @Override // com.mappls.android.lms.MapplsLMSAPI.InstanceProcessor
            public void process(MapplsLMSAPI mapplsLMSAPI) {
                if (mapplsLMSAPI.getTrackAutomaticEvents().booleanValue()) {
                    try {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put(AutomaticEvents.APP_CRASHED_REASON, th.toString());
                        mapplsLMSAPI.track(AutomaticEvents.APP_CRASHED, jSONObject, true);
                    } catch (JSONException unused) {
                    }
                }
            }
        });
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.mDefaultExceptionHandler;
        if (uncaughtExceptionHandler != null) {
            uncaughtExceptionHandler.uncaughtException(thread, th);
        } else {
            killProcessAndExit();
        }
    }
}
