package com.coveiot.android.leonardo.onboarding.paring.activities;

import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.error.Error;
import com.coveiot.android.bleabstract.listeners.ConnectionResultListener;
import com.coveiot.android.bleabstract.models.BleDevice;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.DeviceSupportedFeatures;
import com.coveiot.android.bleabstract.request.ConnectRequest;
import com.coveiot.android.leonardo.bt3call.viewmodel.BT3CallViewModel;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.android.theme.utils.ThemeConstants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ktx.Firebase;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.ktx.RemoteConfigKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class ActivityPairing$connectTo$1 implements ConnectionResultListener {
    public final /* synthetic */ String h;
    public final /* synthetic */ ActivityPairing i;
    public final /* synthetic */ BleDevice j;
    public final /* synthetic */ Ref.BooleanRef k;
    public final /* synthetic */ DeviceSupportedFeatures l;
    public final /* synthetic */ ConnectRequest m;

    public ActivityPairing$connectTo$1(String str, ActivityPairing activityPairing, BleDevice bleDevice, Ref.BooleanRef booleanRef, DeviceSupportedFeatures deviceSupportedFeatures, ConnectRequest connectRequest) {
        this.h = str;
        this.i = activityPairing;
        this.j = bleDevice;
        this.k = booleanRef;
        this.l = deviceSupportedFeatures;
        this.m = connectRequest;
    }

    public static final void d(ActivityPairing this$0, DeviceSupportedFeatures deviceSupportedFeatures, Exception it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(deviceSupportedFeatures, "$deviceSupportedFeatures");
        Intrinsics.checkNotNullParameter(it, "it");
        this$0.getBt3CallViewModel().connectToBT3Watch(true, false, deviceSupportedFeatures.isOneClickToConnectSupported());
    }

    public static final void e(final FirebaseRemoteConfig remoteConfig, final ActivityPairing this$0, final DeviceSupportedFeatures deviceSupportedFeatures, Task task) {
        Intrinsics.checkNotNullParameter(remoteConfig, "$remoteConfig");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(deviceSupportedFeatures, "$deviceSupportedFeatures");
        Intrinsics.checkNotNullParameter(task, "task");
        if (task.isSuccessful()) {
            Void r4 = (Void) task.getResult();
            remoteConfig.activate().addOnCompleteListener(new OnCompleteListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.activities.i
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task2) {
                    ActivityPairing$connectTo$1.f(FirebaseRemoteConfig.this, this$0, deviceSupportedFeatures, task2);
                }
            });
        }
    }

    public static final void f(FirebaseRemoteConfig remoteConfig, ActivityPairing this$0, DeviceSupportedFeatures deviceSupportedFeatures, Task it) {
        Intrinsics.checkNotNullParameter(remoteConfig, "$remoteConfig");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(deviceSupportedFeatures, "$deviceSupportedFeatures");
        Intrinsics.checkNotNullParameter(it, "it");
        this$0.getBt3CallViewModel().connectToBT3Watch(true, remoteConfig.getBoolean(ThemeConstants.SHOULD_USE_REFLECTION_API_PAIRING.getValue()), deviceSupportedFeatures.isOneClickToConnectSupported());
    }

    @Override // com.coveiot.android.bleabstract.listeners.ConnectionResultListener
    public void onConnectionResponse(@NotNull ConnectionStatus bleState) {
        DeviceSupportedFeatures deviceSupportedFeatures;
        DeviceSupportedFeatures deviceSupportedFeatures2;
        Intrinsics.checkNotNullParameter(bleState, "bleState");
        if (bleState == ConnectionStatus.CONNECTED) {
            String str = this.h;
            boolean z = false;
            if (str == null || str.length() == 0) {
                this.i.setConnectedDeviceName(this.j.getmDevice().getName());
            } else {
                this.i.setConnectedDeviceName(this.h);
            }
            this.i.handleConnected(this.k.element, this.j);
            BleApi bleApi = BleApiManager.getInstance(this.i).getBleApi();
            if ((bleApi == null || (deviceSupportedFeatures2 = bleApi.getDeviceSupportedFeatures()) == null || !deviceSupportedFeatures2.isKahaBTCallSupported()) ? false : true) {
                try {
                    final FirebaseRemoteConfig remoteConfig = RemoteConfigKt.getRemoteConfig(Firebase.INSTANCE);
                    Task<Void> fetch = remoteConfig.fetch(10L);
                    final ActivityPairing activityPairing = this.i;
                    final DeviceSupportedFeatures deviceSupportedFeatures3 = this.l;
                    Task<Void> addOnFailureListener = fetch.addOnFailureListener(new OnFailureListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.activities.j
                        @Override // com.google.android.gms.tasks.OnFailureListener
                        public final void onFailure(Exception exc) {
                            ActivityPairing$connectTo$1.d(ActivityPairing.this, deviceSupportedFeatures3, exc);
                        }
                    });
                    final ActivityPairing activityPairing2 = this.i;
                    final DeviceSupportedFeatures deviceSupportedFeatures4 = this.l;
                    addOnFailureListener.addOnCompleteListener(new OnCompleteListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.activities.h
                        @Override // com.google.android.gms.tasks.OnCompleteListener
                        public final void onComplete(Task task) {
                            ActivityPairing$connectTo$1.e(FirebaseRemoteConfig.this, activityPairing2, deviceSupportedFeatures4, task);
                        }
                    });
                } catch (Exception unused) {
                    this.i.getBt3CallViewModel().connectToBT3Watch(true, false, this.l.isOneClickToConnectSupported());
                }
                return;
            }
            BleApi bleApi2 = BleApiManager.getInstance(this.i).getBleApi();
            if (bleApi2 != null && (deviceSupportedFeatures = bleApi2.getDeviceSupportedFeatures()) != null && deviceSupportedFeatures.isBTCallingSupported()) {
                z = true;
            }
            if (z && PayUtils.isIDODeviceSupportsBTCall(this.i)) {
                BT3CallViewModel.connectToBT3Watch$default(this.i.getBt3CallViewModel(), true, false, false, 6, null);
            }
        } else if (bleState == ConnectionStatus.DISCONNECTED) {
            this.i.handleDisconnected();
        }
    }

    @Override // com.coveiot.android.bleabstract.listeners.ConnectionResultListener
    public void onError(@NotNull Error error) {
        Intrinsics.checkNotNullParameter(error, "error");
        this.i.handleOnError(error, this.k.element, this.j, this.m);
    }
}
