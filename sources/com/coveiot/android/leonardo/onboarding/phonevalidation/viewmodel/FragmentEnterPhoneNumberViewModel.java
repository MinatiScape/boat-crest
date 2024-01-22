package com.coveiot.android.leonardo.onboarding.phonevalidation.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.clevertap.android.sdk.Constants;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.onboarding.phonevalidation.model.CountryCodeModel;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.websupport.ActivityInAppWebViewer;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.utils.utility.AppUtils;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class FragmentEnterPhoneNumberViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5316a;
    @Nullable
    public MutableLiveData<CountryCodeModel> b;

    public FragmentEnterPhoneNumberViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5316a = context;
        this.b = new MutableLiveData<>();
        this.b = new MutableLiveData<>();
    }

    public final String a(String str, String str2) {
        int hashCode = str.hashCode();
        if (hashCode != 2100) {
            if (hashCode != 2142) {
                if (hashCode != 2144) {
                    if (hashCode != 2165) {
                        if (hashCode != 2267) {
                            if (hashCode != 2340) {
                                if (hashCode != 2415) {
                                    if (hashCode != 2562) {
                                        if (hashCode != 2627) {
                                            if (hashCode == 2718 && str.equals("US")) {
                                                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                                                String format = String.format(Locale.ENGLISH, "Country code %s - %s", Arrays.copyOf(new Object[]{str2, "United States"}, 2));
                                                Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
                                                return format;
                                            }
                                        } else if (str.equals("RU")) {
                                            StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                                            String format2 = String.format(Locale.ENGLISH, "Country code %s - %s", Arrays.copyOf(new Object[]{str2, "Russia"}, 2));
                                            Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
                                            return format2;
                                        }
                                    } else if (str.equals("PR")) {
                                        StringCompanionObject stringCompanionObject3 = StringCompanionObject.INSTANCE;
                                        String format3 = String.format(Locale.ENGLISH, "Country code %s - %s", Arrays.copyOf(new Object[]{str2, "Puerto Rico"}, 2));
                                        Intrinsics.checkNotNullExpressionValue(format3, "format(locale, format, *args)");
                                        return format3;
                                    }
                                } else if (str.equals("KZ")) {
                                    StringCompanionObject stringCompanionObject4 = StringCompanionObject.INSTANCE;
                                    String format4 = String.format(Locale.ENGLISH, "Country code %s - %s", Arrays.copyOf(new Object[]{str2, "Kazakhstan"}, 2));
                                    Intrinsics.checkNotNullExpressionValue(format4, "format(locale, format, *args)");
                                    return format4;
                                }
                            } else if (str.equals("IM")) {
                                StringCompanionObject stringCompanionObject5 = StringCompanionObject.INSTANCE;
                                String format5 = String.format(Locale.ENGLISH, "Country code %s - %s", Arrays.copyOf(new Object[]{str2, "Isle of Man"}, 2));
                                Intrinsics.checkNotNullExpressionValue(format5, "format(locale, format, *args)");
                                return format5;
                            }
                        } else if (str.equals("GB")) {
                            StringCompanionObject stringCompanionObject6 = StringCompanionObject.INSTANCE;
                            String format6 = String.format(Locale.ENGLISH, "Country code %s - %s", Arrays.copyOf(new Object[]{str2, "United Kingdom"}, 2));
                            Intrinsics.checkNotNullExpressionValue(format6, "format(locale, format, *args)");
                            return format6;
                        }
                    } else if (str.equals("CX")) {
                        StringCompanionObject stringCompanionObject7 = StringCompanionObject.INSTANCE;
                        String format7 = String.format(Locale.ENGLISH, "Country code %s - %s", Arrays.copyOf(new Object[]{str2, "Christmas Island"}, 2));
                        Intrinsics.checkNotNullExpressionValue(format7, "format(locale, format, *args)");
                        return format7;
                    }
                } else if (str.equals("CC")) {
                    StringCompanionObject stringCompanionObject8 = StringCompanionObject.INSTANCE;
                    String format8 = String.format(Locale.ENGLISH, "Country code %s - %s", Arrays.copyOf(new Object[]{str2, "Cocos (Keeling) Islands"}, 2));
                    Intrinsics.checkNotNullExpressionValue(format8, "format(locale, format, *args)");
                    return format8;
                }
            } else if (str.equals("CA")) {
                StringCompanionObject stringCompanionObject9 = StringCompanionObject.INSTANCE;
                String format9 = String.format(Locale.ENGLISH, "Country code %s - %s", Arrays.copyOf(new Object[]{str2, "Canada"}, 2));
                Intrinsics.checkNotNullExpressionValue(format9, "format(locale, format, *args)");
                return format9;
            }
        } else if (str.equals("AU")) {
            StringCompanionObject stringCompanionObject10 = StringCompanionObject.INSTANCE;
            String format10 = String.format(Locale.ENGLISH, "Country code %s - %s", Arrays.copyOf(new Object[]{str2, "Australia"}, 2));
            Intrinsics.checkNotNullExpressionValue(format10, "format(locale, format, *args)");
            return format10;
        }
        return HexStringBuilder.DEFAULT_SEPARATOR;
    }

    @NotNull
    public final Context getContext() {
        return this.f5316a;
    }

    @Nullable
    public final MutableLiveData<CountryCodeModel> getMSelectedCountryCode() {
        return this.b;
    }

    public final void onCountryCodeChanged(@NotNull String countryCode, @NotNull String countryCodeName) {
        String format;
        Intrinsics.checkNotNullParameter(countryCode, "countryCode");
        Intrinsics.checkNotNullParameter(countryCodeName, "countryCodeName");
        ArrayList<String> retrieveCountryNamesFromIsd = AppUtils.retrieveCountryNamesFromIsd(this.f5316a, countryCode);
        if (retrieveCountryNamesFromIsd.isEmpty()) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            Locale locale = Locale.ENGLISH;
            String string = this.f5316a.getString(R.string.country_not_recognized);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.country_not_recognized)");
            Object[] objArr = new Object[1];
            int length = countryCode.length() - 1;
            int i = 0;
            boolean z = false;
            while (i <= length) {
                boolean z2 = Intrinsics.compare((int) countryCode.charAt(!z ? i : length), 32) <= 0;
                if (z) {
                    if (!z2) {
                        break;
                    }
                    length--;
                } else if (z2) {
                    i++;
                } else {
                    z = true;
                }
            }
            objArr[0] = countryCode.subSequence(i, length + 1).toString();
            format = String.format(locale, string, Arrays.copyOf(objArr, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
        } else if (retrieveCountryNamesFromIsd.size() == 1) {
            StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
            format = String.format(Locale.ENGLISH, "Country code %s - %s", Arrays.copyOf(new Object[]{countryCode, retrieveCountryNamesFromIsd.get(0)}, 2));
            Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
        } else if (retrieveCountryNamesFromIsd.size() > 1) {
            format = a(countryCodeName, countryCode);
        } else {
            StringCompanionObject stringCompanionObject3 = StringCompanionObject.INSTANCE;
            format = String.format(Locale.ENGLISH, "Country code %s - %s", Arrays.copyOf(new Object[]{countryCode, TextUtils.join(Constants.SEPARATOR_COMMA, retrieveCountryNamesFromIsd)}, 2));
            Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
        }
        ArrayList<String> retrieveCountryCodesFromIsd = AppUtils.retrieveCountryCodesFromIsd(this.f5316a, countryCode);
        Drawable drawable = null;
        if (retrieveCountryCodesFromIsd.isEmpty()) {
            countryCodeName = "";
        } else {
            try {
                if (retrieveCountryCodesFromIsd.size() > 1) {
                    Context context = this.f5316a;
                    String lowerCase = countryCodeName.toLowerCase();
                    Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
                    drawable = AppUtils.retriveCountryFlag(context, lowerCase);
                } else {
                    String str = AppUtils.retrieveCountryNamesFromIsdThreeChar(this.f5316a, countryCode).get(0);
                    Intrinsics.checkNotNullExpressionValue(str, "AppUtils.retrieveCountry…(context, countryCode)[0]");
                    countryCodeName = str;
                    Context context2 = this.f5316a;
                    String str2 = retrieveCountryCodesFromIsd.get(Integer.parseInt(AppConstants.DEFAULT_COUNTRY_INDEX.getValue()));
                    Intrinsics.checkNotNullExpressionValue(str2, "countryNames[AppConstant…NTRY_INDEX.value.toInt()]");
                    String lowerCase2 = str2.toLowerCase();
                    Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase()");
                    drawable = AppUtils.retriveCountryFlag(context2, lowerCase2);
                }
            } catch (Exception e) {
                e.printStackTrace();
                countryCodeName = AppConstants.EMPTY_STRING.getValue();
                StringCompanionObject stringCompanionObject4 = StringCompanionObject.INSTANCE;
                Locale locale2 = Locale.ENGLISH;
                String string2 = this.f5316a.getString(R.string.country_not_recognized);
                Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.string.country_not_recognized)");
                Object[] objArr2 = new Object[1];
                int length2 = countryCode.length() - 1;
                int i2 = 0;
                boolean z3 = false;
                while (i2 <= length2) {
                    boolean z4 = Intrinsics.compare((int) countryCode.charAt(!z3 ? i2 : length2), 32) <= 0;
                    if (z3) {
                        if (!z4) {
                            break;
                        }
                        length2--;
                    } else if (z4) {
                        i2++;
                    } else {
                        z3 = true;
                    }
                }
                objArr2[0] = countryCode.subSequence(i2, length2 + 1).toString();
                format = String.format(locale2, string2, Arrays.copyOf(objArr2, 1));
                Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
            }
        }
        CountryCodeModel countryCodeModel = new CountryCodeModel();
        countryCodeModel.setCountryCodeInfo(format);
        countryCodeModel.setFlagInfo(countryCodeName);
        countryCodeModel.setFlagDrawable(drawable);
        MutableLiveData<CountryCodeModel> mutableLiveData = this.b;
        if (mutableLiveData == null) {
            return;
        }
        mutableLiveData.setValue(countryCodeModel);
    }

    public final void setMSelectedCountryCode(@Nullable MutableLiveData<CountryCodeModel> mutableLiveData) {
        this.b = mutableLiveData;
    }

    public final void showPrivacyPolicy() {
        SessionManager sessionManager = SessionManager.getInstance(this.f5316a);
        Intent intent = new Intent(this.f5316a, ActivityInAppWebViewer.class);
        intent.putExtra(AppConstants.INTENT_EXTRA_URL.getValue(), sessionManager.getPrivacyPolicyDocUrl() != null ? sessionManager.getPrivacyPolicyDocUrl() : AppConstants.PRIVACY_POLICY_URL.getValue());
        intent.putExtra(AppConstants.INTENT_EXTRA_TITLE.getValue(), this.f5316a.getString(R.string.privacy_policy));
        this.f5316a.startActivity(intent);
    }

    public final void showTermsAndConditions() {
        SessionManager sessionManager = SessionManager.getInstance(this.f5316a);
        Intent intent = new Intent(this.f5316a, ActivityInAppWebViewer.class);
        intent.putExtra(AppConstants.INTENT_EXTRA_URL.getValue(), sessionManager.getLegalDocUrl() != null ? sessionManager.getLegalDocUrl() : AppConstants.EULA_URL.getValue());
        intent.putExtra(AppConstants.INTENT_EXTRA_TITLE.getValue(), this.f5316a.getString(R.string.terms_conditions_title));
        this.f5316a.startActivity(intent);
    }
}
