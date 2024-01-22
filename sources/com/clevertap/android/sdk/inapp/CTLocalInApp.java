package com.clevertap.android.sdk.inapp;

import com.clevertap.android.sdk.Constants;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public final class CTLocalInApp {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String FALLBACK_TO_NOTIFICATION_SETTINGS = "fallbackToNotificationSettings";
    @NotNull
    public static final String IS_LOCAL_INAPP = "isLocalInApp";

    /* loaded from: classes2.dex */
    public static final class Builder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public JSONObject f2622a = new JSONObject();

        /* loaded from: classes2.dex */
        public static final class Builder1 {
            @NotNull

            /* renamed from: a  reason: collision with root package name */
            public JSONObject f2623a;

            public Builder1(@NotNull JSONObject jsonObject) {
                Intrinsics.checkNotNullParameter(jsonObject, "jsonObject");
                this.f2623a = jsonObject;
            }

            @NotNull
            public final Builder2 setTitleText(@NotNull String titleText) {
                Intrinsics.checkNotNullParameter(titleText, "titleText");
                JSONObject jSONObject = this.f2623a;
                jSONObject.put("title", new JSONObject().put("text", titleText));
                return new Builder2(jSONObject);
            }
        }

        /* loaded from: classes2.dex */
        public static final class Builder2 {
            @NotNull

            /* renamed from: a  reason: collision with root package name */
            public JSONObject f2624a;

            public Builder2(@NotNull JSONObject jsonObject) {
                Intrinsics.checkNotNullParameter(jsonObject, "jsonObject");
                this.f2624a = jsonObject;
            }

            @NotNull
            public final Builder3 setMessageText(@NotNull String messageText) {
                Intrinsics.checkNotNullParameter(messageText, "messageText");
                JSONObject jSONObject = this.f2624a;
                jSONObject.put(Constants.KEY_MESSAGE, new JSONObject().put("text", messageText));
                return new Builder3(jSONObject);
            }
        }

        /* loaded from: classes2.dex */
        public static final class Builder3 {
            @NotNull

            /* renamed from: a  reason: collision with root package name */
            public JSONObject f2625a;

            public Builder3(@NotNull JSONObject jsonObject) {
                Intrinsics.checkNotNullParameter(jsonObject, "jsonObject");
                this.f2625a = jsonObject;
            }

            @NotNull
            public final Builder4 followDeviceOrientation(boolean z) {
                JSONObject jSONObject = this.f2625a;
                jSONObject.put(Constants.KEY_PORTRAIT, true);
                jSONObject.put(Constants.KEY_LANDSCAPE, z);
                return new Builder4(jSONObject);
            }
        }

        /* loaded from: classes2.dex */
        public static final class Builder4 {
            @NotNull

            /* renamed from: a  reason: collision with root package name */
            public JSONObject f2626a;

            public Builder4(@NotNull JSONObject jsonObject) {
                Intrinsics.checkNotNullParameter(jsonObject, "jsonObject");
                this.f2626a = jsonObject;
            }

            @NotNull
            public final Builder5 setPositiveBtnText(@NotNull String positiveBtnText) {
                Intrinsics.checkNotNullParameter(positiveBtnText, "positiveBtnText");
                JSONObject jSONObject = this.f2626a;
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("text", positiveBtnText);
                jSONObject2.put(Constants.KEY_RADIUS, "2");
                jSONObject.put(Constants.KEY_BUTTONS, new JSONArray().put(0, jSONObject2));
                return new Builder5(jSONObject);
            }
        }

        /* loaded from: classes2.dex */
        public static final class Builder5 {
            @NotNull

            /* renamed from: a  reason: collision with root package name */
            public JSONObject f2627a;

            public Builder5(@NotNull JSONObject jsonObject) {
                Intrinsics.checkNotNullParameter(jsonObject, "jsonObject");
                this.f2627a = jsonObject;
            }

            @NotNull
            public final Builder6 setNegativeBtnText(@NotNull String negativeBtnText) {
                Intrinsics.checkNotNullParameter(negativeBtnText, "negativeBtnText");
                JSONObject jSONObject = this.f2627a;
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("text", negativeBtnText);
                jSONObject2.put(Constants.KEY_RADIUS, "2");
                jSONObject.getJSONArray(Constants.KEY_BUTTONS).put(1, jSONObject2);
                return new Builder6(jSONObject);
            }
        }

        /* loaded from: classes2.dex */
        public static final class Builder6 {
            @NotNull

            /* renamed from: a  reason: collision with root package name */
            public JSONObject f2628a;
            @NotNull
            public final Function2<String, String, Unit> b;

            /* loaded from: classes2.dex */
            public static final class a extends Lambda implements Function2<String, String, Unit> {
                public a() {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(String str, String str2) {
                    invoke2(str, str2);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2(@NotNull String key, @NotNull String value) {
                    Intrinsics.checkNotNullParameter(key, "key");
                    Intrinsics.checkNotNullParameter(value, "value");
                    Integer[] numArr = {0, 1};
                    Builder6 builder6 = Builder6.this;
                    for (int i = 0; i < 2; i++) {
                        builder6.f2628a.getJSONArray(Constants.KEY_BUTTONS).getJSONObject(numArr[i].intValue()).put(key, value);
                    }
                }
            }

            public Builder6(@NotNull JSONObject jsonObject) {
                Intrinsics.checkNotNullParameter(jsonObject, "jsonObject");
                this.f2628a = jsonObject;
                this.b = new a();
            }

            @NotNull
            public final JSONObject build() {
                return this.f2628a;
            }

            @NotNull
            public final Builder6 setBackgroundColor(@NotNull String backgroundColor) {
                Intrinsics.checkNotNullParameter(backgroundColor, "backgroundColor");
                this.f2628a.put(Constants.KEY_BG, backgroundColor);
                return this;
            }

            @NotNull
            public final Builder6 setBtnBackgroundColor(@NotNull String btnBackgroundColor) {
                Intrinsics.checkNotNullParameter(btnBackgroundColor, "btnBackgroundColor");
                this.b.invoke(Constants.KEY_BG, btnBackgroundColor);
                return this;
            }

            @NotNull
            public final Builder6 setBtnBorderColor(@NotNull String btnBorderColor) {
                Intrinsics.checkNotNullParameter(btnBorderColor, "btnBorderColor");
                this.b.invoke(Constants.KEY_BORDER, btnBorderColor);
                return this;
            }

            @NotNull
            public final Builder6 setBtnBorderRadius(@NotNull String btnBorderRadius) {
                Intrinsics.checkNotNullParameter(btnBorderRadius, "btnBorderRadius");
                this.b.invoke(Constants.KEY_RADIUS, btnBorderRadius);
                return this;
            }

            @NotNull
            public final Builder6 setBtnTextColor(@NotNull String btnTextColor) {
                Intrinsics.checkNotNullParameter(btnTextColor, "btnTextColor");
                this.b.invoke("color", btnTextColor);
                return this;
            }

            @NotNull
            public final Builder6 setFallbackToSettings(boolean z) {
                this.f2628a.put(CTLocalInApp.FALLBACK_TO_NOTIFICATION_SETTINGS, z);
                return this;
            }

            @NotNull
            public final Builder6 setImageUrl(@NotNull String imageUrl) {
                Intrinsics.checkNotNullParameter(imageUrl, "imageUrl");
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("url", imageUrl);
                jSONObject.put("content_type", "image");
                JSONObject jSONObject2 = this.f2628a;
                jSONObject2.put(Constants.KEY_MEDIA, jSONObject);
                if (jSONObject2.getBoolean(Constants.KEY_LANDSCAPE)) {
                    jSONObject2.put(Constants.KEY_MEDIA_LANDSCAPE, jSONObject);
                }
                return this;
            }

            @NotNull
            public final Builder6 setMessageTextColor(@NotNull String messageTextColor) {
                Intrinsics.checkNotNullParameter(messageTextColor, "messageTextColor");
                this.f2628a.getJSONObject(Constants.KEY_MESSAGE).put("color", messageTextColor);
                return this;
            }

            @NotNull
            public final Builder6 setTitleTextColor(@NotNull String titleTextColor) {
                Intrinsics.checkNotNullParameter(titleTextColor, "titleTextColor");
                this.f2628a.getJSONObject("title").put("color", titleTextColor);
                return this;
            }
        }

        @NotNull
        public final Builder1 setInAppType(@NotNull InAppType inAppType) {
            Intrinsics.checkNotNullParameter(inAppType, "inAppType");
            JSONObject jSONObject = this.f2622a;
            jSONObject.put("type", inAppType.getType());
            jSONObject.put(CTLocalInApp.IS_LOCAL_INAPP, true);
            jSONObject.put(Constants.KEY_HIDE_CLOSE, true);
            return new Builder1(jSONObject);
        }
    }

    /* loaded from: classes2.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final Builder builder() {
            return new Builder();
        }
    }

    /* JADX WARN: Enum visitor error
    jadx.core.utils.exceptions.JadxRuntimeException: Init of enum ALERT uses external variables
    	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:444)
    	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByField(EnumVisitor.java:368)
    	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByWrappedInsn(EnumVisitor.java:333)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:318)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:258)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInvoke(EnumVisitor.java:289)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
    	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
    	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
     */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* loaded from: classes2.dex */
    public static final class InAppType {
        private static final /* synthetic */ InAppType[] $VALUES;
        public static final InAppType ALERT;
        public static final InAppType HALF_INTERSTITIAL;
        @NotNull
        private final String type;

        private static final /* synthetic */ InAppType[] $values() {
            return new InAppType[]{ALERT, HALF_INTERSTITIAL};
        }

        static {
            String cTInAppType = CTInAppType.CTInAppTypeAlert.toString();
            Intrinsics.checkNotNullExpressionValue(cTInAppType, "CTInAppTypeAlert.toString()");
            ALERT = new InAppType("ALERT", 0, cTInAppType);
            String cTInAppType2 = CTInAppType.CTInAppTypeHalfInterstitial.toString();
            Intrinsics.checkNotNullExpressionValue(cTInAppType2, "CTInAppTypeHalfInterstitial.toString()");
            HALF_INTERSTITIAL = new InAppType("HALF_INTERSTITIAL", 1, cTInAppType2);
            $VALUES = $values();
        }

        private InAppType(String str, int i, String str2) {
            this.type = str2;
        }

        public static InAppType valueOf(String str) {
            return (InAppType) Enum.valueOf(InAppType.class, str);
        }

        public static InAppType[] values() {
            return (InAppType[]) $VALUES.clone();
        }

        @NotNull
        public final String getType() {
            return this.type;
        }
    }

    @JvmStatic
    @NotNull
    public static final Builder builder() {
        return Companion.builder();
    }
}
