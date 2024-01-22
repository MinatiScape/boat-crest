package com.coveiot.android.smasdk.api;

import com.bestmafen.baseble.data.ByteArrayExtKt;
import com.blankj.utilcode.util.LogUtils;
import com.coveiot.android.smasdk.model.Contact;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class SmaSyncContactsReq extends SmaBaseReq {
    @Nullable
    public final ArrayList<Contact> d;

    public SmaSyncContactsReq(@Nullable ArrayList<Contact> arrayList) {
        this.d = arrayList;
    }

    @Nullable
    public final ArrayList<Contact> getContactArrayList() {
        return this.d;
    }

    @Nullable
    public final byte[] getContactBytes() {
        byte[] bArr;
        byte[] bArr2;
        String phone;
        String name;
        ArrayList<Contact> arrayList = this.d;
        if (arrayList != null) {
            LogUtils.d(arrayList.toString());
            ArrayList<Contact> arrayList2 = this.d;
            Intrinsics.checkNotNull(arrayList2);
            byte[] bArr3 = new byte[arrayList2.size() * 40];
            ArrayList<Contact> arrayList3 = this.d;
            Intrinsics.checkNotNull(arrayList3);
            int size = arrayList3.size();
            for (int i = 0; i < size; i++) {
                ArrayList<Contact> arrayList4 = this.d;
                Intrinsics.checkNotNull(arrayList4);
                Contact contact = arrayList4.get(i);
                if (contact == null || (name = contact.getName()) == null) {
                    bArr = null;
                } else {
                    bArr = name.getBytes(Charsets.UTF_8);
                    Intrinsics.checkNotNullExpressionValue(bArr, "this as java.lang.String).getBytes(charset)");
                }
                if (bArr != null) {
                    int length = bArr.length;
                    for (int i2 = 0; i2 < length; i2++) {
                        if (i2 < 24) {
                            Byte valueOf = Byte.valueOf(bArr[i2]);
                            Intrinsics.checkNotNull(valueOf);
                            bArr3[(i * 40) + i2] = valueOf.byteValue();
                        }
                    }
                }
                ArrayList<Contact> arrayList5 = this.d;
                Intrinsics.checkNotNull(arrayList5);
                Contact contact2 = arrayList5.get(i);
                if (contact2 == null || (phone = contact2.getPhone()) == null) {
                    bArr2 = null;
                } else {
                    bArr2 = phone.getBytes(Charsets.UTF_8);
                    Intrinsics.checkNotNullExpressionValue(bArr2, "this as java.lang.String).getBytes(charset)");
                }
                if (bArr2 != null) {
                    int length2 = bArr2.length;
                    for (int i3 = 0; i3 < length2; i3++) {
                        if (i3 < 16) {
                            Byte valueOf2 = Byte.valueOf(bArr2[i3]);
                            Intrinsics.checkNotNull(valueOf2);
                            bArr3[(i * 40) + 24 + i3] = valueOf2.byteValue();
                        }
                    }
                }
            }
            LogUtils.d(ByteArrayExtKt.getMHexString(bArr3));
            return bArr3;
        }
        return null;
    }

    @Override // com.coveiot.android.smasdk.api.SmaBaseReq
    public boolean isMultiPacket() {
        return false;
    }

    @Override // com.coveiot.android.smasdk.api.SmaBaseReq
    public boolean isPriority() {
        return true;
    }
}
