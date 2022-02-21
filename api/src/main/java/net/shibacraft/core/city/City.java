package net.shibacraft.core.city;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.jetbrains.annotations.NotNull;

import java.util.*;


public interface City extends ConfigurationSerializable {
   
   String getName();
   
   void setName(String name);
   
   String getPrefix();
   
   void setPrefix(String prefix);
   
   String getOwner();
   
   void setOwner(String owner);
   
   void addMember(String member);
   
   void removeMember(String member);
   
   int getMaximumMembers();
   
   void setMaximumMembers(int membersRem);
   
   Set<String> getMembers();
   
   @NotNull
   default Map<String, Object> serialize(){
      Map<String, Object> citiesSerializable = new HashMap<>();
      citiesSerializable.put("city-name", getName());
      citiesSerializable.put("displayname", getPrefix());
      citiesSerializable.put("owner", getOwner());
      Set<String> members = new HashSet<>(getMembers());
      citiesSerializable.put("members", members);
      citiesSerializable.put("maximum-members", getMaximumMembers());
      return citiesSerializable;
   }
   
}
