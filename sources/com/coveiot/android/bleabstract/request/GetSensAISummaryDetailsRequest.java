package com.coveiot.android.bleabstract.request;

import com.coveiot.android.bleabstract.models.BleCommand;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class GetSensAISummaryDetailsRequest extends BleBaseRequest {
    @Nullable
    public String f;

    /* loaded from: classes2.dex */
    public static final class Builder {
        @Nullable

        /* renamed from: a  reason: collision with root package name */
        public String f3496a;
        @Nullable
        public Object b;

        @NotNull
        public final GetSensAISummaryDetailsRequest build() {
            GetSensAISummaryDetailsRequest getSensAISummaryDetailsRequest = new GetSensAISummaryDetailsRequest();
            getSensAISummaryDetailsRequest.setSessionID(this.f3496a);
            return getSensAISummaryDetailsRequest;
        }

        @Nullable
        public final Object getId$bleabstract_release() {
            return this.b;
        }

        @Nullable
        public final String getSessionID$bleabstract_release() {
            return this.f3496a;
        }

        public final void setId(@NotNull Object id) {
            Intrinsics.checkNotNullParameter(id, "id");
            this.b = id;
        }

        public final void setId$bleabstract_release(@Nullable Object obj) {
            this.b = obj;
        }

        @NotNull
        public final Builder setSessionID(@NotNull String sessionID) {
            Intrinsics.checkNotNullParameter(sessionID, "sessionID");
            this.f3496a = sessionID;
            return this;
        }

        public final void setSessionID$bleabstract_release(@Nullable String str) {
            this.f3496a = str;
        }
    }

    @Override // com.coveiot.android.bleabstract.request.BleBaseRequest
    @Nullable
    public BleCommand getBleCommand() {
        return BleCommand.GET_SENS_AI_SUMMARY_DETAILS;
    }

    @Nullable
    public final String getSessionID() {
        return this.f;
    }

    @Override // com.coveiot.android.bleabstract.request.BleBaseRequest
    public void setBleCommand(@Nullable BleCommand bleCommand) {
        super.setBleCommand(bleCommand);
    }

    public final void setSessionID(@Nullable String str) {
        this.f = str;
    }
}
