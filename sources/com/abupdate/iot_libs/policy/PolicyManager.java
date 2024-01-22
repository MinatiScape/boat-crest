package com.abupdate.iot_libs.policy;

import android.content.Context;
import android.util.Pair;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.abupdate.iot_libs.constant.OtaConstants;
import com.abupdate.iot_libs.engine.g;
import com.abupdate.iot_libs.info.PolicyMapInfo;
import com.abupdate.iot_libs.info.VersionInfo;
import com.abupdate.iot_libs.inter.IParsePolicyListener;
import com.abupdate.iot_libs.utils.j;
import com.abupdate.trace.Trace;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class PolicyManager {
    public static PolicyManager INSTANCE = new PolicyManager();

    public final boolean a(String str) {
        PolicyMapInfo policyMapInfo;
        try {
            policyMapInfo = VersionInfo.getInstance().policyHashMap.get(str);
        } catch (Exception unused) {
        }
        return policyMapInfo != null && "true".equals(policyMapInfo.key_value);
    }

    public String displayPolicy() {
        StringBuilder sb = new StringBuilder();
        if (VersionInfo.getInstance().policyHashMap != null) {
            for (Map.Entry<String, PolicyMapInfo> entry : VersionInfo.getInstance().policyHashMap.entrySet()) {
                sb.append(entry.getKey());
                sb.append(":");
                sb.append(entry.getValue());
                sb.append("\n");
            }
        } else {
            sb.append("null");
        }
        return sb.toString();
    }

    public Pair<Date, Date> getForceInstallTime() {
        if (PolicyConfig.getInstance().install_force) {
            return getTimeInterval(OtaConstants.IntervalTimePolicy.type_install_force);
        }
        return null;
    }

    public Pair<Date, Date> getInstallFreeTime() {
        if (PolicyConfig.getInstance().install_free_time) {
            return getTimeInterval(OtaConstants.IntervalTimePolicy.type_install_free_time);
        }
        return null;
    }

    public Pair<Date, Date> getTimeInterval(OtaConstants.IntervalTimePolicy intervalTimePolicy) {
        PolicyMapInfo policyMapInfo = VersionInfo.getInstance().policyHashMap.get(intervalTimePolicy.getType());
        if (policyMapInfo != null) {
            try {
                JSONObject jSONObject = new JSONObject(policyMapInfo.key_value);
                String string = jSONObject.getString("from");
                String string2 = jSONObject.getString(TypedValues.TransitionType.S_TO);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                Date parse = simpleDateFormat.parse(string);
                Date parse2 = simpleDateFormat.parse(string2);
                if (parse.getTime() != parse2.getTime()) {
                    return new Pair<>(parse, parse2);
                }
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public int get_check_cycle() {
        if (PolicyConfig.getInstance().check_cycle) {
            try {
                PolicyMapInfo policyMapInfo = VersionInfo.getInstance().policyHashMap.get(OtaConstants.KEY_CHECK_CYCLE);
                if (policyMapInfo != null) {
                    try {
                        int parseInt = Integer.parseInt(policyMapInfo.key_value);
                        if (parseInt > 60) {
                            return parseInt;
                        }
                        return 60;
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        return -2;
                    }
                }
            } catch (Exception unused) {
            }
            return -1;
        }
        return -1;
    }

    public int get_remind_cycle() {
        if (PolicyConfig.getInstance().remind_cycle) {
            try {
                PolicyMapInfo policyMapInfo = VersionInfo.getInstance().policyHashMap.get(OtaConstants.KEY_REMIND_CYCLE);
                if (policyMapInfo != null) {
                    try {
                        int parseInt = Integer.parseInt(policyMapInfo.key_value);
                        if (parseInt > 60) {
                            return parseInt;
                        }
                        return 60;
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        return -2;
                    }
                }
            } catch (Exception unused) {
            }
            return -1;
        }
        return -1;
    }

    public String get_storage_path() {
        PolicyMapInfo policyMapInfo;
        if (PolicyConfig.getInstance().storage_path && (policyMapInfo = VersionInfo.getInstance().policyHashMap.get(OtaConstants.KEY_DOWNLOAD_STORAGE_PATH)) != null) {
            return policyMapInfo.key_value;
        }
        return null;
    }

    public boolean isDownloadForce() {
        if (PolicyConfig.getInstance().download_force) {
            IParsePolicyListener iParsePolicyListener = PolicyConfig.getInstance().parsePolicyListenerMap.get(OtaConstants.KEY_DOWNLOAD_FORCE);
            if (iParsePolicyListener != null) {
                return iParsePolicyListener.doParse();
            }
            return a(OtaConstants.KEY_DOWNLOAD_FORCE);
        }
        return false;
    }

    public boolean isGetToInstallFreeTime() {
        if (PolicyConfig.getInstance().install_free_time) {
            IParsePolicyListener iParsePolicyListener = PolicyConfig.getInstance().parsePolicyListenerMap.get(OtaConstants.KEY_INSTALL_FREE_TIME);
            if (iParsePolicyListener != null) {
                return iParsePolicyListener.doParse();
            }
            Pair<Date, Date> installFreeTime = getInstallFreeTime();
            if (installFreeTime != null) {
                if (j.a(g.a().b().get(11) + ":" + g.a().b().get(12), (Date) installFreeTime.first, (Date) installFreeTime.second)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public boolean isRebootUpdateForce() {
        if (PolicyConfig.getInstance().reboot_update_force) {
            IParsePolicyListener iParsePolicyListener = PolicyConfig.getInstance().parsePolicyListenerMap.get(OtaConstants.KEY_REBOOT_UPDATE_FORCE);
            if (iParsePolicyListener != null) {
                return iParsePolicyListener.doParse();
            }
            return a(OtaConstants.KEY_REBOOT_UPDATE_FORCE);
        }
        return false;
    }

    public boolean is_battery_enough(Context context) {
        if (PolicyConfig.getInstance().battery) {
            IParsePolicyListener iParsePolicyListener = PolicyConfig.getInstance().parsePolicyListenerMap.get(OtaConstants.KEY_INSTALL_BATTERY);
            if (iParsePolicyListener != null) {
                return iParsePolicyListener.doParse();
            }
            PolicyMapInfo policyMapInfo = VersionInfo.getInstance().policyHashMap.get(OtaConstants.KEY_INSTALL_BATTERY);
            if (policyMapInfo != null) {
                int a2 = g.a().a(context);
                Trace.d("PolicyManager", "batteryLevel mobile = " + a2 + "  config = " + policyMapInfo.key_value);
                try {
                    return a2 >= Integer.parseInt(policyMapInfo.key_value);
                } catch (NumberFormatException | Exception unused) {
                }
            }
            return true;
        }
        return true;
    }

    public boolean is_force_install() {
        if (PolicyConfig.getInstance().install_force) {
            IParsePolicyListener iParsePolicyListener = PolicyConfig.getInstance().parsePolicyListenerMap.get(OtaConstants.KEY_INSTALL_FORCE);
            if (iParsePolicyListener != null) {
                return iParsePolicyListener.doParse();
            }
            Pair<Date, Date> forceInstallTime = getForceInstallTime();
            if (forceInstallTime != null) {
                if (j.a(g.a().b().get(11) + ":" + g.a().b().get(12), (Date) forceInstallTime.first, (Date) forceInstallTime.second)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public boolean is_request_wifi() {
        if (PolicyConfig.getInstance().wifi) {
            IParsePolicyListener iParsePolicyListener = PolicyConfig.getInstance().parsePolicyListenerMap.get(OtaConstants.KEY_DOWNLOAD_WIFI);
            if (iParsePolicyListener != null) {
                return iParsePolicyListener.doParse();
            }
            PolicyMapInfo policyMapInfo = VersionInfo.getInstance().policyHashMap.get(OtaConstants.KEY_DOWNLOAD_WIFI);
            if (policyMapInfo != null) {
                if ("required".equals(policyMapInfo.key_value)) {
                    return true;
                }
                Trace.d("PolicyManager", "is_request_wifi()");
            }
            return false;
        }
        return false;
    }

    public boolean is_storage_space_enough(String str) {
        if (PolicyConfig.getInstance().storage_size) {
            IParsePolicyListener iParsePolicyListener = PolicyConfig.getInstance().parsePolicyListenerMap.get(OtaConstants.KEY_DOWNLOAD_STORAGE_SIZE);
            if (iParsePolicyListener != null) {
                return iParsePolicyListener.doParse();
            }
            PolicyMapInfo policyMapInfo = VersionInfo.getInstance().policyHashMap.get(OtaConstants.KEY_DOWNLOAD_STORAGE_SIZE);
            if (policyMapInfo != null) {
                try {
                    long a2 = g.a().a(str);
                    Trace.i("PolicyManager", String.format("is_storage_space_enough() need_size = %s,free_size = %s,path = %s", policyMapInfo.key_value, Long.valueOf(a2), str));
                    return Long.parseLong(policyMapInfo.key_value) <= a2;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return true;
        }
        return true;
    }
}
