package com.coveiot.analytics;

import android.os.Bundle;
import com.coveiot.analytics.AnalyticsEventParams;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class AnalyticsLog {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public String f2696a;
    @NotNull
    public final HashMap<String, String> b = new HashMap<>();
    @NotNull
    public final HashMap<String, Object> c = new HashMap<>();

    @Nullable
    public final String getEventName() {
        return this.f2696a;
    }

    @NotNull
    public final HashMap<String, Object> getHashMapData() {
        return this.c;
    }

    @NotNull
    public final AnalyticsLog setActivityCode(@NotNull String activityCode) {
        Intrinsics.checkNotNullParameter(activityCode, "activityCode");
        this.b.put(AnalyticsEventParams.Key.CV_ACT_CODE.textValue$analytics_release(), activityCode);
        return this;
    }

    @NotNull
    public final AnalyticsLog setAppPermissionId(@NotNull String appPermissionId) {
        Intrinsics.checkNotNullParameter(appPermissionId, "appPermissionId");
        this.b.put(AnalyticsEventParams.Key.APP_PERMISSION_ID.textValue$analytics_release(), appPermissionId);
        return this;
    }

    @NotNull
    public final AnalyticsLog setCTX(@NotNull String ctx) {
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        this.b.put(AnalyticsEventParams.Key.CV_CTX.textValue$analytics_release(), ctx);
        return this;
    }

    @NotNull
    public final AnalyticsLog setCVDescription(@NotNull String descKey) {
        Intrinsics.checkNotNullParameter(descKey, "descKey");
        this.b.put(AnalyticsEventParams.Key.CV_DESC_KEY.textValue$analytics_release(), descKey);
        return this;
    }

    @NotNull
    public final AnalyticsLog setCVPrevScreenName(@NotNull String previousScreenName) {
        Intrinsics.checkNotNullParameter(previousScreenName, "previousScreenName");
        this.b.put(AnalyticsEventParams.Key.CV_PREV_SCREEN_NAME.textValue$analytics_release(), previousScreenName);
        return this;
    }

    @NotNull
    public final AnalyticsLog setCVScreenName(@NotNull String screenName) {
        Intrinsics.checkNotNullParameter(screenName, "screenName");
        this.b.put(AnalyticsEventParams.Key.CV_SCREEN_NAME.textValue$analytics_release(), screenName);
        return this;
    }

    @NotNull
    public final AnalyticsLog setCVValue(@NotNull String cvValue) {
        Intrinsics.checkNotNullParameter(cvValue, "cvValue");
        this.b.put(AnalyticsEventParams.Key.CV_VALUE.textValue$analytics_release(), cvValue);
        return this;
    }

    @NotNull
    public final AnalyticsLog setCategoryId(@NotNull String categoryId) {
        Intrinsics.checkNotNullParameter(categoryId, "categoryId");
        this.b.put(AnalyticsEventParams.Key.CV_CAT_ID.textValue$analytics_release(), categoryId);
        return this;
    }

    @NotNull
    public final AnalyticsLog setCommandName(@NotNull String commandName) {
        Intrinsics.checkNotNullParameter(commandName, "commandName");
        this.b.put(AnalyticsEventParams.Key.COMMAND_NAME.textValue$analytics_release(), commandName);
        return this;
    }

    @NotNull
    public final AnalyticsLog setCommandStatus(@NotNull String commandStatus) {
        Intrinsics.checkNotNullParameter(commandStatus, "commandStatus");
        this.b.put(AnalyticsEventParams.Key.COMMAND_STATUS.textValue$analytics_release(), commandStatus);
        return this;
    }

    @NotNull
    public final AnalyticsLog setCricketTeam1(@NotNull String newActivityCode) {
        Intrinsics.checkNotNullParameter(newActivityCode, "newActivityCode");
        this.b.put(AnalyticsEventParams.Key.CV_CRICKET_TEAM1.textValue$analytics_release(), newActivityCode);
        return this;
    }

    @NotNull
    public final AnalyticsLog setCricketTeam2(@NotNull String descKey) {
        Intrinsics.checkNotNullParameter(descKey, "descKey");
        this.b.put(AnalyticsEventParams.Key.CV_CRICKET_TEAM2.textValue$analytics_release(), descKey);
        return this;
    }

    @NotNull
    public final AnalyticsLog setData(@NotNull Object data) {
        Intrinsics.checkNotNullParameter(data, "data");
        if (data instanceof ArrayList) {
            ArrayList arrayList = (ArrayList) data;
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                HashMap<String, String> hashMap = this.b;
                hashMap.put("object " + i, arrayList.get(i).toString());
            }
        }
        return this;
    }

    @NotNull
    public final AnalyticsLog setDescription(@NotNull String description) {
        Intrinsics.checkNotNullParameter(description, "description");
        this.b.put(AnalyticsEventParams.Key.DESCRIPTION.textValue$analytics_release(), description);
        return this;
    }

    @NotNull
    public final AnalyticsLog setDeviceConnectionStatus(@NotNull String deviceConnectionStatus) {
        Intrinsics.checkNotNullParameter(deviceConnectionStatus, "deviceConnectionStatus");
        this.b.put(AnalyticsEventParams.Key.DEVICE_CONNECTION_STATUS.textValue$analytics_release(), deviceConnectionStatus);
        return this;
    }

    @NotNull
    public final AnalyticsLog setDisplay(@NotNull String display) {
        Intrinsics.checkNotNullParameter(display, "display");
        this.b.put(AnalyticsEventParams.Key.KH_BUILD_DISPLAY_NAME.textValue$analytics_release(), display);
        return this;
    }

    @NotNull
    public final AnalyticsLog setEventName(@NotNull String eventName) {
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        this.f2696a = eventName;
        return this;
    }

    @NotNull
    public final AnalyticsLog setFingerPrint(@NotNull String fingerPrint) {
        Intrinsics.checkNotNullParameter(fingerPrint, "fingerPrint");
        this.b.put(AnalyticsEventParams.Key.KH_BUILD_FINGERPRINT.textValue$analytics_release(), fingerPrint);
        return this;
    }

    @NotNull
    public final AnalyticsLog setHashMapData(@NotNull HashMap<String, Object> dataMap) {
        Intrinsics.checkNotNullParameter(dataMap, "dataMap");
        for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
            Intrinsics.checkNotNullExpressionValue(entry, "dataMap.entries");
            String key = entry.getKey();
            Object value = entry.getValue();
            HashMap<String, Object> hashMap = this.c;
            Intrinsics.checkNotNullExpressionValue(key, "key");
            Intrinsics.checkNotNullExpressionValue(value, "value");
            hashMap.put(key, value);
        }
        return this;
    }

    @NotNull
    public final AnalyticsLog setMapData(@NotNull HashMap<String, String> dataMap) {
        Intrinsics.checkNotNullParameter(dataMap, "dataMap");
        for (Map.Entry<String, String> entry : dataMap.entrySet()) {
            Intrinsics.checkNotNullExpressionValue(entry, "dataMap.entries");
            String key = entry.getKey();
            String value = entry.getValue();
            HashMap<String, String> hashMap = this.b;
            Intrinsics.checkNotNullExpressionValue(key, "key");
            Intrinsics.checkNotNullExpressionValue(value, "value");
            hashMap.put(key, value);
        }
        return this;
    }

    @NotNull
    public final AnalyticsLog setMatchDate(@NotNull String descKey) {
        Intrinsics.checkNotNullParameter(descKey, "descKey");
        this.b.put(AnalyticsEventParams.Key.CV_MATCH_DATE.textValue$analytics_release(), descKey);
        return this;
    }

    @NotNull
    public final AnalyticsLog setMatchID(@NotNull String newActivityCode) {
        Intrinsics.checkNotNullParameter(newActivityCode, "newActivityCode");
        this.b.put(AnalyticsEventParams.Key.CV_MATCH_ID.textValue$analytics_release(), newActivityCode);
        return this;
    }

    @NotNull
    public final AnalyticsLog setMatchStartTime(@NotNull String matchStartTime) {
        Intrinsics.checkNotNullParameter(matchStartTime, "matchStartTime");
        this.b.put(AnalyticsEventParams.Key.CV_MATCH_START_TIME.textValue$analytics_release(), matchStartTime);
        return this;
    }

    @NotNull
    public final AnalyticsLog setMatchType(@NotNull String newActivityCode) {
        Intrinsics.checkNotNullParameter(newActivityCode, "newActivityCode");
        this.b.put(AnalyticsEventParams.Key.CV_MATCH_TYPE.textValue$analytics_release(), newActivityCode);
        return this;
    }

    @NotNull
    public final AnalyticsLog setNewActivityCode(@NotNull String newActivityCode) {
        Intrinsics.checkNotNullParameter(newActivityCode, "newActivityCode");
        this.b.put(AnalyticsEventParams.Key.CV_NEW_ACT_CODE.textValue$analytics_release(), newActivityCode);
        return this;
    }

    @NotNull
    public final AnalyticsLog setNewCountryCode(@NotNull String newCountryCode) {
        Intrinsics.checkNotNullParameter(newCountryCode, "newCountryCode");
        this.b.put(AnalyticsEventParams.Key.NEW_COUNTRY_CODE.textValue$analytics_release(), newCountryCode);
        return this;
    }

    @NotNull
    public final AnalyticsLog setNewStepsTarget(@NotNull String newStepsTarget) {
        Intrinsics.checkNotNullParameter(newStepsTarget, "newStepsTarget");
        this.b.put(AnalyticsEventParams.Key.NEW_STEPS_TARGET.textValue$analytics_release(), newStepsTarget);
        return this;
    }

    @NotNull
    public final AnalyticsLog setOldActivityCode(@NotNull String oldActivityCode) {
        Intrinsics.checkNotNullParameter(oldActivityCode, "oldActivityCode");
        this.b.put(AnalyticsEventParams.Key.CV_OLD_ACT_CODE.textValue$analytics_release(), oldActivityCode);
        return this;
    }

    @NotNull
    public final AnalyticsLog setOldCountryCode(@NotNull String oldCountryCode) {
        Intrinsics.checkNotNullParameter(oldCountryCode, "oldCountryCode");
        this.b.put(AnalyticsEventParams.Key.OLD_COUNTRY_CODE.textValue$analytics_release(), oldCountryCode);
        return this;
    }

    @NotNull
    public final AnalyticsLog setOldStepstarget(@NotNull String oldStepsTarget) {
        Intrinsics.checkNotNullParameter(oldStepsTarget, "oldStepsTarget");
        this.b.put(AnalyticsEventParams.Key.OLD_STEPS_TARGET.textValue$analytics_release(), oldStepsTarget);
        return this;
    }

    @NotNull
    public final AnalyticsLog setParentScreenName(@NotNull String parentScreenName) {
        Intrinsics.checkNotNullParameter(parentScreenName, "parentScreenName");
        this.b.put(AnalyticsEventParams.Key.PARENT_SCREEN_NAME.textValue$analytics_release(), parentScreenName);
        return this;
    }

    @NotNull
    public final AnalyticsLog setPreviousScreenName(@NotNull String previousScreenName) {
        Intrinsics.checkNotNullParameter(previousScreenName, "previousScreenName");
        this.b.put(AnalyticsEventParams.Key.PREVIOUS_SCREEN_NAME.textValue$analytics_release(), previousScreenName);
        return this;
    }

    @NotNull
    public final AnalyticsLog setScreenName(@NotNull String screenName) {
        Intrinsics.checkNotNullParameter(screenName, "screenName");
        this.b.put(AnalyticsEventParams.Key.SCREEN_NAME.textValue$analytics_release(), screenName);
        return this;
    }

    @NotNull
    public final AnalyticsLog setSecondaryTimeZone(@NotNull String timeZone) {
        Intrinsics.checkNotNullParameter(timeZone, "timeZone");
        this.b.put(AnalyticsEventParams.Key.KH_SECONDARY_TIME_ZONE.textValue$analytics_release(), timeZone);
        return this;
    }

    @NotNull
    public final AnalyticsLog setTimeStamp(@NotNull String timeStamp) {
        Intrinsics.checkNotNullParameter(timeStamp, "timeStamp");
        this.b.put(AnalyticsEventParams.Key.TIME_STAMP.textValue$analytics_release(), timeStamp);
        return this;
    }

    @NotNull
    public final AnalyticsLog setUiElementName(@NotNull String uiElementName) {
        Intrinsics.checkNotNullParameter(uiElementName, "uiElementName");
        this.b.put(AnalyticsEventParams.Key.UI_ELEMENT.textValue$analytics_release(), uiElementName);
        return this;
    }

    @NotNull
    public final AnalyticsLog setWatchFace(@NotNull String watchFace) {
        Intrinsics.checkNotNullParameter(watchFace, "watchFace");
        this.b.put(AnalyticsEventParams.Key.KH_WATCH_FACE_SET.textValue$analytics_release(), watchFace);
        return this;
    }

    @NotNull
    public final Bundle toBundle() {
        Bundle bundle = new Bundle();
        try {
            HashMap<String, String> hashMap = this.b;
            if (hashMap != null && hashMap.size() > 0) {
                for (Map.Entry<String, String> entry : this.b.entrySet()) {
                    bundle.putString(entry.getKey(), entry.getValue());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bundle;
    }
}
