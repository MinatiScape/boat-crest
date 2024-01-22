package com.realsil.sdk.core.bluetooth.scanner;

import android.os.ParcelUuid;
import android.util.SparseArray;
import androidx.annotation.Nullable;
import com.realsil.sdk.core.bluetooth.utils.BluetoothUuid;
import com.realsil.sdk.core.utility.DataConverter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
/* loaded from: classes12.dex */
public final class SpecScanRecord {

    /* renamed from: a  reason: collision with root package name */
    public final int f13580a;
    @Nullable
    public final List<ParcelUuid> b;
    public final SparseArray<byte[]> c;
    public final Map<ParcelUuid, byte[]> d;
    public final int e;
    public final String f;
    public final byte[] g;

    public SpecScanRecord(List<ParcelUuid> list, SparseArray<byte[]> sparseArray, Map<ParcelUuid, byte[]> map, int i, int i2, String str, byte[] bArr) {
        this.b = list;
        this.c = sparseArray;
        this.d = map;
        this.f = str;
        this.f13580a = i;
        this.e = i2;
        this.g = bArr;
    }

    public static int a(byte[] bArr, int i, int i2, int i3, List<ParcelUuid> list) {
        while (i2 > 0) {
            list.add(BluetoothUuid.parseUuidFrom(a(bArr, i, i3)));
            i2 -= i3;
            i += i3;
        }
        return i;
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00a3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.realsil.sdk.core.bluetooth.scanner.SpecScanRecord parseFromBytes(byte[] r16) {
        /*
            Method dump skipped, instructions count: 242
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.sdk.core.bluetooth.scanner.SpecScanRecord.parseFromBytes(byte[]):com.realsil.sdk.core.bluetooth.scanner.SpecScanRecord");
    }

    public int getAdvertiseFlags() {
        return this.f13580a;
    }

    public byte[] getBytes() {
        return this.g;
    }

    @Nullable
    public String getDeviceName() {
        return this.f;
    }

    public SparseArray<byte[]> getManufacturerSpecificData() {
        return this.c;
    }

    public Map<ParcelUuid, byte[]> getServiceData() {
        return this.d;
    }

    public List<ParcelUuid> getServiceUuids() {
        return this.b;
    }

    public int getTxPowerLevel() {
        return this.e;
    }

    public String toString() {
        String sb;
        StringBuilder sb2 = new StringBuilder();
        sb2.append("ScanRecord [mAdvertiseFlags=");
        sb2.append(this.f13580a);
        sb2.append(", mServiceUuids=");
        sb2.append(this.b);
        sb2.append("\n, mManufacturerSpecificData=");
        SparseArray<byte[]> sparseArray = this.c;
        String str = "{}";
        if (sparseArray == null) {
            sb = "null";
        } else if (sparseArray.size() == 0) {
            sb = "{}";
        } else {
            StringBuilder sb3 = new StringBuilder();
            sb3.append('{');
            for (int i = 0; i < sparseArray.size(); i++) {
                byte[] valueAt = sparseArray.valueAt(i);
                int length = valueAt != null ? valueAt.length : 0;
                sb3.append(sparseArray.keyAt(i));
                sb3.append("=(");
                sb3.append(length);
                sb3.append(")");
                sb3.append(DataConverter.bytes2HexWithSeparate(valueAt));
            }
            sb3.append('}');
            sb = sb3.toString();
        }
        sb2.append(sb);
        sb2.append(", mServiceData=");
        Map<ParcelUuid, byte[]> map = this.d;
        if (map == null) {
            str = "null";
        } else if (!map.isEmpty()) {
            StringBuilder sb4 = new StringBuilder();
            sb4.append('{');
            Iterator<Map.Entry<ParcelUuid, byte[]>> it = map.entrySet().iterator();
            while (it.hasNext()) {
                ParcelUuid key = it.next().getKey();
                byte[] bArr = map.get(key);
                int length2 = bArr != null ? bArr.length : 0;
                sb4.append(key);
                sb4.append("=(");
                sb4.append(length2);
                sb4.append(")");
                sb4.append(DataConverter.bytes2HexWithSeparate(bArr));
                if (it.hasNext()) {
                    sb4.append(", ");
                }
            }
            sb4.append('}');
            str = sb4.toString();
        }
        sb2.append(str);
        sb2.append(", mTxPowerLevel=");
        sb2.append(this.e);
        sb2.append(", mDeviceName=");
        sb2.append(this.f);
        sb2.append("]");
        return sb2.toString();
    }

    @Nullable
    public byte[] getManufacturerSpecificData(int i) {
        SparseArray<byte[]> sparseArray = this.c;
        if (sparseArray != null) {
            return sparseArray.get(i);
        }
        return null;
    }

    @Nullable
    public byte[] getServiceData(ParcelUuid parcelUuid) {
        if (parcelUuid == null) {
            return null;
        }
        return this.d.get(parcelUuid);
    }

    public static byte[] a(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        return bArr2;
    }
}
