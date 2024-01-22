package com.polidea.rxandroidble2;

import java.util.concurrent.TimeUnit;
/* loaded from: classes9.dex */
public class ConnectionSetup {
    public static final int DEFAULT_OPERATION_TIMEOUT = 30;
    public final boolean autoConnect;
    public final Timeout operationTimeout;
    public final boolean suppressOperationCheck;

    /* loaded from: classes9.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public boolean f13360a = false;
        public boolean b = false;
        public Timeout c = new Timeout(30, TimeUnit.SECONDS);

        public ConnectionSetup build() {
            return new ConnectionSetup(this.f13360a, this.b, this.c);
        }

        public Builder setAutoConnect(boolean z) {
            this.f13360a = z;
            return this;
        }

        public Builder setOperationTimeout(Timeout timeout) {
            this.c = timeout;
            return this;
        }

        public Builder setSuppressIllegalOperationCheck(boolean z) {
            this.b = z;
            return this;
        }
    }

    public ConnectionSetup(boolean z, boolean z2, Timeout timeout) {
        this.autoConnect = z;
        this.suppressOperationCheck = z2;
        this.operationTimeout = timeout;
    }
}
