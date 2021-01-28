package com.b4b;

import java.io.File;
import java.util.Comparator;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.LinkedBlockingQueue;

// Exercise #1
public class B4BFile
{
	public static void main(String[] args)
	{
		File rootFile = new File(args[0]);

		if (rootFile.isDirectory())
		{
			Queue<File> fileQ = new LinkedBlockingQueue<>();
			fileQ.addAll(getFileQueue(rootFile.listFiles()));

			Set<FileNameAndSize> result = breadthFirstSearchOfFileSystem(fileQ);

			printResult(result);
		}
		else
		{
			System.out.println("Provided input " + args[0] + " is not a directory.");
		}
	}

	private static Set<FileNameAndSize> breadthFirstSearchOfFileSystem(Queue<File> fileQ)
	{
		Set<FileNameAndSize> result = new TreeSet<>(Comparator.comparing(FileNameAndSize::getSize));
		while (!fileQ.isEmpty())
		{
			File currFile = fileQ.poll();
			if (currFile.isDirectory())
			{
				fileQ.addAll(getFileQueue(currFile.listFiles()));
			}
			else
			{
				FileNameAndSize fileNameAndSize = new FileNameAndSize(currFile);
				result.add(fileNameAndSize);
			}
		}

		return result;
	}

	private static void printResult(Set<FileNameAndSize> result)
	{
		for (FileNameAndSize f : result)
		{
			System.out.println(f.path + " / " + f.name + " / " + f.size);
		}
	}

	private static Queue<File> getFileQueue(File[] rootFile)
	{
		Queue<File> result = new LinkedBlockingQueue<>();
		for (int i = 0; i < rootFile.length; i++)
		{
			result.add(rootFile[i]);
		}

		return result;
	}

	public static class FileNameAndSize
	{
		public FileNameAndSize(File currFile)
		{
			this.path = currFile.getAbsolutePath();
			this.name = currFile.getName();
			this.size = currFile.length();
		}

		public String path;
		public String name;
		public long size;

		public long getSize()
		{
			return this.size;
		}
	}
}
