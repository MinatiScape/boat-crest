package rx.internal.producers;

import rx.Producer;
/* loaded from: classes13.dex */
public final class ProducerArbiter implements Producer {
    public static final Producer n = new a();
    public long h;
    public Producer i;
    public boolean j;
    public long k;
    public long l;
    public Producer m;

    /* loaded from: classes13.dex */
    public static class a implements Producer {
        @Override // rx.Producer
        public void request(long j) {
        }
    }

    public void emitLoop() {
        while (true) {
            synchronized (this) {
                long j = this.k;
                long j2 = this.l;
                Producer producer = this.m;
                int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
                if (i == 0 && j2 == 0 && producer == null) {
                    this.j = false;
                    return;
                }
                this.k = 0L;
                this.l = 0L;
                this.m = null;
                long j3 = this.h;
                if (j3 != Long.MAX_VALUE) {
                    long j4 = j3 + j;
                    if (j4 < 0 || j4 == Long.MAX_VALUE) {
                        this.h = Long.MAX_VALUE;
                        j3 = Long.MAX_VALUE;
                    } else {
                        j3 = j4 - j2;
                        if (j3 >= 0) {
                            this.h = j3;
                        } else {
                            throw new IllegalStateException("more produced than requested");
                        }
                    }
                }
                if (producer != null) {
                    if (producer == n) {
                        this.i = null;
                    } else {
                        this.i = producer;
                        producer.request(j3);
                    }
                } else {
                    Producer producer2 = this.i;
                    if (producer2 != null && i != 0) {
                        producer2.request(j);
                    }
                }
            }
        }
    }

    public void produced(long j) {
        if (j > 0) {
            synchronized (this) {
                if (this.j) {
                    this.l += j;
                    return;
                }
                this.j = true;
                try {
                    long j2 = this.h;
                    if (j2 != Long.MAX_VALUE) {
                        long j3 = j2 - j;
                        if (j3 >= 0) {
                            this.h = j3;
                        } else {
                            throw new IllegalStateException("more items arrived than were requested");
                        }
                    }
                    emitLoop();
                    return;
                } catch (Throwable th) {
                    synchronized (this) {
                        this.j = false;
                        throw th;
                    }
                }
            }
        }
        throw new IllegalArgumentException("n > 0 required");
    }

    @Override // rx.Producer
    public void request(long j) {
        int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
        if (i < 0) {
            throw new IllegalArgumentException("n >= 0 required");
        }
        if (i == 0) {
            return;
        }
        synchronized (this) {
            if (this.j) {
                this.k += j;
                return;
            }
            this.j = true;
            try {
                long j2 = this.h + j;
                if (j2 < 0) {
                    j2 = Long.MAX_VALUE;
                }
                this.h = j2;
                Producer producer = this.i;
                if (producer != null) {
                    producer.request(j);
                }
                emitLoop();
            } catch (Throwable th) {
                synchronized (this) {
                    this.j = false;
                    throw th;
                }
            }
        }
    }

    public void setProducer(Producer producer) {
        synchronized (this) {
            if (this.j) {
                if (producer == null) {
                    producer = n;
                }
                this.m = producer;
                return;
            }
            this.j = true;
            try {
                this.i = producer;
                if (producer != null) {
                    producer.request(this.h);
                }
                emitLoop();
            } catch (Throwable th) {
                synchronized (this) {
                    this.j = false;
                    throw th;
                }
            }
        }
    }
}
