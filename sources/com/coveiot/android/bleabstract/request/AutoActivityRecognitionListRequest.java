package com.coveiot.android.bleabstract.request;

import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class AutoActivityRecognitionListRequest extends BleBaseRequest {
    @Nullable
    public List<AutoActivityRecognitionSettingRequest> f;

    /* loaded from: classes2.dex */
    public static final class Builder {
        @Nullable

        /* renamed from: a  reason: collision with root package name */
        public List<AutoActivityRecognitionSettingRequest> f3474a;

        @NotNull
        public final AutoActivityRecognitionListRequest build() {
            AutoActivityRecognitionListRequest autoActivityRecognitionListRequest = new AutoActivityRecognitionListRequest();
            autoActivityRecognitionListRequest.setAutoRecogListRequests(this.f3474a);
            return autoActivityRecognitionListRequest;
        }

        @Nullable
        public final List<AutoActivityRecognitionSettingRequest> getSetAutoActivityRecognitionRequests() {
            return this.f3474a;
        }

        @NotNull
        public final Builder setAutoActivityRecognitionList(@Nullable List<AutoActivityRecognitionSettingRequest> list) {
            this.f3474a = list;
            return this;
        }

        public final void setSetAutoActivityRecognitionRequests(@Nullable List<AutoActivityRecognitionSettingRequest> list) {
            this.f3474a = list;
        }
    }

    @Nullable
    public final List<AutoActivityRecognitionSettingRequest> getAutoRecogListRequests() {
        return this.f;
    }

    public final void setAutoRecogListRequests(@Nullable List<AutoActivityRecognitionSettingRequest> list) {
        this.f = list;
    }
}
