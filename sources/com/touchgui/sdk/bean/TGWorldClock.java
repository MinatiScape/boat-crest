package com.touchgui.sdk.bean;

import android.text.TextUtils;
import androidx.annotation.Nullable;
import java.nio.charset.StandardCharsets;
/* loaded from: classes12.dex */
public class TGWorldClock {
    @Nullable
    private String cityName;
    private int timezone;

    @Nullable
    public String getCityName() {
        return this.cityName;
    }

    public byte[] getCityNameBytes() {
        return !TextUtils.isEmpty(this.cityName) ? this.cityName.getBytes(StandardCharsets.UTF_8) : new byte[0];
    }

    public int getCityNameLen() {
        if (TextUtils.isEmpty(this.cityName)) {
            return 0;
        }
        return getCityNameBytes().length;
    }

    public int getTimezone() {
        return this.timezone;
    }

    public void setCityName(@Nullable String str) {
        this.cityName = str;
    }

    public void setTimezone(int i) {
        this.timezone = i;
    }
}
