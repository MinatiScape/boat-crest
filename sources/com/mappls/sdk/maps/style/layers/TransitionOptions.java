package com.mappls.sdk.maps.style.layers;

import androidx.annotation.Keep;
/* loaded from: classes11.dex */
public class TransitionOptions {
    @Keep
    private long delay;
    @Keep
    private long duration;
    @Keep
    private boolean enablePlacementTransitions;

    public TransitionOptions(long j, long j2) {
        this(j, j2, true);
    }

    @Keep
    @Deprecated
    public static TransitionOptions fromTransitionOptions(long j, long j2) {
        return new TransitionOptions(j, j2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TransitionOptions transitionOptions = (TransitionOptions) obj;
        return this.duration == transitionOptions.duration && this.delay == transitionOptions.delay && this.enablePlacementTransitions == transitionOptions.enablePlacementTransitions;
    }

    public long getDelay() {
        return this.delay;
    }

    public long getDuration() {
        return this.duration;
    }

    public int hashCode() {
        long j = this.duration;
        long j2 = this.delay;
        return (((((int) (j ^ (j >>> 32))) * 31) + ((int) ((j2 >>> 32) ^ j2))) * 31) + (this.enablePlacementTransitions ? 1 : 0);
    }

    public boolean isEnablePlacementTransitions() {
        return this.enablePlacementTransitions;
    }

    public String toString() {
        return "TransitionOptions{duration=" + this.duration + ", delay=" + this.delay + ", enablePlacementTransitions=" + this.enablePlacementTransitions + '}';
    }

    public TransitionOptions(long j, long j2, boolean z) {
        this.duration = j;
        this.delay = j2;
        this.enablePlacementTransitions = z;
    }

    @Keep
    public static TransitionOptions fromTransitionOptions(long j, long j2, boolean z) {
        return new TransitionOptions(j, j2, z);
    }
}
