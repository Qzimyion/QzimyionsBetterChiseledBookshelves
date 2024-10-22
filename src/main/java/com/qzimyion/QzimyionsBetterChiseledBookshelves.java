package com.qzimyion;

import com.qzimyion.client.QCBItemTexturesUtil;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.registry.DynamicRegistries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QzimyionsBetterChiseledBookshelves implements ModInitializer {

	public static final String MOD_ID = "qzimyions-better-chiseled-bookshelves";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final RegistryKey<Registry<QCBItemTexturesUtil>> PIZZA_TOPPING_VARIANT = RegistryKey.ofRegistry(Identifier.of(MOD_ID, "item_texture_variant"));


	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");
		QCBDataComponents.init();
		DynamicRegistries.register(PIZZA_TOPPING_VARIANT, QCBItemTexturesUtil.CODEC);
	}
}