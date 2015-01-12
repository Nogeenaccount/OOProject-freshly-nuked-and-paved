package rest;

import javax.swing.JTextArea;

public class ThreadTest implements Runnable {

    private static JTextArea workSpace;

    @Override
    public void run() {
	String beginText = "Here is the progress of your match:\n\n";
	workSpace.setText(beginText);

	String advancedText = "You lost";
	beginText = beginText + advancedText;
	workSpace.setText(beginText);

	System.out.println("Thread has set 'ongoingMatchText'");
    }

    public static JTextArea getWorkSpace() {
	return workSpace;
    }

    public static void setWorkSpace(JTextArea workSpace) {
	ThreadTest.workSpace = workSpace;
    }
}
