package com.google.zxing.common;

import java.util.List;
/* loaded from: classes11.dex */
public final class DecoderResult {

    /* renamed from: a  reason: collision with root package name */
    public final byte[] f11787a;
    public int b;
    public final String c;
    public final List<byte[]> d;
    public final String e;
    public Integer f;
    public Integer g;
    public Object h;
    public final int i;
    public final int j;

    public DecoderResult(byte[] bArr, String str, List<byte[]> list, String str2) {
        this(bArr, str, list, str2, -1, -1);
    }

    public List<byte[]> getByteSegments() {
        return this.d;
    }

    public String getECLevel() {
        return this.e;
    }

    public Integer getErasures() {
        return this.g;
    }

    public Integer getErrorsCorrected() {
        return this.f;
    }

    public int getNumBits() {
        return this.b;
    }

    public Object getOther() {
        return this.h;
    }

    public byte[] getRawBytes() {
        return this.f11787a;
    }

    public int getStructuredAppendParity() {
        return this.i;
    }

    public int getStructuredAppendSequenceNumber() {
        return this.j;
    }

    public String getText() {
        return this.c;
    }

    public boolean hasStructuredAppend() {
        return this.i >= 0 && this.j >= 0;
    }

    public void setErasures(Integer num) {
        this.g = num;
    }

    public void setErrorsCorrected(Integer num) {
        this.f = num;
    }

    public void setNumBits(int i) {
        this.b = i;
    }

    public void setOther(Object obj) {
        this.h = obj;
    }

    public DecoderResult(byte[] bArr, String str, List<byte[]> list, String str2, int i, int i2) {
        this.f11787a = bArr;
        this.b = bArr == null ? 0 : bArr.length * 8;
        this.c = str;
        this.d = list;
        this.e = str2;
        this.i = i2;
        this.j = i;
    }
}
