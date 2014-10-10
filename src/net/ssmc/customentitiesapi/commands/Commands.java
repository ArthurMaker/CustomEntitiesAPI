package net.ssmc.customentitiesapi.commands;

import net.ssmc.customentitiesapi.NMSUtils;
import net.ssmc.customentitiesapi.entities.*;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;

public class Commands implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String Label, String[] args) {
		if(s instanceof Player){
			Sheep sh = (Sheep)Bukkit.getWorlds().get(0).spawnEntity(((Player)s).getLocation(), EntityType.SHEEP);
			sh.setColor(DyeColor.BLUE);
			CustomEntitySheep ces = CustomEntities.getCustomEntitySheep(sh);
			
			/*Skeleton s1 = (Skeleton) Bukkit.getWorlds().get(0).spawnEntity(((Player)s).getLocation(), EntityType.SKELETON);
			s1.getEquipment().setItemInHand(new ItemStack(Material.BOW));
			CustomEntitySkeleton s2 = CustomEntities.getCustomEntitySkeleton(s1);
			s2.removeGoalSelectorPathfinderGoalAll();
			s2.setGoalSelectorDefaultPathfinderGoals();*/
		}
		return false;
	}

}
