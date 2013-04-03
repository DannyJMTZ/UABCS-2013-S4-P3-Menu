import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D.Double;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;

	//***************************************************************************
	//					DOCUMENTACION
	/* 
	 * 
	 * JFrame
	 * agrega una barra de menu a la ventana
	 * this.setJMenuBar(<JMenuBar variable>);
	 * 
	 * 
	 * JMenu
	 * agrega un separador al menú
	 * <JMenu>.addSeparator()
	 * 
	 * agrega un shortCut para el menu (atajo)
	 * <JMenu>.setMnemonic(char a);
	 * 
	 * 
	 * JMenuItem 
	 * agrega un shortCut con ctrl...ctrl+n
	 * <JMenuItem>.setAccelerator(KeyStroke.getKeyStroke('N', InputEvent.CTRL_DOWN_MASK));
	 * 
	 * */
	//***************************************************************************

public class Menu extends Ventana implements ActionListener{
	
	// barra de menu
	private JMenuBar menuBar;
	
	// menus de la barra de menus
	private JMenu fileMenu, editMenu, colorMenu;
	
	// crea items para los menus
	private JMenuItem newItem, openItem, closeItem, saveItem, saveAsItem, printItem;
	
	// crear JRadioButtonMenuItem
	// los RadioButton son botones donde se debe seleccionar uno noms
	private JRadioButtonMenuItem lineItem, rectangleItem, circleItem, curveItem, textItem;
	
	// crear JCheckBoxMenuItem
	// los CheckBox son botones donde se puede "palomear" cualquier opcion
	private JCheckBoxMenuItem redItem, yellowItem, greenItem, blueItem;
	
	//
	String objeto = "";
	String color = "";
	
	public Menu(int w, int h, String t) {
		super(w, h, t);
		this.setLayout(null);
		
		// primero se crea la barra de los menu
		// en la barra de los menu se agregan los menu
		menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		
		// crea los menu para la barra de menu
		fileMenu = new JMenu("Archivo");
		editMenu = new JMenu("Editar");
		
		// agregar el setMnemonic al fileMenu para tener un shortCut
		fileMenu.setMnemonic('A');
		
		// agrega los items a el menu Archivo
		newItem = fileMenu.add("Nuevo");
		openItem = fileMenu.add("Abrir");
		fileMenu.addSeparator(); // agrega un separador el Menu
		saveItem = fileMenu.add("Guardar");
		saveAsItem = fileMenu.add("Guardar Como");
		printItem = fileMenu.add("Imprimir");
		fileMenu.addSeparator();
		closeItem = fileMenu.add("Cerrar");
		
		// agrega un shortCut a newItem "ctrl+N"
		newItem.setAccelerator(KeyStroke.getKeyStroke('N', InputEvent.CTRL_DOWN_MASK));		
		
		// se agregan los JRadioButtonMenuItem a el menu editMenu
		// se agregan los JCheckBoxMenuItem a el menu editMen
		editMenu.add(lineItem = new JRadioButtonMenuItem("Linea", false));
		editMenu.add(rectangleItem = new JRadioButtonMenuItem("Rectangulo", false));
		editMenu.add(circleItem = new JRadioButtonMenuItem("Circulo", false));
		editMenu.add(curveItem = new JRadioButtonMenuItem("Curva", false));
		editMenu.addSeparator();
		
		// se agrega un subMenu a el menu editMenu
		colorMenu = new JMenu("Color");
		editMenu.add(colorMenu);

		colorMenu.add(redItem = new JCheckBoxMenuItem("Rojo", false));
		colorMenu.add(yellowItem = new JCheckBoxMenuItem("Amarillo", false));
		colorMenu.add(greenItem = new JCheckBoxMenuItem("Verde", false));
		colorMenu.add(blueItem = new JCheckBoxMenuItem("Azul", false));
		
		// como los RadioButton tienen que estar agrupados para que solo se
		// seleccione uno, se crea un ButtonGroup y se los RadioButton
		// se agregan a ese objeto
		ButtonGroup types = new ButtonGroup();
		types.add(lineItem);
		types.add(rectangleItem);
		types.add(circleItem);
		types.add(curveItem);
		
		// agrega los menus a la barra
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		
		// agrega el ActionListener a los botones del menu archivo
		newItem.addActionListener(this);
		openItem.addActionListener(this);
		saveItem.addActionListener(this);
		saveAsItem.addActionListener(this);
		printItem.addActionListener(this);
		closeItem.addActionListener(this);
		
		// agrega el ActionListener a los botones del menu Editar
		lineItem.addActionListener(this);
		rectangleItem.addActionListener(this);
		circleItem.addActionListener(this);
		curveItem.addActionListener(this);
		
		// agrega el ActionListener a los botones del submenu Color
		redItem.addActionListener(this);
		yellowItem.addActionListener(this);
		greenItem.addActionListener(this);
		blueItem.addActionListener(this);
		
		// pone visible la ventana
		//this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println(arg0.getActionCommand());
		
		if(arg0.getActionCommand().equalsIgnoreCase("Cerrar")){
			this.dispose();
		}
		
		if(arg0.getActionCommand().equalsIgnoreCase("Linea") ||
		   arg0.getActionCommand().equalsIgnoreCase("Rectangulo") ||
		   arg0.getActionCommand().equalsIgnoreCase("Circulo") ||
		   arg0.getActionCommand().equalsIgnoreCase("Curva")){
			objeto = arg0.getActionCommand();
			this.repaint();
		}
		
		if(arg0.getActionCommand().equalsIgnoreCase("Rojo") ||
		   arg0.getActionCommand().equalsIgnoreCase("Amarillo") ||
		   arg0.getActionCommand().equalsIgnoreCase("Verde") ||
		   arg0.getActionCommand().equalsIgnoreCase("Azul")){
			color = arg0.getActionCommand();
			this.repaint();
		}
	}
	
