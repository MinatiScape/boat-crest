package com.szabh.smable3.entity;

import com.bestmafen.baseble.data.BleWritable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class BleSMSQuickReplyContent extends BleIdObject {
    public static final int CONTENT_MAX_LENGTH = 60;
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int ITEM_LENGTH = 61;
    @NotNull
    private String mContent;

    /* loaded from: classes12.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public BleSMSQuickReplyContent() {
        this(null, 1, null);
    }

    public /* synthetic */ BleSMSQuickReplyContent(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str);
    }

    public static /* synthetic */ BleSMSQuickReplyContent copy$default(BleSMSQuickReplyContent bleSMSQuickReplyContent, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = bleSMSQuickReplyContent.mContent;
        }
        return bleSMSQuickReplyContent.copy(str);
    }

    @NotNull
    public final String component1() {
        return this.mContent;
    }

    @NotNull
    public final BleSMSQuickReplyContent copy(@NotNull String mContent) {
        Intrinsics.checkNotNullParameter(mContent, "mContent");
        return new BleSMSQuickReplyContent(mContent);
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public void encode() {
        super.encode();
        writeInt8(getMId());
        BleWritable.writeStringWithFix$default(this, this.mContent, 60, null, 4, null);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof BleSMSQuickReplyContent) && Intrinsics.areEqual(this.mContent, ((BleSMSQuickReplyContent) obj).mContent);
    }

    @NotNull
    public final String getMContent() {
        return this.mContent;
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public int getMLengthToWrite() {
        return 61;
    }

    public int hashCode() {
        return this.mContent.hashCode();
    }

    public final void setMContent(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mContent = str;
    }

    @NotNull
    public String toString() {
        return "BleSMSQuickReplyContent(mId='" + getMId() + "',mContent='" + this.mContent + "')";
    }

    public BleSMSQuickReplyContent(@NotNull String mContent) {
        Intrinsics.checkNotNullParameter(mContent, "mContent");
        this.mContent = mContent;
    }
}
