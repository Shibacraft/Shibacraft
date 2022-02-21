package net.shibacraft.core;

import lombok.Getter;
import net.shibacraft.core.city.City;
import net.shibacraft.core.city.request.RequestHandler;
import net.shibacraft.core.commands.internal.CommandLoader;
import net.shibacraft.core.file.FileMatcher;
import net.shibacraft.core.loader.TickLoader;
import net.shibacraft.core.service.CityService;
import net.shibacraft.core.service.Service;
import net.shibacraft.core.service.UserService;
import net.shibacraft.core.storage.CityStorage;
import net.shibacraft.core.storage.Storage;
import net.shibacraft.core.storage.UserStorage;
import net.shibacraft.core.user.User;
import net.shibacraft.core.utils.banner.Banner;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.UUID;


@Getter
public class CorePlugin {

   private final JavaPlugin loader;
   
   private final FileMatcher fileMatcher;
   private final CommandLoader commandLoader;
   private final RequestHandler requestHandler;
   
   private final TickLoader tickLoader;
   private final Service[] services;
   
   private final Storage<String, City> cityStorage;
   
   private final Storage<UUID, User> userStorage;
   
   public CorePlugin(JavaPlugin loader) {
      this.loader = loader;
      this.fileMatcher = new FileMatcher(this);
      this.commandLoader = new CommandLoader(this);
      this.tickLoader = new TickLoader(this);
      this.cityStorage = new CityStorage(this);
      this.userStorage = new UserStorage(this);
      this.services = new Service[] {new CityService(this), new UserService(this)};
      this.requestHandler = new RequestHandler();
   }
   
   public void load() {
      fileMatcher.load();
      commandLoader.load();
   }
   
   public void enable() {
      Banner.action(loader);
      tickLoader.load();
      Arrays.stream(services).forEach(Service::start);
   }
   
   public void disable() {
      Banner.action(loader);
      Arrays.stream(services).forEach(Service::stop);
   }
   
}

