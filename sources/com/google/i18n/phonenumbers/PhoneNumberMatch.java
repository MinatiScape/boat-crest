package com.google.i18n.phonenumbers;

import com.clevertap.android.sdk.Constants;
import com.google.i18n.phonenumbers.Phonenumber;
import java.util.Arrays;
/* loaded from: classes10.dex */
public final class PhoneNumberMatch {

    /* renamed from: a  reason: collision with root package name */
    public final int f11513a;
    public final String b;
    public final Phonenumber.PhoneNumber c;

    public PhoneNumberMatch(int i, String str, Phonenumber.PhoneNumber phoneNumber) {
        if (i < 0) {
            throw new IllegalArgumentException("Start index must be >= 0.");
        }
        if (str != null && phoneNumber != null) {
            this.f11513a = i;
            this.b = str;
            this.c = phoneNumber;
            return;
        }
        throw null;
    }

    public int end() {
        return this.f11513a + this.b.length();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof PhoneNumberMatch) {
            PhoneNumberMatch phoneNumberMatch = (PhoneNumberMatch) obj;
            return this.b.equals(phoneNumberMatch.b) && this.f11513a == phoneNumberMatch.f11513a && this.c.equals(phoneNumberMatch.c);
        }
        return false;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.f11513a), this.b, this.c});
    }

    public Phonenumber.PhoneNumber number() {
        return this.c;
    }

    public String rawString() {
        return this.b;
    }

    public int start() {
        return this.f11513a;
    }

    public String toString() {
        int start = start();
        int end = end();
        String valueOf = String.valueOf(this.b);
        StringBuilder sb = new StringBuilder(valueOf.length() + 43);
        sb.append("PhoneNumberMatch [");
        sb.append(start);
        sb.append(Constants.SEPARATOR_COMMA);
        sb.append(end);
        sb.append(") ");
        sb.append(valueOf);
        return sb.toString();
    }
}
