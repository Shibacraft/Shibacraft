package net.shibacraft.core.city;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;


public class DefaultCity implements City{
   
   private String name;
   
   private String prefix;
   
   private String owner;
   
   private Set<String> members;
   
   private int maximumMembers;
   
   public DefaultCity(String name, String prefix, String owner) {
      this(name, prefix, owner, new HashSet<>(), 10);
   }
   
   public DefaultCity(String name, String prefix, String owner, Set<String> members, int maximumMembers) {
      this.name = name;
      this.prefix = prefix;
      this.owner = owner;
      this.members = members;
      this.maximumMembers = maximumMembers;
   }
   
   public DefaultCity(Map<String, Object> citySerialize) {
   
   }
   
   @Override
   public String getName() {
      return name;
   }
   
   @Override
   public void setName(String name) {
      this.name = name;
   }
   
   @Override
   public String getPrefix() {
      return prefix;
   }
   
   @Override
   public void setPrefix(String prefix) {
      this.prefix = prefix;
   }
   
   @Override
   public String getOwner() {
      return owner;
   }
   
   @Override
   public void setOwner(String owner) {
      this.owner = owner;
   }
   
   @Override
   public void addMember(String member) {
      members.add(member);
   }
   
   @Override
   public void removeMember(String member) {
      members.remove(member);
   }
   
   @Override
   public int getMaximumMembers() {
      return maximumMembers;
   }
   
   @Override
   public void setMaximumMembers(int membersRem) {
      this.maximumMembers = membersRem;
   }
   
   @Override
   public Set<String> getMembers() {
      return members;
   }
   
   @Override
   public String toString() {
      return "name='" + name + '\'' + ", prefix='" + prefix + '\'' + ", owner='" + owner +
             '\'' + ", members=" + members + ", maximumMembers=" + maximumMembers + '}';
   }
   
}
