package ru.mobileup.codequality.input

import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import java.io.File
import javax.xml.parsers.DocumentBuilderFactory

fun parseDocument(file: File): Document {
    val documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder()
    return documentBuilder.parse(file)
}

fun NodeList.toSequence(): Sequence<Node> {
    return Sequence {
        var index = 0
        object : Iterator<Node> {
            override fun hasNext(): Boolean {
                return index < length
            }

            override fun next(): Node {
                if (!hasNext()) {
                    throw NoSuchElementException()
                }
                return item(index++)
            }
        }
    }
}

val Node.childElements get() = childNodes.toSequence().filterIsInstance<Element>()