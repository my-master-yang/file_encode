package sample.Utils;

/**
 * AES过程演示 这是按照课程中AES讲解的顺序做的 也就是按照 列 排序
 * 输入序列为：x1	x2	x3	x4	x5	x6	x7	x8	x9	x10	x11	x12	x13	x14	x15	x16
 * 矩阵排列为：
 * x1	x5	x9	x13
 * x2	x6	x10	x14
 * x3	x7	x11	x15
 * x4	x8	x12	x16
 *
 * @author Bloomeet
 * @time 2018/9/23 19:55
 */

public class AESclass {
    //     'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
//             'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f',
//             'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
//             'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', '_',
//             '~', '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '<', '>', '?', ':', '"',
//             '{', '}', '|', '[', ']', ',', '.', '/', '`', '+', '=', ' ', '\'', '\\'
    //80
    static final char[] mapString = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
            'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f',
            'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
            'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', '_',
            '~', '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '<', '>', '?', ':', '{',
    };
    //4*
    static final char[] peishi = {
            '[', ']', '+', '=',
    };

    public static String base80(int[] a) {
        String string = "";
        for (int i : a) {
            char p = peishi[i / 80];
            char b = mapString[i % 80];
            string = string + p + b;
        }
        return string;
    }


    public static int[] base80(String s) {
        String string = "";
        char[] a = s.toCharArray();
        int[] ints = new int[s.length()];
        int j = -1;
        for (int i = 0; i < a.length; i++) {
            char b = a[i];
            if (b == peishi[0]) {
                ints[++j] = index(a[++i]);
            } else if (b == peishi[1]) {
                ints[++j] = 80 + index(a[++i]);
            } else if (b == peishi[2]) {
                ints[++j] = 160 + index(a[++i]);
            } else if (b == peishi[3]) {
                ints[++j] = 240 + index(a[++i]);
            }
        }
        int[] rmZone = new int[j + 1];
        int h = 0;
        for (int c : ints) {
            // 把不为0的值写入到newArr数组里面
            if (c != 0) {
                rmZone[h] = c;
                h++;// h作为newArr数组的下标，没写如一个值，下标h加1
            }
        }
        return rmZone;
    }

    private static int index(char c) {
        int j = 0;
        while (c != mapString[j] && j < 80) {
            j++;
        }
        return j;
    }

//    static int key[] = {0x00, 0x71, 0xDA, 0x60, 0x01, 0x01, 0x79, 0x15, 0x20,
//            0x98, 0x17, 0x35, 0x01, 0xAE, 0x14, 0x94};//16进制密钥

    static int text[] = {0x00, 0x01, 0xda, 0x86, 0x01, 0xA1, 0x78, 0x15, 0x00,
            0x98, 0x17, 0x35, 0x01, 0xAF, 0x34, 0x66};//16进制明文

    //    static int key[] = {0x00, 0x01, 0x20, 0x01, 0x71, 0x01, 0x98, 0xAE, 0xDA,
//            0x79, 0x17, 0x14, 0x60, 0x15, 0x35, 0x94};//16进制密钥
//
    static int text1[] = {0x6c, 0x8f, 0xd2, 0x1a,
            0xdd, 0x56, 0x3b, 0x65,
            0x59, 0x42, 0x47, 0x42,
            0x6b, 0xcb, 0x98, 0x2a};//16进制明文


