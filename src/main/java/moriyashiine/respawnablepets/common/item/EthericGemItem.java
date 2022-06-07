/*
 * All Rights Reserved (c) 2022 MoriyaShiine
 */

package moriyashiine.respawnablepets.common.item;

import moriyashiine.respawnablepets.common.RespawnablePets;
import moriyashiine.respawnablepets.common.component.entity.RespawnableComponent;
import moriyashiine.respawnablepets.common.registry.ModEntityComponents;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.List;

public class EthericGemItem extends Item {
	public EthericGemItem(Settings settings) {
		super(settings);
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		if (user.isSneaking()) {
			if (!world.isClient) {
				List<MobEntity> entities = world.getEntitiesByClass(MobEntity.class, new Box(user.getBlockPos()).expand(9, 3, 9), foundEntity -> {
					if (!foundEntity.getComponent(ModEntityComponents.RESPAWNABLE).getRespawnable()) {
						NbtCompound compound = foundEntity.writeNbt(new NbtCompound());
						return compound.containsUuid("Owner") && user.getUuid().equals(compound.getUuid("Owner"));
					}
					return false;
				});
				if (!entities.isEmpty()) {
					entities.forEach(entity -> {
						RespawnableComponent respawnableComponent = entity.getComponent(ModEntityComponents.RESPAWNABLE);
						respawnableComponent.setRespawnable(true);
						respawnableComponent.sync();
					});
					if (entities.size() == 1) {
						user.sendMessage(Text.translatable(RespawnablePets.MOD_ID + ".message.enable_respawn", entities.get(0).getDisplayName()), true);
					} else {
						user.sendMessage(Text.translatable(RespawnablePets.MOD_ID + ".message.enable_respawn", Text.translatable("respawnablepets.message.counted_entities", entities.size())), true);
					}
				}
			}
			return TypedActionResult.success(user.getStackInHand(hand), world.isClient);
		}
		return super.use(world, user, hand);
	}
}
