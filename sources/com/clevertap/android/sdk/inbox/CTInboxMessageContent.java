package com.clevertap.android.sdk.inbox;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.RestrictTo;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.Logger;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes2.dex */
public class CTInboxMessageContent implements Parcelable {
    public static final Parcelable.Creator<CTInboxMessageContent> CREATOR = new a();
    public String h;
    public String i;
    public Boolean j;
    public Boolean k;
    public String l;
    public JSONArray m;
    public String n;
    public String o;
    public String p;
    public String q;
    public String r;
    public String s;

    /* loaded from: classes2.dex */
    public class a implements Parcelable.Creator<CTInboxMessageContent> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public CTInboxMessageContent createFromParcel(Parcel parcel) {
            return new CTInboxMessageContent(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b */
        public CTInboxMessageContent[] newArray(int i) {
            return new CTInboxMessageContent[i];
        }
    }

    public CTInboxMessageContent() {
    }

    public CTInboxMessageContent a(JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = jSONObject.has("title") ? jSONObject.getJSONObject("title") : null;
            if (jSONObject2 != null) {
                this.r = jSONObject2.has("text") ? jSONObject2.getString("text") : "";
                this.s = jSONObject2.has("color") ? jSONObject2.getString("color") : "";
            }
            JSONObject jSONObject3 = jSONObject.has(Constants.KEY_MESSAGE) ? jSONObject.getJSONObject(Constants.KEY_MESSAGE) : null;
            if (jSONObject3 != null) {
                this.o = jSONObject3.has("text") ? jSONObject3.getString("text") : "";
                this.p = jSONObject3.has("color") ? jSONObject3.getString("color") : "";
            }
            JSONObject jSONObject4 = jSONObject.has(Constants.KEY_ICON) ? jSONObject.getJSONObject(Constants.KEY_ICON) : null;
            if (jSONObject4 != null) {
                this.l = jSONObject4.has("url") ? jSONObject4.getString("url") : "";
            }
            JSONObject jSONObject5 = jSONObject.has(Constants.KEY_MEDIA) ? jSONObject.getJSONObject(Constants.KEY_MEDIA) : null;
            if (jSONObject5 != null) {
                this.n = jSONObject5.has("url") ? jSONObject5.getString("url") : "";
                this.i = jSONObject5.has("content_type") ? jSONObject5.getString("content_type") : "";
                this.q = jSONObject5.has(Constants.KEY_POSTER_URL) ? jSONObject5.getString(Constants.KEY_POSTER_URL) : "";
            }
            JSONObject jSONObject6 = jSONObject.has(Constants.KEY_ACTION) ? jSONObject.getJSONObject(Constants.KEY_ACTION) : null;
            if (jSONObject6 != null) {
                boolean z = true;
                this.k = Boolean.valueOf(jSONObject6.has(Constants.KEY_HAS_URL) && jSONObject6.getBoolean(Constants.KEY_HAS_URL));
                if (!jSONObject6.has(Constants.KEY_HAS_LINKS) || !jSONObject6.getBoolean(Constants.KEY_HAS_LINKS)) {
                    z = false;
                }
                this.j = Boolean.valueOf(z);
                JSONObject jSONObject7 = jSONObject6.has("url") ? jSONObject6.getJSONObject("url") : null;
                if (jSONObject7 != null && this.k.booleanValue()) {
                    JSONObject jSONObject8 = jSONObject7.has(Constants.KEY_ANDROID) ? jSONObject7.getJSONObject(Constants.KEY_ANDROID) : null;
                    if (jSONObject8 != null) {
                        this.h = jSONObject8.has("text") ? jSONObject8.getString("text") : "";
                    }
                }
                if (jSONObject7 != null && this.j.booleanValue()) {
                    this.m = jSONObject6.has(Constants.KEY_LINKS) ? jSONObject6.getJSONArray(Constants.KEY_LINKS) : null;
                }
            }
        } catch (JSONException e) {
            Logger.v("Unable to init CTInboxMessageContent with JSON - " + e.getLocalizedMessage());
        }
        return this;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getActionUrl() {
        return this.h;
    }

    public String getContentType() {
        return this.i;
    }

    public String getIcon() {
        return this.l;
    }

    public String getLinkBGColor(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        try {
            return jSONObject.has(Constants.KEY_BG) ? jSONObject.getString(Constants.KEY_BG) : "";
        } catch (JSONException e) {
            Logger.v("Unable to get Link Text Color with JSON - " + e.getLocalizedMessage());
            return null;
        }
    }

    public String getLinkColor(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        try {
            return jSONObject.has("color") ? jSONObject.getString("color") : "";
        } catch (JSONException e) {
            Logger.v("Unable to get Link Text Color with JSON - " + e.getLocalizedMessage());
            return null;
        }
    }

    public String getLinkCopyText(JSONObject jSONObject) {
        if (jSONObject == null) {
            return "";
        }
        try {
            JSONObject jSONObject2 = jSONObject.has("copyText") ? jSONObject.getJSONObject("copyText") : null;
            return (jSONObject2 == null || !jSONObject2.has("text")) ? "" : jSONObject2.getString("text");
        } catch (JSONException e) {
            Logger.v("Unable to get Link Text with JSON - " + e.getLocalizedMessage());
            return "";
        }
    }

