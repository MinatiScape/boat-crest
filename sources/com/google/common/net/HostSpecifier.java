package com.google.common.net;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import java.net.InetAddress;
import java.text.ParseException;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@Beta
@GwtIncompatible
/* loaded from: classes10.dex */
public final class HostSpecifier {

    /* renamed from: a  reason: collision with root package name */
    public final String f10709a;

    public HostSpecifier(String str) {
        this.f10709a = str;
    }

    public static HostSpecifier from(String str) throws ParseException {
        try {
            return fromValid(str);
        } catch (IllegalArgumentException e) {
            String valueOf = String.valueOf(str);
            ParseException parseException = new ParseException(valueOf.length() != 0 ? "Invalid host specifier: ".concat(valueOf) : new String("Invalid host specifier: "), 0);
            parseException.initCause(e);
            throw parseException;
        }
    }

    public static HostSpecifier fromValid(String str) {
        HostAndPort fromString = HostAndPort.fromString(str);
        Preconditions.checkArgument(!fromString.hasPort());
        String host = fromString.getHost();
        InetAddress inetAddress = null;
        try {
            inetAddress = InetAddresses.forString(host);
        } catch (IllegalArgumentException unused) {
        }
        if (inetAddress != null) {
            return new HostSpecifier(InetAddresses.toUriString(inetAddress));
        }
        InternetDomainName from = InternetDomainName.from(host);
        if (from.hasPublicSuffix()) {
            return new HostSpecifier(from.toString());
        }
        String valueOf = String.valueOf(host);
        throw new IllegalArgumentException(valueOf.length() != 0 ? "Domain name does not have a recognized public suffix: ".concat(valueOf) : new String("Domain name does not have a recognized public suffix: "));
    }

    public static boolean isValid(String str) {
        try {
            fromValid(str);
            return true;
        } catch (IllegalArgumentException unused) {
            return false;
        }
    }

    public boolean equals(@NullableDecl Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof HostSpecifier) {
            return this.f10709a.equals(((HostSpecifier) obj).f10709a);
        }
        return false;
    }

    public int hashCode() {
        return this.f10709a.hashCode();
    }

    public String toString() {
        return this.f10709a;
    }
}
