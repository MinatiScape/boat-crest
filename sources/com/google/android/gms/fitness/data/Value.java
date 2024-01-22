package com.google.android.gms.fitness.data;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.HexDumpUtils;
import com.google.android.gms.internal.fitness.zzko;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
@SafeParcelable.Class(creator = "ValueCreator")
@SafeParcelable.Reserved({1000})
/* loaded from: classes6.dex */
public final class Value extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<Value> CREATOR = new zzal();
    @SafeParcelable.Field(getter = "getFormat", id = 1)
    public final int h;
    @SafeParcelable.Field(getter = "isSet", id = 2)
    public boolean i;
    @SafeParcelable.Field(getter = "getValue", id = 3)
    public float j;
    @Nullable
    @SafeParcelable.Field(getter = "getStringValue", id = 4)
    public String k;
    @Nullable
    @SafeParcelable.Field(getter = "getMapValue", id = 5, type = "android.os.Bundle")
    public Map<String, MapValue> l;
    @Nullable
    @SafeParcelable.Field(getter = "getIntArrayValue", id = 6)
    public int[] m;
    @Nullable
    @SafeParcelable.Field(getter = "getFloatArrayValue", id = 7)
    public float[] n;
    @Nullable
    @SafeParcelable.Field(getter = "getBlob", id = 8)
    public byte[] o;

    @ShowFirstParty
    public Value(int i) {
        this(i, false, 0.0f, null, null, null, null, null);
    }

    @NonNull
    public final String asActivity() {
        return zzko.getName(asInt());
    }

    public final float asFloat() {
        Preconditions.checkState(this.h == 2, "Value is not in float format");
        return this.j;
    }

    public final int asInt() {
        Preconditions.checkState(this.h == 1, "Value is not in int format");
        return Float.floatToRawIntBits(this.j);
    }

    @NonNull
    public final String asString() {
        Preconditions.checkState(this.h == 3, "Value is not in string format");
        String str = this.k;
        return str == null ? "" : str;
    }

    @Deprecated
    public final void clearKey(@NonNull String str) {
        Preconditions.checkState(this.h == 4, "Attempting to set a key's value to a field that is not in FLOAT_MAP format.  Please check the data type definition and use the right format.");
        Map<String, MapValue> map = this.l;
        if (map != null) {
            map.remove(str);
        }
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Value) {
            Value value = (Value) obj;
            int i = this.h;
            if (i == value.h && this.i == value.i) {
                if (i != 1) {
                    if (i != 3) {
                        if (i != 4) {
                            if (i != 5) {
                                if (i != 6) {
                                    if (i != 7) {
                                        return this.j == value.j;
                                    }
                                    return Arrays.equals(this.o, value.o);
                                }
                                return Arrays.equals(this.n, value.n);
                            }
                            return Arrays.equals(this.m, value.m);
                        }
                        return Objects.equal(this.l, value.l);
                    }
                    return Objects.equal(this.k, value.k);
                } else if (asInt() == value.asInt()) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public final int getFormat() {
        return this.h;
    }

    @Nullable
    public final Float getKeyValue(@NonNull String str) {
        Preconditions.checkState(this.h == 4, "Value is not in float map format");
        Map<String, MapValue> map = this.l;
        if (map == null || !map.containsKey(str)) {
            return null;
        }
        return Float.valueOf(this.l.get(str).asFloat());
    }

    public final int hashCode() {
        return Objects.hashCode(Float.valueOf(this.j), this.k, this.l, this.m, this.n, this.o);
    }

    public final boolean isSet() {
        return this.i;
    }

    @Deprecated
    public final void setActivity(@NonNull String str) {
        setInt(zzko.zzo(str));
    }

    @Deprecated
    public final void setFloat(float f) {
        Preconditions.checkState(this.h == 2, "Attempting to set an float value to a field that is not in FLOAT format.  Please check the data type definition and use the right format.");
        this.i = true;
        this.j = f;
    }

    @Deprecated
    public final void setInt(int i) {
        Preconditions.checkState(this.h == 1, "Attempting to set an int value to a field that is not in INT32 format.  Please check the data type definition and use the right format.");
        this.i = true;
        this.j = Float.intBitsToFloat(i);
    }

    @Deprecated
    public final void setKeyValue(@NonNull String str, float f) {
        Preconditions.checkState(this.h == 4, "Attempting to set a key's value to a field that is not in FLOAT_MAP format.  Please check the data type definition and use the right format.");
        this.i = true;
        if (this.l == null) {
            this.l = new HashMap();
        }
        this.l.put(str, MapValue.zza(f));
    }

    @Deprecated
    public final void setString(@NonNull String str) {
        Preconditions.checkState(this.h == 3, "Attempting to set a string value to a field that is not in STRING format.  Please check the data type definition and use the right format.");
        this.i = true;
        this.k = str;
    }

    @NonNull
    public final String toString() {
        String dump;
        if (this.i) {
            switch (this.h) {
                case 1:
                    return Integer.toString(asInt());
                case 2:
                    return Float.toString(this.j);
                case 3:
                    String str = this.k;
                    return str == null ? "" : str;
                case 4:
                    return this.l == null ? "" : new TreeMap(this.l).toString();
                case 5:
                    return Arrays.toString(this.m);
                case 6:
                    return Arrays.toString(this.n);
                case 7:
                    byte[] bArr = this.o;
                    return (bArr == null || (dump = HexDumpUtils.dump(bArr, 0, bArr.length, false)) == null) ? "" : dump;
                default:
                    return "unknown";
            }
        }
        return "unset";
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(@NonNull Parcel parcel, int i) {
        Bundle bundle;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, getFormat());
        SafeParcelWriter.writeBoolean(parcel, 2, isSet());
        SafeParcelWriter.writeFloat(parcel, 3, this.j);
        SafeParcelWriter.writeString(parcel, 4, this.k, false);
        if (this.l == null) {
            bundle = null;
        } else {
            bundle = new Bundle(this.l.size());
            for (Map.Entry<String, MapValue> entry : this.l.entrySet()) {
                bundle.putParcelable(entry.getKey(), entry.getValue());
            }
        }
        SafeParcelWriter.writeBundle(parcel, 5, bundle, false);
        SafeParcelWriter.writeIntArray(parcel, 6, this.m, false);
        SafeParcelWriter.writeFloatArray(parcel, 7, this.n, false);
        SafeParcelWriter.writeByteArray(parcel, 8, this.o, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @ShowFirstParty
    @Deprecated
    public final void zza(@NonNull Map<String, Float> map) {
        Preconditions.checkState(this.h == 4, "Attempting to set a float map value to a field that is not in FLOAT_MAP format.  Please check the data type definition and use the right format.");
        this.i = true;
        HashMap hashMap = new HashMap();
        for (Map.Entry<String, Float> entry : map.entrySet()) {
            hashMap.put(entry.getKey(), MapValue.zza(entry.getValue().floatValue()));
        }
        this.l = hashMap;
    }

    @SafeParcelable.Constructor
    public Value(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) boolean z, @SafeParcelable.Param(id = 3) float f, @Nullable @SafeParcelable.Param(id = 4) String str, @Nullable @SafeParcelable.Param(id = 5) Bundle bundle, @Nullable @SafeParcelable.Param(id = 6) int[] iArr, @Nullable @SafeParcelable.Param(id = 7) float[] fArr, @Nullable @SafeParcelable.Param(id = 8) byte[] bArr) {
        ArrayMap arrayMap;
        this.h = i;
        this.i = z;
        this.j = f;
        this.k = str;
        if (bundle == null) {
            arrayMap = null;
        } else {
            bundle.setClassLoader((ClassLoader) Preconditions.checkNotNull(MapValue.class.getClassLoader()));
            arrayMap = new ArrayMap(bundle.size());
            for (String str2 : bundle.keySet()) {
                arrayMap.put(str2, (MapValue) Preconditions.checkNotNull((MapValue) bundle.getParcelable(str2)));
            }
        }
        this.l = arrayMap;
        this.m = iArr;
        this.n = fArr;
        this.o = bArr;
    }
}
