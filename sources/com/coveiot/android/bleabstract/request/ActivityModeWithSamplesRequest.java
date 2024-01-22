package com.coveiot.android.bleabstract.request;

import com.coveiot.android.bleabstract.models.BleCommand;
import java.util.Date;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class ActivityModeWithSamplesRequest extends BleBaseRequest {
    @Nullable
    public Date f;
    @Nullable
    public Date g;

    @Override // com.coveiot.android.bleabstract.request.BleBaseRequest
    @Nullable
    public BleCommand getBleCommand() {
        return BleCommand.GET_ACTIVITY_SUMMARY;
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
