import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.TextArea;
import java.awt.TextField;
import java.util.ArrayList;
import java.util.List;

import BreezyGUI.GBFrame;

/*	Name:				Tze Hei Tam
	Date Created:		3/10/16
	Date Modified:		4/1/16
	Program:			11
	Description:		This program will create a four function calculator using breezy gui 
	Bonus:				1(Made a nine function calculator)
						2(Made buttons for the numbers)
*/
public class Calc extends GBFrame
{
	String number = "", display = "";
	int opCounter = 0;
	boolean bracket = false;
	boolean mode; //true for rad  false for deg
	boolean firstTime = true;
	String answer;
	
	ArrayList<String> num = new ArrayList<String>();
	ArrayList<Integer> opIndex = new ArrayList<Integer>();
	ArrayList<Double> nums = new ArrayList<Double>();
	
	Button add = addButton("+",6,1,1,1);
	Button sub = addButton("-",6,2,1,1);
	Button mult = addButton("*",7,1,1,1);
	Button div = addButton("Ö",7,2,1,1);
	Button sine = addButton("sin",2,4,1,1);
	Button cosine = addButton("cos",3,4,1,1);
	Button tan = addButton("tan",4,4,1,1);
	Button log = addButton("log",5,4,1,1);
	Button ln = addButton("ln",6,4,1,1);
	Button exponent = addButton("^",8,2,1,1);
	Button equals = addButton("=",5,3,1,1);
	Button negative = addButton("(-)",5,2,1,1);
	Button decimal = addButton(".",6,3,1,1);
	Button clear = addButton("C", 7,3,1,1);
	Button startBracket = addButton("(",7,4,1,1);
	Button endBracket = addButton(")",8,4,1,1);
	Button trigMode = addButton("deg/rad",8,3,1,1);
	Button graph = addButton("Graph",9,4,1,1);
	Button x = addButton("x",10,1,1,1);
	
	Button one= addButton("1",2,1,1,1);
	Button two = addButton("2",2,2,1,1);
	Button three = addButton("3",2,3,1,1);
	Button four = addButton("4",3,1,1,1);
	Button five = addButton("5",3,2,1,1);
	Button six = addButton("6",3,3,1,1);
	Button seven = addButton("7",4,1,1,1);
	Button eight = addButton("8",4,2,1,1);
	Button nine = addButton("9",4,3,1,1);
	Button zero = addButton("0",5,1,1,1);
	Button pi = addButton("¹", 8,1,1,1);
	Button e = addButton("e",9,2,1,1);
	Button ans = addButton("Ans",9,1,1,1);
	
	Button buttons[] = {zero, one, two, three, four, five, six, seven, eight, nine, x,pi, e, negative, decimal ,add, sub, mult, div, startBracket, endBracket, exponent, sine, cosine, tan, log, ln};
	
	TextArea field = addTextArea("",1,1,4,1);
	TextField degrad = addTextField("",9,3,1,1);
	
	public Calc()
	{
		opIndex.add(0);
		mode = true;
		degrad.setText("rad");
	}
	public static void main(String[] args)
	{
		Frame calc = new Calc();
		calc.setSize(300,500);
		calc.setBackground(Color.BLUE);
		calc.setVisible(true);
	}
	
	public void buttonClicked(Button button)
	{
		
		field.setText(number);

		for(int i = 0; i < 15; i++)
		{
			if(button == buttons[i])
			{
				if(i == 11)
					number += Double.toString(Math.PI);
				else if(i == 12)
					number += Double.toString(Math.E);
				else if(i != 13)
					number += buttons[i].getLabel();
				else
					number += "-";
				field.setText(number);
			}
		}
		for(int j = 15; j < 27; j++)
		{
			if(button == buttons[j])
			{
				if(number.equals("") && !firstTime && !(j > 19))
					number += "Ans";
				field.setText(number);
				number += buttons[j].getLabel();
				if(j < 22)
				{
					if(number.substring(opIndex.get(opCounter), number.length() - 1).equals("Ans"))
						num.add(answer);
					else
						num.add(number.substring(opIndex.get(opCounter), number.length() - 1));
					
					num.add(buttons[j].getLabel());
					field.setText(field.getText() + buttons[j].getLabel());
				}
				else
				{
					num.add(buttons[j].getLabel());
					num.add("(");	
					field.setText(field.getText() + buttons[j].getLabel());
					field.setText(field.getText() + "(");
					number += "(";
				}
				opCounter++;

				opIndex.add(number.length());
			}
		}
		if(button == ans)
		{
			number += "Ans";
			field.setText(number);
		}
		else if(button == graph)
		{
			if(num.size() != 0  && !num.get(num.size() - 1).equals(")"))
				num.add(number.substring(opIndex.get(opCounter), number.length()));
			else if(num.size() == 0)
				num.add(number.substring(opIndex.get(opCounter), number.length()));
				
			ArrayList<String> temp = new ArrayList<String>();
			temp.add("");
			num.removeAll(temp);
			
			Frame frm = new Window(num, number);
			frm.setVisible(true);
		}
		else if(button == trigMode)
		{
			mode = !mode;
			if(mode)
				degrad.setText("rad");
			else
				degrad.setText("deg");
		}
		else if(button == equals)
		{
			if(num.size() != 0  && !num.get(num.size() - 1).equals(")"))
				num.add(number.substring(opIndex.get(opCounter), number.length()));
			else if(num.size() == 0)
				num.add(number.substring(opIndex.get(opCounter), number.length()));
				
			ArrayList<String> temp = new ArrayList<String>();
			temp.add("");
			num.removeAll(temp);
			
			firstTime = false;
			field.setText(Calc.calculate(num, mode));
			answer = Calc.calculate(num, mode);
		}
		else if(button == clear)
		{
			number = "";
			field.setText(number);
			opIndex.clear();
			opCounter = 0;
			if(num.size() != 0)
				answer = num.get(0);
			opIndex.add(0);
			num.clear();
		}
	}
	
