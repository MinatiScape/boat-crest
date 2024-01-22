package com.coveiot.android.bleabstract.request;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class SetNavigationEventRequest extends BleBaseRequest {
    public boolean f;
    @NotNull
    public String g;
    @NotNull
    public String h;
    public int i;

    /* loaded from: classes2.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public boolean f3540a;
        @NotNull
        public String b = "";
        @NotNull
        public String c = "";
        public int d;

        @NotNull
        public final SetNavigationEventRequest build() {
            return new SetNavigationEventRequest(this, null);
        }

        @NotNull
        public final String getDestination() {
            return this.c;
        }

        public final boolean getEvent() {
            return this.f3540a;
        }

        public final int getMode() {
            return this.d;
        }

        @NotNull
        public final String getSource() {
            return this.b;
        }

        @NotNull
        public final Builder setDestination(@NotNull String destination) {
            Intrinsics.checkNotNullParameter(destination, "destination");
            this.c = destination;
            return this;
        }

        @NotNull
        public final Builder setEvent(boolean z) {
            this.f3540a = z;
            return this;
        }

        @NotNull
        public final Builder setMode(int i) {
            this.d = i;
            return this;
        }

        @NotNull
        public final Builder setSource(@NotNull String source) {
            Intrinsics.checkNotNullParameter(source, "source");
            this.b = source;
            return this;
        }
    }

    public SetNavigationEventRequest(Builder builder) {
        this.f = builder.getEvent();
        this.g = builder.getSource();
        this.h = builder.getDestination();
        this.i = builder.getMode();
    }

    public /* synthetic */ SetNavigationEventRequest(Builder builder, DefaultConstructorMarker defaultConstructorMarker) {
        this(builder);
    }

    @NotNull
    public final String getDestination() {
        return this.h;
    }

    public final boolean getEvent() {
        return this.f;
    }

    public final int getMode() {
        return this.i;
    }

    @NotNull
    public final String getSource() {
        return this.g;
    }
}