    //S盒
    static int sbox[] = {0x63, 0x7C, 0x77, 0x7B, 0xF2, 0x6B, 0x6F, 0xC5, 0x30,
            0x01, 0x67, 0x2B, 0xFE, 0xD7, 0xAB, 0x76, 0xCA, 0x82, 0xC9, 0x7D,
            0xFA, 0x59, 0x47, 0xF0, 0xAD, 0xD4, 0xA2, 0xAF, 0x9C, 0xA4, 0x72,
            0xC0, 0xB7, 0xFD, 0x93, 0x26, 0x36, 0x3F, 0xF7, 0xCC, 0x34, 0xA5,
            0xE5, 0xF1, 0x71, 0xD8, 0x31, 0x15, 0x04, 0xC7, 0x23, 0xC3, 0x18,
            0x96, 0x05, 0x9A, 0x07, 0x12, 0x80, 0xE2, 0xEB, 0x27, 0xB2, 0x75,
            0x09, 0x83, 0x2C, 0x1A, 0x1B, 0x6E, 0x5A, 0xA0, 0x52, 0x3B, 0xD6,
            0xB3, 0x29, 0xE3, 0x2F, 0x84, 0x53, 0xD1, 0x00, 0xED, 0x20, 0xFC,
            0xB1, 0x5B, 0x6A, 0xCB, 0xBE, 0x39, 0x4A, 0x4C, 0x58, 0xCF, 0xD0,
            0xEF, 0xAA, 0xFB, 0x43, 0x4D, 0x33, 0x85, 0x45, 0xF9, 0x02, 0x7F,
            0x50, 0x3C, 0x9F, 0xA8, 0x51, 0xA3, 0x40, 0x8F, 0x92, 0x9D, 0x38,
            0xF5, 0xBC, 0xB6, 0xDA, 0x21, 0x10, 0xFF, 0xF3, 0xD2, 0xCD, 0x0C,
            0x13, 0xEC, 0x5F, 0x97, 0x44, 0x17, 0xC4, 0xA7, 0x7E, 0x3D, 0x64,
            0x5D, 0x19, 0x73, 0x60, 0x81, 0x4F, 0xDC, 0x22, 0x2A, 0x90, 0x88,
            0x46, 0xEE, 0xB8, 0x14, 0xDE, 0x5E, 0x0B, 0xDB, 0xE0, 0x32, 0x3A,
            0x0A, 0x49, 0x06, 0x24, 0x5C, 0xC2, 0xD3, 0xAC, 0x62, 0x91, 0x95,
            0xE4, 0x79, 0xE7, 0xC8, 0x37, 0x6D, 0x8D, 0xD5, 0x4E, 0xA9, 0x6C,
            0x56, 0xF4, 0xEA, 0x65, 0x7A, 0xAE, 0x08, 0xBA, 0x78, 0x25, 0x2E,
            0x1C, 0xA6, 0xB4, 0xC6, 0xE8, 0xDD, 0x74, 0x1F, 0x4B, 0xBD, 0x8B,
            0x8A, 0x70, 0x3E, 0xB5, 0x66, 0x48, 0x03, 0xF6, 0x0E, 0x61, 0x35,
            0x57, 0xB9, 0x86, 0xC1, 0x1D, 0x9E, 0xE1, 0xF8, 0x98, 0x11, 0x69,
            0xD9, 0x8E, 0x94, 0x9B, 0x1E, 0x87, 0xE9, 0xCE, 0x55, 0x28, 0xDF,
            0x8C, 0xA1, 0x89, 0x0D, 0xBF, 0xE6, 0x42, 0x68, 0x41, 0x99, 0x2D,
            0x0F, 0xB0, 0x54, 0xBB, 0x16,};//S盒表

