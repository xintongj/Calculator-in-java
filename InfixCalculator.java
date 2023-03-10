package A2;

import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;

import javax.swing.*;

import java.util.*;

public class InfixCalculator extends JFrame implements ActionListener
{
	String[] arr = new String[19];
	static Boolean flag=false;
	int counter = 0;
	static JTextField text = new JTextField(16);
	static String s0, s, display="", res;
	int result=0;
	static BigDecimal var;
	static String d;//for the infix stack function
	
	
	InfixCalculator(){
		s0 = "";
	}
	
	public static BigDecimal calc(String[] ch) {
		
		//System.out.print(Arrays.toString(ch));
		Queue<BigDecimal> operand = new ArrayDeque<BigDecimal>();
		Queue<String> operator = new ArrayDeque<String>();
		int len=strlen(ch);
		for(int i=0;i<len;i++) {
			//System.out.println(Arrays.toString(ch));
			if(ch[i]!=null) {
				//if input is digit, push in operand Stack, if input is operator, push in operator stack
				if(ch[i].matches("\\d")||ch[i].matches("\\.")) {
					if(!flag) {
						s0+=ch[i];
					}
					else { 
						s0=ch[i];
						flag=false;
					}
					if(i==len-1){
						operand.add(BigDecimal.valueOf(Double.parseDouble(s0)));
					}
					else
					if(!ch[i+1].matches("\\d")&&!ch[i+1].matches("\\.")) {
						operand.add(BigDecimal.valueOf(Double.parseDouble(s0)));
					}
				}
				else{				
					if(i<ch.length) {
						flag=true;
						switch (ch[i]) {
						case "+":operator.add("+");
						break;
						case "-":operator.add("-");
						break;
						case "*":operator.add("*");
						break;
						case "/":operator.add("/");
						break;
						default:
							System.out.println("bad input");								
						}
					}
				}		
			}
		}
		//calculate here
		while(!operator.isEmpty()&&!operand.isEmpty()) {
			BigDecimal opn=operand.remove();
			String opr=operator.remove();
			if(!operator.isEmpty()) {
				if((operator.peek()=="*"||operator.peek()=="/")&&(opr=="+"||opr=="-")) {
					operator.add(opr);
					operand.add(opn);
				}	
				else {
					BigDecimal var = operand.remove();
					switch (opr) {
					case "+":((ArrayDeque<BigDecimal>) operand).addFirst(opn.add(var));
					break;
					case "-":((ArrayDeque<BigDecimal>) operand).addFirst(opn.subtract(var));
					break;
					case "*":((ArrayDeque<BigDecimal>) operand).addFirst(opn.multiply(var));
					break;
					case "/":((ArrayDeque<BigDecimal>) operand).addFirst(opn.divide(var));
					break;		
					}
				}
			}
			else {
				BigDecimal var = operand.remove();
				switch (opr) {
				case "+":((ArrayDeque<BigDecimal>) operand).addFirst(opn.add(var));
				break;
				case "-":((ArrayDeque<BigDecimal>) operand).addFirst(opn.subtract(var));
				break;
				case "*":((ArrayDeque<BigDecimal>) operand).addFirst(opn.multiply(var));
				break;
				case "/":((ArrayDeque<BigDecimal>) operand).addFirst(opn.divide(var));
				break;
				}
			}
				
		}
		//System.out.println(Arrays.toString(operand.toArray()));
		//System.out.println(Arrays.toString(operator.toArray()));
		return operand.peek();
	}
	
	private static int strlen(String[] ch) {
		int i=0;
		for(i=0;i<ch.length;) {
			if(ch[i]!=null) {
				i++;
			}
			else return i;
		}
		return i;
	}

	public void actionPerformed(ActionEvent e) {				
		//This is for operands up to 10
		if(e.getActionCommand()!="=") {
			s = e.getActionCommand();
			arr[counter] = s;
			display += s;
			text.setText(display);
			counter++;
		}
		else {
			text.setText(display+"="+calc(arr));
		}	
	}
	
	
	public static void main(String[] args) {
	
		JFrame frame = new JFrame("Calculator");
		JPanel mainPanel = new JPanel();
		JPanel panelText = new JPanel();
		JPanel panel = new JPanel();
		InfixCalculator c = new InfixCalculator();
		text.setEditable(false);

		JButton button0 = new JButton("0");
		JButton button1 = new JButton("1");
		JButton button2 = new JButton("2");
		JButton button3 = new JButton("3");
		JButton button4 = new JButton("4");
		JButton button5 = new JButton("5");
		JButton button6 = new JButton("6");
		JButton button7 = new JButton("7");
		JButton button8 = new JButton("8");
		JButton button9 = new JButton("9");
		JButton buttonPlus = new JButton("+");
		JButton buttonMinus = new JButton("-");
		JButton buttonMult = new JButton("*");
		JButton buttonDev = new JButton("/");
		JButton buttonDot = new JButton(".");
		JButton buttonEq = new JButton("=");
		
		button0.addActionListener(c);
		button1.addActionListener(c);
		button2.addActionListener(c);
		button3.addActionListener(c);
        button4.addActionListener(c);
        button5.addActionListener(c);
        button6.addActionListener(c);
        button7.addActionListener(c);
        button8.addActionListener(c);
        button9.addActionListener(c);
        buttonPlus.addActionListener(c);
        buttonMinus.addActionListener(c);
        buttonMult.addActionListener(c);
        buttonDev.addActionListener(c);
        buttonDot.addActionListener(c);
        buttonEq.addActionListener(c);
		
        panelText.add(text);
		panel.add(button1);
		panel.add(button2);
		panel.add(button3);
		panel.add(buttonPlus);
		panel.add(button4);
		panel.add(button5);
		panel.add(button6);
		panel.add(buttonMinus);
		panel.add(button7);
		panel.add(button8);
		panel.add(button9);
		panel.add(buttonMult);
		panel.add(button0);
		panel.add(buttonDot);
		panel.add(buttonEq);
		panel.add(buttonDev);
		
		frame.setSize(300,300);
		frame.setLayout(new FlowLayout());
		panel.setLayout(new GridLayout(4,3));
		frame.add(panelText);	
		frame.add(panel);			
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setSize(200,200);
		frame.setVisible(true);
	    
		
		
		//String[] test= {"11"," ","*"," ","2"," ","-"," ","15"};
	    //System.out.println("11 * 2 - 15 = "+calc(test));
	}
}

