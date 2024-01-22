package com.coveiot.android.bleabstract.response;

import com.coveiot.android.bleabstract.models.SmartAlertAppData;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.Serializable;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class GetSmartAlertConfigResponse implements Serializable {

    /* renamed from: a  reason: collision with root package name */
    public boolean f3624a;
    @Nullable
    public List<SmartAlertAppData> b;

    @Nullable
    public final List<SmartAlertAppData> getSmartAlertAppDataList() {
        return this.b;
    }

    public final boolean isEnabled() {
        return this.f3624a;
    }

    public final void setEnabled(boolean z) {
        this.f3624a = z;
    }

    public final void setSmartAlertAppDataList(@Nullable List<SmartAlertAppData> list) {
        this.b = list;
    }

    @NotNull
    public String toString() {
        return "GetSmartAlertConfigResponse(isEnabled=" + this.f3624a + ", smartAlertAppDataList=" + this.b + HexStringBuilder.COMMENT_END_CHAR;
    }
}
