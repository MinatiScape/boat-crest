package com.coveiot.android.crpsdk;

import com.coveiot.coveaccess.constants.CoveApiConstants;
import com.crrepa.ble.conn.bean.CRPWatchFaceLayoutInfo;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\u000e\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\n"}, d2 = {"Lcom/coveiot/android/crpsdk/KhCRPUtil;", "", "", "type", "", "getActivityMode", "Lcom/crrepa/ble/conn/bean/CRPWatchFaceLayoutInfo$CompressionType;", "getWatchFaceLayoutCompressionType", "<init>", "()V", "crpSdk_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes3.dex */
public final class KhCRPUtil {
    @NotNull
    public static final KhCRPUtil INSTANCE = new KhCRPUtil();

    @Nullable
    public final String getActivityMode(int i) {
        if (i != 12) {
            if (i != 0) {
                switch (i) {
                    case 2:
                        return CoveApiConstants.CYCLE;
                    case 3:
                        return "SKIPPING";
                    case 4:
                        return "BADMINTON";
                    case 5:
                        return "BASKETBALL";
                    case 6:
                        return "FOOTBALL";
                    case 7:
                        return CoveApiConstants.SWIM;
                    case 8:
                        return CoveApiConstants.HIKING;
                    default:
                        return CoveApiConstants.RUN;
                }
            }
            return "WALK";
        }
        return "YOGA";
    }

    @NotNull
    public final CRPWatchFaceLayoutInfo.CompressionType getWatchFaceLayoutCompressionType(@NotNull String type) {
        Intrinsics.checkNotNullParameter(type, "type");
        CRPWatchFaceLayoutInfo.CompressionType compressionType = CRPWatchFaceLayoutInfo.CompressionType.LZO;
        if (Intrinsics.areEqual(type, compressionType.name())) {
            return compressionType;
        }
        CRPWatchFaceLayoutInfo.CompressionType compressionType2 = CRPWatchFaceLayoutInfo.CompressionType.RGB_DEDUPLICATION;
        if (!Intrinsics.areEqual(type, compressionType2.name())) {
            compressionType2 = CRPWatchFaceLayoutInfo.CompressionType.RGB_LINE;
            if (!Intrinsics.areEqual(type, compressionType2.name())) {
                compressionType2 = CRPWatchFaceLayoutInfo.CompressionType.ORIGINAL;
                if (!Intrinsics.areEqual(type, compressionType2.name())) {
                    return compressionType;
                }
            }
        }
        return compressionType2;
    }
}
