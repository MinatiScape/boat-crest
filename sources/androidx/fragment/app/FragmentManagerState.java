package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.fragment.app.FragmentManager;
import java.util.ArrayList;
@SuppressLint({"BanParcelableUsage"})
/* loaded from: classes.dex */
public final class FragmentManagerState implements Parcelable {
    public static final Parcelable.Creator<FragmentManagerState> CREATOR = new a();
    public ArrayList<FragmentState> h;
    public ArrayList<String> i;
    public BackStackState[] j;
    public int k;
    public String l;
    public ArrayList<String> m;
    public ArrayList<Bundle> n;
    public ArrayList<FragmentManager.LaunchedFragmentInfo> o;

    /* loaded from: classes.dex */
    public class a implements Parcelable.Creator<FragmentManagerState> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public FragmentManagerState createFromParcel(Parcel parcel) {
            return new FragmentManagerState(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b */
        public FragmentManagerState[] newArray(int i) {
            return new FragmentManagerState[i];
        }
    }

    public FragmentManagerState() {
        this.l = null;
        this.m = new ArrayList<>();
        this.n = new ArrayList<>();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.h);
        parcel.writeStringList(this.i);
        parcel.writeTypedArray(this.j, i);
        parcel.writeInt(this.k);
        parcel.writeString(this.l);
        parcel.writeStringList(this.m);
        parcel.writeTypedList(this.n);
        parcel.writeTypedList(this.o);
    }

    public FragmentManagerState(Parcel parcel) {
        this.l = null;
        this.m = new ArrayList<>();
        this.n = new ArrayList<>();
        this.h = parcel.createTypedArrayList(FragmentState.CREATOR);
        this.i = parcel.createStringArrayList();
        this.j = (BackStackState[]) parcel.createTypedArray(BackStackState.CREATOR);
        this.k = parcel.readInt();
        this.l = parcel.readString();
        this.m = parcel.createStringArrayList();
        this.n = parcel.createTypedArrayList(Bundle.CREATOR);
        this.o = parcel.createTypedArrayList(FragmentManager.LaunchedFragmentInfo.CREATOR);
    }
}
