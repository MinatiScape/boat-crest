package com.coveiot.android.remotecommandframework.alexa.utils;

import androidx.exifinterface.media.ExifInterface;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes6.dex */
public enum ResponseType {
    SUCCESS(ExifInterface.LATITUDE_SOUTH),
    ERROR(ExifInterface.LONGITUDE_EAST),
    ACK(ExifInterface.GPS_MEASUREMENT_IN_PROGRESS);
    
    @NotNull
    private final String status;

    ResponseType(String str) {
        this.status = str;
    }

    @NotNull
    public final String getStatus() {
        return this.status;
    }
}
