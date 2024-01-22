package com.coveiot.android.crpsdk.model;

import com.crrepa.ble.conn.bean.CRPHeartRateInfo;
import java.io.Serializable;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u001e\u0010\u001fR$\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR*\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R$\u0010\u0011\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R$\u0010\u0018\u001a\u0004\u0018\u00010\u00178\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001d¨\u0006 "}, d2 = {"Lcom/coveiot/android/crpsdk/model/KhCRPHeartRateInfo;", "Ljava/io/Serializable;", "", "startTime", "Ljava/lang/Long;", "getStartTime", "()Ljava/lang/Long;", "setStartTime", "(Ljava/lang/Long;)V", "", "", "heartRateList", "Ljava/util/List;", "getHeartRateList", "()Ljava/util/List;", "setHeartRateList", "(Ljava/util/List;)V", "timeInterval", "Ljava/lang/Integer;", "getTimeInterval", "()Ljava/lang/Integer;", "setTimeInterval", "(Ljava/lang/Integer;)V", "Lcom/crrepa/ble/conn/bean/CRPHeartRateInfo$HeartRateType;", "heartRateType", "Lcom/crrepa/ble/conn/bean/CRPHeartRateInfo$HeartRateType;", "getHeartRateType", "()Lcom/crrepa/ble/conn/bean/CRPHeartRateInfo$HeartRateType;", "setHeartRateType", "(Lcom/crrepa/ble/conn/bean/CRPHeartRateInfo$HeartRateType;)V", "<init>", "()V", "crpSdk_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes3.dex */
public final class KhCRPHeartRateInfo implements Serializable {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public Long f4121a;
    @Nullable
    public List<Integer> b;
    @Nullable
    public Integer c;
    @Nullable
    public CRPHeartRateInfo.HeartRateType d;

    @Nullable
    public final List<Integer> getHeartRateList() {
        return this.b;
    }

    @Nullable
    public final CRPHeartRateInfo.HeartRateType getHeartRateType() {
        return this.d;
    }

    @Nullable
    public final Long getStartTime() {
        return this.f4121a;
    }

    @Nullable
    public final Integer getTimeInterval() {
        return this.c;
    }

    public final void setHeartRateList(@Nullable List<Integer> list) {
        this.b = list;
    }

    public final void setHeartRateType(@Nullable CRPHeartRateInfo.HeartRateType heartRateType) {
        this.d = heartRateType;
    }

    public final void setStartTime(@Nullable Long l) {
        this.f4121a = l;
    }

    public final void setTimeInterval(@Nullable Integer num) {
        this.c = num;
    }
}
