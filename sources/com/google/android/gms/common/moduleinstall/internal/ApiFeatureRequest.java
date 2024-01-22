package com.google.android.gms.common.moduleinstall.internal;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.OptionalModuleApi;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.moduleinstall.ModuleInstallRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
@KeepForSdk
@SafeParcelable.Class(creator = "ApiFeatureRequestCreator")
/* loaded from: classes6.dex */
public class ApiFeatureRequest extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<ApiFeatureRequest> CREATOR = new zac();
    public static final Comparator l = new Comparator() { // from class: com.google.android.gms.common.moduleinstall.internal.zab
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            Feature feature = (Feature) obj;
            Feature feature2 = (Feature) obj2;
            Parcelable.Creator<ApiFeatureRequest> creator = ApiFeatureRequest.CREATOR;
            if (!feature.getName().equals(feature2.getName())) {
                return feature.getName().compareTo(feature2.getName());
            }
            return (feature.getVersion() > feature2.getVersion() ? 1 : (feature.getVersion() == feature2.getVersion() ? 0 : -1));
        }
    };
    @SafeParcelable.Field(getter = "getApiFeatures", id = 1)
    public final List h;
    @SafeParcelable.Field(getter = "getIsUrgent", id = 2)
    public final boolean i;
    @Nullable
    @SafeParcelable.Field(getter = "getFeatureRequestSessionId", id = 3)
    public final String j;
    @Nullable
    @SafeParcelable.Field(getter = "getCallingPackage", id = 4)
    public final String k;

    @SafeParcelable.Constructor
    public ApiFeatureRequest(@NonNull @SafeParcelable.Param(id = 1) List list, @SafeParcelable.Param(id = 2) boolean z, @Nullable @SafeParcelable.Param(id = 3) String str, @Nullable @SafeParcelable.Param(id = 4) String str2) {
        Preconditions.checkNotNull(list);
        this.h = list;
        this.i = z;
        this.j = str;
        this.k = str2;
    }

    public static ApiFeatureRequest a(List list, boolean z) {
        TreeSet treeSet = new TreeSet(l);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Collections.addAll(treeSet, ((OptionalModuleApi) it.next()).getOptionalFeatures());
        }
        return new ApiFeatureRequest(new ArrayList(treeSet), z, null, null);
    }

    @NonNull
    @KeepForSdk
    public static ApiFeatureRequest fromModuleInstallRequest(@NonNull ModuleInstallRequest moduleInstallRequest) {
        return a(moduleInstallRequest.getApis(), true);
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj != null && (obj instanceof ApiFeatureRequest)) {
            ApiFeatureRequest apiFeatureRequest = (ApiFeatureRequest) obj;
            return this.i == apiFeatureRequest.i && Objects.equal(this.h, apiFeatureRequest.h) && Objects.equal(this.j, apiFeatureRequest.j) && Objects.equal(this.k, apiFeatureRequest.k);
        }
        return false;
    }

    @NonNull
    @KeepForSdk
    public List<Feature> getApiFeatures() {
        return this.h;
    }

    public final int hashCode() {
        return Objects.hashCode(Boolean.valueOf(this.i), this.h, this.j, this.k);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, getApiFeatures(), false);
        SafeParcelWriter.writeBoolean(parcel, 2, this.i);
        SafeParcelWriter.writeString(parcel, 3, this.j, false);
        SafeParcelWriter.writeString(parcel, 4, this.k, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
