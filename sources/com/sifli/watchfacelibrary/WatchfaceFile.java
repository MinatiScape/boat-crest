package com.sifli.watchfacelibrary;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
/* loaded from: classes12.dex */
public class WatchfaceFile implements Parcelable {
    public static final Parcelable.Creator<WatchfaceFile> CREATOR = new a();
    public String h;
    public String i;
    public String j;
    public byte[] k;

    /* loaded from: classes12.dex */
    public class a implements Parcelable.Creator<WatchfaceFile> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public WatchfaceFile createFromParcel(Parcel parcel) {
            return new WatchfaceFile(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b */
        public WatchfaceFile[] newArray(int i) {
            return new WatchfaceFile[i];
        }
    }

    public WatchfaceFile(String str, byte[] bArr) {
        this.h = str;
        this.k = bArr;
    }

    public void addCRC() {
        int CRC32MPEG2GetValue = CRC32MPEG2.CRC32MPEG2GetValue(this.k);
        byte[] bArr = this.k;
        byte[] bArr2 = new byte[bArr.length + 4];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        WatchfaceUtil.addIntToByteArray(CRC32MPEG2GetValue, bArr2, this.k.length);
        this.k = bArr2;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public byte[] getFileData() {
        return this.k;
    }

    public String getFileExtension() {
        return this.j;
    }

    public String getFilePath() {
        return this.h;
    }

    public void makeAlignment() {
        int length = this.k.length;
        int i = length % 4;
        if (i == 0) {
            return;
        }
        int i2 = 4 - i;
        byte[] bArr = new byte[length + i2];
        byte[] bArr2 = new byte[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            bArr2[i3] = 0;
        }
        System.arraycopy(this.k, 0, bArr, 0, length);
        System.arraycopy(bArr2, 0, bArr, length, i2);
        this.k = bArr;
    }

    public void setFileData(byte[] bArr) {
        this.k = bArr;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.h);
        parcel.writeByteArray(this.k);
        parcel.writeString(this.i);
        parcel.writeString(this.j);
    }

    public WatchfaceFile(String str, byte[] bArr, String str2) {
        this.h = str;
        this.k = bArr;
        this.i = str2;
        int indexOf = str2.indexOf(".");
        if (indexOf == -1) {
            this.j = "any";
            return;
        }
        String substring = str2.substring(indexOf + 1);
        this.j = substring;
        substring.toLowerCase();
    }

    public void makeAlignment(int i) {
        byte b;
        int length = this.k.length;
        int i2 = length % 4;
        if (i == 5) {
            Log.d("watchfaceFile", "align with 0x20");
            b = 32;
        } else {
            b = 0;
        }
        if (i2 == 0) {
            return;
        }
        int i3 = 4 - i2;
        byte[] bArr = new byte[length + i3];
        byte[] bArr2 = new byte[i3];
        for (int i4 = 0; i4 < i3; i4++) {
            bArr2[i4] = b;
        }
        System.arraycopy(this.k, 0, bArr, 0, length);
        System.arraycopy(bArr2, 0, bArr, length, i3);
        this.k = bArr;
    }

    public WatchfaceFile(Parcel parcel) {
        this.h = parcel.readString();
        this.k = parcel.createByteArray();
        this.i = parcel.readString();
        this.j = parcel.readString();
    }
}
