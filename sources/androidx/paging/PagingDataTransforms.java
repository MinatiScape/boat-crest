package androidx.paging;

import androidx.annotation.CheckResult;
import androidx.exifinterface.media.ExifInterface;
import com.clevertap.android.sdk.leanplum.Constants;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.ExecutorsKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/*  JADX ERROR: JadxRuntimeException in pass: ClassModifier
    jadx.core.utils.exceptions.JadxRuntimeException: Not class type: T
    	at jadx.core.dex.info.ClassInfo.checkClassType(ClassInfo.java:53)
    	at jadx.core.dex.info.ClassInfo.fromType(ClassInfo.java:31)
    	at jadx.core.dex.visitors.ClassModifier.removeSyntheticFields(ClassModifier.java:83)
    	at jadx.core.dex.visitors.ClassModifier.visit(ClassModifier.java:61)
    	at jadx.core.dex.visitors.ClassModifier.visit(ClassModifier.java:55)
    */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u001aZ\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00010\u0003\"\b\b\u0000\u0010\u0001*\u00020\u0000\"\b\b\u0001\u0010\u0002*\u00020\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00032\"\u0010\u0006\u001a\u001e\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00000\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\b\u001aH\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00010\u0003\"\b\b\u0000\u0010\u0001*\u00020\u0000\"\b\b\u0001\u0010\u0002*\u00020\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00032\u0006\u0010\n\u001a\u00020\t2\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u000bH\u0007\u001a`\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00010\u0003\"\b\b\u0000\u0010\u0001*\u00020\u0000\"\b\b\u0001\u0010\u0002*\u00020\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00032(\u0010\u0006\u001a$\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\f0\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00000\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b\r\u0010\b\u001aN\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00010\u0003\"\b\b\u0000\u0010\u0001*\u00020\u0000\"\b\b\u0001\u0010\u0002*\u00020\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00032\u0006\u0010\n\u001a\u00020\t2\u0018\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\f0\u000bH\u0007\u001aP\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003\"\b\b\u0000\u0010\u0001*\u00020\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00032\"\u0010\u000f\u001a\u001e\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00000\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\b\u001a>\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003\"\b\b\u0000\u0010\u0001*\u00020\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00032\u0006\u0010\n\u001a\u00020\t2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u000e0\u000bH\u0007\u001ap\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00010\u0003\"\b\b\u0000\u0010\u0001*\u00028\u0001\"\b\b\u0001\u0010\u0002*\u00020\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00032\b\b\u0002\u0010\u0012\u001a\u00020\u00112.\u0010\u0014\u001a*\b\u0001\u0012\u0006\u0012\u0004\u0018\u00018\u0000\u0012\u0006\u0012\u0004\u0018\u00018\u0000\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00018\u00010\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00000\u0013H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016\u001a^\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003\"\b\b\u0000\u0010\u0002*\u00020\u0000\"\b\b\u0001\u0010\u0001*\u00028\u0000*\b\u0012\u0004\u0012\u00028\u00010\u00032\b\b\u0002\u0010\u0012\u001a\u00020\u00112\u0006\u0010\n\u001a\u00020\t2\u001e\u0010\u0014\u001a\u001a\u0012\u0006\u0012\u0004\u0018\u00018\u0001\u0012\u0006\u0012\u0004\u0018\u00018\u0001\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u0004H\u0007\u001a;\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003\"\b\b\u0000\u0010\u0001*\u00020\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00032\b\b\u0002\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00028\u0000H\u0007¢\u0006\u0004\b\u0018\u0010\u0019\u001a;\u0010\u001a\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003\"\b\b\u0000\u0010\u0001*\u00020\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00032\b\b\u0002\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00028\u0000H\u0007¢\u0006\u0004\b\u001a\u0010\u0019\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001b"}, d2 = {"", ExifInterface.GPS_DIRECTION_TRUE, "R", "Landroidx/paging/PagingData;", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "transform", "map", "(Landroidx/paging/PagingData;Lkotlin/jvm/functions/Function2;)Landroidx/paging/PagingData;", "Ljava/util/concurrent/Executor;", "executor", "Lkotlin/Function1;", "", "flatMap", "", "predicate", "filter", "Landroidx/paging/TerminalSeparatorType;", "terminalSeparatorType", "Lkotlin/Function3;", "generator", "insertSeparators", "(Landroidx/paging/PagingData;Landroidx/paging/TerminalSeparatorType;Lkotlin/jvm/functions/Function3;)Landroidx/paging/PagingData;", Constants.IAP_ITEM_PARAM, "insertHeaderItem", "(Landroidx/paging/PagingData;Landroidx/paging/TerminalSeparatorType;Ljava/lang/Object;)Landroidx/paging/PagingData;", "insertFooterItem", "paging-common"}, k = 2, mv = {1, 5, 1})
@JvmName(name = "PagingDataTransforms")
/* loaded from: classes.dex */
public final class PagingDataTransforms {

