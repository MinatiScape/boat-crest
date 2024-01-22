package com.coveiot.android.leonardo.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.coveiot.android.activitymodes.models.CustomMessageConfiguration;
import com.coveiot.coveaccess.model.server.SRemoteConfigResponse;
import com.coveiot.covepreferences.data.AppNotificationData;
import com.coveiot.utils.utility.LogHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
/* loaded from: classes5.dex */
public class PreferenceManager {

    /* renamed from: a  reason: collision with root package name */
    public static Gson f5432a = new Gson();

    /* loaded from: classes5.dex */
    public class a extends TypeToken<ArrayList<AppNotificationData>> {
    }

    public static void a(Context context, PreferenceType preferenceType, String str, Object obj) {
        String fileName;
        Boolean parseBoolean;
        if (preferenceType != null) {
            try {
                fileName = preferenceType.getFileName();
            } catch (Exception e) {
                LogHelper.d("TAG", "Exception :" + e.toString());
                return;
            }
        } else {
            fileName = str;
        }
        SharedPreferences.Editor edit = context.getSharedPreferences(fileName, 0).edit();
        if (obj instanceof String) {
            edit.putString(str, obj.toString());
        } else if (obj instanceof Integer) {
            Integer valueOf = Integer.valueOf(PayUtils.parseInt(obj.toString(), -1));
            if (valueOf.intValue() != -1) {
                edit.putInt(str, valueOf.intValue());
            }
        } else if (obj instanceof Long) {
            Long valueOf2 = Long.valueOf(PayUtils.parseLong(obj.toString(), -1L));
            if (valueOf2.longValue() != -1) {
                edit.putLong(str, valueOf2.longValue());
            }
        } else if ((obj instanceof Boolean) && (parseBoolean = PayUtils.parseBoolean(obj.toString(), null)) != null) {
            edit.putBoolean(str, parseBoolean.booleanValue());
        }
        edit.commit();
    }

    public static void clearAppNotificationData(Context context) {
        remove(context.getApplicationContext(), PreferenceNames.APP_NOTIFICATION_DATA.getName());
    }

    public static void clearPreferences(Context context) {
        for (String str : context.getSharedPreferences(PreferenceType.SF_APP.getFileName(), 0).getAll().keySet()) {
            remove(context, str);
        }
    }

