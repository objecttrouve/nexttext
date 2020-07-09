package org.objecttrouve.nexttext


import org.hamcrest.Matchers.`is`
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
}
