package com.google.i18n.phonenumbers.internal;

import com.google.i18n.phonenumbers.Phonemetadata;
import com.google.i18n.phonenumbers.RegexCache;
import java.util.regex.Matcher;
/* loaded from: classes10.dex */
public final class RegexBasedMatcher implements MatcherApi {

    /* renamed from: a  reason: collision with root package name */
    public final RegexCache f11521a = new RegexCache(100);

    public static MatcherApi create() {
        return new RegexBasedMatcher();
    }

    @Override // com.google.i18n.phonenumbers.internal.MatcherApi
    public boolean matchesNationalNumber(String str, Phonemetadata.PhoneNumberDesc phoneNumberDesc, boolean z) {
        Matcher matcher = this.f11521a.getPatternForRegex(phoneNumberDesc.getNationalNumberPattern()).matcher(str);
        return matcher.matches() || (z && matcher.lookingAt());
    }

    @Override // com.google.i18n.phonenumbers.internal.MatcherApi
    public boolean matchesPossibleNumber(String str, Phonemetadata.PhoneNumberDesc phoneNumberDesc) {
        return this.f11521a.getPatternForRegex(phoneNumberDesc.getPossibleNumberPattern()).matcher(str).matches();
    }
}
