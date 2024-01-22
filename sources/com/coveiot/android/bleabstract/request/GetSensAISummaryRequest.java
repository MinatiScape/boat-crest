package com.coveiot.android.bleabstract.request;

import com.coveiot.android.bleabstract.models.BleCommand;
import java.util.Date;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class GetSensAISummaryRequest extends BleBaseRequest {
    @Nullable
    public Date f;
    @Nullable
    public Date g;

    /* loaded from: classes2.dex */
    public static final class Builder {
        @Nullable

        /* renamed from: a  reason: collision with root package name */
        public Date f3497a;
        @Nullable
        public Date b;
        @Nullable
        public Object c;

        @NotNull
        public final GetSensAISummaryRequest build() {
            GetSensAISummaryRequest getSensAISummaryRequest = new GetSensAISummaryRequest();
            getSensAISummaryRequest.setEndDate(this.b);
            getSensAISummaryRequest.setStartDate(this.f3497a);
            return getSensAISummaryRequest;
        }

        @Nullable
        public final Date getEndDate$bleabstract_release() {
            return this.b;
        }

        @Nullable
        public final Object getId$bleabstract_release() {
            return this.c;
        }

        @Nullable
        public final Date getStartDate$bleabstract_release() {
            return this.f3497a;
        }

        @NotNull
        public final Builder setEndDate(@NotNull Date endDate) {
            Intrinsics.checkNotNullParameter(endDate, "endDate");
            this.b = endDate;
            return this;
        }

        public final void setEndDate$bleabstract_release(@Nullable Date date) {
            this.b = date;
        }

        public final void setId(@NotNull Object id) {
            Intrinsics.checkNotNullParameter(id, "id");
            this.c = id;
        }

        public final void setId$bleabstract_release(@Nullable Object obj) {
            this.c = obj;
        }

        @NotNull
        public final Builder setStartDate(@NotNull Date startDate) {
            Intrinsics.checkNotNullParameter(startDate, "startDate");
            this.f3497a = startDate;
            return this;
        }

        public final void setStartDate$bleabstract_release(@Nullable Date date) {
            this.f3497a = date;
        }
    }

    @Override // com.coveiot.android.bleabstract.request.BleBaseRequest
    @Nullable
    public BleCommand getBleCommand() {
        return BleCommand.GET_SENS_AI_SUMMARY_DATA;
    }

    @Nullable
    public final Date getEndDate() {
        return this.g;
    }

    @Nullable
    public final Date getStartDate() {
        return this.f;
    }

    @Override // com.coveiot.android.bleabstract.request.BleBaseRequest
    public void setBleCommand(@Nullable BleCommand bleCommand) {
        super.setBleCommand(bleCommand);
    }

    public final void setEndDate(@Nullable Date date) {
        this.g = date;
    }

    public final void setStartDate(@Nullable Date date) {
        this.f = date;
    }
}
