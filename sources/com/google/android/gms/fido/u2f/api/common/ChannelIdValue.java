package com.google.android.gms.fido.u2f.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import org.json.JSONException;
import org.json.JSONObject;
@SafeParcelable.Class(creator = "ChannelIdValueCreator")
@SafeParcelable.Reserved({1})
@Deprecated
/* loaded from: classes6.dex */
public class ChannelIdValue extends AbstractSafeParcelable {
    @SafeParcelable.Field(getter = "getTypeAsInt", id = 2, type = "int")
    public final ChannelIdValueType h;
    @SafeParcelable.Field(getter = "getStringValue", id = 3)
    public final String i;
    @SafeParcelable.Field(getter = "getObjectValueAsString", id = 4)
    public final String j;
    @NonNull
    public static final Parcelable.Creator<ChannelIdValue> CREATOR = new zzb();
    @NonNull
    public static final ChannelIdValue ABSENT = new ChannelIdValue();
    @NonNull
    public static final ChannelIdValue UNAVAILABLE = new ChannelIdValue("unavailable");
    @NonNull
    public static final ChannelIdValue UNUSED = new ChannelIdValue("unused");

    /* loaded from: classes6.dex */
    public enum ChannelIdValueType implements Parcelable {
        ABSENT(0),
        STRING(1),
        OBJECT(2);
        
        @NonNull
        public static final Parcelable.Creator<ChannelIdValueType> CREATOR = new a();
        private final int zzb;

        ChannelIdValueType(int i) {
            this.zzb = i;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(@NonNull Parcel parcel, int i) {
            parcel.writeInt(this.zzb);
        }
    }

    /* loaded from: classes6.dex */
    public static class UnsupportedChannelIdValueTypeException extends Exception {
        public UnsupportedChannelIdValueTypeException(int i) {
            super(String.format("ChannelIdValueType %s not supported", Integer.valueOf(i)));
        }
    }

    public ChannelIdValue() {
        this.h = ChannelIdValueType.ABSENT;
        this.j = null;
        this.i = null;
    }

    @NonNull
    public static ChannelIdValueType toChannelIdValueType(int i) throws UnsupportedChannelIdValueTypeException {
        ChannelIdValueType[] values;
        for (ChannelIdValueType channelIdValueType : ChannelIdValueType.values()) {
            if (i == channelIdValueType.zzb) {
                return channelIdValueType;
            }
        }
        throw new UnsupportedChannelIdValueTypeException(i);
    }

    public boolean equals(@NonNull Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ChannelIdValue) {
            ChannelIdValue channelIdValue = (ChannelIdValue) obj;
            if (this.h.equals(channelIdValue.h)) {
                int ordinal = this.h.ordinal();
                if (ordinal != 0) {
                    if (ordinal != 1) {
                        if (ordinal != 2) {
                            return false;
                        }
                        return this.j.equals(channelIdValue.j);
                    }
                    return this.i.equals(channelIdValue.i);
                }
                return true;
            }
            return false;
        }
        return false;
    }

    @NonNull
    public JSONObject getObjectValue() {
        if (this.j == null) {
            return null;
        }
        try {
            return new JSONObject(this.j);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @NonNull
    public String getObjectValueAsString() {
        return this.j;
    }

    @NonNull
    public String getStringValue() {
        return this.i;
    }

    @NonNull
    public ChannelIdValueType getType() {
        return this.h;
    }

    public int getTypeAsInt() {
        return this.h.zzb;
    }

    public int hashCode() {
        int i;
        int hashCode;
        int hashCode2 = this.h.hashCode() + 31;
        int ordinal = this.h.ordinal();
        if (ordinal == 1) {
            i = hashCode2 * 31;
            hashCode = this.i.hashCode();
        } else if (ordinal != 2) {
            return hashCode2;
        } else {
            i = hashCode2 * 31;
            hashCode = this.j.hashCode();
        }
        return i + hashCode;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, getTypeAsInt());
        SafeParcelWriter.writeString(parcel, 3, getStringValue(), false);
        SafeParcelWriter.writeString(parcel, 4, getObjectValueAsString(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @SafeParcelable.Constructor
    public ChannelIdValue(@SafeParcelable.Param(id = 2) int i, @SafeParcelable.Param(id = 3) String str, @SafeParcelable.Param(id = 4) String str2) {
        try {
            this.h = toChannelIdValueType(i);
            this.i = str;
            this.j = str2;
        } catch (UnsupportedChannelIdValueTypeException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public ChannelIdValue(String str) {
        this.i = (String) Preconditions.checkNotNull(str);
        this.h = ChannelIdValueType.STRING;
        this.j = null;
    }

    public ChannelIdValue(@NonNull JSONObject jSONObject) {
        this.j = (String) Preconditions.checkNotNull(jSONObject.toString());
        this.h = ChannelIdValueType.OBJECT;
        this.i = null;
    }
}
