# Java Collections

Some notes on Java Collections, how they work and what they are used for.

Collections are data structures.

Most of them aren't thread safe by default.

## Map

It isn't possible to instantiate a map, so we use HashMap instead. There are some variations like TreeMap, WeakHashMap.

You can't have duplicate keys on a Map-like structure.

TreeMap guarantees order

Relates keys and values

You can't have two values with same key
## List

To declare an ArrayList, we do the following:

`ArrayList<T> name = new ArrayList<>();`

Where T is the type of the items that will be on the ArrayList.

Can be sorted.

Allows duplicates.

Its size is dynamic.

LinkedList know the previous and the next element.

ArrayList is an array that knows only the beginning and the end, interesting to be used on random access

LinkedList - sequential access


## Vector

## Queue

Priority Queue

## Set

Sets don't allow duplicate elements.

HashSet - Hashing

TreeSet - Has an index