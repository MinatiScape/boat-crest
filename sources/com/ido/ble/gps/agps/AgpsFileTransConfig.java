package com.ido.ble.gps.agps;
/* loaded from: classes11.dex */
public class AgpsFileTransConfig {
    public static final int FILE_TYPE_GPS = 1;
    public static final int FILE_TYPE_LANG = 2;
    public String filePath;
    public IAGpsTranslateStateListener listener;
    public int fileType = 1;
    public int PRN = 5;

    public String toString() {
        return "AgpsFileTransConfig{fileType=" + this.fileType + ", filePath='" + this.filePath + "', listener=" + this.listener + ", PRN=" + this.PRN + '}';
    }
}