	public void paint( Graphics g ){
		super.paint(g);
		
		switch(color){
			case "Rojo":
				g.setColor(Color.RED);
				break;
			case "Amarillo":
				g.setColor(Color.YELLOW);
				break;
			case "Verde":
				g.setColor(Color.GREEN);
				break;
			case "Azul":
				g.setColor(Color.BLUE);
				break;		
		}
		
		switch(objeto){
			case "Linea":
				g.drawLine(200, 200, 300, 200);
				break;
				
			case "Rectangulo":
				g.fillRect(200, 200, 50, 50);
				break;
				
			case "Circulo":
				g.fillOval(200, 200, 80, 80);
				break;
				
			case "Curva":
				g.drawArc(200, 200, 100, 50, 0, 180);
				break;
		}
		/*Graphics2D g2 = (Graphics2D) g;
		
		switch(color){
			case "Rojo":
				g2.setPaint(Color.RED);
				break;
			case "Amarillo":
				g2.setPaint(Color.YELLOW);
				break;
			case "Verde":
				g2.setPaint(Color.GREEN);
				break;
			case "Azul":
				g2.setPaint(Color.BLUE);
				break;		
		}
		
		switch(objeto){
			case "Linea":
				g2.draw(new Line2D.Double(200, 100, 300, 100));
				break;
				
			case "Rectangulo":
				Rectangle2D.Double rect1 = new Rectangle2D.Double(50, 50, 50, 50);
				g2.fill(rect1);
				break;
				
			case "Circulo":
				Ellipse2D.Double elipse2 = new Ellipse2D.Double(200, 200, 80, 80);
				g2.fill(elipse2);
				break;
				
			case "Curva":
				g2.draw(new Line2D.Double(100, 100, 150, 300));
				break;
		}*/
		
		
		/*if(objeto.equalsIgnoreCase("Rectangulo"));	
			g2.draw(new Rectangle2D.Double(50, 50, 150, 300));*/
		
		//g2.draw(new Line2D.Double(150, 300, 250, 100));
		//g2.draw(new Rectangle2D.Double(50, 50, 150, 300));
		//g2.draw(new RoundRectangle2D.Double(90, 90, 150, 150, 10, 10));
		
	}
	
	
	public static void main(String[] args) {
		
		Menu menu = new Menu(500, 500, "Menu");
		menu.setVisible(true);
	}

}
