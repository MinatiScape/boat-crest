package com.goodix.ble.gr.libdfu.define;
/* loaded from: classes5.dex */
public enum MemoryLayout {
    SYSTEM_CONFIG(16777280, 400),
    BOOT_INFO(16777216, 24);
    

    /* renamed from: a  reason: collision with root package name */
    private int f7984a;
    private int b;

    MemoryLayout(int i, int i2) {
        this.f7984a = i;
        this.b = i2;
    }

    public int getAddress() {
        return this.f7984a;
    }

    public int getSize() {
        return this.b;
    }
}
