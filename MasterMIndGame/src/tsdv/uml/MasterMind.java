package tsdv.uml;

import java.util.*;

public class MasterMind {
	private static int defaultmaxAllowTime = 8;
	private static int QUIZZ_LENGTH = 4;
	private static char allCorrect = '*';
	private static char numberCorrect = '!';

	public enum State {
		PROGRESS, LOST, WIN
	}

	private String hiddenNumber;
	private State gameState;
	private int maxAllowTime;
	private ArrayList attemptList;
	private String correctAnswer = new String();

	public MasterMind() {
		gameState = State.PROGRESS;
		hiddenNumber = generateHiddenNumber();
		maxAllowTime = defaultmaxAllowTime;
		for (int i = 1; i <= QUIZZ_LENGTH; i++) {
			correctAnswer = correctAnswer + allCorrect;
		}
		attemptList = new ArrayList();
	}

	public String getHiddenNumber() {
		return hiddenNumber;
	}

	public boolean isProgress() {
		return gameState == State.PROGRESS;
	}

	public boolean isGameOver() {
		return gameState == State.LOST;
	}

	public boolean isWon() {
		return gameState == State.WIN;
	}

	private String generateHiddenNumber() {
		Random rand = new Random();
		return String.format("%04d", rand.nextInt(10000));
	}

	public String getResult() {
		// A ;
		Answers ans = (Answers) attemptList.get(attemptList.size() - 1);
		return ans.getResult();
	}

	public void evaluateResult(Answers Ans) {
		if (attemptList.size() < maxAllowTime) {
			Ans.setResult(matchResult(Ans.getAnswer()));
			this.attemptList.add(Ans);
		}
		changeGameStatus(Ans);
	}

	private void changeGameStatus(Answers paraAttmpt) {
		// attemptList.size() < maxAllowTime
		if (attemptList.size() < maxAllowTime) {
			if (paraAttmpt.getResult().equals(correctAnswer))
				gameState = State.WIN;
		} else
			gameState = State.LOST;
	}

	public String matchResult(String inputNumber) {
		char[] inChar;
		char[] hidChar;
		String rtnValue = new String();
		inChar = inputNumber.toCharArray();
		hidChar = hiddenNumber.toCharArray();
		for (int i = 0; i < hiddenNumber.length(); i++) {
			if (inChar[i] == hidChar[i]) {
				// mark the character is already used
				inChar[i] = '#';
				rtnValue = rtnValue + this.allCorrect;
			} else
				for (int j = 0; j < hiddenNumber.length(); j++) {
					if (inChar[i] == hidChar[j]) {
						// mark the character is already used
						inChar[j] = '#';
						rtnValue = rtnValue + this.numberCorrect;
						break;
					}
				}
		}
		return rtnValue;
	}
}

class Answers {
	private String answer;
	private String result;

	public Answers(String ans) {
		this.answer = new String(ans);
		result = new String();
	}

	public String getResult() {
		return this.result;
	}

	public String getAnswer() {
		return this.answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
