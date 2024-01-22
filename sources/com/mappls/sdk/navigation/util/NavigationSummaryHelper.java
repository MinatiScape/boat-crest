package com.mappls.sdk.navigation.util;

import androidx.annotation.Keep;
import com.mappls.sdk.navigation.NavLocation;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import timber.log.Timber;
@Keep
/* loaded from: classes11.dex */
public final class NavigationSummaryHelper {
    @NotNull
    public static final Companion Companion = new Companion(0);
    @Nullable
    private static NavigationSummaryHelper instance;
    private boolean isTollable;
    @Nullable
    private NavLocation lastLocation;
    @Nullable
    private NavLocation lastTollableLocation;
    private long navigationEndTime;
    private long navigationStartTime;
    private float speedCount;
    private float totalDistance;
    private float totalSpeed;
    private float totalTollableDistance;

    /* loaded from: classes11.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(int i) {
            this();
        }

        @JvmStatic
        @Keep
        @Nullable
        public final NavigationSummaryHelper getInstance() {
            if (NavigationSummaryHelper.instance == null) {
                NavigationSummaryHelper.instance = new NavigationSummaryHelper();
            }
            return NavigationSummaryHelper.instance;
        }
    }

    @JvmStatic
    @Keep
    @Nullable
    public static final NavigationSummaryHelper getInstance() {
        return Companion.getInstance();
    }

    public final float getAverageSpeed() {
        if (getTotalTimeTaken() > 0) {
            float f = this.totalDistance;
            if (f > 0.0f) {
                return f / getTotalTimeTaken();
            }
            return 0.0f;
        }
        return this.totalDistance;
    }

    @Nullable
    public final NavLocation getLastLocation() {
        return this.lastLocation;
    }

    @Nullable
    public final NavLocation getLastTollableLocation() {
        return this.lastTollableLocation;
    }

    public final float getTotalDistance() {
        return this.totalDistance;
    }

    public final int getTotalTimeTaken() {
        long j = this.navigationEndTime;
        if (j <= 0) {
            j = System.currentTimeMillis();
        }
        return (int) ((j - this.navigationStartTime) / 1000);
    }

    public final void navigationFinished() {
        this.navigationEndTime = System.currentTimeMillis();
    }

    public final void navigationStarted(@NotNull NavLocation location) {
        Intrinsics.checkNotNullParameter(location, "location");
        this.navigationStartTime = System.currentTimeMillis();
        this.lastLocation = location;
        this.totalSpeed = location.hasSpeed() ? location.getSpeed() : 0.0f;
        this.speedCount = 1.0f;
        this.totalDistance = 0.0f;
        this.navigationEndTime = -1L;
        this.totalTollableDistance = 0.0f;
        this.isTollable = false;
    }

    public final int onNewLocation(@NotNull NavLocation location, boolean z) {
        NavLocation navLocation;
        Intrinsics.checkNotNullParameter(location, "location");
        int i = -1;
        if (location.hasSpeed() && location.hasAccuracy()) {
            NavLocation navLocation2 = this.lastLocation;
            if (navLocation2 != null) {
                this.totalDistance = location.distanceTo(navLocation2) + this.totalDistance;
            }
            this.speedCount += 1.0f;
            this.totalSpeed = location.getSpeed() + this.totalSpeed;
            this.lastLocation = location;
            if (z) {
                if (!this.isTollable) {
                    Timber.d("Entering toll Road", new Object[0]);
                    i = 1;
                }
                if (this.isTollable && (navLocation = this.lastTollableLocation) != null) {
                    this.totalTollableDistance = location.distanceTo(navLocation) + this.totalTollableDistance;
                }
            } else {
                if (this.isTollable) {
                    Timber.d("Exiting toll Road", new Object[0]);
                    i = 2;
                }
                location = null;
            }
            this.lastTollableLocation = location;
            this.isTollable = z;
        }
        return i;
    }

    public final void reset() {
        this.lastLocation = null;
        this.navigationStartTime = 0L;
        this.navigationEndTime = 0L;
        this.speedCount = 0.0f;
        this.totalSpeed = 0.0f;
        this.totalDistance = 0.0f;
    }

    public final void setLastLocation(@Nullable NavLocation navLocation) {
        this.lastLocation = navLocation;
    }

    public final void setLastTollableLocation(@Nullable NavLocation navLocation) {
        this.lastTollableLocation = navLocation;
    }
}
