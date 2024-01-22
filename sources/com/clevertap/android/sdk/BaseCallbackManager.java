package com.clevertap.android.sdk;

import com.clevertap.android.sdk.displayunits.DisplayUnitListener;
import com.clevertap.android.sdk.displayunits.model.CleverTapDisplayUnit;
import com.clevertap.android.sdk.interfaces.OnInitCleverTapIDListener;
import com.clevertap.android.sdk.interfaces.SCDomainListener;
import com.clevertap.android.sdk.product_config.CTProductConfigListener;
import com.clevertap.android.sdk.pushnotification.CTPushNotificationListener;
import com.clevertap.android.sdk.pushnotification.amp.CTPushAmpListener;
import com.clevertap.android.sdk.variables.callbacks.FetchVariablesCallback;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes2.dex */
public abstract class BaseCallbackManager {
    public abstract void _notifyInboxMessagesDidUpdate();

    public abstract void a();

    public abstract FailureFlushListener getFailureFlushListener();

    @Deprecated
    public abstract CTFeatureFlagsListener getFeatureFlagListener();

    public abstract FetchVariablesCallback getFetchVariablesCallback();

    public abstract GeofenceCallback getGeofenceCallback();

    public abstract InAppNotificationButtonListener getInAppNotificationButtonListener();

    public abstract InAppNotificationListener getInAppNotificationListener();

    public abstract CTInboxListener getInboxListener();

    public abstract OnInitCleverTapIDListener getOnInitCleverTapIDListener();

    @Deprecated
    public abstract CTProductConfigListener getProductConfigListener();

    public abstract CTPushAmpListener getPushAmpListener();

    public abstract CTPushNotificationListener getPushNotificationListener();

    public abstract List<PushPermissionResponseListener> getPushPermissionResponseListenerList();

    public abstract SCDomainListener getSCDomainListener();

    public abstract SyncListener getSyncListener();

    public abstract void notifyDisplayUnitsLoaded(ArrayList<CleverTapDisplayUnit> arrayList);

    public abstract void notifyUserProfileInitialized(String str);

    public abstract void registerPushPermissionResponseListener(PushPermissionResponseListener pushPermissionResponseListener);

    public abstract void setDisplayUnitListener(DisplayUnitListener displayUnitListener);

    public abstract void setFailureFlushListener(FailureFlushListener failureFlushListener);

    @Deprecated
    public abstract void setFeatureFlagListener(CTFeatureFlagsListener cTFeatureFlagsListener);

    public abstract void setFetchVariablesCallback(FetchVariablesCallback fetchVariablesCallback);

    public abstract void setGeofenceCallback(GeofenceCallback geofenceCallback);

    public abstract void setInAppNotificationButtonListener(InAppNotificationButtonListener inAppNotificationButtonListener);

    public abstract void setInAppNotificationListener(InAppNotificationListener inAppNotificationListener);

    public abstract void setInboxListener(CTInboxListener cTInboxListener);

    public abstract void setOnInitCleverTapIDListener(OnInitCleverTapIDListener onInitCleverTapIDListener);

    @Deprecated
    public abstract void setProductConfigListener(CTProductConfigListener cTProductConfigListener);

    public abstract void setPushAmpListener(CTPushAmpListener cTPushAmpListener);

    public abstract void setPushNotificationListener(CTPushNotificationListener cTPushNotificationListener);

    public abstract void setSCDomainListener(SCDomainListener sCDomainListener);

    public abstract void setSyncListener(SyncListener syncListener);

    public abstract void unregisterPushPermissionResponseListener(PushPermissionResponseListener pushPermissionResponseListener);
}
