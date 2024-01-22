package com.google.i18n.phonenumbers.internal;

import com.google.i18n.phonenumbers.Phonemetadata;
/* loaded from: classes10.dex */
public interface MatcherApi {
    boolean matchesNationalNumber(String str, Phonemetadata.PhoneNumberDesc phoneNumberDesc, boolean z);

    boolean matchesPossibleNumber(String str, Phonemetadata.PhoneNumberDesc phoneNumberDesc);
}
