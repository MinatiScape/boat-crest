package com.crrepa.ble.conn.bean;
/* loaded from: classes9.dex */
public class CRPWatchFaceLayoutInfo {
    public static final int PICTURE_MD5_LENGTH = 32;

    /* renamed from: a  reason: collision with root package name */
    public int f7677a;
    public int b;
    public int c;
    public int d;
    public String e;
    public int f;
    public int g;
    public int h;
    public int i;
    public CompressionType j;

    /* loaded from: classes9.dex */
    public enum CompressionType {
        LZO(0),
        RGB_DEDUPLICATION(1),
        RGB_LINE(2),
        ORIGINAL(255);
        
        private int value;

        CompressionType(int i) {
            this.value = i;
        }

        public static CompressionType valueOf(int i) {
            if (i != 0) {
                if (i != 1) {
                    if (i != 2) {
                        if (i != 255) {
                            return null;
                        }
                        return ORIGINAL;
                    }
                    return RGB_LINE;
                }
                return RGB_DEDUPLICATION;
            }
            return LZO;
        }

        public int value() {
            return this.value;
        }
    }

    public String getBackgroundPictureMd5() {
        return this.e;
    }

    public CompressionType getCompressionType() {
        return this.j;
    }

    public int getHeight() {
        return this.f;
    }

    public int getTextColor() {
        return this.d;
    }

    public int getThumHeight() {
        return this.h;
    }

    public int getThumWidth() {
        return this.i;
    }

    public int getTimeBottomContent() {
        return this.c;
    }

    public int getTimePosition() {
        return this.f7677a;
    }

    public int getTimeTopContent() {
        return this.b;
    }

    public int getWidth() {
        return this.g;
    }

    public void setBackgroundPictureMd5(String str) {
        this.e = str;
    }

    public void setCompressionType(CompressionType compressionType) {
        this.j = compressionType;
    }

    public void setHeight(int i) {
        this.f = i;
    }

    public void setTextColor(int i) {
        this.d = i;
    }

    public void setThumHeight(int i) {
        this.h = i;
    }

    public void setThumWidth(int i) {
        this.i = i;
    }

    public void setTimeBottomContent(int i) {
        this.c = i;
    }

    public void setTimePosition(int i) {
        this.f7677a = i;
    }

    public void setTimeTopContent(int i) {
        this.b = i;
    }

    public void setWidth(int i) {
        this.g = i;
    }
}
