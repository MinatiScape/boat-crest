package com.clevertap.android.sdk;
/* loaded from: classes2.dex */
public class Application extends android.app.Application {
    @Override // android.app.Application
    public void onCreate() {
        ActivityLifecycleCallback.register(this);
        super.onCreate();
    }
}
