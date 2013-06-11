package com.rburgos.mastermindtestlayout;

import javax.swing.*;
import java.awt.*;
import java.beans.Transient;


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
		g.drawRoundRect(1, 1, this.getWidth()-2, this.getHeight()-2, 15, 15);
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
}
