package com.polidea.rxandroidble2;

import android.support.v4.media.MediaDescriptionCompat;
import androidx.annotation.IntRange;
/* loaded from: classes9.dex */
public interface ConnectionParameters {
    @IntRange(from = MediaDescriptionCompat.BT_FOLDER_TYPE_YEARS, to = 3200)
    int getConnectionInterval();

    @IntRange(from = 0, to = 499)
    int getSlaveLatency();

    @IntRange(from = 10, to = 3200)
    int getSupervisionTimeout();
}
