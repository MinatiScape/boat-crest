package com.htsmart.wristband2.bean;

import androidx.annotation.Nullable;
/* loaded from: classes11.dex */
public class DialSubBinInfo {
    public static final byte TYPE_CUSTOM_STYLE_BLACK = 33;
    public static final byte TYPE_CUSTOM_STYLE_GRAY = 36;
    public static final byte TYPE_CUSTOM_STYLE_GREEN = 35;
    public static final byte TYPE_CUSTOM_STYLE_WHITE = 32;
    public static final byte TYPE_CUSTOM_STYLE_YELLOW = 34;
    public static final byte TYPE_NONE = 0;
    public static final byte TYPE_NORMAL = 16;

    /* renamed from: a  reason: collision with root package name */
    public byte f11957a;
    public int b;
    public int c;
    public byte d;
    @Nullable
    public DialComponent[] e;
    public int f;

    public byte getBinFlag() {
        return this.d;
    }

    public int getBinVersion() {
        return this.c;
    }

    @Nullable
    public DialComponent[] getComponents() {
        return this.e;
    }

    public int getDialNum() {
        return this.b;
    }

    public int getDialSpace() {
        return this.f;
    }

    public byte getDialType() {
        return this.f11957a;
    }

    public void setBinFlag(byte b) {
        this.d = b;
    }

    public void setBinVersion(int i) {
        this.c = i;
    }

    public void setComponents(@Nullable DialComponent[] dialComponentArr) {
        this.e = dialComponentArr;
    }

    public void setDialNum(int i) {
        this.b = i;
    }

    public void setDialSpace(int i) {
        this.f = i;
    }

    public void setDialType(byte b) {
        this.f11957a = b;
    }
}