    //逆S盒
    //逆S盒
    static int sbox_R[] = {
            0x52, 0x09, 0x6A, 0xD5, 0x30, 0x36, 0xA5, 0x38, 0xBF, 0x40, 0xA3, 0x9E, 0x81, 0xF3, 0xD7, 0xFB,
            0x7C, 0xE3, 0x39, 0x82, 0x9B, 0x2F, 0xFF, 0x87, 0x34, 0x8E, 0x43, 0x44, 0xC4, 0xDE, 0xE9, 0xCB,
            0x54, 0x7B, 0x94, 0x32, 0xA6, 0xC2, 0x23, 0x3D, 0xEE, 0x4C, 0x95, 0x0B, 0x42, 0xFA, 0xC3, 0x4E,
            0x08, 0x2E, 0xA1, 0x66, 0x28, 0xD9, 0x24, 0xB2, 0x76, 0x5B, 0xA2, 0x49, 0x6D, 0x8B, 0xD1, 0x25,
            0x72, 0xF8, 0xF6, 0x64, 0x86, 0x68, 0x98, 0x16, 0xD4, 0xA4, 0x5C, 0xCC, 0x5D, 0x65, 0xB6, 0x92,
            0x6C, 0x70, 0x48, 0x50, 0xFD, 0xED, 0xB9, 0xDA, 0x5E, 0x15, 0x46, 0x57, 0xA7, 0x8D, 0x9D, 0x84,
            0x90, 0xD8, 0xAB, 0x00, 0x8C, 0xBC, 0xD3, 0x0A, 0xF7, 0xE4, 0x58, 0x05, 0xB8, 0xB3, 0x45, 0x06,
            0xD0, 0x2C, 0x1E, 0x8F, 0xCA, 0x3F, 0x0F, 0x02, 0xC1, 0xAF, 0xBD, 0x03, 0x01, 0x13, 0x8A, 0x6B,
            0x3A, 0x91, 0x11, 0x41, 0x4F, 0x67, 0xDC, 0xEA, 0x97, 0xF2, 0xCF, 0xCE, 0xF0, 0xB4, 0xE6, 0x73,
            0x96, 0xAC, 0x74, 0x22, 0xE7, 0xAD, 0x35, 0x85, 0xE2, 0xF9, 0x37, 0xE8, 0x1C, 0x75, 0xDF, 0x6E,
            0x47, 0xF1, 0x1A, 0x71, 0x1D, 0x29, 0xC5, 0x89, 0x6F, 0xB7, 0x62, 0x0E, 0xAA, 0x18, 0xBE, 0x1B,
            0xFC, 0x56, 0x3E, 0x4B, 0xC6, 0xD2, 0x79, 0x20, 0x9A, 0xDB, 0xC0, 0xFE, 0x78, 0xCD, 0x5A, 0xF4,
            0x1F, 0xDD, 0xA8, 0x33, 0x88, 0x07, 0xC7, 0x31, 0xB1, 0x12, 0x10, 0x59, 0x27, 0x80, 0xEC, 0x5F,
            0x60, 0x51, 0x7F, 0xA9, 0x19, 0xB5, 0x4A, 0x0D, 0x2D, 0xE5, 0x7A, 0x9F, 0x93, 0xC9, 0x9C, 0xEF,
            0xA0, 0xE0, 0x3B, 0x4D, 0xAE, 0x2A, 0xF5, 0xB0, 0xC8, 0xEB, 0xBB, 0x3C, 0x83, 0x53, 0x99, 0x61,
            0x17, 0x2B, 0x04, 0x7E, 0xBA, 0x77, 0xD6, 0x26, 0xE1, 0x69, 0x14, 0x63, 0x55, 0x21, 0x0C, 0x7D
    };

    static int RCon[];//密钥的常量数组
    static int word[][];//轮密钥扩展后的数组，大小为 word[44][4]

    //加密
    public static int[] Encode(int data[],int[] key) {
        keyExpansion(key);
        int[] newHex = new int[data.length];
        for (int L = 0; L < data.length / 16; L++) {
            int[] hex = new int[16];
            for (int k = 0; k < 16; k++) {
                hex[k] = data[L * 16 + k];
            }
            int i, r = 0, nu = 10;
            hex = addRoundKey(hex, 0);//初始轮密钥加
            r++;
            for (; r <= nu; r++) { // 迭代10轮
                for (i = 0; i < 16; i++) {
                    hex[i] = subByte(hex[i]);// 字节替代
                }
                hex = shiftRows(hex); // 行移位
                if (r != 10) {// 注意：AES加密最后一轮没有列混合
                    hex = mixColumn(hex);// 列混合
                }
                hex = addRoundKey(hex, r);// 轮密钥加
                if (r == 10) {
//                    System.out.println("encode:");
                    for (i = 0; i < 16; i++) {
                        newHex[L * 16 + i] = hex[i];
//                        System.out.print(Integer.toHexString(hex[i]) + " ");
//                        if (i % 4 == 3) {
//                            System.out.println();
//                        }
                    }
                }
            }
        }
        return newHex;
    }

    //解密
    public static int[] Decode(int encodeList[],int[] key) {
        keyExpansion(key);
        int[] newHex = new int[encodeList.length];
        for (int L = 0; L < encodeList.length / 16; L++) {
            int[] hex = new int[16];
            for (int k = 0; k < 16; k++) {
                hex[k] = encodeList[L * 16 + k];
            }
            int i, r = 10, nu = 10;
            hex = addRoundKey(hex, r);// 轮密钥加
            r--;
            for (; r >= 0; r--) { // 迭代10轮
                hex = shiftRows_R(hex); // 逆行移位
                for (i = 0; i < 16; i++) {
                    hex[i] = subByte_R(hex[i]);// 逆字节替代
                }
                hex = addRoundKey(hex, r);// 轮密钥加
                if (r != 0) {
                    hex = mixColumn_R(hex);// 逆列混合
                }
            }
            for (i = 0; i < 16; i++) {
                newHex[L * 16 + i] = hex[i];
//                System.out.print(Integer.toHexString(hex[i]) + " ");
//                if (i % 4 == 3) {
//                    System.out.println();
//                }
            }
        }
        return newHex;
    }

