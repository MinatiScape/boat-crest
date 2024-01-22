package com.szabh.smable3.entity;

import com.bestmafen.baseble.data.BleReadable;
import com.bestmafen.baseble.data.BleWritable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class BleWorldClock extends BleIdObject {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int ITEM_LENGTH = 68;
    private static final int NAME_MAX_LENGTH = 62;
    private int isLocal;
    @NotNull
    private String mCityName;
    private int mTimeZoneOffset;
    private int reversed;

    /* loaded from: classes12.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public BleWorldClock() {
        this(0, 0, 0, null, 15, null);
    }

    public /* synthetic */ BleWorldClock(int i, int i2, int i3, String str, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this((i4 & 1) != 0 ? 0 : i, (i4 & 2) != 0 ? 0 : i2, (i4 & 4) != 0 ? 0 : i3, (i4 & 8) != 0 ? "" : str);
    }

    public static /* synthetic */ BleWorldClock copy$default(BleWorldClock bleWorldClock, int i, int i2, int i3, String str, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i = bleWorldClock.isLocal;
        }
        if ((i4 & 2) != 0) {
            i2 = bleWorldClock.mTimeZoneOffset;
        }
        if ((i4 & 4) != 0) {
            i3 = bleWorldClock.reversed;
        }
        if ((i4 & 8) != 0) {
            str = bleWorldClock.mCityName;
        }
        return bleWorldClock.copy(i, i2, i3, str);
    }

    public final int component1() {
        return this.isLocal;
    }

    public final int component2() {
        return this.mTimeZoneOffset;
    }

    public final int component3() {
        return this.reversed;
    }

    @NotNull
    public final String component4() {
        return this.mCityName;
    }

    @NotNull
    public final BleWorldClock copy(int i, int i2, int i3, @NotNull String mCityName) {
        Intrinsics.checkNotNullParameter(mCityName, "mCityName");
        return new BleWorldClock(i, i2, i3, mCityName);
    }

    @Override // com.bestmafen.baseble.data.BleReadable
    public void decode() {
        super.decode();
        this.isLocal = readIntN(1);
        setMId(readIntN(7));
        this.mTimeZoneOffset = readInt8();
        this.reversed = BleReadable.readInt16$default(this, null, 1, null);
        this.mCityName = readString(62, Charsets.UTF_16LE);
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public void encode() {
        super.encode();
        writeIntN(this.isLocal, 1);
        writeIntN(getMId(), 7);
        writeInt8(this.mTimeZoneOffset);
        BleWritable.writeInt16$default(this, this.reversed, null, 2, null);
        writeStringWithFix(this.mCityName, 62, Charsets.UTF_16LE);
        BleWritable.writeInt16$default(this, 0, null, 2, null);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BleWorldClock) {
            BleWorldClock bleWorldClock = (BleWorldClock) obj;
            return this.isLocal == bleWorldClock.isLocal && this.mTimeZoneOffset == bleWorldClock.mTimeZoneOffset && this.reversed == bleWorldClock.reversed && Intrinsics.areEqual(this.mCityName, bleWorldClock.mCityName);
        }
        return false;
    }

    @NotNull
    public final String getMCityName() {
        return this.mCityName;
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public int getMLengthToWrite() {
        return 68;
    }

    public final int getMTimeZoneOffset() {
        return this.mTimeZoneOffset;
    }

    public final int getReversed() {
        return this.reversed;
    }

    public int hashCode() {
        return (((((Integer.hashCode(this.isLocal) * 31) + Integer.hashCode(this.mTimeZoneOffset)) * 31) + Integer.hashCode(this.reversed)) * 31) + this.mCityName.hashCode();
    }

    public final int isLocal() {
        return this.isLocal;
    }

    public final void setLocal(int i) {
        this.isLocal = i;
    }

    public final void setMCityName(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mCityName = str;
    }

    public final void setMTimeZoneOffset(int i) {
        this.mTimeZoneOffset = i;
    }

    public final void setReversed(int i) {
        this.reversed = i;
    }

    @NotNull
    public String toString() {
        return "BleWorldClock(mId=" + getMId() + ", isLocal=" + this.isLocal + ", mTimeZoneOffset=" + this.mTimeZoneOffset + ", mCityName='" + this.mCityName + "')";
    }

    public BleWorldClock(int i, int i2, int i3, @NotNull String mCityName) {
        Intrinsics.checkNotNullParameter(mCityName, "mCityName");
        this.isLocal = i;
        this.mTimeZoneOffset = i2;
        this.reversed = i3;
        this.mCityName = mCityName;
    }
}
