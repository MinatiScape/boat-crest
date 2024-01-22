package com.coveiot.android.bleabstract.response;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class UserProfileData {

    /* renamed from: a  reason: collision with root package name */
    public int f3681a;
    public int b;
    public float c;
    public float d;

    public final int getMAge() {
        return this.b;
    }

    public final int getMGender() {
        return this.f3681a;
    }

    public final float getMHeight() {
        return this.c;
    }

    public final float getMWeight() {
        return this.d;
    }

    public final void setMAge(int i) {
        this.b = i;
    }

    public final void setMGender(int i) {
        this.f3681a = i;
    }

    public final void setMHeight(float f) {
        this.c = f;
    }

    public final void setMWeight(float f) {
        this.d = f;
    }

    @NotNull
    public String toString() {
        return "UserProfileDetails(mGender=" + this.f3681a + ", mAge=" + this.b + ", mHeight=" + this.c + ", mWeight=" + this.d + HexStringBuilder.COMMENT_END_CHAR;
    }
}
