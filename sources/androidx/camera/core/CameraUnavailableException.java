package androidx.camera.core;

import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes.dex */
public class CameraUnavailableException extends Exception {
    public static final int CAMERA_DISABLED = 1;
    public static final int CAMERA_DISCONNECTED = 2;
    public static final int CAMERA_ERROR = 3;
    public static final int CAMERA_IN_USE = 4;
    public static final int CAMERA_MAX_IN_USE = 5;
    public static final int CAMERA_UNAVAILABLE_DO_NOT_DISTURB = 6;
    public static final int CAMERA_UNKNOWN_ERROR = 0;
    private final int mReason;

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    /* loaded from: classes.dex */
    public @interface Reason {
    }

    public CameraUnavailableException(int i) {
        this.mReason = i;
    }

    public int getReason() {
        return this.mReason;
    }

    public CameraUnavailableException(int i, @Nullable String str) {
        super(str);
        this.mReason = i;
    }

    public CameraUnavailableException(int i, @Nullable String str, @Nullable Throwable th) {
        super(str, th);
        this.mReason = i;
    }

    public CameraUnavailableException(int i, @Nullable Throwable th) {
        super(th);
        this.mReason = i;
    }
}
