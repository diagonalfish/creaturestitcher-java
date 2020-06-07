/*
 * Created on Sep 14, 2004
 */

package rebound.bits;

import java.io.*;


/**
 * Utility methods for packing/unpacking, getting/putting, reading/relaying primitive values in/out of byte arrays.
 */
public class Bytes
{
	/*
	 * Methods for unpacking primitive values from byte arrays starting at
	 * given offsets.
	 */
	public static boolean getBoolean(byte[] b, int off)
	{
		return b[off] != 0;
	}
	
	
	
	public static char getBigChar(byte[] b, int off)
	{
		return (char) (((b[off + 1] & 0xFF) << 0) + 
			((b[off + 0] & 0xFF) << 8));
	}
	
	public static short getBigShort(byte[] b, int off)
	{
		return (short) (((b[off + 1] & 0xFF) << 0) + 
			((b[off + 0] & 0xFF) << 8));
	}
	
	public static int getBigInt(byte[] b, int off)
	{
		return ((b[off + 3] & 0xFF) << 0) +
		((b[off + 2] & 0xFF) << 8) +
		((b[off + 1] & 0xFF) << 16) +
		((b[off + 0] & 0xFF) << 24);
	}
	
	public static float getBigFloat(byte[] b, int off)
	{
		int i = ((b[off + 3] & 0xFF) << 0) +
		((b[off + 2] & 0xFF) << 8) +
		((b[off + 1] & 0xFF) << 16) +
		((b[off + 0] & 0xFF) << 24);
		return Float.intBitsToFloat(i);
	}
	
	public static long getBigLong(byte[] b, int off)
	{
		return ((b[off + 7] & 0xFFL) << 0) +
		((b[off + 6] & 0xFFL) << 8) +
		((b[off + 5] & 0xFFL) << 16) +
		((b[off + 4] & 0xFFL) << 24) +
		((b[off + 3] & 0xFFL) << 32) +
		((b[off + 2] & 0xFFL) << 40) +
		((b[off + 1] & 0xFFL) << 48) +
		((b[off + 0] & 0xFFL) << 56);
	}
	
	public static double getBigDouble(byte[] b, int off)
	{
		long j = ((b[off + 7] & 0xFFL) << 0) +
		((b[off + 6] & 0xFFL) << 8) +
		((b[off + 5] & 0xFFL) << 16) +
		((b[off + 4] & 0xFFL) << 24) +
		((b[off + 3] & 0xFFL) << 32) +
		((b[off + 2] & 0xFFL) << 40) +
		((b[off + 1] & 0xFFL) << 48) +
		((b[off + 0] & 0xFFL) << 56);
		return Double.longBitsToDouble(j);
	}
	
	
	
	public static char getLittleChar(byte[] b, int off)
	{
		return (char) (((b[off + 0] & 0xFF) << 0) + 
			((b[off + 1] & 0xFF) << 8));
	}
	
	public static short getLittleShort(byte[] b, int off)
	{
		return (short) (((b[off + 0] & 0xFF) << 0) + 
			((b[off + 1] & 0xFF) << 8));
	}
	
	public static int getLittleInt(byte[] b, int off)
	{
		return ((b[off + 0] & 0xFF) << 0) +
		((b[off + 1] & 0xFF) << 8) +
		((b[off + 2] & 0xFF) << 16) +
		((b[off + 3] & 0xFF) << 24);
	}
	
	public static float getLittleFloat(byte[] b, int off)
	{
		int i = ((b[off + 0] & 0xFF) << 0) +
		((b[off + 1] & 0xFF) << 8) +
		((b[off + 2] & 0xFF) << 16) +
		((b[off + 3] & 0xFF) << 24);
		return Float.intBitsToFloat(i);
	}
	
	public static long getLittleLong(byte[] b, int off)
	{
		return ((b[off + 0] & 0xFFL) << 0) +
		((b[off + 1] & 0xFFL) << 8) +
		((b[off + 2] & 0xFFL) << 16) +
		((b[off + 3] & 0xFFL) << 24) +
		((b[off + 4] & 0xFFL) << 32) +
		((b[off + 5] & 0xFFL) << 40) +
		((b[off + 6] & 0xFFL) << 48) +
		((b[off + 7] & 0xFFL) << 56);
	}
	
