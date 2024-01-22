package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import java.util.ArrayList;
@SuppressLint({"BanParcelableUsage"})
/* loaded from: classes.dex */
public final class BackStackState implements Parcelable {
    public static final Parcelable.Creator<BackStackState> CREATOR = new a();
    public final int[] h;
    public final ArrayList<String> i;
    public final int[] j;
    public final int[] k;
    public final int l;
    public final String m;
    public final int n;
    public final int o;
    public final CharSequence p;
    public final int q;
    public final CharSequence r;
    public final ArrayList<String> s;
    public final ArrayList<String> t;
    public final boolean u;

    /* loaded from: classes.dex */
    public class a implements Parcelable.Creator<BackStackState> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public BackStackState createFromParcel(Parcel parcel) {
            return new BackStackState(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b */
        public BackStackState[] newArray(int i) {
            return new BackStackState[i];
        }
    }

    public BackStackState(androidx.fragment.app.a aVar) {
        int size = aVar.c.size();
        this.h = new int[size * 5];
        if (aVar.i) {
            this.i = new ArrayList<>(size);
            this.j = new int[size];
            this.k = new int[size];
            int i = 0;
            int i2 = 0;
            while (i < size) {
                FragmentTransaction.a aVar2 = aVar.c.get(i);
                int i3 = i2 + 1;
                this.h[i2] = aVar2.f1320a;
                ArrayList<String> arrayList = this.i;
                Fragment fragment = aVar2.b;
                arrayList.add(fragment != null ? fragment.mWho : null);
                int[] iArr = this.h;
                int i4 = i3 + 1;
                iArr[i3] = aVar2.c;
                int i5 = i4 + 1;
                iArr[i4] = aVar2.d;
                int i6 = i5 + 1;
                iArr[i5] = aVar2.e;
                iArr[i6] = aVar2.f;
                this.j[i] = aVar2.g.ordinal();
                this.k[i] = aVar2.h.ordinal();
                i++;
                i2 = i6 + 1;
            }
            this.l = aVar.h;
            this.m = aVar.k;
            this.n = aVar.v;
            this.o = aVar.l;
            this.p = aVar.m;
            this.q = aVar.n;
            this.r = aVar.o;
            this.s = aVar.p;
            this.t = aVar.q;
            this.u = aVar.r;
            return;
        }
        throw new IllegalStateException("Not on back stack");
    }

    public androidx.fragment.app.a a(FragmentManager fragmentManager) {
        androidx.fragment.app.a aVar = new androidx.fragment.app.a(fragmentManager);
        int i = 0;
        int i2 = 0;
        while (i < this.h.length) {
            FragmentTransaction.a aVar2 = new FragmentTransaction.a();
            int i3 = i + 1;
            aVar2.f1320a = this.h[i];
            if (FragmentManager.x0(2)) {
                Log.v("FragmentManager", "Instantiate " + aVar + " op #" + i2 + " base fragment #" + this.h[i3]);
            }
            String str = this.i.get(i2);
            if (str != null) {
                aVar2.b = fragmentManager.c0(str);
            } else {
                aVar2.b = null;
            }
            aVar2.g = Lifecycle.State.values()[this.j[i2]];
            aVar2.h = Lifecycle.State.values()[this.k[i2]];
            int[] iArr = this.h;
            int i4 = i3 + 1;
            int i5 = iArr[i3];
            aVar2.c = i5;
            int i6 = i4 + 1;
            int i7 = iArr[i4];
            aVar2.d = i7;
            int i8 = i6 + 1;
            int i9 = iArr[i6];
            aVar2.e = i9;
            int i10 = iArr[i8];
            aVar2.f = i10;
            aVar.d = i5;
            aVar.e = i7;
            aVar.f = i9;
            aVar.g = i10;
            aVar.c(aVar2);
            i2++;
            i = i8 + 1;
        }
        aVar.h = this.l;
        aVar.k = this.m;
        aVar.v = this.n;
        aVar.i = true;
        aVar.l = this.o;
        aVar.m = this.p;
        aVar.n = this.q;
        aVar.o = this.r;
        aVar.p = this.s;
        aVar.q = this.t;
        aVar.r = this.u;
        aVar.f(1);
        return aVar;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeIntArray(this.h);
        parcel.writeStringList(this.i);
        parcel.writeIntArray(this.j);
        parcel.writeIntArray(this.k);
        parcel.writeInt(this.l);
        parcel.writeString(this.m);
        parcel.writeInt(this.n);
        parcel.writeInt(this.o);
        TextUtils.writeToParcel(this.p, parcel, 0);
        parcel.writeInt(this.q);
        TextUtils.writeToParcel(this.r, parcel, 0);
        parcel.writeStringList(this.s);
        parcel.writeStringList(this.t);
        parcel.writeInt(this.u ? 1 : 0);
    }

    public BackStackState(Parcel parcel) {
        this.h = parcel.createIntArray();
        this.i = parcel.createStringArrayList();
        this.j = parcel.createIntArray();
        this.k = parcel.createIntArray();
        this.l = parcel.readInt();
        this.m = parcel.readString();
        this.n = parcel.readInt();
        this.o = parcel.readInt();
        this.p = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.q = parcel.readInt();
        this.r = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.s = parcel.createStringArrayList();
        this.t = parcel.createStringArrayList();
        this.u = parcel.readInt() != 0;
    }
}
