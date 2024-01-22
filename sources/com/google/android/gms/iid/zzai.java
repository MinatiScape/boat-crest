package com.google.android.gms.iid;

import javax.annotation.concurrent.GuardedBy;
/* loaded from: classes6.dex */
public abstract class zzai {
    @GuardedBy("SdkFlagFactory.class")

    /* renamed from: a  reason: collision with root package name */
    public static zzai f8493a;

    public static synchronized zzai zzy() {
        zzai zzaiVar;
        synchronized (zzai.class) {
            if (f8493a == null) {
                f8493a = new b();
            }
            zzaiVar = f8493a;
        }
        return zzaiVar;
    }

    public abstract zzaj<Boolean> zzd(String str, boolean z);
}
