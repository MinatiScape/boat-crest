package com.mappls.sdk.navigation.refresh;

import android.content.Context;
import com.mappls.sdk.navigation.data.LocationPoint;
import com.mappls.sdk.services.api.alongroute.models.SuggestedPOI;
import com.mappls.sdk.services.api.geocoding.GeoCodingCriteria;
/* loaded from: classes11.dex */
public final class c implements LocationPoint {

    /* renamed from: a  reason: collision with root package name */
    public SuggestedPOI f12934a;
    public int b;

    public c(SuggestedPOI suggestedPOI, int i) {
        this.f12934a = suggestedPOI;
        this.b = i;
    }

    public final int a() {
        return this.b;
    }

    @Override // com.mappls.sdk.navigation.data.LocationPoint
    public final double getLatitude() {
        return this.f12934a.getLatitude().doubleValue();
    }

    @Override // com.mappls.sdk.navigation.data.LocationPoint
    public final double getLongitude() {
        return this.f12934a.getLongitude().doubleValue();
    }

    @Override // com.mappls.sdk.navigation.data.LocationPoint
    public final com.mappls.sdk.navigation.data.a getPointDescription(Context context) {
        return new com.mappls.sdk.navigation.data.a(GeoCodingCriteria.POD_POINT_OF_INTEREST, this.f12934a.getPopularName());
    }
}
