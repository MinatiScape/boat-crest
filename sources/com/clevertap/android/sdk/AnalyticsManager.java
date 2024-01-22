package com.clevertap.android.sdk;

import android.content.Context;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.clevertap.android.sdk.displayunits.model.CleverTapDisplayUnit;
import com.clevertap.android.sdk.events.BaseEventQueueManager;
import com.clevertap.android.sdk.inapp.CTInAppNotification;
import com.clevertap.android.sdk.inbox.CTInboxMessage;
import com.clevertap.android.sdk.response.CleverTapResponseHelper;
import com.clevertap.android.sdk.response.DisplayUnitResponse;
import com.clevertap.android.sdk.response.InAppResponse;
import com.clevertap.android.sdk.response.InboxResponse;
import com.clevertap.android.sdk.task.CTExecutorFactory;
import com.clevertap.android.sdk.utils.CTJsonConverter;
import com.clevertap.android.sdk.utils.UriHelper;
import com.clevertap.android.sdk.validation.ValidationResult;
import com.clevertap.android.sdk.validation.ValidationResultFactory;
import com.clevertap.android.sdk.validation.ValidationResultStack;
import com.clevertap.android.sdk.validation.Validator;
import com.google.common.net.HttpHeaders;
import com.realsil.sdk.dfu.DfuConstants;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import org.jose4j.jwk.RsaJsonWebKey;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class AnalyticsManager extends BaseAnalyticsManager {

    /* renamed from: a  reason: collision with root package name */
    public final CTLockManager f2564a;
    public final BaseEventQueueManager c;
    public final BaseCallbackManager d;
    public final CleverTapInstanceConfig e;
    public final Context f;
    public final ControllerManager g;
    public final CoreMetaData h;
    public final DeviceInfo i;
    public final LocalDataStore j;
    public final ValidationResultStack k;
    public final Validator l;
    public i p;
    public final HashMap<String, Integer> b = new HashMap<>(8);
    public final HashMap<String, Object> m = new HashMap<>();
    public final Object n = new Object();
    public final HashMap<String, Object> o = new HashMap<>();

    /* loaded from: classes2.dex */
    public class a implements Callable<Void> {
        public final /* synthetic */ ArrayList h;
        public final /* synthetic */ String i;

        public a(ArrayList arrayList, String str) {
            this.h = arrayList;
            this.i = str;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Void call() {
            AnalyticsManager.this.h(this.h, this.i, Constants.COMMAND_ADD);
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public class b implements Callable<Void> {
        public final /* synthetic */ Bundle h;

        public b(Bundle bundle) {
            this.h = bundle;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Void call() {
            try {
                Logger.v("Received in-app via push payload: " + this.h.getString(Constants.INAPP_PREVIEW_PUSH_PAYLOAD_KEY));
                JSONObject jSONObject = new JSONObject();
                JSONArray jSONArray = new JSONArray();
                jSONObject.put(Constants.INAPP_JSON_RESPONSE_KEY, jSONArray);
                jSONArray.put(new JSONObject(this.h.getString(Constants.INAPP_PREVIEW_PUSH_PAYLOAD_KEY)));
                new InAppResponse(new CleverTapResponseHelper(), AnalyticsManager.this.e, AnalyticsManager.this.g, true).processResponse(jSONObject, null, AnalyticsManager.this.f);
            } catch (Throwable th) {
                Logger.v("Failed to display inapp notification from push notification payload", th);
            }
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public class c implements Callable<Void> {
        public final /* synthetic */ Bundle h;

        public c(Bundle bundle) {
            this.h = bundle;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Void call() {
            try {
                Logger.v("Received inbox via push payload: " + this.h.getString(Constants.INBOX_PREVIEW_PUSH_PAYLOAD_KEY));
                JSONObject jSONObject = new JSONObject();
                JSONArray jSONArray = new JSONArray();
                jSONObject.put(Constants.INBOX_JSON_RESPONSE_KEY, jSONArray);
                JSONObject jSONObject2 = new JSONObject(this.h.getString(Constants.INBOX_PREVIEW_PUSH_PAYLOAD_KEY));
                jSONObject2.put("_id", String.valueOf(System.currentTimeMillis() / 1000));
                jSONArray.put(jSONObject2);
                new InboxResponse(new CleverTapResponseHelper(), AnalyticsManager.this.e, AnalyticsManager.this.f2564a, AnalyticsManager.this.d, AnalyticsManager.this.g).processResponse(jSONObject, null, AnalyticsManager.this.f);
            } catch (Throwable th) {
                Logger.v("Failed to process inbox message from push notification payload", th);
            }
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public class d implements Callable<Void> {
        public final /* synthetic */ Map h;

        public d(Map map) {
            this.h = map;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Void call() {
            AnalyticsManager.this.i(this.h);
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public class e implements Callable<Void> {
        public final /* synthetic */ ArrayList h;
        public final /* synthetic */ String i;

        public e(ArrayList arrayList, String str) {
            this.h = arrayList;
            this.i = str;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Void call() {
            AnalyticsManager.this.h(this.h, this.i, Constants.COMMAND_REMOVE);
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public class f implements Callable<Void> {
        public final /* synthetic */ String h;

        public f(String str) {
            this.h = str;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Void call() {
            AnalyticsManager.this.j(this.h);
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public class g implements Callable<Void> {
        public final /* synthetic */ ArrayList h;
        public final /* synthetic */ String i;

        public g(ArrayList arrayList, String str) {
            this.h = arrayList;
            this.i = str;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Void call() {
            AnalyticsManager.this.h(this.h, this.i, Constants.COMMAND_SET);
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static /* synthetic */ class h {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f2565a;

        static {
            int[] iArr = new int[i.values().length];
            f2565a = iArr;
            try {
                iArr[i.DOUBLE_NUMBER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2565a[i.FLOAT_NUMBER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* loaded from: classes2.dex */
    public enum i {
        INT_NUMBER,
        FLOAT_NUMBER,
        DOUBLE_NUMBER
    }

    public AnalyticsManager(Context context, CleverTapInstanceConfig cleverTapInstanceConfig, BaseEventQueueManager baseEventQueueManager, Validator validator, ValidationResultStack validationResultStack, CoreMetaData coreMetaData, LocalDataStore localDataStore, DeviceInfo deviceInfo, BaseCallbackManager baseCallbackManager, ControllerManager controllerManager, CTLockManager cTLockManager) {
        this.f = context;
        this.e = cleverTapInstanceConfig;
        this.c = baseEventQueueManager;
        this.l = validator;
        this.k = validationResultStack;
        this.h = coreMetaData;
        this.j = localDataStore;
        this.i = deviceInfo;
        this.d = baseCallbackManager;
        this.f2564a = cTLockManager;
        this.g = controllerManager;
    }

    public Future<?> A(String str, JSONObject jSONObject) {
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("evtName", str);
            jSONObject2.put("evtData", jSONObject);
            Location location = new Location("");
            location.setLatitude(jSONObject.getDouble("triggered_lat"));
            location.setLongitude(jSONObject.getDouble("triggered_lng"));
            jSONObject.remove("triggered_lat");
            jSONObject.remove("triggered_lng");
            this.h.setLocationFromUser(location);
            return this.c.queueEvent(this.f, jSONObject2, 4);
        } catch (JSONException e2) {
            Logger logger = this.e.getLogger();
            String accountId = this.e.getAccountId();
            logger.debug(accountId, "Geofences : JSON Exception when raising GeoFence event " + str + " - " + e2.getLocalizedMessage());
            return null;
        }
    }

    public Future<?> B(String str, JSONObject jSONObject) {
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("evtName", str);
            jSONObject2.put("evtData", jSONObject);
            return this.c.queueEvent(this.f, jSONObject2, 4);
        } catch (JSONException e2) {
            Logger logger = this.e.getLogger();
            String accountId = this.e.getAccountId();
            logger.debug(accountId, "SignedCall : JSON Exception when raising Signed Call event " + str + " - " + e2.getLocalizedMessage());
            return null;
        }
    }

    public void C(JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = new JSONObject();
            if (jSONObject != null && jSONObject.length() > 0) {
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    try {
                        String next = keys.next();
                        jSONObject2.put(next, jSONObject.getString(next));
                    } catch (ClassCastException unused) {
                    }
                }
            }
            this.c.queueEvent(this.f, jSONObject2, 1);
        } catch (Throwable unused2) {
        }
    }

    public void D(String str, ArrayList<String> arrayList) {
        CTExecutorFactory.executors(this.e).postAsyncSafelyTask().execute("setMultiValuesForKey", new g(arrayList, str));
    }

    public final JSONArray a(ArrayList<String> arrayList, String str) {
        if (arrayList != null && str != null) {
            try {
                JSONArray jSONArray = new JSONArray();
                Iterator<String> it = arrayList.iterator();
                while (it.hasNext()) {
                    String next = it.next();
                    if (next == null) {
                        next = "";
                    }
                    ValidationResult cleanMultiValuePropertyValue = this.l.cleanMultiValuePropertyValue(next);
                    if (cleanMultiValuePropertyValue.getErrorCode() != 0) {
                        this.k.pushValidationResult(cleanMultiValuePropertyValue);
                    }
                    String obj = cleanMultiValuePropertyValue.getObject() != null ? cleanMultiValuePropertyValue.getObject().toString() : null;
                    if (obj != null && !obj.isEmpty()) {
                        jSONArray.put(obj);
                    }
                    d(str);
                    return null;
                }
                return jSONArray;
            } catch (Throwable th) {
                Logger logger = this.e.getLogger();
                String accountId = this.e.getAccountId();
                logger.verbose(accountId, "Error cleaning multi values for key " + str, th);
                d(str);
            }
        }
        return null;
    }

    @Override // com.clevertap.android.sdk.BaseAnalyticsManager
    public void addMultiValuesForKey(String str, ArrayList<String> arrayList) {
        CTExecutorFactory.executors(this.e).postAsyncSafelyTask().execute("addMultiValuesForKey", new a(arrayList, str));
    }

    public final JSONArray b(String str, String str2) {
        boolean equals = str2.equals(Constants.COMMAND_REMOVE);
        boolean equals2 = str2.equals(Constants.COMMAND_ADD);
        if (!equals && !equals2) {
            return new JSONArray();
        }
        Object f2 = f(str);
        if (f2 == null) {
            if (equals) {
                return null;
            }
            return new JSONArray();
        } else if (f2 instanceof JSONArray) {
            return (JSONArray) f2;
        } else {
            JSONArray jSONArray = equals2 ? new JSONArray() : null;
            String k = k(f2);
            return k != null ? new JSONArray().put(k) : jSONArray;
        }
    }

    public final void c(Number number, String str, String str2) {
        if (str == null || number == null) {
            return;
        }
        try {
            ValidationResult cleanObjectKey = this.l.cleanObjectKey(str);
            String obj = cleanObjectKey.getObject().toString();
            if (obj.isEmpty()) {
                ValidationResult create = ValidationResultFactory.create(512, 2, obj);
                this.k.pushValidationResult(create);
                this.e.getLogger().debug(this.e.getAccountId(), create.getErrorDesc());
                return;
            }
            if (number.intValue() >= 0 && number.doubleValue() >= 0.0d && number.floatValue() >= 0.0f) {
                if (cleanObjectKey.getErrorCode() != 0) {
                    this.k.pushValidationResult(cleanObjectKey);
                }
                this.j.M(obj, g(obj, number, str2));
                this.c.pushBasicProfile(new JSONObject().put(obj, new JSONObject().put(str2, number)), false);
                return;
            }
            ValidationResult create2 = ValidationResultFactory.create(512, 25, obj);
            this.k.pushValidationResult(create2);
            this.e.getLogger().debug(this.e.getAccountId(), create2.getErrorDesc());
        } catch (Throwable th) {
            Logger logger = this.e.getLogger();
            String accountId = this.e.getAccountId();
            logger.verbose(accountId, "Failed to update profile value for key " + str, th);
        }
    }

    public void d(String str) {
        ValidationResult create = ValidationResultFactory.create(512, 1, str);
        this.k.pushValidationResult(create);
        this.e.getLogger().debug(this.e.getAccountId(), create.getErrorDesc());
    }

    @Override // com.clevertap.android.sdk.BaseAnalyticsManager
    public void decrementValue(String str, Number number) {
        c(number, str, Constants.COMMAND_DECREMENT);
    }

    public final void e(String str) {
        this.k.pushValidationResult(ValidationResultFactory.create(DfuConstants.PROGRESS_PENDING_ACTIVE_IMAGE, 23, str));
        Logger logger = this.e.getLogger();
        String accountId = this.e.getAccountId();
        logger.debug(accountId, "Invalid multi-value property key " + str + " profile multi value operation aborted");
    }

    public final Object f(String str) {
        return this.j.y(str);
    }

    @Override // com.clevertap.android.sdk.BaseAnalyticsManager
    public void fetchFeatureFlags() {
        if (this.e.isAnalyticsOnly()) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put(RsaJsonWebKey.FACTOR_CRT_COEFFICIENT, 1);
            jSONObject.put("evtName", Constants.WZRK_FETCH);
            jSONObject.put("evtData", jSONObject2);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        sendFetchEvent(jSONObject);
    }

    @Override // com.clevertap.android.sdk.BaseAnalyticsManager
    public void forcePushAppLaunchedEvent() {
        this.h.g(false);
        pushAppLaunchedEvent();
    }

    public final Number g(@NonNull String str, Number number, String str2) {
        Number number2 = (Number) f(str);
        if (number2 == null) {
            int i2 = h.f2565a[v(number).ordinal()];
            if (i2 == 1) {
                if (str2.equals(Constants.COMMAND_INCREMENT)) {
                    return Double.valueOf(number.doubleValue());
                }
                if (str2.equals(Constants.COMMAND_DECREMENT)) {
                    return Double.valueOf(-number.doubleValue());
                }
                return null;
            } else if (i2 != 2) {
                if (str2.equals(Constants.COMMAND_INCREMENT)) {
                    return Integer.valueOf(number.intValue());
                }
                if (str2.equals(Constants.COMMAND_DECREMENT)) {
                    return Integer.valueOf(-number.intValue());
                }
                return null;
            } else if (str2.equals(Constants.COMMAND_INCREMENT)) {
                return Float.valueOf(number.floatValue());
            } else {
                if (str2.equals(Constants.COMMAND_DECREMENT)) {
                    return Float.valueOf(-number.floatValue());
                }
                return null;
            }
        }
        int i3 = h.f2565a[v(number2).ordinal()];
        if (i3 == 1) {
            if (str2.equals(Constants.COMMAND_INCREMENT)) {
                return Double.valueOf(number2.doubleValue() + number.doubleValue());
            }
            if (str2.equals(Constants.COMMAND_DECREMENT)) {
                return Double.valueOf(number2.doubleValue() - number.doubleValue());
            }
            return null;
        } else if (i3 != 2) {
            if (str2.equals(Constants.COMMAND_INCREMENT)) {
                return Integer.valueOf(number2.intValue() + number.intValue());
            }
            if (str2.equals(Constants.COMMAND_DECREMENT)) {
                return Integer.valueOf(number2.intValue() - number.intValue());
            }
            return null;
        } else if (str2.equals(Constants.COMMAND_INCREMENT)) {
            return Float.valueOf(number2.floatValue() + number.floatValue());
        } else {
            if (str2.equals(Constants.COMMAND_DECREMENT)) {
                return Float.valueOf(number2.floatValue() - number.floatValue());
            }
            return null;
        }
    }

    public final void h(ArrayList<String> arrayList, String str, String str2) {
        if (str == null) {
            return;
        }
        if (arrayList != null && !arrayList.isEmpty()) {
            ValidationResult cleanMultiValuePropertyKey = this.l.cleanMultiValuePropertyKey(str);
            if (cleanMultiValuePropertyKey.getErrorCode() != 0) {
                this.k.pushValidationResult(cleanMultiValuePropertyKey);
            }
            String obj = cleanMultiValuePropertyKey.getObject() != null ? cleanMultiValuePropertyKey.getObject().toString() : null;
            if (obj != null && !obj.isEmpty()) {
                try {
                    l(b(obj, str2), a(arrayList, obj), arrayList, obj, str2);
                    return;
                } catch (Throwable th) {
                    Logger logger = this.e.getLogger();
                    String accountId = this.e.getAccountId();
                    logger.verbose(accountId, "Error handling multi value operation for key " + obj, th);
                    return;
                }
            }
            e(str);
            return;
        }
        d(str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v2, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v5, types: [java.lang.Object, java.lang.String] */
    public final void i(Map<String, Object> map) {
        if (map == null || map.isEmpty()) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            for (String str : map.keySet()) {
                Object obj = map.get(str);
                ValidationResult cleanObjectKey = this.l.cleanObjectKey(str);
                String obj2 = cleanObjectKey.getObject().toString();
                if (cleanObjectKey.getErrorCode() != 0) {
                    this.k.pushValidationResult(cleanObjectKey);
                }
                if (obj2.isEmpty()) {
                    ValidationResult create = ValidationResultFactory.create(512, 2, new String[0]);
                    this.k.pushValidationResult(create);
                    this.e.getLogger().debug(this.e.getAccountId(), create.getErrorDesc());
                } else {
                    ValidationResult cleanObjectValue = this.l.cleanObjectValue(obj, Validator.ValidationContext.Profile);
                    Object object = cleanObjectValue.getObject();
                    if (cleanObjectValue.getErrorCode() != 0) {
                        this.k.pushValidationResult(cleanObjectValue);
                    }
                    if (obj2.equalsIgnoreCase("Phone")) {
                        try {
                            object = object.toString();
                            String countryCode = this.i.getCountryCode();
                            if ((countryCode == null || countryCode.isEmpty()) && !object.startsWith("+")) {
                                ValidationResult create2 = ValidationResultFactory.create(512, 4, object);
                                this.k.pushValidationResult(create2);
                                this.e.getLogger().debug(this.e.getAccountId(), create2.getErrorDesc());
                            }
                            Logger logger = this.e.getLogger();
                            String accountId = this.e.getAccountId();
                            StringBuilder sb = new StringBuilder();
                            sb.append("Profile phone is: ");
                            sb.append((Object) object);
                            sb.append(" device country code is: ");
                            if (countryCode == null) {
                                countryCode = "null";
                            }
                            sb.append(countryCode);
                            logger.verbose(accountId, sb.toString());
                        } catch (Exception e2) {
                            this.k.pushValidationResult(ValidationResultFactory.create(512, 5, new String[0]));
                            Logger logger2 = this.e.getLogger();
                            String accountId2 = this.e.getAccountId();
                            logger2.debug(accountId2, "Invalid phone number: " + e2.getLocalizedMessage());
                        }
                    }
                    jSONObject2.put(obj2, object);
                    jSONObject.put(obj2, object);
                }
            }
            Logger logger3 = this.e.getLogger();
            String accountId3 = this.e.getAccountId();
            logger3.verbose(accountId3, "Constructed custom profile: " + jSONObject.toString());
            if (jSONObject2.length() > 0) {
                this.j.O(jSONObject2);
            }
            this.c.pushBasicProfile(jSONObject, false);
        } catch (Throwable th) {
            this.e.getLogger().verbose(this.e.getAccountId(), "Failed to push profile", th);
        }
    }

    @Override // com.clevertap.android.sdk.BaseAnalyticsManager
    public void incrementValue(String str, Number number) {
        c(number, str, Constants.COMMAND_INCREMENT);
    }

    public final void j(String str) {
        if (str == null) {
            str = "";
        }
        try {
            ValidationResult cleanObjectKey = this.l.cleanObjectKey(str);
            String obj = cleanObjectKey.getObject().toString();
            if (obj.isEmpty()) {
                ValidationResult create = ValidationResultFactory.create(512, 6, new String[0]);
                this.k.pushValidationResult(create);
                this.e.getLogger().debug(this.e.getAccountId(), create.getErrorDesc());
                return;
            }
            if (cleanObjectKey.getErrorCode() != 0) {
                this.k.pushValidationResult(cleanObjectKey);
            }
            if (obj.toLowerCase().contains("identity")) {
                Logger logger = this.e.getLogger();
                String accountId = this.e.getAccountId();
                logger.verbose(accountId, "Cannot remove value for key " + obj + " from user profile");
                return;
            }
            this.j.I(obj);
            this.c.pushBasicProfile(new JSONObject().put(obj, new JSONObject().put(Constants.COMMAND_DELETE, true)), true);
            Logger logger2 = this.e.getLogger();
            String accountId2 = this.e.getAccountId();
            logger2.verbose(accountId2, "removing value for key " + obj + " from user profile");
        } catch (Throwable th) {
            Logger logger3 = this.e.getLogger();
            String accountId3 = this.e.getAccountId();
            logger3.verbose(accountId3, "Failed to remove profile value for key " + str, th);
        }
    }

    public final String k(Object obj) {
        String jsonString = CTJsonConverter.toJsonString(obj);
        if (jsonString != null) {
            ValidationResult cleanMultiValuePropertyValue = this.l.cleanMultiValuePropertyValue(jsonString);
            if (cleanMultiValuePropertyValue.getErrorCode() != 0) {
                this.k.pushValidationResult(cleanMultiValuePropertyValue);
            }
            if (cleanMultiValuePropertyValue.getObject() != null) {
                return cleanMultiValuePropertyValue.getObject().toString();
            }
            return null;
        }
        return jsonString;
    }

    public final void l(JSONArray jSONArray, JSONArray jSONArray2, ArrayList<String> arrayList, String str, String str2) {
        if (jSONArray == null || jSONArray2 == null || arrayList == null || str == null || str2 == null) {
            return;
        }
        try {
            ValidationResult mergeMultiValuePropertyForKey = this.l.mergeMultiValuePropertyForKey(jSONArray, jSONArray2, str2.equals(Constants.COMMAND_REMOVE) ? Validator.REMOVE_VALUES_OPERATION : Validator.ADD_VALUES_OPERATION, str);
            if (mergeMultiValuePropertyForKey.getErrorCode() != 0) {
                this.k.pushValidationResult(mergeMultiValuePropertyForKey);
            }
            JSONArray jSONArray3 = (JSONArray) mergeMultiValuePropertyForKey.getObject();
            if (jSONArray3 != null && jSONArray3.length() > 0) {
                this.j.M(str, jSONArray3);
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(str2, new JSONArray((Collection<?>) arrayList));
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put(str, jSONObject);
                this.c.pushBasicProfile(jSONObject2, false);
                Logger logger = this.e.getLogger();
                String accountId = this.e.getAccountId();
                logger.verbose(accountId, "Constructed multi-value profile push: " + jSONObject2.toString());
            }
            this.j.I(str);
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put(str2, new JSONArray((Collection<?>) arrayList));
            JSONObject jSONObject22 = new JSONObject();
            jSONObject22.put(str, jSONObject3);
            this.c.pushBasicProfile(jSONObject22, false);
            Logger logger2 = this.e.getLogger();
            String accountId2 = this.e.getAccountId();
            logger2.verbose(accountId2, "Constructed multi-value profile push: " + jSONObject22.toString());
        } catch (Throwable th) {
            Logger logger3 = this.e.getLogger();
            String accountId3 = this.e.getAccountId();
            logger3.verbose(accountId3, "Error pushing multiValue for key " + str, th);
        }
    }

    @Override // com.clevertap.android.sdk.BaseAnalyticsManager
    public void pushAppLaunchedEvent() {
        if (this.e.b()) {
            this.h.g(true);
            this.e.getLogger().debug(this.e.getAccountId(), "App Launched Events disabled in the Android Manifest file");
        } else if (this.h.isAppLaunchPushed()) {
            this.e.getLogger().verbose(this.e.getAccountId(), "App Launched has already been triggered. Will not trigger it ");
        } else {
            this.e.getLogger().verbose(this.e.getAccountId(), "Firing App Launched event");
            this.h.g(true);
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("evtName", Constants.APP_LAUNCHED_EVENT);
                jSONObject.put("evtData", this.i.getAppLaunchedFields());
            } catch (Throwable unused) {
            }
            this.c.queueEvent(this.f, jSONObject, 4);
        }
    }

    @Override // com.clevertap.android.sdk.BaseAnalyticsManager
    public void pushDefineVarsEvent(JSONObject jSONObject) {
        this.c.queueEvent(this.f, jSONObject, 8);
    }

    @Override // com.clevertap.android.sdk.BaseAnalyticsManager
    public void pushDisplayUnitClickedEventForID(String str) {
        CleverTapDisplayUnit displayUnitForID;
        JSONObject wZRKFields;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("evtName", Constants.NOTIFICATION_CLICKED_EVENT_NAME);
            if (this.g.getCTDisplayUnitController() != null && (displayUnitForID = this.g.getCTDisplayUnitController().getDisplayUnitForID(str)) != null && (wZRKFields = displayUnitForID.getWZRKFields()) != null) {
                jSONObject.put("evtData", wZRKFields);
                try {
                    this.h.setWzrkParams(wZRKFields);
                } catch (Throwable unused) {
                }
            }
            this.c.queueEvent(this.f, jSONObject, 4);
        } catch (Throwable th) {
            Logger logger = this.e.getLogger();
            String accountId = this.e.getAccountId();
            logger.verbose(accountId, "DisplayUnit : Failed to push Display Unit clicked event" + th);
        }
    }

    @Override // com.clevertap.android.sdk.BaseAnalyticsManager
    public void pushDisplayUnitViewedEventForID(String str) {
        CleverTapDisplayUnit displayUnitForID;
        JSONObject wZRKFields;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("evtName", Constants.NOTIFICATION_VIEWED_EVENT_NAME);
            if (this.g.getCTDisplayUnitController() != null && (displayUnitForID = this.g.getCTDisplayUnitController().getDisplayUnitForID(str)) != null && (wZRKFields = displayUnitForID.getWZRKFields()) != null) {
                jSONObject.put("evtData", wZRKFields);
            }
            this.c.queueEvent(this.f, jSONObject, 4);
        } catch (Throwable th) {
            Logger logger = this.e.getLogger();
            String accountId = this.e.getAccountId();
            logger.verbose(accountId, "DisplayUnit : Failed to push Display Unit viewed event" + th);
        }
    }

    @Override // com.clevertap.android.sdk.BaseAnalyticsManager
    public void pushError(String str, int i2) {
        HashMap hashMap = new HashMap();
        hashMap.put("Error Message", str);
        hashMap.put("Error Code", Integer.valueOf(i2));
        try {
            String currentActivityName = CoreMetaData.getCurrentActivityName();
            if (currentActivityName != null) {
                hashMap.put(HttpHeaders.LOCATION, currentActivityName);
            } else {
                hashMap.put(HttpHeaders.LOCATION, "Unknown");
            }
        } catch (Throwable unused) {
            hashMap.put(HttpHeaders.LOCATION, "Unknown");
        }
        pushEvent("Error Occurred", hashMap);
    }

    @Override // com.clevertap.android.sdk.BaseAnalyticsManager
    public void pushEvent(String str, Map<String, Object> map) {
        if (str == null || str.equals("")) {
            return;
        }
        ValidationResult isRestrictedEventName = this.l.isRestrictedEventName(str);
        if (isRestrictedEventName.getErrorCode() > 0) {
            this.k.pushValidationResult(isRestrictedEventName);
            return;
        }
        ValidationResult isEventDiscarded = this.l.isEventDiscarded(str);
        if (isEventDiscarded.getErrorCode() > 0) {
            this.k.pushValidationResult(isEventDiscarded);
            return;
        }
        if (map == null) {
            map = new HashMap<>();
        }
        JSONObject jSONObject = new JSONObject();
        try {
            ValidationResult cleanEventName = this.l.cleanEventName(str);
            if (cleanEventName.getErrorCode() != 0) {
                jSONObject.put(Constants.ERROR_KEY, CTJsonConverter.getErrorObject(cleanEventName));
            }
            String obj = cleanEventName.getObject().toString();
            JSONObject jSONObject2 = new JSONObject();
            for (String str2 : map.keySet()) {
                Object obj2 = map.get(str2);
                ValidationResult cleanObjectKey = this.l.cleanObjectKey(str2);
                String obj3 = cleanObjectKey.getObject().toString();
                if (cleanObjectKey.getErrorCode() != 0) {
                    jSONObject.put(Constants.ERROR_KEY, CTJsonConverter.getErrorObject(cleanObjectKey));
                }
                try {
                    ValidationResult cleanObjectValue = this.l.cleanObjectValue(obj2, Validator.ValidationContext.Event);
                    Object object = cleanObjectValue.getObject();
                    if (cleanObjectValue.getErrorCode() != 0) {
                        jSONObject.put(Constants.ERROR_KEY, CTJsonConverter.getErrorObject(cleanObjectValue));
                    }
                    jSONObject2.put(obj3, object);
                } catch (IllegalArgumentException unused) {
                    String[] strArr = new String[3];
                    strArr[0] = obj;
                    strArr[1] = obj3;
                    strArr[2] = obj2 != null ? obj2.toString() : "";
                    ValidationResult create = ValidationResultFactory.create(512, 7, strArr);
                    this.e.getLogger().debug(this.e.getAccountId(), create.getErrorDesc());
                    this.k.pushValidationResult(create);
                }
            }
            jSONObject.put("evtName", obj);
            jSONObject.put("evtData", jSONObject2);
            this.c.queueEvent(this.f, jSONObject, 4);
        } catch (Throwable unused2) {
        }
    }

    @Override // com.clevertap.android.sdk.BaseAnalyticsManager
    public void pushInAppNotificationStateEvent(boolean z, CTInAppNotification cTInAppNotification, Bundle bundle) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject wzrkFields = CTJsonConverter.getWzrkFields(cTInAppNotification);
            if (bundle != null) {
                for (String str : bundle.keySet()) {
                    Object obj = bundle.get(str);
                    if (obj != null) {
                        wzrkFields.put(str, obj);
                    }
                }
            }
            if (z) {
                try {
                    this.h.setWzrkParams(wzrkFields);
                } catch (Throwable unused) {
                }
                jSONObject.put("evtName", Constants.NOTIFICATION_CLICKED_EVENT_NAME);
            } else {
                jSONObject.put("evtName", Constants.NOTIFICATION_VIEWED_EVENT_NAME);
            }
            jSONObject.put("evtData", wzrkFields);
            this.c.queueEvent(this.f, jSONObject, 4);
        } catch (Throwable unused2) {
        }
    }

    @Override // com.clevertap.android.sdk.BaseAnalyticsManager
    public void pushInstallReferrer(String str) {
        try {
            Logger logger = this.e.getLogger();
            String accountId = this.e.getAccountId();
            logger.verbose(accountId, "Referrer received: " + str);
            if (str == null) {
                return;
            }
            int currentTimeMillis = (int) (System.currentTimeMillis() / 1000);
            if (this.b.containsKey(str) && currentTimeMillis - this.b.get(str).intValue() < 10) {
                this.e.getLogger().verbose(this.e.getAccountId(), "Skipping install referrer due to duplicate within 10 seconds");
                return;
            }
            this.b.put(str, Integer.valueOf(currentTimeMillis));
            y(Uri.parse("wzrk://track?install=true&" + str), true);
        } catch (Throwable unused) {
        }
    }

    @Override // com.clevertap.android.sdk.BaseAnalyticsManager
    public void pushNotificationClickedEvent(Bundle bundle) {
        if (this.e.isAnalyticsOnly()) {
            this.e.getLogger().debug(this.e.getAccountId(), "is Analytics Only - will not process Notification Clicked event.");
        } else if (bundle != null && !bundle.isEmpty() && bundle.get("wzrk_pn") != null) {
            String str = null;
            try {
                str = bundle.getString(Constants.WZRK_ACCT_ID_KEY);
            } catch (Throwable unused) {
            }
            if (!((str == null && this.e.isDefaultInstance()) || this.e.getAccountId().equals(str))) {
                this.e.getLogger().debug(this.e.getAccountId(), "Push notification not targeted at this instance, not processing Notification Clicked Event");
            } else if (bundle.containsKey(Constants.INAPP_PREVIEW_PUSH_PAYLOAD_KEY)) {
                CTExecutorFactory.executors(this.e).postAsyncSafelyTask().execute("testInappNotification", new b(bundle));
            } else if (bundle.containsKey(Constants.INBOX_PREVIEW_PUSH_PAYLOAD_KEY)) {
                CTExecutorFactory.executors(this.e).postAsyncSafelyTask().execute("testInboxNotification", new c(bundle));
            } else if (bundle.containsKey(Constants.DISPLAY_UNIT_PREVIEW_PUSH_PAYLOAD_KEY)) {
                w(bundle);
            } else if (bundle.containsKey(Constants.NOTIFICATION_ID_TAG) && bundle.getString(Constants.NOTIFICATION_ID_TAG) != null) {
                if (u(bundle, this.m, 5000)) {
                    Logger logger = this.e.getLogger();
                    String accountId = this.e.getAccountId();
                    logger.debug(accountId, "Already processed Notification Clicked event for " + bundle.toString() + ", dropping duplicate.");
                    return;
                }
                JSONObject jSONObject = new JSONObject();
                JSONObject jSONObject2 = new JSONObject();
                try {
                    for (String str2 : bundle.keySet()) {
                        if (str2.startsWith(Constants.WZRK_PREFIX)) {
                            jSONObject2.put(str2, bundle.get(str2));
                        }
                    }
                    jSONObject.put("evtName", Constants.NOTIFICATION_CLICKED_EVENT_NAME);
                    jSONObject.put("evtData", jSONObject2);
                    this.c.queueEvent(this.f, jSONObject, 4);
                    this.h.setWzrkParams(CTJsonConverter.getWzrkFields(bundle));
                } catch (Throwable unused2) {
                }
                if (this.d.getPushNotificationListener() != null) {
                    this.d.getPushNotificationListener().onNotificationClickedPayloadReceived(Utils.convertBundleObjectToHashMap(bundle));
                } else {
                    Logger.d("CTPushNotificationListener is not set");
                }
            } else {
                Logger logger2 = this.e.getLogger();
                String accountId2 = this.e.getAccountId();
                logger2.debug(accountId2, "Push notification ID Tag is null, not processing Notification Clicked event for:  " + bundle.toString());
            }
        } else {
            Logger logger3 = this.e.getLogger();
            String accountId3 = this.e.getAccountId();
            StringBuilder sb = new StringBuilder();
            sb.append("Push notification: ");
            sb.append(bundle == null ? "NULL" : bundle.toString());
            sb.append(" not from CleverTap - will not process Notification Clicked event.");
            logger3.debug(accountId3, sb.toString());
        }
    }

    @Override // com.clevertap.android.sdk.BaseAnalyticsManager
    public void pushNotificationViewedEvent(Bundle bundle) {
        if (bundle != null && !bundle.isEmpty() && bundle.get("wzrk_pn") != null) {
            if (bundle.containsKey(Constants.NOTIFICATION_ID_TAG) && bundle.getString(Constants.NOTIFICATION_ID_TAG) != null) {
                if (u(bundle, this.o, 2000)) {
                    Logger logger = this.e.getLogger();
                    String accountId = this.e.getAccountId();
                    logger.debug(accountId, "Already processed Notification Viewed event for " + bundle.toString() + ", dropping duplicate.");
                    return;
                }
                Logger logger2 = this.e.getLogger();
                logger2.debug("Recording Notification Viewed event for notification:  " + bundle.toString());
                JSONObject jSONObject = new JSONObject();
                try {
                    JSONObject wzrkFields = CTJsonConverter.getWzrkFields(bundle);
                    jSONObject.put("evtName", Constants.NOTIFICATION_VIEWED_EVENT_NAME);
                    jSONObject.put("evtData", wzrkFields);
                } catch (Throwable unused) {
                }
                this.c.queueEvent(this.f, jSONObject, 6);
                return;
            }
            Logger logger3 = this.e.getLogger();
            String accountId2 = this.e.getAccountId();
            logger3.debug(accountId2, "Push notification ID Tag is null, not processing Notification Viewed event for:  " + bundle.toString());
            return;
        }
        Logger logger4 = this.e.getLogger();
        String accountId3 = this.e.getAccountId();
        StringBuilder sb = new StringBuilder();
        sb.append("Push notification: ");
        sb.append(bundle == null ? "NULL" : bundle.toString());
        sb.append(" not from CleverTap - will not process Notification Viewed event.");
        logger4.debug(accountId3, sb.toString());
    }

    @Override // com.clevertap.android.sdk.BaseAnalyticsManager
    public void pushProfile(Map<String, Object> map) {
        if (map == null || map.isEmpty()) {
            return;
        }
        CTExecutorFactory.executors(this.e).postAsyncSafelyTask().execute("profilePush", new d(map));
    }

    @Override // com.clevertap.android.sdk.BaseAnalyticsManager
    public void removeMultiValuesForKey(String str, ArrayList<String> arrayList) {
        CTExecutorFactory.executors(this.e).postAsyncSafelyTask().execute("removeMultiValuesForKey", new e(arrayList, str));
    }

    @Override // com.clevertap.android.sdk.BaseAnalyticsManager
    public void removeValueForKey(String str) {
        CTExecutorFactory.executors(this.e).postAsyncSafelyTask().execute("removeValueForKey", new f(str));
    }

    @Override // com.clevertap.android.sdk.BaseAnalyticsManager
    public void sendDataEvent(JSONObject jSONObject) {
        this.c.queueEvent(this.f, jSONObject, 5);
    }

    @Override // com.clevertap.android.sdk.BaseAnalyticsManager
    public void sendFetchEvent(JSONObject jSONObject) {
        this.c.queueEvent(this.f, jSONObject, 7);
    }

    public void sendPingEvent(JSONObject jSONObject) {
        this.c.queueEvent(this.f, jSONObject, 2);
    }

    public final boolean u(Bundle bundle, HashMap<String, Object> hashMap, int i2) {
        boolean z;
        synchronized (this.n) {
            z = false;
            try {
                String string = bundle.getString(Constants.NOTIFICATION_ID_TAG);
                long currentTimeMillis = System.currentTimeMillis();
                if (hashMap.containsKey(string) && currentTimeMillis - ((Long) hashMap.get(string)).longValue() < i2) {
                    z = true;
                }
                hashMap.put(string, Long.valueOf(currentTimeMillis));
            } catch (Throwable unused) {
            }
        }
        return z;
    }

    public final i v(Number number) {
        if (number.equals(Integer.valueOf(number.intValue()))) {
            this.p = i.INT_NUMBER;
        } else if (number.equals(Double.valueOf(number.doubleValue()))) {
            this.p = i.DOUBLE_NUMBER;
        } else if (number.equals(Float.valueOf(number.floatValue()))) {
            this.p = i.FLOAT_NUMBER;
        }
        return this.p;
    }

    public final void w(Bundle bundle) {
        try {
            new DisplayUnitResponse(new CleverTapResponseHelper(), this.e, this.d, this.g).processResponse(CTJsonConverter.displayUnitFromExtras(bundle), null, this.f);
        } catch (Throwable th) {
            Logger.v("Failed to process Display Unit from push notification payload", th);
        }
    }

    public void x(HashMap<String, Object> hashMap, ArrayList<HashMap<String, Object>> arrayList) {
        Iterator<String> it;
        int i2;
        boolean z;
        boolean z2;
        if (hashMap != null && arrayList != null) {
            if (arrayList.size() > 50) {
                ValidationResult create = ValidationResultFactory.create(DfuConstants.PROGRESS_HAND_OVER_PROCESSING);
                this.e.getLogger().debug(this.e.getAccountId(), create.getErrorDesc());
                this.k.pushValidationResult(create);
            }
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            try {
                it = hashMap.keySet().iterator();
            } catch (Throwable unused) {
                return;
            }
            while (true) {
                i2 = 2;
                z = true;
                z2 = false;
                if (!it.hasNext()) {
                    break;
                }
                String next = it.next();
                Object obj = hashMap.get(next);
                ValidationResult cleanObjectKey = this.l.cleanObjectKey(next);
                String obj2 = cleanObjectKey.getObject().toString();
                if (cleanObjectKey.getErrorCode() != 0) {
                    jSONObject2.put(Constants.ERROR_KEY, CTJsonConverter.getErrorObject(cleanObjectKey));
                }
                try {
                    ValidationResult cleanObjectValue = this.l.cleanObjectValue(obj, Validator.ValidationContext.Event);
                    Object object = cleanObjectValue.getObject();
                    if (cleanObjectValue.getErrorCode() != 0) {
                        jSONObject2.put(Constants.ERROR_KEY, CTJsonConverter.getErrorObject(cleanObjectValue));
                    }
                    jSONObject.put(obj2, object);
                } catch (IllegalArgumentException unused2) {
                    String[] strArr = new String[3];
                    strArr[0] = Constants.CHARGED_EVENT;
                    strArr[1] = obj2;
                    strArr[2] = obj != null ? obj.toString() : "";
                    ValidationResult create2 = ValidationResultFactory.create(511, 7, strArr);
                    this.k.pushValidationResult(create2);
                    this.e.getLogger().debug(this.e.getAccountId(), create2.getErrorDesc());
                }
                return;
            }
            JSONArray jSONArray = new JSONArray();
            Iterator<HashMap<String, Object>> it2 = arrayList.iterator();
            while (it2.hasNext()) {
                HashMap<String, Object> next2 = it2.next();
                JSONObject jSONObject3 = new JSONObject();
                for (String str : next2.keySet()) {
                    Object obj3 = next2.get(str);
                    ValidationResult cleanObjectKey2 = this.l.cleanObjectKey(str);
                    String obj4 = cleanObjectKey2.getObject().toString();
                    if (cleanObjectKey2.getErrorCode() != 0) {
                        jSONObject2.put(Constants.ERROR_KEY, CTJsonConverter.getErrorObject(cleanObjectKey2));
                    }
                    try {
                        ValidationResult cleanObjectValue2 = this.l.cleanObjectValue(obj3, Validator.ValidationContext.Event);
                        Object object2 = cleanObjectValue2.getObject();
                        if (cleanObjectValue2.getErrorCode() != 0) {
                            jSONObject2.put(Constants.ERROR_KEY, CTJsonConverter.getErrorObject(cleanObjectValue2));
                        }
                        jSONObject3.put(obj4, object2);
                        z = true;
                        z2 = false;
                    } catch (IllegalArgumentException unused3) {
                        String[] strArr2 = new String[i2];
                        strArr2[0] = obj4;
                        strArr2[1] = obj3 != null ? obj3.toString() : "";
                        ValidationResult create3 = ValidationResultFactory.create(511, 15, strArr2);
                        this.e.getLogger().debug(this.e.getAccountId(), create3.getErrorDesc());
                        this.k.pushValidationResult(create3);
                        z = true;
                        z2 = false;
                        i2 = 2;
                    }
                }
                jSONArray.put(jSONObject3);
                z = z;
                z2 = z2;
                i2 = 2;
            }
            jSONObject.put("Items", jSONArray);
            jSONObject2.put("evtName", Constants.CHARGED_EVENT);
            jSONObject2.put("evtData", jSONObject);
            this.c.queueEvent(this.f, jSONObject2, 4);
            return;
        }
        this.e.getLogger().debug(this.e.getAccountId(), "Invalid Charged event: details and or items is null");
    }

    public synchronized void y(Uri uri, boolean z) {
        if (uri == null) {
            return;
        }
        try {
            JSONObject urchinFromUri = UriHelper.getUrchinFromUri(uri);
            if (urchinFromUri.has("us")) {
                this.h.q(urchinFromUri.get("us").toString());
            }
            if (urchinFromUri.has("um")) {
                this.h.n(urchinFromUri.get("um").toString());
            }
            if (urchinFromUri.has("uc")) {
                this.h.h(urchinFromUri.get("uc").toString());
            }
            urchinFromUri.put("referrer", uri.toString());
            if (z) {
                urchinFromUri.put("install", true);
            }
            C(urchinFromUri);
        } finally {
        }
    }

    public void z(boolean z, CTInboxMessage cTInboxMessage, Bundle bundle) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject wzrkFields = CTJsonConverter.getWzrkFields(cTInboxMessage);
            if (bundle != null) {
                for (String str : bundle.keySet()) {
                    Object obj = bundle.get(str);
                    if (obj != null) {
                        wzrkFields.put(str, obj);
                    }
                }
            }
            if (z) {
                try {
                    this.h.setWzrkParams(wzrkFields);
                } catch (Throwable unused) {
                }
                jSONObject.put("evtName", Constants.NOTIFICATION_CLICKED_EVENT_NAME);
            } else {
                jSONObject.put("evtName", Constants.NOTIFICATION_VIEWED_EVENT_NAME);
            }
            jSONObject.put("evtData", wzrkFields);
            this.c.queueEvent(this.f, jSONObject, 4);
        } catch (Throwable unused2) {
        }
    }

    @Override // com.clevertap.android.sdk.BaseAnalyticsManager
    public synchronized void pushInstallReferrer(String str, String str2, String str3) {
        if (str == null && str2 == null && str3 == null) {
            return;
        }
        try {
        } catch (Throwable th) {
            Logger.v("Failed to push install referrer", th);
        }
        if (StorageHelper.getInt(this.f, "app_install_status", 0) != 0) {
            Logger.d("Install referrer has already been set. Will not override it");
            return;
        }
        StorageHelper.putInt(this.f, "app_install_status", 1);
        if (str != null) {
            str = Uri.encode(str);
        }
        if (str2 != null) {
            str2 = Uri.encode(str2);
        }
        if (str3 != null) {
            str3 = Uri.encode(str3);
        }
        String str4 = "wzrk://track?install=true";
        if (str != null) {
            str4 = "wzrk://track?install=true&utm_source=" + str;
        }
        if (str2 != null) {
            str4 = str4 + "&utm_medium=" + str2;
        }
        if (str3 != null) {
            str4 = str4 + "&utm_campaign=" + str3;
        }
        y(Uri.parse(str4), true);
    }
}
