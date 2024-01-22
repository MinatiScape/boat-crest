package com.coveiot.android.bleabstract.request;

import com.coveiot.android.bleabstract.models.BleCommand;
import java.util.Date;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class GetCalorieDataRequest extends BleBaseRequest {
    @Nullable
    public Date f;
    @Nullable
    public Date g;

    /* loaded from: classes2.dex */
    public static final class Builder {
        @Nullable

        /* renamed from: a  reason: collision with root package name */
        public Date f3492a;
        @Nullable
        public Date b;
        @Nullable
        public Object c;

        @NotNull
        public final GetCalorieDataRequest build() {
            GetCalorieDataRequest getCalorieDataRequest = new GetCalorieDataRequest();
            getCalorieDataRequest.setEndDate(this.b);
            getCalorieDataRequest.setStartDate(this.f3492a);
            return getCalorieDataRequest;
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
            return this.f3492a;
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
            this.f3492a = startDate;
            return this;
        }

        public final void setStartDate$bleabstract_release(@Nullable Date date) {
            this.f3492a = date;
        }
    }

    @Override // com.coveiot.android.bleabstract.request.BleBaseRequest
    @Nullable
    public BleCommand getBleCommand() {
        return BleCommand.GET_CALORIE;
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