	public static double getLittleDouble(byte[] b, int off)
	{
		long j = ((b[off + 0] & 0xFFL) << 0) +
		((b[off + 1] & 0xFFL) << 8) +
		((b[off + 2] & 0xFFL) << 16) +
		((b[off + 3] & 0xFFL) << 24) +
		((b[off + 4] & 0xFFL) << 32) +
		((b[off + 5] & 0xFFL) << 40) +
		((b[off + 6] & 0xFFL) << 48) +
		((b[off + 7] & 0xFFL) << 56);
		return Double.longBitsToDouble(j);
	}
	
	
	
	
	
	
	/*
	 * Methods for packing primitive values into byte arrays starting at given
	 * offsets.
	 */
	public static void putBoolean(byte[] b, int off, boolean val)
	{
		b[off] = (byte) (val ? 1 : 0);
	}
	
	public static void putBig(byte[] b, int off, boolean val)
	{
		putBoolean(b, off, val);
	}
	
	public static void putLittle(byte[] b, int off, boolean val)
	{
		putBoolean(b, off, val);
	}
	
	
	public static void putBig(byte[] b, int off, char val)
	{
		b[off + 1] = (byte) (val >>> 0);
		b[off + 0] = (byte) (val >>> 8);
	}
	
	public static void putBig(byte[] b, int off, short val)
	{
		b[off + 1] = (byte) (val >>> 0);
		b[off + 0] = (byte) (val >>> 8);
	}
	
	public static void putBig(byte[] b, int off, int val)
	{
		b[off + 3] = (byte) (val >>> 0);
		b[off + 2] = (byte) (val >>> 8);
		b[off + 1] = (byte) (val >>> 16);
		b[off + 0] = (byte) (val >>> 24);
	}
	
	public static void putBig(byte[] b, int off, float val)
	{
		int i = Float.floatToIntBits(val);
		b[off + 3] = (byte) (i >>> 0);
		b[off + 2] = (byte) (i >>> 8);
		b[off + 1] = (byte) (i >>> 16);
		b[off + 0] = (byte) (i >>> 24);
	}
	
	public static void putBig(byte[] b, int off, long val)
	{
		b[off + 7] = (byte) (val >>> 0);
		b[off + 6] = (byte) (val >>> 8);
		b[off + 5] = (byte) (val >>> 16);
		b[off + 4] = (byte) (val >>> 24);
		b[off + 3] = (byte) (val >>> 32);
		b[off + 2] = (byte) (val >>> 40);
		b[off + 1] = (byte) (val >>> 48);
		b[off + 0] = (byte) (val >>> 56);
	}
	
	public static void putBig(byte[] b, int off, double val)
	{
		long j = Double.doubleToLongBits(val);
		b[off + 7] = (byte) (j >>> 0);
		b[off + 6] = (byte) (j >>> 8);
		b[off + 5] = (byte) (j >>> 16);
		b[off + 4] = (byte) (j >>> 24);
		b[off + 3] = (byte) (j >>> 32);
		b[off + 2] = (byte) (j >>> 40);
		b[off + 1] = (byte) (j >>> 48);
		b[off + 0] = (byte) (j >>> 56);
	}
	
	
	
	public static void putLittle(byte[] b, int off, char val)
	{
		b[off + 0] = (byte) (val >>> 0);
		b[off + 1] = (byte) (val >>> 8);
	}
	
	public static void putLittle(byte[] b, int off, short val)
	{
		b[off + 0] = (byte) (val >>> 0);
		b[off + 1] = (byte) (val >>> 8);
	}
	
	public static void putLittle(byte[] b, int off, int val)
	{
		b[off + 0] = (byte) (val >>> 0);
		b[off + 1] = (byte) (val >>> 8);
		b[off + 2] = (byte) (val >>> 16);
		b[off + 3] = (byte) (val >>> 24);
	}
	
	public static void putLittle(byte[] b, int off, float val)
	{
		int i = Float.floatToIntBits(val);
		b[off + 0] = (byte) (i >>> 0);
		b[off + 1] = (byte) (i >>> 8);
		b[off + 2] = (byte) (i >>> 16);
		b[off + 3] = (byte) (i >>> 24);
	}
	
	public static void putLittle(byte[] b, int off, long val)
	{
		b[off + 0] = (byte) (val >>> 0);
		b[off + 1] = (byte) (val >>> 8);
		b[off + 2] = (byte) (val >>> 16);
		b[off + 3] = (byte) (val >>> 24);
		b[off + 4] = (byte) (val >>> 32);
		b[off + 5] = (byte) (val >>> 40);
		b[off + 6] = (byte) (val >>> 48);
		b[off + 7] = (byte) (val >>> 56);
	}
	
