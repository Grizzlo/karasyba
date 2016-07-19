package com.grizzly;

import java.util.Scanner;
import java.math.*;

/**
 * Created by grizzly on 4/20/16.
 */
public class Kathuba {
    //int max_length =308;
    Kathuba(){
        Scanner sc1 = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        System.out.println("Enter X and Y");
        String x = sc1.nextLine();
        String y = sc2.nextLine();
        BigInteger x1 = new BigInteger(x);
        BigInteger y1 = new BigInteger(y);
        System.out.println(x);
        System.out.println(y);
        System.out.println(recurce_sol(x,y));
        // double sol=Double.parseDouble(x)*Double.parseDouble(y);
        System.out.println("sol1 = "+ x1.multiply(y1));

    }
    BigInteger recurce_sol(String x, String y){//change data type
        BigInteger sol= new BigInteger("0");
        String [] ab=new String[2];
        String [] cd=new String[2];
        String [] xy=new String[2];
        if ((x.length()>2)||(y.length()>2)){ //врахувати випадок 1-9 10-99

            if ((two(x.length())==false)||(two(y.length())==false)||(x.length()!=y.length())){
                xy=add_zer(x,y);// дописувати нулі
            }
            else {
                xy[0]=x;
                xy[1]=y;
            }
            ab=decompos(xy[0]);
            cd=decompos(xy[1]);
            int lngth = xy[0].length();
            System.out.println("Length= "+lngth);
            System.out.println("Decompos a= "+ab[0]);
            System.out.println("Decompos b= "+ab[1]);
            System.out.println("Decompos c= "+cd[0]);
            System.out.println("Decompos d= "+cd[1]);
            BigInteger ac = recurce_sol(ab[0],cd[0]);
            System.out.println("AC = "+ ac);
            BigInteger bd = recurce_sol(ab[1],cd[1]);
            System.out.println("BD = "+ bd);
            String [] abcd1 = abcd(ab[0],ab[1],cd[0],cd[1]);
            System.out.println("Decompos a+b= "+abcd1[0]);
            System.out.println("Decompos c+d= "+abcd1[1]);
            BigInteger abcd = recurce_sol(abcd1[0],abcd1[1]);
            System.out.println("Decompos (a+b)(b+d)= "+abcd);
            sol = Kath_solve(ac,bd,abcd,lngth);
        }
        else {
            if ((x.length()<2)||(y.length()<2)){
                xy=add_zer(x,y);
            }
            else {
                xy[0]=x;
                xy[1]=y;
            }
            ab=decompos(xy[0]);
            cd=decompos(xy[1]);
            //System.out.println(ab[0]+" "+ab[1]+" "+cd[0]+" "+cd[1]);
            sol=Kath_solve(ab[0],ab[1],cd[0],cd[1]);
        }
        /*if (sol != soln(x,y)) {
            System.out.println(soln(x,y));
            System.out.println(sol);
            System.out.println("FALSE EQUAL");
        }*/
        return sol;
    }
    BigInteger soln (String n1, String n2) {
        BigInteger n11 = new BigInteger(n1) ;
        BigInteger n12 = new BigInteger(n2) ;
        return n11.multiply(n12);
    }
    /*int length(double num, int numb){
        int lng = 0;
        if (num < Math.pow(10, numb)){
            lng=length(num,numb-1);
        }
        else {
            lng=numb;
        }
        return lng;
        //System.out.println("Length = "+lng);
    }*/
    String[] add_zer(String num,String num2){
        int qrt_length;
        double lng = 1;
        String [] zer = new String[2];
        zer[0]=num;
        zer[1]=num2;

        int lng_x = num.length();
        //System.out.println("Length x = "+lng_x);
        int lng_y = num2.length();
       // System.out.println("Length y = "+lng_y);

        if (num.length()>num2.length()){
            qrt_length=num.length();
        }
        else{
            qrt_length=num2.length();
        }
        //System.out.println("Length y = "+qrt_length);

        int j=2;
        int lg=2;
        while (qrt_length>j)
        {
            lg = j*2;
            j=j*2;
        }
        // найближче значення
        System.out.println(""+num.length());
        for (int i = 0; i<lg-num.length();i++) zer[0]="0"+zer[0];
        for (int i = 0; i<lg-num2.length();i++) zer[1]="0"+zer[1];
        System.out.println("num1 = "+zer[0]);
        System.out.println("num2 = "+zer[1]);
        return zer;
    }
    String [] decompos(String num){

        String [] dec = new String[2];
        dec[0]=num.substring(0,(num.length()/2));
        dec[1]=num.substring(num.length()/2,num.length());
        return dec;
    }
    BigInteger Kath_solve(String a,String b,String c, String d){
        BigInteger solv= new BigInteger("0");
        int ac,bd,abcd,ab,cd;
        ab = Integer.parseInt(a) + Integer.parseInt(b);
        //System.out.println("ab_Kath_solve = "+ab);
        cd = Integer.parseInt(c) + Integer.parseInt(d);
        //System.out.println("cd_Kath_solve = "+cd);
        if ((ab > 9)||(cd > 9)){//додробити за алгоритмом
            int a1,b1,c1,d1,ac1,bd1,abcd1;
            a1 = ab/10;
            b1 = ab%10;
            c1 = cd/10;
            d1 = cd%10;
            ac1=a1*c1;
            bd1=b1*d1;
            abcd1=(a1+b1)*(c1+d1);
            abcd = ac1 * 100 + bd1 + (abcd1-ac1-bd1) * 10;
        }
        else abcd = ab*cd;
        ac = Integer.parseInt(a)*Integer.parseInt(c);
        //System.out.println("ac = "+ac);
        bd = Integer.parseInt(b)*Integer.parseInt(d);
        solv =solv.valueOf(ac * 100 + bd + (abcd - ac - bd)*10);
        return solv;
    }
    BigInteger Kath_solve(BigInteger ac,BigInteger bd,BigInteger abcd, int length){
        BigInteger solv= new BigInteger("0");
        BigInteger exp= new BigInteger("10");
        solv = ac.multiply(exp.pow(length)).add(bd.add(abcd.add(ac.add(bd).negate()).multiply(exp.pow(length/2))));
        return solv;
    }
    String [] abcd(String a,String b,String c, String d){
        String [] abcd = new String[2];
        BigInteger ab,cd;
        BigInteger a1 = new BigInteger(a);
        BigInteger b1 = new BigInteger(b);
        BigInteger c1 = new BigInteger(c);
        BigInteger d1 = new BigInteger(d);
        ab = a1.add(b1);
        cd = c1.add(d1);
        abcd[0] = ab.toString();
        abcd[1] = cd.toString();
        //System.out.println("abcd0 = "+abcd[0]);
        //System.out.println("abcd1 = "+abcd[1]);
        return abcd;
    }
    boolean two(int length){
        boolean two=false;
        double ln=length;
        for (int i=2;i<6;i++){
            if (ln==Math.pow(2,i))
                two=true;
        }
        return two;
    }
}

