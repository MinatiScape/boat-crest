package com.coveiot.sdk.ble.api.request;

import com.coveiot.sdk.ble.api.BleUUID;
import com.coveiot.sdk.ble.utils.CommandNames;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
/* loaded from: classes9.dex */
public class SetSocialDistanceScanSettingsReq extends BaseRequest {
    public int f;
    public int g;
    public int h;
    public int i;
    public String j;
    public int k;
    public int l;
    public int m;
    public int n;

    /* loaded from: classes9.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public Object f7533a;
        public int b;
        public int c;
        public int d;
        public int e;
        public String f;
        public int g;
        public int h;
        public int i;
        public int j;

        public static Builder Builder() {
            return new Builder();
        }

        public SetSocialDistanceScanSettingsReq build() {
            SetSocialDistanceScanSettingsReq setSocialDistanceScanSettingsReq = new SetSocialDistanceScanSettingsReq(this.f7533a);
            setSocialDistanceScanSettingsReq.f = this.b;
            setSocialDistanceScanSettingsReq.g = this.c;
            setSocialDistanceScanSettingsReq.h = this.d;
            setSocialDistanceScanSettingsReq.i = this.e;
            setSocialDistanceScanSettingsReq.j = this.f;
            setSocialDistanceScanSettingsReq.k = this.g;
            setSocialDistanceScanSettingsReq.l = this.h;
            setSocialDistanceScanSettingsReq.m = this.i;
            setSocialDistanceScanSettingsReq.n = this.j;
            return setSocialDistanceScanSettingsReq;
        }

        public Builder setAddressFilter(String str) {
            this.f = str;
            return this;
        }

        public Builder setBandAlert(int i) {
            this.d = i;
            return this;
        }

        public void setId(Object obj) {
            this.f7533a = obj;
        }

        public Builder setRssiFilter(int i) {
            this.e = i;
            return this;
        }

        public Builder setScanInterval(int i) {
            this.h = i;
            return this;
        }

        public Builder setScanPeriod(int i) {
            this.b = i;
            return this;
        }

        public Builder setScanTimeout(int i) {
            this.j = i;
            return this;
        }

        public Builder setScanUnit(int i) {
            this.c = i;
            return this;
        }

        public Builder setScanWindow(int i) {
            this.i = i;
            return this;
        }

        public Builder setUuidFilter(int i) {
            this.g = i;
            return this;
        }
    }

    public final Long b() {
        if (this.j != null) {
            this.j = "ff:ff:ff:ff:ff:ff";
        }
        Long l = 0L;
        if (Pattern.compile("^([0-9a-fA-F]{2}:*){5}[0-9a-fA-F]{2}$").matcher(this.j).find()) {
            try {
                l = Long.valueOf(this.j.replace(":", ""), 16);
                if (l.longValue() < 0 || l.longValue() > 281474976710655L) {
                    LogHelper.e("ScanSettingsReq", "Address filter out of range!", ModuleNames.BLEABSTRACT.getModuleName());
                }
            } catch (Exception unused) {
                LogHelper.e("ScanSettingsReq", "Parsing address filter to long failed!", ModuleNames.BLEABSTRACT.getModuleName());
            }
        } else {
            LogHelper.e("ScanSettingsReq", "Invalid address filter!", ModuleNames.BLEABSTRACT.getModuleName());
        }
        return l;
    }

    public String getAddressFilter() {
        return this.j;
    }

    public int getBandAlert() {
        return this.h;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public List<CommandBytes> getCommandBytes() {
        ArrayList arrayList = new ArrayList();
        byte[] bArr = BleUUID.SET_SOCIAL_DISTANCE_SCAN_SETTINGS;
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 0, bArr2, 0, length);
        bArr2[4] = (byte) (this.f & 63);
        if (this.g == 1) {
            bArr2[4] = (byte) (bArr2[4] | 64);
        }
        if (this.h == 1) {
            bArr2[4] = (byte) (bArr2[4] | 128);
        }
        bArr2[5] = (byte) this.i;
        byte[] array = ByteBuffer.allocate(8).putLong(b().longValue()).array();
        bArr2[6] = array[2];
        bArr2[7] = array[3];
        bArr2[8] = array[4];
        bArr2[9] = array[5];
        bArr2[10] = array[6];
        bArr2[11] = array[7];
        byte[] array2 = ByteBuffer.allocate(2).putShort((short) this.k).array();
        bArr2[12] = array2[1];
        bArr2[13] = array2[0];
        byte[] array3 = ByteBuffer.allocate(2).putShort((short) this.l).array();
        bArr2[14] = array3[1];
        bArr2[15] = array3[0];
        byte[] array4 = ByteBuffer.allocate(2).putShort((short) this.m).array();
        bArr2[16] = array4[1];
        bArr2[17] = array4[0];
        byte[] array5 = ByteBuffer.allocate(2).putShort((short) this.n).array();
        bArr2[18] = array5[1];
        bArr2[19] = array5[0];
        CommandBytes commandBytes = new CommandBytes();
        commandBytes.setCommandData(bArr2);
        arrayList.add(commandBytes);
        return arrayList;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public CommandNames getCommandName() {
        return CommandNames.GET_SOCIAL_DISTANCE_SCAN_SETTINGS;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public String getGattCharacteristicToRead() {
        return null;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public String getGattServiceToRead() {
        return null;
    }

    public int getRssiFilter() {
        return this.i;
    }

    public int getScanInterval() {
        return this.l;
    }

    public int getScanPeriod() {
        return this.f;
    }

    public int getScanTimeout() {
        return this.n;
    }

    public int getScanUnit() {
        return this.g;
    }

    public int getScanWindow() {
        return this.m;
    }

    public int getUuidFilter() {
        return this.k;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public boolean isMultiPacket() {
        return false;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public boolean isPriority() {
        return true;
    }

    public SetSocialDistanceScanSettingsReq(Object obj) {
        super(obj);
    }
}
