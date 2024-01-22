package com.coveiot.android.bleabstract.response;

import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class Spo2WaveResponse {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public List<Spo2Wave> f3666a = new ArrayList();

    @Nullable
    public final List<Spo2Wave> getWaves() {
        return this.f3666a;
    }

    public final void setWaves(@Nullable List<Spo2Wave> list) {
        this.f3666a = list;
    }
}
