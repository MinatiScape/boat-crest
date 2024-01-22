package com.goodix.ble.gr.libdfu.dfu.entity;

import com.goodix.ble.libcomx.util.HexBuilder;
import com.goodix.ble.libcomx.util.HexReader;
/* loaded from: classes5.dex */
public class BootInfo {

    /* renamed from: a  reason: collision with root package name */
    public BootConfig f7993a = new BootConfig();
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;

    public void deserialize(HexReader hexReader) {
        this.f = hexReader.get(4);
        this.e = hexReader.get(4);
        this.d = hexReader.get(4);
        this.c = hexReader.get(4);
        this.b = hexReader.get(4);
        this.f7993a.setConfig(hexReader.get(4));
    }

    public int getAppSize() {
        return this.f;
    }

    public int getChecksum() {
        return this.e;
    }

    public BootConfig getConfig() {
        return this.f7993a;
    }

    public int getLoadAddr() {
        return this.d;
    }

    public int getRunAddr() {
        return this.c;
    }

    public int getSerializeSize() {
        return 24;
    }

    public int getSpiAccessMode() {
        return this.b;
    }

    public void serialize(HexBuilder hexBuilder) {
        hexBuilder.put(this.f, 4);
        hexBuilder.put(this.e, 4);
        hexBuilder.put(this.d, 4);
        hexBuilder.put(this.c, 4);
        hexBuilder.put(this.b, 4);
        hexBuilder.put(this.f7993a.getConfig(), 4);
    }

    public void setAppSize(int i) {
        this.f = i;
    }

    public void setChecksum(int i) {
        this.e = i;
    }

    public void setConfig(BootConfig bootConfig) {
        this.f7993a = bootConfig;
    }

    public void setLoadAddr(int i) {
        this.d = i;
    }

    public void setRunAddr(int i) {
        this.c = i;
    }

    public void setSpiAccessMode(int i) {
        this.b = i;
    }
}
