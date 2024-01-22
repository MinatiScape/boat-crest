package com.google.android.gms.fido.u2f.api.common;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
@SafeParcelable.Class(creator = "SignRequestParamsCreator")
@SafeParcelable.Reserved({1})
@Deprecated
/* loaded from: classes6.dex */
public class SignRequestParams extends RequestParams {
    @NonNull
    public static final Parcelable.Creator<SignRequestParams> CREATOR = new zzk();
    public static final int MAX_DISPLAY_HINT_LENGTH = 80;
    @SafeParcelable.Field(getter = "getRequestId", id = 2)
    public final Integer h;
    @Nullable
    @SafeParcelable.Field(getter = "getTimeoutSeconds", id = 3)
    public final Double i;
    @SafeParcelable.Field(getter = "getAppId", id = 4)
    public final Uri j;
    @SafeParcelable.Field(getter = "getDefaultSignChallenge", id = 5)
    public final byte[] k;
    @SafeParcelable.Field(getter = "getRegisteredKeys", id = 6)
    public final List l;
    @SafeParcelable.Field(getter = "getChannelIdValue", id = 7)
    public final ChannelIdValue m;
    @SafeParcelable.Field(getter = "getDisplayHint", id = 8)
    public final String n;
    public final Set o;

    /* loaded from: classes6.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public Integer f8419a;
        @Nullable
        public Double b;
        public Uri c;
        public byte[] d;
        public List e;
        public ChannelIdValue f;
        public String g;

        @NonNull
        public SignRequestParams build() {
            return new SignRequestParams(this.f8419a, this.b, this.c, this.d, this.e, this.f, this.g);
        }

        @NonNull
        public Builder setAppId(@NonNull Uri uri) {
            this.c = uri;
            return this;
        }

        @NonNull
        public Builder setChannelIdValue(@NonNull ChannelIdValue channelIdValue) {
            this.f = channelIdValue;
            return this;
        }

        @NonNull
        public Builder setDefaultSignChallenge(@NonNull byte[] bArr) {
            this.d = bArr;
            return this;
        }

        @NonNull
        public Builder setDisplayHint(@NonNull String str) {
            this.g = str;
            return this;
        }

        @NonNull
        public Builder setRegisteredKeys(@NonNull List<RegisteredKey> list) {
            this.e = list;
            return this;
        }

        @NonNull
        public Builder setRequestId(@NonNull Integer num) {
            this.f8419a = num;
            return this;
        }

        @NonNull
        public Builder setTimeoutSeconds(@Nullable Double d) {
            this.b = d;
            return this;
        }
    }

    @SafeParcelable.Constructor
    public SignRequestParams(@SafeParcelable.Param(id = 2) Integer num, @Nullable @SafeParcelable.Param(id = 3) Double d, @SafeParcelable.Param(id = 4) Uri uri, @SafeParcelable.Param(id = 5) byte[] bArr, @SafeParcelable.Param(id = 6) List list, @SafeParcelable.Param(id = 7) ChannelIdValue channelIdValue, @SafeParcelable.Param(id = 8) String str) {
        this.h = num;
        this.i = d;
        this.j = uri;
        this.k = bArr;
        boolean z = false;
        Preconditions.checkArgument((list == null || list.isEmpty()) ? false : true, "registeredKeys must not be null or empty");
        this.l = list;
        this.m = channelIdValue;
        HashSet hashSet = new HashSet();
        if (uri != null) {
            hashSet.add(uri);
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            RegisteredKey registeredKey = (RegisteredKey) it.next();
            Preconditions.checkArgument((registeredKey.getAppId() == null && uri == null) ? false : true, "registered key has null appId and no request appId is provided");
            registeredKey.getChallengeValue();
            Preconditions.checkArgument(true, "register request has null challenge and no default challenge isprovided");
            if (registeredKey.getAppId() != null) {
                hashSet.add(Uri.parse(registeredKey.getAppId()));
            }
        }
        this.o = hashSet;
        Preconditions.checkArgument((str == null || str.length() <= 80) ? true : z, "Display Hint cannot be longer than 80 characters");
        this.n = str;
    }

    public boolean equals(@NonNull Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SignRequestParams) {
            SignRequestParams signRequestParams = (SignRequestParams) obj;
            return Objects.equal(this.h, signRequestParams.h) && Objects.equal(this.i, signRequestParams.i) && Objects.equal(this.j, signRequestParams.j) && Arrays.equals(this.k, signRequestParams.k) && this.l.containsAll(signRequestParams.l) && signRequestParams.l.containsAll(this.l) && Objects.equal(this.m, signRequestParams.m) && Objects.equal(this.n, signRequestParams.n);
        }
        return false;
    }

    @Override // com.google.android.gms.fido.u2f.api.common.RequestParams
    @NonNull
    public Set<Uri> getAllAppIds() {
        return this.o;
    }

    @Override // com.google.android.gms.fido.u2f.api.common.RequestParams
    @NonNull
    public Uri getAppId() {
        return this.j;
    }

    @Override // com.google.android.gms.fido.u2f.api.common.RequestParams
    @NonNull
    public ChannelIdValue getChannelIdValue() {
        return this.m;
    }

    @NonNull
    public byte[] getDefaultSignChallenge() {
        return this.k;
    }

    @Override // com.google.android.gms.fido.u2f.api.common.RequestParams
    @NonNull
    public String getDisplayHint() {
        return this.n;
    }

    @Override // com.google.android.gms.fido.u2f.api.common.RequestParams
    @NonNull
    public List<RegisteredKey> getRegisteredKeys() {
        return this.l;
    }

    @Override // com.google.android.gms.fido.u2f.api.common.RequestParams
    @NonNull
    public Integer getRequestId() {
        return this.h;
    }

    @Override // com.google.android.gms.fido.u2f.api.common.RequestParams
    @Nullable
    public Double getTimeoutSeconds() {
        return this.i;
    }

    public int hashCode() {
        return Objects.hashCode(this.h, this.j, this.i, this.l, this.m, this.n, Integer.valueOf(Arrays.hashCode(this.k)));
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeIntegerObject(parcel, 2, getRequestId(), false);
        SafeParcelWriter.writeDoubleObject(parcel, 3, getTimeoutSeconds(), false);
        SafeParcelWriter.writeParcelable(parcel, 4, getAppId(), i, false);
        SafeParcelWriter.writeByteArray(parcel, 5, getDefaultSignChallenge(), false);
        SafeParcelWriter.writeTypedList(parcel, 6, getRegisteredKeys(), false);
        SafeParcelWriter.writeParcelable(parcel, 7, getChannelIdValue(), i, false);
        SafeParcelWriter.writeString(parcel, 8, getDisplayHint(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
