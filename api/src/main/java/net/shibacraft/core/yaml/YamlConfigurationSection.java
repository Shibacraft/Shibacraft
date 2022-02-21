package net.shibacraft.core.yaml;

import java.util.List;
import java.util.Set;

public interface YamlConfigurationSection {
    
    Object get(String path);
    
    void set(String path, Object value);
    
    String getString(String path);
    
    boolean contains(String path);
    
    int getInt(String path);
    
    double getDouble(String path);
    
    boolean getBoolean(String path);
    
    float getFloat(String path);
    
    List<String> getStringList(String path);
    
    Set<String> getKeys(boolean deep);
    
    YamlConfigurationSection getSection(String path);
    
}
