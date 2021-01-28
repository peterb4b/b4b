package com.b4b;

import java.util.LinkedList;

public class B4BCompress
{
	public static void main(String[] args)
	{
		LinkedList<String> result = new LinkedList<>();

		String input = args[0];

		int count = 0;
		char prevChar = '\0';
		for (Character currChar : input.toCharArray())
		{
			if (currChar != prevChar)
			{
				if (prevChar != '\0')
				{
					if (count > 1)
					{
						result.add(count + "");
					}

					count = 1;
				}
				else
				{
					count++;
				}

				result.add(currChar + "");
				prevChar = currChar;
			}
			else
			{
				count++;
			}
		}

		if (count > 1)
		{
			result.add(count + "");			
		}

		result.stream().forEach(c -> System.out.print(c));
	}
}