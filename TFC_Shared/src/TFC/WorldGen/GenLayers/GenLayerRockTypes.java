package TFC.WorldGen.GenLayers;

import TFC.WorldGen.DataLayer;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.GenLayer;
import net.minecraft.src.IntCache;
import net.minecraft.src.WorldType;

public class GenLayerRockTypes extends GenLayerTFC
{
	public static DataLayer[] biomeArray1 = new DataLayer[] {DataLayer.Granite, DataLayer.Diorite, DataLayer.Gabbro, 
		DataLayer.Siltstone, DataLayer.Mudstone, DataLayer.Shale, DataLayer.Claystone, DataLayer.RockSalt, DataLayer.Limestone, DataLayer.Conglomerate, DataLayer.Dolomite, DataLayer.Chert, DataLayer.Chalk,
		DataLayer.Rhyolite,DataLayer.Basalt,DataLayer.Andesite,DataLayer.Dacite,
		DataLayer.Quartzite,DataLayer.Slate,DataLayer.Phyllite,DataLayer.Schist,DataLayer.Gneiss,DataLayer.Marble};
	public static DataLayer[] biomeArray2 = new DataLayer[] {DataLayer.Granite, DataLayer.Diorite, DataLayer.Gabbro, 
		DataLayer.Rhyolite,DataLayer.Basalt,DataLayer.Andesite,DataLayer.Dacite,
		DataLayer.Quartzite,DataLayer.Slate,DataLayer.Phyllite,DataLayer.Schist,DataLayer.Gneiss,DataLayer.Marble};
	public static DataLayer[] biomeArray3 = new DataLayer[] {DataLayer.Granite, DataLayer.Diorite, DataLayer.Gabbro, 
		DataLayer.Quartzite,DataLayer.Slate,DataLayer.Phyllite,DataLayer.Schist,DataLayer.Gneiss,DataLayer.Marble};

	/** this sets all the biomes that are allowed to appear in the overworld */
	private DataLayer[] allowedBiomes;

	public GenLayerRockTypes(long par1, GenLayer par3GenLayer, WorldType par4WorldType, int layer)
	{
		super(par1);
		if(layer == 1)
			this.allowedBiomes = biomeArray1;
		else if(layer == 2)
			this.allowedBiomes = biomeArray2;
		else if(layer == 3)
			this.allowedBiomes = biomeArray3;
		
		this.parent = (GenLayerTFC) par3GenLayer;
	}

	/**
	 * Returns a list of integer values generated by this layer. These may be interpreted as temperatures, rainfall
	 * amounts, or biomeList[] indices based on the particular GenLayer subclass.
	 */
	public int[] getInts(int par1, int par2, int par3, int par4)
	{
		int[] var5 = this.parent.getInts(par1, par2, par3, par4);
		int[] var6 = IntCache.getIntCache(par3 * par4);

		for (int var7 = 0; var7 < par4; ++var7)
		{
			for (int var8 = 0; var8 < par3; ++var8)
			{
				this.initChunkSeed((long)(var8 + par1), (long)(var7 + par2));
				int var9 = var5[var8 + var7 * par3];

				if (var9 == 0)
				{
					var6[var8 + var7 * par3] = 0;
				}
				else if (var9 == 1)
				{
					var6[var8 + var7 * par3] = this.allowedBiomes[this.nextInt(this.allowedBiomes.length)].ID;
				}
				else
				{
					var6[var8 + var7 * par3] = DataLayer.Granite.ID;
				}
			}
		}

		return var6;
	}
}