	public static void putLittle(byte[] b, int off, double val)
	{
		long j = Double.doubleToLongBits(val);
		b[off + 0] = (byte) (j >>> 0);
		b[off + 1] = (byte) (j >>> 8);
		b[off + 2] = (byte) (j >>> 16);
		b[off + 3] = (byte) (j >>> 24);
		b[off + 4] = (byte) (j >>> 32);
		b[off + 5] = (byte) (j >>> 40);
		b[off + 6] = (byte) (j >>> 48);
		b[off + 7] = (byte) (j >>> 56);
	}
	
	
	
	/*
	 * Methods for converting primitive values to byte arrays.
	 */
	public static byte[] packBoolean(boolean val)
	{
		return new byte[]
						{
						 (byte) (val ? 1 : 0)
						};
	}
	
	public static byte[] packBig(boolean val)
	{
		return packBoolean(val);
	}
	
	public static byte[] packLittle(boolean val)
	{
		return packBoolean(val);
	}
	
	
	public static byte[] packBig(char val)
	{
		return new byte[]
						{
						 (byte) (val >>> 0),
						 (byte) (val >>> 8),
						};
	}
	
	public static byte[] packBig(short val)
	{
		return new byte[]
						{
						 (byte) (val >>> 0),
						 (byte) (val >>> 8),
						};
	}
	
	public static byte[] packBig(int val)
	{
		return new byte[]
						{
						 (byte) (val >>> 0),
						 (byte) (val >>> 8),
						 (byte) (val >>> 16),
						 (byte) (val >>> 24),
						};
	}
	
	public static byte[] packBig(float val)
	{
		int i = Float.floatToIntBits(val);
		return new byte[]
						{
						 (byte) (i >>> 0),
						 (byte) (i >>> 8),
						 (byte) (i >>> 16),
						 (byte) (i >>> 24),
						};
	}
	
	public static byte[] packBig(long val)
	{
		return new byte[]
						{
						 (byte) (val >>> 0),
						 (byte) (val >>> 8),
						 (byte) (val >>> 16),
						 (byte) (val >>> 24),
						 (byte) (val >>> 32),
						 (byte) (val >>> 40),
						 (byte) (val >>> 48),
						 (byte) (val >>> 56),
						};
	}
	
	public static byte[] packBig(double val)
	{
		long j = Double.doubleToLongBits(val);
		return new byte[]
						{
						 (byte) (j >>> 0),
						 (byte) (j >>> 8),
						 (byte) (j >>> 16),
						 (byte) (j >>> 24),
						 (byte) (j >>> 32),
						 (byte) (j >>> 40),
						 (byte) (j >>> 48),
						 (byte) (j >>> 56),
						};
	}
	
	
	
	public static byte[] packLittle(char val)
	{
		return new byte[]
						{
						 (byte) (val >>> 0),
						 (byte) (val >>> 8),
						};
	}
	
	public static byte[] packLittle(short val)
	{
		return new byte[]
						{
						 (byte) (val >>> 0),
						 (byte) (val >>> 8),
						};
	}
	
	public static byte[] packLittle(int val)
	{
		return new byte[]
						{
						 (byte) (val >>> 0),
						 (byte) (val >>> 8),
						 (byte) (val >>> 16),
						 (byte) (val >>> 24),
						};
	}
	
	public static byte[] packLittle(float val)
	{
		int i = Float.floatToIntBits(val);
		return new byte[]
						{
						 (byte) (i >>> 0),
						 (byte) (i >>> 8),
						 (byte) (i >>> 16),
						 (byte) (i >>> 24),
						};
	}
	
	public static byte[] packLittle(long val)
	{
		return new byte[]
						{
						 (byte) (val >>> 0),
						 (byte) (val >>> 8),
						 (byte) (val >>> 16),
						 (byte) (val >>> 24),
						 (byte) (val >>> 32),
						 (byte) (val >>> 40),
						 (byte) (val >>> 48),
						 (byte) (val >>> 56),
						};
	}
	
