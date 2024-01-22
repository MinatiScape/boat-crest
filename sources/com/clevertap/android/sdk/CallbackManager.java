package com.clevertap.android.sdk;

import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import com.clevertap.android.sdk.displayunits.DisplayUnitListener;
import com.clevertap.android.sdk.displayunits.model.CleverTapDisplayUnit;
import com.clevertap.android.sdk.interfaces.OnInitCleverTapIDListener;
import com.clevertap.android.sdk.interfaces.SCDomainListener;
import com.clevertap.android.sdk.product_config.CTProductConfigListener;
import com.clevertap.android.sdk.pushnotification.CTPushNotificationListener;
import com.clevertap.android.sdk.pushnotification.amp.CTPushAmpListener;
import com.clevertap.android.sdk.variables.callbacks.FetchVariablesCallback;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes2.dex */
public class CallbackManager extends BaseCallbackManager {

    /* renamed from: a  reason: collision with root package name */
    public WeakReference<DisplayUnitListener> f2570a;
    public GeofenceCallback b;
    public SCDomainListener c;
    public WeakReference<InAppNotificationButtonListener> d;
    public InAppNotificationListener e;
    public CTInboxListener g;
    public final CleverTapInstanceConfig h;
    public final DeviceInfo i;
    public FailureFlushListener j;
    @Deprecated
    public WeakReference<CTFeatureFlagsListener> k;
    public OnInitCleverTapIDListener l;
    @Deprecated
    public WeakReference<CTProductConfigListener> m;
    public FetchVariablesCallback q;
    public final List<PushPermissionResponseListener> f = new ArrayList();
    public CTPushAmpListener n = null;
    public CTPushNotificationListener o = null;
    public SyncListener p = null;

    /* loaded from: classes2.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (CallbackManager.this.g != null) {
                CallbackManager.this.g.inboxMessagesDidUpdate();
            }
        }
    }

    /* loaded from: classes2.dex */
    public class b implements Runnable {
        public final /* synthetic */ ArrayList h;

        public b(ArrayList arrayList) {
            this.h = arrayList;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (CallbackManager.this.f2570a == null || CallbackManager.this.f2570a.get() == null) {
                return;
            }
            ((DisplayUnitListener) CallbackManager.this.f2570a.get()).onDisplayUnitsLoaded(this.h);
        }
    }

