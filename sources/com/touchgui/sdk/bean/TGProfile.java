package com.touchgui.sdk.bean;

import androidx.annotation.Nullable;
import java.util.Date;
/* loaded from: classes12.dex */
public class TGProfile {
    public static final int FEMALE = 0;
    public static final int MAN = 1;
    @Nullable
    private Date birthday;
    private int gender;
    private int height;
    private int weight;

    @Nullable
    public Date getBirthday() {
        return this.birthday;
    }

    public int getGender() {
        return this.gender;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWeight() {
        return this.weight;
    }

    public void setBirthday(@Nullable Date date) {
        this.birthday = date;
    }

    public void setGender(int i) {
        this.gender = i;
    }

    public void setHeight(int i) {
        this.height = i;
    }

    public void setWeight(int i) {
        this.weight = i;
    }
}
