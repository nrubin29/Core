package me.nrubin29.core.script;

public abstract class ScriptCommand {

	public abstract void parse(String[] args);
	
	public String argsToString(String[] args, int start) {
		StringBuffer buffer = new StringBuffer();
		
		for (int i = start; i < args.length; i++) buffer.append(args[i] + " ");
		
		return buffer.toString().trim();
	}
}