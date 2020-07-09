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
}
