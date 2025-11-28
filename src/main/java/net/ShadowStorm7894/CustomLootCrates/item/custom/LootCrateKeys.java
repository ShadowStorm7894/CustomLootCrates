package net.ShadowStorm7894.CustomLootCrates.item.custom;

import net.ShadowStorm7894.CustomLootCrates.item.ModCrateItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;


public class LootCrateKeys extends Item {
    public LootCrateKeys(Properties pProperties) {
        super(pProperties);
    }
    public final int cooldown = 20;

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Player player = pContext.getPlayer();
        ItemStack itemstack = pContext.getItemInHand();
        List<TagKey<Item>> itemTags = itemstack.getTags().toList();

        boolean correctcrate = false;
        assert player != null;
        player.getCooldowns().addCooldown(this, cooldown);

        //are we on serverside
        if (!pContext.getLevel().isClientSide()) {
            BlockPos blockPos = pContext.getClickedPos();
            BlockState state = pContext.getLevel().getBlockState(blockPos);
            ResourceLocation id = ForgeRegistries.BLOCKS.getKey(state.getBlock());
            player.sendSystemMessage(Component.literal("1"));
            for(RegistryObject<Item> crateItem : ModCrateItems.CRATE_ITEMS.getEntries()){
                if(id.toString().equals(ForgeRegistries.ITEMS.getKey(crateItem.get()).toString())){
                    player.sendSystemMessage(Component.literal("2"));

                    List<TagKey<Item>> crateTags = getCrateItemTags(pContext.getLevel(), crateItem.get());
                    player.sendSystemMessage(Component.literal("3"));

                    for(TagKey<Item> tag : crateTags) {
                        if(itemTags.contains(tag)){
                            correctcrate = true;
                            player.sendSystemMessage(Component.translatable("That's the right crate!"));
                        }
                    }
                    if(!correctcrate){
                        player.sendSystemMessage(Component.translatable("That's the wrong crate!"));
                    }
                }
            }
        }
        if (!player.getAbilities().instabuild) {
            itemstack.shrink(1);
        }
        return InteractionResult.SUCCESS;
    }
    public static List<TagKey<Item>> getCrateItemTags(Level level, Item item) {
        // Dynamic registry lookup (non-deprecated)
        HolderLookup<Item> lookup = level.registryAccess().lookupOrThrow(Registries.ITEM);

        // Item ID (ResourceLocation)
        ResourceLocation key = ForgeRegistries.ITEMS.getKey(item);

        // MUST wrap it as a ResourceKey<Item>
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, key);

        // Correct: get the dynamic holder from the lookup
        Holder<Item> holder = lookup.getOrThrow(itemKey);

        // Return tag keys
        return holder.tags().toList();
    }
}


