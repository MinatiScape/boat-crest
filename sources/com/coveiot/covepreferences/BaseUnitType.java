package com.coveiot.covepreferences;

import com.coveiot.utils.utility.UNIT_TYPE;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes8.dex */
public final class BaseUnitType {
    @NotNull
    public static final BaseUnitType INSTANCE = new BaseUnitType();
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public static UNIT_TYPE f7001a;
    @NotNull
    public static UNIT_TYPE b;
    @NotNull
    public static UNIT_TYPE c;
    @NotNull
    public static UNIT_TYPE d;
    @NotNull
    public static UNIT_TYPE e;

    static {
        UNIT_TYPE unit_type = UNIT_TYPE.METRIC;
        f7001a = unit_type;
        b = unit_type;
        c = unit_type;
        d = unit_type;
        e = unit_type;
        f7001a = unit_type;
        c = unit_type;
        d = unit_type;
        e = unit_type;
        b = unit_type;
    }

    @NotNull
    public final UNIT_TYPE getUnitTypeDisatnce() {
        return d;
    }

    @NotNull
    public final UNIT_TYPE getUnitTypeHeight() {
        return f7001a;
    }

    @NotNull
    public final UNIT_TYPE getUnitTypeStrideLength() {
        return b;
    }

    @NotNull
    public final UNIT_TYPE getUnitTypeTemperature() {
        return e;
    }

    @NotNull
    public final UNIT_TYPE getUnitTypeWeight() {
        return c;
    }

    public final void setUnitTypeDisatnce(@NotNull UNIT_TYPE unit_type) {
        Intrinsics.checkNotNullParameter(unit_type, "<set-?>");
        d = unit_type;
    }

    public final void setUnitTypeHeight(@NotNull UNIT_TYPE unit_type) {
        Intrinsics.checkNotNullParameter(unit_type, "<set-?>");
        f7001a = unit_type;
    }

    public final void setUnitTypeStrideLength(@NotNull UNIT_TYPE unit_type) {
        Intrinsics.checkNotNullParameter(unit_type, "<set-?>");
        b = unit_type;
    }

    public final void setUnitTypeTemperature(@NotNull UNIT_TYPE unit_type) {
        Intrinsics.checkNotNullParameter(unit_type, "<set-?>");
        e = unit_type;
    }

    public final void setUnitTypeWeight(@NotNull UNIT_TYPE unit_type) {
        Intrinsics.checkNotNullParameter(unit_type, "<set-?>");
        c = unit_type;
    }
}
