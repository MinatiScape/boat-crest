package com.coveiot.android.bleabstract.response;

import java.util.List;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class AmbientSoundTimeLogBeanData {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public List<AmbientSoundHourData> f3579a;

    @Nullable
    public final List<AmbientSoundHourData> getLogBeans() {
        return this.f3579a;
    }

    public final void setLogBeans(@Nullable List<AmbientSoundHourData> list) {
        this.f3579a = list;
    }
}
