package com.liucc.stack;

import java.util.*;

/**
 * 逆波兰表达式
 */
public class PolandNotation {
    public static void main(String[] args) {

//        String calculate = "5 1 2 + 4 * + 3 -"; // 14
//        List<String> list = getList(calculate);
//        System.out.println("list= " + list);
//        int res = calculate(list);
//        System.out.println("res=" + res);

        // 1+((2+3)*4)-5 中缀==>后缀表达式
        String infixStr = "1+((2+3)*4)-5";
        List<String> infixList = infixStr2List(infixStr);
        System.out.println("中缀表达式List=" + infixList);
        // TODO 中缀表达式转后缀表达式
    }

    /**
     * 中缀表达式转为List，注意多位数的情况
     *
     * @param infixStr 中缀表达式
     * @return
     */
    public static List<String> infixStr2List(String infixStr) {
        List<String> infixList = new ArrayList<>();
        int index = 0;
        String str = "";
        char c = ' ';
        do {
            // 如果不是数字直接添加到list中
            c = infixStr.charAt(index);
            if (c < 48 || c > 57) {
                infixList.add("" + c);
                index++; //指针后移
            } else {
                // 是数字
                str = ""; // 重置str
                while (index < infixStr.length() && infixStr.charAt(index) >= 48 && infixStr.charAt(index) <= 57) {
                    str = str + infixStr.charAt(index); // 组合多位数
                    index++;
                }
                infixList.add(str);
            }

        } while (index < infixStr.length());
        return infixList;
    }


    /**
     * 求后缀表达式的结果值
     *
     * @param calculate 后缀表达式
     * @return 计算结果值
     */
    public static List<String> getList(String calculate) {
        List<String> list = new ArrayList<>();
        String[] split = calculate.split(" ");
        for (String ele : split) {
            list.add(ele);
        }
        return list;
    }

    public static int calculate(List<String> list) {
        Deque<String> stack = new LinkedList<>();
        for (String s : list) {
            // 是数字，入堆栈
            if (s.matches("\\d+")) {
                stack.push(s);
                continue;
            }
            // 操作符，pop两个数进行运算，运算结果入堆栈
            Integer num1 = Integer.parseInt(stack.pop());
            Integer num2 = Integer.parseInt(stack.pop());
            int res = 0;
            switch (s) {
                case "+":
                    res = num2 + num1;
                    break;
                case "-":
                    res = num2 - num1;
                    break;
                case "*":
                    res = num2 * num1;
                    break;
                case "/":
                    res = num2 / num1;
                    break;
                default:
                    throw new RuntimeException("运算符有误！");
            }
            stack.push("" + res);
        }
        return Integer.parseInt(stack.peek());
    }

}
