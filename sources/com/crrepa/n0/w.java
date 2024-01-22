package com.crrepa.n0;
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes9.dex */
public abstract class w {

    /* renamed from: a  reason: collision with root package name */
    public static final w f7784a;
    public static final w b;
    private static final /* synthetic */ w[] c;

    /* loaded from: classes9.dex */
    public enum a extends w {
        public a(String str, int i) {
            super(str, i, null);
        }

        @Override // com.crrepa.n0.w
        public l a(Long l) {
            return new r((Number) l);
        }
    }

    static {
        a aVar = new a("DEFAULT", 0);
        f7784a = aVar;
        w wVar = new w("STRING", 1) { // from class: com.crrepa.n0.w.b
            @Override // com.crrepa.n0.w
            public l a(Long l) {
                return new r(String.valueOf(l));
            }
        };
        b = wVar;
        c = new w[]{aVar, wVar};
    }

    private w(String str, int i) {
    }

    public /* synthetic */ w(String str, int i, a aVar) {
        this(str, i);
    }

    public static w valueOf(String str) {
        return (w) Enum.valueOf(w.class, str);
    }

    public static w[] values() {
        return (w[]) c.clone();
    }

    public abstract l a(Long l);
}