    public HashMap<String, String> getLinkKeyValue(JSONObject jSONObject) {
        if (jSONObject != null && jSONObject.has(Constants.KEY_KV)) {
            try {
                JSONObject jSONObject2 = jSONObject.getJSONObject(Constants.KEY_KV);
                Iterator<String> keys = jSONObject2.keys();
                HashMap<String, String> hashMap = new HashMap<>();
                while (keys.hasNext()) {
                    String next = keys.next();
                    String string = jSONObject2.getString(next);
                    if (!TextUtils.isEmpty(next)) {
                        hashMap.put(next, string);
                    }
                }
                if (hashMap.isEmpty()) {
                    return null;
                }
                return hashMap;
            } catch (JSONException e) {
                Logger.v("Unable to get Link Key Value with JSON - " + e.getLocalizedMessage());
            }
        }
        return null;
    }

    public String getLinkText(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        try {
            return jSONObject.has("text") ? jSONObject.getString("text") : "";
        } catch (JSONException e) {
            Logger.v("Unable to get Link Text with JSON - " + e.getLocalizedMessage());
            return null;
        }
    }

    public String getLinkUrl(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        try {
            JSONObject jSONObject2 = jSONObject.has("url") ? jSONObject.getJSONObject("url") : null;
            if (jSONObject2 == null) {
                return null;
            }
            JSONObject jSONObject3 = jSONObject2.has(Constants.KEY_ANDROID) ? jSONObject2.getJSONObject(Constants.KEY_ANDROID) : null;
            return (jSONObject3 == null || !jSONObject3.has("text")) ? "" : jSONObject3.getString("text");
        } catch (JSONException e) {
            Logger.v("Unable to get Link URL with JSON - " + e.getLocalizedMessage());
            return null;
        }
    }

    public JSONArray getLinks() {
        return this.m;
    }

    public String getLinktype(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        try {
            return jSONObject.has("type") ? jSONObject.getString("type") : "";
        } catch (JSONException e) {
            Logger.v("Unable to get Link Type with JSON - " + e.getLocalizedMessage());
            return null;
        }
    }

    public String getMedia() {
        return this.n;
    }

    public String getMessage() {
        return this.o;
    }

    public String getMessageColor() {
        return this.p;
    }

    public String getPosterUrl() {
        return this.q;
    }

    public String getTitle() {
        return this.r;
    }

    public String getTitleColor() {
        return this.s;
    }

    public boolean isFallbackSettingsEnabled(JSONObject jSONObject) {
        if (jSONObject == null) {
            return false;
        }
        try {
            if (jSONObject.has(Constants.KEY_FALLBACK_NOTIFICATION_SETTINGS)) {
                return jSONObject.getBoolean(Constants.KEY_FALLBACK_NOTIFICATION_SETTINGS);
            }
            return false;
        } catch (JSONException e) {
            Logger.v("Unable to get fallback settings key with JSON - " + e.getLocalizedMessage());
            return false;
        }
    }

    public boolean mediaIsAudio() {
        String contentType = getContentType();
        return (contentType == null || this.n == null || !contentType.startsWith("audio")) ? false : true;
    }

    public boolean mediaIsGIF() {
        String contentType = getContentType();
        return (contentType == null || this.n == null || !contentType.equals("image/gif")) ? false : true;
    }

    public boolean mediaIsImage() {
        String contentType = getContentType();
        return (contentType == null || this.n == null || !contentType.startsWith("image") || contentType.equals("image/gif")) ? false : true;
    }

    public boolean mediaIsVideo() {
        String contentType = getContentType();
        return (contentType == null || this.n == null || !contentType.startsWith("video")) ? false : true;
    }

    public void setPosterUrl(String str) {
        this.q = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.r);
        parcel.writeString(this.s);
        parcel.writeString(this.o);
        parcel.writeString(this.p);
        parcel.writeString(this.n);
        parcel.writeByte(this.k.booleanValue() ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.j.booleanValue() ? (byte) 1 : (byte) 0);
        parcel.writeString(this.h);
        parcel.writeString(this.l);
        if (this.m == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeString(this.m.toString());
        }
        parcel.writeString(this.i);
        parcel.writeString(this.q);
    }

    public CTInboxMessageContent(Parcel parcel) {
        this.r = parcel.readString();
        this.s = parcel.readString();
        this.o = parcel.readString();
        this.p = parcel.readString();
        this.n = parcel.readString();
        this.k = Boolean.valueOf(parcel.readByte() != 0);
        this.j = Boolean.valueOf(parcel.readByte() != 0);
        this.h = parcel.readString();
        this.l = parcel.readString();
        try {
            this.m = parcel.readByte() == 0 ? null : new JSONArray(parcel.readString());
        } catch (JSONException e) {
            Logger.v("Unable to init CTInboxMessageContent with Parcel - " + e.getLocalizedMessage());
        }
        this.i = parcel.readString();
        this.q = parcel.readString();
    }
}
