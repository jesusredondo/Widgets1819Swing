package Redondo_Garcia_Jesus;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Clase que representa un diálogo selector de color.
 * Para utilizar esta clase crea un listener del estilo:
 * {@code  DialogSelectorColor dialog = new DialogSelectorColor(bDialogo, panelColor.getBackground());
			dialog.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosed(WindowEvent arg0) {
					System.out.println(dialog.getColor());
				}
			});	}
 * La clase está preparada para que pueda funcionar sobre cualquier componente.
 * @author Jesus Redondo García
 *
 */
public class DialogSelectorColor extends JDialog implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	//Referencia al selectorColor:	
	
	JLabel titulo;
	JLabel lRojo;
	JLabel lVerde;
	JLabel lAzul;
	
	//Sliders:
	JSlider sliderRojo;
	JSlider sliderVerde;
	JSlider sliderAzul;
	
	//ColorInicial:
	Color color = Color.BLUE;
	
	


	//BotonAceptar
	JButton bAceptar;
	
	//MostradorColor:
	JLabel mostradorColor;

	public DialogSelectorColor(JComponent componente, Color colorInicial ) {
		super();
		setModal(true); // PARA HACERLO MODAL
		setBounds((int)componente.getLocationOnScreen().getX(),(int)componente.getLocationOnScreen().getY(), 300, 500); //Dónde empieza el Dialog y qué tamaño tiene
		this.color = colorInicial;
		anadirElementos();
		anadirListeners();
	}
	
	/**
	 * Método que añade todos los elementos al Dialog. Se codifica como un JFrame
	 */
	private void anadirElementos(){
		this.setLayout(new GridBagLayout());
		
		//PANEL PRINCIPAL
		//Titulo
		titulo = new JLabel("Selecciona tu color:");
		GridBagConstraints settings = new GridBagConstraints();
		settings.gridx = 0;
		settings.gridy = 0;
		settings.insets = new Insets(20, 0, 50, 0);
		add(titulo,settings);
		
		//Rojo
		lRojo = new JLabel("Nivel de rojo:");
		settings = new GridBagConstraints();
		settings.gridx = 0;
		settings.gridy = 1;
		add(lRojo,settings);
		
	
		sliderRojo = new JSlider(JSlider.HORIZONTAL, 0, 255, color.getRed());
		sliderRojo.setMinorTickSpacing(5);
		sliderRojo.setMajorTickSpacing(255);
		sliderRojo.setPaintTicks(true);
		sliderRojo.setPaintLabels(true);
		settings = new GridBagConstraints();
		settings.gridx = 0;
		settings.gridy = 2;
		add(sliderRojo,settings);

		
		//Verde
		lVerde = new JLabel("Nivel de Verde:");
		settings = new GridBagConstraints();
		settings.gridx = 0;
		settings.gridy = 3;
		add(lVerde,settings);
		
	
		sliderVerde = new JSlider(JSlider.HORIZONTAL, 0, 255, color.getGreen());
		sliderVerde.setMinorTickSpacing(5);
		sliderVerde.setMajorTickSpacing(255);
		sliderVerde.setPaintTicks(true);
		sliderVerde.setPaintLabels(true);
		settings = new GridBagConstraints();
		settings.gridx = 0;
		settings.gridy = 4;
		add(sliderVerde,settings);
		
		
		//Azul
		lAzul = new JLabel("Nivel de azul:");
		settings = new GridBagConstraints();
		settings.gridx = 0;
		settings.gridy = 5;
		add(lAzul,settings);
		
	
		sliderAzul = new JSlider(JSlider.HORIZONTAL, 0, 255, color.getBlue());
		sliderAzul.setMinorTickSpacing(5);
		sliderAzul.setMajorTickSpacing(255);
		sliderAzul.setPaintTicks(true);
		sliderAzul.setPaintLabels(true);
		settings = new GridBagConstraints();
		settings.gridx = 0;
		settings.gridy = 6;
		add(sliderAzul,settings);
		
		/*
		System.out.println("----------\n"
				+ "ROJO: "+sliderRojo.getValue()+"\n"
				+ "VERDE:"+sliderVerde.getValue()+"\n"
				+ "AZUL: "+sliderAzul.getValue()+"\n"
				);
		*/
		
		
		//MostradorColor:
		mostradorColor = new JLabel();
		mostradorColor.setOpaque(true);
		mostradorColor.setBackground(new Color(sliderRojo.getValue(), sliderVerde.getValue(), sliderAzul.getValue()));
		mostradorColor.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		settings = new GridBagConstraints();
		settings.gridx = 0;
		settings.gridy = 7;
		settings.insets = new Insets(20, 0, 20, 0);
		settings.ipady = 40;
		settings.ipadx = 150;
		add(mostradorColor,settings);
		
		//bAceptar
		bAceptar = new JButton("Aceptar");
		settings = new GridBagConstraints();
		settings.gridx = 0;
		settings.gridy = 8;
		settings.insets = new Insets(20, 0, 20, 0);
		add(bAceptar,settings);
		
	}
	
	/**
	 * Método que añade todos los listeners al Dialog
	 */
	private void anadirListeners(){
		ChangeListener listenerSliders = new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				mostradorColor.setBackground(new Color(sliderRojo.getValue(), sliderVerde.getValue(), sliderAzul.getValue()));
				color = mostradorColor.getBackground();
			}
	    };
	    
	    sliderAzul.addChangeListener(listenerSliders);
	    sliderRojo.addChangeListener(listenerSliders);
	    sliderVerde.addChangeListener(listenerSliders);
	
	
	    //Botón Aceptar:
	    bAceptar.addActionListener(this);
	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(DialogSelectorColor.this.color);
		this.dispose();
	}
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}


