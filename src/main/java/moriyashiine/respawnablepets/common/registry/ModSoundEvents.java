package moriyashiine.respawnablepets.common.registry;

import moriyashiine.respawnablepets.common.RespawnablePets;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModSoundEvents {
	public static final SoundEvent ENTITY_GENERIC_TELEPORT = new SoundEvent(new Identifier(RespawnablePets.MOD_ID, "entity.generic.teleport"));
	
	public static void init() {
		Registry.register(Registry.SOUND_EVENT, new Identifier(RespawnablePets.MOD_ID, "entity.generic.teleport"), ENTITY_GENERIC_TELEPORT);
	}
}
