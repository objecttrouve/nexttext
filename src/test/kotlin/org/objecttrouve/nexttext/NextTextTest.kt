/**
 * Released under the terms of the Apache License 2.0.
 * Copyright (c) 2020 objecttrouve.org <un.object.trouve@gmail.com>.
 */
package org.objecttrouve.nexttext


import org.hamcrest.Matchers.*
import org.hamcrest.number.IsCloseTo.closeTo
import org.junit.Assert.assertThat
import kotlin.test.Test

class NextTextTest {

    @Test fun criDistance__empty__input() {
        val nextText = NextText.Builder().build()
        val text1 = ""
        val text2 = ""

        val criDistance = nextText.criDistance(text1, text2)

        assertThat(criDistance, `is`(0.0))
    }

    @Test fun criDistance__empty__A() {
        val nextText = NextText.Builder().build()
        val text1 = ""
        val text2 = "A"

        val criDistance = nextText.criDistance(text1, text2)

        assertThat(criDistance, `is`(1.0))
    }

    @Test fun criDistance__A__empty() {
        val nextText = NextText.Builder().build()
        val text1 = "A"
        val text2 = ""

        val criDistance = nextText.criDistance(text1, text2)

        assertThat(criDistance, `is`(1.0))
    }

    @Test fun criDistance__A__B() {
        val nextText = NextText.Builder().build()
        val text1 = "A"
        val text2 = "B"

        val criDistance = nextText.criDistance(text1, text2)

        assertThat(criDistance, `is`(1.0))
    }

    @Test fun criDistance__ABA__BAA() {
        val nextText = NextText.Builder().build()
        val text1 = "ABA"
        val text2 = "BAA"

        val criDistance = nextText.criDistance(text1, text2)

        assertThat(criDistance, `is`(1.0))
    }

    @Test fun criDistance__BABA__BAAB() {
        val nextText = NextText.Builder().build()
        val text1 = "BABA"
        val text2 = "BAAB"

        val criDistance = nextText.criDistance(text1, text2)

        assertThat(criDistance, `is`(0.5))
    }

    @Test fun criDistance__BAAB__BAAB() {
        val nextText = NextText.Builder().build()
        val text1 = "BAAB"
        val text2 = "BAAB"

        val criDistance = nextText.criDistance(text1, text2)

        assertThat(criDistance, `is`(0.0))
    }

    @Test fun criDistance__emojis_1() {
        val minCodePoint = "\u1f600".codePoints().min().orElseGet {-1}
        val maxCodePoint = "\ue007f".codePoints().max().orElseGet {-1}
        assertThat(minCodePoint, greaterThanOrEqualTo(0))
        assertThat(maxCodePoint, greaterThanOrEqualTo(0))
        assertThat(maxCodePoint, greaterThan(minCodePoint))

        val nextText = NextText.Builder()
                .withMinCodePoint(minCodePoint)
                .withMaxCodePoint(maxCodePoint)
                .build()
        val text1 = "\u1f601\u1f970\u1f601"
        val text2 = "\u1f601\u2622\u1f51e\u1f601"

        val criDistance = nextText.criDistance(text1, text2)

        assertThat(criDistance, closeTo(0.692, 0.001))
    }

    @Test fun criDistance__emojis_2() {
        val minCodePoint = "\u1f600".codePoints().min().orElseGet {-1}
        val maxCodePoint = "\ue007f".codePoints().max().orElseGet {-1}
        assertThat(minCodePoint, greaterThanOrEqualTo(0))
        assertThat(maxCodePoint, greaterThanOrEqualTo(0))
        assertThat(maxCodePoint, greaterThan(minCodePoint))

        val nextText = NextText.Builder()
                .withMinCodePoint(minCodePoint)
                .withMaxCodePoint(maxCodePoint)
                .build()
        val text1 = "\u1f601\u1f970\u1f601"
        val text2 = "\u1f601\u1f970\u1f601"

        val criDistance = nextText.criDistance(text1, text2)

        assertThat(criDistance, `is`(0.0))
    }

