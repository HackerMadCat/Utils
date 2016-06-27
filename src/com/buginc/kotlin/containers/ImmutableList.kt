package com.buginc.kotlin.containers

import java.util.*

//** ** Created by DeveloperHacker ** **//
//* https://github.com/DeveloperHacker *//

class ImmutableList<T>(list: List<T>) : List<T> {

    constructor(vararg list: T) : this(list.toList())

    private val memory = ArrayList<T>(list)

    override val size: Int
        get() = memory.size

    override fun contains(element: T) = memory.contains(element)

    override fun containsAll(elements: Collection<T>) = memory.containsAll(elements)

    override fun get(index: Int) = memory[index]

    override fun indexOf(element: T) = memory.indexOf(element)

    override fun isEmpty() = memory.isEmpty()

    override fun iterator() = object : Iterator<T> {

        private val it = memory.iterator()

        override fun hasNext() = it.hasNext()

        override fun next() = it.next()
    }

    override fun lastIndexOf(element: T) = memory.lastIndexOf(element)

    override fun listIterator() = object : ListIterator<T> {

        private val it = memory.listIterator()

        override fun hasNext() = it.hasNext()

        override fun hasPrevious() = it.hasPrevious()

        override fun next() = it.next()

        override fun nextIndex() = it.nextIndex()

        override fun previous() = it.previous()

        override fun previousIndex() = it.previousIndex()
    }

    override fun listIterator(index: Int) = object : ListIterator<T> {

        private val it = memory.listIterator(index)

        override fun hasNext() = it.hasNext()

        override fun hasPrevious() = it.hasPrevious()

        override fun next() = it.next()

        override fun nextIndex() = it.nextIndex()

        override fun previous() = it.previous()

        override fun previousIndex() = it.previousIndex()
    }

    override fun subList(fromIndex: Int, toIndex: Int) = ImmutableList(memory.subList(fromIndex, toIndex))
}
