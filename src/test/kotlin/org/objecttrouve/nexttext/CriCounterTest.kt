package org.objecttrouve.nexttext

import org.hamcrest.Matchers.`is`
import org.junit.Assert.assertThat
import org.objecttrouve.nexttext.Matching.aBunchOfCriCounts
import org.objecttrouve.nexttext.Matching.counts
import kotlin.test.Test


class CriCounterTest {

    @Test
    fun criCounts__empty_String() {
        val criStuff = CriCounter()

        val criCounts = criStuff.criCounts("")

        val aBunchOfCriCounts = aBunchOfCriCounts()
        for (shiftedCodePointIndex in 0..127) {
            aBunchOfCriCounts.withItems(intArrayOf(0))
        }
        assertThat(criCounts, `is`(Matching.aCriCounts().with(counts, aBunchOfCriCounts.ofSize(128).exactly().ordered())))
    }

    @Test
    fun criCounts__codePointRange_65_to_67__and__empty_String() {
        val criStuff = CriCounter(65, 67) // "A","B","C"

        val criCounts = criStuff.criCounts("")

        val aBunchOfCriCounts = aBunchOfCriCounts()
        for (shiftedCodePointIndex in 0..2) {
            aBunchOfCriCounts.withItems(intArrayOf(0))
        }
        assertThat(criCounts, `is`(Matching.aCriCounts().with(counts, aBunchOfCriCounts.ofSize(3).exactly().ordered())))
    }

    @Test(expected = IllegalArgumentException::class)
    fun criCounts__codePointRange_65_to_64() {
       CriCounter(65, 64)
    }

    @Test(expected = IllegalArgumentException::class)
    fun criCounts__codePointRange_minus1_to_2() {
        CriCounter(-1, 2)
    }

    @Test
    fun criCounts__codePointRange_65_to_65__and__empty_String() {
        val criStuff = CriCounter(65, 65)

        val criCounts = criStuff.criCounts("")

        assertThat(criCounts,
                `is`(Matching.aCriCounts()
                        .with(counts, aBunchOfCriCounts()
                                .withItems(intArrayOf(0))
                                .exactly()
                                .ordered())))
    }

    @Test
    fun criCounts__codePointRange_65_to_65__and__an_A() {
        val criStuff = CriCounter(65, 65)

        val criCounts = criStuff.criCounts("A")

        assertThat(criCounts,
                `is`(Matching.aCriCounts()
                        .with(counts, aBunchOfCriCounts()
                                .withItems(intArrayOf(1))
                                .exactly()
                                .ordered())))
    }

    @Test
    fun criCounts__codePointRange_65_to_67__and__an_A() {
        val criStuff = CriCounter(65, 67)

        val criCounts = criStuff.criCounts("A")

        val aBunchOfCriCounts = aBunchOfCriCounts()
                .withItems(intArrayOf(1))
                .withItems(intArrayOf(0))
                .withItems(intArrayOf(0))
        assertThat(criCounts, `is`(Matching.aCriCounts().with(counts, aBunchOfCriCounts.ofSize(3).exactly().ordered())))
    }

    @Test
    fun criCounts__codePointRange_65_to_67__and__an_AA() {
        val criStuff = CriCounter(65, 67)

        val criCounts = criStuff.criCounts("AA")

        val aBunchOfCriCounts = aBunchOfCriCounts()
                .withItems(intArrayOf(1, 1))
                .withItems(intArrayOf(0, 0))
                .withItems(intArrayOf(0, 0))
        assertThat(criCounts, `is`(Matching.aCriCounts().with(counts, aBunchOfCriCounts.ofSize(3).exactly().ordered())))
    }

    @Test
    fun criCounts__codePointRange_65_to_67__and__an_AAA() {
        val criStuff = CriCounter(65, 67)

        val criCounts = criStuff.criCounts("AAA")

        val aBunchOfCriCounts = aBunchOfCriCounts()
                .withItems(intArrayOf(1, 2))
                .withItems(intArrayOf(0, 0))
                .withItems(intArrayOf(0, 0))
        assertThat(criCounts, `is`(Matching.aCriCounts().with(counts, aBunchOfCriCounts.ofSize(3).exactly().ordered())))
    }

    @Test
    fun criCounts__codePointRange_65_to_67__and__an_AAAA() {
        val criStuff = CriCounter(65, 67)

        val criCounts = criStuff.criCounts("AAAA")

        val aBunchOfCriCounts = aBunchOfCriCounts()
                .withItems(intArrayOf(1, 3))
                .withItems(intArrayOf(0, 0))
                .withItems(intArrayOf(0, 0))
        assertThat(criCounts, `is`(Matching.aCriCounts().with(counts, aBunchOfCriCounts.ofSize(3).exactly().ordered())))
    }

