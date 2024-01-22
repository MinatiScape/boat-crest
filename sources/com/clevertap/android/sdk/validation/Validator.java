package com.clevertap.android.sdk.validation;

import androidx.annotation.NonNull;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.Logger;
import com.realsil.sdk.dfu.DfuConstants;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public final class Validator {
    public static final String ADD_VALUES_OPERATION = "multiValuePropertyAddValues";
    public static final String REMOVE_VALUES_OPERATION = "multiValuePropertyRemoveValues";
    public static final String[] b = {".", ":", "$", "'", "\"", "\\"};
    public static final String[] c = {".", ":", "$", "'", "\"", "\\"};
    public static final String[] d = {"'", "\"", "\\"};
    public static final String[] e = {"Stayed", Constants.NOTIFICATION_CLICKED_EVENT_NAME, Constants.NOTIFICATION_VIEWED_EVENT_NAME, "UTM Visited", "Notification Sent", Constants.APP_LAUNCHED_EVENT, "wzrk_d", "App Uninstalled", "Notification Bounced", Constants.GEOFENCE_ENTERED_EVENT_NAME, Constants.GEOFENCE_EXITED_EVENT_NAME, Constants.SC_OUTGOING_EVENT_NAME, Constants.SC_INCOMING_EVENT_NAME, Constants.SC_END_EVENT_NAME, Constants.SC_CAMPAIGN_OPT_OUT_EVENT_NAME};

    /* renamed from: a  reason: collision with root package name */
    public ArrayList<String> f2691a;

    /* loaded from: classes2.dex */
    public enum ValidationContext {
        Profile,
        Event
    }

    /* loaded from: classes2.dex */
    public enum a {
        Name,
        Email,
        Education,
        Married,
        DOB,
        Gender,
        Phone,
        Age,
        FBID,
        GPID,
        Birthday
    }

    public final ValidationResult a(String str, JSONArray jSONArray, JSONArray jSONArray2, boolean z, ValidationResult validationResult) {
        if (jSONArray == null) {
            validationResult.setObject(null);
            return validationResult;
        } else if (jSONArray2 == null) {
            validationResult.setObject(jSONArray);
            return validationResult;
        } else {
            JSONArray jSONArray3 = new JSONArray();
            HashSet hashSet = new HashSet();
            int length = jSONArray.length();
            int length2 = jSONArray2.length();
            BitSet bitSet = z ? null : new BitSet(length + length2);
            int c2 = c(jSONArray2, hashSet, bitSet, length);
            int c3 = (z || hashSet.size() >= 100) ? 0 : c(jSONArray, hashSet, bitSet, 0);
            for (int i = c3; i < length; i++) {
                if (z) {
                    try {
                        String str2 = (String) jSONArray.get(i);
                        if (!hashSet.contains(str2)) {
                            jSONArray3.put(str2);
                        }
                    } catch (Throwable unused) {
                    }
                } else if (!bitSet.get(i)) {
                    jSONArray3.put(jSONArray.get(i));
                }
            }
            if (!z && jSONArray3.length() < 100) {
                for (int i2 = c2; i2 < length2; i2++) {
                    try {
                        if (!bitSet.get(i2 + length)) {
                            jSONArray3.put(jSONArray2.get(i2));
                        }
                    } catch (Throwable unused2) {
                    }
                }
            }
            if (c2 > 0 || c3 > 0) {
                ValidationResult create = ValidationResultFactory.create(DfuConstants.PROGRESS_START_DFU_PROCESS, 12, str, "100");
                validationResult.setErrorCode(create.getErrorCode());
                validationResult.setErrorDesc(create.getErrorDesc());
            }
            validationResult.setObject(jSONArray3);
            return validationResult;
        }
    }

    public final ArrayList<String> b() {
        return this.f2691a;
    }

    public final int c(JSONArray jSONArray, Set<String> set, BitSet bitSet, int i) {
        if (jSONArray != null) {
            for (int length = jSONArray.length() - 1; length >= 0; length--) {
                try {
                    Object obj = jSONArray.get(length);
                    String obj2 = obj != null ? obj.toString() : null;
                    if (bitSet != null) {
                        if (obj2 != null && !set.contains(obj2)) {
                            set.add(obj2);
                            if (set.size() == 100) {
                                return length;
                            }
                        }
                        bitSet.set(length + i, true);
                    } else if (obj2 != null) {
                        set.add(obj2);
                    }
                } catch (Throwable unused) {
                }
            }
            return 0;
        }
        return 0;
    }

    public ValidationResult cleanEventName(String str) {
        ValidationResult validationResult = new ValidationResult();
        String trim = str.trim();
        for (String str2 : b) {
            trim = trim.replace(str2, "");
        }
        if (trim.length() > 512) {
            trim = trim.substring(0, 511);
            ValidationResult create = ValidationResultFactory.create(510, 11, trim.trim(), "512");
            validationResult.setErrorDesc(create.getErrorDesc());
            validationResult.setErrorCode(create.getErrorCode());
        }
        validationResult.setObject(trim.trim());
        return validationResult;
    }

    public ValidationResult cleanMultiValuePropertyKey(String str) {
        ValidationResult cleanObjectKey = cleanObjectKey(str);
        String str2 = (String) cleanObjectKey.getObject();
        try {
            if (a.valueOf(str2) != null) {
                ValidationResult create = ValidationResultFactory.create(DfuConstants.PROGRESS_PENDING_ACTIVE_IMAGE, 24, str2);
                cleanObjectKey.setErrorDesc(create.getErrorDesc());
                cleanObjectKey.setErrorCode(create.getErrorCode());
                cleanObjectKey.setObject(null);
            }
        } catch (Throwable unused) {
        }
        return cleanObjectKey;
    }

    public ValidationResult cleanMultiValuePropertyValue(@NonNull String str) {
        ValidationResult validationResult = new ValidationResult();
        String lowerCase = str.trim().toLowerCase();
        for (String str2 : d) {
            lowerCase = lowerCase.replace(str2, "");
        }
        try {
            if (lowerCase.length() > 512) {
                lowerCase = lowerCase.substring(0, 511);
                ValidationResult create = ValidationResultFactory.create(DfuConstants.PROGRESS_START_DFU_PROCESS, 11, lowerCase, "512");
                validationResult.setErrorDesc(create.getErrorDesc());
                validationResult.setErrorCode(create.getErrorCode());
            }
        } catch (Exception unused) {
        }
        validationResult.setObject(lowerCase);
        return validationResult;
    }

    public ValidationResult cleanObjectKey(String str) {
        ValidationResult validationResult = new ValidationResult();
        String trim = str.trim();
        for (String str2 : c) {
            trim = trim.replace(str2, "");
        }
        if (trim.length() > 120) {
            trim = trim.substring(0, 119);
            ValidationResult create = ValidationResultFactory.create(520, 11, trim.trim(), "120");
            validationResult.setErrorDesc(create.getErrorDesc());
            validationResult.setErrorCode(create.getErrorCode());
        }
        validationResult.setObject(trim.trim());
        return validationResult;
    }

    public ValidationResult cleanObjectValue(Object obj, ValidationContext validationContext) throws IllegalArgumentException {
        String str;
        ValidationResult validationResult = new ValidationResult();
        if (!(obj instanceof Integer) && !(obj instanceof Float) && !(obj instanceof Boolean) && !(obj instanceof Double) && !(obj instanceof Long)) {
            if (!(obj instanceof String) && !(obj instanceof Character)) {
                if (obj instanceof Date) {
                    validationResult.setObject("$D_" + (((Date) obj).getTime() / 1000));
                    return validationResult;
                }
                boolean z = obj instanceof String[];
                if ((z || (obj instanceof ArrayList)) && validationContext.equals(ValidationContext.Profile)) {
                    ArrayList arrayList = obj instanceof ArrayList ? (ArrayList) obj : null;
                    String[] strArr = z ? (String[]) obj : null;
                    ArrayList arrayList2 = new ArrayList();
                    if (strArr != null) {
                        for (String str2 : strArr) {
                            try {
                                arrayList2.add(str2);
                            } catch (Exception unused) {
                            }
                        }
                    } else {
                        Iterator it = arrayList.iterator();
                        while (it.hasNext()) {
                            try {
                                arrayList2.add((String) it.next());
                            } catch (Exception unused2) {
                            }
                        }
                    }
                    String[] strArr2 = (String[]) arrayList2.toArray(new String[0]);
                    if (strArr2.length > 0 && strArr2.length <= 100) {
                        JSONArray jSONArray = new JSONArray();
                        JSONObject jSONObject = new JSONObject();
                        for (String str3 : strArr2) {
                            jSONArray.put(str3);
                        }
                        try {
                            jSONObject.put(Constants.COMMAND_SET, jSONArray);
                        } catch (JSONException unused3) {
                        }
                        validationResult.setObject(jSONObject);
                    } else {
                        ValidationResult create = ValidationResultFactory.create(DfuConstants.PROGRESS_START_DFU_PROCESS, 13, strArr2.length + "", "100");
                        validationResult.setErrorDesc(create.getErrorDesc());
                        validationResult.setErrorCode(create.getErrorCode());
                    }
                    return validationResult;
                }
                throw new IllegalArgumentException("Not a String, Boolean, Long, Integer, Float, Double, or Date");
            }
            if (obj instanceof Character) {
                str = String.valueOf(obj);
            } else {
                str = (String) obj;
            }
            String trim = str.trim();
            for (String str4 : d) {
                trim = trim.replace(str4, "");
            }
            try {
                if (trim.length() > 512) {
                    trim = trim.substring(0, 511);
                    ValidationResult create2 = ValidationResultFactory.create(DfuConstants.PROGRESS_START_DFU_PROCESS, 11, trim.trim(), "512");
                    validationResult.setErrorDesc(create2.getErrorDesc());
                    validationResult.setErrorCode(create2.getErrorCode());
                }
            } catch (Exception unused4) {
            }
            validationResult.setObject(trim.trim());
            return validationResult;
        }
        validationResult.setObject(obj);
        return validationResult;
    }

    public ValidationResult isEventDiscarded(String str) {
        ValidationResult validationResult = new ValidationResult();
        if (str == null) {
            ValidationResult create = ValidationResultFactory.create(510, 14, new String[0]);
            validationResult.setErrorCode(create.getErrorCode());
            validationResult.setErrorDesc(create.getErrorDesc());
            return validationResult;
        }
        if (b() != null) {
            Iterator<String> it = b().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                } else if (str.equalsIgnoreCase(it.next())) {
                    ValidationResult create2 = ValidationResultFactory.create(513, 17, str);
                    validationResult.setErrorCode(create2.getErrorCode());
                    validationResult.setErrorDesc(create2.getErrorDesc());
                    Logger.d(str + " s a discarded event name as per CleverTap. Dropping event at SDK level. Check discarded events in CleverTap Dashboard settings.");
                    break;
                }
            }
        }
        return validationResult;
    }

    public ValidationResult isRestrictedEventName(String str) {
        ValidationResult validationResult = new ValidationResult();
        if (str == null) {
            ValidationResult create = ValidationResultFactory.create(510, 14, new String[0]);
            validationResult.setErrorCode(create.getErrorCode());
            validationResult.setErrorDesc(create.getErrorDesc());
            return validationResult;
        }
        for (String str2 : e) {
            if (str.equalsIgnoreCase(str2)) {
                ValidationResult create2 = ValidationResultFactory.create(513, 16, str);
                validationResult.setErrorCode(create2.getErrorCode());
                validationResult.setErrorDesc(create2.getErrorDesc());
                Logger.v(create2.getErrorDesc());
                return validationResult;
            }
        }
        return validationResult;
    }

    public ValidationResult mergeMultiValuePropertyForKey(JSONArray jSONArray, JSONArray jSONArray2, String str, String str2) {
        return a(str2, jSONArray, jSONArray2, REMOVE_VALUES_OPERATION.equals(str), new ValidationResult());
    }

    public void setDiscardedEvents(ArrayList<String> arrayList) {
        this.f2691a = arrayList;
    }
}
