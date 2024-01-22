package com.coveiot.android.bleabstract.request;

import com.coveiot.android.bleabstract.models.DynamicSportsField;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class SetSmartAlertApplicationContentRequest extends BleBaseRequest {
    public final int f;
    public final int g;
    @NotNull
    public final List<DynamicSportsField> h;

    /* JADX WARN: Multi-variable type inference failed */
    public SetSmartAlertApplicationContentRequest(int i, int i2, @NotNull List<? extends DynamicSportsField> dynamicSportFields) {
        Intrinsics.checkNotNullParameter(dynamicSportFields, "dynamicSportFields");
        this.f = i;
        this.g = i2;
        this.h = dynamicSportFields;
    }

    public final int getAppId() {
        return this.f;
    }

    public final int getDisplayPosition() {
        return this.g;
    }

    @NotNull
    public final List<DynamicSportsField> getDynamicSportFields() {
        return this.h;
    }
}
