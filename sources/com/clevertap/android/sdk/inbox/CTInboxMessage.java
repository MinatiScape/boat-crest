package com.clevertap.android.sdk.inbox;

import android.os.Parcel;
import android.os.Parcelable;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.Logger;
import com.jstyle.blesdk1860.constant.BleConst;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class CTInboxMessage implements Parcelable {
    public static final Parcelable.Creator<CTInboxMessage> CREATOR = new a();
    public String h;
    public String i;
    public String j;
    public String k;
    public JSONObject l;
    public JSONObject m;
    public long n;
    public long o;
    public String p;
    public ArrayList<CTInboxMessageContent> q;
    public boolean r;
    public String s;
    public String t;
    public List<String> u;
    public String v;
    public CTInboxMessageType w;
    public JSONObject x;

    /* loaded from: classes2.dex */
    public class a implements Parcelable.Creator<CTInboxMessage> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public CTInboxMessage createFromParcel(Parcel parcel) {
            return new CTInboxMessage(parcel, null);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b */
        public CTInboxMessage[] newArray(int i) {
            return new CTInboxMessage[i];
        }
    }

    public /* synthetic */ CTInboxMessage(Parcel parcel, a aVar) {
        this(parcel);
    }

    public static Parcelable.Creator<CTInboxMessage> getCREATOR() {
        return CREATOR;
    }

    public void a(boolean z) {
        this.r = z;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getActionUrl() {
        return this.h;
    }

    public String getBgColor() {
        return this.i;
    }

    public String getBody() {
        return this.j;
    }

    public String getCampaignId() {
        return this.k;
    }

    public ArrayList<String> getCarouselImages() {
        ArrayList<String> arrayList = new ArrayList<>();
        Iterator<CTInboxMessageContent> it = getInboxMessageContents().iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getMedia());
        }
        return arrayList;
    }

    public JSONObject getCustomData() {
        return this.l;
    }

    public JSONObject getData() {
        return this.m;
    }

    public long getDate() {
        return this.n;
    }

    public long getExpires() {
        return this.o;
    }

    public String getImageUrl() {
        return this.p;
    }

    public ArrayList<CTInboxMessageContent> getInboxMessageContents() {
        return this.q;
    }

    public String getMessageId() {
        return this.s;
    }

    public String getOrientation() {
        return this.t;
    }

    public List<String> getTags() {
        return this.u;
    }

    public String getTitle() {
        return this.v;
    }

    public CTInboxMessageType getType() {
        return this.w;
    }

    public JSONObject getWzrkParams() {
        JSONObject jSONObject = this.x;
        return jSONObject == null ? new JSONObject() : jSONObject;
    }

    public boolean isRead() {
        return this.r;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.v);
        parcel.writeString(this.j);
        parcel.writeString(this.p);
        parcel.writeString(this.h);
        parcel.writeLong(this.n);
        parcel.writeLong(this.o);
        parcel.writeString(this.s);
        if (this.m == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeString(this.m.toString());
        }
        if (this.l == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeString(this.l.toString());
        }
        parcel.writeByte(this.r ? (byte) 1 : (byte) 0);
        parcel.writeValue(this.w);
        if (this.u == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeList(this.u);
        }
        parcel.writeString(this.i);
        if (this.q == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeList(this.q);
        }
        parcel.writeString(this.t);
        parcel.writeString(this.k);
        if (this.x == null) {
            parcel.writeByte((byte) 0);
            return;
        }
        parcel.writeByte((byte) 1);
        parcel.writeString(this.x.toString());
    }

    public CTInboxMessage(JSONObject jSONObject) {
        this.l = new JSONObject();
        this.q = new ArrayList<>();
        this.u = new ArrayList();
        this.m = jSONObject;
        try {
            this.s = jSONObject.has("id") ? jSONObject.getString("id") : BleConst.GetDeviceTime;
            this.k = jSONObject.has(Constants.NOTIFICATION_ID_TAG) ? jSONObject.getString(Constants.NOTIFICATION_ID_TAG) : Constants.TEST_IDENTIFIER;
            this.n = jSONObject.has("date") ? jSONObject.getLong("date") : System.currentTimeMillis() / 1000;
            this.o = jSONObject.has("wzrk_ttl") ? jSONObject.getLong("wzrk_ttl") : System.currentTimeMillis() + 86400000;
            this.r = jSONObject.has(Constants.KEY_IS_READ) && jSONObject.getBoolean(Constants.KEY_IS_READ);
            JSONArray jSONArray = jSONObject.has(Constants.KEY_TAGS) ? jSONObject.getJSONArray(Constants.KEY_TAGS) : null;
            if (jSONArray != null) {
                for (int i = 0; i < jSONArray.length(); i++) {
                    this.u.add(jSONArray.getString(i));
                }
            }
            JSONObject jSONObject2 = jSONObject.has("msg") ? jSONObject.getJSONObject("msg") : null;
            if (jSONObject2 != null) {
                this.w = jSONObject2.has("type") ? CTInboxMessageType.fromString(jSONObject2.getString("type")) : CTInboxMessageType.fromString("");
                this.i = jSONObject2.has(Constants.KEY_BG) ? jSONObject2.getString(Constants.KEY_BG) : "";
                JSONArray jSONArray2 = jSONObject2.has("content") ? jSONObject2.getJSONArray("content") : null;
                if (jSONArray2 != null) {
                    for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                        this.q.add(new CTInboxMessageContent().a(jSONArray2.getJSONObject(i2)));
                    }
                }
                JSONArray jSONArray3 = jSONObject2.has(Constants.KEY_CUSTOM_KV) ? jSONObject2.getJSONArray(Constants.KEY_CUSTOM_KV) : null;
                if (jSONArray3 != null) {
                    for (int i3 = 0; i3 < jSONArray3.length(); i3++) {
                        JSONObject jSONObject3 = jSONArray3.getJSONObject(i3);
                        if (jSONObject3.has(Constants.KEY_KEY)) {
                            String string = jSONObject3.getString(Constants.KEY_KEY);
                            if (jSONObject3.has("value")) {
                                this.l.put(string, jSONObject3.getJSONObject("value").getString("text"));
                            }
                        }
                    }
                }
                this.t = jSONObject2.has(Constants.KEY_ORIENTATION) ? jSONObject2.getString(Constants.KEY_ORIENTATION) : "";
            }
            this.x = jSONObject.has(Constants.KEY_WZRK_PARAMS) ? jSONObject.getJSONObject(Constants.KEY_WZRK_PARAMS) : null;
        } catch (JSONException e) {
            Logger.v("Unable to init CTInboxMessage with JSON - " + e.getLocalizedMessage());
        }
    }

    public CTInboxMessage(Parcel parcel) {
        this.l = new JSONObject();
        this.q = new ArrayList<>();
        this.u = new ArrayList();
        try {
            this.v = parcel.readString();
            this.j = parcel.readString();
            this.p = parcel.readString();
            this.h = parcel.readString();
            this.n = parcel.readLong();
            this.o = parcel.readLong();
            this.s = parcel.readString();
            JSONObject jSONObject = null;
            this.m = parcel.readByte() == 0 ? null : new JSONObject(parcel.readString());
            this.l = parcel.readByte() == 0 ? null : new JSONObject(parcel.readString());
            this.r = parcel.readByte() != 0;
            this.w = (CTInboxMessageType) parcel.readValue(CTInboxMessageType.class.getClassLoader());
            if (parcel.readByte() == 1) {
                List arrayList = new ArrayList();
                this.u = arrayList;
                parcel.readList(arrayList, String.class.getClassLoader());
            } else {
                this.u = null;
            }
            this.i = parcel.readString();
            if (parcel.readByte() == 1) {
                ArrayList<CTInboxMessageContent> arrayList2 = new ArrayList<>();
                this.q = arrayList2;
                parcel.readList(arrayList2, CTInboxMessageContent.class.getClassLoader());
            } else {
                this.q = null;
            }
            this.t = parcel.readString();
            this.k = parcel.readString();
            if (parcel.readByte() != 0) {
                jSONObject = new JSONObject(parcel.readString());
            }
            this.x = jSONObject;
        } catch (JSONException e) {
            Logger.v("Unable to parse CTInboxMessage from parcel - " + e.getLocalizedMessage());
        }
    }
}
