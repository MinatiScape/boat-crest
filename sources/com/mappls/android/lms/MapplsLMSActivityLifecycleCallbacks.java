package com.mappls.android.lms;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import java.lang.ref.WeakReference;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes11.dex */
class MapplsLMSActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {
    public static final int CHECK_DELAY = 500;
    private static Double sStartSessionTime;
    private Runnable check;
    private final MapplsLMSConfig mConfig;
    private WeakReference<Activity> mCurrentActivity;
    private final MapplsLMSAPI mMpInstance;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private boolean mIsForeground = false;
    private boolean mPaused = true;

    public MapplsLMSActivityLifecycleCallbacks(MapplsLMSAPI mapplsLMSAPI, MapplsLMSConfig mapplsLMSConfig) {
        this.mMpInstance = mapplsLMSAPI;
        this.mConfig = mapplsLMSConfig;
        if (sStartSessionTime == null) {
            sStartSessionTime = Double.valueOf(System.currentTimeMillis());
        }
    }

    public boolean isInForeground() {
        return this.mIsForeground;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        this.mPaused = true;
        Runnable runnable = this.check;
        if (runnable != null) {
            this.mHandler.removeCallbacks(runnable);
        }
        this.mCurrentActivity = null;
        Handler handler = this.mHandler;
        Runnable runnable2 = new Runnable() { // from class: com.mappls.android.lms.MapplsLMSActivityLifecycleCallbacks.1
            @Override // java.lang.Runnable
            public void run() {
                if (MapplsLMSActivityLifecycleCallbacks.this.mIsForeground && MapplsLMSActivityLifecycleCallbacks.this.mPaused) {
                    MapplsLMSActivityLifecycleCallbacks.this.mIsForeground = false;
                    try {
                        double currentTimeMillis = System.currentTimeMillis() - MapplsLMSActivityLifecycleCallbacks.sStartSessionTime.doubleValue();
                        if (currentTimeMillis >= MapplsLMSActivityLifecycleCallbacks.this.mConfig.getMinimumSessionDuration() && currentTimeMillis < MapplsLMSActivityLifecycleCallbacks.this.mConfig.getSessionTimeoutDuration() && MapplsLMSActivityLifecycleCallbacks.this.mMpInstance.getTrackAutomaticEvents().booleanValue()) {
                            double round = Math.round((currentTimeMillis / 1000.0d) * 10.0d) / 10.0d;
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put(AutomaticEvents.SESSION_LENGTH, round);
                            MapplsLMSActivityLifecycleCallbacks.this.mMpInstance.getPeople().increment(AutomaticEvents.TOTAL_SESSIONS, 1.0d);
                            MapplsLMSActivityLifecycleCallbacks.this.mMpInstance.getPeople().increment(AutomaticEvents.TOTAL_SESSIONS_LENGTH, round);
                            MapplsLMSActivityLifecycleCallbacks.this.mMpInstance.track(AutomaticEvents.SESSION, jSONObject, true);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    MapplsLMSActivityLifecycleCallbacks.this.mMpInstance.onBackground();
                }
            }
        };
        this.check = runnable2;
        handler.postDelayed(runnable2, 500L);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        this.mCurrentActivity = new WeakReference<>(activity);
        this.mPaused = false;
        boolean z = !this.mIsForeground;
        this.mIsForeground = true;
        Runnable runnable = this.check;
        if (runnable != null) {
            this.mHandler.removeCallbacks(runnable);
        }
        if (z) {
            sStartSessionTime = Double.valueOf(System.currentTimeMillis());
            this.mMpInstance.onForeground();
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
    }
}
