package com.benbenlaw.infinity.multiblock;

import com.benbenlaw.infinity.block.ModBlocks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.Tags;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ForgeRegistries;
import org.mangorage.mangomultiblock.core.SimpleMultiBlockPattern;

public class MultiBlockManagers {

    public static final MultiBlockManagerBeta<Generators> POWER_MULTIBLOCKS = new MultiBlockManagerBeta<>();
    
    static {

        // Furnace Generator
        POWER_MULTIBLOCKS.register("infinity:furnace_generator",
                Generators.FURNACE,
                SimpleMultiBlockPattern.AisleBuilder.start()
                        .aisle("W0W", "0*0", "W0W")
                        .where('0', a -> a.getState().is(Blocks.FURNACE) && a.getState().getValue(BlockStateProperties.LIT))
                        .where('W', a -> a.getState().is(BlockTags.WALLS))
                        .where('*', a -> a.getState().is(ModBlocks.INFINITY_GENERATOR.get()))
                        .build()
        );

        // Lava Generator
        POWER_MULTIBLOCKS.register("infinity:lava_generator",
                Generators.LAVA,
                SimpleMultiBlockPattern.AisleBuilder.start()
                        .aisle("LLL", "L*L", "LLL")
                        .where('L', a -> a.getState().getFluidState().is(Fluids.LAVA))
                        .where('*', a -> a.getState().is(ModBlocks.INFINITY_GENERATOR.get()))
                        .build()
        );

        // Steam Generator
        POWER_MULTIBLOCKS.register("infinity:steam_generator",
                Generators.STEAM,
                SimpleMultiBlockPattern.AisleBuilder.start()
                        .aisle("   ", " * ", "   ")
                        .aisle("SSS", "S S", "SSS")
                        .aisle("   ", "   ", "   ")
                        .aisle("WWW", "WWW", "WWW")
                        .aisle("GGG", "GGG", "GGG")
                        .aisle("CCC", "CCC", "CCC")
                        .where('S', a -> a.getState().is(Tags.Blocks.STONE))
                        .where('G', a -> a.getState().is(Tags.Blocks.GLASS))
                        .where('W', a -> a.getState().getFluidState().is(Fluids.WATER))
                        .where('C', a -> a.getState().is(Blocks.CAMPFIRE) && a.getState().getValue(BlockStateProperties.LIT))
                        .where(' ', a -> !a.getState().is(ModBlocks.INFINITY_GENERATOR.get()))
                        .where('*', a -> a.getState().is(ModBlocks.INFINITY_GENERATOR.get()))
                        .build()
        );

        // Gold Generator
        POWER_MULTIBLOCKS.register("infinity:gold_generator",
                Generators.GOLD,
                SimpleMultiBlockPattern.AisleBuilder.start()
                        .aisle("         ", "    G    ", "         ")
                        .aisle("    G    ", "   GGG   ", "    G    ")
                        .aisle("         ", "    G    ", "         ")
                        .aisle("         ", " G     G ", "         ")
                        .aisle(" G     G ", "GGG * GGG", " G     G ")
                        .aisle("         ", " G     G ", "         ")
                        .aisle("         ", "    G    ", "         ")
                        .aisle("    G    ", "   GGG   ", "    G    ")
                        .aisle("         ", "    G    ", "         ")
                        .where('G', a -> a.getState().is(Blocks.GOLD_BLOCK))
                        .where(' ', a -> !a.getState().is(ModBlocks.INFINITY_GENERATOR.get()))
                        .where('*', a -> a.getState().is(ModBlocks.INFINITY_GENERATOR.get()))
                        .build()
        );

        // Blood Magic
        if (ModList.get().isLoaded("bloodmagic")) {
            System.out.println("Bloodmagic is loaded");
            Block rune = ForgeRegistries.BLOCKS.getValue(new ResourceLocation("bloodmagic:blankrune"));
            POWER_MULTIBLOCKS.register("infinity:blood_magic_generator",
                    Generators.BLOOD_MAGIC_ALTAR,
                    SimpleMultiBlockPattern.AisleBuilder.start()
                            .aisle("R     R", "       ", "       ", "       ", "       ", "       ", "R     R")
                            .aisle("B     B", "       ", "       ", "   *   ", "       ", "       ", "B     B")
                            .aisle("B     B", "       ", "  UUU  ", "  U U  ", "  UUU  ", "       ", "B     B")
                            .aisle(" UUUUU ", "U     U", "U     U", "U     U", "U     U", "U     U", " UUUUU ")
                            .where('U', a ->  a.getState().is(rune))
                            .where('*', a -> a.getState().is(ModBlocks.INFINITY_GENERATOR.get()))
                            .where('R', a -> a.getState().is(Blocks.REDSTONE_BLOCK))
                            .where(' ', a -> !a.getState().is(ModBlocks.INFINITY_GENERATOR.get()))
                            .where('B', a -> a.getState().is(BlockTags.STONE_BRICKS))
                            .build()
            );
        }

        // Stoneopolis Modpack (Remove from 1.21+)
        Block rainbowBricks = ForgeRegistries.BLOCKS.getValue(new ResourceLocation("stoneopolis:rainbow_bricks"));
        if (ModList.get().isLoaded("kubejs") && rainbowBricks != null) {
            POWER_MULTIBLOCKS.register("infinity:stoneopolis_generator",
                    Generators.STONEOPOLIS,
                    SimpleMultiBlockPattern.AisleBuilder.start()
                            .aisle("       ", " RRRRR ", " R   R ", " R   R ", " R   R ", " RRRRR ", "       ")
                            .aisle(" RRRRR ", "R     R", "R     R", "R     R", "R     R", "R     R", " RRRRR ")
                            .aisle(" R   R ", "R     R", "       ", "       ", "       ", "R     R", " R   R ")
                            .aisle(" R   R ", "R     R", "       ", "   *   ", "       ", "R     R", " R   R ")
                            .aisle(" R   R ", "R     R", "       ", "       ", "       ", "R     R", " R   R ")
                            .aisle(" RRRRR ", "R     R", "R     R", "R     R", "R     R", "R     R", " RRRRR ")
                            .aisle("       ", " RRRRR ", " R   R ", " R   R ", " R   R ", " RRRRR ", "       ")
                            .where('R', a -> a.getState().is(rainbowBricks))
                            .where(' ', a -> !a.getState().is(ModBlocks.INFINITY_GENERATOR.get()))
                            .where('*', a -> a.getState().is(ModBlocks.INFINITY_GENERATOR.get()))
                            .build()
            );
        }
    }

}
