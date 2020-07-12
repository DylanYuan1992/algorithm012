class Solution {
  public int[] twoSum(int[] nums, int target) {
    int length = nums.length;
    Map<Integer, Integer> map = new HashMap<>(length);
    for (int i = 0; i < length; i++) {
      Integer other = map.get(target - nums[i]);
      if (other != null) {
        return new int[] { other, i };
      }
      map.put(nums[i], i);
    }
    return null;
  }
}