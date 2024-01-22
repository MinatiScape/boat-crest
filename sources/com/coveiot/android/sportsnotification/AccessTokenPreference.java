package com.coveiot.android.sportsnotification;

import android.content.Context;
import android.content.SharedPreferences;
import android.security.keystore.KeyGenParameterSpec;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class AccessTokenPreference {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5794a;
    @Nullable
    public SharedPreferences b;
    @Nullable
    public SharedPreferences.Editor c;
    @NotNull
    public final String d;
    @NotNull
    public final String e;

    public AccessTokenPreference(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5794a = context;
        this.d = "AccessTokenPref";
        this.e = "accessToken";
        KeyGenParameterSpec AES256_GCM_SPEC = MasterKeys.AES256_GCM_SPEC;
        Intrinsics.checkNotNullExpressionValue(AES256_GCM_SPEC, "AES256_GCM_SPEC");
        String orCreate = MasterKeys.getOrCreate(AES256_GCM_SPEC);
        Intrinsics.checkNotNullExpressionValue(orCreate, "getOrCreate(keyGenParameterSpec)");
        Intrinsics.checkNotNull(context);
        SharedPreferences create = EncryptedSharedPreferences.create("AccessTokenPref", orCreate, context, EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV, EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM);
        this.b = create;
        Intrinsics.checkNotNull(create);
        this.c = create.edit();
    }

    @NotNull
    public final String getACCESS_TOKEN() {
        return this.e;
    }

    @NotNull
    public final String getAccessToken() {
        SharedPreferences sharedPreferences = this.b;
        String string = sharedPreferences != null ? sharedPreferences.getString(this.e, BuildConfig.DEFAULT_SPORTS_TOKEN) : null;
        Intrinsics.checkNotNull(string);
        return string;
    }

    @NotNull
    public final Context getContext() {
        return this.f5794a;
    }

    @Nullable
    public final SharedPreferences.Editor getEditor() {
        return this.c;
    }

    @Nullable
    public final SharedPreferences getPref() {
        return this.b;
    }

    public final void saveAccessToken(@NotNull String string) {
        Intrinsics.checkNotNullParameter(string, "string");
        SharedPreferences.Editor editor = this.c;
        Intrinsics.checkNotNull(editor);
        editor.putString(this.e, string);
        SharedPreferences.Editor editor2 = this.c;
        Intrinsics.checkNotNull(editor2);
        editor2.commit();
    }

    public final void setEditor(@Nullable SharedPreferences.Editor editor) {
        this.c = editor;
    }

    public final void setPref(@Nullable SharedPreferences sharedPreferences) {
        this.b = sharedPreferences;
    }
}
