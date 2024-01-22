package com.google.zxing.pdf417;
/* loaded from: classes11.dex */
public final class PDF417ResultMetadata {

    /* renamed from: a  reason: collision with root package name */
    public int f11845a;
    public String b;
    public boolean c;
    public String e;
    public String f;
    public String g;
    public int[] k;
    public int d = -1;
    public long h = -1;
    public long i = -1;
    public int j = -1;

    public String getAddressee() {
        return this.f;
    }

    public int getChecksum() {
        return this.j;
    }

    public String getFileId() {
        return this.b;
    }

    public String getFileName() {
        return this.g;
    }

    public long getFileSize() {
        return this.h;
    }

    @Deprecated
    public int[] getOptionalData() {
        return this.k;
    }

    public int getSegmentCount() {
        return this.d;
    }

    public int getSegmentIndex() {
        return this.f11845a;
    }

    public String getSender() {
        return this.e;
    }

    public long getTimestamp() {
        return this.i;
    }

    public boolean isLastSegment() {
        return this.c;
    }

    public void setAddressee(String str) {
        this.f = str;
    }

    public void setChecksum(int i) {
        this.j = i;
    }

    public void setFileId(String str) {
        this.b = str;
    }

    public void setFileName(String str) {
        this.g = str;
    }

    public void setFileSize(long j) {
        this.h = j;
    }

    public void setLastSegment(boolean z) {
        this.c = z;
    }

    @Deprecated
    public void setOptionalData(int[] iArr) {
        this.k = iArr;
    }

    public void setSegmentCount(int i) {
        this.d = i;
    }

    public void setSegmentIndex(int i) {
        this.f11845a = i;
    }

    public void setSender(String str) {
        this.e = str;
    }

    public void setTimestamp(long j) {
        this.i = j;
    }
}
