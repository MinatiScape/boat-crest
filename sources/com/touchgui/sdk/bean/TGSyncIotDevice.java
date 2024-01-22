package com.touchgui.sdk.bean;

import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.touchgui.sdk.internal.s;
import java.util.List;
/* loaded from: classes12.dex */
public class TGSyncIotDevice {
    public static final int DELETE_SIGNAL_DEVICE = 3;
    public static final int DELETE_SIGNAL_DEVICE_ITEMS = 5;
    public static final int SYNC_DEVICE_LIST = 1;
    public static final int UPDATE_SIGNAL_DEVICE = 2;
    public static final int UPDATE_SIGNAL_DEVICE_ITEMS = 4;
    private int currentIndex;
    private int deviceType;
    @Nullable
    private List<TGIotFunction> functions;
    private String iconName;
    private String mac;
    private String name;
    private int operateType;
    private int totalCount;

    public int getCurrentIndex() {
        return this.currentIndex;
    }

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

    public byte[] getIconNameBytes() {
        byte[] bArr = new byte[64];
        if (!TextUtils.isEmpty(this.iconName)) {
            byte[] bytes = this.iconName.getBytes();
            System.arraycopy(bytes, 0, bArr, 0, Math.min(bytes.length, 64));
        }
        return bArr;
    }

    public String getMac() {
        return this.mac;
    }

    public byte[] getMacBytes() {
        byte[] bArr = new byte[6];
        if (!TextUtils.isEmpty(this.mac)) {
            byte[] a2 = s.a(this.mac.replace(":", ""));
            System.arraycopy(a2, 0, bArr, 0, Math.min(a2.length, 6));
        }
        return bArr;
    }

    public String getName() {
        return this.name;
    }

    public byte[] getNameBytes() {
        byte[] bArr = new byte[64];
        if (!TextUtils.isEmpty(this.name)) {
            byte[] bytes = this.name.getBytes();
            System.arraycopy(bytes, 0, bArr, 0, Math.min(bytes.length, 64));
        }
        return bArr;
    }

    public int getOperateType() {
        return this.operateType;
    }

    public int getTotalCount() {
        return this.totalCount;
    }

    public void setCurrentIndex(int i) {
        this.currentIndex = i;
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

    public void setOperateType(int i) {
        this.operateType = i;
    }

    public void setTotalCount(int i) {
        this.totalCount = i;
    }
}
