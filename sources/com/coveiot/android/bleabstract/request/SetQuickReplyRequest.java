package com.coveiot.android.bleabstract.request;

import com.coveiot.sdk.ble.api.model.BleQuickReplyModel;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class SetQuickReplyRequest extends BleBaseRequest {
    public final boolean f;
    @Nullable
    public final List<BleQuickReplyModel> g;

    /* loaded from: classes2.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public boolean f3543a;
        @Nullable
        public List<? extends BleQuickReplyModel> b;

        @NotNull
        public final SetQuickReplyRequest build() {
            return new SetQuickReplyRequest(this.f3543a, this.b);
        }

        @Nullable
        public final List<BleQuickReplyModel> getBleQuickReplyModelList() {
            return this.b;
        }

        public final boolean isQuickReply() {
            return this.f3543a;
        }

        public final void setBleQuickReplyModelList(@Nullable List<? extends BleQuickReplyModel> list) {
            this.b = list;
        }

        public final void setQuickReply(boolean z) {
            this.f3543a = z;
        }

        @NotNull
        public final Builder setQuickReply(boolean z, @Nullable List<? extends BleQuickReplyModel> list) {
            this.f3543a = z;
            this.b = list;
            return this;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SetQuickReplyRequest(boolean z, @Nullable List<? extends BleQuickReplyModel> list) {
        this.f = z;
        this.g = list;
    }

    @Nullable
    public final List<BleQuickReplyModel> getBleQuickReplyModelList() {
        return this.g;
    }

    public final boolean isQuickReply() {
        return this.f;
    }

    public /* synthetic */ SetQuickReplyRequest(boolean z, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, (i & 2) != 0 ? null : list);
    }
}
