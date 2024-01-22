package com.szabh.smable3.entity;

import com.bestmafen.baseble.data.BleWritable;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class BleStreamPacket extends BleWritable {
    private final long mIndex;
    @NotNull
    private final byte[] mPacket;
    private final long mSize;
    private final int mType;
    @NotNull
    public static final Companion Companion = new Companion(null);
    private static long BUFFER_MAX_SIZE = 480;

    /* loaded from: classes12.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final long getBUFFER_MAX_SIZE() {
            return BleStreamPacket.BUFFER_MAX_SIZE;
        }

        public final void setBUFFER_MAX_SIZE(long j) {
            BleStreamPacket.BUFFER_MAX_SIZE = j;
        }
    }

    public BleStreamPacket(int i, long j, long j2, @NotNull byte[] mPacket) {
        Intrinsics.checkNotNullParameter(mPacket, "mPacket");
        this.mType = i;
        this.mSize = j;
        this.mIndex = j2;
        this.mPacket = mPacket;
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public void encode() {
        super.encode();
        writeInt8(this.mType);
        BleWritable.writeUInt32$default(this, this.mSize, null, 2, null);
        BleWritable.writeUInt32$default(this, this.mIndex, null, 2, null);
        BleWritable.writeBytes$default(this, this.mPacket, null, 2, null);
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public int getMLengthToWrite() {
        return this.mPacket.length + 9;
    }

    @NotNull
    public String toString() {
        return "BleStreamPacket(mType=" + this.mType + ", mSize=" + this.mSize + ", mIndex=" + this.mIndex + ", mPacket=" + this.mPacket.length + HexStringBuilder.COMMENT_END_CHAR;
    }
}
