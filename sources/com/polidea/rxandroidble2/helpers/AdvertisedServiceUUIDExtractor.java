package com.polidea.rxandroidble2.helpers;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.polidea.rxandroidble2.internal.util.ScanRecordParser;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
/* loaded from: classes9.dex */
public class AdvertisedServiceUUIDExtractor {

    /* renamed from: a  reason: collision with root package name */
    public final ScanRecordParser f13380a = new ScanRecordParser();

    public List<UUID> extractUUIDs(byte[] bArr) {
        return this.f13380a.extractUUIDs(bArr);
    }

    @NonNull
    public Set<UUID> toDistinctSet(@Nullable UUID[] uuidArr) {
        if (uuidArr == null) {
            uuidArr = new UUID[0];
        }
        return new HashSet(Arrays.asList(uuidArr));
    }
}
