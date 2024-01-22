package com.coveiot.repository.datasync.domainlogic;

import android.content.Context;
import android.content.SharedPreferences;
/* loaded from: classes9.dex */
public class SyncSessionManager {
    public static SyncSessionManager c;

    /* renamed from: a  reason: collision with root package name */
    public final SharedPreferences f7387a;
    public final SharedPreferences.Editor b;

    public SyncSessionManager(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("DataSyncPref", 0);
        this.f7387a = sharedPreferences;
        this.b = sharedPreferences.edit();
    }

    public static SyncSessionManager getInstance(Context context) {
        if (c == null) {
            c = new SyncSessionManager(context);
        }
        return c;
    }

    public boolean isExistingUserFirstTime() {
        return this.f7387a.getBoolean("KEY_EXISTING_USER_FIRST_TIME", false);
    }

    public void setExistingUserFirstTime(boolean z) {
        this.b.putBoolean("KEY_EXISTING_USER_FIRST_TIME", z).commit();
    }
}
