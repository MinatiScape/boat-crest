package com.google.android.odml.image;
/* loaded from: classes10.dex */
public final class c extends ImageProperties {

    /* renamed from: a  reason: collision with root package name */
    public final int f10444a;
    public final int b;

    public /* synthetic */ c(int i, int i2, a aVar) {
        this.f10444a = i;
        this.b = i2;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ImageProperties) {
            ImageProperties imageProperties = (ImageProperties) obj;
            if (this.f10444a == imageProperties.getImageFormat() && this.b == imageProperties.getStorageType()) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.odml.image.ImageProperties
    public final int getImageFormat() {
        return this.f10444a;
    }

    @Override // com.google.android.odml.image.ImageProperties
    public final int getStorageType() {
        return this.b;
    }

    public final int hashCode() {
        return ((this.f10444a ^ 1000003) * 1000003) ^ this.b;
    }

    public final String toString() {
        int i = this.f10444a;
        int i2 = this.b;
        StringBuilder sb = new StringBuilder(65);
        sb.append("ImageProperties{imageFormat=");
        sb.append(i);
        sb.append(", storageType=");
        sb.append(i2);
        sb.append("}");
        return sb.toString();
    }
}
