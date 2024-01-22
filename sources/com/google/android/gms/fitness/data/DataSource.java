package com.google.android.gms.fitness.data;

import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import com.google.android.gms.internal.fitness.zzkq;
import java.util.Locale;
import org.jose4j.jwk.RsaJsonWebKey;
@SafeParcelable.Class(creator = "DataSourceCreator")
@SafeParcelable.Reserved({2, 7, 8, 1000})
/* loaded from: classes6.dex */
public class DataSource extends AbstractSafeParcelable {
    @NonNull
    @ShowFirstParty
    public static final Parcelable.Creator<DataSource> CREATOR;
    @NonNull
    public static final String EXTRA_DATA_SOURCE = "vnd.google.fitness.data_source";
    public static final int TYPE_DERIVED = 1;
    public static final int TYPE_RAW = 0;
    public static final String n;
    public static final String o;
    @SafeParcelable.Field(getter = "getDataType", id = 1)
    public final DataType h;
    @SafeParcelable.Field(getter = "getType", id = 3)
    public final int i;
    @Nullable
    @SafeParcelable.Field(getter = "getDevice", id = 4)
    public final Device j;
    @Nullable
    @SafeParcelable.Field(getter = "getApplication", id = 5)
    public final zza k;
    @SafeParcelable.Field(getter = "getStreamName", id = 6)
    public final String l;
    public final String m;

    /* loaded from: classes6.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public DataType f8431a;
        public Device c;
        public zza d;
        public int b = -1;
        public String e = "";

        @NonNull
        public final DataSource build() {
            Preconditions.checkState(this.f8431a != null, "Must set data type");
            Preconditions.checkState(this.b >= 0, "Must set data source type");
            return new DataSource(this);
        }

        @NonNull
        public final Builder setAppPackageName(@NonNull String str) {
            this.d = zza.zza(str);
            return this;
        }

        @NonNull
        public final Builder setDataType(@NonNull DataType dataType) {
            this.f8431a = dataType;
            return this;
        }

        @NonNull
        public final Builder setDevice(@NonNull Device device) {
            this.c = device;
            return this;
        }

        @NonNull
        public final Builder setStreamName(@NonNull String str) {
            Preconditions.checkArgument(str != null, "Must specify a valid stream name");
            this.e = str;
            return this;
        }

        @NonNull
        public final Builder setType(int i) {
            this.b = i;
            return this;
        }

        @NonNull
        public final Builder setAppPackageName(@NonNull Context context) {
            return setAppPackageName(context.getPackageName());
        }
    }

    static {
        String name = zzkq.zzb.zzc.RAW.name();
        Locale locale = Locale.ROOT;
        n = name.toLowerCase(locale);
        o = zzkq.zzb.zzc.DERIVED.name().toLowerCase(locale);
        CREATOR = new zzk();
    }

    @SafeParcelable.Constructor
    public DataSource(@SafeParcelable.Param(id = 1) DataType dataType, @SafeParcelable.Param(id = 3) int i, @Nullable @SafeParcelable.Param(id = 4) Device device, @Nullable @SafeParcelable.Param(id = 5) zza zzaVar, @SafeParcelable.Param(id = 6) String str) {
        this.h = dataType;
        this.i = i;
        this.j = device;
        this.k = zzaVar;
        this.l = str;
        StringBuilder sb = new StringBuilder();
        sb.append(a(i));
        sb.append(":");
        sb.append(dataType.getName());
        if (zzaVar != null) {
            sb.append(":");
            sb.append(zzaVar.getPackageName());
        }
        if (device != null) {
            sb.append(":");
            sb.append(device.getStreamIdentifier());
        }
        if (str != null) {
            sb.append(":");
            sb.append(str);
        }
        this.m = sb.toString();
    }

    public static String a(int i) {
        if (i != 0) {
            if (i != 1) {
                return o;
            }
            return o;
        }
        return n;
    }

    @Nullable
    public static DataSource extract(@NonNull Intent intent) {
        if (intent == null) {
            return null;
        }
        return (DataSource) SafeParcelableSerializer.deserializeFromIntentExtra(intent, EXTRA_DATA_SOURCE, CREATOR);
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof DataSource) {
            return this.m.equals(((DataSource) obj).m);
        }
        return false;
    }

    @Nullable
    public String getAppPackageName() {
        zza zzaVar = this.k;
        if (zzaVar == null) {
            return null;
        }
        return zzaVar.getPackageName();
    }

    @NonNull
    public DataType getDataType() {
        return this.h;
    }

    @Nullable
    public Device getDevice() {
        return this.j;
    }

    @NonNull
    public String getStreamIdentifier() {
        return this.m;
    }

    @NonNull
    public String getStreamName() {
        return this.l;
    }

    public int getType() {
        return this.i;
    }

    public int hashCode() {
        return this.m.hashCode();
    }

    @NonNull
    @ShowFirstParty
    public final String toDebugString() {
        String concat;
        String str;
        int i = this.i;
        String str2 = i != 0 ? i != 1 ? "?" : "d" : RsaJsonWebKey.PRIME_FACTOR_OTHER_MEMBER_NAME;
        String zzm = this.h.zzm();
        zza zzaVar = this.k;
        String str3 = "";
        if (zzaVar == null) {
            concat = "";
        } else if (zzaVar.equals(zza.zzlb)) {
            concat = ":gms";
        } else {
            String valueOf = String.valueOf(this.k.getPackageName());
            concat = valueOf.length() != 0 ? ":".concat(valueOf) : new String(":");
        }
        Device device = this.j;
        if (device != null) {
            String model = device.getModel();
            String uid = this.j.getUid();
            StringBuilder sb = new StringBuilder(String.valueOf(model).length() + 2 + String.valueOf(uid).length());
            sb.append(":");
            sb.append(model);
            sb.append(":");
            sb.append(uid);
            str = sb.toString();
        } else {
            str = "";
        }
        String str4 = this.l;
        if (str4 != null) {
            String valueOf2 = String.valueOf(str4);
            str3 = valueOf2.length() != 0 ? ":".concat(valueOf2) : new String(":");
        }
        StringBuilder sb2 = new StringBuilder(str2.length() + 1 + String.valueOf(zzm).length() + String.valueOf(concat).length() + String.valueOf(str).length() + String.valueOf(str3).length());
        sb2.append(str2);
        sb2.append(":");
        sb2.append(zzm);
        sb2.append(concat);
        sb2.append(str);
        sb2.append(str3);
        return sb2.toString();
    }

    @NonNull
    public String toString() {
        StringBuilder sb = new StringBuilder("DataSource{");
        sb.append(a(this.i));
        if (this.k != null) {
            sb.append(":");
            sb.append(this.k);
        }
        if (this.j != null) {
            sb.append(":");
            sb.append(this.j);
        }
        if (this.l != null) {
            sb.append(":");
            sb.append(this.l);
        }
        sb.append(":");
        sb.append(this.h);
        sb.append("}");
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getDataType(), i, false);
        SafeParcelWriter.writeInt(parcel, 3, getType());
        SafeParcelWriter.writeParcelable(parcel, 4, getDevice(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 5, this.k, i, false);
        SafeParcelWriter.writeString(parcel, 6, getStreamName(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Nullable
    @ShowFirstParty
    public final zza zzj() {
        return this.k;
    }

    public DataSource(Builder builder) {
        this(builder.f8431a, builder.b, builder.c, builder.d, builder.e);
    }
}
