package com.google.zxing.oned.rss.expanded.decoders;

import com.goodix.ble.libcomx.logger.RingLogger;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;
import com.jieli.jl_rcsp.constant.Command;
import kotlin.text.Typography;
import org.apache.commons.codec.language.Soundex;
/* loaded from: classes11.dex */
public final class r {

    /* renamed from: a  reason: collision with root package name */
    public final BitArray f11842a;
    public final l b = new l();
    public final StringBuilder c = new StringBuilder();

    public r(BitArray bitArray) {
        this.f11842a = bitArray;
    }

    public static int g(BitArray bitArray, int i, int i2) {
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            if (bitArray.get(i + i4)) {
                i3 |= 1 << ((i2 - i4) - 1);
            }
        }
        return i3;
    }

    public String a(StringBuilder sb, int i) throws NotFoundException, FormatException {
        String str = null;
        while (true) {
            n c = c(i, str);
            String a2 = q.a(c.b());
            if (a2 != null) {
                sb.append(a2);
            }
            String valueOf = c.d() ? String.valueOf(c.c()) : null;
            if (i != c.a()) {
                i = c.a();
                str = valueOf;
            } else {
                return sb.toString();
            }
        }
    }

    public final m b(int i) {
        char c;
        int f = f(i, 5);
        if (f == 15) {
            return new m(i + 5, Typography.dollar);
        }
        if (f >= 5 && f < 15) {
            return new m(i + 5, (char) ((f + 48) - 5));
        }
        int f2 = f(i, 6);
        if (f2 >= 32 && f2 < 58) {
            return new m(i + 6, (char) (f2 + 33));
        }
        switch (f2) {
            case 58:
                c = '*';
                break;
            case 59:
                c = ',';
                break;
            case 60:
                c = Soundex.SILENT_MARKER;
                break;
            case 61:
                c = '.';
                break;
            case 62:
                c = '/';
                break;
            default:
                throw new IllegalStateException("Decoding invalid alphanumeric value: ".concat(String.valueOf(f2)));
        }
        return new m(i + 6, c);
    }

    public n c(int i, String str) throws FormatException {
        this.c.setLength(0);
        if (str != null) {
            this.c.append(str);
        }
        this.b.h(i);
        n o = o();
        if (o != null && o.d()) {
            return new n(this.b.a(), this.c.toString(), o.c());
        }
        return new n(this.b.a(), this.c.toString());
    }

    public final m d(int i) throws FormatException {
        char c;
        int f = f(i, 5);
        if (f == 15) {
            return new m(i + 5, Typography.dollar);
        }
        if (f >= 5 && f < 15) {
            return new m(i + 5, (char) ((f + 48) - 5));
        }
        int f2 = f(i, 7);
        if (f2 < 64 || f2 >= 90) {
            if (f2 >= 90 && f2 < 116) {
                return new m(i + 7, (char) (f2 + 7));
            }
            switch (f(i, 8)) {
                case 232:
                    c = '!';
                    break;
                case 233:
                    c = Typography.quote;
                    break;
                case 234:
                    c = '%';
                    break;
                case 235:
                    c = Typography.amp;
                    break;
                case 236:
                    c = '\'';
                    break;
                case 237:
                    c = HexStringBuilder.COMMENT_BEGIN_CHAR;
                    break;
                case 238:
                    c = HexStringBuilder.COMMENT_END_CHAR;
                    break;
                case 239:
                    c = '*';
                    break;
                case 240:
                    c = '+';
                    break;
                case Command.CMD_PHONE_NUMBER_PLAY_MODE /* 241 */:
                    c = ',';
                    break;
                case 242:
                    c = Soundex.SILENT_MARKER;
                    break;
                case 243:
                    c = '.';
                    break;
                case 244:
                    c = '/';
                    break;
                case 245:
                    c = ':';
                    break;
                case 246:
                    c = ';';
                    break;
                case 247:
                    c = Typography.less;
                    break;
                case RingLogger.EVT_UPDATE /* 248 */:
                    c = '=';
                    break;
                case 249:
                    c = Typography.greater;
                    break;
                case 250:
                    c = org.apache.commons.codec.net.a.SEP;
                    break;
                case 251:
                    c = '_';
                    break;
                case 252:
                    c = ' ';
                    break;
                default:
                    throw FormatException.getFormatInstance();
            }
            return new m(i + 8, c);
        }
        return new m(i + 7, (char) (f2 + 1));
    }

    public final o e(int i) throws FormatException {
        int i2 = i + 7;
        if (i2 > this.f11842a.getSize()) {
            int f = f(i, 4);
            if (f == 0) {
                return new o(this.f11842a.getSize(), 10, 10);
            }
            return new o(this.f11842a.getSize(), f - 1, 10);
        }
        int f2 = f(i, 7) - 8;
        return new o(i2, f2 / 11, f2 % 11);
    }

    public int f(int i, int i2) {
        return g(this.f11842a, i, i2);
    }

    public final boolean h(int i) {
        int i2 = i + 3;
        if (i2 > this.f11842a.getSize()) {
            return false;
        }
        while (i < i2) {
            if (this.f11842a.get(i)) {
                return false;
            }
            i++;
        }
        return true;
    }

    public final boolean i(int i) {
        int i2;
        if (i + 1 > this.f11842a.getSize()) {
            return false;
        }
        for (int i3 = 0; i3 < 5 && (i2 = i3 + i) < this.f11842a.getSize(); i3++) {
            if (i3 == 2) {
                if (!this.f11842a.get(i + 2)) {
                    return false;
                }
            } else if (this.f11842a.get(i2)) {
                return false;
            }
        }
        return true;
    }

    public final boolean j(int i) {
        int i2;
        if (i + 1 > this.f11842a.getSize()) {
            return false;
        }
        for (int i3 = 0; i3 < 4 && (i2 = i3 + i) < this.f11842a.getSize(); i3++) {
            if (this.f11842a.get(i2)) {
                return false;
            }
        }
        return true;
    }

    public final boolean k(int i) {
        int f;
        if (i + 5 > this.f11842a.getSize()) {
            return false;
        }
        int f2 = f(i, 5);
        if (f2 < 5 || f2 >= 16) {
            return i + 6 <= this.f11842a.getSize() && (f = f(i, 6)) >= 16 && f < 63;
        }
        return true;
    }

    public final boolean l(int i) {
        int f;
        if (i + 5 > this.f11842a.getSize()) {
            return false;
        }
        int f2 = f(i, 5);
        if (f2 < 5 || f2 >= 16) {
            if (i + 7 > this.f11842a.getSize()) {
                return false;
            }
            int f3 = f(i, 7);
            if (f3 < 64 || f3 >= 116) {
                return i + 8 <= this.f11842a.getSize() && (f = f(i, 8)) >= 232 && f < 253;
            }
            return true;
        }
        return true;
    }

    public final boolean m(int i) {
        if (i + 7 > this.f11842a.getSize()) {
            return i + 4 <= this.f11842a.getSize();
        }
        int i2 = i;
        while (true) {
            int i3 = i + 3;
            if (i2 < i3) {
                if (this.f11842a.get(i2)) {
                    return true;
                }
                i2++;
            } else {
                return this.f11842a.get(i3);
            }
        }
    }

    public final k n() {
        while (k(this.b.a())) {
            m b = b(this.b.a());
            this.b.h(b.a());
            if (b.c()) {
                return new k(new n(this.b.a(), this.c.toString()), true);
            }
            this.c.append(b.b());
        }
        if (h(this.b.a())) {
            this.b.b(3);
            this.b.g();
        } else if (i(this.b.a())) {
            if (this.b.a() + 5 < this.f11842a.getSize()) {
                this.b.b(5);
            } else {
                this.b.h(this.f11842a.getSize());
            }
            this.b.f();
        }
        return new k(false);
    }

    public final n o() throws FormatException {
        k q;
        boolean b;
        do {
            int a2 = this.b.a();
            if (this.b.c()) {
                q = n();
                b = q.b();
            } else if (this.b.d()) {
                q = p();
                b = q.b();
            } else {
                q = q();
                b = q.b();
            }
            if (!(a2 != this.b.a()) && !b) {
                break;
            }
        } while (!b);
        return q.a();
    }

    public final k p() throws FormatException {
        while (l(this.b.a())) {
            m d = d(this.b.a());
            this.b.h(d.a());
            if (d.c()) {
                return new k(new n(this.b.a(), this.c.toString()), true);
            }
            this.c.append(d.b());
        }
        if (h(this.b.a())) {
            this.b.b(3);
            this.b.g();
        } else if (i(this.b.a())) {
            if (this.b.a() + 5 < this.f11842a.getSize()) {
                this.b.b(5);
            } else {
                this.b.h(this.f11842a.getSize());
            }
            this.b.e();
        }
        return new k(false);
    }

    public final k q() throws FormatException {
        n nVar;
        while (m(this.b.a())) {
            o e = e(this.b.a());
            this.b.h(e.a());
            if (e.d()) {
                if (e.e()) {
                    nVar = new n(this.b.a(), this.c.toString());
                } else {
                    nVar = new n(this.b.a(), this.c.toString(), e.c());
                }
                return new k(nVar, true);
            }
            this.c.append(e.b());
            if (e.e()) {
                return new k(new n(this.b.a(), this.c.toString()), true);
            }
            this.c.append(e.c());
        }
        if (j(this.b.a())) {
            this.b.e();
            this.b.b(4);
        }
        return new k(false);
    }
}