	public static String calculate(List<String> nums, boolean mode)
	{
		int i = 0;
		int j = 0;
		while(nums.contains("("))
		{
			int index;
			if(nums.lastIndexOf("(") == 0)
				index = 0;
			else
				index = nums.lastIndexOf("(") - 1;
			nums = (ArrayList<String>) Calc.brackets(nums, mode);
		
			if(nums.get(index).equals("log"))
			{
				nums.add(index, Double.toString(Math.log10(Double.parseDouble(nums.get(index + 1)))));
				nums.remove(index + 1);
				nums.remove(index + 1);
			}
			else if(nums.get(index).equals("ln"))
			{
				nums.add(index, Double.toString(Math.log(Double.parseDouble(nums.get(index + 1)))));
				nums.remove(index + 1);
				nums.remove(index + 1);
			}
			else if(nums.get(index).equals("sin"))
			{
				if(mode)
					nums.add(index, Double.toString(Math.sin(Double.parseDouble(nums.get(index + 1)))));
				else
					nums.add(index, Double.toString(Math.sin((Math.PI / 180) * (Double.parseDouble(nums.get(index + 1))))));
					nums.remove(index + 1);
					nums.remove(index + 1);
			}
			else if(nums.get(index).equals("cos"))
			{
				if(mode)
					nums.add(index, Double.toString(Math.cos(Double.parseDouble(nums.get(index + 1)))));
				else
					nums.add(index, Double.toString(Math.cos((Math.PI / 180) * (Double.parseDouble(nums.get(index + 1))))));
					nums.remove(index + 1);
					nums.remove(index + 1);
			}
			else if(nums.get(index).equals("tan"))
			{
				if(mode)
					nums.add(index, Double.toString(Math.tan(Double.parseDouble(nums.get(index + 1)))));
				else
					nums.add(index, Double.toString(Math.tan((Math.PI / 180) * (Double.parseDouble(nums.get(index + 1))))));
					nums.remove(index + 1);
					nums.remove(index + 1);
			}
		}
		while(nums.contains("^"))
		{
			if(nums.get(j).equals("^"))
			{
				nums.add(j - 1, Double.toString((Math.pow(Double.parseDouble(nums.get(j - 1)), Double.parseDouble(nums.get(j + 1))))));
				nums.remove(j);
				nums.remove(j);
				nums.remove(j);
				j = 0;
			}
			j++;
		}
		while(nums.contains("*") || nums.contains("Ö"))
		{
			if(nums.get(i).equals("*"))
			{
				nums.add(i - 1, Double.toString(Double.parseDouble(nums.get(i - 1)) * Double.parseDouble(nums.get(i + 1))));
				nums.remove(i);
				nums.remove(i);
				nums.remove(i);
				i = 0;
			}
			else if(nums.get(i).equals("Ö"))
			{
				nums.add(i - 1, Double.toString(Double.parseDouble(nums.get(i - 1)) / Double.parseDouble(nums.get(i + 1))));
				nums.remove(i);
				nums.remove(i);
				nums.remove(i);
				i = 0;
			}

			i++;
		}
		
		while(nums.contains("+") || nums.contains("-"))
		{
			if(nums.get(1).equals("+"))
			{
				nums.add(0, Double.toString(Double.parseDouble(nums.get(0)) + Double.parseDouble(nums.get(2))));
				nums.remove(1);
				nums.remove(1);
				nums.remove(1);
			}
			else if(nums.get(1).equals("-"))
			{
				nums.add(0, Double.toString(Double.parseDouble(nums.get(0)) - Double.parseDouble(nums.get(2))));
				nums.remove(1);
				nums.remove(1);
				nums.remove(1);
			}
		}
		
		return nums.get(0);
	}
	
	public static List<String> brackets(List<String> num, boolean mode)
	{
		ArrayList<String> temp = new ArrayList<String>();
		temp.add("");
		boolean bracket = false;
		int index = 0;
		for(int i = num.lastIndexOf("("); i < num.size() && !bracket; i++)
		{
				if(num.get(i).equals(")"))
				{
					index = i;
					bracket = true;
					break;
				}
		}
		int startIndex = num.lastIndexOf("(") + 1;
		num.add(num.lastIndexOf("("), Calc.calculate(num.subList(num.lastIndexOf("(") + 1, index), mode));

		num.remove(startIndex);
		num.remove(startIndex);
		num.remove(startIndex);
		num.remove(temp);
		
		return num;
	}
}
