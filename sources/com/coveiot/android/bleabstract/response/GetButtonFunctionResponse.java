package com.coveiot.android.bleabstract.response;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class GetButtonFunctionResponse {

    /* renamed from: a  reason: collision with root package name */
    public int f3602a;
    public int b;
    public int c;
    public int d;

    public final int getLongPressFunction() {
        return this.d;
    }

    public final int getPosition() {
        return this.f3602a;
    }

    public final int getShortPressFunction() {
        return this.c;
    }

    public final int getVersionNumber() {
        return this.b;
    }

    public final void setLongPressFunction(int i) {
        this.d = i;
    }

    public final void setPosition(int i) {
        this.f3602a = i;
    }

    public final void setShortPressFunction(int i) {
        this.c = i;
    }

    public final void setVersionNumber(int i) {
        this.b = i;
    }

    @NotNull
    public String toString() {
        return "(position=" + this.f3602a + " ,versionNumber=" + this.b + ", shortPressFunction=" + this.c + ", longPressFunction=" + this.d + HexStringBuilder.COMMENT_END_CHAR;
    }
}
