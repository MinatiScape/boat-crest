package androidx.core.view;

import android.content.ClipData;
import android.content.ClipDescription;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.ContentInfo;
import androidx.annotation.DoNotInline;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.util.Preconditions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
/* loaded from: classes.dex */
public final class ContentInfoCompat {
    public static final int FLAG_CONVERT_TO_PLAIN_TEXT = 1;
    public static final int SOURCE_APP = 0;
    public static final int SOURCE_AUTOFILL = 4;
    public static final int SOURCE_CLIPBOARD = 1;
    public static final int SOURCE_DRAG_AND_DROP = 3;
    public static final int SOURCE_INPUT_METHOD = 2;
    public static final int SOURCE_PROCESS_TEXT = 5;
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    public final f f1125a;

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    /* loaded from: classes.dex */
    public @interface Flags {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    /* loaded from: classes.dex */
    public @interface Source {
    }

    @RequiresApi(31)
    /* loaded from: classes.dex */
    public static final class a {
        @NonNull
        @DoNotInline
        public static Pair<ContentInfo, ContentInfo> a(@NonNull ContentInfo contentInfo, @NonNull final Predicate<ClipData.Item> predicate) {
            ClipData clip = contentInfo.getClip();
            if (clip.getItemCount() == 1) {
                boolean test = predicate.test(clip.getItemAt(0));
                ContentInfo contentInfo2 = test ? contentInfo : null;
                if (test) {
                    contentInfo = null;
                }
                return Pair.create(contentInfo2, contentInfo);
            }
            Objects.requireNonNull(predicate);
            Pair<ClipData, ClipData> c = ContentInfoCompat.c(clip, new androidx.core.util.Predicate() { // from class: androidx.core.view.b
                @Override // androidx.core.util.Predicate
                public final boolean test(Object obj) {
                    return predicate.test((ClipData.Item) obj);
                }
            });
            if (c.first == null) {
                return Pair.create(null, contentInfo);
            }
            if (c.second == null) {
                return Pair.create(contentInfo, null);
            }
            return Pair.create(new ContentInfo.Builder(contentInfo).setClip((ClipData) c.first).build(), new ContentInfo.Builder(contentInfo).setClip((ClipData) c.second).build());
        }
    }

    /* loaded from: classes.dex */
    public interface c {
        void a(int i);

        void b(int i);

        @NonNull
        ContentInfoCompat build();

        void c(@Nullable Uri uri);

        void d(@NonNull ClipData clipData);

        void setExtras(@Nullable Bundle bundle);
    }

    @RequiresApi(31)
    /* loaded from: classes.dex */
    public static final class e implements f {
        @NonNull

        /* renamed from: a  reason: collision with root package name */
        public final ContentInfo f1129a;

        public e(@NonNull ContentInfo contentInfo) {
            this.f1129a = (ContentInfo) Preconditions.checkNotNull(contentInfo);
        }

        @Override // androidx.core.view.ContentInfoCompat.f
        @Nullable
        public Uri a() {
            return this.f1129a.getLinkUri();
        }

        @Override // androidx.core.view.ContentInfoCompat.f
        @NonNull
        public ContentInfo b() {
            return this.f1129a;
        }

        @Override // androidx.core.view.ContentInfoCompat.f
        @NonNull
        public ClipData c() {
            return this.f1129a.getClip();
        }

        @Override // androidx.core.view.ContentInfoCompat.f
        @Nullable
        public Bundle getExtras() {
            return this.f1129a.getExtras();
        }

        @Override // androidx.core.view.ContentInfoCompat.f
        public int getFlags() {
            return this.f1129a.getFlags();
        }

        @Override // androidx.core.view.ContentInfoCompat.f
        public int getSource() {
            return this.f1129a.getSource();
        }

        @NonNull
        public String toString() {
            return "ContentInfoCompat{" + this.f1129a + "}";
        }
    }

    /* loaded from: classes.dex */
    public interface f {
        @Nullable
        Uri a();

        @Nullable
        ContentInfo b();

        @NonNull
        ClipData c();

        @Nullable
        Bundle getExtras();

        int getFlags();

        int getSource();
    }

    /* loaded from: classes.dex */
    public static final class g implements f {
        @NonNull

        /* renamed from: a  reason: collision with root package name */
        public final ClipData f1130a;
        public final int b;
        public final int c;
        @Nullable
        public final Uri d;
        @Nullable
        public final Bundle e;

        public g(d dVar) {
            this.f1130a = (ClipData) Preconditions.checkNotNull(dVar.f1128a);
            this.b = Preconditions.checkArgumentInRange(dVar.b, 0, 5, "source");
            this.c = Preconditions.checkFlagsArgument(dVar.c, 1);
            this.d = dVar.d;
            this.e = dVar.e;
        }

