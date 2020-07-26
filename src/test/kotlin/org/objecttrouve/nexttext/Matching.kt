/**
 * Released under the terms of the Apache License 2.0.
 * Copyright (c) 2020 objecttrouve.org <un.object.trouve@gmail.com>.
 */
package org.objecttrouve.nexttext

import org.objecttrouve.testing.matchers.ConvenientMatchers
import org.objecttrouve.testing.matchers.customization.StringifiersConfig
import org.objecttrouve.testing.matchers.fluentatts.Attribute
import org.objecttrouve.testing.matchers.fluentatts.FluentAttributeMatcher
import org.objecttrouve.testing.matchers.fluentits.FluentIterableMatcher
import java.util.*

object Matching {

    internal val counts: Attribute<CriCounts, List<IntArray>> = Attribute.attribute("CRI counts") { criCounts -> criCounts.counts().toList() }
    private val an = ConvenientMatchers.customized()
            .withStringifiers(StringifiersConfig.stringifiers()
                    .withShortStringifier(IntArray::class.java) { intArray -> Arrays.toString(intArray) })
            .build()

    internal fun aBunchOfCriCounts(): FluentIterableMatcher<IntArray, Iterable<IntArray>> = an.iterableOf(IntArray::class.java)
    internal fun aCriCounts(): FluentAttributeMatcher<CriCounts> = an.instanceOf(CriCounts::class.java)
}
