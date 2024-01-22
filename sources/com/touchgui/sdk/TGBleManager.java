package com.touchgui.sdk;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes12.dex */
public abstract class TGBleManager {

    /* loaded from: classes12.dex */
    public static final class Builder {
        private final Context context;
        public boolean disableAutoReconnect = false;
        public int otaRetryCount = 0;
        public int maxTimeoutErrorCount = 3;
        public int maxReconnectCount = 3;

        public Builder(@NonNull Context context) {
            this.context = context.getApplicationContext();
        }

        public TGBleManager build() {
            return new a(this.context, this);
        }

        public Builder disableAutoReconnect() {
            this.disableAutoReconnect = true;
            return this;
        }

        public Builder setMaxReconnectCount(int i) {
            this.maxReconnectCount = i;
            return this;
        }

        public Builder setMaxTimeoutErrorCount(int i) {
            this.maxTimeoutErrorCount = i;
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

    public abstract TGConnection getConnection(@NonNull String str);

    public abstract boolean hasConnection(@NonNull String str);

    public abstract void recycleConnection(@NonNull String str);

    public abstract void registerConnectCallback(TGConnectCallback tGConnectCallback);

    public abstract void unregisterConnectCallback(TGConnectCallback tGConnectCallback);

    public static void init(boolean z, @Nullable TGLogListener tGLogListener) {
        TGLogger.setDebug(z);
        TGLogger.setLogListener(tGLogListener);
    }
}
