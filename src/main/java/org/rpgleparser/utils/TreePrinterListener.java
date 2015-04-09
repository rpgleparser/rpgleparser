package org.rpgleparser.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.misc.Utils;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.tree.Trees;

public class TreePrinterListener implements ParseTreeListener {
    private final List<String> ruleNames;
    private final StringBuilder builder = new StringBuilder();
    Map<RuleContext,ArrayList<String>> stack = new HashMap<RuleContext,ArrayList<String>>();

    public TreePrinterListener(Parser parser) {
        this.ruleNames = Arrays.asList(parser.getRuleNames());
    }

    public TreePrinterListener(List<String> ruleNames) {
        this.ruleNames = ruleNames;
    }

    @Override
    public void visitTerminal(TerminalNode node) {
    	String text = Utils.escapeWhitespace(Trees.getNodeText(node, ruleNames), false);
    	if(text.startsWith(" ") || text.endsWith(" ")){
    		text = "'" + text + "'";
    	}
        stack.get(node.getParent()).add(text);
    }

    @Override
    public void visitErrorNode(ErrorNode node) {
        stack.get(node.getParent()).add(Utils.escapeWhitespace(Trees.getNodeText(node, ruleNames), false));
    }

    @Override
    public void enterEveryRule(ParserRuleContext ctx) {
    	if(!stack.containsKey(ctx.parent)){
    		stack.put(ctx.parent, new ArrayList<String>());
    	}
    	if(!stack.containsKey(ctx)){
    		stack.put(ctx, new ArrayList<String>());
    	}

    	final StringBuilder sb = new StringBuilder();
    	int ruleIndex = ctx.getRuleIndex();
        String ruleName;
        if (ruleIndex >= 0 && ruleIndex < ruleNames.size()) {
            ruleName = ruleNames.get(ruleIndex);
        }
        else {
            ruleName = Integer.toString(ruleIndex);
        }
        sb.append(ruleName);
        stack.get(ctx).add(sb.toString());
    }

    @Override
    public void exitEveryRule(ParserRuleContext ctx) {
    	ArrayList<String> ruleStack = stack.remove(ctx);
    	StringBuilder sb = new StringBuilder();
    	boolean brackit =ruleStack.size()>1; 
    	if(brackit){
    		sb.append("(");
    	}
    	sb.append(ruleStack.get(0));
    	for(int i=1; i<ruleStack.size(); i++){
    		sb.append(" ");
    		sb.append(ruleStack.get(i));
    	}
    	if(brackit){
        	sb.append(")");
    	}
    	if(sb.length() < 80){
    		stack.get(ctx.parent).add(sb.toString());
    	}else{
    		// Current line is too long, regenerate it using 1 line per item.
    		sb.setLength(0);
        	if(brackit){
        		sb.append("(");
        	}
        	if(!ruleStack.isEmpty()){
        		sb.append(ruleStack.remove(0)).append("\r\n");
        	}
        	while(!ruleStack.isEmpty()){
        		sb.append(indent(ruleStack.remove(0))).append("\r\n");
        	}
        	if(brackit){
        		sb.append(")");
        	}
       		stack.get(ctx.parent).add(sb.toString());
    	}
    	if(ctx.parent == null){
    		builder.append(sb.toString());
    	}
    }
    
    static String indent(String input){
    	return "  " + input.replaceAll("\r\n(.)","\r\n  $1");
    }

    @Override
    public String toString() {
        return builder.toString();
    }
    
    
}