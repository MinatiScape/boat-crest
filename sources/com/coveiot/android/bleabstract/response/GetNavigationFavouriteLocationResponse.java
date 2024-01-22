package com.coveiot.android.bleabstract.response;

import com.coveiot.sdk.ble.api.model.FavouriteLocationData;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.gson.Gson;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class GetNavigationFavouriteLocationResponse {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public ArrayList<FavouriteLocationData> f3616a = new ArrayList<>();
    public int b;

    @NotNull
    public final ArrayList<FavouriteLocationData> getFavouriteLocationList() {
        return this.f3616a;
    }

    public final int getLocationNum() {
        return this.b;
    }

    public final void setFavouriteLocationList(@NotNull ArrayList<FavouriteLocationData> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.f3616a = arrayList;
    }

    public final void setLocationNum(int i) {
        this.b = i;
    }

    @NotNull
    public String toString() {
        return "(locationNum=" + this.b + ", favouriteLocationList=" + new Gson().toJson(this.f3616a) + HexStringBuilder.COMMENT_END_CHAR;
    }
}
