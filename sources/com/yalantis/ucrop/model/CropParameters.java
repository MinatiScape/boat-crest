package com.yalantis.ucrop.model;

import android.graphics.Bitmap;
/* loaded from: classes12.dex */
public class CropParameters {

    /* renamed from: a  reason: collision with root package name */
    public int f13876a;
    public int b;
    public Bitmap.CompressFormat c;
    public int d;
    public String e;
    public String f;
    public ExifInfo g;

    public CropParameters(int i, int i2, Bitmap.CompressFormat compressFormat, int i3, String str, String str2, ExifInfo exifInfo) {
        this.f13876a = i;
        this.b = i2;
        this.c = compressFormat;
        this.d = i3;
        this.e = str;
        this.f = str2;
        this.g = exifInfo;
    }

    public Bitmap.CompressFormat getCompressFormat() {
        return this.c;
    }

    public int getCompressQuality() {
        return this.d;
    }

    public ExifInfo getExifInfo() {
        return this.g;
    }

    public String getImageInputPath() {
        return this.e;
    }

    public String getImageOutputPath() {
        return this.f;
    }

    public int getMaxResultImageSizeX() {
        return this.f13876a;
    }

    public int getMaxResultImageSizeY() {
        return this.b;
    }
}
