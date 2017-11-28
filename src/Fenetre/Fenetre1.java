package Fenetre;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Fenetre1 {

	public static void main(String[] args) {
		//get local graphics environment
		GraphicsEnvironment graphicsEnvironment=GraphicsEnvironment.getLocalGraphicsEnvironment();
		         
		//get maximum window bounds
		Rectangle maximumWindowBounds=graphicsEnvironment.getMaximumWindowBounds();
		// TODO Instancier une JFrame
		JFrame maFenetre = new JFrame();
		// TODO Afficher la Jframe
		maFenetre.setVisible(true);
		// TODO Définir la taille de la fenêtre
		maFenetre.setSize(maximumWindowBounds.width,maximumWindowBounds.height);
		// TODO Définit la fermeture de la fenêtre
		maFenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// TODO Définir le titre de la fenêtre
		maFenetre.setTitle("Ma première fenêtre");
		// TODO définir si l'utilisateur peut changer la taille de la fenêtre
		maFenetre.setResizable(true);
		// TODO centrer la fenêtre
		maFenetre.setLocationRelativeTo(null);
		// TODO afficher du texte
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		JLabel label = new JLabel("Ma première fenêtre");
		JButton bouton= new JButton("Test");
		panel.add(label);
		panel.add(bouton,BorderLayout.CENTER);
		maFenetre.add(panel);
		

	}

}
