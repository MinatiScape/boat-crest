package com.szabh.smable3.entity;

import com.bestmafen.baseble.data.BleWritable;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.Arrays;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class BleAddressSettings extends BleWritable {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int ITEM_LENGTH = 6;
    @NotNull
    private byte[] mAddress;

    /* loaded from: classes12.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public BleAddressSettings() {
        this(null, 1, null);
    }

    public /* synthetic */ BleAddressSettings(byte[] bArr, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new byte[6] : bArr);
    }

    public static /* synthetic */ BleAddressSettings copy$default(BleAddressSettings bleAddressSettings, byte[] bArr, int i, Object obj) {
        if ((i & 1) != 0) {
            bArr = bleAddressSettings.mAddress;
        }
        return bleAddressSettings.copy(bArr);
    }

    @NotNull
    public final byte[] component1() {
        return this.mAddress;
    }

    @NotNull
    public final BleAddressSettings copy(@NotNull byte[] mAddress) {
        Intrinsics.checkNotNullParameter(mAddress, "mAddress");
        return new BleAddressSettings(mAddress);
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public void encode() {
        super.encode();
        BleWritable.writeBytes$default(this, ArraysKt___ArraysKt.reversedArray(this.mAddress), null, 2, null);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof BleAddressSettings) && Intrinsics.areEqual(this.mAddress, ((BleAddressSettings) obj).mAddress);
    }

    @NotNull
    public final byte[] getMAddress() {
        return this.mAddress;
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public int getMLengthToWrite() {
        return 6;
    }

    public int hashCode() {
        return Arrays.hashCode(this.mAddress);
    }

    public final void setMAddress(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<set-?>");
        this.mAddress = bArr;
    }

    @NotNull
    public String toString() {
        return "BleAddressSettings(mAddress=" + Arrays.toString(this.mAddress) + HexStringBuilder.COMMENT_END_CHAR;
    }

    public BleAddressSettings(@NotNull byte[] mAddress) {
        Intrinsics.checkNotNullParameter(mAddress, "mAddress");
        this.mAddress = mAddress;
    }
}
