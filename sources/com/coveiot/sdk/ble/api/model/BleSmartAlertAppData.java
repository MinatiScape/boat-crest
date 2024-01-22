package com.coveiot.sdk.ble.api.model;

import java.io.Serializable;
/* loaded from: classes9.dex */
public class BleSmartAlertAppData implements Serializable {
    private int id;
    private int logoInfo;
    private int logoSize;
    private String name;

    public BleSmartAlertAppData(int i, String str, int i2, int i3) {
        this.id = i;
        this.name = str;
        this.logoSize = i2;
        this.logoInfo = i3;
    }

    public int getId() {
        return this.id;
    }

    public int getLogoInfo() {
        return this.logoInfo;
    }

    public int getLogoSize() {
        return this.logoSize;
    }

    public String getName() {
        return this.name;
    }

    public void setId(int i) {
        this.id = i;
    }

    public void setLogoInfo(int i) {
        this.logoInfo = i;
    }

    public void setLogoSize(int i) {
        this.logoSize = i;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String toString() {
        return "BleSmartAlertAppData{id=" + this.id + ", name='" + this.name + "', logoSize=" + this.logoSize + ", logoInfo=" + this.logoInfo + '}';
    }

    public BleSmartAlertAppData(int i, String str, int i2, int i3, int i4) {
        this.id = i;
        this.name = str;
        this.logoSize = i2;
        this.logoInfo = i3;
    }
}
