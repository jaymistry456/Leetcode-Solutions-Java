// tc: O(n^2), sc: O(1)
class Main {
    public static void bubbleSort(int[] nums) {
        for(int i = 0; i < nums.length; i++) {
            for(int j = i + 1; j < nums.length; j++) {
                if(nums[j] < nums[i]) {
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
            }
        }
    }
    
    public static void main(String[] args) {
        int[] nums = new int[]{7, 6, 5, 4, 3, 2, 1};
        
        bubbleSort(nums);
        
        for(int num: nums) {
            System.out.print(num + " ");
        }
    }
}