package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
//@RequestMapping("time-entries/") // Use this as the root, {x} can be done with annotations per method
public class TimeEntryController {

    // Declaration of our repository object to instantiate
    private TimeEntryRepository repo;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.repo = timeEntryRepository;
    }

    // Annotation maps to the expected HTTP request type
    @PostMapping("/time-entries")
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {
        TimeEntry timeEntry = repo.create(timeEntryToCreate);
        return new ResponseEntity(timeEntry,HttpStatus.CREATED);
    }

    @GetMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable long timeEntryId) {
        TimeEntry timeEntry = repo.find(timeEntryId);
        if(timeEntry==null){
            return new ResponseEntity(null,HttpStatus.NOT_FOUND);

        }else{
            return new ResponseEntity(timeEntry,HttpStatus.OK);
        }
    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> timeEntryList = repo.list();
        return new ResponseEntity<>(timeEntryList, HttpStatus.OK);
    }

    @PutMapping("/time-entries/{timeEntryId}")
    public ResponseEntity update(@PathVariable long timeEntryId, @RequestBody TimeEntry timeEntry) {
        TimeEntry timeEntryReturned = repo.update(timeEntryId,timeEntry);

        if (timeEntryReturned == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity(timeEntryReturned, HttpStatus.OK);
        }
    }
    @DeleteMapping("/time-entries/{timeEntryId}")
    public ResponseEntity delete(@PathVariable long timeEntryId) {
        repo.delete(timeEntryId);
        return new ResponseEntity(null, HttpStatus.NO_CONTENT);
    }
}
