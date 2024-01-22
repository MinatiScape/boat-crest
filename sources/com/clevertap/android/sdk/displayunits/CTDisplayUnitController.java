package com.clevertap.android.sdk.displayunits;

import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.displayunits.model.CleverTapDisplayUnit;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class CTDisplayUnitController {

    /* renamed from: a  reason: collision with root package name */
    public final HashMap<String, CleverTapDisplayUnit> f2607a = new HashMap<>();

    @Nullable
    public synchronized ArrayList<CleverTapDisplayUnit> getAllDisplayUnits() {
        if (!this.f2607a.isEmpty()) {
            return new ArrayList<>(this.f2607a.values());
        }
        Logger.d(Constants.FEATURE_DISPLAY_UNIT, "Failed to return Display Units, nothing found in the cache");
        return null;
    }

    @Nullable
    public synchronized CleverTapDisplayUnit getDisplayUnitForID(String str) {
        if (!TextUtils.isEmpty(str)) {
            return this.f2607a.get(str);
        }
        Logger.d(Constants.FEATURE_DISPLAY_UNIT, "Can't return Display Unit, id was null");
        return null;
    }

    public synchronized void reset() {
        this.f2607a.clear();
        Logger.d(Constants.FEATURE_DISPLAY_UNIT, "Cleared Display Units Cache");
    }

    @Nullable
    public synchronized ArrayList<CleverTapDisplayUnit> updateDisplayUnits(JSONArray jSONArray) {
        reset();
        if (jSONArray != null && jSONArray.length() > 0) {
            ArrayList<CleverTapDisplayUnit> arrayList = new ArrayList<>();
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    CleverTapDisplayUnit displayUnit = CleverTapDisplayUnit.toDisplayUnit((JSONObject) jSONArray.get(i));
                    if (TextUtils.isEmpty(displayUnit.getError())) {
                        this.f2607a.put(displayUnit.getUnitID(), displayUnit);
                        arrayList.add(displayUnit);
                    } else {
                        Logger.d(Constants.FEATURE_DISPLAY_UNIT, "Failed to convert JsonArray item at index:" + i + " to Display Unit");
                    }
                } catch (Exception e) {
                    Logger.d(Constants.FEATURE_DISPLAY_UNIT, "Failed while parsing Display Unit:" + e.getLocalizedMessage());
                    return null;
                }
            }
            return arrayList.isEmpty() ? null : arrayList;
        }
        Logger.d(Constants.FEATURE_DISPLAY_UNIT, "Null json array response can't parse Display Units ");
        return null;
    }
}
