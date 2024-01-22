package com.coveiot.android.leonardo.quickreply.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class QuickReplyModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public String f5346a;

    public QuickReplyModel(@NotNull String quickReplyMessage) {
        Intrinsics.checkNotNullParameter(quickReplyMessage, "quickReplyMessage");
        this.f5346a = quickReplyMessage;
    }

    public static /* synthetic */ QuickReplyModel copy$default(QuickReplyModel quickReplyModel, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = quickReplyModel.f5346a;
        }
        return quickReplyModel.copy(str);
    }

    @NotNull
    public final String component1() {
        return this.f5346a;
    }

    @NotNull
    public final QuickReplyModel copy(@NotNull String quickReplyMessage) {
        Intrinsics.checkNotNullParameter(quickReplyMessage, "quickReplyMessage");
        return new QuickReplyModel(quickReplyMessage);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof QuickReplyModel) && Intrinsics.areEqual(this.f5346a, ((QuickReplyModel) obj).f5346a);
    }

    @NotNull
    public final String getQuickReplyMessage() {
        return this.f5346a;
    }

    public int hashCode() {
        return this.f5346a.hashCode();
    }

    public final void setQuickReplyMessage(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.f5346a = str;
    }

    @NotNull
    public String toString() {
        return "QuickReplyModel(quickReplyMessage=" + this.f5346a + HexStringBuilder.COMMENT_END_CHAR;
    }
}
