package com.coveiot.android.bleabstract.request;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class DeleteScheduleRequest extends BleBaseRequest {
    public int f;

    public DeleteScheduleRequest(int i) {
        this.f = i;
    }

    public static /* synthetic */ DeleteScheduleRequest copy$default(DeleteScheduleRequest deleteScheduleRequest, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = deleteScheduleRequest.f;
        }
        return deleteScheduleRequest.copy(i);
    }

    public final int component1() {
        return this.f;
    }

    @NotNull
    public final DeleteScheduleRequest copy(int i) {
        return new DeleteScheduleRequest(i);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof DeleteScheduleRequest) && this.f == ((DeleteScheduleRequest) obj).f;
    }

    public final int getScheduleId() {
        return this.f;
    }

    public int hashCode() {
        return Integer.hashCode(this.f);
    }

    public final void setScheduleId(int i) {
        this.f = i;
    }

    @NotNull
    public String toString() {
        return "DeleteScheduleRequest(scheduleId=" + this.f + HexStringBuilder.COMMENT_END_CHAR;
    }
}
