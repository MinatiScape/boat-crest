package com.coveiot.android.bleabstract.models;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class ActivityTypeModel {

    /* renamed from: a  reason: collision with root package name */
    public final int f3395a;

    public ActivityTypeModel(int i) {
        this.f3395a = i;
    }

    public final int getType() {
        return this.f3395a;
    }

    @NotNull
    public String toString() {
        return "ActivityTypeModel(type=" + this.f3395a + HexStringBuilder.COMMENT_END_CHAR;
    }
}
