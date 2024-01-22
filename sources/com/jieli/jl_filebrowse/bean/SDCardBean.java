package com.jieli.jl_filebrowse.bean;

import android.bluetooth.BluetoothDevice;
import java.io.Serializable;
/* loaded from: classes11.dex */
public class SDCardBean implements Serializable {
    public static final int FLASH = 2;
    public static final int FLASH_2 = 4;
    public static final int INDEX_FLASH = 3;
    public static final int INDEX_FLASH2 = 5;
    public static final int INDEX_LINE_IN = 4;
    public static final int INDEX_SD0 = 1;
    public static final int INDEX_SD1 = 2;
    public static final int INDEX_USB = 0;
    public static final int LINEIN = 3;
    public static final int SD = 0;
    public static final int USB = 1;
    private static final long serialVersionUID = -7060210544600464481L;
    private int devHandler = -1;
    private BluetoothDevice device;
    private int index;
    private String name;
    private boolean online;
    private int type;

    public int getDevHandler() {
        return this.devHandler;
    }

    public BluetoothDevice getDevice() {
        return this.device;
    }

    public int getIndex() {
        return this.index;
    }

    public String getName() {
        return this.name;
    }

    public int getType() {
        return this.type;
    }

    public boolean isOnline() {
        return this.online;
    }

    public void setDevHandler(int i) {
        this.devHandler = i;
    }

    public void setDevice(BluetoothDevice bluetoothDevice) {
        this.device = bluetoothDevice;
    }

    public void setIndex(int i) {
        this.index = i;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setOnline(boolean z) {
        this.online = z;
    }

    public void setType(int i) {
        this.type = i;
    }

    public String toString() {
        return "SDCardBean{index=" + this.index + ", type=" + this.type + ", name='" + this.name + "', devHandler=" + this.devHandler + ", online=" + this.online + ", device='" + this.device + "'}";
    }
}
