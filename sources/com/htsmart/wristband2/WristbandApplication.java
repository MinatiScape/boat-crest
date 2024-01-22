package com.htsmart.wristband2;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import com.htsmart.wristband2.a.e.d;
import com.htsmart.wristband2.utils.WristbandLog;
import com.polidea.rxandroidble2.LogOptions;
import com.polidea.rxandroidble2.RxBleClient;
import com.polidea.rxandroidble2.exceptions.BleException;
import com.polidea.rxandroidble2.internal.RxBleLog;
import io.reactivex.annotations.Beta;
import io.reactivex.exceptions.UndeliverableException;
import io.reactivex.functions.Consumer;
import io.reactivex.plugins.RxJavaPlugins;
/* loaded from: classes11.dex */
public class WristbandApplication extends Application {
    public static boolean ECG_PRODUCTION_TEST = false;
    public static boolean UPGRADE_PRODUCTION_TEST = false;
    @Beta
    public static boolean distributeConnectEventEvertTry = true;
    public static Application h = null;
    public static boolean i = false;
    public static RxBleClient j = null;
    public static WristbandManager k = null;
    public static b l = null;
    public static String m = null;
    public static boolean n = true;

    /* loaded from: classes11.dex */
    public static class a implements Consumer<Throwable> {
        @Override // io.reactivex.functions.Consumer
        /* renamed from: a */
        public void accept(Throwable th) throws Exception {
            if (!(th instanceof UndeliverableException) || !(th.getCause() instanceof BleException)) {
                throw new Exception(th);
            }
        }
    }

    /* loaded from: classes11.dex */
    public static class b implements Application.ActivityLifecycleCallbacks {
        public int h;

        public boolean a() {
            return this.h > 0;
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
            this.h++;
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
            this.h--;
        }
    }

    @Beta
    public static boolean betaIsRequestMtuEnabled() {
        return n;
    }

    @Beta
    public static void betaSetRequestMtuEnabled(boolean z) {
        n = z;
    }

    public static Context getContext() {
        Application application = h;
        if (application != null) {
            return application;
        }
        throw new IllegalStateException("Please init WristbandApplication at first");
    }

    public static RxBleClient getRxBleClient() {
        RxBleClient rxBleClient = j;
        if (rxBleClient != null) {
            return rxBleClient;
        }
        throw new IllegalStateException("Please init WristbandApplication at first");
    }

    public static String getTestDeviceNameRegex() {
        return m;
    }

    public static WristbandManager getWristbandManager() {
        WristbandManager wristbandManager = k;
        if (wristbandManager != null) {
            return wristbandManager;
        }
        throw new IllegalStateException("Please init WristbandApplication at first");
    }

    public static void init(Application application) {
        if (h != null) {
            return;
        }
        h = application;
        b bVar = new b();
        l = bVar;
        application.registerActivityLifecycleCallbacks(bVar);
        setDebugEnable(i);
        RxJavaPlugins.setErrorHandler(new a());
        j = RxBleClient.create(application);
        k = new d();
    }

    public static boolean isDebugEnable() {
        return i;
    }

    public static boolean isForeground() {
        if (h != null) {
            return l.a();
        }
        throw new IllegalStateException("Please init WristbandApplication at first");
    }

    public static void setDebugEnable(boolean z) {
        int i2;
        LogOptions.Builder uuidsLogSetting;
        Boolean bool;
        i = z;
        if (z) {
            i2 = 2;
            uuidsLogSetting = new LogOptions.Builder().setLogLevel(2).setMacAddressLogSetting(2).setUuidsLogSetting(2);
            bool = Boolean.TRUE;
        } else {
            i2 = Integer.MAX_VALUE;
            uuidsLogSetting = new LogOptions.Builder().setLogLevel(Integer.MAX_VALUE).setMacAddressLogSetting(Integer.MAX_VALUE).setUuidsLogSetting(Integer.MAX_VALUE);
            bool = Boolean.FALSE;
        }
        RxBleLog.updateLogOptions(uuidsLogSetting.setShouldLogAttributeValues(bool).build());
        WristbandLog.setLogLevel(i2);
    }

    public static void setTestDeviceNameRegex(String str) {
        m = str;
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        init(this);
    }
}
