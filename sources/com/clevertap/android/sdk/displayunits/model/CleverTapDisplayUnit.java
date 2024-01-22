package com.clevertap.android.sdk.displayunits.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.displayunits.CTDisplayUnitType;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class CleverTapDisplayUnit implements Parcelable {
    public static final Parcelable.Creator<CleverTapDisplayUnit> CREATOR = new a();
    public String h;
    public ArrayList<CleverTapDisplayUnitContent> i;
    public HashMap<String, String> j;
    public String k;
    public JSONObject l;
    public CTDisplayUnitType m;
    public String n;

    /* loaded from: classes2.dex */
    public class a implements Parcelable.Creator<CleverTapDisplayUnit> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public CleverTapDisplayUnit createFromParcel(Parcel parcel) {
            return new CleverTapDisplayUnit(parcel, null);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b */
        public CleverTapDisplayUnit[] newArray(int i) {
            return new CleverTapDisplayUnit[i];
        }
    }

    public /* synthetic */ CleverTapDisplayUnit(Parcel parcel, a aVar) {
        this(parcel);
    }

    @NonNull
    public static CleverTapDisplayUnit toDisplayUnit(JSONObject jSONObject) {
        try {
            String string = jSONObject.has(Constants.NOTIFICATION_ID_TAG) ? jSONObject.getString(Constants.NOTIFICATION_ID_TAG) : Constants.TEST_IDENTIFIER;
            CTDisplayUnitType type = jSONObject.has("type") ? CTDisplayUnitType.type(jSONObject.getString("type")) : null;
            String string2 = jSONObject.has(Constants.KEY_BG) ? jSONObject.getString(Constants.KEY_BG) : "";
            JSONArray jSONArray = jSONObject.has("content") ? jSONObject.getJSONArray("content") : null;
            ArrayList arrayList = new ArrayList();
            if (jSONArray != null) {
                for (int i = 0; i < jSONArray.length(); i++) {
                    CleverTapDisplayUnitContent a2 = CleverTapDisplayUnitContent.a(jSONArray.getJSONObject(i));
                    if (TextUtils.isEmpty(a2.getError())) {
                        arrayList.add(a2);
                    }
                }
            }
            return new CleverTapDisplayUnit(jSONObject, string, type, string2, arrayList, jSONObject.has(Constants.KEY_CUSTOM_KV) ? jSONObject.getJSONObject(Constants.KEY_CUSTOM_KV) : null, null);
        } catch (Exception e) {
            Logger.d(Constants.FEATURE_DISPLAY_UNIT, "Unable to init CleverTapDisplayUnit with JSON - " + e.getLocalizedMessage());
            return new CleverTapDisplayUnit(null, "", null, null, null, null, "Error Creating Display Unit from JSON : " + e.getLocalizedMessage());
        }
    }

    public HashMap<String, String> a(JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                Iterator<String> keys = jSONObject.keys();
                if (keys != null) {
                    HashMap<String, String> hashMap = null;
                    while (keys.hasNext()) {
                        String next = keys.next();
                        String string = jSONObject.getString(next);
                        if (!TextUtils.isEmpty(next)) {
                            if (hashMap == null) {
                                hashMap = new HashMap<>();
                            }
                            hashMap.put(next, string);
                        }
                    }
                    return hashMap;
                }
            } catch (Exception e) {
                Logger.d(Constants.FEATURE_DISPLAY_UNIT, "Error in getting Key Value Pairs " + e.getLocalizedMessage());
            }
        }
        return null;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getBgColor() {
        return this.h;
    }

    public ArrayList<CleverTapDisplayUnitContent> getContents() {
        return this.i;
    }

    public HashMap<String, String> getCustomExtras() {
        return this.j;
    }

    public String getError() {
        return this.k;
    }

    public JSONObject getJsonObject() {
        return this.l;
    }

    public CTDisplayUnitType getType() {
        return this.m;
    }

    public String getUnitID() {
        return this.n;
    }

    public JSONObject getWZRKFields() {
        try {
            JSONObject jSONObject = this.l;
            if (jSONObject != null) {
                Iterator<String> keys = jSONObject.keys();
                JSONObject jSONObject2 = new JSONObject();
                while (keys.hasNext()) {
                    String next = keys.next();
                    if (next.startsWith(Constants.WZRK_PREFIX)) {
                        jSONObject2.put(next, this.l.get(next));
                    }
                }
                return jSONObject2;
            }
            return null;
        } catch (Exception e) {
            Logger.d(Constants.FEATURE_DISPLAY_UNIT, "Error in getting WiZRK fields " + e.getLocalizedMessage());
            return null;
        }
    }

    @NonNull
    public String toString() {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            sb.append(" Unit id- ");
            sb.append(this.n);
            sb.append(", Type- ");
            CTDisplayUnitType cTDisplayUnitType = this.m;
            sb.append(cTDisplayUnitType != null ? cTDisplayUnitType.toString() : null);
            sb.append(", bgColor- ");
            sb.append(this.h);
            ArrayList<CleverTapDisplayUnitContent> arrayList = this.i;
            if (arrayList != null && !arrayList.isEmpty()) {
                for (int i = 0; i < this.i.size(); i++) {
                    CleverTapDisplayUnitContent cleverTapDisplayUnitContent = this.i.get(i);
                    if (cleverTapDisplayUnitContent != null) {
                        sb.append(", Content Item:");
                        sb.append(i);
                        sb.append(HexStringBuilder.DEFAULT_SEPARATOR);
                        sb.append(cleverTapDisplayUnitContent.toString());
                        sb.append("\n");
                    }
                }
            }
            if (this.j != null) {
                sb.append(", Custom KV:");
                sb.append(this.j);
            }
            sb.append(", JSON -");
            sb.append(this.l);
            sb.append(", Error-");
            sb.append(this.k);
            sb.append(" ]");
            return sb.toString();
        } catch (Exception e) {
            Logger.d(Constants.FEATURE_DISPLAY_UNIT, "Exception in toString:" + e);
            return super.toString();
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.n);
        parcel.writeValue(this.m);
        parcel.writeString(this.h);
        if (this.i == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeList(this.i);
        }
        parcel.writeMap(this.j);
        if (this.l == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeString(this.l.toString());
        }
        parcel.writeString(this.k);
    }

    public CleverTapDisplayUnit(JSONObject jSONObject, String str, CTDisplayUnitType cTDisplayUnitType, String str2, ArrayList<CleverTapDisplayUnitContent> arrayList, JSONObject jSONObject2, String str3) {
        this.l = jSONObject;
        this.n = str;
        this.m = cTDisplayUnitType;
        this.h = str2;
        this.i = arrayList;
        this.j = a(jSONObject2);
        this.k = str3;
    }

    public CleverTapDisplayUnit(Parcel parcel) {
        try {
            this.n = parcel.readString();
            this.m = (CTDisplayUnitType) parcel.readValue(CTDisplayUnitType.class.getClassLoader());
            this.h = parcel.readString();
            JSONObject jSONObject = null;
            if (parcel.readByte() == 1) {
                ArrayList<CleverTapDisplayUnitContent> arrayList = new ArrayList<>();
                this.i = arrayList;
                parcel.readList(arrayList, CleverTapDisplayUnitContent.class.getClassLoader());
            } else {
                this.i = null;
            }
            this.j = parcel.readHashMap(null);
            if (parcel.readByte() != 0) {
                jSONObject = new JSONObject(parcel.readString());
            }
            this.l = jSONObject;
            this.k = parcel.readString();
        } catch (Exception e) {
            String str = "Error Creating Display Unit from parcel : " + e.getLocalizedMessage();
            this.k = str;
            Logger.d(Constants.FEATURE_DISPLAY_UNIT, str);
        }
    }
}
