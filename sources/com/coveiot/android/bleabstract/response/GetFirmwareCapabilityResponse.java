package com.coveiot.android.bleabstract.response;
/* loaded from: classes2.dex */
public class GetFirmwareCapabilityResponse {

    /* renamed from: a  reason: collision with root package name */
    public byte[] f3608a;

    public GetFirmwareCapabilityResponse(byte[] bArr) {
        this.f3608a = bArr;
    }

    public byte[] getCapabilities() {
        return this.f3608a;
    }

    public String toString() {
        return "GetFirmwareCapabilityResponse{capabilityItemList=" + this.f3608a + '}';
    }
}
