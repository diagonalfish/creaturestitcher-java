/*
 * Created on Jan 5, 2006
 * 	by the wonderful Eclipse(c)
 */
package rebound.jagent.lib.c16;

class DataMap
{
	/*
	 * Offsets start at beginning of data
	 * 
	 * Row #0 is really 
	 * 
	 * Dimensions are assigned as follows:
	 * 	offsets[spriteIndex][rowIndex]
	 */
	int[][] offsets;
	
	int headersLength;
	
	
	public int getOffset(int spriteIndex, int rowIndex)
	{
		return offsets[spriteIndex][rowIndex];
	}
}
