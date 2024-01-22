package com.szabh.smable3.entity;

import com.bestmafen.baseble.data.BleWritable;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.TimeZone;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class BleTimeZone extends BleWritable {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int ITEM_LENGTH = 1;
    private int mOffset = TimeZone.getDefault().getOffset(System.currentTimeMillis());

    /* loaded from: classes12.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Override // com.bestmafen.baseble.data.BleReadable
    public void decode() {
        super.decode();
        this.mOffset = readInt8() * 1000 * 60 * 15;
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public void encode() {
        super.encode();
        writeInt8(((this.mOffset / 1000) / 60) / 15);
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public int getMLengthToWrite() {
        return 1;
    }

    @NotNull
    public String toString() {
        return "BleTimeZone(mOffset=" + this.mOffset + HexStringBuilder.COMMENT_END_CHAR;
    }
}
