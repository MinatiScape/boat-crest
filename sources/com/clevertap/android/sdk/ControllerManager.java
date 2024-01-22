package com.clevertap.android.sdk;

import android.content.Context;
import androidx.annotation.AnyThread;
import androidx.annotation.WorkerThread;
import com.clevertap.android.sdk.db.BaseDatabaseManager;
import com.clevertap.android.sdk.displayunits.CTDisplayUnitController;
import com.clevertap.android.sdk.featureFlags.CTFeatureFlagsController;
import com.clevertap.android.sdk.inapp.InAppController;
import com.clevertap.android.sdk.inbox.CTInboxController;
import com.clevertap.android.sdk.product_config.CTProductConfigController;
import com.clevertap.android.sdk.pushnotification.PushProviders;
import com.clevertap.android.sdk.task.CTExecutorFactory;
import com.clevertap.android.sdk.variables.CTVariables;
import com.clevertap.android.sdk.variables.callbacks.FetchVariablesCallback;
import java.util.concurrent.Callable;
/* loaded from: classes2.dex */
public class ControllerManager {

    /* renamed from: a  reason: collision with root package name */
    public InAppFCManager f2573a;
    public final BaseDatabaseManager b;
    public CTDisplayUnitController c;
    @Deprecated
    public CTFeatureFlagsController d;
    public CTInboxController e;
    public final CTLockManager f;
    @Deprecated
    public CTProductConfigController g;
    public final BaseCallbackManager h;
    public final CleverTapInstanceConfig i;
    public final Context j;
    public final DeviceInfo k;
    public InAppController l;
    public PushProviders m;
    public CTVariables n;

    /* loaded from: classes2.dex */
    public class a implements Callable<Void> {
        public a() {
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Void call() {
            ControllerManager.this.a();
            return null;
        }
    }

    public ControllerManager(Context context, CleverTapInstanceConfig cleverTapInstanceConfig, CTLockManager cTLockManager, BaseCallbackManager baseCallbackManager, DeviceInfo deviceInfo, BaseDatabaseManager baseDatabaseManager) {
        this.i = cleverTapInstanceConfig;
        this.f = cTLockManager;
        this.h = baseCallbackManager;
        this.k = deviceInfo;
        this.j = context;
        this.b = baseDatabaseManager;
    }

    @WorkerThread
    public final void a() {
        synchronized (this.f.getInboxControllerLock()) {
            if (getCTInboxController() != null) {
                this.h.a();
                return;
            }
            if (this.k.getDeviceID() != null) {
                setCTInboxController(new CTInboxController(this.i, this.k.getDeviceID(), this.b.loadDBAdapter(this.j), this.f, this.h, Utils.haveVideoPlayerSupport));
                this.h.a();
            } else {
                this.i.getLogger().info("CRITICAL : No device ID found!");
            }
        }
    }

    public CTDisplayUnitController getCTDisplayUnitController() {
        return this.c;
    }

    @Deprecated
    public CTFeatureFlagsController getCTFeatureFlagsController() {
        return this.d;
    }

    public CTInboxController getCTInboxController() {
        return this.e;
    }

    @Deprecated
    public CTProductConfigController getCTProductConfigController() {
        return this.g;
    }

    public CleverTapInstanceConfig getConfig() {
        return this.i;
    }

    public CTVariables getCtVariables() {
        return this.n;
    }

    public InAppController getInAppController() {
        return this.l;
    }

    public InAppFCManager getInAppFCManager() {
        return this.f2573a;
    }

    public PushProviders getPushProviders() {
        return this.m;
    }

    @AnyThread
    public void initializeInbox() {
        if (this.i.isAnalyticsOnly()) {
            this.i.getLogger().debug(this.i.getAccountId(), "Instance is analytics only, not initializing Notification Inbox");
        } else {
            CTExecutorFactory.executors(this.i).postAsyncSafelyTask().execute("initializeInbox", new a());
        }
    }

    public void invokeCallbacksForNetworkError() {
        if (this.n != null) {
            FetchVariablesCallback fetchVariablesCallback = this.h.getFetchVariablesCallback();
            this.h.setFetchVariablesCallback(null);
            this.n.handleVariableResponseError(fetchVariablesCallback);
        }
    }

    public void setCTDisplayUnitController(CTDisplayUnitController cTDisplayUnitController) {
        this.c = cTDisplayUnitController;
    }

    @Deprecated
    public void setCTFeatureFlagsController(CTFeatureFlagsController cTFeatureFlagsController) {
        this.d = cTFeatureFlagsController;
    }

    public void setCTInboxController(CTInboxController cTInboxController) {
        this.e = cTInboxController;
    }

    @Deprecated
    public void setCTProductConfigController(CTProductConfigController cTProductConfigController) {
        this.g = cTProductConfigController;
    }

    public void setCtVariables(CTVariables cTVariables) {
        this.n = cTVariables;
    }

    public void setInAppController(InAppController inAppController) {
        this.l = inAppController;
    }

    public void setInAppFCManager(InAppFCManager inAppFCManager) {
        this.f2573a = inAppFCManager;
    }

    public void setPushProviders(PushProviders pushProviders) {
        this.m = pushProviders;
    }
}