    public static ArrayList<AppNotificationData> getAppNotificationData(Context context) {
        String str = (String) getPreference(context.getApplicationContext(), PreferenceNames.APP_NOTIFICATION_DATA, AppConstants.EMPTY_STRING.getValue());
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return (ArrayList) f5432a.fromJson(str, new a().getType());
        } catch (Exception unused) {
            return null;
        }
    }

    public static SRemoteConfigResponse.DataBean.FitnessPlanBean.WebViewUrlBean getConfigUrlsFromPref(Context context) {
        return (SRemoteConfigResponse.DataBean.FitnessPlanBean.WebViewUrlBean) new Gson().fromJson((String) getPreference(context.getApplicationContext(), PreferenceNames.APP_CONFIG_URLS, AppConstants.EMPTY_STRING.getValue()), (Class<Object>) SRemoteConfigResponse.DataBean.FitnessPlanBean.WebViewUrlBean.class);
    }

    public static CustomMessageConfiguration getCustomMessageConfiguration(Context context) {
        return (CustomMessageConfiguration) new Gson().fromJson((String) getPreference(context, PreferenceNames.CUSTOM_MESSAGE_CONFIG, AppConstants.EMPTY_STRING.getValue()), (Class<Object>) CustomMessageConfiguration.class);
    }

    public static String getLastSensAIServerSync(Context context) {
        return (String) getPreference(context.getApplicationContext(), PreferenceNames.LAST_SENS_AI_SERVER_SYNC, AppConstants.EMPTY_STRING.getValue());
    }

    public static <T> T getPreference(Context context, SavedPreference savedPreference, T t) {
        return (T) getPreference(context, savedPreference.getPreferenceType(), savedPreference.getName(), t);
    }

    public static void insertConfigUrlsToPref(Context context, SRemoteConfigResponse.DataBean.FitnessPlanBean.WebViewUrlBean webViewUrlBean) {
        savePreference(context.getApplicationContext(), PreferenceNames.APP_CONFIG_URLS, f5432a.toJson(webViewUrlBean));
    }

    public static boolean isBoatCoinsEnabled(Context context) {
        return ((Boolean) getPreference(context.getApplicationContext(), PreferenceNames.IS_BOAT_COINS_ENABLED, Boolean.FALSE)).booleanValue();
    }

    public static boolean isRunStrideLengthManuallyEdited(Context context) {
        return ((Boolean) getPreference(context.getApplicationContext(), PreferenceNames.IS_RUN_STRIDE_LENGTH_MANUALLY_EDITED, Boolean.FALSE)).booleanValue();
    }

    public static boolean isSensAIEnabled(Context context) {
        return ((Boolean) getPreference(context.getApplicationContext(), PreferenceNames.IS_SENS_AI_ENABLED, Boolean.FALSE)).booleanValue();
    }

    public static boolean isSensAIFTUShown(Context context) {
        return ((Boolean) getPreference(context.getApplicationContext(), PreferenceNames.IS_SENS_AI_FTU_SHOWN, Boolean.FALSE)).booleanValue();
    }

    public static boolean isSpO2FTUDone(Context context) {
        return ((Boolean) getPreference(context.getApplicationContext(), PreferenceNames.SPO2_FTU, Boolean.FALSE)).booleanValue();
    }

    public static boolean isTemperatureDisclaimerDoNotShowAgainOn(Context context) {
        return ((Boolean) getPreference(context.getApplicationContext(), PreferenceNames.TEMPERATURE_DISCLAIMER_DO_NOT_SHOW_ON, Boolean.FALSE)).booleanValue();
    }

    public static boolean isTemperatureDisclaimerShown(Context context) {
        return ((Boolean) getPreference(context.getApplicationContext(), PreferenceNames.IS_TEMPERATURE_DISCLAIMER_SHOWN, Boolean.FALSE)).booleanValue();
    }

    public static boolean isWalkStrideLengthManuallyEdited(Context context) {
        return ((Boolean) getPreference(context.getApplicationContext(), PreferenceNames.IS_WALK_STRIDE_LENGTH_MANUALLY_EDITED, Boolean.FALSE)).booleanValue();
    }

    public static void remove(Context context, String str) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PreferenceType.SF_APP.getFileName(), 0);
        if (sharedPreferences.contains(str)) {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.remove(str);
            edit.commit();
        }
    }

    public static void saveAppNotificationData(Context context, ArrayList<AppNotificationData> arrayList) {
        savePreference(context.getApplicationContext(), PreferenceNames.APP_NOTIFICATION_DATA, f5432a.toJson(arrayList));
    }

    public static void saveBoatCoinesEnabled(Context context, boolean z) {
        savePreference(context.getApplicationContext(), PreferenceNames.IS_BOAT_COINS_ENABLED, Boolean.valueOf(z));
    }

    public static void saveCustomMessageConfiguration(Context context, CustomMessageConfiguration customMessageConfiguration) {
        savePreference(context, PreferenceNames.CUSTOM_MESSAGE_CONFIG, f5432a.toJson(customMessageConfiguration));
    }

    public static void savePreference(Context context, SavedPreference savedPreference, Object obj) {
        a(context, savedPreference.getPreferenceType(), savedPreference.getName(), obj);
    }

    public static void saveRunStrideLengthManuallyEdited(Context context, boolean z) {
        savePreference(context.getApplicationContext(), PreferenceNames.IS_RUN_STRIDE_LENGTH_MANUALLY_EDITED, Boolean.valueOf(z));
    }

    public static void saveSensAIEnabled(Context context, boolean z) {
        savePreference(context.getApplicationContext(), PreferenceNames.IS_SENS_AI_ENABLED, Boolean.valueOf(z));
    }

    public static void saveSensAIFTUShown(Context context, boolean z) {
        savePreference(context.getApplicationContext(), PreferenceNames.IS_SENS_AI_FTU_SHOWN, Boolean.valueOf(z));
    }

    public static void saveSpO2FTU(Context context, boolean z) {
        savePreference(context.getApplicationContext(), PreferenceNames.SPO2_FTU, Boolean.valueOf(z));
    }

    public static void saveTemperatureDisclaimerDoNotShowAgain(Context context, boolean z) {
        savePreference(context.getApplicationContext(), PreferenceNames.TEMPERATURE_DISCLAIMER_DO_NOT_SHOW_ON, Boolean.valueOf(z));
    }

    public static void saveTemperatureDisclaimerShown(Context context, boolean z) {
        savePreference(context.getApplicationContext(), PreferenceNames.IS_TEMPERATURE_DISCLAIMER_SHOWN, Boolean.valueOf(z));
    }

    public static void saveWalkStrideLengthManuallyEdited(Context context, boolean z) {
        savePreference(context.getApplicationContext(), PreferenceNames.IS_WALK_STRIDE_LENGTH_MANUALLY_EDITED, Boolean.valueOf(z));
    }

    public static void setLastSensAIServerSync(Context context, String str) {
        savePreference(context.getApplicationContext(), PreferenceNames.LAST_SENS_AI_SERVER_SYNC, str);
    }

    public static <T> T getPreference(Context context, PreferenceType preferenceType, String str, T t) {
        String fileName;
        if (preferenceType != null) {
            try {
                fileName = preferenceType.getFileName();
            } catch (Exception e) {
                LogHelper.d("TAG", "Exception : " + e.toString());
            }
        } else {
            fileName = str;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, 0);
        if (t instanceof String) {
            return (T) sharedPreferences.getString(str, t.toString());
        }
        if (t instanceof Integer) {
            return (T) Integer.valueOf(sharedPreferences.getInt(str, Integer.valueOf(PayUtils.parseInt(t.toString(), -1)).intValue()));
        }
        if (t instanceof Long) {
            return (T) Long.valueOf(sharedPreferences.getLong(str, Long.valueOf(PayUtils.parseLong(t.toString(), -1L)).longValue()));
        }
        if (t instanceof Boolean) {
            return (T) Boolean.valueOf(sharedPreferences.getBoolean(str, PayUtils.parseBoolean(t.toString(), null).booleanValue()));
        }
        return t;
    }
}
