package com.clevertap.android.sdk.inapp;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.RestrictTo;
import com.clevertap.android.sdk.Constants;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes2.dex */
public class CTInAppNotificationButton implements Parcelable {
    public static final Parcelable.Creator<CTInAppNotificationButton> CREATOR = new a();
    public String h;
    public String i;
    public String j;
    public String k;
    public String l;
    public JSONObject m;
    public HashMap<String, String> n;
    public String o;
    public String p;
    public String q;
    public boolean r;

    /* loaded from: classes2.dex */
    public class a implements Parcelable.Creator<CTInAppNotificationButton> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public CTInAppNotificationButton createFromParcel(Parcel parcel) {
            return new CTInAppNotificationButton(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b */
        public CTInAppNotificationButton[] newArray(int i) {
            return new CTInAppNotificationButton[i];
        }
    }

    public CTInAppNotificationButton() {
    }

    public String a() {
        return this.i;
    }

    public String b() {
        return this.j;
    }

    public String c() {
        return this.k;
    }

    public String d() {
        return this.l;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String e() {
        return this.p;
    }

    public CTInAppNotificationButton f(JSONObject jSONObject) {
        JSONObject jSONObject2;
        Iterator<String> keys;
        try {
            this.m = jSONObject;
            this.o = jSONObject.has("text") ? jSONObject.getString("text") : "";
            this.p = jSONObject.has("color") ? jSONObject.getString("color") : Constants.BLUE;
            boolean has = jSONObject.has(Constants.KEY_BG);
            String str = Constants.WHITE;
            this.i = has ? jSONObject.getString(Constants.KEY_BG) : Constants.WHITE;
            if (jSONObject.has(Constants.KEY_BORDER)) {
                str = jSONObject.getString(Constants.KEY_BORDER);
            }
            this.j = str;
            this.k = jSONObject.has(Constants.KEY_RADIUS) ? jSONObject.getString(Constants.KEY_RADIUS) : "";
            JSONObject jSONObject3 = jSONObject.has(Constants.KEY_ACTIONS) ? jSONObject.getJSONObject(Constants.KEY_ACTIONS) : null;
            if (jSONObject3 != null) {
                String string = jSONObject3.has(Constants.KEY_ANDROID) ? jSONObject3.getString(Constants.KEY_ANDROID) : "";
                if (!string.isEmpty()) {
                    this.h = string;
                }
                this.q = jSONObject3.has("type") ? jSONObject3.getString("type") : "";
                this.r = jSONObject3.has(Constants.KEY_FALLBACK_NOTIFICATION_SETTINGS) ? jSONObject3.getBoolean(Constants.KEY_FALLBACK_NOTIFICATION_SETTINGS) : false;
            }
            if (g(jSONObject3) && (jSONObject2 = jSONObject3.getJSONObject(Constants.KEY_KV)) != null && (keys = jSONObject2.keys()) != null) {
                while (keys.hasNext()) {
                    String next = keys.next();
                    String string2 = jSONObject2.getString(next);
                    if (!TextUtils.isEmpty(next)) {
                        if (this.n == null) {
                            this.n = new HashMap<>();
                        }
                        this.n.put(next, string2);
                    }
                }
            }
        } catch (JSONException unused) {
            this.l = "Invalid JSON";
        }
        return this;
    }

    public final boolean g(JSONObject jSONObject) throws JSONException {
        return jSONObject != null && jSONObject.has("type") && Constants.KEY_KV.equalsIgnoreCase(jSONObject.getString("type")) && jSONObject.has(Constants.KEY_KV);
    }

    public String getActionUrl() {
        return this.h;
    }

    public HashMap<String, String> getKeyValues() {
        return this.n;
    }

    public String getText() {
        return this.o;
    }

    public String getType() {
        return this.q;
    }

    public boolean isFallbackToSettings() {
        return this.r;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.o);
        parcel.writeString(this.p);
        parcel.writeString(this.i);
        parcel.writeString(this.h);
        parcel.writeString(this.j);
        parcel.writeString(this.k);
        parcel.writeString(this.q);
        parcel.writeByte(this.r ? (byte) 1 : (byte) 0);
        if (this.m == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeString(this.m.toString());
        }
        parcel.writeString(this.l);
        parcel.writeMap(this.n);
    }

    public CTInAppNotificationButton(Parcel parcel) {
        this.o = parcel.readString();
        this.p = parcel.readString();
        this.i = parcel.readString();
        this.h = parcel.readString();
        this.j = parcel.readString();
        this.k = parcel.readString();
        this.q = parcel.readString();
        this.r = parcel.readByte() != 0;
        try {
            this.m = parcel.readByte() == 0 ? null : new JSONObject(parcel.readString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.l = parcel.readString();
        this.n = parcel.readHashMap(null);
    }
}
