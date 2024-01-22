package com.coveiot.android.leonardo.more.viewmodel;

import android.content.Context;
import android.widget.Toast;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.models.WorldClock;
import com.coveiot.android.bleabstract.request.GetWorldClockDataRequest;
import com.coveiot.android.bleabstract.request.SetWorldClockListRequest;
import com.coveiot.android.bleabstract.request.SetWorldClockRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.response.GetWorldClockDataResponse;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.more.models.WorldClockData;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.android.weeklyreport.listeners.OnSuccessListener;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveUserAppSettings;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.userappsetting.SaveDualTimeZoneSettingsReq;
import com.coveiot.coveaccess.userappsetting.SaveDualTimeZoneSettingsRes;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.WorldClockPrefData;
import com.coveiot.utils.utility.LogHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import com.google.gson.Gson;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt__StringsKt;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class WorldClockViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5207a;
    @NotNull
    public ArrayList<WorldClockPrefData> b;
    @NotNull
    public MutableLiveData<Boolean> c;
    @Nullable
    public OnSuccessListener d;
    @Nullable
    public MutableLiveData<ArrayList<WorldClockPrefData>> e;
    @NotNull
    public ArrayList<WorldClockPrefData> f;

    public WorldClockViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5207a = context;
        this.b = new ArrayList<>();
        this.c = new MutableLiveData<>();
        if (UserDataManager.getInstance(context).getWorldClocList() != null && UserDataManager.getInstance(context).getWorldClocList().size() > 0) {
            this.b.addAll(UserDataManager.getInstance(context).getWorldClocList());
        }
        MutableLiveData<ArrayList<WorldClockPrefData>> mutableLiveData = new MutableLiveData<>();
        mutableLiveData.postValue(j());
        this.e = mutableLiveData;
        this.f = new ArrayList<>();
    }

    public static final void f(WorldClockViewModel this$0, Ref.ObjectRef mFirebaseRemoteConfig, Task task) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(mFirebaseRemoteConfig, "$mFirebaseRemoteConfig");
        Intrinsics.checkNotNullParameter(task, "task");
        if (task.isSuccessful()) {
            String string = ((FirebaseRemoteConfig) mFirebaseRemoteConfig.element).getString("worldclock_json_url");
            Intrinsics.checkNotNullExpressionValue(string, "mFirebaseRemoteConfig.ge…ng(\"worldclock_json_url\")");
            this$0.e(string);
            return;
        }
        OnSuccessListener onSuccessListener = this$0.d;
        if (onSuccessListener != null) {
            String string2 = this$0.f5207a.getResources().getString(R.string.fetch_failed);
            Intrinsics.checkNotNullExpressionValue(string2, "context.resources.getString(R.string.fetch_failed)");
            onSuccessListener.onDataFailure(string2);
        }
        Context context = this$0.f5207a;
        Toast.makeText(context, context.getResources().getString(R.string.fetch_failed), 0).show();
    }

    public final void c(List<SetWorldClockRequest> list) {
        SaveDualTimeZoneSettingsReq saveDualTimeZoneSettingsReq = new SaveDualTimeZoneSettingsReq();
        ArrayList arrayList = new ArrayList();
        int size = list.size();
        int i = 0;
        while (i < size) {
            SaveDualTimeZoneSettingsReq.TimeZone timeZone = new SaveDualTimeZoneSettingsReq.TimeZone();
            timeZone.setCity(list.get(i).getCityName());
            timeZone.setOffset(i(list.get(i).getTimeZoneOffsetMinutes()));
            i++;
            timeZone.setPreference(i);
            arrayList.add(timeZone);
        }
        saveDualTimeZoneSettingsReq.setTimeZones(arrayList);
        CoveUserAppSettings.saveDualTimeZoneSettings(saveDualTimeZoneSettingsReq, new CoveApiListener<SaveDualTimeZoneSettingsRes, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.more.viewmodel.WorldClockViewModel$callWorldClockAPI$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                LogHelper.i("WorldClockViewModel", "Save DualTime error1");
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@NotNull SaveDualTimeZoneSettingsRes object) {
                Intrinsics.checkNotNullParameter(object, "object");
                if (object.getCode() == 200) {
                    LogHelper.i("WorldClockViewModel", "DualTime saved successful");
                } else {
                    LogHelper.i("WorldClockViewModel", " Save DualTime error");
                }
            }
        });
    }

    public final ArrayList<String> d(ArrayList<WorldClockPrefData> arrayList) {
        ArrayList<String> arrayList2 = new ArrayList<>();
        if (arrayList != null && arrayList.size() > 0) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                String name = arrayList.get(i).getName();
                Intrinsics.checkNotNull(name);
                arrayList2.add(name);
            }
        }
        return arrayList2;
    }

    public final void e(String str) {
        new OkHttpClient().newCall(new Request.Builder().url(str).build()).enqueue(new Callback() { // from class: com.coveiot.android.leonardo.more.viewmodel.WorldClockViewModel$getCountryListFromApi$1
            @Override // okhttp3.Callback
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(e, "e");
                OnSuccessListener onSuccessListener = WorldClockViewModel.this.getOnSuccessListener();
                if (onSuccessListener != null) {
                    String string = WorldClockViewModel.this.getContext().getResources().getString(R.string.please_try_again);
                    Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr….string.please_try_again)");
                    onSuccessListener.onDataFailure(string);
                }
            }

            @Override // okhttp3.Callback
            public void onResponse(@NotNull Call call, @NotNull Response response) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                Gson gson = new Gson();
                ResponseBody body = response.body();
                Intrinsics.checkNotNull(body);
                String string = body.string();
                WorldClockViewModel worldClockViewModel = WorldClockViewModel.this;
                Object fromJson = gson.fromJson(string, (Class<Object>) WorldClockPrefData[].class);
                Intrinsics.checkNotNullExpressionValue(fromJson, "gson.fromJson(jsonString…ockPrefData>::class.java)");
                List list = ArraysKt___ArraysKt.toList((Object[]) fromJson);
                Intrinsics.checkNotNull(list, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.covepreferences.data.WorldClockPrefData>{ kotlin.collections.TypeAliasesKt.ArrayList<com.coveiot.covepreferences.data.WorldClockPrefData> }");
                worldClockViewModel.setWorldClockCountryList((ArrayList) list);
                WorldClockViewModel.this.loadAllCountries();
            }
        });
    }

    public final void filterCountries(@Nullable String str) {
        ArrayList<WorldClockPrefData> arrayList = new ArrayList<>();
        if (str != null) {
            if (str.length() > 0) {
                Locale locale = Locale.ENGLISH;
                Intrinsics.checkNotNullExpressionValue(locale, "locale");
                String lowerCase = str.toLowerCase(locale);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
                Iterator<WorldClockPrefData> it = j().iterator();
                while (it.hasNext()) {
                    WorldClockPrefData next = it.next();
                    String country = next.getCountry();
                    Intrinsics.checkNotNull(country);
                    Locale locale2 = Locale.ROOT;
                    String lowerCase2 = country.toLowerCase(locale2);
                    Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                    if (!StringsKt__StringsKt.contains$default((CharSequence) lowerCase2, (CharSequence) lowerCase, false, 2, (Object) null)) {
                        String name = next.getName();
                        Intrinsics.checkNotNull(name);
                        String lowerCase3 = name.toLowerCase(locale2);
                        Intrinsics.checkNotNullExpressionValue(lowerCase3, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                        if (StringsKt__StringsKt.contains$default((CharSequence) lowerCase3, (CharSequence) lowerCase, false, 2, (Object) null)) {
                        }
                    }
                    arrayList.add(next);
                }
                MutableLiveData<ArrayList<WorldClockPrefData>> mutableLiveData = this.e;
                Intrinsics.checkNotNull(mutableLiveData);
                mutableLiveData.postValue(arrayList);
            }
        }
        arrayList.clear();
        arrayList.addAll(j());
        MutableLiveData<ArrayList<WorldClockPrefData>> mutableLiveData2 = this.e;
        Intrinsics.checkNotNull(mutableLiveData2);
        mutableLiveData2.postValue(arrayList);
    }

    @NotNull
    public final Context getContext() {
        return this.f5207a;
    }

    @NotNull
    public final MutableLiveData<ArrayList<WorldClockPrefData>> getCountriesLiveData() {
        MutableLiveData<ArrayList<WorldClockPrefData>> mutableLiveData = this.e;
        Intrinsics.checkNotNull(mutableLiveData);
        return mutableLiveData;
    }

    public final void getCountryList(@NotNull WorldClockPrefData prefData) {
        Intrinsics.checkNotNullParameter(prefData, "prefData");
        ArrayList<WorldClockPrefData> arrayList = this.b;
        if (arrayList != null && arrayList.size() > 0) {
            int size = this.b.size();
            boolean z = false;
            int i = 0;
            while (true) {
                if (i >= size) {
                    break;
                } else if (kotlin.text.m.equals(this.b.get(i).getName(), prefData.getName(), true)) {
                    this.b.remove(i);
                    z = true;
                    break;
                } else {
                    i++;
                }
            }
            if (z) {
                return;
            }
            this.b.add(prefData);
            return;
        }
        this.b.add(prefData);
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [T, com.google.firebase.remoteconfig.FirebaseRemoteConfig, java.lang.Object] */
    public final void getCountryListUrlFromRemoteConfig() {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        ?? firebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        Intrinsics.checkNotNullExpressionValue(firebaseRemoteConfig, "getInstance()");
        objectRef.element = firebaseRemoteConfig;
        FirebaseRemoteConfigSettings build = new FirebaseRemoteConfigSettings.Builder().setMinimumFetchIntervalInSeconds(0L).build();
        Intrinsics.checkNotNullExpressionValue(build, "Builder()\n            .s…s(0)\n            .build()");
        ((FirebaseRemoteConfig) objectRef.element).setConfigSettingsAsync(build);
        ((FirebaseRemoteConfig) objectRef.element).fetchAndActivate().addOnCompleteListener(new OnCompleteListener() { // from class: com.coveiot.android.leonardo.more.viewmodel.o
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                WorldClockViewModel.f(WorldClockViewModel.this, objectRef, task);
            }
        });
    }

    @NotNull
    public final ArrayList<WorldClockData> getDefaultCountryListWithTime() {
        ArrayList<WorldClockData> arrayList = new ArrayList<>();
        ArrayList<WorldClockPrefData> h = h();
        int size = h.size();
        for (int i = 0; i < size; i++) {
            Locale locale = Locale.ENGLISH;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm a", locale);
            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd hh:mm a", locale);
            SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat("hh:mm a", locale);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone(h.get(i).getTimeZoneName()));
            simpleDateFormat3.setTimeZone(TimeZone.getTimeZone(h.get(i).getTimeZoneName()));
            simpleDateFormat2.setTimeZone(TimeZone.getDefault());
            LogHelper.i("WorldClockViewmodel", "Date and time in city selected: " + simpleDateFormat.format(new Date()));
            LogHelper.i("WorldClockViewmodel", "Date and time in city india: " + simpleDateFormat2.format(new Date()));
            StringBuilder sb = new StringBuilder();
            sb.append("Today ");
            PayUtils payUtils = PayUtils.INSTANCE;
            String format = simpleDateFormat.format(new Date());
            Intrinsics.checkNotNullExpressionValue(format, "selectedCountry.format(\n… Date()\n                )");
            String format2 = simpleDateFormat2.format(new Date());
            Intrinsics.checkNotNullExpressionValue(format2, "currentCountry.format(Date())");
            sb.append(payUtils.getTimeDifferenceWorldClock(format, format2, "yyyy-MM-dd hh:mm a"));
            sb.append(" hrs");
            String sb2 = sb.toString();
            String name = h.get(i).getName();
            Intrinsics.checkNotNull(name);
            String format3 = simpleDateFormat3.format(new Date());
            Intrinsics.checkNotNullExpressionValue(format3, "selectedCountryTime.format(Date())");
            arrayList.add(new WorldClockData(name, sb2, format3));
        }
        return arrayList;
    }

    @NotNull
    public final MutableLiveData<Boolean> getGetWorldClockFromWatchLiveData() {
        return this.c;
    }

    @Nullable
    public final OnSuccessListener getOnSuccessListener() {
        return this.d;
    }

    @NotNull
    public final ArrayList<WorldClockPrefData> getRemovedCountryList() {
        return this.b;
    }

    @NotNull
    public final ArrayList<WorldClockPrefData> getWorldClockCountryList() {
        return this.f;
    }

    public final void getWorldClockFromWatch() {
        if (BleApiManager.getInstance(this.f5207a).getBleApi().getDeviceSupportedFeatures().isReadWorldClockFromWatchSupported()) {
            if (BleApiManager.getInstance(this.f5207a).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                BleApiManager.getInstance(this.f5207a).getBleApi().getData(new GetWorldClockDataRequest(), new DataResultListener() { // from class: com.coveiot.android.leonardo.more.viewmodel.WorldClockViewModel$getWorldClockFromWatch$1
                    @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                    public void onDataError(@NotNull BleBaseError error) {
                        Intrinsics.checkNotNullParameter(error, "error");
                        WorldClockViewModel.this.getGetWorldClockFromWatchLiveData().postValue(Boolean.TRUE);
                    }

                    @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                    public void onDataResponse(@NotNull BleBaseResponse response) {
                        Intrinsics.checkNotNullParameter(response, "response");
                        if (response.getResponseData() != null && (response.getResponseData() instanceof GetWorldClockDataResponse)) {
                            Object responseData = response.getResponseData();
                            Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.GetWorldClockDataResponse");
                            ArrayList<WorldClock> worldClockList = ((GetWorldClockDataResponse) responseData).getWorldClockList();
                            Intrinsics.checkNotNull(worldClockList);
                            List<WorldClockPrefData> worldClocList = UserDataManager.getInstance(WorldClockViewModel.this.getContext()).getWorldClocList();
                            if (!(worldClocList == null || worldClocList.isEmpty())) {
                                List<WorldClockPrefData> worldClockPrefList = UserDataManager.getInstance(WorldClockViewModel.this.getContext()).getWorldClocList();
                                ArrayList arrayList = new ArrayList();
                                if (worldClockList != null && worldClockList.size() != worldClockPrefList.size()) {
                                    Iterator<WorldClock> it = worldClockList.iterator();
                                    while (it.hasNext()) {
                                        WorldClock next = it.next();
                                        int size = worldClockPrefList.size();
                                        int i = 0;
                                        while (true) {
                                            if (i >= size) {
                                                break;
                                            } else if (Intrinsics.areEqual(next.getCityName(), worldClockPrefList.get(i).getName())) {
                                                WorldClockPrefData worldClockPrefData = worldClockPrefList.get(i);
                                                Intrinsics.checkNotNullExpressionValue(worldClockPrefData, "worldClockPrefList[i]");
                                                arrayList.add(worldClockPrefData);
                                                break;
                                            } else {
                                                i++;
                                            }
                                        }
                                    }
                                    worldClockPrefList = arrayList;
                                } else {
                                    Intrinsics.checkNotNullExpressionValue(worldClockPrefList, "worldClockPrefList");
                                }
                                if (!worldClockPrefList.isEmpty()) {
                                    UserDataManager.getInstance(WorldClockViewModel.this.getContext()).saveWorldClockList(worldClockPrefList);
                                    WorldClockViewModel.this.k(worldClockPrefList);
                                }
                                WorldClockViewModel.this.getGetWorldClockFromWatchLiveData().postValue(Boolean.TRUE);
                                return;
                            }
                            WorldClockViewModel.this.getGetWorldClockFromWatchLiveData().postValue(Boolean.TRUE);
                            return;
                        }
                        WorldClockViewModel.this.getGetWorldClockFromWatchLiveData().postValue(Boolean.TRUE);
                    }

                    @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                    public void onProgressUpdate(@NotNull ProgressData progress) {
                        Intrinsics.checkNotNullParameter(progress, "progress");
                    }
                });
                return;
            }
            this.c.postValue(Boolean.TRUE);
            return;
        }
        this.c.postValue(Boolean.TRUE);
    }

    public final ArrayList<WorldClockPrefData> h() {
        ArrayList<WorldClockPrefData> arrayList = new ArrayList<>();
        List<WorldClockPrefData> worldClocList = UserDataManager.getInstance(this.f5207a).getWorldClocList();
        if (!(worldClocList == null || worldClocList.isEmpty())) {
            arrayList.addAll(worldClocList);
        }
        return arrayList;
    }

    public final String i(int i) {
        if (i == 0) {
            return "+00:00";
        }
        String str = i < 0 ? "-" : "+";
        int abs = Math.abs(i);
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Locale locale = Locale.ENGLISH;
        String format = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(abs / 60)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
        sb.append(format);
        sb.append(':');
        String format2 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(abs % 60)}, 1));
        Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
        sb.append(format2);
        return sb.toString();
    }

    public final boolean isChanged(@NotNull WorldClockPrefData data) {
        Intrinsics.checkNotNullParameter(data, "data");
        List<WorldClockPrefData> worldClocList = UserDataManager.getInstance(this.f5207a).getWorldClocList();
        Intrinsics.checkNotNull(worldClocList, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.covepreferences.data.WorldClockPrefData>{ kotlin.collections.TypeAliasesKt.ArrayList<com.coveiot.covepreferences.data.WorldClockPrefData> }");
        return (data.getName() == null || CollectionsKt___CollectionsKt.contains(d((ArrayList) worldClocList), data.getName())) ? false : true;
    }

    public final ArrayList<WorldClockPrefData> j() {
        return this.f;
    }

    public final void k(List<WorldClockPrefData> list) {
        ArrayList arrayList = new ArrayList();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            SetWorldClockRequest setWorldClockRequest = new SetWorldClockRequest();
            Integer id = list.get(i).getId();
            Intrinsics.checkNotNull(id);
            setWorldClockRequest.setId(id.intValue());
            String name = list.get(i).getName();
            Intrinsics.checkNotNull(name);
            setWorldClockRequest.setCityName(name);
            Double latitude = list.get(i).getLatitude();
            Intrinsics.checkNotNull(latitude);
            setWorldClockRequest.setLatitude(latitude.doubleValue());
            Double longitude = list.get(i).getLongitude();
            Intrinsics.checkNotNull(longitude);
            setWorldClockRequest.setLongitude(longitude.doubleValue());
            setWorldClockRequest.setSunSet(0);
            setWorldClockRequest.setSunRise(0);
            setWorldClockRequest.setTimeZoneOffsetMinutes((int) TimeUnit.MILLISECONDS.toMinutes(TimeZone.getTimeZone(list.get(i).getTimeZoneName()).getOffset(Calendar.getInstance().getTimeInMillis())));
            arrayList.add(setWorldClockRequest);
        }
        c(arrayList);
    }

    public final void loadAllCountries() {
        MutableLiveData<ArrayList<WorldClockPrefData>> mutableLiveData = this.e;
        Intrinsics.checkNotNull(mutableLiveData);
        mutableLiveData.postValue(j());
    }

    public final void sendToBle(final boolean z, @NotNull final List<WorldClockPrefData> countryListData) {
        Intrinsics.checkNotNullParameter(countryListData, "countryListData");
        final ArrayList arrayList = new ArrayList();
        int size = countryListData.size();
        for (int i = 0; i < size; i++) {
            SetWorldClockRequest setWorldClockRequest = new SetWorldClockRequest();
            Integer id = countryListData.get(i).getId();
            Intrinsics.checkNotNull(id);
            setWorldClockRequest.setId(id.intValue());
            String name = countryListData.get(i).getName();
            Intrinsics.checkNotNull(name);
            setWorldClockRequest.setCityName(name);
            Double latitude = countryListData.get(i).getLatitude();
            Intrinsics.checkNotNull(latitude);
            setWorldClockRequest.setLatitude(latitude.doubleValue());
            Double longitude = countryListData.get(i).getLongitude();
            Intrinsics.checkNotNull(longitude);
            setWorldClockRequest.setLongitude(longitude.doubleValue());
            setWorldClockRequest.setSunSet(0);
            setWorldClockRequest.setSunRise(0);
            setWorldClockRequest.setTimeZoneOffsetMinutes((int) TimeUnit.MILLISECONDS.toMinutes(TimeZone.getTimeZone(countryListData.get(i).getTimeZoneName()).getOffset(Calendar.getInstance().getTimeInMillis())));
            arrayList.add(setWorldClockRequest);
        }
        BleApiManager.getInstance(this.f5207a).getBleApi().setUserSettings(new SetWorldClockListRequest.Builder().setSedentaryReminderList(arrayList).build(), new SettingsResultListener() { // from class: com.coveiot.android.leonardo.more.viewmodel.WorldClockViewModel$sendToBle$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                OnSuccessListener onSuccessListener;
                Intrinsics.checkNotNullParameter(error, "error");
                if (!z || (onSuccessListener = this.getOnSuccessListener()) == null) {
                    return;
                }
                String string = this.getContext().getResources().getString(R.string.please_try_again);
                Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr….string.please_try_again)");
                onSuccessListener.onDataFailure(string);
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                OnSuccessListener onSuccessListener;
                Intrinsics.checkNotNullParameter(response, "response");
                if (z && (onSuccessListener = this.getOnSuccessListener()) != null) {
                    onSuccessListener.onSuccess();
                }
                UserDataManager.getInstance(this.getContext()).saveWorldClockList(countryListData);
                this.c(arrayList);
            }
        });
    }

    public final void setGetWorldClockFromWatchLiveData(@NotNull MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.c = mutableLiveData;
    }

    public final void setOnSuccessListener(@Nullable OnSuccessListener onSuccessListener) {
        this.d = onSuccessListener;
    }

    public final void setRemovedCountryList(@NotNull ArrayList<WorldClockPrefData> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.b = arrayList;
    }

    public final void setWorldClockCountryList(@NotNull ArrayList<WorldClockPrefData> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.f = arrayList;
    }

    public final void swapedList(@NotNull ArrayList<WorldClockData> worldClockList) {
        Intrinsics.checkNotNullParameter(worldClockList, "worldClockList");
        List<WorldClockPrefData> worldClocList = UserDataManager.getInstance(this.f5207a).getWorldClocList();
        ArrayList arrayList = new ArrayList();
        int size = worldClockList.size();
        for (int i = 0; i < size; i++) {
            int size2 = worldClocList.size();
            for (int i2 = 0; i2 < size2; i2++) {
                if (kotlin.text.m.equals(worldClockList.get(i).getCountryName(), worldClocList.get(i2).getName(), true)) {
                    arrayList.add(i, worldClocList.get(i2));
                }
            }
        }
        sendToBle(true, arrayList);
    }
}
