package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
@SuppressLint({"BanParcelableUsage"})
/* loaded from: classes.dex */
public final class FragmentState implements Parcelable {
    public static final Parcelable.Creator<FragmentState> CREATOR = new a();
    public final String h;
    public final String i;
    public final boolean j;
    public final int k;
    public final int l;
    public final String m;
    public final boolean n;
    public final boolean o;
    public final boolean p;
    public final Bundle q;
    public final boolean r;
    public final int s;
    public Bundle t;

    /* loaded from: classes.dex */
    public class a implements Parcelable.Creator<FragmentState> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public FragmentState createFromParcel(Parcel parcel) {
            return new FragmentState(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b */
        public FragmentState[] newArray(int i) {
            return new FragmentState[i];
        }
    }

    public FragmentState(Fragment fragment) {
        this.h = fragment.getClass().getName();
        this.i = fragment.mWho;
        this.j = fragment.mFromLayout;
        this.k = fragment.mFragmentId;
        this.l = fragment.mContainerId;
        this.m = fragment.mTag;
        this.n = fragment.mRetainInstance;
        this.o = fragment.mRemoving;
        this.p = fragment.mDetached;
        this.q = fragment.mArguments;
        this.r = fragment.mHidden;
        this.s = fragment.mMaxState.ordinal();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @NonNull
    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("FragmentState{");
        sb.append(this.h);
        sb.append(" (");
        sb.append(this.i);
        sb.append(")}:");
        if (this.j) {
            sb.append(" fromLayout");
        }
        if (this.l != 0) {
            sb.append(" id=0x");
            sb.append(Integer.toHexString(this.l));
        }
        String str = this.m;
        if (str != null && !str.isEmpty()) {
            sb.append(" tag=");
            sb.append(this.m);
        }
        if (this.n) {
            sb.append(" retainInstance");
        }
        if (this.o) {
            sb.append(" removing");
        }
        if (this.p) {
            sb.append(" detached");
        }
        if (this.r) {
            sb.append(" hidden");
        }
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.h);
        parcel.writeString(this.i);
        parcel.writeInt(this.j ? 1 : 0);
        parcel.writeInt(this.k);
        parcel.writeInt(this.l);
        parcel.writeString(this.m);
        parcel.writeInt(this.n ? 1 : 0);
        parcel.writeInt(this.o ? 1 : 0);
        parcel.writeInt(this.p ? 1 : 0);
        parcel.writeBundle(this.q);
        parcel.writeInt(this.r ? 1 : 0);
        parcel.writeBundle(this.t);
        parcel.writeInt(this.s);
    }

    public FragmentState(Parcel parcel) {
        this.h = parcel.readString();
        this.i = parcel.readString();
        this.j = parcel.readInt() != 0;
        this.k = parcel.readInt();
        this.l = parcel.readInt();
        this.m = parcel.readString();
        this.n = parcel.readInt() != 0;
        this.o = parcel.readInt() != 0;
        this.p = parcel.readInt() != 0;
        this.q = parcel.readBundle();
        this.r = parcel.readInt() != 0;
        this.t = parcel.readBundle();
        this.s = parcel.readInt();
    }
}
