package com.ido.ble.protocol.model;

import java.io.Serializable;
import java.util.List;
/* loaded from: classes11.dex */
public class HeartRateMeasureModeV3 implements Serializable {
    public static final int MODE_AUTO = 136;
    public static final int MODE_CUSTOM = 204;
    public static final int MODE_DEFAULT = 187;
    public static final int MODE_MANUAL = 170;
    public static final int MODE_OFF = 85;
    public static final int MODE_PERSISTENT = 153;
    public static final int SWITCH_OFF = 85;
    public static final int SWITCH_ON = 170;
    public static final int TIME_RANGE_OFF = 0;
    public static final int TIME_RANGE_ON = 1;
    private static final long serialVersionUID = 1;
    public int highHeartMode;
    public int highHeartValue;
    public int lowHeartMode;
    public int lowHeartValue;
    public int measurementInterval;
    private List<Integer> minModeArray;
    public int mode;
    public int notifyFlag;
    private List<Integer> secModeArray;
    public int startHour;
    public int startMinute;
    public int updateTime;
    public int hasTimeRange = 1;
    public int endHour = 23;
    public int endMinute = 59;

    public List<Integer> getMinModeArray() {
        return this.minModeArray;
    }

    public List<Integer> getSecModeArray() {
        return this.secModeArray;
    }

    public String toString() {
        return "HeartRateMeasureModeV3{mode=" + this.mode + ", hasTimeRange=" + this.hasTimeRange + ", startHour=" + this.startHour + ", startMinute=" + this.startMinute + ", endHour=" + this.endHour + ", endMinute=" + this.endMinute + ", measurementInterval=" + this.measurementInterval + ", updateTime=" + this.updateTime + ", secModeArray=" + this.secModeArray + ", minModeArray=" + this.minModeArray + ", notifyFlag=" + this.notifyFlag + ", highHeartMode=" + this.highHeartMode + ", lowHeartMode=" + this.lowHeartMode + ", highHeartValue=" + this.highHeartValue + ", lowHeartValue=" + this.lowHeartValue + '}';
    }
}
