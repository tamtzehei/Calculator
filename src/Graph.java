import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;


public class Graph extends JFrame implements KeyListener, ActionListener
{
	ArrayList<String> equation = new ArrayList<String>();
	JFrame frame = new JFrame();
	JToolBar tools = new JToolBar();
	JTextField xField = new JTextField("x = ");
	JTextField yField = new JTextField("y = ");
	JButton trace = new JButton("Trace");
	int count = 640, xCoord, yCoord;
	int x[];
	int y[];
	ArrayList<Integer> asymptotes = new ArrayList<Integer>();
	double counter, xScale, scale, yMin, yScale, yMax, xMax;
	int xCenter, yCenter;
	char temp[] = {'x'};
	private Graphics g;
	
	/**
	 * 
	 * @param num - ArrayList with equation
	 * @param xScale - increment for counter
	 * @param yScale - scale for y-axis
	 * @param xMin - minimum x-value
	 * @param yMin - minimum y-value
	 * @param scale - scale for x-axis
	 */
	
	public Graph(ArrayList<String> num, double xScale, double yScale, double xMin,double xMax, double yMin, double yMax, double scale, int count)
	{

		this.setSize(500, 500);
		tools.add(trace);
		tools.add(xField);
		tools.add(yField);
		
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(tools, BorderLayout.SOUTH);
		frame.add(panel);
		//this.add(panel);
		addKeyListener(this);
		equation = num;
		x = new int[1280];
		y = new int[1280];
		this.yScale = yScale;
		this.counter = xMin;
		this.xScale = xScale;
		this.scale = scale;
		this.xCenter = (int) (-scale * xMin);
		this.yCenter = 500 - (int) (-yScale * yMin);
		this.yMin = yMin;
		this.yMax = yMax;
		this.xMax = xMax;
		//this.count = count;
		
	}
	
	public void paint(Graphics g)
	{
		g.translate(xCenter, yCenter);
		g.drawLine((int)(counter * scale), 0, (int)(counter + (scale * 1280)), 0); // x-axis
		g.drawLine(0, (int)(-yMax * yScale), 0, (int)(-yMin * yScale)); // y-axis
		
		for(int i = (int) counter; i <= xMax; i++)
		{
			g.drawLine((int)(i * scale), 5, (int)(i * scale), -5);
		}
		
		for(int i = (int) -yMin; i >= -yMax; i--)
		{
			g.drawLine(5, (int)(i * yScale), -5, (int)(i * yScale));
		}

		
		Calc calc = new Calc();
		for(int j = 0; j < 1280; j++)
		{
			ArrayList<String> temp = new ArrayList<String>();
			for(int i = 0; i < equation.size(); i++)
			{
				if(equation.get(i).equals("x"))
					temp.add(Double.toString(counter));
				else
					temp.add(equation.get(i));
			}

			x[j] = (int)(counter * scale);
		    y[j] = (int)(Double.parseDouble(calc.calculate(temp, true)) * -yScale);

		    counter = counter + xScale;
		}

		for(int i = 1; i < 1280; i++)
		{
			if(Math.abs(y[i] - y[i - 1]) < 100)
				g.drawLine(x[i - 1], y[i - 1], x[i], y[i]);
		}
		

		
	}

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
		repaint();
		setVisible(true);
	}

	public void trace(Graphics g, int count)
	{
		
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
	
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		
	}
}
