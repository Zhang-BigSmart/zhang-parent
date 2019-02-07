package com.zhang.practice.offere;

/**
 * @ClassName Power
 * @Description:
 * @Author: zhangzh
 * @Date 2019/1/19 9:12
 */
public class Power {

    /**
     * 1.全面考察指数的正负、底数是否为零等情况。
     * 2.写出指数的二进制表达，例如13表达为二进制1101。
     * 3.举例:10^1101 = 10^0001*10^0100*10^1000。
     * 4.通过&1和>>1来逐位读取1101，为1时将该位代表的乘数累乘到最终结果。
     */
    public double power(double base, int n) {
        double res = 1,curr = base;
        int exponent;
        if(n>0){
            exponent = n;
        }else if(n<0){
            if(base==0)
                throw new RuntimeException("分母不能为0");
            exponent = -n;
        }else{
            return 1;
        }
        while(exponent!=0){
            if((exponent&1)==1)
                res*=curr;
            // 翻倍
            curr *= curr;
            // 右移一位
            exponent>>=1;
        }
        return n>=0?res:(1/res);
    }

    public static void main(String[] args) {
        Power power = new Power();
        System.out.println(power.power(2.0,3));

    }
}
