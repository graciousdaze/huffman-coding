package edu.metrostate.ics340.p2.go1337;

import java.util.HashMap;
import java.util.Map;

/**
 * This class creates an Encoding Map based on the Huffman encoder/decoder algorithm. It creates a map that holds key value pairs
 * of characters to their codes. The codes are created based on the traversal of the binary decoding tree, with 0's indicating
 * left node paths and 1's indicating right node paths.
 * 
 * @author Grace O'Brien
 * Sunday, May 31st, 2020
 *
 */
public class EncodingMap {
	
	private Map<Character, String> encodingMap;
	
	/**
	 * Constructor, creates a new Map<Character,String>() object
	 */
	public EncodingMap()
	{
		encodingMap = new HashMap<Character, String>();
	}
	
	/**
	 * This method takes the root of a decoding tree. It then traverses through the tree in order to create an encoding map
	 * for the characters in the tree. 0's indicate a left path node and 1's indicate a right path node.
	 * 
	 * @param root Node, the root of the decoding binary tree
	 */
	public void createMap(Node root)
	{
		if(root != null && (root.getLeftChild() != null || root.getRightChild() != null))
			createMap(root, "");
	}
	
	private void createMap(Node currentNode, String charCode)
	{
		if(currentNode.getLeftChild() != null)
		{
			charCode += "0";
			
			if(currentNode.getLeftChild().getValue() != null)
				encodingMap.put(currentNode.getLeftChild().getValue(), charCode);
			else
				createMap(currentNode.getLeftChild(), charCode);
		}
		
		charCode = charCode.substring(0, charCode.length() - 1);
		
		if(currentNode.getRightChild() != null)
		{
			charCode += "1";
			
			if(currentNode.getRightChild().getValue() != null)
				encodingMap.put(currentNode.getRightChild().getValue(), charCode);
			else
				createMap(currentNode.getRightChild(), charCode);
		}
		charCode = charCode.substring(0, charCode.length() - 1);
	}
	
	/**
	 * Returns a map of characters and their codes
	 * 
	 * @return Map<Character, String>, a map of characters and their codes
	 */
	public Map<Character, String> getMap()
	{
		return encodingMap;
	}

}
