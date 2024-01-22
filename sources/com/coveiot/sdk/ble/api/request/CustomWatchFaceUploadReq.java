package com.coveiot.sdk.ble.api.request;

import android.net.Uri;
import com.coveiot.sdk.ble.utils.CommandNames;
import java.util.List;
/* loaded from: classes9.dex */
public class CustomWatchFaceUploadReq extends BaseRequest {
    public String f;
    public Uri g;
    public byte[] h;

    public CustomWatchFaceUploadReq(Object obj, String str) {
        super(obj);
        this.f = str;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public List<CommandBytes> getCommandBytes() {
        return null;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public CommandNames getCommandName() {
        return CommandNames.SET_CUSTOM_WATCHFACE;
    }

    public byte[] getData() {
        return this.h;
    }

    public String getFilePath() {
        return this.f;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public String getGattCharacteristicToRead() {
        return null;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public String getGattServiceToRead() {
        return null;
    }

    public Uri getUri() {
        return this.g;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public boolean isMultiPacket() {
        return false;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public boolean isPriority() {
        return false;
    }

    public void setData(byte[] bArr) {
        this.h = bArr;
    }

    public void setFilePath(String str) {
        this.f = str;
    }

    public void setUri(Uri uri) {
        this.g = uri;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public boolean shouldWaitForRes() {
        return false;
    }

    public CustomWatchFaceUploadReq(Object obj, Uri uri) {
        super(obj);
        this.g = uri;
    }

    public CustomWatchFaceUploadReq(Object obj, byte[] bArr) {
        super(obj);
        this.h = bArr;
    }
}
