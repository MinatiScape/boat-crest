package com.google.android.recaptcha.internal;

import com.google.firebase.crashlytics.BuildConfig;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.m;
/* loaded from: classes10.dex */
public final class zzab {
    private zzab() {
    }

    public /* synthetic */ zzab(DefaultConstructorMarker defaultConstructorMarker) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int zzb(String str) {
        return Integer.parseInt(m.replace$default(BuildConfig.VERSION_NAME, ".", "", false, 4, (Object) null));
    }
}
