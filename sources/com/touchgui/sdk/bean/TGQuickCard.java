package com.touchgui.sdk.bean;
/* loaded from: classes12.dex */
public class TGQuickCard {
    public static final int ACTIVITY = 1;
    public static final int ALIPAY = 6;
    public static final int HEART_RATE = 3;
    public static final int MUSIC = 5;
    public static final int SLEEP = 2;
    public static final int SPO2 = 8;
    public static final int STRESS = 7;
    public static final int WEATHER = 4;
    private int id;
    private boolean visible = false;

    public int getId() {
        return this.id;
    }

    public boolean isVisible() {
        return this.visible;
    }

    public void setId(int i) {
        this.id = i;
    }

    public void setVisible(boolean z) {
        this.visible = z;
    }
}
