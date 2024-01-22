package com.polidea.rxandroidble2.internal.scan;

import android.os.ParcelUuid;
import android.util.SparseArray;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import com.polidea.rxandroidble2.scan.ScanRecord;
import java.util.List;
import java.util.Map;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes12.dex */
public class ScanRecordImplCompat implements ScanRecord {

    /* renamed from: a  reason: collision with root package name */
    public final int f13476a;
    @Nullable
    public final List<ParcelUuid> b;
    @Nullable
    public final List<ParcelUuid> c;
    public final SparseArray<byte[]> d;
    public final Map<ParcelUuid, byte[]> e;
    public final int f;
    public final String g;
    public final byte[] h;

    public ScanRecordImplCompat(@Nullable List<ParcelUuid> list, @Nullable List<ParcelUuid> list2, SparseArray<byte[]> sparseArray, Map<ParcelUuid, byte[]> map, int i, int i2, String str, byte[] bArr) {
        this.b = list;
        this.c = list2;
        this.d = sparseArray;
        this.e = map;
        this.g = str;
        this.f13476a = i;
        this.f = i2;
        this.h = bArr;
    }

    @Override // com.polidea.rxandroidble2.scan.ScanRecord
    public int getAdvertiseFlags() {
        return this.f13476a;
    }

    @Override // com.polidea.rxandroidble2.scan.ScanRecord
    public byte[] getBytes() {
        return this.h;
    }

    @Override // com.polidea.rxandroidble2.scan.ScanRecord
    @Nullable
    public String getDeviceName() {
        return this.g;
    }

    @Override // com.polidea.rxandroidble2.scan.ScanRecord
    public SparseArray<byte[]> getManufacturerSpecificData() {
        return this.d;
    }

    @Override // com.polidea.rxandroidble2.scan.ScanRecord
    public Map<ParcelUuid, byte[]> getServiceData() {
        return this.e;
    }

    @Override // com.polidea.rxandroidble2.scan.ScanRecord
    @Nullable
    public List<ParcelUuid> getServiceSolicitationUuids() {
        return this.c;
    }

    @Override // com.polidea.rxandroidble2.scan.ScanRecord
    @Nullable
    public List<ParcelUuid> getServiceUuids() {
        return this.b;
    }

    @Override // com.polidea.rxandroidble2.scan.ScanRecord
    public int getTxPowerLevel() {
        return this.f;
    }

    @Override // com.polidea.rxandroidble2.scan.ScanRecord
    @Nullable
    public byte[] getManufacturerSpecificData(int i) {
        return this.d.get(i);
    }

    @Override // com.polidea.rxandroidble2.scan.ScanRecord
    @Nullable
    public byte[] getServiceData(ParcelUuid parcelUuid) {
        if (parcelUuid == null) {
            return null;
        }
        return this.e.get(parcelUuid);
    }
}