    //字符串转换和填充为16 的整数倍
    public static int[] filling(String s) {
        char[] data = s.toCharArray();
        int fill = 16 - s.length() % 16;
        int[] arr = new int[s.length() + fill];
        for (int i = 0; i < data.length; i++) {
            byte T = (byte) data[i];
            arr[i] = T;
        }
        for (int j = data.length; j < arr.length; j++) {
            arr[j] = fill;
        }
        return arr;
    }

    public static String filling(int[] s) {
        String string = "";
        for (int i = 0; i < s.length; i++) {
            if (s[i] > 16) {
                char T = (char) s[i];
                string = string + T;
            }
        }
        return string;
    }



    public static void main(String[] args) {
        String s = "yangxiong";
        //key 不能大于16位，系统设置值
        String key = "123";
        int[] keys = filling(key);
        System.out.println("keys= " + keys);
        int[] a = filling(s);
        System.out.println("原码：" + s);
        int[] encodelist = Encode(a,keys);

        String as = base80(encodelist);
        System.out.println("80位映射对应 " + as);
        int[] mm = base80(as);
        System.out.println("加密：" + mm);

        int[] decode = Decode(mm,keys);
        String decodeList = filling(decode);
        System.out.println("解密：" + decodeList);

    }
    //采用默认值加密
    public static String encode(String s1){
        String key ="yangxiong";
        int[] keys = AESclass.filling(key);
        int[] s = AESclass.filling(s1);
        int[] encodelist = AESclass.Encode(s,keys);
        return AESclass.base80(encodelist);
    }
    //采用默认值解密
    public static String decode(String s1){
        String key ="yangxiong";
        int[] keys = AESclass.filling(key);
        int[] mm = AESclass.base80(s1);
        int[] decode = AESclass.Decode(mm,keys);
        return AESclass.filling(decode);
    }

    /**
     * 密钥数组扩展
     */
    static void keyExpansion(int[] key) {
        RCon = new int[10];//轮常量为固定值
        RCon[0] = 0x01;
        RCon[1] = 0x02;
        RCon[2] = 0x04;
        RCon[3] = 0x08;
        RCon[4] = 0x10;
        RCon[5] = 0x20;
        RCon[6] = 0x40;
        RCon[7] = 0x80;
        RCon[8] = 0x1B;
        RCon[9] = 0x36;
        word = new int[44][4];//44组轮密钥
        int i, j;
        int temp[];

        for (i = 0; i < 4; i++) {
            for (j = 0; j < 4; j++) {
                word[i][j] = key[j * 4 + i]; //把初始密钥放入数组
            }
        }

        /*通过密钥计算规则计算余下数组
         *
         *1.如果i不是4的倍数，那么第i列由如下等式确定：
         *W[i]=W[i-4]⨁W[i-1]
         *2.如果i是4的倍数，那么第i列由如下等式确定：
         *W[i]=W[i-4]⨁T(W[i-1])
         *其中，T是一个有点复杂的函数。函数T由3部分组成：字循环(每次循环一位)、字节代换（s盒）和轮常量异或。
         */

        for (i = 4; i < 44; i++) {
            temp = word[i - 1];//看作W[i-1]
            if (i % 4 == 0) { //i为4的倍数 进入函数运算 W[i-1]=T(W[i-1])
                temp = subWord(rotWord(temp));
                temp[0] = temp[0] ^ RCon[i / 4 - 1];
            }
            for (j = 0; j < 4; j++) {
                word[i][j] = word[i - 4][j] ^ temp[j];//相当于W[i]=W[i-4]⨁W[i-1]
            }
        }

    }

    //密钥扩展中的移位
    static int[] rotWord(int[] word) {
        int[] rot = new int[4];
        int i;
        for (i = 0; i < 4; i++) {
            rot[i] = word[(i + 1) % 4];
        }
        return rot;
    }

    //密钥扩展中的4个字节的代换（4个字节为一组） 例如 A2 BE C4 D5
    static int[] subWord(int[] word) {
        int sub[] = new int[4];
        int i;
        for (i = 0; i < 4; i++) {
            sub[i] = subByte(word[i]);
        }
        return sub;
    }

