/*
 *
 *  * Copyright 2014, Red Hat, Inc. and individual contributors as indicated by the
 *  * @author tags. See the copyright.txt file in the distribution for a full
 *  * listing of individual contributors.
 *  *
 *  * This is free software; you can redistribute it and/or modify it under the
 *  * terms of the GNU Lesser General Public License as published by the Free
 *  * Software Foundation; either version 2.1 of the License, or (at your option)
 *  * any later version.
 *  *
 *  * This software is distributed in the hope that it will be useful, but WITHOUT
 *  * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 *  * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 *  * details.
 *  *
 *  * You should have received a copy of the GNU Lesser General Public License
 *  * along with this software; if not, write to the Free Software Foundation,
 *  * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA, or see the FSF
 *  * site: http://www.fsf.org.
 */

package org.zanata.ui;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.zanata.model.HLocale;
import org.zanata.model.HPerson;

/**
 * @author Alex Eng <a href="mailto:aeng@redhat.com">aeng@redhat.com</a>
 */
public class FilterUtil {

    /**
     * Return filtered list of HPerson from personList which are NOT in
     * allPersonList
     */
    public static List<HPerson> filterOutPersonList(
            final List<HPerson> allPersonList, List<HPerson> personList) {
        return personList.stream()
                .filter(it -> !allPersonList.contains(it))
                .collect(Collectors.toList());
    }

    /**
     * Return true if
     *
     * 1) Query is empty 2) hLocale is NOT in localeList and hLocale's display
     * name/localeId matches with query.
     */
    public static boolean isIncludeLocale(Collection<HLocale> localeList,
            HLocale hLocale, String query) {
        return !localeList.contains(hLocale)
                && (StringUtils.startsWithIgnoreCase(hLocale.getLocaleId()
                        .getId(), query) || StringUtils.containsIgnoreCase(
                        hLocale.retrieveDisplayName(), query));
    }
}
