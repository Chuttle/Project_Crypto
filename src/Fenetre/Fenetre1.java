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
		// TODO D�finir la taille de la fen�tre
		maFenetre.setSize(maximumWindowBounds.width,maximumWindowBounds.height);
		// TODO D�finit la fermeture de la fen�tre
		maFenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// TODO D�finir le titre de la fen�tre
		maFenetre.setTitle("Ma premi�re fen�tre");
		// TODO d�finir si l'utilisateur peut changer la taille de la fen�tre
		maFenetre.setResizable(true);
		// TODO centrer la fen�tre
		maFenetre.setLocationRelativeTo(null);
		// TODO afficher du texte
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		JLabel label = new JLabel("Ma premi�re fen�tre");
		JButton bouton= new JButton("Test");
		panel.add(label);
		panel.add(bouton,BorderLayout.CENTER);
		maFenetre.add(panel);
		

	}

}
