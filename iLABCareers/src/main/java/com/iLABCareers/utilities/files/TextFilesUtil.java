package com.iLABCareers.utilities.files;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TextFilesUtil {

	public static void writeToFile(final String fileName, final String[] message,String newline)
	{
		if(message == null)
		{
			return;
		}
		
		try {
			FileWriter fstream = new FileWriter(fileName, false);
			final BufferedWriter out = new BufferedWriter(fstream);
			for(int i=0;i<message.length;i++)
			{
				out.write(message[i] + newline);
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static List<String> readFileIntoArray(String fileName)
	{
		FileReader fileReader = null;
		BufferedReader br = null;
		List<String> fileContents = new ArrayList<String>();
		try
		{
			fileReader = new FileReader(fileName);
			br = new BufferedReader(fileReader);
			String line = null;

			while ((line = br.readLine()) != null)
			{
				fileContents.add(line);
			}
		}
		catch (Exception ex)
		{
			
		}
		finally
		{
			try
			{
				if(br != null)
				{
					br.close();
				}

				if(fileReader != null)
				{
					fileReader.close();
				}

			}
			catch (Exception ex)
			{
				
			}
		}
		return fileContents;
	}

	/**
	 * Generates the name of the file from the given path to the file.
	 * 
	 * @param pathToFile
	 *            Full path to the file.
	 * @return The name of the file.
	 */
	public static String getFilenameFromFullPath(String pathToFile)
	{
		File file = new File(pathToFile);
		if(file.exists())
		{
			return file.getName();
		}
		return null;
	}
	
	
	/**
	 * Gets a list of files in a given directory.
	 * 
	 * @param directory
	 *            A directory where the files are stored
	 * 
	 * @return A list of file names.
	 */
	public static List<String> getFiles(String directory)
	{
		FileFilter fileFilter = new FileFilter()
		{
			@Override
			public boolean accept(File file)
			{
				return file.isFile();
			}
		};
		return getFiles(directory, fileFilter);
	}
	
	/**
	 * Gets a list of contents in a given directory
	 * 
	 * @param directory
	 *            A directory where the items will be fetched
	 * @param filter
	 *            The filter to be applied on the items to be loaded
	 * @return A collection of items from the directory.
	 */
	private static List<String> getFiles(String directory, FileFilter filter)
	{
		List<String> files = new ArrayList<String>();
		if(directory == null || filter == null)
		{
			return files;
		}
		File parentDir = new File(directory);
		if(!parentDir.exists())
		{
			return files;
		}
		File[] subDir = parentDir.listFiles(filter);

		for (int index = 0; index < subDir.length; index++)
		{
			files.add(subDir[index].getName());
		}
		return files;
	}
	
	
}
