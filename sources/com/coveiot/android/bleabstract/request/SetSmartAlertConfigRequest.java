package com.coveiot.android.bleabstract.request;

import com.coveiot.android.bleabstract.models.SmartAlertAppData;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class SetSmartAlertConfigRequest extends BleBaseRequest {
    public final boolean f;
    @NotNull
    public final List<SmartAlertAppData> g;

    public SetSmartAlertConfigRequest(boolean z, @NotNull List<SmartAlertAppData> appList) {
        Intrinsics.checkNotNullParameter(appList, "appList");
        this.f = z;
        this.g = appList;
    }

    @NotNull
    public final List<SmartAlertAppData> getAppList() {
        return this.g;
    }

    public final boolean isEnabled() {
        return this.f;
    }
}
