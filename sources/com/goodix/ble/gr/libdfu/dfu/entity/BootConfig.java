package com.goodix.ble.gr.libdfu.dfu.entity;
/* loaded from: classes5.dex */
public class BootConfig {

    /* renamed from: a  reason: collision with root package name */
    public int f7992a;

    public final int a(int i, int i2) {
        if (i2 <= 0) {
            return 0;
        }
        if (i < 0) {
            i = 0;
        } else if (i > 31) {
            i = 31;
        }
        if (i2 + i > 31) {
            i2 = 32 - i;
        }
        int i3 = this.f7992a;
        if (i > 0) {
            i3 >>= i;
        }
        return i3 & ((1 << i2) - 1);
    }

    public final void b(int i, int i2, int i3) {
        if (i2 <= 0) {
            return;
        }
        if (i < 0) {
            i = 0;
        } else if (i > 31) {
            i = 31;
        }
        if (i2 + i > 31) {
            i2 = 32 - i;
        }
        int i4 = (1 << i2) - 1;
        this.f7992a = ((i4 & i3) << i) | (this.f7992a & (~(i4 << i)));
    }

    public int getBootDelay() {
        return a(9, 1);
    }

    public int getCodeCopyMode() {
        return a(4, 1);
    }

    public int getConfig() {
        return this.f7992a;
    }

    public int getDapBoot() {
        return a(10, 1);
    }

    public int getImgChkFlag() {
        return a(8, 1);
    }

    public int getSystemClk() {
        return a(5, 3);
    }

    public int getXQspiSpeed() {
        return a(0, 4);
    }

    public void setBootDelay(int i) {
        b(9, 1, i);
    }

    public void setCodeCopyMode(int i) {
        b(4, 1, i);
    }

    public void setConfig(int i) {
        this.f7992a = i;
    }

    public void setDapBoot(int i) {
        b(10, 1, i);
    }

    public void setImgChkFlag(int i) {
        b(8, 1, i);
    }

    public void setSystemClk(int i) {
        b(5, 3, i);
    }

    public void setXQspiSpeed(int i) {
        b(0, 4, i);
    }
}
