package com.touchgui.sdk.bean;

import androidx.annotation.Nullable;
import java.util.List;
/* loaded from: classes12.dex */
public class TGIotDevice {
    private int deviceType;
    @Nullable
    private List<TGIotFunction> functions;
    private String iconName;
    private String mac;
    private String name;

    public int getDeviceType() {
        return this.deviceType;
    }

    @Nullable
    public List<TGIotFunction> getFunctions() {
        return this.functions;
    }

    public String getIconName() {
        return this.iconName;
    }

    public String getMac() {
        return this.mac;
    }

    public String getName() {
        return this.name;
    }

    public void setDeviceType(int i) {
        this.deviceType = i;
    }

    public void setFunctions(@Nullable List<TGIotFunction> list) {
        this.functions = list;
    }

    public void setIconName(String str) {
        this.iconName = str;
    }

    public void setMac(String str) {
        this.mac = str;
    }

    public void setName(String str) {
        this.name = str;
    }
}
