package com.szabh.smable3.entity;

import com.bestmafen.baseble.data.BleReadable;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.Arrays;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class BleBleAddress extends BleReadable {
    @NotNull
    private String mAddress;

    public BleBleAddress() {
        this(null, 1, null);
    }

    public BleBleAddress(@NotNull String mAddress) {
        Intrinsics.checkNotNullParameter(mAddress, "mAddress");
        this.mAddress = mAddress;
    }

    public static /* synthetic */ BleBleAddress copy$default(BleBleAddress bleBleAddress, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = bleBleAddress.mAddress;
        }
        return bleBleAddress.copy(str);
    }

    @NotNull
    public final String component1() {
        return this.mAddress;
    }

    @NotNull
    public final BleBleAddress copy(@NotNull String mAddress) {
        Intrinsics.checkNotNullParameter(mAddress, "mAddress");
        return new BleBleAddress(mAddress);
    }

    @Override // com.bestmafen.baseble.data.BleReadable
    public void decode() {
        super.decode();
        if (getMBytes() != null) {
            byte[] mBytes = getMBytes();
            Intrinsics.checkNotNull(mBytes);
            if (mBytes.length < 6) {
                return;
            }
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String format = String.format("%02X:%02X:%02X:%02X:%02X:%02X", Arrays.copyOf(new Object[]{Byte.valueOf(readInt8()), Byte.valueOf(readInt8()), Byte.valueOf(readInt8()), Byte.valueOf(readInt8()), Byte.valueOf(readInt8()), Byte.valueOf(readInt8())}, 6));
            Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
            this.mAddress = format;
        }
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof BleBleAddress) && Intrinsics.areEqual(this.mAddress, ((BleBleAddress) obj).mAddress);
    }

    @NotNull
    public final String getMAddress() {
        return this.mAddress;
    }

    public int hashCode() {
        return this.mAddress.hashCode();
    }

    public final void setMAddress(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mAddress = str;
    }

    @NotNull
    public String toString() {
        return "BleBleAddress(mAddress=" + this.mAddress + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ BleBleAddress(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str);
    }
}
