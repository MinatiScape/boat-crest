package com.coveiot.android.remotecommandframeworksdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.security.keystore.KeyGenParameterSpec;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;
import com.coveiot.android.remotecommandframeworksdk.utils.SingletonHolder;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwk.RsaJsonWebKey;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u0011\b\u0002\u0012\u0006\u0010\u0016\u001a\u00020\u0011¢\u0006\u0004\b\u0017\u0010\u0018J\u0015\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\u0007\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u0015\u0010\n\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0002¢\u0006\u0004\b\n\u0010\u0006J\u000f\u0010\u000b\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u000b\u0010\bJ\u0015\u0010\r\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0002¢\u0006\u0004\b\r\u0010\u0006J\u000f\u0010\u000e\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u000e\u0010\bJ\r\u0010\u000f\u001a\u00020\u0004¢\u0006\u0004\b\u000f\u0010\u0010R\u0019\u0010\u0016\u001a\u00020\u00118\u0006@\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u001a"}, d2 = {"Lcom/coveiot/android/remotecommandframeworksdk/PreferenceManager;", "", "", "caData", "", "saveCa", "(Ljava/lang/String;)V", "getCa", "()Ljava/lang/String;", "crtData", "saveCrt", "getCrt", "kyData", "saveKy", "getKy", "clearCertificates", "()V", "Landroid/content/Context;", RsaJsonWebKey.EXPONENT_MEMBER_NAME, "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Companion", "remotecommandframeworksdk_prodRelease"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes6.dex */
public final class PreferenceManager {
    public static final Companion Companion = new Companion(null);

    /* renamed from: a  reason: collision with root package name */
    public final String f5651a;
    public final String b;
    public final String c;
    public final SharedPreferences d;
    @NotNull
    public final Context e;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lcom/coveiot/android/remotecommandframeworksdk/PreferenceManager$Companion;", "Lcom/coveiot/android/remotecommandframeworksdk/utils/SingletonHolder;", "Lcom/coveiot/android/remotecommandframeworksdk/PreferenceManager;", "Landroid/content/Context;", "<init>", "()V", "remotecommandframeworksdk_prodRelease"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes6.dex */
    public static final class Companion extends SingletonHolder<PreferenceManager, Context> {

        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroid/content/Context;", "p1", "Lcom/coveiot/android/remotecommandframeworksdk/PreferenceManager;", "invoke", "(Landroid/content/Context;)Lcom/coveiot/android/remotecommandframeworksdk/PreferenceManager;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
        /* renamed from: com.coveiot.android.remotecommandframeworksdk.PreferenceManager$Companion$1  reason: invalid class name */
        /* loaded from: classes6.dex */
        public static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Context, PreferenceManager> {
            public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

            public AnonymousClass1() {
                super(1, PreferenceManager.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final PreferenceManager invoke(@NotNull Context p1) {
                Intrinsics.checkNotNullParameter(p1, "p1");
                return new PreferenceManager(p1, null);
            }
        }

        public Companion() {
            super(AnonymousClass1.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public PreferenceManager(Context context) {
        this.e = context;
        KeyGenParameterSpec keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC;
        Intrinsics.checkNotNullExpressionValue(keyGenParameterSpec, "MasterKeys.AES256_GCM_SPEC");
        String orCreate = MasterKeys.getOrCreate(keyGenParameterSpec);
        Intrinsics.checkNotNullExpressionValue(orCreate, "MasterKeys.getOrCreate(keyGenParameterSpec)");
        this.f5651a = "ca";
        this.b = "crt";
        this.c = "ky";
        SharedPreferences create = EncryptedSharedPreferences.create("kh_rcf_pref", orCreate, context, EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV, EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM);
        Intrinsics.checkNotNullExpressionValue(create, "EncryptedSharedPreferenc…onScheme.AES256_GCM\n    )");
        this.d = create;
    }

    public /* synthetic */ PreferenceManager(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    public final void clearCertificates() {
        this.d.edit().putString(this.f5651a, null).commit();
        this.d.edit().putString(this.b, null).commit();
        this.d.edit().putString(this.c, null).commit();
    }

    @Nullable
    public final String getCa() {
        return this.d.getString(this.f5651a, null);
    }

    @NotNull
    public final Context getContext() {
        return this.e;
    }

    @Nullable
    public final String getCrt() {
        return this.d.getString(this.b, null);
    }

    @Nullable
    public final String getKy() {
        return this.d.getString(this.c, null);
    }

    public final void saveCa(@NotNull String caData) {
        Intrinsics.checkNotNullParameter(caData, "caData");
        this.d.edit().putString(this.f5651a, caData).commit();
    }

    public final void saveCrt(@NotNull String crtData) {
        Intrinsics.checkNotNullParameter(crtData, "crtData");
        this.d.edit().putString(this.b, crtData).commit();
    }

    public final void saveKy(@NotNull String kyData) {
        Intrinsics.checkNotNullParameter(kyData, "kyData");
        this.d.edit().putString(this.c, kyData).commit();
    }
}