    public CallbackManager(CleverTapInstanceConfig cleverTapInstanceConfig, DeviceInfo deviceInfo) {
        this.h = cleverTapInstanceConfig;
        this.i = deviceInfo;
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    public void _notifyInboxMessagesDidUpdate() {
        if (this.g != null) {
            Utils.runOnUiThread(new a());
        }
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    public void a() {
        CTInboxListener cTInboxListener = this.g;
        if (cTInboxListener != null) {
            cTInboxListener.inboxDidInitialize();
        }
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    public FailureFlushListener getFailureFlushListener() {
        return this.j;
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    @Deprecated
    public CTFeatureFlagsListener getFeatureFlagListener() {
        WeakReference<CTFeatureFlagsListener> weakReference = this.k;
        if (weakReference == null || weakReference.get() == null) {
            return null;
        }
        return this.k.get();
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    @Nullable
    public FetchVariablesCallback getFetchVariablesCallback() {
        return this.q;
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    public GeofenceCallback getGeofenceCallback() {
        return this.b;
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    public InAppNotificationButtonListener getInAppNotificationButtonListener() {
        WeakReference<InAppNotificationButtonListener> weakReference = this.d;
        if (weakReference == null || weakReference.get() == null) {
            return null;
        }
        return this.d.get();
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    public InAppNotificationListener getInAppNotificationListener() {
        return this.e;
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    public CTInboxListener getInboxListener() {
        return this.g;
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    public OnInitCleverTapIDListener getOnInitCleverTapIDListener() {
        return this.l;
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    @Deprecated
    public CTProductConfigListener getProductConfigListener() {
        WeakReference<CTProductConfigListener> weakReference = this.m;
        if (weakReference == null || weakReference.get() == null) {
            return null;
        }
        return this.m.get();
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    public CTPushAmpListener getPushAmpListener() {
        return this.n;
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    public CTPushNotificationListener getPushNotificationListener() {
        return this.o;
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    public List<PushPermissionResponseListener> getPushPermissionResponseListenerList() {
        return this.f;
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    public SCDomainListener getSCDomainListener() {
        return this.c;
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    public SyncListener getSyncListener() {
        return this.p;
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    public void notifyDisplayUnitsLoaded(ArrayList<CleverTapDisplayUnit> arrayList) {
        if (arrayList != null && !arrayList.isEmpty()) {
            WeakReference<DisplayUnitListener> weakReference = this.f2570a;
            if (weakReference != null && weakReference.get() != null) {
                Utils.runOnUiThread(new b(arrayList));
                return;
            } else {
                this.h.getLogger().verbose(this.h.getAccountId(), "DisplayUnit : No registered listener, failed to notify");
                return;
            }
        }
        this.h.getLogger().verbose(this.h.getAccountId(), "DisplayUnit : No Display Units found");
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    public void notifyUserProfileInitialized(String str) {
        if (str == null) {
            str = this.i.getDeviceID();
        }
        if (str == null) {
            return;
        }
        try {
            SyncListener syncListener = getSyncListener();
            if (syncListener != null) {
                syncListener.profileDidInitialize(str);
            }
        } catch (Throwable unused) {
        }
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    public void registerPushPermissionResponseListener(PushPermissionResponseListener pushPermissionResponseListener) {
        this.f.add(pushPermissionResponseListener);
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    public void setDisplayUnitListener(DisplayUnitListener displayUnitListener) {
        if (displayUnitListener != null) {
            this.f2570a = new WeakReference<>(displayUnitListener);
        } else {
            this.h.getLogger().verbose(this.h.getAccountId(), "DisplayUnit : Failed to set - DisplayUnitListener can't be null");
        }
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    public void setFailureFlushListener(FailureFlushListener failureFlushListener) {
        this.j = failureFlushListener;
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    @Deprecated
    public void setFeatureFlagListener(CTFeatureFlagsListener cTFeatureFlagsListener) {
        this.k = new WeakReference<>(cTFeatureFlagsListener);
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    public void setFetchVariablesCallback(FetchVariablesCallback fetchVariablesCallback) {
        this.q = fetchVariablesCallback;
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    public void setGeofenceCallback(GeofenceCallback geofenceCallback) {
        this.b = geofenceCallback;
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    public void setInAppNotificationButtonListener(InAppNotificationButtonListener inAppNotificationButtonListener) {
        this.d = new WeakReference<>(inAppNotificationButtonListener);
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    public void setInAppNotificationListener(InAppNotificationListener inAppNotificationListener) {
        this.e = inAppNotificationListener;
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    public void setInboxListener(CTInboxListener cTInboxListener) {
        this.g = cTInboxListener;
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    public void setOnInitCleverTapIDListener(OnInitCleverTapIDListener onInitCleverTapIDListener) {
        this.l = onInitCleverTapIDListener;
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    @Deprecated
    public void setProductConfigListener(CTProductConfigListener cTProductConfigListener) {
        if (cTProductConfigListener != null) {
            this.m = new WeakReference<>(cTProductConfigListener);
        }
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    public void setPushAmpListener(CTPushAmpListener cTPushAmpListener) {
        this.n = cTPushAmpListener;
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    public void setPushNotificationListener(CTPushNotificationListener cTPushNotificationListener) {
        this.o = cTPushNotificationListener;
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    public void setSCDomainListener(SCDomainListener sCDomainListener) {
        this.c = sCDomainListener;
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    public void setSyncListener(SyncListener syncListener) {
        this.p = syncListener;
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    public void unregisterPushPermissionResponseListener(PushPermissionResponseListener pushPermissionResponseListener) {
        this.f.remove(pushPermissionResponseListener);
    }
}
