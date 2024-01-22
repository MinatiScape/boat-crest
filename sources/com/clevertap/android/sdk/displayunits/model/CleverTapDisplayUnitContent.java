package com.clevertap.android.sdk.displayunits.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.Logger;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class CleverTapDisplayUnitContent implements Parcelable {
    public static final Parcelable.Creator<CleverTapDisplayUnitContent> CREATOR = new a();
    public String h;
    public String i;
    public String j;
    public String k;
    public String l;
    public String m;
    public String n;
    public String o;
    public String p;
    public String q;

    /* loaded from: classes2.dex */
    public class a implements Parcelable.Creator<CleverTapDisplayUnitContent> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public CleverTapDisplayUnitContent createFromParcel(Parcel parcel) {
            return new CleverTapDisplayUnitContent(parcel, null);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b */
        public CleverTapDisplayUnitContent[] newArray(int i) {
            return new CleverTapDisplayUnitContent[i];
        }
    }

    public /* synthetic */ CleverTapDisplayUnitContent(Parcel parcel, a aVar) {
        this(parcel);
    }

    public static CleverTapDisplayUnitContent a(JSONObject jSONObject) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8;
        try {
            JSONObject jSONObject2 = jSONObject.has("title") ? jSONObject.getJSONObject("title") : null;
            String str9 = "";
            if (jSONObject2 != null) {
                String string = jSONObject2.has("text") ? jSONObject2.getString("text") : "";
                str2 = jSONObject2.has("color") ? jSONObject2.getString("color") : "";
                str = string;
            } else {
                str = "";
                str2 = str;
            }
            JSONObject jSONObject3 = jSONObject.has(Constants.KEY_MESSAGE) ? jSONObject.getJSONObject(Constants.KEY_MESSAGE) : null;
            if (jSONObject3 != null) {
                String string2 = jSONObject3.has("text") ? jSONObject3.getString("text") : "";
                str4 = jSONObject3.has("color") ? jSONObject3.getString("color") : "";
                str3 = string2;
            } else {
                str3 = "";
                str4 = str3;
            }
            JSONObject jSONObject4 = jSONObject.has(Constants.KEY_ICON) ? jSONObject.getJSONObject(Constants.KEY_ICON) : null;
            if (jSONObject4 != null) {
                str5 = jSONObject4.has("url") ? jSONObject4.getString("url") : "";
            } else {
                str5 = "";
            }
            JSONObject jSONObject5 = jSONObject.has(Constants.KEY_MEDIA) ? jSONObject.getJSONObject(Constants.KEY_MEDIA) : null;
            if (jSONObject5 != null) {
                String string3 = jSONObject5.has("url") ? jSONObject5.getString("url") : "";
                String string4 = jSONObject5.has("content_type") ? jSONObject5.getString("content_type") : "";
                str8 = jSONObject5.has(Constants.KEY_POSTER_URL) ? jSONObject5.getString(Constants.KEY_POSTER_URL) : "";
                str7 = string4;
                str6 = string3;
            } else {
                str6 = "";
                str7 = str6;
                str8 = str7;
            }
            JSONObject jSONObject6 = jSONObject.has(Constants.KEY_ACTION) ? jSONObject.getJSONObject(Constants.KEY_ACTION) : null;
            if (jSONObject6 != null) {
                JSONObject jSONObject7 = jSONObject6.has("url") ? jSONObject6.getJSONObject("url") : null;
                if (jSONObject7 != null) {
                    JSONObject jSONObject8 = jSONObject7.has(Constants.KEY_ANDROID) ? jSONObject7.getJSONObject(Constants.KEY_ANDROID) : null;
                    if (jSONObject8 != null && jSONObject8.has("text")) {
                        str9 = jSONObject8.getString("text");
                    }
                }
            }
            return new CleverTapDisplayUnitContent(str, str2, str3, str4, str5, str6, str7, str8, str9, null);
        } catch (Exception e) {
            Logger.d(Constants.FEATURE_DISPLAY_UNIT, "Unable to init CleverTapDisplayUnitContent with JSON - " + e.getLocalizedMessage());
            return new CleverTapDisplayUnitContent("", "", "", "", "", "", "", "", "", "Error Creating DisplayUnit Content from JSON : " + e.getLocalizedMessage());
        }
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

    public String getError() {
        return this.j;
    }

    public String getIcon() {
        return this.k;
    }

    public String getMedia() {
        return this.l;
    }

    public String getMessage() {
        return this.m;
    }

    public String getMessageColor() {
        return this.n;
    }

    public String getPosterUrl() {
        return this.o;
    }

    public String getTitle() {
        return this.p;
    }

    public String getTitleColor() {
        return this.q;
    }

    public boolean mediaIsAudio() {
        String str = this.i;
        return (str == null || this.l == null || !str.startsWith("audio")) ? false : true;
    }

    public boolean mediaIsGIF() {
        String str = this.i;
        return (str == null || this.l == null || !str.equals("image/gif")) ? false : true;
    }

    public boolean mediaIsImage() {
        String str = this.i;
        return (str == null || this.l == null || !str.startsWith("image") || this.i.equals("image/gif")) ? false : true;
    }

    public boolean mediaIsVideo() {
        String str = this.i;
        return (str == null || this.l == null || !str.startsWith("video")) ? false : true;
    }

    @NonNull
    public String toString() {
        return "[ title:" + this.p + ", titleColor:" + this.q + " message:" + this.m + ", messageColor:" + this.n + ", media:" + this.l + ", contentType:" + this.i + ", posterUrl:" + this.o + ", actionUrl:" + this.h + ", icon:" + this.k + ", error:" + this.j + " ]";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.p);
        parcel.writeString(this.q);
        parcel.writeString(this.m);
        parcel.writeString(this.n);
        parcel.writeString(this.k);
        parcel.writeString(this.l);
        parcel.writeString(this.i);
        parcel.writeString(this.o);
        parcel.writeString(this.h);
        parcel.writeString(this.j);
    }

    public CleverTapDisplayUnitContent(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10) {
        this.p = str;
        this.q = str2;
        this.m = str3;
        this.n = str4;
        this.k = str5;
        this.l = str6;
        this.i = str7;
        this.o = str8;
        this.h = str9;
        this.j = str10;
    }

    public CleverTapDisplayUnitContent(Parcel parcel) {
        this.p = parcel.readString();
        this.q = parcel.readString();
        this.m = parcel.readString();
        this.n = parcel.readString();
        this.k = parcel.readString();
        this.l = parcel.readString();
        this.i = parcel.readString();
        this.o = parcel.readString();
        this.h = parcel.readString();
        this.j = parcel.readString();
    }
}
