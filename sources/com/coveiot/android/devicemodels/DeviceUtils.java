package com.coveiot.android.devicemodels;

import android.content.Context;
import android.provider.Settings;
import androidx.annotation.RequiresApi;
import com.android.volley.toolbox.JsonRequest;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.DeviceType;
import com.coveiot.android.devicemodels.DeviceConstants;
import com.coveiot.android.devicemodels.DeviceRemoteConfig;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.leonardo.utils.RrHrHelperKt;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.DeviceModelBean;
import com.coveiot.covepreferences.data.FeatureMapping;
import com.coveiot.sdk.ble.model.BleDeviceInfo;
import com.coveiot.utils.utility.LogHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ktx.Firebase;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.ktx.RemoteConfigKt;
import com.google.gson.Gson;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import javax.crypto.Cipher;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt__StringsKt;
import kotlin.text.Typography;
import kotlin.text.m;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class DeviceUtils {
    @NotNull
    public static final Companion Companion = new Companion(null);

    /* loaded from: classes4.dex */
    public static final class Companion {

        /* loaded from: classes4.dex */
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[DeviceType.values().length];
                try {
                    iArr[DeviceType.TOUCH_LUNAR_CALL_PLUS.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[DeviceType.TOUCH_LUNAR_CONNECT_PLUS.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[DeviceType.smaF2.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                try {
                    iArr[DeviceType.kh17.ordinal()] = 4;
                } catch (NoSuchFieldError unused4) {
                }
                try {
                    iArr[DeviceType.crpGPF5.ordinal()] = 5;
                } catch (NoSuchFieldError unused5) {
                }
                try {
                    iArr[DeviceType.smaR9.ordinal()] = 6;
                } catch (NoSuchFieldError unused6) {
                }
                try {
                    iArr[DeviceType.matrix.ordinal()] = 7;
                } catch (NoSuchFieldError unused7) {
                }
                try {
                    iArr[DeviceType.WAVEARMOUR.ordinal()] = 8;
                } catch (NoSuchFieldError unused8) {
                }
                try {
                    iArr[DeviceType.WAVEFORCE.ordinal()] = 9;
                } catch (NoSuchFieldError unused9) {
                }
                try {
                    iArr[DeviceType.jstyle1860.ordinal()] = 10;
                } catch (NoSuchFieldError unused10) {
                }
                try {
                    iArr[DeviceType.CZ0.ordinal()] = 11;
                } catch (NoSuchFieldError unused11) {
                }
                try {
                    iArr[DeviceType.CZ3.ordinal()] = 12;
                } catch (NoSuchFieldError unused12) {
                }
                try {
                    iArr[DeviceType.wavePrime.ordinal()] = 13;
                } catch (NoSuchFieldError unused13) {
                }
                try {
                    iArr[DeviceType.WAVE_ELITE.ordinal()] = 14;
                } catch (NoSuchFieldError unused14) {
                }
                try {
                    iArr[DeviceType.CA0.ordinal()] = 15;
                } catch (NoSuchFieldError unused15) {
                }
                try {
                    iArr[DeviceType.CA3.ordinal()] = 16;
                } catch (NoSuchFieldError unused16) {
                }
                try {
                    iArr[DeviceType.IDO_SELECT.ordinal()] = 17;
                } catch (NoSuchFieldError unused17) {
                }
                try {
                    iArr[DeviceType.CA3_BT_CALL.ordinal()] = 18;
                } catch (NoSuchFieldError unused18) {
                }
                try {
                    iArr[DeviceType.CA2.ordinal()] = 19;
                } catch (NoSuchFieldError unused19) {
                }
                try {
                    iArr[DeviceType.CA5_WAVE_STYLE.ordinal()] = 20;
                } catch (NoSuchFieldError unused20) {
                }
                try {
                    iArr[DeviceType.CA5_WAVE_PLAY.ordinal()] = 21;
                } catch (NoSuchFieldError unused21) {
                }
                try {
                    iArr[DeviceType.CA5_WAVE_BEAT.ordinal()] = 22;
                } catch (NoSuchFieldError unused22) {
                }
                try {
                    iArr[DeviceType.ULC3_WAVE_SMART.ordinal()] = 23;
                } catch (NoSuchFieldError unused23) {
                }
                try {
                    iArr[DeviceType.ULC2_WAVE_BEAT_PLUS.ordinal()] = 24;
                } catch (NoSuchFieldError unused24) {
                }
                try {
                    iArr[DeviceType.ULC2_WAVE_STYLE_PLUS.ordinal()] = 25;
                } catch (NoSuchFieldError unused25) {
                }
                try {
                    iArr[DeviceType.ULC2_WAVE_SMART_PLUS.ordinal()] = 26;
                } catch (NoSuchFieldError unused26) {
                }
                try {
                    iArr[DeviceType.ULC2_WAVE_LYNC.ordinal()] = 27;
                } catch (NoSuchFieldError unused27) {
                }
                try {
                    iArr[DeviceType.CA3_BT_STORM_PRO_CALL.ordinal()] = 28;
                } catch (NoSuchFieldError unused28) {
                }
                try {
                    iArr[DeviceType.CA3_BT_WAVE_COSMOS_PRO.ordinal()] = 29;
                } catch (NoSuchFieldError unused29) {
                }
                try {
                    iArr[DeviceType.CA3_WAVE_COSMOS.ordinal()] = 30;
                } catch (NoSuchFieldError unused30) {
                }
                try {
                    iArr[DeviceType.IDO_CONNECT.ordinal()] = 31;
                } catch (NoSuchFieldError unused31) {
                }
                try {
                    iArr[DeviceType.CY1_PRIMIA_VOICE.ordinal()] = 32;
                } catch (NoSuchFieldError unused32) {
                }
                try {
                    iArr[DeviceType.CY1_LOOP_CALL_PRO.ordinal()] = 33;
                } catch (NoSuchFieldError unused33) {
                }
                try {
                    iArr[DeviceType.CY1_LOOP_CONNECT_PRO.ordinal()] = 34;
                } catch (NoSuchFieldError unused34) {
                }
                try {
                    iArr[DeviceType.TOUCH_WAVE_CALL_PLUS.ordinal()] = 35;
                } catch (NoSuchFieldError unused35) {
                }
                try {
                    iArr[DeviceType.TOUCH_WAVE_CONNECT_PLUS.ordinal()] = 36;
                } catch (NoSuchFieldError unused36) {
                }
                try {
                    iArr[DeviceType.TOUCH_LUNAR_CALL.ordinal()] = 37;
                } catch (NoSuchFieldError unused37) {
                }
                try {
                    iArr[DeviceType.TOUCH_LUNAR_CONNECT.ordinal()] = 38;
                } catch (NoSuchFieldError unused38) {
                }
                try {
                    iArr[DeviceType.TOUCH_XTEND_CALL_PLUS.ordinal()] = 39;
                } catch (NoSuchFieldError unused39) {
                }
                try {
                    iArr[DeviceType.TOUCH_STORM_CONNECT_PLUS.ordinal()] = 40;
                } catch (NoSuchFieldError unused40) {
                }
                try {
                    iArr[DeviceType.ULC5_ULTIMA_CALL.ordinal()] = 41;
                } catch (NoSuchFieldError unused41) {
                }
                try {
                    iArr[DeviceType.ULC5_ULTIMA_CONNECT.ordinal()] = 42;
                } catch (NoSuchFieldError unused42) {
                }
                try {
                    iArr[DeviceType.EA_LEAP_CALL.ordinal()] = 43;
                } catch (NoSuchFieldError unused43) {
                }
                try {
                    iArr[DeviceType.EA_FLEX_CONNECT.ordinal()] = 44;
                } catch (NoSuchFieldError unused44) {
                }
                try {
                    iArr[DeviceType.EA_STRIDE_VOICE.ordinal()] = 45;
                } catch (NoSuchFieldError unused45) {
                }
                try {
                    iArr[DeviceType.EA_XTEND_PLUS.ordinal()] = 46;
                } catch (NoSuchFieldError unused46) {
                }
                try {
                    iArr[DeviceType.EA_STORM_PLUS.ordinal()] = 47;
                } catch (NoSuchFieldError unused47) {
                }
                try {
                    iArr[DeviceType.EA_COSMOS_PLUS.ordinal()] = 48;
                } catch (NoSuchFieldError unused48) {
                }
                try {
                    iArr[DeviceType.EA_LUNAR_CALL_ACE.ordinal()] = 49;
                } catch (NoSuchFieldError unused49) {
                }
                try {
                    iArr[DeviceType.EA_LUNAR_CONNECT_ACE.ordinal()] = 50;
                } catch (NoSuchFieldError unused50) {
                }
                try {
                    iArr[DeviceType.EA_PRIMIA_ACE.ordinal()] = 51;
                } catch (NoSuchFieldError unused51) {
                }
                try {
                    iArr[DeviceType.LUNARFIT.ordinal()] = 52;
                } catch (NoSuchFieldError unused52) {
                }
                try {
                    iArr[DeviceType.WAVEARMOUR2.ordinal()] = 53;
                } catch (NoSuchFieldError unused53) {
                }
                try {
                    iArr[DeviceType.WAVEFORCE2.ordinal()] = 54;
                } catch (NoSuchFieldError unused54) {
                }
                try {
                    iArr[DeviceType.WAVECALL2.ordinal()] = 55;
                } catch (NoSuchFieldError unused55) {
                }
                try {
                    iArr[DeviceType.STORMCALL2.ordinal()] = 56;
                } catch (NoSuchFieldError unused56) {
                }
                try {
                    iArr[DeviceType.WAVEASTRA.ordinal()] = 57;
                } catch (NoSuchFieldError unused57) {
                }
                try {
                    iArr[DeviceType.WAVESIGMA.ordinal()] = 58;
                } catch (NoSuchFieldError unused58) {
                }
                try {
                    iArr[DeviceType.WAVENEOPLUS.ordinal()] = 59;
                } catch (NoSuchFieldError unused59) {
                }
                try {
                    iArr[DeviceType.WAVEACTIVE.ordinal()] = 60;
                } catch (NoSuchFieldError unused60) {
                }
                try {
                    iArr[DeviceType.ULTIMAPRISM.ordinal()] = 61;
                } catch (NoSuchFieldError unused61) {
                }
                try {
                    iArr[DeviceType.ULTIMACHRONOS.ordinal()] = 62;
                } catch (NoSuchFieldError unused62) {
                }
                try {
                    iArr[DeviceType.WAVECONVEX.ordinal()] = 63;
                } catch (NoSuchFieldError unused63) {
                }
                try {
                    iArr[DeviceType.LUNARORB.ordinal()] = 64;
                } catch (NoSuchFieldError unused64) {
                }
                try {
                    iArr[DeviceType.LUNARPRIME.ordinal()] = 65;
                } catch (NoSuchFieldError unused65) {
                }
                try {
                    iArr[DeviceType.PRIMIACURV.ordinal()] = 66;
                } catch (NoSuchFieldError unused66) {
                }
                try {
                    iArr[DeviceType.XTENDPRO2.ordinal()] = 67;
                } catch (NoSuchFieldError unused67) {
                }
                try {
                    iArr[DeviceType.STROMPROCALL2.ordinal()] = 68;
                } catch (NoSuchFieldError unused68) {
                }
                try {
                    iArr[DeviceType.LUNARCALLPRO2.ordinal()] = 69;
                } catch (NoSuchFieldError unused69) {
                }
                try {
                    iArr[DeviceType.LUNARCONNECTPRO2.ordinal()] = 70;
                } catch (NoSuchFieldError unused70) {
                }
                try {
                    iArr[DeviceType.WAVEPRIMIATALK2.ordinal()] = 71;
                } catch (NoSuchFieldError unused71) {
                }
                try {
                    iArr[DeviceType.ULTIMACALLPRO.ordinal()] = 72;
                } catch (NoSuchFieldError unused72) {
                }
                try {
                    iArr[DeviceType.ULTIMACONNECTPRO.ordinal()] = 73;
                } catch (NoSuchFieldError unused73) {
                }
                try {
                    iArr[DeviceType.WAVECOSMOSTALK.ordinal()] = 74;
                } catch (NoSuchFieldError unused74) {
                }
                try {
                    iArr[DeviceType.TOUCH_WAVE_NEO.ordinal()] = 75;
                } catch (NoSuchFieldError unused75) {
                }
                try {
                    iArr[DeviceType.SMA_WAVE_GENESIS_PRO.ordinal()] = 76;
                } catch (NoSuchFieldError unused76) {
                }
                try {
                    iArr[DeviceType.SMA_WAVE_ELEVATE_PRO.ordinal()] = 77;
                } catch (NoSuchFieldError unused77) {
                }
                try {
                    iArr[DeviceType.SMA_WAVE_GLORY_PRO.ordinal()] = 78;
                } catch (NoSuchFieldError unused78) {
                }
                try {
                    iArr[DeviceType.SMA_LUNAR_SEEK.ordinal()] = 79;
                } catch (NoSuchFieldError unused79) {
                }
                try {
                    iArr[DeviceType.SMA_ULTIMA_VOGUE.ordinal()] = 80;
                } catch (NoSuchFieldError unused80) {
                }
                try {
                    iArr[DeviceType.SMA_LUNAR_COMET.ordinal()] = 81;
                } catch (NoSuchFieldError unused81) {
                }
                try {
                    iArr[DeviceType.SMA_LUNAR_VELOCITY.ordinal()] = 82;
                } catch (NoSuchFieldError unused82) {
                }
                try {
                    iArr[DeviceType.TOUCH_WAVE_MAGMA.ordinal()] = 83;
                } catch (NoSuchFieldError unused83) {
                }
                try {
                    iArr[DeviceType.PS1_ENIGMA_OASIS.ordinal()] = 84;
                } catch (NoSuchFieldError unused84) {
                }
                try {
                    iArr[DeviceType.TOUCH_LUNAR_EMBRACE.ordinal()] = 85;
                } catch (NoSuchFieldError unused85) {
                }
                try {
                    iArr[DeviceType.TOUCH_WAVE_SPECTRA.ordinal()] = 86;
                } catch (NoSuchFieldError unused86) {
                }
                try {
                    iArr[DeviceType.ULTIMA_RISE.ordinal()] = 87;
                } catch (NoSuchFieldError unused87) {
                }
                try {
                    iArr[DeviceType.WAVE_REGAL.ordinal()] = 88;
                } catch (NoSuchFieldError unused88) {
                }
                try {
                    iArr[DeviceType.WAVE_CHASE.ordinal()] = 89;
                } catch (NoSuchFieldError unused89) {
                }
                try {
                    iArr[DeviceType.WAVECALL3.ordinal()] = 90;
                } catch (NoSuchFieldError unused90) {
                }
                try {
                    iArr[DeviceType.STORMCALL3.ordinal()] = 91;
                } catch (NoSuchFieldError unused91) {
                }
                try {
                    iArr[DeviceType.WAVEASTRA2.ordinal()] = 92;
                } catch (NoSuchFieldError unused92) {
                }
                try {
                    iArr[DeviceType.WAVESIGMA3.ordinal()] = 93;
                } catch (NoSuchFieldError unused93) {
                }
                try {
                    iArr[DeviceType.BES_ENIGMA_VIRTUO.ordinal()] = 94;
                } catch (NoSuchFieldError unused94) {
                }
                try {
                    iArr[DeviceType.jstyle1810G.ordinal()] = 95;
                } catch (NoSuchFieldError unused95) {
                }
                try {
                    iArr[DeviceType.jstyle1790.ordinal()] = 96;
                } catch (NoSuchFieldError unused96) {
                }
                try {
                    iArr[DeviceType.jstyle1963D.ordinal()] = 97;
                } catch (NoSuchFieldError unused97) {
                }
                try {
                    iArr[DeviceType.jstyle1963YH.ordinal()] = 98;
                } catch (NoSuchFieldError unused98) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ String appendUriForAnalytics$default(Companion companion, String str, String str2, int i, Object obj) {
            if ((i & 2) != 0) {
                str2 = null;
            }
            return companion.appendUriForAnalytics(str, str2);
        }

        public static final void d(Exception it) {
            Intrinsics.checkNotNullParameter(it, "it");
        }

        public static final void e(final FirebaseRemoteConfig remoteConfig, final Context mContext, Task task) {
            Intrinsics.checkNotNullParameter(remoteConfig, "$remoteConfig");
            Intrinsics.checkNotNullParameter(mContext, "$mContext");
            Intrinsics.checkNotNullParameter(task, "task");
            if (task.isSuccessful()) {
                Void r3 = (Void) task.getResult();
                remoteConfig.activate().addOnCompleteListener(new OnCompleteListener() { // from class: com.coveiot.android.devicemodels.a
                    @Override // com.google.android.gms.tasks.OnCompleteListener
                    public final void onComplete(Task task2) {
                        DeviceUtils.Companion.f(FirebaseRemoteConfig.this, mContext, task2);
                    }
                });
                return;
            }
            LogHelper.e("DeviceUtils", "Remote Config Failed getFeatureMapping");
        }

        public static final void f(FirebaseRemoteConfig remoteConfig, Context mContext, Task it) {
            Intrinsics.checkNotNullParameter(remoteConfig, "$remoteConfig");
            Intrinsics.checkNotNullParameter(mContext, "$mContext");
            Intrinsics.checkNotNullParameter(it, "it");
            String string = remoteConfig.getString("feature_mapping");
            Intrinsics.checkNotNullExpressionValue(string, "remoteConfig.getString(\"feature_mapping\")");
            FeatureMapping featureMapping = (FeatureMapping) new Gson().fromJson(string, (Class<Object>) FeatureMapping.class);
            if (featureMapping != null) {
                UserDataManager.getInstance(mContext).saveFeatureMappingConfig(featureMapping);
            }
        }

        @NotNull
        public final String appendUri(@NotNull String uri, @NotNull String appendQuery) {
            String str;
            Intrinsics.checkNotNullParameter(uri, "uri");
            Intrinsics.checkNotNullParameter(appendQuery, "appendQuery");
            try {
                URI uri2 = new URI(uri);
                String str2 = "deviceAgent=" + appendQuery;
                String query = uri2.getQuery();
                if (query != null) {
                    String str3 = query + Typography.amp + str2;
                    if (str3 != null) {
                        str = str3;
                        String uri3 = new URI(uri2.getScheme(), uri2.getAuthority(), uri2.getPath(), str, uri2.getFragment()).toString();
                        Intrinsics.checkNotNullExpressionValue(uri3, "{\n                val ol….toString()\n            }");
                        return uri3;
                    }
                }
                str = str2;
                String uri32 = new URI(uri2.getScheme(), uri2.getAuthority(), uri2.getPath(), str, uri2.getFragment()).toString();
                Intrinsics.checkNotNullExpressionValue(uri32, "{\n                val ol….toString()\n            }");
                return uri32;
            } catch (URISyntaxException unused) {
                return uri + "?deviceAgent=" + appendQuery;
            }
        }

        @NotNull
        public final String appendUriForAnalytics(@NotNull String uri, @Nullable String str) {
            String str2;
            Intrinsics.checkNotNullParameter(uri, "uri");
            try {
                URI uri2 = new URI(uri);
                String str3 = "_ax=" + str;
                String query = uri2.getQuery();
                if (query != null) {
                    String str4 = query + Typography.amp + str3;
                    if (str4 != null) {
                        str2 = str4;
                        String uri3 = new URI(uri2.getScheme(), uri2.getAuthority(), uri2.getPath(), str2, uri2.getFragment()).toString();
                        Intrinsics.checkNotNullExpressionValue(uri3, "{\n                val ol….toString()\n            }");
                        return uri3;
                    }
                }
                str2 = str3;
                String uri32 = new URI(uri2.getScheme(), uri2.getAuthority(), uri2.getPath(), str2, uri2.getFragment()).toString();
                Intrinsics.checkNotNullExpressionValue(uri32, "{\n                val ol….toString()\n            }");
                return uri32;
            } catch (URISyntaxException unused) {
                return uri + "?_ax=" + str;
            }
        }

        @RequiresApi(26)
        @Nullable
        public final String decryptRSA(@NotNull Context context, @Nullable String str) {
            Intrinsics.checkNotNullParameter(context, "context");
            try {
                String keyString = SessionManager.getInstance(context).getRsaPrivateKey();
                Base64.Decoder decoder = Base64.getDecoder();
                Intrinsics.checkNotNullExpressionValue(keyString, "keyString");
                Charset forName = Charset.forName(JsonRequest.PROTOCOL_CHARSET);
                Intrinsics.checkNotNullExpressionValue(forName, "forName(charsetName)");
                byte[] bytes = keyString.getBytes(forName);
                Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
                byte[] decode = decoder.decode(bytes);
                Intrinsics.checkNotNullExpressionValue(decode, "getDecoder().decode(keyS…eArray(charset(\"utf-8\")))");
                PrivateKey generatePrivate = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decode));
                Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1PADDING");
                cipher.init(2, generatePrivate);
                return StringsKt__StringsKt.trim(new String(cipher.doFinal(Base64.getDecoder().decode(str)), Charsets.UTF_8)).toString();
            } catch (Exception e) {
                LogHelper.d("decryptRSA Exception: ", "Exception = : " + e.getMessage());
                return null;
            }
        }

        @Nullable
        public final DeviceType getBleDeviceType(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            String deviceType = SessionManager.getInstance(context).getDeviceType();
            if (deviceType != null) {
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.smaf2_device))) {
                    return DeviceType.smaF2;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.moyangy20_device))) {
                    return DeviceType.kh17;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.cove_cz1))) {
                    return DeviceType.CZ0;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.moyangygpf5_device))) {
                    return DeviceType.crpGPF5;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.j1860_device))) {
                    return DeviceType.jstyle1860;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.smas12_device))) {
                    return DeviceType.smaR9;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.matrix_device))) {
                    return DeviceType.matrix;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.cove_cz3))) {
                    return DeviceType.CZ3;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.cove_wave_prime))) {
                    return DeviceType.wavePrime;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.cove_wave_elite))) {
                    return DeviceType.WAVE_ELITE;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.cove_ca0))) {
                    return DeviceType.CA0;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.cove_ca3))) {
                    return DeviceType.CA3;
                }
                int i = R.string.ido_select;
                if (Intrinsics.areEqual(deviceType, context.getString(i))) {
                    return DeviceType.IDO_SELECT;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.cove_ca3_bt))) {
                    return DeviceType.CA3_BT_CALL;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.cove_ca2))) {
                    return DeviceType.CA2;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.cove_ca3_wave_cosmos))) {
                    return DeviceType.CA3_WAVE_COSMOS;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.cove_ca3_bt_wave_cosmsos_pro))) {
                    return DeviceType.CA3_BT_WAVE_COSMOS_PRO;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.cove_ca3_bt_stormpro_call))) {
                    return DeviceType.CA3_BT_STORM_PRO_CALL;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.cove_ca5_wave_play))) {
                    return DeviceType.CA5_WAVE_PLAY;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.cove_ca5_wave_style))) {
                    return DeviceType.CA5_WAVE_STYLE;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.cove_ca5_wave_beat))) {
                    return DeviceType.CA5_WAVE_BEAT;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.cove_ulc3_wave_smart))) {
                    return DeviceType.ULC3_WAVE_SMART;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.cove_ulc2_wave_beat_plus))) {
                    return DeviceType.ULC2_WAVE_BEAT_PLUS;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.cove_ulc2_wave_style_plus))) {
                    return DeviceType.ULC2_WAVE_STYLE_PLUS;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.cove_ulc2_wave_smart_plus))) {
                    return DeviceType.ULC2_WAVE_SMART_PLUS;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.cove_ulc2_wave_lync))) {
                    return DeviceType.ULC2_WAVE_LYNC;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.ido_connect))) {
                    return DeviceType.IDO_CONNECT;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(i))) {
                    return DeviceType.IDO_SELECT;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.cy1_primia_voice))) {
                    return DeviceType.CY1_PRIMIA_VOICE;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.cy1_loop_call_pro))) {
                    return DeviceType.CY1_LOOP_CALL_PRO;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.cy1_loop_connect_pro))) {
                    return DeviceType.CY1_LOOP_CONNECT_PRO;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.wave_call_plus))) {
                    return DeviceType.TOUCH_WAVE_CALL_PLUS;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.sma_wave_genesis_pro))) {
                    return DeviceType.SMA_WAVE_GENESIS_PRO;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.sma_wave_elevate_pro))) {
                    return DeviceType.SMA_WAVE_ELEVATE_PRO;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.sma_wave_glory_pro))) {
                    return DeviceType.SMA_WAVE_GLORY_PRO;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.sma_ultima_vogue))) {
                    return DeviceType.SMA_ULTIMA_VOGUE;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.sma_lunar_seek))) {
                    return DeviceType.SMA_LUNAR_SEEK;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.sma_lunar_comet))) {
                    return DeviceType.SMA_LUNAR_COMET;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.sma_lunar_velocity))) {
                    return DeviceType.SMA_LUNAR_VELOCITY;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.wave_connect_plus))) {
                    return DeviceType.TOUCH_WAVE_CONNECT_PLUS;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.lunar_call))) {
                    return DeviceType.TOUCH_LUNAR_CALL;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.lunar_connect))) {
                    return DeviceType.TOUCH_LUNAR_CONNECT;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.lunar_call_plus))) {
                    return DeviceType.TOUCH_LUNAR_CALL_PLUS;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.lunar_connect_plus))) {
                    return DeviceType.TOUCH_LUNAR_CONNECT_PLUS;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.xtend_call_plus))) {
                    return DeviceType.TOUCH_XTEND_CALL_PLUS;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.storm_connect_plus))) {
                    return DeviceType.TOUCH_STORM_CONNECT_PLUS;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.ultima_call))) {
                    return DeviceType.ULC5_ULTIMA_CALL;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.ultima_connect))) {
                    return DeviceType.ULC5_ULTIMA_CONNECT;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.wave_force))) {
                    return DeviceType.WAVEFORCE;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.wave_armour))) {
                    return DeviceType.WAVEARMOUR;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.leap_call))) {
                    return DeviceType.EA_LEAP_CALL;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.flex_connect))) {
                    return DeviceType.EA_FLEX_CONNECT;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.stride_voice))) {
                    return DeviceType.EA_STRIDE_VOICE;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.lunar_connect_ace))) {
                    return DeviceType.EA_LUNAR_CONNECT_ACE;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.lunar_call_ace))) {
                    return DeviceType.EA_LUNAR_CALL_ACE;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.primia_ace))) {
                    return DeviceType.EA_PRIMIA_ACE;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.xtend_plus))) {
                    return DeviceType.EA_XTEND_PLUS;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.storm_plus))) {
                    return DeviceType.EA_STORM_PLUS;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.cosmos_plus))) {
                    return DeviceType.EA_COSMOS_PLUS;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.lunar_fit))) {
                    return DeviceType.LUNARFIT;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.wave_armour2))) {
                    return DeviceType.WAVEARMOUR2;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.wave_force2))) {
                    return DeviceType.WAVEFORCE2;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.wave_call_2))) {
                    return DeviceType.WAVECALL2;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.storm_call_2))) {
                    return DeviceType.STORMCALL2;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.wave_astra))) {
                    return DeviceType.WAVEASTRA;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.wave_sigma))) {
                    return DeviceType.WAVESIGMA;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.wave_neo_plus))) {
                    return DeviceType.WAVENEOPLUS;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.wave_active))) {
                    return DeviceType.WAVEACTIVE;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.ultima_prism))) {
                    return DeviceType.ULTIMAPRISM;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.ultima_chronos))) {
                    return DeviceType.ULTIMACHRONOS;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.wave_convex))) {
                    return DeviceType.WAVECONVEX;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.lunar_orb))) {
                    return DeviceType.LUNARORB;
                }
                int i2 = R.string.lunar_prime;
                if (Intrinsics.areEqual(deviceType, context.getString(i2))) {
                    return DeviceType.LUNARPRIME;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(i2))) {
                    return DeviceType.LUNARPRIME;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.primia_curv))) {
                    return DeviceType.PRIMIACURV;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.xtend_pro_2))) {
                    return DeviceType.XTENDPRO2;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.storm_pro_call_2))) {
                    return DeviceType.STROMPROCALL2;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.lunar_call_pro_2))) {
                    return DeviceType.LUNARCALLPRO2;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.lunar_connect_pro_2))) {
                    return DeviceType.LUNARCONNECTPRO2;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.wave_primia_talk_2))) {
                    return DeviceType.WAVEPRIMIATALK2;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.ultima_call_pro))) {
                    return DeviceType.ULTIMACALLPRO;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.ultima_connect_pro))) {
                    return DeviceType.ULTIMACONNECTPRO;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.wave_cosmos_talk))) {
                    return DeviceType.WAVECOSMOSTALK;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.wave_neo))) {
                    return DeviceType.TOUCH_WAVE_NEO;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.wave_magma))) {
                    return DeviceType.TOUCH_WAVE_MAGMA;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.enigma_oasis))) {
                    return DeviceType.PS1_ENIGMA_OASIS;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.lunar_embrace))) {
                    return DeviceType.TOUCH_LUNAR_EMBRACE;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.wave_spectra))) {
                    return DeviceType.TOUCH_WAVE_SPECTRA;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.ultima_rise))) {
                    return DeviceType.ULTIMA_RISE;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.wave_regal))) {
                    return DeviceType.WAVE_REGAL;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.wave_chase))) {
                    return DeviceType.WAVE_CHASE;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.wave_call_3))) {
                    return DeviceType.WAVECALL3;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.storm_call_3))) {
                    return DeviceType.STORMCALL3;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.wave_astra_2))) {
                    return DeviceType.WAVEASTRA2;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.wave_sigma_3))) {
                    return DeviceType.WAVESIGMA3;
                }
                if (Intrinsics.areEqual(deviceType, context.getString(R.string.enigma_virtuo))) {
                    return DeviceType.BES_ENIGMA_VIRTUO;
                }
                return null;
            }
            return null;
        }

        @NotNull
        public final HashMap<String, String> getDeviceId(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("Mobile device id (mac)", string);
            return hashMap;
        }

        @NotNull
        public final DeviceRemoteConfig.DeviceModelsBean getDeviceModelFromDeviceType(@NotNull DeviceType deviceType) {
            Intrinsics.checkNotNullParameter(deviceType, "deviceType");
            DeviceRemoteConfig.DeviceModelsBean deviceModelsBean = new DeviceRemoteConfig.DeviceModelsBean();
            switch (WhenMappings.$EnumSwitchMapping$0[deviceType.ordinal()]) {
                case 1:
                    deviceModelsBean.setType(DeviceConstants.Companion.getLUNAR_CALL_PLUS());
                    return deviceModelsBean;
                case 2:
                    deviceModelsBean.setType(DeviceConstants.Companion.getLUNAR_CONNECT_PLUS());
                    return deviceModelsBean;
                case 3:
                    deviceModelsBean.setType(DeviceConstants.Companion.getMERCURY());
                    return deviceModelsBean;
                case 4:
                    deviceModelsBean.setType(DeviceConstants.Companion.getVERTEX());
                    return deviceModelsBean;
                case 5:
                    deviceModelsBean.setType(DeviceConstants.Companion.getWAVEFIT());
                    return deviceModelsBean;
                case 6:
                    deviceModelsBean.setType(DeviceConstants.Companion.getPRIMIA());
                    return deviceModelsBean;
                case 7:
                    deviceModelsBean.setType(DeviceConstants.Companion.getMATRIX());
                    return deviceModelsBean;
                case 8:
                    deviceModelsBean.setType(DeviceConstants.Companion.getWAVE_ARMOUR());
                    return deviceModelsBean;
                case 9:
                    deviceModelsBean.setType(DeviceConstants.Companion.getWAVE_FORCE());
                    return deviceModelsBean;
                case 10:
                    deviceModelsBean.setType(DeviceConstants.Companion.getWANDERER());
                    return deviceModelsBean;
                case 11:
                    deviceModelsBean.setType(DeviceConstants.Companion.getWAVEPRO());
                    return deviceModelsBean;
                case 12:
                    deviceModelsBean.setType(DeviceConstants.Companion.getWAVEPlUS());
                    return deviceModelsBean;
                case 13:
                    deviceModelsBean.setType(DeviceConstants.Companion.getWAVEPRIME());
                    return deviceModelsBean;
                case 14:
                    deviceModelsBean.setType(DeviceConstants.Companion.getWAVEELITE());
                    return deviceModelsBean;
                case 15:
                    deviceModelsBean.setType(DeviceConstants.Companion.getCA_0());
                    return deviceModelsBean;
                case 16:
                    deviceModelsBean.setType(DeviceConstants.Companion.getSTORMPRO());
                    return deviceModelsBean;
                case 17:
                    deviceModelsBean.setType(DeviceConstants.Companion.getIDO_SELECT());
                    return deviceModelsBean;
                case 18:
                    deviceModelsBean.setType(DeviceConstants.Companion.getXTENDPROBT3());
                    return deviceModelsBean;
                case 19:
                    deviceModelsBean.setType(DeviceConstants.Companion.getWAVEACTIVE());
                    return deviceModelsBean;
                case 20:
                    deviceModelsBean.setType(DeviceConstants.Companion.getWAVESTYLE());
                    return deviceModelsBean;
                case 21:
                    deviceModelsBean.setType(DeviceConstants.Companion.getWAVEPLAY());
                    return deviceModelsBean;
                case 22:
                    deviceModelsBean.setType(DeviceConstants.Companion.getWAVEBEAT());
                    return deviceModelsBean;
                case 23:
                    deviceModelsBean.setType(DeviceConstants.Companion.getWAVESMART());
                    return deviceModelsBean;
                case 24:
                    deviceModelsBean.setType(DeviceConstants.Companion.getBEATPLUS());
                    return deviceModelsBean;
                case 25:
                    deviceModelsBean.setType(DeviceConstants.Companion.getSTYLEPLUS());
                    return deviceModelsBean;
                case 26:
                    deviceModelsBean.setType(DeviceConstants.Companion.getSMARTPLUS());
                    return deviceModelsBean;
                case 27:
                    deviceModelsBean.setType(DeviceConstants.Companion.getLYNC());
                    return deviceModelsBean;
                case 28:
                    deviceModelsBean.setType(DeviceConstants.Companion.getSTORMPROCALL());
                    return deviceModelsBean;
                case 29:
                    deviceModelsBean.setType(DeviceConstants.Companion.getWAVECOSMOSPRO());
                    return deviceModelsBean;
                case 30:
                    deviceModelsBean.setType(DeviceConstants.Companion.getWAVECOSMOS());
                    return deviceModelsBean;
                case 31:
                    deviceModelsBean.setType(DeviceConstants.Companion.getIDO_CONNECT());
                    return deviceModelsBean;
                case 32:
                    deviceModelsBean.setType(DeviceConstants.Companion.getPRIMIA_VOICE());
                    return deviceModelsBean;
                case 33:
                    deviceModelsBean.setType(DeviceConstants.Companion.getLOOP_CALL_PRO());
                    return deviceModelsBean;
                case 34:
                    deviceModelsBean.setType(DeviceConstants.Companion.getLOOP_CONNECT_PRO());
                    return deviceModelsBean;
                case 35:
                    deviceModelsBean.setType(DeviceConstants.Companion.getWAVE_CALL_PLUS());
                    return deviceModelsBean;
                case 36:
                    deviceModelsBean.setType(DeviceConstants.Companion.getWAVE_CONNECT_PLUS());
                    return deviceModelsBean;
                case 37:
                    deviceModelsBean.setType(DeviceConstants.Companion.getLUNAR_CALL());
                    return deviceModelsBean;
                case 38:
                    deviceModelsBean.setType(DeviceConstants.Companion.getLUNAR_CONNECT());
                    return deviceModelsBean;
                case 39:
                    deviceModelsBean.setType(DeviceConstants.Companion.getXTEND_CALL_PLUS());
                    return deviceModelsBean;
                case 40:
                    deviceModelsBean.setType(DeviceConstants.Companion.getSTORM_CONNECT_PLUS());
                    return deviceModelsBean;
                case 41:
                    deviceModelsBean.setType(DeviceConstants.Companion.getULTIMA_CALL());
                    return deviceModelsBean;
                case 42:
                    deviceModelsBean.setType(DeviceConstants.Companion.getULTIMA_CONNECT());
                    return deviceModelsBean;
                case 43:
                    deviceModelsBean.setType(DeviceConstants.Companion.getLEAP_CALL());
                    return deviceModelsBean;
                case 44:
                    deviceModelsBean.setType(DeviceConstants.Companion.getFLEX_CONNECT());
                    return deviceModelsBean;
                case 45:
                    deviceModelsBean.setType(DeviceConstants.Companion.getSTRIDE_VOICE());
                    return deviceModelsBean;
                case 46:
                    deviceModelsBean.setType(DeviceConstants.Companion.getXTEND_PLUS());
                    return deviceModelsBean;
                case 47:
                    deviceModelsBean.setType(DeviceConstants.Companion.getSTORM_PLUS());
                    return deviceModelsBean;
                case 48:
                    deviceModelsBean.setType(DeviceConstants.Companion.getULTIMA_CONNECT());
                    return deviceModelsBean;
                case 49:
                    deviceModelsBean.setType(DeviceConstants.Companion.getLUNAR_CALL_ACE());
                    return deviceModelsBean;
                case 50:
                    deviceModelsBean.setType(DeviceConstants.Companion.getLUNAR_CONNECT_ACE());
                    return deviceModelsBean;
                case 51:
                    deviceModelsBean.setType(DeviceConstants.Companion.getPRIMIA_ACE());
                    return deviceModelsBean;
                case 52:
                    deviceModelsBean.setType(DeviceConstants.Companion.getLUNAR_FIT());
                    return deviceModelsBean;
                case 53:
                    deviceModelsBean.setType(DeviceConstants.Companion.getWAVE_ARMOUR_2());
                    return deviceModelsBean;
                case 54:
                    deviceModelsBean.setType(DeviceConstants.Companion.getWAVE_FORCE_2());
                    return deviceModelsBean;
                case 55:
                    deviceModelsBean.setType(DeviceConstants.Companion.getWAVECALL2());
                    return deviceModelsBean;
                case 56:
                    deviceModelsBean.setType(DeviceConstants.Companion.getSTORMCALL2());
                    return deviceModelsBean;
                case 57:
                    deviceModelsBean.setType(DeviceConstants.Companion.getWAVEASTRA());
                    return deviceModelsBean;
                case 58:
                    deviceModelsBean.setType(DeviceConstants.Companion.getWAVESIGMA());
                    return deviceModelsBean;
                case 59:
                    deviceModelsBean.setType(DeviceConstants.Companion.getWAVENEOPLUS());
                    return deviceModelsBean;
                case 60:
                    deviceModelsBean.setType(DeviceConstants.Companion.getWAVEACTIVE());
                    return deviceModelsBean;
                case 61:
                    deviceModelsBean.setType(DeviceConstants.Companion.getULTIMAPRISM());
                    return deviceModelsBean;
                case 62:
                    deviceModelsBean.setType(DeviceConstants.Companion.getULTIMACHRONOS());
                    return deviceModelsBean;
                case 63:
                    deviceModelsBean.setType(DeviceConstants.Companion.getWAVECONVEX());
                    return deviceModelsBean;
                case 64:
                    deviceModelsBean.setType(DeviceConstants.Companion.getLUNARORB());
                    return deviceModelsBean;
                case 65:
                    deviceModelsBean.setType(DeviceConstants.Companion.getLUNARPRIME());
                    return deviceModelsBean;
                case 66:
                    deviceModelsBean.setType(DeviceConstants.Companion.getPRIMIACURV());
                    return deviceModelsBean;
                case 67:
                    deviceModelsBean.setType(DeviceConstants.Companion.getXTENDPRO2());
                    return deviceModelsBean;
                case 68:
                    deviceModelsBean.setType(DeviceConstants.Companion.getSTORMPROCALL2());
                    return deviceModelsBean;
                case 69:
                    deviceModelsBean.setType(DeviceConstants.Companion.getLUNARCALLPRO2());
                    return deviceModelsBean;
                case 70:
                    deviceModelsBean.setType(DeviceConstants.Companion.getLUNARCONNECTPRO2());
                    return deviceModelsBean;
                case 71:
                    deviceModelsBean.setType(DeviceConstants.Companion.getWAVEPRIMIATALK2());
                    return deviceModelsBean;
                case 72:
                    deviceModelsBean.setType(DeviceConstants.Companion.getULTIMACALLPRO());
                    return deviceModelsBean;
                case 73:
                    deviceModelsBean.setType(DeviceConstants.Companion.getULTIMACONNECTPRO());
                    return deviceModelsBean;
                case 74:
                    deviceModelsBean.setType(DeviceConstants.Companion.getWAVECOSMOSTALK());
                    return deviceModelsBean;
                case 75:
                    deviceModelsBean.setType(DeviceConstants.Companion.getWAVE_NEO());
                    return deviceModelsBean;
                case 76:
                    deviceModelsBean.setType(DeviceConstants.Companion.getWAVEGENESISPRO());
                    return deviceModelsBean;
                case 77:
                    deviceModelsBean.setType(DeviceConstants.Companion.getWAVEELEVATEPRO());
                    return deviceModelsBean;
                case 78:
                    deviceModelsBean.setType(DeviceConstants.Companion.getWAVEGENESISPRO());
                    return deviceModelsBean;
                case 79:
                    deviceModelsBean.setType(DeviceConstants.Companion.getLUNARSEEK());
                    return deviceModelsBean;
                case 80:
                    deviceModelsBean.setType(DeviceConstants.Companion.getULTIMAVOGUE());
                    return deviceModelsBean;
                case 81:
                    deviceModelsBean.setType(DeviceConstants.Companion.getLUNARCOMET());
                    return deviceModelsBean;
                case 82:
                    deviceModelsBean.setType(DeviceConstants.Companion.getLUNARVELOCITY());
                    return deviceModelsBean;
                case 83:
                    deviceModelsBean.setType(DeviceConstants.Companion.getWAVE_MAGMA());
                    return deviceModelsBean;
                case 84:
                    deviceModelsBean.setType(DeviceConstants.Companion.getENIGMA_OASIS());
                    return deviceModelsBean;
                case 85:
                    deviceModelsBean.setType(DeviceConstants.Companion.getLUNAR_EMBRACE());
                    return deviceModelsBean;
                case 86:
                    deviceModelsBean.setType(DeviceConstants.Companion.getWAVE_SPECTRA());
                    return deviceModelsBean;
                case 87:
                    deviceModelsBean.setType(DeviceConstants.Companion.getULTIMA_RISE());
                    return deviceModelsBean;
                case 88:
                    deviceModelsBean.setType(DeviceConstants.Companion.getWAVE_REGAL());
                    return deviceModelsBean;
                case 89:
                    deviceModelsBean.setType(DeviceConstants.Companion.getWAVE_CHASE());
                    return deviceModelsBean;
                case 90:
                    deviceModelsBean.setType(DeviceConstants.Companion.getWAVECALL3());
                    return deviceModelsBean;
                case 91:
                    deviceModelsBean.setType(DeviceConstants.Companion.getSTORMCALL3());
                    return deviceModelsBean;
                case 92:
                    deviceModelsBean.setType(DeviceConstants.Companion.getWAVEASTRA2());
                    return deviceModelsBean;
                case 93:
                    deviceModelsBean.setType(DeviceConstants.Companion.getWAVESIGMA3());
                    return deviceModelsBean;
                case 94:
                    deviceModelsBean.setType(DeviceConstants.Companion.getENIGMAVIRTUO());
                    return deviceModelsBean;
                default:
                    deviceModelsBean.setType(DeviceConstants.Companion.getMERCURY());
                    return deviceModelsBean;
            }
        }

        @Nullable
        public final String getDeviceModelNameFromType(@NotNull Context context, @Nullable String str) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (str != null) {
                DeviceConstants.Companion companion = DeviceConstants.Companion;
                if (Intrinsics.areEqual(str, companion.getMERCURY())) {
                    return context.getString(R.string.smaf2_device);
                }
                if (Intrinsics.areEqual(str, companion.getVERTEX())) {
                    return context.getString(R.string.moyangy20_device);
                }
                if (Intrinsics.areEqual(str, companion.getWAVEPRO())) {
                    return context.getString(R.string.cove_cz1);
                }
                if (Intrinsics.areEqual(str, companion.getWAVEFIT())) {
                    return context.getString(R.string.moyangygpf5_device);
                }
                if (Intrinsics.areEqual(str, companion.getWANDERER())) {
                    return context.getString(R.string.j1860_device);
                }
                if (Intrinsics.areEqual(str, companion.getPRIMIA())) {
                    return context.getString(R.string.smas12_device);
                }
                if (Intrinsics.areEqual(str, companion.getWAVEGENESISPRO())) {
                    return context.getString(R.string.sma_wave_genesis_pro);
                }
                if (Intrinsics.areEqual(str, companion.getWAVEELEVATEPRO())) {
                    return context.getString(R.string.sma_wave_elevate_pro);
                }
                if (Intrinsics.areEqual(str, companion.getWAVEGLORYPRO())) {
                    return context.getString(R.string.sma_wave_glory_pro);
                }
                if (Intrinsics.areEqual(str, companion.getULTIMAVOGUE())) {
                    return context.getString(R.string.sma_ultima_vogue);
                }
                if (Intrinsics.areEqual(str, companion.getLUNARSEEK())) {
                    return context.getString(R.string.sma_lunar_seek);
                }
                if (Intrinsics.areEqual(str, companion.getULTIMA_RISE())) {
                    return context.getString(R.string.ultima_rise);
                }
                if (Intrinsics.areEqual(str, companion.getLUNARCOMET())) {
                    return context.getString(R.string.sma_lunar_comet);
                }
                if (Intrinsics.areEqual(str, companion.getLUNARVELOCITY())) {
                    return context.getString(R.string.sma_lunar_velocity);
                }
                if (Intrinsics.areEqual(str, companion.getMATRIX())) {
                    return context.getString(R.string.matrix_device);
                }
                if (Intrinsics.areEqual(str, companion.getWAVEPlUS())) {
                    return context.getString(R.string.cove_cz3);
                }
                if (Intrinsics.areEqual(str, companion.getWAVEPRIME())) {
                    return context.getString(R.string.cove_wave_prime);
                }
                if (Intrinsics.areEqual(str, companion.getWAVEELITE())) {
                    return context.getString(R.string.cove_wave_elite);
                }
                if (Intrinsics.areEqual(str, companion.getCA_0())) {
                    return context.getString(R.string.cove_ca0);
                }
                if (Intrinsics.areEqual(str, companion.getSTORMPRO())) {
                    return context.getString(R.string.cove_ca3);
                }
                if (Intrinsics.areEqual(str, companion.getIDO_SELECT())) {
                    return context.getString(R.string.ido_select);
                }
                if (Intrinsics.areEqual(str, companion.getXTENDPROBT3())) {
                    return context.getString(R.string.cove_ca3_bt);
                }
                if (Intrinsics.areEqual(str, companion.getPRIMIA_VOICE())) {
                    return context.getString(R.string.cy1_primia_voice);
                }
                if (Intrinsics.areEqual(str, companion.getLOOP_CALL_PRO())) {
                    return context.getString(R.string.cy1_loop_call_pro);
                }
                if (Intrinsics.areEqual(str, companion.getLOOP_CONNECT_PRO())) {
                    return context.getString(R.string.cy1_loop_connect_pro);
                }
                if (Intrinsics.areEqual(str, companion.getWAVECOSMOS())) {
                    return context.getString(R.string.cove_ca3_wave_cosmos);
                }
                if (Intrinsics.areEqual(str, companion.getWAVECOSMOSPRO())) {
                    return context.getString(R.string.cove_ca3_bt_wave_cosmsos_pro);
                }
                if (Intrinsics.areEqual(str, companion.getSTORMPROCALL())) {
                    return context.getString(R.string.cove_ca3_bt_stormpro_call);
                }
                if (Intrinsics.areEqual(str, companion.getWAVEPLAY())) {
                    return context.getString(R.string.cove_ca5_wave_play);
                }
                if (Intrinsics.areEqual(str, companion.getWAVESTYLE())) {
                    return context.getString(R.string.cove_ca5_wave_style);
                }
                if (Intrinsics.areEqual(str, companion.getWAVEBEAT())) {
                    return context.getString(R.string.cove_ca5_wave_beat);
                }
                if (Intrinsics.areEqual(str, companion.getWAVESMART())) {
                    return context.getString(R.string.cove_ulc3_wave_smart);
                }
                if (Intrinsics.areEqual(str, companion.getBEATPLUS())) {
                    return context.getString(R.string.cove_ulc2_wave_beat_plus);
                }
                if (Intrinsics.areEqual(str, companion.getSTYLEPLUS())) {
                    return context.getString(R.string.cove_ulc2_wave_style_plus);
                }
                if (Intrinsics.areEqual(str, companion.getSMARTPLUS())) {
                    return context.getString(R.string.cove_ulc2_wave_smart_plus);
                }
                if (Intrinsics.areEqual(str, companion.getLYNC())) {
                    return context.getString(R.string.cove_ulc2_wave_lync);
                }
                if (Intrinsics.areEqual(str, companion.getIDO_CONNECT())) {
                    return context.getString(R.string.ido_connect);
                }
                if (Intrinsics.areEqual(str, companion.getWAVE_CALL_PLUS())) {
                    return context.getString(R.string.wave_call_plus);
                }
                if (Intrinsics.areEqual(str, companion.getWAVE_CONNECT_PLUS())) {
                    return context.getString(R.string.wave_connect_plus);
                }
                if (Intrinsics.areEqual(str, companion.getLUNAR_CALL())) {
                    return context.getString(R.string.lunar_call);
                }
                if (Intrinsics.areEqual(str, companion.getLUNAR_CONNECT())) {
                    return context.getString(R.string.lunar_connect);
                }
                if (Intrinsics.areEqual(str, companion.getLUNAR_CALL_PLUS())) {
                    return context.getString(R.string.lunar_call_plus);
                }
                if (Intrinsics.areEqual(str, companion.getLUNAR_CONNECT_PLUS())) {
                    return context.getString(R.string.lunar_connect_plus);
                }
                if (Intrinsics.areEqual(str, companion.getXTEND_CALL_PLUS())) {
                    return context.getString(R.string.xtend_call_plus);
                }
                if (Intrinsics.areEqual(str, companion.getSTORM_CONNECT_PLUS())) {
                    return context.getString(R.string.storm_connect_plus);
                }
                if (Intrinsics.areEqual(str, companion.getULTIMA_CALL())) {
                    return context.getString(R.string.ultima_call);
                }
                if (Intrinsics.areEqual(str, companion.getULTIMA_CONNECT())) {
                    return context.getString(R.string.ultima_connect);
                }
                if (Intrinsics.areEqual(str, companion.getWAVE_ARMOUR())) {
                    return context.getString(R.string.wave_armour);
                }
                if (Intrinsics.areEqual(str, companion.getWAVE_FORCE())) {
                    return context.getString(R.string.wave_force);
                }
                if (Intrinsics.areEqual(str, companion.getLEAP_CALL())) {
                    return context.getString(R.string.leap_call);
                }
                if (Intrinsics.areEqual(str, companion.getSTRIDE_VOICE())) {
                    return context.getString(R.string.stride_voice);
                }
                if (Intrinsics.areEqual(str, companion.getFLEX_CONNECT())) {
                    return context.getString(R.string.flex_connect);
                }
                if (Intrinsics.areEqual(str, companion.getLUNAR_CALL_ACE())) {
                    return context.getString(R.string.lunar_call_ace);
                }
                if (Intrinsics.areEqual(str, companion.getLUNAR_CONNECT_ACE())) {
                    return context.getString(R.string.lunar_connect_ace);
                }
                if (Intrinsics.areEqual(str, companion.getPRIMIA_ACE())) {
                    return context.getString(R.string.primia_ace);
                }
                if (Intrinsics.areEqual(str, companion.getXTEND_PLUS())) {
                    return context.getString(R.string.xtend_plus);
                }
                if (Intrinsics.areEqual(str, companion.getSTORM_PLUS())) {
                    return context.getString(R.string.storm_plus);
                }
                if (Intrinsics.areEqual(str, companion.getCOSMOS_PLUS())) {
                    return context.getString(R.string.cosmos_plus);
                }
                if (Intrinsics.areEqual(str, companion.getLUNAR_FIT())) {
                    return context.getString(R.string.lunar_fit);
                }
                if (Intrinsics.areEqual(str, companion.getWAVE_ARMOUR_2())) {
                    return context.getString(R.string.wave_armour2);
                }
                if (Intrinsics.areEqual(str, companion.getWAVE_FORCE_2())) {
                    return context.getString(R.string.wave_force2);
                }
                if (Intrinsics.areEqual(str, companion.getWAVECALL2())) {
                    return context.getString(R.string.wave_call_2);
                }
                if (Intrinsics.areEqual(str, companion.getSTORMCALL2())) {
                    return context.getString(R.string.storm_call_2);
                }
                if (Intrinsics.areEqual(str, companion.getWAVEASTRA())) {
                    return context.getString(R.string.wave_astra);
                }
                if (Intrinsics.areEqual(str, companion.getWAVESIGMA())) {
                    return context.getString(R.string.wave_sigma);
                }
                if (Intrinsics.areEqual(str, companion.getWAVENEOPLUS())) {
                    return context.getString(R.string.wave_neo_plus);
                }
                if (Intrinsics.areEqual(str, companion.getWAVEACTIVE())) {
                    return context.getString(R.string.wave_active);
                }
                if (Intrinsics.areEqual(str, companion.getXTENDPRO2())) {
                    return context.getString(R.string.xtend_pro_2);
                }
                if (Intrinsics.areEqual(str, companion.getSTORMPROCALL2())) {
                    return context.getString(R.string.storm_pro_call_2);
                }
                if (Intrinsics.areEqual(str, companion.getLUNARCALLPRO2())) {
                    return context.getString(R.string.lunar_call_pro_2);
                }
                if (Intrinsics.areEqual(str, companion.getLUNARCONNECTPRO2())) {
                    return context.getString(R.string.lunar_connect_pro_2);
                }
                if (Intrinsics.areEqual(str, companion.getWAVEPRIMIATALK2())) {
                    return context.getString(R.string.wave_primia_talk_2);
                }
                if (Intrinsics.areEqual(str, companion.getULTIMACALLPRO())) {
                    return context.getString(R.string.ultima_call_pro);
                }
                if (Intrinsics.areEqual(str, companion.getULTIMACONNECTPRO())) {
                    return context.getString(R.string.ultima_connect_pro);
                }
                if (Intrinsics.areEqual(str, companion.getWAVECOSMOSTALK())) {
                    return context.getString(R.string.wave_cosmos_talk);
                }
                if (Intrinsics.areEqual(str, companion.getULTIMAPRISM())) {
                    return context.getString(R.string.ultima_prism);
                }
                if (Intrinsics.areEqual(str, companion.getULTIMACHRONOS())) {
                    return context.getString(R.string.ultima_chronos);
                }
                if (Intrinsics.areEqual(str, companion.getWAVECONVEX())) {
                    return context.getString(R.string.wave_convex);
                }
                if (Intrinsics.areEqual(str, companion.getLUNARORB())) {
                    return context.getString(R.string.lunar_orb);
                }
                if (Intrinsics.areEqual(str, companion.getLUNARPRIME())) {
                    return context.getString(R.string.lunar_prime);
                }
                if (Intrinsics.areEqual(str, companion.getPRIMIACURV())) {
                    return context.getString(R.string.primia_curv);
                }
                if (Intrinsics.areEqual(str, companion.getWAVE_NEO())) {
                    return context.getString(R.string.wave_neo);
                }
                if (Intrinsics.areEqual(str, companion.getWAVE_MAGMA())) {
                    return context.getString(R.string.wave_magma);
                }
                if (Intrinsics.areEqual(str, companion.getENIGMA_OASIS())) {
                    return context.getString(R.string.enigma_oasis);
                }
                if (Intrinsics.areEqual(str, companion.getLUNAR_EMBRACE())) {
                    return context.getString(R.string.lunar_embrace);
                }
                if (Intrinsics.areEqual(str, companion.getWAVE_SPECTRA())) {
                    return context.getString(R.string.wave_spectra);
                }
                if (Intrinsics.areEqual(str, companion.getULTIMA_RISE())) {
                    return context.getString(R.string.ultima_rise);
                }
                if (Intrinsics.areEqual(str, companion.getWAVE_REGAL())) {
                    return context.getString(R.string.wave_regal);
                }
                if (Intrinsics.areEqual(str, companion.getWAVE_CHASE())) {
                    return context.getString(R.string.wave_chase);
                }
                if (Intrinsics.areEqual(str, companion.getWAVECALL3())) {
                    return context.getString(R.string.wave_call_3);
                }
                if (Intrinsics.areEqual(str, companion.getSTORMCALL3())) {
                    return context.getString(R.string.storm_call_3);
                }
                if (Intrinsics.areEqual(str, companion.getWAVEASTRA2())) {
                    return context.getString(R.string.wave_astra_2);
                }
                if (Intrinsics.areEqual(str, companion.getWAVESIGMA3())) {
                    return context.getString(R.string.wave_sigma_3);
                }
                if (Intrinsics.areEqual(str, companion.getENIGMAVIRTUO())) {
                    return context.getString(R.string.enigma_virtuo);
                }
                return null;
            }
            return null;
        }

        public final int getDevicePairingResultImage(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            DeviceType deviceType = BleApiManager.getInstance(context).getDeviceType();
            switch (deviceType == null ? -1 : WhenMappings.$EnumSwitchMapping$0[deviceType.ordinal()]) {
                case 1:
                    return R.drawable.touch_lunar_call_plus_connection_success;
                case 2:
                    return R.drawable.touch_lunar_connect_plus_connection_success;
                case 3:
                    return R.drawable.s2_connection_success;
                case 4:
                    return R.drawable.kh17_connection_success;
                case 5:
                    return R.drawable.wavefit_connection_success;
                case 6:
                    return R.drawable.s12_connection_success;
                case 7:
                    return R.drawable.matrix_connection_success;
                case 8:
                    return R.drawable.wave_armour_connection_success;
                case 9:
                    return R.drawable.wave_force_connection_success;
                case 10:
                case 15:
                case 19:
                default:
                    return R.drawable.ic_check_mark;
                case 11:
                    return R.drawable.cz2_connection_success;
                case 12:
                    return R.drawable.cz3_connection_success;
                case 13:
                    return R.drawable.waveprime_connection_success;
                case 14:
                    return R.drawable.waveelite_connection_succes;
                case 16:
                    return R.drawable.ca3_connection_succes;
                case 17:
                    return R.drawable.ido_connection_succes;
                case 18:
                    return R.drawable.xtendpro_connection_succes;
                case 20:
                    return R.drawable.ca5_wave_style_connection_success;
                case 21:
                    return R.drawable.ca5_wave_play_connection_success;
                case 22:
                    return R.drawable.ca5_wave_beat_connection_success;
                case 23:
                    return R.drawable.wavesmart_connection_succes;
                case 24:
                    return R.drawable.ulc2_wave_beat_plus_connection_success;
                case 25:
                    return R.drawable.ulc2_wave_style_plus_connection_success;
                case 26:
                    return R.drawable.ulc2_wave_smart_plus_connection_success;
                case 27:
                    return R.drawable.ulc2_wave_lync_connection_success;
                case 28:
                    return R.drawable.ca3_stormprocall_connection_success;
                case 29:
                    return R.drawable.ca3_cosmos_pro_connection_success;
                case 30:
                    return R.drawable.ca3_cosmos_connection_success;
                case 31:
                    return R.drawable.ido_connect_connection_success;
                case 32:
                    return R.drawable.prime_voice_connection_success;
                case 33:
                    return R.drawable.loop_call_pro_connection_success;
                case 34:
                    return R.drawable.loop_connect_pro_connection_success;
                case 35:
                    return R.drawable.touch_wavecall_connection_success;
                case 36:
                    return R.drawable.touch_wave_connect_plus_connection_success;
                case 37:
                    return R.drawable.touch_lunar_call_connection_success;
                case 38:
                    return R.drawable.touch_lunar_connect_connection_success;
                case 39:
                    return R.drawable.touch_xtend_call_plus_connection_success;
                case 40:
                    return R.drawable.touch_extend_connect_plus_connection_success;
                case 41:
                    return R.drawable.ultima_call_connection_succes;
                case 42:
                    return R.drawable.ultima_connect_connection_succes;
                case 43:
                    return R.drawable.eastapex_leap_call_connection_success;
                case 44:
                    return R.drawable.eastapex_flex_connect_connection_success;
                case 45:
                    return R.drawable.eastapex_stride_voice_connection_success;
                case 46:
                    return R.drawable.eastapex_xtend_plus_connection_success;
                case 47:
                    return R.drawable.eastapex_storm_plus_connection_success;
                case 48:
                    return R.drawable.eastapex_cosmos_plus_connection_success;
                case 49:
                    return R.drawable.eastapex_lunar_call_ace_connection_success;
                case 50:
                    return R.drawable.eastapex_lunar_connect_ace_connection_success;
                case 51:
                    return R.drawable.eastapex_primia_ace_connection_success;
                case 52:
                    return R.drawable.fitcloud_lunar_fit_connection_success;
                case 53:
                    return R.drawable.fitcloud_wave_armour_2_connection_success;
                case 54:
                    return R.drawable.fitcloud_wave_force_2_connection_success;
                case 55:
                    return R.drawable.opp1_wave_call2_connection_image;
                case 56:
                    return R.drawable.opp1_storm_call2_connection_image;
                case 57:
                    return R.drawable.opp1_wave_astra_connection_image;
                case 58:
                    return R.drawable.opp5_wav_sigma_connection_image;
                case 59:
                    return R.drawable.opp2_wave_neo_plus_connection_image;
                case 60:
                    return R.drawable.opp2_wave_active_connection_image;
                case 61:
                    return R.drawable.opp3_ultima_prism_connection_image;
                case 62:
                    return R.drawable.opp3_ultima_chronos_connection_image;
                case 63:
                    return R.drawable.opp3_wave_convex_connection_image;
                case 64:
                    return R.drawable.opp4_lunar_orb_connection_image;
                case 65:
                    return R.drawable.opp4_lunar_prime_connection_image;
                case 66:
                    return R.drawable.opp4_primia_curv_connection_image;
                case 67:
                    return R.drawable.ultima_connect_connection_succes;
                case 68:
                    return R.drawable.ultima_connect_connection_succes;
                case 69:
                    return R.drawable.prime_voice_connection_success;
                case 70:
                    return R.drawable.prime_voice_connection_success;
                case 71:
                    return R.drawable.prime_voice_connection_success;
                case 72:
                    return R.drawable.ultima_connect_connection_succes;
                case 73:
                    return R.drawable.ultima_connect_connection_succes;
                case 74:
                    return R.drawable.ultima_connect_connection_succes;
                case 75:
                    return R.drawable.touch_wave_neo_connection_success;
                case 76:
                    return R.drawable.sma_genesis_pro_connection_success;
                case 77:
                    return R.drawable.sma_elevate_pro_connection_success;
                case 78:
                    return R.drawable.sma_glory_pro_connection_success;
                case 79:
                    return R.drawable.sma_lunar_seek_connection_success;
                case 80:
                    return R.drawable.sma_ultima_vogue_connection_success;
                case 81:
                    return R.drawable.sma_lunar_comet_connection_success;
                case 82:
                    return R.drawable.sma_lunar_velocity_connection_success;
                case 83:
                    return R.drawable.touch_wave_magma_connection_success;
                case 84:
                    return R.drawable.ps1_enigma_oasis_connection_success;
                case 85:
                    return R.drawable.touch_lunar_embrace_connection_success;
                case 86:
                    return R.drawable.touch_wave_spectra_connection_success;
                case 87:
                    return R.drawable.ultima_rise_connection_success;
                case 88:
                    return R.drawable.wave_regal_connection_success;
                case 89:
                    return R.drawable.wave_chase_connection_success;
                case 90:
                    return R.drawable.opp1_wave_call2_connection_image;
                case 91:
                    return R.drawable.opp1_storm_call2_connection_image;
                case 92:
                    return R.drawable.opp1_wave_astra_connection_image;
                case 93:
                    return R.drawable.opp5_wav_sigma_connection_image;
            }
        }

        public final void getFeatureMapping(@NotNull final Context mContext) {
            Intrinsics.checkNotNullParameter(mContext, "mContext");
            final FirebaseRemoteConfig remoteConfig = RemoteConfigKt.getRemoteConfig(Firebase.INSTANCE);
            remoteConfig.fetch(10L).addOnFailureListener(new OnFailureListener() { // from class: com.coveiot.android.devicemodels.c
                @Override // com.google.android.gms.tasks.OnFailureListener
                public final void onFailure(Exception exc) {
                    DeviceUtils.Companion.d(exc);
                }
            }).addOnCompleteListener(new OnCompleteListener() { // from class: com.coveiot.android.devicemodels.b
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task) {
                    DeviceUtils.Companion.e(FirebaseRemoteConfig.this, mContext, task);
                }
            });
        }

        @Nullable
        public final String getModelNumber(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            DeviceType deviceType = BleApiManager.getInstance(context).getDeviceType();
            switch (deviceType == null ? -1 : WhenMappings.$EnumSwitchMapping$0[deviceType.ordinal()]) {
                case 1:
                    return ModelNameList.MODEL_NUMBER_LUNAR_CALL_PLUS.getValue();
                case 2:
                    return ModelNameList.MODEL_NUMBER_LUNAR_CONNECT_PLUS.getValue();
                case 3:
                    return ModelNameList.MODEL_NUMBER_MERCURY.getValue();
                case 4:
                    return ModelNameList.MODEL_NUMBER_VERTEX.getValue();
                case 5:
                    return ModelNameList.MODEL_NUMBER_WAVEFIT.getValue();
                case 6:
                    return ModelNameList.MODEL_NUMBER_PRIMIA.getValue();
                case 7:
                    return ModelNameList.MODEL_NUMBER_MATRIX.getValue();
                case 8:
                    return ModelNameList.MODEL_NUMBER_WAVE_ARMOUR.getValue();
                case 9:
                    return ModelNameList.MODEL_NUMBER_WAVE_FORCE.getValue();
                case 10:
                    return ModelNameList.MODEL_NUMBER_WANDERER.getValue();
                case 11:
                    return ModelNameList.MODEL_NUMBER_WAVEPRO.getValue();
                case 12:
                    return ModelNameList.MODEL_NUMBER_XTEND_SPORT.getValue();
                case 13:
                    return ModelNameList.MODEL_NUMBER_WAVEPRIME.getValue();
                case 14:
                    return ModelNameList.MODEL_NUMBER_WAVEELITE.getValue();
                case 15:
                    return ModelNameList.MODEL_NUMBER_CA0.getValue();
                case 16:
                    return ModelNameList.MODEL_NUMBER_STORMPRO.getValue();
                case 17:
                    return ModelNameList.MODEL_NUMBER_IDO_SELECT.getValue();
                case 18:
                    return ModelNameList.MODEL_NUMBER_XTENDPRO.getValue();
                case 19:
                    return ModelNameList.MODEL_NUMBER_WAVEACTIVE.getValue();
                case 20:
                    return ModelNameList.MODEL_NUMBER_WAVE_STYLE.getValue();
                case 21:
                    return ModelNameList.MODEL_NUMBER_WAVE_PLAY.getValue();
                case 22:
                    return ModelNameList.MODEL_NUMBER_WAVE_BEAT.getValue();
                case 23:
                    return ModelNameList.MODEL_NUMBER_ULC3_WAVE_SMART.getValue();
                case 24:
                    return ModelNameList.MODEL_NUMBER_ULC2_BEAT_PLUS.getValue();
                case 25:
                    return ModelNameList.MODEL_NUMBER_ULC2_STYLE_PLUS.getValue();
                case 26:
                    return ModelNameList.MODEL_NUMBER_ULC2_SMART_PLUS.getValue();
                case 27:
                    return ModelNameList.MODEL_NUMBER_ULC2_LYNC.getValue();
                case 28:
                    return ModelNameList.MODEL_NUMBER_STORMPRO_CALL.getValue();
                case 29:
                    return ModelNameList.MODEL_NUMBER_WAVE_COSMOSPRO.getValue();
                case 30:
                    return ModelNameList.MODEL_NUMBER_WAVE_COSMOS.getValue();
                case 31:
                    return ModelNameList.MODEL_NUMBER_IDO_CONNECT.getValue();
                case 32:
                    return ModelNameList.MODEL_NUMBER_PRIMIA_VOICE.getValue();
                case 33:
                    return ModelNameList.MODEL_NUMBER_LOOP_CALL_PRO.getValue();
                case 34:
                    return ModelNameList.MODEL_NUMBER_LOOP_CONNECT_PRO.getValue();
                case 35:
                    return ModelNameList.MODEL_NUMBER_WAVE_CALL_PLUS.getValue();
                case 36:
                    return ModelNameList.MODEL_NUMBER_WAVE_CONNECT_PLUS.getValue();
                case 37:
                    return ModelNameList.MODEL_NUMBER_LUNAR_CALL.getValue();
                case 38:
                    return ModelNameList.MODEL_NUMBER_LUNAR_CONNECT.getValue();
                case 39:
                    return ModelNameList.MODEL_NUMBER_XTEND_CALL_PLUS.getValue();
                case 40:
                    return ModelNameList.MODEL_NUMBER_STORM_CONNECT_PLUS.getValue();
                case 41:
                    return ModelNameList.MODEL_NUMBER_ULTIMA_CALL.getValue();
                case 42:
                    return ModelNameList.MODEL_NUMBER_ULTIMA_CONNECT.getValue();
                case 43:
                    return ModelNameList.MODEL_NUMBER_LEAP_CALL.getValue();
                case 44:
                    return ModelNameList.MODEL_NUMBER_FLEX_CONNECT.getValue();
                case 45:
                    return ModelNameList.MODEL_NUMBER_STRIDE_VOICE.getValue();
                case 46:
                    return ModelNameList.MODEL_NUMBER_XTEND_PLUS.getValue();
                case 47:
                    return ModelNameList.MODEL_NUMBER_STORM_PLUS.getValue();
                case 48:
                    return ModelNameList.MODEL_NUMBER_COSMOS_PLUS.getValue();
                case 49:
                    return ModelNameList.MODEL_NUMBER_LUNAR_CALL_ACE.getValue();
                case 50:
                    return ModelNameList.MODEL_NUMBER_LUNAR_CONNECT_ACE.getValue();
                case 51:
                    return ModelNameList.MODEL_NUMBER_PRIMIA_ACE.getValue();
                case 52:
                    return ModelNameList.MODEL_NUMBER_LUNAR_FIT.getValue();
                case 53:
                    return ModelNameList.MODEL_NUMBER_WAVE_ARMOUR_2.getValue();
                case 54:
                    return ModelNameList.MODEL_NUMBER_WAVE_FORCE_2.getValue();
                case 55:
                    return ModelNameList.MODEL_NUMBER_WAVE_CALL_2.getValue();
                case 56:
                    return ModelNameList.MODEL_NUMBER_STORM_CALL_2.getValue();
                case 57:
                    return ModelNameList.MODEL_NUMBER_WAVE_ASTRA.getValue();
                case 58:
                    return ModelNameList.MODEL_NUMBER_WAVE_SIGMA.getValue();
                case 59:
                    return ModelNameList.MODEL_NUMBER_WAVE_NEO_PLUS.getValue();
                case 60:
                    return ModelNameList.MODEL_NUMBER_WAVE_ACTIVE.getValue();
                case 61:
                    return ModelNameList.MODEL_NUMBER_ULTIMA_PRISM.getValue();
                case 62:
                    return ModelNameList.MODEL_NUMBER_ULTIMA_CHRONOS.getValue();
                case 63:
                    return ModelNameList.MODEL_NUMBER_WAVE_CONVEX.getValue();
                case 64:
                    return ModelNameList.MODEL_NUMBER_LUNAR_ORB.getValue();
                case 65:
                    return ModelNameList.MODEL_NUMBER_LUNAR_PRIME.getValue();
                case 66:
                    return ModelNameList.MODEL_NUMBER_PRIMIA_CURV.getValue();
                case 67:
                    return ModelNameList.MODEL_NUMBER_XTEND_PRO_2.getValue();
                case 68:
                    return ModelNameList.MODEL_NUMBER_STORM_PRO_CALL_2.getValue();
                case 69:
                    return ModelNameList.MODEL_NUMBER_LUNAR_CALL_PRO_2.getValue();
                case 70:
                    return ModelNameList.MODEL_NUMBER_LUNAR_CONNECT_PRO_2.getValue();
                case 71:
                    return ModelNameList.MODEL_NUMBER_WAVE_PRIMIA_TALK_2.getValue();
                case 72:
                    return ModelNameList.MODEL_NUMBER_ULTIMA_CALL_PRO.getValue();
                case 73:
                    return ModelNameList.MODEL_NUMBER_ULTIMA_CONNECT_PRO.getValue();
                case 74:
                    return ModelNameList.MODEL_NUMBER_WAVE_COSMOS_TALK.getValue();
                case 75:
                    return ModelNameList.MODEL_NUMBER_WAVE_NEO.getValue();
                case 76:
                    return ModelNameList.MODEL_NUMBER_WAVE_GENESIS_PRO.getValue();
                case 77:
                    return ModelNameList.MODEL_NUMBER_WAVE_ELEVATE_PRO.getValue();
                case 78:
                    return ModelNameList.MODEL_NUMBER_WAVE_GLORY_PRO.getValue();
                case 79:
                    return ModelNameList.MODEL_NUMBER_LUNAR_SEEK.getValue();
                case 80:
                    return ModelNameList.MODEL_NUMBER_ULTIMA_VOGUE.getValue();
                case 81:
                    return ModelNameList.MODEL_NUMBER_LUNAR_COMET.getValue();
                case 82:
                    return ModelNameList.MODEL_NUMBER_LUNAR_VELOCITY.getValue();
                case 83:
                    return ModelNameList.MODEL_NUMBER_WAVE_MAGMA.getValue();
                case 84:
                    return ModelNameList.MODEL_NUMBER_ENIGMA_OASIS.getValue();
                case 85:
                    return ModelNameList.MODEL_NUMBER_LUNAR_EMBRACE.getValue();
                case 86:
                    return ModelNameList.MODEL_NUMBER_WAVE_SPECTRA.getValue();
                case 87:
                    return ModelNameList.MODEL_NUMBER_ULTIMA_RISE.getValue();
                case 88:
                    return ModelNameList.MODEL_NUMBER_WAVE_REGAL.getValue();
                case 89:
                    return ModelNameList.MODEL_NUMBER_WAVE_CHASE.getValue();
                case 90:
                    return ModelNameList.MODEL_NUMBER_WAVE_CALL_3.getValue();
                case 91:
                    return ModelNameList.MODEL_NUMBER_STORM_CALL_3.getValue();
                case 92:
                    return ModelNameList.MODEL_NUMBER_WAVE_ASTRA2.getValue();
                case 93:
                    return ModelNameList.MODEL_NUMBER_WAVE_SIGMA_3.getValue();
                case 94:
                    return ModelNameList.MODEL_NUMBER_ENIGMA_VIRTUO.getValue();
                case 95:
                    return ModelNameList.MODEL_NUMBER_COVE9.getValue();
                case 96:
                    return ModelNameList.MODEL_NUMBER_BA1009V11.getValue();
                case 97:
                    return ModelNameList.MODEL_NUMBER_WA1002V11.getValue();
                case 98:
                    return ModelNameList.MODEL_NUMBER_TWTXW1.getValue();
                default:
                    return null;
            }
        }

        @Nullable
        public final String getModelNumberActual(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (SessionManager.getInstance(context).getDeviceType() != null) {
                if (SessionManager.getInstance(context).getDeviceType().equals(context.getString(R.string.cove_cz1))) {
                    return "WA7V2";
                }
                if (SessionManager.getInstance(context).getDeviceType().equals(context.getString(R.string.cove_cz3))) {
                    return "WA7V3";
                }
                if (SessionManager.getInstance(context).getDeviceType().equals(context.getString(R.string.cove_wave_prime))) {
                    return "WA7V1";
                }
                if (SessionManager.getInstance(context).getDeviceType().equals(context.getString(R.string.cove_wave_elite))) {
                    return "WA7V4";
                }
                if (SessionManager.getInstance(context).getDeviceType().equals(context.getString(R.string.cove_ca0))) {
                    return "WA12V1";
                }
                if (SessionManager.getInstance(context).getDeviceType().equals(context.getString(R.string.cove_ca3))) {
                    return "WA13V1";
                }
                if (SessionManager.getInstance(context).getDeviceType().equals(context.getString(R.string.cove_ca2))) {
                    return "WA19V1";
                }
                if (SessionManager.getInstance(context).getDeviceType().equals(context.getString(R.string.cove_ca3_bt))) {
                    return "WA13V2";
                }
                if (SessionManager.getInstance(context).getDeviceType().equals(context.getString(R.string.cy1_primia_voice))) {
                    return "WA20V3";
                }
                if (SessionManager.getInstance(context).getDeviceType().equals(context.getString(R.string.cy1_loop_call_pro))) {
                    return "WA20V1";
                }
                if (SessionManager.getInstance(context).getDeviceType().equals(context.getString(R.string.cy1_loop_connect_pro))) {
                    return "WA20V2";
                }
                if (SessionManager.getInstance(context).getDeviceType().equals(context.getString(R.string.cove_ca3_bt_stormpro_call))) {
                    return ModelNameList.MODEL_NUMBER_WA13V5.getValue();
                }
                if (SessionManager.getInstance(context).getDeviceType().equals(context.getString(R.string.cove_ca3_bt_wave_cosmsos_pro))) {
                    return ModelNameList.MODEL_NUMBER_WA13V4.getValue();
                }
                if (SessionManager.getInstance(context).getDeviceType().equals(context.getString(R.string.cove_ca3_wave_cosmos))) {
                    return ModelNameList.MODEL_NUMBER_WA13V3.getValue();
                }
                if (SessionManager.getInstance(context).getDeviceType().equals(context.getString(R.string.cove_ca5_wave_style))) {
                    return ModelNameList.MODEL_NUMBER_WA18V1.getValue();
                }
                if (SessionManager.getInstance(context).getDeviceType().equals(context.getString(R.string.cove_ca5_wave_beat))) {
                    return ModelNameList.MODEL_NUMBER_WA18V2.getValue();
                }
                if (SessionManager.getInstance(context).getDeviceType().equals(context.getString(R.string.cove_ca5_wave_play))) {
                    return ModelNameList.MODEL_NUMBER_WA18V3.getValue();
                }
                if (SessionManager.getInstance(context).getDeviceType().equals(context.getString(R.string.cove_ulc3_wave_smart))) {
                    return ModelNameList.MODEL_NUMBER_WA18V4.getValue();
                }
                if (SessionManager.getInstance(context).getDeviceType().equals(context.getString(R.string.cove_ulc2_wave_beat_plus))) {
                    return ModelNameList.MODEL_NUMBER_WA18V5.getValue();
                }
                if (SessionManager.getInstance(context).getDeviceType().equals(context.getString(R.string.cove_ulc2_wave_style_plus))) {
                    return ModelNameList.MODEL_NUMBER_WA18V6.getValue();
                }
                if (SessionManager.getInstance(context).getDeviceType().equals(context.getString(R.string.cove_ulc2_wave_smart_plus))) {
                    return ModelNameList.MODEL_NUMBER_WA18V7.getValue();
                }
                if (SessionManager.getInstance(context).getDeviceType().equals(context.getString(R.string.cove_ulc2_wave_lync))) {
                    return ModelNameList.MODEL_NUMBER_WA18V8.getValue();
                }
                if (SessionManager.getInstance(context).getDeviceType().equals(context.getString(R.string.ido_select))) {
                    return ModelNameList.MODEL_NUMBER_WA14V1.getValue();
                }
                if (SessionManager.getInstance(context).getDeviceType().equals(context.getString(R.string.matrix_device))) {
                    return ModelNameList.MODEL_NUMBER_WA8V1.getValue();
                }
                if (BleApiManager.getInstance(context).getDeviceType() == DeviceType.kh17) {
                    return ModelNameList.MODEL_NUMBER_WA5V1.getValue();
                }
                if (BleApiManager.getInstance(context).getDeviceType() == DeviceType.crpGPF5) {
                    return ModelNameList.MODEL_NUMBER_WA5V2.getValue();
                }
                if (m.equals(SessionManager.getInstance(context).getDeviceType(), context.getString(R.string.smaf2_device), false)) {
                    String lowerCase = ModelNameList.MODEL_NUMBER_WA3V1.getValue().toLowerCase();
                    Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
                    return lowerCase;
                } else if (m.equals(SessionManager.getInstance(context).getDeviceType(), context.getString(R.string.smas12_device), false)) {
                    String lowerCase2 = ModelNameList.MODEL_NUMBER_WA6V1.getValue().toLowerCase();
                    Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase()");
                    return lowerCase2;
                } else if (m.equals(SessionManager.getInstance(context).getDeviceType(), context.getString(R.string.sma_wave_genesis_pro), false)) {
                    String lowerCase3 = ModelNameList.MODEL_NUMBER_WA41V1.getValue().toLowerCase();
                    Intrinsics.checkNotNullExpressionValue(lowerCase3, "this as java.lang.String).toLowerCase()");
                    return lowerCase3;
                } else if (m.equals(SessionManager.getInstance(context).getDeviceType(), context.getString(R.string.sma_wave_elevate_pro), false)) {
                    String lowerCase4 = ModelNameList.MODEL_NUMBER_WA41V2.getValue().toLowerCase();
                    Intrinsics.checkNotNullExpressionValue(lowerCase4, "this as java.lang.String).toLowerCase()");
                    return lowerCase4;
                } else if (m.equals(SessionManager.getInstance(context).getDeviceType(), context.getString(R.string.sma_wave_glory_pro), false)) {
                    String lowerCase5 = ModelNameList.MODEL_NUMBER_WA41V3.getValue().toLowerCase();
                    Intrinsics.checkNotNullExpressionValue(lowerCase5, "this as java.lang.String).toLowerCase()");
                    return lowerCase5;
                } else if (m.equals(SessionManager.getInstance(context).getDeviceType(), context.getString(R.string.sma_ultima_vogue), false)) {
                    String lowerCase6 = ModelNameList.MODEL_NUMBER_WA42V1.getValue().toLowerCase();
                    Intrinsics.checkNotNullExpressionValue(lowerCase6, "this as java.lang.String).toLowerCase()");
                    return lowerCase6;
                } else if (m.equals(SessionManager.getInstance(context).getDeviceType(), context.getString(R.string.sma_lunar_seek), false)) {
                    String lowerCase7 = ModelNameList.MODEL_NUMBER_WA43V1.getValue().toLowerCase();
                    Intrinsics.checkNotNullExpressionValue(lowerCase7, "this as java.lang.String).toLowerCase()");
                    return lowerCase7;
                } else if (m.equals(SessionManager.getInstance(context).getDeviceType(), context.getString(R.string.sma_lunar_comet), false)) {
                    String lowerCase8 = ModelNameList.MODEL_NUMBER_WA43V2.getValue().toLowerCase();
                    Intrinsics.checkNotNullExpressionValue(lowerCase8, "this as java.lang.String).toLowerCase()");
                    return lowerCase8;
                } else if (m.equals(SessionManager.getInstance(context).getDeviceType(), context.getString(R.string.sma_lunar_velocity), false)) {
                    String lowerCase9 = ModelNameList.MODEL_NUMBER_WA43V3.getValue().toLowerCase();
                    Intrinsics.checkNotNullExpressionValue(lowerCase9, "this as java.lang.String).toLowerCase()");
                    return lowerCase9;
                } else if (BleApiManager.getInstance(context).getDeviceType() == DeviceType.jstyle1810G) {
                    return ModelNameList.MODEL_NUMBER_COVE9.getValue();
                } else {
                    if (BleApiManager.getInstance(context).getDeviceType() == DeviceType.jstyle1790) {
                        return ModelNameList.MODEL_NUMBER_BA1009V11.getValue();
                    }
                    if (BleApiManager.getInstance(context).getDeviceType() == DeviceType.jstyle1963D) {
                        return ModelNameList.MODEL_NUMBER_WA1002V11.getValue();
                    }
                    if (BleApiManager.getInstance(context).getDeviceType() == DeviceType.jstyle1963YH) {
                        return ModelNameList.MODEL_NUMBER_TWTXW1.getValue();
                    }
                    if (BleApiManager.getInstance(context).getDeviceType() == DeviceType.jstyle1860) {
                        return ModelNameList.MODEL_NUMBER_WA4V1.getValue();
                    }
                    if (m.equals(SessionManager.getInstance(context).getDeviceType(), context.getString(R.string.C11G), false)) {
                        return ModelNameList.MODEL_NUMBER_WA2V1.getValue();
                    }
                    if (SessionManager.getInstance(context).getDeviceType().equals(context.getString(R.string.ido_connect))) {
                        return ModelNameList.MODEL_NUMBER_WA15V1.getValue();
                    }
                    if (SessionManager.getInstance(context).getDeviceType().equals(context.getString(R.string.wave_call_plus))) {
                        return ModelNameList.MODEL_NUMBER_WA26V1.getValue();
                    }
                    if (SessionManager.getInstance(context).getDeviceType().equals(context.getString(R.string.wave_connect_plus))) {
                        return ModelNameList.MODEL_NUMBER_WA26V2.getValue();
                    }
                    if (SessionManager.getInstance(context).getDeviceType().equals(context.getString(R.string.lunar_call))) {
                        return ModelNameList.MODEL_NUMBER_WA23V1.getValue();
                    }
                    if (SessionManager.getInstance(context).getDeviceType().equals(context.getString(R.string.lunar_connect))) {
                        return ModelNameList.MODEL_NUMBER_WA23V2.getValue();
                    }
                    if (SessionManager.getInstance(context).getDeviceType().equals(context.getString(R.string.lunar_call_plus))) {
                        return ModelNameList.MODEL_NUMBER_WA25V1.getValue();
                    }
                    if (SessionManager.getInstance(context).getDeviceType().equals(context.getString(R.string.lunar_connect_plus))) {
                        return ModelNameList.MODEL_NUMBER_WA25V2.getValue();
                    }
                    if (SessionManager.getInstance(context).getDeviceType().equals(context.getString(R.string.xtend_call_plus))) {
                        return ModelNameList.MODEL_NUMBER_WA24V1.getValue();
                    }
                    if (SessionManager.getInstance(context).getDeviceType().equals(context.getString(R.string.storm_connect_plus))) {
                        return ModelNameList.MODEL_NUMBER_WA24V2.getValue();
                    }
                    if (SessionManager.getInstance(context).getDeviceType().equals(context.getString(R.string.ultima_call))) {
                        return ModelNameList.MODEL_NUMBER_WA18V9.getValue();
                    }
                    if (SessionManager.getInstance(context).getDeviceType().equals(context.getString(R.string.ultima_connect))) {
                        return ModelNameList.MODEL_NUMBER_WA18V10.getValue();
                    }
                    if (SessionManager.getInstance(context).getDeviceType().equals(context.getString(R.string.wave_force))) {
                        return ModelNameList.MODEL_NUMBER_WA28V1.getValue();
                    }
                    if (SessionManager.getInstance(context).getDeviceType().equals(context.getString(R.string.wave_armour))) {
                        return ModelNameList.MODEL_NUMBER_WA28V2.getValue();
                    }
                    if (SessionManager.getInstance(context).getDeviceType().equals(context.getString(R.string.leap_call))) {
                        return ModelNameList.MODEL_NUMBER_WA31V2.getValue();
                    }
                    if (SessionManager.getInstance(context).getDeviceType().equals(context.getString(R.string.flex_connect))) {
                        return ModelNameList.MODEL_NUMBER_WA31V1.getValue();
                    }
                    if (SessionManager.getInstance(context).getDeviceType().equals(context.getString(R.string.stride_voice))) {
                        return ModelNameList.MODEL_NUMBER_WA31V3.getValue();
                    }
                    if (SessionManager.getInstance(context).getDeviceType().equals(context.getString(R.string.lunar_connect_ace))) {
                        return ModelNameList.MODEL_NUMBER_WA30V2.getValue();
                    }
                    if (SessionManager.getInstance(context).getDeviceType().equals(context.getString(R.string.lunar_call_ace))) {
                        return ModelNameList.MODEL_NUMBER_WA30V1.getValue();
                    }
                    if (SessionManager.getInstance(context).getDeviceType().equals(context.getString(R.string.primia_ace))) {
                        return ModelNameList.MODEL_NUMBER_WA30V3.getValue();
                    }
                    if (SessionManager.getInstance(context).getDeviceType().equals(context.getString(R.string.xtend_plus))) {
                        return ModelNameList.MODEL_NUMBER_WA29V1.getValue();
                    }
                    if (SessionManager.getInstance(context).getDeviceType().equals(context.getString(R.string.storm_plus))) {
                        return ModelNameList.MODEL_NUMBER_WA29V2.getValue();
                    }
                    if (SessionManager.getInstance(context).getDeviceType().equals(context.getString(R.string.cosmos_plus))) {
                        return ModelNameList.MODEL_NUMBER_WA29V3.getValue();
                    }
                    if (SessionManager.getInstance(context).getDeviceType().equals(context.getString(R.string.lunar_fit))) {
                        return ModelNameList.MODEL_NUMBER_WA28V3.getValue();
                    }
                    if (SessionManager.getInstance(context).getDeviceType().equals(context.getString(R.string.wave_armour2))) {
                        return ModelNameList.MODEL_NUMBER_WA28V4.getValue();
                    }
                    if (SessionManager.getInstance(context).getDeviceType().equals(context.getString(R.string.wave_force2))) {
                        return ModelNameList.MODEL_NUMBER_WA28V5.getValue();
                    }
                    if (SessionManager.getInstance(context).getDeviceType().equals(context.getString(R.string.wave_neo_plus))) {
                        return ModelNameList.MODEL_NUMBER_WA36V1.getValue();
                    }
                    if (SessionManager.getInstance(context).getDeviceType().equals(context.getString(R.string.wave_active))) {
                        return ModelNameList.MODEL_NUMBER_WA36V2.getValue();
                    }
                    if (SessionManager.getInstance(context).getDeviceType().equals(context.getString(R.string.xtend_pro_2))) {
                        return ModelNameList.MODEL_NUMBER_WA32V1.getValue();
                    }
                    if (SessionManager.getInstance(context).getDeviceType().equals(context.getString(R.string.storm_pro_call_2))) {
                        return ModelNameList.MODEL_NUMBER_WA32V2.getValue();
                    }
                    if (SessionManager.getInstance(context).getDeviceType().equals(context.getString(R.string.lunar_call_pro_2))) {
                        return ModelNameList.MODEL_NUMBER_WA20V4.getValue();
                    }
                    if (SessionManager.getInstance(context).getDeviceType().equals(context.getString(R.string.lunar_connect_pro_2))) {
                        return ModelNameList.MODEL_NUMBER_WA20V5.getValue();
                    }
                    if (SessionManager.getInstance(context).getDeviceType().equals(context.getString(R.string.wave_primia_talk_2))) {
                        return ModelNameList.MODEL_NUMBER_WA20V6.getValue();
                    }
                    if (SessionManager.getInstance(context).getDeviceType().equals(context.getString(R.string.ultima_call_pro))) {
                        return ModelNameList.MODEL_NUMBER_WA21V1.getValue();
                    }
                    if (SessionManager.getInstance(context).getDeviceType().equals(context.getString(R.string.ultima_connect_pro))) {
                        return ModelNameList.MODEL_NUMBER_WA21V2.getValue();
                    }
                    if (SessionManager.getInstance(context).getDeviceType().equals(context.getString(R.string.wave_cosmos_talk))) {
                        return ModelNameList.MODEL_NUMBER_WA21V3.getValue();
                    }
                    if (SessionManager.getInstance(context).getDeviceType().equals(context.getString(R.string.wave_neo))) {
                        return ModelNameList.MODEL_NUMBER_WA44V1.getValue();
                    }
                    if (SessionManager.getInstance(context).getDeviceType().equals(context.getString(R.string.wave_magma))) {
                        return ModelNameList.MODEL_NUMBER_WA45V1.getValue();
                    }
                    if (SessionManager.getInstance(context).getDeviceType().equals(context.getString(R.string.enigma_oasis))) {
                        return ModelNameList.MODEL_NUMBER_WA46V1.getValue();
                    }
                    if (SessionManager.getInstance(context).getDeviceType().equals(context.getString(R.string.lunar_embrace))) {
                        return ModelNameList.MODEL_NUMBER_WA22V1.getValue();
                    }
                    if (SessionManager.getInstance(context).getDeviceType().equals(context.getString(R.string.wave_spectra))) {
                        return ModelNameList.MODEL_NUMBER_WA22V2.getValue();
                    }
                    if (SessionManager.getInstance(context).getDeviceType().equals(context.getString(R.string.ultima_rise))) {
                        return ModelNameList.MODEL_NUMBER_ULTIMA_RISE.getValue();
                    }
                }
            }
            return null;
        }

        @JvmStatic
        @NotNull
        public final String getStressRange(int i, @NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            boolean z = true;
            if (isSmaDevice(context)) {
                if (!(1 <= i && i < 26)) {
                    if (26 <= i && i < 51) {
                        return RrHrHelperKt.b;
                    }
                    if (51 <= i && i < 76) {
                        return "Moderate";
                    }
                    if (76 > i || i >= 101) {
                        z = false;
                    }
                    if (z) {
                        return RrHrHelperKt.c;
                    }
                }
                return "Relax";
            }
            Companion companion = DeviceUtils.Companion;
            if (!companion.isCADevice(context) && !companion.isCYDevice(context) && !isPS1Device(context) && !isBESDevice(context)) {
                if (1 <= i && i < 30) {
                    return "Relax";
                }
                if (30 <= i && i < 60) {
                    return RrHrHelperKt.b;
                }
                if (60 <= i && i < 80) {
                    return "Medium";
                }
                if (80 > i || i >= 100) {
                    z = false;
                }
                return z ? RrHrHelperKt.c : RrHrHelperKt.f5433a;
            }
            if (!(1 <= i && i < 26)) {
                if (26 <= i && i < 51) {
                    return "Mild";
                }
                if (51 <= i && i < 76) {
                    return "Moderate";
                }
                if (76 > i || i >= 101) {
                    z = false;
                }
                if (z) {
                    return RrHrHelperKt.c;
                }
            }
            return "Relaxed";
        }

        @NotNull
        public final HashMap<String, String> getWatchDetails(@NotNull Context context) {
            String str;
            String firmwareRevision;
            Intrinsics.checkNotNullParameter(context, "context");
            HashMap<String, String> hashMap = new HashMap<>();
            BleDeviceInfo bleDeviceInfo = (BleDeviceInfo) new Gson().fromJson(SessionManager.getInstance(context).getBleDeviceInfo(), (Class<Object>) BleDeviceInfo.class);
            if (bleDeviceInfo == null || (firmwareRevision = bleDeviceInfo.getFirmwareRevision()) == null) {
                str = null;
            } else {
                str = firmwareRevision.toLowerCase();
                Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String).toLowerCase()");
            }
            BleDeviceInfo bleDeviceInfo2 = (BleDeviceInfo) new Gson().fromJson(SessionManager.getInstance(context).getBleDeviceInfo(), (Class<Object>) BleDeviceInfo.class);
            String macAddress = bleDeviceInfo2 != null ? bleDeviceInfo2.getMacAddress() : null;
            if (SessionManager.getInstance(context).getDeviceModelBean() != null && SessionManager.getInstance(context).getDeviceModelBean().getName() != null) {
                String name = SessionManager.getInstance(context).getDeviceModelBean().getName();
                Intrinsics.checkNotNull(name);
                hashMap.put("Watch model", name);
            }
            if (str != null) {
                hashMap.put("Watch firmware", str);
            }
            if (macAddress != null) {
                hashMap.put("Watch mac", macAddress);
            }
            return hashMap;
        }

        @JvmStatic
        public final int getWatchModelImage(@NotNull DeviceRemoteConfig.DeviceModelsBean device) {
            Intrinsics.checkNotNullParameter(device, "device");
            String type = device.getType();
            DeviceConstants.Companion companion = DeviceConstants.Companion;
            if (Intrinsics.areEqual(type, companion.getMERCURY())) {
                return R.drawable.watch_sma_s2;
            }
            if (Intrinsics.areEqual(type, companion.getVERTEX())) {
                return R.drawable.watch_moyang_y20;
            }
            if (Intrinsics.areEqual(type, companion.getWAVEPRO())) {
                return R.drawable.cz2watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getWAVEFIT())) {
                return R.drawable.watch_moyang_wavefit;
            }
            if (Intrinsics.areEqual(type, companion.getPRIMIA())) {
                return R.drawable.s_12_image;
            }
            if (Intrinsics.areEqual(type, companion.getMATRIX())) {
                return R.drawable.watch_matrix;
            }
            if (Intrinsics.areEqual(type, companion.getWAVE_ARMOUR())) {
                return R.drawable.wave_armour_watch;
            }
            if (Intrinsics.areEqual(type, companion.getWAVE_FORCE())) {
                return R.drawable.watch_wave_force;
            }
            if (Intrinsics.areEqual(type, companion.getWAVEPlUS())) {
                return R.drawable.ca3_xtend_sport_watch;
            }
            if (Intrinsics.areEqual(type, companion.getWAVEPRIME())) {
                return R.drawable.waveprime_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getSTORMPRO())) {
                return R.drawable.ca3_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getIDO_SELECT())) {
                return R.drawable.ido_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getXTENDPROBT3())) {
                return R.drawable.ca3_bt_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getXTEND_SPORT())) {
                return R.drawable.ca3_xtend_sport_watch;
            }
            if (Intrinsics.areEqual(type, companion.getWAVECOSMOS())) {
                return R.drawable.ca3_cosmos_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getWAVECOSMOSPRO())) {
                return R.drawable.ca3_cosmos_pro_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getSTORMPROCALL())) {
                return R.drawable.ca3_stormpro_call_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getWAVEBEAT())) {
                return R.drawable.ca5_wave_beat_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getWAVEPLAY())) {
                return R.drawable.ca5_wave_play_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getWAVESTYLE())) {
                return R.drawable.ca5_wave_style_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getWAVEELITE())) {
                return R.drawable.cz1_wave_elite_image;
            }
            if (Intrinsics.areEqual(type, companion.getIDO_CONNECT())) {
                return R.drawable.ido_connect_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getSTYLEPLUS())) {
                return R.drawable.ulc2_wave_style_plus_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getBEATPLUS())) {
                return R.drawable.ulc2_wave_beat_plus_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getSMARTPLUS())) {
                return R.drawable.ulc2_wave_smart_plus_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getLYNC())) {
                return R.drawable.ulc2_wave_lync_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getWAVESMART())) {
                return R.drawable.ulc2_wave_smart_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getPRIMIA_VOICE())) {
                return R.drawable.cy_primiavoice_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getLOOP_CONNECT_PRO())) {
                return R.drawable.cy1_lunar_connect_pro;
            }
            if (Intrinsics.areEqual(type, companion.getLOOP_CALL_PRO())) {
                return R.drawable.cy_1_primiavoice;
            }
            if (Intrinsics.areEqual(type, companion.getWAVE_CALL_PLUS())) {
                return R.drawable.touch_wavecall_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getWAVE_CONNECT_PLUS())) {
                return R.drawable.touch_wave_connect_plus_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getLUNAR_CALL())) {
                return R.drawable.touch_lunar_call_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getLUNAR_CONNECT())) {
                return R.drawable.touch_lunar_connect_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getLUNAR_CALL_PLUS())) {
                return R.drawable.lunar_call_plus_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getLUNAR_CONNECT_PLUS())) {
                return R.drawable.lunar_connect_plus_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getXTEND_CALL_PLUS())) {
                return R.drawable.xtend_call_plus_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getSTORM_CONNECT_PLUS())) {
                return R.drawable.storm_connect_plus_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getULTIMA_CALL())) {
                return R.drawable.ulc5_ultima_call_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getULTIMA_CONNECT())) {
                return R.drawable.ulc5_ultima_connect_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getLEAP_CALL())) {
                return R.drawable.eastapex_leap_call_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getFLEX_CONNECT())) {
                return R.drawable.eastapex_flex_connect_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getSTRIDE_VOICE())) {
                return R.drawable.eastapex_stride_voice_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getLUNAR_CONNECT_ACE())) {
                return R.drawable.eastapex_lunar_connect_ace_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getLUNAR_CALL_ACE())) {
                return R.drawable.eastapex_lunar_call_ace_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getPRIMIA_ACE())) {
                return R.drawable.eastapex_primia_ace_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getXTEND_PLUS())) {
                return R.drawable.eastapex_xtend_plus_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getSTORM_PLUS())) {
                return R.drawable.eastapex_storm_plus_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getCOSMOS_PLUS())) {
                return R.drawable.eastapex_cosmos_plus_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getLUNAR_FIT())) {
                return R.drawable.fitcloud_lunar_fit_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getWAVE_ARMOUR_2())) {
                return R.drawable.fitcloud_wave_armour_2_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getWAVE_FORCE_2())) {
                return R.drawable.fitcloud_wave_force_2_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getWAVECALL2())) {
                return R.drawable.opp1_wave_call2_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getSTORMCALL2())) {
                return R.drawable.opp1_storm_call2_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getWAVEASTRA())) {
                return R.drawable.opp1_wave_astra_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getWAVESIGMA())) {
                return R.drawable.opp5_wav_sigma_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getWAVENEOPLUS())) {
                return R.drawable.opp2_wave_neo_plus_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getWAVEACTIVE())) {
                return R.drawable.opp2_wave_active_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getULTIMAPRISM())) {
                return R.drawable.opp3_ultima_prism_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getULTIMACHRONOS())) {
                return R.drawable.opp3_ultima_chronos_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getWAVECONVEX())) {
                return R.drawable.opp3_wave_convex_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getLUNARORB())) {
                return R.drawable.opp4_lunar_orb_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getLUNARPRIME())) {
                return R.drawable.opp4_lunar_prime_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getPRIMIACURV())) {
                return R.drawable.opp4_primia_curv_watch_image;
            }
            if (!Intrinsics.areEqual(type, companion.getXTENDPRO2()) && !Intrinsics.areEqual(type, companion.getSTORMPROCALL2())) {
                if (!Intrinsics.areEqual(type, companion.getLUNARCALLPRO2()) && !Intrinsics.areEqual(type, companion.getLUNARCONNECTPRO2()) && !Intrinsics.areEqual(type, companion.getWAVEPRIMIATALK2())) {
                    if (!Intrinsics.areEqual(type, companion.getULTIMACALLPRO()) && !Intrinsics.areEqual(type, companion.getULTIMACONNECTPRO()) && !Intrinsics.areEqual(type, companion.getWAVECOSMOSTALK())) {
                        return Intrinsics.areEqual(type, companion.getWAVE_NEO()) ? R.drawable.touch_wave_neo_watch_image : Intrinsics.areEqual(type, companion.getWAVEGENESISPRO()) ? R.drawable.sma_genesis_pro_watch_image : Intrinsics.areEqual(type, companion.getWAVEELEVATEPRO()) ? R.drawable.sma_elevate_pro_watch_image : Intrinsics.areEqual(type, companion.getWAVEGLORYPRO()) ? R.drawable.sma_glory_pro_watch_image : Intrinsics.areEqual(type, companion.getULTIMAVOGUE()) ? R.drawable.sma_ultima_vogue_watch_image : Intrinsics.areEqual(type, companion.getLUNARSEEK()) ? R.drawable.sma_lunar_seek_watch_image : Intrinsics.areEqual(type, companion.getLUNARCOMET()) ? R.drawable.sma_lunar_comet_watch_image : Intrinsics.areEqual(type, companion.getLUNARVELOCITY()) ? R.drawable.sma_lunar_velocity_watch_image : Intrinsics.areEqual(type, companion.getWAVE_MAGMA()) ? R.drawable.touch_wave_magma_watch_image : Intrinsics.areEqual(type, companion.getLUNAR_EMBRACE()) ? R.drawable.touch_lunar_embrace_watch_image : Intrinsics.areEqual(type, companion.getWAVE_SPECTRA()) ? R.drawable.touch_wave_spectra_watch_image : Intrinsics.areEqual(type, companion.getULTIMA_RISE()) ? R.drawable.ultima_rise_watch_image : Intrinsics.areEqual(type, companion.getENIGMA_OASIS()) ? R.drawable.ps1_enigma_oasis_watch_image : Intrinsics.areEqual(type, companion.getWAVE_REGAL()) ? R.drawable.wave_regal_watch_image : Intrinsics.areEqual(type, companion.getWAVE_CHASE()) ? R.drawable.wave_chase_watch_image : Intrinsics.areEqual(type, companion.getSTORMCALL3()) ? R.drawable.opp1_storm_call2_watch_image : Intrinsics.areEqual(type, companion.getWAVECALL3()) ? R.drawable.opp1_wave_call2_watch_image : Intrinsics.areEqual(type, companion.getWAVEASTRA2()) ? R.drawable.opp1_wave_astra_watch_image : Intrinsics.areEqual(type, companion.getWAVESIGMA3()) ? R.drawable.opp5_wav_sigma_watch_image : R.drawable.watch_matrix;
                    }
                    return R.drawable.ulc5_ultima_connect_watch_image;
                }
                return R.drawable.cy_1_primiavoice;
            }
            return R.drawable.ulc5_ultima_connect_watch_image;
        }

        public final int getWatchModelImagePref(@NotNull DeviceModelBean device) {
            Intrinsics.checkNotNullParameter(device, "device");
            String type = device.getType();
            DeviceConstants.Companion companion = DeviceConstants.Companion;
            if (Intrinsics.areEqual(type, companion.getMERCURY())) {
                return R.drawable.watch_sma_s2;
            }
            if (Intrinsics.areEqual(type, companion.getVERTEX())) {
                return R.drawable.watch_moyang_y20;
            }
            if (Intrinsics.areEqual(type, companion.getWAVEPRO())) {
                return R.drawable.cz2watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getWAVEFIT())) {
                return R.drawable.watch_moyang_wavefit;
            }
            if (Intrinsics.areEqual(type, companion.getPRIMIA())) {
                return R.drawable.s_12_image;
            }
            if (Intrinsics.areEqual(type, companion.getMATRIX())) {
                return R.drawable.watch_matrix;
            }
            if (Intrinsics.areEqual(type, companion.getWAVE_ARMOUR())) {
                return R.drawable.wave_armour_watch;
            }
            if (Intrinsics.areEqual(type, companion.getWAVE_FORCE())) {
                return R.drawable.watch_wave_force;
            }
            if (Intrinsics.areEqual(type, companion.getWAVEPlUS())) {
                return R.drawable.ca3_xtend_sport_watch;
            }
            if (Intrinsics.areEqual(type, companion.getWAVEPRIME())) {
                return R.drawable.waveprime_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getSTORMPRO())) {
                return R.drawable.ca3_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getIDO_SELECT())) {
                return R.drawable.ido_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getXTENDPROBT3())) {
                return R.drawable.ca3_bt_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getXTEND_SPORT())) {
                return R.drawable.ca3_xtend_sport_watch;
            }
            if (Intrinsics.areEqual(type, companion.getWAVECOSMOS())) {
                return R.drawable.ca3_cosmos_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getWAVECOSMOSPRO())) {
                return R.drawable.ca3_cosmos_pro_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getSTORMPROCALL())) {
                return R.drawable.ca3_stormpro_call_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getWAVEBEAT())) {
                return R.drawable.ca5_wave_beat_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getWAVEPLAY())) {
                return R.drawable.ca5_wave_play_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getWAVESTYLE())) {
                return R.drawable.ca5_wave_style_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getWAVEELITE())) {
                return R.drawable.cz1_wave_elite_image;
            }
            if (Intrinsics.areEqual(type, companion.getIDO_CONNECT())) {
                return R.drawable.ido_connect_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getSTYLEPLUS())) {
                return R.drawable.ulc2_wave_style_plus_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getBEATPLUS())) {
                return R.drawable.ulc2_wave_beat_plus_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getSMARTPLUS())) {
                return R.drawable.ulc2_wave_smart_plus_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getLYNC())) {
                return R.drawable.ulc2_wave_lync_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getWAVESMART())) {
                return R.drawable.ulc2_wave_smart_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getPRIMIA_VOICE())) {
                return R.drawable.cy_primiavoice_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getLOOP_CONNECT_PRO())) {
                return R.drawable.cy1_lunar_connect_pro;
            }
            if (Intrinsics.areEqual(type, companion.getLOOP_CALL_PRO())) {
                return R.drawable.cy_1_primiavoice;
            }
            if (Intrinsics.areEqual(type, companion.getWAVE_CALL_PLUS())) {
                return R.drawable.touch_wavecall_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getWAVE_CONNECT_PLUS())) {
                return R.drawable.touch_wave_connect_plus_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getLUNAR_CALL())) {
                return R.drawable.touch_lunar_call_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getLUNAR_CONNECT())) {
                return R.drawable.touch_lunar_connect_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getLUNAR_CALL_PLUS())) {
                return R.drawable.lunar_call_plus_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getLUNAR_CONNECT_PLUS())) {
                return R.drawable.lunar_connect_plus_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getXTEND_CALL_PLUS())) {
                return R.drawable.xtend_call_plus_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getSTORM_CONNECT_PLUS())) {
                return R.drawable.storm_connect_plus_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getULTIMA_CALL())) {
                return R.drawable.ulc5_ultima_call_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getULTIMA_CONNECT())) {
                return R.drawable.ulc5_ultima_connect_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getLEAP_CALL())) {
                return R.drawable.eastapex_leap_call_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getFLEX_CONNECT())) {
                return R.drawable.eastapex_flex_connect_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getSTRIDE_VOICE())) {
                return R.drawable.eastapex_stride_voice_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getLUNAR_CONNECT_ACE())) {
                return R.drawable.eastapex_lunar_connect_ace_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getLUNAR_CALL_ACE())) {
                return R.drawable.eastapex_lunar_call_ace_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getPRIMIA_ACE())) {
                return R.drawable.eastapex_primia_ace_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getXTEND_PLUS())) {
                return R.drawable.eastapex_xtend_plus_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getSTORM_PLUS())) {
                return R.drawable.eastapex_storm_plus_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getCOSMOS_PLUS())) {
                return R.drawable.eastapex_cosmos_plus_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getLUNAR_FIT())) {
                return R.drawable.fitcloud_lunar_fit_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getWAVE_ARMOUR_2())) {
                return R.drawable.fitcloud_wave_armour_2_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getWAVE_FORCE_2())) {
                return R.drawable.fitcloud_wave_force_2_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getWAVECALL2())) {
                return R.drawable.opp1_wave_call2_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getSTORMCALL2())) {
                return R.drawable.opp1_storm_call2_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getWAVEASTRA())) {
                return R.drawable.opp1_wave_astra_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getWAVESIGMA())) {
                return R.drawable.opp5_wav_sigma_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getWAVENEOPLUS())) {
                return R.drawable.opp2_wave_neo_plus_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getWAVEACTIVE())) {
                return R.drawable.opp2_wave_active_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getULTIMAPRISM())) {
                return R.drawable.opp3_ultima_prism_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getULTIMACHRONOS())) {
                return R.drawable.opp3_ultima_chronos_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getWAVECONVEX())) {
                return R.drawable.opp3_wave_convex_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getLUNARORB())) {
                return R.drawable.opp4_lunar_orb_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getLUNARPRIME())) {
                return R.drawable.opp4_lunar_prime_watch_image;
            }
            if (Intrinsics.areEqual(type, companion.getPRIMIACURV())) {
                return R.drawable.opp4_primia_curv_watch_image;
            }
            if (!Intrinsics.areEqual(type, companion.getXTENDPRO2()) && !Intrinsics.areEqual(type, companion.getSTORMPROCALL2())) {
                if (!Intrinsics.areEqual(type, companion.getLUNARCALLPRO2()) && !Intrinsics.areEqual(type, companion.getLUNARCONNECTPRO2()) && !Intrinsics.areEqual(type, companion.getWAVEPRIMIATALK2())) {
                    if (!Intrinsics.areEqual(type, companion.getULTIMACALLPRO()) && !Intrinsics.areEqual(type, companion.getULTIMACONNECTPRO()) && !Intrinsics.areEqual(type, companion.getWAVECOSMOSTALK())) {
                        return Intrinsics.areEqual(type, companion.getWAVE_NEO()) ? R.drawable.touch_wave_neo_watch_image : Intrinsics.areEqual(type, companion.getWAVEGENESISPRO()) ? R.drawable.sma_genesis_pro_watch_image : Intrinsics.areEqual(type, companion.getWAVEELEVATEPRO()) ? R.drawable.sma_elevate_pro_watch_image : Intrinsics.areEqual(type, companion.getWAVEGLORYPRO()) ? R.drawable.sma_glory_pro_watch_image : Intrinsics.areEqual(type, companion.getULTIMAVOGUE()) ? R.drawable.sma_ultima_vogue_watch_image : Intrinsics.areEqual(type, companion.getLUNARSEEK()) ? R.drawable.sma_lunar_seek_watch_image : Intrinsics.areEqual(type, companion.getLUNARCOMET()) ? R.drawable.sma_lunar_comet_watch_image : Intrinsics.areEqual(type, companion.getLUNARVELOCITY()) ? R.drawable.sma_lunar_velocity_watch_image : Intrinsics.areEqual(type, companion.getWAVE_MAGMA()) ? R.drawable.touch_wave_magma_watch_image : Intrinsics.areEqual(type, companion.getLUNAR_EMBRACE()) ? R.drawable.touch_lunar_embrace_watch_image : Intrinsics.areEqual(type, companion.getWAVE_SPECTRA()) ? R.drawable.touch_wave_spectra_watch_image : Intrinsics.areEqual(type, companion.getENIGMA_OASIS()) ? R.drawable.ps1_enigma_oasis_watch_image : Intrinsics.areEqual(type, companion.getULTIMA_RISE()) ? R.drawable.ultima_rise_watch_image : Intrinsics.areEqual(type, companion.getWAVE_REGAL()) ? R.drawable.wave_regal_watch_image : Intrinsics.areEqual(type, companion.getWAVE_CHASE()) ? R.drawable.wave_chase_watch_image : Intrinsics.areEqual(type, companion.getWAVECALL3()) ? R.drawable.opp1_wave_call2_watch_image : Intrinsics.areEqual(type, companion.getSTORMCALL3()) ? R.drawable.opp1_storm_call2_watch_image : Intrinsics.areEqual(type, companion.getWAVEASTRA2()) ? R.drawable.opp1_wave_astra_watch_image : Intrinsics.areEqual(type, companion.getWAVESIGMA3()) ? R.drawable.opp5_wav_sigma_watch_image : R.drawable.watch_matrix;
                    }
                    return R.drawable.ulc5_ultima_connect_watch_image;
                }
                return R.drawable.cy_1_primiavoice;
            }
            return R.drawable.ulc5_ultima_connect_watch_image;
        }

        @JvmStatic
        public final int getWatchModelImageStraight(@NotNull DeviceRemoteConfig.DeviceModelsBean device) {
            Intrinsics.checkNotNullParameter(device, "device");
            String type = device.getType();
            DeviceConstants.Companion companion = DeviceConstants.Companion;
            if (Intrinsics.areEqual(type, companion.getMERCURY())) {
                return R.drawable.watch_murcury_straight;
            }
            if (Intrinsics.areEqual(type, companion.getVERTEX())) {
                return R.drawable.watch_vertex_straight;
            }
            if (Intrinsics.areEqual(type, companion.getWAVEPRO())) {
                return R.drawable.watch_wave_pro_straight;
            }
            if (Intrinsics.areEqual(type, companion.getWAVEFIT())) {
                return R.drawable.watch_wavefit_straight;
            }
            if (Intrinsics.areEqual(type, companion.getPRIMIA())) {
                return R.drawable.watch_primia_straight;
            }
            if (Intrinsics.areEqual(type, companion.getMATRIX())) {
                return R.drawable.watch_matrix_straight;
            }
            if (Intrinsics.areEqual(type, companion.getWAVE_ARMOUR())) {
                return R.drawable.watch_wave_armour_straight;
            }
            if (Intrinsics.areEqual(type, companion.getWAVE_FORCE())) {
                return R.drawable.watch_wave_force_straight;
            }
            if (Intrinsics.areEqual(type, companion.getWAVEPlUS())) {
                return R.drawable.watch_xtend_sport_straight;
            }
            if (Intrinsics.areEqual(type, companion.getWAVEPRIME())) {
                return R.drawable.watch_wave_prime_straight;
            }
            if (Intrinsics.areEqual(type, companion.getSTORMPRO())) {
                return R.drawable.watch_stormpro_watch;
            }
            if (Intrinsics.areEqual(type, companion.getIDO_SELECT())) {
                return R.drawable.watch_wave_select_straight;
            }
            if (Intrinsics.areEqual(type, companion.getXTENDPROBT3())) {
                return R.drawable.watch_xtendpro_straight;
            }
            if (Intrinsics.areEqual(type, companion.getXTEND_SPORT())) {
                return R.drawable.watch_xtend_sport_straight;
            }
            if (Intrinsics.areEqual(type, companion.getWAVECOSMOS())) {
                return R.drawable.watch_cosmos_straight;
            }
            if (Intrinsics.areEqual(type, companion.getWAVECOSMOSPRO())) {
                return R.drawable.watch_cosmospro_straight;
            }
            if (Intrinsics.areEqual(type, companion.getSTORMPROCALL())) {
                return R.drawable.watch_smart_call_pro_straight;
            }
            if (Intrinsics.areEqual(type, companion.getWAVEBEAT())) {
                return R.drawable.watch_wave_breat_straight;
            }
            if (Intrinsics.areEqual(type, companion.getWAVEPLAY())) {
                return R.drawable.watch_wave_play_straight;
            }
            if (Intrinsics.areEqual(type, companion.getWAVESTYLE())) {
                return R.drawable.watch_wave_style_straight;
            }
            if (Intrinsics.areEqual(type, companion.getWAVEELITE())) {
                return R.drawable.watch_wave_elite;
            }
            if (Intrinsics.areEqual(type, companion.getIDO_CONNECT())) {
                return R.drawable.watch_wave_connect_straight;
            }
            if (Intrinsics.areEqual(type, companion.getSTYLEPLUS())) {
                return R.drawable.watch_wave_style_call_straight;
            }
            if (Intrinsics.areEqual(type, companion.getBEATPLUS())) {
                return R.drawable.watch_wave_beat_call_straight;
            }
            if (Intrinsics.areEqual(type, companion.getSMARTPLUS())) {
                return R.drawable.watch_wave_smart_call;
            }
            if (Intrinsics.areEqual(type, companion.getLYNC())) {
                return R.drawable.watch_wave_lynk_voice_straight;
            }
            if (Intrinsics.areEqual(type, companion.getWAVESMART())) {
                return R.drawable.watch_wave_smart_straight;
            }
            if (Intrinsics.areEqual(type, companion.getPRIMIA_VOICE())) {
                return R.drawable.watch_wave_prime_talk_straight;
            }
            if (Intrinsics.areEqual(type, companion.getLOOP_CONNECT_PRO())) {
                return R.drawable.watch_lunar_connect_pro_straight;
            }
            if (Intrinsics.areEqual(type, companion.getLOOP_CALL_PRO())) {
                return R.drawable.watch_lunar_call_pro_straight;
            }
            if (Intrinsics.areEqual(type, companion.getWAVE_CALL_PLUS())) {
                return R.drawable.watch_wave_call_plus_straight;
            }
            if (Intrinsics.areEqual(type, companion.getWAVE_CONNECT_PLUS())) {
                return R.drawable.watch_wave_connect_plus_straight;
            }
            if (Intrinsics.areEqual(type, companion.getLUNAR_CALL())) {
                return R.drawable.watch_lunar_call_straight;
            }
            if (Intrinsics.areEqual(type, companion.getLUNAR_CONNECT())) {
                return R.drawable.watch_lunar_connect_straight;
            }
            if (Intrinsics.areEqual(type, companion.getLUNAR_CALL_PLUS())) {
                return R.drawable.watch_lunar_call_plus_straight;
            }
            if (Intrinsics.areEqual(type, companion.getLUNAR_CONNECT_PLUS())) {
                return R.drawable.watch_lunar_connect_plus_straight;
            }
            if (Intrinsics.areEqual(type, companion.getXTEND_CALL_PLUS())) {
                return R.drawable.watch_xtend_call_plus_straight;
            }
            if (Intrinsics.areEqual(type, companion.getSTORM_CONNECT_PLUS())) {
                return R.drawable.watch_storm_connect_plus_straight;
            }
            if (Intrinsics.areEqual(type, companion.getULTIMA_CALL())) {
                return R.drawable.watch_ultima_call_straight;
            }
            if (Intrinsics.areEqual(type, companion.getULTIMA_CONNECT())) {
                return R.drawable.watch_ultima_connect_straight;
            }
            if (Intrinsics.areEqual(type, companion.getLEAP_CALL())) {
                return R.drawable.watch_eastapex_leap_call_straight;
            }
            if (Intrinsics.areEqual(type, companion.getFLEX_CONNECT())) {
                return R.drawable.watch_eastapex_flex_connect_straight;
            }
            if (Intrinsics.areEqual(type, companion.getSTRIDE_VOICE())) {
                return R.drawable.watch_eastapex_stride_voice_straight;
            }
            if (!Intrinsics.areEqual(type, companion.getLUNAR_CONNECT_ACE()) && !Intrinsics.areEqual(type, companion.getLUNAR_CALL_ACE())) {
                if (Intrinsics.areEqual(type, companion.getPRIMIA_ACE())) {
                    return R.drawable.watch_eastapex_primia_ace_straight;
                }
                if (Intrinsics.areEqual(type, companion.getXTEND_PLUS())) {
                    return R.drawable.watch_eastapex_xtend_plus_straight;
                }
                if (Intrinsics.areEqual(type, companion.getSTORM_PLUS())) {
                    return R.drawable.watch_eastapex_storm_plus_straight;
                }
                if (Intrinsics.areEqual(type, companion.getCOSMOS_PLUS())) {
                    return R.drawable.watch_eastapex_cosmos_plus_straight;
                }
                if (Intrinsics.areEqual(type, companion.getLUNAR_FIT())) {
                    return R.drawable.watch_lunar_fit_straight;
                }
                if (Intrinsics.areEqual(type, companion.getWAVE_ARMOUR_2())) {
                    return R.drawable.watch_wave_armour_2_straight;
                }
                if (Intrinsics.areEqual(type, companion.getWAVE_FORCE_2())) {
                    return R.drawable.watch_wave_force_2_straight;
                }
                if (Intrinsics.areEqual(type, companion.getWAVECALL2())) {
                    return R.drawable.opp1_wave_call2_straight_image;
                }
                if (Intrinsics.areEqual(type, companion.getSTORMCALL2())) {
                    return R.drawable.opp1_storm_call2_straight_image;
                }
                if (Intrinsics.areEqual(type, companion.getWAVEASTRA())) {
                    return R.drawable.opp1_wave_astra_straight_image;
                }
                if (Intrinsics.areEqual(type, companion.getWAVESIGMA())) {
                    return R.drawable.opp5_wav_sigma_straight_image;
                }
                if (Intrinsics.areEqual(type, companion.getWAVENEOPLUS())) {
                    return R.drawable.opp2_wave_neo_plus_straight_image;
                }
                if (Intrinsics.areEqual(type, companion.getWAVEACTIVE())) {
                    return R.drawable.opp2wave_active_straight_image;
                }
                if (Intrinsics.areEqual(type, companion.getULTIMAPRISM())) {
                    return R.drawable.opp3_ultima_prism_straight_image;
                }
                if (Intrinsics.areEqual(type, companion.getULTIMACHRONOS())) {
                    return R.drawable.opp3_ultima_chronos_straight_image;
                }
                if (Intrinsics.areEqual(type, companion.getWAVECONVEX())) {
                    return R.drawable.opp3_wave_convex_straight_image;
                }
                if (Intrinsics.areEqual(type, companion.getLUNARORB())) {
                    return R.drawable.opp4_lunar_orb_straight_image;
                }
                if (Intrinsics.areEqual(type, companion.getLUNARPRIME())) {
                    return R.drawable.opp4_lunar_prime_straight_image;
                }
                if (Intrinsics.areEqual(type, companion.getPRIMIACURV())) {
                    return R.drawable.opp4_primia_curv_straight_image;
                }
                if (!Intrinsics.areEqual(type, companion.getXTENDPRO2()) && !Intrinsics.areEqual(type, companion.getSTORMPROCALL2())) {
                    if (!Intrinsics.areEqual(type, companion.getLUNARCALLPRO2()) && !Intrinsics.areEqual(type, companion.getLUNARCONNECTPRO2()) && !Intrinsics.areEqual(type, companion.getWAVEPRIMIATALK2())) {
                        if (!Intrinsics.areEqual(type, companion.getULTIMACALLPRO()) && !Intrinsics.areEqual(type, companion.getULTIMACONNECTPRO()) && !Intrinsics.areEqual(type, companion.getWAVECOSMOSTALK())) {
                            return Intrinsics.areEqual(type, companion.getWAVE_NEO()) ? R.drawable.touch_wave_neo_straight : Intrinsics.areEqual(type, companion.getWAVEGENESISPRO()) ? R.drawable.watch_sma_genesis_pro_straight : Intrinsics.areEqual(type, companion.getWAVEELEVATEPRO()) ? R.drawable.watch_sma_elevate_pro_straight : Intrinsics.areEqual(type, companion.getWAVEGLORYPRO()) ? R.drawable.watch_sma_glory_pro_straight : Intrinsics.areEqual(type, companion.getULTIMAVOGUE()) ? R.drawable.watch_sma_ultima_vogue_straight : Intrinsics.areEqual(type, companion.getLUNARSEEK()) ? R.drawable.watch_sma_lunar_seek_straight : Intrinsics.areEqual(type, companion.getLUNARCOMET()) ? R.drawable.watch_sma_lunar_comet_straight : Intrinsics.areEqual(type, companion.getLUNARVELOCITY()) ? R.drawable.watch_sma_lunar_velocity_straight : Intrinsics.areEqual(type, companion.getWAVE_MAGMA()) ? R.drawable.touch_wave_magma_straight : Intrinsics.areEqual(type, companion.getLUNAR_EMBRACE()) ? R.drawable.touch_lunar_embrace_straight : Intrinsics.areEqual(type, companion.getWAVE_SPECTRA()) ? R.drawable.touch_wave_spectra_straight : Intrinsics.areEqual(type, companion.getENIGMA_OASIS()) ? R.drawable.ps1_enigma_oasis_straight : Intrinsics.areEqual(type, companion.getULTIMA_RISE()) ? R.drawable.ultima_rise_straight : Intrinsics.areEqual(type, companion.getWAVE_REGAL()) ? R.drawable.wave_regal_straight : Intrinsics.areEqual(type, companion.getWAVE_CHASE()) ? R.drawable.wave_chase_straight : Intrinsics.areEqual(type, companion.getWAVECALL3()) ? R.drawable.opp1_wave_call2_straight_image : Intrinsics.areEqual(type, companion.getSTORMCALL3()) ? R.drawable.opp1_storm_call2_straight_image : Intrinsics.areEqual(type, companion.getWAVEASTRA2()) ? R.drawable.opp1_wave_astra_straight_image : Intrinsics.areEqual(type, companion.getWAVESIGMA3()) ? R.drawable.opp5_wav_sigma_straight_image : R.drawable.watch_matrix;
                        }
                        return R.drawable.watch_ultima_connect_straight;
                    }
                    return R.drawable.watch_wave_prime_talk_straight;
                }
                return R.drawable.watch_ultima_connect_straight;
            }
            return R.drawable.watch_eastapex_lunar_call_connect_ace_straight;
        }

        @JvmStatic
        @NotNull
        public final String getWatchName(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            DeviceModelBean deviceModelBean = SessionManager.getInstance(context).getDeviceModelBean();
            if (deviceModelBean == null || deviceModelBean.getName() == null) {
                return "Not Available";
            }
            String name = deviceModelBean.getName();
            Intrinsics.checkNotNullExpressionValue(name, "deviceBean.name");
            return name;
        }

        @JvmStatic
        public final boolean isBESDevice(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.enigma_virtuo), false);
        }

        @JvmStatic
        public final boolean isCADevice(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ca0), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ca3), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ca3_bt), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ca2), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ca5_wave_style), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ca5_wave_play), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ca5_wave_beat), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ca3_wave_cosmos), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ca3_bt_wave_cosmsos_pro), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ca3_bt_stormpro_call), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ulc3_wave_smart), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ulc2_wave_beat_plus), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ulc2_wave_style_plus), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ulc2_wave_smart_plus), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ulc2_wave_lync), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.ultima_call), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.ultima_connect), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_call_2), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.storm_call_2), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_astra), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_sigma), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_neo_plus), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_active), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.ultima_prism), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.ultima_chronos), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_convex), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.lunar_orb), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.lunar_prime), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.primia_curv), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.xtend_pro_2), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.storm_pro_call_2), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.ultima_rise), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_regal), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_chase), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.storm_call_3), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_call_3), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_astra_2), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_sigma_3), false);
        }

        @JvmStatic
        public final boolean isCYDevice(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cy1_primia_voice), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cy1_loop_call_pro), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cy1_loop_connect_pro), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.lunar_call_pro_2), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.lunar_connect_pro_2), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_primia_talk_2), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.ultima_call_pro), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.ultima_connect_pro), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_cosmos_talk), false);
        }

        @JvmStatic
        public final boolean isCZDevice(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_cz1), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_cz3), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_wave_prime), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_wave_elite), false);
        }

        @JvmStatic
        public final boolean isCricketNotificationSupported(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            Companion companion = DeviceUtils.Companion;
            return companion.isCZDevice(context) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ca3), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ca3_bt), false) || companion.isCYDevice(context) || companion.isPS1Device(context) || isBESDevice(context) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ca2), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ca3_wave_cosmos), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ca3_bt_wave_cosmsos_pro), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ca3_bt_stormpro_call), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ulc2_wave_beat_plus), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ulc2_wave_style_plus), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ulc2_wave_smart_plus), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ulc2_wave_lync), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.ultima_call), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.ultima_connect), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_call_2), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.storm_call_2), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_astra), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_sigma), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_neo_plus), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_active), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.ultima_prism), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.ultima_chronos), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_convex), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.lunar_orb), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.lunar_prime), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.primia_curv), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.xtend_pro_2), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.storm_pro_call_2), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.lunar_call_pro_2), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.lunar_connect_pro_2), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_primia_talk_2), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.ultima_call_pro), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.ultima_connect_pro), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_cosmos_talk), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.ultima_rise), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_regal), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_chase), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_call_3), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.storm_call_3), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_astra_2), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_sigma_3), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.enigma_virtuo), false);
        }

        public final boolean isCurrentFirmwareHasIssueWithBatteryPercentage(@NotNull Context context) {
            String str;
            String firmwareRevision;
            Intrinsics.checkNotNullParameter(context, "context");
            BleDeviceInfo bleDeviceInfo = (BleDeviceInfo) new Gson().fromJson(SessionManager.getInstance(context).getBleDeviceInfo(), (Class<Object>) BleDeviceInfo.class);
            if (bleDeviceInfo == null || (firmwareRevision = bleDeviceInfo.getFirmwareRevision()) == null) {
                str = null;
            } else {
                str = firmwareRevision.toLowerCase();
                Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String).toLowerCase()");
            }
            if (!isOPP1Device(context)) {
                if (isOPP5Device(context)) {
                    return Intrinsics.areEqual(str, "v0.00.16");
                }
                return false;
            } else if (str != null) {
                int hashCode = str.hashCode();
                if (hashCode != 506438816) {
                    if (hashCode != 506438840) {
                        if (hashCode != 506438842 || !str.equals("v0.00.44")) {
                            return false;
                        }
                    } else if (!str.equals("v0.00.42")) {
                        return false;
                    }
                } else if (!str.equals("v0.00.39")) {
                    return false;
                }
                return true;
            } else {
                return false;
            }
        }

        @JvmStatic
        public final boolean isEastApexDevice(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.leap_call), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.flex_connect), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.stride_voice), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.lunar_connect_ace), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.lunar_call_ace), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.primia_ace), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.xtend_plus), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.storm_plus), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cosmos_plus), false);
        }

        @JvmStatic
        public final boolean isIDODevice(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.ido_select), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.ido_connect), false);
        }

        public final boolean isJeiLiChipSet(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            FeatureMapping featureMappingConfig = UserDataManager.getInstance(context).getFeatureMappingConfig();
            List<String> jeiliChipModels = featureMappingConfig != null ? featureMappingConfig.getJeiliChipModels() : null;
            BleDeviceInfo bleDeviceInfo = (BleDeviceInfo) new Gson().fromJson(SessionManager.getInstance(context).getBleDeviceInfo(), (Class<Object>) BleDeviceInfo.class);
            if (jeiliChipModels != null) {
                return jeiliChipModels.contains(bleDeviceInfo.getmModelNumber());
            }
            return false;
        }

        @JvmStatic
        public final boolean isJstyleDevice(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.j1790_device), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.j1810g_device), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.j1963yh_device), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.j1963d_device), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.j1860_device), false);
        }

        @JvmStatic
        public final boolean isKaHaDevice(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return isCADevice(context) || isCZDevice(context) || isCYDevice(context) || isPS1Device(context);
        }

        @JvmStatic
        public final boolean isKaHaDeviceWithRem(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (!m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_cz1), false) && !m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_cz3), false) && !m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_wave_prime), false) && !m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_wave_elite), false) && !m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ca3), false) && !m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ca3_bt), false) && !isCYDevice(context)) {
                Companion companion = DeviceUtils.Companion;
                if (!companion.isPS1Device(context) && !companion.isBESDevice(context) && !m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ca2), false) && !m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ca5_wave_style), false) && !m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ca5_wave_play), false) && !m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ca5_wave_beat), false) && !m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ca3_wave_cosmos), false) && !m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ca3_bt_wave_cosmsos_pro), false) && !m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ca3_bt_stormpro_call), false) && !m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ulc3_wave_smart), false) && !m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ulc2_wave_beat_plus), false) && !m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ulc2_wave_style_plus), false) && !m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ulc2_wave_smart_plus), false) && !m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ulc2_wave_lync), false) && !m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.ultima_call), false) && !m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.ultima_connect), false) && !m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.ultima_rise), false) && !isCYDevice(context) && !isPS1Device(context) && !isOPP1Device(context) && !isOPP2Device(context) && !isOPP3Device(context) && !isOPP4Device(context) && !isOPP5Device(context) && !isBESDevice(context)) {
                    return false;
                }
            }
            return true;
        }

        @JvmStatic
        public final boolean isMatrixDevice(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.matrix_device), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_force), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_armour), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.lunar_fit), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_armour2), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_force2), false);
        }

        public final boolean isMigratedDevice(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.flex_connect), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.leap_call), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.stride_voice), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_neo), false);
        }

        @JvmStatic
        public final boolean isMoyangDevice(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.moyangy20_device), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.moyangygpf5_device), false);
        }

        public final boolean isOPP1Device(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_call_2), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_astra), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.storm_call_2), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.ultima_rise), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.storm_call_3), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_call_3), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_astra_2), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.enigma_virtuo), false);
        }

        public final boolean isOPP2Device(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_neo_plus), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_active), false);
        }

        public final boolean isOPP3Device(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.ultima_prism), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.ultima_chronos), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_convex), false);
        }

        public final boolean isOPP4Device(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.lunar_orb), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.primia_curv), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.lunar_prime), false);
        }

        public final boolean isOPP5Device(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_sigma), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_chase), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_regal), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_sigma_3), false);
        }

        public final boolean isOPPDevice(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return isOPP1Device(context) || isOPP2Device(context) || isOPP3Device(context) || isOPP4Device(context) || isOPP5Device(context);
        }

        @JvmStatic
        public final boolean isPS1Device(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.enigma_oasis), false);
        }

        @JvmStatic
        public final boolean isPrimiaDevice(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.smas12_device), false);
        }

        public final boolean isRoundWatch(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (BleApiManager.getInstance(context) == null || BleApiManager.getInstance(context).getDeviceType() == null) {
                return false;
            }
            DeviceType deviceType = BleApiManager.getInstance(context).getDeviceType();
            int i = deviceType == null ? -1 : WhenMappings.$EnumSwitchMapping$0[deviceType.ordinal()];
            if (i != 1 && i != 2 && i != 6 && i != 79 && i != 94 && i != 37 && i != 38 && i != 81 && i != 82 && i != 84 && i != 85) {
                switch (i) {
                    case 32:
                    case 33:
                    case 34:
                        break;
                    default:
                        switch (i) {
                            case 49:
                            case 50:
                            case 51:
                            case 52:
                                break;
                            default:
                                switch (i) {
                                    case 64:
                                    case 65:
                                    case 66:
                                        break;
                                    default:
                                        switch (i) {
                                            case 69:
                                            case 70:
                                            case 71:
                                                break;
                                            default:
                                                return false;
                                        }
                                }
                        }
                }
            }
            return true;
        }

        public final boolean isRuggedDevice(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_force), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_armour), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.lunar_fit), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_armour2), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_force2), false);
        }

        @JvmStatic
        public final boolean isSmaDevice(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.smaf2_device), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.smas12_device), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.sma_wave_genesis_pro), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.sma_wave_elevate_pro), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.sma_wave_glory_pro), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.sma_ultima_vogue), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.sma_lunar_seek), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.sma_lunar_comet), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.sma_lunar_velocity), false);
        }

        @JvmStatic
        public final boolean isSmaJieieDevice(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.sma_wave_genesis_pro), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.sma_wave_elevate_pro), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.sma_wave_glory_pro), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.sma_ultima_vogue), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.sma_lunar_seek), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.sma_lunar_comet), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.sma_lunar_velocity), false);
        }

        @JvmStatic
        public final boolean isSmaRoundJieieDevice(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.sma_lunar_seek), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.sma_lunar_comet), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.sma_lunar_velocity), false);
        }

        @JvmStatic
        public final boolean isTouchELXDevice(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_call_plus), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_connect_plus), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.lunar_call), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.lunar_connect), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.lunar_call_plus), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.lunar_connect_plus), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.xtend_call_plus), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.storm_connect_plus), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_neo), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_magma), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.lunar_embrace), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_spectra), false);
        }

        public final boolean isTouchLunarPlusDevice(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            DeviceType deviceType = BleApiManager.getInstance(context).getDeviceType();
            int i = deviceType == null ? -1 : WhenMappings.$EnumSwitchMapping$0[deviceType.ordinal()];
            return i == 1 || i == 2;
        }

        @JvmStatic
        public final boolean isUltimaCallOrUltimaConnectDeviceForOTA(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.ultima_call), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.ultima_connect), false);
        }

        public final void logAnalyticsEvent(@NotNull String eventName, @Nullable HashMap<String, Object> hashMap) {
            Intrinsics.checkNotNullParameter(eventName, "eventName");
            AnalyticsLog analyticsLog = new AnalyticsLog();
            analyticsLog.setEventName(eventName);
            if (!(hashMap == null || hashMap.isEmpty())) {
                analyticsLog.setHashMapData(hashMap);
            }
            CoveAnalyticsManager.Companion.getInstance().logAnalyticEvent(analyticsLog);
        }

        public final boolean watchSupportsAccurateData(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            Companion companion = DeviceUtils.Companion;
            return (companion.isCADevice(context) || companion.isCZDevice(context) || isOPPDevice(context)) ? false : true;
        }
    }

    @JvmStatic
    @NotNull
    public static final String getStressRange(int i, @NotNull Context context) {
        return Companion.getStressRange(i, context);
    }

    @JvmStatic
    public static final int getWatchModelImage(@NotNull DeviceRemoteConfig.DeviceModelsBean deviceModelsBean) {
        return Companion.getWatchModelImage(deviceModelsBean);
    }

    @JvmStatic
    public static final int getWatchModelImageStraight(@NotNull DeviceRemoteConfig.DeviceModelsBean deviceModelsBean) {
        return Companion.getWatchModelImageStraight(deviceModelsBean);
    }

    @JvmStatic
    @NotNull
    public static final String getWatchName(@NotNull Context context) {
        return Companion.getWatchName(context);
    }

    @JvmStatic
    public static final boolean isBESDevice(@NotNull Context context) {
        return Companion.isBESDevice(context);
    }

    @JvmStatic
    public static final boolean isCADevice(@NotNull Context context) {
        return Companion.isCADevice(context);
    }

    @JvmStatic
    public static final boolean isCYDevice(@NotNull Context context) {
        return Companion.isCYDevice(context);
    }

    @JvmStatic
    public static final boolean isCZDevice(@NotNull Context context) {
        return Companion.isCZDevice(context);
    }

    @JvmStatic
    public static final boolean isCricketNotificationSupported(@NotNull Context context) {
        return Companion.isCricketNotificationSupported(context);
    }

    @JvmStatic
    public static final boolean isEastApexDevice(@NotNull Context context) {
        return Companion.isEastApexDevice(context);
    }

    @JvmStatic
    public static final boolean isIDODevice(@NotNull Context context) {
        return Companion.isIDODevice(context);
    }

    @JvmStatic
    public static final boolean isJstyleDevice(@NotNull Context context) {
        return Companion.isJstyleDevice(context);
    }

    @JvmStatic
    public static final boolean isKaHaDevice(@NotNull Context context) {
        return Companion.isKaHaDevice(context);
    }

    @JvmStatic
    public static final boolean isKaHaDeviceWithRem(@NotNull Context context) {
        return Companion.isKaHaDeviceWithRem(context);
    }

    @JvmStatic
    public static final boolean isMatrixDevice(@NotNull Context context) {
        return Companion.isMatrixDevice(context);
    }

    @JvmStatic
    public static final boolean isMoyangDevice(@NotNull Context context) {
        return Companion.isMoyangDevice(context);
    }

    @JvmStatic
    public static final boolean isPS1Device(@NotNull Context context) {
        return Companion.isPS1Device(context);
    }

    @JvmStatic
    public static final boolean isPrimiaDevice(@NotNull Context context) {
        return Companion.isPrimiaDevice(context);
    }

    @JvmStatic
    public static final boolean isSmaDevice(@NotNull Context context) {
        return Companion.isSmaDevice(context);
    }

    @JvmStatic
    public static final boolean isSmaJieieDevice(@NotNull Context context) {
        return Companion.isSmaJieieDevice(context);
    }

    @JvmStatic
    public static final boolean isSmaRoundJieieDevice(@NotNull Context context) {
        return Companion.isSmaRoundJieieDevice(context);
    }

    @JvmStatic
    public static final boolean isTouchELXDevice(@NotNull Context context) {
        return Companion.isTouchELXDevice(context);
    }

    @JvmStatic
    public static final boolean isUltimaCallOrUltimaConnectDeviceForOTA(@NotNull Context context) {
        return Companion.isUltimaCallOrUltimaConnectDeviceForOTA(context);
    }
}
