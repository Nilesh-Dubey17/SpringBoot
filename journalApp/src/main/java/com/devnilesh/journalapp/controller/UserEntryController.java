package com.devnilesh.journalapp.controller;

import com.devnilesh.journalapp.controller.entry.User;
import com.devnilesh.journalapp.repository.UserEntryRepository;
import com.devnilesh.journalapp.service.UserEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")       //puri class pr mapping add k hai
public class UserEntryController {


    @Autowired
    private UserEntryService userEntryService;

    @Autowired
    UserEntryRepository userEntryRepository;

    @GetMapping("/getOwnDetails")
    public ResponseEntity<User> getAllUsers()
    {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String name = authentication.getName();
            return new ResponseEntity<>(userEntryService.findByUserName(name), HttpStatus.OK);
    }


    @PutMapping("/updateUser")
    public ResponseEntity<?> updateUser(@RequestBody User user )
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        User userInDb = userEntryService.findByUserName(userName);
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userEntryService.saveNewUser(userInDb);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<?> deleteUser()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userEntryRepository.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}


/*For a PUT request: HTTP 200, HTTP 204 should imply "resource updated successfully".
 HTTP 201 if the PUT request created a new resource.
 */