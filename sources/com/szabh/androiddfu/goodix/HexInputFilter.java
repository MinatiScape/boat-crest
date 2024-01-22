package com.szabh.androiddfu.goodix;

import android.text.InputFilter;
import android.text.Spanned;
/* loaded from: classes12.dex */
public class HexInputFilter implements InputFilter {
    public StringBuilder h = new StringBuilder(32);

    @Override // android.text.InputFilter
    public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
        StringBuilder sb = this.h;
        sb.delete(0, sb.length());
        int length = charSequence.length();
        for (int i5 = 0; i5 < length; i5++) {
            char charAt = charSequence.charAt(i5);
            boolean z = true;
            if ((charAt < '0' || charAt > '9') && (charAt < 'A' || charAt > 'F')) {
                if (charAt < 'a' || charAt > 'f') {
                    z = false;
                } else {
                    charAt = (char) ((charAt - 'a') + 65);
                }
            }
            if (z) {
                this.h.append(charAt);
            }
        }
        return this.h;
    }
}
