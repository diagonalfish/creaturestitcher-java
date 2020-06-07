/*
 * Created on Jan 4, 2006
 * 	by the wonderful Eclipse(c)
 */
package rebound.jagent.lib.c16;

import java.awt.color.*;
import java.awt.image.*;
import java.io.*;
import rebound.bits.*;
import rebound.jagent.lib.*;

public class FromC16Converter
{
	protected String c16File;
	
	protected boolean bits565;
	protected BufferedImage[] sprites;
	protected FromC16Notifee notifee;
	
	/**
	 * Array indexing:<br>
	 * alphaMaps[spriteIndex][row(y)][column(x)]<br>
	 * <br>
	 * Elements test if pixels are opaque, id est:<br>
	 * <ul>
	 * 	<li><code>true</code> - Transparent</li>
	 * 	<li><code>false</code> - Opaque</li>
	 * </ul>
	 */
	protected boolean[][][] alphaMaps;
	
	public FromC16Converter(String c16File)
	{
		this.c16File = c16File;
	}
	
	public void read() throws IOException
	{
		RandomAccessFile file = new RandomAccessFile(c16File, "r");
		DataMap map = new DataMap();
		if (notifee != null) notifee.startC16Reading();
		readHeaders(file, map);
		readImageDatii(file, map);
		if (notifee != null) notifee.finC16Reading();
	}
	
	//Internals of read()
	protected void readHeaders(RandomAccessFile file, DataMap map) throws IOException
	{
		int flags = Bytes.getLittleInt(file);
		bits565 = (flags & 1) != 0;
		
		int count = Bytes.getLittleShort(file);
		map.offsets = new int[count][];
		sprites = new BufferedImage[count];
		
		for (int i = 0; i < count; i++)
			readHeader(file, map, i);
	}
	
	protected void readHeader(RandomAccessFile file, DataMap map, int index) throws IOException
	{
		int line0 = Bytes.getLittleInt(file);
		int width = Bytes.getLittleShort(file);
		int height = Bytes.getLittleShort(file);
		
		sprites[index] = new BufferedImage(width, height, bits565 ? BufferedImage.TYPE_USHORT_565_RGB : BufferedImage.TYPE_USHORT_555_RGB);
		map.offsets[index] = new int[height];
		map.offsets[index][0] = line0;
		
		for (int o = 1; o < height; o++)
		{
			map.offsets[index][o] = Bytes.getLittleInt(file);
		}
	}
	
	protected void readImageDatii(RandomAccessFile file, DataMap map) throws IOException
	{
		alphaMaps = new boolean[sprites.length][][];
		for (int i = 0; i < sprites.length; i++)
		{
			readImageData(file, map, i);
			if (notifee != null) notifee.finC16ReadingFrame(i, sprites.length);
		}
	}
	
	protected void readImageData(RandomAccessFile file, DataMap map, int index) throws IOException
	{
		BufferedImage img = sprites[index];
		alphaMaps[index] = new boolean[img.getHeight()][img.getWidth()];
		Cursor x = new Cursor();
		
		//Bugfix cache var
		long flen = new File(getC16File()).length();
		
		for (int row = 0;  row < img.getHeight();  row++)
		{
			file.seek(map.offsets[index][row]);
			
			x.seek(0);
			while (x.getPos() < img.getWidth())
			{
				//Hang-up bugfix
				if (file.getFilePointer() >= flen)
					throw new IOException("Past EOF (Corrupted File)");
				
				readRun(file, index, row, x);
			}
		}
	}
	
	protected void readRun(RandomAccessFile file, int index, int y, Cursor x) throws IOException
	{
		short tag = Bytes.getLittleShort(file);
		boolean transparent = (tag & 1) == 0;
		int len = tag;
		
		//Unset the flag bit
		len = ~len;
		len |= 1;
		len = ~len;
		
		//Shift
		len >>= 1;
		
		if (transparent)
			//Record Alpha
			for (int i = 0; i < len; i++)
				alphaMaps[index][y][x.iteratePos()] = true;
		else
		{
			short data = 0;
			for (int relx = 0; relx < len; relx++)
			{
				data = Bytes.getLittleShort(file);
				setPixel(data, index, x.iteratePos(), y);
			}
		}
	}
	
	/*
	 * 1			2		3		4		5
	 * 1			2		4		8		16
	 * 
	 * 6			7		8		9		10
	 * 32		64		128		256		512
	 * 
	 * 11		12		13		14		15
	 * 1024		2048		4096		8192		16384
	 * 
	 * 16
	 * 32768
	 */
	
	public static final int
	BFLAGS = 1|2|4|8|16,
	GFLAGS_555 = 32|64|128|256|512,
	GFLAGS_565 = 32|64|128|256|512|1024,
	RFLAGS_555 = 1024|2048|4096|8192|16384,
	RFLAGS_565 = 2048|4096|8192|16384|32768;
	
	protected void setPixel(short data, int index, int x, int y)
	{
		BufferedImage img = sprites[index];
		if (x < img.getWidth() && y < img.getHeight())
		{
			int r = data;
			int g = data;
			int b = data;
			
			r &= bits565 ? RFLAGS_565 : RFLAGS_555;
			
			g &= bits565 ? GFLAGS_565 : GFLAGS_555;
			
			b &= BFLAGS;
			
			r >>= bits565 ? 11: 10;
			
			g >>= 5;
			
			
			int[] sample = new int[]{r, g, b};
			img.getRaster().setPixel(x, y, sample);
			alphaMaps[index][y][x] = false;
		}
	}
	
	public BufferedImage[] convertFrames(int newTYPE)
	{
		BufferedImage[] imgs = new BufferedImage[sprites.length];
		if (notifee != null) notifee.startC16PostConverting();
		for (int i = 0; i < sprites.length; i++)
		{
			imgs[i] = convertFrame(i, newTYPE);
			if (notifee != null) notifee.finC16PostConvertingFrame(i, sprites.length);
		}
		if (notifee != null) notifee.finC16PostConverting();
		return imgs;
	}
	
	protected BufferedImage convertFrame(int index, int newTYPE)
	{
		ColorConvertOp converter = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_sRGB), null);
		BufferedImage src = sprites[index];
		BufferedImage dest = new BufferedImage(src.getWidth(), src.getHeight(), newTYPE);
		converter.filter(src, dest);
		
		//Alphasetting
		if (dest.getColorModel().hasAlpha())
		{
			int x = 0, y = 0;
			int[] sample = new int[4];
			WritableRaster raster = dest.getRaster();
			while (y < dest.getHeight())
			{
				x = 0;
				while (x < dest.getWidth())
				{
					raster.getPixel(x, y, sample);
					raster.setPixel(x, y, new int[]{sample[0], sample[1], sample[2], alphaMaps[index][y][x] ? 0 : 255});
					raster.getPixel(x, y, sample);
					x++;
				}
				y++;
			}
		}
		return dest;
	}
	
	public FromC16Notifee getNotifee()
	{
		return this.notifee;
	}
	
	public void setNotifee(FromC16Notifee notifee)
	{
		this.notifee = notifee;
	}
	
	public boolean isBits565()
	{
		return this.bits565;
	}
	
	public String getC16File()
	{
		return this.c16File;
	}
	
	public BufferedImage[] getSprites()
	{		return this.sprites;
	}
	
	public void setC16File(String file)
	{
		this.c16File = file;
	}
}
