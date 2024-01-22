package com.coveiot.android.bleabstract.response;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class GetSensAIActivityConfigResponse {

    /* renamed from: a  reason: collision with root package name */
    public int f3621a;
    @Nullable
    public List<String> b;

    public final int getActivityNumber() {
        return this.f3621a;
    }

    @Nullable
    public final List<String> getType() {
        return this.b;
    }

    public final void setActivityNumber(int i) {
        this.f3621a = i;
    }

    public final void setType(@Nullable List<String> list) {
        this.b = list;
    }

    @NotNull
    public String toString() {
        return "(activityNumber=" + this.f3621a + ", type=" + this.b + HexStringBuilder.COMMENT_END_CHAR;
    }
}
