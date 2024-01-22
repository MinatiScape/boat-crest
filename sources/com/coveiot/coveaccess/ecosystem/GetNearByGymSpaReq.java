package com.coveiot.coveaccess.ecosystem;
/* loaded from: classes8.dex */
public class GetNearByGymSpaReq {

    /* renamed from: a  reason: collision with root package name */
    public LatlongData f6487a;
    public String b;
    public int c = 500;
    public PlaceType d;

    public LatlongData getLatlongData() {
        return this.f6487a;
    }

    public String getName() {
        return this.b;
    }

    public PlaceType getPlaceType() {
        return this.d;
    }

    public int getRadius() {
        return this.c;
    }

    public void setLatlongData(LatlongData latlongData) {
        this.f6487a = latlongData;
    }

    public void setName(String str) {
        this.b = str;
    }

    public void setPlaceType(PlaceType placeType) {
        this.d = placeType;
    }

    public void setRadius(int i) {
        this.c = i;
    }
}
