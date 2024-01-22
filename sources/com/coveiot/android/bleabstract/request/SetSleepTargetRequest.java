package com.coveiot.android.bleabstract.request;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class SetSleepTargetRequest extends BleBaseRequest {
    public int f = 450;

    /* loaded from: classes2.dex */
    public static final class Builder {
        @Nullable

        /* renamed from: a  reason: collision with root package name */
        public Object f3549a;
        public int b = 450;

        @NotNull
        public final SetSleepTargetRequest build() {
            SetSleepTargetRequest setSleepTargetRequest = new SetSleepTargetRequest();
            setSleepTargetRequest.setTarget$bleabstract_release(this.b);
            return setSleepTargetRequest;
        }

        @Nullable
        public final Object getId$bleabstract_release() {
            return this.f3549a;
        }

        public final int getTarget$bleabstract_release() {
            return this.b;
        }

        @NotNull
        public final Builder setId() {
            return this;
        }

        public final void setId$bleabstract_release(@Nullable Object obj) {
            this.f3549a = obj;
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
