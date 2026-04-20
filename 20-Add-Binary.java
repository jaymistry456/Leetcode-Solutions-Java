// https://leetcode.com/problems/add-binary/


// given: two binary strings a and b
// required: return their sum as a binary string

// constraints
// length of both strings in [1, 10k]
// both strings contain only '0'/'1'
class Solution {
    public String addBinary(String a, String b) {
        StringBuilder result = new StringBuilder("");

        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;

        while(i >= 0 || j >= 0) {
            if(i >= 0 && j >= 0) {
                int aNum = a.charAt(i) - '0';
                int bNum = b.charAt(j) - '0';
                int sum = aNum + bNum + carry;
                if(sum >= 2) {
                    carry = 1;
                    result.append(String.valueOf(sum % 2));
                }
                else {
                    carry = 0;
                    result.append(String.valueOf(sum));
                }
                i--;
                j--;
            }
            else if(i >= 0) {
                int aNum = a.charAt(i) - '0';
                int sum = aNum + carry;
                if(sum == 2) {
                    carry = 1;
                    result.append(String.valueOf(sum % 2));
                }
                else {
                    carry = 0;
                    result.append(String.valueOf(sum));
                }
                i--;
            }
            else if(j >= 0) {
                int bNum = b.charAt(j) - '0';
                int sum = bNum + carry;
                if(sum == 2) {
                    carry = 1;
                    result.append(String.valueOf(sum % 2));
                }
                else {
                    carry = 0;
                    result.append(String.valueOf(sum));
                }
                j--;
            }
        }

        if(carry == 1) {
            result.append("1");
        }

        return result.reverse().toString();
    }
}