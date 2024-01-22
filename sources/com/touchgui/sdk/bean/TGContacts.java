package com.touchgui.sdk.bean;

import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.touchgui.sdk.internal.s;
import java.nio.charset.StandardCharsets;
/* loaded from: classes12.dex */
public class TGContacts {
    @Nullable
    private String name;
    @Nullable
    private String phoneNum;

    @Nullable
    public String getName() {
        return this.name;
    }

    public byte[] getNameBytes() {
        return s.a(64, this.name).getBytes(StandardCharsets.UTF_8);
    }

    public int getNameLen() {
        if (TextUtils.isEmpty(this.name)) {
            return 0;
        }
        return getNameBytes().length;
    }

    @Nullable
    public String getPhoneNum() {
        return this.phoneNum;
    }

    public byte[] getPhoneNumBytes() {
        return s.a(64, this.phoneNum).getBytes(StandardCharsets.UTF_8);
    }

    public int getPhoneNumLen() {
        if (TextUtils.isEmpty(this.phoneNum)) {
            return 0;
        }
        return getPhoneNumBytes().length;
    }

    public void setName(@Nullable String str) {
        this.name = str;
    }

    public void setPhoneNum(@Nullable String str) {
        this.phoneNum = str;
    }
}