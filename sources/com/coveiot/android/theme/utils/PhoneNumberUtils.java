package com.coveiot.android.theme.utils;

import android.content.Context;
import android.telephony.TelephonyManager;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.m;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class PhoneNumberUtils {
    @NotNull
    public static final PhoneNumberUtils INSTANCE = new PhoneNumberUtils();

    public final String a(Context context) {
        String networkCountryIso;
        try {
            Object systemService = context.getSystemService("phone");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.telephony.TelephonyManager");
            TelephonyManager telephonyManager = (TelephonyManager) systemService;
            String simCountryIso = telephonyManager.getSimCountryIso();
            if (simCountryIso != null && simCountryIso.length() == 2) {
                String upperCase = simCountryIso.toUpperCase();
                Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase()");
                return upperCase;
            } else if (telephonyManager.getPhoneType() == 2 || (networkCountryIso = telephonyManager.getNetworkCountryIso()) == null || networkCountryIso.length() != 2) {
                return null;
            } else {
                String upperCase2 = networkCountryIso.toUpperCase();
                Intrinsics.checkNotNullExpressionValue(upperCase2, "this as java.lang.String).toUpperCase()");
                return upperCase2;
            }
        } catch (Exception unused) {
            return null;
        }
    }

    public final String b(String str) {
        String replace$default = m.replace$default(str, "[^\\d+]", "", false, 4, (Object) null);
        int length = replace$default.length() - 1;
        int i = 0;
        boolean z = false;
        while (i <= length) {
            boolean z2 = Intrinsics.compare((int) replace$default.charAt(!z ? i : length), 32) <= 0;
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
        boolean startsWith$default = m.startsWith$default(replace$default.subSequence(i, length + 1).toString(), "+", false, 2, null);
        String replace$default2 = m.replace$default(str, "[^\\d]", "", false, 4, (Object) null);
        if (startsWith$default) {
            return '+' + replace$default2;
        }
        return replace$default2;
    }

    @Nullable
    public final String verifyPhoneNumber(@NotNull Context context, @NotNull String mobileNo) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(mobileNo, "mobileNo");
        try {
            Phonenumber.PhoneNumber parse = PhoneNumberUtil.getInstance().parse(b(mobileNo), a(context));
            StringBuilder sb = new StringBuilder();
            sb.append('+');
            sb.append(parse.getCountryCode());
            sb.append(parse.getNationalNumber());
            return sb.toString();
        } catch (NumberParseException unused) {
            return null;
        }
    }
}
