package com.touchgui.sdk.bean;
/* loaded from: classes12.dex */
public class TGIotFunction {
    public static final int TYPE_FEATURE_SET = 5;
    public static final int TYPE_FLUCTUATE = 4;
    public static final int TYPE_INVALID = 0;
    public static final int TYPE_ONLINE = 3;
    public static final int TYPE_SWITCH = 1;
    public static final int TYPE_TEXT = 2;
    private String name;
    private String nameEnum;
    private int type;

    public TGIotFunction(int i) {
        this.type = i;
    }

    public String getName() {
        return this.name;
    }

    public String getNameEnum() {
        return this.nameEnum;
    }

    public int getType() {
        return this.type;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setNameEnum(String str) {
        this.nameEnum = str;
    }

    public void setType(int i) {
        this.type = i;
    }
}
