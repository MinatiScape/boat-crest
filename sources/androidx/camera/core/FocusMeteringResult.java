package androidx.camera.core;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
/* loaded from: classes.dex */
public final class FocusMeteringResult {

    /* renamed from: a  reason: collision with root package name */
    public boolean f624a;

    public FocusMeteringResult(boolean z) {
        this.f624a = z;
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static FocusMeteringResult create(boolean z) {
        return new FocusMeteringResult(z);
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static FocusMeteringResult emptyInstance() {
        return new FocusMeteringResult(false);
    }

    public boolean isFocusSuccessful() {
        return this.f624a;
    }
}
