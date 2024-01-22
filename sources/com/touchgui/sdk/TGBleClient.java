package com.touchgui.sdk;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.touchgui.sdk.internal.ia;
/* loaded from: classes12.dex */
public class TGBleClient {

    /* loaded from: classes12.dex */
    public static final class Builder {
        private final Context context;
        private boolean disableAutoReconnect = false;
        private int otaRetryCount = 0;
        private int otaDelayMillis = 5;
        private int maxTimeoutErrorCount = 3;
        private int maxReconnectCount = 3;

        public Builder(@NonNull Context context) {
            this.context = context.getApplicationContext();
        }

        public TGClient build() {
            return innerBuild();
        }

        public Builder disableAutoReconnect() {
            this.disableAutoReconnect = true;
            return this;
        }

        public com.touchgui.sdk.internal.b0 innerBuild() {
            com.touchgui.sdk.internal.z zVar = new com.touchgui.sdk.internal.z(this.context);
            if (this.disableAutoReconnect) {
                zVar.c = false;
            }
            zVar.d = this.otaRetryCount;
            if (zVar.b == null) {
                zVar.b = new com.touchgui.sdk.internal.j(zVar.f13849a);
            }
            return new com.touchgui.sdk.internal.b0(new com.touchgui.sdk.internal.a0(zVar));
        }

        public Builder setMaxReconnectCount(int i) {
            this.maxReconnectCount = i;
            return this;
        }

        public Builder setMaxTimeoutErrorCount(int i) {
            this.maxTimeoutErrorCount = i;
            return this;
        }

        public Builder setOtaDelayMillis(int i) {
            this.otaDelayMillis = i;
            return this;
        }

        public Builder setOtaRetryCount(int i) {
            this.otaRetryCount = i;
            return this;
        }
    }

    public static void init(boolean z) {
        init(z, null);
    }

    public static Builder newBuilder(@NonNull Context context) {
        return new Builder(context);
    }

    public static TGScanner newScanner(Context context, int i) {
        return newScanner(context, i, true, null);
    }

    public static void init(boolean z, @Nullable TGLogListener tGLogListener) {
        TGLogger.setDebug(z);
        TGLogger.setLogListener(tGLogListener);
    }

    public static TGScanner newScanner(Context context, int i, boolean z, String str) {
        ia iaVar = new ia(context);
        iaVar.b = z;
        iaVar.c = str;
        iaVar.d = i;
        return new n(iaVar);
    }
}
