package com.abupdate.iot_libs.utils;

import android.content.Context;
import android.text.TextUtils;
import com.abupdate.iot_download_libs.segmentDownload.SegmentDownInfo;
import com.abupdate.iot_libs.info.PolicyMapInfo;
import com.abupdate.iot_libs.info.RegisterInfo;
import com.abupdate.iot_libs.info.SafeInfo;
import com.abupdate.iot_libs.info.VersionInfo;
import com.abupdate.trace.Trace;
import com.jstyle.blesdk1860.constant.BleConst;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class a {
    public static void a(String str, Context context) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("data")) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("data");
                if (jSONObject2.has(SPFTool.KEY_DEVICE_SECRET)) {
                    String string = jSONObject2.getString(SPFTool.KEY_DEVICE_SECRET);
                    RegisterInfo.getInstance().deviceSecret = string;
                    SPFTool.putString(SPFTool.KEY_DEVICE_SECRET, string);
                }
                if (jSONObject2.has("deviceId")) {
                    String string2 = jSONObject2.getString("deviceId");
                    RegisterInfo.getInstance().deviceId = string2;
                    SPFTool.putString(SPFTool.KEY_DEVICE_ID, string2);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void b(String str, String str2) {
        try {
            JSONArray jSONArray = new JSONArray(str);
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                PolicyMapInfo policyMapInfo = new PolicyMapInfo();
                if (jSONObject.has("key_name")) {
                    policyMapInfo.key_name = jSONObject.getString("key_name");
                }
                if (jSONObject.has("key_value")) {
                    policyMapInfo.key_value = jSONObject.getString("key_value");
                }
                if (jSONObject.has("key_message")) {
                    policyMapInfo.key_message = jSONObject.getString("key_message");
                }
                HashMap<String, PolicyMapInfo> hashMap = VersionInfo.getInstance().policyHashMap;
                hashMap.put(str2 + "_" + policyMapInfo.key_name, policyMapInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void a(String str) {
        String str2;
        VersionInfo.getInstance().reset();
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("data")) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("data");
                if (jSONObject2.has("version")) {
                    JSONObject jSONObject3 = new JSONObject(jSONObject2.getString("version"));
                    if (jSONObject3.has("versionName")) {
                        VersionInfo.getInstance().versionName = jSONObject3.getString("versionName");
                    }
                    if (jSONObject3.has("fileSize")) {
                        str2 = "policy";
                        VersionInfo.getInstance().fileSize = jSONObject3.getInt("fileSize");
                    } else {
                        str2 = "policy";
                    }
                    if (jSONObject3.has("deltaID")) {
                        VersionInfo.getInstance().deltaID = jSONObject3.getString("deltaID");
                    }
                    if (jSONObject3.has("md5sum")) {
                        String string = jSONObject3.getString("md5sum");
                        int length = 32 - string.length();
                        for (int i = 0; i < length; i++) {
                            string = BleConst.GetDeviceTime + string;
                        }
                        VersionInfo.getInstance().md5sum = string;
                    }
                    if (jSONObject3.has("versionAlias")) {
                        VersionInfo.getInstance().versionAlias = jSONObject3.getString("versionAlias");
                        if (TextUtils.isEmpty(VersionInfo.getInstance().versionAlias)) {
                            VersionInfo.getInstance().versionAlias = VersionInfo.getInstance().versionName;
                        }
                    }
                    if (jSONObject3.has("deltaUrl")) {
                        VersionInfo.getInstance().deltaUrl = jSONObject3.getString("deltaUrl");
                    }
                    if (jSONObject3.has("segmentMd5")) {
                        String string2 = jSONObject3.getString("segmentMd5");
                        if (string2.length() > 0 && !TextUtils.equals(string2, "null")) {
                            JSONArray jSONArray = new JSONArray(string2);
                            ArrayList arrayList = new ArrayList();
                            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                                JSONObject jSONObject4 = jSONArray.getJSONObject(i2);
                                SegmentDownInfo segmentDownInfo = new SegmentDownInfo();
                                if (jSONObject4.has("num")) {
                                    segmentDownInfo.setNum(jSONObject4.getInt("num"));
                                }
                                if (jSONObject4.has("md5")) {
                                    segmentDownInfo.setMd5(jSONObject4.getString("md5"));
                                }
                                if (jSONObject4.has("startpos")) {
                                    segmentDownInfo.setStartpos(jSONObject4.getLong("startpos"));
                                }
                                if (jSONObject4.has("endpos")) {
                                    segmentDownInfo.setEndpos(jSONObject4.getLong("endpos"));
                                }
                                arrayList.add(segmentDownInfo);
                            }
                            VersionInfo.getInstance().segmentSha = arrayList;
                        }
                    }
                } else {
                    str2 = "policy";
                }
                if (jSONObject2.has("releaseNotes")) {
                    JSONObject jSONObject5 = new JSONObject(jSONObject2.getString("releaseNotes"));
                    if (jSONObject5.has("version")) {
                        VersionInfo.getInstance().versionName = jSONObject5.getString("version");
                    }
                    if (jSONObject5.has("publishDate")) {
                        VersionInfo.getInstance().publishDate = jSONObject5.getString("publishDate");
                    }
                    if (jSONObject5.has("content")) {
                        VersionInfo.getInstance().content = jSONObject5.getString("content");
                    }
                }
                String str3 = str2;
                if (jSONObject2.has(str3)) {
                    JSONObject jSONObject6 = new JSONObject(jSONObject2.getString(str3));
                    if (jSONObject6.has("download")) {
                        b(jSONObject6.getString("download"), "download");
                    }
                    if (jSONObject6.has("install")) {
                        b(jSONObject6.getString("install"), "install");
                    }
                    if (jSONObject6.has("check")) {
                        b(jSONObject6.getString("check"), "check");
                    }
                }
                if (jSONObject2.has("safe")) {
                    JSONObject jSONObject7 = new JSONObject(jSONObject2.getString("safe"));
                    if (jSONObject7.has("isEncrypt")) {
                        SafeInfo.getInstance().isEncrypt = jSONObject7.getInt("isEncrypt");
                    }
                    if (jSONObject7.has("encKey")) {
                        SafeInfo.getInstance().encKey = jSONObject7.getString("encKey");
                    }
                }
            }
            SPFTool.putString(SPFTool.KEY_VERSION_INFO, str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void a() {
        String string = SPFTool.getString(SPFTool.KEY_VERSION_INFO, "");
        if (TextUtils.isEmpty(string)) {
            return;
        }
        Trace.d("BeanUtils", "setVersionInfoFromLocal() set version info");
        a(string);
    }
}
