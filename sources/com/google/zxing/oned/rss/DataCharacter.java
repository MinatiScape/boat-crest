package com.google.zxing.oned.rss;

import com.goodix.ble.libcomx.util.HexStringBuilder;
/* loaded from: classes11.dex */
public class DataCharacter {

    /* renamed from: a  reason: collision with root package name */
    public final int f11833a;
    public final int b;

    public DataCharacter(int i, int i2) {
        this.f11833a = i;
        this.b = i2;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof DataCharacter) {
            DataCharacter dataCharacter = (DataCharacter) obj;
            return this.f11833a == dataCharacter.f11833a && this.b == dataCharacter.b;
        }
        return false;
    }

    public final int getChecksumPortion() {
        return this.b;
    }

    public final int getValue() {
        return this.f11833a;
    }

    public final int hashCode() {
        return this.f11833a ^ this.b;
    }

    public final String toString() {
        return this.f11833a + "(" + this.b + HexStringBuilder.COMMENT_END_CHAR;
    }
}
