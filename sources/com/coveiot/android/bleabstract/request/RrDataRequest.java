package com.coveiot.android.bleabstract.request;

import com.coveiot.android.bleabstract.models.BleCommand;
import java.util.Date;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class RrDataRequest extends BleBaseRequest {
    @Nullable
    public Date f;
    @Nullable
    public Date g;
    public int h;
    public int i;

    /* loaded from: classes2.dex */
    public static final class Builder {
        @Nullable

        /* renamed from: a  reason: collision with root package name */
        public Date f3513a;
        @Nullable
        public Date b;
        public int c = -1;
        public int d = -1;
        @Nullable
        public Object e;

        @NotNull
        public final RrDataRequest build() {
            RrDataRequest rrDataRequest = new RrDataRequest();
            rrDataRequest.setEndDate$bleabstract_release(this.b);
            rrDataRequest.setStartHour$bleabstract_release(this.c);
            rrDataRequest.setStartDate$bleabstract_release(this.f3513a);
            rrDataRequest.setEndHour$bleabstract_release(this.d);
            return rrDataRequest;
        }

        @Nullable
        public final Date getEndDate$bleabstract_release() {
            return this.b;
        }

        public final int getEndHour$bleabstract_release() {
            return this.d;
        }

        @Nullable
        public final Object getId$bleabstract_release() {
            return this.e;
        }

        @Nullable
        public final Date getStartDate$bleabstract_release() {
            return this.f3513a;
        }

        public final int getStartHour$bleabstract_release() {
            return this.c;
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

        @NotNull
        public final Builder setEndHour(int i) {
            this.d = i;
            return this;
        }

        public final void setEndHour$bleabstract_release(int i) {
            this.d = i;
        }

        public final void setId(@NotNull Object id) {
            Intrinsics.checkNotNullParameter(id, "id");
            this.e = id;
        }

        public final void setId$bleabstract_release(@Nullable Object obj) {
            this.e = obj;
        }

        @NotNull
        public final Builder setStartDate(@NotNull Date startDate) {
            Intrinsics.checkNotNullParameter(startDate, "startDate");
            this.f3513a = startDate;
            return this;
        }

        public final void setStartDate$bleabstract_release(@Nullable Date date) {
            this.f3513a = date;
        }

        @NotNull
        public final Builder setStartHour(int i) {
            this.c = i;
            return this;
        }

        public final void setStartHour$bleabstract_release(int i) {
            this.c = i;
        }
    }

    @Override // com.coveiot.android.bleabstract.request.BleBaseRequest
    @Nullable
    public BleCommand getBleCommand() {
        return BleCommand.GET_ECG_RR_DATA;
    }

    @Nullable
    public final Date getEndDate() {
        return this.g;
    }

    public final int getEndHour() {
        return this.i;
    }

    @Nullable
    public final Date getStartDate() {
        return this.f;
    }

    public final int getStartHour() {
        return this.h;
    }

    @Override // com.coveiot.android.bleabstract.request.BleBaseRequest
    public void setBleCommand(@Nullable BleCommand bleCommand) {
        super.setBleCommand(bleCommand);
    }

    public final void setEndDate$bleabstract_release(@Nullable Date date) {
        this.g = date;
    }

    public final void setEndHour$bleabstract_release(int i) {
        this.i = i;
    }

    public final void setStartDate$bleabstract_release(@Nullable Date date) {
        this.f = date;
    }

    public final void setStartHour$bleabstract_release(int i) {
        this.h = i;
    }
}