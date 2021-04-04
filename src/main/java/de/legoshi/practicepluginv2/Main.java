package de.legoshi.practicepluginv2;

import de.legoshi.practicepluginv2.commands.EdgeJumpCommand;
import de.legoshi.practicepluginv2.commands.FrontJumpCommand;
import de.legoshi.practicepluginv2.commands.JumpCommand;
import de.legoshi.practicepluginv2.commands.TierCalcCommand;
import de.legoshi.practicepluginv2.listener.*;
import de.legoshi.practicepluginv2.manager.*;
import de.legoshi.practicepluginv2.util.CheckPointObject;
import de.legoshi.practicepluginv2.util.InventoryObject;
import de.legoshi.practicepluginv2.util.PathPlayer;
import de.legoshi.practicepluginv2.util.PlayerObject;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    public static Main instance;
    public PathPlayerManager pathPlayerManager;
    public PathPlayer pathPlayer;
    public JumpPathManager jumpPathManager;
    public CheckPointObject checkPointObject;
    public CheckPointManager checkPointManager;
    public ItemClickManager itemClickManager;
    public InventoryObject inventoryObject;
    public InventoryObjectManager inventoryObjectManager;
    public PlayerObjectManager playerObjectManager;
    public PlayerObject playerObject;
    public PlayerLocationManager playerLocationManager;

    @Override
    public void onEnable() {
        instance = this;

        checkPointObject = new CheckPointObject();
        checkPointManager = new CheckPointManager();
        itemClickManager = new ItemClickManager();

        pathPlayer = new PathPlayer();
        pathPlayerManager = new PathPlayerManager();
        jumpPathManager = new JumpPathManager();

        inventoryObjectManager = new InventoryObjectManager();
        inventoryObject = new InventoryObject();

        playerObjectManager = new PlayerObjectManager();
        playerObject = new PlayerObject();
        playerLocationManager = new PlayerLocationManager();

        //TODO: add ItemBuilder or settings to activate outputs (how far away from block you landed)

        //TODO: 45 - Strafe rater (Rating 45 strafes)
        //TODO: auto 45 strafe

        //TODO: add lobbysystem
        //TODO: Setup Perms for World/Spawn

        //TODO: make a Worldcreator
        //TODO: create a new World with command
        //TODO: create a new World max 1 for each player with /world Create
        //TODO: create an invition command for people
        //TODO: create an open world command

        //TODO: add Signs with Location + setup Commands

        //TODO: add a FacingChanger
        //TODO: make armorstands rideable
        //TODO: make armorstands edible from rideposition
        //TODO: change my facing on the tick of the edited armorstands index


        CommandRegistration();
        ListenerRegistration();

        reload();
        jumpPathManager.jumpPath();
        playerLocationManager.playerLocation();

    }

    @Override
    public void onDisable() {
        close();

    }

    private void CommandRegistration() {
        getCommand("practice").setExecutor(new JumpCommand());
        getCommand("jumpedge").setExecutor(new EdgeJumpCommand());
        getCommand("jumpfront").setExecutor(new FrontJumpCommand());
        getCommand("tier").setExecutor(new TierCalcCommand());

    }

    private void ListenerRegistration() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new JoinListener(), this);
        pm.registerEvents(new QuitListener(), this);
        pm.registerEvents(new PlayerInteractListener(), this);
        pm.registerEvents(new PlayerDropListener(), this);
        pm.registerEvents(new PlayerBlockDestroyListener(), this);
        pm.registerEvents(new PlayerBlockPlaceListener(), this);

    }

    private void reload() {
        for(Player all : Bukkit.getOnlinePlayers()) {
            inventoryObjectManager.joinPlayer(all);
            inventoryObject.setBuild(all);
            pathPlayerManager.joinPlayer(all);
            checkPointManager.joinPlayer(all);
            playerObjectManager.joinPlayer(all);
        }
    }

    private void close() {
        for(Player all : Bukkit.getOnlinePlayers()) {
            pathPlayerManager.leavePlayer(all);
        }
    }

    public static Main getInstance() {
        return instance;
    }


}
