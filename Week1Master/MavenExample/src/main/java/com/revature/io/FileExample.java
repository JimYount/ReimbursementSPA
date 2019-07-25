package com.revature.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class FileExample {
	private static Logger log = Logger.getLogger(FileExample.class);
	public static void main(String[] args) {
		String filename = "samplefile.txt";
		writeCharacterStream(filename, "Hello, Alex");
		readCharacterStream(filename);
		readScanner(filename);
	}

	private static void readScanner(String filename) {
		try(FileInputStream stream = new FileInputStream(filename);
				Scanner scan = new Scanner(stream);) {
			while(scan.hasNextLine()) {
				System.out.println(scan.nextLine());
			}
		}catch (IOException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
	}

	private static void readCharacterStream(String filename) {
		try(FileReader reader = new FileReader(filename)) {
			int i;
			//i = reader.read(); //int holds all characters. EOF is -1
			while((i = reader.read())!= -1) {
				System.out.print((char) i);
			}
		} catch (IOException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
	}

	public static void writeCharacterStream(String filename, String message) {
		try(FileWriter writer = new FileWriter(filename, true)) {
			writer.write(message+"\n");
		} catch (IOException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
	}
}
