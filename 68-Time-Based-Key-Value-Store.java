// https://leetcode.com/problems/time-based-key-value-store/


// given: a TimeMap class
// required: implement the class and its methods such we can store multiple values for the same key at different timestamps and retrieve the key's value at a certain timestamp

// constraints
// keys, values length in [1, 100]
// key, value contain lowercase letters only
// timestamp in [1, 10M]
// all timestamps are strictly increasing -> important
// at most 100k calls will be made to set and get

class TimeValue {
    String value;
    int timestamp;

    public TimeValue(String value, int timestamp) {
        this.value = value;
        this.timestamp = timestamp;
    }

    public String getValue() {
        return value;
    }

    public int getTimestamp() {
        return timestamp;
    }
}

class TimeMap {
    Map<String, List<TimeValue>> map;

    public TimeMap() {
        map = new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {
        if(!map.containsKey(key)) {
            map.put(key, new ArrayList<>());
        }
        map.get(key).add(new TimeValue(value, timestamp));
    }
    
    // tc: O(timeValues), sc: O(1)
    public String get(String key, int timestamp) {
        List<TimeValue> timeValues = map.get(key);

        String result = "";
        for(TimeValue timeValue: timeValues) {
            if(timeValue.getTimestamp() <= timestamp) {
                result = timeValue.getValue();
            }
        }

        return result;
    }
}




class TimeValue {
    String value;
    int timestamp;

    public TimeValue(String value, int timestamp) {
        this.value = value;
        this.timestamp = timestamp;
    }

    public String getValue() {
        return value;
    }

    public int getTimestamp() {
        return timestamp;
    }
}

class TimeMap {
    Map<String, List<TimeValue>> map;

    public TimeMap() {
        map = new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {
        if(!map.containsKey(key)) {
            map.put(key, new ArrayList<>());
        }
        map.get(key).add(new TimeValue(value, timestamp));
    }
    
    // tc: O(log(timeValues)), sc: O(1)
    public String get(String key, int timestamp) {
        List<TimeValue> timeValues = map.get(key);

        if(timeValues == null) {
            return "";
        }

        String result = "";
        int start = 0;
        int end = timeValues.size() - 1;
        
        while(start <= end) {
            int mid = start + (end - start) / 2;

            if(timeValues.get(mid).getTimestamp() <= timestamp) {
                result = timeValues.get(mid).getValue();
                start = mid + 1;
            }
            else {
                end = mid - 1;
            }
        }

        return result;
    }
}



/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */