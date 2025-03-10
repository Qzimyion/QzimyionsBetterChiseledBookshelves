package com.qzimyion;

import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QzimyionsBetterChiseledBookshelves implements ModInitializer {

	public static final String MOD_ID = "qzimyions-better-chiseled-bookshelves";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static Identifier id(String... path) {
		return Identifier.of(MOD_ID, String.join("/", path));
	}

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");
	}
}