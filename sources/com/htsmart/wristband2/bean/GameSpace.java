package com.htsmart.wristband2.bean;
/* loaded from: classes11.dex */
public class GameSpace {

    /* renamed from: a  reason: collision with root package name */
    public int f11961a;
    public int b;
    public byte c;
    public int d;

    public GameSpace() {
    }

    public GameSpace(int i, int i2, byte b, int i3) {
        this.f11961a = i;
        this.b = i2;
        this.c = b;
        this.d = i3;
    }

    public byte getBinFlag() {
        return this.c;
    }

    public int getGameType() {
        return this.f11961a;
    }

    public int getSkinNum() {
        return this.b;
    }

    public int getSpaceSize() {
        return this.d;
    }

    public void setBinFlag(byte b) {
        this.c = b;
    }

    public void setGameType(int i) {
        this.f11961a = i;
    }

    public void setSkinNum(int i) {
        this.b = i;
    }

    public void setSpaceSize(int i) {
        this.d = i;
    }
}
