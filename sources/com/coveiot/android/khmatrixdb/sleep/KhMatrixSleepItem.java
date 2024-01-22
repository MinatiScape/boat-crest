package com.coveiot.android.khmatrixdb.sleep;

import com.alibaba.fastjson.annotation.JSONField;
import com.htsmart.wristband2.bean.data.ICalculateSleepItem;
import java.util.Date;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class KhMatrixSleepItem implements ICalculateSleepItem {

    /* renamed from: a  reason: collision with root package name */
    public int f4626a;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @Nullable
    public Date b;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @Nullable
    public Date c;

    @Override // com.htsmart.wristband2.bean.data.ICalculateSleepItem
    public long getCalculateEndTime() {
        Date date = this.c;
        Intrinsics.checkNotNull(date);
        return date.getTime();
    }

    @Override // com.htsmart.wristband2.bean.data.ICalculateSleepItem
    public long getCalculateStartTime() {
        Date date = this.b;
        Intrinsics.checkNotNull(date);
        return date.getTime();
    }

    @Override // com.htsmart.wristband2.bean.data.ICalculateSleepItem
    public int getCalculateStatus() {
        return this.f4626a;
    }

    @Nullable
    public final Date getEndTime() {
        return this.c;
    }

    @Nullable
    public final Date getStartTime() {
        return this.b;
    }

    public final int getStatus() {
        return this.f4626a;
    }

    public final void setEndTime(@Nullable Date date) {
        this.c = date;
    }

    public final void setStartTime(@Nullable Date date) {
        this.b = date;
    }

    public final void setStatus(int i) {
        this.f4626a = i;
    }
}
