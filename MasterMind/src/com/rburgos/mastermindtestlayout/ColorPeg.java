package com.rburgos.mastermindtestlayout;

import javax.swing.*;
import java.awt.*;
import java.beans.Transient;


@SuppressWarnings("serial")
public class ColorPeg extends JComponent
{
    Dimension prefSize;
    Color c;
    
    public ColorPeg(Color c) 
    {
        prefSize = new Dimension(40, 40);
        this.c = c;
    }

	@Override
    public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(c);
        g.fillRoundRect(1, 1, this.getWidth()-2, this.getHeight()-2, 15, 15);
        g.setColor(c.brighter());
        g.fillRoundRect(10, 10, this.getWidth()-15, this.getHeight()-15, 15, 15);
	}
	
	@Override
    public Dimension getPreferredSize()
    {
        return prefSize;
    }

    @Override
    public void setPreferredSize(Dimension preferredSize)
    {
        prefSize.setSize(preferredSize);
    }

    @Override
    @Transient
    public Dimension getMaximumSize()
    {
        return (new Dimension(200, 200));
    }

    @Override
    @Transient
    public Dimension getMinimumSize()
    {
        return (new Dimension(50, 50));
    }
    
    public Color getColor()
    {
        return c;
    }
    
    public void setColor(Color c)
    {
        this.c = c;
    }
}
