/**
 * 
 */
package edu.metrostate.ics340.p2.go1337;

/**
 * This class is used to represent a Node in a binary decoding tree used in the Huffman coding/decoding algorithm. It can
 * store a character data and frequency data, as well as have a left and right child node.
 * 
 * @author Grace O'Brien
 * Sunday, May 31st, 2020
 *
 */
public class Node implements Comparable<Node> {

	private Node left;
	private Node right;
	private Character characterData;
	private int frequencyData;
	
	/**
	 * Constructor method for Nodes that only contain frequency values.
	 * 
	 * @param frequencyData, frequency of a character occurrence
	 */
	public Node(int frequencyData)
	{
		left = null;
		right = null;
		characterData = null;
		this.frequencyData = frequencyData;
	}
	
	/**
	 * Constructor method for Nodes that hold a character and frequency value
	 * 
	 * @param characterData	Character, data held by Node
	 * @param frequencyData	Frequency, frequency of occurrence for character being held
	 */
	public Node(Character characterData, int frequencyData)
	{
		this(frequencyData);
		this.characterData = characterData;
	}
	
	/**
	 * Updates the left child node
	 * 
	 * @param left	Node, left child of this reference
	 */
	public void setLeftChild(Node left)
	{
		this.left = left;
	}
	
	/**
	 * Updates the right child node
	 * 
	 * @param right	Node, right child of this reference
	 */
	public void setRightChild(Node right)
	{
		this.right = right;
	}
	
	/**
	 * Updates the character value of the node
	 * 
	 * @param characterData	char, character for the node to be updated to
	 */
	public void setValue(Character characterData)
	{
		this.characterData = characterData;
	}
	
	/**
	 * Updates the frequency value of the node
	 * 
	 * @param frequencyData int, the new frequency value
	 */
	public void setFrequency(int frequencyData)
	{
		this.frequencyData = frequencyData;
	}
	
	/**
	 * Returns the node's frequency value
	 * 
	 * @return int, the node's frequency value
	 */
	public int getFrequency()
	{
		return frequencyData;
	}
	
	/**
	 * Returns the node's left child
	 * 
	 * @return TreeNode<Character>, the left child node
	 */
	public Node getLeftChild() {
		return left;
	}

	/**
	 * Returns the node's right child
	 * 
	 * @return TreeNode<Character>, the right child node
	 */
	public Node getRightChild() {
		return right;
	}

	/**
	 * Returns the node's character value
	 * 
	 * @return Character, the node's character value
	 */
	public Character getValue() {
		return characterData;
	}

	/**
	 * This method compares this node's frequency to the frequency of another node
	 * 
	 * @return  <0 if this node's frequency is less than the other one
	 * 		     0 if this node's frequency is equal to the other one
	 * 		 	>0 if this node's frequency is greater than the other one
	 */
	@Override
	public int compareTo(Node o) {
		return this.getFrequency() - o.getFrequency();
	}

}
