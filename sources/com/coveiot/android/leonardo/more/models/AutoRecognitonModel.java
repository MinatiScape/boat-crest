package com.coveiot.android.leonardo.more.models;

import com.coveiot.android.bleabstract.models.ActivityTypes;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class AutoRecognitonModel {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public ActivityTypes f5133a;
    public boolean b;
    @Nullable
    public Integer c;

    public AutoRecognitonModel(@Nullable ActivityTypes activityTypes, boolean z, @Nullable Integer num) {
        this.f5133a = activityTypes;
        this.b = z;
        this.c = num;
    }

    public static /* synthetic */ AutoRecognitonModel copy$default(AutoRecognitonModel autoRecognitonModel, ActivityTypes activityTypes, boolean z, Integer num, int i, Object obj) {
        if ((i & 1) != 0) {
            activityTypes = autoRecognitonModel.f5133a;
        }
        if ((i & 2) != 0) {
            z = autoRecognitonModel.b;
        }
        if ((i & 4) != 0) {
            num = autoRecognitonModel.c;
        }
        return autoRecognitonModel.copy(activityTypes, z, num);
    }

    @Nullable
    public final ActivityTypes component1() {
        return this.f5133a;
    }

    public final boolean component2() {
        return this.b;
    }

    @Nullable
    public final Integer component3() {
        return this.c;
    }

    @NotNull
    public final AutoRecognitonModel copy(@Nullable ActivityTypes activityTypes, boolean z, @Nullable Integer num) {
        return new AutoRecognitonModel(activityTypes, z, num);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AutoRecognitonModel) {
            AutoRecognitonModel autoRecognitonModel = (AutoRecognitonModel) obj;
            return this.f5133a == autoRecognitonModel.f5133a && this.b == autoRecognitonModel.b && Intrinsics.areEqual(this.c, autoRecognitonModel.c);
        }
        return false;
    }

    @Nullable
    public final ActivityTypes getActivityTypes() {
        return this.f5133a;
    }

    @Nullable
    public final Integer getImageResource() {
        return this.c;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        ActivityTypes activityTypes = this.f5133a;
        int hashCode = (activityTypes == null ? 0 : activityTypes.hashCode()) * 31;
        boolean z = this.b;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        int i2 = (hashCode + i) * 31;
        Integer num = this.c;
        return i2 + (num != null ? num.hashCode() : 0);
    }

    public final boolean isSelected() {
        return this.b;
    }

    public final void setActivityTypes(@Nullable ActivityTypes activityTypes) {
        this.f5133a = activityTypes;
    }

    public final void setImageResource(@Nullable Integer num) {
        this.c = num;
    }

    public final void setSelected(boolean z) {
        this.b = z;
    }

    @NotNull
    public String toString() {
        return "AutoRecognitonModel(activityTypes=" + this.f5133a + ", isSelected=" + this.b + ", imageResource=" + this.c + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ AutoRecognitonModel(ActivityTypes activityTypes, boolean z, Integer num, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(activityTypes, z, (i & 4) != 0 ? PayUtils.INSTANCE.geAADImage(activityTypes) : num);
    }
}
