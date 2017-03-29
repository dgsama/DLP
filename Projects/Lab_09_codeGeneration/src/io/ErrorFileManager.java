package io;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import error.Err;
import error.ErrorHandler;

public class ErrorFileManager {

	private String phase;
	private String file;
	private static ErrorFileManager instance = null;

	public static ErrorFileManager getInstance() {
		if (instance == null) {
			instance = new ErrorFileManager();
		}
		return instance;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}

	public void createErrorLog() throws IOException {
		List<Err> errors = ErrorHandler.getInstance().getErrorsList();
		String aux = parseName();
		FileWriter fW = new FileWriter("errorFiles/errors_" + aux + ".log");

		fW.write("\t\t" + new Date().toString() + "\n\n");

		fW.write("  The errors are in the " + phase + " phase.\n");
		fW.write("  There are " + errors.size() + " in the errors list.\n\n");

		int i = 0;
		for (Err each : errors) {
			i++;
			fW.write("\t+ " + i + ": " + each.getErrorMessage() + "in line [" + each.getLine() + "] column ["
					+ each.getColumn() + "]\n");
		}

		fW.close();
	}

	private String parseName() {
		String[] name = file.split("/");
		if (name.length == 1)
			return name[0];
		else
			return name[1];
	}

	public void setFile(String file) {
		this.file = file;
	}

}
