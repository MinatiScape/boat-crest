package androidx.camera.core.impl;

import androidx.annotation.NonNull;
/* loaded from: classes.dex */
public final class CameraCaptureFailure {

    /* renamed from: a  reason: collision with root package name */
    public final Reason f691a;

    /* loaded from: classes.dex */
    public enum Reason {
        ERROR
    }

    public CameraCaptureFailure(@NonNull Reason reason) {
        this.f691a = reason;
    }

    @NonNull
    public Reason getReason() {
        return this.f691a;
    }
}
