package com.google.android.datatransport.runtime.backends;

import com.google.auto.value.AutoValue;
@AutoValue
/* loaded from: classes6.dex */
public abstract class BackendResponse {

    /* loaded from: classes6.dex */
    public enum Status {
        OK,
        TRANSIENT_ERROR,
        FATAL_ERROR
    }

    public static BackendResponse fatalError() {
        return new b(Status.FATAL_ERROR, -1L);
    }

    public static BackendResponse ok(long j) {
        return new b(Status.OK, j);
    }

    public static BackendResponse transientError() {
        return new b(Status.TRANSIENT_ERROR, -1L);
    }

    public abstract long getNextRequestWaitMillis();

    public abstract Status getStatus();
}
