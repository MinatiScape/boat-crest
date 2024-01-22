package com.coveiot.android.leonardo.utils;

import android.content.Context;
import android.content.SharedPreferences;
/* loaded from: classes5.dex */
public class AppSessionManager {
    public static AppSessionManager d;

    /* renamed from: a  reason: collision with root package name */
    public SharedPreferences f5415a;
    public SharedPreferences.Editor b;
    public int c = 0;

    public AppSessionManager(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("CONFIGURATION_SETTINGS", 0);
        this.f5415a = sharedPreferences;
        this.b = sharedPreferences.edit();
    }

    public static AppSessionManager getInstance(Context context) {
        if (d == null) {
            d = new AppSessionManager(context);
        }
        return d;
    }

    public boolean isDfuFailed() {
        return this.f5415a.getBoolean("is_dfu_failed", false);
    }

    public boolean isFirstTimeUserComplete() {
        return this.f5415a.getBoolean("KEY_IS_FIRST_TIME_USER_COMPLETE", false);
    }

    public boolean isPermissionFTUSet() {
        return this.f5415a.getBoolean("is_permission_page_ftu", false);
    }

    public boolean isPermissionSet() {
        return this.f5415a.getBoolean("is_permission_page", false);
    }

    public boolean isProfileCompletedFirstTime() {
        return this.f5415a.getBoolean("isProfileCompletedFirstTime", false);
    }

    public void setFirstTimeUserComplete(boolean z) {
        this.b.putBoolean("KEY_IS_FIRST_TIME_USER_COMPLETE", z);
        this.b.commit();
    }

    public void setIsDfuFailed(boolean z) {
        this.b.putBoolean("is_dfu_failed", z);
        this.b.commit();
    }

    public void setIsProfileCompletedFirstTime(boolean z) {
        this.b.putBoolean("isProfileCompletedFirstTime", z);
        this.b.commit();
    }

    public void setPermissionFTUSettings(Boolean bool) {
        this.b.putBoolean("is_permission_page_ftu", bool.booleanValue());
        this.b.commit();
    }

    public void setPermissionSettings(Boolean bool) {
        this.b.putBoolean("is_permission_page", bool.booleanValue());
        this.b.commit();
    }
}
