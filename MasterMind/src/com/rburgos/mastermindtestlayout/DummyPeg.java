package com.rburgos.mastermindtestlayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.beans.Transient;

import javax.swing.JComponent;


@SuppressWarnings("serial")
public class DummyPeg extends JComponent
{
    Dimension prefSize;
    public DummyPeg() 
    {
        prefSize = new Dimension(40, 40);
    }

	@Override
    public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(Color.DARK_GRAY);
		// g.fillRoundRect(x1, y1, x2, y2, 15, 15);
		g.drawRoundRect(1, 1, this.getWidth()-2, this.getHeight()-2, 15, 15);
        // g.setColor(Color.DARK_GRAY.brighter());
        // g.fillRoundRect(x1+10, y1+10, x2-15, y2-15, 20, 20);
        // g.fillRoundRect(10, 10, this.getWidth()-15, this.getHeight()-15, 20, 20);
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
}
