package moriyashiine.respawnablepets.client;

import moriyashiine.respawnablepets.client.network.message.SpawnSmokeParticlesPacket;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

@Environment(EnvType.CLIENT)
public class RespawnablePetsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ClientPlayNetworking.registerGlobalReceiver(SpawnSmokeParticlesPacket.ID, SpawnSmokeParticlesPacket::receive);
	}
}
