/**
 * Released under the terms of the Apache License 2.0.
 * Copyright (c) 2020 objecttrouve.org <un.object.trouve@gmail.com>.
 */
package org.objecttrouve.nexttext

import com.bitplan.mediawiki.japi.Mediawiki

import com.bitplan.mediawiki.japi.api.Rev
import org.apache.commons.lang3.StringUtils
import java.util.*

class Wiki(siteurl: String?) : Mediawiki(siteurl) {


    @Throws(Exception::class)
    fun getPageRevisions(pageTitle: String, revisionLimit: Int, rvprop: String, queryParams: String?): List<Rev> {
        if (StringUtils.isBlank(pageTitle)) {
            throw IllegalArgumentException("Please provide a valid page title.")
        }
        if (revisionLimit < 1 || revisionLimit > 500) {
            throw IllegalArgumentException("Revision limit must be > 0 and <= 500.")
        }
        if (StringUtils.isBlank(rvprop)) {
            throw IllegalArgumentException("Please provide a meaningful rvprop string.")
        }
        val api = getQueryResult("" +
                "&prop=revisions" +
                "&rvprop=" + rvprop +
                "&rvlimit=" + revisionLimit + (queryParams ?: "") +
                "&titles=" + normalizeTitle(pageTitle))
        handleError(api)
        val pages = api.query.pages
        val pageRevisions = LinkedList<Rev>()
        if (pages != null) {
            val page = pages[0]
            if (page != null) {
                pageRevisions.addAll(page.revisions)
            }
        }
        return Collections.unmodifiableList(pageRevisions)
    }


}