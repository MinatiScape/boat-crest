package com.ido.ble.watch.custom.model;
/* loaded from: classes11.dex */
public class WatchPlateOperation {
    public static final int OPERATE_DELETE = 2;
    public static final int OPERATE_QUERY = 0;
    public static final int OPERATE_SET = 1;
    public int errCode;
    public String fileName;
    public int operate;

    public String toString() {
        return "WatchPlateOperation{errCode=" + this.errCode + ", operate=" + this.operate + ", fileName='" + this.fileName + "'}";
    }
}
