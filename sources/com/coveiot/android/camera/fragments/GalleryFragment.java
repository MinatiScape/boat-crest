package com.coveiot.android.camera.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.ImageButton;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.navigation.NavArgsLazy;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.coveiot.android.camera.R;
import com.coveiot.android.camera.fragments.PhotoFragment;
import com.coveiot.android.camera.utils.ViewExtensionsKt;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class GalleryFragment extends Fragment {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public final NavArgsLazy h = new NavArgsLazy(Reflection.getOrCreateKotlinClass(GalleryFragmentArgs.class), new Function0<Bundle>() { // from class: com.coveiot.android.camera.fragments.GalleryFragment$special$$inlined$navArgs$1
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final Bundle invoke() {
            Bundle arguments = Fragment.this.getArguments();
            if (arguments != null) {
                return arguments;
            }
            throw new IllegalStateException("Fragment " + Fragment.this + " has null arguments");
        }
    });
    @Nullable
    public PhotoSelectionListener i;
    public List<File> j;

    @SuppressLint({"WrongConstant"})
    /* loaded from: classes3.dex */
    public final class MediaPagerAdapter extends FragmentStatePagerAdapter {
        public final /* synthetic */ GalleryFragment j;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public MediaPagerAdapter(@NotNull GalleryFragment galleryFragment, FragmentManager fm) {
            super(fm, 1);
            Intrinsics.checkNotNullParameter(fm, "fm");
            this.j = galleryFragment;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int getCount() {
            return 1;
        }

        @Override // androidx.fragment.app.FragmentStatePagerAdapter
        @NotNull
        public Fragment getItem(int i) {
            PhotoFragment.Companion companion = PhotoFragment.Companion;
            List list = this.j.j;
            if (list == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mediaList");
                list = null;
            }
            return companion.create((File) list.get(0));
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int getItemPosition(@NotNull Object obj) {
            Intrinsics.checkNotNullParameter(obj, "obj");
            return -2;
        }
    }

    /* loaded from: classes3.dex */
    public interface PhotoSelectionListener {
        void onPhototSelected(@NotNull Uri uri);
    }

    public static final boolean i(File file) {
        String[] extension_whitelist = GalleryFragmentKt.getEXTENSION_WHITELIST();
        Intrinsics.checkNotNullExpressionValue(file, "file");
        String extension = kotlin.io.e.getExtension(file);
        Locale ROOT = Locale.ROOT;
        Intrinsics.checkNotNullExpressionValue(ROOT, "ROOT");
        String upperCase = extension.toUpperCase(ROOT);
        Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase(locale)");
        return ArraysKt___ArraysKt.contains(extension_whitelist, upperCase);
    }

    public static final void j(final View view, final GalleryFragment this$0, final ViewPager viewPager, View view2) {
        Intrinsics.checkNotNullParameter(view, "$view");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AlertDialog create = new AlertDialog.Builder(view.getContext(), 16974373).setTitle(this$0.getString(R.string.delete_title)).setMessage(this$0.getString(R.string.delete_dialog)).setIcon(17301543).setPositiveButton(17039379, new DialogInterface.OnClickListener() { // from class: com.coveiot.android.camera.fragments.j
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                GalleryFragment.k(GalleryFragment.this, viewPager, view, dialogInterface, i);
            }
        }).setNegativeButton(17039369, (DialogInterface.OnClickListener) null).create();
        Intrinsics.checkNotNullExpressionValue(create, "Builder(view.context, an…                .create()");
        ViewExtensionsKt.showImmersive(create);
    }

    public static final void k(GalleryFragment this$0, ViewPager viewPager, View view, DialogInterface dialogInterface, int i) {
        FragmentManager fragmentManager;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(view, "$view");
        List<File> list = this$0.j;
        List<File> list2 = null;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mediaList");
            list = null;
        }
        File file = (File) CollectionsKt___CollectionsKt.getOrNull(list, viewPager.getCurrentItem());
        if (file != null) {
            file.delete();
            MediaScannerConnection.scanFile(view.getContext(), new String[]{file.getAbsolutePath()}, null, null);
            List<File> list3 = this$0.j;
            if (list3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mediaList");
                list3 = null;
            }
            list3.remove(viewPager.getCurrentItem());
            PagerAdapter adapter = viewPager.getAdapter();
            if (adapter != null) {
                adapter.notifyDataSetChanged();
            }
            List<File> list4 = this$0.j;
            if (list4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mediaList");
            } else {
                list2 = list4;
            }
            if (!list2.isEmpty() || (fragmentManager = this$0.getFragmentManager()) == null) {
                return;
            }
            fragmentManager.popBackStack();
        }
    }

    public static final void l(GalleryFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Navigation.findNavController(this$0.requireActivity(), R.id.fragment_container).navigateUp();
    }

    public static final void m(GalleryFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Navigation.findNavController(this$0.requireActivity(), R.id.fragment_container).navigateUp();
    }

    public static final void n(GalleryFragment this$0, ViewPager viewPager, View view, View view2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(view, "$view");
        List<File> list = this$0.j;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mediaList");
            list = null;
        }
        File file = (File) CollectionsKt___CollectionsKt.getOrNull(list, viewPager.getCurrentItem());
        if (file != null) {
            Context context = view.getContext();
            StringBuilder sb = new StringBuilder();
            Context context2 = this$0.getContext();
            Intrinsics.checkNotNull(context2);
            sb.append(context2.getPackageName());
            sb.append(".provider");
            Uri uri = FileProvider.getUriForFile(context, sb.toString(), file);
            PhotoSelectionListener photoSelectionListener = this$0.i;
            if (photoSelectionListener != null) {
                Intrinsics.checkNotNullExpressionValue(uri, "uri");
                photoSelectionListener.onPhototSelected(uri);
            }
        }
    }

    public static final void o(GalleryFragment this$0, ViewPager viewPager, View view, View view2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(view, "$view");
        List<File> list = this$0.j;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mediaList");
            list = null;
        }
        File file = (File) CollectionsKt___CollectionsKt.getOrNull(list, viewPager.getCurrentItem());
        if (file != null) {
            Intent intent = new Intent();
            String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(kotlin.io.e.getExtension(file));
            intent.putExtra("android.intent.extra.STREAM", FileProvider.getUriForFile(view.getContext(), "com.coveiot.android.leonardo.provider", file));
            intent.setType(mimeTypeFromExtension);
            intent.setAction("android.intent.action.SEND");
            intent.setFlags(1);
            this$0.startActivity(Intent.createChooser(intent, this$0.getString(R.string.share_hint)));
        }
    }

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Nullable
    public View _$_findCachedViewById(int i) {
        View findViewById;
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View view2 = getView();
            if (view2 == null || (findViewById = view2.findViewById(i)) == null) {
                return null;
            }
            map.put(Integer.valueOf(i), findViewById);
            return findViewById;
        }
        return view;
    }

    public final GalleryFragmentArgs h() {
        return (GalleryFragmentArgs) this.h.getValue();
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        super.onAttach(context);
        if (context instanceof PhotoSelectionListener) {
            this.i = (PhotoSelectionListener) context;
            return;
        }
        throw new RuntimeException("Must implement PhotoSelectionListener");
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        List<File> arrayList;
        List sortedDescending;
        super.onCreate(bundle);
        setRetainInstance(true);
        File[] listFiles = new File(h().getRootDirectory()).listFiles(new FileFilter() { // from class: com.coveiot.android.camera.fragments.p
            @Override // java.io.FileFilter
            public final boolean accept(File file) {
                boolean i;
                i = GalleryFragment.i(file);
                return i;
            }
        });
        if (listFiles == null || (sortedDescending = ArraysKt___ArraysKt.sortedDescending(listFiles)) == null || (arrayList = CollectionsKt___CollectionsKt.toMutableList((Collection) sortedDescending)) == null) {
            arrayList = new ArrayList<>();
        }
        this.j = arrayList;
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_gallery, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull final View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        final ViewPager viewPager = (ViewPager) view.findViewById(R.id.photo_view_pager);
        viewPager.setOffscreenPageLimit(2);
        FragmentManager childFragmentManager = getChildFragmentManager();
        Intrinsics.checkNotNullExpressionValue(childFragmentManager, "childFragmentManager");
        viewPager.setAdapter(new MediaPagerAdapter(this, childFragmentManager));
        if (Build.VERSION.SDK_INT >= 28) {
            View findViewById = view.findViewById(R.id.cutout_safe_area);
            Intrinsics.checkNotNullExpressionValue(findViewById, "view.findViewById<Constr…t>(R.id.cutout_safe_area)");
            ViewExtensionsKt.padWithDisplayCutout(findViewById);
        }
        ((ImageButton) view.findViewById(R.id.back_button)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.camera.fragments.m
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                GalleryFragment.l(GalleryFragment.this, view2);
            }
        });
        ((ImageButton) view.findViewById(R.id.cancel_btn)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.camera.fragments.l
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                GalleryFragment.m(GalleryFragment.this, view2);
            }
        });
        ((ImageButton) view.findViewById(R.id.tick_button)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.camera.fragments.o
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                GalleryFragment.n(GalleryFragment.this, viewPager, view, view2);
            }
        });
        ((ImageButton) view.findViewById(R.id.share_button)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.camera.fragments.n
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                GalleryFragment.o(GalleryFragment.this, viewPager, view, view2);
            }
        });
        ((ImageButton) view.findViewById(R.id.delete_button)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.camera.fragments.k
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                GalleryFragment.j(view, this, viewPager, view2);
            }
        });
    }
}