    @Test fun criDistance__emojis_3() {
        val minCodePoint = "\u1f600".codePoints().min().orElseGet {-1}
        val maxCodePoint = "\ue007f".codePoints().max().orElseGet {-1}
        assertThat(minCodePoint, greaterThanOrEqualTo(0))
        assertThat(maxCodePoint, greaterThanOrEqualTo(0))
        assertThat(maxCodePoint, greaterThan(minCodePoint))

        val nextText = NextText.Builder()
                .withMinCodePoint(minCodePoint)
                .withMaxCodePoint(maxCodePoint)
                .build()
        val text1 = "\u1f601\u1f970\u1f601\u1f970"
        val text2 = "\u1f601\u1f970\u1f970\u1f601"

        val criDistance = nextText.criDistance(text1, text2)

        assertThat(criDistance, `is`(0.5))
    }

    @Test(expected = IllegalArgumentException::class) fun Builder__withMinCodePoint__negative_input() {
        NextText.Builder().withMinCodePoint(-1)
    }

    @Test(expected = IllegalArgumentException::class) fun Builder__withMaxCodePoint__negative_input() {
        NextText.Builder().withMaxCodePoint(-1)
    }

    @Test fun criDistance__conjugated() {
        val nextText = NextText.Builder().build()
        val word1 = "scribere"

        assertThat(nextText.criDistance(word1, "scribo"), closeTo(0.286, 0.001))
        assertThat(nextText.criDistance(word1, "scribis"), closeTo(0.333, 0.001))
        assertThat(nextText.criDistance(word1, "scribit"), closeTo(0.333, 0.001))
        assertThat(nextText.criDistance(word1, "scribimus"), closeTo(0.412, 0.001))
        assertThat(nextText.criDistance(word1, "scribitis"), closeTo(0.412, 0.001))
        assertThat(nextText.criDistance(word1, "scribunt"), closeTo(0.375, 0.001))

        val word2 = "scribo"

        assertThat(nextText.criDistance(word2, "scribis"), closeTo(0.231, 0.001))
        assertThat(nextText.criDistance(word2, "scribit"), closeTo(0.231, 0.001))
        assertThat(nextText.criDistance(word2, "scribimus"), closeTo(0.333, 0.001))
        assertThat(nextText.criDistance(word2, "scribitis"), closeTo(0.333, 0.001))
        assertThat(nextText.criDistance(word2, "scribunt"), closeTo(0.286, 0.001))

        val word3 = "scribis"

        assertThat(nextText.criDistance(word3, "scribit"), closeTo(0.143, 0.001))
        assertThat(nextText.criDistance(word3, "scribimus"), closeTo(0.25, 0.001))
        assertThat(nextText.criDistance(word3, "scribitis"), closeTo(0.25, 0.001))
        assertThat(nextText.criDistance(word3, "scribunt"), closeTo(0.333, 0.001))

        val word4 = "scribit"

        assertThat(nextText.criDistance(word4, "scribimus"), closeTo(0.25, 0.001))
        assertThat(nextText.criDistance(word4, "scribitis"), closeTo(0.125, 0.001))
        assertThat(nextText.criDistance(word4, "scribunt"), closeTo(0.333, 0.001))

        val word5 = "scribimus"

        assertThat(nextText.criDistance(word5, "scribitis"), closeTo(0.222, 0.001))
        assertThat(nextText.criDistance(word5, "scribunt"), closeTo(0.412, 0.001))

        val word6 = "scribitis"

        assertThat(nextText.criDistance(word6, "scribunt"), closeTo(0.412, 0.001))


        assertThat(nextText.criDistance("lego", "scribo"), closeTo(1.0, 0.001))
        assertThat(nextText.criDistance("legis", "scribis"), closeTo(0.833, 0.001))
        assertThat(nextText.criDistance("legit", "scribit"), closeTo(0.833, 0.001))
        assertThat(nextText.criDistance("legimus", "scribimus"), closeTo(0.875, 0.001))
        assertThat(nextText.criDistance("legitis", "scribitis"), closeTo(0.75, 0.001))
        assertThat(nextText.criDistance("legunt", "scribunt"), closeTo(1.0, 0.001))


        // 😢
        assertThat(nextText.criDistance("avus", "clavus"), closeTo(1.0, 0.001))


        assertThat(nextText.criDistance("lex", "lux"), closeTo(0.333, 0.001))

    }

}