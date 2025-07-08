package com.devnilesh.journalapp.controller;

import com.devnilesh.journalapp.controller.entry.User;
import com.devnilesh.journalapp.service.UserEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")       //puri class pr mapping add k hai
public class UserEntryController {


    @Autowired
    private UserEntryService userEntryService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers()
    {
        return new ResponseEntity<>(userEntryService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user)
    {
       try{
           userEntryService.saveEntry(user);
           return new ResponseEntity<>(user,HttpStatus.CREATED);
       }
       catch (Exception e) {
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
    }


    @PutMapping("/{username}")
    public ResponseEntity<?> updateUser(@RequestBody User user,@PathVariable String username )
    {
        User userInDb = userEntryService.findByUserName(username);
        if(userInDb !=null )
        {
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userEntryService.saveEntry(userInDb);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}


/*For a PUT request: HTTP 200, HTTP 204 should imply "resource updated successfully".
 HTTP 201 if the PUT request created a new resource.
 */