package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTimeEntryRepository implements TimeEntryRepository{
    private Map<Long, TimeEntry> map = new HashMap<>();
    private Long counter=0L;

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        counter++;
        timeEntry.setId(counter);
        map.put(counter,timeEntry);
        return timeEntry;
    }

    @Override
    public TimeEntry find(long timeEntryId) {
        return map.get(timeEntryId);

    }

    @Override
    public List<TimeEntry> list() {
        ArrayList<TimeEntry> al = new ArrayList<>(map.values());

        return al; //return a list
    }

    @Override
    public TimeEntry update(long id, TimeEntry anyTimeEntry) {

        if (map.containsKey(id) ){
            anyTimeEntry.setId(id);
            map.put(id, anyTimeEntry);
            return anyTimeEntry;
        }
        return null;
    }

    @Override
    public void delete(long timeEntryId) {
        map.remove(timeEntryId);
    }
}
