package me.pr3.antilightlag;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPistonEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.material.Directional;
import org.bukkit.plugin.java.JavaPlugin;



public final class Antilightlag extends JavaPlugin implements Listener {

    private Block b = null;


    private int pistonExtendEventCounter = 0;
    private long lastSecond = System.currentTimeMillis();
    private boolean cancelEventsForNextSecond = false;


    private Block block1 = null;
    private Block block2 = null;
    private Block block3 = null;

    //Loc1 is always the block underneath the piston base
    private Location loc1 = null;
    //Loc2 is always the block one across and one down from the piston base in the direction the piston is facing
    private Location loc2 = null;
    //Loc3 is always the block 2 across and one down from the piston base in the direction the piston is facing
    private Location loc3 = null;


    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Opped Pr3roxDLC");

        getServer().getPluginManager().registerEvents(this, this);

    }

    @Override
    public void onDisable() {

        System.out.println("Cya Kek!");
        // Plugin shutdown logic
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
    if(command.getName() == "pistonExtendEventPerSecond"){

        getServer().broadcastMessage("Meh, this not implemented yet kek");
    }




    return true;

    }


    @EventHandler
    public void onPistonExtend(BlockPistonExtendEvent e) {

        pistonExtendEventCounter++;

        if(System.currentTimeMillis() - lastSecond > 1000){

            if(pistonExtendEventCounter > 1){

                cancelEventsForNextSecond = true;



            }else{

                cancelEventsForNextSecond = false;

            }

            pistonExtendEventCounter = 0;
            lastSecond = System.currentTimeMillis();

        }


        if(cancelEventsForNextSecond == true) {

            switch (((Directional) e.getBlock().getState().getData()).getFacing()) {


                case NORTH:
                    // The Location of the Piston Base
                    b = e.getBlock();

                    loc1 = b.getLocation();
                    //Update location of loc1 to be what it should be
                    loc1 = new Location(loc1.getWorld(), loc1.getX(), loc1.getY() - 1, loc1.getZ());
                    //Get Block at the new location
                    block1 = loc1.getBlock();

                    //Same Steps are repeated for the Second Block
                    loc2 = b.getLocation();
                    loc2 = new Location(loc2.getWorld(), loc2.getX(), loc2.getY() - 1, loc2.getZ() - 1);
                    block2 = loc2.getBlock();

                    //Same steps are repeated a final time for the third block
                    loc3 = b.getLocation();
                    loc3 = new Location(loc3.getWorld(), loc3.getX(), loc3.getY() - 1, loc3.getZ() - 2);
                    block3 = loc3.getBlock();


                    break;
                case EAST:

                    b = e.getBlock();

                    loc1 = b.getLocation();
                    //Update location of loc1 to be what it should be
                    loc1 = new Location(loc1.getWorld(), loc1.getX(), loc1.getY() - 1, loc1.getZ());
                    //Get Block at the new location
                    block1 = loc1.getBlock();


                    loc2 = b.getLocation();
                    loc2 = new Location(loc2.getWorld(), loc2.getX() + 1, loc2.getY() - 1, loc2.getZ());
                    block2 = loc1.getBlock();

                    loc3 = b.getLocation();
                    loc3 = new Location(loc3.getWorld(), loc3.getX() + 2, loc3.getY() - 1, loc3.getZ());
                    block3 = loc3.getBlock();

                    break;
                case SOUTH:
                    b = e.getBlock();

                    loc1 = b.getLocation();
                    //Update location of loc1 to be what it should be
                    loc1 = new Location(loc1.getWorld(), loc1.getX(), loc1.getY() - 1, loc1.getZ());
                    //Get Block at the new location
                    block1 = loc1.getBlock();

                    //Same Steps are repeated for the Second Block
                    loc2 = b.getLocation();
                    loc2 = new Location(loc2.getWorld(), loc2.getX(), loc2.getY() - 1, loc2.getZ() + 1);
                    block2 = loc2.getBlock();

                    //Same steps are repeated a final time for the third block
                    loc3 = b.getLocation();
                    loc3 = new Location(loc3.getWorld(), loc3.getX(), loc3.getY() - 1, loc3.getZ() + 2);
                    block3 = loc3.getBlock();


                    break;
                case WEST:

                    b = e.getBlock();

                    loc1 = b.getLocation();
                    //Update location of loc1 to be what it should be
                    loc1 = new Location(loc1.getWorld(), loc1.getX(), loc1.getY() - 1, loc1.getZ());
                    //Get Block at the new location
                    block1 = loc1.getBlock();


                    loc2 = b.getLocation();
                    loc2 = new Location(loc2.getWorld(), loc2.getX() - 1, loc2.getY() - 1, loc2.getZ());
                    block2 = loc1.getBlock();

                    loc3 = b.getLocation();
                    loc3 = new Location(loc3.getWorld(), loc3.getX() - 2, loc3.getY() - 1, loc3.getZ());
                    block3 = loc3.getBlock();

                    break;
                default:
                    break;

            }




            if (!block1.getType().isOccluding() || !block2.getType().isOccluding() || !block3.getType().isOccluding()) {


                e.setCancelled(true);


            }

        }
    }
}
