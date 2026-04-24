// tc: O(n^2), sc: O(1)
class Main {
    public static void insertionSort(int[] nums) {
        for(int i = 0; i < nums.length; i++) {
            int temp = nums[i];
            
            int j = i - 1;
            while(j >= 0 && nums[j] > temp) {
                nums[j + 1] = nums[j];
                j--;
            }
        
            nums[j + 1] = temp;
        }
    }
    
    public static void main(String[] args) {
        int[] nums = new int[]{7, 6, 5, 4, 3, 2, 1};
        
        insertionSort(nums);
        
        for(int num: nums) {
            System.out.print(num + " ");
        }
    }
}