    //S盒的单个字节代换 例如 AE
    static int subByte(int w) {
        int x = w / 16;
        int y = w % 16;
        return sbox[x * 16 + y];
    }

    static int subByte_R(int w) {
        int x = w / 16;
        int y = w % 16;
        return sbox_R[x * 16 + y];
    }

    //轮密钥加
    static int[] addRoundKey(int[] text, int round) {
        int[] add = new int[16];
        int i, j;
        for (i = 0; i < 4; i++) {
            for (j = 0; j < 4; j++) {
                //System.out.print(Integer.toHexString(word[4 * round + i][j]) + ",");//第round轮的轮密钥
                add[4 * j + i] = text[4 * j + i] ^ word[4 * round + i][j]; //逐比特异或
            }
            //System.out.println();
        }
        return add;
    }

    //行移位<<--（循环移位）规则：第0行移0位  .....  第3行移3位
    static int[] shiftRows(int[] text) {
        int[] shift = new int[16];
        int i, j;
        for (i = 0; i < 4; i++) {
            for (j = 0; j < 4; j++) {
                if ((4 * i + j + i) == 8 || (4 * i + j + i) == 12 || (4 * i + j + i) == 13 || (4 * i + j + i) > 15) {
                    shift[4 * i + j] = text[(4 * i + j + i) - 4];
                } else {
                    shift[4 * i + j] = text[4 * i + j + i];
                }
            }
        }
        return shift;
    }

    //逆行移位-->>（循环移位）规则：第0行移0位  .....  第3行移3位
    static int[] shiftRows_R(int[] text) {
        int[] shift = new int[16];
        int i, j;
//        System.out.println("qian<<<");
//        for (i = 0; i < 16; i++) {
//            System.out.print(Integer.toHexString(text[i]) + " ");
//            if (i % 4 == 3) {
//                System.out.println();
//            }
//        }
        for (i = 0; i < 4; i++) {
            for (j = 0; j < 4; j++) {
                if ((4 * i + j + i) == 8 || (4 * i + j + i) == 12 || (4 * i + j + i) == 13 || (4 * i + j + i) > 15) {
                    shift[4 * i + j - (4 - i)] = text[4 * i + j];
                } else {
                    shift[4 * i + j + i] = text[4 * i + j];
                }
            }
        }
//        System.out.println(">>>>>>>");
//        for (i = 0; i < 16; i++) {
//            System.out.print(Integer.toHexString(shift[i]) + " ");
//            if (i % 4 == 3) {
//                System.out.println();
//            }
//        }
        return shift;
    }


    //列混合
    static int[] mixColumn(int[] text) {
        int[] mix = new int[16];
        int[] mass = {2, 3, 1, 1,
                1, 2, 3, 1,
                1, 1, 2, 3,
                3, 1, 1, 2
        };
        int i, j, u;
        for (i = 0; i < 16; i++) {
            u = 0;
            for (j = 0; j < 4; j++) {
                u = u ^ fieldMulit(mass[(i / 4) * 4 + j], text[4 * j + i % 4]);
            }
            mix[i] = u;
        }
        return mix;
    }

    //逆列混合
    static int[] mixColumn_R(int[] text) {
        int[] mix = new int[16];
        int[] mass = {0x0E, 0x0B, 0x0D, 0x09,
                0x09, 0x0E, 0x0B, 0x0D,
                0x0D, 0x09, 0x0E, 0x0B,
                0x0B, 0x0D, 0x09, 0x0E};  //使用列混合矩阵的逆矩阵
        int i, j, u;
        for (i = 0; i < 16; i++) {
            u = 0;
            for (j = 0; j < 4; j++) {
                u = u ^ fieldMulit(mass[(i / 4) * 4 + j], text[4 * j + i % 4]);
            }
            mix[i] = u;
        }
        return mix;
    }


    static int fieldMulit(int x, int y) {
        String xString = Integer.toBinaryString(x);
        int i, j, mul = 0, tem = y;
        for (i = 0; i < xString.length(); i++) {
            if (xString.charAt(i) == '1') {
                for (j = 1; j < xString.length() - i; j++) {
                    tem = tem << 1;
                    if (tem > 255) {
                        tem = tem % 256;
//                        tem = tem ^ 0x1b;//00011011
                        tem = tem ^ 0x1b;//00011011
                    }
                }
                mul = mul ^ tem;
                tem = y;
            }
        }
        return mul;
    }


}