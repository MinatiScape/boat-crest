package com.clevertap.android.sdk;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
/* loaded from: classes2.dex */
public final class ActivityLifecycleCallback {
    public static boolean registered = false;

    /* loaded from: classes2.dex */
    public class a implements Application.ActivityLifecycleCallbacks {
        public final /* synthetic */ String h;

        public a(String str) {
            this.h = str;
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
            String str = this.h;
            if (str != null) {
                CleverTapAPI.v(activity, str);
            } else {
                CleverTapAPI.u(activity);
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
            CleverTapAPI.onActivityPaused();
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
            String str = this.h;
            if (str != null) {
                CleverTapAPI.onActivityResumed(activity, str);
            } else {
                CleverTapAPI.onActivityResumed(activity);
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

    @TargetApi(14)
    public static synchronized void register(android.app.Application application, String str) {
        synchronized (ActivityLifecycleCallback.class) {
            if (application == null) {
                Logger.i("Application instance is null/system API is too old");
            } else if (registered) {
                Logger.v("Lifecycle callbacks have already been registered");
            } else {
                registered = true;
                application.registerActivityLifecycleCallbacks(new a(str));
                Logger.i("Activity Lifecycle Callback successfully registered");
            }
        }
    }

    @TargetApi(14)
    public static synchronized void register(android.app.Application application) {
        synchronized (ActivityLifecycleCallback.class) {
            register(application, null);
        }
    }
}
