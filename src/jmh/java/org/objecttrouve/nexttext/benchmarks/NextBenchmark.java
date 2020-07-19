package org.objecttrouve.nexttext.benchmarks;

import org.objecttrouve.nexttext.NextText;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

@BenchmarkMode(Mode.All)
@Warmup(iterations = 10)
@Measurement(iterations = 100, batchSize = 10)
@State(Scope.Benchmark)
public class NextBenchmark {

    @State(Scope.Benchmark)
    public static class NextState {
        NextText nextText = new NextText.Builder().build();

        @Setup(Level.Invocation)
        public void setUp() {
            nextText = new NextText.Builder().build();
        }
    }

    @Benchmark
    public void bench__l4(final Blackhole blackhole, final NextState nextState){
        blackhole.consume(nextState.nextText.criDistance("abba", "baba"));
    }

    @Benchmark
    public void bench__l4__x2(final Blackhole blackhole, final NextState nextState){
        blackhole.consume(nextState.nextText.criDistance("abbaabba", "babababa"));
    }

    @Benchmark
    public void bench__l4__x4(final Blackhole blackhole, final NextState nextState){
        blackhole.consume(nextState.nextText.criDistance("abbaabbaabbaabba", "babababababababa"));
    }

    @Benchmark
    public void bench__l4__x8(final Blackhole blackhole, final NextState nextState){
        blackhole.consume(nextState.nextText.criDistance("abbaabbaabbaabbaabbaabbaabbaabba", "babababababababababababababababa"));
    }

    @Benchmark
    public void bench__l4__x16(final Blackhole blackhole, final NextState nextState){
        blackhole.consume(nextState.nextText.criDistance(
                "abbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabba",
                "babababababababababababababababababababababababababababababababa"
        ));
    }

    @Benchmark
    public void bench__l4__x32(final Blackhole blackhole, final NextState nextState){
        blackhole.consume(nextState.nextText.criDistance(
                "abbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabba",
                "babababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababa"
        ));
    }

    @Benchmark
    public void bench__l4__x64(final Blackhole blackhole, final NextState nextState){
        blackhole.consume(nextState.nextText.criDistance(
                "abbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabba",
                "babababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababa"
        ));
    }


}
