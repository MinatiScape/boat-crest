package com.google.android.gms.internal.vision;

import java.io.IOException;
/* loaded from: classes10.dex */
public class zzhc extends IOException {
    private zzic zzxn;

    public zzhc(String str) {
        super(str);
        this.zzxn = null;
    }

    public static zzhc zzgm() {
        return new zzhc("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }

    public static zzhc zzgn() {
        return new zzhc("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    public static zzhc zzgo() {
        return new zzhc("CodedInputStream encountered a malformed varint.");
    }

    public static zzhc zzgp() {
        return new zzhc("Protocol message contained an invalid tag (zero).");
    }

    public static zzhc zzgq() {
        return new zzhc("Protocol message end-group tag did not match expected tag.");
    }

    public static zzhb zzgr() {
        return new zzhb("Protocol message tag had invalid wire type.");
    }

    public static zzhc zzgs() {
        return new zzhc("Failed to parse the message.");
    }

    public static zzhc zzgt() {
        return new zzhc("Protocol message had invalid UTF-8.");
    }

    public final zzhc zzg(zzic zzicVar) {
        this.zzxn = zzicVar;
        return this;
    }
}
