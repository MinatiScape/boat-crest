package com.coveiot.android.bleabstract.response;

import com.coveiot.android.bleabstract.models.SensAISummaryData;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.ArrayList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class GetSensAISummaryDataResponse {

    /* renamed from: a  reason: collision with root package name */
    public boolean f3622a;
    @Nullable
    public ArrayList<SensAISummaryData> b;

    public GetSensAISummaryDataResponse(@Nullable ArrayList<SensAISummaryData> arrayList) {
        this.b = arrayList;
    }

    @Nullable
    public final ArrayList<SensAISummaryData> getSensAISummaryData() {
        return this.b;
    }

    public final boolean isComplete() {
        return this.f3622a;
    }

    public final void setComplete(boolean z) {
        this.f3622a = z;
    }

    @NotNull
    public String toString() {
        return "SensAISummaryResponse(sensAISummaryDataList=" + this.b + HexStringBuilder.COMMENT_END_CHAR;
    }
}
