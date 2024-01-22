package com.touchgui.sdk;

import com.touchgui.sdk.bean.TGIotDevice;
import com.touchgui.sdk.bean.TGQuickReply;
import com.touchgui.sdk.bean.TGSportStatusEvent;
import java.util.List;
/* loaded from: classes12.dex */
public class TGEventListener {
    public static final int ALARM_UPDATED = 769;
    public static final int ANSWER = 12;
    public static final int APP_MENU_STYLE_UPDATED = 294;
    public static final int AWAKE_SCREEN_UPDATED = 771;
    public static final int MEASURE_HEART_RATE = 272;
    public static final int MEASURE_SPO2 = 273;
    public static final int MEASURE_STRESS = 274;
    public static final int NEXT = 5;
    public static final int OTA_COMPLETED = 292;
    public static final int PAUSE = 2;
    public static final int PLAY = 1;
    public static final int PREVIOUS = 4;
    public static final int REFUSE = 13;
    public static final int REQUEST_UPDATE_STOCK = 295;
    public static final int REQUEST_UPDATE_WEATHER = 770;
    public static final int SILENT = 14;
    public static final int SLEEP_START = 290;
    public static final int SLEEP_STOP = 291;
    public static final int START_FIND_PHONE = 0;
    public static final int STOP_FIND_PHONE = 1;
    public static final int VOLUME_DOWN = 9;
    public static final int VOLUME_UP = 8;
    public static final int WATCH_FACE_INSTALLED = 293;
    public static final int WORKOUT_START = 288;
    public static final int WORKOUT_STOP = 289;

    public void onControlIotDevice(List<TGIotDevice> list) {
    }

    public void onEvent(int i) {
    }

    public void onFindPhone(int i) {
    }

    public void onQuickReply(TGQuickReply tGQuickReply) {
    }

    public void onRequestIotDeviceIcon(List<String> list) {
    }

    public void onSportStatusEvent(TGSportStatusEvent tGSportStatusEvent) {
    }
}
