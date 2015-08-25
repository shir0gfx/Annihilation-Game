package eu.shirogfx.annihilation.api;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import eu.shirogfx.annihilation.maps.GameMap;

public class GameStartEvent extends Event {
    
    private static final HandlerList handlers = new HandlerList();

    private GameMap m;
    
    public GameStartEvent(GameMap m) {
        this.m = m;
    }
    
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
    
    public static HandlerList getHandlerList() {
        return handlers;
    }

    public GameMap getMap() {
        return m;
    }
}
