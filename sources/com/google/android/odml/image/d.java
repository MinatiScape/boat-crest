package com.google.android.odml.image;

import android.graphics.Bitmap;
/* loaded from: classes10.dex */
public final /* synthetic */ class d {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ int[] f10445a;

    static {
        int[] iArr = new int[Bitmap.Config.values().length];
        f10445a = iArr;
        try {
            iArr[Bitmap.Config.ALPHA_8.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            f10445a[Bitmap.Config.ARGB_8888.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
    }
}
