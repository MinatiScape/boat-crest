package com.touchgui.sdk.bean;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import java.nio.charset.StandardCharsets;
/* loaded from: classes12.dex */
public class TGStock {
    private float current;
    private float fluctuates;
    private float percent;
    @NonNull
    private String code = "";
    @NonNull
    private String name = "";

    @NonNull
    public String getCode() {
        return this.code;
    }

    public byte[] getCodeBytes() {
        return !TextUtils.isEmpty(this.code) ? this.code.getBytes(StandardCharsets.UTF_8) : new byte[0];
    }

    public int getCodeLen() {
        if (TextUtils.isEmpty(this.code)) {
            return 0;
        }
        return getCodeBytes().length;
    }

    public float getCurrent() {
        return this.current;
    }

    public float getFluctuates() {
        return this.fluctuates;
    }

    @NonNull
    public String getName() {
        return this.name;
    }

    public byte[] getNameBytes() {
        return !TextUtils.isEmpty(this.name) ? this.name.getBytes(StandardCharsets.UTF_8) : new byte[0];
    }

    public int getNameLen() {
        if (TextUtils.isEmpty(this.name)) {
            return 0;
        }
        return getNameBytes().length;
    }

    public float getPercent() {
        return this.percent;
    }

    public void setCode(@NonNull String str) {
        this.code = str;
    }

    public void setCurrent(float f) {
        this.current = f;
    }

    public void setFluctuates(float f) {
        this.fluctuates = f;
    }

    public void setName(@NonNull String str) {
        this.name = str;
    }

    public void setPercent(float f) {
        this.percent = f;
    }
}
