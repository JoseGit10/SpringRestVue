package com.sv.crud.util;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Utilidades {

	private static final Logger LOG = LoggerFactory.getLogger(Utilidades.class);
	
	public static String crearAlias(String nombre) {
		LOG.info("Iniciando creacion alias");
		char[] letras = new char[2];
		String[] nombreDiv = nombre.split(" ");
		LOG.info("Tamaño del nombre dividido "+ nombreDiv.length);
		for(int i=0;i<=nombreDiv.length;i++) {
			if(i==0) {
				letras[0] = nombreDiv[i].charAt(0);
				LOG.info("Letra guardada "+letras[0]);
			}
			else if(i==1) {
				letras[1] = nombreDiv[i].charAt(0);
				LOG.info("Letra guardada "+letras[1]);
			}
		}
		
		String unirLetra = ""+letras[0]+""+letras[1];
		
		LOG.info("Letras unidas "+unirLetra);
		
		unirLetra += String.valueOf(numeroAlAzar());
		
		LOG.info("Letras y numeros "+unirLetra);
		
		return unirLetra;
	
	}
	
	public static Integer numeroAlAzar() {
		LOG.info("Creando número al azar");
		
		Random ram = new Random();
		Integer numero = ram.nextInt(90000)+ 10000;
		
		LOG.info("numero creado al hazar "+numero);
		return numero;
	}
	
}
