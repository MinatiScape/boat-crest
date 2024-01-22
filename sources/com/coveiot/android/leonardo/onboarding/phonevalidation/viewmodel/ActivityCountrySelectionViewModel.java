package com.coveiot.android.leonardo.onboarding.phonevalidation.viewmodel;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.coveiot.utils.model.CountryCodeModel;
import com.coveiot.utils.utility.AppUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityCountrySelectionViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5292a;
    @Nullable
    public MutableLiveData<ArrayList<CountryCodeModel>> b;

    public ActivityCountrySelectionViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5292a = context;
        MutableLiveData<ArrayList<CountryCodeModel>> mutableLiveData = new MutableLiveData<>();
        mutableLiveData.setValue(AppUtils.getCodeModelArrayList(context));
        this.b = mutableLiveData;
    }

    public final void filterCountries(@Nullable String str) {
        ArrayList<CountryCodeModel> arrayList = new ArrayList<>();
        if (str != null) {
            if (str.length() > 0) {
                Locale locale = Locale.ENGLISH;
                Intrinsics.checkNotNullExpressionValue(locale, "locale");
                String lowerCase = str.toLowerCase(locale);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
                Iterator<CountryCodeModel> it = AppUtils.getCodeModelArrayList(this.f5292a).iterator();
                while (it.hasNext()) {
                    CountryCodeModel next = it.next();
                    Locale locale2 = new Locale(locale.getLanguage(), next.getCountryCode());
                    String displayCountry = locale2.getDisplayCountry();
                    Intrinsics.checkNotNullExpressionValue(displayCountry, "locale.displayCountry");
                    String lowerCase2 = displayCountry.toLowerCase(locale2);
                    Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase(locale)");
                    if (!StringsKt__StringsKt.contains$default((CharSequence) lowerCase2, (CharSequence) lowerCase, false, 2, (Object) null)) {
                        String isoCode = next.getIsoCode();
                        Intrinsics.checkNotNullExpressionValue(isoCode, "model.isoCode");
                        if (StringsKt__StringsKt.contains$default((CharSequence) isoCode, (CharSequence) lowerCase, false, 2, (Object) null)) {
                        }
                    }
                    arrayList.add(next);
                }
                MutableLiveData<ArrayList<CountryCodeModel>> mutableLiveData = this.b;
                Intrinsics.checkNotNull(mutableLiveData);
                mutableLiveData.setValue(arrayList);
            }
        }
        arrayList.clear();
        arrayList.addAll(AppUtils.getCodeModelArrayList(this.f5292a));
        MutableLiveData<ArrayList<CountryCodeModel>> mutableLiveData2 = this.b;
        Intrinsics.checkNotNull(mutableLiveData2);
        mutableLiveData2.setValue(arrayList);
    }

    @NotNull
    public final Context getContext() {
        return this.f5292a;
    }

    @NotNull
    public final MutableLiveData<ArrayList<CountryCodeModel>> getCountriesLiveData() {
        MutableLiveData<ArrayList<CountryCodeModel>> mutableLiveData = this.b;
        Intrinsics.checkNotNull(mutableLiveData);
        return mutableLiveData;
    }

    public final void loadAllCountries() {
        MutableLiveData<ArrayList<CountryCodeModel>> mutableLiveData = this.b;
        Intrinsics.checkNotNull(mutableLiveData);
        mutableLiveData.setValue(AppUtils.getCodeModelArrayList(this.f5292a));
    }
}
