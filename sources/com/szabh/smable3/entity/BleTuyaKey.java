package com.szabh.smable3.entity;

import com.bestmafen.baseble.data.BleWritable;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.nio.charset.Charset;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class BleTuyaKey extends BleWritable {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int ITEM_LENGTH = 61;
    private static final int KEY_LENGTH = 32;
    private static final int MAC_LENGTH = 12;
    private static final int UUID_LENGTH = 16;
    @NotNull
    private String mKey;
    @NotNull
    private String mMac;
    private int mType;
    @NotNull
    private String mUuid;

    /* loaded from: classes12.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public BleTuyaKey() {
        this(0, null, null, null, 15, null);
    }

    public /* synthetic */ BleTuyaKey(int i, String str, String str2, String str3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0 : i, (i2 & 2) != 0 ? "" : str, (i2 & 4) != 0 ? "" : str2, (i2 & 8) != 0 ? "" : str3);
    }

    public static /* synthetic */ BleTuyaKey copy$default(BleTuyaKey bleTuyaKey, int i, String str, String str2, String str3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = bleTuyaKey.mType;
        }
        if ((i2 & 2) != 0) {
            str = bleTuyaKey.mUuid;
        }
        if ((i2 & 4) != 0) {
            str2 = bleTuyaKey.mKey;
        }
        if ((i2 & 8) != 0) {
            str3 = bleTuyaKey.mMac;
        }
        return bleTuyaKey.copy(i, str, str2, str3);
    }

    public final int component1() {
        return this.mType;
    }

    @NotNull
    public final String component2() {
        return this.mUuid;
    }

    @NotNull
    public final String component3() {
        return this.mKey;
    }

    @NotNull
    public final String component4() {
        return this.mMac;
    }

    @NotNull
    public final BleTuyaKey copy(int i, @NotNull String mUuid, @NotNull String mKey, @NotNull String mMac) {
        Intrinsics.checkNotNullParameter(mUuid, "mUuid");
        Intrinsics.checkNotNullParameter(mKey, "mKey");
        Intrinsics.checkNotNullParameter(mMac, "mMac");
        return new BleTuyaKey(i, mUuid, mKey, mMac);
    }

    @Override // com.bestmafen.baseble.data.BleReadable
    public void decode() {
        super.decode();
        this.mType = readInt8();
        Charset charset = Charsets.US_ASCII;
        this.mUuid = readString(16, charset);
        this.mKey = readString(32, charset);
        this.mMac = readString(12, charset);
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public void encode() {
        super.encode();
        writeInt8(this.mType);
        String str = this.mUuid;
        Charset charset = Charsets.US_ASCII;
        writeStringWithFix(str, 16, charset);
        writeStringWithFix(this.mKey, 32, charset);
        writeStringWithFix(this.mMac, 12, charset);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BleTuyaKey) {
            BleTuyaKey bleTuyaKey = (BleTuyaKey) obj;
            return this.mType == bleTuyaKey.mType && Intrinsics.areEqual(this.mUuid, bleTuyaKey.mUuid) && Intrinsics.areEqual(this.mKey, bleTuyaKey.mKey) && Intrinsics.areEqual(this.mMac, bleTuyaKey.mMac);
        }
        return false;
    }

    @NotNull
    public final String getMKey() {
        return this.mKey;
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public int getMLengthToWrite() {
        return 61;
    }

    @NotNull
    public final String getMMac() {
        return this.mMac;
    }

    public final int getMType() {
        return this.mType;
    }

    @NotNull
    public final String getMUuid() {
        return this.mUuid;
    }

    public int hashCode() {
        return (((((Integer.hashCode(this.mType) * 31) + this.mUuid.hashCode()) * 31) + this.mKey.hashCode()) * 31) + this.mMac.hashCode();
    }

    public final void setMKey(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mKey = str;
    }

    public final void setMMac(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mMac = str;
    }

    public final void setMType(int i) {
        this.mType = i;
    }

    public final void setMUuid(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mUuid = str;
    }

    @NotNull
    public String toString() {
        return "BleTuyaKey(key=" + this.mUuid + this.mKey + this.mMac + HexStringBuilder.COMMENT_END_CHAR;
    }

    public BleTuyaKey(int i, @NotNull String mUuid, @NotNull String mKey, @NotNull String mMac) {
        Intrinsics.checkNotNullParameter(mUuid, "mUuid");
        Intrinsics.checkNotNullParameter(mKey, "mKey");
        Intrinsics.checkNotNullParameter(mMac, "mMac");
        this.mType = i;
        this.mUuid = mUuid;
        this.mKey = mKey;
        this.mMac = mMac;
    }
}
