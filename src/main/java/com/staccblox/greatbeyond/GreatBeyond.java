package com.staccblox.greatbeyond;

import com.staccblox.greatbeyond.block.ModBlocks;
import com.staccblox.greatbeyond.component.ModDataComponents;
import com.staccblox.greatbeyond.effect.ModEffects;
import com.staccblox.greatbeyond.enchantment.ModEnchantmentEffects;
import com.staccblox.greatbeyond.entity.ModEntities;
import com.staccblox.greatbeyond.entity.client.ChairRenderer;
import com.staccblox.greatbeyond.entity.client.GeckoRenderer;
import com.staccblox.greatbeyond.entity.client.TomahawkProjectileRenderer;
import com.staccblox.greatbeyond.item.ModCreativeModeTabs;
import com.staccblox.greatbeyond.item.ModItems;
import com.staccblox.greatbeyond.loot.ModLootModifiers;
import com.staccblox.greatbeyond.particle.BismuthParticles;
import com.staccblox.greatbeyond.particle.ModParticles;
import com.staccblox.greatbeyond.potion.ModPotions;
import com.staccblox.greatbeyond.sound.ModSounds;
import com.staccblox.greatbeyond.util.ModItemProperties;
import com.staccblox.greatbeyond.villager.ModVillagers;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(GreatBeyond.MOD_ID)
public class GreatBeyond {
    public static final String MOD_ID = "greatbeyond";
    private static final Logger LOGGER = LogUtils.getLogger();

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public GreatBeyond(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);

        NeoForge.EVENT_BUS.register(this);

        ModCreativeModeTabs.register(modEventBus);

        modEventBus.addListener(this::addCreative);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.BISMUTH);
            event.accept(ModItems.RAW_BISMUTH);
        }

        if(event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ModBlocks.BISMUTH_BLOCK);
            event.accept(ModBlocks.BISMUTH_ORE);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            ModItemProperties.addCustomItemProperties();

            EntityRenderers.register(ModEntities.GECKO.get(), GeckoRenderer::new);
            EntityRenderers.register(ModEntities.TOMAHAWK.get(), TomahawkProjectileRenderer::new);

            EntityRenderers.register(ModEntities.CHAIR_ENTITY.get(), ChairRenderer::new);
        }

        @SubscribeEvent
        public static void registerParticleFactories(RegisterParticleProvidersEvent event) {
            event.registerSpriteSet(ModParticles.BISMUTH_PARTICLES.get(), BismuthParticles.Provider::new);
        }
    }
}
