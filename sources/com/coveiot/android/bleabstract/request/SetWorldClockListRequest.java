package com.coveiot.android.bleabstract.request;

import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class SetWorldClockListRequest extends BleBaseRequest {
    @Nullable
    public List<SetWorldClockRequest> f;

    /* loaded from: classes2.dex */
    public static final class Builder {
        @Nullable

        /* renamed from: a  reason: collision with root package name */
        public List<SetWorldClockRequest> f3557a;

        @NotNull
        public final SetWorldClockListRequest build() {
            SetWorldClockListRequest setWorldClockListRequest = new SetWorldClockListRequest();
            setWorldClockListRequest.setWorldClockRequests(this.f3557a);
            return setWorldClockListRequest;
        }

        @Nullable
        public final List<SetWorldClockRequest> getSetWorldClockRequests() {
            return this.f3557a;
        }

        @NotNull
        public final Builder setSedentaryReminderList(@Nullable List<SetWorldClockRequest> list) {
            this.f3557a = list;
            return this;
        }

        public final void setSetWorldClockRequests(@Nullable List<SetWorldClockRequest> list) {
            this.f3557a = list;
        }
    }

    @Nullable
    public final List<SetWorldClockRequest> getWorldClockRequests() {
        return this.f;
    }

    public final void setWorldClockRequests(@Nullable List<SetWorldClockRequest> list) {
        this.f = list;
    }
}
