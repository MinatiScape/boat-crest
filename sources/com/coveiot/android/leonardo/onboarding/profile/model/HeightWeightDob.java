package com.coveiot.android.leonardo.onboarding.profile.model;

import com.coveiot.android.leonardo.utils.AppConstants;
import com.jstyle.blesdk1860.constant.BleConst;
import java.util.Calendar;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import kotlin.text.m;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class HeightWeightDob {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public Calendar f5328a;
    @Nullable
    public String b;
    @Nullable
    public String c;
    @Nullable
    public String d;
    @Nullable
    public final String e;

    @Nullable
    public final Calendar getDateOfBirth() {
        return this.f5328a;
    }

    @Nullable
    public final String getHeight() {
        return this.c;
    }

    @Nullable
    public final String getStrDob() {
        return this.b;
    }

    @Nullable
    public final String getStrideLength() {
        return this.e;
    }

    @NotNull
    public final String getStrideeLength() {
        String replace$default;
        String str = this.c;
        if (str != null) {
            String obj = (str == null || (replace$default = m.replace$default(str, "cms", AppConstants.EMPTY_STRING.getValue(), false, 4, (Object) null)) == null) ? null : StringsKt__StringsKt.trim(replace$default).toString();
            Intrinsics.checkNotNull(obj);
            return String.valueOf(Integer.parseInt(obj) * 0.413d);
        }
        return BleConst.GetDeviceTime;
    }

    @Nullable
    public final String getWeight() {
        return this.d;
    }

    public final void setDateOfBirth(@Nullable Calendar calendar) {
        this.f5328a = calendar;
    }

    public final void setHeight(@Nullable String str) {
        this.c = str;
    }

    public final void setStrDob(@Nullable String str) {
        this.b = str;
    }

    public final void setWeight(@Nullable String str) {
        this.d = str;
    }
}
