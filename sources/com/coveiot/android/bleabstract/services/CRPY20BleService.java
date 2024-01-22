package com.coveiot.android.bleabstract.services;

import android.os.Binder;
import com.coveiot.android.crpsdk.BleConnectStateChangeEvent;
import com.squareup.otto.Subscribe;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class CRPY20BleService extends CRPBaseBleService {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final String G = CRPY20BleService.class.getSimpleName();

    /* loaded from: classes2.dex */
    public final class BtServiceBinder extends Binder {
        public BtServiceBinder() {
        }

        @NotNull
        public final CRPBaseBleService getService() {
            return CRPY20BleService.this;
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
            return CRPY20BleService.G;
        }
    }

    public CRPY20BleService() {
        setServiceBinder(new BtServiceBinder());
    }

    @Subscribe
    public final void onConnectionStateChanged(@NotNull BleConnectStateChangeEvent bleConnectionStateChangeEvent) {
        Intrinsics.checkNotNullParameter(bleConnectionStateChangeEvent, "bleConnectionStateChangeEvent");
        super.onConnectionStateChangedHandler(bleConnectionStateChangeEvent);
    }
}
