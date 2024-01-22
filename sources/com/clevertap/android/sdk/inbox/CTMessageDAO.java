package com.clevertap.android.sdk.inbox;

import android.text.TextUtils;
import androidx.annotation.RestrictTo;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.Logger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes2.dex */
public class CTMessageDAO {

    /* renamed from: a  reason: collision with root package name */
    public String f2633a;
    public long b;
    public long c;
    public String d;
    public JSONObject e;
    public boolean f;
    public List<String> g;
    public String h;
    public JSONObject i;

    public CTMessageDAO() {
        this.g = new ArrayList();
    }

    public static JSONObject b(JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = new JSONObject();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            if (next.startsWith(Constants.WZRK_PREFIX)) {
                jSONObject2.put(next, jSONObject.get(next));
            }
        }
        return jSONObject2;
    }

    public static CTMessageDAO c(JSONObject jSONObject, String str) {
        try {
            String string = jSONObject.has("_id") ? jSONObject.getString("_id") : null;
            long j = jSONObject.has("date") ? jSONObject.getInt("date") : System.currentTimeMillis() / 1000;
            long j2 = jSONObject.has("wzrk_ttl") ? jSONObject.getInt("wzrk_ttl") : (System.currentTimeMillis() + 86400000) / 1000;
            JSONObject jSONObject2 = jSONObject.has("msg") ? jSONObject.getJSONObject("msg") : null;
            ArrayList arrayList = new ArrayList();
            if (jSONObject2 != null) {
                JSONArray jSONArray = jSONObject2.has(Constants.KEY_TAGS) ? jSONObject2.getJSONArray(Constants.KEY_TAGS) : null;
                if (jSONArray != null) {
                    for (int i = 0; i < jSONArray.length(); i++) {
                        arrayList.add(jSONArray.getString(i));
                    }
                }
            }
            String string2 = jSONObject.has(Constants.NOTIFICATION_ID_TAG) ? jSONObject.getString(Constants.NOTIFICATION_ID_TAG) : Constants.TEST_IDENTIFIER;
            if (string2.equalsIgnoreCase(Constants.TEST_IDENTIFIER)) {
                jSONObject.put(Constants.NOTIFICATION_ID_TAG, string2);
            }
            JSONObject b = b(jSONObject);
            if (string == null) {
                return null;
            }
            return new CTMessageDAO(string, jSONObject2, false, j, j2, str, arrayList, string2, b);
        } catch (JSONException e) {
            Logger.d("Unable to parse Notification inbox message to CTMessageDao - " + e.getLocalizedMessage());
            return null;
        }
    }

    public boolean a() {
        Logger.d("CTMessageDAO:containsVideoOrAudio() called");
        CTInboxMessageContent cTInboxMessageContent = new CTInboxMessage(toJSON()).getInboxMessageContents().get(0);
        return cTInboxMessageContent.mediaIsVideo() || cTInboxMessageContent.mediaIsAudio();
    }

    public String getCampaignId() {
        return this.f2633a;
    }

    public long getDate() {
        return this.b;
    }

    public long getExpires() {
        return this.c;
    }

    public String getId() {
        return this.d;
    }

    public JSONObject getJsonData() {
        return this.e;
    }

    public String getTags() {
        return TextUtils.join(Constants.SEPARATOR_COMMA, this.g);
    }

    public String getUserId() {
        return this.h;
    }

    public JSONObject getWzrkParams() {
        return this.i;
    }

    public int isRead() {
        return this.f ? 1 : 0;
    }

    public void setCampaignId(String str) {
        this.f2633a = str;
    }

    public void setDate(long j) {
        this.b = j;
    }

    public void setExpires(long j) {
        this.c = j;
    }

    public void setId(String str) {
        this.d = str;
    }

    public void setJsonData(JSONObject jSONObject) {
        this.e = jSONObject;
    }

    public void setRead(int i) {
        this.f = i == 1;
    }

    public void setTags(String str) {
        this.g.addAll(Arrays.asList(str.split(Constants.SEPARATOR_COMMA)));
    }

    public void setUserId(String str) {
        this.h = str;
    }

    public void setWzrkParams(JSONObject jSONObject) {
        this.i = jSONObject;
    }

    public JSONObject toJSON() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", this.d);
            jSONObject.put("msg", this.e);
            jSONObject.put(Constants.KEY_IS_READ, this.f);
            jSONObject.put("date", this.b);
            jSONObject.put("wzrk_ttl", this.c);
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < this.g.size(); i++) {
                jSONArray.put(this.g.get(i));
            }
            jSONObject.put(Constants.KEY_TAGS, jSONArray);
            jSONObject.put(Constants.NOTIFICATION_ID_TAG, this.f2633a);
            jSONObject.put(Constants.KEY_WZRK_PARAMS, this.i);
            return jSONObject;
        } catch (JSONException e) {
            Logger.v("Unable to convert CTMessageDao to JSON - " + e.getLocalizedMessage());
            return jSONObject;
        }
    }

    public CTMessageDAO(String str, JSONObject jSONObject, boolean z, long j, long j2, String str2, List<String> list, String str3, JSONObject jSONObject2) {
        this.g = new ArrayList();
        this.d = str;
        this.e = jSONObject;
        this.f = z;
        this.b = j;
        this.c = j2;
        this.h = str2;
        this.g = list;
        this.f2633a = str3;
        this.i = jSONObject2;
    }
}
