package com.coveiot.sdk.ble.events;

import com.coveiot.sdk.ble.model.BandData;
/* loaded from: classes9.dex */
public class ResponseEvent {

    /* renamed from: a  reason: collision with root package name */
    public Object f7570a;
    public ResponseType b;
    public ResponseStatus c;

    public ResponseEvent(ResponseType responseType, ResponseStatus responseStatus) {
        this.b = responseType;
        this.c = responseStatus;
        this.f7570a = null;
    }

    public Object getData() {
        return this.f7570a;
    }

    public ResponseStatus getStatus() {
        return this.c;
    }

    public ResponseType getType() {
        return this.b;
    }

    public void setStatus(ResponseStatus responseStatus) {
        this.c = responseStatus;
    }

    public ResponseEvent(ResponseType responseType, Object obj) {
        this.b = responseType;
        this.f7570a = obj;
        this.c = ResponseStatus.RESPONSE_STATUS_SUCCESS;
    }

    public ResponseEvent(ResponseType responseType, Object obj, ResponseStatus responseStatus) {
        this.b = responseType;
        this.f7570a = obj;
        this.c = responseStatus;
    }

    public ResponseEvent(BandData bandData) {
        this.f7570a = bandData;
    }
}
