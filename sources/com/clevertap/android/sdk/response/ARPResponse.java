package com.clevertap.android.sdk.response;

import android.content.Context;
import android.content.SharedPreferences;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.ControllerManager;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.StorageHelper;
import com.clevertap.android.sdk.network.NetworkManager;
import com.clevertap.android.sdk.product_config.CTProductConfigController;
import com.clevertap.android.sdk.validation.Validator;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class ARPResponse extends a {

    /* renamed from: a  reason: collision with root package name */
    public final CTProductConfigController f2670a;
    public final CleverTapResponse b;
    public final CleverTapInstanceConfig c;
    public final Logger d;
    public final NetworkManager e;
    public final Validator f;

    public ARPResponse(CleverTapResponse cleverTapResponse, CleverTapInstanceConfig cleverTapInstanceConfig, NetworkManager networkManager, Validator validator, ControllerManager controllerManager) {
        this.b = cleverTapResponse;
        this.c = cleverTapInstanceConfig;
        this.f2670a = controllerManager.getCTProductConfigController();
        this.d = cleverTapInstanceConfig.getLogger();
        this.e = networkManager;
        this.f = validator;
    }

    public final void a(Context context, JSONObject jSONObject) {
        String newNamespaceARPKey;
        if (jSONObject == null || jSONObject.length() == 0 || (newNamespaceARPKey = this.e.getNewNamespaceARPKey()) == null) {
            return;
        }
        SharedPreferences.Editor edit = StorageHelper.getPreferences(context, newNamespaceARPKey).edit();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            try {
                Object obj = jSONObject.get(next);
                if (obj instanceof Number) {
                    edit.putInt(next, ((Number) obj).intValue());
                } else if (obj instanceof String) {
                    if (((String) obj).length() < 100) {
                        edit.putString(next, (String) obj);
                    } else {
                        Logger logger = this.d;
                        String accountId = this.c.getAccountId();
                        logger.verbose(accountId, "ARP update for key " + next + " rejected (string value too long)");
                    }
                } else if (obj instanceof Boolean) {
                    edit.putBoolean(next, ((Boolean) obj).booleanValue());
                } else {
                    Logger logger2 = this.d;
                    String accountId2 = this.c.getAccountId();
                    logger2.verbose(accountId2, "ARP update for key " + next + " rejected (invalid data type)");
                }
            } catch (JSONException unused) {
            }
        }
        Logger logger3 = this.d;
        String accountId3 = this.c.getAccountId();
        logger3.verbose(accountId3, "Stored ARP for namespace key: " + newNamespaceARPKey + " values: " + jSONObject.toString());
        StorageHelper.persist(edit);
    }

    public final void b(JSONObject jSONObject) {
        if (!jSONObject.has(Constants.DISCARDED_EVENT_JSON_KEY)) {
            this.d.verbose(this.c.getAccountId(), "ARP doesn't contain the Discarded Events key");
            return;
        }
        try {
            ArrayList<String> arrayList = new ArrayList<>();
            JSONArray jSONArray = jSONObject.getJSONArray(Constants.DISCARDED_EVENT_JSON_KEY);
            if (jSONArray != null) {
                for (int i = 0; i < jSONArray.length(); i++) {
                    arrayList.add(jSONArray.getString(i));
                }
            }
            Validator validator = this.f;
            if (validator != null) {
                validator.setDiscardedEvents(arrayList);
            } else {
                this.d.verbose(this.c.getAccountId(), "Validator object is NULL");
            }
        } catch (JSONException e) {
            Logger logger = this.d;
            String accountId = this.c.getAccountId();
            logger.verbose(accountId, "Error parsing discarded events list" + e.getLocalizedMessage());
        }
    }

    @Override // com.clevertap.android.sdk.response.a, com.clevertap.android.sdk.response.CleverTapResponse
    public void processResponse(JSONObject jSONObject, String str, Context context) {
        try {
            if (jSONObject.has("arp")) {
                JSONObject jSONObject2 = (JSONObject) jSONObject.get("arp");
                if (jSONObject2.length() > 0) {
                    CTProductConfigController cTProductConfigController = this.f2670a;
                    if (cTProductConfigController != null) {
                        cTProductConfigController.setArpValue(jSONObject2);
                    }
                    b(jSONObject2);
                    a(context, jSONObject2);
                }
            }
        } catch (Throwable th) {
            this.d.verbose(this.c.getAccountId(), "Failed to process ARP", th);
        }
        this.b.processResponse(jSONObject, str, context);
    }
}
