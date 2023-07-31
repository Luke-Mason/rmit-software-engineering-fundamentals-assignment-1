package txt_io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class TextFile 
{
	private ArrayList<Line> lines;
	private String path;
	private int size;
	private BufferedReader reader;
	private BufferedWriter writer;
	
	public TextFile(String path) 				
	{   
		this.path = path;					 
		lines = new ArrayList<Line>();
	
		load();
	    
	    size = lines.size();
	}
	
	public String get(int row, int column) // throws exception
	{
		return lines.get(row).getColumn(column);
	}
	
	public int getSize() { return size; }
	
	private boolean load()
	{
		try {
			reader = new BufferedReader(new FileReader(path));
			String line = new String();

			while ((line = reader.readLine()) != null)
				lines.add(new Line(line));

			reader.close();

		} catch (FileNotFoundException fnfe) {
			System.out.println("fnfe");
			return false;
		} catch (IOException ioe) {
			System.out.println("ioe");
			return false;
		}	  
		
		return true;
	}
	
	public boolean overwrite(ArrayList<String> lines)
	{
		size = lines.size();
		
		try {
			writer = new BufferedWriter(new FileWriter(path)); 
			
			for (int i = 0; i < lines.size(); i++) 
			{ 
				writer.append(lines.get(i));
				
				if (i != lines.size() - 1)
					writer.newLine();
				//writer.newLine();
			}
			
			writer.close();
			
		} catch (IOException ioe) {
			System.out.println("File at " + path + " could not be written");
			return false;
		}
		
		this.lines.clear(); 
		
		if (!load())
			return false;
		
		return true;			
	}
	
	
	
	// getlinelength(int line_no) { ...; }
}
