package com.coveiot.android.bleabstract.request;

import com.coveiot.sdk.ble.api.model.FavouriteLocationData;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class SetNavigationFavouriteLocationRequest extends BleBaseRequest {
    @NotNull
    public ArrayList<FavouriteLocationData> f;

    /* loaded from: classes2.dex */
    public static final class Builder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public ArrayList<FavouriteLocationData> f3541a = new ArrayList<>();

        @NotNull
        public final SetNavigationFavouriteLocationRequest build() {
            return new SetNavigationFavouriteLocationRequest(this.f3541a);
        }

        @NotNull
        public final Builder setNavigationFavouriteLocationRequest(@NotNull ArrayList<FavouriteLocationData> favouriteLocationList) {
            Intrinsics.checkNotNullParameter(favouriteLocationList, "favouriteLocationList");
            this.f3541a = favouriteLocationList;
            return this;
        }
    }

    public SetNavigationFavouriteLocationRequest(@NotNull ArrayList<FavouriteLocationData> favouriteLocationList) {
        Intrinsics.checkNotNullParameter(favouriteLocationList, "favouriteLocationList");
        this.f = favouriteLocationList;
    }

    @NotNull
    public final ArrayList<FavouriteLocationData> getFavouriteLocationList() {
        return this.f;
    }

    public final void setFavouriteLocationList(@NotNull ArrayList<FavouriteLocationData> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.f = arrayList;
    }
}
