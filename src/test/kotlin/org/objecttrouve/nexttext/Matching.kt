package org.objecttrouve.nexttext

import org.objecttrouve.testing.matchers.ConvenientMatchers
import org.objecttrouve.testing.matchers.customization.StringifiersConfig
import org.objecttrouve.testing.matchers.fluentatts.Attribute
import org.objecttrouve.testing.matchers.fluentatts.FluentAttributeMatcher
import org.objecttrouve.testing.matchers.fluentits.FluentIterableMatcher
import java.util.*

object Matching {

    val counts: Attribute<CriCounts, List<IntArray>> = Attribute.attribute("CRI counts") { criCounts -> criCounts.counts().toList()}
    private val an = ConvenientMatchers.customized()
            .withStringifiers(StringifiersConfig.stringifiers()
                    .withShortStringifier(IntArray::class.java) { intArray -> Arrays.toString(intArray)})
            .build()

    fun aBunchOfCriCounts(): FluentIterableMatcher<IntArray, Iterable<IntArray>> = an.iterableOf(IntArray::class.java)
    fun aCriCounts(): FluentAttributeMatcher<CriCounts> = an.instanceOf(CriCounts::class.java)
}
