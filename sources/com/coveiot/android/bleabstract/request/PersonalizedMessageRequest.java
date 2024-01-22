package com.coveiot.android.bleabstract.request;

import com.coveiot.android.bleabstract.models.DynamicSportsField;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class PersonalizedMessageRequest extends BleBaseRequest {
    public short f;
    public short g;
    public short h;
    public short i;
    public short j;
    @NotNull
    public List<? extends DynamicSportsField> k;

    public PersonalizedMessageRequest(short s, short s2, short s3, short s4, short s5, @NotNull List<? extends DynamicSportsField> dynamicSportsFieldList) {
        Intrinsics.checkNotNullParameter(dynamicSportsFieldList, "dynamicSportsFieldList");
        this.f = s;
        this.g = s2;
        this.h = s3;
        this.i = s4;
        this.j = s5;
        this.k = dynamicSportsFieldList;
    }

    public final short displayTime() {
        return this.h;
    }

    public final short enterDirection() {
        return this.i;
    }

    public final short exitDirection() {
        return this.j;
    }

    public final short getDisplayTime() {
        return this.h;
    }

    @NotNull
    public final List<DynamicSportsField> getDynamicSportsFieldList() {
        return this.k;
    }

    public final short getEnterDirection() {
        return this.i;
    }

    public final short getExitDirection() {
        return this.j;
    }

    public final short getMessageType() {
        return this.f;
    }

    public final short getVibrationDuration() {
        return this.g;
    }

    public final short messageType() {
        return this.f;
    }

    public final void setDisplayTime(short s) {
        this.h = s;
    }

    public final void setDynamicSportsFieldList(@NotNull List<? extends DynamicSportsField> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.k = list;
    }

    public final void setEnterDirection(short s) {
        this.i = s;
    }

    public final void setExitDirection(short s) {
        this.j = s;
    }

    public final void setMessageType(short s) {
        this.f = s;
    }

    public final void setVibrationDuration(short s) {
        this.g = s;
    }

    public final short vibrationDuration() {
        return this.g;
    }
}
