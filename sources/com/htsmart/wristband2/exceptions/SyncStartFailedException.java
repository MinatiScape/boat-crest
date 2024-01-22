package com.htsmart.wristband2.exceptions;
/* loaded from: classes11.dex */
public class SyncStartFailedException extends WristbandException {
    public static final int REASON_CHECKING_ECG = 1;
    public static final int REASON_SAVING_ECG = 2;
    public static final int REASON_UNKNOWN = 0;

    /* renamed from: a  reason: collision with root package name */
    private int f12022a;

    public SyncStartFailedException() {
        this(0);
    }

    public SyncStartFailedException(int i) {
        this.f12022a = i;
    }

    public int getReason() {
        return this.f12022a;
    }
}
