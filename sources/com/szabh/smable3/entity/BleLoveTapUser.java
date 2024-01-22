package com.szabh.smable3.entity;

import com.bestmafen.baseble.data.BleReadable;
import com.bestmafen.baseble.data.BleWritable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class BleLoveTapUser extends BleIdObject {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int ITEM_LENGTH = 28;
    private static final int NAME_LENGTH = 24;
    @NotNull
    private String mName;

    /* loaded from: classes12.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public BleLoveTapUser() {
        this(null, 1, null);
    }

    public /* synthetic */ BleLoveTapUser(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str);
    }

    public static /* synthetic */ BleLoveTapUser copy$default(BleLoveTapUser bleLoveTapUser, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = bleLoveTapUser.mName;
        }
        return bleLoveTapUser.copy(str);
    }

    @NotNull
    public final String component1() {
        return this.mName;
    }

    @NotNull
    public final BleLoveTapUser copy(@NotNull String mName) {
        Intrinsics.checkNotNullParameter(mName, "mName");
        return new BleLoveTapUser(mName);
    }

    @Override // com.bestmafen.baseble.data.BleReadable
    public void decode() {
        super.decode();
        setMId(readUInt8());
        BleReadable.readInt24$default(this, null, 1, null);
        this.mName = BleReadable.readString$default(this, 24, null, 2, null);
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public void encode() {
        super.encode();
        writeInt8(getMId());
        BleWritable.writeInt24$default(this, 0, null, 2, null);
        BleWritable.writeStringWithFix$default(this, this.mName, 24, null, 4, null);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof BleLoveTapUser) && Intrinsics.areEqual(this.mName, ((BleLoveTapUser) obj).mName);
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public int getMLengthToWrite() {
        return 28;
    }

    @NotNull
    public final String getMName() {
        return this.mName;
    }

    public int hashCode() {
        return this.mName.hashCode();
    }

    public final void setMName(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mName = str;
    }

    @NotNull
    public String toString() {
        return "BleLoveTapUser(mId=" + getMId() + ", mName='" + this.mName + "')";
    }

    public BleLoveTapUser(@NotNull String mName) {
        Intrinsics.checkNotNullParameter(mName, "mName");
        this.mName = mName;
    }
}
