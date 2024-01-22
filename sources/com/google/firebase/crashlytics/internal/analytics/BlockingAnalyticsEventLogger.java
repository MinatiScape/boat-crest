package com.google.firebase.crashlytics.internal.analytics;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.firebase.crashlytics.internal.Logger;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
/* loaded from: classes10.dex */
public class BlockingAnalyticsEventLogger implements AnalyticsEventReceiver, AnalyticsEventLogger {

    /* renamed from: a  reason: collision with root package name */
    public final CrashlyticsOriginAnalyticsEventLogger f11127a;
    public final int b;
    public final TimeUnit c;
    public final Object d = new Object();
    public CountDownLatch e;

    public BlockingAnalyticsEventLogger(@NonNull CrashlyticsOriginAnalyticsEventLogger crashlyticsOriginAnalyticsEventLogger, int i, TimeUnit timeUnit) {
        this.f11127a = crashlyticsOriginAnalyticsEventLogger;
        this.b = i;
        this.c = timeUnit;
    }

    @Override // com.google.firebase.crashlytics.internal.analytics.AnalyticsEventLogger
    public void logEvent(@NonNull String str, @Nullable Bundle bundle) {
        synchronized (this.d) {
            Logger logger = Logger.getLogger();
            logger.v("Logging event " + str + " to Firebase Analytics with params " + bundle);
            this.e = new CountDownLatch(1);
            this.f11127a.logEvent(str, bundle);
            Logger.getLogger().v("Awaiting app exception callback from Analytics...");
            try {
                if (this.e.await(this.b, this.c)) {
                    Logger.getLogger().v("App exception callback received from Analytics listener.");
                } else {
                    Logger.getLogger().w("Timeout exceeded while awaiting app exception callback from Analytics listener.");
                }
            } catch (InterruptedException unused) {
                Logger.getLogger().e("Interrupted while awaiting app exception callback from Analytics listener.");
            }
            this.e = null;
        }
    }

    @Override // com.google.firebase.crashlytics.internal.analytics.AnalyticsEventReceiver
    public void onEvent(@NonNull String str, @NonNull Bundle bundle) {
        CountDownLatch countDownLatch = this.e;
        if (countDownLatch != null && "_ae".equals(str)) {
            countDownLatch.countDown();
        }
    }
}
