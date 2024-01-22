package com.bestmafen.baseble.messenger;

import com.bestmafen.baseble.connector.AbsBleConnector;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes.dex */
public abstract class AbsBleMessenger {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int DEFAULT_PACKET_SIZE = 20;
    public AbsBleConnector mAbsBleConnector;
    private int mPacketSize = 20;

    /* loaded from: classes.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public abstract void dequeueMessage();

    public abstract void dequeueWritePacket();

    public abstract void enqueueMessage(@NotNull BleMessage bleMessage);

    @NotNull
    public final AbsBleConnector getMAbsBleConnector() {
        AbsBleConnector absBleConnector = this.mAbsBleConnector;
        if (absBleConnector != null) {
            return absBleConnector;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mAbsBleConnector");
        return null;
    }

    public final int getMPacketSize() {
        return this.mPacketSize;
    }

    public abstract void reset();

    public final void setMAbsBleConnector(@NotNull AbsBleConnector absBleConnector) {
        Intrinsics.checkNotNullParameter(absBleConnector, "<set-?>");
        this.mAbsBleConnector = absBleConnector;
    }

    public final void setMPacketSize(int i) {
        this.mPacketSize = i;
    }
}
