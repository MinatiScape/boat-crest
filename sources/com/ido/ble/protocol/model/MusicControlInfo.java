package com.ido.ble.protocol.model;
/* loaded from: classes11.dex */
public class MusicControlInfo {
    public static final int STATUS_INVALID = 0;
    public static final int STATUS_PAUSE = 2;
    public static final int STATUS_PLAY = 1;
    public static final int STATUS_STOP = 3;
    public int curTimeSecond;
    public String musicName;
    public String singerName;
    public int status;
    public int totalTimeSecond;

    public String toString() {
        return "MusicControlInfo{status=" + this.status + ", curTimeSecond=" + this.curTimeSecond + ", totalTimeSecond=" + this.totalTimeSecond + ", musicName='" + this.musicName + "', singerName='" + this.singerName + "'}";
    }
}
