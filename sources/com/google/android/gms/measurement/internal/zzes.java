package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.WorkerThread;
import com.clevertap.android.sdk.product_config.CTProductConfigConstants;
import com.google.android.gms.common.internal.Preconditions;
import org.jose4j.jwk.RsaJsonWebKey;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes10.dex */
public final class zzes {

    /* renamed from: a  reason: collision with root package name */
    public final String f10148a;
    public final Bundle b;
    public Bundle c;
    public final /* synthetic */ v d;

    public zzes(v vVar, String str, Bundle bundle) {
        this.d = vVar;
        Preconditions.checkNotEmpty("default_event_parameters");
        this.f10148a = "default_event_parameters";
        this.b = new Bundle();
    }

    @WorkerThread
    public final Bundle zza() {
        char c;
        if (this.c == null) {
            String string = this.d.b().getString(this.f10148a, null);
            if (string != null) {
                try {
                    Bundle bundle = new Bundle();
                    JSONArray jSONArray = new JSONArray(string);
                    for (int i = 0; i < jSONArray.length(); i++) {
                        try {
                            JSONObject jSONObject = jSONArray.getJSONObject(i);
                            String string2 = jSONObject.getString("n");
                            String string3 = jSONObject.getString(RsaJsonWebKey.FACTOR_CRT_COEFFICIENT);
                            int hashCode = string3.hashCode();
                            if (hashCode == 100) {
                                if (string3.equals("d")) {
                                    c = 1;
                                }
                                c = 65535;
                            } else if (hashCode != 108) {
                                if (hashCode == 115 && string3.equals("s")) {
                                    c = 0;
                                }
                                c = 65535;
                            } else {
                                if (string3.equals("l")) {
                                    c = 2;
                                }
                                c = 65535;
                            }
                            if (c == 0) {
                                bundle.putString(string2, jSONObject.getString(CTProductConfigConstants.PRODUCT_CONFIG_JSON_KEY_FOR_VALUE));
                            } else if (c == 1) {
                                bundle.putDouble(string2, Double.parseDouble(jSONObject.getString(CTProductConfigConstants.PRODUCT_CONFIG_JSON_KEY_FOR_VALUE)));
                            } else if (c != 2) {
                                this.d.zzs.zzay().zzd().zzb("Unrecognized persisted bundle type. Type", string3);
                            } else {
                                bundle.putLong(string2, Long.parseLong(jSONObject.getString(CTProductConfigConstants.PRODUCT_CONFIG_JSON_KEY_FOR_VALUE)));
                            }
                        } catch (NumberFormatException | JSONException unused) {
                            this.d.zzs.zzay().zzd().zza("Error reading value from SharedPreferences. Value dropped");
                        }
                    }
                    this.c = bundle;
                } catch (JSONException unused2) {
                    this.d.zzs.zzay().zzd().zza("Error loading bundle from SharedPreferences. Values will be lost");
                }
            }
            if (this.c == null) {
                this.c = this.b;
            }
        }
        return this.c;
    }

    @WorkerThread
    public final void zzb(Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        SharedPreferences.Editor edit = this.d.b().edit();
        if (bundle.size() == 0) {
            edit.remove(this.f10148a);
        } else {
            String str = this.f10148a;
            JSONArray jSONArray = new JSONArray();
            for (String str2 : bundle.keySet()) {
                Object obj = bundle.get(str2);
                if (obj != null) {
                    try {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("n", str2);
                        jSONObject.put(CTProductConfigConstants.PRODUCT_CONFIG_JSON_KEY_FOR_VALUE, String.valueOf(obj));
                        if (obj instanceof String) {
                            jSONObject.put(RsaJsonWebKey.FACTOR_CRT_COEFFICIENT, "s");
                        } else if (obj instanceof Long) {
                            jSONObject.put(RsaJsonWebKey.FACTOR_CRT_COEFFICIENT, "l");
                        } else if (obj instanceof Double) {
                            jSONObject.put(RsaJsonWebKey.FACTOR_CRT_COEFFICIENT, "d");
                        } else {
                            this.d.zzs.zzay().zzd().zzb("Cannot serialize bundle value to SharedPreferences. Type", obj.getClass());
                        }
                        jSONArray.put(jSONObject);
                    } catch (JSONException e) {
                        this.d.zzs.zzay().zzd().zzb("Cannot serialize bundle value to SharedPreferences", e);
                    }
                }
            }
            edit.putString(str, jSONArray.toString());
        }
        edit.apply();
        this.c = bundle;
    }
}
