package net.ssmc.customentitiesapi.entities;

import java.lang.reflect.Field;

import ja.CtClass;
import ja.CtField;
import ja.CtNewMethod;
import ja.LoaderClassPath;
import net.ssmc.customentitiesapi.CustomEntitiesAPI;
import net.ssmc.customentitiesapi.NMSUtils;

import org.bukkit.Location;
import org.bukkit.entity.Entity;

public class CustomEntityZombie extends CustomEntityMonster{

	public CustomEntityZombie(){
		super("CustomEntityZombie");
		if(ctClass==null)return;
		register();
	}

	public CustomEntityZombie(Location location){
		this();
		a();
		spawnEntity(location);
		removeGoalSelectorPathfinderGoalMeleeAttack();
		newGoalSelectorPathfinderGoalMeleeAttackDefault();
	}

	public CustomEntityZombie(Entity e){
		this();
		a();
		entity = NMSUtils.getHandle(e);
	}
	
	public CustomEntityZombie(Object o){
		this();
		a();
		entity = o;
	}

	public static Class<?> getCustomEntityZombieClass(){
		try {
			return Class.forName("temp.CustomEntityZombie");
		} catch (Exception e) {
			return null;
		}
	}

	private void register(){
		try{
			customentity = Class.forName("temp.CustomEntityZombie");
		}catch(Exception e1){
			try {
				cp.appendClassPath(new LoaderClassPath(CustomEntityZombie.class.getClassLoader()));
				CtClass ces = cp.getAndRename("net.ssmc.customentitiesapi.entities.CustomEntityZombieHelper", "temp.CustomEntityZombieHelper");
				ces.setSuperclass(cp.get("temp.CustomEntityMonsterHelper"));
				ces.toClass();
				CtClass EntityZombie = cp.getCtClass("net.minecraft.server." + NMSUtils.getVersion() + "EntityZombie");
				cp.importPackage("net.minecraft.server." + NMSUtils.getVersion() + "EntityZombie");
				cp.importPackage("net.minecraft.server." + NMSUtils.getVersion() + "EntityLiving");
				cp.importPackage("temp");
				fields.add(new CtField(CtClass.booleanType, "pushable", ctClass));	
				for(CtField f : fields){
					ctClass.addField(f);
				}
				fields.clear();
				ctClass.setSuperclass(EntityZombie);
				methods.add("public void setUnableToMove(){"
						+ "CustomEntityZombieHelper.setUnableToMove(this);"
						+ "}");
				methods.add("public void setAbleToMove(){"
						+ "CustomEntityZombieHelper.setAbleToMove(this);"
						+ "}");

				methods.add("public void setAbleToMove(double d){"
						+ "CustomEntityZombieHelper.setAbleToMove(this, d);"
						+ "}");
				for(String m : methods){
					ctClass.addMethod(CtNewMethod.make(m, ctClass));
				}
				methods.clear();
				customentity = ctClass.toClass();
			}catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		if(customentity!=null)CustomEntitiesAPI.getInstance().registerEntity("EntityZombie", 54, customentity);
	}

	public void a(){
		try{
			customentity = Class.forName("temp.CustomEntityZombie");
			helper = Class.forName("temp.CustomEntityZombieHelper");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void spawnEntity(Location location){
		if(entity!=null)return;
		try {
			Object o = NMSUtils.getHandle(location.getWorld());
			Object newzombie = helper.getMethod("createEntity", Object.class, Class.class, double.class, double.class, double.class, float.class, float.class)
					.invoke(null, o, customentity, location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
			o.getClass().getMethod("addEntity", NMSUtils.getNMSClass("Entity")).invoke(o, newzombie);
			entity = newzombie;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setPushable(){
		try {
			entity.getClass().getMethod("setPushable").invoke(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setPushable(boolean value){
		if(value){
			setPushable();
		}else{
			setUnpushable();
		}
	}

	public void setUnpushable(){
		try {
			entity.getClass().getMethod("setUnpushable").invoke(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setUnpushable(boolean value){
		if(value){
			setUnpushable();
		}else{
			setPushable();
		}
	}

	public void setAbleToMove(){
		try {
			entity.getClass().getMethod("setAbleToMove", double.class).invoke(entity, 1.0D);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setAbleToMove(boolean value){
		if(value){
			setAbleToMove();
		}else{
			setUnableToMove();
		}
	}

	public void setAbleToMove(double d){
		try {
			entity.getClass().getMethod("setAbleToMove", double.class).invoke(entity, d);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setUnableToMove(){
		try {
			entity.getClass().getMethod("setUnableToMove").invoke(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setUnableToMove(boolean value){
		if(value){
			setUnableToMove();
		}else{
			setAbleToMove();
		}
	}

	public void setGoalSelectorDefaultPathfinderGoals(){
		try {
			helper.getMethod("setGoalSelectorDefaultPathfinderGoals", Object.class).invoke(null, entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void newGoalSelectorPathfinderGoalMeleeAttackDefault(){
		try{
			try{
				Field f = entity.getClass().getField("world");
				Object world = f.get(entity);
				Field config = world.getClass().getDeclaredField("spigotConfig");
				Object config1 = config.get(world);
				Field f1 = config1.getClass().getField("zombieAggressiveTowardsVillager");
				Object value = f1.get(config1);
				if((boolean)value){
					newGoalSelectorPathfinderGoalMeleeAttack(1.0D, true);
				}
			} catch (Exception e) {
				newGoalSelectorPathfinderGoalMeleeAttack(1.0D, true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void newGoalSelectorPathfinderGoalMoveTowardsRestrictionDefault(){
		newGoalSelectorPathfinderGoalMoveTowardsRestriction(1.0D);
	}

	public void newGoalSelectorPathfinderGoalMoveThroughVillageDefault(){
		newGoalSelectorPathfinderGoalMoveThroughVillage(1.0D, false);
	}

	public void newGoalSelectorPathfinderGoalRandomStrollDefault(){
		newGoalSelectorPathfinderGoalRandomStroll(1.0D);
	}

	public void newGoalSelectorPathfinderGoalLookAtPlayerDefault(){
		newGoalSelectorPathfinderGoalLookAtPlayer(8.0F);
	}

	public boolean isVillager(){
		try {
			return (boolean)entity.getClass().getMethod("isVillager").invoke(entity);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void setBaby(boolean flag){
		try {
			entity.getClass().getMethod("setBaby", boolean.class).invoke(entity, flag);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
