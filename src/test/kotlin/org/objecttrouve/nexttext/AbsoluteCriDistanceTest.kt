package org.objecttrouve.nexttext

import org.hamcrest.Matchers
import org.junit.Assert.assertThat
import kotlin.test.Test

class AbsoluteCriDistanceTest {


    @Test fun distance__0_127__2_empty_Strings() {
        val criStuff = CriCounter()
        val criCounts1 = criStuff.criCounts("")
        val criCounts2 = criStuff.criCounts("")
        val calc = AbsoluteCriDistance()

        val distance = calc.distance(criCounts1, criCounts2)

        assertThat(distance, Matchers.`is`(0))
    }


    @Test fun distance__65_65__empty_String__A() {
        val criStuff = CriCounter(65, 65)
        val criCounts1 = criStuff.criCounts("")
        val criCounts2 = criStuff.criCounts("A")
        val calc = AbsoluteCriDistance()

        val distance = calc.distance(criCounts1, criCounts2)

        assertThat(distance, Matchers.`is`(1))
    }

    @Test fun distance__0_127__empty_String__A() {
        val criStuff = CriCounter(0, 127)
        val criCounts1 = criStuff.criCounts("")
        val criCounts2 = criStuff.criCounts("A")
        val calc = AbsoluteCriDistance()

        val distance = calc.distance(criCounts1, criCounts2)

        assertThat(distance, Matchers.`is`(1))
    }

    @Test fun distance__0_127__empty_String__AB() {
        val criStuff = CriCounter(0, 127)
        val criCounts1 = criStuff.criCounts("")
        val criCounts2 = criStuff.criCounts("AB")
        val calc = AbsoluteCriDistance()

        val distance = calc.distance(criCounts1, criCounts2)

        assertThat(distance, Matchers.`is`(2))
    }

    @Test fun distance__0_127__AB__AB() {
        val criStuff = CriCounter(0, 127)
        val criCounts1 = criStuff.criCounts("AB")
        val criCounts2 = criStuff.criCounts("AB")
        val calc = AbsoluteCriDistance()

        val distance = calc.distance(criCounts1, criCounts2)

        assertThat(distance, Matchers.`is`(0))
    }

    @Test fun distance__0_127__A__B() {
        val criStuff = CriCounter(0, 127)
        val criCounts1 = criStuff.criCounts("A")
        val criCounts2 = criStuff.criCounts("B")
        val calc = AbsoluteCriDistance()

        val distance = calc.distance(criCounts1, criCounts2)

        assertThat(distance, Matchers.`is`(2))
    }


    @Test fun distance__0_127__AB__BA() {
        val criStuff = CriCounter(0, 127)
        val criCounts1 = criStuff.criCounts("AB")
        val criCounts2 = criStuff.criCounts("BA")
        val calc = AbsoluteCriDistance()

        val distance = calc.distance(criCounts1, criCounts2)

        assertThat(distance, Matchers.`is`(4))
    }

    @Test fun distance__0_127__ABBA__BABA() {
        val criStuff = CriCounter(0, 127)
        val criCounts1 = criStuff.criCounts("ABBA")
        val criCounts2 = criStuff.criCounts("BABA")
        val calc = AbsoluteCriDistance()

        val distance = calc.distance(criCounts1, criCounts2)

        assertThat(distance, Matchers.`is`(8))
    }

    @Test fun distance__0_127__ABBA_BABA__BABA_ABBA() {
        val criStuff = CriCounter(0, 127)
        val criCounts1 = criStuff.criCounts("ABBA BABA")
        val criCounts2 = criStuff.criCounts("BABA ABBA")
        val calc = AbsoluteCriDistance()

        val distance = calc.distance(criCounts1, criCounts2)

        assertThat(distance, Matchers.`is`(8))
    }

