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

        // 1+((2+3)*4)-5 中缀==>后缀表达式 1 2 3 + 4 * + 5 -
        String infixStr = "1+((2+3)*4)-5";
        List<String> infixList = infixStr2List(infixStr);
        System.out.println("中缀表达式List=" + infixList);
        // TODO 中缀表达式转后缀表达式
        List<String> suffixList = infix2suffixList(infixList);
        System.out.println("后缀表达式List=" + suffixList);
    }

    /**
     * 中缀表达式转为后缀表达式
     * <p>
     * 实现思路：
     * 1）初始化两个栈：运算符栈s1和储存中间结果的栈s2；
     * 2）从左至右扫描中缀表达式；
     * 3）遇到操作数时，将其压s2；
     * 4）遇到运算符时，比较其与s1栈顶运算符的优先级：
     * 1.如果s1为空，或栈顶运算符为左括号“（”，则直接将此运算符入栈；
     * 2.否则，若优先级比栈顶运算符的高，也将运算符压入s1；
     * 3.否则，将s1栈顶的运算符弹出并压入到s2中，再次转到（4—1）与s1中新的栈顶运算符相比较；
     * 5)遇到括号时：
     * （1）如果是左括号“（”，则直接压入s1
     * （2）如果是右括号“）”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
     * 6）重复步骤2至5，直到表达式的最右边
     * 7）将s1中剩余的运算符依次弹出并压入s2
     * 8）依次弹出s2中的元素并输出，结果的逆序即为中缀表达式对应的后缀表达式
     *
     * @param infixList 中缀表达式list
     * @return
     */
    public static List<String> infix2suffixList(List<String> infixList) {
        Deque<String> s1 = new ArrayDeque<>();
        List<String> s2 = new ArrayList<>();
        for (String s : infixList) {
            // [遇到操作数] 遇到操作数时，将其压s2；
            if (s.matches("\\d+")) {
                s2.add(s);
            } else if (")".equals(s)) {
                // [遇到括号] 如果是右括号“）”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
                while (!s1.isEmpty() && !")".equals(s1.peek())){
                    s2.add(s1.pop());
                }
                // 弹出左括号
                s1.pop();
            } else {
                // [遇到操作符]
                while (true) {
                    // 如果s1为空，或栈顶运算符为左括号“（”，则直接将此运算符入栈
                    if (s1.isEmpty() || "(".equals(s1.peek())) {
                        s1.push(s);
                        break;
                        // 否则，若优先级比栈顶运算符的高，也将运算符压入s1；
                    } else if (!s1.isEmpty() && Operation.checkPriority(s, s1.peek())) {
                        s1.push(s);
                        break;
                    } else {
                        // 否则，将s1栈顶的运算符弹出并压入到s2中，再次转到（4—1）与s1中新的栈顶运算符相比较；
                        s2.add(s1.pop());
                    }
                }

            }
        }
        // 将s1中剩余的运算符依次弹出并压入s2
        while (!s1.isEmpty()){
            s2.add(s1.pop());
        }
        return s2;
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

class Operation {
    private static Map<String, Integer> map = new HashMap<>();
    private static String ADD = "+";
    private static String SUB = "-";
    private static String MUL = "*";
    private static String DIV = "/";

    static {
        map.put(ADD, 1);
        map.put(SUB, 1);
        map.put(MUL, 2);
        map.put(DIV, 2);
    }

    /**
     * 返回两个操作符的优先级，true代表第一个操作符优先级高
     *
     * @param oper1
     * @param oper2
     * @return
     */
    public static boolean checkPriority(String oper1, String oper2) {
        try {
            boolean result = map.get(oper1) > map.get(oper2);
        } catch (Exception e) {
            throw new RuntimeException("不是操作运算符");
        }
        return map.get(oper1) > map.get(oper2);
    }
}