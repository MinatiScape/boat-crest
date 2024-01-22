package com.htsmart.wristband2.bean;

import java.util.List;
/* loaded from: classes11.dex */
public class SyncDataRaw {

    /* renamed from: a  reason: collision with root package name */
    public byte f11967a;
    public List<byte[]> b;
    public WristbandConfig c;

    public SyncDataRaw(byte b, List<byte[]> list, WristbandConfig wristbandConfig) {
        this.f11967a = b;
        this.b = list;
        this.c = wristbandConfig;
    }

    public WristbandConfig getConfig() {
        return this.c;
    }

    public byte getDataType() {
        return this.f11967a;
    }

    public List<byte[]> getDatas() {
        return this.b;
    }

    public void setConfig(WristbandConfig wristbandConfig) {
        this.c = wristbandConfig;
    }

    public void setDataType(byte b) {
        this.f11967a = b;
    }

    public void setDatas(List<byte[]> list) {
        this.b = list;
    }
}
