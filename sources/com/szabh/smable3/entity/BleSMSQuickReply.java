package com.szabh.smable3.entity;

import com.bestmafen.baseble.data.BleReadable;
import com.bestmafen.baseble.data.BleWritable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class BleSMSQuickReply extends BleWritable {
    private int mId;
    @NotNull
    private String mPhone;

    public BleSMSQuickReply() {
        this(0, null, 3, null);
    }

    public /* synthetic */ BleSMSQuickReply(int i, String str, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0 : i, (i2 & 2) != 0 ? "" : str);
    }

    public static /* synthetic */ BleSMSQuickReply copy$default(BleSMSQuickReply bleSMSQuickReply, int i, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = bleSMSQuickReply.mId;
        }
        if ((i2 & 2) != 0) {
            str = bleSMSQuickReply.mPhone;
        }
        return bleSMSQuickReply.copy(i, str);
    }

    public final int component1() {
        return this.mId;
    }

    @NotNull
    public final String component2() {
        return this.mPhone;
    }

    @NotNull
    public final BleSMSQuickReply copy(int i, @NotNull String mPhone) {
        Intrinsics.checkNotNullParameter(mPhone, "mPhone");
        return new BleSMSQuickReply(i, mPhone);
    }

    @Override // com.bestmafen.baseble.data.BleReadable
    public void decode() {
        super.decode();
        this.mId = readUInt8();
        this.mPhone = BleReadable.readString$default(this, 32, null, 2, null);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BleSMSQuickReply) {
            BleSMSQuickReply bleSMSQuickReply = (BleSMSQuickReply) obj;
            return this.mId == bleSMSQuickReply.mId && Intrinsics.areEqual(this.mPhone, bleSMSQuickReply.mPhone);
        }
        return false;
    }

    public final int getMId() {
        return this.mId;
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public int getMLengthToWrite() {
        return 33;
    }

    @NotNull
    public final String getMPhone() {
        return this.mPhone;
    }

    public int hashCode() {
        return (Integer.hashCode(this.mId) * 31) + this.mPhone.hashCode();
    }

    public final void setMId(int i) {
        this.mId = i;
    }

    public final void setMPhone(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mPhone = str;
    }

    @NotNull
    public String toString() {
        return "BleSMSQuickReply(mId=" + this.mId + ", mPhone='" + this.mPhone + "')";
    }

    public BleSMSQuickReply(int i, @NotNull String mPhone) {
        Intrinsics.checkNotNullParameter(mPhone, "mPhone");
        this.mId = i;
        this.mPhone = mPhone;
    }
}
