# Version history

About versions, they are hell! After personal experience of having different versions for each module / produced jar which was close to impossible to keep track of which was compatible with which, I decided to go with just one and the same version for all modules of the tool. This has the side effect of changes in any submodule, even the editor, which probably not everyone uses, will change the version for all even though no changes have been done for some modules. What have changed for each version is documented below so that you can determine if upgrading to the latest version is wanted/needed or not.

## 1.4

* Added support for what I call _Markdown Style Sheet_ or MSS for short. This is only applicable to PDF generation. For HTML there is CSS, and generating CSS from the MSS is a bad idea. The MSS is relatively simple and JSON based. 

  * It supports ttf, otf, and any other format supported by iText for external fonts. 

  * It allows for image configuration like scaling, rotating, and alignment. Before all images were alinged to the left. Now they can be alingned to the  left, middle, or right. In previous versions all images was scaled to 60 percent due to iText rendering images very much bigger than any other image viewer (that I have at least).  This scaling can now be set with MSS.

* Added support for `<div class="..">...</div>`. This tool is mainly for writing documentation and generating PDF, but I wanted to add more flexibility for generating HTML pages also. Even though you probably want to keep a common style throughout a document, I did add div support to MSS. Divs within divs inherit styles upward. This was relatively simple to do. Note that the "Options / Settings" section uses a div with slightly different formatting than the rest of the document. Each option is a level 3 heading (H3) which is why it is part of the TOC, but styled with a smaller font with a different color.

* Added possibility to also have an image on the title page.

* Added annotations within a comment block. Most of the options for the PDF generator can now be specified with annotations in the document. For example `@PDFPageSize("A4")`. This means for example that the title page can be part of the document. This comment with annotations should preferrably be the first thing in the document. 

* Added labels in options for all previously hardcoded text strings in PDFGenerator. It should now be possible to completely generate a document in a different language than English. These can also be set with comment annotations as mentioned above.

* Added Undo / Redo to editor.

The addition of MSS made huge changes to the code. To be as backwards compatible as possible the defaults for the MSS settings are as things looked before. There is also a _default.mss_ file that gets used if you don't supply your own. This has settings that mimics the previous styles. 

Also note that the PDF UserGuide now shows off the new features, mostly for that purpose :-).


## 1.3.9

Only bugfix in editor when generating HTML directly from editor, which caused an NPE.

## 1.3.8

Bad internal version dependencies in well ... probably from version 1.3.4 up to 1.3.7. The markdowndoc-maven-plugin were using a too old (hardcoded!!) version of markdown-doc-lib, which is the core of MarkdownDoc! It was pointing to version 1.3.3. This means that fixes in 1.3.4 and 1.3.5 were not available when maven plugin was used! It now uses ${project.version}. The command line jar and the editor have had the correct version dependency.

Very sorry for this!

## 1.3.7

* Bugfixes in the maven plugin.

* The maven plugin also no longer has any runtime dependency on CodeLicenseManager which is a build only plugin, something maven does not really distinguish.

* Includes a pull request submitted by both komarevsky and iorixxx that fixes an XML error in an example in the user guide. Thanks for seeing that and submitting pull requests!

## 1.3.6

Bug fixes in MarkdownDocEditor:

- Preformatted styling should now behave correctly.

- Preformatted font (monospace) settings now work. Also defaulted font size of monospace to 14 rather than 16.

## 1.3.5

What I did not mention in the information for version 1.3.4 is that the editor was converted from Java to Groovy. Here I apparently ran into a Groovy gotcha: What looked to be a member reference were actually a property reference to the same method that tried to reference member. In this case it was an anonymously implemented interface with a getter whose implementation tried to reference the outer class member of same name as getter property, and got the property rather than the member causing a never ending loop resulting in java.lang.StackOverflowError.

This affected only generating of PDF and HTML. The error occured after writing generated output, but before opening the generated output (when told to do so by checkbox setting). This problem is now fixed by this version and is the only thing that differs from previous version.

## 1.3.4

Fixed a bug with relative path for images using _PDFGenerator_ reported by Maher Gamal. There are now 5 ways to specifiy paths to images for PDF:

1. Absolute path
2. Relative to current directory.
3. Relative to markdown document.
4. Relative to resulting PDF document.
5. Relative to a supplied root dir. This can now be specified in the PDF generator options. If using the library, passing rootDir will override the options rootDir.

These paths will be automatically resolved.

## 1.3.3

Ironed out all _known_ bugs in editor.

## 1.3.2

Added markdown formatting as you write.

## 1.3.1

Bug fixes. Monospaced font now rendering correctly.

Deleting text with backspace have strange effects on text layout. That is, the place where a senetence is broken to the right and moved down to the next line keeps moving around while deleting text, in some completely different paragraph! This is entirely handled by JTextPane. I have tried to find a way to intercept the delete key and handle delete myself, but I have not been successful in finding a way to do that if it is even possible. Continuing writing new text after deleting text seems to restore the layout. This oddity has no effect on the final text, it is just the layout while editing that is affacted. You will also only see this if you write paragraphs as one block of text that wraps around into multiple lines without pressing return until the end of the paragraph.

## 1.3

Made big changes to the editor, finally making it into what I want, with some markdown formatting as you write, and far more configuration in settings dialog, which have also been redone.

Bug fixes.

## 1.2.10

Added support for &amp;lt;, &amp;gt;, and &amp;amp;.

## 1.2.9

Added markdown file reading feature by allowing markdown files to be dropped on the editor in preview mode, in wihch case the dropped file will be formatted and displayed without changeing the content of the editor. Exiting preview and doing a preview again will again preview the editor content.

## 1.2.8

Headings can now **not** be more than one line (not include LF/CRLF). Before they were treated like paragraphs. This to be more compatible with other Markdown tools and Markdown documents.

## 1.2.7

Added settings for specifying top, bottom, left, and right margins in editor. Please note that I've been a bit lazy here. The sizes are in pixels, not characters/lines!

## 1.2.6

Added the new _.mddoc_ format, which makes command line usage easier, but it is also supported by the maven plugin and the library has a utility that completely handles this format.

Added a Java Swing based editor for editing markdown with support.

## 1.2.5

Added _parserOptions_ now used by JavadocParser to markdown parse javadoc comments if markdownJavadoc=true is provided. The Parser API is thus also updated to take a Properties object for the parser options.

## 1.2.4

Added _makeFileLinksRelativeTo_ option for HTMLGenerator and MarkdownGenerator mostly to be able to manipulate _file:_ references to images in the generated result so that the image paths still work in source when editing with a markdown tool and is still correct when generated to a different path.

## 1.2.3

If image paths are not absolute and not http referenced then they are now looked for relative to the source markdown file first, and then the are looked for relative to the result file as before. This makes it easier to generate a big document for a whole project containing several subproject with local makdown documents and referenced images. The image reference can still be relative to the subproject local markdown file.

## 1.2.2

Added support for non breaking space (nbsp) to be able to indent text. This is one more exception to no html pass through.
