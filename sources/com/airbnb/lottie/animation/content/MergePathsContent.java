package com.airbnb.lottie.animation.content;

import android.annotation.TargetApi;
import android.graphics.Path;
import android.os.Build;
import com.airbnb.lottie.model.content.MergePaths;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
@TargetApi(19)
/* loaded from: classes.dex */
public class MergePathsContent implements b, com.airbnb.lottie.animation.content.a {
    public final String d;
    public final MergePaths f;

    /* renamed from: a  reason: collision with root package name */
    public final Path f1998a = new Path();
    public final Path b = new Path();
    public final Path c = new Path();
    public final List<b> e = new ArrayList();

    /* loaded from: classes.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f1999a;

        static {
            int[] iArr = new int[MergePaths.MergePathsMode.values().length];
            f1999a = iArr;
            try {
                iArr[MergePaths.MergePathsMode.MERGE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1999a[MergePaths.MergePathsMode.ADD.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1999a[MergePaths.MergePathsMode.SUBTRACT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1999a[MergePaths.MergePathsMode.INTERSECT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1999a[MergePaths.MergePathsMode.EXCLUDE_INTERSECTIONS.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public MergePathsContent(MergePaths mergePaths) {
        if (Build.VERSION.SDK_INT >= 19) {
            this.d = mergePaths.getName();
            this.f = mergePaths;
            return;
        }
        throw new IllegalStateException("Merge paths are not supported pre-KitKat.");
    }

    public final void a() {
        for (int i = 0; i < this.e.size(); i++) {
            this.c.addPath(this.e.get(i).getPath());
        }
    }

    @Override // com.airbnb.lottie.animation.content.a
    public void absorbContent(ListIterator<Content> listIterator) {
        while (listIterator.hasPrevious() && listIterator.previous() != this) {
        }
        while (listIterator.hasPrevious()) {
            Content previous = listIterator.previous();
            if (previous instanceof b) {
                this.e.add((b) previous);
                listIterator.remove();
            }
        }
    }

    @TargetApi(19)
    public final void b(Path.Op op) {
        this.b.reset();
        this.f1998a.reset();
        for (int size = this.e.size() - 1; size >= 1; size--) {
            b bVar = this.e.get(size);
            if (bVar instanceof ContentGroup) {
                ContentGroup contentGroup = (ContentGroup) bVar;
                List<b> c = contentGroup.c();
                for (int size2 = c.size() - 1; size2 >= 0; size2--) {
                    Path path = c.get(size2).getPath();
                    path.transform(contentGroup.d());
                    this.b.addPath(path);
                }
            } else {
                this.b.addPath(bVar.getPath());
            }
        }
        b bVar2 = this.e.get(0);
        if (bVar2 instanceof ContentGroup) {
            ContentGroup contentGroup2 = (ContentGroup) bVar2;
            List<b> c2 = contentGroup2.c();
            for (int i = 0; i < c2.size(); i++) {
                Path path2 = c2.get(i).getPath();
                path2.transform(contentGroup2.d());
                this.f1998a.addPath(path2);
            }
        } else {
            this.f1998a.set(bVar2.getPath());
        }
        this.c.op(this.f1998a, this.b, op);
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public String getName() {
        return this.d;
    }

    @Override // com.airbnb.lottie.animation.content.b
    public Path getPath() {
        this.c.reset();
        if (this.f.isHidden()) {
            return this.c;
        }
        int i = a.f1999a[this.f.getMode().ordinal()];
        if (i == 1) {
            a();
        } else if (i == 2) {
            b(Path.Op.UNION);
        } else if (i == 3) {
            b(Path.Op.REVERSE_DIFFERENCE);
        } else if (i == 4) {
            b(Path.Op.INTERSECT);
        } else if (i == 5) {
            b(Path.Op.XOR);
        }
        return this.c;
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public void setContents(List<Content> list, List<Content> list2) {
        for (int i = 0; i < this.e.size(); i++) {
            this.e.get(i).setContents(list, list2);
        }
    }
}