        @Override // androidx.core.view.ContentInfoCompat.f
        @Nullable
        public Uri a() {
            return this.d;
        }

        @Override // androidx.core.view.ContentInfoCompat.f
        @Nullable
        public ContentInfo b() {
            return null;
        }

        @Override // androidx.core.view.ContentInfoCompat.f
        @NonNull
        public ClipData c() {
            return this.f1130a;
        }

        @Override // androidx.core.view.ContentInfoCompat.f
        @Nullable
        public Bundle getExtras() {
            return this.e;
        }

        @Override // androidx.core.view.ContentInfoCompat.f
        public int getFlags() {
            return this.c;
        }

        @Override // androidx.core.view.ContentInfoCompat.f
        public int getSource() {
            return this.b;
        }

        @NonNull
        public String toString() {
            String str;
            StringBuilder sb = new StringBuilder();
            sb.append("ContentInfoCompat{clip=");
            sb.append(this.f1130a.getDescription());
            sb.append(", source=");
            sb.append(ContentInfoCompat.d(this.b));
            sb.append(", flags=");
            sb.append(ContentInfoCompat.b(this.c));
            if (this.d == null) {
                str = "";
            } else {
                str = ", hasLinkUri(" + this.d.toString().length() + ")";
            }
            sb.append(str);
            sb.append(this.e != null ? ", hasExtras" : "");
            sb.append("}");
            return sb.toString();
        }
    }

    public ContentInfoCompat(@NonNull f fVar) {
        this.f1125a = fVar;
    }