	public static byte[] packLittle(double val)
	{
		long j = Double.doubleToLongBits(val);
		return new byte[]
						{
						 (byte) (j >>> 0),
						 (byte) (j >>> 8),
						 (byte) (j >>> 16),
						 (byte) (j >>> 24),
						 (byte) (j >>> 32),
						 (byte) (j >>> 40),
						 (byte) (j >>> 48),
						 (byte) (j >>> 56),
						};
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//Convenience Methods for writing to/reading from streams
	public static boolean getBoolean(InputStream in) throws IOException
	{
		byte[] b = new byte[1];
		in.read(b);
		return getBoolean(b, 0);
	}
	
	public static char getBigChar(InputStream in) throws IOException
	{
		byte[] b = new byte[2];
		in.read(b);
		return getBigChar(b, 0);
	}
	
	public static short getBigShort(InputStream in) throws IOException
	{
		byte[] b = new byte[2];
		in.read(b);
		return getBigShort(b, 0);
	}
	
	public static int getBigInt(InputStream in) throws IOException
	{
		byte[] b = new byte[4];
		in.read(b);
		return getBigInt(b, 0);
	}
	
	public static float getBigFloat(InputStream in) throws IOException
	{
		byte[] b = new byte[4];
		in.read(b);
		return getBigFloat(b, 0);
	}
	
	public static long getBigLong(InputStream in) throws IOException
	{
		byte[] b = new byte[8];
		in.read(b);
		return getBigLong(b, 0);
	}
	
	public static double getBigDouble(InputStream in) throws IOException
	{
		byte[] b = new byte[8];
		in.read(b);
		return getBigDouble(b, 0);
	}
	
	
	
	public static char getLittleChar(InputStream in) throws IOException
	{
		byte[] b = new byte[2];
		in.read(b);
		return getLittleChar(b, 0);
	}
	
	public static short getLittleShort(InputStream in) throws IOException
	{
		byte[] b = new byte[2];
		in.read(b);
		return getLittleShort(b, 0);
	}
	
	public static int getLittleInt(InputStream in) throws IOException
	{
		byte[] b = new byte[4];
		in.read(b);
		return getLittleInt(b, 0);
	}
	
	public static float getLittleFloat(InputStream in) throws IOException
	{
		byte[] b = new byte[4];
		in.read(b);
		return getLittleFloat(b, 0);
	}
	
	public static long getLittleLong(InputStream in) throws IOException
	{
		byte[] b = new byte[8];
		in.read(b);
		return getLittleLong(b, 0);
	}
	
	public static double getLittleDouble(InputStream in) throws IOException
	{
		byte[] b = new byte[8];
		in.read(b);
		return getLittleDouble(b, 0);
	}
	
	
	
	
	
	
	public static void putBoolean(OutputStream out, boolean val) throws IOException
	{
		byte[] b = new byte[1];
		putBoolean(b, 0, val);
		out.write(b);
	}
	
	public static void putBig(OutputStream out, boolean val) throws IOException
	{
		putBoolean(out, val);
	}
	
	public static void putLittle(OutputStream out, boolean val) throws IOException
	{
		putBoolean(out, val);
	}
	
	
	
	public static void putBig(OutputStream out, char val) throws IOException
	{
		byte[] b = new byte[2];
		putBig(b, 0, val);
		out.write(b);
	}
	
	public static void putBig(OutputStream out, short val) throws IOException
	{
		byte[] b = new byte[2];
		putBig(b, 0, val);
		out.write(b);
	}
	
	public static void putBig(OutputStream out, int val) throws IOException
	{
		byte[] b = new byte[4];
		putBig(b, 0, val);
		out.write(b);
	}
	
	public static void putBig(OutputStream out, float val) throws IOException
	{
		byte[] b = new byte[4];
		putBig(b, 0, val);
		out.write(b);
	}
	
	public static void putBig(OutputStream out, long val) throws IOException
	{
		byte[] b = new byte[8];
		putBig(b, 0, val);
		out.write(b);
	}
	
	public static void putBig(OutputStream out, double val) throws IOException
	{
		byte[] b = new byte[8];
		putBig(b, 0, val);
		out.write(b);
	}
	
	
	
	public static void putLittle(OutputStream out, char val) throws IOException
	{
		byte[] b = new byte[2];
		putLittle(b, 0, val);
		out.write(b);
	}
	
	public static void putLittle(OutputStream out, short val) throws IOException
	{
		byte[] b = new byte[2];
		putLittle(b, 0, val);
		out.write(b);
	}
	
	public static void putLittle(OutputStream out, int val) throws IOException
	{
		byte[] b = new byte[4];
		putLittle(b, 0, val);
		out.write(b);
	}
	
	public static void putLittle(OutputStream out, float val) throws IOException
	{
		byte[] b = new byte[4];
		putLittle(b, 0, val);
		out.write(b);
	}
	
	public static void putLittle(OutputStream out, long val) throws IOException
	{
		byte[] b = new byte[8];
		putLittle(b, 0, val);
		out.write(b);
	}
	
	public static void putLittle(OutputStream out, double val) throws IOException
	{
		byte[] b = new byte[8];
		putLittle(b, 0, val);
		out.write(b);
	}
	
	
	
	
	
	
	
	
	
	//Convience  methods  for  readFully/writ ing  from/to  RandomAccessFile s
	public static boolean getBoolean(RandomAccessFile in) throws IOException
	{
		byte[] b = new byte[1];
		in.readFully(b);
		return getBoolean(b, 0);
	}
	
	public static char getBigChar(RandomAccessFile in) throws IOException
	{
		byte[] b = new byte[2];
		in.readFully(b);
		return getBigChar(b, 0);
	}
	
	public static short getBigShort(RandomAccessFile in) throws IOException
	{
		byte[] b = new byte[2];
		in.readFully(b);
		return getBigShort(b, 0);
	}
	
	public static int getBigInt(RandomAccessFile in) throws IOException
	{
		byte[] b = new byte[4];
		in.readFully(b);
		return getBigInt(b, 0);
	}
	
	public static float getBigFloat(RandomAccessFile in) throws IOException
	{
		byte[] b = new byte[4];
		in.readFully(b);
		return getBigFloat(b, 0);
	}
	
	public static long getBigLong(RandomAccessFile in) throws IOException
	{
		byte[] b = new byte[8];
		in.readFully(b);
		return getBigLong(b, 0);
	}
	
	public static double getBigDouble(RandomAccessFile in) throws IOException
	{
		byte[] b = new byte[8];
		in.readFully(b);
		return getBigDouble(b, 0);
	}
	
	
	
	public static char getLittleChar(RandomAccessFile in) throws IOException
	{
		byte[] b = new byte[2];
		in.readFully(b);
		return getLittleChar(b, 0);
	}
	
	public static short getLittleShort(RandomAccessFile in) throws IOException
	{
		byte[] b = new byte[2];
		in.readFully(b);
		return getLittleShort(b, 0);
	}
	
	public static int getLittleInt(RandomAccessFile in) throws IOException
	{
		byte[] b = new byte[4];
		in.readFully(b);
		return getLittleInt(b, 0);
	}
	
	public static float getLittleFloat(RandomAccessFile in) throws IOException
	{
		byte[] b = new byte[4];
		in.readFully(b);
		return getLittleFloat(b, 0);
	}
	
	public static long getLittleLong(RandomAccessFile in) throws IOException
	{
		byte[] b = new byte[8];
		in.readFully(b);
		return getLittleLong(b, 0);
	}
	
	public static double getLittleDouble(RandomAccessFile in) throws IOException
	{
		byte[] b = new byte[8];
		in.readFully(b);
		return getLittleDouble(b, 0);
	}
	
	
	
	
	
	public static void putBoolean(RandomAccessFile out, boolean val) throws IOException
	{
		byte[] b = new byte[1];
		putBoolean(b, 0, val);
		out.write(b);
	}
	
	public static void putBig(RandomAccessFile out, boolean val) throws IOException
	{
		putBoolean(out, val);
	}
	
	public static void putLittle(RandomAccessFile out, boolean val) throws IOException
	{
		putBoolean(out, val);
	}
	
	
	
	public static void putBig(RandomAccessFile out, char val) throws IOException
	{
		byte[] b = new byte[2];
		putBig(b, 0, val);
		out.write(b);
	}
	
	public static void putBig(RandomAccessFile out, short val) throws IOException
	{
		byte[] b = new byte[2];
		putBig(b, 0, val);
		out.write(b);
	}
	
	public static void putBig(RandomAccessFile out, int val) throws IOException
	{
		byte[] b = new byte[4];
		putBig(b, 0, val);
		out.write(b);
	}
	
	public static void putBig(RandomAccessFile out, float val) throws IOException
	{
		byte[] b = new byte[4];
		putBig(b, 0, val);
		out.write(b);
	}
	
	public static void putBig(RandomAccessFile out, long val) throws IOException
	{
		byte[] b = new byte[8];
		putBig(b, 0, val);
		out.write(b);
	}
	
	public static void putBig(RandomAccessFile out, double val) throws IOException
	{
		byte[] b = new byte[8];
		putBig(b, 0, val);
		out.write(b);
	}
	
	
	
	public static void putLittle(RandomAccessFile out, char val) throws IOException
	{
		byte[] b = new byte[2];
		putLittle(b, 0, val);
		out.write(b);
	}
	
	public static void putLittle(RandomAccessFile out, short val) throws IOException
	{
		byte[] b = new byte[2];
		putLittle(b, 0, val);
		out.write(b);
	}
	
	public static void putLittle(RandomAccessFile out, int val) throws IOException
	{
		byte[] b = new byte[4];
		putLittle(b, 0, val);
		out.write(b);
	}
	
	public static void putLittle(RandomAccessFile out, float val) throws IOException
	{
		byte[] b = new byte[4];
		putLittle(b, 0, val);
		out.write(b);
	}
	
	public static void putLittle(RandomAccessFile out, long val) throws IOException
	{
		byte[] b = new byte[8];
		putLittle(b, 0, val);
		out.write(b);
	}
	
	public static void putLittle(RandomAccessFile out, double val) throws IOException
	{
		byte[] b = new byte[8];
		putLittle(b, 0, val);
		out.write(b);
	}
	
	
	
	
	
	
	
	
	//[Un]signed
	public static int getUnsigned(byte sig)
	{
		int rv = sig;
		if (rv <  0)
		{
			rv = 256 + rv;
		}
		return rv;
	}
	
	public static int getUnsigned(short sig)
	{
		int rv = sig;
		if (rv <  0)
		{
			rv = 32768 + rv;
		}
		return rv;
	}
	
	public static long getUnsigned(int sig)
	{
		long rv = sig;
		if (rv < 0)
		{
			rv = 2147483648L + rv;
		}
		return rv;
	}
	
	
	public static int[] getUnsigned(byte[] sig)
	{
		int[] rvs = new int[sig.length];
		int i = 0;
		while (i < sig.length)
		{
			rvs[i] = getUnsigned(sig[i]);
			i++;
		}
		return rvs;
	}
	
	public static int[] getUnsigned(short[] sig)
	{
		int[] rvs = new int[sig.length];
		int i = 0;
		while (i < sig.length)
		{
			rvs[i] = getUnsigned(sig[i]);
			i++;
		}
		return rvs;
	}
	
	public static long[] getUnsigned(int[] sig)
	{
		long[] rvs = new long[sig.length];
		int i = 0;
		while (i < sig.length)
		{
			rvs[i] = getUnsigned(sig[i]);
			i++;
		}
		return rvs;
	}
	
	
	
	//Utilities
	public static byte[] makeLPacket(byte[] data)
	{
		int len = data.length;
		byte[] lpkt = new byte[len+4];
		putLittle(lpkt, 0, len);
		System.arraycopy(data, 0, lpkt, 4, len);
		return lpkt;
	}
	
	public static void relayLPacket(byte[] data, OutputStream relay) throws IOException
	{
		relay.write(packLittle(data.length));
		relay.write(data);
	}
	
	
	public static byte[] readLPacket(byte[] pkt, int offset)
	{
		int len = getLittleInt(pkt, offset);
		byte[] data = new byte[len];
		System.arraycopy(pkt, offset+4, data, 0, len);
		return data;
	}
	
	public static byte[] readLPacket(InputStream in) throws IOException
	{
		int len = getLittleInt(in);
		byte[] data = new byte[len];
		int i = -1;
		while (i++ < len)
			data[i] = (byte)in.read();
		return data;
	}
	
	
	public static byte[] makeLPacket(String utf8)
	{
		try
		{
			return makeLPacket(utf8.getBytes("utf8"));
		}
		catch (UnsupportedEncodingException exc)
		{
			return new byte[4];
		}
	}
	
	public static void relayLPacket(String utf8, OutputStream out) throws IOException
	{
		try
		{
			relayLPacket(utf8.getBytes("utf8"), out);
		}
		catch (UnsupportedEncodingException exc)
		{
			out.write(new byte[4]);
		}
	}
}
