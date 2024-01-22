package com.jieli.jl_rcsp.model.device;

import com.jieli.jl_rcsp.util.CHexConver;
/* loaded from: classes11.dex */
public class DevStorageInfo {
    private int flash2Handler;
    private int flash2Status;
    private int flashHandler;
    private int flashStatus;
    private boolean isStorageReuse;
    private int lineInStatus;
    private int sd0Handler;
    private int sd0Status;
    private int sd1Handler;
    private int sd1Status;
    private int status;
    private int ubsStatus;
    private int usbHandler;

    public int getFlash2Handler() {
        return this.flash2Handler;
    }

    public int getFlash2Status() {
        return this.flash2Status;
    }

    public int getFlashHandler() {
        return this.flashHandler;
    }

    public int getFlashStatus() {
        return this.flashStatus;
    }

    public int getLineInStatus() {
        return this.lineInStatus;
    }

    public int getSd0Handler() {
        return this.sd0Handler;
    }

    public int getSd0Status() {
        return this.sd0Status;
    }

    public int getSd1Handler() {
        return this.sd1Handler;
    }

    public int getSd1Status() {
        return this.sd1Status;
    }

    public int getStatus() {
        return this.status;
    }

    public int getUbsStatus() {
        return this.ubsStatus;
    }

    public int getUsbHandler() {
        return this.usbHandler;
    }

    public boolean isStorageReuse() {
        return this.isStorageReuse;
    }

    public void parseData(byte[] bArr) {
        if (bArr != null) {
            if (bArr.length < 1) {
                return;
            }
            byte b = bArr[0];
            setStatus(CHexConver.byteToInt(b));
            setUbsStatus(b & 1);
            setSd0Status(b & 2);
            setSd1Status(b & 4);
            setFlashStatus(b & 8);
            setLineInStatus(b & 16);
            setFlash2Status(b & 32);
            int i = 5;
            if (bArr.length >= 5) {
                setUsbHandler(CHexConver.bytesToInt(bArr, 1, 4));
            } else {
                i = 1;
            }
            int i2 = i + 4;
            if (bArr.length >= i2) {
                setSd0Status(CHexConver.bytesToInt(bArr, i, 4));
                i = i2;
            }
            int i3 = i + 4;
            if (bArr.length >= i3) {
                setSd1Status(CHexConver.bytesToInt(bArr, i, 4));
                i = i3;
            }
            int i4 = i + 4;
            if (bArr.length >= i4) {
                setFlashHandler(CHexConver.bytesToInt(bArr, i, 4));
                i = i4;
            }
            int i5 = i + 4;
            if (bArr.length >= i5) {
                setFlash2Handler(CHexConver.bytesToInt(bArr, i, 4));
                i = i5;
            }
            if (bArr.length >= i + 1) {
                setStorageReuse(CHexConver.byteToInt(bArr[i]) == 1);
            }
        }
    }

    public DevStorageInfo setFlash2Handler(int i) {
        this.flash2Handler = i;
        return this;
    }

    public DevStorageInfo setFlash2Status(int i) {
        this.flash2Status = i;
        return this;
    }

    public DevStorageInfo setFlashHandler(int i) {
        this.flashHandler = i;
        return this;
    }

    public DevStorageInfo setFlashStatus(int i) {
        this.flashStatus = i;
        return this;
    }

    public DevStorageInfo setLineInStatus(int i) {
        this.lineInStatus = i;
        return this;
    }

    public DevStorageInfo setSd0Handler(int i) {
        this.sd0Handler = i;
        return this;
    }

    public DevStorageInfo setSd0Status(int i) {
        this.sd0Status = i;
        return this;
    }

    public DevStorageInfo setSd1Handler(int i) {
        this.sd1Handler = i;
        return this;
    }

    public DevStorageInfo setSd1Status(int i) {
        this.sd1Status = i;
        return this;
    }

    public DevStorageInfo setStatus(int i) {
        this.status = i;
        return this;
    }

    public DevStorageInfo setStorageReuse(boolean z) {
        this.isStorageReuse = z;
        return this;
    }

    public DevStorageInfo setUbsStatus(int i) {
        this.ubsStatus = i;
        return this;
    }

    public DevStorageInfo setUsbHandler(int i) {
        this.usbHandler = i;
        return this;
    }

    public String toString() {
        return "DevStorageInfo{ubsStatus=" + this.ubsStatus + ", sd0Status=" + this.sd0Status + ", sd1Status=" + this.sd1Status + ", flashStatus=" + this.flashStatus + ", usbHandler=" + this.usbHandler + ", sd0Handler=" + this.sd0Handler + ", sd1Handler=" + this.sd1Handler + ", flashHandler=" + this.flashHandler + ", lineInStatus=" + this.lineInStatus + ", flash2Handler=" + this.flash2Handler + ", isStorageReuse=" + this.isStorageReuse + '}';
    }
}
