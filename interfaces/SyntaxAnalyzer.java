package interfaces;

import java.util.LinkedList;

/**
 * COSC 455 Programming Languages: Implementation and Design.
 *
 * A Simple Syntax Analyzer adapted from Sebesta (2010) by Josh Dehlinger,
 * modified by Adam Conover (2012) and interfaced by Josh Dehlinger (2013).
 *
 * Note that these are not the only methods necessary to parse the BNF
 * grammar rules. You will likely need to add new methods to your implementation
 * of this interface.
 *
 */
public interface SyntaxAnalyzer {

	/**
	 * This method implements the BNF grammar rule for the document annotation.
	 * 
	 */
	void lolcode(LinkedList code);

	/**
	 * This method implements the BNF grammar rule for the head annotation.
	 * 
	 */
	void comment(LinkedList code);

	/**
	 * This method implements the BNF grammar rule for the head annotation.
	 * 
	 */
	void head(LinkedList code);

	/**
	 * This method implements the BNF grammar rule for the title annotation.
	 * 

	 */
	void title(LinkedList code);

	/**
	 * This method implements the BNF grammar rule for the paragraph annotation.
	 * 
	 */
	void paragraph(LinkedList code);

	/**
	 * This method implements the BNF grammar rule for the bold annotation.
	 * 
	 */
	void bold(LinkedList code);

	/**
	 * This method implements the BNF grammar rule for the italics annotation.
	 * 
	 */
	void italics(LinkedList code);

	/**
	 * This method implements the BNF grammar rule for the list annotation.
	 * 
	 */
	void list(LinkedList code);

	/**
	 * This method implements the BNF grammar rule for the item annotation.
	 * 
	 */
	void item(LinkedList code);

	/**
	 * This method implements the BNF grammar rule for the audio annotation.
	 * 
	 */
	void audio(LinkedList code);

	/**
	 * This method implements the BNF grammar rule for the video annotation.
	 * 
	 */
	void video(LinkedList code);

	/**
	 * This method implements the BNF grammar rule for the define annotation.
	 * 
	 */
	void define(LinkedList code);

	/**
	 * This method implements the BNF grammar rule for the use annotation.
	 * 
	 */
	void use(LinkedList code);
}
