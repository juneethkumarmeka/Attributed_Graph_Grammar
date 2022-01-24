/*******************************************************************************
 * <copyright>
 * Copyright (c) 1995, 2015 Technische Universität Berlin. All rights reserved. 
 * This program and the accompanying materials are made available 
 * under the terms of the Eclipse Public License v1.0 which 
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * </copyright>
 *******************************************************************************/
package agg.attribute.parser.javaExpr;

/**
 * Attention! This class is not generated by jjtree/javacc.
 *@author $Author: olga $
 *@version $Id: ObjectConstNode.java,v 1.8 2010/09/23 08:15:01 olga Exp $
 */

import java.util.Vector;

public class ObjectConstNode extends SimpleNode {

	static final long serialVersionUID = 1L;

	protected Object obj;

	public ObjectConstNode(String id) {
		super(id);
	}

	public static Node jjtCreate(String id) {
		return new ObjectConstNode(id);
	}

	public void checkContext() {
		setNodeClass(this.obj.getClass());
	}

	protected boolean isConstantExpr() {
		return true;
	}

	public void interpret() {
//		stack[top] = obj;
		if (top >= stack.size())
			stack.add(this.obj);
		else
		stack.set(top, this.obj);
	}

	public String getString() {
		return this.obj.toString();
	}

	public Node copy() {
		Node copy = super.copy();
		((ObjectConstNode) copy).obj = this.obj;
		return copy;
	}

	public void getAllVariablesinExpression(Vector<String> v) {
	}
}
/*
 * $Log: ObjectConstNode.java,v $
 * Revision 1.8  2010/09/23 08:15:01  olga
 * tuning
 *
 * Revision 1.7  2010/07/29 10:09:22  olga
 * Array stack changed to Vector stack
 *
 * Revision 1.6  2010/03/31 21:10:49  olga
 * tuning
 *
 * Revision 1.5  2010/03/18 18:16:14  olga
 * loop bug fixed
 *
 * Revision 1.4  2007/11/01 09:58:17  olga
 * Code refactoring: generic types- done
 *
 * Revision 1.3  2007/09/10 13:05:47  olga
 * In this update:
 * - package xerces2.5.0 is not used anymore;
 * - class com.objectspace.jgl.Pair is replaced by the agg own generic class agg.util.Pair;
 * - bugs fixed in:  usage of PACs in rules;  match completion;
 * 	usage of static method calls in attr. conditions
 * - graph editing: added some new features
 * Revision 1.2 2006/01/16 09:37:01 olga Extended
 * attr. setting
 * 
 * Revision 1.1 2005/08/25 11:56:52 enrico *** empty log message ***
 * 
 * Revision 1.1 2005/05/30 12:58:01 olga Version with Eclipse
 * 
 * Revision 1.1.1.1 2002/07/11 12:17:04 olga Imported sources
 * 
 * Revision 1.4 2000/05/17 11:57:10 olga Testversion an Gabi mit diversen
 * Aenderungen. Fehler sind moeglich!!
 * 
 * Revision 1.3 2000/04/05 12:11:07 shultzke serialVersionUID aus V1.0.0
 * generiert
 * 
 * Revision 1.2 2000/03/14 10:59:55 shultzke Transformieren von Variablen auf
 * Variablen sollte jetzt funktionieren Ueber das Design der Copy-Methode des
 * abstrakten Syntaxbaumes sollte unbedingt diskutiert werden.
 * 
 * Revision 1.1 2000/03/13 13:57:47 shultzke Erweitert den abstrakten Syntaxbaum
 * um beliebige konstante Objekte. Diese Erweiterung erfolgt allerdings OHNE die
 * Grammatik zu aendern.
 * 
 */