    @Test
    fun criCounts__codePointRange_65_to_67__and__ABBA() {
        val criStuff = CriCounter(65, 67)

        val criCounts = criStuff.criCounts("ABBA")

        val aBunchOfCriCounts = aBunchOfCriCounts()
                .withItems(intArrayOf(1, 0, 0, 1))
                .withItems(intArrayOf(0, 2, 0, 0))
                .withItems(intArrayOf(0, 0, 0, 0))
        assertThat(criCounts, `is`(Matching.aCriCounts().with(counts, aBunchOfCriCounts.ofSize(3).exactly().ordered())))
    }

    @Test
    fun criCounts__codePointRange_65_to_67__and__BABA() {
        val criStuff = CriCounter(65, 67)

        val criCounts = criStuff.criCounts("BABA")

        val aBunchOfCriCounts = aBunchOfCriCounts()
                .withItems(intArrayOf(0, 1, 1))
                .withItems(intArrayOf(1, 0, 1))
                .withItems(intArrayOf(0, 0, 0))
        assertThat(criCounts, `is`(Matching.aCriCounts().with(counts, aBunchOfCriCounts.ofSize(3).exactly().ordered())))
    }

    @Test
    fun criCounts__codePointRange_65_to_67__and__an_ABC() {
        val criStuff = CriCounter(65, 67)

        val criCounts = criStuff.criCounts("ABC")

        val aBunchOfCriCounts = aBunchOfCriCounts()
                .withItems(intArrayOf(1, 0, 0))
                .withItems(intArrayOf(0, 1, 0))
                .withItems(intArrayOf(0, 0, 1))
        assertThat(criCounts, `is`(Matching.aCriCounts().with(counts, aBunchOfCriCounts.ofSize(3).exactly().ordered())))
    }

    @Test
    fun criCounts__codePointRange_65_to_67__and__an_ABCABC() {
        val criStuff = CriCounter(65, 67)

        val criCounts = criStuff.criCounts("ABCABC")

        val aBunchOfCriCounts = aBunchOfCriCounts()
                .withItems(intArrayOf(1, 0, 0, 1))
                .withItems(intArrayOf(0, 1, 0, 1))
                .withItems(intArrayOf(0, 0, 1, 1))
        assertThat(criCounts, `is`(Matching.aCriCounts().with(counts, aBunchOfCriCounts.ofSize(3).exactly().ordered())))
    }

    @Test
    fun criCounts__codePointRange_65_to_67__and__an_ABCCBA() {
        val criStuff = CriCounter(65, 67)

        val criCounts = criStuff.criCounts("ABCCBA")

        val aBunchOfCriCounts = aBunchOfCriCounts()
                .withItems(intArrayOf(1, 0, 0, 0, 0, 1))
                .withItems(intArrayOf(0, 1, 0, 1, 0, 0))
                .withItems(intArrayOf(0, 1, 1, 0, 0, 0))
        assertThat(criCounts, `is`(Matching.aCriCounts().with(counts, aBunchOfCriCounts.ofSize(3).exactly().ordered())))
    }

    @Test
    fun criCounts__codePointRange_65_to_67__and__an_ABCCCBA() {
        val criStuff = CriCounter(65, 67)

        val criCounts = criStuff.criCounts("ABCCCBA")

        val aBunchOfCriCounts = aBunchOfCriCounts()
                .withItems(intArrayOf(1, 0, 0, 0, 0, 0, 1))
                .withItems(intArrayOf(0, 1, 0, 0, 1, 0, 0))
                .withItems(intArrayOf(0, 2, 1, 0, 0, 0, 0))
        assertThat(criCounts, `is`(Matching.aCriCounts().with(counts, aBunchOfCriCounts.ofSize(3).exactly().ordered())))
    }

    @Test
    fun criCounts__codePointRange_65_to_67__and__an_ABCCCBABCB() {
        val criStuff = CriCounter(65, 67)

        val criCounts = criStuff.criCounts("ABCCCBABCB")

        val aBunchOfCriCounts = aBunchOfCriCounts()
                .withItems(intArrayOf(1,0,0,0,0,0,1))
                .withItems(intArrayOf(0,1,2,0,1,0,0))
                .withItems(intArrayOf(0,2,1,0,1,0,0))
        assertThat(criCounts, `is`(Matching.aCriCounts().with(counts, aBunchOfCriCounts.ofSize(3).exactly().ordered())))
    }

