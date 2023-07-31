package txt_io;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class Line 
{
	private ArrayList<String> columns;
	private int size;
	
	public Line(String line)
	{	
		columns = new ArrayList<String>();
		StringTokenizer tokens = new StringTokenizer(line, ",");
		int size = tokens.countTokens();
		
		for (int i = 0; i < size; i++)
		{
			columns.add(tokens.nextToken());	
		}
	}
	
	public String getColumn(int column)
	{
		return columns.get(column);
	}
}
