package com.coveiot.android.sportsnotification;

import android.content.Context;
import com.coveiot.android.sportsnotification.model.WatchCricketUIModel;
import com.coveiot.android.theme.utils.BoatCoinsFirebaseConfigResultListener;
import com.coveiot.android.theme.utils.ThemeConstants;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.sdk.ble.utils.BleUtils;
import com.goodix.ble.libcomx.task.ITask;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ktx.Firebase;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.ktx.RemoteConfigKt;
import com.jieli.jl_rcsp.constant.Command;
import com.realsil.sdk.dfu.DfuException;
import com.touchgui.sdk.TGEventListener;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import kotlin.io.ByteStreamsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.bouncycastle.crypto.tls.CipherSuite;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class SportsUtils {
    @NotNull
    public static final SportsUtils INSTANCE = new SportsUtils();

    /* renamed from: a  reason: collision with root package name */
    public static final int f5833a;
    public static final int b;
    public static final int c;
    public static final int d;
    public static final int e;
    public static final int f;
    public static final int g;
    public static final int gameStatusTypeFace = 0;
    public static final int h;
    public static final int i;
    public static final int imageLength = -1;
    public static final int imageRGBColor = -1;
    public static final int imageWidth = -1;
    public static final int imageXPosition = 0;
    public static final int imageYPosition = 0;
    public static final int matchFormatTypeFace = 0;
    public static final int teamANameTypeFace = 0;
    public static final int teamAScoreTypeFace = 0;
    public static final int teamBNameTypeFace = 0;
    public static final int teamBScoreTypeFace = 0;

    static {
        int convertHexTo565 = BleUtils.convertHexTo565("#ffffff");
        f5833a = convertHexTo565;
        int convertHexTo5652 = BleUtils.convertHexTo565("#ffffff");
        b = convertHexTo5652;
        int convertHexTo5653 = BleUtils.convertHexTo565("#ffffff");
        c = convertHexTo5653;
        d = BleUtils.convertHexTo565("#fbea3c");
        e = convertHexTo565;
        f = convertHexTo5652;
        g = convertHexTo5652;
        h = convertHexTo5653;
        i = convertHexTo5653;
    }

    public static final void d(BoatCoinsFirebaseConfigResultListener listener, Exception it) {
        Intrinsics.checkNotNullParameter(listener, "$listener");
        Intrinsics.checkNotNullParameter(it, "it");
        listener.onFailure(null);
    }

    public static final void e(final FirebaseRemoteConfig remoteConfig, final BoatCoinsFirebaseConfigResultListener listener, Task task) {
        Intrinsics.checkNotNullParameter(remoteConfig, "$remoteConfig");
        Intrinsics.checkNotNullParameter(listener, "$listener");
        Intrinsics.checkNotNullParameter(task, "task");
        if (task.isSuccessful()) {
            Void r3 = (Void) task.getResult();
            remoteConfig.activate().addOnCompleteListener(new OnCompleteListener() { // from class: com.coveiot.android.sportsnotification.q
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task2) {
                    SportsUtils.f(FirebaseRemoteConfig.this, listener, task2);
                }
            });
        }
    }

    public static final void f(FirebaseRemoteConfig remoteConfig, BoatCoinsFirebaseConfigResultListener listener, Task it) {
        Intrinsics.checkNotNullParameter(remoteConfig, "$remoteConfig");
        Intrinsics.checkNotNullParameter(listener, "$listener");
        Intrinsics.checkNotNullParameter(it, "it");
        listener.onResult(remoteConfig.getBoolean(ThemeConstants.BOAT_COINS_VISIBILITY.getValue()));
    }

    @JvmStatic
    public static final void isBoatCoinsEnabled(@NotNull Context context, @NotNull final BoatCoinsFirebaseConfigResultListener listener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(listener, "listener");
        try {
            final FirebaseRemoteConfig remoteConfig = RemoteConfigKt.getRemoteConfig(Firebase.INSTANCE);
            remoteConfig.fetch(10L).addOnFailureListener(new OnFailureListener() { // from class: com.coveiot.android.sportsnotification.s
                @Override // com.google.android.gms.tasks.OnFailureListener
                public final void onFailure(Exception exc) {
                    SportsUtils.d(BoatCoinsFirebaseConfigResultListener.this, exc);
                }
            }).addOnCompleteListener(new OnCompleteListener() { // from class: com.coveiot.android.sportsnotification.p
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task) {
                    SportsUtils.e(FirebaseRemoteConfig.this, listener, task);
                }
            });
        } catch (Exception unused) {
            listener.onFailure(null);
        }
    }

    @JvmStatic
    public static final boolean isCYDevice(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cy1_primia_voice), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cy1_loop_call_pro), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cy1_loop_connect_pro), false);
    }

    @Nullable
    public final String convertMonthNumberToName(int i2) {
        return (i2 <= 0 || i2 > 12) ? HexStringBuilder.DEFAULT_SEPARATOR : new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"}[i2];
    }

    @NotNull
    public final String generateDefaultSoccerConfig(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        InputStream open = context.getAssets().open("soccer_filter_config.json");
        Intrinsics.checkNotNullExpressionValue(open, "context.assets.open(\"soccer_filter_config.json\")");
        return new String(ByteStreamsKt.readBytes(open), Charsets.UTF_8);
    }

    @Nullable
    public final String getDayOfMonthSuffix(int i2) {
        if (i2 < 1 || i2 > 31) {
            return String.valueOf(i2);
        }
        if (i2 < 11 || i2 > 13) {
            int i3 = i2 % 10;
            return i3 != 1 ? i3 != 2 ? i3 != 3 ? "th" : "rd" : "nd" : "st";
        }
        return "th";
    }

    @NotNull
    public final String getGameState(int i2, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        switch (i2) {
            case 0:
                return "None";
            case 1:
                return "Starts Shortly";
            case 2:
                return "Toss";
            case 3:
                return "Play Ongoing";
            case 4:
                return "Delayed";
            case 5:
                return "Drinks Break";
            case 6:
                return "Innings Break";
            case 7:
                return "Stumps";
            case 8:
                return "Lunch Break";
            case 9:
                return "Tea Break";
            default:
                return "";
        }
    }

    public final int getGameStatusFont(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return (isOPP3Device(context) || isOPP4Device(context)) ? 30 : 24;
    }

    public final int getGameStatusLength(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return (isOPP3Device(context) || isOPP4Device(context)) ? 30 : 24;
    }

    public final int getGameStatusRGBColor(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (isPS1Device(context)) {
            return f5833a;
        }
        return d;
    }

    public final int getGameStatusWidth(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return (isOPP3Device(context) || isOPP4Device(context)) ? 30 : 32;
    }

    @NotNull
    public final String getMatchFormat(int i2) {
        switch (i2) {
            case 1:
                return "ODI";
            case 2:
                return "TEST";
            case 3:
                return "T20I";
            case 4:
                return "List A";
            case 5:
                return "First Class";
            case 6:
                return "T20 Domestic";
            case 7:
                return "Women ODI";
            case 8:
                return "Women T20";
            case 9:
                return "Youth ODI";
            case 10:
                return "Youth T20";
            case 11:
                return "Others";
            default:
                switch (i2) {
                    case 17:
                        return "T10";
                    case 18:
                        return "T100";
                    case 19:
                        return "Women T100";
                    default:
                        return "";
                }
        }
    }

    public final int getMatchFormatLWF(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return (isOPP3Device(context) || isOPP4Device(context)) ? 30 : 32;
    }

    public final int getMatchFormatRGBColor() {
        return e;
    }

    @NotNull
    public final String getMatchStatus(int i2, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return i2 != 1 ? i2 != 2 ? i2 != 3 ? i2 != 4 ? "" : "Abandoned" : "Live" : "Completed" : "Scheduled";
    }

    public final int getSportsCricketImageID(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return isPS1Device(context) ? 60002 : 997;
    }

    @NotNull
    public final Calendar getStartTime(@NotNull String dateStart) {
        Intrinsics.checkNotNullParameter(dateStart, "dateStart");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getDefault());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        calendar.setTime(simpleDateFormat.parse(dateStart));
        Intrinsics.checkNotNullExpressionValue(calendar, "calendar");
        return calendar;
    }

    public final int getTeamANameLWF(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return (isOPP3Device(context) || isOPP4Device(context)) ? 30 : 32;
    }

    public final int getTeamANameRGBColor() {
        return f;
    }

    public final int getTeamAScoreLWF(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return (isOPP3Device(context) || isOPP4Device(context)) ? 30 : 32;
    }

    public final int getTeamAScoreRGBColor() {
        return h;
    }

    public final int getTeamBNameLWF(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return (isOPP3Device(context) || isOPP4Device(context)) ? 30 : 32;
    }

    public final int getTeamBNameRGBColor() {
        return g;
    }

    public final int getTeamBScoreLWF(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return (isOPP3Device(context) || isOPP4Device(context)) ? 30 : 32;
    }

    public final int getTeamBScoreRGBColor() {
        return i;
    }

    public final long getTimeInCurrentZone(@NotNull String strDate) {
        Intrinsics.checkNotNullParameter(strDate, "strDate");
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeZone(TimeZone.getDefault());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            calendar.setTime(simpleDateFormat.parse(strDate));
            return calendar.getTimeInMillis();
        } catch (Exception unused) {
            return 0L;
        }
    }

    @NotNull
    public final String getTossDecision(int i2, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return i2 != 1 ? i2 != 2 ? "" : "Fielding" : "Batting";
    }

    @NotNull
    public final WatchCricketUIModel getWatchCricketUIPreference(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        WatchCricketUIModel watchCricketUIModel = new WatchCricketUIModel(null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 524287, null);
        if (isCADevice(context)) {
            if (Intrinsics.areEqual(SessionManager.getInstance(context).getDeviceType(), context.getString(R.string.cove_ca2))) {
                watchCricketUIModel.setBackGroundFileName("cricket_bg_ca2.bmp");
                watchCricketUIModel.setBackgroundFileWidth(320);
                watchCricketUIModel.setBackgroundFileHeight(385);
                watchCricketUIModel.setTeamANameXPosition(35);
                watchCricketUIModel.setTeamANameYPosition(233);
                watchCricketUIModel.setTeamBNameXPosition(35);
                watchCricketUIModel.setTeamBNameYPosition(DfuException.ERROR_READ_REMOTE_MAC_ADDR);
                watchCricketUIModel.setTeamAScoreXPosition(157);
                watchCricketUIModel.setTeamAScoreXPosition2(137);
                watchCricketUIModel.setTeamBScoreXPosition(157);
                watchCricketUIModel.setTeamBScoreXPosition2(137);
                watchCricketUIModel.setGameStatusXPosition(104);
                watchCricketUIModel.setGameStatusXPosition2(104);
                watchCricketUIModel.setGameStatusYPosition(com.veryfit.multi.nativeprotocol.b.m1);
                watchCricketUIModel.setTeamAScoreYPosition(233);
                watchCricketUIModel.setTeamBScoreYPosition(DfuException.ERROR_READ_REMOTE_MAC_ADDR);
                watchCricketUIModel.setMatchFormatXPosition(158);
                watchCricketUIModel.setMatchFormatXPosition2(142);
                watchCricketUIModel.setMatchFormatYPosition(96);
            } else if (isCAULCDevice(context)) {
                watchCricketUIModel.setBackGroundFileName("cricket_bg_ca3_ulc.bmp");
                watchCricketUIModel.setBackgroundFileWidth(240);
                watchCricketUIModel.setBackgroundFileHeight(DfuException.ERROR_ENTER_OTA_MODE_FAILED);
                watchCricketUIModel.setTeamANameXPosition(30);
                watchCricketUIModel.setTeamANameYPosition(130);
                watchCricketUIModel.setTeamBNameXPosition(30);
                watchCricketUIModel.setTeamBNameYPosition(165);
                watchCricketUIModel.setTeamAScoreXPosition(115);
                watchCricketUIModel.setTeamAScoreXPosition2(98);
                watchCricketUIModel.setTeamBScoreXPosition(115);
                watchCricketUIModel.setTeamBScoreXPosition2(98);
                watchCricketUIModel.setGameStatusXPosition(115);
                watchCricketUIModel.setGameStatusXPosition2(115);
                watchCricketUIModel.setGameStatusYPosition(230);
                watchCricketUIModel.setTeamAScoreYPosition(130);
                watchCricketUIModel.setTeamBScoreYPosition(165);
                watchCricketUIModel.setMatchFormatXPosition(125);
                watchCricketUIModel.setMatchFormatXPosition2(122);
                watchCricketUIModel.setMatchFormatYPosition(50);
            } else {
                watchCricketUIModel.setBackGroundFileName("cricket_bg_ca3.bmp");
                watchCricketUIModel.setBackgroundFileWidth(368);
                watchCricketUIModel.setBackgroundFileHeight(448);
                watchCricketUIModel.setTeamANameXPosition(40);
                watchCricketUIModel.setTeamANameYPosition(DfuException.ERROR_READ_DEVICE_INFO_ERROR);
                watchCricketUIModel.setTeamBNameXPosition(40);
                watchCricketUIModel.setTeamBNameYPosition(com.veryfit.multi.nativeprotocol.b.h1);
                watchCricketUIModel.setTeamAScoreXPosition(181);
                watchCricketUIModel.setTeamAScoreXPosition2(158);
                watchCricketUIModel.setTeamBScoreXPosition(181);
                watchCricketUIModel.setTeamBScoreXPosition2(158);
                watchCricketUIModel.setGameStatusXPosition(120);
                watchCricketUIModel.setGameStatusXPosition2(120);
                watchCricketUIModel.setGameStatusYPosition(386);
                watchCricketUIModel.setTeamAScoreYPosition(DfuException.ERROR_READ_DEVICE_INFO_ERROR);
                watchCricketUIModel.setTeamBScoreYPosition(com.veryfit.multi.nativeprotocol.b.h1);
                watchCricketUIModel.setMatchFormatXPosition(158);
                watchCricketUIModel.setMatchFormatXPosition2(163);
                watchCricketUIModel.setMatchFormatYPosition(112);
            }
        } else if (!isCYDevice(context) && !kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.lunar_call_pro_2), false) && !kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.lunar_connect_pro_2), false) && !kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_primia_talk_2), false)) {
            if (isOPP1Device(context)) {
                watchCricketUIModel.setBackGroundFileName("cricket_bg_opp1.bmp");
                watchCricketUIModel.setBackgroundFileWidth(240);
                watchCricketUIModel.setBackgroundFileHeight(DfuException.ERROR_DFU_ENABLE_BUFFER_CHECK_NO_RESPONSE);
                watchCricketUIModel.setTeamANameXPosition(30);
                watchCricketUIModel.setTeamANameYPosition(130);
                watchCricketUIModel.setTeamBNameXPosition(30);
                watchCricketUIModel.setTeamBNameYPosition(165);
                watchCricketUIModel.setTeamAScoreXPosition(115);
                watchCricketUIModel.setTeamAScoreXPosition2(98);
                watchCricketUIModel.setTeamBScoreXPosition(115);
                watchCricketUIModel.setTeamBScoreXPosition2(98);
                watchCricketUIModel.setGameStatusXPosition(115);
                watchCricketUIModel.setGameStatusXPosition2(115);
                watchCricketUIModel.setGameStatusYPosition(230);
                watchCricketUIModel.setTeamAScoreYPosition(130);
                watchCricketUIModel.setTeamBScoreYPosition(165);
                watchCricketUIModel.setMatchFormatXPosition(125);
                watchCricketUIModel.setMatchFormatXPosition2(122);
                watchCricketUIModel.setMatchFormatYPosition(50);
            } else if (isOPP2Device(context)) {
                watchCricketUIModel.setBackGroundFileName("cricket_bg_opp2.bmp");
                watchCricketUIModel.setBackgroundFileWidth(240);
                watchCricketUIModel.setBackgroundFileHeight(DfuException.ERROR_DFU_SPP_RWS_NOT_READY);
                watchCricketUIModel.setTeamANameXPosition(30);
                watchCricketUIModel.setTeamANameYPosition(130);
                watchCricketUIModel.setTeamBNameXPosition(30);
                watchCricketUIModel.setTeamBNameYPosition(165);
                watchCricketUIModel.setTeamAScoreXPosition(115);
                watchCricketUIModel.setTeamAScoreXPosition2(98);
                watchCricketUIModel.setTeamBScoreXPosition(115);
                watchCricketUIModel.setTeamBScoreXPosition2(98);
                watchCricketUIModel.setGameStatusXPosition(115);
                watchCricketUIModel.setGameStatusXPosition2(115);
                watchCricketUIModel.setGameStatusYPosition(230);
                watchCricketUIModel.setTeamAScoreYPosition(130);
                watchCricketUIModel.setTeamBScoreYPosition(165);
                watchCricketUIModel.setMatchFormatXPosition(125);
                watchCricketUIModel.setMatchFormatXPosition2(122);
                watchCricketUIModel.setMatchFormatYPosition(50);
            } else if (isOPP3Device(context)) {
                watchCricketUIModel.setBackGroundFileName("cricket_bg_opp3.bmp");
                watchCricketUIModel.setBackgroundFileWidth(com.veryfit.multi.nativeprotocol.b.C1);
                watchCricketUIModel.setBackgroundFileHeight(502);
                watchCricketUIModel.setTeamANameXPosition(57);
                watchCricketUIModel.setTeamANameYPosition(244);
                watchCricketUIModel.setTeamBNameXPosition(57);
                watchCricketUIModel.setTeamBNameYPosition(301);
                watchCricketUIModel.setTeamAScoreXPosition(195);
                watchCricketUIModel.setTeamAScoreXPosition2(195);
                watchCricketUIModel.setTeamBScoreXPosition(195);
                watchCricketUIModel.setTeamBScoreXPosition2(195);
                watchCricketUIModel.setGameStatusXPosition(135);
                watchCricketUIModel.setGameStatusXPosition2(135);
                watchCricketUIModel.setGameStatusYPosition(377);
                watchCricketUIModel.setTeamAScoreYPosition(244);
                watchCricketUIModel.setTeamBScoreYPosition(301);
                watchCricketUIModel.setMatchFormatXPosition(192);
                watchCricketUIModel.setMatchFormatXPosition2(192);
                watchCricketUIModel.setMatchFormatYPosition(85);
            } else if (isOPP4Device(context)) {
                watchCricketUIModel.setBackGroundFileName("cricket_bg_opp4.bmp");
                watchCricketUIModel.setBackgroundFileWidth(466);
                watchCricketUIModel.setBackgroundFileHeight(466);
                watchCricketUIModel.setTeamANameXPosition(85);
                watchCricketUIModel.setTeamANameYPosition(235);
                watchCricketUIModel.setTeamBNameXPosition(85);
                watchCricketUIModel.setTeamBNameYPosition(TGEventListener.OTA_COMPLETED);
                watchCricketUIModel.setTeamAScoreXPosition(225);
                watchCricketUIModel.setTeamAScoreXPosition2(225);
                watchCricketUIModel.setTeamBScoreXPosition(224);
                watchCricketUIModel.setTeamBScoreXPosition2(225);
                watchCricketUIModel.setGameStatusXPosition(135);
                watchCricketUIModel.setGameStatusXPosition2(135);
                watchCricketUIModel.setGameStatusYPosition(360);
                watchCricketUIModel.setTeamAScoreYPosition(235);
                watchCricketUIModel.setTeamBScoreYPosition(TGEventListener.OTA_COMPLETED);
                watchCricketUIModel.setMatchFormatXPosition(200);
                watchCricketUIModel.setMatchFormatXPosition2(200);
                watchCricketUIModel.setMatchFormatYPosition(86);
            } else if (isOPP5Device(context)) {
                watchCricketUIModel.setBackGroundFileName("cricket_bg_opp5.bmp");
                watchCricketUIModel.setBackgroundFileWidth(240);
                watchCricketUIModel.setBackgroundFileHeight(296);
                watchCricketUIModel.setTeamANameXPosition(30);
                watchCricketUIModel.setTeamANameYPosition(130);
                watchCricketUIModel.setTeamBNameXPosition(30);
                watchCricketUIModel.setTeamBNameYPosition(165);
                watchCricketUIModel.setTeamAScoreXPosition(115);
                watchCricketUIModel.setTeamAScoreXPosition2(98);
                watchCricketUIModel.setTeamBScoreXPosition(115);
                watchCricketUIModel.setTeamBScoreXPosition2(98);
                watchCricketUIModel.setGameStatusXPosition(115);
                watchCricketUIModel.setGameStatusXPosition2(115);
                watchCricketUIModel.setGameStatusYPosition(230);
                watchCricketUIModel.setTeamAScoreYPosition(130);
                watchCricketUIModel.setTeamBScoreYPosition(165);
                watchCricketUIModel.setMatchFormatXPosition(125);
                watchCricketUIModel.setMatchFormatXPosition2(122);
                watchCricketUIModel.setMatchFormatYPosition(50);
            } else if (!kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.xtend_pro_2), false) && !kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.storm_pro_call_2), false) && !kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.ultima_call_pro), false) && !kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.ultima_connect_pro), false) && !kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_cosmos_talk), false)) {
                if (isPS1Device(context)) {
                    watchCricketUIModel.setBackGroundFileName("");
                    watchCricketUIModel.setBackgroundFileWidth(466);
                    watchCricketUIModel.setBackgroundFileHeight(466);
                    watchCricketUIModel.setTeamANameXPosition(100);
                    watchCricketUIModel.setTeamANameYPosition(CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA256);
                    watchCricketUIModel.setTeamBNameXPosition(314);
                    watchCricketUIModel.setTeamBNameYPosition(CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA256);
                    watchCricketUIModel.setTeamAScoreXPosition(53);
                    watchCricketUIModel.setTeamAScoreXPosition2(50);
                    watchCricketUIModel.setTeamAScoreYPosition(230);
                    watchCricketUIModel.setTeamBScoreXPosition(DfuException.ERROR_SEND_COMMAND_FAIL);
                    watchCricketUIModel.setTeamBScoreXPosition2(DfuException.ERROR_SEND_COMMAND_FAIL);
                    watchCricketUIModel.setTeamBScoreYPosition(230);
                    watchCricketUIModel.setGameStatusXPosition(174);
                    watchCricketUIModel.setGameStatusXPosition2(188);
                    watchCricketUIModel.setGameStatusYPosition(386);
                    watchCricketUIModel.setMatchFormatXPosition(206);
                    watchCricketUIModel.setMatchFormatXPosition2(220);
                    watchCricketUIModel.setMatchFormatYPosition(94);
                }
            } else {
                watchCricketUIModel.setBackGroundFileName("cricket_bg_ca3.bmp");
                watchCricketUIModel.setBackgroundFileWidth(368);
                watchCricketUIModel.setBackgroundFileHeight(448);
                watchCricketUIModel.setTeamANameXPosition(40);
                watchCricketUIModel.setTeamANameYPosition(DfuException.ERROR_READ_DEVICE_INFO_ERROR);
                watchCricketUIModel.setTeamBNameXPosition(40);
                watchCricketUIModel.setTeamBNameYPosition(com.veryfit.multi.nativeprotocol.b.h1);
                watchCricketUIModel.setTeamAScoreXPosition(181);
                watchCricketUIModel.setTeamAScoreXPosition2(158);
                watchCricketUIModel.setTeamBScoreXPosition(181);
                watchCricketUIModel.setTeamBScoreXPosition2(158);
                watchCricketUIModel.setGameStatusXPosition(120);
                watchCricketUIModel.setGameStatusXPosition2(120);
                watchCricketUIModel.setGameStatusYPosition(386);
                watchCricketUIModel.setTeamAScoreYPosition(DfuException.ERROR_READ_DEVICE_INFO_ERROR);
                watchCricketUIModel.setTeamBScoreYPosition(com.veryfit.multi.nativeprotocol.b.h1);
                watchCricketUIModel.setMatchFormatXPosition(158);
                watchCricketUIModel.setMatchFormatXPosition2(163);
                watchCricketUIModel.setMatchFormatYPosition(112);
            }
        } else {
            watchCricketUIModel.setBackGroundFileName("cricket_bg_cy1.bmp");
            watchCricketUIModel.setBackgroundFileWidth(454);
            watchCricketUIModel.setBackgroundFileHeight(454);
            watchCricketUIModel.setTeamANameXPosition(50);
            watchCricketUIModel.setTeamANameYPosition(185);
            watchCricketUIModel.setTeamBNameXPosition(50);
            watchCricketUIModel.setTeamBNameYPosition(247);
            watchCricketUIModel.setTeamAScoreXPosition(223);
            watchCricketUIModel.setTeamAScoreXPosition2(195);
            watchCricketUIModel.setTeamBScoreXPosition(223);
            watchCricketUIModel.setTeamBScoreXPosition2(195);
            watchCricketUIModel.setGameStatusXPosition(168);
            watchCricketUIModel.setGameStatusXPosition2(188);
            watchCricketUIModel.setGameStatusYPosition(ITask.EVT_START);
            watchCricketUIModel.setTeamAScoreYPosition(185);
            watchCricketUIModel.setTeamBScoreYPosition(247);
            watchCricketUIModel.setMatchFormatXPosition(Command.CMD_RECEIVE_SPEECH_CANCEL);
            watchCricketUIModel.setMatchFormatXPosition2(Command.CMD_RECEIVE_SPEECH_CANCEL);
            watchCricketUIModel.setMatchFormatYPosition(42);
        }
        return watchCricketUIModel;
    }

    @NotNull
    public final WatchCricketUIModel getWatchFootballUIPreference(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        WatchCricketUIModel watchCricketUIModel = new WatchCricketUIModel(null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 524287, null);
        if (isCADevice(context)) {
            if (Intrinsics.areEqual(SessionManager.getInstance(context).getDeviceType(), context.getString(R.string.cove_ca2))) {
                watchCricketUIModel.setBackGroundFileName("cricket_bg_ca2.bmp");
                watchCricketUIModel.setBackgroundFileWidth(320);
                watchCricketUIModel.setBackgroundFileHeight(385);
                watchCricketUIModel.setTeamANameXPosition(35);
                watchCricketUIModel.setTeamANameYPosition(233);
                watchCricketUIModel.setTeamBNameXPosition(35);
                watchCricketUIModel.setTeamBNameYPosition(DfuException.ERROR_READ_REMOTE_MAC_ADDR);
                watchCricketUIModel.setTeamAScoreXPosition(157);
                watchCricketUIModel.setTeamAScoreXPosition2(137);
                watchCricketUIModel.setTeamBScoreXPosition(157);
                watchCricketUIModel.setTeamBScoreXPosition2(137);
                watchCricketUIModel.setGameStatusXPosition(104);
                watchCricketUIModel.setGameStatusYPosition(com.veryfit.multi.nativeprotocol.b.m1);
                watchCricketUIModel.setTeamAScoreYPosition(233);
                watchCricketUIModel.setTeamBScoreYPosition(DfuException.ERROR_READ_REMOTE_MAC_ADDR);
                watchCricketUIModel.setMatchFormatXPosition(158);
                watchCricketUIModel.setMatchFormatXPosition2(142);
                watchCricketUIModel.setMatchFormatYPosition(96);
            } else if (isCAULCDevice(context)) {
                watchCricketUIModel.setBackGroundFileName("football_bg_ulc5.bmp");
                watchCricketUIModel.setBackgroundFileWidth(240);
                watchCricketUIModel.setBackgroundFileHeight(DfuException.ERROR_ENTER_OTA_MODE_FAILED);
                watchCricketUIModel.setTeamANameXPosition(30);
                watchCricketUIModel.setTeamANameYPosition(130);
                watchCricketUIModel.setTeamBNameXPosition(30);
                watchCricketUIModel.setTeamBNameYPosition(165);
                watchCricketUIModel.setTeamAScoreXPosition(115);
                watchCricketUIModel.setTeamAScoreXPosition2(98);
                watchCricketUIModel.setTeamBScoreXPosition(115);
                watchCricketUIModel.setTeamBScoreXPosition2(98);
                watchCricketUIModel.setGameStatusXPosition(115);
                watchCricketUIModel.setGameStatusXPosition2(115);
                watchCricketUIModel.setGameStatusYPosition(230);
                watchCricketUIModel.setTeamAScoreYPosition(130);
                watchCricketUIModel.setTeamBScoreYPosition(165);
                watchCricketUIModel.setMatchFormatXPosition(125);
                watchCricketUIModel.setMatchFormatXPosition2(122);
                watchCricketUIModel.setMatchFormatYPosition(45);
            } else {
                watchCricketUIModel.setBackGroundFileName("football_ca3.bmp");
                watchCricketUIModel.setBackgroundFileWidth(368);
                watchCricketUIModel.setBackgroundFileHeight(448);
                watchCricketUIModel.setTeamANameXPosition(40);
                watchCricketUIModel.setTeamANameYPosition(DfuException.ERROR_READ_DEVICE_INFO_ERROR);
                watchCricketUIModel.setTeamBNameXPosition(40);
                watchCricketUIModel.setTeamBNameYPosition(com.veryfit.multi.nativeprotocol.b.h1);
                watchCricketUIModel.setTeamAScoreXPosition(181);
                watchCricketUIModel.setTeamAScoreXPosition2(158);
                watchCricketUIModel.setTeamBScoreXPosition(181);
                watchCricketUIModel.setTeamBScoreXPosition2(158);
                watchCricketUIModel.setGameStatusXPosition(120);
                watchCricketUIModel.setGameStatusYPosition(386);
                watchCricketUIModel.setTeamAScoreYPosition(DfuException.ERROR_READ_DEVICE_INFO_ERROR);
                watchCricketUIModel.setTeamBScoreYPosition(com.veryfit.multi.nativeprotocol.b.h1);
                watchCricketUIModel.setMatchFormatXPosition(158);
                watchCricketUIModel.setMatchFormatXPosition2(163);
                watchCricketUIModel.setMatchFormatYPosition(112);
            }
        } else if (isCYDevice(context)) {
            watchCricketUIModel.setBackGroundFileName("football_bg_cy1.bmp");
            watchCricketUIModel.setBackgroundFileWidth(454);
            watchCricketUIModel.setBackgroundFileHeight(454);
            watchCricketUIModel.setTeamANameXPosition(45);
            watchCricketUIModel.setTeamANameYPosition(DfuException.ERROR_READ_DEVICE_INFO_ERROR);
            watchCricketUIModel.setTeamBNameXPosition(40);
            watchCricketUIModel.setTeamBNameYPosition(com.veryfit.multi.nativeprotocol.b.h1);
            watchCricketUIModel.setTeamAScoreXPosition(181);
            watchCricketUIModel.setTeamAScoreXPosition2(158);
            watchCricketUIModel.setTeamBScoreXPosition(181);
            watchCricketUIModel.setTeamBScoreXPosition2(158);
            watchCricketUIModel.setGameStatusXPosition(120);
            watchCricketUIModel.setGameStatusYPosition(386);
            watchCricketUIModel.setTeamAScoreYPosition(DfuException.ERROR_READ_DEVICE_INFO_ERROR);
            watchCricketUIModel.setTeamBScoreYPosition(com.veryfit.multi.nativeprotocol.b.h1);
            watchCricketUIModel.setMatchFormatXPosition(158);
            watchCricketUIModel.setMatchFormatXPosition2(163);
            watchCricketUIModel.setMatchFormatYPosition(112);
        } else if (isOPP1Device(context)) {
            watchCricketUIModel.setBackGroundFileName("football_opp1.bmp");
            watchCricketUIModel.setBackgroundFileWidth(240);
            watchCricketUIModel.setBackgroundFileHeight(DfuException.ERROR_DFU_ENABLE_BUFFER_CHECK_NO_RESPONSE);
            watchCricketUIModel.setTeamANameXPosition(45);
            watchCricketUIModel.setTeamANameYPosition(DfuException.ERROR_READ_DEVICE_INFO_ERROR);
            watchCricketUIModel.setTeamBNameXPosition(40);
            watchCricketUIModel.setTeamBNameYPosition(com.veryfit.multi.nativeprotocol.b.h1);
            watchCricketUIModel.setTeamAScoreXPosition(181);
            watchCricketUIModel.setTeamAScoreXPosition2(158);
            watchCricketUIModel.setTeamBScoreXPosition(181);
            watchCricketUIModel.setTeamBScoreXPosition2(158);
            watchCricketUIModel.setGameStatusXPosition(120);
            watchCricketUIModel.setGameStatusYPosition(386);
            watchCricketUIModel.setTeamAScoreYPosition(DfuException.ERROR_READ_DEVICE_INFO_ERROR);
            watchCricketUIModel.setTeamBScoreYPosition(com.veryfit.multi.nativeprotocol.b.h1);
            watchCricketUIModel.setMatchFormatXPosition(158);
            watchCricketUIModel.setMatchFormatXPosition2(163);
            watchCricketUIModel.setMatchFormatYPosition(112);
        } else if (isOPP2Device(context)) {
            watchCricketUIModel.setBackGroundFileName("football_opp2.bmp");
            watchCricketUIModel.setBackgroundFileWidth(240);
            watchCricketUIModel.setBackgroundFileHeight(DfuException.ERROR_DFU_SPP_RWS_NOT_READY);
            watchCricketUIModel.setTeamANameXPosition(45);
            watchCricketUIModel.setTeamANameYPosition(DfuException.ERROR_READ_DEVICE_INFO_ERROR);
            watchCricketUIModel.setTeamBNameXPosition(40);
            watchCricketUIModel.setTeamBNameYPosition(com.veryfit.multi.nativeprotocol.b.h1);
            watchCricketUIModel.setTeamAScoreXPosition(181);
            watchCricketUIModel.setTeamAScoreXPosition2(158);
            watchCricketUIModel.setTeamBScoreXPosition(181);
            watchCricketUIModel.setTeamBScoreXPosition2(158);
            watchCricketUIModel.setGameStatusXPosition(120);
            watchCricketUIModel.setGameStatusYPosition(386);
            watchCricketUIModel.setTeamAScoreYPosition(DfuException.ERROR_READ_DEVICE_INFO_ERROR);
            watchCricketUIModel.setTeamBScoreYPosition(com.veryfit.multi.nativeprotocol.b.h1);
            watchCricketUIModel.setMatchFormatXPosition(158);
            watchCricketUIModel.setMatchFormatXPosition2(163);
            watchCricketUIModel.setMatchFormatYPosition(112);
        } else if (isOPP3Device(context)) {
            watchCricketUIModel.setBackGroundFileName("football_opp3.bmp");
            watchCricketUIModel.setBackgroundFileWidth(com.veryfit.multi.nativeprotocol.b.C1);
            watchCricketUIModel.setBackgroundFileHeight(502);
            watchCricketUIModel.setTeamANameXPosition(45);
            watchCricketUIModel.setTeamANameYPosition(DfuException.ERROR_READ_DEVICE_INFO_ERROR);
            watchCricketUIModel.setTeamBNameXPosition(40);
            watchCricketUIModel.setTeamBNameYPosition(com.veryfit.multi.nativeprotocol.b.h1);
            watchCricketUIModel.setTeamAScoreXPosition(181);
            watchCricketUIModel.setTeamAScoreXPosition2(158);
            watchCricketUIModel.setTeamBScoreXPosition(181);
            watchCricketUIModel.setTeamBScoreXPosition2(158);
            watchCricketUIModel.setGameStatusXPosition(120);
            watchCricketUIModel.setGameStatusYPosition(386);
            watchCricketUIModel.setTeamAScoreYPosition(DfuException.ERROR_READ_DEVICE_INFO_ERROR);
            watchCricketUIModel.setTeamBScoreYPosition(com.veryfit.multi.nativeprotocol.b.h1);
            watchCricketUIModel.setMatchFormatXPosition(158);
            watchCricketUIModel.setMatchFormatXPosition2(163);
            watchCricketUIModel.setMatchFormatYPosition(112);
        } else if (isOPP5Device(context)) {
            watchCricketUIModel.setBackGroundFileName("football_opp5.bmp");
            watchCricketUIModel.setBackgroundFileWidth(240);
            watchCricketUIModel.setBackgroundFileHeight(296);
            watchCricketUIModel.setTeamANameXPosition(45);
            watchCricketUIModel.setTeamANameYPosition(DfuException.ERROR_READ_DEVICE_INFO_ERROR);
            watchCricketUIModel.setTeamBNameXPosition(40);
            watchCricketUIModel.setTeamBNameYPosition(com.veryfit.multi.nativeprotocol.b.h1);
            watchCricketUIModel.setTeamAScoreXPosition(181);
            watchCricketUIModel.setTeamAScoreXPosition2(158);
            watchCricketUIModel.setTeamBScoreXPosition(181);
            watchCricketUIModel.setTeamBScoreXPosition2(158);
            watchCricketUIModel.setGameStatusXPosition(120);
            watchCricketUIModel.setGameStatusYPosition(386);
            watchCricketUIModel.setTeamAScoreYPosition(DfuException.ERROR_READ_DEVICE_INFO_ERROR);
            watchCricketUIModel.setTeamBScoreYPosition(com.veryfit.multi.nativeprotocol.b.h1);
            watchCricketUIModel.setMatchFormatXPosition(158);
            watchCricketUIModel.setMatchFormatXPosition2(163);
            watchCricketUIModel.setMatchFormatYPosition(112);
        } else if (isOPP4Device(context)) {
            watchCricketUIModel.setBackGroundFileName("football_bg_opp4.bmp");
            watchCricketUIModel.setBackgroundFileWidth(466);
            watchCricketUIModel.setBackgroundFileHeight(466);
            watchCricketUIModel.setTeamANameXPosition(45);
            watchCricketUIModel.setTeamANameYPosition(DfuException.ERROR_READ_DEVICE_INFO_ERROR);
            watchCricketUIModel.setTeamBNameXPosition(40);
            watchCricketUIModel.setTeamBNameYPosition(com.veryfit.multi.nativeprotocol.b.h1);
            watchCricketUIModel.setTeamAScoreXPosition(181);
            watchCricketUIModel.setTeamAScoreXPosition2(158);
            watchCricketUIModel.setTeamBScoreXPosition(181);
            watchCricketUIModel.setTeamBScoreXPosition2(158);
            watchCricketUIModel.setGameStatusXPosition(120);
            watchCricketUIModel.setGameStatusYPosition(386);
            watchCricketUIModel.setTeamAScoreYPosition(DfuException.ERROR_READ_DEVICE_INFO_ERROR);
            watchCricketUIModel.setTeamBScoreYPosition(com.veryfit.multi.nativeprotocol.b.h1);
            watchCricketUIModel.setMatchFormatXPosition(158);
            watchCricketUIModel.setMatchFormatXPosition2(163);
            watchCricketUIModel.setMatchFormatYPosition(112);
        }
        return watchCricketUIModel;
    }

    public final boolean isCADevice(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ca0), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ca3), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ca3_bt), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ca2), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ca5_wave_style), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ca5_wave_play), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ca5_wave_beat), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ca3_wave_cosmos), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ca3_bt_wave_cosmsos_pro), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ca3_bt_stormpro_call), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ulc3_wave_smart), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ulc2_wave_beat_plus), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ulc2_wave_style_plus), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ulc2_wave_smart_plus), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ulc2_wave_lync), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.ultima_call), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.ultima_connect), false);
    }

    public final boolean isCAULCDevice(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ca5_wave_style), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ca5_wave_play), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ca5_wave_beat), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ulc3_wave_smart), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ulc2_wave_beat_plus), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ulc2_wave_style_plus), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ulc2_wave_smart_plus), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ulc2_wave_lync), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.ultima_call), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.ultima_connect), false);
    }

    public final boolean isCZDevice(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_cz1), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_cz3), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_wave_prime), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_wave_elite), false);
    }

    public final boolean isDeviceSupportsSoccerSportsSettings(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ca3_bt), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ca3_bt_stormpro_call), false) || isCYDevice(context) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.ultima_call), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.ultima_connect), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_call_2), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.storm_call_2), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_astra), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_sigma), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_neo_plus), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_active), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.ultima_prism), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.ultima_chronos), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_convex), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.lunar_orb), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.lunar_prime), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.primia_curv), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.xtend_pro_2), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.storm_pro_call_2), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.enigma_oasis), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.ultima_rise), false);
    }

    public final boolean isEndDateToday(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(11, 23);
        calendar2.set(12, 59);
        calendar2.set(13, 59);
        calendar2.set(14, 0);
        return j <= calendar2.getTimeInMillis() && calendar.getTimeInMillis() <= j;
    }

    public final boolean isOPP1Device(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_call_2), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_astra), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.storm_call_2), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.ultima_rise), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_call_3), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_astra_2), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.storm_call_3), false);
    }

    public final boolean isOPP2Device(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_neo_plus), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_active), false);
    }

    public final boolean isOPP3Device(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.ultima_prism), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.ultima_chronos), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_convex), false);
    }

    public final boolean isOPP4Device(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.lunar_orb), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.primia_curv), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.lunar_prime), false);
    }

    public final boolean isOPP5Device(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_sigma), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_sigma_3), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_chase), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_regal), false);
    }

    public final boolean isOPPDevice(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_call_2), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_astra), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.storm_call_2), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_sigma), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_neo_plus), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_active), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.ultima_prism), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.ultima_chronos), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_convex), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.lunar_orb), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.lunar_prime), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.primia_curv), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.xtend_pro_2), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.storm_pro_call_2), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.lunar_call_pro_2), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.lunar_connect_pro_2), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_primia_talk_2), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.ultima_call_pro), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.ultima_connect_pro), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_cosmos_talk), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.ultima_rise), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_call_3), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_astra_2), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.storm_call_3), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_sigma_3), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_chase), false) || kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_regal), false);
    }

    public final boolean isPS1Device(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.enigma_oasis), false);
    }

    @NotNull
    public final Calendar minusMinutesFromCalendar(int i2, long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        calendar.add(12, -i2);
        Intrinsics.checkNotNullExpressionValue(calendar, "calendar");
        return calendar;
    }
}
