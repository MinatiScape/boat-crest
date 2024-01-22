package com.coveiot.sdk.ble.api.request;

import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public abstract class GenericCommandRequest extends BaseRequest {
    public List<Byte> f;

    public GenericCommandRequest(Object obj) {
        super(obj);
        this.f = new ArrayList();
    }

    public List<Byte> getPayload() {
        return this.f;
    }

    public void setPayload(List<Byte> list) {
        this.f = list;
    }
}
