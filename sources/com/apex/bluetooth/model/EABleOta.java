package com.apex.bluetooth.model;
/* loaded from: classes.dex */
public class EABleOta implements Comparable<EABleOta> {
    public int byteSize;
    public byte[] fileBytes;
    public String filePath;
    public boolean isPop;
    public OtaType otaType;
    public String version;
    public int versionCode;

    /* loaded from: classes.dex */
    public enum OtaType {
        apollo,
        res,
        hr,
        tp,
        agps,
        gps,
        stm32,
        user_wf
    }

    public int getByteSize() {
        return this.byteSize;
    }

    public byte[] getFileBytes() {
        return this.fileBytes;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public OtaType getOtaType() {
        return this.otaType;
    }

    public String getVersion() {
        return this.version;
    }

    public int getVersionCode() {
        return this.versionCode;
    }

    public boolean isPop() {
        return this.isPop;
    }

    public void setByteSize(int i) {
        this.byteSize = i;
    }

    public void setFileBytes(byte[] bArr) {
        this.fileBytes = bArr;
    }

    public void setFilePath(String str) {
        this.filePath = str;
    }

    public void setOtaType(OtaType otaType) {
        this.otaType = otaType;
    }

    public void setPop(boolean z) {
        this.isPop = z;
    }

    public void setVersion(String str) {
        this.version = str;
    }

    public void setVersionCode(int i) {
        this.versionCode = i;
    }

    @Override // java.lang.Comparable
    public int compareTo(EABleOta eABleOta) {
        if (eABleOta != null) {
            int i = this.versionCode;
            int i2 = eABleOta.versionCode;
            if (i > i2) {
                return 1;
            }
            return i == i2 ? 0 : -1;
        }
        return 0;
    }
}
