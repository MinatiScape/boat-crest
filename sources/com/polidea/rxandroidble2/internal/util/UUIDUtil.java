package com.polidea.rxandroidble2.internal.util;

import android.os.ParcelUuid;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import com.polidea.rxandroidble2.scan.ScanRecord;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
@Deprecated
/* loaded from: classes12.dex */
public class UUIDUtil {
    public static final ParcelUuid BASE_UUID = new ParcelUuid(ScanRecordParser.BASE_UUID);
    public static final int UUID_BYTES_128_BIT = 16;
    public static final int UUID_BYTES_16_BIT = 2;
    public static final int UUID_BYTES_32_BIT = 4;

    /* renamed from: a  reason: collision with root package name */
    public final ScanRecordParser f13525a = new ScanRecordParser();

    public List<UUID> extractUUIDs(byte[] bArr) {
        return this.f13525a.extractUUIDs(bArr);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public ScanRecord parseFromBytes(byte[] bArr) {
        return this.f13525a.parseFromBytes(bArr);
    }

    @NonNull
    public Set<UUID> toDistinctSet(@Nullable UUID[] uuidArr) {
        if (uuidArr == null) {
            uuidArr = new UUID[0];
        }
        return new HashSet(Arrays.asList(uuidArr));
    }
}
