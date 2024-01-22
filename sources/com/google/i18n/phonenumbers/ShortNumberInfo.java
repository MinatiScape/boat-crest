package com.google.i18n.phonenumbers;

import com.google.i18n.phonenumbers.Phonemetadata;
import com.google.i18n.phonenumbers.Phonenumber;
import com.google.i18n.phonenumbers.internal.MatcherApi;
import com.google.i18n.phonenumbers.internal.RegexBasedMatcher;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
/* loaded from: classes10.dex */
public class ShortNumberInfo {
    public static final Logger c = Logger.getLogger(ShortNumberInfo.class.getName());
    public static final ShortNumberInfo d = new ShortNumberInfo(RegexBasedMatcher.create());
    public static final Set<String> e;

    /* renamed from: a  reason: collision with root package name */
    public final MatcherApi f11518a;
    public final Map<Integer, List<String>> b = CountryCodeToRegionCodeMap.a();

    /* loaded from: classes10.dex */
    public enum ShortNumberCost {
        TOLL_FREE,
        STANDARD_RATE,
        PREMIUM_RATE,
        UNKNOWN_COST
    }

    /* loaded from: classes10.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f11519a;

        static {
            int[] iArr = new int[ShortNumberCost.values().length];
            f11519a = iArr;
            try {
                iArr[ShortNumberCost.PREMIUM_RATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f11519a[ShortNumberCost.UNKNOWN_COST.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f11519a[ShortNumberCost.STANDARD_RATE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f11519a[ShortNumberCost.TOLL_FREE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    static {
        HashSet hashSet = new HashSet();
        e = hashSet;
        hashSet.add("BR");
        hashSet.add("CL");
        hashSet.add("NI");
    }

    public ShortNumberInfo(MatcherApi matcherApi) {
        this.f11518a = matcherApi;
    }

    public static String a(Phonenumber.PhoneNumber phoneNumber) {
        StringBuilder sb = new StringBuilder();
        if (phoneNumber.isItalianLeadingZero()) {
            char[] cArr = new char[phoneNumber.getNumberOfLeadingZeros()];
            Arrays.fill(cArr, '0');
            sb.append(new String(cArr));
        }
        sb.append(phoneNumber.getNationalNumber());
        return sb.toString();
    }

    public static ShortNumberInfo getInstance() {
        return d;
    }

    public final String b(Phonenumber.PhoneNumber phoneNumber, List<String> list) {
        if (list.size() == 0) {
            return null;
        }
        if (list.size() == 1) {
            return list.get(0);
        }
        String a2 = a(phoneNumber);
        for (String str : list) {
            Phonemetadata.PhoneMetadata c2 = com.google.i18n.phonenumbers.a.c(str);
            if (c2 != null && f(a2, c2.getShortCode())) {
                return str;
            }
        }
        return null;
    }

    public final List<String> c(int i) {
        List<String> list = this.b.get(Integer.valueOf(i));
        if (list == null) {
            list = new ArrayList<>(0);
        }
        return Collections.unmodifiableList(list);
    }

    public boolean connectsToEmergencyNumber(String str, String str2) {
        return e(str, str2, true);
    }

    public Set<String> d() {
        return Collections.unmodifiableSet(com.google.i18n.phonenumbers.a.d());
    }

    public final boolean e(String str, String str2, boolean z) {
        Phonemetadata.PhoneMetadata c2;
        String g = PhoneNumberUtil.g(str);
        boolean z2 = false;
        if (PhoneNumberUtil.s.matcher(g).lookingAt() || (c2 = com.google.i18n.phonenumbers.a.c(str2)) == null || !c2.hasEmergency()) {
            return false;
        }
        String normalizeDigitsOnly = PhoneNumberUtil.normalizeDigitsOnly(g);
        Phonemetadata.PhoneNumberDesc emergency = c2.getEmergency();
        if (z && !e.contains(str2)) {
            z2 = true;
        }
        return this.f11518a.matchesNationalNumber(normalizeDigitsOnly, emergency, z2);
    }

    public final boolean f(String str, Phonemetadata.PhoneNumberDesc phoneNumberDesc) {
        return this.f11518a.matchesPossibleNumber(str, phoneNumberDesc) && this.f11518a.matchesNationalNumber(str, phoneNumberDesc, false);
    }

    public ShortNumberCost getExpectedCost(Phonenumber.PhoneNumber phoneNumber) {
        List<String> c2 = c(phoneNumber.getCountryCode());
        if (c2.size() == 0) {
            return ShortNumberCost.UNKNOWN_COST;
        }
        if (c2.size() == 1) {
            return getExpectedCostForRegion(phoneNumber, c2.get(0));
        }
        ShortNumberCost shortNumberCost = ShortNumberCost.TOLL_FREE;
        for (String str : c2) {
            ShortNumberCost expectedCostForRegion = getExpectedCostForRegion(phoneNumber, str);
            int i = a.f11519a[expectedCostForRegion.ordinal()];
            if (i == 1) {
                return ShortNumberCost.PREMIUM_RATE;
            }
            if (i == 2) {
                shortNumberCost = ShortNumberCost.UNKNOWN_COST;
            } else if (i != 3) {
                if (i != 4) {
                    Logger logger = c;
                    Level level = Level.SEVERE;
                    String valueOf = String.valueOf(expectedCostForRegion);
                    StringBuilder sb = new StringBuilder(valueOf.length() + 30);
                    sb.append("Unrecognised cost for region: ");
                    sb.append(valueOf);
                    logger.log(level, sb.toString());
                }
            } else if (shortNumberCost != ShortNumberCost.UNKNOWN_COST) {
                shortNumberCost = ShortNumberCost.STANDARD_RATE;
            }
        }
        return shortNumberCost;
    }

    @Deprecated
    public ShortNumberCost getExpectedCostForRegion(String str, String str2) {
        Phonemetadata.PhoneMetadata c2 = com.google.i18n.phonenumbers.a.c(str2);
        if (c2 == null) {
            return ShortNumberCost.UNKNOWN_COST;
        }
        if (f(str, c2.getPremiumRate())) {
            return ShortNumberCost.PREMIUM_RATE;
        }
        if (f(str, c2.getStandardRate())) {
            return ShortNumberCost.STANDARD_RATE;
        }
        if (f(str, c2.getTollFree())) {
            return ShortNumberCost.TOLL_FREE;
        }
        if (isEmergencyNumber(str, str2)) {
            return ShortNumberCost.TOLL_FREE;
        }
        return ShortNumberCost.UNKNOWN_COST;
    }

    public boolean isCarrierSpecific(Phonenumber.PhoneNumber phoneNumber) {
        String b = b(phoneNumber, c(phoneNumber.getCountryCode()));
        String a2 = a(phoneNumber);
        Phonemetadata.PhoneMetadata c2 = com.google.i18n.phonenumbers.a.c(b);
        return c2 != null && f(a2, c2.getCarrierSpecific());
    }

    public boolean isEmergencyNumber(String str, String str2) {
        return e(str, str2, false);
    }

    public boolean isPossibleShortNumber(Phonenumber.PhoneNumber phoneNumber) {
        List<String> c2 = c(phoneNumber.getCountryCode());
        String a2 = a(phoneNumber);
        for (String str : c2) {
            if (this.f11518a.matchesPossibleNumber(a2, com.google.i18n.phonenumbers.a.c(str).getGeneralDesc())) {
                return true;
            }
        }
        return false;
    }

    @Deprecated
    public boolean isPossibleShortNumberForRegion(String str, String str2) {
        Phonemetadata.PhoneMetadata c2 = com.google.i18n.phonenumbers.a.c(str2);
        if (c2 == null) {
            return false;
        }
        return this.f11518a.matchesPossibleNumber(str, c2.getGeneralDesc());
    }

    public boolean isValidShortNumber(Phonenumber.PhoneNumber phoneNumber) {
        List<String> c2 = c(phoneNumber.getCountryCode());
        String b = b(phoneNumber, c2);
        if (c2.size() <= 1 || b == null) {
            return isValidShortNumberForRegion(phoneNumber, b);
        }
        return true;
    }

    @Deprecated
    public boolean isValidShortNumberForRegion(String str, String str2) {
        Phonemetadata.PhoneMetadata c2 = com.google.i18n.phonenumbers.a.c(str2);
        if (c2 != null && f(str, c2.getGeneralDesc())) {
            return f(str, c2.getShortCode());
        }
        return false;
    }

    public boolean isPossibleShortNumberForRegion(Phonenumber.PhoneNumber phoneNumber, String str) {
        Phonemetadata.PhoneMetadata c2 = com.google.i18n.phonenumbers.a.c(str);
        if (c2 == null) {
            return false;
        }
        return this.f11518a.matchesPossibleNumber(a(phoneNumber), c2.getGeneralDesc());
    }

    public boolean isValidShortNumberForRegion(Phonenumber.PhoneNumber phoneNumber, String str) {
        Phonemetadata.PhoneMetadata c2 = com.google.i18n.phonenumbers.a.c(str);
        if (c2 == null) {
            return false;
        }
        String a2 = a(phoneNumber);
        if (f(a2, c2.getGeneralDesc())) {
            return f(a2, c2.getShortCode());
        }
        return false;
    }

    public ShortNumberCost getExpectedCostForRegion(Phonenumber.PhoneNumber phoneNumber, String str) {
        Phonemetadata.PhoneMetadata c2 = com.google.i18n.phonenumbers.a.c(str);
        if (c2 == null) {
            return ShortNumberCost.UNKNOWN_COST;
        }
        String a2 = a(phoneNumber);
        if (f(a2, c2.getPremiumRate())) {
            return ShortNumberCost.PREMIUM_RATE;
        }
        if (f(a2, c2.getStandardRate())) {
            return ShortNumberCost.STANDARD_RATE;
        }
        if (f(a2, c2.getTollFree())) {
            return ShortNumberCost.TOLL_FREE;
        }
        if (isEmergencyNumber(a2, str)) {
            return ShortNumberCost.TOLL_FREE;
        }
        return ShortNumberCost.UNKNOWN_COST;
    }
}
