package com.ido.ble.watch.custom.model;

import java.util.List;
/* loaded from: classes11.dex */
public class WatchPlateFileInfo {
    public int availableCount;
    public int fileMaxSize;
    public List<String> fileNameList;
    public int version;

    public String toString() {
        return "WatchPlateFileInfo{availableCount=" + this.availableCount + ", fileMaxSize=" + this.fileMaxSize + ", fileNameList=" + this.fileNameList + ", version=" + this.version + '}';
    }
}
