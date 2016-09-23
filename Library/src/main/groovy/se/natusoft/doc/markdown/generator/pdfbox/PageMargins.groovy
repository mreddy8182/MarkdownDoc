/*
 *
 * PROJECT
 *     Name
 *         MarkdownDoc Library
 *
 *     Code Version
 *         1.5.0
 *
 *     Description
 *         Parses markdown and generates HTML and PDF.
 *
 * COPYRIGHTS
 *     Copyright (C) 2012 by Natusoft AB All rights reserved.
 *
 * LICENSE
 *     Apache 2.0 (Open Source)
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 *
 * AUTHORS
 *     tommy ()
 *         Changes:
 *         2016-07-29: Created!
 *
 */
package se.natusoft.doc.markdown.generator.pdfbox

import groovy.transform.CompileStatic
import groovy.transform.TypeChecked

/**
 * This just holds the page margins.
 */
@CompileStatic
@TypeChecked
class PageMargins {

    /** The page margins. */
    float topMargin, bottomMargin, leftMargin, rightMargin

    /** Insets for indenting on both left and right sides. */
    float leftInset = 0, rightInset = 0

    /**
     * Overrides default left margin getter to also add left inset.
     */
    float getLeftMargin() {
        this.leftMargin + leftInset
    }

    /**
     * Overrides default right margin getter to also add right inset.
     */
    float getRightMargin() {
        this.rightMargin + rightInset
    }
}