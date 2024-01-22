package com.polidea.rxandroidble2.internal.scan;

import android.os.Build;
import android.os.ParcelUuid;
import android.util.SparseArray;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import com.polidea.rxandroidble2.internal.util.ScanRecordParser;
import com.polidea.rxandroidble2.scan.ScanRecord;
import java.util.List;
import java.util.Map;
@RequiresApi(21)
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes12.dex */
public class ScanRecordImplNativeWrapper implements ScanRecord {

    /* renamed from: a  reason: collision with root package name */
    public final android.bluetooth.le.ScanRecord f13477a;
    public final ScanRecordParser b;

    public ScanRecordImplNativeWrapper(android.bluetooth.le.ScanRecord scanRecord, ScanRecordParser scanRecordParser) {
        this.f13477a = scanRecord;
        this.b = scanRecordParser;
    }

    @Override // com.polidea.rxandroidble2.scan.ScanRecord
    public int getAdvertiseFlags() {
        return this.f13477a.getAdvertiseFlags();
    }

    @Override // com.polidea.rxandroidble2.scan.ScanRecord
    public byte[] getBytes() {
        return this.f13477a.getBytes();
    }

    @Override // com.polidea.rxandroidble2.scan.ScanRecord
    @Nullable
    public String getDeviceName() {
        return this.f13477a.getDeviceName();
    }

    @Override // com.polidea.rxandroidble2.scan.ScanRecord
    public SparseArray<byte[]> getManufacturerSpecificData() {
        return this.f13477a.getManufacturerSpecificData();
    }

    @Override // com.polidea.rxandroidble2.scan.ScanRecord
    public Map<ParcelUuid, byte[]> getServiceData() {
        return this.f13477a.getServiceData();
    }

    @Override // com.polidea.rxandroidble2.scan.ScanRecord
    public List<ParcelUuid> getServiceSolicitationUuids() {
        if (Build.VERSION.SDK_INT >= 29) {
            return this.f13477a.getServiceSolicitationUuids();
        }
        return this.b.parseFromBytes(this.f13477a.getBytes()).getServiceSolicitationUuids();
    }

    @Override // com.polidea.rxandroidble2.scan.ScanRecord
    public List<ParcelUuid> getServiceUuids() {
        return this.f13477a.getServiceUuids();
    }

    @Override // com.polidea.rxandroidble2.scan.ScanRecord
    public int getTxPowerLevel() {
        return this.f13477a.getTxPowerLevel();
    }

    @Override // com.polidea.rxandroidble2.scan.ScanRecord
    @Nullable
    public byte[] getManufacturerSpecificData(int i) {
        return this.f13477a.getManufacturerSpecificData(i);
    }

    @Override // com.polidea.rxandroidble2.scan.ScanRecord
    @Nullable
    public byte[] getServiceData(ParcelUuid parcelUuid) {
        return this.f13477a.getServiceData(parcelUuid);
    }
}
