package com.coveiot.coveaccess.navigation;

import com.coveiot.coveaccess.navigation.model.FavouritePlace;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
/* loaded from: classes8.dex */
public class GetPlaceTrackHistoryRes implements Serializable {
    @SerializedName(FirebaseAnalytics.Param.ITEMS)
    @Expose
    public List<FavouritePlace> favouritePlaceList;

    public List<FavouritePlace> getFavouritePlaceList() {
        return this.favouritePlaceList;
    }

    public void setFavouritePlaceList(List<FavouritePlace> list) {
        this.favouritePlaceList = list;
    }
}
