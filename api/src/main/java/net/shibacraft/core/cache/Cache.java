package net.shibacraft.core.cache;

import java.util.Collection;


public interface Cache<K, V> {
   
   V get(K key);
   
   void add(V value);
   
   void remove(K key);
   
   boolean exists(K key);
   
   Collection<V> getValues();
   
}
