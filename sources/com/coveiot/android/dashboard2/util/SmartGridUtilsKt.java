package com.coveiot.android.dashboard2.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.annotation.RequiresApi;
import com.coveiot.android.activitymodes.utils.ViewUtilsKt;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.DeviceSupportedFeatures;
import com.coveiot.android.dashboard2.R;
import com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.healthbuddies.activities.ActivityInAppWebViewer;
import com.coveiot.android.healthbuddies.constants.Constants;
import com.coveiot.android.sos.utils.SOSCleverTapConstants;
import com.coveiot.android.theme.CleverTapConstants;
import com.coveiot.android.theme.model.BoatCoinsScreenCaller;
import com.coveiot.android.theme.utils.ThemeConstants;
import com.coveiot.android.watchfaceui.webSupport.ActivityInAppWebViewerWatchface;
import com.coveiot.coveaccess.smartgrid.model.GetSmartGridRes;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import kotlin.text.m;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes4.dex */
public final class SmartGridUtilsKt {
    public static final Boolean a(Context context, String str) {
        BleApi bleApi;
        DeviceSupportedFeatures deviceSupportedFeatures;
        BleApi bleApi2;
        DeviceSupportedFeatures deviceSupportedFeatures2;
        BleApi bleApi3;
        DeviceSupportedFeatures deviceSupportedFeatures3;
        BleApi bleApi4;
        DeviceSupportedFeatures deviceSupportedFeatures4;
        if (Intrinsics.areEqual(str, SmartGridEnums.WATCH_FACE_STUDIO.getValue())) {
            return Boolean.valueOf(SetupYourWatchDataHelper.INSTANCE.isDiySupported(context));
        }
        if (Intrinsics.areEqual(str, SmartGridEnums.QR_TRAY.getValue())) {
            BleApiManager bleApiManager = BleApiManager.getInstance(context);
            if (bleApiManager == null || (bleApi4 = bleApiManager.getBleApi()) == null || (deviceSupportedFeatures4 = bleApi4.getDeviceSupportedFeatures()) == null) {
                return null;
            }
            return Boolean.valueOf(deviceSupportedFeatures4.isQRCodeSupported());
        } else if (Intrinsics.areEqual(str, SmartGridEnums.NAVIGATION.getValue())) {
            BleApiManager bleApiManager2 = BleApiManager.getInstance(context);
            if (bleApiManager2 == null || (bleApi3 = bleApiManager2.getBleApi()) == null || (deviceSupportedFeatures3 = bleApi3.getDeviceSupportedFeatures()) == null) {
                return null;
            }
            return Boolean.valueOf(deviceSupportedFeatures3.isTurnByTurnNavigationSupported());
        } else if (Intrinsics.areEqual(str, SmartGridEnums.SMART_ALERTS.getValue())) {
            BleApiManager bleApiManager3 = BleApiManager.getInstance(context);
            if (bleApiManager3 == null || (bleApi2 = bleApiManager3.getBleApi()) == null || (deviceSupportedFeatures2 = bleApi2.getDeviceSupportedFeatures()) == null) {
                return null;
            }
            return Boolean.valueOf(deviceSupportedFeatures2.isSmartAlertsSupported());
        } else if (Intrinsics.areEqual(str, SmartGridEnums.SOS.getValue())) {
            BleApiManager bleApiManager4 = BleApiManager.getInstance(context);
            if (bleApiManager4 == null || (bleApi = bleApiManager4.getBleApi()) == null || (deviceSupportedFeatures = bleApi.getDeviceSupportedFeatures()) == null) {
                return null;
            }
            return Boolean.valueOf(deviceSupportedFeatures.isSosSupported());
        } else if (Intrinsics.areEqual(str, SmartGridEnums.ACTIV_HLTH.getValue())) {
            return Boolean.FALSE;
        } else {
            if (Intrinsics.areEqual(str, SmartGridEnums.BOAT_COINS.getValue()) ? true : Intrinsics.areEqual(str, SmartGridEnums.FITNESS_CHALLENGE.getValue()) ? true : Intrinsics.areEqual(str, SmartGridEnums.FITNESS_BUDDIES.getValue()) ? true : Intrinsics.areEqual(str, SmartGridEnums.FITNESS_REPORT.getValue())) {
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        }
    }

    public static final void b(Context context, String str) {
        Uri parse = Uri.parse(str);
        Intrinsics.checkNotNullExpressionValue(parse, "parse(ctaLink)");
        Intent intent = new Intent("android.intent.action.VIEW", parse);
        intent.putExtra(CleverTapConstants.CustomEventProperties.SOURCE.getValue(), CleverTapConstants.CustomEventValues.HP_GRID.getValue());
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
            return;
        }
        String string = context.getString(R.string.no_app_to_handle_deep_link);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.stri…_app_to_handle_deep_link)");
        ViewUtilsKt.toast(context, string);
    }

    @RequiresApi(26)
    public static final void callCTEventSmartGrid(@NotNull Context context, @NotNull GetSmartGridRes.GridItem item) {
        String eventValue;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(item, "item");
        String featureId = item.getFeatureId();
        if (Intrinsics.areEqual(featureId, SmartGridEnums.WATCH_FACE_STUDIO.getValue())) {
            eventValue = CleverTapConstants.CustomEventValues.WFS.getValue();
        } else if (Intrinsics.areEqual(featureId, SmartGridEnums.BOAT_COINS.getValue())) {
            eventValue = CleverTapConstants.CustomEventValues.BOATCOINS.getValue();
        } else if (Intrinsics.areEqual(featureId, SmartGridEnums.FITNESS_CHALLENGE.getValue())) {
            eventValue = CleverTapConstants.CustomEventValues.CHALLENGES.getValue();
        } else if (Intrinsics.areEqual(featureId, SmartGridEnums.QR_TRAY.getValue())) {
            eventValue = CleverTapConstants.CustomEventValues.QRTRAY.getValue();
        } else if (Intrinsics.areEqual(featureId, SmartGridEnums.NAVIGATION.getValue())) {
            eventValue = CleverTapConstants.CustomEventValues.NAVIGATION.getValue();
        } else if (Intrinsics.areEqual(featureId, SmartGridEnums.SMART_ALERTS.getValue())) {
            eventValue = CleverTapConstants.CustomEventValues.SMARTALERTS.getValue();
        } else if (Intrinsics.areEqual(featureId, SmartGridEnums.SOS.getValue())) {
            eventValue = CleverTapConstants.CustomEventValues.SOS.getValue();
        } else if (Intrinsics.areEqual(featureId, SmartGridEnums.ACTIV_HLTH.getValue())) {
            eventValue = CleverTapConstants.CustomEventValues.ACTIV_HEALTH.getValue();
        } else if (Intrinsics.areEqual(featureId, SmartGridEnums.FITNESS_BUDDIES.getValue())) {
            eventValue = CleverTapConstants.CustomEventValues.FITNESS_BUDDIES.getValue();
        } else if (Intrinsics.areEqual(featureId, SmartGridEnums.FITNESS_REPORT.getValue())) {
            eventValue = CleverTapConstants.CustomEventValues.FITNESS_REPORT.getValue();
        } else {
            eventValue = item.getTitle();
        }
        HashMap<String, Object> hashMap = new HashMap<>();
        String value = CleverTapConstants.CustomEventProperties.GRIDITEM.getValue();
        Intrinsics.checkNotNullExpressionValue(eventValue, "eventValue");
        hashMap.put(value, eventValue);
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        hashMap.putAll(companion.getDeviceId(context));
        hashMap.putAll(companion.getWatchDetails(context));
        companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_HP_GRIDTAPPED.getValue(), hashMap);
    }

    @NotNull
    public static final ArrayList<GetSmartGridRes.GridItem> getFilteredSmartGridList(@NotNull Context context, @NotNull List<GetSmartGridRes.GridItem> gridList) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(gridList, "gridList");
        ArrayList<GetSmartGridRes.GridItem> arrayList = new ArrayList<>();
        for (GetSmartGridRes.GridItem gridItem : gridList) {
            if (gridItem.getFeatureId() != null) {
                String featureId = gridItem.getFeatureId();
                Intrinsics.checkNotNullExpressionValue(featureId, "gridItem.featureId");
                if (Intrinsics.areEqual(a(context, featureId), Boolean.TRUE)) {
                    arrayList.add(gridItem);
                }
            } else {
                arrayList.add(gridItem);
            }
        }
        return arrayList;
    }

    @RequiresApi(26)
    public static final void navigateSmartGridItems(@NotNull Activity activity, @NotNull Context context, @NotNull GetSmartGridRes.GridItem item) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(item, "item");
        if (item.getCtaType() != null) {
            String ctaType = item.getCtaType();
            if (ctaType != null) {
                int hashCode = ctaType.hashCode();
                if (hashCode != -2130109465) {
                    if (hashCode != -1038134325) {
                        if (hashCode == 1775547265 && ctaType.equals("IN_APP_WEBVIEW")) {
                            if (item.getCtaLink() != null) {
                                Intent intent = new Intent(context, ActivityInAppWebViewer.class);
                                intent.putExtra(Constants.INTENT_EXTRA_URL.getValue(), item.getCtaLink());
                                intent.putExtra(Constants.INTENT_EXTRA_TITLE.getValue(), item.getTitle());
                                context.startActivity(intent);
                                return;
                            }
                            return;
                        }
                    } else if (ctaType.equals("EXTERNAL")) {
                        if (item.getCtaLink() != null) {
                            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(item.getCtaLink())));
                            return;
                        }
                        return;
                    }
                } else if (ctaType.equals("IN_APP")) {
                    if (item.getCtaLink() != null) {
                        String ctaLink = item.getCtaLink();
                        Intrinsics.checkNotNullExpressionValue(ctaLink, "item.ctaLink");
                        if (StringsKt__StringsKt.contains$default((CharSequence) ctaLink, (CharSequence) "__u", false, 2, (Object) null)) {
                            String decode = URLDecoder.decode(item.getCtaLink(), "UTF-8");
                            Intrinsics.checkNotNullExpressionValue(decode, "decode(item.ctaLink, \"UTF-8\")");
                            Uri parse = Uri.parse(decode);
                            Intrinsics.checkNotNullExpressionValue(parse, "parse(this)");
                            String queryParameter = parse.getQueryParameter("__u");
                            String replace$default = queryParameter != null ? m.replace$default(queryParameter, "-", "+", false, 4, (Object) null) : null;
                            String replace$default2 = replace$default != null ? m.replace$default(replace$default, "_", MqttTopic.TOPIC_LEVEL_SEPARATOR, false, 4, (Object) null) : null;
                            String valueOf = String.valueOf(DeviceUtils.Companion.decryptRSA(context, replace$default2 + '='));
                            if (Intrinsics.areEqual(item.getFeatureId(), SmartGridEnums.BOAT_COINS.getValue())) {
                                if (activity instanceof FragmentHomeCallBackInterface) {
                                    ((FragmentHomeCallBackInterface) activity).navigateToBoatCoinsWebViewer(BoatCoinsScreenCaller.NULL, valueOf);
                                    return;
                                }
                                return;
                            } else if (Intrinsics.areEqual(item.getFeatureId(), SmartGridEnums.WATCH_FACE_STUDIO.getValue())) {
                                Intent intent2 = new Intent(context, ActivityInAppWebViewerWatchface.class);
                                intent2.putExtra(Constants.INTENT_EXTRA_URL.getValue(), valueOf);
                                intent2.putExtra(ThemeConstants.INTENT_EXTRA_FROM_DASHBOARD.getValue(), true);
                                intent2.putExtra(ThemeConstants.FROM_SMART_GRID.getValue(), true);
                                intent2.setFlags(67108864);
                                activity.startActivityForResult(intent2, 123);
                                return;
                            } else {
                                return;
                            }
                        } else if (Intrinsics.areEqual(item.getFeatureId(), SmartGridEnums.FITNESS_BUDDIES.getValue())) {
                            if (activity instanceof FragmentHomeCallBackInterface) {
                                ((FragmentHomeCallBackInterface) activity).navigateToFitnessBuddies();
                                return;
                            }
                            return;
                        } else if (Intrinsics.areEqual(item.getFeatureId(), SmartGridEnums.SOS.getValue())) {
                            if (activity instanceof FragmentHomeCallBackInterface) {
                                ((FragmentHomeCallBackInterface) activity).navigateToSOSSettings(SOSCleverTapConstants.SOS_HP_GRID.getValue());
                                return;
                            }
                            return;
                        } else if (Intrinsics.areEqual(item.getFeatureId(), SmartGridEnums.NAVIGATION.getValue())) {
                            if (activity instanceof FragmentHomeCallBackInterface) {
                                ((FragmentHomeCallBackInterface) activity).navigateToNavigationScreen();
                                return;
                            }
                            return;
                        } else {
                            String ctaLink2 = item.getCtaLink();
                            Intrinsics.checkNotNullExpressionValue(ctaLink2, "item.ctaLink");
                            b(context, ctaLink2);
                            return;
                        }
                    }
                    return;
                }
            }
            String string = context.getString(R.string.something_went_wrong);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.something_went_wrong)");
            ViewUtilsKt.toast(context, string);
        }
    }
}