    @Test fun distance__0_127__BAB__AABAB() {
        val criStuff = CriCounter(0, 127)
        val criCounts1 = criStuff.criCounts("BAB")
        val criCounts2 = criStuff.criCounts("AABAB")
        val calc = AbsoluteCriDistance()

        val distance = calc.distance(criCounts1, criCounts2)

        assertThat(distance, Matchers.`is`(4))
    }

    @Test fun distance__0_127__BAB__BABAA() {
        val criStuff = CriCounter(0, 127)
        val criCounts1 = criStuff.criCounts("BAB")
        val criCounts2 = criStuff.criCounts("BABAA")
        val calc = AbsoluteCriDistance()

        val distance = calc.distance(criCounts1, criCounts2)

        assertThat(distance, Matchers.`is`(2))
    }


    private val text1 = "This is a text."
    private val text2 = "This is a longer text."

    @Test fun distance__0_127__text1__text2() {
        val criStuff = CriCounter(0, 127)
        val criCounts1 = criStuff.criCounts(text1)
        val criCounts2 = criStuff.criCounts(text2)
        val calc = AbsoluteCriDistance()

        val distance = calc.distance(criCounts1, criCounts2)

        assertThat(distance, Matchers.`is`(15))
    }

    private val text3 = "This is a much much longer text which isn't really the same."

    @Test fun distance__0_127__text1__text3() {
        val criStuff = CriCounter(0, 127)
        val criCounts1 = criStuff.criCounts(text1)
        val criCounts2 = criStuff.criCounts(text3)
        val calc = AbsoluteCriDistance()

        val distance = calc.distance(criCounts1, criCounts2)

        assertThat(distance, Matchers.`is`(53))
    }

    private val text4 = "Assume I'm writing an essay. " +
            "I'm good in academic writing, so I start the essay with a topic sentence." +
            "And I finish the introduction with the confirmation that this text is about writing essays.\n"

    @Test fun distance__0_127__text4__text4() {
        val criStuff = CriCounter(0, 127)
        val criCounts1 = criStuff.criCounts(text4)
        val criCounts2 = criStuff.criCounts(text4)
        val calc = AbsoluteCriDistance()

        val distance = calc.distance(criCounts1, criCounts2)

        assertThat(distance, Matchers.`is`(0))
    }

    private val text5 = "Assume I'm writing an essay. " +
            "I'm good in academic writing, so I start the essay with a topic sentence." +
            "And I finish the introduction with the confirmation that this text is about writing essays.\n" +
            "\n" +
            "Now, I'm starting a new paragraph. It's about as long as the first." +
            "The highly philosophical question is. Is this longer text much distant from the previous version?" +
            "Is it?\n"


    @Test fun distance__0_127__text4__text5() {
        val criStuff = CriCounter(0, 127)
        val criCounts1 = criStuff.criCounts(text4)
        val criCounts2 = criStuff.criCounts(text5)
        val calc = AbsoluteCriDistance()

        val distance = calc.distance(criCounts1, criCounts2)

        assertThat(distance, Matchers.`is`(172))
    }

    private val text6 = "Assume I'm writing an essay. " +
            "I'm good in academic writing, so I start the essay with a topic sentence." +
            "And I finish the introduction with the confirmation that this text is about writing essays.\n" +
            "\n" +
            "Now, I'm starting a new paragraph. It's about as long as the first." +
            "The highly philosophical question is. Is this longer text much distant from the previous version?" +
            "Is it?\n" +
            "And what now?\n"

    @Test fun distance__0_127__text5__text6() {
        val criStuff = CriCounter(0, 127)
        val criCounts1 = criStuff.criCounts(text5)
        val criCounts2 = criStuff.criCounts(text6)
        val calc = AbsoluteCriDistance()

        val distance = calc.distance(criCounts1, criCounts2)

        assertThat(distance, Matchers.`is`(14))
    }

