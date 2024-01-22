package com.coveiot.android.bleabstract.services;

import android.os.Binder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class SmaF2BleService extends SmaBaseBleService {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final String l = SmaF2BleService.class.getSimpleName();

    /* loaded from: classes2.dex */
    public final class BtServiceBinder extends Binder {
        public BtServiceBinder() {
        }

        @NotNull
        public final SmaBaseBleService getService() {
            return SmaF2BleService.this;
        }
    }

    /* loaded from: classes2.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String getTAG() {
            return SmaF2BleService.l;
        }
    }

    public SmaF2BleService() {
        setServiceBinder(new BtServiceBinder());
    }

    @Override // com.coveiot.android.bleabstract.services.SmaBaseBleService, android.app.Service
    public void onCreate() {
        super.onCreate();
        createForegroundNotification();
    }
}
