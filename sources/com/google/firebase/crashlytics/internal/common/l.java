package com.google.firebase.crashlytics.internal.common;

import android.content.Context;
/* loaded from: classes10.dex */
public class l {

    /* renamed from: a  reason: collision with root package name */
    public String f11160a;

    public static String b(Context context) {
        String installerPackageName = context.getPackageManager().getInstallerPackageName(context.getPackageName());
        return installerPackageName == null ? "" : installerPackageName;
    }

    public synchronized String a(Context context) {
        if (this.f11160a == null) {
            this.f11160a = b(context);
        }
        return "".equals(this.f11160a) ? null : this.f11160a;
    }
}