    private val text7 = "Assume I'm writing an essay. " +
            "I'm good in academic writing, so I start the essay with a topic sentence." +
            "And I finish the introduction with the confirmation that this text is about writing essays.\n" +
            "\n" +
            "Now, I'm starting a new paragraph. It's about as long as the first." +
            "The highly philosophical question is. Is this longer text much distant from the previous version?" +
            "Is it?\n" +
            "\n" +
            "And what now?\n"


    @Test fun distance__0_127__text6__text7() {
        val criStuff = CriCounter(0, 127)
        val criCounts1 = criStuff.criCounts(text5)
        val criCounts2 = criStuff.criCounts(text6)
        val calc = AbsoluteCriDistance()

        val distance = calc.distance(criCounts1, criCounts2)

        assertThat(distance, Matchers.`is`(14))
    }

    private val text8 = "Essay writing is an intertesting topic." +
            "You can formulate thoughts about it in various ways. " +
            "You can even have different topic phases." +
            "And it's still about essay writing.\n" +
            "\n" +
            "New paragraphs are started here, too. But is this the same document as the one we saw earilier. " +
            "The philosoph says no! There's a high distance.\n" +
            "\n" +
            "\nIs there? And what then?"

    @Test fun distance__0_127__text7__text8() {
        val criStuff = CriCounter(0, 127)
        val criCounts1 = criStuff.criCounts(text7)
        val criCounts2 = criStuff.criCounts(text8)
        val calc = AbsoluteCriDistance()

        val distance = calc.distance(criCounts1, criCounts2)

        assertThat(distance, Matchers.`is`(435))
    }

    @Test fun distance__0_127__text8__text9() {
        val criStuff = CriCounter(0, 127)
        val criCounts1 = criStuff.criCounts(text8)
        val criCounts2 = criStuff.criCounts(Doc.text9)
        val calc = AbsoluteCriDistance()

        val distance = calc.distance(criCounts1, criCounts2)

        assertThat(distance, Matchers.`is`(7887))
    }

    @Test fun distance__0_127__text9__text10() {
        val criStuff = CriCounter(0, 127)
        val criCounts1 = criStuff.criCounts(Doc.text9)
        val criCounts2 = criStuff.criCounts(Doc.text10)
        val calc = AbsoluteCriDistance()

        val distance = calc.distance(criCounts1, criCounts2)

        assertThat(distance, Matchers.`is`(141))
    }

    @Test fun distance__0_127__text9__text11() {
        val criStuff = CriCounter(0, 127)
        val criCounts1 = criStuff.criCounts(Doc.text9)
        val criCounts2 = criStuff.criCounts(Doc.text11)
        val calc = AbsoluteCriDistance()

        val distance = calc.distance(criCounts1, criCounts2)

        assertThat(distance, Matchers.`is`(325))
    }

    @Test fun distance__0_127__text9__text12() {
        val criStuff = CriCounter(0, 127)
        val criCounts1 = criStuff.criCounts(Doc.text9)
        val criCounts2 = criStuff.criCounts(Doc.text12)
        val calc = AbsoluteCriDistance()

        val distance = calc.distance(criCounts1, criCounts2)

        assertThat(distance, Matchers.`is`(359))
    }

    @Test fun distance__0_127__text9__text13() {
        val criStuff = CriCounter(0, 127)
        val criCounts1 = criStuff.criCounts(Doc.text9)
        val criCounts2 = criStuff.criCounts(Doc.text13)
        val calc = AbsoluteCriDistance()

        val distance = calc.distance(criCounts1, criCounts2)

        assertThat(distance, Matchers.`is`(8256))
    }

    @Test fun distance__0_127__text13__text14() {
        val criStuff = CriCounter(0, 127)
        val criCounts1 = criStuff.criCounts(Doc.text13)
        val criCounts2 = criStuff.criCounts(Doc.text14)
        val calc = AbsoluteCriDistance()

        val distance = calc.distance(criCounts1, criCounts2)

        assertThat(distance, Matchers.`is`(5946))
    }
}
