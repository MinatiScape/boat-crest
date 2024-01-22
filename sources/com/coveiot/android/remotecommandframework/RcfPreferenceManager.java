package com.coveiot.android.remotecommandframework;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import com.coveiot.android.remotecommandframework.alexa.utils.Constants;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class RcfPreferenceManager {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5587a;
    @NotNull
    public final String b;
    @NotNull
    public final String c;
    @NotNull
    public final String d;
    @NotNull
    public final String e;
    @NotNull
    public final String f;
    @NotNull
    public final String g;
    @NotNull
    public final String h;
    @NotNull
    public final String i;
    @NotNull
    public final String j;
    @NotNull
    public final String k;
    public int l;
    @NotNull
    public String m;
    @NotNull
    public final SharedPreferences n;

    /* loaded from: classes6.dex */
    public static final class Companion extends SingletonHolder<RcfPreferenceManager, Context> {

        /* loaded from: classes6.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, RcfPreferenceManager> {
            public static final a INSTANCE = new a();

            public a() {
                super(1, RcfPreferenceManager.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final RcfPreferenceManager invoke(@NotNull Context p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new RcfPreferenceManager(p0, null);
            }
        }

        public Companion() {
            super(a.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public RcfPreferenceManager(Context context) {
        this.f5587a = context;
        this.b = "kh_rcf_pref_app";
        this.c = "tp_pub";
        this.d = "tp_sub";
        this.e = "c_id";
        this.f = "hst";
        this.g = "keep_alive";
        this.h = "rq_qos";
        this.i = "rs_qos";
        this.j = "rq_content_type";
        this.k = "rs_content_type";
        this.m = "account_linking_uri_from_alexa_app";
        SharedPreferences sharedPreferences = context.getSharedPreferences("kh_rcf_pref_app", this.l);
        Intrinsics.checkNotNullExpressionValue(sharedPreferences, "context.getSharedPrefere…dPrefsFile, PRIVATE_MODE)");
        this.n = sharedPreferences;
    }

    public /* synthetic */ RcfPreferenceManager(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    @Nullable
    public final Uri getAlexaAccountLinkingUri() {
        String string = this.n.getString(this.m, null);
        if (string == null || string.length() == 0) {
            return null;
        }
        return Uri.parse(string);
    }

    @Nullable
    public final String getClientId() {
        return this.n.getString(this.e, null);
    }

    @NotNull
    public final Context getContext() {
        return this.f5587a;
    }

    @Nullable
    public final String getHost() {
        return this.n.getString(this.f, null);
    }

    public final int getKeepAlive() {
        return this.n.getInt(this.g, Integer.parseInt(Constants.KEEP_ALIVE_INTERVAL.getValue()));
    }

    @Nullable
    public final String getRequestContentType() {
        return this.n.getString(this.j, null);
    }

    public final int getRequestQOS() {
        return this.n.getInt(this.h, Integer.parseInt(Constants.MQTT_QOS_1.getValue()));
    }

    @Nullable
    public final String getResponseContentType() {
        return this.n.getString(this.k, null);
    }

    public final int getResponseQOS() {
        return this.n.getInt(this.i, Integer.parseInt(Constants.MQTT_QOS_1.getValue()));
    }

    @Nullable
    public final String getTopicPub() {
        return this.n.getString(this.c, null);
    }

    @Nullable
    public final String getTopicSub() {
        return this.n.getString(this.d, null);
    }

    public final void saveAlexaAccountLinkingUri(@Nullable Uri uri) {
        this.n.edit().putString(this.m, String.valueOf(uri)).commit();
    }

    public final void saveClientId(@NotNull String cId) {
        Intrinsics.checkNotNullParameter(cId, "cId");
        this.n.edit().putString(this.e, cId).commit();
    }

    public final void saveHost(@NotNull String host) {
        Intrinsics.checkNotNullParameter(host, "host");
        this.n.edit().putString(this.f, host).commit();
    }

    public final void saveKeepAlive(int i) {
        this.n.edit().putInt(this.g, i).commit();
    }

    public final void saveRequestContentType(@NotNull String contentType) {
        Intrinsics.checkNotNullParameter(contentType, "contentType");
        this.n.edit().putString(this.j, contentType).commit();
    }

    public final void saveRequestQOS(int i) {
        this.n.edit().putInt(this.h, i).commit();
    }

    public final void saveResponseContentType(@NotNull String contentType) {
        Intrinsics.checkNotNullParameter(contentType, "contentType");
        this.n.edit().putString(this.k, contentType).commit();
    }

    public final void saveResponseQOS(int i) {
        this.n.edit().putInt(this.i, i).commit();
    }

    public final void saveTopicPub(@NotNull String tpPub) {
        Intrinsics.checkNotNullParameter(tpPub, "tpPub");
        this.n.edit().putString(this.c, tpPub).commit();
    }

    public final void saveTopicSub(@NotNull String tpSub) {
        Intrinsics.checkNotNullParameter(tpSub, "tpSub");
        this.n.edit().putString(this.d, tpSub).commit();
    }
}
