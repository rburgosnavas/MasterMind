package com.rburgos.mastermindtestlayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.beans.Transient;

import javax.swing.JComponent;


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
        // TODO Auto-generated method stub
        // super.setPreferredSize(preferredSize);
        prefSize.setSize(preferredSize);
    }

    @Override
    @Transient
    public Dimension getMaximumSize()
    {
        // TODO Auto-generated method stub
        // return super.getMaximumSize();
        return (new Dimension(200, 200));
    }

    @Override
    @Transient
    public Dimension getMinimumSize()
    {
        // TODO Auto-generated method stub
        // return super.getMinimumSize();
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