    @Test
    fun criCounts__codePointRange_65_to_67__and__an_atABCCCBABCB__skipping_64() {
        val criStuff = CriCounter(65, 67)

        val criCounts = criStuff.criCounts("@ABCCCBABCB")

        val aBunchOfCriCounts = aBunchOfCriCounts()
                .withItems(intArrayOf(0, 1, 0, 0, 0, 0, 1))
                .withItems(intArrayOf(0, 0, 3, 0, 1, 0, 0))
                .withItems(intArrayOf(0, 2, 0, 1, 1, 0, 0))
        assertThat(criCounts, `is`(Matching.aCriCounts().with(counts, aBunchOfCriCounts.ofSize(3).exactly().ordered())))
    }

    @Test
    fun criCounts__codePointRange_65_to_67__and__an_AatBCCCBABCB__skipping_64() {
        val criStuff = CriCounter(65, 67)

        val criCounts = criStuff.criCounts("A@BCCCBABCB")

        val aBunchOfCriCounts = aBunchOfCriCounts()
                //                    0 1 2 3 4 5 6 7
                .withItems(intArrayOf(1, 0, 0, 0, 0, 0, 0, 1))
                .withItems(intArrayOf(0, 0, 3, 0, 1, 0, 0, 0))
                .withItems(intArrayOf(0, 2, 0, 1, 1, 0, 0, 0))
        assertThat(criCounts, `is`(Matching.aCriCounts().with(counts, aBunchOfCriCounts.ofSize(3).exactly().ordered())))
    }

    @Test
    fun criCounts__codePointRange_65_to_67__and__an_ABCCCBABCBat__skipping_64() {
        val criStuff = CriCounter(65, 67)

        val criCounts = criStuff.criCounts("ABCCCBABCB@")

        val aBunchOfCriCounts = aBunchOfCriCounts()
                .withItems(intArrayOf(1, 0, 0, 0, 0, 0, 1))
                .withItems(intArrayOf(0, 1, 2, 0, 1, 0, 0))
                .withItems(intArrayOf(0, 2, 1, 0, 1, 0, 0))
        assertThat(criCounts, `is`(Matching.aCriCounts().with(counts, aBunchOfCriCounts.ofSize(3).exactly().ordered())))
    }


    @Test
    fun criCounts__codePointRange_65_to_67__and__an_DABCCCBABCB__skipping_68() {
        val criStuff = CriCounter(65, 67)

        val criCounts = criStuff.criCounts("DABCCCBABCB")

        val aBunchOfCriCounts = aBunchOfCriCounts()
                .withItems(intArrayOf(0, 1, 0, 0, 0, 0, 1))
                .withItems(intArrayOf(0, 0, 3, 0, 1, 0, 0))
                .withItems(intArrayOf(0, 2, 0, 1, 1, 0, 0))
        assertThat(criCounts, `is`(Matching.aCriCounts().with(counts, aBunchOfCriCounts.ofSize(3).exactly().ordered())))
    }

    @Test
    fun criCounts__codePointRange_65_to_67__and__an_ADBCCCBABCB__skipping_68() {
        val criStuff = CriCounter(65, 67)

        val criCounts = criStuff.criCounts("ADBCCCBABCB")

        val aBunchOfCriCounts = aBunchOfCriCounts()
                //                    0 1 2 3 4 5 6 7
                .withItems(intArrayOf(1, 0, 0, 0, 0, 0, 0, 1))
                .withItems(intArrayOf(0, 0, 3, 0, 1, 0, 0, 0))
                .withItems(intArrayOf(0, 2, 0, 1, 1, 0, 0, 0))
        assertThat(criCounts, `is`(Matching.aCriCounts().with(counts, aBunchOfCriCounts.ofSize(3).exactly().ordered())))
    }

    @Test
    fun criCounts__codePointRange_65_to_67__and__an_ABCCCBABCBD__skipping_68() {
        val criStuff = CriCounter(65, 67)

        val criCounts = criStuff.criCounts("ABCCCBABCBD")

        val aBunchOfCriCounts = aBunchOfCriCounts()
                .withItems(intArrayOf(1, 0, 0, 0, 0, 0, 1))
                .withItems(intArrayOf(0, 1, 2, 0, 1, 0, 0))
                .withItems(intArrayOf(0, 2, 1, 0, 1, 0, 0))
        assertThat(criCounts, `is`(Matching.aCriCounts().with(counts, aBunchOfCriCounts.ofSize(3).exactly().ordered())))
    }
}




