package com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes9.dex */
public final class IFramePlayerOptions {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final IFramePlayerOptions b = new Builder().controls(1).build();
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final JSONObject f13321a;

    /* loaded from: classes9.dex */
    public static final class Builder {
        @NotNull
        public static final Companion Companion = new Companion(null);
        @NotNull
        public static final String ORIGIN = "origin";
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final JSONObject f13322a = new JSONObject();

        /* loaded from: classes9.dex */
        public static final class Companion {
            public Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }
        }

        public Builder() {
            a("autoplay", 0);
            a("mute", 0);
            a("controls", 0);
            a("enablejsapi", 1);
            a("fs", 0);
            b("origin", "https://www.youtube.com");
            a("rel", 0);
            a("showinfo", 0);
            a("iv_load_policy", 3);
            a("modestbranding", 1);
            a("cc_load_policy", 0);
        }

        public final void a(String str, int i) {
            try {
                this.f13322a.put(str, i);
            } catch (JSONException unused) {
                throw new RuntimeException("Illegal JSON value " + str + ": " + i);
            }
        }

        @NotNull
        public final Builder autoplay(int i) {
            a("autoplay", i);
            return this;
        }

        public final void b(String str, String str2) {
            try {
                this.f13322a.put(str, str2);
            } catch (JSONException unused) {
                throw new RuntimeException("Illegal JSON value " + str + ": " + str2);
            }
        }

        @NotNull
        public final IFramePlayerOptions build() {
            return new IFramePlayerOptions(this.f13322a, null);
        }

        @NotNull
        public final Builder ccLoadPolicy(int i) {
            a("cc_load_policy", i);
            return this;
        }

        @NotNull
        public final Builder controls(int i) {
            a("controls", i);
            return this;
        }

        @NotNull
        public final Builder fullscreen(int i) {
            a("fs", i);
            return this;
        }

        @NotNull
        public final Builder ivLoadPolicy(int i) {
            a("iv_load_policy", i);
            return this;
        }

        @NotNull
        public final Builder langPref(@NotNull String languageCode) {
            Intrinsics.checkNotNullParameter(languageCode, "languageCode");
            b("cc_lang_pref", languageCode);
            return this;
        }

        @NotNull
        public final Builder list(@NotNull String list) {
            Intrinsics.checkNotNullParameter(list, "list");
            b("list", list);
            return this;
        }

        @NotNull
        public final Builder listType(@NotNull String listType) {
            Intrinsics.checkNotNullParameter(listType, "listType");
            b("listType", listType);
            return this;
        }

        @NotNull
        public final Builder mute(int i) {
            a("mute", i);
            return this;
        }

        @NotNull
        public final Builder origin(@NotNull String origin) {
            Intrinsics.checkNotNullParameter(origin, "origin");
            b("origin", origin);
            return this;
        }

        @NotNull
        public final Builder rel(int i) {
            a("rel", i);
            return this;
        }
    }

    /* loaded from: classes9.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final IFramePlayerOptions getDefault() {
            return IFramePlayerOptions.b;
        }
    }

    public IFramePlayerOptions(JSONObject jSONObject) {
        this.f13321a = jSONObject;
    }

    public /* synthetic */ IFramePlayerOptions(JSONObject jSONObject, DefaultConstructorMarker defaultConstructorMarker) {
        this(jSONObject);
    }

    @NotNull
    public final String getOrigin$core_release() {
        String string = this.f13321a.getString("origin");
        Intrinsics.checkNotNullExpressionValue(string, "playerOptions.getString(Builder.ORIGIN)");
        return string;
    }

    @NotNull
    public String toString() {
        String jSONObject = this.f13321a.toString();
        Intrinsics.checkNotNullExpressionValue(jSONObject, "playerOptions.toString()");
        return jSONObject;
    }
}
