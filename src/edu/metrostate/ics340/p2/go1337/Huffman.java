package edu.metrostate.ics340.p2.go1337;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

import com.google.common.base.CharMatcher;

/**
 * This class is used to represent the Huffman encoder/decoder algorithm. The purpose of the algorithm is to increase
 * encoding compression efficiency. It does so by calculating the frequency of occurrence of each character, and creating a
 * binary tree based on the frequencies. From the binary tree codes are created based on the traversal order to find a character,
 * with left node paths represented by a 0 and right node paths represented by a 1.
 * 
 * @author Grace O'Brien
 * Sunday, May 31st, 2020
 *
 */
public class Huffman {
	
	private Map<Character, String> encodingMap;
	private Node decodingTree;
	
	private Huffman(Map<Character, String> encodingMap, Node decodingTree)
	{
		this.encodingMap = encodingMap;
		this.decodingTree = decodingTree;
	}
	
	/**
	 * This static factory method takes a file path and creates a Huffman object. In this method it extracts
	 * all the characters from the file path and gets their frequencies. It then creates a binary decoding tree, and creates
	 * an encoding map that maps characters to their codes. A Huffman object with the decoding tree and encoding map is then
	 * created.
	 * 
	 * @param filePath The text to be used to create the Huffman object
	 * @return	Huffman, a new instance of Huffman
	 */
	public static Huffman build(String filePath)
	{
		if(filePath == null)
			return null;
		
		try 
		{
			File file = new File(filePath);
			Scanner scan = new Scanner(file);
			String message = "";
			
			while(scan.hasNextLine())
				message = message + scan.nextLine() + "\n";
			
			message = message.trim().toLowerCase();
			
			scan.close();
			
			PriorityQueue<Node> frequencyQueue = new PriorityQueue<>();
			
			while(message.length() != 0)
			{
				char currentChar = message.charAt(0);
				int previousLength = message.length();
				
				message = CharMatcher.is(currentChar).removeFrom(message);
				frequencyQueue.add(new Node(currentChar, previousLength - message.length()));	
			}
			
			while(frequencyQueue.size() != 1)
			{
				Node left = frequencyQueue.poll();
				Node right = frequencyQueue.poll();
				
				Node newNode = new Node(left.getFrequency() + right.getFrequency());
				
				newNode.setLeftChild(left);
				newNode.setRightChild(right);
				
				frequencyQueue.add(newNode);
			}
			
			EncodingMap encodingMap = new EncodingMap();
			encodingMap.createMap(frequencyQueue.peek());
			return new Huffman(encodingMap.getMap(), frequencyQueue.poll());

		}
		catch (FileNotFoundException e)
		{
			return null;
		}
	}
	
	/**
	 * This method takes in a string, and then encodes it using the encoding map.
	 * 
	 * @param text The string to be encoded
	 * @return	String, the encoded string message
	 */
	public String encode(String text)
	{
		String code = "";
		text = text.toLowerCase();
		
		for(int i = 0; i < text.length(); i++)
		{
			Character currentChar = text.charAt(i);
			
			if(encodingMap.containsKey(currentChar))
				code = code + encodingMap.get(currentChar) + " ";
			else
				throw new IllegalArgumentException("Illegal Character in Text: " + currentChar);
		}
		return code.trim();
	}
	
	/**
	 * This method decodes a string that has been encoded using the Huffman encoder
	 * 
	 * @param code	The string to be decoded
	 * @return	String, the decoded message
	 */
	public String decode(String code)
	{
		String decodedText = "";
		Node currentNode = decodingTree;
		String charCode = "";
		
		for(int i = 0; i <= code.length(); i++)
		{
			if(i != code.length() && code.charAt(i) != ' ')
				charCode += code.charAt(i);
			else
			{
				decodedText += traverseDecodingTree(charCode, currentNode);
				currentNode = decodingTree;
				charCode = "";
			}
		}
		return decodedText;
	}
	
	private char traverseDecodingTree(String charCode, Node currentNode)
	{
		for(int j = 0; j < charCode.length(); j++)
		{
			if(charCode.charAt(j) == '0' && currentNode.getLeftChild() != null)
				currentNode = currentNode.getLeftChild();
			else if(charCode.charAt(j) == '1' && currentNode.getRightChild() != null)
				currentNode = currentNode.getRightChild();
			else
				throw new IllegalArgumentException("Unrecognized code: " + charCode);
			
			if(j == charCode.length() - 1)
			{
				if(currentNode.getValue() != null)
					return currentNode.getValue();
			}
		}
		throw new IllegalArgumentException("Unrecognized code: " + charCode);
	}
	
	/**
	 * This method returns the encoding map for the referenced Huffman object
	 * 
	 * @return Map<Character, String>, the encoding map for the referenced Huffman object
	 */
	public Map<Character, String> getEncodingMap()
	{		
		return encodingMap;
	}
	
	/**
	 * This method returns the decoding tree for the referenced Huffman object
	 * 
	 * @return TreeNode<Character>, the root to the binary decoding tree for the referenced Huffman object
	 */
	public Node getDecodingTree()
	{
		return decodingTree;
	}

}
