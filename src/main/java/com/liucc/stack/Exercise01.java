package com.liucc.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @ClassName: Exercise01
 * @Description: 中缀表达式实现计算器
 * @author: liuchuangchuang
 * @date: 2024/9/19 15:59
 * @version: V1.0
 */
public class Exercise01 {
    public static void main(String[] args) {
//        String expression = "3+2*6-2"; // 期望:13
        String expression = "5+15/3-2*2"; // 期望:6
        System.out.println(calculate(expression));
    }

    /**
     * 基于给定的计算表达式,返回计算后的结果值
     * 实现思路:
     * 3+2*6-2
     * 1、如果当前符号是数字，直接入数栈
     * 2、如果是操作符，且符号栈是空栈，则直接入栈
     * 3、如果是操作符，且符号栈不为空，则比较优先级
     *      如果当前操作符优先级高于符号栈栈顶元素，则直接入栈
     *      如果优先级小于等于符号栈栈顶元素，则
     *             数栈pop出两个元素，符号栈pop出一个元素，
     *             进行运算，将运算结果push到数栈。
     *             最后将当前操作符push到符号栈
     * 4、当计算表达式扫描完毕，依次从数栈和符号栈pop元素进行计算，结果值存入到数栈。
     * 5、到符号栈为空时，终止操作，此时数栈中的数字即是表达式的运算结果。
     * @param expression
     * @return
     */
    public static int calculate(String expression){
        expression = expression.replaceAll(" ", "");
        Deque<Integer> numStack = new ArrayDeque<>(); // 数栈
        Deque<Character> charStack = new ArrayDeque<>(); // 符号栈
        StringBuilder numBuilder = new StringBuilder();
        for (char cur: expression.toCharArray()){
            // 注意ASCII表的映射关系 48-0  57-9
            if (cur >= '0' && cur <= '9'){ // 入数栈
                numBuilder.append(cur);
                numStack.push(Integer.parseInt(numBuilder.toString()));// 入数栈
                numBuilder.setLength(0); // 重置
            }else {// 符号栈
                // 空栈,直接入栈
                if (charStack.isEmpty()){
                    charStack.push(cur);
                }else if (isHigher(cur, charStack.peek())){ //当前操作符优先级高,直接入栈
                    charStack.push(cur);
                }else { //优先级低
                    Integer num1 = numStack.pop();
                    Integer num2 = numStack.pop();
                    Character oper = charStack.pop();
                    int result = cal(num1, num2, oper);
                    // 计算结果入数栈
                    numStack.push(result);
                    // 最后将当前操作符push到符号栈
                    charStack.push(cur);
                }
            }
        }
        // 处理最后一个未被处理的数字
        if (numBuilder.length() >0){
            numStack.push(Integer.parseInt(numBuilder.toString()));// 入数栈
            numBuilder.setLength(0); // 重置
        }
        // 表达式扫描完毕,清算栈内元素
        while (!charStack.isEmpty()){
            Integer num1 = numStack.pop();
            Integer num2 = numStack.pop();
            Character oper = charStack.pop();
            int cal = cal(num1, num2, oper);
            numStack.push(cal);
        }
        return numStack.peek();
    }

    /**
     * 判断操作符号优先级高低
     * @param cur 当前遍历元素
     * @param topChar 符号栈栈顶元素
     * @return
     */
    public static boolean isHigher(char cur, char topChar){
        if ((cur == '*' || cur == '/') && (topChar == '+' || topChar == '-')){
            return true;
        }
        return false;
    }

    public static int cal(Integer c1, Integer c2, char oper){
        int result = 0;
        switch (oper){
            case '+':
                result = c2+c1;
                break;
            case '-':
                result = c2-c1;
                break;
            case '*':
                result = c2*c1;
                break;
            case '/':
                result = c2/c1;
                break;
        }
        return result;
    }
}
