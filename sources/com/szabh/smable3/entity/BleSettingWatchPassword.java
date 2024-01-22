package com.szabh.smable3.entity;

import com.bestmafen.baseble.data.BleReadable;
import com.bestmafen.baseble.data.BleWritable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class BleSettingWatchPassword extends BleWritable {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int ITEM_LENGTH = 5;
    private static final int PWD_LENGTH = 4;
    private int mEnabled;
    @NotNull
    private String mPassword;

    /* loaded from: classes12.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public BleSettingWatchPassword() {
        this(0, null, 3, null);
    }

    public /* synthetic */ BleSettingWatchPassword(int i, String str, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0 : i, (i2 & 2) != 0 ? "" : str);
    }

    public static /* synthetic */ BleSettingWatchPassword copy$default(BleSettingWatchPassword bleSettingWatchPassword, int i, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = bleSettingWatchPassword.mEnabled;
        }
        if ((i2 & 2) != 0) {
            str = bleSettingWatchPassword.mPassword;
        }
        return bleSettingWatchPassword.copy(i, str);
    }

    public final int component1() {
        return this.mEnabled;
    }

    @NotNull
    public final String component2() {
        return this.mPassword;
    }

    @NotNull
    public final BleSettingWatchPassword copy(int i, @NotNull String mPassword) {
        Intrinsics.checkNotNullParameter(mPassword, "mPassword");
        return new BleSettingWatchPassword(i, mPassword);
    }

    @Override // com.bestmafen.baseble.data.BleReadable
    public void decode() {
        super.decode();
        this.mEnabled = readUInt8();
        this.mPassword = BleReadable.readString$default(this, 4, null, 2, null);
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public void encode() {
        super.encode();
        writeInt8(this.mEnabled);
        BleWritable.writeStringWithFix$default(this, this.mPassword, 4, null, 4, null);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BleSettingWatchPassword) {
            BleSettingWatchPassword bleSettingWatchPassword = (BleSettingWatchPassword) obj;
            return this.mEnabled == bleSettingWatchPassword.mEnabled && Intrinsics.areEqual(this.mPassword, bleSettingWatchPassword.mPassword);
        }
        return false;
    }

    public final int getMEnabled() {
        return this.mEnabled;
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public int getMLengthToWrite() {
        return 5;
    }

    @NotNull
    public final String getMPassword() {
        return this.mPassword;
    }

    public int hashCode() {
        return (Integer.hashCode(this.mEnabled) * 31) + this.mPassword.hashCode();
    }

    public final void setMEnabled(int i) {
        this.mEnabled = i;
    }

    public final void setMPassword(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mPassword = str;
    }

    @NotNull
    public String toString() {
        return "BleSettingWatchPassword(mEnabled=" + this.mEnabled + ", mPassword='" + this.mPassword + "')";
    }

    public BleSettingWatchPassword(int i, @NotNull String mPassword) {
        Intrinsics.checkNotNullParameter(mPassword, "mPassword");
        this.mEnabled = i;
        this.mPassword = mPassword;
    }
}
