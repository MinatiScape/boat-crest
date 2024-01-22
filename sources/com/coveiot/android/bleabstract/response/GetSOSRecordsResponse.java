package com.coveiot.android.bleabstract.response;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class GetSOSRecordsResponse {

    /* renamed from: a  reason: collision with root package name */
    public int f3620a;
    @NotNull
    public List<GetSOSRecordsItem> b = new ArrayList();

    public final int getNumberOfRecords() {
        return this.f3620a;
    }

    @NotNull
    public final List<GetSOSRecordsItem> getRecordsItem() {
        return this.b;
    }

    public final void setNumberOfRecords(int i) {
        this.f3620a = i;
    }

    public final void setRecordsItem(@NotNull List<GetSOSRecordsItem> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.b = list;
    }

    @NotNull
    public String toString() {
        return "GetSOSRecordsResponse(numberOfRecords=" + this.f3620a + ", recordsItem=" + this.b + HexStringBuilder.COMMENT_END_CHAR;
    }
}
