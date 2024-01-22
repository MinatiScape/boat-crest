package com.coveiot.android.tappy.model;

import java.util.List;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class GetStepUpOptionsResponse {
    @Nullable
    private List<StepUpRequest> stepUpOptions;

    @Nullable
    public final List<StepUpRequest> getStepUpOptions() {
        return this.stepUpOptions;
    }

    public final void setStepUpOptions(@Nullable List<StepUpRequest> list) {
        this.stepUpOptions = list;
    }
}
