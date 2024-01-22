package com.szabh.smable3.entity;

import com.bestmafen.baseble.data.BleWritable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class BleBtName extends BleWritable {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int ITEM_LENGTH = 31;
    private static final int NAME_LENGTH = 29;
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

    public BleBtName() {
        this(null, 1, null);
    }

    public /* synthetic */ BleBtName(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str);
    }

    public static /* synthetic */ BleBtName copy$default(BleBtName bleBtName, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = bleBtName.mName;
        }
        return bleBtName.copy(str);
    }

    @NotNull
    public final String component1() {
        return this.mName;
    }

    @NotNull
    public final BleBtName copy(@NotNull String mName) {
        Intrinsics.checkNotNullParameter(mName, "mName");
        return new BleBtName(mName);
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public void encode() {
        super.encode();
        writeInt8(this.mName.length());
        writeStringWithFix(this.mName, 29, Charsets.US_ASCII);
        writeInt8(0);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof BleBtName) && Intrinsics.areEqual(this.mName, ((BleBtName) obj).mName);
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public int getMLengthToWrite() {
        return 31;
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
        return "BleBtName(mName='" + this.mName + "')";
    }

    public BleBtName(@NotNull String mName) {
        Intrinsics.checkNotNullParameter(mName, "mName");
        this.mName = mName;
    }
}
