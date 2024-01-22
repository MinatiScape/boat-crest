package com.coveiot.android.crpsdk.events;

import com.coveiot.android.crpsdk.model.CRPBandData;
/* loaded from: classes3.dex */
public class CRPResponseEvent {

    /* renamed from: a  reason: collision with root package name */
    public Object f4119a;
    public CRPResponseType b;
    public CRPResponseStatus c;

    public CRPResponseEvent(CRPResponseType cRPResponseType, CRPResponseStatus cRPResponseStatus) {
        this.b = cRPResponseType;
        this.c = cRPResponseStatus;
        this.f4119a = null;
    }

    public Object getData() {
        return this.f4119a;
    }

    public CRPResponseStatus getStatus() {
        return this.c;
    }

    public CRPResponseType getType() {
        return this.b;
    }

    public void setStatus(CRPResponseStatus cRPResponseStatus) {
        this.c = cRPResponseStatus;
    }

    public CRPResponseEvent(CRPResponseType cRPResponseType, Object obj) {
        this.b = cRPResponseType;
        this.f4119a = obj;
        this.c = CRPResponseStatus.RESPONSE_STATUS_SUCCESS;
    }

    public CRPResponseEvent(CRPResponseType cRPResponseType, Object obj, CRPResponseStatus cRPResponseStatus) {
        this.b = cRPResponseType;
        this.f4119a = obj;
        this.c = cRPResponseStatus;
    }

    public CRPResponseEvent(CRPBandData cRPBandData) {
        this.f4119a = cRPBandData;
    }
}
