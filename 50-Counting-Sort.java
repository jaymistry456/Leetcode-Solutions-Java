// tc: O(n + k), sc: O(k) where k = maxVal - minVal + 1
class Main {
    public static void countingSort(int[] nums) {
        int minVal = Integer.MAX_VALUE;
        int maxVal = Integer.MIN_VALUE;
        for(int num: nums) {
            minVal = Math.min(minVal, num);
            maxVal = Math.max(maxVal, num);
        }
        
        int[] freq = new int[maxVal - minVal + 1];
        for(int num: nums) {
            freq[num - minVal]++;
        }
        
        int j = 0;
        for(int i = minVal; i <= maxVal; i++) {
            int currFreq = freq[i - minVal];
            while(currFreq != 0) {
                nums[j] = i;
                j++;
                currFreq--;
            }
        }
    }
    
    public static void main(String[] args) {
        int[] nums = new int[]{7, 6, 5, 4, 3, 2, 1};
        
        countingSort(nums);
        
        for(int num: nums) {
            System.out.print(num + " ");
        }
    }
}