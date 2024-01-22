package com.google.android.gms.internal.firebase_ml;

import java.io.EOFException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes7.dex */
public final class w0 extends zzib {
    public final zzsz c;
    public final zzig d;
    public List<String> e = new ArrayList();
    public zzih f;
    public String g;

    public w0(zzig zzigVar, zzsz zzszVar) {
        this.d = zzigVar;
        this.c = zzszVar;
        zzszVar.setLenient(true);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzib
    public final void close() throws IOException {
        this.c.close();
    }

    public final void f() {
        zzih zzihVar = this.f;
        zzml.checkArgument(zzihVar == zzih.VALUE_NUMBER_INT || zzihVar == zzih.VALUE_NUMBER_FLOAT);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzib
    public final int getIntValue() {
        f();
        return Integer.parseInt(this.g);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzib
    public final String getText() {
        return this.g;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzib
    public final zzhx zzhb() {
        return this.d;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzib
    public final zzih zzhc() throws IOException {
        zztb zztbVar;
        zzih zzihVar;
        zzih zzihVar2 = this.f;
        if (zzihVar2 != null) {
            int i = v0.f8741a[zzihVar2.ordinal()];
            if (i == 1) {
                this.c.beginArray();
                this.e.add(null);
            } else if (i == 2) {
                this.c.beginObject();
                this.e.add(null);
            }
        }
        try {
            zztbVar = this.c.zzrb();
        } catch (EOFException unused) {
            zztbVar = zztb.END_DOCUMENT;
        }
        switch (v0.b[zztbVar.ordinal()]) {
            case 1:
                this.g = "[";
                this.f = zzih.START_ARRAY;
                break;
            case 2:
                this.g = "]";
                this.f = zzih.END_ARRAY;
                List<String> list = this.e;
                list.remove(list.size() - 1);
                this.c.endArray();
                break;
            case 3:
                this.g = "{";
                this.f = zzih.START_OBJECT;
                break;
            case 4:
                this.g = "}";
                this.f = zzih.END_OBJECT;
                List<String> list2 = this.e;
                list2.remove(list2.size() - 1);
                this.c.endObject();
                break;
            case 5:
                if (this.c.nextBoolean()) {
                    this.g = "true";
                    this.f = zzih.VALUE_TRUE;
                    break;
                } else {
                    this.g = "false";
                    this.f = zzih.VALUE_FALSE;
                    break;
                }
            case 6:
                this.g = "null";
                this.f = zzih.VALUE_NULL;
                this.c.nextNull();
                break;
            case 7:
                this.g = this.c.nextString();
                this.f = zzih.VALUE_STRING;
                break;
            case 8:
                String nextString = this.c.nextString();
                this.g = nextString;
                if (nextString.indexOf(46) == -1) {
                    zzihVar = zzih.VALUE_NUMBER_INT;
                } else {
                    zzihVar = zzih.VALUE_NUMBER_FLOAT;
                }
                this.f = zzihVar;
                break;
            case 9:
                this.g = this.c.nextName();
                this.f = zzih.FIELD_NAME;
                List<String> list3 = this.e;
                list3.set(list3.size() - 1, this.g);
                break;
            default:
                this.g = null;
                this.f = null;
                break;
        }
        return this.f;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzib
    public final zzih zzhd() {
        return this.f;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzib
    public final String zzhe() {
        if (this.e.isEmpty()) {
            return null;
        }
        List<String> list = this.e;
        return list.get(list.size() - 1);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzib
    public final zzib zzhf() throws IOException {
        zzih zzihVar = this.f;
        if (zzihVar != null) {
            int i = v0.f8741a[zzihVar.ordinal()];
            if (i == 1) {
                this.c.skipValue();
                this.g = "]";
                this.f = zzih.END_ARRAY;
            } else if (i == 2) {
                this.c.skipValue();
                this.g = "}";
                this.f = zzih.END_OBJECT;
            }
        }
        return this;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzib
    public final byte zzhg() {
        f();
        return Byte.parseByte(this.g);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzib
    public final short zzhh() {
        f();
        return Short.parseShort(this.g);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzib
    public final float zzhi() {
        f();
        return Float.parseFloat(this.g);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzib
    public final long zzhj() {
        f();
        return Long.parseLong(this.g);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzib
    public final double zzhk() {
        f();
        return Double.parseDouble(this.g);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzib
    public final BigInteger zzhl() {
        f();
        return new BigInteger(this.g);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzib
    public final BigDecimal zzhm() {
        f();
        return new BigDecimal(this.g);
    }
}
