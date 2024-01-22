package com.coveiot.covepreferences.data;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class QuickReplyListModel {

    /* renamed from: a  reason: collision with root package name */
    public boolean f7036a;
    @NotNull
    public List<String> b;

    public QuickReplyListModel(boolean z, @NotNull List<String> quickReplyMessage) {
        Intrinsics.checkNotNullParameter(quickReplyMessage, "quickReplyMessage");
        this.f7036a = z;
        this.b = quickReplyMessage;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ QuickReplyListModel copy$default(QuickReplyListModel quickReplyListModel, boolean z, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            z = quickReplyListModel.f7036a;
        }
        if ((i & 2) != 0) {
            list = quickReplyListModel.b;
        }
        return quickReplyListModel.copy(z, list);
    }

    public final boolean component1() {
        return this.f7036a;
    }

    @NotNull
    public final List<String> component2() {
        return this.b;
    }

    @NotNull
    public final QuickReplyListModel copy(boolean z, @NotNull List<String> quickReplyMessage) {
        Intrinsics.checkNotNullParameter(quickReplyMessage, "quickReplyMessage");
        return new QuickReplyListModel(z, quickReplyMessage);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof QuickReplyListModel) {
            QuickReplyListModel quickReplyListModel = (QuickReplyListModel) obj;
            return this.f7036a == quickReplyListModel.f7036a && Intrinsics.areEqual(this.b, quickReplyListModel.b);
        }
        return false;
    }

    @NotNull
    public final List<String> getQuickReplyMessage() {
        return this.b;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5 */
    public int hashCode() {
        boolean z = this.f7036a;
        ?? r0 = z;
        if (z) {
            r0 = 1;
        }
        return (r0 * 31) + this.b.hashCode();
    }

    public final boolean isQuickReplyEnabled() {
        return this.f7036a;
    }

    public final void setQuickReplyEnabled(boolean z) {
        this.f7036a = z;
    }

    public final void setQuickReplyMessage(@NotNull List<String> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.b = list;
    }

    @NotNull
    public String toString() {
        return "QuickReplyListModel(isQuickReplyEnabled=" + this.f7036a + ", quickReplyMessage=" + this.b + HexStringBuilder.COMMENT_END_CHAR;
    }
}