    @DebugMetadata(c = "androidx.paging.PagingDataTransforms$filter$2$1", f = "PagingDataTransforms.kt", i = {}, l = {108}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes.dex */
    public static final class a<T> extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super PageEvent<T>>, Object> {
        public final /* synthetic */ PageEvent<T> $event;
        public final /* synthetic */ Function1<T, Boolean> $predicate;
        public int label;

        @DebugMetadata(c = "androidx.paging.PagingDataTransforms$filter$2$1$1", f = "PagingDataTransforms.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: androidx.paging.PagingDataTransforms$a$a */
        /* loaded from: classes.dex */
        public static final class C0163a extends SuspendLambda implements Function2<T, Continuation<? super Boolean>, Object> {
            public final /* synthetic */ Function1<T, Boolean> $predicate;
            public /* synthetic */ Object L$0;
            public int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public C0163a(Function1<? super T, Boolean> function1, Continuation<? super C0163a> continuation) {
                super(2, continuation);
                this.$predicate = function1;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                C0163a c0163a = new C0163a(this.$predicate, continuation);
                c0163a.L$0 = obj;
                return c0163a;
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Object invoke(Object obj, Continuation<? super Boolean> continuation) {
                return invoke2((C0163a) obj, continuation);
            }

            @Nullable
            /* renamed from: invoke */
            public final Object invoke2(@NotNull T t, @Nullable Continuation<? super Boolean> continuation) {
                return ((C0163a) create(t, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    return this.$predicate.invoke(this.L$0);
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public a(PageEvent<T> pageEvent, Function1<? super T, Boolean> function1, Continuation<? super a> continuation) {
            super(2, continuation);
            this.$event = pageEvent;
            this.$predicate = function1;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(this.$event, this.$predicate, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Object obj) {
            return invoke(coroutineScope, (Continuation) ((Continuation) obj));
        }

        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super PageEvent<T>> continuation) {
            return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                PageEvent<T> pageEvent = this.$event;
                C0163a c0163a = new C0163a(this.$predicate, null);
                this.label = 1;
                obj = pageEvent.filter(c0163a, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                ResultKt.throwOnFailure(obj);
            }
            return obj;
        }
    }

    @DebugMetadata(c = "androidx.paging.PagingDataTransforms$flatMap$2$1", f = "PagingDataTransforms.kt", i = {}, l = {83}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes.dex */
    public static final class b<R> extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super PageEvent<R>>, Object> {
        public final /* synthetic */ PageEvent<T> $event;
        public final /* synthetic */ Function1<T, Iterable<R>> $transform;
        public int label;

        @DebugMetadata(c = "androidx.paging.PagingDataTransforms$flatMap$2$1$1", f = "PagingDataTransforms.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes.dex */
        public static final class a<T> extends SuspendLambda implements Function2<T, Continuation<? super Iterable<? extends R>>, Object> {
            public final /* synthetic */ Function1<T, Iterable<R>> $transform;
            public /* synthetic */ Object L$0;
            public int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public a(Function1<? super T, ? extends Iterable<? extends R>> function1, Continuation<? super a> continuation) {
                super(2, continuation);
                this.$transform = function1;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                a aVar = new a(this.$transform, continuation);
                aVar.L$0 = obj;
                return aVar;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                return invoke((a<T>) obj, (Continuation) ((Continuation) obj2));
            }

            @Nullable
            public final Object invoke(@NotNull T t, @Nullable Continuation<? super Iterable<? extends R>> continuation) {
                return ((a) create(t, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    return this.$transform.invoke(this.L$0);
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public b(PageEvent<T> pageEvent, Function1<? super T, ? extends Iterable<? extends R>> function1, Continuation<? super b> continuation) {
            super(2, continuation);
            this.$event = pageEvent;
            this.$transform = function1;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new b(this.$event, this.$transform, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Object obj) {
            return invoke(coroutineScope, (Continuation) ((Continuation) obj));
        }

        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super PageEvent<R>> continuation) {
            return ((b) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                PageEvent<T> pageEvent = this.$event;
                a aVar = new a(this.$transform, null);
                this.label = 1;
                obj = pageEvent.flatMap(aVar, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                ResultKt.throwOnFailure(obj);
            }
            return obj;
        }
    }

    @DebugMetadata(c = "androidx.paging.PagingDataTransforms$insertFooterItem$1", f = "PagingDataTransforms.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes.dex */
    public static final class c<T> extends SuspendLambda implements Function3<T, T, Continuation<? super T>, Object> {
        public final /* synthetic */ T $item;
        public /* synthetic */ Object L$0;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(T t, Continuation<? super c> continuation) {
            super(3, continuation);
            this.$item = t;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            return invoke(obj, obj2, (Continuation<? super Object>) obj3);
        }

        @Nullable
        public final Object invoke(@Nullable T t, @Nullable T t2, @Nullable Continuation<? super T> continuation) {
            c cVar = new c(this.$item, continuation);
            cVar.L$0 = t2;
            return cVar.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (this.L$0 == null) {
                    return this.$item;
                }
                return null;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "androidx.paging.PagingDataTransforms$insertHeaderItem$1", f = "PagingDataTransforms.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes.dex */
    public static final class d<T> extends SuspendLambda implements Function3<T, T, Continuation<? super T>, Object> {
        public final /* synthetic */ T $item;
        public /* synthetic */ Object L$0;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public d(T t, Continuation<? super d> continuation) {
            super(3, continuation);
            this.$item = t;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            return invoke(obj, obj2, (Continuation<? super Object>) obj3);
        }

        @Nullable
        public final Object invoke(@Nullable T t, @Nullable T t2, @Nullable Continuation<? super T> continuation) {
            d dVar = new d(this.$item, continuation);
            dVar.L$0 = t;
            return dVar.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (this.L$0 == null) {
                    return this.$item;
                }
                return null;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "androidx.paging.PagingDataTransforms$insertSeparators$1", f = "PagingDataTransforms.kt", i = {}, l = {261}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes.dex */
    public static final class e<R, T> extends SuspendLambda implements Function3<T, T, Continuation<? super R>, Object> {
        public final /* synthetic */ Executor $executor;
        public final /* synthetic */ Function2<T, T, R> $generator;
        public /* synthetic */ Object L$0;
        public /* synthetic */ Object L$1;
        public int label;

        @DebugMetadata(c = "androidx.paging.PagingDataTransforms$insertSeparators$1$1", f = "PagingDataTransforms.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super R>, Object> {
            public final /* synthetic */ T $after;
            public final /* synthetic */ T $before;
            public final /* synthetic */ Function2<T, T, R> $generator;
            public int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public a(Function2<? super T, ? super T, ? extends R> function2, T t, T t2, Continuation<? super a> continuation) {
                super(2, continuation);
                this.$generator = function2;
                this.$before = t;
                this.$after = t2;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new a(this.$generator, this.$before, this.$after, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Object obj) {
                return invoke(coroutineScope, (Continuation) ((Continuation) obj));
            }

            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super R> continuation) {
                return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    return this.$generator.invoke(this.$before, this.$after);
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public e(Executor executor, Function2<? super T, ? super T, ? extends R> function2, Continuation<? super e> continuation) {
            super(3, continuation);
            this.$executor = executor;
            this.$generator = function2;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            return invoke(obj, obj2, (Continuation) ((Continuation) obj3));
        }

        @Nullable
        public final Object invoke(@Nullable T t, @Nullable T t2, @Nullable Continuation<? super R> continuation) {
            e eVar = new e(this.$executor, this.$generator, continuation);
            eVar.L$0 = t;
            eVar.L$1 = t2;
            return eVar.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Object obj2 = this.L$0;
                Object obj3 = this.L$1;
                CoroutineDispatcher from = ExecutorsKt.from(this.$executor);
                a aVar = new a(this.$generator, obj2, obj3, null);
                this.L$0 = null;
                this.label = 1;
                obj = BuildersKt.withContext(from, aVar, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                ResultKt.throwOnFailure(obj);
            }
            return obj;
        }
    }

    @DebugMetadata(c = "androidx.paging.PagingDataTransforms$map$2$1", f = "PagingDataTransforms.kt", i = {}, l = {57}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes.dex */
    public static final class f<R> extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super PageEvent<R>>, Object> {
        public final /* synthetic */ PageEvent<T> $event;
        public final /* synthetic */ Function1<T, R> $transform;
        public int label;

        @DebugMetadata(c = "androidx.paging.PagingDataTransforms$map$2$1$1", f = "PagingDataTransforms.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes.dex */
        public static final class a<T> extends SuspendLambda implements Function2<T, Continuation<? super R>, Object> {
            public final /* synthetic */ Function1<T, R> $transform;
            public /* synthetic */ Object L$0;
            public int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public a(Function1<? super T, ? extends R> function1, Continuation<? super a> continuation) {
                super(2, continuation);
                this.$transform = function1;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                a aVar = new a(this.$transform, continuation);
                aVar.L$0 = obj;
                return aVar;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                return invoke((a<T>) obj, (Continuation) ((Continuation) obj2));
            }

            @Nullable
            public final Object invoke(@NotNull T t, @Nullable Continuation<? super R> continuation) {
                return ((a) create(t, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    return this.$transform.invoke(this.L$0);
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public f(PageEvent<T> pageEvent, Function1<? super T, ? extends R> function1, Continuation<? super f> continuation) {
            super(2, continuation);
            this.$event = pageEvent;
            this.$transform = function1;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new f(this.$event, this.$transform, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Object obj) {
            return invoke(coroutineScope, (Continuation) ((Continuation) obj));
        }

        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super PageEvent<R>> continuation) {
            return ((f) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                PageEvent<T> pageEvent = this.$event;
                a aVar = new a(this.$transform, null);
                this.label = 1;
                obj = pageEvent.map(aVar, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                ResultKt.throwOnFailure(obj);
            }
            return obj;
        }
    }

    @CheckResult
    public static final /* synthetic */ PagingData filter(PagingData pagingData, final Function2 predicate) {
        Intrinsics.checkNotNullParameter(pagingData, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        final Flow flow$paging_common = pagingData.getFlow$paging_common();
        return new PagingData(new Flow<PageEvent<T>>() { // from class: androidx.paging.PagingDataTransforms$filter$$inlined$transform$1

            @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001b\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006¸\u0006\u0007"}, d2 = {"kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3", "Lkotlinx/coroutines/flow/FlowCollector;", "value", "", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core", "androidx/paging/PagingDataTransforms$transform$$inlined$map$1$2"}, k = 1, mv = {1, 5, 1})
            /* renamed from: androidx.paging.PagingDataTransforms$filter$$inlined$transform$1$2  reason: invalid class name */
            /* loaded from: classes.dex */
            public static final class AnonymousClass2 implements FlowCollector<PageEvent<T>> {
                public final /* synthetic */ FlowCollector h;
                public final /* synthetic */ Function2 i;

                @Metadata(bv = {1, 0, 3}, d1 = {}, d2 = {}, k = 3, mv = {1, 5, 1})
                @DebugMetadata(c = "androidx.paging.PagingDataTransforms$filter$$inlined$transform$1$2", f = "PagingDataTransforms.kt", i = {}, l = {138, 138}, m = "emit", n = {}, s = {})
                /* renamed from: androidx.paging.PagingDataTransforms$filter$$inlined$transform$1$2$1  reason: invalid class name */
                /* loaded from: classes.dex */
                public static final class AnonymousClass1 extends ContinuationImpl {
                    public Object L$0;
                    public int label;
                    public /* synthetic */ Object result;

                    public AnonymousClass1(Continuation continuation) {
                        super(continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @Nullable
                    public final Object invokeSuspend(@NotNull Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(FlowCollector flowCollector, Function2 function2) {
                    this.h = flowCollector;
                    this.i = function2;
                }

                /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
                /* JADX WARN: Removed duplicated region for block: B:16:0x003c  */
                /* JADX WARN: Removed duplicated region for block: B:22:0x005e A[RETURN] */
                @Override // kotlinx.coroutines.flow.FlowCollector
                @org.jetbrains.annotations.Nullable
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public java.lang.Object emit(java.lang.Object r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation r8) {
                    /*
                        r6 = this;
                        boolean r0 = r8 instanceof androidx.paging.PagingDataTransforms$filter$$inlined$transform$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r8
                        androidx.paging.PagingDataTransforms$filter$$inlined$transform$1$2$1 r0 = (androidx.paging.PagingDataTransforms$filter$$inlined$transform$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        androidx.paging.PagingDataTransforms$filter$$inlined$transform$1$2$1 r0 = new androidx.paging.PagingDataTransforms$filter$$inlined$transform$1$2$1
                        r0.<init>(r8)
                    L18:
                        java.lang.Object r8 = r0.result
                        java.lang.Object r1 = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
                        int r2 = r0.label
                        r3 = 2
                        r4 = 1
                        if (r2 == 0) goto L3c
                        if (r2 == r4) goto L34
                        if (r2 != r3) goto L2c
                        kotlin.ResultKt.throwOnFailure(r8)
                        goto L5f
                    L2c:
                        java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                        java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
                        r7.<init>(r8)
                        throw r7
                    L34:
                        java.lang.Object r7 = r0.L$0
                        kotlinx.coroutines.flow.FlowCollector r7 = (kotlinx.coroutines.flow.FlowCollector) r7
                        kotlin.ResultKt.throwOnFailure(r8)
                        goto L53
                    L3c:
                        kotlin.ResultKt.throwOnFailure(r8)
                        kotlinx.coroutines.flow.FlowCollector r8 = r6.h
                        androidx.paging.PageEvent r7 = (androidx.paging.PageEvent) r7
                        kotlin.jvm.functions.Function2 r2 = r6.i
                        r0.L$0 = r8
                        r0.label = r4
                        java.lang.Object r7 = r7.filter(r2, r0)
                        if (r7 != r1) goto L50
                        return r1
                    L50:
                        r5 = r8
                        r8 = r7
                        r7 = r5
                    L53:
                        r2 = 0
                        r0.L$0 = r2
                        r0.label = r3
                        java.lang.Object r7 = r7.emit(r8, r0)
                        if (r7 != r1) goto L5f
                        return r1
                    L5f:
                        kotlin.Unit r7 = kotlin.Unit.INSTANCE
                        return r7
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.paging.PagingDataTransforms$filter$$inlined$transform$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            @Nullable
            public Object collect(@NotNull FlowCollector flowCollector, @NotNull Continuation continuation) {
                Object collect = Flow.this.collect(new AnonymousClass2(flowCollector, predicate), continuation);
                return collect == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? collect : Unit.INSTANCE;
            }
        }, pagingData.getReceiver$paging_common());
    }

    @CheckResult
    public static final /* synthetic */ PagingData flatMap(PagingData pagingData, final Function2 transform) {
        Intrinsics.checkNotNullParameter(pagingData, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        final Flow flow$paging_common = pagingData.getFlow$paging_common();
        return new PagingData(new Flow<PageEvent<R>>() { // from class: androidx.paging.PagingDataTransforms$flatMap$$inlined$transform$1

            /* JADX INFO: Add missing generic type declarations: [T] */
            @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001b\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006¸\u0006\u0007"}, d2 = {"kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3", "Lkotlinx/coroutines/flow/FlowCollector;", "value", "", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core", "androidx/paging/PagingDataTransforms$transform$$inlined$map$1$2"}, k = 1, mv = {1, 5, 1})
            /* renamed from: androidx.paging.PagingDataTransforms$flatMap$$inlined$transform$1$2  reason: invalid class name */
            /* loaded from: classes.dex */
            public static final class AnonymousClass2<T> implements FlowCollector<PageEvent<T>> {
                public final /* synthetic */ FlowCollector h;
                public final /* synthetic */ Function2 i;

                @Metadata(bv = {1, 0, 3}, d1 = {}, d2 = {}, k = 3, mv = {1, 5, 1})
                @DebugMetadata(c = "androidx.paging.PagingDataTransforms$flatMap$$inlined$transform$1$2", f = "PagingDataTransforms.kt", i = {}, l = {138, 138}, m = "emit", n = {}, s = {})
                /* renamed from: androidx.paging.PagingDataTransforms$flatMap$$inlined$transform$1$2$1  reason: invalid class name */
                /* loaded from: classes.dex */
                public static final class AnonymousClass1 extends ContinuationImpl {
                    public Object L$0;
                    public int label;
                    public /* synthetic */ Object result;

                    public AnonymousClass1(Continuation continuation) {
                        super(continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @Nullable
                    public final Object invokeSuspend(@NotNull Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(FlowCollector flowCollector, Function2 function2) {
                    this.h = flowCollector;
                    this.i = function2;
                }

                /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
                /* JADX WARN: Removed duplicated region for block: B:16:0x003c  */
                /* JADX WARN: Removed duplicated region for block: B:22:0x005e A[RETURN] */
                @Override // kotlinx.coroutines.flow.FlowCollector
                @org.jetbrains.annotations.Nullable
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public java.lang.Object emit(java.lang.Object r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation r8) {
                    /*
                        r6 = this;
                        boolean r0 = r8 instanceof androidx.paging.PagingDataTransforms$flatMap$$inlined$transform$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r8
                        androidx.paging.PagingDataTransforms$flatMap$$inlined$transform$1$2$1 r0 = (androidx.paging.PagingDataTransforms$flatMap$$inlined$transform$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        androidx.paging.PagingDataTransforms$flatMap$$inlined$transform$1$2$1 r0 = new androidx.paging.PagingDataTransforms$flatMap$$inlined$transform$1$2$1
                        r0.<init>(r8)
                    L18:
                        java.lang.Object r8 = r0.result
                        java.lang.Object r1 = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
                        int r2 = r0.label
                        r3 = 2
                        r4 = 1
                        if (r2 == 0) goto L3c
                        if (r2 == r4) goto L34
                        if (r2 != r3) goto L2c
                        kotlin.ResultKt.throwOnFailure(r8)
                        goto L5f
                    L2c:
                        java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                        java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
                        r7.<init>(r8)
                        throw r7
                    L34:
                        java.lang.Object r7 = r0.L$0
                        kotlinx.coroutines.flow.FlowCollector r7 = (kotlinx.coroutines.flow.FlowCollector) r7
                        kotlin.ResultKt.throwOnFailure(r8)
                        goto L53
                    L3c:
                        kotlin.ResultKt.throwOnFailure(r8)
                        kotlinx.coroutines.flow.FlowCollector r8 = r6.h
                        androidx.paging.PageEvent r7 = (androidx.paging.PageEvent) r7
                        kotlin.jvm.functions.Function2 r2 = r6.i
                        r0.L$0 = r8
                        r0.label = r4
                        java.lang.Object r7 = r7.flatMap(r2, r0)
                        if (r7 != r1) goto L50
                        return r1
                    L50:
                        r5 = r8
                        r8 = r7
                        r7 = r5
                    L53:
                        r2 = 0
                        r0.L$0 = r2
                        r0.label = r3
                        java.lang.Object r7 = r7.emit(r8, r0)
                        if (r7 != r1) goto L5f
                        return r1
                    L5f:
                        kotlin.Unit r7 = kotlin.Unit.INSTANCE
                        return r7
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.paging.PagingDataTransforms$flatMap$$inlined$transform$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            @Nullable
            public Object collect(@NotNull FlowCollector flowCollector, @NotNull Continuation continuation) {
                Object collect = Flow.this.collect(new AnonymousClass2(flowCollector, transform), continuation);
                return collect == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? collect : Unit.INSTANCE;
            }
        }, pagingData.getReceiver$paging_common());
    }

    @CheckResult
    @JvmOverloads
    @NotNull
    public static final <T> PagingData<T> insertFooterItem(@NotNull PagingData<T> pagingData, @NotNull TerminalSeparatorType terminalSeparatorType, @NotNull T item) {
        Intrinsics.checkNotNullParameter(pagingData, "<this>");
        Intrinsics.checkNotNullParameter(terminalSeparatorType, "terminalSeparatorType");
        Intrinsics.checkNotNullParameter(item, "item");
        return insertSeparators(pagingData, terminalSeparatorType, new c(item, null));
    }

    @CheckResult
    @JvmOverloads
    @NotNull
    public static final <T> PagingData<T> insertFooterItem(@NotNull PagingData<T> pagingData, @NotNull T item) {
        Intrinsics.checkNotNullParameter(pagingData, "<this>");
        Intrinsics.checkNotNullParameter(item, "item");
        return insertFooterItem$default(pagingData, null, item, 1, null);
    }

    public static /* synthetic */ PagingData insertFooterItem$default(PagingData pagingData, TerminalSeparatorType terminalSeparatorType, Object obj, int i, Object obj2) {
        if ((i & 1) != 0) {
            terminalSeparatorType = TerminalSeparatorType.FULLY_COMPLETE;
        }
        return insertFooterItem(pagingData, terminalSeparatorType, obj);
    }

    @CheckResult
    @JvmOverloads
    @NotNull
    public static final <T> PagingData<T> insertHeaderItem(@NotNull PagingData<T> pagingData, @NotNull TerminalSeparatorType terminalSeparatorType, @NotNull T item) {
        Intrinsics.checkNotNullParameter(pagingData, "<this>");
        Intrinsics.checkNotNullParameter(terminalSeparatorType, "terminalSeparatorType");
        Intrinsics.checkNotNullParameter(item, "item");
        return insertSeparators(pagingData, terminalSeparatorType, new d(item, null));
    }

    @CheckResult
    @JvmOverloads
    @NotNull
    public static final <T> PagingData<T> insertHeaderItem(@NotNull PagingData<T> pagingData, @NotNull T item) {
        Intrinsics.checkNotNullParameter(pagingData, "<this>");
        Intrinsics.checkNotNullParameter(item, "item");
        return insertHeaderItem$default(pagingData, null, item, 1, null);
    }

    public static /* synthetic */ PagingData insertHeaderItem$default(PagingData pagingData, TerminalSeparatorType terminalSeparatorType, Object obj, int i, Object obj2) {
        if ((i & 1) != 0) {
            terminalSeparatorType = TerminalSeparatorType.FULLY_COMPLETE;
        }
        return insertHeaderItem(pagingData, terminalSeparatorType, obj);
    }

    @CheckResult
    public static final /* synthetic */ PagingData insertSeparators(PagingData pagingData, TerminalSeparatorType terminalSeparatorType, Function3 generator) {
        Intrinsics.checkNotNullParameter(pagingData, "<this>");
        Intrinsics.checkNotNullParameter(terminalSeparatorType, "terminalSeparatorType");
        Intrinsics.checkNotNullParameter(generator, "generator");
        return new PagingData(SeparatorsKt.insertEventSeparators(pagingData.getFlow$paging_common(), terminalSeparatorType, generator), pagingData.getReceiver$paging_common());
    }

    @CheckResult
    @JvmOverloads
    @NotNull
    public static final <R, T extends R> PagingData<R> insertSeparators(@NotNull PagingData<T> pagingData, @NotNull Executor executor, @NotNull Function2<? super T, ? super T, ? extends R> generator) {
        Intrinsics.checkNotNullParameter(pagingData, "<this>");
        Intrinsics.checkNotNullParameter(executor, "executor");
        Intrinsics.checkNotNullParameter(generator, "generator");
        return insertSeparators$default(pagingData, null, executor, generator, 1, null);
    }

    public static /* synthetic */ PagingData insertSeparators$default(PagingData pagingData, TerminalSeparatorType terminalSeparatorType, Function3 function3, int i, Object obj) {
        if ((i & 1) != 0) {
            terminalSeparatorType = TerminalSeparatorType.FULLY_COMPLETE;
        }
        return insertSeparators(pagingData, terminalSeparatorType, function3);
    }

    @CheckResult
    public static final /* synthetic */ PagingData map(PagingData pagingData, final Function2 transform) {
        Intrinsics.checkNotNullParameter(pagingData, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        final Flow flow$paging_common = pagingData.getFlow$paging_common();
        return new PagingData(new Flow<PageEvent<R>>() { // from class: androidx.paging.PagingDataTransforms$map$$inlined$transform$1

            /* JADX INFO: Add missing generic type declarations: [T] */
            @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001b\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006¸\u0006\u0007"}, d2 = {"kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3", "Lkotlinx/coroutines/flow/FlowCollector;", "value", "", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core", "androidx/paging/PagingDataTransforms$transform$$inlined$map$1$2"}, k = 1, mv = {1, 5, 1})
            /* renamed from: androidx.paging.PagingDataTransforms$map$$inlined$transform$1$2  reason: invalid class name */
            /* loaded from: classes.dex */
            public static final class AnonymousClass2<T> implements FlowCollector<PageEvent<T>> {
                public final /* synthetic */ FlowCollector h;
                public final /* synthetic */ Function2 i;

                @Metadata(bv = {1, 0, 3}, d1 = {}, d2 = {}, k = 3, mv = {1, 5, 1})
                @DebugMetadata(c = "androidx.paging.PagingDataTransforms$map$$inlined$transform$1$2", f = "PagingDataTransforms.kt", i = {}, l = {138, 138}, m = "emit", n = {}, s = {})
                /* renamed from: androidx.paging.PagingDataTransforms$map$$inlined$transform$1$2$1  reason: invalid class name */
                /* loaded from: classes.dex */
                public static final class AnonymousClass1 extends ContinuationImpl {
                    public Object L$0;
                    public int label;
                    public /* synthetic */ Object result;

                    public AnonymousClass1(Continuation continuation) {
                        super(continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @Nullable
                    public final Object invokeSuspend(@NotNull Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(FlowCollector flowCollector, Function2 function2) {
                    this.h = flowCollector;
                    this.i = function2;
                }

                /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
                /* JADX WARN: Removed duplicated region for block: B:16:0x003c  */
                /* JADX WARN: Removed duplicated region for block: B:22:0x005e A[RETURN] */
                @Override // kotlinx.coroutines.flow.FlowCollector
                @org.jetbrains.annotations.Nullable
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public java.lang.Object emit(java.lang.Object r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation r8) {
                    /*
                        r6 = this;
                        boolean r0 = r8 instanceof androidx.paging.PagingDataTransforms$map$$inlined$transform$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r8
                        androidx.paging.PagingDataTransforms$map$$inlined$transform$1$2$1 r0 = (androidx.paging.PagingDataTransforms$map$$inlined$transform$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        androidx.paging.PagingDataTransforms$map$$inlined$transform$1$2$1 r0 = new androidx.paging.PagingDataTransforms$map$$inlined$transform$1$2$1
                        r0.<init>(r8)
                    L18:
                        java.lang.Object r8 = r0.result
                        java.lang.Object r1 = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
                        int r2 = r0.label
                        r3 = 2
                        r4 = 1
                        if (r2 == 0) goto L3c
                        if (r2 == r4) goto L34
                        if (r2 != r3) goto L2c
                        kotlin.ResultKt.throwOnFailure(r8)
                        goto L5f
                    L2c:
                        java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                        java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
                        r7.<init>(r8)
                        throw r7
                    L34:
                        java.lang.Object r7 = r0.L$0
                        kotlinx.coroutines.flow.FlowCollector r7 = (kotlinx.coroutines.flow.FlowCollector) r7
                        kotlin.ResultKt.throwOnFailure(r8)
                        goto L53
                    L3c:
                        kotlin.ResultKt.throwOnFailure(r8)
                        kotlinx.coroutines.flow.FlowCollector r8 = r6.h
                        androidx.paging.PageEvent r7 = (androidx.paging.PageEvent) r7
                        kotlin.jvm.functions.Function2 r2 = r6.i
                        r0.L$0 = r8
                        r0.label = r4
                        java.lang.Object r7 = r7.map(r2, r0)
                        if (r7 != r1) goto L50
                        return r1
                    L50:
                        r5 = r8
                        r8 = r7
                        r7 = r5
                    L53:
                        r2 = 0
                        r0.L$0 = r2
                        r0.label = r3
                        java.lang.Object r7 = r7.emit(r8, r0)
                        if (r7 != r1) goto L5f
                        return r1
                    L5f:
                        kotlin.Unit r7 = kotlin.Unit.INSTANCE
                        return r7
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.paging.PagingDataTransforms$map$$inlined$transform$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            @Nullable
            public Object collect(@NotNull FlowCollector flowCollector, @NotNull Continuation continuation) {
                Object collect = Flow.this.collect(new AnonymousClass2(flowCollector, transform), continuation);
                return collect == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? collect : Unit.INSTANCE;
            }
        }, pagingData.getReceiver$paging_common());
    }

    public static /* synthetic */ PagingData insertSeparators$default(PagingData pagingData, TerminalSeparatorType terminalSeparatorType, Executor executor, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            terminalSeparatorType = TerminalSeparatorType.FULLY_COMPLETE;
        }
        return insertSeparators(pagingData, terminalSeparatorType, executor, function2);
    }

    @CheckResult
    @JvmOverloads
    @NotNull
    public static final <R, T extends R> PagingData<R> insertSeparators(@NotNull PagingData<T> pagingData, @NotNull TerminalSeparatorType terminalSeparatorType, @NotNull Executor executor, @NotNull Function2<? super T, ? super T, ? extends R> generator) {
        Intrinsics.checkNotNullParameter(pagingData, "<this>");
        Intrinsics.checkNotNullParameter(terminalSeparatorType, "terminalSeparatorType");
        Intrinsics.checkNotNullParameter(executor, "executor");
        Intrinsics.checkNotNullParameter(generator, "generator");
        return insertSeparators(pagingData, terminalSeparatorType, new e(executor, generator, null));
    }

    @CheckResult
    @JvmName(name = "filter")
    @NotNull
    public static final <T> PagingData<T> filter(@NotNull PagingData<T> pagingData, @NotNull final Executor executor, @NotNull final Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(pagingData, "<this>");
        Intrinsics.checkNotNullParameter(executor, "executor");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        final Flow<PageEvent<T>> flow$paging_common = pagingData.getFlow$paging_common();
        return new PagingData<>(new Flow<PageEvent<T>>() { // from class: androidx.paging.PagingDataTransforms$filter$$inlined$transform$2

            @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001b\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006¸\u0006\u0007"}, d2 = {"kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3", "Lkotlinx/coroutines/flow/FlowCollector;", "value", "", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core", "androidx/paging/PagingDataTransforms$transform$$inlined$map$1$2"}, k = 1, mv = {1, 5, 1})
            /* renamed from: androidx.paging.PagingDataTransforms$filter$$inlined$transform$2$2  reason: invalid class name */
            /* loaded from: classes.dex */
            public static final class AnonymousClass2 implements FlowCollector<PageEvent<T>> {
                public final /* synthetic */ FlowCollector h;
                public final /* synthetic */ Executor i;
                public final /* synthetic */ Function1 j;

                @Metadata(bv = {1, 0, 3}, d1 = {}, d2 = {}, k = 3, mv = {1, 5, 1})
                @DebugMetadata(c = "androidx.paging.PagingDataTransforms$filter$$inlined$transform$2$2", f = "PagingDataTransforms.kt", i = {}, l = {138, 138}, m = "emit", n = {}, s = {})
                /* renamed from: androidx.paging.PagingDataTransforms$filter$$inlined$transform$2$2$1  reason: invalid class name */
                /* loaded from: classes.dex */
                public static final class AnonymousClass1 extends ContinuationImpl {
                    public Object L$0;
                    public int label;
                    public /* synthetic */ Object result;

                    public AnonymousClass1(Continuation continuation) {
                        super(continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @Nullable
                    public final Object invokeSuspend(@NotNull Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(FlowCollector flowCollector, Executor executor, Function1 function1) {
                    this.h = flowCollector;
                    this.i = executor;
                    this.j = function1;
                }

                /* JADX WARN: Removed duplicated region for block: B:10:0x0025  */
                /* JADX WARN: Removed duplicated region for block: B:16:0x003d  */
                /* JADX WARN: Removed duplicated region for block: B:22:0x0069 A[RETURN] */
                @Override // kotlinx.coroutines.flow.FlowCollector
                @org.jetbrains.annotations.Nullable
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public java.lang.Object emit(java.lang.Object r10, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation r11) {
                    /*
                        r9 = this;
                        boolean r0 = r11 instanceof androidx.paging.PagingDataTransforms$filter$$inlined$transform$2.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r11
                        androidx.paging.PagingDataTransforms$filter$$inlined$transform$2$2$1 r0 = (androidx.paging.PagingDataTransforms$filter$$inlined$transform$2.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        androidx.paging.PagingDataTransforms$filter$$inlined$transform$2$2$1 r0 = new androidx.paging.PagingDataTransforms$filter$$inlined$transform$2$2$1
                        r0.<init>(r11)
                    L18:
                        java.lang.Object r11 = r0.result
                        java.lang.Object r1 = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
                        int r2 = r0.label
                        r3 = 0
                        r4 = 2
                        r5 = 1
                        if (r2 == 0) goto L3d
                        if (r2 == r5) goto L35
                        if (r2 != r4) goto L2d
                        kotlin.ResultKt.throwOnFailure(r11)
                        goto L6a
                    L2d:
                        java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
                        java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
                        r10.<init>(r11)
                        throw r10
                    L35:
                        java.lang.Object r10 = r0.L$0
                        kotlinx.coroutines.flow.FlowCollector r10 = (kotlinx.coroutines.flow.FlowCollector) r10
                        kotlin.ResultKt.throwOnFailure(r11)
                        goto L5f
                    L3d:
                        kotlin.ResultKt.throwOnFailure(r11)
                        kotlinx.coroutines.flow.FlowCollector r11 = r9.h
                        androidx.paging.PageEvent r10 = (androidx.paging.PageEvent) r10
                        java.util.concurrent.Executor r2 = r9.i
                        kotlinx.coroutines.CoroutineDispatcher r2 = kotlinx.coroutines.ExecutorsKt.from(r2)
                        androidx.paging.PagingDataTransforms$a r6 = new androidx.paging.PagingDataTransforms$a
                        kotlin.jvm.functions.Function1 r7 = r9.j
                        r6.<init>(r10, r7, r3)
                        r0.L$0 = r11
                        r0.label = r5
                        java.lang.Object r10 = kotlinx.coroutines.BuildersKt.withContext(r2, r6, r0)
                        if (r10 != r1) goto L5c
                        return r1
                    L5c:
                        r8 = r11
                        r11 = r10
                        r10 = r8
                    L5f:
                        r0.L$0 = r3
                        r0.label = r4
                        java.lang.Object r10 = r10.emit(r11, r0)
                        if (r10 != r1) goto L6a
                        return r1
                    L6a:
                        kotlin.Unit r10 = kotlin.Unit.INSTANCE
                        return r10
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.paging.PagingDataTransforms$filter$$inlined$transform$2.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            @Nullable
            public Object collect(@NotNull FlowCollector flowCollector, @NotNull Continuation continuation) {
                Object collect = Flow.this.collect(new AnonymousClass2(flowCollector, executor, predicate), continuation);
                return collect == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? collect : Unit.INSTANCE;
            }
        }, pagingData.getReceiver$paging_common());
    }

    @CheckResult
    @NotNull
    public static final <T, R> PagingData<R> flatMap(@NotNull PagingData<T> pagingData, @NotNull final Executor executor, @NotNull final Function1<? super T, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(pagingData, "<this>");
        Intrinsics.checkNotNullParameter(executor, "executor");
        Intrinsics.checkNotNullParameter(transform, "transform");
        final Flow<PageEvent<T>> flow$paging_common = pagingData.getFlow$paging_common();
        return new PagingData<>(new Flow<PageEvent<R>>() { // from class: androidx.paging.PagingDataTransforms$flatMap$$inlined$transform$2

            /* JADX INFO: Add missing generic type declarations: [T] */
            @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001b\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006¸\u0006\u0007"}, d2 = {"kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3", "Lkotlinx/coroutines/flow/FlowCollector;", "value", "", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core", "androidx/paging/PagingDataTransforms$transform$$inlined$map$1$2"}, k = 1, mv = {1, 5, 1})
            /* renamed from: androidx.paging.PagingDataTransforms$flatMap$$inlined$transform$2$2  reason: invalid class name */
            /* loaded from: classes.dex */
            public static final class AnonymousClass2<T> implements FlowCollector<PageEvent<T>> {
                public final /* synthetic */ FlowCollector h;
                public final /* synthetic */ Executor i;
                public final /* synthetic */ Function1 j;

                @Metadata(bv = {1, 0, 3}, d1 = {}, d2 = {}, k = 3, mv = {1, 5, 1})
                @DebugMetadata(c = "androidx.paging.PagingDataTransforms$flatMap$$inlined$transform$2$2", f = "PagingDataTransforms.kt", i = {}, l = {138, 138}, m = "emit", n = {}, s = {})
                /* renamed from: androidx.paging.PagingDataTransforms$flatMap$$inlined$transform$2$2$1  reason: invalid class name */
                /* loaded from: classes.dex */
                public static final class AnonymousClass1 extends ContinuationImpl {
                    public Object L$0;
                    public int label;
                    public /* synthetic */ Object result;

                    public AnonymousClass1(Continuation continuation) {
                        super(continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @Nullable
                    public final Object invokeSuspend(@NotNull Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(FlowCollector flowCollector, Executor executor, Function1 function1) {
                    this.h = flowCollector;
                    this.i = executor;
                    this.j = function1;
                }

                /* JADX WARN: Removed duplicated region for block: B:10:0x0025  */
                /* JADX WARN: Removed duplicated region for block: B:16:0x003d  */
                /* JADX WARN: Removed duplicated region for block: B:22:0x0069 A[RETURN] */
                @Override // kotlinx.coroutines.flow.FlowCollector
                @org.jetbrains.annotations.Nullable
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public java.lang.Object emit(java.lang.Object r10, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation r11) {
                    /*
                        r9 = this;
                        boolean r0 = r11 instanceof androidx.paging.PagingDataTransforms$flatMap$$inlined$transform$2.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r11
                        androidx.paging.PagingDataTransforms$flatMap$$inlined$transform$2$2$1 r0 = (androidx.paging.PagingDataTransforms$flatMap$$inlined$transform$2.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        androidx.paging.PagingDataTransforms$flatMap$$inlined$transform$2$2$1 r0 = new androidx.paging.PagingDataTransforms$flatMap$$inlined$transform$2$2$1
                        r0.<init>(r11)
                    L18:
                        java.lang.Object r11 = r0.result
                        java.lang.Object r1 = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
                        int r2 = r0.label
                        r3 = 0
                        r4 = 2
                        r5 = 1
                        if (r2 == 0) goto L3d
                        if (r2 == r5) goto L35
                        if (r2 != r4) goto L2d
                        kotlin.ResultKt.throwOnFailure(r11)
                        goto L6a
                    L2d:
                        java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
                        java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
                        r10.<init>(r11)
                        throw r10
                    L35:
                        java.lang.Object r10 = r0.L$0
                        kotlinx.coroutines.flow.FlowCollector r10 = (kotlinx.coroutines.flow.FlowCollector) r10
                        kotlin.ResultKt.throwOnFailure(r11)
                        goto L5f
                    L3d:
                        kotlin.ResultKt.throwOnFailure(r11)
                        kotlinx.coroutines.flow.FlowCollector r11 = r9.h
                        androidx.paging.PageEvent r10 = (androidx.paging.PageEvent) r10
                        java.util.concurrent.Executor r2 = r9.i
                        kotlinx.coroutines.CoroutineDispatcher r2 = kotlinx.coroutines.ExecutorsKt.from(r2)
                        androidx.paging.PagingDataTransforms$b r6 = new androidx.paging.PagingDataTransforms$b
                        kotlin.jvm.functions.Function1 r7 = r9.j
                        r6.<init>(r10, r7, r3)
                        r0.L$0 = r11
                        r0.label = r5
                        java.lang.Object r10 = kotlinx.coroutines.BuildersKt.withContext(r2, r6, r0)
                        if (r10 != r1) goto L5c
                        return r1
                    L5c:
                        r8 = r11
                        r11 = r10
                        r10 = r8
                    L5f:
                        r0.L$0 = r3
                        r0.label = r4
                        java.lang.Object r10 = r10.emit(r11, r0)
                        if (r10 != r1) goto L6a
                        return r1
                    L6a:
                        kotlin.Unit r10 = kotlin.Unit.INSTANCE
                        return r10
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.paging.PagingDataTransforms$flatMap$$inlined$transform$2.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            @Nullable
            public Object collect(@NotNull FlowCollector flowCollector, @NotNull Continuation continuation) {
                Object collect = Flow.this.collect(new AnonymousClass2(flowCollector, executor, transform), continuation);
                return collect == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? collect : Unit.INSTANCE;
            }
        }, pagingData.getReceiver$paging_common());
    }

    @CheckResult
    @NotNull
    public static final <T, R> PagingData<R> map(@NotNull PagingData<T> pagingData, @NotNull final Executor executor, @NotNull final Function1<? super T, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(pagingData, "<this>");
        Intrinsics.checkNotNullParameter(executor, "executor");
        Intrinsics.checkNotNullParameter(transform, "transform");
        final Flow<PageEvent<T>> flow$paging_common = pagingData.getFlow$paging_common();
        return new PagingData<>(new Flow<PageEvent<R>>() { // from class: androidx.paging.PagingDataTransforms$map$$inlined$transform$2

            /* JADX INFO: Add missing generic type declarations: [T] */
            @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001b\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006¸\u0006\u0007"}, d2 = {"kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3", "Lkotlinx/coroutines/flow/FlowCollector;", "value", "", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core", "androidx/paging/PagingDataTransforms$transform$$inlined$map$1$2"}, k = 1, mv = {1, 5, 1})
            /* renamed from: androidx.paging.PagingDataTransforms$map$$inlined$transform$2$2  reason: invalid class name */
            /* loaded from: classes.dex */
            public static final class AnonymousClass2<T> implements FlowCollector<PageEvent<T>> {
                public final /* synthetic */ FlowCollector h;
                public final /* synthetic */ Executor i;
                public final /* synthetic */ Function1 j;

                @Metadata(bv = {1, 0, 3}, d1 = {}, d2 = {}, k = 3, mv = {1, 5, 1})
                @DebugMetadata(c = "androidx.paging.PagingDataTransforms$map$$inlined$transform$2$2", f = "PagingDataTransforms.kt", i = {}, l = {138, 138}, m = "emit", n = {}, s = {})
                /* renamed from: androidx.paging.PagingDataTransforms$map$$inlined$transform$2$2$1  reason: invalid class name */
                /* loaded from: classes.dex */
                public static final class AnonymousClass1 extends ContinuationImpl {
                    public Object L$0;
                    public int label;
                    public /* synthetic */ Object result;

                    public AnonymousClass1(Continuation continuation) {
                        super(continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @Nullable
                    public final Object invokeSuspend(@NotNull Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(FlowCollector flowCollector, Executor executor, Function1 function1) {
                    this.h = flowCollector;
                    this.i = executor;
                    this.j = function1;
                }

                /* JADX WARN: Removed duplicated region for block: B:10:0x0025  */
                /* JADX WARN: Removed duplicated region for block: B:16:0x003d  */
                /* JADX WARN: Removed duplicated region for block: B:22:0x0069 A[RETURN] */
                @Override // kotlinx.coroutines.flow.FlowCollector
                @org.jetbrains.annotations.Nullable
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public java.lang.Object emit(java.lang.Object r10, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation r11) {
                    /*
                        r9 = this;
                        boolean r0 = r11 instanceof androidx.paging.PagingDataTransforms$map$$inlined$transform$2.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r11
                        androidx.paging.PagingDataTransforms$map$$inlined$transform$2$2$1 r0 = (androidx.paging.PagingDataTransforms$map$$inlined$transform$2.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        androidx.paging.PagingDataTransforms$map$$inlined$transform$2$2$1 r0 = new androidx.paging.PagingDataTransforms$map$$inlined$transform$2$2$1
                        r0.<init>(r11)
                    L18:
                        java.lang.Object r11 = r0.result
                        java.lang.Object r1 = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
                        int r2 = r0.label
                        r3 = 0
                        r4 = 2
                        r5 = 1
                        if (r2 == 0) goto L3d
                        if (r2 == r5) goto L35
                        if (r2 != r4) goto L2d
                        kotlin.ResultKt.throwOnFailure(r11)
                        goto L6a
                    L2d:
                        java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
                        java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
                        r10.<init>(r11)
                        throw r10
                    L35:
                        java.lang.Object r10 = r0.L$0
                        kotlinx.coroutines.flow.FlowCollector r10 = (kotlinx.coroutines.flow.FlowCollector) r10
                        kotlin.ResultKt.throwOnFailure(r11)
                        goto L5f
                    L3d:
                        kotlin.ResultKt.throwOnFailure(r11)
                        kotlinx.coroutines.flow.FlowCollector r11 = r9.h
                        androidx.paging.PageEvent r10 = (androidx.paging.PageEvent) r10
                        java.util.concurrent.Executor r2 = r9.i
                        kotlinx.coroutines.CoroutineDispatcher r2 = kotlinx.coroutines.ExecutorsKt.from(r2)
                        androidx.paging.PagingDataTransforms$f r6 = new androidx.paging.PagingDataTransforms$f
                        kotlin.jvm.functions.Function1 r7 = r9.j
                        r6.<init>(r10, r7, r3)
                        r0.L$0 = r11
                        r0.label = r5
                        java.lang.Object r10 = kotlinx.coroutines.BuildersKt.withContext(r2, r6, r0)
                        if (r10 != r1) goto L5c
                        return r1
                    L5c:
                        r8 = r11
                        r11 = r10
                        r10 = r8
                    L5f:
                        r0.L$0 = r3
                        r0.label = r4
                        java.lang.Object r10 = r10.emit(r11, r0)
                        if (r10 != r1) goto L6a
                        return r1
                    L6a:
                        kotlin.Unit r10 = kotlin.Unit.INSTANCE
                        return r10
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.paging.PagingDataTransforms$map$$inlined$transform$2.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            @Nullable
            public Object collect(@NotNull FlowCollector flowCollector, @NotNull Continuation continuation) {
                Object collect = Flow.this.collect(new AnonymousClass2(flowCollector, executor, transform), continuation);
                return collect == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? collect : Unit.INSTANCE;
            }
        }, pagingData.getReceiver$paging_common());
    }
}
