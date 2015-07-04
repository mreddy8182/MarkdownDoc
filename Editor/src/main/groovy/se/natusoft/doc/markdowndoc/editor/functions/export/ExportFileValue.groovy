/*
 *
 * PROJECT
 *     Name
 *         MarkdownDocEditor
 *
 *     Code Version
 *         1.3.9
 *
 *     Description
 *         An editor that supports editing markdown with formatting preview.
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
 *     Tommy Svensson (tommy@natusoft.se)
 *         Changes:
 *         2014-10-12: Created!
 *
 */
package se.natusoft.doc.markdowndoc.editor.functions.export

import groovy.transform.CompileStatic
import groovy.transform.TypeChecked

import javax.swing.JComponent

@CompileStatic
@TypeChecked
class ExportFileValue extends ExportDataValue {

    private String whatFile

    // This is provided later in constructor of subclass since it is provided to the constructor.
    DelayedServiceData delayedServiceData

    ExportFileValue(String labelText, String whatFile) {
        super(labelText)
        this.whatFile = whatFile
    }

    @Override
    protected JComponent ensureValueComp() {
        if (super.valueComp == null) {
            super.valueComp = new FileSelector(this.whatFile, delayedServiceData)
        }

        super.valueComp
    }

    String getValue() {
        if (whatFile == null || delayedServiceData == null) {
            throw new IllegalStateException("'whatFile' and 'gui' properties must have been provided before this call" +
                    " can be made!")
        }
        ((FileSelector)ensureValueComp()).getFile()
    }

    void setValue(String value) {
        ((FileSelector)ensureValueComp()).setFile(value)
    }
}
