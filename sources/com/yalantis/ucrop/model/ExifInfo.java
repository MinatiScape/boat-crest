package com.yalantis.ucrop.model;
/* loaded from: classes12.dex */
public class ExifInfo {

    /* renamed from: a  reason: collision with root package name */
    public int f13877a;
    public int b;
    public int c;

    public ExifInfo(int i, int i2, int i3) {
        this.f13877a = i;
        this.b = i2;
        this.c = i3;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ExifInfo exifInfo = (ExifInfo) obj;
        return this.f13877a == exifInfo.f13877a && this.b == exifInfo.b && this.c == exifInfo.c;
    }

    public int getExifDegrees() {
        return this.b;
    }

    public int getExifOrientation() {
        return this.f13877a;
    }

    public int getExifTranslation() {
        return this.c;
    }

    public int hashCode() {
        return (((this.f13877a * 31) + this.b) * 31) + this.c;
    }

    public void setExifDegrees(int i) {
        this.b = i;
    }

    public void setExifOrientation(int i) {
        this.f13877a = i;
    }

    public void setExifTranslation(int i) {
        this.c = i;
    }
}
