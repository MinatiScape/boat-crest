package com.crrepa.ble.conn.bean;

import android.graphics.Bitmap;
import com.crrepa.ble.conn.bean.CRPWatchFaceLayoutInfo;
/* loaded from: classes9.dex */
public class CRPWatchFaceBackgroundInfo {

    /* renamed from: a  reason: collision with root package name */
    public Bitmap f7674a;
    public Bitmap b;
    public CRPWatchFaceLayoutInfo.CompressionType c;
    public int d = 30;

    public CRPWatchFaceBackgroundInfo(Bitmap bitmap, Bitmap bitmap2, CRPWatchFaceLayoutInfo.CompressionType compressionType) {
        this.f7674a = bitmap;
        this.b = bitmap2;
        this.c = compressionType;
    }

    public CRPWatchFaceBackgroundInfo(Bitmap bitmap, CRPWatchFaceLayoutInfo.CompressionType compressionType) {
        this.f7674a = bitmap;
        this.c = compressionType;
    }

    public Bitmap getBitmap() {
        return this.f7674a;
    }

    public Bitmap getThumBitmap() {
        return this.b;
    }

    public int getTimeout() {
        return this.d;
    }

    public CRPWatchFaceLayoutInfo.CompressionType getType() {
        return this.c;
    }

    public void setBitmap(Bitmap bitmap) {
        this.f7674a = bitmap;
    }

    public void setThumBitmap(Bitmap bitmap) {
        this.b = bitmap;
    }

    public void setTimeout(int i) {
        this.d = i;
    }

    public void setType(CRPWatchFaceLayoutInfo.CompressionType compressionType) {
        this.c = compressionType;
    }
}
