package com.google.android.gms.vision.label;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ShowFirstParty;
@ShowFirstParty
@KeepForSdk
/* loaded from: classes10.dex */
public class ImageLabel {

    /* renamed from: a  reason: collision with root package name */
    public final String f10196a;
    public final String b;
    public final float c;

    public ImageLabel(String str, String str2, float f) {
        this.f10196a = str;
        this.b = str2;
        this.c = f;
    }

    @KeepForSdk
    public float getConfidence() {
        return this.c;
    }

    @KeepForSdk
    public String getLabel() {
        return this.b;
    }

    @KeepForSdk
    public String getMid() {
        return this.f10196a;
    }
}
