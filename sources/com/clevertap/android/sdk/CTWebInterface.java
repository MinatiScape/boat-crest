package com.clevertap.android.sdk;

import android.webkit.JavascriptInterface;
import androidx.annotation.RestrictTo;
import com.clevertap.android.sdk.inapp.CTInAppBaseFullFragment;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class CTWebInterface {

    /* renamed from: a  reason: collision with root package name */
    public final WeakReference<CleverTapAPI> f2569a;
    public CTInAppBaseFullFragment b;

    public CTWebInterface(CleverTapAPI cleverTapAPI) {
        CoreState coreState;
        WeakReference<CleverTapAPI> weakReference = new WeakReference<>(cleverTapAPI);
        this.f2569a = weakReference;
        CleverTapAPI cleverTapAPI2 = weakReference.get();
        if (cleverTapAPI2 == null || (coreState = cleverTapAPI2.getCoreState()) == null) {
            return;
        }
        coreState.getCoreMetaData().setWebInterfaceInitializedExternally(true);
    }

    @JavascriptInterface
    public void addMultiValueForKey(String str, String str2) {
        CleverTapAPI cleverTapAPI = this.f2569a.get();
        if (cleverTapAPI == null) {
            Logger.d("CleverTap Instance is null.");
        } else {
            cleverTapAPI.addMultiValueForKey(str, str2);
        }
    }

    @JavascriptInterface
    public void addMultiValuesForKey(String str, String str2) {
        CleverTapAPI cleverTapAPI = this.f2569a.get();
        if (cleverTapAPI == null) {
            Logger.d("CleverTap Instance is null.");
        } else if (str == null) {
            Logger.v("Key passed to CTWebInterface is null");
        } else if (str2 != null) {
            try {
                cleverTapAPI.addMultiValuesForKey(str, Utils.convertJSONArrayToArrayList(new JSONArray(str2)));
            } catch (JSONException e) {
                Logger.v("Unable to parse values from WebView " + e.getLocalizedMessage());
            }
        } else {
            Logger.v("values passed to CTWebInterface is null");
        }
    }

    @JavascriptInterface
    public void decrementValue(String str, double d) {
        CleverTapAPI cleverTapAPI = this.f2569a.get();
        if (cleverTapAPI == null) {
            Logger.d("CleverTap Instance is null.");
        } else {
            cleverTapAPI.decrementValue(str, Double.valueOf(d));
        }
    }

    @JavascriptInterface
    public void dismissInAppNotification() {
        if (this.f2569a.get() == null) {
            Logger.d("CleverTap Instance is null.");
            return;
        }
        CTInAppBaseFullFragment cTInAppBaseFullFragment = this.b;
        if (cTInAppBaseFullFragment != null) {
            cTInAppBaseFullFragment.didDismiss(null);
        }
    }

    @JavascriptInterface
    public void incrementValue(String str, double d) {
        CleverTapAPI cleverTapAPI = this.f2569a.get();
        if (cleverTapAPI == null) {
            Logger.d("CleverTap Instance is null.");
        } else {
            cleverTapAPI.incrementValue(str, Double.valueOf(d));
        }
    }

    @JavascriptInterface
    public void onUserLogin(String str) {
        CleverTapAPI cleverTapAPI = this.f2569a.get();
        if (cleverTapAPI == null) {
            Logger.d("CleverTap Instance is null.");
        } else if (str != null) {
            try {
                cleverTapAPI.onUserLogin(Utils.convertJSONObjectToHashMap(new JSONObject(str)));
            } catch (JSONException e) {
                Logger.v("Unable to parse profile from WebView " + e.getLocalizedMessage());
            }
        } else {
            Logger.v("profile passed to CTWebInterface is null");
        }
    }

    @JavascriptInterface
    public void promptPushPermission(boolean z) {
        CleverTapAPI cleverTapAPI = this.f2569a.get();
        if (cleverTapAPI == null) {
            Logger.d("CleverTap Instance is null.");
            return;
        }
        dismissInAppNotification();
        cleverTapAPI.promptForPushPermission(z);
    }

    @JavascriptInterface
    public void pushChargedEvent(String str, String str2) {
        CleverTapAPI cleverTapAPI = this.f2569a.get();
        if (cleverTapAPI == null) {
            Logger.d("CleverTap Instance is null.");
            return;
        }
        HashMap<String, Object> hashMap = new HashMap<>();
        if (str != null) {
            try {
                hashMap = Utils.convertJSONObjectToHashMap(new JSONObject(str));
            } catch (JSONException e) {
                Logger.v("Unable to parse chargeDetails for Charged Event from WebView " + e.getLocalizedMessage());
            }
            ArrayList<HashMap<String, Object>> arrayList = null;
            if (str2 != null) {
                try {
                    arrayList = Utils.convertJSONArrayOfJSONObjectsToArrayListOfHashMaps(new JSONArray(str2));
                } catch (JSONException e2) {
                    Logger.v("Unable to parse items for Charged Event from WebView " + e2.getLocalizedMessage());
                }
                cleverTapAPI.pushChargedEvent(hashMap, arrayList);
                return;
            }
            return;
        }
        Logger.v("chargeDetails passed to CTWebInterface is null");
    }

    @JavascriptInterface
    public void pushEvent(String str) {
        CleverTapAPI cleverTapAPI = this.f2569a.get();
        if (cleverTapAPI == null) {
            Logger.d("CleverTap Instance is null.");
        } else {
            cleverTapAPI.pushEvent(str);
        }
    }

    @JavascriptInterface
    public void pushProfile(String str) {
        CleverTapAPI cleverTapAPI = this.f2569a.get();
        if (cleverTapAPI == null) {
            Logger.d("CleverTap Instance is null.");
        } else if (str != null) {
            try {
                cleverTapAPI.pushProfile(Utils.convertJSONObjectToHashMap(new JSONObject(str)));
            } catch (JSONException e) {
                Logger.v("Unable to parse profile from WebView " + e.getLocalizedMessage());
            }
        } else {
            Logger.v("profile passed to CTWebInterface is null");
        }
    }

    @JavascriptInterface
    public void removeMultiValueForKey(String str, String str2) {
        CleverTapAPI cleverTapAPI = this.f2569a.get();
        if (cleverTapAPI == null) {
            Logger.d("CleverTap Instance is null.");
        } else if (str == null) {
            Logger.v("Key passed to CTWebInterface is null");
        } else if (str2 == null) {
            Logger.v("Value passed to CTWebInterface is null");
        } else {
            cleverTapAPI.removeMultiValueForKey(str, str2);
        }
    }

    @JavascriptInterface
    public void removeMultiValuesForKey(String str, String str2) {
        CleverTapAPI cleverTapAPI = this.f2569a.get();
        if (cleverTapAPI == null) {
            Logger.d("CleverTap Instance is null.");
        } else if (str == null) {
            Logger.v("Key passed to CTWebInterface is null");
        } else if (str2 != null) {
            try {
                cleverTapAPI.removeMultiValuesForKey(str, Utils.convertJSONArrayToArrayList(new JSONArray(str2)));
            } catch (JSONException e) {
                Logger.v("Unable to parse values from WebView " + e.getLocalizedMessage());
            }
        } else {
            Logger.v("values passed to CTWebInterface is null");
        }
    }

    @JavascriptInterface
    public void removeValueForKey(String str) {
        CleverTapAPI cleverTapAPI = this.f2569a.get();
        if (cleverTapAPI == null) {
            Logger.d("CleverTap Instance is null.");
        } else if (str == null) {
            Logger.v("Key passed to CTWebInterface is null");
        } else {
            cleverTapAPI.removeValueForKey(str);
        }
    }

    @JavascriptInterface
    public void setMultiValueForKey(String str, String str2) {
        CleverTapAPI cleverTapAPI = this.f2569a.get();
        if (cleverTapAPI == null) {
            Logger.d("CleverTap Instance is null.");
        } else if (str == null) {
            Logger.v("Key passed to CTWebInterface is null");
        } else if (str2 != null) {
            try {
                cleverTapAPI.setMultiValuesForKey(str, Utils.convertJSONArrayToArrayList(new JSONArray(str2)));
            } catch (JSONException e) {
                Logger.v("Unable to parse values from WebView " + e.getLocalizedMessage());
            }
        } else {
            Logger.v("values passed to CTWebInterface is null");
        }
    }

    @JavascriptInterface
    public void pushEvent(String str, String str2) {
        CleverTapAPI cleverTapAPI = this.f2569a.get();
        if (cleverTapAPI == null) {
            Logger.d("CleverTap Instance is null.");
        } else if (str2 != null) {
            try {
                cleverTapAPI.pushEvent(str, Utils.convertJSONObjectToHashMap(new JSONObject(str2)));
            } catch (JSONException e) {
                Logger.v("Unable to parse eventActions from WebView " + e.getLocalizedMessage());
            }
        } else {
            Logger.v("eventActions passed to CTWebInterface is null");
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public CTWebInterface(CleverTapAPI cleverTapAPI, CTInAppBaseFullFragment cTInAppBaseFullFragment) {
        this.f2569a = new WeakReference<>(cleverTapAPI);
        this.b = cTInAppBaseFullFragment;
    }
}
