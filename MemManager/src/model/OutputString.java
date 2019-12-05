package model;

public class OutputString {
	private MemoryList memList;
	private String outputString;
	private final String WAITING_QUEUE_HEADER = "WAITING QUEUE\n--------------\n\n";
	private final String MEMORY_HEADER = "\n\tMEMORY\n--------------------------------\n";
	private final String DIVIDER = " *********************** ";

	public OutputString(MemoryList memList) {
		super();
		this.memList = memList;
		this.outputString = "";
//		buildWaitingListString();
		buildOutputString();
	}

	private void buildOutputString() {
		outputString += MEMORY_HEADER + DIVIDER + "0KB\n\n";
		int sizeInt = 0;
		for (int i = 0; i < memList.getList().size(); i++) {
			if (memList.getList().get(i).getProcessID() == 0) {
				sizeInt += memList.getList().get(i).getSize();
				outputString += "\tOS\t" + memList.getList().get(i).getSize() + "KB\n\n" + DIVIDER + sizeInt + "KB\n\n";

			} else if (memList.getList().get(i).getProcessID() == -1) {
				sizeInt += memList.getList().get(i).getSize();
				outputString += "\tEMPTY" + "\t" + memList.getList().get(i).getSize() + "KB\n\n" + DIVIDER + sizeInt
						+ "KB\n\n";
			} else {
				sizeInt += memList.getList().get(i).getSize();
				outputString += "\tP" + memList.getList().get(i).getProcessID() + "\t"
						+ memList.getList().get(i).getSize() + "KB\n\n" + DIVIDER + sizeInt + "KB\n\n";

			}
		}
	}

	public String getOutputString() {
		return outputString;
	}

	private void buildWaitingListString() {
		if (memList.getWaitingQueue().isEmpty()) {
			outputString += "EMPTY\n\n";
		}
		for (int i = 0; i < memList.getWaitingQueue().size(); i++) {
			outputString += "P" + memList.getWaitingQueue().get(i).getProcessID() + "\t"
					+ memList.getWaitingQueue().get(i).getSize() + "KB\n";
		}

	}

}
