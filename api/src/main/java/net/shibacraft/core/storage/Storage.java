package net.shibacraft.core.storage;

import java.util.Map;
import java.util.Optional;


public interface Storage<K, V> {
   
   Map<K, V> get();
   
   Optional<V> find(K key);
   
   Optional<V> findFromData(K key);
   
   void save(K key);
   
   void saveObject(K key, V value) throws Exception;
   
   void remove(K key);
   
   void add(K key, V value);
   
   void saveAll() throws Exception;
   
   void loadAll();

}
