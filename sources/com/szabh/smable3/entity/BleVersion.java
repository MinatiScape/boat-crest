package com.szabh.smable3.entity;

import com.bestmafen.baseble.data.BleReadable;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.jstyle.blesdk1860.constant.BleConst;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class BleVersion extends BleReadable {
    @NotNull
    private String mVersion;

    public BleVersion() {
        this(null, 1, null);
    }

    public BleVersion(@NotNull String mVersion) {
        Intrinsics.checkNotNullParameter(mVersion, "mVersion");
        this.mVersion = mVersion;
    }

    public static /* synthetic */ BleVersion copy$default(BleVersion bleVersion, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = bleVersion.mVersion;
        }
        return bleVersion.copy(str);
    }

    @NotNull
    public final String component1() {
        return this.mVersion;
    }

    @NotNull
    public final BleVersion copy(@NotNull String mVersion) {
        Intrinsics.checkNotNullParameter(mVersion, "mVersion");
        return new BleVersion(mVersion);
    }

    @Override // com.bestmafen.baseble.data.BleReadable
    public void decode() {
        super.decode();
        byte[] mBytes = getMBytes();
        this.mVersion = (mBytes == null || (r0 = ArraysKt___ArraysKt.joinToString$default(mBytes, (CharSequence) ".", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) new Function1<Byte, CharSequence>() { // from class: com.szabh.smable3.entity.BleVersion$decode$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ CharSequence invoke(Byte b) {
                return invoke(b.byteValue());
            }

            @NotNull
            public final CharSequence invoke(byte b) {
                int i = b & 255;
                return i > 9 ? BleConst.GetDeviceTime : String.valueOf(i);
            }
        }, 30, (Object) null)) == null) ? "" : "";
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof BleVersion) && Intrinsics.areEqual(this.mVersion, ((BleVersion) obj).mVersion);
    }

    @NotNull
    public final String getMVersion() {
        return this.mVersion;
    }

    public int hashCode() {
        return this.mVersion.hashCode();
    }

    public final void setMVersion(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mVersion = str;
    }

    @NotNull
    public String toString() {
        return "BleVersion(mVersion=" + this.mVersion + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ BleVersion(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str);
    }
}
