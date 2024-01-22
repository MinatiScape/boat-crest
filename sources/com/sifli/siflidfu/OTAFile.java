package com.sifli.siflidfu;
/* loaded from: classes12.dex */
public class OTAFile {

    /* renamed from: a  reason: collision with root package name */
    public byte[] f13700a;
    public String b;
    public int c;
    public String d;
    public String e;
    public String f;
    public int g;

    public OTAFile(byte[] bArr, String str) {
        this.f13700a = bArr;
        this.b = str;
    }

    public byte[] getFileData() {
        return this.f13700a;
    }

    public String getFileExtension() {
        return this.e;
    }

    public int getFileIndex() {
        return this.c;
    }

    public int getFileLength() {
        byte[] bArr = this.f13700a;
        if (bArr == null) {
            return FileProcess.getFileSize(this.d);
        }
        return bArr.length;
    }

    public String getFileName() {
        return this.b;
    }

    public String getFilePath() {
        return this.d;
    }

    public int getImageID() {
        return this.g;
    }

    public String getListName() {
        return this.f;
    }

    public void setFileIndex(int i) {
        this.c = i;
    }

    public void setFilePath(String str) {
        this.d = str;
    }

    public void setImageID(int i) {
        this.g = i;
    }

    public OTAFile(String str, byte[] bArr, String str2) {
        this.d = str;
        this.f13700a = bArr;
        this.b = str2;
        this.g = -2;
        int indexOf = str2.indexOf(".");
        if (indexOf == -1) {
            this.e = "any";
            this.f = str2;
            return;
        }
        String substring = str2.substring(indexOf + 1);
        this.e = substring;
        substring.toLowerCase();
        this.f = str2.substring(0, indexOf - 4);
        this.f += "." + this.e;
    }

    public OTAFile(byte[] bArr, int i) {
        this.f13700a = bArr;
        this.g = i;
    }
}
