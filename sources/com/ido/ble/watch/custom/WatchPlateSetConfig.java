package com.ido.ble.watch.custom;

import com.ido.ble.watch.custom.callback.WatchPlateCallBack;
/* loaded from: classes11.dex */
public class WatchPlateSetConfig {
    public WatchPlateCallBack.ISetPlatErrorCallback errorCallback;
    public String filePath;
    public WatchPlateCallBack.IAutoSetPlateCallBack stateListener;
    public String uniqueID;
    public boolean isOnlyTranslateWatchFile = true;
    public int PRN = 10;
    public int maxRetryTimes = 0;

    public String toString() {
        return "WatchPlateSetConfig{filePath='" + this.filePath + "', uniqueID='" + this.uniqueID + "', isOnlyTranslateWatchFile=" + this.isOnlyTranslateWatchFile + ", stateListener=" + this.stateListener + ", PRN=" + this.PRN + ", maxRetryTimes=" + this.maxRetryTimes + '}';
    }
}
