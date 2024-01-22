package com.google.android.gms.internal.firebase_ml;

import java.io.IOException;
/* loaded from: classes7.dex */
public class zzxk extends IOException {
    private zzyk zzcmk;

    public zzxk(String str) {
        super(str);
        this.zzcmk = null;
    }

    public static zzxk zzve() {
        return new zzxk("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }

    public static zzxk zzvf() {
        return new zzxk("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    public static zzxk zzvg() {
        return new zzxk("Protocol message contained an invalid tag (zero).");
    }

    public static zzxn zzvh() {
        return new zzxn("Protocol message tag had invalid wire type.");
    }

    public static zzxk zzvi() {
        return new zzxk("Failed to parse the message.");
    }

    public static zzxk zzvj() {
        return new zzxk("Protocol message had invalid UTF-8.");
    }

    public final zzxk zzg(zzyk zzykVar) {
        this.zzcmk = zzykVar;
        return this;
    }
}