    @NonNull
    public static ClipData a(@NonNull ClipDescription clipDescription, @NonNull List<ClipData.Item> list) {
        ClipData clipData = new ClipData(new ClipDescription(clipDescription), list.get(0));
        for (int i = 1; i < list.size(); i++) {
            clipData.addItem(list.get(i));
        }
        return clipData;
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public static String b(int i) {
        return (i & 1) != 0 ? "FLAG_CONVERT_TO_PLAIN_TEXT" : String.valueOf(i);
    }

    @NonNull
    public static Pair<ClipData, ClipData> c(@NonNull ClipData clipData, @NonNull androidx.core.util.Predicate<ClipData.Item> predicate) {
        ArrayList arrayList = null;
        ArrayList arrayList2 = null;
        for (int i = 0; i < clipData.getItemCount(); i++) {
            ClipData.Item itemAt = clipData.getItemAt(i);
            if (predicate.test(itemAt)) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(itemAt);
            } else {
                if (arrayList2 == null) {
                    arrayList2 = new ArrayList();
                }
                arrayList2.add(itemAt);
            }
        }
        if (arrayList == null) {
            return Pair.create(null, clipData);
        }
        if (arrayList2 == null) {
            return Pair.create(clipData, null);
        }
        return Pair.create(a(clipData.getDescription(), arrayList), a(clipData.getDescription(), arrayList2));
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public static String d(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? String.valueOf(i) : "SOURCE_PROCESS_TEXT" : "SOURCE_AUTOFILL" : "SOURCE_DRAG_AND_DROP" : "SOURCE_INPUT_METHOD" : "SOURCE_CLIPBOARD" : "SOURCE_APP";
    }

    @NonNull
    @RequiresApi(31)
    public static ContentInfoCompat toContentInfoCompat(@NonNull ContentInfo contentInfo) {
        return new ContentInfoCompat(new e(contentInfo));
    }

    @NonNull
    public ClipData getClip() {
        return this.f1125a.c();
    }

    @Nullable
    public Bundle getExtras() {
        return this.f1125a.getExtras();
    }

    public int getFlags() {
        return this.f1125a.getFlags();
    }

    @Nullable
    public Uri getLinkUri() {
        return this.f1125a.a();
    }

    public int getSource() {
        return this.f1125a.getSource();
    }

    @NonNull
    public Pair<ContentInfoCompat, ContentInfoCompat> partition(@NonNull androidx.core.util.Predicate<ClipData.Item> predicate) {
        ClipData c2 = this.f1125a.c();
        if (c2.getItemCount() == 1) {
            boolean test = predicate.test(c2.getItemAt(0));
            return Pair.create(test ? this : null, test ? null : this);
        }
        Pair<ClipData, ClipData> c3 = c(c2, predicate);
        if (c3.first == null) {
            return Pair.create(null, this);
        }
        if (c3.second == null) {
            return Pair.create(this, null);
        }
        return Pair.create(new Builder(this).setClip((ClipData) c3.first).build(), new Builder(this).setClip((ClipData) c3.second).build());
    }

    @NonNull
    @RequiresApi(31)
    public ContentInfo toContentInfo() {
        ContentInfo b2 = this.f1125a.b();
        Objects.requireNonNull(b2);
        return b2;
    }

    @NonNull
    public String toString() {
        return this.f1125a.toString();
    }

    @RequiresApi(31)
    /* loaded from: classes.dex */
    public static final class b implements c {
        @NonNull

        /* renamed from: a  reason: collision with root package name */
        public final ContentInfo.Builder f1127a;

        public b(@NonNull ClipData clipData, int i) {
            this.f1127a = new ContentInfo.Builder(clipData, i);
        }

        @Override // androidx.core.view.ContentInfoCompat.c
        public void a(int i) {
            this.f1127a.setFlags(i);
        }

        @Override // androidx.core.view.ContentInfoCompat.c
        public void b(int i) {
            this.f1127a.setSource(i);
        }

        @Override // androidx.core.view.ContentInfoCompat.c
        @NonNull
        public ContentInfoCompat build() {
            return new ContentInfoCompat(new e(this.f1127a.build()));
        }

        @Override // androidx.core.view.ContentInfoCompat.c
        public void c(@Nullable Uri uri) {
            this.f1127a.setLinkUri(uri);
        }

        @Override // androidx.core.view.ContentInfoCompat.c
        public void d(@NonNull ClipData clipData) {
            this.f1127a.setClip(clipData);
        }

        @Override // androidx.core.view.ContentInfoCompat.c
        public void setExtras(@Nullable Bundle bundle) {
            this.f1127a.setExtras(bundle);
        }

        public b(@NonNull ContentInfoCompat contentInfoCompat) {
            this.f1127a = new ContentInfo.Builder(contentInfoCompat.toContentInfo());
        }
    }

    /* loaded from: classes.dex */
    public static final class d implements c {
        @NonNull

        /* renamed from: a  reason: collision with root package name */
        public ClipData f1128a;
        public int b;
        public int c;
        @Nullable
        public Uri d;
        @Nullable
        public Bundle e;

        public d(@NonNull ClipData clipData, int i) {
            this.f1128a = clipData;
            this.b = i;
        }

        @Override // androidx.core.view.ContentInfoCompat.c
        public void a(int i) {
            this.c = i;
        }

        @Override // androidx.core.view.ContentInfoCompat.c
        public void b(int i) {
            this.b = i;
        }

        @Override // androidx.core.view.ContentInfoCompat.c
        @NonNull
        public ContentInfoCompat build() {
            return new ContentInfoCompat(new g(this));
        }

        @Override // androidx.core.view.ContentInfoCompat.c
        public void c(@Nullable Uri uri) {
            this.d = uri;
        }

        @Override // androidx.core.view.ContentInfoCompat.c
        public void d(@NonNull ClipData clipData) {
            this.f1128a = clipData;
        }

        @Override // androidx.core.view.ContentInfoCompat.c
        public void setExtras(@Nullable Bundle bundle) {
            this.e = bundle;
        }

        public d(@NonNull ContentInfoCompat contentInfoCompat) {
            this.f1128a = contentInfoCompat.getClip();
            this.b = contentInfoCompat.getSource();
            this.c = contentInfoCompat.getFlags();
            this.d = contentInfoCompat.getLinkUri();
            this.e = contentInfoCompat.getExtras();
        }
    }

    /* loaded from: classes.dex */
    public static final class Builder {
        @NonNull

        /* renamed from: a  reason: collision with root package name */
        public final c f1126a;

        public Builder(@NonNull ContentInfoCompat contentInfoCompat) {
            if (Build.VERSION.SDK_INT >= 31) {
                this.f1126a = new b(contentInfoCompat);
            } else {
                this.f1126a = new d(contentInfoCompat);
            }
        }

        @NonNull
        public ContentInfoCompat build() {
            return this.f1126a.build();
        }

        @NonNull
        public Builder setClip(@NonNull ClipData clipData) {
            this.f1126a.d(clipData);
            return this;
        }

        @NonNull
        public Builder setExtras(@Nullable Bundle bundle) {
            this.f1126a.setExtras(bundle);
            return this;
        }

        @NonNull
        public Builder setFlags(int i) {
            this.f1126a.a(i);
            return this;
        }

        @NonNull
        public Builder setLinkUri(@Nullable Uri uri) {
            this.f1126a.c(uri);
            return this;
        }

        @NonNull
        public Builder setSource(int i) {
            this.f1126a.b(i);
            return this;
        }

        public Builder(@NonNull ClipData clipData, int i) {
            if (Build.VERSION.SDK_INT >= 31) {
                this.f1126a = new b(clipData, i);
            } else {
                this.f1126a = new d(clipData, i);
            }
        }
    }

    @NonNull
    @RequiresApi(31)
    public static Pair<ContentInfo, ContentInfo> partition(@NonNull ContentInfo contentInfo, @NonNull Predicate<ClipData.Item> predicate) {
        return a.a(contentInfo, predicate);
    }
}
