package com.coveiot.sdk.ble.api.request;

import java.util.List;
/* loaded from: classes9.dex */
public abstract class HybridCommandRequest extends GenericCommandRequest {
    public static final short DIRECTION_APP_TO_DEVICE = 0;
    public static final short DIRECTION_DEVICE_TO_APP = 128;
    public static final short NEED_RESPONSE = 64;
    public static final short REQUEST_TYPE_GET = 0;
    public static final short REQUEST_TYPE_PUSH = 2;
    public static final short REQUEST_TYPE_SET = 1;
    public short deploy;
    public short g;

    public HybridCommandRequest(Object obj, short s) {
        super(obj);
        this.g = (short) 0;
        this.deploy = (short) 0;
        this.g = s;
        this.deploy = (short) (s | 64);
    }

    @Override // com.coveiot.sdk.ble.api.request.GenericCommandRequest
    public List<Byte> getPayload() {
        List<Byte> payload = super.getPayload();
        payload.add(0, Byte.valueOf((byte) this.deploy));
        return payload;
    }

    public short getRequestType() {
        return this.g;
    }
}
