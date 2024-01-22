package com.touchgui.sdk.bean;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;
/* loaded from: classes12.dex */
public class TGAppMenuStyle {
    public static final int STYLE_COLUMN1 = 1;
    public static final int STYLE_COLUMN2 = 2;
    public static final int STYLE_COLUMN3 = 3;
    public static final int STYLE_FLOWER = 5;
    public static final int STYLE_STAGGERED = 6;
    public static final int STYLE_STAR = 4;
    private int style;
    private List<Integer> supportList;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes12.dex */
    public @interface Style {
    }

    public int getStyle() {
        return this.style;
    }

    public List<Integer> getSupportList() {
        return this.supportList;
    }

    public void setStyle(int i) {
        this.style = i;
    }

    public void setSupportList(List<Integer> list) {
        this.supportList = list;
    }
}
