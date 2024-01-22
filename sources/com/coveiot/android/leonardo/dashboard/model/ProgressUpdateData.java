package com.coveiot.android.leonardo.dashboard.model;

import java.io.Serializable;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class ProgressUpdateData implements Serializable {
    private int percentBySatge;
    private int progressPercent;
    private boolean shouldShowSubText;
    @Nullable
    private String textProgress;

    public final int getPercentBySatge() {
        return this.percentBySatge;
    }

    public final int getProgressPercent() {
        return this.progressPercent;
    }

    public final boolean getShouldShowSubText() {
        return this.shouldShowSubText;
    }

    @Nullable
    public final String getTextProgress() {
        return this.textProgress;
    }

    public final void setPercentBySatge(int i) {
        this.percentBySatge = i;
    }

    public final void setProgressPercent(int i) {
        this.progressPercent = i;
    }

    public final void setShouldShowSubText(boolean z) {
        this.shouldShowSubText = z;
    }

    public final void setTextProgress(@Nullable String str) {
        this.textProgress = str;
    }
}
