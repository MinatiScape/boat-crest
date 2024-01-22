package com.google.firebase.remoteconfig.ktx;

import com.clevertap.android.sdk.Constants;
import com.google.firebase.FirebaseApp;
import com.google.firebase.ktx.Firebase;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigValue;
import com.ido.ble.event.stat.one.d;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u0012\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001\u001a\u0015\u0010\b\u001a\u00020\u0007*\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0005H\u0086\u0002\u001a\u001f\u0010\u000f\u001a\u00020\u000e2\u0017\u0010\r\u001a\u0013\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\t¢\u0006\u0002\b\f\"\u0016\u0010\u0010\u001a\u00020\u00058\u0000@\u0000X\u0080T¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011\"\u0017\u0010\u0004\u001a\u00020\u0003*\u00020\u00008F@\u0006¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Lcom/google/firebase/ktx/Firebase;", "Lcom/google/firebase/FirebaseApp;", "app", "Lcom/google/firebase/remoteconfig/FirebaseRemoteConfig;", "remoteConfig", "", Constants.KEY_KEY, "Lcom/google/firebase/remoteconfig/FirebaseRemoteConfigValue;", "get", "Lkotlin/Function1;", "Lcom/google/firebase/remoteconfig/FirebaseRemoteConfigSettings$Builder;", "", "Lkotlin/ExtensionFunctionType;", d.m, "Lcom/google/firebase/remoteconfig/FirebaseRemoteConfigSettings;", "remoteConfigSettings", "LIBRARY_NAME", "Ljava/lang/String;", "getRemoteConfig", "(Lcom/google/firebase/ktx/Firebase;)Lcom/google/firebase/remoteconfig/FirebaseRemoteConfig;", "com.google.firebase-firebase-config-ktx"}, k = 2, mv = {1, 4, 0})
/* loaded from: classes10.dex */
public final class RemoteConfigKt {
    @NotNull
    public static final String LIBRARY_NAME = "fire-cfg-ktx";

    @NotNull
    public static final FirebaseRemoteConfigValue get(@NotNull FirebaseRemoteConfig get, @NotNull String key) {
        Intrinsics.checkParameterIsNotNull(get, "$this$get");
        Intrinsics.checkParameterIsNotNull(key, "key");
        FirebaseRemoteConfigValue value = get.getValue(key);
        Intrinsics.checkExpressionValueIsNotNull(value, "this.getValue(key)");
        return value;
    }

    @NotNull
    public static final FirebaseRemoteConfig getRemoteConfig(@NotNull Firebase remoteConfig) {
        Intrinsics.checkParameterIsNotNull(remoteConfig, "$this$remoteConfig");
        FirebaseRemoteConfig firebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(firebaseRemoteConfig, "FirebaseRemoteConfig.getInstance()");
        return firebaseRemoteConfig;
    }

    @NotNull
    public static final FirebaseRemoteConfig remoteConfig(@NotNull Firebase remoteConfig, @NotNull FirebaseApp app) {
        Intrinsics.checkParameterIsNotNull(remoteConfig, "$this$remoteConfig");
        Intrinsics.checkParameterIsNotNull(app, "app");
        FirebaseRemoteConfig firebaseRemoteConfig = FirebaseRemoteConfig.getInstance(app);
        Intrinsics.checkExpressionValueIsNotNull(firebaseRemoteConfig, "FirebaseRemoteConfig.getInstance(app)");
        return firebaseRemoteConfig;
    }

    @NotNull
    public static final FirebaseRemoteConfigSettings remoteConfigSettings(@NotNull Function1<? super FirebaseRemoteConfigSettings.Builder, Unit> init) {
        Intrinsics.checkParameterIsNotNull(init, "init");
        FirebaseRemoteConfigSettings.Builder builder = new FirebaseRemoteConfigSettings.Builder();
        init.invoke(builder);
        FirebaseRemoteConfigSettings build = builder.build();
        Intrinsics.checkExpressionValueIsNotNull(build, "builder.build()");
        return build;
    }
}
