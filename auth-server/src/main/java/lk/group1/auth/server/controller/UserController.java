package lk.group1.auth.server.controller;


import lk.group1.auth.server.model.User;
import lk.group1.auth.server.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/oauth")
public class UserController {
    @Autowired
    private UserDetailServiceImpl userDetailServiceimpl;


    public UserController(UserDetailServiceImpl userDetailServiceimpl) {
         this.userDetailServiceimpl=userDetailServiceimpl;
    }


    //create new user
    @RequestMapping(value="/user",method= RequestMethod.POST)
    public User saveUser(@RequestBody User user){


         return userDetailServiceimpl.save(user);
      }

    //update existing user ( only user name )
    @RequestMapping(value="/updateUser/{id}",method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @Valid @RequestBody User user){

        User tempUser = new User();
        tempUser.setId(id);
        User updatedUser = userDetailServiceimpl.fetchUsers(tempUser);

        if (!userDetailServiceimpl.findById(id).isPresent()){
            ResponseEntity.badRequest().build();
        }

        updatedUser.setUsername(user.getUsername());


        return ResponseEntity.ok(userDetailServiceimpl.save(updatedUser));

    }

    //delete existing user
    @RequestMapping(value = "/deleteUser/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUsers(@PathVariable Integer id){

        if (!userDetailServiceimpl.findById(id).isPresent()){
            ResponseEntity.badRequest().build();
        }

        userDetailServiceimpl.deteteById(id);

        return ResponseEntity.ok().build();
    }

}
