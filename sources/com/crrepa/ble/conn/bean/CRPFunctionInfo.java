package com.crrepa.ble.conn.bean;

import java.util.List;
/* loaded from: classes9.dex */
public class CRPFunctionInfo {
    public static final int BO_VIEW = 7;
    public static final int BP_VIEW = 6;
    public static final int CAMERA_VIEW = 11;
    public static final int HR_VIEW = 4;
    public static final int MSG_LIST_VIEW = 9;
    public static final int MUSIC_PLAYER_VIEW = 10;
    public static final int OTHER_VIEW = 12;
    public static final int SLEEP_VIEW = 3;
    public static final int STEP_VIEW = 2;
    public static final int TIME_VIEW = 1;
    public static final int TRAINING_VIEW = 5;
    public static final int WEATHER_VIEW = 8;

    /* renamed from: a  reason: collision with root package name */
    public List<Integer> f7647a;
    public boolean b;

    public CRPFunctionInfo() {
    }

    public CRPFunctionInfo(List<Integer> list, boolean z) {
        this.f7647a = list;
        this.b = z;
    }

    public List<Integer> getFunctionList() {
        return this.f7647a;
    }

    public boolean isDisplayFunction() {
        return this.b;
    }

    public void setDisplayFunction(boolean z) {
        this.b = z;
    }

    public void setFunctionList(List<Integer> list) {
        this.f7647a = list;
    }
}
