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
public final class BleLanguagePackVersion extends BleReadable {
    private int mLanguageCode;
    @NotNull
    private String mVersion;

    public BleLanguagePackVersion() {
        this(null, 0, 3, null);
    }

    public /* synthetic */ BleLanguagePackVersion(String str, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? "0.0.0" : str, (i2 & 2) != 0 ? 1 : i);
    }

    public static /* synthetic */ BleLanguagePackVersion copy$default(BleLanguagePackVersion bleLanguagePackVersion, String str, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = bleLanguagePackVersion.mVersion;
        }
        if ((i2 & 2) != 0) {
            i = bleLanguagePackVersion.mLanguageCode;
        }
        return bleLanguagePackVersion.copy(str, i);
    }

    @NotNull
    public final String component1() {
        return this.mVersion;
    }

    public final int component2() {
        return this.mLanguageCode;
    }

    @NotNull
    public final BleLanguagePackVersion copy(@NotNull String mVersion, int i) {
        Intrinsics.checkNotNullParameter(mVersion, "mVersion");
        return new BleLanguagePackVersion(mVersion, i);
    }

    @Override // com.bestmafen.baseble.data.BleReadable
    public void decode() {
        super.decode();
        if (getMBytes() != null) {
            byte[] mBytes = getMBytes();
            Intrinsics.checkNotNull(mBytes);
            if (mBytes.length == 4) {
                this.mVersion = ArraysKt___ArraysKt.joinToString$default(readBytes(3), (CharSequence) ".", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) new Function1<Byte, CharSequence>() { // from class: com.szabh.smable3.entity.BleLanguagePackVersion$decode$1
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ CharSequence invoke(Byte b) {
                        return invoke(b.byteValue());
                    }

                    @NotNull
                    public final CharSequence invoke(byte b) {
                        int i = b & 255;
                        return i > 9 ? BleConst.GetDeviceTime : String.valueOf(i);
                    }
                }, 30, (Object) null);
                this.mLanguageCode = readUInt8();
            }
        }
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BleLanguagePackVersion) {
            BleLanguagePackVersion bleLanguagePackVersion = (BleLanguagePackVersion) obj;
            return Intrinsics.areEqual(this.mVersion, bleLanguagePackVersion.mVersion) && this.mLanguageCode == bleLanguagePackVersion.mLanguageCode;
        }
        return false;
    }

    public final int getMLanguageCode() {
        return this.mLanguageCode;
    }

    @NotNull
    public final String getMVersion() {
        return this.mVersion;
    }

    public int hashCode() {
        return (this.mVersion.hashCode() * 31) + Integer.hashCode(this.mLanguageCode);
    }

    public final void setMLanguageCode(int i) {
        this.mLanguageCode = i;
    }

    public final void setMVersion(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mVersion = str;
    }

    @NotNull
    public String toString() {
        return "BleLanguagePackVersion(mVersion=" + this.mVersion + ", mLanguageCode=" + this.mLanguageCode + HexStringBuilder.COMMENT_END_CHAR;
    }

    public BleLanguagePackVersion(@NotNull String mVersion, int i) {
        Intrinsics.checkNotNullParameter(mVersion, "mVersion");
        this.mVersion = mVersion;
        this.mLanguageCode = i;
    }
}
