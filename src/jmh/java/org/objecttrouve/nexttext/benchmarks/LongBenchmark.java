/**
 * Released under the terms of the Apache License 2.0.
 * Copyright (c) 2020 objecttrouve.org <un.object.trouve@gmail.com>.
 */
package org.objecttrouve.nexttext.benchmarks;

import com.google.common.base.Strings;
import org.apache.commons.io.IOUtils;
import org.objecttrouve.nexttext.NextText;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


@SuppressWarnings("unused")
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 10)
@Measurement(iterations = 100, batchSize = 10)
@State(Scope.Benchmark)
public class LongBenchmark {

    @State(Scope.Benchmark)
    public static class NextState {
        NextText nextText = new NextText.Builder().build();

        String source1;
        String source2;

        String source1_x2;
        String source2_x2;

        String source1_x4;
        String source2_x4;

        String source1_x16;
        String source2_x16;

        String source1_x256;
        String source2_x256;

        @Setup(Level.Invocation)
        public void setUp() throws IOException {
            nextText = new NextText.Builder().build();

            source1 = read("/home/heidi/mine/gitte/objecttrouve/nexttext/src/jmh/resources/LaBible-Genese.html");
            source2 = read("/home/heidi/mine/gitte/objecttrouve/nexttext/src/jmh/resources/LaBible-Exode.html");

            source1_x2 = Strings.repeat(source1, 2);
            source2_x2 = Strings.repeat(source2, 2);

            source1_x4 = Strings.repeat(source1_x2, 2);
            source2_x4 = Strings.repeat(source2_x2, 2);

            source1_x16 = Strings.repeat(source1_x4, 4);
            source2_x16 = Strings.repeat(source2_x4, 4);

            source1_x256 = Strings.repeat(source1_x4, 16);
            source2_x256 = Strings.repeat(source2_x4, 16);
        }

        private String read(String fileName) throws IOException {
            FileInputStream fis = new FileInputStream(fileName);
            String longString = IOUtils.toString(fis, StandardCharsets.UTF_8);
            fis.close();
            return longString;
        }

    }

    @Benchmark
    public void bench__base(final Blackhole blackhole, final NextState nextState){
        blackhole.consume(nextState.nextText.criDistance(nextState.source1, nextState.source2));
    }

    @Benchmark
    public void bench__base_x2(final Blackhole blackhole, final NextState nextState){
        blackhole.consume(nextState.nextText.criDistance(nextState.source1_x2, nextState.source2_x2));
    }

    @Benchmark
    public void bench__base_x4(final Blackhole blackhole, final NextState nextState){
        blackhole.consume(nextState.nextText.criDistance(nextState.source1_x4, nextState.source2_x4));
    }

    @Benchmark
    public void bench__base_x16(final Blackhole blackhole, final NextState nextState){
        blackhole.consume(nextState.nextText.criDistance(nextState.source1_x16, nextState.source2_x16));
    }

    @Benchmark
    public void bench__base_x256(final Blackhole blackhole, final NextState nextState){
        blackhole.consume(nextState.nextText.criDistance(nextState.source1_x256, nextState.source2_x256));
    }




}
