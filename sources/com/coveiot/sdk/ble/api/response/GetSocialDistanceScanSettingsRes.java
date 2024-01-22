package com.coveiot.sdk.ble.api.response;

import com.clevertap.android.sdk.Constants;
import com.coveiot.sdk.ble.api.model.SocialDistanceScanSettingsData;
import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.model.CommandType;
import java.nio.ByteBuffer;
/* loaded from: classes9.dex */
public class GetSocialDistanceScanSettingsRes extends BaseResponse {
    public GetSocialDistanceScanSettingsRes(CommandType commandType, BaseRequest baseRequest) {
        super(commandType, baseRequest);
    }

    public final SocialDistanceScanSettingsData b() {
        String str = this.c.getDataList().get(0);
        String[] split = str.substring(1, str.length() - 1).split(Constants.SEPARATOR_COMMA);
        byte[] bArr = new byte[split.length + 1];
        for (int i = 0; i < split.length; i++) {
            bArr[i] = Integer.valueOf(split[i].trim()).byteValue();
        }
        short s = ByteBuffer.wrap(new byte[]{0, bArr[4]}).getShort();
        byte b = bArr[5];
        String format = String.format("%02x:%02x:%02x:%02x:%02x:%02x", Byte.valueOf(bArr[6]), Byte.valueOf(bArr[7]), Byte.valueOf(bArr[8]), Byte.valueOf(bArr[9]), Byte.valueOf(bArr[10]), Byte.valueOf(bArr[11]));
        int i2 = ByteBuffer.wrap(new byte[]{0, 0, bArr[13], bArr[12]}).getInt();
        int i3 = ByteBuffer.wrap(new byte[]{0, 0, bArr[15], bArr[14]}).getInt();
        int i4 = ByteBuffer.wrap(new byte[]{0, 0, bArr[17], bArr[16]}).getInt();
        int i5 = ByteBuffer.wrap(new byte[]{0, 0, bArr[19], bArr[18]}).getInt();
        SocialDistanceScanSettingsData socialDistanceScanSettingsData = new SocialDistanceScanSettingsData();
        socialDistanceScanSettingsData.setScanPeriod(s);
        socialDistanceScanSettingsData.setRssiFilter(b);
        socialDistanceScanSettingsData.setAddressFilter(format);
        socialDistanceScanSettingsData.setUuidFilter(i2);
        socialDistanceScanSettingsData.setScanInterval(i3);
        socialDistanceScanSettingsData.setScanWindow(i4);
        socialDistanceScanSettingsData.setScanTimeout(i5);
        return socialDistanceScanSettingsData;
    }

    public SocialDistanceScanSettingsData getSocialDistanceScanSettingsData() {
        return b();
    }
}
