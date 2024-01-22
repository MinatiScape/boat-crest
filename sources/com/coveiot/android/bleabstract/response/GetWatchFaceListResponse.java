package com.coveiot.android.bleabstract.response;

import com.coveiot.android.bleabstract.models.WatchFace;
import java.util.ArrayList;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class GetWatchFaceListResponse {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public ArrayList<WatchFace> f3627a;

    @Nullable
    public final ArrayList<WatchFace> getWatchFaceList() {
        return this.f3627a;
    }

    public final void setWatchFaceList(@Nullable ArrayList<WatchFace> arrayList) {
        this.f3627a = arrayList;
    }
}
