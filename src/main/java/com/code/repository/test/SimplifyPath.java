package com.code.repository.test;

import java.util.Stack;

/**
 * 以 Unix 风格给出一个文件的绝对路径，你需要简化它。
 * 在 Unix 风格的文件系统中，一个点（.）表示当前目录本身；此外，两个点 （..） 表示将目录切换到上一级（指向父目录）；两者都可以是复杂相对路径的组成部分。更多信息请参阅：Linux / Unix中的绝对路径 vs 相对路径
 *
 * 请注意，返回的规范路径必须始终以斜杠 / 开头，并且两个目录名之间必须只有一个斜杠 /。最后一个目录名（如果存在）不能以 / 结尾。此外，规范路径必须是表示绝对路径的最短字符串。
 *
 * 输入："/home/"
 * 输出："/home"
 * 解释：注意，最后一个目录名后面没有斜杠。
 *
 * 输入："/../"
 * 输出："/"
 * 解释：从根目录向上一级是不可行的，因为根是你可以到达的最高级。
 *
 * 输入："/home//foo/"
 * 输出："/home/foo"
 * 解释：在规范路径中，多个连续斜杠需要用一个斜杠替换。
 *
 * 输入："/a/./b/../../c/"
 * 输出："/c"
 *
 *
 */
public class SimplifyPath {

    public static void main(String[] args) {
        String cm = "/a/../../b/../c//.//";
        System.out.println(simplifyPath(cm));
    }

    public static String simplifyPath(String path) {
        Stack<String> s = new Stack<String>();
        String[] cmArray = path.split("/");
        for(int i=0;i<cmArray.length;i++){
            if(cmArray[i].equals(".") || cmArray[i].equals("")){
            }else if(cmArray[i].equals("..")){
                if(s.size()>0){
                    s.pop();
                }
            }else{
                s.push(cmArray[i]);
            }
        }
        String result = "";
        if(s.size()==0){
            return "/";
        }
        while(s.size()>0){
            result="/"+s.pop()+result;
        }
        return result;
    }
}
