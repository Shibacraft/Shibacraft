package net.shibacraft.core.yaml;

public interface YamlFileConfiguration extends YamlConfigurationSection {
    
    void reload() throws Exception;
    
    void save() throws Exception;
    
}
