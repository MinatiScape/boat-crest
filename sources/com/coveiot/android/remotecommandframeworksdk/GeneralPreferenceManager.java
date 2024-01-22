package com.coveiot.android.remotecommandframeworksdk;

import android.content.Context;
import android.content.SharedPreferences;
import com.coveiot.android.remotecommandframeworksdk.utils.SingletonHolder;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jose4j.jwk.RsaJsonWebKey;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0011\b\u0002\u0012\u0006\u0010\u0010\u001a\u00020\u000b¢\u0006\u0004\b\u0011\u0010\u0012J\u0015\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\r\u0010\u0007\u001a\u00020\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u0015\u0010\t\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\t\u0010\u0006J\r\u0010\n\u001a\u00020\u0002¢\u0006\u0004\b\n\u0010\bR\u0019\u0010\u0010\u001a\u00020\u000b8\u0006@\u0006¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0014"}, d2 = {"Lcom/coveiot/android/remotecommandframeworksdk/GeneralPreferenceManager;", "", "", "timestamp", "", "saveLastConnectionTimestamp", "(J)V", "getLastConnectionTimestamp", "()J", "saveLastConnectionLostTimestamp", "getLastConnectionLostTimestamp", "Landroid/content/Context;", RsaJsonWebKey.EXPONENT_MEMBER_NAME, "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Companion", "remotecommandframeworksdk_prodRelease"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes6.dex */
public final class GeneralPreferenceManager {
    public static final Companion Companion = new Companion(null);

    /* renamed from: a  reason: collision with root package name */
    public final String f5649a;
    public final String b;
    public int c;
    public final SharedPreferences d;
    @NotNull
    public final Context e;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lcom/coveiot/android/remotecommandframeworksdk/GeneralPreferenceManager$Companion;", "Lcom/coveiot/android/remotecommandframeworksdk/utils/SingletonHolder;", "Lcom/coveiot/android/remotecommandframeworksdk/GeneralPreferenceManager;", "Landroid/content/Context;", "<init>", "()V", "remotecommandframeworksdk_prodRelease"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes6.dex */
    public static final class Companion extends SingletonHolder<GeneralPreferenceManager, Context> {

        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroid/content/Context;", "p1", "Lcom/coveiot/android/remotecommandframeworksdk/GeneralPreferenceManager;", "invoke", "(Landroid/content/Context;)Lcom/coveiot/android/remotecommandframeworksdk/GeneralPreferenceManager;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
        /* renamed from: com.coveiot.android.remotecommandframeworksdk.GeneralPreferenceManager$Companion$1  reason: invalid class name */
        /* loaded from: classes6.dex */
        public static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Context, GeneralPreferenceManager> {
            public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

            public AnonymousClass1() {
                super(1, GeneralPreferenceManager.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final GeneralPreferenceManager invoke(@NotNull Context p1) {
                Intrinsics.checkNotNullParameter(p1, "p1");
                return new GeneralPreferenceManager(p1, null);
            }
        }

        public Companion() {
            super(AnonymousClass1.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public GeneralPreferenceManager(Context context) {
        this.e = context;
        this.f5649a = "last_connection_timestamp";
        this.b = "last_connection_lost_timestamp";
        SharedPreferences sharedPreferences = context.getSharedPreferences("kh_rcf_pref_general", this.c);
        Intrinsics.checkNotNullExpressionValue(sharedPreferences, "context.getSharedPrefere…dPrefsFile, PRIVATE_MODE)");
        this.d = sharedPreferences;
    }

    public /* synthetic */ GeneralPreferenceManager(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    @NotNull
    public final Context getContext() {
        return this.e;
    }

    public final long getLastConnectionLostTimestamp() {
        return this.d.getLong(this.b, -1L);
    }

    public final long getLastConnectionTimestamp() {
        return this.d.getLong(this.f5649a, -1L);
    }

    public final void saveLastConnectionLostTimestamp(long j) {
        this.d.edit().putLong(this.b, j).commit();
    }

    public final void saveLastConnectionTimestamp(long j) {
        this.d.edit().putLong(this.f5649a, j).commit();
    }
}
