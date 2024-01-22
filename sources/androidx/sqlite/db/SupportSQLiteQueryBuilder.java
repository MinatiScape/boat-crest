package androidx.sqlite.db;

import java.util.regex.Pattern;
/* loaded from: classes.dex */
public final class SupportSQLiteQueryBuilder {
    public static final Pattern j = Pattern.compile("\\s*\\d+\\s*(,\\s*\\d+\\s*)?");
    public final String b;
    public String d;
    public Object[] e;

    /* renamed from: a  reason: collision with root package name */
    public boolean f1684a = false;
    public String[] c = null;
    public String f = null;
    public String g = null;
    public String h = null;
    public String i = null;

    public SupportSQLiteQueryBuilder(String str) {
        this.b = str;
    }

    public static void a(StringBuilder sb, String str, String str2) {
        if (c(str2)) {
            return;
        }
        sb.append(str);
        sb.append(str2);
    }

    public static void b(StringBuilder sb, String[] strArr) {
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            String str = strArr[i];
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(str);
        }
        sb.append(' ');
    }

    public static SupportSQLiteQueryBuilder builder(String str) {
        return new SupportSQLiteQueryBuilder(str);
    }

    public static boolean c(String str) {
        return str == null || str.length() == 0;
    }

    public SupportSQLiteQueryBuilder columns(String[] strArr) {
        this.c = strArr;
        return this;
    }

    public SupportSQLiteQuery create() {
        if (c(this.f) && !c(this.g)) {
            throw new IllegalArgumentException("HAVING clauses are only permitted when using a groupBy clause");
        }
        StringBuilder sb = new StringBuilder(120);
        sb.append("SELECT ");
        if (this.f1684a) {
            sb.append("DISTINCT ");
        }
        String[] strArr = this.c;
        if (strArr != null && strArr.length != 0) {
            b(sb, strArr);
        } else {
            sb.append(" * ");
        }
        sb.append(" FROM ");
        sb.append(this.b);
        a(sb, " WHERE ", this.d);
        a(sb, " GROUP BY ", this.f);
        a(sb, " HAVING ", this.g);
        a(sb, " ORDER BY ", this.h);
        a(sb, " LIMIT ", this.i);
        return new SimpleSQLiteQuery(sb.toString(), this.e);
    }

    public SupportSQLiteQueryBuilder distinct() {
        this.f1684a = true;
        return this;
    }

    public SupportSQLiteQueryBuilder groupBy(String str) {
        this.f = str;
        return this;
    }

    public SupportSQLiteQueryBuilder having(String str) {
        this.g = str;
        return this;
    }

    public SupportSQLiteQueryBuilder limit(String str) {
        if (!c(str) && !j.matcher(str).matches()) {
            throw new IllegalArgumentException("invalid LIMIT clauses:" + str);
        }
        this.i = str;
        return this;
    }

    public SupportSQLiteQueryBuilder orderBy(String str) {
        this.h = str;
        return this;
    }

    public SupportSQLiteQueryBuilder selection(String str, Object[] objArr) {
        this.d = str;
        this.e = objArr;
        return this;
    }
}
