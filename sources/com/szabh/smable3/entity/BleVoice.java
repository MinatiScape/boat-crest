package com.szabh.smable3.entity;

import com.bestmafen.baseble.data.BleWritable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class BleVoice extends BleWritable {
    public static final int CATEGORY_INPUT_TEXT = 0;
    public static final int CATEGORY_OUTPUT_TEXT = 1;
    public static final int CONTENT_MAX_LENGTH = 512;
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int STATUS_DONE = 1;
    private int mCategory;
    @NotNull
    private String mContent;
    private int mStatus;
    private long mTime;

    /* loaded from: classes12.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public BleVoice() {
        this(0, 0L, null, 0, 15, null);
    }

    public /* synthetic */ BleVoice(int i, long j, String str, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 0 : i, (i3 & 2) != 0 ? 0L : j, (i3 & 4) != 0 ? "" : str, (i3 & 8) != 0 ? 0 : i2);
    }

    public static /* synthetic */ BleVoice copy$default(BleVoice bleVoice, int i, long j, String str, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = bleVoice.mCategory;
        }
        if ((i3 & 2) != 0) {
            j = bleVoice.mTime;
        }
        long j2 = j;
        if ((i3 & 4) != 0) {
            str = bleVoice.mContent;
        }
        String str2 = str;
        if ((i3 & 8) != 0) {
            i2 = bleVoice.mStatus;
        }
        return bleVoice.copy(i, j2, str2, i2);
    }

    public final int component1() {
        return this.mCategory;
    }

    public final long component2() {
        return this.mTime;
    }

    @NotNull
    public final String component3() {
        return this.mContent;
    }

    public final int component4() {
        return this.mStatus;
    }

    @NotNull
    public final BleVoice copy(int i, long j, @NotNull String mContent, int i2) {
        Intrinsics.checkNotNullParameter(mContent, "mContent");
        return new BleVoice(i, j, mContent, i2);
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public void encode() {
        super.encode();
        writeInt8(this.mCategory);
        BleWritable.writeBytes$default(this, new byte[7], null, 2, null);
        writeObject(BleTime.Companion.ofLocal(this.mTime));
        byte[] bytes = this.mContent.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        BleWritable.writeInt16$default(this, Math.min(bytes.length, 512), null, 2, null);
        BleWritable.writeStringWithLimit$default(this, this.mContent, 512, null, true, 4, null);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BleVoice) {
            BleVoice bleVoice = (BleVoice) obj;
            return this.mCategory == bleVoice.mCategory && this.mTime == bleVoice.mTime && Intrinsics.areEqual(this.mContent, bleVoice.mContent) && this.mStatus == bleVoice.mStatus;
        }
        return false;
    }

    public final int getMCategory() {
        return this.mCategory;
    }

    @NotNull
    public final String getMContent() {
        return this.mContent;
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public int getMLengthToWrite() {
        byte[] bytes = this.mContent.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        return Math.min(bytes.length, 512) + 16;
    }

    public final int getMStatus() {
        return this.mStatus;
    }

    public final long getMTime() {
        return this.mTime;
    }

    public int hashCode() {
        return (((((Integer.hashCode(this.mCategory) * 31) + Long.hashCode(this.mTime)) * 31) + this.mContent.hashCode()) * 31) + Integer.hashCode(this.mStatus);
    }

    public final void setMCategory(int i) {
        this.mCategory = i;
    }

    public final void setMContent(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mContent = str;
    }

    public final void setMStatus(int i) {
        this.mStatus = i;
    }

    public final void setMTime(long j) {
        this.mTime = j;
    }

    @NotNull
    public String toString() {
        return "BleAudioText(mCategory=" + this.mCategory + ", mTime=" + this.mTime + ", mContent='" + this.mContent + ", mStatus=" + this.mStatus + "')";
    }

    public BleVoice(int i, long j, @NotNull String mContent, int i2) {
        Intrinsics.checkNotNullParameter(mContent, "mContent");
        this.mCategory = i;
        this.mTime = j;
        this.mContent = mContent;
        this.mStatus = i2;
    }
}
