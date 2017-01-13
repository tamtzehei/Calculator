import java.awt.Button;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import BreezyGUI.GBFrame;
import BreezyGUI.IntegerField;


public class Window extends GBFrame implements KeyListener
{
	int count = 640;
	double yScale, scale;
	double xScale;
	ArrayList<String> equation = new ArrayList<String>();
	Frame frm;
	TextArea field = addTextArea("y = ",1,1,2,1);
	
	Label xmin = addLabel("X-min",2,1,1,1);
	Label xmax = addLabel("X-max",3,1,1,1);
	Label ymin = addLabel("Y-min",4,1,1,1);
	Label ymax = addLabel("Y-max",5,1,1,1);
	
	IntegerField Xmin = addIntegerField(-10,2,2,1,1);
	IntegerField Xmax = addIntegerField(10,3,2,1,1);
	IntegerField Ymin = addIntegerField(-10,4,2,1,1);
	IntegerField Ymax = addIntegerField(10,5,2,1,1);
	
	Button graph = addButton("Graph",6,2,1,1);
	
	public Window(ArrayList<String> equation, String number)
	{
		field.setText(field.getText() + number);
		this.equation = equation;
		this.setSize(300,500);
		addKeyListener(this);
	}
	
	public void buttonClicked(Button button)
	{
		if(button == graph)
		{
			xScale = (double)(Xmax.getNumber() - Xmin.getNumber()) / 1280;
			yScale = 500 / (double)(Ymax.getNumber() - Ymin.getNumber());
			scale = 500 / (double)(Xmax.getNumber() - Xmin.getNumber());
			
			Frame frm = new Graph(equation, xScale, yScale, Xmin.getNumber(), Xmax.getNumber(), Ymin.getNumber(), Ymax.getNumber(), scale, count);
			frm.repaint();
			frm.setVisible(true);
			
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0)
	{
		if(arg0.getKeyCode() == KeyEvent.VK_LEFT)
		{
			count -= 10;
		}
		else if(arg0.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			count += 10;
		}
		System.out.println(count);
		Frame frm = new Graph(equation, xScale, yScale, Xmin.getNumber(), Xmax.getNumber(), Ymin.getNumber(), Ymax.getNumber(), scale, count);
		frm.repaint();
		frm.setVisible(true);
		System.out.println();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
