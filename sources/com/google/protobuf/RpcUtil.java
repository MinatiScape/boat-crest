package com.google.protobuf;
/* loaded from: classes11.dex */
public final class RpcUtil {

    /* loaded from: classes11.dex */
    public static final class AlreadyCalledException extends RuntimeException {
        private static final long serialVersionUID = 5469741279507848266L;

        public AlreadyCalledException() {
            super("This RpcCallback was already called and cannot be called multiple times.");
        }
    }

    /* loaded from: classes11.dex */
    public static class a implements RpcCallback<Message> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Class f11705a;
        public final /* synthetic */ Message b;
        public final /* synthetic */ RpcCallback c;

        public a(Class cls, Message message, RpcCallback rpcCallback) {
            this.f11705a = cls;
            this.b = message;
            this.c = rpcCallback;
        }

        @Override // com.google.protobuf.RpcCallback
        /* renamed from: a */
        public void run(Message message) {
            Message copyAsType;
            try {
                copyAsType = (Message) this.f11705a.cast(message);
            } catch (ClassCastException unused) {
                copyAsType = RpcUtil.copyAsType(this.b, message);
            }
            this.c.run(copyAsType);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [ParameterType] */
    /* loaded from: classes11.dex */
    public static class b<ParameterType> implements RpcCallback<ParameterType> {

        /* renamed from: a  reason: collision with root package name */
        public boolean f11706a = false;
        public final /* synthetic */ RpcCallback b;

        public b(RpcCallback rpcCallback) {
            this.b = rpcCallback;
        }

        @Override // com.google.protobuf.RpcCallback
        public void run(ParameterType parametertype) {
            synchronized (this) {
                if (!this.f11706a) {
                    this.f11706a = true;
                } else {
                    throw new AlreadyCalledException();
                }
            }
            this.b.run(parametertype);
        }
    }

    private RpcUtil() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <Type extends Message> Type copyAsType(Type type, Message message) {
        return (Type) type.newBuilderForType().mergeFrom(message).build();
    }

    public static <Type extends Message> RpcCallback<Message> generalizeCallback(RpcCallback<Type> rpcCallback, Class<Type> cls, Type type) {
        return new a(cls, type, rpcCallback);
    }

    public static <ParameterType> RpcCallback<ParameterType> newOneTimeCallback(RpcCallback<ParameterType> rpcCallback) {
        return new b(rpcCallback);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <Type extends Message> RpcCallback<Type> specializeCallback(RpcCallback<Message> rpcCallback) {
        return rpcCallback;
    }
}
