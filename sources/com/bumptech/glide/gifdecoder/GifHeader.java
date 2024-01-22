package com.bumptech.glide.gifdecoder;

import androidx.annotation.ColorInt;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class GifHeader {
    public static final int NETSCAPE_LOOP_COUNT_DOES_NOT_EXIST = -1;
    public static final int NETSCAPE_LOOP_COUNT_FOREVER = 0;
    public a d;
    public int f;
    public int g;
    public boolean h;
    public int i;
    public int j;
    public int k;
    @ColorInt
    public int l;
    @ColorInt

    /* renamed from: a  reason: collision with root package name */
    public int[] f2318a = null;
    public int b = 0;
    public int c = 0;
    public final List<a> e = new ArrayList();
    public int m = -1;

    public int getHeight() {
        return this.g;
    }

    public int getNumFrames() {
        return this.c;
    }

    public int getStatus() {
        return this.b;
    }

    public int getWidth() {
        return this.f;
    }
}
