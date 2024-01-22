package com.coveiot.android.bleabstract.request;

import com.coveiot.android.bleabstract.models.DynamicSportsField;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class CustomMessageRequest extends BleBaseRequest {
    @NotNull
    public List<? extends DynamicSportsField> f;
    public short g;
    public short h;
    public short i;
    public short j;
    public short k;

    public CustomMessageRequest(@NotNull List<? extends DynamicSportsField> dynamicSportsFieldList, short s, short s2, short s3, short s4, short s5) {
        Intrinsics.checkNotNullParameter(dynamicSportsFieldList, "dynamicSportsFieldList");
        this.f = dynamicSportsFieldList;
        this.g = s;
        this.h = s2;
        this.i = s3;
        this.j = s4;
        this.k = s5;
    }

    public final short displayTime() {
        return this.i;
    }

    public final short enterDirection() {
        return this.j;
    }

    public final short exitDirection() {
        return this.k;
    }

    public final short getDisplayTime() {
        return this.i;
    }

    @NotNull
    public final List<DynamicSportsField> getDynamicSportsFieldList() {
        return this.f;
    }

    public final short getEnterDirection() {
        return this.j;
    }

    public final short getExitDirection() {
        return this.k;
    }

    public final short getMessageType() {
        return this.g;
    }

    public final short getVibrationDuration() {
        return this.h;
    }

    public final short messageType() {
        return this.g;
    }

    public final void setDisplayTime(short s) {
        this.i = s;
    }

    public final void setDynamicSportsFieldList(@NotNull List<? extends DynamicSportsField> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.f = list;
    }

    public final void setEnterDirection(short s) {
        this.j = s;
    }

    public final void setExitDirection(short s) {
        this.k = s;
    }

    public final void setMessageType(short s) {
        this.g = s;
    }

    public final void setVibrationDuration(short s) {
        this.h = s;
    }

    public final short vibrationDuration() {
        return this.h;
    }
}
