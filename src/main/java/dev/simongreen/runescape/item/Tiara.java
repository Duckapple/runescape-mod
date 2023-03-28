package dev.simongreen.runescape.item;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class Tiara extends ArmorItem {
    public static final String RUNE_TYPE_KEY = "altar";

    public Tiara(EquipmentSlot slot, Settings settings) {
        super(new TiaraMaterial(), slot, settings);
    }

    public static class TiaraMaterial implements ArmorMaterial {

        @Override
        public int getDurability(EquipmentSlot slot) {
            return 77;
        }

        @Override
        public int getProtectionAmount(EquipmentSlot slot) {
            return 2;
        }

        @Override
        public int getEnchantability() {
            return 9;
        }

        @Override
        public SoundEvent getEquipSound() {
            return SoundEvents.ITEM_ARMOR_EQUIP_IRON;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.ofItems(Items.IRON_INGOT);
        }

        @Override
        public String getName() {
            return "tiara";
        }

        @Override
        public float getToughness() {
            return 0;
        }

        @Override
        public float getKnockbackResistance() {
            return 0;
        }
    }
}
