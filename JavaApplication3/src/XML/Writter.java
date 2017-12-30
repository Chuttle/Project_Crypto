package XML;

import org.jdom2.output.*;
import org.jdom2.*;
import java.io.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Writter extends JDom1
{
	
	//Racine du XML
		static Element racine = new Element("data");
		
		//Création d'un nouveau document JDOM basé sur la racine précédement creée
		static org.jdom2.Document document = new Document(racine);
		
		 static String events[][] = {   { "01/01/2018", "08h00", "04h00", "Reunion"},
						{ "01/01/2018", "12h00", "02h00", "Repas"},
                                                { "01/01/2018", "14h00", "04h00", "Reunion2"}
                                            };
		

	
	public static void main(String[] args)
	{
		Element info = new Element("Info");
		racine.addContent(info);
		
		Element entrees = new Element("Entrees");
		racine.addContent(entrees);
		
		
		//
		// Ecriture dans le XML de chaque event contenu dans l'Array 
		//		
		for(int i=0; i<events.length; i++)
		{
			entrees.addContent(new Element("event")
						.setAttribute(new Attribute("duree", events[i][2]))
						.setAttribute(new Attribute("heure",events[i][1]))
						.setAttribute(new Attribute("date",events[i][0]))
						.addContent(new Element("details")
								.setText(events[i][3]))

					);
			
		}
		
		
		
		affiche(document);
		enregistre(document, "Voila.xml");
		
	}
	
}
