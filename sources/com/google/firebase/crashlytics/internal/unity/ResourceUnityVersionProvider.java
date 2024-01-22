package com.google.firebase.crashlytics.internal.unity;

import android.content.Context;
import com.google.firebase.crashlytics.internal.common.CommonUtils;
/* loaded from: classes10.dex */
public class ResourceUnityVersionProvider implements UnityVersionProvider {

    /* renamed from: a  reason: collision with root package name */
    public final Context f11263a;
    public boolean b = false;
    public String c;

    public ResourceUnityVersionProvider(Context context) {
        this.f11263a = context;
    }

    @Override // com.google.firebase.crashlytics.internal.unity.UnityVersionProvider
    public String getUnityVersion() {
        if (!this.b) {
            this.c = CommonUtils.resolveUnityEditorVersion(this.f11263a);
            this.b = true;
        }
        String str = this.c;
        if (str != null) {
            return str;
        }
        return null;
    }
}
