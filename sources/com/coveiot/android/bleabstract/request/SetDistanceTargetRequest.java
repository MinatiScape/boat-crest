package com.coveiot.android.bleabstract.request;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class SetDistanceTargetRequest extends BleBaseRequest {
    public int f = 5000;

    /* loaded from: classes2.dex */
    public static final class Builder {
        @Nullable

        /* renamed from: a  reason: collision with root package name */
        public Object f3527a;
        public int b = 5000;

        @NotNull
        public final SetDistanceTargetRequest build() {
            SetDistanceTargetRequest setDistanceTargetRequest = new SetDistanceTargetRequest();
            setDistanceTargetRequest.setTarget$bleabstract_release(this.b);
            return setDistanceTargetRequest;
        }

        @Nullable
        public final Object getId$bleabstract_release() {
            return this.f3527a;
        }

        public final int getTarget$bleabstract_release() {
            return this.b;
        }

        @NotNull
        public final Builder setId() {
            return this;
        }

        public final void setId$bleabstract_release(@Nullable Object obj) {
            this.f3527a = obj;
        }

        @NotNull
        public final Builder setTarget(int i) {
            this.b = i;
            return this;
        }

        public final void setTarget$bleabstract_release(int i) {
            this.b = i;
        }
    }

    public final int getTarget() {
        return this.f;
    }

    public final int getTarget$bleabstract_release() {
        return this.f;
    }

    public final void setTarget$bleabstract_release(int i) {
        this.f = i;
    }
}
