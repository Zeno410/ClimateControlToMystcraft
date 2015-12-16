
package cc2mc;
import com.xcompwiz.mystcraft.api.MystObjects;
import com.xcompwiz.mystcraft.item.ItemPage;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.ShapedOreRecipe;
    @Mod(modid = "cc2mc", name = "ClimateControlToMystcraft", version = "0.1",
    dependencies = "required-after:Mystcraft;required-after:climatecontrol")

/**
 *
 * @author Zeno410
 */
public class ClimateControlToMystcraftMod {
    /*@SidedProxy(clientSide = "cc2mc.ClientProxy",
                serverSide = "cc2mc.CommonProxy")*/
    public ModifierInstaller installer;

    @EventHandler
    public void load(FMLInitializationEvent event){
        /* Registration needs to be done in the load phase of the mod. Before ClimateControl pre-inits,
         * there's no registry; after it post-initializes configs might not be set up properly
         * With proper mod load timing you can do it in the other phases but why risk it?
         */
        try {
            installer = new ModifierInstallerFor10();
            installer.install();
        } catch (java.lang.NoClassDefFoundError e){
            try {
                installer = new ModifierInstallerFor11();
                installer.install();
            } catch (java.lang.NoClassDefFoundError e2) {
                try {
                    installer = new ModifierInstallerFor12();
                    installer.install();
                } catch (java.lang.NoClassDefFoundError e3) {
                    throw e3;
                }
            }
        }

    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event){
        addRecipes();
    }

    private void addRecipe(String name, ItemStack basePage, ItemStack endStone, int dyeColor) {
        ItemStack productPage = pageStack(name);
        GameRegistry.addShapelessRecipe(productPage, basePage,endStone,new ItemStack(Items.dye,1,dyeColor));
    }

    private void addRecipe(String name, ItemStack basePage, ItemStack endStone, Block block) {
        ItemStack productPage = pageStack(name);
        GameRegistry.addShapelessRecipe(productPage, basePage,endStone,new ItemStack(block));
    }

    private void addRecipe(String name, ItemStack basePage, ItemStack endStone, Block block, Block block2) {
        ItemStack productPage = pageStack(name);
        GameRegistry.addShapelessRecipe(productPage, basePage,endStone,new ItemStack(block),new ItemStack(block2));
    }

    private void addRecipe(String name, ItemStack basePage, Block block, Block block2) {
        ItemStack productPage = pageStack(name);
        GameRegistry.addShapelessRecipe(productPage, basePage,new ItemStack(block),new ItemStack(block2));
    }

    private void addRecipe(String name, ItemStack basePage, ItemStack endStone, Block block, Item item) {
        ItemStack productPage = pageStack(name);
        GameRegistry.addShapelessRecipe(productPage, basePage,endStone,new ItemStack(block),new ItemStack(item));
    }

    private void addRecipe(String name, ItemStack basePage, ItemStack endStone, Block block, int dyeColor ) {
        ItemStack productPage = pageStack(name);
        GameRegistry.addShapelessRecipe(productPage, basePage,endStone,new ItemStack(block),new ItemStack(Items.dye,1,dyeColor));
    }

    private void addRecipe(String name, ItemStack basePage, ItemStack endStone, ItemStack item) {
        ItemStack productPage = pageStack(name);
        GameRegistry.addShapelessRecipe(productPage, basePage,endStone,item);
    }

    private void addRecipe(String name, ItemStack basePage, ItemStack endStone, int dyeColor, int dyeColor2) {
        ItemStack productPage = pageStack(name);
        GameRegistry.addShapelessRecipe(productPage, basePage,endStone,new ItemStack(Items.dye,1,dyeColor),new ItemStack(Items.dye,1,dyeColor2));
    }

    public ItemStack pageStack(String name) {
        ItemStack itemstack = null;
        try {
              itemstack  = PageSourceV10.page();
        } catch (java.lang.NoSuchFieldError e) {
            itemstack = PageSourceV12.page();
        }
        itemstack.stackTagCompound = new NBTTagCompound();
        itemstack.stackTagCompound.setString("symbol", name);
        return itemstack;
    }

    public void addRecipes() {
        ItemStack itemstack = pageStack("ClimateControl");
        ItemStack netherRack = new ItemStack(Blocks.netherrack);
        IRecipe recipe = new ShapedOreRecipe(itemstack, "XXX", "X  ", "XXX", 'X',netherRack);
        CraftingManager.getInstance().getRecipeList().add(recipe);

        ItemStack noPage = pageStack("Not");
        GameRegistry.addShapelessRecipe(noPage, itemstack, new ItemStack(Items.dye,1,0));
        ItemStack decreasePage = pageStack("Decrease");
        GameRegistry.addShapelessRecipe(decreasePage, itemstack, new ItemStack(Items.dye,1,8));
        ItemStack endStone = new ItemStack(Blocks.end_stone);
        addRecipe("ComplexSubBiomes",itemstack,endStone,2,3);
        addRecipe("BiomeFrequency",itemstack,Blocks.dirt,Blocks.leaves);
        addRecipe("LargeClimates",itemstack,endStone,11,15);
        addRecipe("MediumClimates",itemstack,endStone,11,8);
        addRecipe("SmallClimates",itemstack,endStone,11,7);
        addRecipe("NoClimates",itemstack,endStone,11,0);
        addRecipe("RiverChasms",itemstack,endStone,Blocks.stone,4);
        addRecipe("BiomeSize",itemstack,endStone,Blocks.leaves);
        addRecipe("RiverFrequency",itemstack,endStone,4);
        addRecipe("Hot",itemstack,endStone,Blocks.sand);
        addRecipe("Warm",itemstack,endStone,new ItemStack(Blocks.log,1,2));
        addRecipe("Cool",itemstack,endStone,new ItemStack(Blocks.log,1,1));
        addRecipe("Snowy",itemstack,endStone,Blocks.snow);
        addRecipe("Mild",itemstack,endStone,new ItemStack(Blocks.log,1,0));
        addRecipe("Land",itemstack,endStone,Blocks.dirt);
        addRecipe("Island",itemstack,endStone,Blocks.dirt,8);
        addRecipe("Continent",itemstack,endStone,Blocks.dirt,7);
        addRecipe("Forested",itemstack,endStone,Blocks.log,Blocks.log);
        addRecipe("Hilly",itemstack,endStone,Blocks.dirt,Blocks.stone);
        addRecipe("Mountainous",itemstack,endStone,Blocks.stone,Blocks.stone);
        addRecipe("Level",itemstack,endStone,Blocks.dirt,Blocks.dirt);
        addRecipe("Swampy",itemstack,endStone,Blocks.dirt,Blocks.vine);
        addRecipe("Arid",itemstack,endStone,Blocks.dirt,Blocks.sand);
        addRecipe("Rainy",itemstack,endStone,Blocks.dirt,Blocks.leaves);
        addRecipe("Wasted",itemstack,endStone,Blocks.dirt,Items.stick);
        addRecipe("Magical",itemstack,endStone,Blocks.dirt,Items.diamond);

    }
}
