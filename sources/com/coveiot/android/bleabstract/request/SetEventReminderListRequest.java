package com.coveiot.android.bleabstract.request;

import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class SetEventReminderListRequest extends BleBaseRequest {
    @Nullable
    public List<SetEventReminderRequest> f;

    /* loaded from: classes2.dex */
    public static final class Builder {
        @Nullable

        /* renamed from: a  reason: collision with root package name */
        public List<SetEventReminderRequest> f3529a;

        @NotNull
        public final SetEventReminderListRequest build() {
            SetEventReminderListRequest setEventReminderListRequest = new SetEventReminderListRequest();
            setEventReminderListRequest.setReminderRequestList(this.f3529a);
            return setEventReminderListRequest;
        }

        @Nullable
        public final List<SetEventReminderRequest> getEventReminderRequestList() {
            return this.f3529a;
        }

        @NotNull
        public final Builder setEventReminderList(@Nullable List<SetEventReminderRequest> list) {
            this.f3529a = list;
            return this;
        }

        public final void setEventReminderRequestList(@Nullable List<SetEventReminderRequest> list) {
            this.f3529a = list;
        }
    }

    @Nullable
    public final List<SetEventReminderRequest> getReminderRequestList() {
        return this.f;
    }

    public final void setReminderRequestList(@Nullable List<SetEventReminderRequest> list) {
        this.f = list;
    }
}
