import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Trace extends Frame implements KeyListener, ActionListener
{
	char temp[] = {'x'};
	int x[];
	int y[];
	int xCoord,yCoord, counter = 0, xCenter, yCenter;
	
	public Trace(int x[], int y[], int xCenter, int yCenter)
	{
		this.x = x;
		this.y = y;
		this.xCenter = xCenter;
		this.yCenter = yCenter;
		xCoord = 0;
		yCoord = 0;
		addKeyListener(this);
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		if(arg0.getKeyCode() == KeyEvent.VK_LEFT)
		{
			counter--;
			xCoord = x[counter];
			yCoord = y[counter];
		}
		else if(arg0.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			counter++;
			xCoord = x[counter];
			yCoord = y[counter];
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void paint(Graphics g)
	{
		g.translate(xCenter, yCenter);
		g.drawChars(temp, 0, 1, xCoord, yCoord);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
