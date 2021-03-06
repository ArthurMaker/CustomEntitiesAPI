package me.dablakbandit.customentitiesapi.entities;

import ja.ClassClassPath;
import ja.CtClass;
import ja.CtField;
import ja.CtNewMethod;
import me.dablakbandit.customentitiesapi.CustomEntitiesAPI;
import me.dablakbandit.customentitiesapi.NMSUtils;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

public class CustomEntityBat extends CustomEntityAmbient {

	public CustomEntityBat() {
		super("CustomEntityBat");
		if (ctClass == null)
			return;
		register();
	}

	public CustomEntityBat(Location location) {
		this();
		a();
		spawnEntity(location);
	}

	public CustomEntityBat(Entity e) {
		this();
		a();
		try {
			entity = customentity.cast(NMSUtils.getHandle(e));
		} catch (Exception e1) {
		}
	}

	public CustomEntityBat(Object o) {
		this();
		a();
		entity = o;
	}

	public static Class<?> getCustomEntityBatClass() {
		try {
			return Class.forName("temp.CustomEntityBat");
		} catch (Exception e) {
			return null;
		}
	}

	public void a() {
		try {
			customentity = Class.forName("temp.CustomEntityBat");
			helper = Class.forName("temp.CustomEntityBatHelper");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void register() {
		try {
			customentity = Class.forName("temp.CustomEntityBat");
		} catch (Exception e1) {
			try {
				cp.insertClassPath(new ClassClassPath(
						new CustomEntityBatHelper().getClass()));
				CtClass ces = cp
						.getAndRename(
								"me.dablakbandit.customentitiesapi.entities.CustomEntityBatHelper",
								"temp.CustomEntityBatHelper");
				ces.setSuperclass(cp.get("temp.CustomEntityAmbientHelper"));
				ces.toClass();
				CtClass EntityBat = cp.getCtClass("net.minecraft.server."
						+ NMSUtils.getVersion() + "EntityBat");
				cp.importPackage("net.minecraft.server."
						+ NMSUtils.getVersion() + "EntityBat");
				cp.importPackage("net.minecraft.server."
						+ NMSUtils.getVersion() + "EntityAmbient");
				for (CtField f : fields) {
					ctClass.addField(f);
				}
				fields.clear();
				ctClass.setSuperclass(EntityBat);
				for (String m : methods) {
					ctClass.addMethod(CtNewMethod.make(m, ctClass));
				}
				methods.clear();
				customentity = ctClass.toClass();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		if (customentity != null)
			CustomEntitiesAPI.getInstance().registerEntity("EntityBat", 65,
					customentity);
	}

	public boolean isAsleep(){
		try{
			return (boolean) entity.getClass().getMethod("isAsleep").invoke(entity);
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}

	public void setAsleep(boolean value){
		try{
			entity.getClass().getMethod("setAsleep", boolean.class).invoke(entity, value);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
