package com.google.firebase.ktx;

import android.content.Context;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u001a\u0012\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001\u001a\u0014\u0010\u0007\u001a\u0004\u0018\u00010\u0003*\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0005\u001a\u001a\u0010\u0007\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\b\u001a\"\u0010\u0007\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u0002\u001a\u00020\u0001\"\u0016\u0010\n\u001a\u00020\u00018\u0000@\u0000X\u0080T¢\u0006\u0006\n\u0004\b\n\u0010\u000b\"\u0017\u0010\u0004\u001a\u00020\u0003*\u00020\u00008F@\u0006¢\u0006\u0006\u001a\u0004\b\f\u0010\r\"\u0017\u0010\t\u001a\u00020\b*\u00020\u00008F@\u0006¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lcom/google/firebase/ktx/Firebase;", "", AppMeasurementSdk.ConditionalUserProperty.NAME, "Lcom/google/firebase/FirebaseApp;", "app", "Landroid/content/Context;", "context", "initialize", "Lcom/google/firebase/FirebaseOptions;", "options", "LIBRARY_NAME", "Ljava/lang/String;", "getApp", "(Lcom/google/firebase/ktx/Firebase;)Lcom/google/firebase/FirebaseApp;", "getOptions", "(Lcom/google/firebase/ktx/Firebase;)Lcom/google/firebase/FirebaseOptions;", "com.google.firebase-firebase-common-ktx"}, k = 2, mv = {1, 4, 0})
/* loaded from: classes10.dex */
public final class FirebaseKt {
    @NotNull
    public static final String LIBRARY_NAME = "fire-core-ktx";

    @NotNull
    public static final FirebaseApp app(@NotNull Firebase app, @NotNull String name) {
        Intrinsics.checkParameterIsNotNull(app, "$this$app");
        Intrinsics.checkParameterIsNotNull(name, "name");
        FirebaseApp firebaseApp = FirebaseApp.getInstance(name);
        Intrinsics.checkExpressionValueIsNotNull(firebaseApp, "FirebaseApp.getInstance(name)");
        return firebaseApp;
    }

    @NotNull
    public static final FirebaseApp getApp(@NotNull Firebase app) {
        Intrinsics.checkParameterIsNotNull(app, "$this$app");
        FirebaseApp firebaseApp = FirebaseApp.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(firebaseApp, "FirebaseApp.getInstance()");
        return firebaseApp;
    }

    @NotNull
    public static final FirebaseOptions getOptions(@NotNull Firebase options) {
        Intrinsics.checkParameterIsNotNull(options, "$this$options");
        FirebaseOptions options2 = getApp(Firebase.INSTANCE).getOptions();
        Intrinsics.checkExpressionValueIsNotNull(options2, "Firebase.app.options");
        return options2;
    }

    @Nullable
    public static final FirebaseApp initialize(@NotNull Firebase initialize, @NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(initialize, "$this$initialize");
        Intrinsics.checkParameterIsNotNull(context, "context");
        return FirebaseApp.initializeApp(context);
    }

    @NotNull
    public static final FirebaseApp initialize(@NotNull Firebase initialize, @NotNull Context context, @NotNull FirebaseOptions options) {
        Intrinsics.checkParameterIsNotNull(initialize, "$this$initialize");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(options, "options");
        FirebaseApp initializeApp = FirebaseApp.initializeApp(context, options);
        Intrinsics.checkExpressionValueIsNotNull(initializeApp, "FirebaseApp.initializeApp(context, options)");
        return initializeApp;
    }

    @NotNull
    public static final FirebaseApp initialize(@NotNull Firebase initialize, @NotNull Context context, @NotNull FirebaseOptions options, @NotNull String name) {
        Intrinsics.checkParameterIsNotNull(initialize, "$this$initialize");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(options, "options");
        Intrinsics.checkParameterIsNotNull(name, "name");
        FirebaseApp initializeApp = FirebaseApp.initializeApp(context, options, name);
        Intrinsics.checkExpressionValueIsNotNull(initializeApp, "FirebaseApp.initializeApp(context, options, name)");
        return initializeApp;
    }
}
