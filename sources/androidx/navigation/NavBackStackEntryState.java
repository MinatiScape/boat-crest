package androidx.navigation;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.UUID;
@SuppressLint({"BanParcelableUsage"})
/* loaded from: classes.dex */
public final class NavBackStackEntryState implements Parcelable {
    public static final Parcelable.Creator<NavBackStackEntryState> CREATOR = new a();
    public final UUID h;
    public final int i;
    public final Bundle j;
    public final Bundle k;

    /* loaded from: classes.dex */
    public class a implements Parcelable.Creator<NavBackStackEntryState> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public NavBackStackEntryState createFromParcel(Parcel parcel) {
            return new NavBackStackEntryState(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b */
        public NavBackStackEntryState[] newArray(int i) {
            return new NavBackStackEntryState[i];
        }
    }

    public NavBackStackEntryState(NavBackStackEntry navBackStackEntry) {
        this.h = navBackStackEntry.m;
        this.i = navBackStackEntry.getDestination().getId();
        this.j = navBackStackEntry.getArguments();
        Bundle bundle = new Bundle();
        this.k = bundle;
        navBackStackEntry.e(bundle);
    }

    @Nullable
    public Bundle a() {
        return this.j;
    }

    public int b() {
        return this.i;
    }

    @NonNull
    public Bundle c() {
        return this.k;
    }

    @NonNull
    public UUID d() {
        return this.h;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(this.h.toString());
        parcel.writeInt(this.i);
        parcel.writeBundle(this.j);
        parcel.writeBundle(this.k);
    }

    public NavBackStackEntryState(Parcel parcel) {
        this.h = UUID.fromString(parcel.readString());
        this.i = parcel.readInt();
        this.j = parcel.readBundle(NavBackStackEntryState.class.getClassLoader());
        this.k = parcel.readBundle(NavBackStackEntryState.class.getClassLoader());
    }
}
