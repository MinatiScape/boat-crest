package com.coveiot.kheastapexdb.sleep;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¢\u0006\u0004\b\r\u0010\u000eR\u0019\u0010\u0003\u001a\u00020\u00028\u0006@\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R!\u0010\t\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00078\u0006@\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f¨\u0006\u000f"}, d2 = {"Lcom/coveiot/kheastapexdb/sleep/KHEASleepDayData;", "", "", "date", "Ljava/lang/String;", "getDate", "()Ljava/lang/String;", "", "Lcom/coveiot/kheastapexdb/sleep/EntityEASleepData;", "entityEASleepData", "Ljava/util/List;", "getEntityEASleepData", "()Ljava/util/List;", "<init>", "(Ljava/lang/String;Ljava/util/List;)V", "kheastapexdb_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes8.dex */
public final class KHEASleepDayData {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final String f7065a;
    @Nullable
    public final List<EntityEASleepData> b;

    public KHEASleepDayData(@NotNull String date, @Nullable List<EntityEASleepData> list) {
        Intrinsics.checkNotNullParameter(date, "date");
        this.f7065a = date;
        this.b = list;
    }

    @NotNull
    public final String getDate() {
        return this.f7065a;
    }

    @Nullable
    public final List<EntityEASleepData> getEntityEASleepData() {
        return this.b;
    }
}
