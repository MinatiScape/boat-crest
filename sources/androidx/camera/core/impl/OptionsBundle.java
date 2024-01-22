package androidx.camera.core.impl;

import android.util.ArrayMap;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.core.impl.Config;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
/* loaded from: classes.dex */
public class OptionsBundle implements Config {
    public static final Comparator<Config.Option<?>> ID_COMPARE;

    /* renamed from: a  reason: collision with root package name */
    public static final OptionsBundle f709a;
    public final TreeMap<Config.Option<?>, Map<Config.OptionPriority, Object>> mOptions;

    static {
        q qVar = new Comparator() { // from class: androidx.camera.core.impl.q
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int b;
                b = OptionsBundle.b((Config.Option) obj, (Config.Option) obj2);
                return b;
            }
        };
        ID_COMPARE = qVar;
        f709a = new OptionsBundle(new TreeMap(qVar));
    }

    public OptionsBundle(TreeMap<Config.Option<?>, Map<Config.OptionPriority, Object>> treeMap) {
        this.mOptions = treeMap;
    }

    public static /* synthetic */ int b(Config.Option option, Config.Option option2) {
        return option.getId().compareTo(option2.getId());
    }

    @NonNull
    public static OptionsBundle emptyBundle() {
        return f709a;
    }

    @NonNull
    public static OptionsBundle from(@NonNull Config config) {
        if (OptionsBundle.class.equals(config.getClass())) {
            return (OptionsBundle) config;
        }
        TreeMap treeMap = new TreeMap(ID_COMPARE);
        for (Config.Option<?> option : config.listOptions()) {
            Set<Config.OptionPriority> priorities = config.getPriorities(option);
            ArrayMap arrayMap = new ArrayMap();
            for (Config.OptionPriority optionPriority : priorities) {
                arrayMap.put(optionPriority, config.retrieveOptionWithPriority(option, optionPriority));
            }
            treeMap.put(option, arrayMap);
        }
        return new OptionsBundle(treeMap);
    }

    @Override // androidx.camera.core.impl.Config
    public boolean containsOption(@NonNull Config.Option<?> option) {
        return this.mOptions.containsKey(option);
    }

    /* JADX WARN: Removed duplicated region for block: B:5:0x001a  */
    @Override // androidx.camera.core.impl.Config
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void findOptions(@androidx.annotation.NonNull java.lang.String r4, @androidx.annotation.NonNull androidx.camera.core.impl.Config.OptionMatcher r5) {
        /*
            r3 = this;
            java.lang.Class<java.lang.Void> r0 = java.lang.Void.class
            androidx.camera.core.impl.Config$Option r0 = androidx.camera.core.impl.Config.Option.create(r4, r0)
            java.util.TreeMap<androidx.camera.core.impl.Config$Option<?>, java.util.Map<androidx.camera.core.impl.Config$OptionPriority, java.lang.Object>> r1 = r3.mOptions
            java.util.SortedMap r0 = r1.tailMap(r0)
            java.util.Set r0 = r0.entrySet()
            java.util.Iterator r0 = r0.iterator()
        L14:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L3d
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            java.lang.Object r2 = r1.getKey()
            androidx.camera.core.impl.Config$Option r2 = (androidx.camera.core.impl.Config.Option) r2
            java.lang.String r2 = r2.getId()
            boolean r2 = r2.startsWith(r4)
            if (r2 != 0) goto L31
            goto L3d
        L31:
            java.lang.Object r1 = r1.getKey()
            androidx.camera.core.impl.Config$Option r1 = (androidx.camera.core.impl.Config.Option) r1
            boolean r1 = r5.onOptionMatched(r1)
            if (r1 != 0) goto L14
        L3d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.core.impl.OptionsBundle.findOptions(java.lang.String, androidx.camera.core.impl.Config$OptionMatcher):void");
    }

    @Override // androidx.camera.core.impl.Config
    @NonNull
    public Config.OptionPriority getOptionPriority(@NonNull Config.Option<?> option) {
        Map<Config.OptionPriority, Object> map = this.mOptions.get(option);
        if (map != null) {
            return (Config.OptionPriority) Collections.min(map.keySet());
        }
        throw new IllegalArgumentException("Option does not exist: " + option);
    }

    @Override // androidx.camera.core.impl.Config
    @NonNull
    public Set<Config.OptionPriority> getPriorities(@NonNull Config.Option<?> option) {
        Map<Config.OptionPriority, Object> map = this.mOptions.get(option);
        if (map == null) {
            return Collections.emptySet();
        }
        return Collections.unmodifiableSet(map.keySet());
    }

    @Override // androidx.camera.core.impl.Config
    @NonNull
    public Set<Config.Option<?>> listOptions() {
        return Collections.unmodifiableSet(this.mOptions.keySet());
    }

    @Override // androidx.camera.core.impl.Config
    @Nullable
    public <ValueT> ValueT retrieveOption(@NonNull Config.Option<ValueT> option) {
        Map<Config.OptionPriority, Object> map = this.mOptions.get(option);
        if (map != null) {
            return (ValueT) map.get((Config.OptionPriority) Collections.min(map.keySet()));
        }
        throw new IllegalArgumentException("Option does not exist: " + option);
    }

    @Override // androidx.camera.core.impl.Config
    @Nullable
    public <ValueT> ValueT retrieveOptionWithPriority(@NonNull Config.Option<ValueT> option, @NonNull Config.OptionPriority optionPriority) {
        Map<Config.OptionPriority, Object> map = this.mOptions.get(option);
        if (map != null) {
            if (map.containsKey(optionPriority)) {
                return (ValueT) map.get(optionPriority);
            }
            throw new IllegalArgumentException("Option does not exist: " + option + " with priority=" + optionPriority);
        }
        throw new IllegalArgumentException("Option does not exist: " + option);
    }

    @Override // androidx.camera.core.impl.Config
    @Nullable
    public <ValueT> ValueT retrieveOption(@NonNull Config.Option<ValueT> option, @Nullable ValueT valuet) {
        try {
            return (ValueT) retrieveOption(option);
        } catch (IllegalArgumentException unused) {
            return valuet;
        }
    }
}
